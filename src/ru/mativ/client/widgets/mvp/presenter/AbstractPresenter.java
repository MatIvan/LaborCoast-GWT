package ru.mativ.client.widgets.mvp.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

import ru.mativ.client.widgets.mvp.view.ViewInterface;

public abstract class AbstractPresenter<T extends ViewInterface<?>> implements PresenterInterface {

    protected T form;

    public AbstractPresenter(T form) {
        this.form = form;
        bind();
    }

    protected abstract void bind();

    @Override
    public void go(HasWidgets container) {
        container.add(form.asWidget());
    }
}
