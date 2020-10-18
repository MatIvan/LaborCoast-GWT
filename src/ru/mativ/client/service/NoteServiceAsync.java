package ru.mativ.client.service;

import java.util.Date;
import java.util.List;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.shared.NoteDto;

public interface NoteServiceAsync {

    RequestBuilder getByDate(Date date, AsyncCallback<List<NoteDto>> callback);
}
