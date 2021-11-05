package com.snayab.ahooraelevator.ui.contact_us.repository;

import com.snayab.ahooraelevator.ui.contact_us.model.ContactUsResponseModel;

import io.reactivex.Single;

public interface ContactUsDataSource {

    Single<ContactUsResponseModel> getContactUsData();


}
