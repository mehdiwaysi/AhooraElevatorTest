package com.snayab.ahooraelevator.ui.fragments.category.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ElevatorTypesResponse {

    @SerializedName("status")
    private Integer status;

    @SerializedName("elevators")
    private ArrayList<Elevators> elevators;

    public class Elevators {
        @SerializedName("id")
        private Integer id;

        @SerializedName("name")
        private String name;

        @SerializedName("picture")
        private String picture;

        @SerializedName("description")
        private String description;

        @SerializedName("children")
        private ArrayList<SubElevators> sub_elevators;

        public class SubElevators {
            @SerializedName("id")
            private Integer id;

            @SerializedName("name")
            private String name;

            @SerializedName("picture")
            private String picture;

            @SerializedName("description")
            private String description;

            @SerializedName("children")
            private ArrayList<Sub_Elevators> sub_elevators;

            public class Sub_Elevators {
                @SerializedName("id")
                private Integer id;

                @SerializedName("name")
                private String name;

                @SerializedName("picture")
                private String picture;

                @SerializedName("description")
                private String description;



                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
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

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }


            public ArrayList<Sub_Elevators> getSub_elevators() {
                return sub_elevators;
            }

            public void setSub_elevators(ArrayList<Sub_Elevators> sub_elevators) {
                this.sub_elevators = sub_elevators;
            }
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ArrayList<SubElevators> getSub_elevators() {
            return sub_elevators;
        }

        public void setSub_elevators(ArrayList<SubElevators> sub_elevators) {
            this.sub_elevators = sub_elevators;
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ArrayList<Elevators> getElevators() {
        return elevators;
    }

    public void setElevators(ArrayList<Elevators> elevators) {
        this.elevators = elevators;
    }
}
