package ru.mativ.client.form.notes.calendar;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.widgets.mvp.view.FlowView;
import ru.mativ.shared.bean.NoteBean;
import ru.mativ.shared.bean.NoteCalendarDay;

public class NoteDayCalendarWidget extends FlowView<NoteCalendarDay> {
    private static final String STYLE = "NoteDayCalendarWidget";
    private static final String STYLE_WATER_LABEL = STYLE + "-waterLabel";

    private static final int MAX_TEXT_LENGTH = 8;
    private static final DateTimeFormat DATE_FORMAT = DateTimeFormat.getFormat("dd/mm/yyyy");

    private NoteCalendarDay noteCalendarDay;
    private String waterLabelText;

    public NoteDayCalendarWidget(String waterLabelText) {
        this(waterLabelText, null);
    }

    public NoteDayCalendarWidget(String waterLabelText, NoteCalendarDay value) {
        super();
        this.waterLabelText = waterLabelText;
        setStyleName(STYLE);
        setValue(value);
    }

    @Override
    public NoteCalendarDay getValue() {
        // do nothing. read only
        return null;
    }

    @Override
    public void setValue(NoteCalendarDay value, boolean fireEvents) {
        noteCalendarDay = value;
        build();
    }

    @Override
    protected void init() {
        // do nothing
    }

    @Override
    protected void build() {
        clear();
        addField(makeWaterLabel());

        if (noteCalendarDay == null) {
            return;
        }

        addField(makeCaptionWidget());
        for (NoteBean noteBean : noteCalendarDay.getNoteList()) {
            addField(makeRowWidget(noteBean));
        }
    }

    private Widget makeWaterLabel() {
        Label waterLabel = new Label(waterLabelText);
        SimplePanel panel = new SimplePanel();
        panel.addStyleName(STYLE_WATER_LABEL);
        panel.add(waterLabel);
        return panel;
    }

    private Widget makeCaptionWidget() {
        //TODO make normal css style

        String hoursSumm = String.valueOf(noteCalendarDay.getHoursSumm());
        String text = DATE_FORMAT.format(noteCalendarDay.getDate());

        Label lHours = new Label(hoursSumm);
        lHours.getElement().getStyle().setFontWeight(FontWeight.BOLD);

        Label lDate = new Label(text);
        lDate.getElement().getStyle().setFontWeight(FontWeight.BOLD);

        FlowPanel rowPanel = new FlowPanel();
        rowPanel.getElement().getStyle().setBackgroundColor("grey");
        rowPanel.add(lDate);
        rowPanel.add(lHours);

        return rowPanel;
    }

    private Widget makeRowWidget(NoteBean noteBean) {
        String hours = noteBean.getHours().toString();
        String text = noteBean.getNote().substring(0, MAX_TEXT_LENGTH);

        FlowPanel rowPanel = new FlowPanel();
        rowPanel.add(new Label(text));
        rowPanel.add(new Label(hours));

        return rowPanel;
    }
}
