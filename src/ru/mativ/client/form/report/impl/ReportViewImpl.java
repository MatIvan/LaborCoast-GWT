package ru.mativ.client.form.report.impl;

import java.util.Date;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Label;

import ru.mativ.client.form.report.ReportModel;
import ru.mativ.client.form.report.ReportView;
import ru.mativ.client.widgets.AdvancedMonthPicker;
import ru.mativ.client.widgets.mvp.view.VerticalView;

public class ReportViewImpl extends VerticalView<ReportModel> implements ReportView {

    private ReportViewHandler handler;

    private AdvancedMonthPicker advancedMonthPicker;
    private TicketsTable ticketsTable;

    public ReportViewImpl() {
        super();
    }

    @Override
    public ReportModel getValue() {
        // do nothing
        return null;
    }

    @Override
    public void setValue(ReportModel value, boolean fireEvents) {
        if (value == null) {
            setErrorMessage("Empty data.");
            return;
        }
        advancedMonthPicker.setValue(value.getDate());
        ticketsTable.fill(value.getTickets());
    }

    @Override
    protected void init() {
        advancedMonthPicker = new AdvancedMonthPicker();
        advancedMonthPicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                if (handler != null) {
                    handler.onDatePick(event.getValue());
                }
            }
        });

        ticketsTable = new TicketsTable();
    }

    @Override
    protected void build() {
        addField("Date:", advancedMonthPicker);
        addField("Tickets:", ticketsTable);

        //HorizontalPanel buttonsPanel = new HorizontalPanel();
        //buttonsPanel.add(addNoteButton);
        //addField(buttonsPanel);
    }

    @Override
    public void setErrorMessage(String message) {
        clear();
        addField("Error:", new Label(message));
    }

    @Override
    public void setReportViewHandler(ReportViewHandler handler) {
        this.handler = handler;
    }
}
