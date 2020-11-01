package ru.mativ.client.form.notes.widgets.input;

import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.ValueBoxBase;

public class VerticalLabeledPassBox extends VerticalLabeledInputBox<String> {

    public VerticalLabeledPassBox(String caption) {
        super(caption);
    }

    @Override
    protected ValueBoxBase<String> createInputWidget() {
        return new PasswordTextBox();
    }
}
