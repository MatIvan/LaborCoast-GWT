package ru.mativ.client.form.login.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.event.navigation.NavigationEvent;
import ru.mativ.client.event.navigation.NavigationTarget;
import ru.mativ.client.form.login.LoginFormModel;
import ru.mativ.client.form.login.LoginFormPresenter;
import ru.mativ.client.form.login.LoginFormView;
import ru.mativ.client.service.proxy.LoginServiceProxy;
import ru.mativ.shared.bean.UserSessionBean;

public class LoginFormPresenterDefault implements LoginFormPresenter {
    private static final LoginServiceProxy loginService = LaborCoast.getLoginServiceProxy();
    private static final EventBus globalBus = LaborCoast.getEventBus();

    private List<LoginFormView> viewList = new ArrayList<>();

    private AsyncCallback<UserSessionBean> getLoginCallBack() {
        return new AsyncCallback<UserSessionBean>() {

            @Override
            public void onFailure(Throwable caught) {
                loginService.setUserSession(null);
                setMessageForViews("Logging is Failure: " + caught.getMessage());
            }

            @Override
            public void onSuccess(UserSessionBean result) {
                loginService.setUserSession(result);
                String resultMessageText = (result == null ? "Failure: Empty session." : "Success: " + loginService.getUser());
                setMessageForViews("Logging is " + resultMessageText);
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.HOME));
            }
        };
    }

    private void setMessageForViews(String message) {
        for (LoginFormView view : viewList) {
            view.setMessage(message);
        }
    }

    @Override
    public void onLoginBtnClicked(LoginFormModel model) {
        loginService.makeToken(
                model.getLogin(),
                model.getPassword(),
                getLoginCallBack());
    }

    @Override
    public void addView(LoginFormView view) {
        viewList.add(view);
    }
}
