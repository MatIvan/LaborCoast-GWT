package ru.mativ.client.form.login.impl;

import ru.mativ.client.form.login.LoginFormModel;

public class LoginFormModelDefault implements LoginFormModel {

    private String login;
    private String password;

    public LoginFormModelDefault(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
