package com.mywork.vipramilk.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.repository.CustomerDataRepository;

import java.util.List;

public class CustomerDataViewModel extends AndroidViewModel {
    private CustomerDataRepository customerDataRepository;

    private LiveData<List<CustomerData>> customerDataList;

    public CustomerDataViewModel (Application application) {
        super(application);
        customerDataRepository = new CustomerDataRepository(application);
        customerDataList = customerDataRepository.getAllCustomers();
    }

    public LiveData<List<CustomerData>> getAllCustomers() { return customerDataList; }

    public void insertCustomerData(CustomerData customerData) {
        customerDataRepository.insertCustomerData(customerData);
    }

    public void updateCustomerData(CustomerData customerData) {
        customerDataRepository.updateCustomerData(customerData);
    }

}
