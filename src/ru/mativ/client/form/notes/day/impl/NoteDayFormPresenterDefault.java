package ru.mativ.client.form.notes.day.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.form.notes.day.NoteDayFormPresenter;
import ru.mativ.client.form.notes.day.NoteDayFormView;
import ru.mativ.client.service.proxy.NoteServiceProxy;

public class NoteDayFormPresenterDefault implements NoteDayFormPresenter {
    private static final NoteServiceProxy noteService = LaborCoast.getNoteServiceProxy();
    private static final EventBus globalBus = LaborCoast.getEventBus();

    private List<NoteDayFormView> viewList = new ArrayList<>();

    @Override
    public void addView(NoteDayFormView view) {
        viewList.add(view);
    }
}
