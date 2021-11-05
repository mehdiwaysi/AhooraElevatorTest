package com.snayab.ahooraelevator.ui.periodic_services.repository;

import com.snayab.ahooraelevator.ui.periodic_services.model.PeriodicServicesResponse;

import io.reactivex.Single;

public interface PeriodicServicesDataSource {

    Single<PeriodicServicesResponse> getPeriodicServices(int userId);

    Single<PeriodicServicesResponse> getPeriodicServiceSingle(int contractId);

}

