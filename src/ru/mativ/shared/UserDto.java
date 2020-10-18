package ru.mativ.shared;

import java.io.Serializable;

public class UserDto implements Serializable {
    private static final long serialVersionUID = 7801510757661880639L;

    private Integer id;
    private String login;
    private String name;
    private String mail;

    public UserDto() {
    }

    public UserDto(Integer id, String login, String name, String mail) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.mail = mail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "UserDto [id=" + id + ", login=" + login + ", name=" + name + ", mail=" + mail + "]";
    }

}
