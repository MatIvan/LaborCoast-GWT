package ru.mativ.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ru.mativ.server.session.UserSession;
import ru.mativ.shared.bean.UserBean;
import ru.mativ.shared.exception.LoginFialException;

@SuppressWarnings("serial")
public class BaseServiceImpl extends RemoteServiceServlet {

    protected UserBean getCurrentUser() throws LoginFialException {
        try {
            UserSession session = (UserSession) getThreadLocalRequest().getUserPrincipal();
            return session.getUserBean();
        } catch (Exception e) {
            throw new LoginFialException("Bad user.");
        }
    }
}
