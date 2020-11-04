package ru.mativ.client.widgets;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public abstract class HasValueComposite<T> extends Composite implements HasValue<T> {

    public HasValueComposite() {
        init();
        build();
        initWidget(getMainPanel());
    }

    protected abstract void init();

    protected abstract void build();

    protected abstract Widget getMainPanel();

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
