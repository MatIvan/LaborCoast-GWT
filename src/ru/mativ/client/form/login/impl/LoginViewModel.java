package ru.mativ.client.form.login.impl;

public class LoginViewModel {

    private String login;
    private String pass;

    public LoginViewModel(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
}
