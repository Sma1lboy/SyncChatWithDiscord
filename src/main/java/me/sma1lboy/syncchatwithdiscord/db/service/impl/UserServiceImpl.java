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
        User user = users.size() == 1 ? users.get(0) : getUser(userID, userUUID);
        /*
        updating
         */
        user.setMcId(mcID);
        user.setMcUuid(mcUUID);
        mapper.updateByPrimaryKey(user);

        /*
        Close session
         */
        session.close();
    }


    @Override
    public User getUser(String dcID, String dcUUID) {
                /*
        get SqlSession and mapper
        */
        SqlSession session = SqlSessionUtil.getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        UserExample userExmp = new UserExample();
        userExmp.createCriteria().andDcIdEqualTo(dcID);
        List<User> users = mapper.selectByExample(userExmp);
        User user = new User(dcID, dcUUID);
        if (users.size() == 1) {
            user = users.get(0);
        } else {
            mapper.insert(user);
        }
        /*
        Close session
        */
        session.close();

        return user;
    }

    @Override
    public void insertUser(User user) {
                /*
        get SqlSession and mapper
         */
        SqlSession session = SqlSessionUtil.getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        mapper.insert(user);
        /*
        Close session
         */
        session.close();
    }

    @Override
    public boolean contains(String mcId) {
        /*
        get SqlSession and mapper
         */
        SqlSession session = SqlSessionUtil.getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        UserExample userExample = new UserExample();
        userExample.createCriteria().andMcIdEqualTo(mcId);
        int i = mapper.countByExample(userExample);
        /*
        Close session
         */
        session.close();
        return i != 0;
    }

    @Test
    public void testUserExample() {
        binding("asd", "ken", "Sma1lboy", "Asd");

    }
}
