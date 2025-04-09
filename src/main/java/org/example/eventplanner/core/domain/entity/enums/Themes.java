package org.example.eventplanner.core.domain.entity.enums;

public enum Themes {
    BTG("Black Tie Gala"), FB("Futuristic Banquet"), _8RN("80s Retro Night"), MB("Masquerade Ball");

    String theme;

    Themes(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }
}
