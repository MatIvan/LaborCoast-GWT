package ru.mativ.client.form.login.impl;

import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

import ru.mativ.client.widgets.mvp.view.VerticalView;

public class LoginView extends VerticalView<LoginViewModel> {

    private TextBox loginBox;
    private PasswordTextBox passBox;

    public LoginView() {
        super();
    }

    @Override
    public LoginViewModel getValue() {
        return new LoginViewModel(loginBox.getValue(), passBox.getValue());
    }

    @Override
    public void setValue(LoginViewModel value, boolean fireEvents) {
        loginBox.setValue(value.getLogin());
        passBox.setValue(value.getPass());
    }

    @Override
    protected void init() {
        loginBox = new TextBox();
        passBox = new PasswordTextBox();
    }

    @Override
    protected void build() {
        addField("Login:", loginBox);
        addField("Password:", passBox);
    }
}
