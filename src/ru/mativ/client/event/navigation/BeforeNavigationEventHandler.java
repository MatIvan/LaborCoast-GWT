package ru.mativ.client.event.navigation;

import com.google.gwt.event.shared.EventHandler;

public interface BeforeNavigationEventHandler extends EventHandler {
    void beforeNavigation(NavigationTarget target);
}
