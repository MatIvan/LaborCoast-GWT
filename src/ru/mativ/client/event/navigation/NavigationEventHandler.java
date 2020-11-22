package ru.mativ.client.event.navigation;

import com.google.gwt.event.shared.EventHandler;

public interface NavigationEventHandler extends EventHandler {
    void navigate(NavigationEvent navigationEvent);
}
