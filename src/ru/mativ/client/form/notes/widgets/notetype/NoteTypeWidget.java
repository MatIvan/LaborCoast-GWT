package ru.mativ.client.form.notes.widgets.notetype;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.service.proxy.NoteTypeServiceProxy;
import ru.mativ.client.widgets.mvp.view.HorizontalView;
import ru.mativ.shared.bean.NoteTypeBean;

public class NoteTypeWidget extends HorizontalView<Integer> {
    private static final Logger Log = Logger.getLogger(NoteTypeWidget.class.getName());
    private static final NoteTypeServiceProxy noteTypeService = LaborCoast.getNoteTypeServiceProxy();

    private List<NoteTypeBean> list = new ArrayList<>();
    private Integer noteTypeId;

    public NoteTypeWidget() {
        super();
    }

    @Override
    public Integer getValue() {
        return noteTypeId;
    }

    @Override
    public void setValue(Integer value, boolean fireEvents) {
        noteTypeId = value;
        if (list == null || list.isEmpty()) {
            this.clear();
            loadData();
        }
        build();
        if (fireEvents) {
            fireValueChangeEvent();
        }
    }

    @Override
    protected void init() {
        // do nothing
    }

    @Override
    protected void build() {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.clear();
        for (NoteTypeBean bean : list) {
            addField(createElement(bean));
        }
    }

    private void showError(String message) {
        this.clear();
        addField(new Label(message));
    }

    private Widget createElement(NoteTypeBean bean) {
        final Integer id = bean.getId();
        RadioButton btn = new RadioButton(NoteTypeWidget.class.getName(), bean.getName());
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

    private void loadData() {
        showError("Loading types...");
        list.clear();
        noteTypeService.getAll(new AsyncCallback<List<NoteTypeBean>>() {

            @Override
            public void onSuccess(List<NoteTypeBean> result) {
                list = result;
                build();
            }

            @Override
            public void onFailure(Throwable caught) {
                caught.printStackTrace();
                String msg = "Can not load Note Types.";
                Log.log(Level.SEVERE, msg, caught);
                showError(msg);
            }
        });
    }

    public String getValueName() {
        Integer id = getValue();
        for (NoteTypeBean bean : list) {
            if (id.equals(bean.getId())) {
                return bean.getName();
            }
        }
        return null;
    }
}
