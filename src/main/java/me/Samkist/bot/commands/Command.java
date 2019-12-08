package me.Samkist.bot.commands;

import me.Samkist.bot.commands.meta.CommandArgument;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public abstract class Command {
    public Command() {

    }

    public abstract String getCommand();

    public abstract String[] getAliases();

    public abstract String getUsage();

    public abstract CommandCategory getCommandCategory();

    public abstract ArrayList<CommandArgument> getCommandArguments();

    public abstract String getDescription();

    public abstract void execute(String[] args, MessageReceivedEvent event);
}
