package com.bcopstein.demo.Dominio;

import java.util.Calendar;

public class ToCalendar {
    public static Calendar getCalendar(int day, int month, int year) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);

        date.set(Calendar.MONTH, month);

        date.set(Calendar.DAY_OF_MONTH, day);

        return date;
    }
}