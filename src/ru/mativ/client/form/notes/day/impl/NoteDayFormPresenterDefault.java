package ru.mativ.client.form.notes.day.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.form.notes.day.NoteDayFormPresenter;
import ru.mativ.client.form.notes.day.NoteDayFormView;
import ru.mativ.client.form.notes.widgets.noteslist.NotesListRowData;
import ru.mativ.client.service.proxy.NoteServiceProxy;
import ru.mativ.shared.bean.NoteBean;

public class NoteDayFormPresenterDefault implements NoteDayFormPresenter {
    private static final Logger Log = Logger.getLogger(NoteDayFormPresenterDefault.class.getName());
    private static final NoteServiceProxy noteService = LaborCoast.getNoteServiceProxy();

    private NoteDayFormView view;

    public NoteDayFormPresenterDefault() {
    }

    @Override
    public void setView(NoteDayFormView view) {
        this.view = view;
    }

    @Override
    public void update(final Date date) {
        noteService.getByDate(date, new AsyncCallback<List<NoteBean>>() {

            @Override
            public void onFailure(Throwable caught) {
                Log.log(Level.SEVERE, "Can not load notes by day.", caught);
            }

            @Override
            public void onSuccess(List<NoteBean> result) {
                setData(date, result);
            }
        });
    }

    private void setData(Date date, List<NoteBean> dayNotes) {
        List<NotesListRowData> noteList = makeNotesListRowData(dayNotes);
        NoteDayFormModelDefault model = new NoteDayFormModelDefault(date, noteList);
        view.setData(model);
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

    @Override
    public void editNote(int noteId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteNote(int noteId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addNote(Date date) {
        // TODO Auto-generated method stub

    }

}
