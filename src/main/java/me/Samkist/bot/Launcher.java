package me.Samkist.bot;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.Samkist.bot.Commands.EchoCommand;
import me.Samkist.bot.Commands.SettingsCommand;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

public class Launcher {
    private static CommandClient client;
    private static CommandClientBuilder clientBuilder = new CommandClientBuilder();
    private static JDA jda;
    public static void main(String[] args) throws LoginException, IndexOutOfBoundsException
    {
        Logger logger = LoggerFactory.getLogger(Launcher.class);

        String token = Config.getToken();
        String ownerId = Config.getOwnerId();
        String prefix = Config.getPrefix();

        EventWaiter waiter = new EventWaiter();

        clientBuilder.useDefaultGame();

        clientBuilder.setOwnerId(ownerId);

        clientBuilder.setPrefix("!");

        clientBuilder.addCommands(
                new EchoCommand(waiter),
                new SettingsCommand(waiter)
        );

        jda = new JDABuilder(AccountType.BOT)
                .setToken(Config.getToken())
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .addEventListeners(waiter, setInitialClient(clientBuilder.build()))
                .build();
    }

    private static CommandClient setInitialClient(CommandClient c) {
        client = c;
        return c;
    }

    private static CommandClient getClient() {
        return client;
    }

    public static void setClient(CommandClient c) {
        try{
            jda.removeEventListener(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        client = c;
        jda.addEventListener(client);
    }

    public static CommandClientBuilder getCommandClientBuilder() {
        return clientBuilder;
    }

    public static JDA getJda() {
        return jda;
    }


}
