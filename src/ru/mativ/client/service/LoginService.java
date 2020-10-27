package ru.mativ.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ru.mativ.client.service.exception.LoginFialException;
import ru.mativ.client.service.exception.RegistrationException;
import ru.mativ.shared.bean.UserBean;
import ru.mativ.shared.bean.UserSessionBean;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {

    UserSessionBean makeToken(String login, String pass) throws LoginFialException;

    Void newUser(UserBean user, String pass) throws RegistrationException;

}
