package com.mywork.vipramilk.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "milkman_table")
public class MilkmanData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("milkman_id")
    @Expose
    @ColumnInfo(name = "milkman_id")
    private int milkmanId = 0;

    @SerializedName("milkman_name")
    @Expose
    @ColumnInfo(name = "milkman_name")
    private String milkmanName;

    @SerializedName("contact_one")
    @Expose
    @ColumnInfo(name = "contact_one")
    private String contactOne;

    @SerializedName("contact_two")
    @Expose
    @ColumnInfo(name = "contact_two")
    private String contactTwo;

    @SerializedName("contact_whatsapp")
    @Expose
    @ColumnInfo(name = "contact_whatsapp")
    private String contactWhatsapp;

    @SerializedName("contact_email")
    @Expose
    @ColumnInfo(name = "contact_email")
    private String contactEmail;

    @SerializedName("route_id")
    @Expose
    @ColumnInfo(name = "route_id")
    private int routeId;

    @SerializedName("date")
    @Expose
    @ColumnInfo(name = "date")
    private String date;


    public int getMilkmanId() {
        return milkmanId;
    }

    public void setMilkmanId(int milkmanId) {
        this.milkmanId = milkmanId;
    }

    public String getMilkmanName() {
        return milkmanName;
    }

    public void setMilkmanName(String milkmanName) {
        this.milkmanName = milkmanName;
    }

    public String getContactOne() {
        return contactOne;
    }

    public void setContactOne(String contactOne) {
        this.contactOne = contactOne;
    }

    public String getContactTwo() {
        return contactTwo;
    }

    public void setContactTwo(String contactTwo) {
        this.contactTwo = contactTwo;
    }

    public String getContactWhatsapp() {
        return contactWhatsapp;
    }

    public void setContactWhatsapp(String contactWhatsapp) {
        this.contactWhatsapp = contactWhatsapp;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
