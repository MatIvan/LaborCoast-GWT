package ru.mativ.client.form.registration;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.service.proxy.LoginServiceProxy;
import ru.mativ.shared.UserDto;

public class RegistrationForm {
    private static final LoginServiceProxy loginService = LaborCoast.getLoginServiceProxy();

    private RegistrationFormView view;
    private Command onRigistrationSuccess;

    public RegistrationForm(RegistrationFormView view) {
        this.view = view;
        view.setHandler(getRegistrationFormViewHandler());
    }

    private RegistrationFormViewHandler getRegistrationFormViewHandler() {
        return new RegistrationFormViewHandler() {
            @Override
            public void onRegistrBtnClicked(RegistrationFormModel model) {
                loginService.newUser(
                        makeUserDto(model),
                        model.getPass(),
                        getRegistrCallBack());
            }

        };
    }

    private AsyncCallback<Void> getRegistrCallBack() {
        return new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                view.setMessage("Registration is Failure: " + caught.getMessage());
            }

            @Override
            public void onSuccess(Void result) {
                view.setMessage("Logging is success.");
                if (onRigistrationSuccess != null) {
                    onRigistrationSuccess.execute();
                }
            }
        };
    }

    private UserDto makeUserDto(RegistrationFormModel model) {
        return new UserDto(
                model.getLogin(),
                model.getName(),
                model.getMail());
    }

    public Widget asWidget() {
        return view.asWidget();
    }

    public void setOnRigistrationSuccessCommand(Command onLoginSuccess) {
        this.onRigistrationSuccess = onLoginSuccess;
    }

}
