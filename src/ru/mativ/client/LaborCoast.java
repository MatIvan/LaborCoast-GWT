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
import ru.mativ.client.service.RequestService;
import ru.mativ.client.service.proxy.GreetingServiceProxy;
import ru.mativ.client.service.proxy.LoginServiceProxy;
import ru.mativ.client.service.proxy.NoteServiceProxy;

public class LaborCoast implements EntryPoint {

    private static final String HEADER = "header";
    private static final String CONTENT = "content";
    private static final String FOOTER = "footer";

    private static final EventBus globalBus = GWT.create(SimpleEventBus.class);

    private static final RequestService requestService = GWT.create(RequestService.class);
    private static final GreetingServiceProxy greetingServiceProxy = GWT.create(GreetingServiceProxy.class);
    private static final LoginServiceProxy loginServiceProxy = GWT.create(LoginServiceProxy.class);
    private static final NoteServiceProxy noteServiceProxy = GWT.create(NoteServiceProxy.class);

    static public GreetingServiceProxy getGreetingServiceProxy() {
        return greetingServiceProxy;
    }

    static public LoginServiceProxy getLoginServiceProxy() {
        return loginServiceProxy;
    }

    static public NoteServiceProxy getNoteServiceProxy() {
        return noteServiceProxy;
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
