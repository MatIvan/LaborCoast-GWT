package ru.mativ.client.event.navigation;

public enum NavigationTarget {
    NULL(""),
    HOME("Home"),
    LOGIN("Login"),
    REGISTRATION("Registration"),
    LOGOFF("Exit"),
    NOTE_DAY("Day notes");

    private String name;

    NavigationTarget(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
