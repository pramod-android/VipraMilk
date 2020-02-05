package com.mywork.vipramilk.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.mywork.vipramilk.database.VipraMilkDatabase;
import com.mywork.vipramilk.entity.ScheduleTable;
import com.mywork.vipramilk.repository.MilkmanDataRepository;
import com.mywork.vipramilk.repository.ScheduleRepository;

public class ScheduleActivityViewModel extends AndroidViewModel {
    ScheduleRepository scheduleRepository;

    public ScheduleActivityViewModel(@NonNull Application application) {
        super(application);
        scheduleRepository = new ScheduleRepository(application);
    }

    public void insertScheduleData(ScheduleTable scheduleTable) {
        scheduleRepository.insertScheduleData(scheduleTable);
    }

    public void updateScheduleData(ScheduleTable scheduleTable) {
        scheduleRepository.updateScheduleData(scheduleTable);

    }

    public ScheduleTable getScheduleData(int cust_id,int month,int year) {
        return scheduleRepository.getScheduleData(cust_id,month,year);
    }
}
