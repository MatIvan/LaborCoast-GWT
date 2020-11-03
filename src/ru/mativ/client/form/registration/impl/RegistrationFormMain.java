package ru.mativ.client.form.registration.impl;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.form.registration.RegistrationForm;
import ru.mativ.client.widgets.VLabeledPanel;
import ru.mativ.client.widgets.mvp.AbstractView;

public class RegistrationFormMain extends AbstractView<RegistrationFormModel> implements RegistrationForm {

    private VerticalPanel mainPanel;
    private TextBox login;
    private TextBox name;
    private TextBox mail;
    private PasswordTextBox passFirst;
    private PasswordTextBox passSecond;
    private Label messageLabel;
    private Button sendBtn;

    public RegistrationFormMain() {
        super(new RegistrationFormPresenter());
    }

    @Override
    protected Widget getMainPanel() {
        return mainPanel;
    }

    @Override
    protected void init() {
        mainPanel = new VerticalPanel();
        mainPanel.setSpacing(10);

        login = new TextBox();
        name = new TextBox();
        mail = new TextBox();
        passFirst = new PasswordTextBox();
        passSecond = new PasswordTextBox();
        messageLabel = new Label();
        sendBtn = new Button("Registration");

        sendBtn.setEnabled(false);
        sendBtn.addClickHandler(getSendBtnHandler());
        passFirst.addChangeHandler(getChangeHandler());
        passFirst.addKeyUpHandler(getKeyUpHandler());
        passSecond.addChangeHandler(getChangeHandler());
        passSecond.addKeyUpHandler(getKeyUpHandler());
    }

    @Override
    protected void build() {
        mainPanel.add(messageLabel);
        mainPanel.add(new VLabeledPanel("Login:", login));
        mainPanel.add(new VLabeledPanel("Name:", name));
        mainPanel.add(new VLabeledPanel("eMail:", mail));
        mainPanel.add(new VLabeledPanel("Password:", passFirst));
        mainPanel.add(new VLabeledPanel("Repeat pass:", passSecond));
        mainPanel.add(sendBtn);
    }

    @Override
    protected RegistrationFormModel makeModel() {
        return new RegistrationFormModel(
                login.getValue(),
                name.getValue(),
                mail.getValue(),
                passFirst.getValue());
    }

    @Override
    public void fill(RegistrationFormModel model) {
        // do nothing
    }

    @Override
    public void showError(String errorText) {
        messageLabel.setText(errorText);
    }

    private ClickHandler getSendBtnHandler() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                notifyAboutModelChanged();
            }
        };
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
}
