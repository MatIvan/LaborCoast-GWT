package ru.mativ.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ru.mativ.shared.bean.UserBean;
import ru.mativ.shared.bean.UserSessionBean;
import ru.mativ.shared.exception.LoginFialException;
import ru.mativ.shared.exception.RegistrationException;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {

    UserSessionBean makeToken(String login, String pass) throws LoginFialException;

    Void newUser(UserBean user, String pass) throws RegistrationException;

}
