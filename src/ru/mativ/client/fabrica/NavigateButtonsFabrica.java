package ru.mativ.client.fabrica;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.event.navigation.NavigationEvent;
import ru.mativ.client.event.navigation.NavigationTarget;

public class NavigateButtonsFabrica {
    private static EventBus globalBus = LaborCoast.getEventBus();

    public static Button createButton(final NavigationTarget target) {
        Button button = new Button(target.getName());
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                globalBus.fireEvent(new NavigationEvent(target));
            }
        });
        return button;
    }
}
