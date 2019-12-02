package me.Samkist.bot.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

public class EchoCommand extends Command {

    private final EventWaiter waiter;
    public EchoCommand(EventWaiter waiter) {
        this.waiter = waiter;
        this.name = "echo";
        this.aliases = new String[]{"repeat"};
        this.help = "Repeats what the user inputs";
    }

    /**
     * The main body method of a {@link Command Command}.
     * <br>This is the "response" for a successful
     * {@link Command#run(CommandEvent) #run(CommandEvent)}.
     *
     * @param event The {@link CommandEvent CommandEvent} that
     *              triggered this Command
     */
    @Override
    protected void execute(CommandEvent event) {
        event.reply(event.getArgs());
    }
}
