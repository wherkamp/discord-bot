package org.codevision.discord.commands;

import me.kingtux.tuxcommand.common.TuxCommand;
import me.kingtux.tuxcommand.common.annotations.BaseCommand;
import me.kingtux.tuxcommand.common.annotations.Command;
import net.dv8tion.jda.core.entities.TextChannel;
import org.codevision.discord.DiscordBot;
import org.codevision.discord.MessageFactory;

@Command(aliases = "info", description = "Just Some basic info", format = "/info")
public class InfoCommand implements TuxCommand {
    private DiscordBot bot;

    public InfoCommand(DiscordBot discordBot) {
        this.bot = discordBot;
    }

    @BaseCommand
    public void base(TextChannel tc) {
        MessageFactory.create(String.valueOf(tc.getGuild().getMembers().size())).queue(tc);
    }
}
