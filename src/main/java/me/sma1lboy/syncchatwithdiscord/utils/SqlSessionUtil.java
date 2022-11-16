package me.sma1lboy.syncchatwithdiscord.utils;

import me.sma1lboy.syncchatwithdiscord.exceptions.GetSqlSessionException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/11/16
 */
public class SqlSessionUtil {

    public static SqlSession getSqlSession(){
        InputStream inputStream;
        SqlSession sqlSession;
        try {
            //获取配置文件输入流
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //获取SqlSessionFactoryBuilder  sql会话工厂构造器 来构造sqlSessionFactory
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            //通过SqlSessionFactoryBuilder 传入配置文件输入流 获取到sql会话工厂
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
            //通过工厂打开sql会话获取sqlSession对象
            //default autocommit true
            sqlSession = sqlSessionFactory.openSession(true);
        } catch (IOException e) {
            throw new GetSqlSessionException("Invalid get sqlSession");
        }
        return sqlSession;
    }

    @Test
    public void testGetSession() {
        SqlSession sqlSession = getSqlSession();
        System.out.println(sqlSession);
    }
}
