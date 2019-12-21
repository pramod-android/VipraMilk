package com.mywork.vipramilk.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Route {

    @SerializedName("route")
    @Expose
    private List<RouteData> route = null;

    @SerializedName("status")
    @Expose
    private String status;

    public List<RouteData> getRoute() {
        return route;
    }

    public void setRoute(List<RouteData> route) {
        this.route = route;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}