package ru.mativ.server.repository;

import java.util.List;
import java.util.logging.Logger;

import ru.mativ.server.mybatis.MyBatisService;
import ru.mativ.server.mybatis.mappers.NoteMapper;
import ru.mativ.shared.bean.NoteBean;

public class NoteRepository {
    private static final Logger Log = Logger.getLogger(NoteRepository.class.getName());
    private final static NoteRepository instance = new NoteRepository();
    private NoteMapper noteMapper;

    public static NoteRepository getInstance() {
        return instance;
    }

    private NoteRepository() {
    }

    private NoteMapper mapper() {
        if (noteMapper == null) {
            noteMapper = MyBatisService.getInstance().getNoteMapper();
        }
        return noteMapper;
    }

    private void commit() {
        MyBatisService.getInstance().commit();
    }

    private void rollback() {
        MyBatisService.getInstance().rollback();
    }

    public List<NoteBean> getByUserIdAndDate(int userId, java.sql.Date date) {
        List<NoteBean> result = mapper().getByUserIdAndDate(userId, date.toString());
        return result;
    }

    public NoteBean getByUserAndId(int userId, int noteId) {
        NoteBean result = mapper().getByUserIdAndNoteId(userId, noteId);
        return result;
    }
}
