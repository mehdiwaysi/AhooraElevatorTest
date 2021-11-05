package com.snayab.ahooraelevator.ui.contact_us.model;

import com.google.gson.annotations.SerializedName;

public class ContactUsResponseModel {

    @SerializedName("contact_us")
    private ContactUs contact_us;

    public class ContactUs {
        @SerializedName("id")
        private Integer id;
        @SerializedName("description")
        private String description;

        @SerializedName("address")
        private String address;

        @SerializedName("primary_phone")
        private String primary_phone;
        @SerializedName("secondary_phone")
        private String secondary_phone;

        @SerializedName("instagram")
        private String instagram;
        @SerializedName("telegram")
        private String telegram;
        @SerializedName("website")
        private String website;
        @SerializedName("whatsapp")
        private String whatsapp;

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

        public String getInstagram() {
            return instagram;
        }

        public void setInstagram(String instagram) {
            this.instagram = instagram;
        }

        public String getTelegram() {
            return telegram;
        }

        public void setTelegram(String telegram) {
            this.telegram = telegram;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPrimary_phone() {
            return primary_phone;
        }

        public void setPrimary_phone(String primary_phone) {
            this.primary_phone = primary_phone;
        }

        public String getSecondary_phone() {
            return secondary_phone;
        }

        public void setSecondary_phone(String secondary_phone) {
            this.secondary_phone = secondary_phone;
        }

        public String getWhatsapp() {
            return whatsapp;
        }

        public void setWhatsapp(String whatsapp) {
            this.whatsapp = whatsapp;
        }
    }

    public ContactUs getContact_us() {
        return contact_us;
    }

    public void setContact_us(ContactUs contact_us) {
        this.contact_us = contact_us;
    }
}
