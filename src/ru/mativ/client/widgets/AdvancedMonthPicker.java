package ru.mativ.client.widgets;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.Format;

public class AdvancedMonthPicker extends AdvancedDatePicker {

    private static final String FORMAT_MONTH = "MMMM yyyy";

    public AdvancedMonthPicker() {
        super();
    }

    protected void addToValue(int number) {
        Date date = getValue();
        CalendarUtil.addMonthsToDate(date, number);
        setValue(date, true);
    }

    protected Format getFormat() {
        DateTimeFormat dateFormat = DateTimeFormat.getFormat(FORMAT_MONTH);
        return new DateBox.DefaultFormat(dateFormat);
    }
}
