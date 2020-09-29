package ru.mativ.server.filter;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import ru.mativ.server.session.UserSession;
import ru.mativ.shared.HeaderName;

public class UserRequestWrapper extends HttpServletRequestWrapper {

    private Principal user;

    public UserRequestWrapper(HttpServletRequest request) {
        super(request);
        user = new UserSession(
                request.getRemoteAddr(),
                request.getHeader(HeaderName.TOKEN),
                System.currentTimeMillis());
    }

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

}
