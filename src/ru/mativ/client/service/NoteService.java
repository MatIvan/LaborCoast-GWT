package ru.mativ.client.service;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ru.mativ.client.service.exception.LoginFialException;
import ru.mativ.shared.bean.NoteBean;

@RemoteServiceRelativePath("note")
public interface NoteService extends RemoteService {

    List<NoteBean> getByDate(Date date) throws LoginFialException;
}
