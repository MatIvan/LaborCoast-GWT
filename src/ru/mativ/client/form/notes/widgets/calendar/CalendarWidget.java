package ru.mativ.client.form.notes.widgets.calendar;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;

import ru.mativ.shared.bean.NoteCalendarDay;

public class CalendarWidget extends Composite {
    private static final Logger Log = Logger.getLogger(CalendarWidget.class.getName());

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
            String tempText = noteCalendarDay.getDate().toString();
            int row = noteCalendarDay.getWeekOfMonth() - 1;
            int col = noteCalendarDay.getDayOfWeek() - 1;

            Panel panel = makeFillerPanel();
            panel.add(new Label(tempText));
            grid.setWidget(row, col, panel);
        }
    }

    private void clear() {
        grid.clear();
        for (int i = 0; i < grid.getRowCount(); i++) {
            for (int j = 0; j < grid.getColumnCount(); j++) {
                grid.setWidget(i, j, makeFillerPanel());
            }
        }
    }

    private Panel makeFillerPanel() {
        SimplePanel panel = new SimplePanel();
        panel.setSize("100px", "100px");
        return panel;
    }
}
