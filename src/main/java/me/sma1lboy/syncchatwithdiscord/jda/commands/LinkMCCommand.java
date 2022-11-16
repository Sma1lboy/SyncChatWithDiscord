package me.sma1lboy.syncchatwithdiscord.jda.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/11/16
 */
public class LinkMCCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        if(!e.getName().equals("linkmc")) return;

        String discordName = e.getUser().getAsTag();
        /*
        check discord name in Database if there exist account linking already
         */
        //TODO check both account, if there exist any binding from database
        OptionMapping mcnameOpt = e.getOption("mcname");

        if(mcnameOpt != null) {
            String mcName = mcnameOpt.getAsString();
            e.reply("Congratulations! binding succeed! You already bound " + discordName + " with " + mcName).setEphemeral(false).queue();
        } else {
            e.reply("Error! you must enter your mc name!").setEphemeral(true).queue();
        }
    }
}
