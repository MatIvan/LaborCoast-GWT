package ru.mativ.client.form.registration;

import com.google.gwt.event.dom.client.ClickHandler;

import ru.mativ.client.form.registration.impl.RegistrationViewModel;
import ru.mativ.client.widgets.mvp.view.ViewInterface;

public interface RegistrationForm extends ViewInterface<RegistrationViewModel> {

    public void setMessage(String message);

    public void addSendBtnClickHandler(ClickHandler handler);
}
