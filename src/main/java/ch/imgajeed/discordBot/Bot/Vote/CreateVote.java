package ch.imgajeed.discordBot.Bot.Vote;

import ch.imgajeed.discordBot.Bot.Listener;
import ch.imgajeed.discordBot.Bot.MessageAction;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class CreateVote extends MessageAction {
    @Override
    public String name() {
        return "CreateVote";
    }

    @Override
    public void Run(@NotNull MessageReceivedEvent event, Listener listener) {
        var content = listener.GetContent(event.getMessage().getContentRaw());
        var title = content.get(0);
        var circleEnabled = content.get(1).equals("true");
        var vote = new Vote(title, circleEnabled);

        event.getChannel().sendMessage(vote.GetMessage()).queue(message -> {
            vote.messageID = message.getId();
            message.addReaction("⬆").queue();
            message.addReaction("⬇").queue();
            listener.reactionActions.add(new VoteFor(message.getId()));
        });

        listener.votes.add(vote);
    }
}
