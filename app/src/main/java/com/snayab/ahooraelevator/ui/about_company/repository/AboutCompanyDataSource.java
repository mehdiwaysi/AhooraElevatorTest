package com.snayab.ahooraelevator.ui.about_company.repository;

import com.snayab.ahooraelevator.ui.about_company.model.AboutCompanyResponse;

import io.reactivex.Single;

public interface AboutCompanyDataSource {

    Single<AboutCompanyResponse> getAboutCompany(String packageName, int userId);


}
