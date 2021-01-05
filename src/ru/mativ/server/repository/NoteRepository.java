package ru.mativ.server.repository;

import java.util.List;
import java.util.logging.Logger;

import ru.mativ.server.mybatis.MyBatisService;
import ru.mativ.server.mybatis.dao.NoteDao;
import ru.mativ.server.mybatis.dao.ReportRowDao;
import ru.mativ.server.mybatis.mappers.NoteMapper;
import ru.mativ.shared.exception.DataSaveException;

public class NoteRepository implements NoteMapper {
    private static final Logger Log = Logger.getLogger(NoteRepository.class.getName());
    private final static NoteRepository instance = new NoteRepository();
    private NoteMapper noteMapper;

    public static NoteRepository getInstance() {
        return instance;
    }

    private NoteRepository() {
        super();
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

    @Override
    public List<NoteDao> getByUserIdAndDate(int userId, String date) {
        return mapper().getByUserIdAndDate(userId, date);
    }

    @Override
    public NoteDao getByUserIdAndNoteId(int userId, int noteId) {
        return mapper().getByUserIdAndNoteId(userId, noteId);
    }

    @Override
    public int insert(NoteDao noteDao) throws DataSaveException {
        Log.fine("Try to insert: " + noteDao);
        try {
            int rowUpdated = mapper().insert(noteDao);
            commit();
            return rowUpdated;

        } catch (Exception e) {
            rollback();
            Log.severe("Insert error: " + noteDao);
            e.printStackTrace();
            throw new DataSaveException("Can not insert note: " + e.getMessage());
        }
    }

    @Override
    public int update(NoteDao noteDao) throws DataSaveException {
        Log.fine("Try to update: " + noteDao);
        try {
            int rowUpdated = mapper().update(noteDao);
            commit();
            return rowUpdated;

        } catch (Exception e) {
            rollback();
            Log.severe("Update error: " + noteDao);
            e.printStackTrace();
            throw new DataSaveException("Can not update note: " + e.getMessage());
        }
    }

    public NoteDao save(NoteDao noteDao) throws DataSaveException {
        Log.fine("Try to save " + noteDao);
        try {
            if (noteDao.getId() == -1) {
                insert(noteDao);
            } else {
                update(noteDao);
            }
            commit();
            return noteDao;

        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            Log.severe("Save error: " + noteDao);
            throw new DataSaveException("Can not save note: " + e.getMessage());
        }
    }

    @Override
    public List<NoteDao> getByUserIdAndPeriod(int userId, String dateFrom, String dateTo) {
        return mapper().getByUserIdAndPeriod(userId, dateFrom, dateTo);
    }

    @Override
    public List<ReportRowDao> getSummByUserIdAndPeriod(int userId, String dateFrom, String dateTo) {
        return mapper().getSummByUserIdAndPeriod(userId, dateFrom, dateTo);
    }
}
