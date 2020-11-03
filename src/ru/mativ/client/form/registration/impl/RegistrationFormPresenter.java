package ru.mativ.client.form.registration.impl;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.event.navigation.NavigationEvent;
import ru.mativ.client.event.navigation.NavigationTarget;
import ru.mativ.client.service.proxy.LoginServiceProxy;
import ru.mativ.client.widgets.mvp.AbstractPresenter;
import ru.mativ.shared.bean.UserBean;

public class RegistrationFormPresenter extends AbstractPresenter<RegistrationFormModel> {
    private static final LoginServiceProxy loginService = LaborCoast.getLoginServiceProxy();
    private static final EventBus globalBus = LaborCoast.getEventBus();

    public RegistrationFormPresenter() {
        super();
    }

    @Override
    public void onDataChanged(RegistrationFormModel model) {
        loginService.newUser(
                makeUserBean(model),
                model.getPass(),
                getRegistrCallBack());
    }

    @Override
    public void loadData(Object context) {
        // do nothing
    }

    @Override
    protected RegistrationFormModel makeModel() {
        // do nothing
        return null;
    }

    private AsyncCallback<Void> getRegistrCallBack() {
        return new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                showErrorAllView("Registration is Failure: " + caught.getMessage());
            }

            @Override
            public void onSuccess(Void result) {
                showErrorAllView("Logging is success.");
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.LOGIN));
            }
        };
    }

    private UserBean makeUserBean(RegistrationFormModel model) {
        return new UserBean(
                model.getLogin(),
                model.getName(),
                model.getMail());
    }
}
