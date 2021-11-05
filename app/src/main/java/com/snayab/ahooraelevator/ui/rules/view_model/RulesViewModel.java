package com.snayab.ahooraelevator.ui.rules.view_model;

import android.content.Context;

import com.snayab.ahooraelevator.ui.rules.model.RulesResponseModel;
import com.snayab.ahooraelevator.ui.rules.repository.RulesRepository;

import io.reactivex.Single;

public class RulesViewModel {

    private Context context;
    private RulesRepository rulesRepository;

    public RulesViewModel(Context context) {
        this.context = context;
        rulesRepository = new RulesRepository(context);
    }

    public Single<RulesResponseModel> getRulesData() {
        return rulesRepository.getRulesData();
    }

}
