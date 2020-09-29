package ru.mativ.client.service.exception;

import java.io.Serializable;

public class LoginFialException extends Exception implements Serializable {
    private static final long serialVersionUID = 1699524107930422612L;

    public LoginFialException() {
        super();
    }

    public LoginFialException(String message) {
        super(message);
    }

}
