package ru.mativ.client.form.notes.single.impl;

import java.util.Date;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.form.notes.single.NoteSingleForm;
import ru.mativ.client.form.notes.single.NoteSingleFormHandler;

public class NoteSinglePopupForm implements NoteSingleForm, IsWidget {

    private DialogBox box;
    private NoteSingleFormViewDefault form;

    public NoteSinglePopupForm() {
        form = new NoteSingleFormViewDefault();
        box = new DialogBox();
        box.setText("NoteSingleForm");
        box.setGlassEnabled(true);
        box.setAnimationEnabled(true);
        box.setWidget(form);
    }

    public void show() {
        box.center();
        box.show();
    }

    public void hide() {
        box.hide();
    }

    @Override
    public void loadNote(int noteId) {
        form.loadNote(noteId);
    }

    @Override
    public void newNote(Date date) {
        form.newNote(date);
    }

    @Override
    public void setHandler(NoteSingleFormHandler noteSingleFormHandler) {
        form.setHandler(noteSingleFormHandler);
    }

    @Override
    public Widget asWidget() {
        return form;
    }
}
