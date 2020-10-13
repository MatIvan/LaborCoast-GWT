package ru.mativ.client.form.login;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.event.navigation.NavigationEvent;
import ru.mativ.client.event.navigation.NavigationTarget;
import ru.mativ.client.service.proxy.LoginServiceProxy;
import ru.mativ.shared.UserSessionDto;

public class LoginForm {
    private static final LoginServiceProxy loginService = LaborCoast.getLoginServiceProxy();
    private static final EventBus globalBus = LaborCoast.getEventBus();

    private LoginFormView view;

    public LoginForm(LoginFormView view) {
        this.view = view;
        view.setHandler(getLoginFormViewHandler());
    }

    private LoginFormViewHandler getLoginFormViewHandler() {
        return new LoginFormViewHandler() {
            @Override
            public void onLoginBtnClicked(LoginFormModel model) {
                loginService.makeToken(
                        model.getLogin(),
                        model.getPassword(),
                        getLoginCallBack());
            }
        };
    }

    private AsyncCallback<UserSessionDto> getLoginCallBack() {
        return new AsyncCallback<UserSessionDto>() {

            @Override
            public void onFailure(Throwable caught) {
                loginService.setUserSession(null);//TODO: it work for other class
                view.setMessage("Logging is Failure: " + caught.getMessage());
            }

            @Override
            public void onSuccess(UserSessionDto result) {
                loginService.setUserSession(result);//TODO: it work for other class
                String resultMessageText = (result == null ? "Failure: Empty session." : "Success: " + loginService.getUser());//TODO: it work for other class
                view.setMessage("Logging is " + resultMessageText);
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.HOME));
            }
        };
    }

    public Widget asWidget() {
        return view.asWidget();
    }
}
