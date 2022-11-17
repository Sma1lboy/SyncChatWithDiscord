package me.sma1lboy.syncchatwithdiscord.jda.commands;

import me.sma1lboy.syncchatwithdiscord.db.pojo.User;
import me.sma1lboy.syncchatwithdiscord.db.service.UserService;
import me.sma1lboy.syncchatwithdiscord.db.service.impl.UserServiceImpl;
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

    UserService userService = new UserServiceImpl();
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        if(!e.getName().equals("linkmc")) return;

        String discordName = e.getUser().getAsTag();
        /*
        check discord name in Database if there exist account linking already
         */
        //TODO check both account, if there exist any binding from database
        User user = userService.getUser(e.getUser().getAsTag(), e.getUser().getId());

        if(user.getMcId() != null) { //discord account already bind mc account
            e.reply("This discord account already bind to " + user.getMcId() + " if trying to link another account please unbind first").setEphemeral(true).queue();
            return;
        }
        OptionMapping mcNameOpt = e.getOption("mcname");
        if(mcNameOpt != null) {
            String mcName = mcNameOpt.getAsString();
            if(userService.contains(mcName)) { //if db already contains the binding mc name
                e.reply("The minecraft account already bound, please DM the server support to see if there is some issue").setEphemeral(true).queue();
                return;
            }
            /*
            binding account, if account not exist it will create one in Database
             */
            userService.binding(e.getUser().getAsTag(), e.getUser().getId(), mcNameOpt.getAsString(), "");
            e.reply("Congratulations!🎉 binding succeed! You already bound " + discordName + " with " + mcName).setEphemeral(false).queue();
        } else {
            e.reply("Error! you must enter your mc name!").setEphemeral(true).queue();
        }
    }
}
