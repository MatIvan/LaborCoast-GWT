package ru.mativ.client.form.notes.widgets.input;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase;

public class VerticalLabeledTextBox extends VerticalLabeledInputBox<String> {

    public VerticalLabeledTextBox(String caption) {
        super(caption);
    }

    @Override
    protected ValueBoxBase<String> createInputWidget() {
        return new TextBox();
    }
}
