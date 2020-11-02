package ru.mativ.client.form.notes.single.impl;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.form.notes.single.NoteSingleFormModel;
import ru.mativ.client.form.notes.single.NoteSingleFormPresenter;
import ru.mativ.client.form.notes.single.NoteSingleFormView;
import ru.mativ.client.service.proxy.NoteServiceProxy;
import ru.mativ.shared.bean.NoteBean;

public class NoteSingleFormPresenterDefault implements NoteSingleFormPresenter {
    private static final Logger Log = Logger.getLogger(NoteSingleFormPresenterDefault.class.getName());
    private static final NoteServiceProxy noteService = LaborCoast.getNoteServiceProxy();

    private NoteSingleFormView view;

    private NoteBean noteBean;

    public NoteSingleFormPresenterDefault() {
        super();
    }

    private void setData(NoteBean bean) {
        noteBean = bean;
        NoteSingleFormModel model = NoteSingleFormModelDefault.makeFromNoteBean(noteBean);
        view.setData(model);
    }

    @Override
    public void setView(NoteSingleFormView view) {
        this.view = view;
    }

    @Override
    public void save(NoteSingleFormModel model) {
        fillFromModel(model);

        noteService.save(noteBean, new AsyncCallback<NoteBean>() {

            @Override
            public void onFailure(Throwable caught) {
                Log.log(Level.SEVERE, "Can not save note.", caught);
                view.showError(caught.getMessage());
            }

            @Override
            public void onSuccess(NoteBean result) {
                setData(result);
                view.dataSaved();
            }
        });
    }

    private void fillFromModel(NoteSingleFormModel model) {
        noteBean.setTypeId(model.getTypeId());
        noteBean.setDate(model.getDate());
        noteBean.setNote(model.getNote());
        noteBean.setComment(model.getComment());
        noteBean.setHours(model.getHours());
    }

    @Override
    public void load(int noteId) {
        noteBean = null;

        noteService.getById(noteId, new AsyncCallback<NoteBean>() {

            @Override
            public void onFailure(Throwable caught) {
                Log.log(Level.SEVERE, "Can not load note by id.", caught);
                view.showError(caught.getMessage());
            }

            @Override
            public void onSuccess(NoteBean result) {
                setData(result);
            }
        });
    }

    @Override
    public void add(Date date) {
        NoteBean newBean = new NoteBean();
        newBean.setId(-1);
        newBean.setDate(date);
        setData(newBean);
    }
}
