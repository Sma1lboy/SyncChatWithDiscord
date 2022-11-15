package me.sma1lboy.syncchatwithdiscord.jda.handlers;

/**
 * The JDAHandler to encapsulation JDA's connection and disconnection
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/11/15
 */
public interface JDAHandler {

    void connection();
    void disconnection();
}
