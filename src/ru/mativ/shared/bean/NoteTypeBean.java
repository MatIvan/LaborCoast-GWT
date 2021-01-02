package ru.mativ.shared.bean;

import java.io.Serializable;

public class NoteTypeBean implements Serializable {
    private static final long serialVersionUID = 5797290186704078500L;
    public static final Integer DEFAULT_TYPE_ID = 1;

    private Integer id;
    private String name;

    public NoteTypeBean() {
        super();
    }

    public NoteTypeBean(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
