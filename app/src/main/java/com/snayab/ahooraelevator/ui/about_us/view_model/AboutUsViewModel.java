package com.snayab.ahooraelevator.ui.about_us.view_model;

import android.content.Context;

import com.snayab.ahooraelevator.ui.about_us.model.AboutUsResponseModel;
import com.snayab.ahooraelevator.ui.about_us.repository.AboutUsRepository;

import io.reactivex.Single;

public class AboutUsViewModel {

    private Context context;
    private AboutUsRepository aboutUsRepository;

    public AboutUsViewModel(Context context) {
        this.context = context;
        aboutUsRepository = new AboutUsRepository(context);
    }

    public Single<AboutUsResponseModel> getAboutUsData() {
        return aboutUsRepository.getAboutUsData();
    }

}
