package com.mywork.vipramilk.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.mywork.vipramilk.entity.MilkmanData;
import com.mywork.vipramilk.repository.MilkmanDataRepository;

import java.util.List;

public class MilkManDataViewModel  extends AndroidViewModel {
    private MilkmanDataRepository milkmanDataRepository;

    private LiveData<List<MilkmanData>> milkmanDataList;

    public MilkManDataViewModel (Application application) {
        super(application);
        milkmanDataRepository = new MilkmanDataRepository(application);
        milkmanDataList = milkmanDataRepository.getAllMilkMans();
    }

    public LiveData<List<MilkmanData>> getAllMilkmans() { return milkmanDataList; }

    public void insertMilkmanData(MilkmanData milkmanData) {
        milkmanDataRepository.insertMilkmanData(milkmanData);
    }

    public void updateMilkmanData(MilkmanData milkmanData) {
        milkmanDataRepository.updateMilkmanData(milkmanData);
    }

    public void deleteMilkMan( int id) {
        milkmanDataRepository.deleteMilkMan(id);
    }

}
