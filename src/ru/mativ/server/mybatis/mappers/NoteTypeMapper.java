package ru.mativ.server.mybatis.mappers;

import java.util.Map;

import ru.mativ.shared.bean.NoteTypeBean;

public interface NoteTypeMapper {

    Map<Integer, NoteTypeBean> getMap();
}
