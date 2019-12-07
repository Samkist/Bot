package me.Samkist.bot.commands.admin;

import me.Samkist.bot.commands.Command;
import me.Samkist.bot.commands.CommandCategory;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Settings extends Command {

    private String command;
    private String[] aliases;
    private String usage;
    private CommandCategory commandCategory;

    public Settings() {
        command = "settings";
        aliases = null;
        usage = "Alters bot settings.";
        commandCategory = CommandCategory.ADMIN;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String[] getAliases() {
        return aliases;
    }

    @Override
    public String getUsage() {
        return usage;
    }

    @Override
    public CommandCategory getCommandCategory() {
        return commandCategory;
    }

    @Override
    public void execute(String[] args, MessageReceivedEvent event) {

    }
}
