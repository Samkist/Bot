package me.Samkist.bot.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.Samkist.bot.Launcher;

public class SettingsCommand extends Command {

    private final EventWaiter waiter;
    public SettingsCommand(EventWaiter waiter) {
        this.waiter = waiter;
        this.name = "settings";
        this.help = "Changes bot settings";
    }

    @Override
    protected void execute(CommandEvent event) {
        String[] args = event.getArgs().split(" ");
        if(args[0].equalsIgnoreCase("prefix")) {
            setPrefix(args[1]);
            event.reply("Set prefix to: " + args[1]);
        }
    }

    private void setPrefix(String prefix) {
        CommandClientBuilder builder = Launcher.getCommandClientBuilder();
        builder.setPrefix(prefix);
        Launcher.setClient(builder.build());
    }
}
