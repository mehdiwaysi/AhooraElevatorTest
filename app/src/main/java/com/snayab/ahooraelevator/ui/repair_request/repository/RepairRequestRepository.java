package com.snayab.ahooraelevator.ui.repair_request.repository;

import com.snayab.ahooraelevator.ui.repair_request.model.ElevatorFailureResponse;
import com.snayab.ahooraelevator.ui.repair_request.model.RepairRequestResponse;

import io.reactivex.Single;

public class RepairRequestRepository implements RepairRequestDataSource {

    private ApiRepairRequestDataSource apiRepairRequestDataSource = new ApiRepairRequestDataSource();

    @Override
    public Single<ElevatorFailureResponse> getElevatorFailures() {
        return apiRepairRequestDataSource.getElevatorFailures();
    }

    @Override
    public Single<RepairRequestResponse> sendRepairRequest(int user_id, int elevator_failure_id, String address,String primaryPhone, String secondaryPhone, String description) {
        return apiRepairRequestDataSource.sendRepairRequest(user_id, elevator_failure_id, address,primaryPhone,secondaryPhone,description);
    }
}
