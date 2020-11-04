package ru.mativ.client.widgets.mvp;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPresenterOld<M> {

    private List<AbstractViewOld<M>> viewList = new ArrayList<>();

    public AbstractPresenterOld() {
        super();
    }

    public abstract void onDataChanged(M model);

    public abstract void loadData(Object context);

    protected abstract M makeModel();

    public void addView(AbstractViewOld<M> view) {
        viewList.add(view);
    }

    protected void fillAllView() {
        M model = makeModel();
        for (AbstractViewOld<M> view : viewList) {
            view.fill(model);
        }
    }

    protected void showErrorAllView(String errorText) {
        for (AbstractViewOld<M> view : viewList) {
            view.showError(errorText);
        }
    }
}
