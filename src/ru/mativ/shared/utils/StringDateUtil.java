package ru.mativ.shared.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringDateUtil {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return FORMAT.format(calendar.getTime());
    }

    public static String getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return FORMAT.format(calendar.getTime());
    }

    public static String getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return FORMAT.format(calendar.getTime());
    }

    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
