package ru.mativ.client.form.report.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.form.report.ReportModel;
import ru.mativ.client.form.report.ReportPresenter;
import ru.mativ.client.form.report.ReportView;
import ru.mativ.client.form.report.ReportView.ReportViewHandler;
import ru.mativ.client.service.proxy.NoteServiceProxy;
import ru.mativ.client.widgets.mvp.presenter.AbstractPresenter;
import ru.mativ.shared.bean.ReportRowBean;

public class ReportPresenterImpl extends AbstractPresenter<ReportView> implements ReportPresenter {
    private static final Logger Log = Logger.getLogger(ReportPresenterImpl.class.getName());
    private static final NoteServiceProxy noteService = LaborCoast.getNoteServiceProxy();

    public ReportPresenterImpl(ReportView form) {
        super(form);
    }

    @Override
    public void go(HasWidgets container) {
        container.add(form.asWidget());
    }

    @Override
    protected void bind() {
        form.setReportViewHandler(new ReportViewHandler() {
            @Override
            public void onDatePick(Date date) {
                load(date);
            }
        });
    }

    @Override
    public void load(final Date date) {
        noteService.getReportRowByMonth(date, new AsyncCallback<List<ReportRowBean>>() {

            @Override
            public void onSuccess(List<ReportRowBean> result) {
                ReportModel model = ReportModel.make(date, result);
                form.setValue(model);
            }

            @Override
            public void onFailure(Throwable caught) {
                final String message = "Can not load notes by day: " + date;
                Log.log(Level.SEVERE, message, caught);
                form.setErrorMessage(message);
            }
        });
    }

}
