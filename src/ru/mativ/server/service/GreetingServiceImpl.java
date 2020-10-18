package ru.mativ.server.service;

import ru.mativ.client.service.GreetingService;

@SuppressWarnings("serial")
public class GreetingServiceImpl extends BaseServiceImpl implements GreetingService {

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
