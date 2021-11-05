package com.snayab.ahooraelevator.ui.about_us.model;

import com.google.gson.annotations.SerializedName;

public class AboutUsResponseModel {


    @SerializedName("about_us")
    private AboutUs aboutUs;

    public class AboutUs {
        @SerializedName("id")
        private Integer id;
        @SerializedName("description")
        private String description;
        @SerializedName("picture")
        private String picture;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }



        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }

    public AboutUs getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(AboutUs aboutUs) {
        this.aboutUs = aboutUs;
    }
}
