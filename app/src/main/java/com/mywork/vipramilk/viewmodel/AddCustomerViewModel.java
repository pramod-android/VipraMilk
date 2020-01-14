package com.mywork.vipramilk.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.RouteData;
import com.mywork.vipramilk.repository.CustomerDataRepository;
import com.mywork.vipramilk.repository.RouteDataRepository;

import java.util.List;

public class AddCustomerViewModel extends AndroidViewModel {
    private CustomerDataRepository customerDataRepository;
    private RouteDataRepository routeDataRepository;

    private LiveData<List<RouteData>> routeDataList;

    public AddCustomerViewModel(@NonNull Application application) {
        super(application);
        customerDataRepository = new CustomerDataRepository(application);
        routeDataRepository = new RouteDataRepository(application);
        routeDataList = routeDataRepository.getAllRoutes();
    }

    public LiveData<List<RouteData>> getAllRoutes() { return routeDataList; }

    public void insertCustomerData(CustomerData customerData) {
        customerDataRepository.insertCustomerData(customerData);
    }

    public void updateCustomerData(CustomerData customerData) {
        customerDataRepository.updateCustomerData(customerData);
    }

}
