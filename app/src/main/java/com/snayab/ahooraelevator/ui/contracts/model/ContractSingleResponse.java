package com.snayab.ahooraelevator.ui.contracts.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

public class ContractSingleResponse {
    @SerializedName("status")
    private Integer status;
    @SerializedName("contract")
    private Contract contract;

    public class Contract {
        @SerializedName("id")
        private Integer id;

        @SerializedName("signed_at")
        private String signed_at;

        @SerializedName("installed_at")
        private String installed_at;

        @SerializedName("file")
        private String file;

        @SerializedName("total_amount")
        private Integer total_amount;

        @SerializedName("user_name")
        private String user_name;

        @SerializedName("elevator_name")
        private String elevator_name;

        @SerializedName("remaining_amount")
        private Integer remaining_amount;

        @SerializedName("payments")
        private ArrayList<Payments> payments;

        public class Payments {
            @SerializedName("id")
            private Integer id;

            @SerializedName("contract_id")
            private Integer contract_id;

            @SerializedName("type")
            private String type;

            @SerializedName("amount")
            private Integer amount;

            @SerializedName("settled_at")
            private String settled_at;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getContract_id() {
                return contract_id;
            }

            public void setContract_id(Integer contract_id) {
                this.contract_id = contract_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Integer getAmount() {
                return amount;
            }

            public void setAmount(Integer amount) {
                this.amount = amount;
            }

            public String getSettled_at() {
                return settled_at;
            }

            public void setSettled_at(String settled_at) {
                this.settled_at = settled_at;
            }
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }


        public String getSigned_at() {
            return signed_at;
        }

        public void setSigned_at(String signed_at) {
            this.signed_at = signed_at;
        }

        public String getInstalled_at() {
            return installed_at;
        }

        public void setInstalled_at(String installed_at) {
            this.installed_at = installed_at;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public Integer getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(Integer total_amount) {
            this.total_amount = total_amount;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getElevator_name() {
            return elevator_name;
        }

        public void setElevator_name(String elevator_name) {
            this.elevator_name = elevator_name;
        }

        public Integer getRemaining_amount() {
            return remaining_amount;
        }

        public void setRemaining_amount(Integer remaining_amount) {
            this.remaining_amount = remaining_amount;
        }

        public ArrayList<Payments> getPayments() {
            return payments;
        }

        public void setPayments(ArrayList<Payments> payments) {
            this.payments = payments;
        }
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
