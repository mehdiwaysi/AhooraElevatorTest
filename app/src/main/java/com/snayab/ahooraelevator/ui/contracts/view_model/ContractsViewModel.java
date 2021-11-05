package com.snayab.ahooraelevator.ui.contracts.view_model;


import com.google.gson.JsonObject;
import com.snayab.ahooraelevator.ui.contracts.model.ContractLastResponse;
import com.snayab.ahooraelevator.ui.contracts.model.ContractSingleResponse;
import com.snayab.ahooraelevator.ui.contracts.repository.ContractsRepository;

import io.reactivex.Single;
import retrofit2.Response;

public class ContractsViewModel {

    ContractsRepository contractsRepository = new ContractsRepository();

    public Single<ContractLastResponse> getLastContract(int userId) {
        return contractsRepository.getLastContract(userId);
    }

    public Single<ContractSingleResponse> getSingleContract(int contract_id) {
        return contractsRepository.getSingleContract(contract_id);
    }



}
