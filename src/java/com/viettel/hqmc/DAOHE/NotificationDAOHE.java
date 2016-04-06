/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.BO.Notification;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.hqmc.FORM.NotificationForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author gpcp_dund1
 */
public class NotificationDAOHE extends GenericDAOHibernate<Notification, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(NotificationDAOHE.class);
//========================================================================

    public NotificationDAOHE() {
        super(Notification.class);
    }

    public GridResult findAllForHomePage(FilesForm form, int start, int count, String sortField) {
        List lstParam = new ArrayList();
        String hql = " FROM VLookupHomepage v";
        if (form != null) {

            if (form.getBusinessName() != null && !"".equals(form.getBusinessName().trim())) {
                hql += " AND lower(v.businessName) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getBusinessName().toLowerCase().trim()));
            }
            if (form.getProductName() != null && !"".equals(form.getProductName().trim())) {
                hql += " AND lower(v.productName) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getProductName().toLowerCase().trim()));
            }
        }

        //Query countQuery = getSession().createQuery("select count(v) " + hql);
        Query query = getSession().createQuery("select v " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            //countQuery.setParameter(i, lstParam.get(i));
        }

        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = 10;
        List<Files> lstResult = query.list();
        GridResult gr = new GridResult(total, lstResult);
        return gr;
    }

    public String GetNOTICE() {
        String result = "";
        try {
            String querySql = "select n.content from Notification n WHERE n.isActive = 1 order by n.sort asc";
            Query query = getSession().createQuery(querySql);
            List<String> listStr = query.list();
            if (listStr != null && listStr.size() > 0) {
                for (Integer i = 0; i < listStr.size(); i++) {
                    if (i == listStr.size() - 1) {
                        result += listStr.get(i);
                    } else {
                        result += listStr.get(i) + "<notice>";
                    }
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return result;
    }

    public GridResult findNoti(NotificationForm form, int start, int count, String sortField) {
        String hql = " from Notification n where 1=1";
        List lstParam = new ArrayList();
        if (form != null) {
            if (form.getIsActive() != null && form.getIsActive() != -1) {
                hql += " and (n.isActive = ?)";
                lstParam.add(form.getIsActive());
            }
            if (form.getSort()!= null && form.getSort()!= -1) {
                hql += " and (n.sort = ?)";
                lstParam.add(form.getSort());
            }
            if (form.getContent() != null && !"".equals(form.getContent().trim())) {
                hql += " and lower(n.content) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getContent().toLowerCase().trim()));
            }
        }
        Query countQuery = getSession().createQuery("select count(*) " + hql);
        Query query = getSession().createQuery("select n " + hql + " order by n.sort desc");
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

    //hieptq update 210115
    public boolean onCreateNotification(NotificationForm NotiForm, Long userId) {
        boolean bReturn = true;
        try {
            Notification noti = new Notification();
            Long stt = NotiForm.getIsActive();
            UsersDAOHE udhe = new UsersDAOHE();
            Users users = udhe.findById(userId);
            if (NotiForm.getNoticeId() == null) {
                String content = NotiForm.getContent().replaceAll("'[-+^]*","");
                noti.setIsActive(stt);
                noti.setContent(content);
                noti.setCreateDate(getSysdate());
                noti.setModifiedBy(users.getUserName());
                noti.setSort(NotiForm.getSort());
                getSession().save(noti);
                bReturn = true;
            } else {
                String content = NotiForm.getContent().replaceAll("'[-+^]*","");
                noti = findById(NotiForm.getNoticeId());
                noti.setContent(content);
                noti.setIsActive(stt);
                noti.setModifiedBy(users.getUserName());
                noti.setModifiedDate(getSysdate());
                noti.setSort(NotiForm.getSort());
                getSession().update(noti);
                bReturn = true;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());;
            bReturn = false;
        }
        return bReturn;
    }

}
