/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.Constants;
import com.viettel.hqmc.BO.TimeProcess;
import com.viettel.hqmc.FORM.TimeProcessForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author binhnt53
 */
public class TimeProcessDAOHE extends GenericDAOHibernate<TimeProcess, Long> {

    private static Logger log = Logger.getLogger(TimeProcess.class);
    private List keyList = new ArrayList();
    private List valueList = new ArrayList();
    private static HashMap<String, List> lstFactory = new HashMap();
    private List lstStandard;
//========================================================================

    /**
     * findAllUserAttach
     * @param UserId
     * @return
     */
    public List findAllUserAttach(Long UserId) {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from TimeProcess u ");
            stringBuilder.append("where u.isActive=1");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstStandard = query.list();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ArrayList<>();
        }
        return lstStandard;
    }

    /**
     *
     * @param bo
     * @return
     */
    public TimeProcessForm boToForm(TimeProcess bo) {
        TimeProcessForm frm = new TimeProcessForm(bo);
        return frm;
    }

    /**
     *
     * @param userId
     * @param start
     * @param nRecord
     * @param sortField
     * @return
     */
    public GridResult getLstUserAttach(Long userId, int start, int nRecord, String sortField) {
        List<TimeProcessForm> list = new ArrayList<>();
        List lstParam = new ArrayList();
        try {
            String countHql = "SELECT count(u) ";
            String selectHQL = "SELECT u";
            String hql = " FROM TimeProcess u WHERE (u.isActive=1) and (u.status = 1) and (u.createdBy = ?)";
            hql += " ORDER BY u.createDate ";
            Query query = getSession().createQuery(selectHQL + hql);
            Query countQuery = getSession().createQuery(countHql + hql);
            lstParam.add(userId);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }
            Long nCount = (Long) countQuery.uniqueResult();
            query.setFirstResult(start);
            query.setMaxResults(nRecord);
            List<TimeProcess> lst = query.list();
            for (TimeProcess bo : lst) {
                list.add(boToForm(bo));
            }
            GridResult result = new GridResult(nCount, list);
            return result;
        } catch (Exception e) {
            e.getMessage();
            return new GridResult(0, null);
        }
    }

    /**
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findTimeProcess(TimeProcessForm form, int start, int count, String sortField) {
        String hql = " from TimeProcess u where (u.isActive <> -1) ";
        List lstParam = new ArrayList();
        if (form != null) {
//            if (form.getTimeProcessDate() != null) {
//                hql += " and lower(u.timeProcessDate) = ?";
//                lstParam.add(form.getTimeProcessDate());
//            }
            if (form.getTimeProcessDateFrom() != null) {
                hql += " and u.timeProcessDate >= ?";
                lstParam.add(form.getTimeProcessDateFrom());
            }
            if (form.getTimeProcessDateTo() != null) {
                hql += " and u.timeProcessDate <= ?";
                lstParam.add(form.getTimeProcessDateTo());
            }
            if (form.getIsDayOff() != null && form.getIsDayOff() > -1) {
                hql += " and lower(u.isDayOff) = ? ";
                lstParam.add(form.getIsDayOff());
            }
            if (form.getIsActive() != null && form.getIsActive() != -1) {
                hql += " and u.isActive = ? ";
                lstParam.add(form.getIsActive());
            }
        }
        Query countQuery = getSession().createQuery("select count(*) " + hql);
        Query query = getSession().createQuery("select u " + hql + " order by u.timeProcessId desc");
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            countQuery.setParameter(i, lstParam.get(i));
        }

        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List lstResult = query.list();
        GridResult gr = new GridResult(total, lstResult);
        return gr;
    }

    //

    /**
     *kiem tra doi tuong co trung khong   
     * @param form
     * @return
     */
        public boolean isDuplicate(TimeProcessForm form) {
        if (form == null) {
            return false;
        }
        Date timeProcessDateTo = form.getTimeProcessDateTo();
        Date timeProcessDateFrom = form.getTimeProcessDateFrom();
        List lstParam = new ArrayList();
        String hql = "select count(t) from TimeProcess t where t.isActive = 1 ";
        if (form.getTimeProcessId() != null && form.getTimeProcessId() > 0l) {
            hql += " and t.timeProcessId <> ? ";
            lstParam.add(form.getTimeProcessId());
        }
        if (form.getTimeProcessDateFrom() == form.getTimeProcessDateTo()) {
            hql += " and lower(t.timeProcessDate) = ?";
            lstParam.add(form.getTimeProcessDateFrom());
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }
            Long count = Long.parseLong(query.uniqueResult().toString());
            if (count >= 1l) {
                return true;
            }
        } else {
            if (timeProcessDateFrom.before(timeProcessDateTo)) {
                if (findDateWork(timeProcessDateFrom, timeProcessDateTo) > 0 && form.getIsDayOff() == 0) {
                    return true;
                }
                if (findDateWorkOff(timeProcessDateFrom, timeProcessDateTo) > 0 && form.getIsDayOff() == 1) {
                    return true;
                }
            }
        }
