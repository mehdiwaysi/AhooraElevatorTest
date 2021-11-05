package com.snayab.ahooraelevator.ui.fragments.main.repository;


import com.snayab.ahooraelevator.ui.fragments.main.model.HomePageResponse;

import io.reactivex.Single;

public class HomeFragmentRepository implements HomeFragmentDataSource {

    private ApiHomeFragmentDataSource apiHomeFragmentDataSource = new ApiHomeFragmentDataSource();

    @Override
    public Single<HomePageResponse> getHomePageData() {
        return apiHomeFragmentDataSource.getHomePageData();
    }
}
