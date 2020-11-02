package ru.mativ.server.service;

import java.util.Date;
import java.util.List;

import ru.mativ.client.service.NoteService;
import ru.mativ.server.mybatis.dao.NoteDao;
import ru.mativ.server.mybatis.dao.NoteDaoConvertor;
import ru.mativ.server.repository.NoteRepository;
import ru.mativ.shared.bean.NoteBean;
import ru.mativ.shared.bean.UserBean;
import ru.mativ.shared.exception.DataSaveException;
import ru.mativ.shared.exception.LoginFialException;
import ru.mativ.shared.exception.NotFoundException;

@SuppressWarnings("serial")
public class NoteServiceImpl extends BaseServiceImpl implements NoteService {
    private NoteRepository noteRepository = NoteRepository.getInstance();

    @Override
    public List<NoteBean> getByDate(Date date) throws LoginFialException, NotFoundException {
        UserBean currentUser = getCurrentUser();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        List<NoteDao> noteDaoList = noteRepository.getByUserIdAndDate(currentUser.getId(), sqlDate.toString());
        if (noteDaoList == null) {
            throw new NotFoundException("Note not found.");
        }

        return NoteDaoConvertor.makeNoteBeanList(noteDaoList);
    }

    @Override
    public NoteBean getById(int noteId) throws LoginFialException, NotFoundException {
        UserBean currentUser = getCurrentUser();
        NoteDao result = noteRepository.getByUserIdAndNoteId(currentUser.getId(), noteId);

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
        UserBean currentUser = getCurrentUser();
        noteBean.setUserId(currentUser.getId());

        NoteDao noteDao = NoteDaoConvertor.makeNoteDao(noteBean);
        NoteDao result = noteRepository.save(noteDao);
        return NoteDaoConvertor.makeNoteBean(result);
    }
}
