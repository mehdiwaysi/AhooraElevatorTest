package com.snayab.ahooraelevator.ui.fragments.main.repository;

import com.snayab.ahooraelevator.module.ApiProvider;
import com.snayab.ahooraelevator.module.ApiService;
import com.snayab.ahooraelevator.ui.fragments.main.model.HomePageResponse;

import io.reactivex.Single;

public class HomeFragmentApiService implements HomeFragmentDataSource {
    private ApiService apiService = ApiProvider.apiProvider();

    @Override
    public Single<HomePageResponse> getHomePageData() {
        return apiService.getHomePageData();
    }
}
