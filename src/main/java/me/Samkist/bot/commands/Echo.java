package me.Samkist.bot.commands;

import me.Samkist.bot.main.Launcher;
import me.Samkist.bot.utils.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

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

    @Override
    public void execute(String[] args, MessageChannel channel, User author, Message message) {
        StringBuilder builder = new StringBuilder();
        for(String s : args) {
            builder.append(s).append(" ");
        }
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setDescription(builder.toString());
        embedBuilder.setColor(new Color(0, 255, 128));
        embedBuilder.setAuthor("Echo", null, "https://cdn.discordapp.com/avatars/133231388538306560/3da005a023dd4de2332fc07d901745b8.png");
        embedBuilder.setFooter("Discord Bot | Samkist");
        channel.sendMessage(embedBuilder.build()).queue();
    }
}
