package ru.mativ.client.form.report.impl;

import java.util.Date;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Label;

import ru.mativ.client.form.report.ReportModel;
import ru.mativ.client.form.report.ReportView;
import ru.mativ.client.widgets.AdvancedMonthPicker;
import ru.mativ.client.widgets.HLabeledPanel;
import ru.mativ.client.widgets.mvp.view.VerticalView;

public class ReportViewImpl extends VerticalView<ReportModel> implements ReportView {

    private ReportViewHandler handler;

    private AdvancedMonthPicker advancedMonthPicker;
    private TicketsTable ticketsTable;

    private Label lMiting;
    private Label lVacation;
    private Label lSumm;

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

        lMiting.setText(String.valueOf(value.getMitingHourse()));
        lVacation.setText(String.valueOf(value.getVacationHourse()));
        lSumm.setText(String.valueOf(value.getSummHourse()));
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

        lMiting = new Label("0");
        lVacation = new Label("0");
        lSumm = new Label("0");
    }

    @Override
    protected void build() {
        addField("Date:", advancedMonthPicker);
        addField("Tickets:", ticketsTable);

        addField(new HLabeledPanel("Mitings: ", lMiting));
        addField(new HLabeledPanel("Vacation: ", lVacation));
        addField(new HLabeledPanel("All summ: ", lSumm));

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
