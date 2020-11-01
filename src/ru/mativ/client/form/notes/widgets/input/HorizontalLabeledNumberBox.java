package ru.mativ.client.form.notes.widgets.input;

import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ValueBoxBase;

public class HorizontalLabeledNumberBox extends HorizontalLabeledInputBox<Integer> {

    public HorizontalLabeledNumberBox(String caption) {
        super(caption);
    }

    @Override
    protected ValueBoxBase<Integer> createInputWidget() {
        return new IntegerBox();
    }
}
