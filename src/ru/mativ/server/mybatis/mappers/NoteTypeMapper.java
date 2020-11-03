package ru.mativ.server.mybatis.mappers;

import java.util.List;

import ru.mativ.shared.bean.NoteTypeBean;

public interface NoteTypeMapper {

    List<NoteTypeBean> getAll();
}
