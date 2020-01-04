package com.mywork.vipramilk.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.mywork.vipramilk.R;
import com.mywork.vipramilk.entity.Holiday;
import com.mywork.vipramilk.entity.HolidayData;
import com.mywork.vipramilk.viewmodel.HolidayDataViewModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
public class AddHolidaysActivity extends AppCompatActivity {
    private static final String TAG = "AddHolidaysActivity";
    CalendarView calendarView;
    List<Calendar> selectedDates=new ArrayList<>();
    Button buttonSubmit;
    HolidayDataViewModel holidayDataViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_holidays);

        calendarView = findViewById(R.id.calendarView);
        buttonSubmit=findViewById(R.id.buttonSubmit);

        holidayDataViewModel=new ViewModelProvider(this).get(HolidayDataViewModel.class);

      holidayDataViewModel.getMonthsHolidays(0).observe(this, new Observer<List<HolidayData>>() {
          @Override
          public void onChanged(List<HolidayData> holidayData) {


             if(holidayData.iterator().hasNext()){
                 holidayData.iterator().next();
                 Log.i(TAG,"holiday");

             }


              Log.i(TAG,"holidays "+holidayData.size());
           //   calendarView.setSelectedDates();
          }
      });


        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();

                if(selectedDates.contains(clickedDayCalendar)){
                    selectedDates.remove(clickedDayCalendar);
                }else {
                    selectedDates.add(clickedDayCalendar);
                }

               // selectedDates = calendarView.getSelectedDates();
                Toast.makeText(AddHolidaysActivity.this,"date :"+clickedDayCalendar.get(Calendar.DAY_OF_MONTH)+"size :"+selectedDates.size(), Toast.LENGTH_SHORT).show();

            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSubmit.setEnabled(false);
                HolidayData holidayData=new HolidayData();
                holidayData.setId(selectedDates.get(0).get(Calendar.DAY_OF_MONTH)+selectedDates.get(0).get(Calendar.MONTH)+selectedDates.get(0).get(Calendar.YEAR));
                holidayData.setMonth(selectedDates.get(0).get(Calendar.MONTH));
                holidayData.setYear(selectedDates.get(0).get(Calendar.YEAR));
                holidayData.setCustomerId(1);
                for(Calendar calendar:selectedDates){

                    switch (calendar.get(Calendar.DAY_OF_MONTH)){
                        case 1:{
                            holidayData.setDay1(1);
                            break;
                        }
                        case 2:{
                            holidayData.setDay2(1);
                            break;
                        }
                        case 3:{
                            holidayData.setDay3(1);
                            break;
                        }
                        case 4:{
                            holidayData.setDay4(1);
                            break;
                        }
                        case 5:{
                            holidayData.setDay5(1);
                            break;
                        }
                        case 6:{
                            holidayData.setDay6(1);
                            break;
                        }
                        case 7:{
                            holidayData.setDay7(1);
                            break;
                        }
                        case 8:{
                            holidayData.setDay8(1);
                            break;
                        }
                        case 9:{
                            holidayData.setDay9(1);
                            break;
                        }
                        case 10:{
                            holidayData.setDay10(1);
                            break;
                        }
                        case 11:{
                            holidayData.setDay11(1);
                            break;
                        }
                        case 12:{
                            holidayData.setDay12(1);
                            break;
                        }
                        case 13:{
                            holidayData.setDay13(1);
                            break;
                        }
                        case 14:{
                            holidayData.setDay14(1);
                            break;
                        }
                        case 15:{
                            holidayData.setDay15(1);
                            break;
                        }
                        case 16:{
                            holidayData.setDay16(1);
                            break;
                        }
                        case 17:{
                            holidayData.setDay17(1);
                            break;
                        }
                        case 18:{
                            holidayData.setDay18(1);
                            break;
                        }
                        case 19:{
                            holidayData.setDay19(1);
                            break;
                        }
                        case 20:{
                            holidayData.setDay20(1);
                            break;
                        }
                        case 21:{
                            holidayData.setDay21(1);
                            break;
                        }
                        case 22:{
                            holidayData.setDay22(1);
                            break;
                        }  case 23:{
                            holidayData.setDay23(1);
                            break;
                        }
                        case 24:{
                            holidayData.setDay24(1);
                            break;
                        }
                        case 25:{
                            holidayData.setDay25(1);
                            break;
                        }
                        case 26:{
                            holidayData.setDay26(1);
                            break;
                        }
                        case 27:{
                            holidayData.setDay27(1);
                            break;
                        }
                        case 28:{
                            holidayData.setDay28(1);
                            break;
                        }
                        case 29:{
                            holidayData.setDay29(1);
                            break;
                        }  case 30:{
                            holidayData.setDay30(1);
                            break;
                        }
                        case 31:{
                            holidayData.setDay31(1);
                            break;
                        }
                    }
                }

                new myAsyncTask(holidayDataViewModel,holidayData).execute(selectedDates.get(0).get(Calendar.DAY_OF_MONTH)+selectedDates.get(0).get(Calendar.MONTH)+selectedDates.get(0).get(Calendar.YEAR));


              //  HolidayData holidayData1=holidayDataViewModel.getItemId(selectedDates.get(0).get(Calendar.DAY_OF_MONTH)+selectedDates.get(0).get(Calendar.MONTH)+selectedDates.get(0).get(Calendar.YEAR));

//                if(holidayData1 == null){
//                    holidayDataViewModel.insertHolidayData(holidayData);
//                }else {
//                    holidayDataViewModel.updateHolidayData(holidayData);
//                }



            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        buttonSubmit.setEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private static class myAsyncTask extends AsyncTask<Integer, Void, HolidayData> {


        HolidayDataViewModel holidayDataViewModel;
        HolidayData updatedHolidayData;
        public myAsyncTask(HolidayDataViewModel model,HolidayData hData) {
            holidayDataViewModel=model;
            updatedHolidayData=hData;

        }

        @Override
        protected HolidayData doInBackground(Integer... params) {
            return holidayDataViewModel.getItemId(params[0]);

        }

        @Override
        protected void onPostExecute(HolidayData holidayData) {
            if(holidayData==null){
                holidayDataViewModel.insertHolidayData(updatedHolidayData);
            }else {
                holidayDataViewModel.updateHolidayData(updatedHolidayData);
            }
        }
    }
}
