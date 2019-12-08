package me.Samkist.bot.commands.meta;

import me.Samkist.bot.commands.Command;
import me.Samkist.bot.utils.Config;

import java.util.PriorityQueue;
import java.util.Queue;

public class CommandArgument {
    private String argument;
    private String usage;
    private Command parentCommand;
    private Queue<CommandArgument> parentArguments = new PriorityQueue<>();

    public CommandArgument(String argument, String usage, Command parentCommand) {
        this.argument = argument;
        this.usage = usage;
        this.parentCommand = parentCommand;
    }

    public CommandArgument(String argument, String usage, Command parentCommand, CommandArgument parentArgument) {
        this.argument = argument;
        this.usage = usage;
        this.parentCommand = parentCommand;
        parentArguments = parentArgument.getParentArguments();
        parentArguments.add(parentArgument);
    }

    public String infoToString() {
        if(!parentArguments.isEmpty()) {
            StringBuilder builder = new StringBuilder("`" + Config.getPrefix() + parentCommand.getCommand() + " ");
            parentArguments.forEach(argument -> {
                builder.append(argument.getArgument()).append(" ");
            });
            builder.append(getArgument()).append(" ").append(getUsage());
            return builder.toString();
        } else {
            return "`" + Config.getPrefix() + parentCommand.getCommand() + " " + getArgument() + "` - " + getUsage();
        }
    }

    public String getArgument() {
        return argument;
    }

    public String getUsage() {
        return usage;
    }

    public Queue<CommandArgument> getParentArguments() {
        return parentArguments;
    }
}
