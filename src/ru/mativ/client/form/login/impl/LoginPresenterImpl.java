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
import ru.mativ.client.widgets.mvp.presenter.AbstractPresenter;
import ru.mativ.shared.bean.UserSessionBean;

public class LoginPresenterImpl extends AbstractPresenter<LoginForm> implements LoginPresenter {
    private static final LoginServiceProxy loginService = LaborCoast.getLoginServiceProxy();
    private static final EventBus globalBus = LaborCoast.getEventBus();

    public LoginPresenterImpl(LoginForm loginForm) {
        super(loginForm);
    }

    @Override
    protected void bind() {
        form.addSendBtnClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                LoginViewModel loginViewModel = form.getValue();
                loginService.makeToken(
                        loginViewModel.getLogin(),
                        loginViewModel.getPass(),
                        getLoginCallBack());
            }
        });

        form.addRegistrationBtnClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.REGISTRATION));
            }
        });
    }

    @Override
    public void setDefaultData(String login, String pass) {
        form.setMessage("Enter your login and password.");
        form.setValue(new LoginViewModel(login, pass));
    }

    private AsyncCallback<UserSessionBean> getLoginCallBack() {
        return new AsyncCallback<UserSessionBean>() {

            @Override
            public void onFailure(Throwable caught) {
                loginService.setUserSession(null);
                form.setMessage(caught.getMessage());
            }

            @Override
            public void onSuccess(UserSessionBean result) {
                loginService.setUserSession(result);
                String resultMessageText = (result == null ? "Failure: Empty session." : "Success: " + loginService.getUser());
                form.setMessage("Logging is " + resultMessageText);
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.HOME));
            }
        };
    }
}
