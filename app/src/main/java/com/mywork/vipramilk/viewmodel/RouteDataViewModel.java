package com.mywork.vipramilk.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.RouteData;
import com.mywork.vipramilk.repository.CustomerDataRepository;
import com.mywork.vipramilk.repository.RouteDataRepository;

import java.util.List;

public class RouteDataViewModel extends AndroidViewModel {
    private RouteDataRepository routeDataRepository;

    private LiveData<List<RouteData>> routeDataList;

    public RouteDataViewModel (Application application) {
        super(application);
        routeDataRepository = new RouteDataRepository(application);
        routeDataList = routeDataRepository.getAllRoutes();
    }

    public LiveData<List<RouteData>> getAllRoutes() { return routeDataList; }

    public void insertCustomerData(RouteData routeData) {
        routeDataRepository.insertRouteData(routeData);
    }

    public void updateCustomerData(RouteData routeData) {
        routeDataRepository.updateRouteData(routeData);
    }

}

