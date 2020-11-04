package ru.mativ.client.widgets.mvp.view;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.widgets.VLabeledPanel;

public abstract class VerticalView<T> extends AbstractView<T> {

    public VerticalView() {
        super(new VerticalPanel());
        ((VerticalPanel) getMainPanel()).setSpacing(10);
    }

    @Override
    public void addField(String label, Widget widget) {
        if (label == null) {
            add(widget);
        } else {
            add(new VLabeledPanel(label, widget));
        }
    }
}
