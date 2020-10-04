package ru.mativ.client.form.registration;

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

public class RegistrationFormView extends Composite {

    private RegistrationFormViewHandler handler;

    private TextBox login;
    private TextBox name;
    private TextBox mail;
    private PasswordTextBox passFirst;
    private PasswordTextBox passSecond;
    private Label messageLabel;
    private Button sendBtn;

    public RegistrationFormView() {
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
                if (handler != null) {
                    RegistrationFormModel model = new RegistrationFormModel(
                            login.getValue(),
                            name.getValue(),
                            mail.getValue(),
                            passFirst.getValue());

                    handler.onRegistrBtnClicked(model);
                }
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

    public void setHandler(RegistrationFormViewHandler handler) {
        this.handler = handler;
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}
