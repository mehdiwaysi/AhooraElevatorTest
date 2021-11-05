package com.snayab.ahooraelevator.ui.periodic_services.repository;

import com.snayab.ahooraelevator.ui.periodic_services.model.PeriodicServicesResponse;

import io.reactivex.Single;

public class ApiPeriodicServicesDataSource implements PeriodicServicesDataSource {

    PeriodicServicesApiService periodicServicesApiService = new PeriodicServicesApiService();


    @Override
    public Single<PeriodicServicesResponse> getPeriodicServices(int user_id) {
        return periodicServicesApiService.getPeriodicServices(user_id);
    }

    @Override
    public Single<PeriodicServicesResponse> getPeriodicServiceSingle(int contractId) {
        return periodicServicesApiService.getPeriodicServiceSingle(contractId);
    }
}
