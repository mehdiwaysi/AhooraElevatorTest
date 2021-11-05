package com.snayab.ahooraelevator.ui.rules.repository;

import com.snayab.ahooraelevator.ui.rules.model.RulesResponseModel;

import io.reactivex.Single;

public class ApiRulesDataSource implements RulesDataSource {

    private RulesApiService rulesApiService = new RulesApiService();



    @Override
    public Single<RulesResponseModel> getRulesData() {
        return rulesApiService.getRulesData();
    }
}
