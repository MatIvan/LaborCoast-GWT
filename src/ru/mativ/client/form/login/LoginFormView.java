package ru.mativ.client.form.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginFormView extends Composite {

    private LoginFormViewHandler handler;
    private Command onRegistrationClicked;

    private TextBox login;
    private PasswordTextBox pass;
    private Label messageLabel;

    public LoginFormView() {
        initWidget(buildGui());
    }

    private Widget buildGui() {
        login = new TextBox();
        pass = new PasswordTextBox();
        messageLabel = new Label();

        Label loginLabel = new Label("Login:");
        Label passLabel = new Label("Password:");

        Button sendBtn = new Button("Login");
        sendBtn.addClickHandler(getSendBtnHandler());

        Button registrBtn = new Button("Registration");
        registrBtn.addClickHandler(getRegistrBtnHandler());

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
                if (handler != null) {
                    LoginFormModel model = new LoginFormModel(login.getValue(), pass.getValue());
                    handler.onLoginBtnClicked(model);
                }
            }
        };
    }

    private ClickHandler getRegistrBtnHandler() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (onRegistrationClicked != null) {
                    onRegistrationClicked.execute();
                }
            }
        };
    }

    public void setHandler(LoginFormViewHandler handler) {
        this.handler = handler;
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    public void setOnRegistrationClicked(Command onRegistrationClicked) {
        this.onRegistrationClicked = onRegistrationClicked;
    }
}
