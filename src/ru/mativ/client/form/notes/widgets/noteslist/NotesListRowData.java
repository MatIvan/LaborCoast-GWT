package ru.mativ.client.form.notes.widgets.noteslist;

public class NotesListRowData {
    private int rowNumber;
    private String type;
    private String note;
    private String comment;
    private int hours;
    private int noteId;

    public NotesListRowData(int rowNumber, String type, String note, String comment, int hours, int noteId) {
        super();
        this.rowNumber = rowNumber;
        this.type = type;
        this.note = note;
        this.comment = comment;
        this.hours = hours;
        this.noteId = noteId;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
}
