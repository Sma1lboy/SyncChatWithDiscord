package me.sma1lboy.syncchatwithdiscord.db.service.impl;

import me.sma1lboy.syncchatwithdiscord.db.mapper.UserMapper;
import me.sma1lboy.syncchatwithdiscord.db.pojo.User;
import me.sma1lboy.syncchatwithdiscord.db.pojo.UserExample;
import me.sma1lboy.syncchatwithdiscord.db.service.UserService;
import me.sma1lboy.syncchatwithdiscord.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/11/16
 */
public class UserServiceImpl implements UserService {

    @Override
    public void checkDaily(String dcId) {
    }

    @Override
    public void insertUser(User user) {
        SqlSession session = SqlSessionUtil.getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.insert(user);
        session.close();
    }

    @Override
    public void updatingUser(User user) {
        SqlSession session = SqlSessionUtil.getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.updateByPrimaryKey(user);
        session.close();
    }

    @Override
    public void binding(String userID, String userUUID, String mcID, String mcUUID) {
        /*
        get SqlSession and mapper
         */
        SqlSession session = SqlSessionUtil.getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        /*
        UserExample userExmp criteria
         */
        UserExample userExmp = new UserExample();
        userExmp.createCriteria().andDcIdEqualTo(userID);
        List<User> users = mapper.selectByExample(userExmp);
        if (users.size() == 1) { // found it
            //TODO check if there is exist a linking
            User user = users.get(0);
            if(user.getMcId().length() == 0) {
                user.setMcId(mcID);
                user.setMcUuid(mcUUID);
                mapper.updateByPrimaryKey(user);
            }
        } else {
            mapper.insert(new User(userID, userUUID, mcID, mcUUID));
        }
        /*
        Close session
         */
        session.close();
    }

    @Override
    public void createUser(String dcID, String dcUUID) {
        /*
        get SqlSession and mapper
        */
        SqlSession session = SqlSessionUtil.getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        mapper.insert(new User(dcID, dcUUID));

        /*
        Close session
        */
        session.close();
    }

    @Test
    public void testUserExample() {
            binding("asd", "ken", "Sma1lboy", "Asd");

    }
}
