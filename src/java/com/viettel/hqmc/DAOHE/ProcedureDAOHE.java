/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.StringUtils;
import com.viettel.common.util.Constants;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.DAO.GridResult;
import java.util.List;
import org.hibernate.Query;

import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import com.viettel.vsaadmin.client.form.DepartmentForm;

/**
 *
 * @author Administrator
 */
public class ProcedureDAOHE extends GenericDAOHibernate<Procedure, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ProcedureDAOHE.class);

    public ProcedureDAOHE() {
        super(Procedure.class);
    }

    public List getAllProcedure() {
        List<Category> lstCategory = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Procedure a ");
            stringBuilder.append("  where a.isActive = ?"
                    + " order by a.createDate ASC");
//                    + " order by nlssort(lower(a.name),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, "1");
            lstCategory = query.list();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return lstCategory;
    }

    //Hiepvv tach rieng Sua doi sau cong bo
    public List getAllProcedure2() {
        List<Category> lstCategory = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Procedure a ");
            stringBuilder.append(" where a.isActive = ?"
                    + " and a.description != 'announcementFile05'"
                    + " order by a.createDate ASC");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, "1");
            lstCategory = query.list();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return lstCategory;
    }

    /**
     * getProcedureForChange
     *
     * @param fileType
     * @return
     */
    public List getProcedureForChange(Long fileType) {
        String typeName = "";

        Criteria cri = getSession().createCriteria(Procedure.class);
        cri.add(Restrictions.eq("procedureId", fileType));
        List<Procedure> cats = cri.list();
        if (!cats.isEmpty()) {
            typeName = cats.get(0).getDescription();
        }
        List<String> lstType = new ArrayList();
        if (typeName.equals("announcementFile01")) {
            lstType.add("confirmNormalVN");
            lstType.add("confirmFuncVN");
            lstType.add("confirmNormalImport");
            lstType.add("confirmFuncImport");
            lstType.add("announcementFile03");
        }
        if (typeName.equals("announcementFile03")) {
            lstType.add("confirmNormalVN");
            lstType.add("confirmFuncVN");
            lstType.add("confirmNormalImport");
            lstType.add("confirmFuncImport");
            lstType.add("announcementFile01");
        }
        if (typeName.equals("confirmNormalImport")) {
            lstType.add("announcementFile01");
            lstType.add("announcementFile03");
            lstType.add("confirmFuncImport");
        }
        if (typeName.equals("confirmFuncImport")) {
            lstType.add("announcementFile01");
            lstType.add("announcementFile03");
            lstType.add("confirmNormalImport");
        }
        if (typeName.equals("confirmFuncVN")) {
            lstType.add("announcementFile01");
            lstType.add("announcementFile03");
            lstType.add("confirmNormalVN");
        }
        if (typeName.equals("confirmNormalVN")) {
            lstType.add("announcementFile01");
            lstType.add("announcementFile03");
            lstType.add("confirmFuncVN");
        }
        if (typeName.equals("confirmNormalVN")) {
            lstType.add("announcementFile01");
            lstType.add("announcementFile03");
            lstType.add("confirmFuncVN");
        }
        if (typeName.equals("announcement4star")) {
            lstType.add("none");
        }

        if (typeName.equals("reConfirmNormalVN")) {
            lstType.add("reConfirmFuncVN");
        }
        if (typeName.equals("reConfirmFuncVN")) {
            lstType.add("reConfirmNormalVN");
        }
        if (typeName.equals("reAnnouncement")) {
            lstType.add("none");
        }
        if (typeName.equals("reConfirmNormalImp")) {
            lstType.add("reConfirmFuncImport");
        }
        if (typeName.equals("reConfirmFuncImport")) {
            lstType.add("reConfirmNormalImp");
        }
        if (typeName.equals("confirmSatisfactory")) {
            lstType.add("none");
        }
        List<Category> lstCategory = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Procedure a ");
            stringBuilder.append("  where a.isActive = ? "
                    + "and a.description in (:lstDescription) "
                    + "order by nlssort(lower(a.name),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, "1");
            if (!"".equals(lstType)) {
                query.setParameterList("lstDescription", lstType);
            }
            lstCategory = query.list();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return lstCategory;
    }

    // lay ho so trang thai da phe duyet
    /**
     * getAllProcedure_Status
     *
     * @return
     */
    public List getAllProcedure_Status() {
        List<Category> lstCategory = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(" from (select * from procedure"
                    + " where procedure_id = ( select file_type from files"
                    + " where status = 6))  a ");
            stringBuilder.append("  where a.isActive = ?"
                    + " order by nlssort(lower(a.name),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, "1");
            lstCategory = query.list();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return lstCategory;
    }

    /**
     * findAllCategorySearch
     *
     * @param type
     * @return
     */
    public List<Category> findAllCategorySearch(String type) {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Category a ");
            stringBuilder.append("  where a.isActive = ? and a.type=? ");
            if (("VOBQ").equals(type)) {
                stringBuilder.append(" order by a.code");
            } else {
                stringBuilder.append(" order by nlssort(lower(ltrim(a.name)),'nls_sort = Vietnamese')");
            }

            //Thoi han bao quan xep theo code
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, type);
            List<Category> lstCategory = query.list();
            Category a = new Category();
            a.setCategoryId(Constants.COMBOBOX_HEADER_VALUE);
            a.setName(Constants.COMBOBOX_HEADER_TEXT);
            List<Category> lstCategoryFull = new ArrayList();
            lstCategoryFull.add(a);
            lstCategoryFull.addAll(lstCategory);
            return lstCategoryFull;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    /**
     * getProcedureById
     *
     * @param id
     * @return
     */
    public Procedure getProcedureById(long id) throws Exception {
        Criteria cri = getSession().createCriteria(Procedure.class);
        cri.add(Restrictions.eq("procedureId", id));
        List<Procedure> cats = cri.list();
        if (cats.isEmpty()) {
            return null;
        } else {
            return cats.get(0);
        }
    }

    public boolean isDuplicate(Procedure form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(c) from Procedure c where c.isActive = 1 ";
        if (form.getProcedureId() != null && form.getProcedureId() > 0l) {
            hql += " and c.procedureId <> ? ";
            lstParam.add(form.getProcedureId());
        }
        hql += " and ( 1=0 ";
        if (form.getCode() != null && form.getCode().trim().length() > 0) {
            hql += " or c.code = ? ";
            lstParam.add(form.getCode());
        }
        if (form.getName() != null && form.getName().trim().length() > 0) {
            hql += " or lower(c.name) = ?";
            lstParam.add(form.getName().toLowerCase());
        }
        hql = hql + ")";
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
     * findProcedure
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findProcedure(Procedure form, int start, int count, String sortField) {
        String hql = " from Procedure c where 1 = 1 ";
        List lstParam = new ArrayList();
        if (form != null) {
            if (form.getCode() != null && !form.getCode().equals("")) {
                hql += " and lower(c.code) LIKE ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getCode().trim().toLowerCase()));
            }

            if (form.getName() != null && !form.getName().equals("")) {
                hql += " and lower(c.name) LIKE ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getName().trim().toLowerCase()));
            }
            if (form.getIsActive() != null && !form.getIsActive().equals("") && !form.getIsActive().equals("-1")) {
                hql += " and c.isActive = ? ";
                lstParam.add(form.getIsActive().trim());
            } else {
                hql += " and (c.isActive = '1' or c.isActive = '0')";

            }
            if (form.getType() != null && form.getType().trim().length() > 0 && form.getType().equals("0") != true) {
                hql += " and lower(c.type) like ? escape'!'";
                lstParam.add("%" + convertToLikeString(form.getType()) + "%");
            }
            if (form.getIsActive() != null && form.getIsActive().trim().length() > 0 && form.getIsActive().equals("-1") != true) {
                hql += " and lower(c.isActive) like ? escape'!'";
                lstParam.add("%" + convertToLikeString(form.getIsActive()) + "%");
            }
        }

        Query countQuery = getSession().createQuery("select count(*) " + hql);
        Query query = getSession().createQuery("select c " + hql + " order by c.name");
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
     * getAllDeptOfProcedure
     *
     * @param procedureDepartmentId
     * @param start
     * @param count
     * @return
     */
    public GridResult getAllDeptOfProcedure(Long procedureId, int start, int count) {
        String hql = " from ProcedureDepartment c where c.procedureId = ? ";
        Query countQuery = getSession().createQuery("select count(*) " + hql);
        countQuery.setParameter(0, procedureId);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());

        Query query = getSession().createQuery("select c " + hql + " order by c.deptName");
        query.setParameter(0, procedureId);
        query.setFirstResult(start);
        query.setMaxResults(count);
        List lstResult = query.list();

        GridResult gr = new GridResult(total, lstResult);
        return gr;
    }

    /**
     * getAllDeptOfProcedureCbx
     *
     * @param procedureId
     * @param start
     * @param count
     * @return
     */
    public List getAllDeptOfProcedureCbx(Long procedureId, int start, int count) {
        String hql = "";
        List lstResult = null;
        if (procedureId != -1) {
            hql = " from ProcedureDepartment c where c.procedureId = ? ";
            Query countQuery = getSession().createQuery("select count(*) " + hql);
            countQuery.setParameter(0, procedureId);
            int total = Integer.parseInt(countQuery.uniqueResult().toString());

            Query query = getSession().createQuery("select c " + hql + " "
                    + "order by c.deptName");
            query.setParameter(0, procedureId);
            query.setFirstResult(start);
            query.setMaxResults(count);
            lstResult = query.list();
        } else {
            hql = " from ProcedureDepartment c ";
            Query countQuery = getSession().createQuery("select count(*) " + hql);
            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            Query query = getSession().createQuery("select distinct c " + hql + " "
                    + "order by c.deptName");
            query.setFirstResult(start);
            query.setMaxResults(count);
            lstResult = query.list();
        }
        return lstResult;
    }

    /**
     * getAllDeptNotInProcedure
     *
     * @param procedureId
     * @param departmentForm
     * @param start
     * @param count
     * @return
     */
    public GridResult getAllDeptNotInProcedure(Long procedureId, DepartmentForm departmentForm, int start, int count) {
        GridResult gr = null;
        List lstParam = new ArrayList();
        try {
            String countHQL = "select count(a) ";
            String searchHQL = "select a";
            String hql = " from Department a where a.status = 1";
//            lstParam.add(procedureId);
            if (departmentForm != null) {
                if (departmentForm.getParentId() != null) {
                    if (!"".equals(departmentForm.getParentId())) {
                        hql = hql + " and parentId = ? ";
                        lstParam.add(departmentForm.getParentId());
                    }
                }

                if (departmentForm.getDeptName() != null && departmentForm.getDeptName().trim().length() > 0) {
                    hql += " and lower(a.deptName) like ? escape '/' ";
                    lstParam.add(StringUtils.toLikeString(departmentForm.getDeptName().trim().toLowerCase()));
                }
                if (departmentForm.getDeptCode() != null && departmentForm.getDeptCode().trim().length() > 0) {
                    hql += " and lower(a.deptCode) like ? escape '/' ";
                    lstParam.add(StringUtils.toLikeString(departmentForm.getDeptCode().trim().toLowerCase()));
                }
                if (departmentForm.getAddress() != null && departmentForm.getAddress().trim().length() > 0) {
                    hql += " and lower(a.address) like ? escape '/' ";
                    lstParam.add(StringUtils.toLikeString(departmentForm.getAddress().trim().toLowerCase()));
                }
                if (departmentForm.getTelephone() != null && departmentForm.getTelephone().trim().length() > 0) {
                    hql += " and lower(a.telephone) like ? escape '/' ";
                    lstParam.add(StringUtils.toLikeString(departmentForm.getTelephone().trim().toLowerCase()));
                }
            }
            hql = hql + "  order by nlssort(lower(a.deptName),'nls_sort = Vietnamese')";

            countHQL = countHQL + hql;
            searchHQL = searchHQL + hql;
            Query searchQuery = session.createQuery(searchHQL);
            Query countQuery = session.createQuery(countHQL);
            for (int i = 0; i < lstParam.size(); i++) {
                searchQuery.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }
            Long total = (Long) countQuery.uniqueResult();

            searchQuery.setFirstResult(start);
            searchQuery.setMaxResults(count);

            List lst = searchQuery.list();
            gr = new GridResult(total, lst);
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return gr;
    }

    /**
     * getProcedureByDescription
     *
     * @param description
     * @return
     */
    public Procedure getProcedureByDescription(String description) throws Exception {
        Criteria cri = getSession().createCriteria(Procedure.class);
        cri.add(Restrictions.eq("description", description));
        List<Procedure> cats = cri.list();
        if (cats.isEmpty()) {
            return null;
        } else {
            return cats.get(0);
        }
    }

    //hieptq update 270515
    public Procedure getProcedureTypeFee(Long fileType) {
        List<Procedure> lstResult = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Procedure p ");
            stringBuilder.append("  where p.isActive = 1 and p.procedureId = ?"
                    + " order by p.createDate ASC");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, fileType);
            lstResult = query.list();
            if (lstResult != null && lstResult.size() > 0) {
                return lstResult.get(0);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
        return null;
    }
        
    public List getAllProcedureAfterAnnounced() {
        List<Category> lstCategory = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Procedure a ");
            stringBuilder.append(" where a.isActive = ?"
                    + " and a.description = 'announcementFile05'"
                    + " order by a.createDate ASC");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, "1");
            lstCategory = query.list();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return lstCategory;
    }
}
