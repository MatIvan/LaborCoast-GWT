package ru.mativ.client.form.login;

import com.google.gwt.event.dom.client.ClickHandler;

import ru.mativ.client.form.login.impl.LoginViewModel;
import ru.mativ.client.widgets.mvp.view.ViewInterface;

public interface LoginForm extends ViewInterface<LoginViewModel> {

    public void setMessage(String message);

    public void addSendBtnClickHandler(ClickHandler handler);

    public void addRegistrationBtnClickHandler(ClickHandler handler);
}
