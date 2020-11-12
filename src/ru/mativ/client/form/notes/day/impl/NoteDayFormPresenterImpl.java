package ru.mativ.client.form.notes.day.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.form.notes.day.NoteDayForm;
import ru.mativ.client.form.notes.day.NoteDayFormPresenter;
import ru.mativ.client.service.proxy.NoteServiceProxy;
import ru.mativ.client.widgets.mvp.presenter.AbstractPresenter;
import ru.mativ.shared.bean.NoteBean;

public class NoteDayFormPresenterImpl extends AbstractPresenter<NoteDayForm> implements NoteDayFormPresenter {
    private static final Logger Log = Logger.getLogger(NoteDayFormPresenterImpl.class.getName());
    private static final NoteServiceProxy noteService = LaborCoast.getNoteServiceProxy();

    public NoteDayFormPresenterImpl(NoteDayForm form) {
        super(form);
    }

    @Override
    public void update(final Date date) {
        noteService.getByDate(date, new AsyncCallback<List<NoteBean>>() {

            @Override
            public void onFailure(Throwable caught) {
                final String message = "Can not load notes by day: " + date;
                Log.log(Level.SEVERE, message, caught);
                form.setErrorMessage(message);
            }

            @Override
            public void onSuccess(List<NoteBean> result) {
                NoteDayFormModel model = new NoteDayFormModel(date, result);
                form.setValue(model);
            }
        });
    }

    @Override
    protected void bind() {
        form.addValueChangeHandler(new ValueChangeHandler<NoteDayFormModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<NoteDayFormModel> event) {
                update(event.getValue().getDate());
            }
        });
    }
}
