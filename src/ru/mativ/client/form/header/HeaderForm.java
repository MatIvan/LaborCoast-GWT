package ru.mativ.client.form.header;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.event.navigation.NavigationEvent;
import ru.mativ.client.event.navigation.NavigationEventHandler;
import ru.mativ.client.event.navigation.NavigationTarget;
import ru.mativ.client.fabrica.NavigateButtonsFabrica;
import ru.mativ.client.service.proxy.LoginServiceProxy;

public class HeaderForm extends Composite {
    private EventBus globalBus = LaborCoast.getEventBus();
    private static final LoginServiceProxy loginService = LaborCoast.getLoginServiceProxy();

    private VerticalPanel mainPanel;
    private HorizontalPanel firstPanel;
    private HorizontalPanel secondPanel;

    private Button btnHome;
    private Button btnLogin;
    private Button btnRegistration;
    private Button btnLogoff;

    private Button btnNoteDay;
    private Button btnNoteMonth;
    private Button btnWorkspace;
    private Button btnReport;

    public HeaderForm() {
        initGui();
        initHandlers();
        updateView();
        initWidget(mainPanel);
    }

    private void initGui() {
        firstPanel = new HorizontalPanel();
        firstPanel.setSpacing(4);
        btnHome = NavigateButtonsFabrica.createButton(NavigationTarget.HOME);
        btnLogin = NavigateButtonsFabrica.createButton(NavigationTarget.LOGIN);
        btnRegistration = NavigateButtonsFabrica.createButton(NavigationTarget.REGISTRATION);
        btnLogoff = NavigateButtonsFabrica.createButton(NavigationTarget.LOGOFF);

        secondPanel = new HorizontalPanel();
        secondPanel.setSpacing(4);
        btnNoteDay = NavigateButtonsFabrica.createButton(NavigationTarget.NOTE_DAY);
        btnNoteMonth = NavigateButtonsFabrica.createButton(NavigationTarget.NOTE_MONTH);
        btnWorkspace = NavigateButtonsFabrica.createButton(NavigationTarget.WORKSPACE);
        btnReport = NavigateButtonsFabrica.createButton(NavigationTarget.REPORT);

        mainPanel = new VerticalPanel();
        mainPanel.setSpacing(2);
        mainPanel.add(firstPanel);
        mainPanel.add(secondPanel);
    }

    private void initHandlers() {
        globalBus.addHandler(NavigationEvent.TYPE, new NavigationEventHandler() {
            @Override
            public void navigate(NavigationEvent navigationEvent) {
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
        firstPanel.clear();
        firstPanel.add(btnHome);
        firstPanel.add(btnLogin);
        firstPanel.add(btnRegistration);

        secondPanel.clear();
    }

    private void showLoggedView() {
        firstPanel.clear();
        Label userName = new Label(loginService.getUser().getName());
        firstPanel.add(btnHome);
        firstPanel.add(btnLogoff);
        firstPanel.add(userName);

        secondPanel.clear();
        secondPanel.add(btnNoteDay);
        secondPanel.add(btnNoteMonth);
        secondPanel.add(btnWorkspace);
        secondPanel.add(btnReport);
    }
}
