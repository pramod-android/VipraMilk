package com.mywork.vipramilk;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mywork.vipramilk.entity.ScheduleData;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ScheduleDataConverter {
    @TypeConverter
    public static List<ScheduleData> storedStringToMyObjects(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<ScheduleData>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String myObjectsToStoredString(List<ScheduleData> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }
}
