package ru.mativ.client.form.notes.day.impl;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import ru.mativ.client.event.aftersave.AfterSaveEvent;
import ru.mativ.client.event.aftersave.AfterSaveHandler;
import ru.mativ.client.form.notes.day.NoteDayForm;
import ru.mativ.client.form.notes.single.impl.NoteSinglePopupForm;
import ru.mativ.client.form.notes.widgets.noteslist.NotesListRowData;
import ru.mativ.client.form.notes.widgets.noteslist.NotesTable;
import ru.mativ.client.form.notes.widgets.noteslist.NotesTableHandler;
import ru.mativ.client.widgets.AdvancedDatePicker;
import ru.mativ.client.widgets.mvp.view.VerticalView;
import ru.mativ.shared.bean.NoteBean;

public class NoteDayFormImpl extends VerticalView<NoteDayFormModel> implements NoteDayForm {

    private AdvancedDatePicker advancedDatePicker;
    private NotesTable table;
    private Button addNoteButton;
    private NoteSinglePopupForm noteSinglePopupForm;

    public NoteDayFormImpl() {
        super();
    }

    private void initNoteSinglePopupForm() {
        noteSinglePopupForm = new NoteSinglePopupForm();
        noteSinglePopupForm.addAfterSaveHandler(new AfterSaveHandler<NoteBean>() {
            @Override
            public void onAfterSave(AfterSaveEvent<NoteBean> event) {
                noteSinglePopupForm.hide();
                advancedDatePicker.setValue(event.getValue().getDate());
                fireValueChangeEvent();
            }
        });
        noteSinglePopupForm.addCloseBtnClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                noteSinglePopupForm.hide();
            }
        });
    }

    private void initAddNoteButton() {
        addNoteButton = new Button("Add note");
        addNoteButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                noteSinglePopupForm.show();
                noteSinglePopupForm.addNew(advancedDatePicker.getValue());
            }
        });
    }

    private void initDatePicker() {
        advancedDatePicker = new AdvancedDatePicker();
        advancedDatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                fireValueChangeEvent();
            }
        });
    }

    private NotesTableHandler getNotesTableHandler() {
        return new NotesTableHandler() {

            @Override
            public void onEditClick(NotesListRowData notesListRowData) {
                noteSinglePopupForm.show();
                noteSinglePopupForm.load(notesListRowData.getNoteId());
            }

            @Override
            public void onDeleteClick(NotesListRowData notesListRowData) {
                //TODO delete note
            }
        };
    }

    @Override
    public NoteDayFormModel getValue() {
        return new NoteDayFormModel(advancedDatePicker.getValue(), null);
    }

    @Override
    public void setValue(NoteDayFormModel value, boolean fireEvents) {
        advancedDatePicker.setValue(value.getDate());
        table.setData(value.getNotesList());
        if (fireEvents) {
            fireValueChangeEvent();
        }
    }

    @Override
    protected void init() {
        table = new NotesTable(getNotesTableHandler());
        initDatePicker();
        initAddNoteButton();
        initNoteSinglePopupForm();
    }

    @Override
    protected void build() {
        addField("Date:", advancedDatePicker);
        addField("Notes:", table);

        HorizontalPanel buttonsPanel = new HorizontalPanel();
        buttonsPanel.add(addNoteButton);
        addField(buttonsPanel);
    }

    @Override
    public void setErrorMessage(String message) {
        clear();
        addField("Error:", new Label(message));
    }
}
