package ru.mativ.client.event.aftersave;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasAfterSaveHandlers<T> extends HasHandlers {
    HandlerRegistration addAfterSaveHandler(AfterSaveHandler<T> handler);
}
