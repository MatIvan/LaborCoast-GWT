package ru.mativ.client.widgets;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;

public abstract class HasValueComposite<T> extends Composite implements HasValue<T> {

    private Panel mainPanel;

    public HasValueComposite(Panel mainPanel) {
        this.mainPanel = mainPanel;
        init();
        build();
        initWidget(mainPanel);
    }

    protected Panel getMainPanel() {
        return mainPanel;
    }

    protected abstract void init();

    protected abstract void build();

    protected void fireValueChangeEvent() {
        ValueChangeEvent.fire(this, getValue());
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
        return this.addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    public void setValue(T value) {
        setValue(value, false);
    }
}
