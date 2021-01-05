package ru.mativ.shared.bean;

import java.io.Serializable;

public class ReportRowBean implements Serializable {
    private static final long serialVersionUID = -9001280456909797168L;

    private Integer typeId;
    private String note;
    private String comment;
    private Integer hoursSum;

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

    public Integer getHoursSum() {
        return hoursSum;
    }

    public void setHoursSum(Integer hoursSum) {
        this.hoursSum = hoursSum;
    }

    @Override
    public String toString() {
        return "ReportBean [typeId=" + typeId + ", note=" + note + ", comment=" + comment + ", hoursSum=" + hoursSum + "]";
    }
}
