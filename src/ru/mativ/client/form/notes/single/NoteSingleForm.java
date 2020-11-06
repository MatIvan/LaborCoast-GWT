package ru.mativ.client.form.notes.single;

import com.google.gwt.event.dom.client.ClickHandler;

import ru.mativ.client.form.notes.single.impl.NoteSingleViewModel;
import ru.mativ.client.widgets.mvp.view.ViewInterface;

public interface NoteSingleForm extends ViewInterface<NoteSingleViewModel> {

    void setErrorMessage(String message);

    void addSaveBtnClickHandler(ClickHandler clickHandler);

    void addCloseBtnClickHandler(ClickHandler clickHandler);
}
