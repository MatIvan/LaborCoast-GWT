package ru.mativ.server.repository;

import ru.mativ.server.mybatis.MyBatisService;
import ru.mativ.server.mybatis.mappers.UserMapper;
import ru.mativ.server.repository.pojo.UserDao;
import ru.mativ.shared.UserDto;

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

    public static UserDto makeUserDto(UserDao userDao) {
        if (userDao == null) {
            return null;
        }
        return new UserDto(
                userDao.getId(),
                userDao.getLogin(),
                userDao.getName(),
                userDao.getMail());
    }

    public UserDto getUserByLoginPass(String login, String pass) {
        if (login == null || login.isEmpty() || pass == null || pass.isEmpty()) {
            return null;
        }
        return makeUserDto(mapper().getByLoginPass(login, pass));
    }

    public UserDto getUserByLogin(String login) {
        return makeUserDto(mapper().getByLogin(login));
    }

    public boolean insert(UserDto userDto, String pass) {
        try {
            mapper().insert(new UserDao(
                    userDto.getLogin(),
                    userDto.getName(),
                    userDto.getMail(),
                    pass));
            commit();
        } catch (Exception e) {
            rollback();
            return false;
        }
        return true;
    }
}
