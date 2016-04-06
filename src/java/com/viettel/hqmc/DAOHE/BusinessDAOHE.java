/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.Business;
import com.viettel.hqmc.FORM.BusinessForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.List;
import org.hibernate.Query;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class BusinessDAOHE extends GenericDAOHibernate<Business, Long> {

    public BusinessDAOHE() {
        super(Business.class);
    }

    /**
     * tim kiem doanh nghiep
     *
     * @return
     */
    public List<Business> getAllBusiness() {
        String hql = " from Business b where b.isActive=1";
        Query query = getSession().createQuery("select b " + hql + " order by b.businessName");
        List lstResult = query.list();
        return lstResult;
    }

    /**
     * tim kiem doanh nghiep
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findBusiness(BusinessForm form, int start, int count, String sortField) {
        String hql = " from Business b where b.isActive <> -1";
        List lstParam = new ArrayList();
        if (form != null) {
            if (form.getBusinessName() != null && form.getBusinessName().trim().length() > 0) {
                hql += " and lower(b.businessName) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getBusinessName().trim()));
            }
            if (form.getBusinessTaxCode() != null && form.getBusinessTaxCode().trim().length() > 0) {
                hql += " and lower(b.businessTaxCode) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getBusinessTaxCode().trim()));
            }
            if (form.getBusinessLicense() != null && form.getBusinessLicense().trim().length() > 0) {
                hql += " and lower(b.businessLicense) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getBusinessLicense().trim()));
            }
            if (form.getUserFullname() != null && form.getUserFullname().trim().length() > 0) {
                hql += " and lower(b.userFullname) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getUserFullname().trim()));
            }
        }

        Query countQuery = getSession().createQuery("select count(b) " + hql);
        Query query = getSession().createQuery("select b " + hql + " order by b.businessId desc");
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

    /**
     * tra ve true neu co ban ghi bi trung, tra ve false neu ko bi trung
     *
     * @param form
     * @return
     */
    public boolean isDuplicate(BusinessForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(b) from Business b where b.isActive = 1 ";
        if (form.getBusinessId() != null && form.getBusinessId() > 0l) {
            hql += " and b.businessId <> ? ";
            lstParam.add(form.getBusinessId());
        }

        if (form.getBusinessTaxCode() != null && form.getBusinessTaxCode().trim().length() > 0) {
            hql += " and lower(b.businessTaxCode) = ?";
            lstParam.add(form.getBusinessTaxCode().toLowerCase());
        }
        if (form.getBusinessLicense() != null && form.getBusinessLicense().trim().length() > 0) {
            hql += " and lower(b.businessLicense) = ?";
            lstParam.add(form.getBusinessLicense().toLowerCase());
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

    /**
     * kiem tra ton tai
     *
     * @param provinceID
     * @return
     */
    public boolean isExistProvince(Long provinceID) {
        boolean ck = false;
        String hql = "select count(b) from Business b where b.businessProvinceId = ? ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, provinceID);
        Long count = Long.parseLong(query.uniqueResult().toString());
        if (count >= 1l) {
            ck = true;
        }
        return ck;
    }

    /**
     * tim kiem theo ten
     *
     * @param name
     * @return
     */
    public Business findByName(String name) {
        // Build sql
        String sqlBuilder = "SELECT b FROM Business b WHERE b.isActive = 1 and lower(b.businessName) LIKE:businessName ESCAPE '/'";

        Query query = session.createQuery(sqlBuilder);
        query.setParameter("businessName", StringUtils.toLikeString(name));
        List<Business> lsObject = query.list();
        if (lsObject != null && lsObject.size() > 0) {
            return lsObject.get(0);
        }
        return null;
    }

        /**
     * tim kiem theo ma so thue
     *
     * @param businessTaxCode
     * @return
     */
    public Business findByBusinessTaxCode(String businessTaxCode) {
        // Build sql
        String sqlBuilder = "SELECT b FROM Business b WHERE b.isActive = 1 and lower(b.businessTaxCode) LIKE:businessTaxCode ESCAPE '/'";

        Query query = session.createQuery(sqlBuilder);
        query.setParameter("businessTaxCode", StringUtils.toLikeString(businessTaxCode));
        List<Business> lsObject = query.list();
        if (lsObject != null && lsObject.size() > 0) {
            return lsObject.get(0);
        }
        return null;
    }
}
