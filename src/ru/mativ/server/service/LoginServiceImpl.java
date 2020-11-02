package ru.mativ.server.service;

import java.util.logging.Logger;

import ru.mativ.client.service.LoginService;
import ru.mativ.server.repository.UserRepository;
import ru.mativ.server.session.SessionController;
import ru.mativ.server.session.UserSession;
import ru.mativ.shared.bean.UserBean;
import ru.mativ.shared.bean.UserSessionBean;
import ru.mativ.shared.exception.LoginFialException;
import ru.mativ.shared.exception.RegistrationException;
import ru.mativ.tools.PasswordUtil;

@SuppressWarnings("serial")
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
    private static final Logger Log = Logger.getLogger(LoginServiceImpl.class.getName());

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public UserSessionBean makeToken(String login, String pass) throws LoginFialException {
        String ip = getThreadLocalRequest().getRemoteAddr();
        Log.info("makeToken > login=" + login + ", ip=" + ip);

        String encodePass = PasswordUtil.calcSHA1Hash(pass);
        UserBean user = userRepository.getByLoginPass(login, encodePass);
        if (user == null) {
            throw new LoginFialException("Login or Password fail.");
        }

        String token = PasswordUtil.generateToken();
        UserSession session = SessionController.instance().registerUser(token, ip, user);

        Log.info("makeToken > " + session);
        return new UserSessionBean(session.getToken(), session.getUserBean());
    }

    @Override
    public Void newUser(UserBean user, String pass) throws RegistrationException {
        String ip = getThreadLocalRequest().getRemoteAddr();
        Log.info("newUser > user=" + user + ", ip=" + ip);

        UserBean oldUser = userRepository.getByLogin(user.getLogin());
        if (oldUser != null) {
            throw new RegistrationException("Login is in use by another user.");
        }

        try {
            String encodePass = PasswordUtil.calcSHA1Hash(pass);
            userRepository.insert(user, encodePass);
        } catch (Exception e) {
            throw new RegistrationException("Cannot save user to data base.");
        }

        Log.info("newUser > created");
        return null;
    }
}
