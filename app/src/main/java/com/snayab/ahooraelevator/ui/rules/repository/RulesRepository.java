package com.snayab.ahooraelevator.ui.rules.repository;

import android.content.Context;

import com.snayab.ahooraelevator.ui.rules.model.RulesResponseModel;

import io.reactivex.Single;

public class RulesRepository implements RulesDataSource {

    private Context context;
    private ApiRulesDataSource apiRulesDataSource;


    public RulesRepository(Context context) {
        this.context = context;
        apiRulesDataSource =   new ApiRulesDataSource();
    }

    @Override
    public Single<RulesResponseModel> getRulesData() {
        return apiRulesDataSource.getRulesData();
    }
}
