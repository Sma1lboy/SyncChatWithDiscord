package me.sma1lboy.syncchatwithdiscord.jda.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import org.jetbrains.annotations.NotNull;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/11/15
 */
public class UserInfoImpl extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent s) {
        if(s.getName().equals("info")) {
            //TODO embed data with player info with minecraft data if exist
        }
    }
}
