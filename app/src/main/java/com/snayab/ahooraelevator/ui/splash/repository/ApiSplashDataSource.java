package com.snayab.ahooraelevator.ui.splash.repository;

import com.snayab.ahooraelevator.ui.splash.model.ForceUpdateResponse;

import io.reactivex.Single;

public class ApiSplashDataSource implements SplashDataSource {

    SplashApiService splashApiService = new SplashApiService();

    @Override
    public Single<ForceUpdateResponse> checkForceUpdate(int versionId) {
        return splashApiService.checkForceUpdate(versionId);
    }
}
