package ru.mativ.tools.log;

import java.util.logging.ConsoleHandler;

public class ConsoleHandlerOut extends ConsoleHandler {

    public ConsoleHandlerOut() {
        super();
        setOutputStream(System.out);
    }
}
