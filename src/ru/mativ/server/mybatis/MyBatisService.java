package ru.mativ.server.mybatis;

import java.util.logging.Logger;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ru.mativ.server.mybatis.mappers.NoteMapper;
import ru.mativ.server.mybatis.mappers.NoteTypeMapper;
import ru.mativ.server.mybatis.mappers.UserMapper;
import ru.mativ.tools.AppConf;

public class MyBatisService {
    private static final Logger Log = Logger.getLogger(MyBatisService.class.getName());

    private static MyBatisService instance = new MyBatisService();

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;

    private UserMapper userMapper;
    private NoteMapper noteMapper;
    private NoteTypeMapper noteTypeMapper;

    private MyBatisService() {
        sqlSessionFactory = AppConf.getSqlSessionFactory();
        session = sqlSessionFactory.openSession();
        userMapper = session.getMapper(UserMapper.class);
        noteMapper = session.getMapper(NoteMapper.class);
        noteTypeMapper = session.getMapper(NoteTypeMapper.class);
        Log.info("MyBatis load success.");
    }

    public static MyBatisService getInstance() {
        return instance;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public NoteMapper getNoteMapper() {
        return noteMapper;
    }

    public NoteTypeMapper getNoteTypeMapper() {
        return noteTypeMapper;
    }

    public void commit() {
        session.commit();
    }

    public void rollback() {
        session.rollback();
    }
}
