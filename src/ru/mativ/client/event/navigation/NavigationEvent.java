package ru.mativ.client.event.navigation;

import java.util.logging.Logger;

import com.google.gwt.event.shared.GwtEvent;

public class NavigationEvent extends GwtEvent<NavigationEventHandler> {
    private static final Logger Log = Logger.getLogger(NavigationEvent.class.getName());
    public static Type<NavigationEventHandler> TYPE = new Type<NavigationEventHandler>();

    private NavigationTarget target;

    public NavigationEvent(NavigationTarget target) {
        this.target = target;
    }

    @Override
    public Type<NavigationEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(NavigationEventHandler handler) {
        Log.fine("Dispatch NavigationEvent: target=" + target.toString());
        switch (target) {
            case HOME:
                handler.toHome(this);
                break;
            case LOGIN:
                handler.toLogin(this);
                break;
            case REGISTRATION:
                handler.toRegistration(this);
                break;
            case NOTE_DAY:
                handler.toNoteDay(this);
                break;
            case LOGOFF:
                break;
            case NULL:
                break;
        }
    }
}
