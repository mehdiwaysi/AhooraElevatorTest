package com.snayab.ahooraelevator.ui.fragments.profile.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProfileResponseResponse {
    @SerializedName("status")
    private Integer status;

    @SerializedName("contracts")
    private Contracts contracts;


    public class Contracts {

        @SerializedName("count")
        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    @SerializedName("price_request")
    private PriceRequest price_request;

    public class PriceRequest {

        @SerializedName("count")
        private int count;
        @SerializedName("last_request_status")
        private boolean last_request_status;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isLast_request_status() {
            return last_request_status;
        }

        public void setLast_request_status(boolean last_request_status) {
            this.last_request_status = last_request_status;
        }
    }

    @SerializedName("repair_requests")
    private RepairRequests repair_requests;

    public class RepairRequests {

        @SerializedName("count")
        private int count;

        @SerializedName("last_request_status")
        private boolean last_request_status;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isLast_request_status() {
            return last_request_status;
        }

        public void setLast_request_status(boolean last_request_status) {
            this.last_request_status = last_request_status;
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Contracts getContracts() {
        return contracts;
    }

    public void setContracts(Contracts contracts) {
        this.contracts = contracts;
    }

    public PriceRequest getPrice_request() {
        return price_request;
    }

    public void setPrice_request(PriceRequest price_request) {
        this.price_request = price_request;
    }

    public RepairRequests getRepair_requests() {
        return repair_requests;
    }

    public void setRepair_requests(RepairRequests repair_requests) {
        this.repair_requests = repair_requests;
    }
}
