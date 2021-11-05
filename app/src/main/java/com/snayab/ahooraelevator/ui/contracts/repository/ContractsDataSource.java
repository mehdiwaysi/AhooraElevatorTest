package com.snayab.ahooraelevator.ui.contracts.repository;

import com.google.gson.JsonObject;
import com.snayab.ahooraelevator.ui.contracts.model.ContractLastResponse;
import com.snayab.ahooraelevator.ui.contracts.model.ContractSingleResponse;

import io.reactivex.Single;
import retrofit2.Response;

public interface ContractsDataSource {

    Single<ContractLastResponse> getLastContract(int userId);
    Single<ContractSingleResponse> getSingleContract(int contractId);

}
