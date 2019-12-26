package com.mywork.vipramilk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.mywork.vipramilk.R;

import java.util.Calendar;
import java.util.List;

public class AddHolidaysActivity extends AppCompatActivity {
    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_holidays);

        calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                List<Calendar> selectedDates = calendarView.getSelectedDates();
                Toast.makeText(AddHolidaysActivity.this, String.valueOf(selectedDates.size()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
