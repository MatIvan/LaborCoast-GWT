package ru.mativ.client.form.notes.widgets.noteslist;

public class NotesListRowData {
    private String note;
    private String comment;
    private int hours;
    private int noteId;

    public NotesListRowData(String note, String comment, int hours, int noteId) {
        super();
        this.note = note;
        this.comment = comment;
        this.hours = hours;
        this.noteId = noteId;
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
