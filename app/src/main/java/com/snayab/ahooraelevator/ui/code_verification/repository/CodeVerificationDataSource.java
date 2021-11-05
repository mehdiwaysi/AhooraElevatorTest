package com.snayab.ahooraelevator.ui.code_verification.repository;

import com.snayab.ahooraelevator.ui.code_verification.model.UserAuthVerifyResendResponse;
import com.snayab.ahooraelevator.ui.code_verification.model.UserAuthVerifyResponse;

import io.reactivex.Single;

public interface CodeVerificationDataSource {

    Single<UserAuthVerifyResponse> authVerify(int verification_code, String phone, String name);

    Single<UserAuthVerifyResendResponse> resendVerificationCode(String encryptedParameters);

}
