package com.snayab.ahooraelevator.ui.periodic_services.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PeriodicServicesResponse {

    @SerializedName("status")
    private Integer status;
    @SerializedName("periodic_services")
    private ArrayList<PeriodicServices> periodic_services;

    public class PeriodicServices {
        @SerializedName("id")
        private int id;

        @SerializedName("done")
        private boolean done;

        @SerializedName("served_at")
        private String served_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Boolean getDone() {
            return done;
        }

        public void setDone(Boolean done) {
            this.done = done;
        }

        public String getServed_at() {
            return served_at;
        }

        public void setServed_at(String served_at) {
            this.served_at = served_at;
        }
    }


    @SerializedName("user_contracts")
    private ArrayList<UserContracts> userContracts;

    public class UserContracts {

        @SerializedName("id")
        private int id;

        @SerializedName("elevator_name")
        private String elevator_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getElevator_name() {
            return elevator_name;
        }

        public void setElevator_name(String elevator_name) {
            this.elevator_name = elevator_name;
        }
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ArrayList<PeriodicServices> getPeriodic_services() {
        return periodic_services;
    }

    public void setPeriodic_services(ArrayList<PeriodicServices> periodic_services) {
        this.periodic_services = periodic_services;
    }

    public ArrayList<UserContracts> getUserContracts() {
        return userContracts;
    }

    public void setUserContracts(ArrayList<UserContracts> userContracts) {
        this.userContracts = userContracts;
    }
}
