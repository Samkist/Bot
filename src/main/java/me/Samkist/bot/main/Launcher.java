package me.Samkist.bot.main;

import me.Samkist.bot.commands.CommandHandler;
import me.Samkist.bot.commands.fun.Echo;
import me.Samkist.bot.commands.fun.Ping;
import me.Samkist.bot.utils.Config;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

public class Launcher {

    private static JDA jda;
    public static void main(String[] args) throws LoginException, IndexOutOfBoundsException
    {
        Logger logger = LoggerFactory.getLogger(Logger.class);

        String token = Config.getToken();

        CommandHandler commandHandler = new CommandHandler();

        commandHandler.addCommands(
                new Echo(),
                new Ping()
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
