package ru.mativ.server.repository.pojo;

public class UserDao {

    private int id;
    private String login;
    private String name;
    private String mail;
    private String password;

    public UserDao(String login, String name, String mail, String password) {
        super();
        this.login = login;
        this.name = name;
        this.mail = mail;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDao [id=" + id + ", login=" + login + ", name=" + name + ", mail=" + mail + ", password=" + password + "]";
    }

}
