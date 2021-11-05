package com.snayab.ahooraelevator.ui.splash.repository;

import com.snayab.ahooraelevator.module.ApiProvider;
import com.snayab.ahooraelevator.module.ApiService;
import com.snayab.ahooraelevator.ui.splash.model.ForceUpdateResponse;

import io.reactivex.Single;

public class SplashApiService implements SplashDataSource {
    private ApiService apiService = ApiProvider.apiProvider();

    @Override
    public Single<ForceUpdateResponse> checkForceUpdate(int versionId) {
        return apiService.checkForceUpdate(versionId);
    }
}
