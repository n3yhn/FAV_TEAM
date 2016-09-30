/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.Announcement;
import com.viettel.hqmc.FORM.AnnouncementForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author vtit_binhnt53
 */
public class AnnouncementDAOHE extends GenericDAOHibernate<Announcement, Long> {

    private static HashMap<String, List> lstFactory = new HashMap();

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        AnnouncementDAOHE.lstFactory = lstFactory;
    }
    private static Logger log = Logger.getLogger(AnnouncementDAOHE.class);
    private List keyList = new ArrayList();
    private List valueList = new ArrayList();
    //removeCache

    public static void removeCache(String type) {
        if (lstFactory == null) {
            return;
        }
        if (lstFactory.containsKey(type)) {
            lstFactory.remove(type);
        }
    }

    /**
     * tim kiem sua doi bo sung
     *
     * @param objId
     * @param originalId
     * @param version
     * @param isLastVersion
     * @return
     */
    public Announcement findIsItempObj(Long objId, Long originalId, Long version, boolean isLastVersion) {//140726
        Announcement bo = new Announcement();
        try {

            List lstParam = new ArrayList();
            String hql = "select t from Announcement t where 1=1";
            if (objId != null && objId > 0L) {
                hql += " and t.announcementId = ?";
                lstParam.add(objId);
            }
            if (originalId != null && originalId > 0L) {
                hql += " and t.originalId = ?";
                lstParam.add(originalId);
            }
            if (version != null && version > 0L) {
                hql += " and t.version = ?";
                lstParam.add(version);
            }
            if (isLastVersion) {
                hql += " and t.lastIsTemp = ?";
                lstParam.add(1L);
            }
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }
            List<Announcement> lstObj = null;
            lstObj = query.list();
            if (!lstObj.isEmpty()) {
                bo = lstObj.get(0);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(e);
        }
        return bo;
    }

    /*
     *
     */
    /**
     * tim kiem ban cong bo
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findAnnouncement(AnnouncementForm form, int start, int count, String sortField) {
        String hql = " from Announcement t where 1 = 1 ";
        List lstParam = new ArrayList();
        if (form != null) {
        }

        Query countQuery = getSession().createQuery("select count(t.announcementNo ) " + hql);
        Query query = getSession().createQuery("select t " + hql + " order by t.announcementNo  desc");
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

    /*
     *
     */
    /**
     * tim kiem ban cong bo
     *
     * @return
     */
    public List findAllAnnouncement() {
        if (lstFactory == null) {
            lstFactory = new HashMap();
        }

        if (lstFactory.containsKey("type")) {
            return lstFactory.get("type");
        }

        List<Announcement> lstAnnouncement = null;
//        List<CategorySearchForm> lstResult = new ArrayList();
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Announcement t ");
            stringBuilder.append("  where t.isActive = 1 "
                    + " order by nlssort(lower(ltrim(t.categoryName)),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstAnnouncement = query.list();

        } catch (Exception ex) {
//            log.error(ex.getMessage());
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<Announcement>();
        }
//        lstCategoryFactory.put(type, lstResult);
        lstFactory.put("type", lstAnnouncement);

        return lstAnnouncement;
    }

    //kiem tra doi tuong co trung khong
    /**
     * kiem tra doi tuong co trung khong
     *
     * @return
     */
    public boolean isDuplicate(AnnouncementForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(t) from Announcement t where 1 = 1 ";
        if (form.getAnnouncementId() != null && form.getAnnouncementId() > 0l) {
            hql += " and t.announcementId <> ? ";
            lstParam.add(form.getAnnouncementId());
        }
        if (form.getAnnouncementNo() != null && form.getAnnouncementNo().trim().length() > 0) {
            hql += " and t.announcementNo = ? ";
            lstParam.add(form.getAnnouncementNo());
        }
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }

        Long count = Long.parseLong(query.uniqueResult().toString());
        boolean bReturn = false;
        if (count >= 1l) {
            bReturn = true;
        }
        return bReturn;
    }

    public void formToBO(AnnouncementForm stAddEditForm, Announcement bo) {
    }

    public AnnouncementForm boToForm(Announcement bo) {
        AnnouncementForm frm = new AnnouncementForm(bo);
        return frm;
    }

    /**
     * dem version
     *
     * @param objId
     * @return
     */
    public Long getCountVersion(Long objId) {
        Long iresult = 0L;
        List lstParam = new ArrayList();
        String hql = "select count(t) from Announcement t where t.originalId = ? ";
        lstParam.add(objId);
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        iresult = Long.parseLong(query.uniqueResult().toString()) + 1;
        return iresult;
    }

    /**
     * update sua doi bo sung
     *
     * @param objId
     * @return
     */
    public int updateSetNotLastIsTemp(Long objId) {
        try {
            String hql = " update Announcement t set t.lastIsTemp = 0 where t.originalId = ?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, objId);
            return query.executeUpdate();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(e);
            return 0;
        }
    }

    /*
     * =========================================================================
     */
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

    public AnnouncementDAOHE() {
        super(Announcement.class);
    }
}
