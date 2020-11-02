package ru.mativ.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ru.mativ.shared.exception.LoginFialException;

@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

    String greetServer(String name) throws LoginFialException;

}
