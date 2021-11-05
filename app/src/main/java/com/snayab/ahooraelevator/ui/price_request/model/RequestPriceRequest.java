package com.snayab.ahooraelevator.ui.price_request.model;

import com.google.gson.annotations.SerializedName;

public class RequestPriceRequest {

    @SerializedName("user_id")
    private int user_id;
    @SerializedName("elevators")
    private byte elevators;
    @SerializedName("floors")
    private byte floors;
    @SerializedName("capacity")
    private byte capacity;
    @SerializedName("pits")
    private boolean pits;


    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }



    public boolean isPits() {
        return pits;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public byte getElevators() {
        return elevators;
    }

    public void setElevators(byte elevators) {
        this.elevators = elevators;
    }

    public byte getFloors() {
        return floors;
    }

    public void setFloors(byte floors) {
        this.floors = floors;
    }

    public byte getCapacity() {
        return capacity;
    }

    public void setCapacity(byte capacity) {
        this.capacity = capacity;
    }

    public void setPits(boolean pits) {
        this.pits = pits;
    }
}

