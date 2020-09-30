package ru.mativ.shared;

import java.io.Serializable;

public class UserSessionDto implements Serializable {
    private static final long serialVersionUID = -7216531938368604129L;

    private String token;
    private UserDto userDto;

    public UserSessionDto() {
    }

    public UserSessionDto(String token, UserDto userDto) {
        this.token = token;
        this.userDto = userDto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public String toString() {
        return "UserSessionDto [token=" + token + ", userDto=" + userDto + "]";
    }

}
