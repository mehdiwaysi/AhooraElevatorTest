package com.snayab.ahooraelevator.ui.about_company.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class AboutCompanyResponse {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("about_us")
    private AboutUs aboutUs;

    public class AboutUs {

        @SerializedName("id")
        private Integer id;
        @SerializedName("description")
        private String description;
        @SerializedName("footer")
        private String footer;
        @SerializedName("instagram")
        private String instagram;
        @SerializedName("telegram")
        private String telegram;
        @SerializedName("website")
        private String website;
        @SerializedName("phone")
        private String phone;
        @SerializedName("portfolio")
        private ArrayList<Portfolio> portfolio;

        public class Portfolio {
            @SerializedName("id")
            private Integer id;
            @SerializedName("about_us_id")
            private Integer about_us_id;
            @SerializedName("name")
            private String name;
            @SerializedName("picture")
            private String picture;
            @SerializedName("link")
            private String link;
            @SerializedName("package_name")
            private String package_name;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getAbout_us_id() {
                return about_us_id;
            }

            public void setAbout_us_id(Integer about_us_id) {
                this.about_us_id = about_us_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getPackage_name() {
                return package_name;
            }

            public void setPackage_name(String package_name) {
                this.package_name = package_name;
            }
        }

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

        public String getFooter() {
            return footer;
        }

        public void setFooter(String footer) {
            this.footer = footer;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public ArrayList<Portfolio> getPortfolio() {
            return portfolio;
        }

        public void setPortfolio(ArrayList<Portfolio> portfolio) {
            this.portfolio = portfolio;
        }
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public AboutUs getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(AboutUs aboutUs) {
        this.aboutUs = aboutUs;
    }
}
