package ru.mativ.client.widgets.mvp.view;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class FlowView<T> extends AbstractView<T> {

    public FlowView() {
        super(new FlowPanel());
    }

    @Override
    protected void addField(Widget widget) {
        add(widget);
    }

    @Override
    public void addField(String label, Widget widget) {
        FlowPanel panel = new FlowPanel();
        panel.add(new Label(label));
        panel.add(widget);
        addField(panel);
    }
}
