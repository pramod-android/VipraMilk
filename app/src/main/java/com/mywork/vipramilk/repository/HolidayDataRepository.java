package com.mywork.vipramilk.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mywork.vipramilk.dao.HolidayDataDao;
import com.mywork.vipramilk.dao.SaleDataDao;
import com.mywork.vipramilk.database.VipraMilkDatabase;
import com.mywork.vipramilk.entity.HolidayData;
import com.mywork.vipramilk.entity.SaleData;

import java.util.List;

public class HolidayDataRepository {
    private HolidayDataDao holidayDataDao;
    private LiveData<List<HolidayData>> holidayDataList;
    int id = 0;


    public HolidayDataRepository(Application application) {
        VipraMilkDatabase db = VipraMilkDatabase.getDatabase(application);
        holidayDataDao = db.holidayDataDao();
        holidayDataList = holidayDataDao.getAllHolidays();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<HolidayData>> getAllHolidays() {
        return holidayDataList;
    }

    public LiveData<List<HolidayData>> getMonthsHolidays(int id) {
        return holidayDataDao.getMonthsHolidays(id);
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertHolidayData(HolidayData holidayData) {
        VipraMilkDatabase.databaseWriteExecutor.execute(() -> {
            holidayDataDao.insertHolidayData(holidayData);
        });
    }

    public void updateHolidayData(HolidayData holidayData) {
        VipraMilkDatabase.databaseWriteExecutor.execute(() -> {
            holidayDataDao.updateHolidayData(holidayData);
        });
    }

    public HolidayData getItemId(int id) {
        return holidayDataDao.getItemId(id);
    }

}
