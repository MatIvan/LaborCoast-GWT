package ru.mativ.client.form.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.service.LoginServiceAsync;
import ru.mativ.client.service.RequestService;
import ru.mativ.shared.NullCommand;

public class LoginForm extends Composite {
    private static final LoginServiceAsync loginService = LaborCoast.getLoginService();
    private static final RequestService requestService = RequestService.getInstance();

    private Command onLoginSuccess = new NullCommand();

    private TextBox login;
    private PasswordTextBox pass;
    private Label resultMessage;

    public LoginForm(Command onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess == null ? new NullCommand() : onLoginSuccess;
        initWidget(buildGui());
    }

    private Widget buildGui() {
        login = new TextBox();
        pass = new PasswordTextBox();
        resultMessage = new Label();

        Label loginLabel = new Label("Login:");
        Label passLabel = new Label("Password:");

        Button sendBtn = new Button("Login");
        sendBtn.addClickHandler(getSendBtnHandler());

        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(resultMessage);
        mainPanel.add(loginLabel);
        mainPanel.add(login);
        mainPanel.add(passLabel);
        mainPanel.add(pass);
        mainPanel.add(sendBtn);

        return mainPanel;
    }

    private ClickHandler getSendBtnHandler() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                requestService.send(loginService.makeToken(login.getValue(), pass.getValue(), getLoginCallBack()));
            }
        };
    }

    private AsyncCallback<String> getLoginCallBack() {
        return new AsyncCallback<String>() {

            @Override
            public void onFailure(Throwable caught) {
                requestService.setToken(null);
                resultMessage.setText("Logging is Failure: " + caught.getMessage());
            }

            @Override
            public void onSuccess(String result) {
                if (result == null || result.isEmpty()) {
                    requestService.setToken(null);
                    resultMessage.setText("Logging is Failure: empty token.");
                    return;
                }
                requestService.setToken(result);
                resultMessage.setText("Logging is Success.");
                onLoginSuccess.execute();
            }
        };
    }
}
