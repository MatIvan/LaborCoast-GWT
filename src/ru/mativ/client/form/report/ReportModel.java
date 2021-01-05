package ru.mativ.client.form.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.mativ.shared.bean.ReportRowBean;

public class ReportModel {
    private static final int TYPE_TICKET = 1;
    private static final int TYPE_MITING = 2;
    private static final int TYPE_VACATION = 3;

    private Date date;
    private List<ReportRowBean> tickets;
    private int mitingHourse;
    private int vacationHourse;
    private int summHourse;

    public ReportModel() {
        tickets = new ArrayList<ReportRowBean>();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ReportRowBean> getTickets() {
        return tickets;
    }

    public void setTickets(List<ReportRowBean> tickets) {
        this.tickets = tickets;
    }

    public int getMitingHourse() {
        return mitingHourse;
    }

    public void setMitingHourse(int mitingHourse) {
        this.mitingHourse = mitingHourse;
    }

    public int getVacationHourse() {
        return vacationHourse;
    }

    public void setVacationHourse(int vacationHourse) {
        this.vacationHourse = vacationHourse;
    }

    public int getSummHourse() {
        return summHourse;
    }

    public void setSummHourse(int summHourse) {
        this.summHourse = summHourse;
    }

    public void fill(Date date, List<ReportRowBean> list) {
        this.date = date;
        for (ReportRowBean bean : list) {
            if (bean.getTypeId() == null) {
                continue;
            }
            switch (bean.getTypeId().intValue()) {
                case TYPE_TICKET:
                    tickets.add(bean);
                    summHourse += bean.getHoursSum();
                    break;
                case TYPE_MITING:
                    mitingHourse += bean.getHoursSum();
                    break;
                case TYPE_VACATION:
                    vacationHourse += bean.getHoursSum();
                    break;
                default:
                    // do nothing
                    break;
            }
        }
        summHourse += mitingHourse + vacationHourse;
    }

    public static ReportModel make(Date date, List<ReportRowBean> list) {
        ReportModel model = new ReportModel();
        model.fill(date, list);
        return model;
    }
}
