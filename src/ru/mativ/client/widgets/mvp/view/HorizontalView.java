package ru.mativ.client.widgets.mvp.view;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.widgets.VLabeledPanel;

public abstract class HorizontalView<T> extends AbstractView<T> {

    public HorizontalView() {
        super(new HorizontalPanel());
        ((HorizontalPanel) getMainPanel()).setSpacing(10);
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
