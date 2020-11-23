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
    protected void addField(Widget widget) {
        add(widget);
    }

    @Override
    public void addField(String label, Widget widget) {
        add(new VLabeledPanel(label, widget));
    }
}
