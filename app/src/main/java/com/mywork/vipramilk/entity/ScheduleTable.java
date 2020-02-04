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

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("month")
    @Expose
    @ColumnInfo(name = "month")
    private int month=0;

    @PrimaryKey(autoGenerate = true)
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


}
