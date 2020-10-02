package ru.mativ.server.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ru.mativ.server.repository.pojo.UserDao;

public interface UserMapper {

    int insert(UserDao userDao);

    List<UserDao> getAll();

    UserDao getById(int id);

    UserDao getByLoginPass(@Param("login") String login, @Param("pass") String pass);

}
