package ru.mativ.server.repository;

import java.util.logging.Logger;

import ru.mativ.server.mybatis.MyBatisService;
import ru.mativ.server.mybatis.mappers.UserMapper;
import ru.mativ.shared.bean.UserBean;
import ru.mativ.shared.exception.DataSaveException;

public class UserRepository implements UserMapper {
    private static final Logger Log = Logger.getLogger(UserRepository.class.getName());
    private final static UserRepository instance = new UserRepository();
    private UserMapper userMapper;

    public static UserRepository getInstance() {
        return instance;
    }

    private UserRepository() {
        super();
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

    @Override
    public int insert(UserBean userBean, String pass) throws DataSaveException {
        Log.fine("Try to insert: " + userBean);
        try {
            int rowUpdated = mapper().insert(userBean, pass);
            commit();
            return rowUpdated;

        } catch (Exception e) {
            rollback();
            Log.severe("Insert error: " + userBean);
            e.printStackTrace();
            throw new DataSaveException("Can not insert user: " + e.getMessage());
        }
    }

    @Override
    public UserBean getByLoginPass(String login, String pass) {
        if (login == null || login.isEmpty() || pass == null || pass.isEmpty()) {
            return null;
        }
        return mapper().getByLoginPass(login, pass);
    }

    @Override
    public UserBean getByLogin(String login) {
        return mapper().getByLogin(login);
    }

}
