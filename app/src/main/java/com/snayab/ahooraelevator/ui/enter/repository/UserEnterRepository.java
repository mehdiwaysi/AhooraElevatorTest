package com.snayab.ahooraelevator.ui.enter.repository;


import com.snayab.ahooraelevator.ui.enter.model.UserAuthEnterResponseModel;

import io.reactivex.Single;

public class UserEnterRepository implements UserEnterDataSource {

    private ApiUserEnterDataSource apiUserEnterDataSource = new ApiUserEnterDataSource();

    @Override
    public Single<UserAuthEnterResponseModel> enter(String phone, String hash_code, int version_id) {
        return apiUserEnterDataSource.enter(phone, hash_code, version_id);

    }
}
