package ru.mativ.client.event.navigation;

import java.util.logging.Logger;

import com.google.gwt.event.shared.GwtEvent;

public class BeforeNavigationEvent extends GwtEvent<BeforeNavigationEventHandler> {
    private static final Logger Log = Logger.getLogger(BeforeNavigationEvent.class.getName());
    public static Type<BeforeNavigationEventHandler> TYPE = new Type<BeforeNavigationEventHandler>();

    private NavigationTarget target;

    public BeforeNavigationEvent(NavigationTarget target) {
        this.target = target;
    }

    @Override
    public Type<BeforeNavigationEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(BeforeNavigationEventHandler handler) {
        Log.fine("Dispatch BeforeNavigationEvent: target=" + target.toString());
        handler.beforeNavigation(target);
    }
}
