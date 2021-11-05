package com.snayab.ahooraelevator.ui.contact_us.repository;


import com.snayab.ahooraelevator.module.ApiProvider;
import com.snayab.ahooraelevator.module.ApiService;
import com.snayab.ahooraelevator.ui.contact_us.model.ContactUsResponseModel;

import io.reactivex.Single;

public class ContactUsApiService implements ContactUsDataSource {
    private ApiService apiService = ApiProvider.apiProvider();

    @Override
    public Single<ContactUsResponseModel> getContactUsData() {
        return apiService.getContactUs();
    }
}
