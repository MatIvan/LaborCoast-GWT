package ru.mativ.server.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.mativ.client.service.NoteService;
import ru.mativ.server.mybatis.dao.NoteDao;
import ru.mativ.server.mybatis.dao.NoteDaoConvertor;
import ru.mativ.server.mybatis.dao.ReportRowDao;
import ru.mativ.server.repository.NoteRepository;
import ru.mativ.shared.bean.NoteBean;
import ru.mativ.shared.bean.NoteCalendarDay;
import ru.mativ.shared.bean.ReportRowBean;
import ru.mativ.shared.exception.DataSaveException;
import ru.mativ.shared.exception.LoginFialException;
import ru.mativ.shared.exception.NotFoundException;
import ru.mativ.tools.StringDateUtil;

@SuppressWarnings("serial")
public class NoteServiceImpl extends BaseServiceImpl implements NoteService {
    private NoteRepository noteRepository = NoteRepository.getInstance();

    @Override
    public List<NoteBean> getByDate(Date date) throws LoginFialException, NotFoundException {
        int userId = getCurrentUser().getId();
        String day = StringDateUtil.getDay(date);

        List<NoteDao> noteDaoList = noteRepository.getByUserIdAndDate(userId, day);
        if (noteDaoList == null) {
            throw new NotFoundException("Note not found.");
        }
        return NoteDaoConvertor.makeNoteBeanList(noteDaoList);
    }

    @Override
    public NoteBean getById(int noteId) throws LoginFialException, NotFoundException {
        int userId = getCurrentUser().getId();
        NoteDao result = noteRepository.getByUserIdAndNoteId(userId, noteId);

        if (result == null) {
            throw new NotFoundException("Note not found.");
        }
        return NoteDaoConvertor.makeNoteBean(result);
    }

    @Override
    public NoteBean save(NoteBean noteBean) throws LoginFialException, DataSaveException {
        if (noteBean == null) {
            return null;
        }
        int userId = getCurrentUser().getId();
        noteBean.setUserId(userId);

        NoteDao noteDao = NoteDaoConvertor.makeNoteDao(noteBean);
        NoteDao result = noteRepository.save(noteDao);
        return NoteDaoConvertor.makeNoteBean(result);
    }

    @Override
    public List<NoteBean> getByMonth(Date date) throws LoginFialException, NotFoundException {
        int userId = getCurrentUser().getId();
        String dateFrom = StringDateUtil.getFirstDayOfMonth(date);
        String sateTo = StringDateUtil.getLastDayOfMonth(date);

        List<NoteDao> noteDaoList = noteRepository.getByUserIdAndPeriod(userId, dateFrom, sateTo);
        if (noteDaoList == null) {
            throw new NotFoundException("Notes not found.");
        }
        return NoteDaoConvertor.makeNoteBeanList(noteDaoList);
    }

    @Override
    public List<NoteCalendarDay> getCalendarDaysByMonth(Date date) throws LoginFialException, NotFoundException {
        List<NoteBean> notesByMonth = getByMonth(date);
        return makeNoteCalendarDayList(date, notesByMonth);
    }

    private List<NoteCalendarDay> makeNoteCalendarDayList(Date date, List<NoteBean> notesByMonth) {
        //Key Integer - DayOfMonth
        Map<Integer, NoteCalendarDay> monthMap = makeMonthMap(date);

        for (NoteBean noteBean : notesByMonth) {
            Integer day = StringDateUtil.getDayOfMonth(noteBean.getDate());
            NoteCalendarDay noteCalendarDay = monthMap.get(day);
            if (noteCalendarDay == null) {
                noteCalendarDay = makeNoteCalendarDay(noteBean.getDate());
                monthMap.put(day, noteCalendarDay);
            }
            noteCalendarDay.getNoteList().add(noteBean);
            noteCalendarDay.increaseHoursSumm(noteBean.getHours());
        }
        return new ArrayList<>(monthMap.values());
    }

    private Map<Integer, NoteCalendarDay> makeMonthMap(Date date) {
        List<Integer> allMonthDays = StringDateUtil.getAllMonthDays(date);
        Map<Integer, NoteCalendarDay> monthMap = new HashMap<>();
        for (Integer day : allMonthDays) {
            NoteCalendarDay noteCalendarDay = makeNoteCalendarDay(day, date);
            monthMap.put(day, noteCalendarDay);
        }
        return monthMap;
    }

    private NoteCalendarDay makeNoteCalendarDay(int day, Date anyMonthDate) {
        Calendar calendar = StringDateUtil.getCalendar(anyMonthDate);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return makeNoteCalendarDay(calendar.getTime());
    }

    private NoteCalendarDay makeNoteCalendarDay(Date date) {
        NoteCalendarDay result = new NoteCalendarDay();

        Calendar calendar = StringDateUtil.getCalendar(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // Normalize becouse USA Sunday it is number 1.
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);

        result.setDayOfWeek(dayOfWeek);
        result.setDayOfMonth(dayOfMonth);
        result.setWeekOfMonth(weekOfMonth);
        result.setDate(date);
        result.setNoteList(new ArrayList<NoteBean>());
        result.setHoursSumm(0);
        return result;
    }

    @Override
    public List<ReportRowBean> getReportRowByMonth(Date date) throws LoginFialException, NotFoundException {
        int userId = getCurrentUser().getId();
        String dateFrom = StringDateUtil.getFirstDayOfMonth(date);
        String sateTo = StringDateUtil.getLastDayOfMonth(date);

        List<ReportRowDao> reportDaoList = noteRepository.getSummByUserIdAndPeriod(userId, dateFrom, sateTo);
        if (reportDaoList == null) {
            throw new NotFoundException("Notes not found.");
        }
        return NoteDaoConvertor.makeReportRowBeanList(reportDaoList);
    }
}
