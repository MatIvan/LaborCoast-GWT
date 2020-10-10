package ru.mativ.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.RootPanel;

import ru.mativ.client.event.navigation.NavigationEvent;
import ru.mativ.client.event.navigation.NavigationTarget;
import ru.mativ.client.form.content.ContentForm;
import ru.mativ.client.form.footer.FooterForm;
import ru.mativ.client.form.header.HeaderForm;
import ru.mativ.client.service.GreetingService;
import ru.mativ.client.service.GreetingServiceAsync;
import ru.mativ.client.service.LoginService;
import ru.mativ.client.service.LoginServiceAsync;
import ru.mativ.client.service.RequestService;

public class LaborCoast implements EntryPoint {

    private static final String HEADER = "header";
    private static final String CONTENT = "content";
    private static final String FOOTER = "footer";

    private static final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
    private static final LoginServiceAsync loginService = GWT.create(LoginService.class);
    private static final RequestService requestService = GWT.create(RequestService.class);
    private static final EventBus globalBus = GWT.create(SimpleEventBus.class);

    static public GreetingServiceAsync getGreetingService() {
        return greetingService;
    }

    static public LoginServiceAsync getLoginService() {
        return loginService;
    }

    static public RequestService getRequestService() {
        return requestService;
    }

    static public EventBus getEventBus() {
        return globalBus;
    }

    public void onModuleLoad() {

        requestService.setOnUnauthorizedCommand(new Command() {
            @Override
            public void execute() {
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.LOGIN));
            }
        });

        RootPanel.get(HEADER).add(new HeaderForm());
        RootPanel.get(CONTENT).add(new ContentForm());
        RootPanel.get(FOOTER).add(new FooterForm());
    }

}
