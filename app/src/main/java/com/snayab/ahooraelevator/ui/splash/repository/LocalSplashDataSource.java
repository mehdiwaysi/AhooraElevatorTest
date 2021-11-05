package com.snayab.ahooraelevator.ui.splash.repository;

import com.snayab.ahooraelevator.ui.splash.model.ForceUpdateResponse;

import io.reactivex.Single;

public class LocalSplashDataSource implements SplashDataSource{
    @Override
    public Single<ForceUpdateResponse> checkForceUpdate(int versionId) {
        return null;
    }
}
