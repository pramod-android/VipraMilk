package com.mywork.vipramilk.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sale {
    @SerializedName("purchase_data")
    @Expose
    private List<SaleData> saleData = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<SaleData> getSaleData() {
        return saleData;
    }

    public void setSaleData(List<SaleData> saleData) {
        this.saleData = saleData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
