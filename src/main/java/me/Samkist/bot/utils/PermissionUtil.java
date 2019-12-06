package me.Samkist.bot.utils;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

import java.util.Objects;

public class PermissionUtil {

    public static boolean userHasPermission(User user, Permission permission, Guild guild) {
        return Objects.requireNonNull(guild.getMember(user)).hasPermission(permission);
    }
}
