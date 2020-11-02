package ru.mativ.shared.exception;

import java.io.Serializable;

public class DataSaveException extends Exception implements Serializable {
    private static final long serialVersionUID = 5000368175226540950L;

    public DataSaveException() {
        super();
    }

    public DataSaveException(String message) {
        super(message);
    }
}
