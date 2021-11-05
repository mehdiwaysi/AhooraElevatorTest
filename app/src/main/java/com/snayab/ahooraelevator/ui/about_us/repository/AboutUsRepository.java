package com.snayab.ahooraelevator.ui.about_us.repository;

import android.content.Context;

import com.snayab.ahooraelevator.ui.about_us.model.AboutUsResponseModel;

import io.reactivex.Single;

public class AboutUsRepository implements AboutUsDataSource {

    private Context context;
    private ApiAboutUsDataSource apiAboutUsDataSource;


    public AboutUsRepository(Context context) {
        this.context = context;
        apiAboutUsDataSource =   new ApiAboutUsDataSource();
    }


    @Override
    public Single<AboutUsResponseModel> getAboutUsData() {
        return apiAboutUsDataSource.getAboutUsData();
    }
}
