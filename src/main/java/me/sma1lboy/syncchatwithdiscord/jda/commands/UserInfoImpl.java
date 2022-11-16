package me.sma1lboy.syncchatwithdiscord.jda.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/11/15
 */
public class UserInfoImpl extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e)
    {
        if (!e.getName().equals("info")) return; // make sure we handle the right command
        /*
         * starting create the embed
         */
        EmbedBuilder builder = new EmbedBuilder();
        builder.addField("Username", "Sma1lboy#5210", true);
        builder.addField("Minecraft Nickname", "Sma1lboy", true);
        builder.addField("Paste Two Weeks in Game", "1 day 2hrs 🔥", true);
        builder.addField("Daily Check Track", "128 days 🔥", false);
        //TODO Change to local img
        builder.setThumbnail("https://play-lh.googleusercontent.com/DJexP6PO8dL06XvNrjG7plb7SW_SaxuNamO80ab512JA71lBEBUnaJCaZzlqWVrrlEiG=w240-h480-rw");
        builder.setAuthor("@someone");
        //TODO add footer icon
        builder.setFooter("@MCMsgBot");
        e.replyEmbeds(builder.build()).setEphemeral(true).queue();
    }
}
