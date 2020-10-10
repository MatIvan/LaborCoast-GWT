package ru.mativ.client.event.navigation;

import com.google.gwt.event.shared.GwtEvent;

public class NavigationEvent extends GwtEvent<NavigationEventHandler> {

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
            default:
                break;
        }
    }
}
