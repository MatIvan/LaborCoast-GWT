package ru.mativ.client.form.registration;

public class RegistrationFormModel {

    private String login;
    private String name;
    private String mail;
    private String pass;

    public RegistrationFormModel(String login, String name, String mail, String pass) {
        this.login = login;
        this.name = name;
        this.mail = mail;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPass() {
        return pass;
    }

}
