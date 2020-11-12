package ru.mativ.client.form.notes.day;

import java.util.Date;

import ru.mativ.client.widgets.mvp.presenter.PresenterInterface;

public interface NoteDayFormPresenter extends PresenterInterface {
    void update(Date date);
}
