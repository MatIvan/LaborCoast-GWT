package ru.mativ.client.widgets;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.user.datepicker.client.DateBox;

public class AdvancedDatePicker extends HasValueComposite<Date> {
    private static final DateTimeFormat DATE_FORMAT = DateTimeFormat.getFormat("dd MMMM yyyy");

    private HorizontalPanel mainPanel;
    private DateBox dateBox;
    private Button prevDayButton;
    private Button nextDayButton;

    public AdvancedDatePicker() {
        super();
    }

    @Override
    protected void init() {
        mainPanel = new HorizontalPanel();
        initPrevDayButton();
        initDateBox();
        initNextDayButton();
    }

    @Override
    protected void build() {
        mainPanel.add(prevDayButton);
        mainPanel.add(dateBox);
        mainPanel.add(nextDayButton);
    }

    @Override
    protected Widget getMainPanel() {
        return mainPanel;
    }

    private void initPrevDayButton() {
        prevDayButton = new Button(" < ");
        prevDayButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                addToValue(-1);
            }
        });
    }

    private void initNextDayButton() {
        nextDayButton = new Button(" > ");
        nextDayButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                addToValue(1);
            }
        });
    }

    private void addToValue(int number) {
        Date date = dateBox.getValue();
        CalendarUtil.addDaysToDate(date, number);
        setValue(date, true);
    }

    private void initDateBox() {
        dateBox = new DateBox();
        dateBox.getDatePicker().setYearArrowsVisible(true);
        dateBox.setFormat(new DateBox.DefaultFormat(DATE_FORMAT));
        dateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                fireValueChangeEvent();
            }
        });
    }

    @Override
    public Date getValue() {
        return dateBox.getValue();
    }

    @Override
    public void setValue(Date value, boolean fireEvents) {
        dateBox.setValue(value, fireEvents);
    }
}
