package me.Samkist.bot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {
    public Command() {

    }

    public abstract String getCommand();

    public abstract String[] getAliases();

    public abstract String getUsage();

    public abstract void execute(String[] args, MessageReceivedEvent event);
}
