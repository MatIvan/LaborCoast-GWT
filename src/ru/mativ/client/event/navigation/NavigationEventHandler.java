package ru.mativ.client.event.navigation;

import com.google.gwt.event.shared.EventHandler;

public interface NavigationEventHandler extends EventHandler {

    void toHome(NavigationEvent navigationEvent);

    void toLogin(NavigationEvent navigationEvent);

    void toRegistration(NavigationEvent navigationEvent);

    void toLogoff(NavigationEvent navigationEvent);

    void toNoteDay(NavigationEvent navigationEvent);
}
