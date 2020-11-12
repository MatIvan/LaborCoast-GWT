package ru.mativ.client.form.notes.day;

import ru.mativ.client.form.notes.day.impl.NoteDayFormModel;
import ru.mativ.client.widgets.mvp.view.ViewInterface;

public interface NoteDayForm extends ViewInterface<NoteDayFormModel> {
    void setErrorMessage(String message);
}
