package ru.mativ.client.form.login.impl;

public class LoginFormModel {

    private String login;
    private String password;

    public LoginFormModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
