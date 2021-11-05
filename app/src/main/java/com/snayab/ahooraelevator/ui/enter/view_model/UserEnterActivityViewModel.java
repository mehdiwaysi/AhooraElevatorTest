package com.snayab.ahooraelevator.ui.enter.view_model;

import android.util.Log;

import com.snayab.ahooraelevator.ui.enter.model.UserAuthEnterResponseModel;
import com.snayab.ahooraelevator.ui.enter.repository.UserEnterRepository;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Single;

public class UserEnterActivityViewModel {

    private UserEnterRepository userEnterRepository = new UserEnterRepository();



    public Single<UserAuthEnterResponseModel> authEnter(String phone, String hash_code, int version_id) {
        return userEnterRepository.enter(phone, hash_code, version_id);
    }


}
