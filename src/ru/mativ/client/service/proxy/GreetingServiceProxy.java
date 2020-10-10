package ru.mativ.client.service.proxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.service.GreetingService;
import ru.mativ.client.service.GreetingServiceAsync;
import ru.mativ.client.service.RequestService;

public class GreetingServiceProxy implements GreetingServiceAsync {
    private static final RequestService requestService = LaborCoast.getRequestService();
    private static final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    @Override
    public RequestBuilder greetServer(String input, AsyncCallback<String> callback) {
        requestService.send(greetingService.greetServer(input, callback));
        return null;
    }

}
