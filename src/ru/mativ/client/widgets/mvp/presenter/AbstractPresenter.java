package ru.mativ.client.widgets.mvp.presenter;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;

import ru.mativ.client.widgets.mvp.view.ViewInterface;

public abstract class AbstractPresenter<T extends ViewInterface<?>> implements PresenterInterface, HasHandlers {

    protected T form;
    protected SimpleEventBus eventBus;

    public AbstractPresenter(T form) {
        this.form = form;
        eventBus = new SimpleEventBus();
        bind();
    }

    protected abstract void bind();

    @Override
    public void go(HasWidgets container) {
        container.add(form.asWidget());
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }
}
