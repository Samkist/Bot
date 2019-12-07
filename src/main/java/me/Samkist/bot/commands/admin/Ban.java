package me.Samkist.bot.commands.admin;

import me.Samkist.bot.commands.Command;
import me.Samkist.bot.commands.CommandCategory;
import me.Samkist.bot.utils.Embeds;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.utils.PermissionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class Ban extends ModerationCommand {

    private static Logger logger = LoggerFactory.getLogger(Ban.class);

    String punishmentAction;
    String command;
    String[] aliases;
    Permission requiredPermission;
    CommandCategory commandCategory;

    public Ban() {
        punishmentAction = "ban";
        command = "ban";
        aliases = new String[]{"ban"};
        requiredPermission = Permission.BAN_MEMBERS;
        commandCategory = CommandCategory.ADMIN;
    }

    @Override
    protected String getPunishmentAction() {
        return punishmentAction;
    }

    @Override
    protected Permission getRequiredPermission() {
        return requiredPermission;
    }

    @Override
    protected boolean punish(Guild guild, Member member, MessageReceivedEvent event, String[] args) {
        if(!PermissionUtil.checkPermission(member, getRequiredPermission())) {
            event.getTextChannel().sendMessage(Embeds.getRedEmbed("You do not have permission to ban.", "Error").build()).queue();
            return false;
        }
        //Args: @Samkist -d 40 "Reason"
        //Args: @Samkist "Reason"
        boolean delDays = false;
        delDays = !args[1].equalsIgnoreCase("-d");
        int days = 7;
        if(!delDays)
                days = Integer.parseInt(args[2]);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = delDays ? 1 : 3; i < args.length; i++)
            stringBuilder.append(args[i]).append(" ");
        int finalDays = days;
        if(delDays) {
            Consumer<Void> success = (ban) -> event.getTextChannel().sendMessage(Embeds.getRedEmbed(member.getAsMention() + " has been banned for " + stringBuilder.toString() + "and their messages from the past " + finalDays + " days have been deleted", "User Banned").build()).queue();
            guild.ban(member, days, stringBuilder.toString()).queue(success);
        } else {
            Consumer<Void> success = (ban) -> event.getTextChannel().sendMessage(Embeds.getRedEmbed(member.getAsMention() + " has been banned for " + stringBuilder.toString(), "User Banned").build()).queue();
            guild.ban(member, 0, stringBuilder.toString()).queue(success);
        }
        return true;
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
    public CommandCategory getCommandCategory() {
        return commandCategory;
    }

    @Override
    public void execute(String[] args, MessageReceivedEvent event) {
        TextChannel channel = event.getTextChannel();
        Guild guild = channel.getGuild();
        Member member;
        try {
            member = event.getMessage().getMentionedMembers().get(0);
        } catch(Exception e) {
            event.getTextChannel().sendMessage(Embeds.getRedEmbed("Could not find member", "Error").build()).queue();
            return;
        }
        logger.info("We get down here doh?");
        punish(guild, member, event, args);
        //!ban @Samkist -t 40 "Reason"
        //Args: @Samkist -t 40 "Reason"
    }
}
