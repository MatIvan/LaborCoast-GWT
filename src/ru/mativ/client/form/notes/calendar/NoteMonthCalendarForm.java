package ru.mativ.client.form.notes.calendar;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.service.proxy.NoteServiceProxy;
import ru.mativ.shared.bean.NoteCalendarDay;

public class NoteMonthCalendarForm extends Composite {
    private static final Logger Log = Logger.getLogger(NoteMonthCalendarForm.class.getName());
    private static final NoteServiceProxy noteService = LaborCoast.getNoteServiceProxy();

    private VerticalPanel mainPanel;
    private CalendarWidget calendarWidget;

    public NoteMonthCalendarForm() {
        init();
        build();
        bind();
        initWidget(mainPanel);
    }

    private void init() {
        mainPanel = new VerticalPanel();
        calendarWidget = new CalendarWidget();
    }

    private void build() {
        mainPanel.add(calendarWidget);
    }

    private void bind() {

    }

    public void loadMonth(Date date) {
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
}
