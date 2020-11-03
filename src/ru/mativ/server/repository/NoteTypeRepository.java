package ru.mativ.server.repository;

import java.util.List;
import java.util.logging.Logger;

import ru.mativ.server.mybatis.MyBatisService;
import ru.mativ.server.mybatis.mappers.NoteTypeMapper;
import ru.mativ.shared.bean.NoteTypeBean;

public class NoteTypeRepository implements NoteTypeMapper {
    private static final Logger Log = Logger.getLogger(NoteTypeRepository.class.getName());
    private final static NoteTypeRepository instance = new NoteTypeRepository();
    private NoteTypeMapper noteTypeMapper;

    public static NoteTypeRepository getInstance() {
        return instance;
    }

    public NoteTypeRepository() {
        super();
    }

    private NoteTypeMapper mapper() {
        if (noteTypeMapper == null) {
            noteTypeMapper = MyBatisService.getInstance().getNoteTypeMapper();
        }
        return noteTypeMapper;
    }

    private void commit() {
        MyBatisService.getInstance().commit();
    }

    private void rollback() {
        MyBatisService.getInstance().rollback();
    }

    @Override
    public List<NoteTypeBean> getAll() {
        return mapper().getAll();
    }
}
