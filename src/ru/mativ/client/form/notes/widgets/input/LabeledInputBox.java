package ru.mativ.client.form.notes.widgets.input;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class LabeledInputBox extends Composite implements HasValue<String> {

    private LabeledInputBoxType type;
    private CellPanel panel;
    private Label label;
    private TextBox input;

    public LabeledInputBox(String caption, LabeledInputBoxType type) {
        this.type = type;
        initGui(caption);
        buildGui();
        initWidget(panel);
    }

    private void initGui(String caption) {
        panel = type.getPanel();
        label = new Label(caption);
        input = type.getInput();
    }

    private void buildGui() {
        panel.add(label);
        panel.add(input);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return input.addValueChangeHandler(handler);
    }

    @Override
    public String getValue() {
        return input.getValue();
    }

    @Override
    public void setValue(String value) {
        setValue(value, false);
    }

    @Override
    public void setValue(String value, boolean fireEvents) {
        input.setValue(value, fireEvents);
    }
}
