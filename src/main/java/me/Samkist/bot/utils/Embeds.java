package me.Samkist.bot.utils;

import net.dv8tion.jda.api.EmbedBuilder;

import javax.annotation.Nullable;
import java.awt.*;

public class Embeds {
    public static EmbedBuilder getDefaultEmbed(String content, @Nullable String title) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setDescription(content);
        embedBuilder.setColor(new Color(0, 255, 128));
        embedBuilder.setAuthor(title, null, "https://cdn.discordapp.com/avatars/133231388538306560/3da005a023dd4de2332fc07d901745b8.png");
        embedBuilder.setFooter("Discord Bot | Samkist");
        return embedBuilder;
    }
}
