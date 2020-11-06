package ru.mativ.client.event.aftersave;

import com.google.gwt.event.shared.EventHandler;

public interface AfterSaveHandler<T> extends EventHandler {
    void onAfterSave(AfterSaveEvent<T> event);
}
