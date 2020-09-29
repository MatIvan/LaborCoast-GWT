package ru.mativ.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.RootPanel;

import ru.mativ.client.form.MainForm;
import ru.mativ.client.form.login.LoginForm;
import ru.mativ.client.service.GreetingService;
import ru.mativ.client.service.GreetingServiceAsync;
import ru.mativ.client.service.LoginService;
import ru.mativ.client.service.LoginServiceAsync;
import ru.mativ.client.service.RequestService;

public class LaborCoast implements EntryPoint {

    private static final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
    private static final LoginServiceAsync loginService = GWT.create(LoginService.class);
    private static final RequestService requestService = RequestService.getInstance();

    static public GreetingServiceAsync getGreetingService() {
        return greetingService;
    }

    static public LoginServiceAsync getLoginService() {
        return loginService;
    }

    public void onModuleLoad() {

        requestService.setOnUnauthorizedCommand(new Command() {
            @Override
            public void execute() {
                showLoginForm();
            }
        });

        showLoginForm();
    }

    private void showLoginForm() {
        RootPanel.get().clear();
        RootPanel.get().add(new LoginForm(new Command() {

            @Override
            public void execute() {
                showMainForm();
            }
        }));
    }

    private void showMainForm() {
        RootPanel.get().clear();
        RootPanel.get().add(new MainForm());
    }

}
