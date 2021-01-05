package ru.mativ.server.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ru.mativ.server.mybatis.dao.NoteDao;
import ru.mativ.server.mybatis.dao.ReportRowDao;
import ru.mativ.shared.exception.DataSaveException;

public interface NoteMapper {

    List<NoteDao> getByUserIdAndDate(@Param("userId") int userId, @Param("date") String date);

    NoteDao getByUserIdAndNoteId(@Param("userId") int userId, @Param("noteId") int noteId);

    int insert(NoteDao noteDao) throws DataSaveException;

    int update(NoteDao noteDao) throws DataSaveException;

    List<NoteDao> getByUserIdAndPeriod(@Param("userId") int userId, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo);

    List<ReportRowDao> getSummByUserIdAndPeriod(@Param("userId") int userId, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo);
}
