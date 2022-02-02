package me.sma1lboy.syncchatwithdiscord;

import me.sma1lboy.syncchatwithdiscord.events.GameChatEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class SyncChatWithDiscord extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {


        //Discord part
        //TODO sync message with server
        getConfig().getString("sdsd");

        // Plugin startup logic
        getServer().getConsoleSender().sendMessage("[SyncChatWithDiscord] " + ChatColor.GREEN + "SyncChatWithDiscord Start! 1.1");
        getServer().getPluginManager().registerEvents(new GameChatEvent(this), this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
