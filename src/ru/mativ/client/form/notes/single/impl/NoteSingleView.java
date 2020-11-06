package ru.mativ.client.form.notes.single.impl;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.TextArea;

import ru.mativ.client.form.notes.widgets.notetype.NoteTypeWidget;
import ru.mativ.client.widgets.AdvancedDatePicker;
import ru.mativ.client.widgets.NumberInputBox;
import ru.mativ.client.widgets.mvp.view.VerticalView;

public class NoteSingleView extends VerticalView<NoteSingleViewModel> {

    private AdvancedDatePicker dateBox;
    private TextArea noteBox;
    private TextArea commentBox;
    private NumberInputBox hoursBox;
    private NoteTypeWidget typeBox;

    public NoteSingleView() {
        super();
    }

    @Override
    public NoteSingleViewModel getValue() {
        return new NoteSingleViewModel(
                typeBox.getValue(),
                noteBox.getValue(),
                commentBox.getValue(),
                dateBox.getValue(),
                hoursBox.getValue());
    }

    @Override
    public void setValue(NoteSingleViewModel value, boolean fireEvents) {
        dateBox.setValue(value.getDate());
        noteBox.setValue(value.getNote());
        commentBox.setValue(value.getComment());
        hoursBox.setValue(value.getHours());
        typeBox.setValue(value.getTypeId());
        if (fireEvents) {
            fireValueChangeEvent();
        }
    }

    @Override
    protected void init() {
        dateBox = new AdvancedDatePicker();
        noteBox = new TextArea();
        commentBox = new TextArea();
        hoursBox = new NumberInputBox();
        typeBox = new NoteTypeWidget();
        typeBox.addValueChangeHandler(new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange(ValueChangeEvent<Integer> event) {
                String name = typeBox.getValueName();
                noteBox.setValue(name);
            }
        });
    }

    @Override
    protected void build() {
        addField("Date:", dateBox);
        addField("Note:", noteBox);
        addField("Comment:", commentBox);
        addField("Hours:", hoursBox);
        addField("Type:", typeBox);
    }
}
