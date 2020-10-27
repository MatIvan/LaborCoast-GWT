package ru.mativ.server.repository;

import ru.mativ.server.mybatis.MyBatisService;
import ru.mativ.server.mybatis.mappers.UserMapper;
import ru.mativ.shared.bean.UserBean;

public class UserRepository {

    private final static UserRepository instance = new UserRepository();
    private UserMapper userMapper;

    public static UserRepository getInstance() {
        return instance;
    }

    private UserRepository() {
    }

    private UserMapper mapper() {
        if (userMapper == null) {
            userMapper = MyBatisService.getInstance().getUserMapper();
        }
        return userMapper;
    }

    private void commit() {
        MyBatisService.getInstance().commit();
    }

    private void rollback() {
        MyBatisService.getInstance().rollback();
    }

    public UserBean getUserByLoginPass(String login, String pass) {
        if (login == null || login.isEmpty() || pass == null || pass.isEmpty()) {
            return null;
        }
        return mapper().getByLoginPass(login, pass);
    }

    public UserBean getUserByLogin(String login) {
        return mapper().getByLogin(login);
    }

    public boolean insert(UserBean userBean, String pass) {
        try {
            mapper().insert(userBean, pass);
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
