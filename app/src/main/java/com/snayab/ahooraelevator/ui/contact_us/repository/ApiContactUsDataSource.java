package com.snayab.ahooraelevator.ui.contact_us.repository;

import com.snayab.ahooraelevator.ui.contact_us.model.ContactUsResponseModel;

import io.reactivex.Single;

public class ApiContactUsDataSource implements ContactUsDataSource {

    private ContactUsApiService contactUsApiService = new ContactUsApiService();


    @Override
    public Single<ContactUsResponseModel> getContactUsData() {
        return contactUsApiService.getContactUsData();
    }
}
