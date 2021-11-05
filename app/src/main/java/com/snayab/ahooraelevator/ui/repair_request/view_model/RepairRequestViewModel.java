package com.snayab.ahooraelevator.ui.repair_request.view_model;


import com.snayab.ahooraelevator.ui.repair_request.model.ElevatorFailureResponse;
import com.snayab.ahooraelevator.ui.repair_request.model.RepairRequestResponse;
import com.snayab.ahooraelevator.ui.repair_request.repository.RepairRequestRepository;

import io.reactivex.Single;

public class RepairRequestViewModel {

    RepairRequestRepository repairRequestRepository = new RepairRequestRepository();

    public Single<RepairRequestResponse> sendRepairRequest(int user_id, int elevator_failure_id, String address,String primaryPhone, String secondaryPhone, String description) {
        return repairRequestRepository.sendRepairRequest(user_id, elevator_failure_id, address,primaryPhone,secondaryPhone,description);
    }

    public Single<ElevatorFailureResponse> getElevatorFailures() {
        return repairRequestRepository.getElevatorFailures();
    }


}
