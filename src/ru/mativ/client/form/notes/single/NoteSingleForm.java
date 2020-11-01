package ru.mativ.client.form.notes.single;

import java.util.Date;

import com.google.gwt.user.client.ui.IsWidget;

public interface NoteSingleForm extends IsWidget {

    void loadNote(int noteId);

    void newNote(Date date);

    void setHandler(NoteSingleFormHandler noteSingleFormHandler);
}
