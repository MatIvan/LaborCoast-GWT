package ru.mativ.client.form.content;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.event.navigation.NavigationEvent;
import ru.mativ.client.event.navigation.NavigationEventHandler;
import ru.mativ.client.event.navigation.NavigationTarget;
import ru.mativ.client.form.home.HomeForm;
import ru.mativ.client.form.login.LoginForm;
import ru.mativ.client.form.login.LoginFormView;
import ru.mativ.client.form.registration.RegistrationForm;
import ru.mativ.client.form.registration.RegistrationFormView;
import ru.mativ.client.service.proxy.LoginServiceProxy;

public class ContentForm extends Composite {
    private static final LoginServiceProxy loginService = LaborCoast.getLoginServiceProxy();
    private static final EventBus globalBus = LaborCoast.getEventBus();

    private FlowPanel panel;

    public ContentForm() {
        initGui();
        initHandlers();
        initWidget(panel);
    }

    private void initGui() {
        panel = new FlowPanel();
        showLoginForm();
    }

    private void initHandlers() {
        globalBus.addHandler(NavigationEvent.TYPE, new NavigationEventHandler() {

            @Override
            public void toHome(NavigationEvent navigationEvent) {
                showHomeForm();
            }

            @Override
            public void toLogin(NavigationEvent navigationEvent) {
                showLoginForm();
            }

            @Override
            public void toRegistration(NavigationEvent navigationEvent) {
                showRegistrationForm();
            }

            @Override
            public void toLogoff(NavigationEvent navigationEvent) {
                // TODO Auto-generated method stub

            }

        });

    }

    private void showLoginForm() {
        if (loginService.isRegistered()) {
            return;
        }
        LoginForm loginForm = new LoginForm(new LoginFormView());
        panel.clear();
        panel.add(loginForm.asWidget());
    }

    private void showRegistrationForm() {
        if (loginService.isRegistered()) {
            return;
        }
        RegistrationForm registrationForm = new RegistrationForm(new RegistrationFormView());
        panel.clear();
        panel.add(registrationForm.asWidget());
    }

    private void showHomeForm() {
        if (!loginService.isRegistered()) {
            globalBus.fireEvent(new NavigationEvent(NavigationTarget.LOGIN));
            return;
        }
        panel.clear();
        panel.add(new HomeForm());
    }
}
