package com.snayab.ahooraelevator.ui.periodic_services.view_model;


import com.snayab.ahooraelevator.ui.periodic_services.model.PeriodicServicesResponse;
import com.snayab.ahooraelevator.ui.periodic_services.repository.PeriodicServicesRepository;

import io.reactivex.Single;

public class PeriodicServicesViewModel {

    PeriodicServicesRepository periodicServicesRepository = new PeriodicServicesRepository();

    public Single<PeriodicServicesResponse> getPeriodicServices(int user_id) {
        return periodicServicesRepository.getPeriodicServices(user_id);
    }

    public Single<PeriodicServicesResponse> getPeriodicServiceSingle(int contractId) {
        return periodicServicesRepository.getPeriodicServiceSingle(contractId);
    }

}
