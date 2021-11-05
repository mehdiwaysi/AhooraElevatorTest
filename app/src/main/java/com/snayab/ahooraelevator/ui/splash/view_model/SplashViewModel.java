package com.snayab.ahooraelevator.ui.splash.view_model;


import com.snayab.ahooraelevator.ui.splash.model.ForceUpdateResponse;
import com.snayab.ahooraelevator.ui.splash.repository.SplashRepository;

import io.reactivex.Single;

public class SplashViewModel {

    SplashRepository splashRepository = new SplashRepository();

 public Single<ForceUpdateResponse> checkForceUpdate(int versionId) {
        return splashRepository.checkForceUpdate(versionId);
    }

}
