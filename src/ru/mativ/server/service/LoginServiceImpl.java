package ru.mativ.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ru.mativ.client.service.LoginService;
import ru.mativ.client.service.exception.LoginFialException;
import ru.mativ.server.ServerPasswordUtil;
import ru.mativ.server.repository.UserRepository;
import ru.mativ.server.session.SessionController;
import ru.mativ.shared.UserDto;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public String makeToken(String login, String pass) throws LoginFialException {
        String ip = getThreadLocalRequest().getRemoteAddr();
        System.out.println("LoginServiceImpl > makeToken > "
                + " login=" + login
                + ", ip=" + ip);

        String encodePass = ServerPasswordUtil.calcSHA1Hash(pass);
        UserDto user = userRepository.getUserByLoginPass(login, encodePass);
        if (user == null) {
            throw new LoginFialException("Login or Password fail.");
        }

        String token = ServerPasswordUtil.generateToken();
        SessionController.instance().put(token, ip, user);

        System.out.println("LoginServiceImpl > makeToken > " + user + ", token=" + token);

        return token;
    }

}
