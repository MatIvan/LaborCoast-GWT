package ru.mativ.server.service;

import java.util.Date;
import java.util.List;

import ru.mativ.client.service.NoteService;
import ru.mativ.server.mybatis.dao.NoteDao;
import ru.mativ.server.mybatis.dao.NoteDaoConvertor;
import ru.mativ.server.repository.NoteRepository;
import ru.mativ.shared.bean.NoteBean;
import ru.mativ.shared.exception.DataSaveException;
import ru.mativ.shared.exception.LoginFialException;
import ru.mativ.shared.exception.NotFoundException;
import ru.mativ.shared.utils.StringDateUtil;

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
}
