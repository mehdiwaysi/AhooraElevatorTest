package com.snayab.ahooraelevator.ui.code_verification.repository;

import com.snayab.ahooraelevator.module.ApiProvider;
import com.snayab.ahooraelevator.module.ApiService;
import com.snayab.ahooraelevator.ui.code_verification.model.UserAuthVerifyResendResponse;
import com.snayab.ahooraelevator.ui.code_verification.model.UserAuthVerifyResponse;

import io.reactivex.Single;

public class CodeVerificationApiService implements CodeVerificationDataSource {

    private ApiService apiService = ApiProvider.apiProvider();


    @Override
    public Single<UserAuthVerifyResponse> authVerify(int verification_code, String phone, String name) {
        return apiService.authVerity(verification_code, phone, name);
    }

    @Override
    public Single<UserAuthVerifyResendResponse> resendVerificationCode(String phone) {
        return apiService.resendVerificationCode(phone);
    }


}
