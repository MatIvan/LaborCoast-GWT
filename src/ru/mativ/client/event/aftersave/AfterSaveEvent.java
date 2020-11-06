package ru.mativ.client.event.aftersave;

import com.google.gwt.event.shared.GwtEvent;

public class AfterSaveEvent<T> extends GwtEvent<AfterSaveHandler<T>> {

    private static Type<AfterSaveHandler<?>> TYPE;

    public static <T> void fire(HasAfterSaveHandlers<T> source, T value) {
        if (TYPE != null) {
            AfterSaveEvent<T> event = new AfterSaveEvent<T>(value);
            source.fireEvent(event);
        }
    }

    public static Type<AfterSaveHandler<?>> getType() {
        if (TYPE == null) {
            TYPE = new Type<AfterSaveHandler<?>>();
        }
        return TYPE;
    }

    private final T value;

    protected AfterSaveEvent(T value) {
        this.value = value;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public final Type<AfterSaveHandler<T>> getAssociatedType() {
        return (Type) TYPE;
    }

    public T getValue() {
        return value;
    }

    @Override
    protected void dispatch(AfterSaveHandler<T> handler) {
        handler.onAfterSave(this);
    }
}
