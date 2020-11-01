package ru.mativ.client.form.notes.single;

import java.util.Date;

public interface NoteSingleFormModel {

    public Integer getTypeId();

    public String getNote();

    public String getComment();

    public Date getDate();

    public Integer getHours();
}
