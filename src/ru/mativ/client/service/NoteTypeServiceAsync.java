package ru.mativ.client.service;

import java.util.List;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.shared.bean.NoteTypeBean;

public interface NoteTypeServiceAsync {

    RequestBuilder getAll(AsyncCallback<List<NoteTypeBean>> callback);
}
