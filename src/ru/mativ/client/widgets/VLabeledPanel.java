package ru.mativ.client.widgets;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VLabeledPanel extends VerticalPanel {

    public VLabeledPanel(String labelText, Widget widget) {
        add(new Label(labelText));
        add(widget);
    }
}
