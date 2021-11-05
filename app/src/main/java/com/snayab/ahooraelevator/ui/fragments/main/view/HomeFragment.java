package com.snayab.ahooraelevator.ui.fragments.main.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderView;
import com.snayab.ahooraelevator.BuildConfig;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.databinding.FragmentHomeBinding;
import com.snayab.ahooraelevator.helpers.SnackBarHelper;
import com.snayab.ahooraelevator.shared_preferences.SharedPreferencesHelper;
import com.snayab.ahooraelevator.ui.contracts.view.ContractsActivity;
import com.snayab.ahooraelevator.ui.fragments.main.adapters.ServicesAdapter;
import com.snayab.ahooraelevator.ui.fragments.main.adapters.SliderAdapter;
import com.snayab.ahooraelevator.ui.fragments.main.model.HomePageResponse;
import com.snayab.ahooraelevator.ui.fragments.main.view_model.HomeFragmentViewModel;
import com.snayab.ahooraelevator.ui.price_request.view.RequestPriceActivity;
import com.snayab.ahooraelevator.ui.repair_request.view.RepairRequestActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private SliderAdapter sliderAdapter;

    private HomeFragmentViewModel homeFragmentViewModel;
    private CompositeDisposable compositeDisposable;
    private String TAG = getClass().getName();
    private SharedPreferencesHelper sharedPreferencesHelper;

    private FragmentHomeBinding binding;
    private ServicesAdapter servicesAdapter;

    private OnImageViewElevatorTypesCLick onImageViewElevatorTypesCLick;

    public void setOnImageViewElevatorTypesCLick(OnImageViewElevatorTypesCLick onImageViewElevatorTypesCLick) {
        this.onImageViewElevatorTypesCLick = onImageViewElevatorTypesCLick;
    }

    public interface OnImageViewElevatorTypesCLick {
        void onImageViewElevatorTypesCLicked();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initObjects();
        initView();
        initSlider();
        observeForHomePageData();

        return view;
    }

    private void initView() {
        servicesAdapter = new ServicesAdapter(requireContext(), new ArrayList<>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.recyclerviewServices.setLayoutManager(linearLayoutManager);
        binding.recyclerviewServices.setAdapter(servicesAdapter);

        binding.imageViewElevatorTypes.setOnClickListener(this);
        binding.imageViewRequestRepair.setOnClickListener(this);
        binding.imageViewCallForPrice.setOnClickListener(this);
    }

    private void initSlider() {

        sliderAdapter = new SliderAdapter(getContext());
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        binding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        binding.imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        binding.imageSlider.setSliderAdapter(sliderAdapter);

        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                binding.imageSlider.startAutoCycle();
            }
        }.start();

    }


    private void initObjects() {
        homeFragmentViewModel = new HomeFragmentViewModel();
        compositeDisposable = new CompositeDisposable();
        sharedPreferencesHelper = new SharedPreferencesHelper(requireContext());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    private void observeForHomePageData() {
        homeFragmentViewModel.getHomePageData().subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                binding.lottieAnimationView.setVisibility(View.GONE);
                binding.lottieAnimationView.cancelAnimation();
            }
        }).subscribe(new SingleObserver<HomePageResponse>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

                compositeDisposable.add(d);

            }

            @Override
            public void onSuccess(@NotNull HomePageResponse homePageResponse) {

                for (int i = 0; i < homePageResponse.getSliders().size(); i++) {
                    sliderAdapter.addItem(BuildConfig.SITE_URL + homePageResponse.getSliders().get(i));
                }

                if (homePageResponse.getAbout_us() != null) {
                    if (homePageResponse.getAbout_us().getTitle() != null)
                        binding.textViewAboutCompanyTitle.setText(homePageResponse.getAbout_us().getTitle());

                    if (homePageResponse.getAbout_us().getDescription() != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            binding.textViewAboutCompanyDescription.setText(Html.fromHtml("" + homePageResponse.getAbout_us().getDescription(), Html.FROM_HTML_MODE_COMPACT));
                        } else {
                            binding.textViewAboutCompanyDescription.setText(Html.fromHtml("" + homePageResponse.getAbout_us().getDescription()));
                        }
                    }

                    if (homePageResponse.getAbout_us().getPicture() != null)
                        Glide.with(requireContext()).load(BuildConfig.SITE_URL + homePageResponse.getAbout_us().getPicture()).into(binding.imageViewAboutCompany);

                    binding.imageViewMyElevator.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (homePageResponse.isHasContract()) {
                                startActivity(new Intent(getContext(), ContractsActivity.class));
                            } else {
                                SnackBarHelper.showSnackBar(getContext(), binding.getRoot(), "شما هنوز قراردادی ندارید!", Snackbar.LENGTH_LONG);
                            }
                        }
                    });

                }


                if (homePageResponse.getServices() != null)
                    servicesAdapter.addItems(homePageResponse.getServices());

            }

            @Override
            public void onError(@NotNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());

                SnackBarHelper.showSnackBar(getContext(), binding.getRoot(), "هنگام دریافت اطلاعات این صفحه مشکلی رخ داده است", Snackbar.LENGTH_LONG);

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imageViewRequestRepair: {

                startActivity(new Intent(getContext(), RepairRequestActivity.class));
                break;
            }

            case R.id.imageViewCallForPrice: {

                startActivity(new Intent(getContext(), RequestPriceActivity.class));
                break;
            }

            case R.id.imageViewElevatorTypes: {

                onImageViewElevatorTypesCLick.onImageViewElevatorTypesCLicked();
                break;
            }


        }
    }
}

