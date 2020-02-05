package com.mywork.vipramilk.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.mywork.vipramilk.entity.SaleData;
import com.mywork.vipramilk.entity.ScheduleData;
import com.mywork.vipramilk.entity.ScheduleTable;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ScheduleTableDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertScheduleData(ScheduleTable scheduleTableData);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateScheduleData(ScheduleTable scheduleTableData);

    @Query("DELETE FROM schedule_table")
    void deleteAll();

    @Query("SELECT * from schedule_table ")
    LiveData<List<ScheduleTable>> getAllSchedules();

    @Query("SELECT * from schedule_table WHERE customer_id=:cust_id AND month=:month AND year=:year ")
    ScheduleTable getScheduleData(int cust_id,int month,int year);


}
