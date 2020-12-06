package ru.mativ.client.form.notes.calendar;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class CalendarHeader extends Composite {
    private static final String STYLE_FIELD = "CalendarHeaderField";
    private static final List<String> DAY_NAMES = Arrays.asList(
            "Понедельник",
            "Вторник",
            "Среда",
            "Четверг",
            "Пятница",
            "Суббота",
            "Воскресенье");

    private HorizontalPanel mainPanel;

    public CalendarHeader() {
        mainPanel = new HorizontalPanel();

        for (String day : DAY_NAMES) {
            Label field = new Label(day);
            field.addStyleName(STYLE_FIELD);
            mainPanel.add(field);
        }

        initWidget(mainPanel);
    }
}
