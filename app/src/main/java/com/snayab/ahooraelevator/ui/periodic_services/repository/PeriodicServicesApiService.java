package com.snayab.ahooraelevator.ui.periodic_services.repository;

import com.snayab.ahooraelevator.module.ApiProvider;
import com.snayab.ahooraelevator.module.ApiService;
import com.snayab.ahooraelevator.ui.periodic_services.model.PeriodicServicesResponse;

import io.reactivex.Single;

public class PeriodicServicesApiService implements PeriodicServicesDataSource {
    private ApiService apiService = ApiProvider.apiProvider();




    @Override
    public Single<PeriodicServicesResponse> getPeriodicServices(int user_id) {
        return apiService.getPeriodicServices(user_id);
    }

    @Override
    public Single<PeriodicServicesResponse> getPeriodicServiceSingle(int contractId) {
        return apiService.getPeriodicServiceSingle(contractId);
    }
}
