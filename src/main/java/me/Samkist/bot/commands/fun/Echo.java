package me.Samkist.bot.commands.fun;

import me.Samkist.bot.commands.Command;
import me.Samkist.bot.commands.CommandCategory;
import me.Samkist.bot.commands.meta.CommandArgument;
import me.Samkist.bot.utils.Config;
import me.Samkist.bot.utils.Embeds;
import me.Samkist.bot.utils.Templates;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Echo extends Command {


    private Logger logger = LoggerFactory.getLogger(Echo.class);
    private String command;
    private String[] aliases;
    private String usage;
    private String description;
    private CommandCategory commandCategory;

    public Echo() {
        command = "echo";
        aliases = new String[]{"repeat"};
        usage = "`<message>`";
        description = "This command will echo what is entered.";
        commandCategory = CommandCategory.FUN;
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

    private void execute(String[] args, MessageChannel channel, User author, Message message) {
        try {
            if(args[0] == null)
                System.out.println("");
        } catch(Exception e) {
            channel.sendMessage(
                    Embeds.getRedEmbed(Templates.getUsageMessage(this), "Echo Usage").build()
            ).queue();
            return;
        }
        StringBuilder builder = new StringBuilder();
        for(String s : args) {
            builder.append(s).append(" ");
        }

        channel.sendMessage(Embeds.getDefaultEmbed(builder.toString(), "Echo").build()).queue();
    }

    @Override
    public void execute(String[] args, MessageReceivedEvent event) {
        execute(args, event.getChannel(), event.getAuthor(), event.getMessage());
    }
}
