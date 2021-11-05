package com.snayab.ahooraelevator.ui.repair_request.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ElevatorFailureResponse {
    @SerializedName("status")
    private Integer status;
    @SerializedName("elevator_failures")
    private ArrayList<ElevatorFailures> elevator_failures;

    public class ElevatorFailures {
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ArrayList<ElevatorFailures> getElevator_failures() {
        return elevator_failures;
    }

    public void setElevator_failures(ArrayList<ElevatorFailures> elevator_failures) {
        this.elevator_failures = elevator_failures;
    }

}
