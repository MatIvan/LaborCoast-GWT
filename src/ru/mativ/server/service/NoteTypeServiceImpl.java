package ru.mativ.server.service;

import java.util.List;

import ru.mativ.client.service.NoteTypeService;
import ru.mativ.server.repository.NoteTypeRepository;
import ru.mativ.shared.bean.NoteTypeBean;

@SuppressWarnings("serial")
public class NoteTypeServiceImpl extends BaseServiceImpl implements NoteTypeService {
    private NoteTypeRepository noteTypeRepository = NoteTypeRepository.getInstance();

    @Override
    public List<NoteTypeBean> getAll() {
        return noteTypeRepository.getAll();
    }
}
