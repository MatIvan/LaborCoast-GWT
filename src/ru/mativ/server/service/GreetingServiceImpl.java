package ru.mativ.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ru.mativ.client.service.GreetingService;
import ru.mativ.client.service.exception.LoginFialException;
import ru.mativ.server.session.UserSession;
import ru.mativ.shared.UserDto;

@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    private UserDto getCurrentUser() throws LoginFialException {
        UserDto userDto;
        try {
            UserSession session = (UserSession) getThreadLocalRequest().getUserPrincipal();
            userDto = session.getUserDto();
        } catch (Exception e) {
            throw new LoginFialException("Bad user.");
        }
        return userDto;
    }

    public String greetServer(String input) {
        StringBuilder res = new StringBuilder("Hello, ")
                .append(input)
                .append("!<br><br> Your data: ");
        try {
            res.append(getCurrentUser().toString());
        } catch (Exception e) {
            res.append(e.getMessage());
        }
        return res.toString();
    }

}
