package me.sma1lboy.syncchatwithdiscord.events;


import me.sma1lboy.syncchatwithdiscord.SyncChatWithDiscord;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import javax.security.auth.login.LoginException;



public class GameChatEvent extends ListenerAdapter implements Listener {


    public SyncChatWithDiscord plugin;
    public JDA jda;
    public GameChatEvent(SyncChatWithDiscord plugin) {
        this.plugin = plugin;
        startBot();
    }
    private void startBot() {
        try {
            //jda = JDABuilder.createDefault(String.valueOf()).build();
             jda = JDABuilder.createDefault(plugin.getConfig().getString("serverGeneralConfig.discordBotToken"))
                     .enableIntents(GatewayIntent.GUILD_MESSAGES)
                     .addEventListeners(this)
                     .build();
        }
        catch (LoginException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void gameChatEvent(AsyncPlayerChatEvent e) {
        String msgRecived = e.getMessage();
        Player player = e.getPlayer();
        TextChannel textChannel = jda.getTextChannelById(plugin.getConfig().getString("serverGeneralConfig.channelID"));
        assert textChannel != null;
        textChannel.sendMessage(player.getDisplayName() + ": " +  "**" + msgRecived + "**").queue();

    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot()) return;
        String msg = e.getMessage().getContentRaw();
        User user = e.getAuthor();
        Bukkit.broadcastMessage("§3§l" + user.getName() + "§f§r: " + msg);

    }


}
