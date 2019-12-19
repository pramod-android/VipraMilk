package com.mywork.vipramilk.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Customer {

     @SerializedName("customer_data")
    @Expose
    private List<CustomerData> customerData;

    @SerializedName("status")
    @Expose
    private String status;

    public List<CustomerData> getCustomerData() {
        return customerData;
    }

    public void setCustomerData(List<CustomerData> customerData) {
        this.customerData = customerData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
