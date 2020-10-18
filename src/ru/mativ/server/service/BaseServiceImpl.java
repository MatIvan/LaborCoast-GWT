package ru.mativ.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ru.mativ.client.service.exception.LoginFialException;
import ru.mativ.server.session.UserSession;
import ru.mativ.shared.UserDto;

@SuppressWarnings("serial")
public class BaseServiceImpl extends RemoteServiceServlet {

    protected UserDto getCurrentUser() throws LoginFialException {
        UserDto userDto;
        try {
            UserSession session = (UserSession) getThreadLocalRequest().getUserPrincipal();
            userDto = session.getUserDto();
        } catch (Exception e) {
            throw new LoginFialException("Bad user.");
        }
        return userDto;
    }
}
