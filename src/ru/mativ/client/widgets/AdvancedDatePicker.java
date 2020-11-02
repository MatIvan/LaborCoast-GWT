package ru.mativ.client.widgets;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.user.datepicker.client.DateBox;

public class AdvancedDatePicker extends Composite implements HasValue<Date> {
    private static final DateTimeFormat DATE_FORMAT = DateTimeFormat.getFormat("dd MMMM yyyy");

    private HorizontalPanel mainPanel;
    private DateBox dateBox;
    private Button prevDayButton;
    private Button nextDayButton;

    public AdvancedDatePicker() {
        initGui();
        initWidget(buildGui());
    }

    private void initGui() {
        mainPanel = new HorizontalPanel();
        initPrevDayButton();
        initDateBox();
        initNextDayButton();
    }

    private Widget buildGui() {
        mainPanel.add(prevDayButton);
        mainPanel.add(dateBox);
        mainPanel.add(nextDayButton);
        return mainPanel;
    }

    private void initPrevDayButton() {
        prevDayButton = new Button(" < ");
        prevDayButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Date date = dateBox.getValue();
                CalendarUtil.addDaysToDate(date, -1);
                setValue(date);
            }
        });
    }

    private void initNextDayButton() {
        nextDayButton = new Button(" > ");
        nextDayButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Date date = dateBox.getValue();
                CalendarUtil.addDaysToDate(date, 1);
                setValue(date);
            }
        });
    }

    private void initDateBox() {
        dateBox = new DateBox();
        dateBox.getDatePicker().setYearArrowsVisible(true);
        dateBox.setFormat(new DateBox.DefaultFormat(DATE_FORMAT));
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> handler) {
        return dateBox.addValueChangeHandler(handler);
    }

    @Override
    public Date getValue() {
        return dateBox.getValue();
    }

    @Override
    public void setValue(Date value) {
        setValue(value, true);
    }

    @Override
    public void setValue(Date value, boolean fireEvents) {
        dateBox.setValue(value, fireEvents);
    }

}
