package com.snayab.ahooraelevator.ui.fragments.main.repository;

import com.snayab.ahooraelevator.ui.fragments.main.model.HomePageResponse;

import io.reactivex.Single;

public class ApiHomeFragmentDataSource implements HomeFragmentDataSource {

    private HomeFragmentApiService homeFragmentApiService = new HomeFragmentApiService();

    public Single<HomePageResponse> getHomePageData() {
        return homeFragmentApiService.getHomePageData();
    }

}
