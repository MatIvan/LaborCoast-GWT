package ru.mativ.client.form.notes.day.impl;

import java.util.Date;
import java.util.List;

import ru.mativ.client.form.notes.day.NoteDayFormModel;
import ru.mativ.client.form.notes.widgets.noteslist.NotesListRowData;

public class NoteDayFormModelDefault implements NoteDayFormModel {

    private Date date;
    private List<String> typeList;
    private List<NotesListRowData> noteList;

    public NoteDayFormModelDefault(Date date, List<String> typeList, List<NotesListRowData> noteList) {
        super();
        this.date = date;
        this.typeList = typeList;
        this.noteList = noteList;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public List<String> getTypeList() {
        return typeList;
    }

    @Override
    public List<NotesListRowData> getNotesList() {
        return noteList;
    }

}
