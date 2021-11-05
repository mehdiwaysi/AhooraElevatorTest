package com.snayab.ahooraelevator.ui.contact_us.view_model;

import android.content.Context;

import com.snayab.ahooraelevator.ui.contact_us.model.ContactUsResponseModel;
import com.snayab.ahooraelevator.ui.contact_us.repository.ContactUsRepository;

import io.reactivex.Single;


public class ContactUsViewModel {

    private Context context;
    private ContactUsRepository contactUsRepository;

    public ContactUsViewModel(Context context) {
        this.context = context;
        contactUsRepository = new ContactUsRepository(context);
    }

    public Single<ContactUsResponseModel> getContactUsData() {
        return contactUsRepository.getContactUsData();
    }

}
