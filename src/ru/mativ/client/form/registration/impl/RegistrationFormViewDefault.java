package ru.mativ.client.form.registration.impl;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.form.registration.RegistrationFormModel;
import ru.mativ.client.form.registration.RegistrationFormPresenter;
import ru.mativ.client.form.registration.RegistrationFormView;

public class RegistrationFormViewDefault extends Composite implements RegistrationFormView {

    private RegistrationFormPresenter presenter;

    private TextBox login;
    private TextBox name;
    private TextBox mail;
    private PasswordTextBox passFirst;
    private PasswordTextBox passSecond;
    private Label messageLabel;
    private Button sendBtn;

    public RegistrationFormViewDefault() {
        this(new RegistrationFormPresenterDefault());
    }

    public RegistrationFormViewDefault(RegistrationFormPresenter presenter) {
        this.presenter = presenter;
        presenter.addView(this);
        initWidget(buildGui());
    }

    private Widget buildGui() {
        login = new TextBox();
        name = new TextBox();
        mail = new TextBox();
        passFirst = new PasswordTextBox();
        passSecond = new PasswordTextBox();
        messageLabel = new Label();
        sendBtn = new Button("Registration");

        Label loginLabel = new Label("Login:");
        Label nameLabel = new Label("Name:");
        Label mailLabel = new Label("eMail:");
        Label passFirstLabel = new Label("Password:");
        Label passSecondLabel = new Label("Repeat pass:");

        sendBtn.setEnabled(false);
        sendBtn.addClickHandler(getSendBtnHandler());
        passFirst.addChangeHandler(getChangeHandler());
        passFirst.addKeyUpHandler(getKeyUpHandler());
        passSecond.addChangeHandler(getChangeHandler());
        passSecond.addKeyUpHandler(getKeyUpHandler());

        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(messageLabel);

        mainPanel.add(loginLabel);
        mainPanel.add(login);

        mainPanel.add(nameLabel);
        mainPanel.add(name);

        mainPanel.add(mailLabel);
        mainPanel.add(mail);

        mainPanel.add(passFirstLabel);
        mainPanel.add(passFirst);

        mainPanel.add(passSecondLabel);
        mainPanel.add(passSecond);

        mainPanel.add(sendBtn);

        return mainPanel;
    }

    private ClickHandler getSendBtnHandler() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RegistrationFormModel model = new RegistrationFormModelDefault(
                        login.getValue(),
                        name.getValue(),
                        mail.getValue(),
                        passFirst.getValue());

                presenter.onRegistrBtnClicked(model);
            }
        };
    }

    private boolean isValidData() {
        String p1 = passFirst.getValue();
        String p2 = passSecond.getValue();

        if (p1.isEmpty()
                || p2.isEmpty()
                || name.getValue().isEmpty()
                || login.getValue().isEmpty()
                || mail.getValue().isEmpty()) {
            return false;
        }

        return p1.equals(p2);
    }

    private ChangeHandler getChangeHandler() {
        return new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                sendBtn.setEnabled(isValidData());
            }
        };
    }

    private KeyUpHandler getKeyUpHandler() {
        return new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                sendBtn.setEnabled(isValidData());
            }
        };
    }

    @Override
    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}
