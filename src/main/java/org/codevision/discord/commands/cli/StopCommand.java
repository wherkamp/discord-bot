package org.codevision.discord.commands.cli;

import me.kingtux.jlinecommand.JlineCommand;
import org.codevision.discord.DiscordBot;

import java.util.Collections;
import java.util.List;

public class StopCommand implements JlineCommand {
    private DiscordBot bot;

    public StopCommand(DiscordBot discordBot) {
        bot = discordBot;
    }

    @Override
    public List<String> commands() {
        return Collections.singletonList("stop");
    }

    @Override
    public void execute(List<String> list) {
bot.stop();
    }
}
