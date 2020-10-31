package ru.mativ.client.form.notes.day;

import java.util.Date;

public interface NoteDayFormPresenter {

    void setView(NoteDayFormView view);

    void update(Date date);

    void editNote(int noteId);

    void deleteNote(int noteId);

    void addNote(Date date);
}
