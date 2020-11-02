package ru.mativ.server.service;

import java.util.Date;
import java.util.List;

import ru.mativ.client.service.NoteService;
import ru.mativ.client.service.exception.LoginFialException;
import ru.mativ.server.repository.NoteRepository;
import ru.mativ.shared.bean.NoteBean;
import ru.mativ.shared.bean.UserBean;

@SuppressWarnings("serial")
public class NoteServiceImpl extends BaseServiceImpl implements NoteService {
    private NoteRepository noteRepository = NoteRepository.getInstance();

    @Override
    public List<NoteBean> getByDate(Date date) throws LoginFialException {
        UserBean currentUser = getCurrentUser();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return noteRepository.getByUserIdAndDate(currentUser.getId(), sqlDate);
    }

    @Override
    public NoteBean getById(int noteId) throws LoginFialException {
        UserBean currentUser = getCurrentUser();
        NoteBean result = noteRepository.getByUserAndId(currentUser.getId(), noteId);

        if (result == null) {
            throw new LoginFialException("Note not found or this info for other user.");
        }
        return result;
    }

    @Override
    public NoteBean save(NoteBean noteBean) throws LoginFialException {
        if (noteBean == null) {
            return null;
        }
        UserBean currentUser = getCurrentUser();
        noteBean.setUserId(currentUser.getId());

        return noteRepository.save(noteBean);
    }
}
