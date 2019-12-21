package com.mywork.vipramilk.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mywork.vipramilk.dao.CustomerDataDao;
import com.mywork.vipramilk.dao.RouteDataDao;
import com.mywork.vipramilk.database.VipraMilkDatabase;
import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.RouteData;

import java.util.List;

public class RouteDataRepository {
    private RouteDataDao routeDataDao;
    private LiveData<List<RouteData>> routeDataList;


    public RouteDataRepository(Application application) {
        VipraMilkDatabase db = VipraMilkDatabase.getDatabase(application);
        routeDataDao = db.routeDataDao();
        routeDataList = routeDataDao.getAllRoutes();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<RouteData>> getAllRoutes() {
        return routeDataList;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertRouteData(RouteData routeData) {
        VipraMilkDatabase.databaseWriteExecutor.execute(() -> {
            routeDataDao.insertRouteData(routeData);
        });
    }

    public void updateRouteData(RouteData routeData) {
        VipraMilkDatabase.databaseWriteExecutor.execute(() -> {
            routeDataDao.updateRouteData(routeData);
        });
    }

}

