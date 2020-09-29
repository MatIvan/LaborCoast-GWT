package ru.mativ.client.service;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Command;

import ru.mativ.shared.HeaderName;
import ru.mativ.shared.NullCommand;

public class RequestService {
    private final static RequestService instance = new RequestService();

    private String token;
    private Command onUnauthorized = new NullCommand();

    private RequestService() {
    }

    public static RequestService getInstance() {
        return instance;
    }

    public void setOnUnauthorizedCommand(Command onUnauthorized) {
        this.onUnauthorized = onUnauthorized == null ? new NullCommand() : onUnauthorized;
    }

    public void setToken(String newToken) {
        this.token = newToken;
    }

    public void send(final RequestBuilder rb) {

        if (token != null) {
            rb.setHeader(HeaderName.TOKEN, token);
        }

        final RequestCallback callback = rb.getCallback();
        rb.setCallback(new RequestCallback() {

            @Override
            public void onResponseReceived(Request request, Response response) {
                if (response.getStatusCode() == Response.SC_UNAUTHORIZED) {
                    onUnauthorized.execute();
                    return;
                }
                if (callback != null) {
                    callback.onResponseReceived(request, response);
                }
            }

            @Override
            public void onError(Request request, Throwable exception) {
                if (callback != null) {
                    callback.onError(request, exception);
                }
            }
        });

        try {
            rb.send();
        } catch (RequestException e) {
            // do nothing
        }
    }

}
