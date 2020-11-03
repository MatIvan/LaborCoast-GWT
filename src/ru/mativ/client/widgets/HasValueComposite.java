package ru.mativ.client.widgets;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public abstract class HasValueComposite<T> extends Composite implements HasValue<T>, HandlerRegistration {

    private ValueChangeHandler<T> handler;

    public HasValueComposite() {
        init();
        build();
        initWidget(getMainPanel());
    }

    protected abstract void init();

    protected abstract void build();

    protected abstract Widget getMainPanel();

    protected void notifyAboutValueChanged() {
        notifyAboutValueChanged(new ValueChangeEvent<T>(getValue()) {
        });
    }

    protected void notifyAboutValueChanged(ValueChangeEvent<T> event) {
        if (handler != null) {
            handler.onValueChange(event);
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
        this.handler = handler;
        return this;
    }

    @Override
    public void removeHandler() {
        handler = null;
    }

    @Override
    public void setValue(T value) {
        setValue(value, false);
    }
}
