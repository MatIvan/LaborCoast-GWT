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
import ru.mativ.client.form.login.LoginPresenter;
import ru.mativ.client.form.login.impl.LoginFormImpl;
import ru.mativ.client.form.login.impl.LoginPresenterImpl;
import ru.mativ.client.form.notes.day.impl.NoteDayFormViewDefault;
import ru.mativ.client.form.registration.RegistrationForm;
import ru.mativ.client.form.registration.impl.RegistrationFormImpl;
import ru.mativ.client.form.registration.impl.RegistrationFormPresenterImpl;
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

            @Override
            public void toNoteDay(NavigationEvent navigationEvent) {
                showNoteDayForm();
            }
        });
    }

    private void showLoginForm() {
        if (loginService.isRegistered()) {
            return;
        }
        panel.clear();
        LoginForm loginForm = new LoginFormImpl();
        LoginPresenter loginPresenter = new LoginPresenterImpl(loginForm);
        loginPresenter.setDefaultData("test", "123"); //Develop only
        loginPresenter.go(panel);
    }

    private void showRegistrationForm() {
        if (loginService.isRegistered()) {
            return;
        }
        panel.clear();
        RegistrationForm registrationForm = new RegistrationFormImpl();
        RegistrationFormPresenterImpl registrationFormPresenter = new RegistrationFormPresenterImpl(registrationForm);
        registrationFormPresenter.go(panel);
    }

    private void showHomeForm() {
        if (!loginService.isRegistered()) {
            globalBus.fireEvent(new NavigationEvent(NavigationTarget.LOGIN));
            return;
        }
        panel.clear();
        panel.add(new HomeForm());
    }

    private void showNoteDayForm() {
        if (!loginService.isRegistered()) {
            globalBus.fireEvent(new NavigationEvent(NavigationTarget.LOGIN));
            return;
        }
        panel.clear();
        panel.add(new NoteDayFormViewDefault());
    }
}
