package com.snayab.ahooraelevator.ui.code_verification.repository;

import android.content.Context;

import com.snayab.ahooraelevator.ui.code_verification.model.UserAuthVerifyResendResponse;
import com.snayab.ahooraelevator.ui.code_verification.model.UserAuthVerifyResponse;

import io.reactivex.Single;

public class CodeVerificationRepository implements CodeVerificationDataSource {
    private ApiCodeVerificationDataSource apiCodeVerificationDataSource = new ApiCodeVerificationDataSource();


    @Override
    public Single<UserAuthVerifyResponse> authVerify(int verification_code, String phone, String name) {
        return apiCodeVerificationDataSource.authVerify(verification_code, phone, name);
    }

    @Override
    public Single<UserAuthVerifyResendResponse> resendVerificationCode(String phone) {
        return apiCodeVerificationDataSource.resendVerificationCode(phone);
    }
}
