package ru.mativ.shared;

import java.io.Serializable;

public class UserDto implements Serializable {
    private static final long serialVersionUID = 7801510757661880639L;

    private String login;
    private String name;
    private String mail;

    public UserDto(String login, String name, String mail) {
        super();
        this.login = login;
        this.name = name;
        this.mail = mail;
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
        return "UserDTO [login=" + login + ", name=" + name + ", mail=" + mail + "]";
    }

}
