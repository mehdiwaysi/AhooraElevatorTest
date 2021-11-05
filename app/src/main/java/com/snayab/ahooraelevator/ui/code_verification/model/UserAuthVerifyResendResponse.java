package com.snayab.ahooraelevator.ui.code_verification.model;

import com.google.gson.annotations.SerializedName;

public class UserAuthVerifyResendResponse {

    @SerializedName("status")
    private Integer status;

//    @SerializedName("user")
//    private User user;

//    public class User {
//        @SerializedName("name")
//        private String name;
//        @SerializedName("phone")
//        private String phone;
//        @SerializedName("id")
//        private Integer id;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getPhone() {
//            return phone;
//        }
//
//        public void setPhone(String phone) {
//            this.phone = phone;
//        }
//
//        public Integer getUser_id() {
//            return id;
//        }
//
//        public void setUser_id(Integer id) {
//            this.id = id;
//        }
//    }
//
//    @SerializedName("token")
//    private String token;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
