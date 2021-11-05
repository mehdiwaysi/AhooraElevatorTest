package com.snayab.ahooraelevator.ui.about_us.repository;

import com.snayab.ahooraelevator.ui.about_us.model.AboutUsResponseModel;

import io.reactivex.Single;

public class ApiAboutUsDataSource implements AboutUsDataSource {

    private AboutUsApiService aboutUsApiService = new AboutUsApiService();

    @Override
    public Single<AboutUsResponseModel> getAboutUsData() {
        return aboutUsApiService.getAboutUsData();
    }

}
