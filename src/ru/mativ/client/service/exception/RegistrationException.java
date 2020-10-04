package ru.mativ.client.service.exception;

import java.io.Serializable;

public class RegistrationException extends Exception implements Serializable {
    private static final long serialVersionUID = 3964403408735084843L;

    public RegistrationException() {
        super();
    }

    public RegistrationException(String message) {
        super(message);
    }

}
