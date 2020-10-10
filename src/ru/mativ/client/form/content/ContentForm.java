package ru.mativ.client.form.content;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Command;
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

public class ContentForm extends Composite {

    private EventBus globalBus = LaborCoast.getEventBus();

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

        });

    }

    private void showLoginForm() {
        LoginForm loginForm = new LoginForm(new LoginFormView());
        loginForm.setOnLoginSuccessCommand(new Command() {
            @Override
            public void execute() {
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.HOME));
            }
        });
        loginForm.setOnRegistrationClicked(new Command() {
            @Override
            public void execute() {
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.REGISTRATION));
            }
        });
        panel.clear();
        panel.add(loginForm.asWidget());
    }

    private void showRegistrationForm() {
        RegistrationForm registrationForm = new RegistrationForm(new RegistrationFormView());
        registrationForm.setOnRigistrationSuccessCommand(new Command() {
            @Override
            public void execute() {
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.LOGIN));
            }
        });

        panel.clear();
        panel.add(registrationForm.asWidget());
    }

    private void showHomeForm() {
        panel.clear();
        panel.add(new HomeForm());
    }
}
