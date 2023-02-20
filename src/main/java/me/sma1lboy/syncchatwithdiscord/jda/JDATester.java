package me.sma1lboy.syncchatwithdiscord.jda;

import me.sma1lboy.syncchatwithdiscord.jda.commands.CheckCommand;
import me.sma1lboy.syncchatwithdiscord.jda.commands.LinkMCCommand;
import me.sma1lboy.syncchatwithdiscord.jda.commands.UserInfoImpl;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

/**
 * Dev environment of JDA discord BOT to prevent always upload file into cloud
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/11/16
 */
public class JDATester extends ListenerAdapter {

    public static void main(String[] args) throws InterruptedException {
        JDA jda = JDABuilder.createDefault("NTE3MjEyNjE4NDYxNjc1NTMw.GN2E5Q.rCPGaHUEMqrqaACG49i2MlgPh2MrFDlzOFB4m8")
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new UserInfoImpl())
                .addEventListeners(new LinkMCCommand())
                .addEventListeners(new CheckCommand())
                .build();
        jda.awaitReady();  // optionally block until JDA is ready
        jda.upsertCommand("info", "showing the information of mc and discord").queue();
        jda.upsertCommand("linkmc", "link your discord account with your mc account").addOption(OptionType.STRING, "mcname", "the account you want to bind").queue();
        jda.upsertCommand("check", "check daily").queue();
    }
}


