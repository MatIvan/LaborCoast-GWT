package ru.mativ.client.form.registration.impl;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import ru.mativ.client.form.registration.RegistrationForm;
import ru.mativ.client.widgets.mvp.view.VerticalView;

public class RegistrationFormImpl extends VerticalView<RegistrationViewModel> implements RegistrationForm {

    private RegistrationView registrationView;
    private Label messageLabel;
    private Button sendBtn;

    public RegistrationFormImpl() {
        super();
    }

    @Override
    protected void init() {
        messageLabel = new Label();
        sendBtn = new Button("Registration");
        sendBtn.setEnabled(false);

        registrationView = new RegistrationView();
        registrationView.addValueChangeHandler(new ValueChangeHandler<RegistrationViewModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<RegistrationViewModel> event) {
                RegistrationViewModel model = event.getValue();
                sendBtn.setEnabled(model.isValid());
                fireValueChangeEvent();
            }
        });
    }

    @Override
    protected void build() {
        addField("Registration form:", registrationView);
        addField(null, messageLabel);

        HorizontalPanel btnPanel = new HorizontalPanel();
        //btnPanel.setSpacing(10);
        btnPanel.add(sendBtn);

        addField(null, btnPanel);
    }

    @Override
    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    @Override
    public RegistrationViewModel getValue() {
        return registrationView.getValue();
    }

    @Override
    public void setValue(RegistrationViewModel value, boolean fireEvents) {
        sendBtn.setEnabled(value.isValid());
        registrationView.setValue(value, fireEvents);
    }

    @Override
    public void addSendBtnClickHandler(ClickHandler handler) {
        sendBtn.addClickHandler(handler);
    }
}
