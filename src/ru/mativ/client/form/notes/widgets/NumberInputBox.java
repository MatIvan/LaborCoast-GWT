package ru.mativ.client.form.notes.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;

public class NumberInputBox extends Composite implements HasValue<Integer>, HandlerRegistration {

    private HorizontalPanel mainPanel;
    private IntegerBox valueBox;
    private Button decBtn;
    private Button incBtn;

    private ValueChangeHandler<Integer> handler;

    public NumberInputBox() {
        initGui();
        buildGui();
        initWidget(mainPanel);
    }

    private void initGui() {
        mainPanel = new HorizontalPanel();
        initValueBox();
        initButtons();
    }

    private void initValueBox() {
        valueBox = new IntegerBox();
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
                if (handler != null) {
                    handler.onValueChange(event);
                }
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
                setValue(getValue() - 1);
            }
        });

        incBtn = new Button(">");
        incBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                setValue(getValue() + 1);
            }
        });
    }

    private void buildGui() {
        mainPanel.add(decBtn);
        mainPanel.add(valueBox);
        mainPanel.add(incBtn);
    }

    public void setValueChangeHandler(ValueChangeHandler<Integer> handler) {
        this.handler = handler;
    }

    @Override
    public Integer getValue() {
        Integer result = valueBox.getValue();
        return result == null ? 0 : result;
    }

    @Override
    public void setValue(Integer value) {
        setValue(value, false);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler) {
        this.handler = handler;
        return this;
    }

    @Override
    public void setValue(Integer value, boolean fireEvents) {
        valueBox.setValue(value, fireEvents);
    }

    @Override
    public void removeHandler() {
        this.handler = null;
    }
}
