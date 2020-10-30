package ru.mativ.client.form.login.impl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.event.navigation.NavigationTarget;
import ru.mativ.client.fabrica.NavigateButtonsFabrica;
import ru.mativ.client.form.login.LoginFormModel;
import ru.mativ.client.form.login.LoginFormPresenter;
import ru.mativ.client.form.login.LoginFormView;

public class LoginFormViewDefault extends Composite implements LoginFormView {

    private LoginFormPresenter presenter;

    private TextBox login;
    private PasswordTextBox pass;
    private Label messageLabel;

    public LoginFormViewDefault() {
        this(new LoginFormPresenterDefault());
    }

    public LoginFormViewDefault(LoginFormPresenter presenter) {
        this.presenter = presenter;
        presenter.addView(this);
        initWidget(buildGui());
    }

    private Widget buildGui() {
        login = new TextBox();
        pass = new PasswordTextBox();
        messageLabel = new Label();

        Label loginLabel = new Label("Login:");
        Label passLabel = new Label("Password:");

        //Develop only
        login.setValue("test");
        pass.setValue("123");

        Button sendBtn = new Button("Login");
        sendBtn.addClickHandler(getSendBtnHandler());

        Button registrBtn = NavigateButtonsFabrica.createButton(NavigationTarget.REGISTRATION);

        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(messageLabel);
        mainPanel.add(loginLabel);
        mainPanel.add(login);
        mainPanel.add(passLabel);
        mainPanel.add(pass);
        mainPanel.add(sendBtn);
        mainPanel.add(registrBtn);

        return mainPanel;
    }

    private ClickHandler getSendBtnHandler() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                LoginFormModel model = new LoginFormModelDefault(login.getValue(), pass.getValue());
                presenter.onLoginBtnClicked(model);
            }
        };
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}
