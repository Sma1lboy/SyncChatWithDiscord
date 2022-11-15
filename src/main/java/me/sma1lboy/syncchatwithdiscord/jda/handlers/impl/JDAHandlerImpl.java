package me.sma1lboy.syncchatwithdiscord.jda.handlers.impl;

import me.sma1lboy.syncchatwithdiscord.SyncChatWithDiscord;
import me.sma1lboy.syncchatwithdiscord.jda.handlers.JDAHandler;
import me.sma1lboy.syncchatwithdiscord.spigot.events.GameChatEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * JDAHandler to Han
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/11/15
 */
public class JDAHandlerImpl implements JDAHandler {

    public JDA jda;
    /**
     * Parsing the yaml file
     */
    private Yaml yaml;

    private final SyncChatWithDiscord plugin;
    /**
     * The bot only service to one text channel right now.
     */
    private TextChannel textChannel;
    private String token;
    public JDAHandlerImpl(SyncChatWithDiscord plugin){
        this.plugin = plugin;
        /*
        initial the file
         */
        yaml = new Yaml();
        /*
        get resource from config file
         */
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.yml");
        /*
        load the data
         */
        Map<String, Object> data = yaml.load(inputStream);

        this.textChannel = this.jda.getTextChannelById(Long.parseLong((String)((Map<String, Object>) data.get("serverGeneralConfig")).get("channelID")));
        this.token = (String) ((Map<String, Object>) data.get("serverGeneralConfig")).get("discordBotToken");
    }

    public void sendMsg(String message) {
        textChannel.sendMessage(message).queue();
    }
    @Override
    public void connect() {
        jda = JDABuilder.createDefault(this.token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT) //let bot access to the user message, Careful it could change API when updating
                .addEventListeners(new GameChatEvent(plugin))
                .build();
    }

    @Override
    public void disconnect() {
        jda.shutdown();
    }
}
