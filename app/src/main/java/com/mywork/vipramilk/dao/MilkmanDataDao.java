package com.mywork.vipramilk.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.MilkmanData;

import java.util.List;

@Dao
public interface MilkmanDataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMilkmanData(MilkmanData milkmanData);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateMilkmanData(MilkmanData milkmanData);

    @Query("DELETE FROM milkman_table")
    void deleteAll();

    @Query("DELETE FROM milkman_table WHERE milkman_id=:milkmanId")
    void deleteMilkMan(int milkmanId);

    @Query("SELECT * from milkman_table ORDER BY date ASC")
    LiveData<List<MilkmanData>> getAllMilkMans();
}
