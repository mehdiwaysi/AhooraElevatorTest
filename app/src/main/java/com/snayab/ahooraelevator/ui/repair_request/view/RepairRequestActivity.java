package com.snayab.ahooraelevator.ui.repair_request.view;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;

import com.google.android.material.snackbar.Snackbar;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.databinding.ActivityRepairRequestBinding;
import com.snayab.ahooraelevator.dialog.DialogMessageConfirm;
import com.snayab.ahooraelevator.helpers.SnackBarHelper;
import com.snayab.ahooraelevator.shared_preferences.SharedPreferencesHelper;
import com.snayab.ahooraelevator.ui.adapter.AdapterStringSpinner;
import com.snayab.ahooraelevator.ui.base.BaseActivity;
import com.snayab.ahooraelevator.ui.repair_request.model.ElevatorFailureResponse;
import com.snayab.ahooraelevator.ui.repair_request.model.RepairRequestResponse;
import com.snayab.ahooraelevator.ui.repair_request.view_model.RepairRequestViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class RepairRequestActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = this.getClass().getName();
    private ActivityRepairRequestBinding binding;
    private RepairRequestViewModel repairRequestViewModel;
    private CompositeDisposable compositeDisposable;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private Integer contractId, elevatorFailureId;
    private ArrayList<String> elevatorFailureList;
    private DialogMessageConfirm dialogMessageConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRepairRequestBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initView();
        initObjects();
        observeForGetElevatorFailures();

    }


    private void initView() {
        binding.btnConfirm.setOnClickListener(this);
        binding.imageViewBack.setOnClickListener(this);


    }

    private void initObjects() {
        repairRequestViewModel = new RepairRequestViewModel();
        compositeDisposable = new CompositeDisposable();
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        elevatorFailureList = new ArrayList<>();

    }

    private void observeForGetElevatorFailures() {
        repairRequestViewModel.getElevatorFailures().subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<ElevatorFailureResponse>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull ElevatorFailureResponse elevatorFailureResponse) {

                elevatorFailureList.clear();
                elevatorFailureList.add("لطفا یک مورد را انتخاب کنید...");
                for (int i = 0; i < elevatorFailureResponse.getElevator_failures().size(); i++) {
                    elevatorFailureList.add(elevatorFailureResponse.getElevator_failures().get(i).getName());
                }
                SpinnerAdapter spinnerAdapter = new AdapterStringSpinner(RepairRequestActivity.this, R.layout.list_popup_spinner, R.id.tvTitle, elevatorFailureList);
                binding.spinnerElevatorFailur.setAdapter(spinnerAdapter);

                binding.spinnerElevatorFailur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if (i != 0) {
                            elevatorFailureId = elevatorFailureResponse.getElevator_failures().get(i - 1).getId();
                            Log.e(TAG, "onItemSelected: " + elevatorFailureId);
                        } else {
                            elevatorFailureId = null;
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


            }


            @Override
            public void onError(@NotNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());

                SnackBarHelper.showSnackBar(RepairRequestActivity.this, binding.getRoot(), "هنگام دریافت اطلاعات این صفحه مشکلی رخ داده است", Snackbar.LENGTH_LONG);

            }

        });

    }

    private void observeForSendRepairRequestData(int user_id, int elevator_failure_id, String address, String primaryPhone, String secondaryPhone, String description) {

        repairRequestViewModel.sendRepairRequest(user_id, elevator_failure_id, address, primaryPhone, secondaryPhone, description).subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<RepairRequestResponse>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull RepairRequestResponse repairRequestResponse) {

                if (repairRequestResponse.getStatus() == 201) {
                    showMessageSendRequest(201, "موفق", "درخواست شما ثبت شد، لطفا منتظر تماس کارشناسان باشید!");
                }

            }

            @Override
            public void onError(@NotNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());

                if (((HttpException) e).code() == 429) {
                    showMessageSendRequest(429, "درخواست تکراری", "درخواست شما ثبت شده است، لطفا منتظر تماس کارشناسان باشید!");

                } else {
                    SnackBarHelper.showSnackBar(RepairRequestActivity.this, binding.getRoot(), "خطا در ارسال اطلاعات به سمت سرور، لطفا بعدا امتحان کنید!", Snackbar.LENGTH_LONG);
                }

            }

        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnConfirm: {

                if (!((binding.etPrimaryPhone.getText().toString().startsWith("9") && binding.etPrimaryPhone.getText().length() == 10) ||
                        (binding.etPrimaryPhone.getText().toString().startsWith("0") && binding.etPrimaryPhone.getText().length() == 11))) {
                    SnackBarHelper.showSnackBar(this, binding.btnConfirm, "شماره تماس اول را بدرستی وارد کنید!", Snackbar.LENGTH_LONG);
                } else if (binding.etAddress.getText().toString().length() < 5) {
                    SnackBarHelper.showSnackBar(this, binding.btnConfirm, "آدرس را بدرستی وارد کنید!", Snackbar.LENGTH_LONG);
                } else if (elevatorFailureId == null) {
                    SnackBarHelper.showSnackBar(this, binding.btnConfirm, "نوع خرابی را تعیین کنید!", Snackbar.LENGTH_LONG);
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
                            observeForSendRepairRequestData(sharedPreferencesHelper.getUserId(), elevatorFailureId, binding.etAddress.getText().toString().trim(), removeZeroInStartOfPhoneNumber(binding.etPrimaryPhone.getText().toString().trim()), removeZeroInStartOfPhoneNumber(binding.etSecondaryPhone.getText().toString().trim()), binding.etDescription.getText().toString().trim());
                            dialogMessageConfirm.dismiss();

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


    private String removeZeroInStartOfPhoneNumber(String phoneNumber) {
        if (isPhoneNumberStartWithZero(phoneNumber)) {
            return phoneNumber.substring(1);
        } else {
            return phoneNumber;
        }

    }

    private boolean isPhoneNumberStartWithZero(String phoneNumber) {
        return phoneNumber.startsWith("0");
    }

}