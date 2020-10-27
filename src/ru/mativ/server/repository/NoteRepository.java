package ru.mativ.server.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.mativ.server.mybatis.MyBatisService;
import ru.mativ.server.mybatis.mappers.NoteMapper;
import ru.mativ.server.repository.pojo.NoteDao;
import ru.mativ.shared.NoteDto;

public class NoteRepository {

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

    public static NoteDto makeNoteDto(NoteDao noteDao) {
        if (noteDao == null) {
            return null;
        }
        NoteDto result = new NoteDto();
        result.setId(noteDao.getId());
        result.setTypeId(noteDao.getTypeId());
        result.setNote(noteDao.getNote());
        result.setComment(noteDao.getComment());
        result.setDate(noteDao.getDate());
        result.setHours(noteDao.getHours());
        return result;
    }

    public List<NoteDto> getByOwnerAndDate(int userId, Date date) {
        if (date == null) {
            return null;
        }
        List<NoteDao> list = mapper().getByOwnerAndDate(userId, date);
        List<NoteDto> result = new ArrayList<>();
        for (NoteDao noteDao : list) {
            result.add(makeNoteDto(noteDao));
        }

        return result;
    }

}
