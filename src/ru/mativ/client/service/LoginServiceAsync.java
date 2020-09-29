package ru.mativ.client.service;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

    RequestBuilder makeToken(String login, String pass, AsyncCallback<String> callback);

}
