package ru.mativ.client.form.registration.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.event.navigation.NavigationEvent;
import ru.mativ.client.event.navigation.NavigationTarget;
import ru.mativ.client.form.registration.RegistrationFormModel;
import ru.mativ.client.form.registration.RegistrationFormPresenter;
import ru.mativ.client.form.registration.RegistrationFormView;
import ru.mativ.client.service.proxy.LoginServiceProxy;
import ru.mativ.shared.UserDto;

public class RegistrationFormPresenterDefault implements RegistrationFormPresenter {
    private static final LoginServiceProxy loginService = LaborCoast.getLoginServiceProxy();
    private static final EventBus globalBus = LaborCoast.getEventBus();

    private List<RegistrationFormView> viewList = new ArrayList<>();

    private AsyncCallback<Void> getRegistrCallBack() {
        return new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                setMessageForViews("Registration is Failure: " + caught.getMessage());
            }

            @Override
            public void onSuccess(Void result) {
                setMessageForViews("Logging is success.");
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.LOGIN));
            }
        };
    }

    private void setMessageForViews(String message) {
        for (RegistrationFormView view : viewList) {
            view.setMessage(message);
        }
    }

    private UserDto makeUserDto(RegistrationFormModel model) {
        UserDto user = new UserDto();
        user.setLogin(model.getLogin());
        user.setName(model.getName());
        user.setMail(model.getMail());
        return user;
    }

    @Override
    public void onRegistrBtnClicked(RegistrationFormModel model) {
        loginService.newUser(
                makeUserDto(model),
                model.getPass(),
                getRegistrCallBack());
    }

    @Override
    public void addView(RegistrationFormView view) {
        viewList.add(view);
    }

}
