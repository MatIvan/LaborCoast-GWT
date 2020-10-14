package ru.mativ.client.form.registration.impl;

import ru.mativ.client.form.registration.RegistrationFormModel;

public class RegistrationFormModelDefault implements RegistrationFormModel {

    private String login;
    private String name;
    private String mail;
    private String pass;

    public RegistrationFormModelDefault(String login, String name, String mail, String pass) {
        this.login = login;
        this.name = name;
        this.mail = mail;
        this.pass = pass;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public String getPass() {
        return pass;
    }
}
