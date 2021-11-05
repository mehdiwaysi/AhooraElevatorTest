package com.snayab.ahooraelevator.ui.about_company.repository;


import com.snayab.ahooraelevator.module.ApiProvider;
import com.snayab.ahooraelevator.module.ApiService;
import com.snayab.ahooraelevator.ui.about_company.model.AboutCompanyResponse;

import io.reactivex.Single;

public class AboutCompanyApiService implements AboutCompanyDataSource {
    private ApiService apiService = ApiProvider.apiProviderSnaagrin();



    @Override
    public Single<AboutCompanyResponse> getAboutCompany(String packageName, int userId) {
        return apiService.getAboutCompany(packageName,userId);
    }
}
