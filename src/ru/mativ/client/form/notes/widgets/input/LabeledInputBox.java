package ru.mativ.client.form.notes.widgets.input;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ValueBoxBase;

public abstract class LabeledInputBox<T> extends Composite implements HasValue<T> {

    private CellPanel panel;
    private Label label;
    private ValueBoxBase<T> input;

    public LabeledInputBox(String caption) {
        initGui(caption);
        buildGui();
        initWidget(panel);
    }

    protected abstract CellPanel createPanel();

    protected abstract ValueBoxBase<T> createInputWidget();

    private void initGui(String caption) {
        panel = createPanel();
        label = new Label(caption);
        input = createInputWidget();
    }

    private void buildGui() {
        panel.add(label);
        panel.add(input);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
        return input.addValueChangeHandler(handler);
    }

    @Override
    public T getValue() {
        return input.getValue();
    }

    @Override
    public void setValue(T value) {
        setValue(value, false);
    }

    @Override
    public void setValue(T value, boolean fireEvents) {
        input.setValue(value, fireEvents);
    }
}
