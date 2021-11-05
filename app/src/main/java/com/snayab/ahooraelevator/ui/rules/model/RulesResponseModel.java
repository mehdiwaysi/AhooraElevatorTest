package com.snayab.ahooraelevator.ui.rules.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RulesResponseModel {

    @SerializedName("rules")
    private ArrayList<Rules> rules;

    public class Rules {
        @SerializedName("id")
        private Integer id;
        @SerializedName("title")
        private String title;
        @SerializedName("description")
        private String description;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

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

    public ArrayList<Rules> getRules() {
        return rules;
    }

    public void setRules(ArrayList<Rules> rules) {
        this.rules = rules;
    }
}
