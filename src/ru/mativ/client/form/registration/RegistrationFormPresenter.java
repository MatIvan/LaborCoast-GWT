package ru.mativ.client.form.registration;

public interface RegistrationFormPresenter {

    void addView(RegistrationFormView view);

    void onRegistrBtnClicked(RegistrationFormModel model);

}
