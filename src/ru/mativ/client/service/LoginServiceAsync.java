package ru.mativ.client.service;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.shared.UserDto;
import ru.mativ.shared.UserSessionDto;

public interface LoginServiceAsync {

    RequestBuilder makeToken(String login, String pass, AsyncCallback<UserSessionDto> callback);

    RequestBuilder newUser(UserDto user, String pass, AsyncCallback<Void> callback);

}
