package ru.mativ.client.widgets.mvp;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;

public class Form<T> extends Composite implements HasValue<T> {

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
        return this.addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    public T getValue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setValue(T value) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setValue(T value, boolean fireEvents) {
        // TODO Auto-generated method stub
        ValueChangeEvent.fire(this, value);
    }

}
