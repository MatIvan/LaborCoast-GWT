package ru.mativ.client.event.navigation;

public enum NavigationTarget {
    NULL("", false),
    HOME("Home", false),
    LOGIN("Login", true),
    REGISTRATION("Registration", true),
    LOGOFF("Exit", false),
    NOTE_DAY("Day notes", false),
    NOTE_MONTH("Month", false),
    WORKSPACE("Workspace", false);

    private String name;
    private boolean isAllowToUnonim;

    NavigationTarget(String name, boolean isAllowToUnonim) {
        this.name = name;
        this.isAllowToUnonim = isAllowToUnonim;
    }

    public String getName() {
        return name;
    }

    public boolean isAllowToUnonim() {
        return isAllowToUnonim;
    }
}
