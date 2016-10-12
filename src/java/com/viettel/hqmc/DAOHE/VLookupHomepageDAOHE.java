/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.BO.VLookupHomepage;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author gpcp_dund1
 */
public class VLookupHomepageDAOHE extends GenericDAOHibernate<VLookupHomepage, Long> {

    private static Logger log = Logger.getLogger(VLookupHomepage.class);
//========================================================================

    public VLookupHomepageDAOHE() {
        super(VLookupHomepage.class);
    }

    public GridResult findAllForHomePage(FilesForm form, int start, int count, String sortField) {
        List lstParam = new ArrayList();
        String hql = " FROM VLookupHomepage v where 1=1";
        if (form != null) {

            if (form.getBusinessName() != null && !"".equals(form.getBusinessName().trim())) {
                hql += " AND lower(v.businessName) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getBusinessName().toLowerCase().trim()));
            }
            if (form.getProductName() != null && !"".equals(form.getProductName().trim())) {
                hql += " AND lower(v.productName) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getProductName().toLowerCase().trim()));
            }
            if (form.getBusinessAddress() != null && !"".equals(form.getBusinessAddress().trim())) {
                hql += " AND lower(v.businessAddress) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getBusinessAddress().toLowerCase().trim()));
            }
            if (form.getAnnouncementNo() != null && !"".equals(form.getAnnouncementNo().trim())) {
                hql += " AND lower(v.receiptNo) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getAnnouncementNo().toLowerCase().trim()));
            }
            if (form.getFileTypeName() != null && !"-1".equals(form.getFileTypeName().trim())) {
                if ("TPCN".equals(form.getFileTypeName()) || "DBT".equals(form.getFileTypeName())) {
                    hql += " AND v.code like ? ";
                    lstParam.add(form.getFileTypeName());
                } else {
                    hql += " AND v.code not like 'TPCN' and v.code not like 'DBT' ";
                }
            }
//            if (form.getSendDateTo() != null) {
//                hql += " AND v.receiptDate=? ";
//                lstParam.add(form.getSendDateTo());
//            }
            if (form.getSendDateFrom()!= null) {
                hql += " AND v.receiptDate >= ?";
                lstParam.add(minDayToCompare(form.getSendDateFrom()));
            }
            if (form.getSendDateTo() != null) {
                hql += " AND v.receiptDate <= ?";
                lstParam.add(maxDayToCompare(form.getSendDateTo()));
            }
        }

        Query countQuery = getSession().createQuery("select count(v) " + hql);
        Query query = getSession().createQuery("select v " + hql + " order by v.receiptDate DESC");
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            countQuery.setParameter(i, lstParam.get(i));
        }

        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List<Files> lstResult = query.list();
        GridResult gr = new GridResult(total, lstResult);
        return gr;
    }
}
