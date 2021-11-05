package com.snayab.ahooraelevator.ui.splash.repository;

import com.snayab.ahooraelevator.ui.splash.model.ForceUpdateResponse;

import io.reactivex.Single;

public interface SplashDataSource {
    Single<ForceUpdateResponse> checkForceUpdate(int versionId);
}
