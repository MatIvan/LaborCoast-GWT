package ru.mativ.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringDateUtil {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String getFirstDayOfMonth(Date date) {
        Calendar calendar = getCalendar(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return FORMAT.format(calendar.getTime());
    }

    public static String getLastDayOfMonth(Date date) {
        Calendar calendar = getCalendar(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return FORMAT.format(calendar.getTime());
    }

    public static String getDay(Date date) {
        Calendar calendar = getCalendar(date);
        return FORMAT.format(calendar.getTime());
    }

    public static int getDayOfMonth(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(1);
        calendar.setTime(date);
        return calendar;
    }
}
