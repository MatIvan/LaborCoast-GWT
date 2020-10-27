package ru.mativ.shared.bean;

import java.io.Serializable;

public class UserSessionBean implements Serializable {
    private static final long serialVersionUID = -7216531938368604129L;

    private String token;
    private UserBean userBean;

    public UserSessionBean() {
    }

    public UserSessionBean(String token, UserBean userBean) {
        this.token = token;
        this.userBean = userBean;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    @Override
    public String toString() {
        return "UserSessionBean [token=" + token + ", userBean=" + userBean + "]";
    }

}
