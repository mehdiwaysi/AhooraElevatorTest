package com.snayab.ahooraelevator.ui.enter.repository;

import com.snayab.ahooraelevator.ui.enter.model.UserAuthEnterResponseModel;

import io.reactivex.Single;

public class ApiUserEnterDataSource implements UserEnterDataSource {

    private UserEnterApiService userEnterApiService = new UserEnterApiService();

    @Override
    public Single<UserAuthEnterResponseModel> enter(String phone, String hash_code, int version_id) {
        return userEnterApiService.enter(phone, hash_code, version_id);

    }
}
