package ru.mativ.client.form.report;

import java.util.Date;

import ru.mativ.client.widgets.mvp.view.ViewInterface;

public interface ReportView extends ViewInterface<ReportModel> {

    public interface ReportViewHandler {
        void onDatePick(Date date);
    }

    void setReportViewHandler(ReportViewHandler handler);

    void setErrorMessage(String message);
}
