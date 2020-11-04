package ru.mativ.tools.mvp;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPresenter<M> {

    private List<AbstractView<M>> viewList = new ArrayList<>();

    public AbstractPresenter() {
        super();
    }

    public abstract void onDataChanged(M model);

    public abstract void loadData(Object context);

    protected abstract M makeModel();

    public void addView(AbstractView<M> view) {
        viewList.add(view);
    }

    protected void fillAllView() {
        M model = makeModel();
        for (AbstractView<M> view : viewList) {
            view.fill(model);
        }
    }

    protected void showErrorAllView(String errorText) {
        for (AbstractView<M> view : viewList) {
            view.showError(errorText);
        }
    }
}
