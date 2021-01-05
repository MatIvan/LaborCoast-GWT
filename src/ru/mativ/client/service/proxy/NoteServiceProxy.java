package ru.mativ.client.service.proxy;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.service.NoteService;
import ru.mativ.client.service.NoteServiceAsync;
import ru.mativ.client.service.RequestService;
import ru.mativ.shared.bean.NoteBean;
import ru.mativ.shared.bean.NoteCalendarDay;
import ru.mativ.shared.bean.ReportRowBean;

public class NoteServiceProxy implements NoteServiceAsync {
    private static final RequestService requestService = LaborCoast.getRequestService();
    private static final NoteServiceAsync noteService = GWT.create(NoteService.class);

    @Override
    public RequestBuilder getByDate(Date date, AsyncCallback<List<NoteBean>> callback) {
        requestService.send(noteService.getByDate(date, callback));
        return null;
    }

    @Override
    public RequestBuilder getById(int noteId, AsyncCallback<NoteBean> callback) {
        requestService.send(noteService.getById(noteId, callback));
        return null;
    }

    @Override
    public RequestBuilder save(NoteBean noteBean, AsyncCallback<NoteBean> callback) {
        requestService.send(noteService.save(noteBean, callback));
        return null;
    }

    @Override
    public RequestBuilder getByMonth(Date date, AsyncCallback<List<NoteBean>> callback) {
        requestService.send(noteService.getByMonth(date, callback));
        return null;
    }

    @Override
    public RequestBuilder getCalendarDaysByMonth(Date date, AsyncCallback<List<NoteCalendarDay>> callback) {
        requestService.send(noteService.getCalendarDaysByMonth(date, callback));
        return null;
    }

    @Override
    public RequestBuilder getReportRowByMonth(Date date, AsyncCallback<List<ReportRowBean>> callback) {
        requestService.send(noteService.getReportRowByMonth(date, callback));
        return null;
    }
}
