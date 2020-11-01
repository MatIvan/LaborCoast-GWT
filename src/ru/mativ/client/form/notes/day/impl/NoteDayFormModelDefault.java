package ru.mativ.client.form.notes.day.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.mativ.client.form.notes.day.NoteDayFormModel;
import ru.mativ.client.form.notes.widgets.noteslist.NotesListRowData;
import ru.mativ.shared.bean.NoteBean;

public class NoteDayFormModelDefault implements NoteDayFormModel {

    private Date date;
    private List<NotesListRowData> noteList;

    public NoteDayFormModelDefault(Date date, List<NoteBean> dayNotes) {
        super();
        this.date = date;
        this.noteList = makeNotesListRowData(dayNotes);
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public List<NotesListRowData> getNotesList() {
        return noteList;
    }

    private List<NotesListRowData> makeNotesListRowData(List<NoteBean> dayNotes) {
        List<NotesListRowData> result = new ArrayList<>();
        for (NoteBean bean : dayNotes) {
            NotesListRowData newBean = new NotesListRowData(
                    bean.getNote(),
                    bean.getComment(),
                    bean.getHours(),
                    bean.getId());
            result.add(newBean);
        }
        return result;
    }
}
