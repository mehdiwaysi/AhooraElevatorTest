package com.snayab.ahooraelevator.module;


import com.snayab.ahooraelevator.BuildConfig;
import com.snayab.ahooraelevator.app.App;
import com.snayab.ahooraelevator.shared_preferences.SharedPreferencesHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private SharedPreferencesHelper sharedPreferencesHelper;

    public HeaderInterceptor() {
        sharedPreferencesHelper = new SharedPreferencesHelper(App.getContext());
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+sharedPreferencesHelper.getApiToken())
                .addHeader("version-id", BuildConfig.APP_VERSION_ID.toString())
                .build();

        return chain.proceed(request);
    }
}
