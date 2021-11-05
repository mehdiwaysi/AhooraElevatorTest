package com.snayab.ahooraelevator.ui.enter.repository;

import com.snayab.ahooraelevator.ui.enter.model.UserAuthEnterResponseModel;

import io.reactivex.Single;

public interface UserEnterDataSource {
    Single<UserAuthEnterResponseModel> enter(String phone,  String hash_code, int version_id);
}
