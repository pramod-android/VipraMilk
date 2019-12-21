package com.mywork.vipramilk.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mywork.vipramilk.dao.CustomerDataDao;
import com.mywork.vipramilk.entity.CustomerData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities={CustomerData.class},version = 1,exportSchema = false)
public abstract class VipraMilkDatabase extends RoomDatabase {
    public abstract CustomerDataDao customerDataDao();

    private static volatile VipraMilkDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 6;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static VipraMilkDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VipraMilkDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            VipraMilkDatabase.class, "vipra_milk_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}