package ru.mativ.client.form.notes.widgets.input;

import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public abstract class HorizontalLabeledInputBox<T> extends LabeledInputBox<T> {

    public HorizontalLabeledInputBox(String caption) {
        super(caption);
    }

    @Override
    protected CellPanel createPanel() {
        return new HorizontalPanel();
    }
}
