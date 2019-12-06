package me.Samkist.bot.commands.admin;

import me.Samkist.bot.commands.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class ModerationCommand extends Command {

    @Override
    public String getUsage() {
        return getCommand() + " `<user>` \t//" + getPunishmentAction() + " from guild";
    }

    protected abstract String getPunishmentAction();

    protected abstract Permission getRequiredPermission();

    protected abstract boolean punish(Guild guild, Member member);

    @Override
    public void execute(String[] args, MessageReceivedEvent event) {
        TextChannel channel = event.getTextChannel();
        Guild guild = channel.getGuild();

    }
}
