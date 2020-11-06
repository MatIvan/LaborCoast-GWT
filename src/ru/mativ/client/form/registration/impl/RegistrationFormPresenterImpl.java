package ru.mativ.client.form.registration.impl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.event.navigation.NavigationEvent;
import ru.mativ.client.event.navigation.NavigationTarget;
import ru.mativ.client.form.registration.RegistrationForm;
import ru.mativ.client.form.registration.RegistrationFormPresenter;
import ru.mativ.client.service.proxy.LoginServiceProxy;
import ru.mativ.client.widgets.mvp.presenter.AbstractPresenter;
import ru.mativ.shared.bean.UserBean;

public class RegistrationFormPresenterImpl extends AbstractPresenter<RegistrationForm> implements RegistrationFormPresenter {
    private static final LoginServiceProxy loginService = LaborCoast.getLoginServiceProxy();
    private static final EventBus globalBus = LaborCoast.getEventBus();

    public RegistrationFormPresenterImpl(RegistrationForm registrationForm) {
        super(registrationForm);
    }

    @Override
    protected void bind() {
        form.addSendBtnClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                send();
            }
        });
    }

    private void send() {
        RegistrationViewModel model = form.getValue();
        if (!model.isValid()) {
            form.setMessage("Invalid data.");
            return;
        }

        loginService.newUser(
                makeUserBean(model),
                model.getPass(),
                getRegistrCallBack());
    }

    private UserBean makeUserBean(RegistrationViewModel model) {
        return new UserBean(
                model.getLogin(),
                model.getName(),
                model.getMail());
    }

    private AsyncCallback<Void> getRegistrCallBack() {
        return new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                form.setMessage(caught.getMessage());
            }

            @Override
            public void onSuccess(Void result) {
                form.setMessage("Logging is success.");
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.LOGIN));
            }
        };
    }
}
