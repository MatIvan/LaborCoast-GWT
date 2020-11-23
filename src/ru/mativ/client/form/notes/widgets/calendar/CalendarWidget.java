package ru.mativ.client.form.notes.widgets.calendar;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.form.notes.calendar.NoteDayCalendarWidget;
import ru.mativ.shared.bean.NoteCalendarDay;

public class CalendarWidget extends Composite {

    private Grid grid;

    public CalendarWidget() {
        init();
        build();
        bind();
        initWidget(grid);
    }

    private void init() {
        grid = new Grid(6, 7);
        grid.setBorderWidth(2);
    }

    private void build() {

    }

    private void bind() {

    }

    public void setData(List<NoteCalendarDay> noteCalendarDayList) {
        clear();
        for (NoteCalendarDay noteCalendarDay : noteCalendarDayList) {
            int row = noteCalendarDay.getWeekOfMonth() - 1;
            int col = noteCalendarDay.getDayOfWeek() - 1;
            Widget dayWidget = new NoteDayCalendarWidget(noteCalendarDay);
            grid.setWidget(row, col, dayWidget);
        }
    }

    private void clear() {
        grid.clear();
        for (int i = 0; i < grid.getRowCount(); i++) {
            for (int j = 0; j < grid.getColumnCount(); j++) {
                Widget dayWidgetEmpty = new NoteDayCalendarWidget(null);
                grid.setWidget(i, j, dayWidgetEmpty);
            }
        }
    }
}
