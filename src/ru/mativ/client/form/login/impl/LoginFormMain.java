package ru.mativ.client.form.login.impl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.event.navigation.NavigationTarget;
import ru.mativ.client.fabrica.NavigateButtonsFabrica;
import ru.mativ.client.form.login.LoginForm;
import ru.mativ.client.widgets.HLabeledPanel;
import ru.mativ.client.widgets.mvp.AbstractView;

public class LoginFormMain extends AbstractView<LoginFormModel> implements LoginForm {

    private VerticalPanel mainPanel;
    private TextBox login;
    private PasswordTextBox pass;
    private Label messageLabel;
    private Button sendBtn;
    private Button registrBtn;

    public LoginFormMain() {
        super(new LoginFormPresenter());
    }

    @Override
    protected Widget getMainPanel() {
        return mainPanel;
    }

    @Override
    protected void init() {
        mainPanel = new VerticalPanel();
        mainPanel.setSpacing(10);

        login = new TextBox();
        pass = new PasswordTextBox();
        messageLabel = new Label();

        sendBtn = new Button("Sing in");
        sendBtn.addClickHandler(getSendBtnHandler());

        registrBtn = NavigateButtonsFabrica.createButton(NavigationTarget.REGISTRATION);
    }

    @Override
    protected void build() {
        mainPanel.add(messageLabel);
        mainPanel.add(new HLabeledPanel("Login:", login));
        mainPanel.add(new HLabeledPanel("Pass:", pass));
        mainPanel.add(sendBtn);
        mainPanel.add(registrBtn);
    }

    private ClickHandler getSendBtnHandler() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                notifyAboutModelChanged();
            }
        };
    }

    @Override
    protected LoginFormModel makeModel() {
        return new LoginFormModel(login.getValue(), pass.getValue());
    }

    @Override
    public void fill(LoginFormModel model) {
        login.setValue(model.getLogin());
        pass.setValue(model.getPassword());
    }

    @Override
    public void showError(String errorText) {
        messageLabel.setText(errorText);
    }

}
