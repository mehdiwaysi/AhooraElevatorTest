package com.snayab.ahooraelevator.ui.about_company.repository;

import android.content.Context;

import com.snayab.ahooraelevator.ui.about_company.model.AboutCompanyResponse;

import io.reactivex.Single;

public class AboutCompanyRepository implements AboutCompanyDataSource {

    private Context context;
    private ApiAboutCompanyDataSource apiAboutCompanyDataSource;


    public AboutCompanyRepository(Context context) {
        this.context = context;
        apiAboutCompanyDataSource =   new ApiAboutCompanyDataSource();
    }


    @Override
    public Single<AboutCompanyResponse> getAboutCompany(String packageName, int userId) {
        return apiAboutCompanyDataSource.getAboutCompany(packageName,userId);
    }
}
