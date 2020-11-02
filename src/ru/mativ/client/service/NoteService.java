package ru.mativ.client.service;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ru.mativ.shared.bean.NoteBean;
import ru.mativ.shared.exception.DataSaveException;
import ru.mativ.shared.exception.LoginFialException;

@RemoteServiceRelativePath("note")
public interface NoteService extends RemoteService {

    List<NoteBean> getByDate(Date date) throws LoginFialException;

    NoteBean getById(int noteId) throws LoginFialException;

    NoteBean save(NoteBean noteBean) throws LoginFialException, DataSaveException;
}
