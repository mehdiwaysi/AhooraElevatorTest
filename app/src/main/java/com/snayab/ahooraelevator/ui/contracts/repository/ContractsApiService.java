package com.snayab.ahooraelevator.ui.contracts.repository;

import com.google.gson.JsonObject;
import com.snayab.ahooraelevator.module.ApiProvider;
import com.snayab.ahooraelevator.module.ApiService;
import com.snayab.ahooraelevator.ui.contracts.model.ContractLastResponse;
import com.snayab.ahooraelevator.ui.contracts.model.ContractSingleResponse;

import io.reactivex.Single;
import retrofit2.Response;

public class ContractsApiService implements ContractsDataSource {
    private ApiService apiService = ApiProvider.apiProvider();


    @Override
    public Single<ContractLastResponse> getLastContract(int userId) {
        return apiService.getLastContract(userId);
    }

    @Override
    public Single<ContractSingleResponse> getSingleContract(int contract_id) {
        return apiService.getSingleContract(contract_id);
    }




}
