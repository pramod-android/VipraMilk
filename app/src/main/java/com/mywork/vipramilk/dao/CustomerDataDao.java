package com.mywork.vipramilk.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mywork.vipramilk.entity.CustomerData;

import java.util.List;

@Dao
public interface CustomerDataDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCustomerData(CustomerData customerData);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateCustomerData(CustomerData customerData);

    @Query("UPDATE customer_table SET is_active = :isactive WHERE customer_id =:id")
    void moveToDeleted(boolean isactive, int id);

    @Query("DELETE FROM customer_table")
    void deleteAll();

    @Query("SELECT * from customer_table WHERE is_active =1 ORDER BY date ASC")
    LiveData<List<CustomerData>> getAllCustomers();

}
