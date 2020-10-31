package ru.mativ.client.form.notes.day;

import java.util.Date;
import java.util.List;

import ru.mativ.client.form.notes.widgets.noteslist.NotesListRowData;

public interface NoteDayFormModel {

    Date getDate();

    List<NotesListRowData> getNotesList();
}
