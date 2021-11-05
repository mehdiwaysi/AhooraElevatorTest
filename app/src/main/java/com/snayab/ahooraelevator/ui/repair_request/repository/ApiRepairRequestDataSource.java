package com.snayab.ahooraelevator.ui.repair_request.repository;

import com.snayab.ahooraelevator.ui.repair_request.model.ElevatorFailureResponse;
import com.snayab.ahooraelevator.ui.repair_request.model.RepairRequestResponse;

import io.reactivex.Single;

public class ApiRepairRequestDataSource implements RepairRequestDataSource {

    RepairRequestApiService repairRequestApiService = new RepairRequestApiService();


    @Override
    public Single<ElevatorFailureResponse> getElevatorFailures() {
        return repairRequestApiService.getElevatorFailures();
    }

    @Override
    public Single<RepairRequestResponse> sendRepairRequest(int user_id, int elevator_failure_id, String address,String primaryPhone, String secondaryPhone, String description) {
        return repairRequestApiService.sendRepairRequest(user_id, elevator_failure_id, address,primaryPhone,secondaryPhone,description);
    }
}
