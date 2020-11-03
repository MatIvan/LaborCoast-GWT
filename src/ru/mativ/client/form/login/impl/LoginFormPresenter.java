package ru.mativ.client.form.login.impl;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.event.navigation.NavigationEvent;
import ru.mativ.client.event.navigation.NavigationTarget;
import ru.mativ.client.service.proxy.LoginServiceProxy;
import ru.mativ.client.widgets.mvp.AbstractPresenter;
import ru.mativ.shared.bean.UserSessionBean;

public class LoginFormPresenter extends AbstractPresenter<LoginFormModel> {
    private static final LoginServiceProxy loginService = LaborCoast.getLoginServiceProxy();
    private static final EventBus globalBus = LaborCoast.getEventBus();

    private String login = "";
    private String pass = "";

    public LoginFormPresenter() {
        super();
    }

    @Override
    public void onDataChanged(LoginFormModel model) {
        loginService.makeToken(
                model.getLogin(),
                model.getPassword(),
                getLoginCallBack());
    }

    @Override
    public void loadData(Object context) {
        login = "";
        pass = "";
        if (context instanceof LoginFormModel) {
            LoginFormModel model = (LoginFormModel) context;
            login = model.getLogin();
            pass = model.getPassword();
        }
        fillAllView();
    }

    @Override
    protected LoginFormModel makeModel() {
        return new LoginFormModel(login, pass);
    }

    private AsyncCallback<UserSessionBean> getLoginCallBack() {
        return new AsyncCallback<UserSessionBean>() {

            @Override
            public void onFailure(Throwable caught) {
                loginService.setUserSession(null);
                showErrorAllView("Logging is Failure: " + caught.getMessage());
            }

            @Override
            public void onSuccess(UserSessionBean result) {
                loginService.setUserSession(result);
                String resultMessageText = (result == null ? "Failure: Empty session." : "Success: " + loginService.getUser());
                showErrorAllView("Logging is " + resultMessageText);
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.HOME));
            }
        };
    }
}
