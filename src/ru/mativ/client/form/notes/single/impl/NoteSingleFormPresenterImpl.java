package ru.mativ.client.form.notes.single.impl;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.event.aftersave.AfterSaveEvent;
import ru.mativ.client.event.aftersave.AfterSaveHandler;
import ru.mativ.client.form.notes.single.NoteSingleForm;
import ru.mativ.client.form.notes.single.NoteSingleFormPresenter;
import ru.mativ.client.service.proxy.NoteServiceProxy;
import ru.mativ.client.widgets.mvp.presenter.AbstractPresenter;
import ru.mativ.shared.bean.NoteBean;
import ru.mativ.shared.bean.NoteTypeBean;

public class NoteSingleFormPresenterImpl extends AbstractPresenter<NoteSingleForm> implements NoteSingleFormPresenter {
    private static final Logger Log = Logger.getLogger(NoteSingleFormPresenterImpl.class.getName());
    private static final NoteServiceProxy noteService = LaborCoast.getNoteServiceProxy();

    private NoteBean noteBean;

    public NoteSingleFormPresenterImpl(NoteSingleForm form) {
        super(form);
    }

    @Override
    public HandlerRegistration addAfterSaveHandler(AfterSaveHandler<NoteBean> handler) {
        return eventBus.addHandler(AfterSaveEvent.getType(), handler);
    }

    @Override
    public void load(int noteId) {
        noteBean = null;
        noteService.getById(noteId, new AsyncCallback<NoteBean>() {

            @Override
            public void onFailure(Throwable caught) {
                Log.log(Level.SEVERE, "Can not load note by id.", caught);
                form.setErrorMessage(caught.getMessage());
            }

            @Override
            public void onSuccess(NoteBean result) {
                setData(result);
            }
        });
    }

    @Override
    public void addNew(Date date) {
        NoteBean newBean = new NoteBean();
        newBean.setId(-1);
        newBean.setDate(date);
        newBean.setTypeId(NoteTypeBean.DEFAULT_TYPE_ID);
        setData(newBean);
    }

    @Override
    protected void bind() {
        form.addSaveBtnClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                save(form.getValue());
            }
        });
    }

    private void setData(NoteBean bean) {
        noteBean = bean;
        NoteSingleViewModel model = NoteSingleViewModel.makeModel(noteBean);
        form.setValue(model);
    }

    private void save(NoteSingleViewModel model) {
        fillFromModel(model);

        noteService.save(noteBean, new AsyncCallback<NoteBean>() {

            @Override
            public void onFailure(Throwable caught) {
                Log.log(Level.SEVERE, "Can not save note.", caught);
                form.setErrorMessage(caught.getMessage());
            }

            @Override
            public void onSuccess(NoteBean result) {
                setData(result);
                AfterSaveEvent.fire(NoteSingleFormPresenterImpl.this, result);
            }
        });
    }

    private void fillFromModel(NoteSingleViewModel model) {
        noteBean.setTypeId(model.getTypeId());
        noteBean.setDate(model.getDate());
        noteBean.setNote(model.getNote());
        noteBean.setComment(model.getComment());
        noteBean.setHours(model.getHours());
    }
}
