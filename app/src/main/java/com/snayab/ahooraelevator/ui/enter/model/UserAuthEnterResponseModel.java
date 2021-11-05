package com.snayab.ahooraelevator.ui.enter.model;

import com.google.gson.annotations.SerializedName;

public class UserAuthEnterResponseModel {
    @SerializedName("status")
    private Integer status;
    @SerializedName("has_basic_information")
    private Boolean has_name;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getHas_name() {
        return has_name;
    }

    public void setHas_name(Boolean has_name) {
        this.has_name = has_name;
    }
}
