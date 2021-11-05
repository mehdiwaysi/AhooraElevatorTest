package com.snayab.ahooraelevator.ui.repair_request.repository;

import com.snayab.ahooraelevator.module.ApiProvider;
import com.snayab.ahooraelevator.module.ApiService;
import com.snayab.ahooraelevator.ui.repair_request.model.ElevatorFailureResponse;
import com.snayab.ahooraelevator.ui.repair_request.model.RepairRequestResponse;

import io.reactivex.Single;

public class RepairRequestApiService implements RepairRequestDataSource {
    private ApiService apiService = ApiProvider.apiProvider();


    @Override
    public Single<ElevatorFailureResponse> getElevatorFailures() {
        return apiService.getElevatorFailures();
    }

    @Override
    public Single<RepairRequestResponse> sendRepairRequest(int user_id, int elevator_failure_id, String address, String primaryPhone, String secondaryPhone, String description) {
        return apiService.repairRequest(user_id, elevator_failure_id, address,primaryPhone,secondaryPhone,description);
    }


}
