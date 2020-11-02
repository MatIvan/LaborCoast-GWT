package ru.mativ.server.mybatis.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ru.mativ.shared.bean.NoteBean;

public class NoteDaoConvertor {

    public static String toDaoDate(java.util.Date date) {
        if (date == null) {
            return null;
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate.toString();
    }

    public static java.util.Date fromDaoDate(String daoDate) {
        java.util.Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(daoDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static NoteDao makeNoteDao(NoteBean noteBean) {
        NoteDao noteDao = new NoteDao();
        noteDao.setId(noteBean.getId());
        noteDao.setUserId(noteBean.getUserId());
        noteDao.setTypeId(noteBean.getTypeId());
        noteDao.setNote(noteBean.getNote());
        noteDao.setComment(noteBean.getComment());
        noteDao.setDate(toDaoDate(noteBean.getDate()));
        noteDao.setHours(noteBean.getHours());
        return noteDao;
    }

    public static NoteBean makeNoteBean(NoteDao noteDao) {
        NoteBean noteBean = new NoteBean();
        noteBean.setId(noteDao.getId());
        noteBean.setUserId(noteDao.getUserId());
        noteBean.setTypeId(noteDao.getTypeId());
        noteBean.setNote(noteDao.getNote());
        noteBean.setComment(noteDao.getComment());
        noteBean.setDate(fromDaoDate(noteDao.getDate()));
        noteBean.setHours(noteDao.getHours());
        return noteBean;
    }

    public static List<NoteBean> makeNoteBeanList(List<NoteDao> noteDaoList) {
        List<NoteBean> resultList = new ArrayList<>();
        for (NoteDao noteDao : noteDaoList) {
            resultList.add(makeNoteBean(noteDao));
        }
        return resultList;
    }
}
