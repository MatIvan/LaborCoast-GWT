package ru.mativ.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ru.mativ.client.service.exception.LoginFialException;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {

    String makeToken(String login, String pass) throws LoginFialException;

}
