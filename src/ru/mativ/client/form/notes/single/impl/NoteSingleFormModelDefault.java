package ru.mativ.client.form.notes.single.impl;

import java.util.Date;

import ru.mativ.client.form.notes.single.NoteSingleFormModel;
import ru.mativ.shared.bean.NoteBean;

public class NoteSingleFormModelDefault implements NoteSingleFormModel {

    private Integer typeId;
    private String note;
    private String comment;
    private Date date;
    private Integer hours;

    public NoteSingleFormModelDefault(Integer typeId, String note, String comment, Date date, Integer hours) {
        super();
        this.typeId = typeId;
        this.note = note;
        this.comment = comment;
        this.date = date;
        this.hours = hours;
    }

    @Override
    public Integer getTypeId() {
        return typeId;
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Integer getHours() {
        return hours;
    }

    public static NoteSingleFormModelDefault makeFromNoteBean(NoteBean noteBean) {
        return new NoteSingleFormModelDefault(
                noteBean.getTypeId(),
                noteBean.getNote(),
                noteBean.getComment(),
                noteBean.getDate(),
                noteBean.getHours());
    }
}
