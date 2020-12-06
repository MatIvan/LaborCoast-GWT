package ru.mativ.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public static List<Integer> getAllMonthDays(Date date) {
        Calendar calendar = getCalendar(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        int firstDay = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        int lastDay = calendar.get(Calendar.DAY_OF_MONTH);

        List<Integer> allMonthDays = new ArrayList<Integer>();
        for (int i = firstDay; i <= lastDay; i++) {
            allMonthDays.add(i);
        }
        return allMonthDays;
    }
}
