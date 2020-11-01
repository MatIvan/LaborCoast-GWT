package ru.mativ.client.form.notes.widgets.input;

import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class VerticalLabeledInputBox<T> extends LabeledInputBox<T> {

    public VerticalLabeledInputBox(String caption) {
        super(caption);
    }

    @Override
    protected CellPanel createPanel() {
        return new VerticalPanel();
    }
}
