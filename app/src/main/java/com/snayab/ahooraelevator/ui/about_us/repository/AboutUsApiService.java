package com.snayab.ahooraelevator.ui.about_us.repository;


import com.snayab.ahooraelevator.module.ApiProvider;
import com.snayab.ahooraelevator.module.ApiService;
import com.snayab.ahooraelevator.ui.about_us.model.AboutUsResponseModel;

import io.reactivex.Single;

public class AboutUsApiService implements AboutUsDataSource {
    private ApiService apiService = ApiProvider.apiProvider();


    @Override
    public Single<AboutUsResponseModel> getAboutUsData() {
        return apiService.getAboutUs();
    }

}
