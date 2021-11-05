package com.snayab.ahooraelevator.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.helpers.MathHelper;
import com.snayab.ahooraelevator.receiver.CheckInternetConnectionReceiver;

import java.util.Stack;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class BaseActivity extends AppCompatActivity implements CheckInternetConnectionReceiver.OnDisconnectListener, CheckInternetConnectionReceiver.OnConnectedListner {

    private MathHelper mathHelper;
    private String TAG = this.getClass().getName();
    private Stack<View> loadingViewsStack;
    private ViewGroup loadingViewGroup;
    private ConnectivityManager connectivityManager;
    private CheckInternetConnectionReceiver checkInternetConnectionReceiver;
    View noNetworkView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.animation_fade_in, R.anim.animation_fade_out);
        loadingViewsStack = new Stack<>();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ViewPump.init(ViewPump.builder().addInterceptor(new CalligraphyInterceptor(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/iranian_sans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()))
                .build());

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        checkInternetConnectionReceiver = new CheckInternetConnectionReceiver(this, this);
        registerReceiver(checkInternetConnectionReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        registerReceiver(checkInternetConnectionReceiver, new IntentFilter("android.net.android.net.wifi.WIFI_STATE_CHANGED"));

        mathHelper = new MathHelper(this);
    }

    public void checkInternetAndTryAgain() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean check = networkInfo != null && networkInfo.isConnected();
        if (check) {
            dismissLoading();
            overridePendingTransition(R.anim.animation_fade_in, R.anim.animation_fade_out);
        }
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {

        if (loadingViewGroup != null) {
            loadingViewGroup.removeView(noNetworkView);
        }
        super.onBackPressed();
        dismissLoading();
        overridePendingTransition(R.anim.animation_fade_in, R.anim.animation_fade_out);

    }

    protected void showLoading() {
        loadingViewGroup = getWindow().getDecorView().findViewById(android.R.id.content);
        View loadingView = getLayoutInflater().inflate(R.layout.dialog_base_loading, null);
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, 0, 0, mathHelper.convertDipToPixels(48));
        loadingView.setLayoutParams(layoutParams);
        loadingViewsStack.push(loadingView);
        loadingViewGroup.addView(loadingView);
    }

    protected void showLoadingFullPage() {
        loadingViewGroup = getWindow().getDecorView().findViewById(android.R.id.content);
        View loadingView = getLayoutInflater().inflate(R.layout.dialog_base_loading, null);
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        loadingView.setLayoutParams(layoutParams);
        loadingViewsStack.push(loadingView);
        loadingViewGroup.addView(loadingView);
    }

    protected void dismissLoading() {
        if (!loadingViewsStack.empty()) {
            for (View view : loadingViewsStack) {
                loadingViewGroup.removeView(view);
            }
            loadingViewsStack.clear();
        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(checkInternetConnectionReceiver);
    }

    @Override
    public void onDisconnect() {
        loadingViewGroup = getWindow().getDecorView().findViewById(android.R.id.content);
        noNetworkView = getLayoutInflater().inflate(R.layout.dialog_base_no_internet, null);
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        noNetworkView.setLayoutParams(layoutParams);
        loadingViewsStack.push(noNetworkView);

        Log.e(TAG, "onDisconnecttttttttttttt: " + loadingViewGroup.getChildCount());

        LinearLayout linearInternetTry = noNetworkView.findViewById(R.id.linearInternetTry);
        loadingViewGroup.addView(noNetworkView);


        linearInternetTry.setOnClickListener(view -> {
            if (noNetworkView != null) {
                checkInternetAndTryAgain();
            }
        });

    }

    @Override
    public void onConnect() {

        if (noNetworkView != null) {
            checkInternetAndTryAgain();
        }

//    if (loadingViewGroup != null) {
//      loadingViewGroup.removeView(noNetworkView);
//    }

    }

    protected void setStatusBarColor(@ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
