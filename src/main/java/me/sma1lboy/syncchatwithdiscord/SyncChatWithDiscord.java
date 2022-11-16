package me.sma1lboy.syncchatwithdiscord;

import me.sma1lboy.syncchatwithdiscord.spigot.events.GameChatEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SyncChatWithDiscord extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        //Discord part
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage("[SyncChatWithDiscord] " + ChatColor.GREEN + "SyncChatWithDiscord Start!");
        getServer().getPluginManager().registerEvents(new GameChatEvent(this), this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage("[SyncChatWithDiscord] " + ChatColor.RED + "SyncChatWithDiscord STOP!");
    }

}
