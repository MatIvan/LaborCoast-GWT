package ru.mativ.server.repository;

import java.util.List;
import java.util.logging.Logger;

import ru.mativ.server.mybatis.MyBatisService;
import ru.mativ.server.mybatis.mappers.NoteMapper;
import ru.mativ.shared.bean.NoteBean;
import ru.mativ.shared.exception.DataSaveException;

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

    public NoteBean save(NoteBean noteBean) throws DataSaveException {
        Log.fine("Try to save " + noteBean);
        try {
            if (noteBean.getId().intValue() == -1) {
                mapper().insert(noteBean);
            } else {
                mapper().update(noteBean);
            }
            commit();
            Log.fine("Saved: " + noteBean);

        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            Log.severe("Save error: " + noteBean);
            throw new DataSaveException("Can not save Note: " + e.getMessage());
        }
        return noteBean;
    }
}
