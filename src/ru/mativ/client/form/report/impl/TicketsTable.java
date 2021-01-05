package ru.mativ.client.form.report.impl;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

import ru.mativ.shared.bean.ReportRowBean;

public class TicketsTable extends Composite {

    private static enum ColumnType {
        NUM(0, "#", "-num"),
        TICKET(1, "Ticket", "-ticket"),
        COMMENT(2, "Comment", "-comment"),
        HOURS(3, "Hours", "-hours");

        int column;
        String headText;
        String style;

        ColumnType(int column, String headText, String style) {
            this.column = column;
            this.headText = headText;
            this.style = style;
        }
    }

    private static final String STYLE = "TicketsTable";
    private static final String STYLE_HEAD = STYLE + "-head";
    private static final String STYLE_CELL = STYLE + "-cell";

    private FlexTable table;

    public TicketsTable() {
        table = new FlexTable();
        table.addStyleName(STYLE);
        clear();
        initWidget(table);
    }

    private void initHead() {
        setHead(ColumnType.NUM);
        setHead(ColumnType.TICKET);
        setHead(ColumnType.COMMENT);
        setHead(ColumnType.HOURS);
    }

    private void setHead(ColumnType colType) {
        Label label = new Label(colType.headText);
        label.addStyleName(STYLE_HEAD + colType.style);
        table.setWidget(0, colType.column, label);
    }

    private void setCell(int row, String text, ColumnType colType) {
        Label label = new Label(text);
        label.addStyleName(STYLE_CELL + colType.style);
        table.setWidget(row, colType.column, label);
    }

    public void clear() {
        table.clear();
        initHead();
    }

    public void fill(List<ReportRowBean> tickets) {
        clear();
        for (int i = 0; i < tickets.size(); i++) {
            ReportRowBean bean = tickets.get(i);
            setCell(i + 1, String.valueOf(i + 1), ColumnType.NUM);
            setCell(i + 1, bean.getNote(), ColumnType.TICKET);
            setCell(i + 1, bean.getComment(), ColumnType.COMMENT);
            setCell(i + 1, bean.getHoursSum().toString(), ColumnType.HOURS);
        }
    }
}
