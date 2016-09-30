/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.ReceiveEmail;
import com.viettel.hqmc.BO.Register;
import com.viettel.hqmc.FORM.RegisterForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import com.viettel.common.util.LogUtil;

/**
 *
 * @author vtit_binhnt53
 */
public class RegisterDAOHE extends GenericDAOHibernate<Register, Long> {

    private static HashMap<String, List> lstFactory = new HashMap();

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        RegisterDAOHE.lstFactory = lstFactory;
    }
    private static Logger log = Logger.getLogger(Register.class);
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

    /*
     *
     */
    /**
     * findRegister
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findRegister(RegisterForm form, int start, int count, String sortField) {
        String hql = " from Register r where 1 = 1 ";
        List lstParam = new ArrayList();
        if (form != null) {
            if (form.getBusinessNameVi() != null && form.getBusinessNameVi().trim().length() > 0) {
                hql += " and lower(r.businessNameVi) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getBusinessNameVi().trim()));
            }
            if (form.getBusinessLicense() != null && form.getBusinessLicense().trim().length() > 0) {
                hql += " and lower(r.businessLicense) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getBusinessLicense().trim()));
            }
            if (form.getBusinessTaxCode() != null && form.getBusinessTaxCode().trim().length() > 0) {
                hql += " and lower(r.businessTaxCode) = ? ";
                lstParam.add(form.getBusinessTaxCode().trim());
            }
            if (form.getUserFullName() != null && form.getUserFullName().trim().length() > 0) {
                hql += " and lower(r.userFullName) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getUserFullName().trim()));
            }
            if (form.getManageEmail() != null && form.getManageEmail().trim().length() > 0) {
                hql += " and lower(r.manageEmail) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getManageEmail().trim()));
            }
            if (form.getStatus() != null && form.getStatus() >= 0) {
                hql += " and lower(r.status) = ? ";
                lstParam.add(form.getStatus());
            }
//            if (form.getPosId()!= null && form.getPosId() >= 0) {
//                hql += " and lower(r.posId) = ? ";
//                lstParam.add(form.getPosId());
//            }
        }

        Query countQuery = getSession().createQuery("select count(r.registerId ) " + hql);
        Query query = getSession().createQuery("select r " + hql + " order by r.registerId  desc");
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
     * findAllRegister
     *
     * @return
     */
    public List findAllRegister() {
        if (lstFactory == null) {
            lstFactory = new HashMap();
        }

        if (lstFactory.containsKey("type")) {
            return lstFactory.get("type");
        }

        List<Register> lstRegister = null;
//        List<CategorySearchForm> lstResult = new ArrayList();
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Register a ");
            stringBuilder.append("  where a.status = -1 "
                    + " order by nlssort(lower(ltrim(a.categoryName)),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstRegister = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<Register>();
        }
//        lstCategoryFactory.put(type, lstResult);
        lstFactory.put("type", lstRegister);

        return lstRegister;
    }

    /**
     * kiem tra doi tuong co trung khong
     *
     * @param form
     * @return
     */

    public boolean isDuplicate(RegisterForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(r) from Register r where (status = 1 or status = 0) ";

        if (form.getBusinessTaxCode() != null && form.getBusinessTaxCode().trim().length() > 0) {
            hql += " and lower(r.businessTaxCode) like ? ";
            lstParam.add(form.getBusinessTaxCode().trim().toLowerCase());
        }
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        Long count = Long.parseLong(query.uniqueResult().toString());
        return count >= 1l;
    }

    /**
     * Check Email
     *
     * @param form
     * @return
     */
    public boolean isDuplicateEmail(RegisterForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(r) from Register r where (status = 1 or status = 0) ";

        if (form.getManageEmail() != null && form.getManageEmail().trim().length() > 0) {
            hql += " and lower(r.manageEmail) like ? ";
            lstParam.add(form.getManageEmail().trim().toLowerCase());
        }
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        Long count1 = Long.parseLong(query.uniqueResult().toString());

        List lstParamUser = new ArrayList();
        String hqlUser = "select count(u.userId) from Users u where 1 = 1 ";
        if (form.getManageEmail() != null && form.getManageEmail().trim().length() > 0) {
            hqlUser += " and lower(u.email) like ? ";
            lstParamUser.add(form.getManageEmail().trim().toLowerCase());
        }
        query = getSession().createQuery(hqlUser);
        for (int i = 0; i < lstParamUser.size(); i++) {
            query.setParameter(i, lstParamUser.get(i));
        }
        Long count2 = Long.parseLong(query.uniqueResult().toString());

        return (count1 >= 1l || count2 >= 1l);
    }

    /*
     * check duplicate Business
     */
    /**
     * isDuplicateBusiness
     *
     * @param form
     * @return
     */
    public boolean isDuplicateBusiness(RegisterForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(b) from Business b where b.isActive = 1 ";
        if (form.getBusinessTaxCode() != null && form.getBusinessTaxCode().trim().length() > 0) {
            hql += " and lower(b.businessTaxCode) like ? ";
            lstParam.add(form.getBusinessTaxCode().trim().toLowerCase());
        }
        if (form.getBusinessLicense() != null && form.getBusinessLicense().trim().length() > 0) {
            hql += " and lower(b.businessLicense) like ? ";
            lstParam.add(form.getBusinessLicense().trim().toLowerCase());
        }
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }

        Long count = Long.parseLong(query.uniqueResult().toString());
        return count >= 1l;
    }/*
     * 
     */

    /**
     * isDuplicateUsers
     *
     * @param form
     * @return
     */
    public boolean isDuplicateUsers(RegisterForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(u.userId) from Users u where 1 = 1 ";
        if (form.getBusinessTaxCode() != null && form.getBusinessTaxCode().trim().length() > 0) {
            hql += " and lower(u.userName) like ? ";
            lstParam.add(form.getBusinessTaxCode().trim().toLowerCase());
        }
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        Long count = Long.parseLong(query.uniqueResult().toString());
        return count >= 1l;
    }/*
     * 
     */

    /**
     * saveToReceiveEmail
     *
     * @param register
     * @param content
     * @return
     */
    public void saveToReceiveEmail(Register register, String content) {

        ReceiveEmail recEmail = new ReceiveEmail();

        recEmail.setContent(content);
        recEmail.setEmail(register.getManageEmail());
        recEmail.setIsProcess(-3);//status register
        recEmail.setReceiveTime(new java.util.Date());
        recEmail.setMsgType(2);//cont

        getSession().save(recEmail);
    }

    public void formToBO(RegisterForm stAddEditForm, Register bo) {
    }

    public RegisterForm boToForm(Register bo) {
        RegisterForm frm = new RegisterForm(bo);
        return frm;
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

    public RegisterDAOHE() {
        super(Register.class);
    }
}
