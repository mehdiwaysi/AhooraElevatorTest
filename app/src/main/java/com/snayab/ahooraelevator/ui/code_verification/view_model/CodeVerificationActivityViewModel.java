package com.snayab.ahooraelevator.ui.code_verification.view_model;

import android.content.Context;
import android.util.Log;

import com.snayab.ahooraelevator.ui.code_verification.model.UserAuthVerifyResendResponse;
import com.snayab.ahooraelevator.ui.code_verification.model.UserAuthVerifyResponse;
import com.snayab.ahooraelevator.ui.code_verification.repository.CodeVerificationRepository;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Single;

public class CodeVerificationActivityViewModel {

    private Context context;
    private CodeVerificationRepository codeVerificationRepository;

    public CodeVerificationActivityViewModel() {
        codeVerificationRepository = new CodeVerificationRepository();
    }

    public Single<UserAuthVerifyResponse> authVerify(int verification_code, String phone, String name) {
        return codeVerificationRepository.authVerify(verification_code, phone, name);
    }

    public Single<UserAuthVerifyResendResponse> resendVerificationCode(String phone) {
        return codeVerificationRepository.resendVerificationCode(phone);
    }


}
