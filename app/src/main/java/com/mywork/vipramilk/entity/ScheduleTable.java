package com.mywork.vipramilk.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mywork.vipramilk.ScheduleDataConverter;

import java.util.List;

@Entity(tableName = "schedule_table")
public class ScheduleTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    private int id=0;

    @SerializedName("customer_id")
    @Expose
    @ColumnInfo(name = "customer_id")
    private int customerId;

    @NonNull
    @SerializedName("month")
    @Expose
    @ColumnInfo(name = "month")
    private int month=0;

    @NonNull
    @SerializedName("year")
    @Expose
    @ColumnInfo(name = "year")
    private int year=0;

    @TypeConverters({ScheduleDataConverter.class})
    @NonNull
    @SerializedName("scheduleDataList")
    @Expose
    @ColumnInfo(name = "scheduleDataList")
    List<ScheduleData> scheduleDataList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @NonNull
    public List<ScheduleData> getScheduleDataList() {
        return scheduleDataList;
    }

    public void setScheduleDataList(@NonNull List<ScheduleData> scheduleDataList) {
        this.scheduleDataList = scheduleDataList;
    }
}
