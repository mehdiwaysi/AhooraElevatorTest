package com.snayab.ahooraelevator.ui.enter.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.snayab.ahooraelevator.BuildConfig;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.databinding.ActivityEnterUserBinding;

import com.snayab.ahooraelevator.helpers.SnackBarHelper;
import com.snayab.ahooraelevator.receiver.sms.AppSignatureHelper;
import com.snayab.ahooraelevator.ui.base.BaseActivity;
import com.snayab.ahooraelevator.ui.code_verification.view.CodeVerificationActivity;
import com.snayab.ahooraelevator.ui.enter.model.UserAuthEnterResponseModel;
import com.snayab.ahooraelevator.ui.enter.view_model.UserEnterActivityViewModel;


import org.jetbrains.annotations.NotNull;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserEnterActivity extends BaseActivity implements View.OnClickListener {

    AppSignatureHelper appSignatureHelper;

    private UserEnterActivityViewModel userEnterActivityViewModel;
    private CompositeDisposable compositeDisposable;
    private String TAG = getClass().getName();

    ActivityEnterUserBinding binding;
    private SnackBarHelper snackBarHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEnterUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //change status bar color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.status));
        }
        appSignatureHelper = new AppSignatureHelper(UserEnterActivity.this);
        Log.v(TAG, "appSignatureHelper: " + appSignatureHelper.getAppSignatures().get(0));

        initObjects();
        setupViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConfirm: {
                if ((binding.edtUserPhone.getText().toString().startsWith("9") && binding.edtUserPhone.getText().length() == 10) ||
                        binding.edtUserPhone.getText().toString().startsWith("0") && binding.edtUserPhone.getText().length() == 11) {
                    Log.e(TAG, "vbcbvcvbvbcvbcvcvb: " + removeZeroInStartOfPhoneNumber(binding.edtUserPhone.getText().toString()));
                    binding.btnConfirm.setEnabled(false);
                    binding.btnConfirm.setAlpha(0.5f);

                    observeForUserAuthEnterResponse(removeZeroInStartOfPhoneNumber(binding.edtUserPhone.getText().toString()), appSignatureHelper.getAppSignatures().get(0), BuildConfig.APP_VERSION_ID);

                } else {
                    SnackBarHelper.showSnackBar(this, binding.btnConfirm, "شماره موبایل اشتباه است!", Snackbar.LENGTH_LONG);

//                 int b =10;
//                 int a = 100 / 0;
                }
                break;
            }
        }
    }

    private void initObjects() {
        userEnterActivityViewModel = new UserEnterActivityViewModel();
        compositeDisposable = new CompositeDisposable();

    }

    private void setupViews() {
        binding.btnConfirm.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }


    private void observeForUserAuthEnterResponse(String phone, String hash_code, int version_id) {
        userEnterActivityViewModel.authEnter(phone, hash_code, version_id).subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<UserAuthEnterResponseModel>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                compositeDisposable.add(d);
            }


            @Override
            public void onSuccess(@NotNull @NonNull UserAuthEnterResponseModel userAuthEnterResponseModel) {
                if (userAuthEnterResponseModel.getStatus() == 200 || userAuthEnterResponseModel.getStatus() == 201) {
                    Intent intent = new Intent(UserEnterActivity.this, CodeVerificationActivity.class);

                    intent.putExtra("has_name", userAuthEnterResponseModel.getHas_name());

                    intent.putExtra("phone", removeZeroInStartOfPhoneNumber(binding.edtUserPhone.getText().toString()));
                    startActivity(intent);
                    binding.btnConfirm.setEnabled(true);
                    binding.btnConfirm.setAlpha(1f);
                    finish();
                } else {
                    binding.btnConfirm.setEnabled(true);
                    binding.btnConfirm.setAlpha(1f);
                    SnackBarHelper.showSnackBar(UserEnterActivity.this, binding.btnConfirm, "خطایی رخ داده است!", Snackbar.LENGTH_LONG);

                }
            }

            @Override
            public void onError(@NotNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
                SnackBarHelper.showSnackBar(UserEnterActivity.this, binding.btnConfirm, "خطایی رخ داده است!", Snackbar.LENGTH_LONG);

                binding.btnConfirm.setEnabled(true);
                binding.btnConfirm.setAlpha(1);
            }
        });
    }

    private boolean isPhoneNumberStartWithZero(String phoneNumber) {
        return phoneNumber.startsWith("0");
    }

    private String removeZeroInStartOfPhoneNumber(String phoneNumber) {
        if (isPhoneNumberStartWithZero(phoneNumber)) {
            return phoneNumber.substring(1);
        } else {
            return phoneNumber;
        }

    }


}