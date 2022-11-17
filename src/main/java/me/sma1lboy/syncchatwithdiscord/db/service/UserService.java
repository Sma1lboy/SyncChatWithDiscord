package me.sma1lboy.syncchatwithdiscord.db.service;

import me.sma1lboy.syncchatwithdiscord.db.pojo.User;

/**
 * The service layout for User service
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/11/16
 */
public interface UserService {
    void checkDaily(String dcId);

    void updatingUser(User user);
    void binding(String userID,  String userUUID, String mcID, String mcUUID);


    void insertUser(User user);
    /**
     * Get user by user's discord name.
     * @param dcID
     * @param dcUUID
     * @return return the user if exsit, otherwise create a new one
     */
    User getUser(String dcID, String dcUUID);

    boolean contains(String mcId);

}
