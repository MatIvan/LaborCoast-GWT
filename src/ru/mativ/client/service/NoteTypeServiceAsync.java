package ru.mativ.client.service;

import java.util.Map;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.shared.bean.NoteTypeBean;

public interface NoteTypeServiceAsync {

    RequestBuilder getMap(AsyncCallback<Map<Integer, NoteTypeBean>> callback);
}
