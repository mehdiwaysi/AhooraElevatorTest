package com.snayab.ahooraelevator.ui.code_verification.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.databinding.ActivityVerificationCodeBinding;
import com.snayab.ahooraelevator.dialog.LoadingDialog;
import com.snayab.ahooraelevator.helpers.RxJavaCountDownTimer;
import com.snayab.ahooraelevator.helpers.SnackBarHelper;
import com.snayab.ahooraelevator.receiver.sms.SmsBroadCastReciver;
import com.snayab.ahooraelevator.shared_preferences.SharedPreferencesHelper;
import com.snayab.ahooraelevator.ui.base.BaseActivity;
import com.snayab.ahooraelevator.ui.code_verification.model.UserAuthVerifyResendResponse;
import com.snayab.ahooraelevator.ui.code_verification.model.UserAuthVerifyResponse;
import com.snayab.ahooraelevator.ui.code_verification.view_model.CodeVerificationActivityViewModel;
import com.snayab.ahooraelevator.ui.enter.view.UserEnterActivity;
import com.snayab.ahooraelevator.ui.main.view.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CodeVerificationActivity extends BaseActivity implements View.OnClickListener {


    private CompositeDisposable compositeDisposable;
    private CodeVerificationActivityViewModel codeVerificationActivityViewModel;
    private String TAG = getClass().getName();
    private SharedPreferencesHelper sharedPreferencesHelper;
    //SmsRetriever
    public static final int CODE_INTENT_FOR_RESULT = 1;
    private SmsBroadCastReciver smsBroadCastReciver;

    private ActivityVerificationCodeBinding binding;
    private SnackBarHelper snackBarHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVerificationCodeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        //change status bar color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.status));
        }
        sharedPreferencesHelper = new SharedPreferencesHelper(this);

        if (getHasUserNameFromIntent()) {
            hideNameFieldField();
        }
        initObjects();
        setupViews();
        startTimer(30L);

        startSmsBroadCast();
    }

    private String getUserPhoneFromIntent() {
        return getIntent().hasExtra("phone") ? getIntent().getExtras().getString("phone") : null;
    }

    private Boolean getHasUserNameFromIntent() {
        return getIntent().hasExtra("has_name") && getIntent().getExtras().getBoolean("has_name");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnVerifyCode: {

                if (binding.edtVerificationCode.getText().length() > 2) {
                    binding.btnVerifyCode.setEnabled(false);
                    binding.btnVerifyCode.setAlpha(0.5f);
                    if (!getHasUserNameFromIntent()) {
                        if (binding.edtNameAndFamily.getText().toString().length() > 2) {
                            observeForUserAuthVerifyResponse(Integer.parseInt(binding.edtVerificationCode.getText().toString()), getUserPhoneFromIntent(), binding.edtNameAndFamily.getText().toString().trim());
                        } else {
                            SnackBarHelper.showSnackBar(this, binding.btnVerifyCode, "نام و نام خانوادگی خود را بدرستی وارد نمایید!", Snackbar.LENGTH_LONG);
                        }
                    } else {
                        observeForUserAuthVerifyResponse(Integer.parseInt(binding.edtVerificationCode.getText().toString()), getUserPhoneFromIntent(), null);
                    }

                } else {
                    SnackBarHelper.showSnackBar(this, binding.btnVerifyCode, "کد دریافتی را وارد کنید!", Snackbar.LENGTH_LONG);
                }


                break;
            }
            case R.id.txtSendVerificationCodeAgain: {
                observeForUserAuthVerifyResendResponse(getUserPhoneFromIntent());
                startTimer(30L);
                setTxtSendVerificationCodeAgainInability(false);
                break;
            }
            case R.id.txtChangeNumber: {
                Intent intent = new Intent(CodeVerificationActivity.this, UserEnterActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }

    }

    private void setupViews() {
        binding.btnVerifyCode.setOnClickListener(this);
        binding.txtSendVerificationCodeAgain.setOnClickListener(this);
        binding.txtChangeNumber.setOnClickListener(this);

    }

    private void initObjects() {
        compositeDisposable = new CompositeDisposable();
        codeVerificationActivityViewModel = new CodeVerificationActivityViewModel();
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    private void hideNameFieldField() {
        binding.txtNameAndFamily.setVisibility(View.GONE);
        binding.edtNameAndFamily.setVisibility(View.GONE);
    }


    private void observeForUserAuthVerifyResponse(int verification_code, String phone, String name) {

        codeVerificationActivityViewModel.authVerify(verification_code, phone, name).subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<UserAuthVerifyResponse>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull UserAuthVerifyResponse userAuthVerifyResponse) {

                try {
                    Integer status = userAuthVerifyResponse.getStatus();

                    if (status == 200 || status == 201 || status == 202) {
                        String apiToken = userAuthVerifyResponse.getToken();
                        Integer userId = userAuthVerifyResponse.getUser().getUser_id();
                        sharedPreferencesHelper.setUserId(userId);
                        sharedPreferencesHelper.setUserName(userAuthVerifyResponse.getUser().getName());
                        sharedPreferencesHelper.setUserPhone(userAuthVerifyResponse.getUser().getPhone());
                        sharedPreferencesHelper.setUserApiToken(apiToken);
                        startActivity(new Intent(CodeVerificationActivity.this, MainActivity.class));
                        if (smsBroadCastReciver != null) {
                            unregisterReceiver(smsBroadCastReciver);
                        }
                        sharedPreferencesHelper.setHasUserSignedIn(true);

                        finish();

                    } else {
                        SnackBarHelper.showSnackBar(CodeVerificationActivity.this, binding.btnVerifyCode, "لطفا کد فعال سازی را صحیح وارد نمایید", Snackbar.LENGTH_LONG);

                    }
                    binding.btnVerifyCode.setEnabled(true);
                    binding.btnVerifyCode.setAlpha(1f);

                } catch (Exception e) {

                }

            }

            @Override
            public void onError(@NotNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
                binding.btnVerifyCode.setEnabled(true);
                binding.btnVerifyCode.setAlpha(1f);
            }
        });
    }

    private void observeForUserAuthVerifyResendResponse(String phone) {

        codeVerificationActivityViewModel.resendVerificationCode(phone).subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<UserAuthVerifyResendResponse>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull UserAuthVerifyResendResponse userAuthVerifyResendResponse) {

            }

            @Override
            public void onError(@NotNull Throwable e) {

            }
        });
    }


    private void startTimer(Long startTimeValue) {
        new RxJavaCountDownTimer(startTimeValue, TimeUnit.SECONDS) {
            @Override
            public void onTick(long tickValue) {
                binding.txtTimer.setText(tickValue + "");
            }

            @Override
            public void onFinish() {
                setTxtSendVerificationCodeAgainInability(true);
            }
        }.start();
    }

    private void setTxtSendVerificationCodeAgainInability(boolean inability) {
        if (inability) {
            binding.txtSendVerificationCodeAgain.setClickable(true);
            binding.txtSendVerificationCodeAgain.setFocusable(true);
            binding.txtSendVerificationCodeAgain.setEnabled(true);
            binding.txtSendVerificationCodeAgain.setTextColor(ContextCompat.getColor(this, R.color.colorDanger));
        } else {
            binding.txtSendVerificationCodeAgain.setClickable(false);
            binding.txtSendVerificationCodeAgain.setFocusable(false);
            binding.txtSendVerificationCodeAgain.setEnabled(false);
            binding.txtSendVerificationCodeAgain.setTextColor(ContextCompat.getColor(this, R.color.md_red_200));
        }
    }


    private void startSmsBroadCast() {
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        client.startSmsUserConsent("").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_INTENT_FOR_RESULT) {
            if (resultCode == RESULT_OK && data != null) {
                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                getOtpFromMessage(message);
            }
        }
    }

    private void RegisterBrodcastReciverSms() {
        smsBroadCastReciver = new SmsBroadCastReciver();
        smsBroadCastReciver.smsBroadcastInterface = new SmsBroadCastReciver.SmsBroadcastInterface() {
            @Override
            public void onsucess(String message) {
                getOtpFromMessage(message);
            }

            @Override
            public void error() {

            }
        };


        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(smsBroadCastReciver, intentFilter);

        SmsRetrieverClient client = SmsRetriever.getClient(this);
        Task<Void> task = client.startSmsRetriever();

        task.addOnSuccessListener(aVoid -> {

        });

        task.addOnFailureListener(e -> {
        });


    }


    private void getOtpFromMessage(String message) {
        final LoadingDialog loadingDialog = new LoadingDialog(this);

        Pattern pattern = Pattern.compile("(|^)\\d{4}");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {

            binding.edtVerificationCode.setText(matcher.group(0));
            if (binding.edtNameAndFamily.getVisibility() == View.GONE) {
                loadingDialog.show();
                binding.btnVerifyCode.setEnabled(false);
                binding.btnVerifyCode.setAlpha(0.5f);
                observeForUserAuthVerifyResponse(Integer.parseInt(binding.edtVerificationCode.getText().toString()), getUserPhoneFromIntent(), binding.edtNameAndFamily.getText().toString());

            }

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        RegisterBrodcastReciverSms();
    }


    @Override
    public void onBackPressed() {

        if (smsBroadCastReciver != null) {
            unregisterReceiver(smsBroadCastReciver);
        }

        finish();
    }

}