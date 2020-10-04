package ru.mativ.client.form.login;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.service.LoginServiceAsync;
import ru.mativ.client.service.RequestService;
import ru.mativ.shared.UserSessionDto;

public class LoginForm {
    private static final LoginServiceAsync loginService = LaborCoast.getLoginService();
    private static final RequestService requestService = RequestService.getInstance();

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
                requestService.send(loginService.makeToken(
                        model.getLogin(),
                        model.getPassword(),
                        getLoginCallBack()));
            }
        };
    }

    private AsyncCallback<UserSessionDto> getLoginCallBack() {
        return new AsyncCallback<UserSessionDto>() {

            @Override
            public void onFailure(Throwable caught) {
                requestService.setUserSession(null);
                view.setMessage("Logging is Failure: " + caught.getMessage());
            }

            @Override
            public void onSuccess(UserSessionDto result) {
                requestService.setUserSession(result);
                String resultMessageText = (result == null ? "Failure: Empty session." : "Success: " + requestService.getUser());
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
}
