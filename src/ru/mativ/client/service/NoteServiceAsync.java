package ru.mativ.client.service;

import java.util.Date;
import java.util.List;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.shared.bean.NoteBean;

public interface NoteServiceAsync {

    RequestBuilder getByDate(Date date, AsyncCallback<List<NoteBean>> callback);

    RequestBuilder getById(int noteId, AsyncCallback<NoteBean> callback);

    RequestBuilder save(NoteBean noteBean, AsyncCallback<NoteBean> callback);

    RequestBuilder getByMonth(Date date, AsyncCallback<List<NoteBean>> callback);
}
