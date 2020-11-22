package ru.mativ.shared.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class NoteCalendarDay implements Serializable {
    private static final long serialVersionUID = -401352685959718532L;

    private Date date;
    private List<NoteBean> noteList;
    private int hoursSumm;
    private int dayOfWeek;
    private int weekOfMonth;
    private int dayOfMonth;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<NoteBean> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<NoteBean> noteList) {
        this.noteList = noteList;
    }

    public int getHoursSumm() {
        return hoursSumm;
    }

    public void setHoursSumm(int hoursSumm) {
        this.hoursSumm = hoursSumm;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getWeekOfMonth() {
        return weekOfMonth;
    }

    public void setWeekOfMonth(int weekOfMonth) {
        this.weekOfMonth = weekOfMonth;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public void increaseHoursSumm(int hours) {
        hoursSumm += hours;
    }

    @Override
    public String toString() {
        return "NoteCalendarDay [date=" + date + ", noteList=" + noteList + ", hoursSumm=" + hoursSumm + ", dayOfWeek=" + dayOfWeek + ", weekOfMonth="
                + weekOfMonth + ", dayOfMonth=" + dayOfMonth + "]";
    }
}
