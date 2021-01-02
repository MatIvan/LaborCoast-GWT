package ru.mativ.client.form.notes.calendar.selectable;

import ru.mativ.client.form.notes.calendar.NoteDayCalendarWidget;
import ru.mativ.shared.bean.NoteCalendarDay;

public class NoteDayCalendarWidgetSelectable extends NoteDayCalendarWidget {
    private static final String STYLE = "NoteDayCalendarWidgetSelectable";
    private boolean isSelected;

    public NoteDayCalendarWidgetSelectable(String waterLabelText, NoteCalendarDay value) {
        super(waterLabelText, value);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        updateStyle();
    }

    private void updateStyle() {
        this.removeStyleName(STYLE);
        if (isSelected) {
            this.addStyleName(STYLE);
        }
    }
}
