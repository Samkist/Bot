package me.Samkist.bot.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public abstract class Command {
    public Command() {

    }

    public abstract String getCommand();

    public abstract String[] getAliases();

    public abstract String getUsage();

    public abstract void execute(String[] args, MessageChannel channel, User author, Message message);
}
