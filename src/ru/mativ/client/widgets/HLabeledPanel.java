package ru.mativ.client.widgets;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HLabeledPanel extends HorizontalPanel {

    public HLabeledPanel(String labelText, Widget widget) {
        Label label = new Label(labelText);
        label.getElement().getStyle().setMarginRight(10, Unit.PX);

        add(label);
        add(widget);
    }
}
