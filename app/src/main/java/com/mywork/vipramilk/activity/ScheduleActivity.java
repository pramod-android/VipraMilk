package com.mywork.vipramilk.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mywork.vipramilk.R;
import com.mywork.vipramilk.adapter.ScheduleListAdapter;
import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.ScheduleData;
import com.mywork.vipramilk.entity.ScheduleTable;
import com.mywork.vipramilk.viewmodel.HolidayDataViewModel;
import com.mywork.vipramilk.viewmodel.ScheduleActivityViewModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements ScheduleListAdapter.ItemClickListener {
    CustomerData customerData;
    ScheduleListAdapter adapter;
    HolidayDataViewModel holidayDataViewModel;
    ScheduleActivityViewModel scheduleActivityViewModel;
    List<ScheduleData> scheduleDataArrayList;

    boolean updateFlag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Intent intent = getIntent();

        customerData = (CustomerData) intent.getSerializableExtra("cust");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new ScheduleListAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        holidayDataViewModel = new ViewModelProvider(this).get(HolidayDataViewModel.class);
        scheduleActivityViewModel = new ViewModelProvider(this).get(ScheduleActivityViewModel.class);
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
      //  ScheduleTable scheduleTableData = scheduleActivityViewModel.getScheduleData(customerData.getcustomerId(), month, year);

     //   SetData(scheduleTableData);

        new myAsyncTask(scheduleActivityViewModel).execute(customerData.getcustomerId(),month,year);



//        holidayDataViewModel.getMonthsHolidays(Integer.valueOf(hId)).observe(this, new Observer<List<HolidayData>>() {
//            @Override
//            public void onChanged(List<HolidayData> holidayData) {
//               // setData(holidayData);
//                SetData();
//            }
//        });
    }

    private void SetData(ScheduleTable scheduleTableData) {

        if (scheduleTableData == null) {
           scheduleDataArrayList = new ArrayList<>();

            Calendar c = Calendar.getInstance();

            int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int i = 1; i < monthMaxDays; i++) {
                ScheduleData scheduleData = new ScheduleData();

                if (customerData.getDeliveryOn().equals("Daily")) {
                    scheduleData.setDate(i);
                    scheduleData.setOnltr(customerData.getQtyOneLtr());
                    scheduleData.setHalfltr(customerData.getQtyHalfLtr());
                    scheduleDataArrayList.add(scheduleData);
                } else if (customerData.getDeliveryOn().equals("EvenDay")) {
                    scheduleData.setDate(i);
                    if (i % 2 == 0) {
                        scheduleData.setOnltr(customerData.getQtyOneLtr());
                        scheduleData.setHalfltr(customerData.getQtyHalfLtr());
                    }
                    scheduleDataArrayList.add(scheduleData);
                } else if (customerData.getDeliveryOn().equals("OddDay")) {
                    scheduleData.setDate(i);
                    if (i % 2 != 0) {
                        scheduleData.setOnltr(customerData.getQtyOneLtr());
                        scheduleData.setHalfltr(customerData.getQtyHalfLtr());
                    }
                    scheduleDataArrayList.add(scheduleData);
                }
            }
            adapter.setScheduleData(scheduleDataArrayList);
           updateFlag=true;
        } else {

            updateFlag=true;
            scheduleDataArrayList=scheduleTableData.getScheduleDataList();
            adapter.setScheduleData(scheduleDataArrayList);
        }

    }

    @Override
    public void onItemClick(View view, ScheduleData scheduleData) {
        Toast.makeText(this, "Item click "+scheduleData.getDate(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemMessageClick(View view, ScheduleData scheduleData) {

    }

    @Override
    public void onItemEditClick(View view, ScheduleData scheduleData) {

    }

    @Override
    public void onItemDeleteClick(View view, ScheduleData scheduleData) {

    }

    public void OnSubmitClick(View view) {
        ScheduleTable scheduleTable = new ScheduleTable();
        scheduleTable.setCustomerId(customerData.getCustomerId());
        scheduleTable.setMonth(Calendar.MONTH);
        scheduleTable.setYear(Calendar.YEAR);
        scheduleTable.setScheduleDataList(scheduleDataArrayList);

        if (updateFlag){
            scheduleActivityViewModel.insertScheduleData(scheduleTable);
        }else {
            scheduleActivityViewModel.insertScheduleData(scheduleTable);
        }
    }
    private class myAsyncTask extends AsyncTask<Integer, Void, ScheduleTable> {


        ScheduleActivityViewModel viewModel;
       // ScheduleTable scheduleTable;
    String day;

    myAsyncTask(ScheduleActivityViewModel model) {
        viewModel = model;
       // scheduleTable = hData;
       // day = daystr;
    }

    @Override
    protected ScheduleTable doInBackground(Integer... params) {
        return scheduleActivityViewModel.getScheduleData(params[0], params[1], params[2]);
    }

    @Override
    protected void onPostExecute(ScheduleTable scheduleTable) {

        SetData(scheduleTable);
    }
}

}
//private class myAsyncTask extends AsyncTask<Integer, Void, HolidayData> {
//
//
//    CustomerDataViewModel viewModel;
//    CustomerData customerData;
//    String day;
//
//    public myAsyncTask(CustomerDataViewModel model, CustomerData hData, String daystr) {
//        viewModel = model;
//        customerData = hData;
//        day = daystr;
//    }
//
//    @Override
//    protected HolidayData doInBackground(Integer... params) {
//        return viewModel.checkIsHolidays(params[0], params[1], params[2]);
//    }
//
//    @Override
//    protected void onPostExecute(HolidayData holidayData) {
//
//        SetData(holidayData);
//    }
//}
//
//    private void SetData(HolidayData holidayData) {
//
//        if(holidayData!=null) {
//            List<ScheduleData> scheduleDataArrayList = new ArrayList<>();
//
//            Calendar c = Calendar.getInstance();
//            int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
//            for (int i = 1; i < monthMaxDays; i++) {
//                ScheduleData scheduleData = new ScheduleData();
//
//                if (customerData.getDeliveryOn().equals("Daily")) {
//                    scheduleData.setDate(i);
//                    scheduleData.setOnltr(customerData.getQtyOneLtr());
//                    scheduleData.setHalfltr(customerData.getQtyHalfLtr());
//                    scheduleDataArrayList.add(scheduleData);
//                } else if (customerData.getDeliveryOn().equals("EvenDay")) {
//                    scheduleData.setDate(i);
//                    if (i % 2 == 0) {
//                        scheduleData.setOnltr(customerData.getQtyOneLtr());
//                        scheduleData.setHalfltr(customerData.getQtyHalfLtr());
//                    }
//                    scheduleDataArrayList.add(scheduleData);
//                } else if (customerData.getDeliveryOn().equals("OddDay")) {
//                    scheduleData.setDate(i);
//                    if (i % 2 != 0) {
//                        scheduleData.setOnltr(customerData.getQtyOneLtr());
//                        scheduleData.setHalfltr(customerData.getQtyHalfLtr());
//                    }
//                    scheduleDataArrayList.add(scheduleData);
//                }
//            }
//            adapter.setScheduleData(scheduleDataArrayList);
//        }else {
//            Gson gson = new Gson();
//            String jsonString = gson.toJson(holidayData);
//            try {
//                JSONObject jsonObject = new JSONObject(jsonString);
//                Iterator<String> keys = jsonObject.keys();
//                int count=0;
//                while (keys.hasNext()) {
//                    if(count<=4){
//                        count++;
//                    }else {
//
//                    }
//                }
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//
//    }