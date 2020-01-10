package com.mywork.vipramilk.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.gson.Gson;
import com.mywork.vipramilk.R;
import com.mywork.vipramilk.entity.Holiday;
import com.mywork.vipramilk.entity.HolidayData;
import com.mywork.vipramilk.viewmodel.HolidayDataViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AddHolidaysActivity extends AppCompatActivity {
    private static final String TAG = "AddHolidaysActivity";
    CalendarView calendarView;
    List<Calendar> selectedDates = new ArrayList<>();
    Button buttonSubmit;
    HolidayDataViewModel holidayDataViewModel;
    int custId;
    int monthOfYear;
    int year;
    String hId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_holidays);

        Intent intent = getIntent();

        custId = intent.getIntExtra("custid", 0);


        calendarView = findViewById(R.id.calendarView);
        buttonSubmit = findViewById(R.id.buttonSubmit);

       

        Date currentTime = Calendar.getInstance().getTime();

        Calendar c = Calendar.getInstance();
        monthOfYear = c.get(Calendar.MONTH);
        year=c.get(Calendar.YEAR);
        
       hId = "" + custId + monthOfYear + year;

        Toast.makeText(this, "" + monthOfYear, Toast.LENGTH_SHORT).show();


        holidayDataViewModel = new ViewModelProvider(this).get(HolidayDataViewModel.class);

        holidayDataViewModel.getMonthsHolidays(monthOfYear).observe(this, new Observer<List<HolidayData>>() {
            @Override
            public void onChanged(List<HolidayData> holidayData) {
                setData(holidayData);
            }
        });


        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();

                if (selectedDates.size() <= 0) {
                    selectedDates = calendarView.getSelectedDates();
                }
                if (selectedDates.contains(clickedDayCalendar)) {
                    selectedDates.remove(clickedDayCalendar);
                } else {
                    selectedDates.add(clickedDayCalendar);
                }
                if (!buttonSubmit.isEnabled()) {
                    //then the button is enabled.
                    buttonSubmit.setEnabled(true);
                }
                // selectedDates = calendarView.getSelectedDates();
                Toast.makeText(AddHolidaysActivity.this, "date :" + clickedDayCalendar.get(Calendar.DAY_OF_MONTH) + "size :" + selectedDates.size(), Toast.LENGTH_SHORT).show();
            }
        });
        calendarView.setOnPreviousPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                selectedDates.clear();
                buttonSubmit.setEnabled(true);
                if (monthOfYear == 0) {
                    monthOfYear = 11;
                    year=year-1;
                } else {
                    monthOfYear = monthOfYear - 1;
                }
                Toast.makeText(AddHolidaysActivity.this, "" + monthOfYear, Toast.LENGTH_SHORT).show();

                holidayDataViewModel.getMonthsHolidays(monthOfYear).observe(AddHolidaysActivity.this, new Observer<List<HolidayData>>() {
                    @Override
                    public void onChanged(List<HolidayData> holidayData) {
                        setData(holidayData);
                    }
                });
            }
        });
        calendarView.setOnForwardPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                selectedDates.clear();
                buttonSubmit.setEnabled(true);
                if (monthOfYear == 11) {
                    monthOfYear = 0;
                    year=year+1;
                } else {
                    monthOfYear = monthOfYear + 1;
                }
                Toast.makeText(AddHolidaysActivity.this, "" + monthOfYear, Toast.LENGTH_SHORT).show();

                holidayDataViewModel.getMonthsHolidays(monthOfYear).observe(AddHolidaysActivity.this, new Observer<List<HolidayData>>() {
                    @Override
                    public void onChanged(List<HolidayData> holidayData) {
                        setData(holidayData);
                    }
                });
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveHolidays();
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

        public myAsyncTask(HolidayDataViewModel model, HolidayData hData) {
            holidayDataViewModel = model;
            updatedHolidayData = hData;

        }

        @Override
        protected HolidayData doInBackground(Integer... params) {
            return holidayDataViewModel.getItemId(params[0]);

        }

        @Override
        protected void onPostExecute(HolidayData holidayData) {
            if (holidayData == null) {
                holidayDataViewModel.insertHolidayData(updatedHolidayData);
            } else {
                holidayDataViewModel.updateHolidayData(updatedHolidayData);
            }
        }
    }

    private void setData(List<HolidayData> holidayData) {
        if (holidayData != null && holidayData.size() > 0) {
            List<Calendar> calendarList = new ArrayList<>();
            if (holidayData.get(0).getDay1() == 1) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 1);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay2() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 2);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay3() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 3);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay4() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 4);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay5() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 5);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay6() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 6);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay7() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 7);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay8() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 8);
                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay9() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 9);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay10() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 10);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay11() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 11);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay12() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 12);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay13() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 13);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay14() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 14);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay15() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 15);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay16() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 16);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay17() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 17);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay18() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 18);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay19() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 19);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay20() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 20);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay21() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 21);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay22() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 22);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay23() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 23);


                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay24() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 24);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay25() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 25);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay26() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 26);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay27() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 27);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay28() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 28);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay29() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 29);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay30() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 30);

                calendarList.add(calendar);
            }
            if (holidayData.get(0).getDay31() == 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, holidayData.get(0).getMonth());
                calendar.set(Calendar.YEAR, holidayData.get(0).getYear());
                calendar.set(Calendar.DAY_OF_MONTH, 31);
                calendarList.add(calendar);
            }
            calendarView.setSelectedDates(calendarList);
        }
    }

    private void SaveHolidays() {
        buttonSubmit.setEnabled(false);
        HolidayData holidayData = new HolidayData();
        holidayData.setMonth(monthOfYear);
       // int month=selectedDates.get(0).get(Calendar.MONTH);
       // int year= selectedDates.get(0).get(Calendar.YEAR);
        hId = "" + custId + monthOfYear + year;
        holidayData.setId(Integer.valueOf(hId));
        holidayData.setYear(selectedDates.get(0).get(Calendar.YEAR));
        holidayData.setCustomerId(custId);
        for (Calendar calendar : selectedDates) {
            
            switch (calendar.get(Calendar.DAY_OF_MONTH)) {
                case 1: {
                    holidayData.setDay1(1);
                    break;
                }
                case 2: {
                    holidayData.setDay2(1);
                    break;
                }
                case 3: {
                    holidayData.setDay3(1);
                    break;
                }
                case 4: {
                    holidayData.setDay4(1);
                    break;
                }
                case 5: {
                    holidayData.setDay5(1);
                    break;
                }
                case 6: {
                    holidayData.setDay6(1);
                    break;
                }
                case 7: {
                    holidayData.setDay7(1);
                    break;
                }
                case 8: {
                    holidayData.setDay8(1);
                    break;
                }
                case 9: {
                    holidayData.setDay9(1);
                    break;
                }
                case 10: {
                    holidayData.setDay10(1);
                    break;
                }
                case 11: {
                    holidayData.setDay11(1);
                    break;
                }
                case 12: {
                    holidayData.setDay12(1);
                    break;
                }
                case 13: {
                    holidayData.setDay13(1);
                    break;
                }
                case 14: {
                    holidayData.setDay14(1);
                    break;
                }
                case 15: {
                    holidayData.setDay15(1);
                    break;
                }
                case 16: {
                    holidayData.setDay16(1);
                    break;
                }
                case 17: {
                    holidayData.setDay17(1);
                    break;
                }
                case 18: {
                    holidayData.setDay18(1);
                    break;
                }
                case 19: {
                    holidayData.setDay19(1);
                    break;
                }
                case 20: {
                    holidayData.setDay20(1);
                    break;
                }
                case 21: {
                    holidayData.setDay21(1);
                    break;
                }
                case 22: {
                    holidayData.setDay22(1);
                    break;
                }
                case 23: {
                    holidayData.setDay23(1);
                    break;
                }
                case 24: {
                    holidayData.setDay24(1);
                    break;
                }
                case 25: {
                    holidayData.setDay25(1);
                    break;
                }
                case 26: {
                    holidayData.setDay26(1);
                    break;
                }
                case 27: {
                    holidayData.setDay27(1);
                    break;
                }
                case 28: {
                    holidayData.setDay28(1);
                    break;
                }
                case 29: {
                    holidayData.setDay29(1);
                    break;
                }
                case 30: {
                    holidayData.setDay30(1);
                    break;
                }
                case 31: {
                    holidayData.setDay31(1);
                    break;
                }
            }
        }

        new myAsyncTask(holidayDataViewModel, holidayData).execute(Integer.valueOf("" + selectedDates.get(0).get(Calendar.DAY_OF_MONTH) + selectedDates.get(0).get(Calendar.MONTH) + selectedDates.get(0).get(Calendar.YEAR)));


        //  HolidayData holidayData1=holidayDataViewModel.getItemId(selectedDates.get(0).get(Calendar.DAY_OF_MONTH)+selectedDates.get(0).get(Calendar.MONTH)+selectedDates.get(0).get(Calendar.YEAR));

//                if(holidayData1 == null){
//                    holidayDataViewModel.insertHolidayData(holidayData);
//                }else {
//                    holidayDataViewModel.updateHolidayData(holidayData);
//                }


    }
}
