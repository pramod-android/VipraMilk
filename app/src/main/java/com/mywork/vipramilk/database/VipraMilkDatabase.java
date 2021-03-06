package com.mywork.vipramilk.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mywork.vipramilk.dao.CustomerDataDao;
import com.mywork.vipramilk.dao.HolidayDataDao;
import com.mywork.vipramilk.dao.MilkmanDataDao;
import com.mywork.vipramilk.dao.RouteDataDao;
import com.mywork.vipramilk.dao.SaleDataDao;
import com.mywork.vipramilk.dao.ScheduleTableDao;
import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.HolidayData;
import com.mywork.vipramilk.entity.Milkman;
import com.mywork.vipramilk.entity.MilkmanData;
import com.mywork.vipramilk.entity.RouteData;
import com.mywork.vipramilk.entity.SaleData;
import com.mywork.vipramilk.entity.ScheduleData;
import com.mywork.vipramilk.entity.ScheduleTable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities={CustomerData.class, RouteData.class, MilkmanData.class, ScheduleTable.class, SaleData.class, HolidayData.class},version = 1,exportSchema = false)
public abstract class VipraMilkDatabase extends RoomDatabase {
    public abstract CustomerDataDao customerDataDao();
    public abstract RouteDataDao routeDataDao();
    public abstract MilkmanDataDao milkmanDataDao();
    public abstract SaleDataDao saleDataDao();
    public abstract HolidayDataDao holidayDataDao();
    public abstract ScheduleTableDao scheduleTableDao();


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