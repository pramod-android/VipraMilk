package com.mywork.vipramilk.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "sale_data")
public class SaleData {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    private int Id = 0;

    @SerializedName("customer_id")
    @Expose
    @ColumnInfo(name = "customer_id")
    private int customerId;
    @SerializedName("month")
    @Expose
    @ColumnInfo(name = "month")
    private int month;
    @SerializedName("year")
    @Expose
    @ColumnInfo(name = "year")
    private int year;
    @SerializedName("day")
    @Expose
    @ColumnInfo(name = "day")
    private int day;
    @SerializedName("one_liter")
    @Expose
    @ColumnInfo(name = "one_liter")
    private int oneLiter;
    @SerializedName("half_liter")
    @Expose
    @ColumnInfo(name = "half_liter")
    private int halfLiter;

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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getOneLiter() {
        return oneLiter;
    }

    public void setOneLiter(int oneLiter) {
        this.oneLiter = oneLiter;
    }

    public int getHalfLiter() {
        return halfLiter;
    }

    public void setHalfLiter(int halfLiter) {
        this.halfLiter = halfLiter;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
