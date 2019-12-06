package me.Samkist.bot.commands.fun;

import me.Samkist.bot.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Ping extends Command {

    private String command;
    private String[] aliases;
    private String usage;

    public Ping() {
        command = "ping";
        aliases = new String[]{};
        usage = "Retrieves the ping of the shard";
    }

    private Color getColorByPing(long ping) {
        if (ping < 100)
            return Color.cyan;
        if (ping < 400)
            return Color.green;
        if (ping < 700)
            return Color.yellow;
        if (ping < 1000)
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
    public void execute(String[] args, MessageReceivedEvent event) {
        MessageBuilder msgBuilder = new MessageBuilder("**Pinging...**");
        Message message = msgBuilder.sendTo(event.getTextChannel()).complete();
        long ping = event.getJDA().getGatewayPing();
        message.editMessage("\u200B").completeAfter(200, TimeUnit.MILLISECONDS);
        message.editMessage(new EmbedBuilder().setColor(getColorByPing(ping)).setDescription(
                ":ping_pong: **Pong!**\n\nThe ping of your shard is `" + ping + "ms`").build()).queue();
    }
}
