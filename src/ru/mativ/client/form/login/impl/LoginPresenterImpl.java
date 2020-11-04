package ru.mativ.client.form.login.impl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.event.navigation.NavigationEvent;
import ru.mativ.client.event.navigation.NavigationTarget;
import ru.mativ.client.form.login.LoginForm;
import ru.mativ.client.form.login.LoginPresenter;
import ru.mativ.client.service.proxy.LoginServiceProxy;
import ru.mativ.shared.bean.UserSessionBean;

public class LoginPresenterImpl implements LoginPresenter {
    private static final LoginServiceProxy loginService = LaborCoast.getLoginServiceProxy();
    private static final EventBus globalBus = LaborCoast.getEventBus();

    private LoginForm loginForm;

    public LoginPresenterImpl(LoginForm loginForm) {
        this.loginForm = loginForm;
        bind();
    }

    private void bind() {
        loginForm.addSendBtnClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                LoginViewModel loginViewModel = loginForm.getValue();
                loginService.makeToken(
                        loginViewModel.getLogin(),
                        loginViewModel.getPass(),
                        getLoginCallBack());
            }
        });

        loginForm.addRegistrationBtnClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.REGISTRATION));
            }
        });
    }

    @Override
    public void setDefaultData(String login, String pass) {
        loginForm.setMessage("Enter your login and password.");
        loginForm.setValue(new LoginViewModel(login, pass));
    }

    private AsyncCallback<UserSessionBean> getLoginCallBack() {
        return new AsyncCallback<UserSessionBean>() {

            @Override
            public void onFailure(Throwable caught) {
                loginService.setUserSession(null);
                loginForm.setMessage(caught.getMessage());
            }

            @Override
            public void onSuccess(UserSessionBean result) {
                loginService.setUserSession(result);
                String resultMessageText = (result == null ? "Failure: Empty session." : "Success: " + loginService.getUser());
                loginForm.setMessage("Logging is " + resultMessageText);
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.HOME));
            }
        };
    }
}
