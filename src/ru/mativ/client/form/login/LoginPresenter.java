package ru.mativ.client.form.login;

import ru.mativ.client.widgets.mvp.presenter.PresenterInterface;

public interface LoginPresenter extends PresenterInterface {

    public void setDefaultData(String login, String pass);
}
