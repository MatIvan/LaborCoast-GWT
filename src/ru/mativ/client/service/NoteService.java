package ru.mativ.client.service;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ru.mativ.shared.bean.NoteBean;
import ru.mativ.shared.bean.NoteCalendarDay;
import ru.mativ.shared.bean.ReportRowBean;
import ru.mativ.shared.exception.DataSaveException;
import ru.mativ.shared.exception.LoginFialException;
import ru.mativ.shared.exception.NotFoundException;

@RemoteServiceRelativePath("note")
public interface NoteService extends RemoteService {

    List<NoteBean> getByDate(Date date) throws LoginFialException, NotFoundException;

    NoteBean getById(int noteId) throws LoginFialException, NotFoundException;

    NoteBean save(NoteBean noteBean) throws LoginFialException, DataSaveException;

    List<NoteBean> getByMonth(Date date) throws LoginFialException, NotFoundException;

    List<NoteCalendarDay> getCalendarDaysByMonth(Date date) throws LoginFialException, NotFoundException;

    List<ReportRowBean> getReportRowByMonth(Date date) throws LoginFialException, NotFoundException;
}
