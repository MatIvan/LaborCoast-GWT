package ru.mativ.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ru.mativ.client.service.LoginService;
import ru.mativ.client.service.exception.LoginFialException;
import ru.mativ.server.repository.UserRepository;
import ru.mativ.server.session.SessionController;
import ru.mativ.server.session.UserSession;
import ru.mativ.shared.UserDto;
import ru.mativ.shared.UserSessionDto;
import ru.mativ.tools.AppConfig;
import ru.mativ.tools.PasswordUtil;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public UserSessionDto makeToken(String login, String pass) throws LoginFialException {
        System.out.println("GREET: " + AppConfig.applicationGreetingMessage());

        String ip = getThreadLocalRequest().getRemoteAddr();
        System.out.println("LoginServiceImpl > makeToken > "
                + " login=" + login
                + ", ip=" + ip);

        String encodePass = PasswordUtil.calcSHA1Hash(pass);
        UserDto user = userRepository.getUserByLoginPass(login, encodePass);
        if (user == null) {
            throw new LoginFialException("Login or Password fail.");
        }

        String token = PasswordUtil.generateToken();
        UserSession session = SessionController.instance().registerUser(token, ip, user);

        System.out.println("LoginServiceImpl > makeToken > " + session);
        return new UserSessionDto(session.getToken(), session.getUserDto());
    }

}
