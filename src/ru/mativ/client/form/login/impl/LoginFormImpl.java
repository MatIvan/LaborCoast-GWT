package ru.mativ.client.form.login.impl;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import ru.mativ.client.form.login.LoginForm;
import ru.mativ.client.widgets.mvp.view.VerticalView;

public class LoginFormImpl extends VerticalView<LoginViewModel> implements LoginForm {

    private Label messageLabel;
    private LoginView loginView;
    private Button sendBtn;
    private Button registrBtn;

    public LoginFormImpl() {
        super();
    }

    @Override
    public LoginViewModel getValue() {
        return loginView.getValue();
    }

    @Override
    public void setValue(LoginViewModel value, boolean fireEvents) {
        loginView.setValue(value);
    }

    @Override
    protected void init() {
        messageLabel = new Label();
        loginView = new LoginView();
        sendBtn = new Button("Sing in");
        registrBtn = new Button("Registration");
    }

    @Override
    protected void build() {
        addField("Login form:", loginView);
        addField(null, messageLabel);

        HorizontalPanel btnPanel = new HorizontalPanel();
        btnPanel.setSpacing(10);
        btnPanel.add(sendBtn);
        btnPanel.add(registrBtn);

        addField(null, btnPanel);
    }

    @Override
    public void addSendBtnClickHandler(ClickHandler handler) {
        sendBtn.addClickHandler(handler);
    }

    @Override
    public void addRegistrationBtnClickHandler(ClickHandler handler) {
        registrBtn.addClickHandler(handler);
    }

    @Override
    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}
