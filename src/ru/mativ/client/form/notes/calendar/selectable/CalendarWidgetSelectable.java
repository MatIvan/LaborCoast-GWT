package ru.mativ.client.form.notes.calendar.selectable;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.HasSelectionChangedHandlers;

import ru.mativ.client.form.notes.calendar.CalendarWidget;
import ru.mativ.client.form.notes.calendar.NoteDayCalendarWidget;
import ru.mativ.shared.bean.NoteCalendarDay;

public class CalendarWidgetSelectable extends CalendarWidget implements HasSelectionChangedHandlers {

    private Date selectedDate;
    private NoteDayCalendarWidgetSelectable selectedWidget;

    public CalendarWidgetSelectable() {
        super();
    }

    @Override
    protected NoteDayCalendarWidget makeNoteDayCalendarWidget(String waterText, NoteCalendarDay noteCalendarDay) {
        NoteDayCalendarWidgetSelectable res = new NoteDayCalendarWidgetSelectable(waterText, noteCalendarDay);

        if (noteCalendarDay != null) {
            final Date date = noteCalendarDay.getDate();
            res.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    select(date);
                    fireSelectionChanged();
                }
            });
        }
        return res;
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

    @Override
    public HandlerRegistration addSelectionChangeHandler(SelectionChangeEvent.Handler handler) {
        return this.addHandler(handler, SelectionChangeEvent.getType());
    }

    private void fireSelectionChanged() {
        SelectionChangeEvent.fire(this);
    }

    public Date getSelectedDate() {
        return selectedDate;
    }
}
