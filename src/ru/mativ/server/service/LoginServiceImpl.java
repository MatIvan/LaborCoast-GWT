package ru.mativ.server.service;

import java.util.logging.Logger;

import ru.mativ.client.service.LoginService;
import ru.mativ.client.service.exception.LoginFialException;
import ru.mativ.client.service.exception.RegistrationException;
import ru.mativ.server.repository.UserRepository;
import ru.mativ.server.session.SessionController;
import ru.mativ.server.session.UserSession;
import ru.mativ.shared.UserDto;
import ru.mativ.shared.UserSessionDto;
import ru.mativ.tools.PasswordUtil;

@SuppressWarnings("serial")
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
    private static final Logger Log = Logger.getLogger("LoginServiceImpl");

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public UserSessionDto makeToken(String login, String pass) throws LoginFialException {
        String ip = getThreadLocalRequest().getRemoteAddr();
        Log.info("makeToken > login=" + login + ", ip=" + ip);

        String encodePass = PasswordUtil.calcSHA1Hash(pass);
        UserDto user = userRepository.getUserByLoginPass(login, encodePass);
        if (user == null) {
            throw new LoginFialException("Login or Password fail.");
        }

        String token = PasswordUtil.generateToken();
        UserSession session = SessionController.instance().registerUser(token, ip, user);

        Log.info("makeToken > " + session);
        return new UserSessionDto(session.getToken(), session.getUserDto());
    }

    @Override
    public Void newUser(UserDto user, String pass) throws RegistrationException {
        String ip = getThreadLocalRequest().getRemoteAddr();
        Log.info("newUser > user=" + user + ", ip=" + ip);

        UserDto oldUser = userRepository.getUserByLogin(user.getLogin());
        if (oldUser != null) {
            throw new RegistrationException("Login is in use by another user.");
        }

        String encodePass = PasswordUtil.calcSHA1Hash(pass);
        if (!userRepository.insert(user, encodePass)) {
            throw new RegistrationException("Cannot save user to data base.");
        }

        Log.info("newUser > created");
        return null;
    }

}
