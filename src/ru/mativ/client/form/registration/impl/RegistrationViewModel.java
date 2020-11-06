package ru.mativ.client.form.registration.impl;

public class RegistrationViewModel {

    private String login;
    private String name;
    private String mail;
    private String pass;
    private String passConfirm;

    public RegistrationViewModel(String login, String name, String mail, String pass, String passConfirm) {
        this.login = login;
        this.name = name;
        this.mail = mail;
        this.pass = pass;
        this.passConfirm = passConfirm;
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

    public String getPassConfirm() {
        return passConfirm;
    }

    public boolean hasEmptyFields() {
        return (login == null
                || name == null
                || mail == null
                || pass == null
                || passConfirm == null
                || login.isEmpty()
                || name.isEmpty()
                || mail.isEmpty()
                || pass.isEmpty()
                || passConfirm.isEmpty());
    }

    public boolean isValid() {
        if (hasEmptyFields()) {
            return false;
        }
        return pass.equals(passConfirm);
    }
}
