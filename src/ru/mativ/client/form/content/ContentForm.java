package ru.mativ.client.form.content;

import java.util.Date;

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
import ru.mativ.client.form.notes.calendar.NoteMonthCalendarForm;
import ru.mativ.client.form.notes.day.NoteDayForm;
import ru.mativ.client.form.notes.day.NoteDayFormPresenter;
import ru.mativ.client.form.notes.day.impl.NoteDayFormImpl;
import ru.mativ.client.form.notes.day.impl.NoteDayFormPresenterImpl;
import ru.mativ.client.form.registration.RegistrationForm;
import ru.mativ.client.form.registration.impl.RegistrationFormImpl;
import ru.mativ.client.form.registration.impl.RegistrationFormPresenterImpl;
import ru.mativ.client.form.workspace.Workspace;
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
            public void navigate(NavigationEvent navigationEvent) {
                showContentForTarget(navigationEvent.getTarget());
            }
        });
    }

    private void showContentForTarget(NavigationTarget target) {
        if (loginService.isRegistered()) {
            if (NavigationTarget.LOGIN.equals(target) || NavigationTarget.REGISTRATION.equals(target)) {
                return;
            }
        } else {
            if (!target.isAllowToUnonim()) {
                globalBus.fireEvent(new NavigationEvent(NavigationTarget.LOGIN));
                return;
            }
        }
        panel.clear();
        switch (target) {
            case HOME:
                showHomeForm();
                break;
            case LOGIN:
                showLoginForm();
                break;
            case LOGOFF:
                // TODO Auto-generated method stub
                break;
            case NOTE_DAY:
                showNoteDayForm();
                break;
            case NOTE_MONTH:
                showNoteMonthForm();
                break;
            case WORKSPACE:
                showWorkspaceForm();
                break;
            case NULL:
                break;
            case REGISTRATION:
                showRegistrationForm();
                break;
            default:
                break;
        }
    }

    private void showLoginForm() {
        LoginForm loginForm = new LoginFormImpl();
        LoginPresenter loginPresenter = new LoginPresenterImpl(loginForm);
        loginPresenter.setDefaultData("test", "123"); //Develop only
        loginPresenter.go(panel);
    }

    private void showRegistrationForm() {
        RegistrationForm registrationForm = new RegistrationFormImpl();
        RegistrationFormPresenterImpl registrationFormPresenter = new RegistrationFormPresenterImpl(registrationForm);
        registrationFormPresenter.go(panel);
    }

    private void showHomeForm() {
        panel.add(new HomeForm());
    }

    private void showNoteDayForm() {
        NoteDayForm noteDayForm = new NoteDayFormImpl();
        NoteDayFormPresenter noteDayFormPresenter = new NoteDayFormPresenterImpl(noteDayForm);
        noteDayFormPresenter.update(new Date());
        noteDayFormPresenter.go(panel);
    }

    private void showNoteMonthForm() {
        NoteMonthCalendarForm noteMonthCalendar = new NoteMonthCalendarForm();
        panel.add(noteMonthCalendar);
        noteMonthCalendar.loadMonth(new Date());
    }

    private void showWorkspaceForm() {
        Workspace workspace = new Workspace();
        panel.add(workspace);
        workspace.load(new Date());
    }
}
