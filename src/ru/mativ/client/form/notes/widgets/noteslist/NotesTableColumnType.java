package ru.mativ.client.form.notes.widgets.noteslist;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;

public enum NotesTableColumnType {

    ROW("#", "30px") {
        @Override
        public Column<NotesListRowData, ?> createColumn() {
            Cell<Number> cell = new AbstractCell<Number>() {
                @Override
                public void render(Context context, Number value, SafeHtmlBuilder sb) {
                    sb.append(context.getIndex() + 1);
                }
            };
            return new Column<NotesListRowData, Number>(cell) {
                @Override
                public Integer getValue(NotesListRowData object) {
                    return null;
                }
            };
        }
    },

    NOTE("Note", "") {
        @Override
        public Column<NotesListRowData, ?> createColumn() {
            return new TextColumn<NotesListRowData>() {
                @Override
                public String getValue(NotesListRowData object) {
                    return object.getNote();
                }
            };
        }
    },

    COMMENT("Comment", "300px") {
        @Override
        public Column<NotesListRowData, ?> createColumn() {
            return new TextColumn<NotesListRowData>() {
                @Override
                public String getValue(NotesListRowData object) {
                    return object.getComment();
                }
            };
        }
    },

    HOURS("Hours", "40px") {
        @Override
        public Column<NotesListRowData, ?> createColumn() {
            NumberCell numberCell = new NumberCell();
            Column<NotesListRowData, ?> tableColumn = new Column<NotesListRowData, Number>(numberCell) {
                @Override
                public Integer getValue(NotesListRowData object) {
                    return object.getHours();
                }
            };
            tableColumn.setHorizontalAlignment(Column.ALIGN_CENTER);
            return tableColumn;
        }
    };

    private String caption;
    private String width;

    public String getCaption() {
        return caption;
    }

    public String getWidth() {
        return width;
    }

    public Column<NotesListRowData, ?> createColumn() {
        return null;
    }

    NotesTableColumnType(String caption, String width) {
        this.caption = caption;
        this.width = width;
    }
}
