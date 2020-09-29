package ru.mativ.server.repository;

import ru.mativ.server.ServerPasswordUtil;
import ru.mativ.shared.UserDto;

public class UserRepository {

    private final static UserRepository instance = new UserRepository();

    private final UserDto testUser = new UserDto("test", "Test Man", "test@mail.ru");
    private final String testPass = ServerPasswordUtil.calcSHA1Hash("TestPass");

    public static UserRepository getInstance() {
        return instance;
    }

    private UserRepository() {

    }

    public UserDto getUserByLoginPass(String login, String pass) {
        if (login == null || login.isEmpty() || pass == null || pass.isEmpty()) {
            return null;
        }

        if (login.equals(testUser.getLogin()) && pass.equals(testPass)) {
            return testUser;
        }

        return null;
    }
}
