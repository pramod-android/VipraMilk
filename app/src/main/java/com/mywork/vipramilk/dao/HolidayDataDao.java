package com.mywork.vipramilk.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mywork.vipramilk.entity.HolidayData;
import com.mywork.vipramilk.entity.SaleData;

import java.util.List;

@Dao
public interface HolidayDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHolidayData(HolidayData holidayData);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateHolidayData(HolidayData holidayData);

    @Query("SELECT * FROM holiday_data_table WHERE id = :id")
    HolidayData getItemId(int id);



    @Query("DELETE FROM holiday_data_table")
    void deleteAll();

    @Query("SELECT * from holiday_data_table ")
    LiveData<List<HolidayData>> getAllHolidays();

    @Query("SELECT * from holiday_data_table WHERE month=:month")
    LiveData<List<HolidayData>> getMonthsHolidays(int month);

}
