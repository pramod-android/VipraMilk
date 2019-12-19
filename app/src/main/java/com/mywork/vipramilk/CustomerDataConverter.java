package com.mywork.vipramilk;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mywork.vipramilk.entity.CustomerData;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class CustomerDataConverter {
    @TypeConverter
    public static List<CustomerData> storedStringToMyObjects(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<CustomerData>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String myObjectsToStoredString(List<CustomerData> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }
}
