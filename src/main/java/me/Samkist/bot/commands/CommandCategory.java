package me.Samkist.bot.commands;

public enum CommandCategory {
    ADMIN("Admin", "You will find all administrative commands here"),
    UNKNOWN("Unknown", "Unknown command type."),
    MAIN("Main", "These are the miscellaneous commands functional to the bot."),
    FUN("Fun", "Just for fun.");
    private final String description;
    private final String name;

    CommandCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
