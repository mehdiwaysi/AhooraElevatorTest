package com.snayab.ahooraelevator.ui.fragments.main.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HomePageResponse {
    @SerializedName("status")
    private Integer status;

    @SerializedName("slider")
    private ArrayList<String> sliders;

    @SerializedName("about_us")
    private AboutUs about_us;

    public class AboutUs {

        @SerializedName("title")
        private String title;
        @SerializedName("picture")
        private String picture;
        @SerializedName("description")
        private String description;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    @SerializedName("services")
    private ArrayList<Services> services;

    public class Services {

        @SerializedName("title")
        private String title;
        @SerializedName("description")
        private String description;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    @SerializedName("hasContract")
    private boolean hasContract;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ArrayList<String> getSliders() {
        return sliders;
    }

    public void setSliders(ArrayList<String> sliders) {
        this.sliders = sliders;
    }

    public AboutUs getAbout_us() {
        return about_us;
    }

    public void setAbout_us(AboutUs about_us) {
        this.about_us = about_us;
    }

    public ArrayList<Services> getServices() {
        return services;
    }

    public void setServices(ArrayList<Services> services) {
        this.services = services;
    }

    public boolean isHasContract() {
        return hasContract;
    }

    public void setHasContract(boolean hasContract) {
        this.hasContract = hasContract;
    }
}
