package me.Samkist.bot.commands.fun;

import me.Samkist.bot.commands.Command;
import me.Samkist.bot.commands.CommandCategory;
import me.Samkist.bot.commands.meta.CommandArgument;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Ping extends Command {

    private String command;
    private String[] aliases;
    private String usage;
    private CommandCategory commandCategory;
    private String description;

    public Ping() {
        command = "ping";
        aliases = new String[]{};
        description = "Retrieves the ping of the shard";
        usage = "`<message>`";
        commandCategory = CommandCategory.FUN;
    }

    private Color getColorByPing(long ping) {
        if (ping < 100)
            return Color.green;
        if (ping < 400)
            return Color.yellow;
        if (ping < 700)
            return Color.orange;
        return Color.red;
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
        MessageBuilder msgBuilder = new MessageBuilder("**Pinging...**");
        Message message = msgBuilder.sendTo(event.getTextChannel()).complete();
        long ping = event.getJDA().getGatewayPing();
        message.editMessage("\u200B").completeAfter(200, TimeUnit.MILLISECONDS);
        message.editMessage(new EmbedBuilder().setColor(getColorByPing(ping)).setDescription(
                ":ping_pong:`" + ping + "ms`").build()).queue();
    }
}
