package ru.mativ.server.mybatis.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import ru.mativ.server.repository.pojo.NoteDao;

public interface NoteMapper {

    List<NoteDao> getByOwnerAndDate(@Param("ownerId") int ownerId, @Param("date") Date date);
}
