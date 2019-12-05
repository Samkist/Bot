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
    private Logger logger = LoggerFactory.getLogger("CommandHandler");
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

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(!event.isFromType(ChannelType.TEXT))
            return;
        if(!Character.toString(event.getMessage().toString().charAt(0)).equalsIgnoreCase(Config.getPrefix()))
            return;
        String msg = event.getMessage().toString().substring(1);
        String command = msg.split(" ")[0];
        String[] args = new String[msg.split(" ").length-1];
        for(int i = 0; i < args.length; i++) {
            args[i] = msg.split(" ")[i+1];
        }
        try {
            commands.stream().filter(command1 -> command1.getCommand().equals(command)).findFirst()
                    .get().execute(args, event.getChannel(), event.getAuthor(), event.getMessage());
        } catch(NoSuchElementException ignored) {

        }

    }
}
