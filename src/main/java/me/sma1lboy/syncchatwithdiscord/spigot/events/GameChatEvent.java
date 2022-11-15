package me.sma1lboy.syncchatwithdiscord.spigot.events;


import me.sma1lboy.syncchatwithdiscord.SyncChatWithDiscord;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.internal.entities.emoji.UnicodeEmojiImpl;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.PluginDisableEvent;



public class GameChatEvent extends ListenerAdapter implements Listener {


    public SyncChatWithDiscord plugin;
    public JDA jda;

    public GameChatEvent(SyncChatWithDiscord plugin) {
        this.plugin = plugin;
        startBot();
    }

    private void startBot() {
        jda = JDABuilder.createDefault(plugin.getConfig().getString("serverGeneralConfig.discordBotToken"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(this)
                .build();
        this.plugin.getServer().getConsoleSender().sendMessage(this.plugin.getConfig().getString("serverGeneralConfig.discordBotToken"));
    }

    @EventHandler
    public void gameChatEvent(AsyncPlayerChatEvent e) {
        String msgReceived = e.getMessage();
        //make sure it's not empty message
        if(msgReceived.length() <= 0) return;
        Player player = e.getPlayer();
        TextChannel textChannel = jda.getTextChannelById(plugin.getConfig().getString("serverGeneralConfig.channelID"));
        textChannel.sendMessage(player.getDisplayName() + ": " + "**" + msgReceived + "**").queue();
    }

    /**
     * Close jda all the stuff
     * @param e event
     */
    @EventHandler
    public void serverShutDown(PluginDisableEvent e) {
        //TODO determine if it is restart or turn off
        this.jda.shutdown();
        this.plugin.getServer().getConsoleSender().sendMessage("[SyncChatWithDiscord] " + ChatColor.RED + "SyncChatWithDiscord JDA BOT SHUTDOWN!");
    }


    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getAuthor().isBot()) return;
        String channelID = plugin.getConfig().getString("serverGeneralConfig.channelID");
        //make sure the bot is only works in specific channel
        if(!e.getChannel().getId().equals(channelID)) {
            return;
        }
        //add reaction to confirm forwarding
        e.getMessage().addReaction(Emoji.fromUnicode("✅")).queue();// String msg = e.getMessage().getContentStripped();
        String username = e.getAuthor().getAsTag();
        Bukkit.broadcastMessage("<" + username + "> " +  e.getMessage().getContentStripped());

    }


}
