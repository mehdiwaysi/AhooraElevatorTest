package com.snayab.ahooraelevator.ui.splash.repository;

import com.snayab.ahooraelevator.ui.splash.model.ForceUpdateResponse;

import io.reactivex.Single;

public class SplashRepository implements SplashDataSource {

    private ApiSplashDataSource apiSplashDataSource = new ApiSplashDataSource();
    private LocalSplashDataSource localSplashDataSource = new LocalSplashDataSource();

    @Override
    public Single<ForceUpdateResponse> checkForceUpdate(int versionId) {
        return apiSplashDataSource.checkForceUpdate(versionId);
    }
}
