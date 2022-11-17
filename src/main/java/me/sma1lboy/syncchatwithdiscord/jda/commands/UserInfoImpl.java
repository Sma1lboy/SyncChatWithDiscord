package me.sma1lboy.syncchatwithdiscord.jda.commands;

import me.sma1lboy.syncchatwithdiscord.db.pojo.User;
import me.sma1lboy.syncchatwithdiscord.db.service.UserService;
import me.sma1lboy.syncchatwithdiscord.db.service.impl.UserServiceImpl;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/11/15
 */
public class UserInfoImpl extends ListenerAdapter {

    UserService userService = new UserServiceImpl();
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e)
    {
        if (!e.getName().equals("info")) return; // make sure we handle the right command
        /*
         * starting create the embed
         */
        EmbedBuilder builder = new EmbedBuilder();

        User user = userService.getUser(e.getUser().getAsTag(), e.getUser().getId());
        String dcUsername = user.getDcId();
        String mcUsername = user.getMcId() == null ? "Not Assign yet" : user.getMcId();
        Integer dayTrack = user.getDailyTrack() == null ? 0 : user.getDailyTrack();
        /*
        TODO status check with different level strick daily tracking
         */
        builder.addField("Username", dcUsername, true);
        builder.addField("Minecraft Nickname", mcUsername, true);
        builder.addField("Paste Two Weeks in Game", "1 day 2hrs 🔥", true);
        builder.addField("Daily Check Track", dayTrack + " days 🔥", false);
        //TODO Change to local img
        builder.setThumbnail("https://play-lh.googleusercontent.com/DJexP6PO8dL06XvNrjG7plb7SW_SaxuNamO80ab512JA71lBEBUnaJCaZzlqWVrrlEiG=w240-h480-rw");
        builder.setAuthor("@someone");
        //TODO add footer icon
        builder.setFooter("@MCMsgBot");
        //TODO check button
        e.replyEmbeds(builder.build()).setEphemeral(true).queue();
    }
}
