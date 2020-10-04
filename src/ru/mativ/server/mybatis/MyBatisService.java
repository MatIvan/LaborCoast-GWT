package ru.mativ.server.mybatis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
        sqlSessionFactory = getFactoryByConfig(AppConfig.myBatisConfigPath());
        session = sqlSessionFactory.openSession();
        userMapper = session.getMapper(UserMapper.class);
    }

    public static MyBatisService getInstance() {
        return instance;
    }

    private SqlSessionFactory getFactoryByConfig(String path) {
        try (FileInputStream stream = new FileInputStream(path)) {
            return new SqlSessionFactoryBuilder().build(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println("Cannot load myBasis config file.");
        return null;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void commit() {
        session.commit();
    }

    public void rollback() {
        session.rollback();
    }

}
