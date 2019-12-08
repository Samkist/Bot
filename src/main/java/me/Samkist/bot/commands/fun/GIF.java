package me.Samkist.bot.commands.fun;

import com.kdotj.simplegiphy.SimpleGiphy;
import com.kdotj.simplegiphy.data.Giphy;
import com.kdotj.simplegiphy.data.GiphyListResponse;
import com.kdotj.simplegiphy.data.GiphyResponse;
import com.kdotj.simplegiphy.data.RandomGiphyResponse;
import me.Samkist.bot.commands.Command;
import me.Samkist.bot.commands.CommandCategory;
import me.Samkist.bot.commands.meta.CommandArgument;
import me.Samkist.bot.utils.Config;
import me.Samkist.bot.utils.Embeds;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class GIF extends Command {

    private String command;
    private String[] aliases;
    private String usage;
    private CommandCategory commandCategory;
    private ArrayList<CommandArgument> arguments;

    public GIF() {
        this.command = "gif";
        this.aliases = new String[0];
        this.usage = "Finds random gif from `https://giphy.com`";
        this.commandCategory = CommandCategory.FUN;
        arguments = new ArrayList<>();
        arguments.add(new CommandArgument("random", "Returns a random GIF", this));
        arguments.add(new CommandArgument("randomtag", "Returns a random GIF with the supplied tag", this));
        arguments.add(new CommandArgument("search", "Searches GIPHY for a GIF with the supplied information", this));
        arguments.add(new CommandArgument("trending", "Returns a random GIF from the GIPHY trending list top 50", this));
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
    public String getUsage() {
        return usage;
    }

    @Override
    public CommandCategory getCommandCategory() {
        return commandCategory;
    }

    @Override
    public ArrayList<CommandArgument> getCommandArguments() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute(String[] args, MessageReceivedEvent event) {
        SimpleGiphy.setApiKey(Config.getGiphyAPIKey());
        try {
            if(args[0] == null)
                System.out.println(args[0]);
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            arguments.forEach(commandArgument -> {
                stringBuilder.append(commandArgument.infoToString()).append("\n");
            });
            event.getTextChannel().sendMessage(
                    Embeds.getDefaultEmbed(stringBuilder.toString(), "GIF").build()
            ).queue();
            return;
        }
        switch (args[0])
        {
            case "random":
                RandomGiphyResponse response = SimpleGiphy.getInstance().random("", "pg-13");
                event.getTextChannel().sendMessage(
                        Embeds.getDefaultEmbed(null, "Random Gif")
                                .setImage(response.getRandomGiphy().getImageUrl()).build())
                        .queue();
                break;
            case "randomtag":
                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 1; i < args.length; i++) {
                    stringBuilder.append(args[i]).append("\\s+");
                }
                RandomGiphyResponse response1 = SimpleGiphy.getInstance().random(stringBuilder.toString(), "pg-13");
                event.getTextChannel().sendMessage(
                        Embeds.getDefaultEmbed(null, "Random Gif w/ Tag")
                                .setImage(response1.getRandomGiphy().getImageUrl()).build())
                        .queue();
                break;
            case "search":
                StringBuilder stringBuilder1 = new StringBuilder();
                for(int i = 1; i < args.length; i++) {
                    stringBuilder1.append(args[i]).append(" "); // Used space because search result - would return \s+ for a space
                }
                GiphyListResponse response2 = SimpleGiphy.getInstance().search(stringBuilder1.toString(), "50", "", "pg-13");
                List<Giphy> responseList = response2.getData();
                double randomDouble = Math.random();
                randomDouble = randomDouble * responseList.size() -1;
                Giphy response3 = responseList.get((int) randomDouble);
                event.getTextChannel().sendMessage(
                        Embeds.getDefaultEmbed(null, "Search Result - " + stringBuilder1.toString())
                            .setImage(response3.getImages().getOriginal().getUrl()).build()
                ).queue();
            case "trending":
                GiphyListResponse response4 = SimpleGiphy.getInstance().trending("50", "pg-13");
                List<Giphy> responseList1 = response4.getData();
                double randomDouble1 = Math.random();
                randomDouble1 = randomDouble1 * responseList1.size() -1;
                Giphy response5 = responseList1.get((int) randomDouble1);
                event.getTextChannel().sendMessage(
                        Embeds.getDefaultEmbed(null, "Random Trending GIF From Top 50")
                                .setImage(response5.getImages().getOriginal().getUrl()).build()
                ).queue();
            default:
        }
    }
}
