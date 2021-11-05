package com.snayab.ahooraelevator.ui.contracts.repository;

import com.google.gson.JsonObject;
import com.snayab.ahooraelevator.ui.contracts.model.ContractLastResponse;
import com.snayab.ahooraelevator.ui.contracts.model.ContractSingleResponse;

import io.reactivex.Single;
import retrofit2.Response;

public class ContractsRepository implements ContractsDataSource {

    private ApiContractsDataSource apiContractsDataSource = new ApiContractsDataSource();


    @Override
    public Single<ContractLastResponse> getLastContract(int userId) {
        return apiContractsDataSource.getLastContract(userId);
    }

    @Override
    public Single<ContractSingleResponse> getSingleContract(int contract_id) {
        return apiContractsDataSource.getSingleContract(contract_id);
    }


}
