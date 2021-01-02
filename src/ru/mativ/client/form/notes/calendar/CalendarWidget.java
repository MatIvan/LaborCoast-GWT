package ru.mativ.client.form.notes.calendar;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.shared.bean.NoteCalendarDay;

public class CalendarWidget extends Composite {
    private static final int GRID_ROWS_MAX = 6;
    private static final int GRID_COLUMNS_MAX = 7;

    private VerticalPanel mainPanel;
    protected Grid grid;

    public CalendarWidget() {
        init();
        build();
        initWidget(mainPanel);
    }

    protected void afterDataUpdated() {
        //do nothing
    }

    private void init() {
        mainPanel = new VerticalPanel();

        grid = new Grid(GRID_ROWS_MAX, GRID_COLUMNS_MAX);
        grid.setBorderWidth(2);
    }

    private void build() {
        mainPanel.add(new CalendarHeader());
        mainPanel.add(grid);
    }

    public void setData(List<NoteCalendarDay> noteCalendarDayList) {
        clear();
        for (NoteCalendarDay noteCalendarDay : noteCalendarDayList) {
            int row = noteCalendarDay.getWeekOfMonth() - 1;
            int col = noteCalendarDay.getDayOfWeek() - 1;
            String waterText = String.valueOf(noteCalendarDay.getDayOfMonth());

            Widget dayWidget = makeNoteDayCalendarWidget(waterText, noteCalendarDay);
            grid.setWidget(row, col, dayWidget);
        }
        afterDataUpdated();
    }

    private void clear() {
        grid.clear();
        for (int i = 0; i < grid.getRowCount(); i++) {
            for (int j = 0; j < grid.getColumnCount(); j++) {
                Widget dayWidgetEmpty = makeNoteDayCalendarWidget(null, null);
                grid.setWidget(i, j, dayWidgetEmpty);
            }
        }
    }

    protected NoteDayCalendarWidget makeNoteDayCalendarWidget(String waterText, NoteCalendarDay noteCalendarDay) {
        return new NoteDayCalendarWidget(waterText, noteCalendarDay);
    }
}
