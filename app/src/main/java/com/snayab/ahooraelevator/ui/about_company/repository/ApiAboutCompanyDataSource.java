package com.snayab.ahooraelevator.ui.about_company.repository;

import com.snayab.ahooraelevator.ui.about_company.model.AboutCompanyResponse;

import io.reactivex.Single;

public class ApiAboutCompanyDataSource implements AboutCompanyDataSource {

    private AboutCompanyApiService aboutCompanyApiService = new AboutCompanyApiService();


    @Override
    public Single<AboutCompanyResponse> getAboutCompany(String packageName, int userId) {
        return aboutCompanyApiService.getAboutCompany(packageName,userId);
    }
}
