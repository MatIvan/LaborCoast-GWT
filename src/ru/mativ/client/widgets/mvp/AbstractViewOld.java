package ru.mativ.client.widgets.mvp;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractViewOld<M> extends Composite implements ViewOld {

    private AbstractPresenterOld<M> presenter;

    public AbstractViewOld(AbstractPresenterOld<M> presenter) {
        super();
        this.presenter = presenter;
        presenter.addView(this);

        init();
        build();
        initWidget(getMainPanel());
    }

    protected abstract Widget getMainPanel();

    protected abstract void init();

    protected abstract void build();

    protected abstract M makeModel();

    public abstract void fill(M model);

    public abstract void showError(String errorText);

    protected void notifyAboutModelChanged() {
        presenter.onDataChanged(makeModel());
    }

    @Override
    public void loadData(Object context) {
        presenter.loadData(context);
    }
}
