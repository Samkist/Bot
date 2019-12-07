package me.Samkist.bot.commands.util;

import me.Samkist.bot.commands.Command;
import me.Samkist.bot.commands.CommandCategory;
import me.Samkist.bot.main.Launcher;
import me.Samkist.bot.utils.Config;
import me.Samkist.bot.utils.Embeds;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class Help extends Command {

    private String command;
    private String[] aliases;
    private String usage;
    private CommandCategory commandCategory;

    public Help() {
        command = "help";
        aliases = new String[]{"helpme"};
        usage = "Returns a list of command categories, that can be opened for more commands";
        commandCategory = CommandCategory.MAIN;
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
        if(args == null) {
            ArrayList<CommandCategory> commandCategories = new ArrayList<>();
            Launcher.getCommandHandler().getCommands().forEach(command -> {
                if (!commandCategories.contains(command.getCommandCategory()))
                    commandCategories.add(command.getCommandCategory());
            });
            StringBuilder builder = new StringBuilder();
            commandCategories.forEach(commandCategory1 -> {
                builder.append("`")
                        .append(Config.getPrefix())
                        .append("help ")
                        .append(commandCategory1.getName())
                        .append("` - ")
                        .append(commandCategory1.getDescription())
                        .append("\n");
            });
            builder.append("`!help` - ").append(new Help().getUsage());
            event.getTextChannel().sendMessage(Embeds.getDefaultEmbed(builder.toString(), "Help").build()).queue();
        } else {
            ArrayList<CommandCategory> commandCategories = new ArrayList<>();
            Launcher.getCommandHandler().getCommands().forEach(command -> {
                if (!commandCategories.contains(command.getCommandCategory()))
                    commandCategories.add(command.getCommandCategory());
            });
            Optional<CommandCategory> result = commandCategories.stream().filter(category -> category.getName().equalsIgnoreCase(args[0])).findFirst();
            ArrayList<Command> commands = new ArrayList<>();
            Launcher.getCommandHandler().getCommands().forEach(command -> {
                if(result.isPresent() && command.getCommandCategory().equals(result.get()))
                    commands.add(command);
            });
            if(!result.isPresent() || commands.size() == 0)
                return;
            StringBuilder builder = new StringBuilder();
            commands.forEach(command1 -> builder.append("`")
                    .append(Config.getPrefix())
                    .append(command1.getCommand())
                    .append("` - ")
                    .append(command1.getUsage())
                    .append("\n"));
            builder.append("`")
                    .append(Config.getPrefix())
                     .append("help ")
                    .append(args[0])
                    .append(" ` - ")
                    .append(commands.get(0).getCommandCategory().getDescription());
            event.getTextChannel().sendMessage(Embeds.getDefaultEmbed(builder.toString(), "Help " + commands.get(0).getCommandCategory().getName()).build()).queue();
        }
    }
}
