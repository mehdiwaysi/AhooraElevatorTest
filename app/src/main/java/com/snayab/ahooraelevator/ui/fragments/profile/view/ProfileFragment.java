package com.snayab.ahooraelevator.ui.fragments.profile.view;


import android.annotation.SuppressLint;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import com.snayab.ahooraelevator.databinding.FragmentProfileBinding;
import com.snayab.ahooraelevator.helpers.SnackBarHelper;
import com.snayab.ahooraelevator.helpers.TextHelper;
import com.snayab.ahooraelevator.shared_preferences.SharedPreferencesHelper;

import com.snayab.ahooraelevator.ui.fragments.profile.model.ProfileResponseResponse;
import com.snayab.ahooraelevator.ui.fragments.profile.view_model.ProfileFragmentViewModel;


import org.jetbrains.annotations.NotNull;


import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class ProfileFragment extends Fragment {

    private String TAG = getClass().getName();
    private ProfileFragmentViewModel profileFragmentViewModel;
    private CompositeDisposable compositeDisposable;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private FragmentProfileBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initObjects();
        initViews();
        observeForUserInfo();

        return view;
    }

    private void initViews() {
        binding.textViewName.setText(sharedPreferencesHelper.getUserName());
        binding.textViewPhone.setText("0" + sharedPreferencesHelper.getUserPhone());

        Log.e(TAG, "initViews: " + sharedPreferencesHelper.getUserName());

    }


    private void initObjects() {
        profileFragmentViewModel = new ProfileFragmentViewModel();
        compositeDisposable = new CompositeDisposable();
        sharedPreferencesHelper = new SharedPreferencesHelper(requireContext());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    public void observeForUserInfo() {
        profileFragmentViewModel.getUserInfo().subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                binding.lottieAnimationView.setVisibility(View.GONE);
                binding.lottieAnimationView.cancelAnimation();
            }
        }).subscribe(new SingleObserver<ProfileResponseResponse>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

                compositeDisposable.add(d);

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(@NotNull ProfileResponseResponse profileResponseResponse) {


                if (profileResponseResponse.getContracts() != null)
                    binding.textViewContractCount.setText("تعداد قرارداد ها : " + TextHelper.perisanNumber("" + profileResponseResponse.getContracts().getCount()));
                else
                    binding.textViewContractCount.setText("تعداد قرارداد ها : " + TextHelper.perisanNumber("0"));


                if (profileResponseResponse.getRepair_requests() != null) {
                    binding.textViewRepairRequestsStatus.setVisibility(View.VISIBLE);
                    binding.textViewRepairRequests.setText("درخواست های اعلام خرابی : " + TextHelper.perisanNumber("" + profileResponseResponse.getRepair_requests().getCount()));
                    binding.textViewRepairRequestsStatus.setText(profileResponseResponse.getRepair_requests().isLast_request_status() ? "وضعیت آخرین درخواست:  مشاهده شده" : "وضعیت آخرین درخواست: در حال بررسی");
                } else {
                    binding.textViewRepairRequests.setText("درخواست های اعلام خرابی : " + TextHelper.perisanNumber("0"));
                    binding.textViewRepairRequestsStatus.setVisibility(View.GONE);
                }

                if (profileResponseResponse.getPrice_request() != null) {
                    binding.textViewPriceRequestsStatus.setVisibility(View.VISIBLE);
                    binding.textViewPriceRequests.setText("درخواست های استعلام قیمت : " + TextHelper.perisanNumber("" + profileResponseResponse.getPrice_request().getCount()));
                    binding.textViewPriceRequestsStatus.setText(profileResponseResponse.getPrice_request().isLast_request_status() ? "وضعیت آخرین درخواست:  مشاهده شده" : "وضعیت آخرین درخواست: در حال بررسی");

                } else {
                    binding.textViewPriceRequests.setText("درخواست های استعلام قیمت : " + TextHelper.perisanNumber("0"));
                    binding.textViewPriceRequestsStatus.setVisibility(View.GONE);

                }


            }

            @Override
            public void onError(@NotNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());

                SnackBarHelper.showSnackBar(getContext(), binding.getRoot(), "هنگام دریافت اطلاعات این صفحه مشکلی رخ داده است", Snackbar.LENGTH_LONG);

            }
        });
    }


}

