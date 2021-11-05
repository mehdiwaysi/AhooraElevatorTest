package com.snayab.ahooraelevator.ui.price_request.view;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.databinding.ActivityRequestPriceBinding;
import com.snayab.ahooraelevator.dialog.DialogMessageConfirm;
import com.snayab.ahooraelevator.helpers.SnackBarHelper;
import com.snayab.ahooraelevator.shared_preferences.SharedPreferencesHelper;
import com.snayab.ahooraelevator.ui.base.BaseActivity;
import com.snayab.ahooraelevator.ui.price_request.model.RequestPriceRequest;
import com.snayab.ahooraelevator.ui.price_request.model.RequestPriceResponse;
import com.snayab.ahooraelevator.ui.price_request.view_model.RequestPriceViewModel;

import org.jetbrains.annotations.NotNull;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class RequestPriceActivity extends BaseActivity implements View.OnClickListener {

    private ActivityRequestPriceBinding binding;
    private RequestPriceViewModel requestPriceViewModel;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private CompositeDisposable compositeDisposable;
    private String TAG = this.getClass().getName();
    private DialogMessageConfirm dialogMessageConfirm;
    private RequestPriceRequest requestPriceRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRequestPriceBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initObjects();
        initView();


    }


    private void initObjects() {
        requestPriceViewModel = new RequestPriceViewModel();
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        compositeDisposable = new CompositeDisposable();
        requestPriceRequest = new RequestPriceRequest();
    }

    private void initView() {
        binding.btnConfirm.setOnClickListener(this);
        binding.imageViewBack.setOnClickListener(this);
    }

    private void observeForRequestPrice(RequestPriceRequest requestPriceRequest) {

        requestPriceViewModel.requestPrice(requestPriceRequest).subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                binding.lottieAnimationView.setVisibility(View.GONE);
                binding.lottieAnimationView.cancelAnimation();
            }
        }).subscribe(new SingleObserver<RequestPriceResponse>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull RequestPriceResponse requestPriceResponse) {

                if (requestPriceResponse.getStatus() == 201) {
                    showMessageSendRequest(201, "موفق", "درخواست شما ثبت شد، لطفا منتظر تماس کارشناسان باشید!");
                }

            }

            @Override
            public void onError(@NotNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());

                if (((HttpException) e).code() == 429) {
                    showMessageSendRequest(429, "درخواست تکراری", "درخواست شما ثبت شده است، لطفا منتظر تماس کارشناسان باشید!");

                } else {
                    SnackBarHelper.showSnackBar(RequestPriceActivity.this, binding.getRoot(), "مشکلی رخ داده است، لطفا بعدا امتحان کنید!", Snackbar.LENGTH_LONG);
                }


            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnConfirm: {

                if (binding.etNumber.getText().toString().length() < 1) {
                    SnackBarHelper.showSnackBar(RequestPriceActivity.this, binding.getRoot(), "تعداد آسانسور را بدرستی وارد کنید!", Snackbar.LENGTH_LONG);

                } else if (binding.etFloor.getText().toString().length() < 1) {
                    SnackBarHelper.showSnackBar(RequestPriceActivity.this, binding.getRoot(), "تعداد طبقه را بدرستی وارد کنید!", Snackbar.LENGTH_LONG);

                } else if (binding.etCapacity.getText().toString().length() < 1) {
                    SnackBarHelper.showSnackBar(RequestPriceActivity.this, binding.getRoot(), "گنجایش  را بدرستی وارد کنید!", Snackbar.LENGTH_LONG);

                } else {

                    dialogMessageConfirm = new DialogMessageConfirm(this, DialogMessageConfirm.DialogType.DIALOG_MESSAGE_INFO);

                    dialogMessageConfirm.setCancelable(false);
                    dialogMessageConfirm.setConfirmText("بله")
                            .setDecriptionVisibility(View.GONE)
                            .setCancelText("خیر")
                            .setTitle("آیا مطمئن هستید؟")
                            .setOnCancelClick(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialogMessageConfirm.dismiss();
                                }
                            }).setOnConfirmClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            requestPriceRequest.setUser_id(sharedPreferencesHelper.getUserId());
                            requestPriceRequest.setElevators(Byte.parseByte(binding.etNumber.getText().toString()));
                            requestPriceRequest.setFloors(Byte.parseByte(binding.etFloor.getText().toString()));
                            requestPriceRequest.setCapacity(Byte.parseByte(binding.etCapacity.getText().toString()));
                            requestPriceRequest.setPits(binding.radioButtonTrue.isChecked());

                            observeForRequestPrice(requestPriceRequest);

                        }
                    }).show();

                }
                break;
            }

            case R.id.imageViewBack: {
                onBackPressed();
                break;
            }


        }
    }


    private void showMessageSendRequest(int type, String title, String description) {
        if (type == 201) {
            dialogMessageConfirm = new DialogMessageConfirm(this, DialogMessageConfirm.DialogType.DIALOG_MESSAGE_SUCCESS);

        } else if (type == 429) {
            dialogMessageConfirm = new DialogMessageConfirm(this, DialogMessageConfirm.DialogType.DIALOG_MESSAGE_WARNING);
        }

        dialogMessageConfirm.setCancelable(false);
        dialogMessageConfirm.setConfirmText("متوجه شدم")
                .setTitle(title)
                .setDescription(description)
                .setCancelVisibility(View.GONE)

                .setOnConfirmClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMessageConfirm.dismiss();
                        finish();
                    }
                }).show();

    }

}