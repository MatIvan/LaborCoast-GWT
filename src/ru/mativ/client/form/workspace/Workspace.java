package ru.mativ.client.form.workspace;

import java.util.Date;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.view.client.SelectionChangeEvent;

import ru.mativ.client.form.notes.calendar.NoteMonthCalendarForm;
import ru.mativ.client.form.notes.day.NoteDayForm;
import ru.mativ.client.form.notes.day.NoteDayFormPresenter;
import ru.mativ.client.form.notes.day.impl.NoteDayFormImpl;
import ru.mativ.client.form.notes.day.impl.NoteDayFormModel;
import ru.mativ.client.form.notes.day.impl.NoteDayFormPresenterImpl;

public class Workspace extends Composite {
    private static final String STYLE_MAIN = "workspace-main";
    private static final String STYLE_LEFT = "workspace-left";
    private static final String STYLE_RIGHT = "workspace-right";
    private static final String STYLE_TOP = "workspace-top";
    private static final String STYLE_BOTTOM = "workspace-bottom";

    private FlowPanel mainPanel;
    private FlowPanel leftContainer;
    private FlowPanel rightContainer;
    private FlowPanel topContainer;
    private FlowPanel bottomContainer;

    NoteMonthCalendarForm noteMonthCalendarForm;
    NoteDayForm noteDayForm;
    NoteDayFormPresenter noteDayFormPresenter;

    public Workspace() {
        initPanels();
        initForms();
        build();
        initWidget(mainPanel);
    }

    private void initPanels() {
        mainPanel = new FlowPanel();
        leftContainer = new FlowPanel();
        rightContainer = new FlowPanel();
        topContainer = new FlowPanel();
        bottomContainer = new FlowPanel();

        mainPanel.addStyleName(STYLE_MAIN);
        leftContainer.addStyleName(STYLE_LEFT);
        rightContainer.addStyleName(STYLE_RIGHT);
        topContainer.addStyleName(STYLE_TOP);
        bottomContainer.addStyleName(STYLE_BOTTOM);
    }

    private void initForms() {
        //month
        noteMonthCalendarForm = new NoteMonthCalendarForm();
        noteMonthCalendarForm.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                noteDayFormPresenter.update(noteMonthCalendarForm.getSelectedDate());
            }
        });

        //day
        noteDayForm = new NoteDayFormImpl();
        noteDayForm.addValueChangeHandler(new ValueChangeHandler<NoteDayFormModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<NoteDayFormModel> event) {
                Date date = event.getValue().getDate();
                noteMonthCalendarForm.loadMonth(date);
                noteMonthCalendarForm.select(date);
            }
        });
        noteDayFormPresenter = new NoteDayFormPresenterImpl(noteDayForm);
    }

    private void build() {
        mainPanel.add(leftContainer);
        mainPanel.add(rightContainer);
        rightContainer.add(topContainer);
        rightContainer.add(bottomContainer);

        leftContainer.add(noteMonthCalendarForm);
        noteDayFormPresenter.go(topContainer);
    }

    public void load(Date date) {
        noteMonthCalendarForm.loadMonth(date);
        noteMonthCalendarForm.select(date);
        noteDayFormPresenter.update(date);
    }
}
