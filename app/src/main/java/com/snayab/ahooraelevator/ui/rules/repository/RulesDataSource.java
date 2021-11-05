package com.snayab.ahooraelevator.ui.rules.repository;

import com.snayab.ahooraelevator.ui.rules.model.RulesResponseModel;

import io.reactivex.Single;

public interface RulesDataSource {

    Single<RulesResponseModel> getRulesData();


}
