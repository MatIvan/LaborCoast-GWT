package ru.mativ.client.service;

import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;

import ru.mativ.shared.bean.NoteTypeBean;

public interface NoteTypeService extends RemoteService {

    Map<Integer, NoteTypeBean> getMap();
}
