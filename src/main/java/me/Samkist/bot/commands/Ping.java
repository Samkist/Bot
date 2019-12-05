package me.Samkist.bot.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class Ping extends Command {

    String command;
    String[] aliases;
    String usage;

    public Ping() {
        command = "ping";
    }

    @Override
    public String getCommand() {
        return null;
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public void execute(String[] args, MessageChannel channel, User author, Message message) {

    }
}
