package ru.mativ.client.form.notes.day.impl;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import ru.mativ.client.form.notes.day.NoteDayFormModel;
import ru.mativ.client.form.notes.day.NoteDayFormPresenter;
import ru.mativ.client.form.notes.day.NoteDayFormView;
import ru.mativ.client.form.notes.single.NoteSingleFormHandler;
import ru.mativ.client.form.notes.single.NoteSingleFormModel;
import ru.mativ.client.form.notes.single.impl.NoteSinglePopupForm;
import ru.mativ.client.form.notes.widgets.noteslist.NotesListRowData;
import ru.mativ.client.form.notes.widgets.noteslist.NotesTable;
import ru.mativ.client.form.notes.widgets.noteslist.NotesTableHandler;
import ru.mativ.client.widgets.AdvancedDatePicker;

public class NoteDayFormViewDefault extends Composite implements NoteDayFormView {
    private NoteDayFormPresenter presenter;

    private VerticalPanel mainPanel;
    private AdvancedDatePicker advancedDatePicker;
    private NotesTable table;
    private Button addNoteButton;

    private NoteSinglePopupForm noteSinglePopupForm;

    public NoteDayFormViewDefault() {
        this(new NoteDayFormPresenterDefault());
    }

    public NoteDayFormViewDefault(NoteDayFormPresenter presenter) {
        this.presenter = presenter;
        presenter.setView(this);

        initGui();
        buildGui();
        initWidget(mainPanel);

        presenter.update(new Date());
    }

    private void initGui() {
        mainPanel = new VerticalPanel();
        table = new NotesTable(getNotesTableHandler());
        initDatePicker();
        initAddNoteButton();
        initNoteSinglePopupForm();
    };

    private void buildGui() {
        mainPanel.add(advancedDatePicker);
        mainPanel.add(table);
        mainPanel.add(addNoteButton);
    }

    private void initNoteSinglePopupForm() {
        noteSinglePopupForm = new NoteSinglePopupForm();
        noteSinglePopupForm.setHandler(new NoteSingleFormHandler() {

            @Override
            public void onSaved(NoteSingleFormModel model) {
                noteSinglePopupForm.hide();
                presenter.update(model.getDate());
            }

            @Override
            public void onClose() {
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
                noteSinglePopupForm.newNote(advancedDatePicker.getValue());
            }
        });
    }

    private void initDatePicker() {
        advancedDatePicker = new AdvancedDatePicker();
        advancedDatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                presenter.update(event.getValue());
            }
        });
    }

    @Override
    public void setData(NoteDayFormModel model) {
        advancedDatePicker.setValue(model.getDate());
        table.setData(model.getNotesList());
    }

    private NotesTableHandler getNotesTableHandler() {
        return new NotesTableHandler() {

            @Override
            public void onEditClick(NotesListRowData notesListRowData) {
                noteSinglePopupForm.show();
                noteSinglePopupForm.loadNote(notesListRowData.getNoteId());
            }

            @Override
            public void onDeleteClick(NotesListRowData notesListRowData) {
                //TODO delete note
            }
        };
    }
}
