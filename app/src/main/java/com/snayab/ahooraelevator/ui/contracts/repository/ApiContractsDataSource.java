package com.snayab.ahooraelevator.ui.contracts.repository;

import com.google.gson.JsonObject;
import com.snayab.ahooraelevator.ui.contracts.model.ContractLastResponse;
import com.snayab.ahooraelevator.ui.contracts.model.ContractSingleResponse;

import io.reactivex.Single;
import retrofit2.Response;

public class ApiContractsDataSource implements ContractsDataSource {

    ContractsApiService contractsApiService = new ContractsApiService();



    @Override
    public Single<ContractLastResponse> getLastContract(int userId) {
        return contractsApiService.getLastContract(userId);
    }

    @Override
    public Single<ContractSingleResponse> getSingleContract(int contract_id) {
        return contractsApiService.getSingleContract(contract_id);
    }


}
