package org.codevision.discord;

import me.kingtux.jlinecommand.JlineCommandManager;
import me.kingtux.tuxcommand.common.MyCommandBuilder;
import me.kingtux.tuxcommand.jda.JDACommandManager;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.codevision.discord.commands.InfoCommand;
import org.codevision.discord.commands.cli.StopCommand;
import org.simpleyaml.configuration.file.YamlFile;

import javax.security.auth.login.LoginException;

public final class DiscordBot {
    private YamlFile config;
    private JDA bot;
    private JlineCommandManager jlineWrapper;
    private JDACommandManager cm;
    private String prefix;

    protected DiscordBot(YamlFile config) {
        this.config = config;

        startBot();
        jlineWrapper = new JlineCommandManager("CodeVision");
        jlineWrapper.register(new StopCommand(this));
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
        prefix=config.getString("commands.prefix");
        cm = new JDACommandManager(bot, prefix);
        cm.register(MyCommandBuilder.create(new InfoCommand(this)).setFormat(prefix+"info"));
    }

    private void startBot() {
        try {
            bot = new JDABuilder(config.getString("token")).addEventListener(new Listeners(this)).build();
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        System.out.println("Closing the Discord Bot!");
        jlineWrapper.close();
        bot.shutdownNow();
    }

    public JDA getBot() {
        return bot;
    }
}
