package com.snayab.ahooraelevator.ui.enter.repository;

import com.snayab.ahooraelevator.module.ApiProvider;
import com.snayab.ahooraelevator.module.ApiService;
import com.snayab.ahooraelevator.ui.enter.model.UserAuthEnterResponseModel;

import io.reactivex.Single;

public class UserEnterApiService implements UserEnterDataSource {

    private ApiService apiService = ApiProvider.apiProvider();

    @Override
    public Single<UserAuthEnterResponseModel> enter(String phone,  String hash_code, int version_id) {
        return apiService.enter(phone, hash_code, version_id);
    }
}
