package ru.mativ.server.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import ru.mativ.shared.bean.UserBean;
import ru.mativ.shared.exception.DataSaveException;

public interface UserMapper {

    int insert(@Param("user") UserBean userBean, @Param("password") String pass) throws DataSaveException;

    UserBean getByLoginPass(@Param("login") String login, @Param("pass") String pass);

    UserBean getByLogin(@Param("login") String login);
}
