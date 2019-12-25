package com.mywork.vipramilk.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Holiday {
    @SerializedName("holiday_list")
    @Expose
    private List<HolidayData> holidayList = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<HolidayData> getHolidayList() {
        return holidayList;
    }

    public void setHolidayList(List<HolidayData> holidayList) {
        this.holidayList = holidayList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