//        Query query = getSession().createQuery(hql);
//        for (int i = 0; i < lstParam.size(); i++) {
//            query.setParameter(i, lstParam.get(i));
//        }
//        Long count = Long.parseLong(query.uniqueResult().toString());
//        boolean bReturn = false;
//        if (count >= 1l) {
//            bReturn = true;
//        }
        return false;
    }

    /**
     *
     * @param userAttachsId
     * @return
     */
    public List<TimeProcess> getAttachsByObject(Long userAttachsId) {
        List<TimeProcess> voAttachsList = new ArrayList<TimeProcess>();
        try {
            StringBuilder strBuf = new StringBuilder("from TimeProcess u where (u.isActive=1) and (u.status = 1)");
            strBuf.append(" and u.userAttachsId = ?");
            Query query = getSession().createQuery("SELECT a " + strBuf.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            if (userAttachsId != null) {
                query.setParameter(1, userAttachsId);
            } else {
                query.setParameter(1, -1L);
            }
            voAttachsList = query.list();

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return voAttachsList;
    }

    /**
     *
     * @param date
     * @return
     */
    public static Calendar DateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     *
     * @param calendar
     * @return
     */
    public static Date CalendarToDate(Calendar calendar) {
        Date date = calendar.getTime();
        return date;
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    public int findDateWorkOff(Date start, Date end) {
        String hql = " from TimeProcess t where t.isActive = 1 and t.isDayOff = 0 ";
        List lstParam = new ArrayList();

        if (start != null) {
            hql += " and t.timeProcessDate > ?";
            lstParam.add(start);
        }
        if (end != null) {
            hql += " and t.timeProcessDate <= ?";
            lstParam.add(end);
        }
        Query countQuery = getSession().createQuery("select count(t.timeProcessId) " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            countQuery.setParameter(i, lstParam.get(i));
        }
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        return total;
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    public int findDateWork(Date start, Date end) {
        String hql = " from TimeProcess t where t.isActive = 1 and t.isDayOff = 1 ";
        List lstParam = new ArrayList();

        if (start != null) {
            hql += " and t.timeProcessDate >= ?";
            lstParam.add(start);
        }
        if (end != null) {
            hql += " and t.timeProcessDate <= ?";
            lstParam.add(end);
        }
        Query countQuery = getSession().createQuery("select count(t.timeProcessId) " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            countQuery.setParameter(i, lstParam.get(i));
        }
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        return total;
    }

    /**
     *
     * @param dayCheck
     * @return
     */
    public boolean checkIsDayOff(Date dayCheck) {
        boolean ck = false;
        String hql = " from TimeProcess t where t.isActive = 1 and t.isDayOff = 0";
        List lstParam = new ArrayList();
        if (dayCheck != null) {
            hql += " and t.timeProcessDate = ?";
            lstParam.add(dayCheck);
        }
        Query countQuery = getSession().createQuery("select count(t.timeProcessId) " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            countQuery.setParameter(i, lstParam.get(i));
        }
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        if (total > 0) {
            ck = true;
        }
        return ck;
    }

//==============================================================================
    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        TimeProcessDAOHE.log = log;
    }

    public List getKeyList() {
        return keyList;
    }

    public void setKeyList(List keyList) {
        this.keyList = keyList;
    }

    public List getValueList() {
        return valueList;
    }

    public void setValueList(List valueList) {
        this.valueList = valueList;
    }

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        TimeProcessDAOHE.lstFactory = lstFactory;
    }

    public TimeProcessDAOHE() {
        super(TimeProcess.class);
    }

    public static void removeCache(String type) {
        if (lstFactory == null) {
            return;
        }
        if (lstFactory.containsKey(type)) {
            lstFactory.remove(type);
        }
    }
}
