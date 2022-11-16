package me.sma1lboy.syncchatwithdiscord.jda.handlers.impl;

import me.sma1lboy.syncchatwithdiscord.jda.handlers.JDAHandler;
import me.sma1lboy.syncchatwithdiscord.spigot.events.GameChatEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
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

    private final GameChatEvent gameChatEvent;
    /**
     * The bot only service to one text channel right now.
     */
    private final String TEXT_CHANNEL;
    private final String TOKEN;
    public JDAHandlerImpl(GameChatEvent gameChatEvent){

        this.gameChatEvent = gameChatEvent;
        /*
         * Parsing the yaml file
         */
        Yaml yaml = new Yaml();
        /*
        get resource from config file
         */
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.yml");
        /*
        load the data
         */
        Map<String, Object> data = yaml.load(inputStream);
        this.TOKEN = (String) ((Map<String, Object>) data.get("serverGeneralConfig")).get("discordBotToken");
        this.TEXT_CHANNEL = (String) ((Map<String, Object>) data.get("serverGeneralConfig")).get("channelID");
    }

    public void sendMsg(String message) {
        assert TEXT_CHANNEL != null;
        this.jda.getTextChannelById(TEXT_CHANNEL).sendMessage(message).queue();
    }
    @Override
    public void connect() {
        jda = JDABuilder.createDefault(this.TOKEN)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT) //let bot access to the user message, Careful it could change API when updating
                .addEventListeners(this.gameChatEvent)
                .build();

    }

    @Override
    public void disconnect() {
        jda.shutdown();
    }
}
