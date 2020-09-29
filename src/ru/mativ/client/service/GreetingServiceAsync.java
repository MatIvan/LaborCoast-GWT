package ru.mativ.client.service;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GreetingServiceAsync {

    RequestBuilder greetServer(String input, AsyncCallback<String> callback);

}
