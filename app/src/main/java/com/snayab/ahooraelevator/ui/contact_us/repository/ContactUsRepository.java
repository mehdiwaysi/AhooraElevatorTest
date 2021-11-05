package com.snayab.ahooraelevator.ui.contact_us.repository;

import android.content.Context;

import com.snayab.ahooraelevator.ui.contact_us.model.ContactUsResponseModel;

import io.reactivex.Single;

public class ContactUsRepository implements ContactUsDataSource {

    private Context context;
    private ApiContactUsDataSource apiContactUsDataSource;


    public ContactUsRepository(Context context) {
        this.context = context;
        apiContactUsDataSource =   new ApiContactUsDataSource();
    }

    @Override
    public Single<ContactUsResponseModel> getContactUsData() {
        return apiContactUsDataSource.getContactUsData();
    }
}
