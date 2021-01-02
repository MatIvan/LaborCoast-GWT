package ru.mativ.client.form.notes.calendar.selectable;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;

import ru.mativ.client.form.notes.calendar.NoteDayCalendarWidget;
import ru.mativ.shared.bean.NoteCalendarDay;

public class NoteDayCalendarWidgetSelectable extends NoteDayCalendarWidget implements HasClickHandlers {
    private static final String STYLE = "NoteDayCalendarWidgetSelectable";
    private static final String STYLE_SELECTED = STYLE + "-selected";

    private boolean isSelected;

    public NoteDayCalendarWidgetSelectable(String waterLabelText, NoteCalendarDay value) {
        super(waterLabelText, value);
        isSelected = false;
        this.addStyleName(STYLE);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        updateStyle();
    }

    private void updateStyle() {
        this.removeStyleName(STYLE_SELECTED);
        if (isSelected) {
            this.addStyleName(STYLE_SELECTED);
        }
    }

    @Override
    public NoteCalendarDay getValue() {
        return noteCalendarDay;
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return this.addDomHandler(handler, ClickEvent.getType());
    }
}
