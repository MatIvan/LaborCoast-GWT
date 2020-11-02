package ru.mativ.shared.bean;

import java.io.Serializable;
import java.sql.Date;

public class NoteBean implements Serializable {
    private static final long serialVersionUID = 3670593746358236347L;

    private Integer id;
    private Integer userId;
    private Integer typeId;
    private String note;
    private String comment;
    private Date date;
    private Integer hours;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(java.util.Date date) {
        this.date = new java.sql.Date(date.getTime());
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "NoteBean [id=" + id + ", userId=" + userId + ", typeId=" + typeId + ", note=" + note + ", comment=" + comment + ", date=" + date + ", hours="
                + hours + "]";
    }
}
