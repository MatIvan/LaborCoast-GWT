package ru.mativ.tools.mvp;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractView<M> extends Composite implements View {

    private AbstractPresenter<M> presenter;

    public AbstractView(AbstractPresenter<M> presenter) {
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
