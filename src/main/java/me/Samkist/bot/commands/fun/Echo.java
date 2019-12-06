package me.Samkist.bot.commands.fun;

import me.Samkist.bot.commands.Command;
import me.Samkist.bot.utils.Embeds;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Echo extends Command {


    private Logger logger = LoggerFactory.getLogger(Echo.class);
    private String command;
    private String[] aliases;
    private String usage;

    public Echo() {
        command = "echo";
        aliases = new String[]{"repeat"};
        usage = "This command will echo what is entered.";
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

    private void execute(String[] args, MessageChannel channel, User author, Message message) {
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
