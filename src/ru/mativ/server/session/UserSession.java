package ru.mativ.server.session;

import java.security.Principal;

import ru.mativ.shared.bean.UserBean;

public class UserSession implements Principal {

    private String ip;
    private String token;
    private Long lastTime;
    private UserBean userBean;

    public UserSession(String ip, String token, Long lastTime) {
        super();
        this.ip = ip;
        this.token = token;
        this.lastTime = lastTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLastTime() {
        return lastTime;
    }

    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    @Override
    public String toString() {
        return "UserSession [ip=" + ip + ", token=" + token + ", lastTime=" + lastTime + ", userBean=" + userBean + "]";
    }

    @Override
    public String getName() {
        return token;
    }

}
