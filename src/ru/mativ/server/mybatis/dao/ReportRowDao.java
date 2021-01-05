package ru.mativ.server.mybatis.dao;

public class ReportRowDao {
    private int typeId;
    private String note;
    private String comment;
    private int hours_sum;

    public ReportRowDao() {
        // empty constructor
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
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

    public int getHours_sum() {
        return hours_sum;
    }

    public void setHours_sum(int hours_sum) {
        this.hours_sum = hours_sum;
    }

    @Override
    public String toString() {
        return "ReportDao [typeId=" + typeId + ", note=" + note + ", comment=" + comment + ", hours_sum=" + hours_sum + "]";
    }
}
