package com.mywork.vipramilk.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.SaleData;

import java.util.List;

@Dao
public interface SaleDataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSaleData(SaleData saleData);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateSaleData(SaleData saleData);

    @Query("DELETE FROM sale_data")
    void deleteAll();

    @Query("SELECT * from sale_data ")
    LiveData<List<SaleData>> getAllSoldItems();

//    @Query("SELECT * from sale_data WHERE is_active =1 & route_id=:routeId ORDER BY date ASC")
//    LiveData<List<CustomerData>> getRouteCustomers(int routeId);

}