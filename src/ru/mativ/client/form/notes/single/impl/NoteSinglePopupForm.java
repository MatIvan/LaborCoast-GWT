package ru.mativ.client.form.notes.single.impl;

import java.util.Date;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasWidgets;

import ru.mativ.client.event.aftersave.AfterSaveHandler;
import ru.mativ.client.form.notes.single.NoteSingleFormPresenter;
import ru.mativ.shared.bean.NoteBean;

public class NoteSinglePopupForm extends NoteSingleFormImpl implements NoteSingleFormPresenter {

    private DialogBox box;
    private NoteSingleFormPresenter presenter;

    public NoteSinglePopupForm() {
        presenter = new NoteSingleFormPresenterImpl(this);

        box = new DialogBox();
        box.setGlassEnabled(true);
        box.setAnimationEnabled(true);
        box.setWidget(this);
    }

    public void show() {
        box.center();
        box.show();
    }

    public void hide() {
        box.hide();
    }

    @Override
    public void go(HasWidgets container) {
        // do nothing
    }

    @Override
    public HandlerRegistration addAfterSaveHandler(AfterSaveHandler<NoteBean> handler) {
        return presenter.addAfterSaveHandler(handler);
    }

    @Override
    public void load(int noteId) {
        presenter.load(noteId);
    }

    @Override
    public void addNew(Date date) {
        presenter.addNew(date);
    }
}
