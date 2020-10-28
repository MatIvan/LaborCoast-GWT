package ru.mativ.client.form.notes.day.impl;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.form.notes.day.NoteDayFormPresenter;
import ru.mativ.client.form.notes.day.NoteDayFormView;

public class NoteDayFormViewDefault extends Composite implements NoteDayFormView {

    private NoteDayFormPresenter presenter;

    public NoteDayFormViewDefault() {
        this(new NoteDayFormPresenterDefault());
    }

    public NoteDayFormViewDefault(NoteDayFormPresenter presenter) {
        this.presenter = presenter;
        presenter.addView(this);
        initWidget(buildGui());
    }

    private Widget buildGui() {
        return new Label("NoteDayFormViewDefault");
    }
}
