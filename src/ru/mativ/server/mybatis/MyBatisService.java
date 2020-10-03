package ru.mativ.server.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ru.mativ.server.mybatis.mappers.UserMapper;
import ru.mativ.tools.AppConfig;

public class MyBatisService {

    private static MyBatisService instance = new MyBatisService();

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;

    private UserMapper userMapper;

    private MyBatisService() {
        sqlSessionFactory = loadConfig(AppConfig.myBatisConfigPath());
        session = sqlSessionFactory.openSession();
        userMapper = session.getMapper(UserMapper.class);
    }

    public static MyBatisService getInstance() {
        return instance;
    }

    private SqlSessionFactory loadConfig(String path) {
        try {
            Reader reader = Resources.getResourceAsReader(path);
            return new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            System.err.println("Cannot load myBasis config file.");
            e.printStackTrace();
        }
        return null;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }
}
