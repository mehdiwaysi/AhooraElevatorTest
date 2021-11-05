package com.snayab.ahooraelevator.ui.about_us.repository;

import com.snayab.ahooraelevator.ui.about_us.model.AboutUsResponseModel;

import io.reactivex.Single;

public interface AboutUsDataSource {

    Single<AboutUsResponseModel> getAboutUsData();


}
