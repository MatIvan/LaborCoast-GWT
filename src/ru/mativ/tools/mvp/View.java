package ru.mativ.tools.mvp;

import com.google.gwt.user.client.ui.IsWidget;

public interface View extends IsWidget {
    void loadData(Object context);
}
