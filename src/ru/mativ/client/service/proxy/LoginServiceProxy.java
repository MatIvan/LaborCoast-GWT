package ru.mativ.client.service.proxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.service.LoginService;
import ru.mativ.client.service.LoginServiceAsync;
import ru.mativ.client.service.RequestService;
import ru.mativ.shared.bean.UserBean;
import ru.mativ.shared.bean.UserSessionBean;

public class LoginServiceProxy implements LoginServiceAsync {
    private static final RequestService requestService = LaborCoast.getRequestService();
    private static final LoginServiceAsync loginService = GWT.create(LoginService.class);

    public void setUserSession(UserSessionBean session) {
        requestService.setUserSession(session);
    }

    public UserBean getUser() {
        return requestService.getUser();
    }

    public boolean isRegistered() {
        return getUser() != null;
    }

    @Override
    public RequestBuilder makeToken(String login, String pass, AsyncCallback<UserSessionBean> callback) {
        requestService.send(loginService.makeToken(login, pass, callback));
        return null;
    }

    @Override
    public RequestBuilder newUser(UserBean user, String pass, AsyncCallback<Void> callback) {
        requestService.send(loginService.newUser(user, pass, callback));
        return null;
    }

}
