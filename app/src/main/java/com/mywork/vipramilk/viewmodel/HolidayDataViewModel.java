package com.mywork.vipramilk.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mywork.vipramilk.dao.HolidayDataDao;
import com.mywork.vipramilk.entity.HolidayData;
import com.mywork.vipramilk.entity.SaleData;
import com.mywork.vipramilk.repository.HolidayDataRepository;
import com.mywork.vipramilk.repository.SalesDataRepository;

import java.util.List;

public class HolidayDataViewModel extends AndroidViewModel {
    private HolidayDataRepository holidayDataRepository;

    private LiveData<List<HolidayData>> holidayDataList;

    public HolidayDataViewModel(Application application) {
        super(application);
        holidayDataRepository = new HolidayDataRepository(application);
        holidayDataList = holidayDataRepository.getAllHolidays();
    }

    public LiveData<List<HolidayData>> getAllHolidays() {
        return holidayDataList;
    }

    public LiveData<List<HolidayData>> getMonthsHolidays(int month) {
        return holidayDataRepository.getMonthsHolidays(month);
    }

    public void insertHolidayData(HolidayData holidayData) {
        holidayDataRepository.insertHolidayData(holidayData);
    }

    public void updateHolidayData(HolidayData holidayData) {
        holidayDataRepository.updateHolidayData(holidayData);
    }
    public HolidayData getItemId(int id) {
        return holidayDataRepository.getItemId(id);

    }
}


