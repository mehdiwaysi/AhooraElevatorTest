package com.snayab.ahooraelevator.module;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.module.LibraryGlideModule;
import com.snayab.ahooraelevator.BuildConfig;

import java.io.InputStream;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    private static Retrofit retrofitSnaagrin = null;

    private static final String BASE_URL = BuildConfig.BASE_URL;
    private static final String BASE_URL_SNAAGRIN = BuildConfig.SNAAGRIN_BASE_URL;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(HttpClient.getUnsafeOkHttpClient().build())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }


    public static Retrofit getClientSnaagrin() {
        if (retrofitSnaagrin == null) {
            retrofitSnaagrin = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    //for use rxJava in retrofit
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(HttpClient.getOkHttpClient())
                    .baseUrl(BASE_URL_SNAAGRIN)
                    .build();
        }
        return retrofitSnaagrin;
    }


}