package ru.mativ.client.form.notes.widgets.notetype;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.service.proxy.NoteTypeServiceProxy;
import ru.mativ.client.widgets.HasValueComposite;
import ru.mativ.shared.bean.NoteTypeBean;

public class NoteTypeWidget extends HasValueComposite<Integer> {
    private static final Logger Log = Logger.getLogger(NoteTypeWidget.class.getName());
    private static final NoteTypeServiceProxy noteTypeService = LaborCoast.getNoteTypeServiceProxy();

    private HorizontalPanel mainPanel;

    private List<NoteTypeBean> list = new ArrayList<>();
    private Integer noteTypeId;

    public NoteTypeWidget() {
        super();
    }

    public void loadData() {
        list.clear();

        noteTypeService.getAll(new AsyncCallback<List<NoteTypeBean>>() {

            @Override
            public void onSuccess(List<NoteTypeBean> result) {
                list = result;
                build();
            }

            @Override
            public void onFailure(Throwable caught) {
                Log.fine("Can not load Note Types: " + caught.getMessage());
                build();
            }
        });
    }

    @Override
    public Integer getValue() {
        return noteTypeId;
    }

    @Override
    public void setValue(Integer value, boolean fireEvents) {
        noteTypeId = value;
        if (fireEvents) {
            notifyAboutValueChanged();
        }
        build();
    }

    @Override
    protected void init() {
        mainPanel = new HorizontalPanel();
        mainPanel.setSpacing(10);
    }

    @Override
    protected void build() {
        mainPanel.clear();
        if (list == null || list.isEmpty()) {
            mainPanel.add(new Label("Can not load note types."));
            return;
        }

        for (NoteTypeBean bean : list) {
            mainPanel.add(createElement(bean));
        }

    }

    private Widget createElement(NoteTypeBean bean) {
        final Integer id = bean.getId();
        RadioButton btn = new RadioButton("NoteTypeWidget", bean.getName());
        btn.setValue(Objects.equals(noteTypeId, bean.getId()));
        btn.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                if (event.getValue()) {
                    setValue(id, true);
                }
            }
        });
        return btn;
    }

    @Override
    protected Widget getMainPanel() {
        return mainPanel;
    }

    public NoteTypeBean getNoteTypeBean() {
        Integer id = getValue();
        for (NoteTypeBean bean : list) {
            if (id.equals(bean.getId())) {
                return bean;
            }
        }
        return null;
    }
}
