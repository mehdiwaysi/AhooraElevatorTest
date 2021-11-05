package com.snayab.ahooraelevator.ui.about_us.view;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.snayab.ahooraelevator.BuildConfig;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.databinding.ActivityAboutUsBinding;
import com.snayab.ahooraelevator.helpers.TextHelper;
import com.snayab.ahooraelevator.ui.about_us.model.AboutUsResponseModel;
import com.snayab.ahooraelevator.ui.about_us.view_model.AboutUsViewModel;
import com.snayab.ahooraelevator.ui.base.BaseActivity;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


public class AboutUsActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = this.getClass().getName();
    private AboutUsViewModel aboutUsViewModel;
    private ActivityAboutUsBinding binding;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutUsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();

        observeForAboutUsData();
        showVersion();

    }

    private void init() {
        aboutUsViewModel = new AboutUsViewModel(this);
        compositeDisposable = new CompositeDisposable();

        binding.imageViewBack.setOnClickListener(this);
    }

    private void observeForAboutUsData() {

        aboutUsViewModel.getAboutUsData().subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                binding.lottieAnimationView.setVisibility(View.GONE);
                binding.lottieAnimationView.cancelAnimation();
            }
        }).subscribe(new SingleObserver<AboutUsResponseModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull AboutUsResponseModel rulesResponseModel) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.tvDescription.setText(Html.fromHtml("" + rulesResponseModel.getAboutUs().getDescription(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    binding.tvDescription.setText(Html.fromHtml(rulesResponseModel.getAboutUs().getDescription()));
                }
                if (rulesResponseModel.getAboutUs().getPicture() != null)
                    Glide.with(AboutUsActivity.this).load(BuildConfig.SITE_URL + rulesResponseModel.getAboutUs().getPicture()).into(binding.imageViewAboutUs);


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError:observeForAboutUsData: " + e.getMessage());
            }
        });


    }


    private void showVersion() {
        PackageManager manager = getPackageManager();
        PackageInfo packageInfo = null;

        try {
            packageInfo = manager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        binding.tvVersion.setText("v " + TextHelper.perisanNumber(packageInfo.versionName));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewBack: {
                onBackPressed();
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();

    }
}