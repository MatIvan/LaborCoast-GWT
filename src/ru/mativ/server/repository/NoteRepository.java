package ru.mativ.server.repository;

import java.util.Date;
import java.util.List;

import ru.mativ.server.mybatis.MyBatisService;
import ru.mativ.server.mybatis.mappers.NoteMapper;
import ru.mativ.shared.bean.NoteBean;

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

    public List<NoteBean> getByUserIdAndDate(int userId, Date date) {
        if (date == null) {
            return null;
        }
        return mapper().getByUserIdAndDate(userId, date);
    }

}
