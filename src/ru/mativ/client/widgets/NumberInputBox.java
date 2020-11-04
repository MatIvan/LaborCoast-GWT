package ru.mativ.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;

public class NumberInputBox extends HasValueComposite<Integer> {

    private IntegerBox valueBox;
    private Button decBtn;
    private Button incBtn;

    public NumberInputBox() {
        super(new HorizontalPanel());
    }

    private void initValueBox() {
        valueBox = new IntegerBox();
        valueBox.setValue(0);
        valueBox.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                correctValue();
            }
        });

        valueBox.addValueChangeHandler(new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange(ValueChangeEvent<Integer> event) {
                correctValue();
                fireValueChangeEvent();
            }
        });
    }

    private void correctValue() {
        if (valueBox.getValue() != null) {
            return;
        }
        String val = valueBox.getText();
        val = val.replaceAll("\\D", "");
        valueBox.setText(val);
    }

    private void initButtons() {
        decBtn = new Button("<");
        decBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                setValue(getValue() - 1, true);
            }
        });

        incBtn = new Button(">");
        incBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                setValue(getValue() + 1, true);
            }
        });
    }

    @Override
    public Integer getValue() {
        Integer result = valueBox.getValue();
        return result == null ? 0 : result;
    }

    @Override
    public void setValue(Integer value, boolean fireEvents) {
        valueBox.setValue(value, fireEvents);
    }

    @Override
    protected void init() {
        initValueBox();
        initButtons();
    }

    @Override
    protected void build() {
        getMainPanel().add(decBtn);
        getMainPanel().add(valueBox);
        getMainPanel().add(incBtn);
    }
}
