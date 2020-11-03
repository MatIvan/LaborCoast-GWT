package ru.mativ.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ru.mativ.shared.bean.NoteTypeBean;

@RemoteServiceRelativePath("notetype")
public interface NoteTypeService extends RemoteService {

    List<NoteTypeBean> getAll();
}
