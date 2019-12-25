package com.mywork.vipramilk.repository;
import android.app.Application;
import androidx.lifecycle.LiveData;
import com.mywork.vipramilk.dao.RouteDataDao;
import com.mywork.vipramilk.dao.SaleDataDao;
import com.mywork.vipramilk.database.VipraMilkDatabase;
import com.mywork.vipramilk.entity.RouteData;
import com.mywork.vipramilk.entity.SaleData;
import java.util.List;
public class SalesDataRepository   {

    private SaleDataDao saleDataDao;
    private LiveData<List<SaleData>> saleDataList;


    public SalesDataRepository(Application application) {
        VipraMilkDatabase db = VipraMilkDatabase.getDatabase(application);
        saleDataDao = db.saleDataDao();
        saleDataList = saleDataDao.getAllSoldItems();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<SaleData>> getAllSoldItems() {
        return saleDataList;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertSaleData(SaleData saleData) {
        VipraMilkDatabase.databaseWriteExecutor.execute(() -> {
            saleDataDao.insertSaleData(saleData);
        });
    }

    public void updateSaleData(SaleData saleData) {
        VipraMilkDatabase.databaseWriteExecutor.execute(() -> {
            saleDataDao.updateSaleData(saleData);
        });
    }

}
