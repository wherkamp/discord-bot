package org.codevision.discord;

import org.apache.commons.io.FileUtils;
import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File file = new File("config.yml");
        YamlFile config = new YamlFile(file);
        try {
            if (!file.exists()) {
                FileUtils.copyInputStreamToFile(Main.class.getResourceAsStream("/config.yml"), file);
                System.out.println("Please Configure the config.yml");
                return;
            }
            config.load();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        new DiscordBot(config);
    }
}
