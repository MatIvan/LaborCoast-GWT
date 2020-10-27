package ru.mativ.server.session;

import java.util.concurrent.ConcurrentHashMap;

import javax.naming.AuthenticationException;

import ru.mativ.shared.bean.UserBean;

public class SessionController {
    private final static SessionController instance = new SessionController();

    private static final long TIMEOUT = 5 * 60 * 1000;//5 minutes

    // <Token, Session>
    private ConcurrentHashMap<String, UserSession> sessionMap = new ConcurrentHashMap<>();

    private SessionController() {
    }

    public static SessionController instance() {
        return instance;
    }

    public void updateUserSession(UserSession userSession) throws AuthenticationException {
        String token = userSession.getToken();
        String ip = userSession.getIp();

        if (token == null || ip == null) {
            throw new AuthenticationException("Token or Ip is empty.");
        }

        UserSession session = sessionMap.get(token);
        if (session == null) {
            throw new AuthenticationException("Unknown session.");
        }

        if (!ip.equals(session.getIp())) {
            throw new AuthenticationException("This token have another Ip.");
        }

        long time = System.currentTimeMillis();
        if (time > session.getLastTime() + TIMEOUT) {
            sessionMap.remove(token);
            throw new AuthenticationException("Session timeout.");
        }

        userSession.setUserBean(session.getUserBean());
    }

    public UserSession registerUser(String token, String ip, UserBean userBean) {
        long time = System.currentTimeMillis();
        UserSession session = new UserSession(ip, token, time);
        session.setUserBean(userBean);
        sessionMap.put(token, session);
        return session;
    }

}
