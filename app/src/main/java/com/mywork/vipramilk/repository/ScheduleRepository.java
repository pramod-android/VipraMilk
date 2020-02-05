package com.mywork.vipramilk.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mywork.vipramilk.dao.SaleDataDao;
import com.mywork.vipramilk.dao.ScheduleTableDao;
import com.mywork.vipramilk.database.VipraMilkDatabase;
import com.mywork.vipramilk.entity.HolidayData;
import com.mywork.vipramilk.entity.SaleData;
import com.mywork.vipramilk.entity.ScheduleTable;

import java.util.List;

public class ScheduleRepository {
    private ScheduleTableDao scheduleTableDao;

    public ScheduleRepository(Application application) {
        VipraMilkDatabase db = VipraMilkDatabase.getDatabase(application);
        scheduleTableDao=db.scheduleTableDao();
    }


    public void insertScheduleData(ScheduleTable scheduleTable) {
        VipraMilkDatabase.databaseWriteExecutor.execute(() -> {
            scheduleTableDao.insertScheduleData(scheduleTable);
        });
    }

    public void updateScheduleData(ScheduleTable scheduleTable) {
        VipraMilkDatabase.databaseWriteExecutor.execute(() -> {
            scheduleTableDao.updateScheduleData(scheduleTable);
        });
    }

    public ScheduleTable getScheduleData(int cust_id,int month,int year) {
        return scheduleTableDao.getScheduleData(cust_id,month,year);
    }

}

