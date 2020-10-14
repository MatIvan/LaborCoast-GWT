package ru.mativ.client.form.login;

public interface LoginFormPresenter {

    void addView(LoginFormView view);

    void onLoginBtnClicked(LoginFormModel model);

}
