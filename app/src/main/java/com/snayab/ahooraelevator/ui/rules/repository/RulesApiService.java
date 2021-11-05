package com.snayab.ahooraelevator.ui.rules.repository;


import com.snayab.ahooraelevator.module.ApiProvider;
import com.snayab.ahooraelevator.module.ApiService;
import com.snayab.ahooraelevator.ui.rules.model.RulesResponseModel;

import io.reactivex.Single;


public class RulesApiService implements RulesDataSource {
    private ApiService apiService = ApiProvider.apiProvider();


    @Override
    public Single<RulesResponseModel> getRulesData() {
        return apiService.getRules();
    }
}
