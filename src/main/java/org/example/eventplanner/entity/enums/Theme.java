package org.example.eventplanner.entity.enums;

public enum Theme {
    BTG("Black Tie Gala"), FB("Futuristic Banquet"), _8RN("80s Retro Night"), MB("Masquerade Ball");

    String theme;

    Theme(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }
}
