package ru.mativ.client.form.notes.day.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.form.notes.day.NoteDayFormModel;
import ru.mativ.client.form.notes.day.NoteDayFormPresenter;
import ru.mativ.client.form.notes.day.NoteDayFormView;
import ru.mativ.client.form.notes.widgets.AdvancedDatePicker;
import ru.mativ.client.form.notes.widgets.noteslist.NotesListRowData;
import ru.mativ.client.form.notes.widgets.noteslist.NotesTable;
import ru.mativ.client.form.notes.widgets.noteslist.NotesTableHandler;

public class NoteDayFormViewDefault extends Composite implements NoteDayFormView {
    private static final Logger Log = Logger.getLogger(NoteDayFormViewDefault.class.getName());
    private NoteDayFormPresenter presenter;

    VerticalPanel mainPanel;
    private AdvancedDatePicker advancedDatePicker;
    private NotesTable table;
    private Button addNoteButton;

    public NoteDayFormViewDefault() {
        this(new NoteDayFormPresenterDefault());
    }

    public NoteDayFormViewDefault(NoteDayFormPresenter presenter) {
        this.presenter = presenter;
        presenter.setView(this);

        initGui();
        initWidget(buildGui());

        presenter.update(new Date());
    }

    private void initGui() {
        mainPanel = new VerticalPanel();
        table = new NotesTable(getNotesTableHandler());
        initDatePicker();
        addNoteButton = new Button("Add note");
    };

    private Widget buildGui() {

        mainPanel.add(advancedDatePicker);
        mainPanel.add(table);

        return mainPanel;
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
                presenter.editNote(notesListRowData.getNoteId());
            }

            @Override
            public void onDeleteClick(NotesListRowData notesListRowData) {
                presenter.deleteNote(notesListRowData.getNoteId());
            }
        };
    }
}
