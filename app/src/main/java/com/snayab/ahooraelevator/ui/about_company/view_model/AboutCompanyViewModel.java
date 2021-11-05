package com.snayab.ahooraelevator.ui.about_company.view_model;

import android.content.Context;

import com.snayab.ahooraelevator.ui.about_company.model.AboutCompanyResponse;
import com.snayab.ahooraelevator.ui.about_company.repository.AboutCompanyRepository;

import io.reactivex.Single;

public class AboutCompanyViewModel {

    private Context context;
    private AboutCompanyRepository aboutCompanyRepository;

    public AboutCompanyViewModel(Context context) {
        this.context = context;
        aboutCompanyRepository = new AboutCompanyRepository(context);
    }

    public Single<AboutCompanyResponse> getAboutCompanyData(String packageName, int userId) {
        return aboutCompanyRepository.getAboutCompany(packageName,userId);
    }

}
