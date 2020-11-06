package ru.mativ.client.form.notes.single.impl;

import java.util.Date;

import ru.mativ.shared.bean.NoteBean;

public class NoteSingleViewModel {

    private Integer typeId;
    private String note;
    private String comment;
    private Date date;
    private Integer hours;

    public NoteSingleViewModel(Integer typeId, String note, String comment, Date date, Integer hours) {
        super();
        this.typeId = typeId;
        this.note = note;
        this.comment = comment;
        this.date = date;
        this.hours = hours;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getNote() {
        return note;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

    public Integer getHours() {
        return hours;
    }

    public static NoteSingleViewModel makeModel(NoteBean noteBean) {
        return new NoteSingleViewModel(
                noteBean.getTypeId(),
                noteBean.getNote(),
                noteBean.getComment(),
                noteBean.getDate(),
                noteBean.getHours());
    }
}
