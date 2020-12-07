package ru.mativ.client.widgets;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.Format;

public class AdvancedDatePicker extends HasValueComposite<Date> {
    private static final String FORMAT_FULL = "dd MMMM yyyy";

    private DateBox dateBox;
    private Button prevDayButton;
    private Button nextDayButton;

    public AdvancedDatePicker() {
        super(new HorizontalPanel());
    }

    @Override
    protected void init() {
        initPrevDayButton();
        initDateBox();
        initNextDayButton();
    }

    @Override
    protected void build() {
        getMainPanel().add(prevDayButton);
        getMainPanel().add(dateBox);
        getMainPanel().add(nextDayButton);
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

    protected void addToValue(int number) {
        Date date = dateBox.getValue();
        CalendarUtil.addDaysToDate(date, number);
        setValue(date, true);
    }

    private void initDateBox() {
        dateBox = new DateBox();
        dateBox.getDatePicker().setYearArrowsVisible(true);
        dateBox.setFormat(getFormat());
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

    protected Format getFormat() {
        DateTimeFormat dateFormat = DateTimeFormat.getFormat(FORMAT_FULL);
        return new DateBox.DefaultFormat(dateFormat);
    }
}
