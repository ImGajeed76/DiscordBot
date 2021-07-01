package ch.imgajeed.discordBot.Bot.Random;

import ch.imgajeed.discordBot.Bot.Listener;
import ch.imgajeed.discordBot.Bot.MessageAction;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RandomNumber extends MessageAction {
    @Override
    public int contentLength() {
        return 2;
    }

    @Override
    public String name() {
        return "RandomNumber";
    }

    @Override
    public String content() {
        return ":*min* :*max*";
    }

    @Override
    public void Run(@NotNull MessageReceivedEvent event, Listener listener) {
        var content = listener.GetContent(event.getMessage().getContentRaw());
        if (content.size() < contentLength()) listener.ContentToShort(event.getChannel());

        var min = GetNumber(content.get(0)) + 1;
        var max = GetNumber(content.get(1)) + 1;

        var bounds = max - min;

        Random random = new Random();
        var number = (random.nextInt(bounds)) + min;
        event.getChannel().sendMessage("> " + number).queue();
    }

    private int GetNumber(String s) {
        var charArray = s.toCharArray();
        StringBuilder numberString = new StringBuilder();

        for (char c : charArray) {
            if (c != ' ') {
                numberString.append(c);
            }
        }

        return Integer.parseInt(numberString.toString());
    }
}
