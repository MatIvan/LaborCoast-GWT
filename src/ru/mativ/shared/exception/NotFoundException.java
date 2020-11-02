package ru.mativ.shared.exception;

import java.io.Serializable;

public class NotFoundException extends Exception implements Serializable {
    private static final long serialVersionUID = -4945466867801196594L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
