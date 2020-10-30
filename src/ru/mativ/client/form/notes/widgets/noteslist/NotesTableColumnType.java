package ru.mativ.client.form.notes.widgets.noteslist;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;

public enum NotesTableColumnType {

    ROW("#", "30px", Column.ALIGN_LEFT) {
        @Override
        protected Cell<String> getStringCell() {
            return new AbstractCell<String>() {
                @Override
                public void render(Context context, String value, SafeHtmlBuilder sb) {
                    sb.append(context.getIndex() + 1);
                }
            };
        }

        @Override
        protected String getCellValue(NotesListRowData object) {
            return null;
        }
    },

    NOTE("Note", "", Column.ALIGN_LEFT) {
        @Override
        protected String getCellValue(NotesListRowData object) {
            return object.getNote();
        }
    },

    COMMENT("Comment", "300px", Column.ALIGN_LEFT) {
        @Override
        protected String getCellValue(NotesListRowData object) {
            return object.getComment();
        }
    },

    HOURS("Hours", "40px", Column.ALIGN_CENTER) {
        @Override
        protected String getCellValue(NotesListRowData object) {
            return String.valueOf(object.getHours());
        }
    },

    EDIT_BTN("edit", "40px", Column.ALIGN_CENTER),

    DELETE_BTN("del", "40px", Column.ALIGN_CENTER);

    private HorizontalAlignmentConstant align;
    private String caption;
    private String width;

    public String getCaption() {
        return caption;
    }

    public String getWidth() {
        return width;
    }

    protected String getCellValue(NotesListRowData object) {
        return null;
    }

    protected Cell<String> getStringCell() {
        return new TextCell();
    }

    protected Column<NotesListRowData, String> createTextColumn() {
        Cell<String> cell = getStringCell();

        Column<NotesListRowData, String> tableColumn = new Column<NotesListRowData, String>(cell) {
            @Override
            public String getValue(NotesListRowData object) {
                return getCellValue(object);
            }
        };

        tableColumn.setHorizontalAlignment(align);
        return tableColumn;
    }

    protected Column<NotesListRowData, ?> createActionColumn(Delegate<NotesListRowData> delegate) {
        ActionCell<NotesListRowData> cell = new ActionCell<NotesListRowData>(caption, delegate);

        Column<NotesListRowData, NotesListRowData> tableColumn = new Column<NotesListRowData, NotesListRowData>(cell) {
            @Override
            public NotesListRowData getValue(NotesListRowData object) {
                return object;
            }
        };

        tableColumn.setHorizontalAlignment(align);
        return tableColumn;
    }

    public Column<NotesListRowData, ?> createColumn(Delegate<NotesListRowData> delegate) {
        return (delegate == null) ? createTextColumn() : createActionColumn(delegate);
    }

    NotesTableColumnType(String caption, String width, HorizontalAlignmentConstant align) {
        this.caption = caption;
        this.width = width;
        this.align = align;
    }
}
