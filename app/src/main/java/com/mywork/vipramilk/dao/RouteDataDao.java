package com.mywork.vipramilk.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.RouteData;

import java.util.List;
@Dao
public interface RouteDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRouteData(RouteData routeData);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateRouteData(RouteData routeData);

    @Query("SELECT * from route_table ORDER BY route_id ASC")
    LiveData<List<RouteData>> getAllRoutes();
}
