package com.mywork.vipramilk.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Milkman {
    @SerializedName("mikman_data")
    @Expose
    private List<MilkmanData> milkmanData;

    @SerializedName("status")
    @Expose
    private String status;
}
