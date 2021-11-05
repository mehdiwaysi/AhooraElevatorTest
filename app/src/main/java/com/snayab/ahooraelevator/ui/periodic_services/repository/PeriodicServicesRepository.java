package com.snayab.ahooraelevator.ui.periodic_services.repository;

import com.snayab.ahooraelevator.ui.periodic_services.model.PeriodicServicesResponse;

import io.reactivex.Single;

public class PeriodicServicesRepository implements PeriodicServicesDataSource {

    private ApiPeriodicServicesDataSource apiPeriodicServicesDataSource = new ApiPeriodicServicesDataSource();

    @Override
    public Single<PeriodicServicesResponse> getPeriodicServices(int user_id) {
        return apiPeriodicServicesDataSource.getPeriodicServices(user_id);
    }

    @Override
    public Single<PeriodicServicesResponse> getPeriodicServiceSingle(int contractId) {
        return apiPeriodicServicesDataSource.getPeriodicServiceSingle(contractId);
    }

}
