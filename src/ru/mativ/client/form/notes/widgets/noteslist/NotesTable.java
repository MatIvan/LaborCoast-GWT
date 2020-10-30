package ru.mativ.client.form.notes.widgets.noteslist;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class NotesTable extends Composite {
    private static final Logger Log = Logger.getLogger(NotesTable.class.getName());

    private static final String TABLE_WIDTH = "800px";

    private CellTable<NotesListRowData> table;

    public NotesTable() {
        table = new CellTable<NotesListRowData>();
        table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
        table.setSelectionModel(makeSelectionModel());
        table.setWidth(TABLE_WIDTH);

        addColumn(NotesTableColumnType.ROW);
        addColumn(NotesTableColumnType.NOTE);
        addColumn(NotesTableColumnType.COMMENT);
        addColumn(NotesTableColumnType.HOURS);

        initWidget(table);
    }

    public void setData(List<NotesListRowData> rows) {
        table.setRowData(rows);
    }

    private void addColumn(NotesTableColumnType column) {
        Column<NotesListRowData, ?> tableColumn = column.createColumn();
        table.addColumn(tableColumn, column.getCaption());
        table.setColumnWidth(tableColumn, column.getWidth());
    }

    private SingleSelectionModel<NotesListRowData> makeSelectionModel() {
        final SingleSelectionModel<NotesListRowData> selectionModel = new SingleSelectionModel<NotesListRowData>();

        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                NotesListRowData selected = selectionModel.getSelectedObject();
                if (selected != null) {
                    Log.info("You selected id: " + selected.getNoteId());
                }
            }
        });
        return selectionModel;
    }
}
