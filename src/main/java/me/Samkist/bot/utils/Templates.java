package me.Samkist.bot.utils;

import me.Samkist.bot.commands.Command;

public class Templates {
    public static String getUsageMessage(Command command) {
        return "`" + Config.getPrefix() + command.getCommand() + "` " + command.getUsage();
    }
}
