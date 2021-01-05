package ru.mativ.client.form.home;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class HomeForm extends Composite {
    private static final Logger Log = Logger.getLogger(HomeForm.class.getName());

    public HomeForm() {
        initWidget(build());
    }

    private Widget build() {
        VerticalPanel panel = new VerticalPanel();
        panel.add(new Label("Welcome !!!"));
        return panel;
    }
}
