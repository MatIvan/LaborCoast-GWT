package ru.mativ.client.form.notes.single.impl;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import ru.mativ.client.form.notes.single.NoteSingleForm;
import ru.mativ.client.form.notes.single.NoteSingleFormHandler;
import ru.mativ.client.form.notes.single.NoteSingleFormModel;
import ru.mativ.client.form.notes.single.NoteSingleFormPresenter;
import ru.mativ.client.form.notes.single.NoteSingleFormView;
import ru.mativ.client.form.notes.widgets.AdvancedDatePicker;
import ru.mativ.client.form.notes.widgets.HLabeledPanel;
import ru.mativ.client.form.notes.widgets.NumberInputBox;
import ru.mativ.client.form.notes.widgets.VLabeledPanel;

public class NoteSingleFormViewDefault extends Composite implements NoteSingleForm, NoteSingleFormView {

    private NoteSingleFormPresenter presenter;
    private NoteSingleFormModel model;
    private NoteSingleFormHandler handler;

    private VerticalPanel mainPanel;
    private AdvancedDatePicker dateBox;
    private TextArea noteBox;
    private TextArea commentBox;
    private NumberInputBox hoursBox;
    private NumberInputBox typeBox; //TODO change to type (not number)

    private Button closeBtn;
    private Button saveBtn;

    public NoteSingleFormViewDefault() {
        this(new NoteSingleFormPresenterDefault());
    }

    public NoteSingleFormViewDefault(NoteSingleFormPresenter presenter) {
        this.presenter = presenter;
        presenter.setView(this);

        initGui();
        buildGui();
        initWidget(mainPanel);
    }

    private void initGui() {
        mainPanel = new VerticalPanel();
        mainPanel.setSpacing(10);

        dateBox = new AdvancedDatePicker();
        noteBox = new TextArea();
        commentBox = new TextArea();
        hoursBox = new NumberInputBox();
        typeBox = new NumberInputBox();

        initButtons();
    };

    private void initButtons() {
        closeBtn = new Button("Close");
        closeBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                handler.onClose();
            }
        });

        saveBtn = new Button("Save");
        saveBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                presenter.save(makeModel());
            }
        });
    }

    private void buildGui() {
        mainPanel.add(dateBox);
        mainPanel.add(new VLabeledPanel("Note:", noteBox));
        mainPanel.add(new VLabeledPanel("Comment:", commentBox));
        mainPanel.add(new HLabeledPanel("Hours:", hoursBox));
        mainPanel.add(new HLabeledPanel("Type:", typeBox));

        HorizontalPanel buttonsPanel = new HorizontalPanel();
        buttonsPanel.add(closeBtn);
        buttonsPanel.add(saveBtn);
        mainPanel.add(buttonsPanel);
    }

    private void fill() {
        dateBox.setValue(model.getDate());
        noteBox.setValue(model.getNote());
        commentBox.setValue(model.getComment());
        hoursBox.setValue(model.getHours());
        typeBox.setValue(model.getTypeId());
    }

    private NoteSingleFormModel makeModel() {
        return new NoteSingleFormModelDefault(
                typeBox.getValue(),
                noteBox.getValue(),
                commentBox.getValue(),
                dateBox.getValue(),
                hoursBox.getValue());
    }

    @Override
    public void setData(NoteSingleFormModel model) {
        this.model = model;
        fill();
    }

    @Override
    public void showError(String errorText) {
        mainPanel.clear();
        mainPanel.add(new Label(errorText));
        mainPanel.add(closeBtn);
    }

    @Override
    public void dataSaved(NoteSingleFormModel model) {
        handler.onSaved(model);
    }

    @Override
    public void setHandler(NoteSingleFormHandler noteSingleFormHandler) {
        this.handler = noteSingleFormHandler;
    }

    @Override
    public void loadNote(int noteId) {
        presenter.load(noteId);
    }

    @Override
    public void newNote(Date date) {
        presenter.add(date);
    }
}
