package com.snayab.ahooraelevator.ui.periodic_services.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.snayab.ahooraelevator.BuildConfig;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.databinding.ActivityPeriodicServicesBinding;
import com.snayab.ahooraelevator.helpers.SnackBarHelper;
import com.snayab.ahooraelevator.shared_preferences.SharedPreferencesHelper;
import com.snayab.ahooraelevator.ui.adapter.AdapterStringSpinner;
import com.snayab.ahooraelevator.ui.base.BaseActivity;
import com.snayab.ahooraelevator.ui.contracts.view.ContractsActivity;
import com.snayab.ahooraelevator.ui.fragments.main.model.HomePageResponse;
import com.snayab.ahooraelevator.ui.periodic_services.adapter.PeriodicServicesAdapter;
import com.snayab.ahooraelevator.ui.periodic_services.model.PeriodicServicesResponse;
import com.snayab.ahooraelevator.ui.periodic_services.view_model.PeriodicServicesViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class PeriodicServicesActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = this.getClass().getName();
    private ActivityPeriodicServicesBinding binding;
    private PeriodicServicesAdapter periodicServicesAdapter;
    private PeriodicServicesViewModel periodicServicesViewModel;
    private CompositeDisposable compositeDisposable;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private ArrayList<String> periodicServicesList;
    private boolean firstTimeItemSpinnerSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPeriodicServicesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initObjects();
        initView();
        observeForGetPeriodicServices();

    }


    private void initView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(periodicServicesAdapter);
        binding.imageViewBack.setOnClickListener(this);

    }


    private void initObjects() {
        periodicServicesAdapter = new PeriodicServicesAdapter(this, new ArrayList<>());
        periodicServicesViewModel = new PeriodicServicesViewModel();
        compositeDisposable = new CompositeDisposable();
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        periodicServicesList = new ArrayList<>();
    }

    private void observeForGetPeriodicServices() {

        periodicServicesViewModel.getPeriodicServices(sharedPreferencesHelper.getUserId()).subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                binding.lottieAnimationView.setVisibility(View.GONE);
                binding.lottieAnimationView.cancelAnimation();
            }
        }).subscribe(new SingleObserver<PeriodicServicesResponse>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

                compositeDisposable.add(d);

            }

            @Override
            public void onSuccess(@NonNull PeriodicServicesResponse periodicServicesResponse) {

                if (periodicServicesResponse.getPeriodic_services() != null) {

                    if (periodicServicesResponse.getPeriodic_services().size() > 0) {
                        periodicServicesAdapter.addItems(periodicServicesResponse.getPeriodic_services());

                    } else {
                        binding.linearLayoutNoService.setVisibility(View.GONE);
                    }

                }

                if (periodicServicesResponse.getUserContracts() != null) {
                    for (int i = 0; i < periodicServicesResponse.getUserContracts().size(); i++) {
                        periodicServicesList.add(periodicServicesResponse.getUserContracts().get(i).getElevator_name());
                    }

                    SpinnerAdapter spinnerAdapter = new AdapterStringSpinner(PeriodicServicesActivity.this, R.layout.list_popup_spinner, R.id.tvTitle, periodicServicesList);
                    binding.spinnerPeriodicServices.setAdapter(spinnerAdapter);

                    binding.spinnerPeriodicServices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            if (firstTimeItemSpinnerSelected) {
                                observeForGetPeriodicServicesSingle(periodicServicesResponse.getUserContracts().get(i).getId());
                            }
                            firstTimeItemSpinnerSelected = true;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }


            @Override
            public void onError(@NotNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());

                SnackBarHelper.showSnackBar(PeriodicServicesActivity.this, binding.getRoot(), "هنگام دریافت اطلاعات این صفحه مشکلی رخ داده است", Snackbar.LENGTH_LONG);
            }
        });

    }

    private void observeForGetPeriodicServicesSingle(int contractId) {

        periodicServicesViewModel.getPeriodicServiceSingle(contractId).subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                binding.lottieAnimationView.setVisibility(View.GONE);
                binding.lottieAnimationView.cancelAnimation();
            }
        }).subscribe(new SingleObserver<PeriodicServicesResponse>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull PeriodicServicesResponse periodicServicesResponse) {

                if (periodicServicesResponse.getPeriodic_services() != null) {

                    if (periodicServicesResponse.getPeriodic_services().size() > 0) {
                        periodicServicesAdapter.addItems(periodicServicesResponse.getPeriodic_services());

                    } else {
                        binding.linearLayoutNoService.setVisibility(View.GONE);
                    }

                }

            }


            @Override
            public void onError(@NotNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());

                SnackBarHelper.showSnackBar(PeriodicServicesActivity.this, binding.getRoot(), "هنگام دریافت اطلاعات این صفحه مشکلی رخ داده است", Snackbar.LENGTH_LONG);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewBack: {
                onBackPressed();
                break;
            }
        }
    }
}

