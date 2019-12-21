package com.mywork.vipramilk.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "route_table")
public class RouteData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("route_id")
    @Expose
    @ColumnInfo(name = "route_id")
    private int routeId=0;

    @SerializedName("route_name")
    @Expose
    @ColumnInfo(name = "route_name")
    private String routeName;

    @SerializedName("route_area")
    @Expose
    @ColumnInfo(name = "route_area")
    private String routeArea;

    @SerializedName("route_number")
    @Expose
    @ColumnInfo(name = "route_number")
    private String routeNumber;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteArea() {
        return routeArea;
    }

    public void setRouteArea(String routeArea) {
        this.routeArea = routeArea;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

}
