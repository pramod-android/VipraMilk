package com.mywork.vipramilk.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mywork.vipramilk.dao.CustomerDataDao;
import com.mywork.vipramilk.database.VipraMilkDatabase;
import com.mywork.vipramilk.entity.Customer;
import com.mywork.vipramilk.entity.CustomerData;

import java.util.List;

public class CustomerDataRepository {
    private CustomerDataDao customerDataDao;
    private LiveData<List<CustomerData>> cutomerDataList;


    public CustomerDataRepository(Application application) {
        VipraMilkDatabase db = VipraMilkDatabase.getDatabase(application);
        customerDataDao = db.customerDataDao();
        cutomerDataList = customerDataDao.getAllCustomers();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<CustomerData>> getAllCustomers() {
        return cutomerDataList;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertCustomerData(CustomerData customerData) {
        VipraMilkDatabase.databaseWriteExecutor.execute(() -> {
            customerDataDao.insertCustomerData(customerData);
        });
    }

    public void updateCustomerData(CustomerData customerData) {
        VipraMilkDatabase.databaseWriteExecutor.execute(() -> {
            customerDataDao.updateCustomerData(customerData);
        });
    }

    public void moveToDeleted(boolean isActive, int id) {
        VipraMilkDatabase.databaseWriteExecutor.execute(() -> {
            customerDataDao.moveToDeleted(isActive,id);
        });
    }

}
