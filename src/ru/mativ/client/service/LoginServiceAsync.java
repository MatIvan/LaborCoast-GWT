package ru.mativ.client.service;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.shared.bean.UserBean;
import ru.mativ.shared.bean.UserSessionBean;

public interface LoginServiceAsync {

    RequestBuilder makeToken(String login, String pass, AsyncCallback<UserSessionBean> callback);

    RequestBuilder newUser(UserBean user, String pass, AsyncCallback<Void> callback);
}
