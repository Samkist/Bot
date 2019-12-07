package me.Samkist.bot.commands;

import me.Samkist.bot.utils.Config;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CommandHandler extends ListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(CommandHandler.class);
    private ArrayList<Command> commands = new ArrayList<>();
    private String prefix = Config.getPrefix();


    public CommandHandler addCommand(Command command) {
        if(!commands.contains(command))
            commands.add(command);
        return this;
    }

    public CommandHandler addCommands(Command... addedCommands) {
        for(Command command : addedCommands) {
            if(!commands.contains(command))
                commands.add(command);
        }
        return this;
    }

    public CommandHandler removeCommand(Command command) {
        commands.remove(command);
        return this;
    }

    public CommandHandler removeCommands(Command... removedCommands) {
        commands.removeAll(Arrays.asList(removedCommands));
        return this;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public ArrayList<Command> getCommandByType(Command command) {
        ArrayList<Command> results = new ArrayList<>();
        commands.forEach(command1 -> {
            if(command1.getClass().equals(command.getClass().asSubclass(command1.getClass())))
                results.add(command1);
        });
        return results;
    }

    public ArrayList<Command> getCommandsByCategory(CommandCategory commandCategory) {
        ArrayList<Command> results = new ArrayList<>();
        commands.forEach(command -> {
            if(command.getCommandCategory().equals(commandCategory))
                results.add(command);
        });
        return results;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().length() == 0)
            return;
        if(!event.isFromType(ChannelType.TEXT))
            return;
        if (!Character.toString(event.getMessage().getContentRaw().charAt(0)).equalsIgnoreCase(prefix))
            return;

        String[] msg = event.getMessage().getContentRaw().substring(1).split("\\s+", 2);
        String command = msg[0];

        String[] args = msg.length > 1 ? msg[1].split("\\s+") : null;
        try {
            Optional<Command> result = commands.stream().filter(command1 -> command1.getCommand().equalsIgnoreCase(command)).findFirst();
            result.ifPresent(value -> {
                value.execute(args, event);
            });

        } catch(NoSuchElementException ignored) {

        }
    }
}
