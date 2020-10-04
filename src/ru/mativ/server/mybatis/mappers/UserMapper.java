package ru.mativ.server.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import ru.mativ.server.repository.pojo.UserDao;

public interface UserMapper {

    int insert(UserDao userDao);

    UserDao getByLoginPass(@Param("login") String login, @Param("pass") String pass);

    UserDao getByLogin(@Param("login") String login);

}
