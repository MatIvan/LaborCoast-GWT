package ru.mativ.client.form.report.impl;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasWidgets;

import ru.mativ.client.form.report.ReportModel;
import ru.mativ.client.form.report.ReportView;
import ru.mativ.client.widgets.mvp.presenter.AbstractPresenter;

public class ReportPresenterImpl extends AbstractPresenter<ReportView> implements ru.mativ.client.form.report.ReportPresenter {

    public ReportPresenterImpl(ReportView form) {
        super(form);
    }

    @Override
    public void go(HasWidgets container) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void bind() {
        form.addValueChangeHandler(new ValueChangeHandler<ReportModel>() {

            @Override
            public void onValueChange(ValueChangeEvent<ReportModel> event) {
                // TODO Auto-generated method stub

            }
        });
    }

}
