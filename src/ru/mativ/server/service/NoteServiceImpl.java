package ru.mativ.server.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import ru.mativ.client.service.NoteService;
import ru.mativ.client.service.exception.LoginFialException;
import ru.mativ.server.repository.NoteRepository;
import ru.mativ.shared.bean.NoteBean;
import ru.mativ.shared.bean.UserBean;

@SuppressWarnings("serial")
public class NoteServiceImpl extends BaseServiceImpl implements NoteService {
    private static final Logger Log = Logger.getLogger("LoginServiceImpl");

    private NoteRepository noteRepository = NoteRepository.getInstance();

    @Override
    public List<NoteBean> getByDate(Date date) throws LoginFialException {
        UserBean currentUser = getCurrentUser();
        return noteRepository.getByUserIdAndDate(currentUser.getId(), date);
    }
}
