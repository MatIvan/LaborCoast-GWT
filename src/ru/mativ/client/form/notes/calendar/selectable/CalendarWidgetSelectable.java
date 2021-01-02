package ru.mativ.client.form.notes.calendar.selectable;

import java.util.Date;

import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.form.notes.calendar.CalendarWidget;
import ru.mativ.client.form.notes.calendar.NoteDayCalendarWidget;
import ru.mativ.shared.bean.NoteCalendarDay;

public class CalendarWidgetSelectable extends CalendarWidget {

    private Date selectedDate;
    private NoteDayCalendarWidgetSelectable selectedWidget;

    public CalendarWidgetSelectable() {
        super();
    }

    @Override
    protected NoteDayCalendarWidget makeNoteDayCalendarWidget(String waterText, NoteCalendarDay noteCalendarDay) {
        return new NoteDayCalendarWidgetSelectable(waterText, noteCalendarDay);
    }

    @Override
    protected void afterDataUpdated() {
        super.afterDataUpdated();
        if (selectedDate != null) {
            select(selectedDate);
        }
    }

    public void select(Date date) {
        clearSelection();
        selectedDate = date;
        for (int i = 0; i < grid.getRowCount(); i++) {
            for (int j = 0; j < grid.getColumnCount(); j++) {
                Widget dayWidget = grid.getWidget(i, j);
                if (dayWidget instanceof NoteDayCalendarWidgetSelectable) {
                    NoteDayCalendarWidgetSelectable widget = (NoteDayCalendarWidgetSelectable) dayWidget;
                    if (widget.getValue() != null && widget.getValue().getDate().equals(selectedDate)) {
                        widget.setSelected(true);
                        selectedWidget = widget;
                        return;
                    }
                }
            }
        }
    }

    private void clearSelection() {
        if (selectedWidget != null) {
            selectedWidget.setSelected(false);
        }
        selectedWidget = null;
        selectedDate = null;
    }

}
