package com.snayab.ahooraelevator.ui.contracts.view;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.snayab.ahooraelevator.BuildConfig;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.databinding.ActivityContractsBinding;
import com.snayab.ahooraelevator.dialog.DialogMessageConfirm;
import com.snayab.ahooraelevator.helpers.MathHelper;
import com.snayab.ahooraelevator.helpers.SnackBarHelper;
import com.snayab.ahooraelevator.helpers.TextHelper;
import com.snayab.ahooraelevator.shared_preferences.SharedPreferencesHelper;
import com.snayab.ahooraelevator.ui.adapter.AdapterPaymentLast;
import com.snayab.ahooraelevator.ui.adapter.AdapterPaymentSingle;
import com.snayab.ahooraelevator.ui.adapter.AdapterStringSpinner;
import com.snayab.ahooraelevator.ui.base.BaseActivity;
import com.snayab.ahooraelevator.ui.contracts.model.ContractLastResponse;
import com.snayab.ahooraelevator.ui.contracts.model.ContractSingleResponse;
import com.snayab.ahooraelevator.ui.contracts.view_model.ContractsViewModel;
import com.snayab.ahooraelevator.ui.periodic_services.view.PeriodicServicesActivity;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ContractsActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = this.getClass().getName();
    private ActivityContractsBinding binding;
    private ContractsViewModel contractsViewModel;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private CompositeDisposable compositeDisposable;
    private AdapterPaymentLast adapterPaymentLast;
    private AdapterPaymentSingle adapterPaymentSingle;
    private ArrayList<String> contractsList;
    private MathHelper mathHelper;
    private boolean firstTimeSetSingleAdapterToRecyclerView = false;
    private boolean firstTimeItemSpinnerSelected = false;
    private int contractId;
    private int REQUEST_FILE_PERMISSION = 1020;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityContractsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initView();
        initObjects();
        observeForGetlastContract();

    }

    private void initView() {
        binding.imageViewBack.setOnClickListener(this);
        binding.btnPeriodicService.setOnClickListener(this);
    }

    private void initObjects() {
        contractsViewModel = new ContractsViewModel();
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        compositeDisposable = new CompositeDisposable();
        adapterPaymentLast = new AdapterPaymentLast(this, new ArrayList<>());
        adapterPaymentSingle = new AdapterPaymentSingle(this, new ArrayList<>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerViewPayment.setLayoutManager(linearLayoutManager);
        binding.recyclerViewPayment.setAdapter(adapterPaymentLast);
        contractsList = new ArrayList<>();

        mathHelper = new MathHelper(this);
    }


    private void observeForGetlastContract() {
        contractsViewModel.getLastContract(sharedPreferencesHelper.getUserId()).subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<ContractLastResponse>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull ContractLastResponse contractLastResponse) {

                if (contractLastResponse.getLastContract() != null) {
                    if (contractLastResponse.getLastContract().getPayments() != null)
                        adapterPaymentLast.addItems(contractLastResponse.getLastContract().getPayments());

                    for (int i = 0; i < contractLastResponse.getUserContracts().size(); i++) {
                        contractsList.add(contractLastResponse.getUserContracts().get(i).getElevator_name());
                    }

                    SpinnerAdapter spinnerAdapter = new AdapterStringSpinner(ContractsActivity.this, R.layout.list_popup_spinner, R.id.tvTitle, contractsList);
                    binding.spinnerContract.setAdapter(spinnerAdapter);

                    binding.spinnerContract.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            if (firstTimeItemSpinnerSelected) {
                                if (!firstTimeSetSingleAdapterToRecyclerView) {
                                    binding.recyclerViewPayment.setAdapter(adapterPaymentSingle);
                                    adapterPaymentLast.clearAll();
                                    firstTimeSetSingleAdapterToRecyclerView = true;
                                }
                                observeForGetSingleContract(contractLastResponse.getUserContracts().get(i).getId());
                            }
                            firstTimeItemSpinnerSelected = true;
                            contractId = contractLastResponse.getUserContracts().get(i).getId();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    if (contractLastResponse.getLastContract().getSigned_at() != null)
                        binding.textViewDate.setText(TextHelper.perisanNumber(contractLastResponse.getLastContract().getSigned_at()));

                    if (contractLastResponse.getLastContract().getInstalled_at() != null)
                        binding.textViewInstallationDate.setText(TextHelper.perisanNumber(contractLastResponse.getLastContract().getInstalled_at()));

                    if (contractLastResponse.getLastContract().getElevator_name() != null)
                        binding.textViewElevatorType.setText(contractLastResponse.getLastContract().getElevator_name());

                    if (contractLastResponse.getLastContract().getTotal_amount() != null)
                        binding.textViewTotalPrice.setText(TextHelper.perisanNumber(mathHelper.convertMoneyToWithComma("" + contractLastResponse.getLastContract().getTotal_amount())) + " تومان ");

                    if (contractLastResponse.getLastContract().getRemaining_amount() != null)
                        binding.textViewRemainingAmount.setText(TextHelper.perisanNumber(mathHelper.convertMoneyToWithComma("" + contractLastResponse.getLastContract().getRemaining_amount())) + " تومان ");


                    binding.imageViewDownload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (checkPermissionFile())
                                downloadDialogAsk(contractLastResponse.getLastContract().getId(), contractLastResponse.getLastContract().getName());

//                            if (contractLastResponse.getLastContract().getFile() != null && contractLastResponse.getLastContract().getElevator_name() != null)
//                                downloadContract(BuildConfig.BASE_URL + contractLastResponse.getLastContract().getFile(), contractLastResponse.getLastContract().getElevator_name());
                        }
                    });

                }

            }


            @Override
            public void onError(@NotNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());

                SnackBarHelper.showSnackBar(ContractsActivity.this, binding.getRoot(), "هنگام دریافت اطلاعات این صفحه مشکلی رخ داده است", Snackbar.LENGTH_LONG);

            }

        });
    }

    private void observeForGetSingleContract(int contractId) {
        contractsViewModel.getSingleContract(contractId).subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<ContractSingleResponse>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull ContractSingleResponse contractSingleResponse) {


                if (contractSingleResponse.getContract() != null) {
                    if (contractSingleResponse.getContract().getPayments() != null)
                        adapterPaymentSingle.addItems(contractSingleResponse.getContract().getPayments());

                    if (contractSingleResponse.getContract().getSigned_at() != null)
                        binding.textViewDate.setText(TextHelper.perisanNumber(contractSingleResponse.getContract().getSigned_at()));

                    if (contractSingleResponse.getContract().getInstalled_at() != null)
                        binding.textViewInstallationDate.setText(TextHelper.perisanNumber(contractSingleResponse.getContract().getInstalled_at()));

                    if (contractSingleResponse.getContract().getElevator_name() != null)
                        binding.textViewElevatorType.setText(contractSingleResponse.getContract().getElevator_name());

                    if (contractSingleResponse.getContract().getTotal_amount() != null)
                        binding.textViewTotalPrice.setText(TextHelper.perisanNumber(mathHelper.convertMoneyToWithComma("" + contractSingleResponse.getContract().getTotal_amount())) + " تومان ");

                    if (contractSingleResponse.getContract().getRemaining_amount() != null)
                        binding.textViewRemainingAmount.setText(TextHelper.perisanNumber(mathHelper.convertMoneyToWithComma("" + contractSingleResponse.getContract().getRemaining_amount())) + " تومان ");

                    binding.imageViewDownload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (checkPermissionFile())
                                downloadDialogAsk(contractSingleResponse.getContract().getId(), contractSingleResponse.getContract().getElevator_name());

//                            if (contractSingleResponse.getContract().getFile() != null && contractSingleResponse.getContract().getElevator_name() != null)
//                                downloadContract(BuildConfig.BASE_URL + contractSingleResponse.getContract().getFile(), contractSingleResponse.getContract().getElevator_name());
                        }
                    });

                }

            }


            @Override
            public void onError(@NotNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());

                SnackBarHelper.showSnackBar(ContractsActivity.this, binding.getRoot(), "هنگام دریافت اطلاعات این صفحه مشکلی رخ داده است", Snackbar.LENGTH_LONG);

            }

        });
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && compositeDisposable.size() > 0) {
            compositeDisposable.dispose();
        }
    }


    private void downloadDialogAsk(int contractId, String name) {

        DialogMessageConfirm dialogMessageConfirm = new DialogMessageConfirm(this, DialogMessageConfirm.DialogType.DIALOG_MESSAGE_INFO);

        dialogMessageConfirm.setCancelable(false);
        dialogMessageConfirm.setConfirmText("بله")
                .setDecriptionVisibility(View.GONE)
                .setCancelText("خیر")
                .setTitle("آیا مایل به دانلود هستید؟")
                .setOnCancelClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMessageConfirm.dismiss();
                    }
                }).setOnConfirmClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                downloadContract("http://test" + "contract/download?contract_id=" + contractId + "&token=" + sharedPreferencesHelper.getApiToken(), name);

                dialogMessageConfirm.dismiss();

            }
        }).show();

    }

    private void downloadContract(String url, String title) {

        Log.e(TAG, "downloadContract: " + url);
//        String extension = url.substring(url.lastIndexOf(".") + 1);

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("دانلود قرارداد " + title);
//                        request.setDescription(description);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.allowScanningByMediaScanner();



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            request.setDestinationInExternalFilesDir(ContractsActivity.this, "/ideagrin/ahoora", title + "." + "pdf");
        } else {
            request.setDestinationInExternalPublicDir("/ideagrin/ahoora", title + "." + "pdf");
        }
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imageViewBack: {
                onBackPressed();
                break;
            }

            case R.id.btnPeriodicService: {
                Intent intent = new Intent(this, PeriodicServicesActivity.class);
                intent.putExtra("contract_id", binding.spinnerContract.getSelectedItemPosition());
                startActivity(intent);
                break;
            }

        }
    }


    private boolean checkPermissionFile() {
        if (ActivityCompat.checkSelfPermission(ContractsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(ContractsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ContractsActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_FILE_PERMISSION);


            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_FILE_PERMISSION) {

        }


    }
}