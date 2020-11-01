package ru.mativ.client.form.notes.single;

import java.util.Date;

public interface NoteSingleFormPresenter {

    void setView(NoteSingleFormView view);

    void load(int noteId);

    void add(Date date);

    void save(NoteSingleFormModel model);
}
