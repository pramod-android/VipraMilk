package com.mywork.vipramilk.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import com.mywork.vipramilk.R;
import com.mywork.vipramilk.adapter.MilkmanListAdapter;
import com.mywork.vipramilk.adapter.ScheduleListAdapter;
import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.MilkmanData;
import com.mywork.vipramilk.entity.ScheduleData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements ScheduleListAdapter.ItemClickListener {
    CustomerData customerData;
    ScheduleListAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Intent intent = getIntent();

        customerData = (CustomerData)intent.getSerializableExtra("cust");

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        adapter = new ScheduleListAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));

        SetData();





    }

    private void SetData(){
        List<ScheduleData> scheduleDataArrayList=new ArrayList<>();

        Calendar c = Calendar.getInstance();
        int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=1;i<monthMaxDays;i++){
            ScheduleData scheduleData = new ScheduleData();

            if(customerData.getDeliveryOn().equals("Daily")) {
                scheduleData.setDate(i);
                scheduleData.setOnltr(customerData.getQtyOneLtr());
                scheduleData.setHalfltr(customerData.getQtyHalfLtr());
                scheduleDataArrayList.add(scheduleData);
            }else if(customerData.getDeliveryOn().equals("EvenDay")){
                scheduleData.setDate(i);
                if(i%2==0){
                    scheduleData.setOnltr(customerData.getQtyOneLtr());
                    scheduleData.setHalfltr(customerData.getQtyHalfLtr());
                }
                scheduleDataArrayList.add(scheduleData);
            }else if(customerData.getDeliveryOn().equals("OddDay")){
                scheduleData.setDate(i);
                if(i%2!=0){
                    scheduleData.setOnltr(customerData.getQtyOneLtr());
                    scheduleData.setHalfltr(customerData.getQtyHalfLtr());
                }
                scheduleDataArrayList.add(scheduleData);
            }
        }
        adapter.setScheduleData(scheduleDataArrayList);

    }
    @Override
    public void onItemClick(View view, ScheduleData milkmanData) {

    }

    @Override
    public void onItemMessageClick(View view, ScheduleData milkmanData) {

    }

    @Override
    public void onItemEditClick(View view, ScheduleData milkmanData) {

    }

    @Override
    public void onItemDeleteClick(View view, ScheduleData milkmanData) {

    }
}
