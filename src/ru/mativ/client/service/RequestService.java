package ru.mativ.client.service;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Command;

import ru.mativ.shared.HeaderName;
import ru.mativ.shared.NullCommand;
import ru.mativ.shared.bean.UserBean;
import ru.mativ.shared.bean.UserSessionBean;

public class RequestService {
    private UserSessionBean session;
    private Command onUnauthorized = new NullCommand();

    private RequestService() {
    }

    public void setOnUnauthorizedCommand(Command onUnauthorized) {
        this.onUnauthorized = onUnauthorized == null ? new NullCommand() : onUnauthorized;
    }

    public void setUserSession(UserSessionBean session) {
        this.session = session;
    }

    public UserBean getUser() {
        return (session == null ? null : session.getUserBean());
    }

    private String getToken() {
        return (session == null ? null : session.getToken());
    }

    public void send(final RequestBuilder rb) {

        String token = getToken();
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
