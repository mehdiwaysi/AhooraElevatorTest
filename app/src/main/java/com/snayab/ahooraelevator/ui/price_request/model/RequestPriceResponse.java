package com.snayab.ahooraelevator.ui.price_request.model;

import com.google.gson.annotations.SerializedName;

public class RequestPriceResponse {

    @SerializedName("status")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}

