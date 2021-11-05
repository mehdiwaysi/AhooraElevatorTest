package com.snayab.ahooraelevator.ui.code_verification.repository;

import com.snayab.ahooraelevator.ui.code_verification.model.UserAuthVerifyResendResponse;
import com.snayab.ahooraelevator.ui.code_verification.model.UserAuthVerifyResponse;

import io.reactivex.Single;

public class ApiCodeVerificationDataSource implements CodeVerificationDataSource {

    private CodeVerificationApiService codeVerificationApiService = new CodeVerificationApiService();

    @Override
    public Single<UserAuthVerifyResponse> authVerify(int verification_code, String phone, String name) {
        return codeVerificationApiService.authVerify(verification_code, phone, name);
    }

    @Override
    public Single<UserAuthVerifyResendResponse> resendVerificationCode(String phone) {
        return codeVerificationApiService.resendVerificationCode( phone);
    }
}
