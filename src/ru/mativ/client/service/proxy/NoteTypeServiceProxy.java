package ru.mativ.client.service.proxy;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.service.NoteTypeService;
import ru.mativ.client.service.NoteTypeServiceAsync;
import ru.mativ.client.service.RequestService;
import ru.mativ.shared.bean.NoteTypeBean;

public class NoteTypeServiceProxy implements NoteTypeServiceAsync {
    private static final RequestService requestService = LaborCoast.getRequestService();
    private static final NoteTypeServiceAsync noteTypeService = GWT.create(NoteTypeService.class);

    @Override
    public RequestBuilder getAll(AsyncCallback<List<NoteTypeBean>> callback) {
        requestService.send(noteTypeService.getAll(callback));
        return null;
    }
}
