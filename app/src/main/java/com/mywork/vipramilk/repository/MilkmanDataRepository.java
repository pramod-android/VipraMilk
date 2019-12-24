package com.mywork.vipramilk.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.mywork.vipramilk.dao.MilkmanDataDao;
import com.mywork.vipramilk.database.VipraMilkDatabase;
import com.mywork.vipramilk.entity.MilkmanData;
import java.util.List;

public class MilkmanDataRepository {
    private MilkmanDataDao milkmanDataDao;
    private LiveData<List<MilkmanData>> milkmanDataList;


    public MilkmanDataRepository(Application application) {
        VipraMilkDatabase db = VipraMilkDatabase.getDatabase(application);
        milkmanDataDao = db.milkmanDataDao();
        milkmanDataList = milkmanDataDao.getAllMilkMans();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<MilkmanData>> getAllMilkMans() {
        return milkmanDataList;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertMilkmanData(MilkmanData milkmanData) {
        VipraMilkDatabase.databaseWriteExecutor.execute(() -> {
            milkmanDataDao.insertMilkmanData(milkmanData);
        });
    }

    public void updateMilkmanData(MilkmanData milkmanData) {
        VipraMilkDatabase.databaseWriteExecutor.execute(() -> {
            milkmanDataDao.updateMilkmanData(milkmanData);
        });
    }

    public void deleteMilkMan(int id) {
        VipraMilkDatabase.databaseWriteExecutor.execute(() -> {
            milkmanDataDao.deleteMilkMan(id);
        });
    }
}
