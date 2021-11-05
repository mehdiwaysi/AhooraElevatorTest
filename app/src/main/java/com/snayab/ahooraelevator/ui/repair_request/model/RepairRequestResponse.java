package com.snayab.ahooraelevator.ui.repair_request.model;

import com.google.gson.annotations.SerializedName;

public class RepairRequestResponse {
    @SerializedName("status")
    private Integer status;
    @SerializedName("update_available")
    private Boolean update_available;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getUpdate_available() {
        return update_available;
    }

    public void setUpdate_available(Boolean update_available) {
        this.update_available = update_available;
    }
}
