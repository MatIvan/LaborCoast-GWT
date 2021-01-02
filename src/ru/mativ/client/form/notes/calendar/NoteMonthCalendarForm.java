package ru.mativ.client.form.notes.calendar;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.form.notes.calendar.selectable.CalendarWidgetSelectable;
import ru.mativ.client.service.proxy.NoteServiceProxy;
import ru.mativ.client.widgets.AdvancedMonthPicker;
import ru.mativ.shared.bean.NoteCalendarDay;

public class NoteMonthCalendarForm extends Composite {
    private static final Logger Log = Logger.getLogger(NoteMonthCalendarForm.class.getName());
    private static final NoteServiceProxy noteService = LaborCoast.getNoteServiceProxy();

    private VerticalPanel mainPanel;
    private CalendarWidgetSelectable calendarWidget;
    private AdvancedMonthPicker advancedMonthPicker;

    public NoteMonthCalendarForm() {
        init();
        build();
        initWidget(mainPanel);
    }

    private void init() {
        mainPanel = new VerticalPanel();
        calendarWidget = new CalendarWidgetSelectable();
        advancedMonthPicker = new AdvancedMonthPicker();
        advancedMonthPicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                loadMonth(event.getValue());
            }
        });
    }

    private void build() {
        mainPanel.add(advancedMonthPicker);
        mainPanel.add(calendarWidget);
    }

    public void loadMonth(Date date) {
        advancedMonthPicker.setValue(date);
        noteService.getCalendarDaysByMonth(date, new AsyncCallback<List<NoteCalendarDay>>() {

            @Override
            public void onSuccess(List<NoteCalendarDay> result) {
                calendarWidget.setData(result);
            }

            @Override
            public void onFailure(Throwable caught) {
                Log.log(Level.SEVERE, "Error loading NoteMonthCalendar.", caught);
            }
        });
    }

    public void select(Date date) {
        calendarWidget.select(date);
    }
}
