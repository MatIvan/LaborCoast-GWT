package ru.mativ.client.form.notes.single;

import java.util.Date;

import ru.mativ.client.event.aftersave.HasAfterSaveHandlers;
import ru.mativ.client.widgets.mvp.presenter.PresenterInterface;
import ru.mativ.shared.bean.NoteBean;

public interface NoteSingleFormPresenter extends PresenterInterface, HasAfterSaveHandlers<NoteBean> {

    void load(int noteId);

    void addNew(Date date);
}
