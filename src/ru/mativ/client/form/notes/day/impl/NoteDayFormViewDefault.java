package ru.mativ.client.form.notes.day.impl;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.form.notes.day.NoteDayFormModel;
import ru.mativ.client.form.notes.day.NoteDayFormPresenter;
import ru.mativ.client.form.notes.day.NoteDayFormView;
import ru.mativ.client.form.notes.widgets.noteslist.NotesTable;

public class NoteDayFormViewDefault extends Composite implements NoteDayFormView {

    private NoteDayFormPresenter presenter;

    private Label dateLabel;
    private NotesTable table;

    public NoteDayFormViewDefault() {
        this(new NoteDayFormPresenterDefault());
    }

    public NoteDayFormViewDefault(NoteDayFormPresenter presenter) {
        this.presenter = presenter;
        presenter.setView(this);
        initWidget(buildGui());

        presenter.update(new Date());
    }

    private Widget buildGui() {
        dateLabel = new Label();
        table = new NotesTable();

        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(dateLabel);
        mainPanel.add(table);

        return mainPanel;
    }

    @Override
    public void setData(NoteDayFormModel model) {
        setDateLabel(model.getDate());
        table.setData(model.getNotesList());
    }

    private void setDateLabel(Date date) {
        String dateString = "--";
        if (date != null) {
            dateString = DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM).format(date);
        }
        dateLabel.setText(dateString);
    }
}
