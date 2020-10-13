package ru.mativ.client.form.header;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.event.navigation.NavigationEvent;
import ru.mativ.client.event.navigation.NavigationEventHandler;
import ru.mativ.client.event.navigation.NavigationTarget;
import ru.mativ.client.fabrica.NavigateButtonsFabrica;
import ru.mativ.client.service.proxy.LoginServiceProxy;

public class HeaderForm extends Composite {
    private EventBus globalBus = LaborCoast.getEventBus();
    private static final LoginServiceProxy loginService = LaborCoast.getLoginServiceProxy();

    private HorizontalPanel panel;

    private Button btnHome;
    private Button btnLogin;
    private Button btnRegistration;
    private Button btnLogoff;

    public HeaderForm() {
        initGui();
        initHandlers();
        updateView();
        initWidget(panel);
    }

    private void initGui() {
        panel = new HorizontalPanel();
        btnHome = NavigateButtonsFabrica.createButton(NavigationTarget.HOME);
        btnLogin = NavigateButtonsFabrica.createButton(NavigationTarget.LOGIN);
        btnRegistration = NavigateButtonsFabrica.createButton(NavigationTarget.REGISTRATION);
        btnLogoff = NavigateButtonsFabrica.createButton(NavigationTarget.LOGOFF);
    }

    private void initHandlers() {
        globalBus.addHandler(NavigationEvent.TYPE, new NavigationEventHandler() {
            @Override
            public void toHome(NavigationEvent navigationEvent) {
                updateView();
            }

            @Override
            public void toLogin(NavigationEvent navigationEvent) {
                updateView();
            }

            @Override
            public void toRegistration(NavigationEvent navigationEvent) {
                updateView();
            }

            @Override
            public void toLogoff(NavigationEvent navigationEvent) {
                updateView();
            }
        });
    }

    private void updateView() {
        if (loginService.isRegistered()) {
            showLoggedView();
        } else {
            showUnregisteredView();
        }
    }

    private void showUnregisteredView() {
        panel.clear();
        panel.add(btnHome);
        panel.add(btnLogin);
        panel.add(btnRegistration);
    }

    private void showLoggedView() {
        panel.clear();
        Label userName = new Label(loginService.getUser().getName());
        panel.add(btnHome);
        panel.add(userName);
        panel.add(btnLogoff);
    }
}
