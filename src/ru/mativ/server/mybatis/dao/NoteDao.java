package ru.mativ.server.mybatis.dao;

public class NoteDao {

    private int id;
    private int userId;
    private int typeId;
    private String note;
    private String comment;
    private String date;
    private int hours;

    public NoteDao() {
        id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id == null ? -1 : id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId == null ? -1 : userId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId == null ? -1 : typeId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours == null ? -1 : hours;
    }

    @Override
    public String toString() {
        return "NoteDao [id=" + id + ", userId=" + userId + ", typeId=" + typeId + ", note=" + note + ", comment=" + comment + ", date=" + date + ", hours="
                + hours + "]";
    }
}
