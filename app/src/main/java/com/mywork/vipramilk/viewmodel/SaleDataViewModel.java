package com.mywork.vipramilk.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mywork.vipramilk.entity.RouteData;
import com.mywork.vipramilk.entity.SaleData;
import com.mywork.vipramilk.repository.RouteDataRepository;
import com.mywork.vipramilk.repository.SalesDataRepository;

import java.util.List;

public class SaleDataViewModel extends AndroidViewModel {
    private SalesDataRepository salesDataRepository;

    private LiveData<List<SaleData>> saleDataList;

    public SaleDataViewModel(Application application) {
        super(application);
        salesDataRepository = new SalesDataRepository(application);
        saleDataList = salesDataRepository.getAllSoldItems();
    }

    public LiveData<List<SaleData>> getAllSoldItems() { return saleDataList; }

    public void insertSaleData(SaleData saleData) {
        salesDataRepository.insertSaleData(saleData);
    }

    public void updateSaleData(SaleData saleData) {
        salesDataRepository.updateSaleData(saleData);
    }

}

