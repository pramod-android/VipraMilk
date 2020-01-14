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

    @Query("SELECT * from customer_table WHERE is_active=1 AND route_id=:routeId ORDER BY route_sequence ASC")
    LiveData<List<CustomerData>> getRouteCustomers(int routeId);
}
