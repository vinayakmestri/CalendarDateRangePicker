package com.archit.calendardaterangepickerdemo;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.archit.calendardaterangepicker.customviews.CalendarListener;
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DateRangeCalendarView calendar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = findViewById(R.id.calendar);

        final Typeface typeface = Typeface.createFromAsset(getAssets(), "JosefinSans-Regular.ttf");
        calendar.setFonts(typeface);

        calendar.setCalendarListener(new CalendarListener() {
            @Override
            public void onFirstDateSelected(@NonNull final Calendar startDate) {
                Toast.makeText(MainActivity.this, "Start Date: " + startDate.getTime().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDateRangeSelected(@NonNull final Calendar startDate, @NonNull final Calendar endDate) {
                Toast.makeText(MainActivity.this, "Start Date: " + startDate.getTime().toString() + " End date: " + endDate.getTime().toString(), Toast.LENGTH_SHORT).show();
            }

        });

        findViewById(R.id.btnReset).setOnClickListener(v -> calendar.resetAllSelectedViews());

//        calendar.setNavLeftImage(ContextCompat.getDrawable(this,R.drawable.ic_left));
//        calendar.setNavRightImage(ContextCompat.getDrawable(this,R.drawable.ic_right));

        final Calendar startMonth = Calendar.getInstance();
        startMonth.add(Calendar.MONTH, -2);
        final Calendar endMonth = (Calendar) startMonth.clone();
        endMonth.add(Calendar.MONTH, 5);
        Log.d(TAG, "Start month: " + startMonth.getTime().toString() + " :: End month: " + endMonth.getTime().toString());
        calendar.setVisibleMonthRange(startMonth, endMonth);

        final Calendar startDateSelectable = (Calendar) startMonth.clone();
        startDateSelectable.add(Calendar.DATE,20);
        final Calendar endDateSelectable = (Calendar) endMonth.clone();
        endDateSelectable.add(Calendar.DATE, -20);
        Log.d(TAG, "startDateSelectable: " + startDateSelectable.getTime().toString() + " :: endDateSelectable: " + endDateSelectable.getTime().toString());
        calendar.setSelectableDateRange(startDateSelectable, endDateSelectable);

        final Calendar startSelectedDate = (Calendar) startDateSelectable.clone();
        startSelectedDate.add(Calendar.DATE, 10);
        final Calendar endSelectedDate = (Calendar) endDateSelectable.clone();
        endSelectedDate.add(Calendar.DATE, -10);
        Log.d(TAG, "startSelectedDate: " + startSelectedDate.getTime().toString() + " :: endSelectedDate: " + endSelectedDate.getTime().toString());
        calendar.setSelectedDateRange(startSelectedDate, endSelectedDate);

        final Calendar current = Calendar.getInstance();
        calendar.setCurrentMonth(current);
    }
}
