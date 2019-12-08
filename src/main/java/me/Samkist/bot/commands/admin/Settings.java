package me.Samkist.bot.commands.admin;

import me.Samkist.bot.commands.Command;
import me.Samkist.bot.commands.CommandCategory;
import me.Samkist.bot.commands.meta.CommandArgument;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

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
    public ArrayList<CommandArgument> getCommandArguments() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute(String[] args, MessageReceivedEvent event) {
        try {
            if(args[0] == null)
                System.out.println(" ");
        } catch (Exception e) {

            return;
        }
        System.out.println(" ");
    }
}
