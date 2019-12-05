package me.Samkist.bot.main;

import me.Samkist.bot.commands.CommandHandler;
import me.Samkist.bot.commands.Echo;
import me.Samkist.bot.utils.Config;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

public class Launcher {

    private static JDA jda;
    public static void main(String[] args) throws LoginException, IndexOutOfBoundsException
    {
        Logger logger = LoggerFactory.getLogger("Launcher");

        String token = Config.getToken();

        CommandHandler commandHandler = new CommandHandler();

        commandHandler.addCommands(
                new Echo()
        );


        jda = new JDABuilder(AccountType.BOT)
                .setToken(token)
                .addEventListeners(commandHandler)
                .build();
    }

    public static JDA getJda() {
        return jda;
    }


}
