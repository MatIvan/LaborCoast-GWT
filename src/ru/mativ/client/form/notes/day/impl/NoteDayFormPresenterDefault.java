package ru.mativ.client.form.notes.day.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.form.notes.day.NoteDayFormPresenter;
import ru.mativ.client.form.notes.day.NoteDayFormView;
import ru.mativ.client.service.proxy.NoteServiceProxy;
import ru.mativ.shared.bean.NoteBean;

public class NoteDayFormPresenterDefault implements NoteDayFormPresenter {
    private static final Logger Log = Logger.getLogger(NoteDayFormPresenterDefault.class.getName());
    private static final NoteServiceProxy noteService = LaborCoast.getNoteServiceProxy();

    private NoteDayFormView view;

    public NoteDayFormPresenterDefault() {
        super();
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
        NoteDayFormModelDefault model = new NoteDayFormModelDefault(date, dayNotes);
        view.setData(model);
    }
}
