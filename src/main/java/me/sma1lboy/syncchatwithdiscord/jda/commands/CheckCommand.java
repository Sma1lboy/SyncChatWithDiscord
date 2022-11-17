package me.sma1lboy.syncchatwithdiscord.jda.commands;

import me.sma1lboy.syncchatwithdiscord.db.pojo.User;
import me.sma1lboy.syncchatwithdiscord.db.service.UserService;
import me.sma1lboy.syncchatwithdiscord.db.service.impl.UserServiceImpl;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.temporal.ChronoField;


/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/11/16
 */
public class CheckCommand extends ListenerAdapter {

    UserService userService = new UserServiceImpl();

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        if (!e.getName().equals("check")) return;
        /*
        initial the user if not in our database
         */
        User user = userService.getUser(e.getUser().getAsTag(), e.getUser().getId());
        String lastTimeCheck = user.getLastTimeCheck();
        if (lastTimeCheck == null) {// which is first time check
            lastTimeCheck = Instant.now().toString();
            e.reply("Keep going, this is your first time check!").setEphemeral(true).queue();
        } else {
            long old = Instant.parse(lastTimeCheck).getLong(ChronoField.INSTANT_SECONDS); // get old time
            Instant currTime = Instant.now();
            long current = currTime.getLong(ChronoField.INSTANT_SECONDS);
            long diff = current - old;
            if (diff >= 86400) {
                lastTimeCheck = currTime.toString();
                user.setDailyTrack((user.getDailyTrack() == null ? 0 : user.getDailyTrack()) + 1);
                e.reply("Congratulations!" + "You already check " + user.getDailyTrack() + " days").setEphemeral(true).queue();
            } else {
                diff = 86400 - diff;
                e.reply("You cannot check right now, you still have to wait " + ((diff / 3600) == 0 ? "" : diff / 3600 + " hrs") + " " + (diff / 60 == 0 ? "" :  (diff % 3600) / 60 + " mins") + " " + (diff % 60) + " seconds").setEphemeral(true).queue();
            }
        }
        user.setLastTimeCheck(lastTimeCheck);
        userService.updatingUser(user);
    }


    /**
     * Lisiten for button checkDaily
     * @param e
     */
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent e) {
        if(!e.getButton().getId().equals("checkDaily")) return;
        User user = userService.getUser(e.getUser().getAsTag(), e.getUser().getId());
        String lastTimeCheck = user.getLastTimeCheck();
        if (lastTimeCheck == null) {// which is first time check
            lastTimeCheck = Instant.now().toString();
            e.reply("Keep going, this is your first time check!").setEphemeral(true).queue();
        } else {
            long old = Instant.parse(lastTimeCheck).getLong(ChronoField.INSTANT_SECONDS); // get old time
            Instant currTime = Instant.now();
            long current = currTime.getLong(ChronoField.INSTANT_SECONDS);
            long diff = current - old;
            if (diff >= 86400) {
                lastTimeCheck = currTime.toString();
                user.setDailyTrack((user.getDailyTrack() == null ? 0 : user.getDailyTrack()) + 1);
                e.reply("Congratulations!" + "You already check " + user.getDailyTrack() + " days").setEphemeral(true).queue();
            } else {
                diff = 86400 - diff;
                e.reply("You cannot check right now, you still have to wait " + ((diff / 3600) == 0 ? "" : diff / 3600 + " hrs") + " " + (diff / 60 == 0 ? "" :  (diff % 3600) / 60 + " mins") + " " + (diff % 60) + " seconds").setEphemeral(true).queue();
            }
        }
        user.setLastTimeCheck(lastTimeCheck);
        userService.updatingUser(user);
    }

}
