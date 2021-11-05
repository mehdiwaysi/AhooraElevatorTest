package com.snayab.ahooraelevator.ui.repair_request.repository;

import com.snayab.ahooraelevator.ui.repair_request.model.ElevatorFailureResponse;
import com.snayab.ahooraelevator.ui.repair_request.model.RepairRequestResponse;

import io.reactivex.Single;

public interface RepairRequestDataSource {

    Single<ElevatorFailureResponse> getElevatorFailures();
    Single<RepairRequestResponse> sendRepairRequest(int user_id, int elevator_failure_id, String address,String primaryPhone, String secondaryPhone, String description);

}
