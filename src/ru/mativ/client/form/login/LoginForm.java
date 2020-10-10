package ru.mativ.client.form.login;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.service.proxy.LoginServiceProxy;
import ru.mativ.shared.UserSessionDto;

public class LoginForm {
    private static final LoginServiceProxy loginService = LaborCoast.getLoginServiceProxy();

    private LoginFormView view;
    private Command onLoginSuccess;

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
                if (onLoginSuccess != null) {
                    onLoginSuccess.execute();
                }
            }
        };
    }

    public Widget asWidget() {
        return view.asWidget();
    }

    public void setOnLoginSuccessCommand(Command onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }

    public void setOnRegistrationClicked(Command onRegistrationClicked) {
        view.setOnRegistrationClicked(onRegistrationClicked);
    }
}
