package ru.mativ.client.widgets.mvp;

import com.google.gwt.user.client.ui.IsWidget;

public interface View extends IsWidget {
    void loadData(Object context);
}
