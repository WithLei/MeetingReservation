package com.android.renly.meetingreservation.listener;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.Calendar;

public abstract class MyOnDateSetListener implements DatePickerDialog.OnDateSetListener {
    private int mYear;
    private int mMonth;
    private int mDay;

    public MyOnDateSetListener() {
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        mYear = year;
        mMonth = month;
        mDay = day;
        String days;
        if (mMonth + 1 < 10) {
            if (mDay < 10) {
                days = new StringBuffer().append(mYear).append("/").append("0").append(mMonth + 1)
                        .append("/").append("0").append(mDay).toString();
            } else {
                days = new StringBuffer().append(mYear).append("/").append("0").append(mMonth + 1)
                        .append("/").append(mDay).toString();
            }
        } else {
            if (mDay < 10) {
                days = new StringBuffer().append(mYear).append("/").append(mMonth + 1)
                        .append("/").append("0").append(mDay).toString();
            } else {
                days = new StringBuffer().append(mYear).append("/").append(mMonth + 1)
                        .append("/").append(mDay).toString();
            }
        }
        afterSetDate(days);
    }

    public abstract void afterSetDate(String days);
}
