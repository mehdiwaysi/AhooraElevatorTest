package com.snayab.ahooraelevator.ui.splash.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.snayab.ahooraelevator.BuildConfig;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.databinding.ActivitySplashBinding;
import com.snayab.ahooraelevator.dialog.DialogMessageConfirm;
import com.snayab.ahooraelevator.helpers.SnackBarHelper;
import com.snayab.ahooraelevator.shared_preferences.SharedPreferencesHelper;
import com.snayab.ahooraelevator.ui.base.BaseActivity;
import com.snayab.ahooraelevator.ui.contracts.view.ContractsActivity;
import com.snayab.ahooraelevator.ui.enter.view.UserEnterActivity;
import com.snayab.ahooraelevator.ui.main.view.MainActivity;
import com.snayab.ahooraelevator.ui.splash.model.ForceUpdateResponse;
import com.snayab.ahooraelevator.ui.splash.view_model.SplashViewModel;


import org.jetbrains.annotations.NotNull;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SplashActivity extends BaseActivity {

    private SharedPreferencesHelper sharedPreferencesHelper;
    private SplashViewModel splashViewModel;
    private CompositeDisposable compositeDisposable;
    private String TAG = getClass().getName();

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        //change status bar color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.status));
        }

        initObjects();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.continuous_zoom_out_zoom_in);
        binding.imageViewLogo.startAnimation(animation);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {

        observeForCheckForceUpdate(BuildConfig.APP_VERSION_ID);

//            }
//        }, 2000);
    }

    private void initObjects() {
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        splashViewModel = new SplashViewModel();
        compositeDisposable = new CompositeDisposable();
    }

    private void observeForCheckForceUpdate(int versionId) {
        splashViewModel.checkForceUpdate(versionId).subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<ForceUpdateResponse>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NotNull ForceUpdateResponse forceUpdateResponse) {
                if (forceUpdateResponse.getUpdate_available()) {
                    showForceUpdateDialog();
                } else {
                    if (sharedPreferencesHelper.getHasUserSignedIn()) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    } else {
                        Intent intent = new Intent(SplashActivity.this, UserEnterActivity.class);
                        startActivity(intent);
                    }
                    finish();
                }
            }

            @Override
            public void onError(@NotNull Throwable e) {
                SnackBarHelper.showSnackBar(SplashActivity.this, binding.getRoot(), "خطا از سمت سرور، لطفا مجددا امتحان کنید!", Snackbar.LENGTH_LONG);

                Log.e(TAG, "onError: " + e.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
        binding.lottieAnimationView.cancelAnimation();
    }

    private void showForceUpdateDialog() {
        DialogMessageConfirm dialogMessageConfirm = new DialogMessageConfirm(this, DialogMessageConfirm.DialogType.DIALOG_MESSAGE_DANGER);
        dialogMessageConfirm.setCancelable(false);
        dialogMessageConfirm.setConfirmText("بروزرسانی برنامه")
                .setTitle("آپدیت اجباری برنامه در درسترس میباشد")
                .setDescription("کاربر گرامی جهت استفاده از برنامه حتما میبایست نسبت به آپدیت آن اقدام نمایید")
                .setCancelVisibility(View.GONE)
                .setOnCancelClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMessageConfirm.dismiss();
                        finish();
                    }
                }).setOnConfirmClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                try {
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(
//                            "https://play.google.com/store/apps/details?id=" + getPackageName()));
//                    intent.setPackage("com.android.vending");
//                    startActivity(intent);
//                } catch (android.content.ActivityNotFoundException anfe) {
//                    String url = getResources().getString(R.string.link_app_site);
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(browserIntent);
//                    finish();
//                }
            }
        }).show();
    }
}