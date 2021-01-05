package ru.mativ.client.form.report;

import java.util.Date;

import ru.mativ.client.widgets.mvp.presenter.PresenterInterface;

public interface ReportPresenter extends PresenterInterface {

    public void load(Date date);
}
