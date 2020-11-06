package ru.mativ.client.form.notes.single.impl;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import ru.mativ.client.form.notes.single.NoteSingleForm;
import ru.mativ.client.widgets.mvp.view.VerticalView;

public class NoteSingleFormImpl extends VerticalView<NoteSingleViewModel> implements NoteSingleForm {

    private NoteSingleView noteSingleView;
    private Button closeBtn;
    private Button saveBtn;

    public NoteSingleFormImpl() {
        super();
    }

    @Override
    public NoteSingleViewModel getValue() {
        return noteSingleView.getValue();
    }

    @Override
    public void setValue(NoteSingleViewModel value, boolean fireEvents) {
        noteSingleView.setValue(value, fireEvents);
    }

    @Override
    public void setErrorMessage(String message) {
        this.clear();
        addField("Error:", new Label(message));
        addField(null, closeBtn);
    }

    @Override
    public void addSaveBtnClickHandler(ClickHandler clickHandler) {
        saveBtn.addClickHandler(clickHandler);
    }

    @Override
    public void addCloseBtnClickHandler(ClickHandler clickHandler) {
        closeBtn.addClickHandler(clickHandler);
    }

    @Override
    protected void init() {
        noteSingleView = new NoteSingleView();
        closeBtn = new Button("Close");
        saveBtn = new Button("Save");
    }

    @Override
    protected void build() {
        addField("NoteSingleForm", noteSingleView);

        HorizontalPanel buttonsPanel = new HorizontalPanel();
        buttonsPanel.add(closeBtn);
        buttonsPanel.add(saveBtn);
        addField(null, buttonsPanel);
    }
}
