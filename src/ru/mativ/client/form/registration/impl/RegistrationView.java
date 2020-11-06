package ru.mativ.client.form.registration.impl;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

import ru.mativ.client.widgets.mvp.view.VerticalView;

public class RegistrationView extends VerticalView<RegistrationViewModel> {

    private TextBox loginBox;
    private TextBox nameBox;
    private TextBox mailBox;
    private PasswordTextBox passFirst;
    private PasswordTextBox passSecond;

    public RegistrationView() {
        super();
    }

    @Override
    public RegistrationViewModel getValue() {
        return new RegistrationViewModel(
                loginBox.getValue(),
                nameBox.getValue(),
                mailBox.getValue(),
                passFirst.getValue(),
                passSecond.getValue());
    }

    @Override
    public void setValue(RegistrationViewModel value, boolean fireEvents) {
        loginBox.setValue(value.getLogin());
        nameBox.setValue(value.getName());
        mailBox.setValue(value.getMail());
        passFirst.setValue(value.getPass());
        passSecond.setValue(value.getPassConfirm());
    }

    @Override
    protected void init() {
        loginBox = new TextBox();
        nameBox = new TextBox();
        mailBox = new TextBox();
        passFirst = new PasswordTextBox();
        passSecond = new PasswordTextBox();

        passFirst.addChangeHandler(getChangeHandler());
        passFirst.addKeyUpHandler(getKeyUpHandler());
        passSecond.addChangeHandler(getChangeHandler());
        passSecond.addKeyUpHandler(getKeyUpHandler());
    }

    private ChangeHandler getChangeHandler() {
        return new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                fireValueChangeEvent();
            }
        };
    }

    private KeyUpHandler getKeyUpHandler() {
        return new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                fireValueChangeEvent();
            }
        };
    }

    @Override
    protected void build() {
        addField("Login:", loginBox);
        addField("Name:", nameBox);
        addField("eMail:", mailBox);
        addField("Password:", passFirst);
        addField("Repeat pass:", passSecond);
    }
}
