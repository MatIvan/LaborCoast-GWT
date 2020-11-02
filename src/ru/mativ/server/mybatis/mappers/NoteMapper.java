package ru.mativ.server.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ru.mativ.shared.bean.NoteBean;

public interface NoteMapper {

    List<NoteBean> getByUserIdAndDate(@Param("userId") int userId, @Param("date") String date);

    NoteBean getByUserIdAndNoteId(@Param("userId") int userId, @Param("noteId") int noteId);

    int insert(NoteBean noteBean);

    int update(NoteBean noteBean);
}
