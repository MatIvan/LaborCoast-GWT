package ru.mativ.client.widgets.mvp.view;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.widgets.HasValueComposite;

public abstract class AbstractView<T> extends HasValueComposite<T> {

    public AbstractView(Panel panel) {
        super(panel);
    }

    protected abstract void addField(String label, Widget widget);

    protected void add(Widget widget) {
        getMainPanel().add(widget);
    }

    protected void clear() {
        getMainPanel().clear();
    }
}
