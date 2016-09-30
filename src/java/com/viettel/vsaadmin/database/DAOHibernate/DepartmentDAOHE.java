/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.vsaadmin.database.DAOHibernate;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.common.util.StringUtils;
//import com.viettel.hqmc.FORM.RegisterForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import com.viettel.vsaadmin.client.form.DepartmentForm;
import com.viettel.vsaadmin.database.BO.Department;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import com.viettel.vsaadmin.database.BO.DeptType;
import com.viettel.vsaadmin.database.BO.VDepartment;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * DepartmentDAOHibernate
 *
 * @author trungnq7@viettel.com.vn
 * @since Apr 6, 2011
 * @version 1.0
 */
public class DepartmentDAOHE extends GenericDAOHibernate<Department, Long> {

    /**
     * log tool.
     */
    private static Logger log = Logger.getLogger(DepartmentDAOHE.class);
    private static HashMap<Long, List> lstFactory = new HashMap();

    public static void removeCache() {
        if (lstFactory == null) {
            return;
        }
        lstFactory.clear();
    }
    /**
     * .
     */
    private final int maxResult = 10;
    /**
     * .
     */
    private final int batchSize = 1000;
    private List keyList = new ArrayList();
    private List valueList = new ArrayList();

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

    public DepartmentDAOHE() {
        super(Department.class);
    }
    private Department headerDept = new Department();

    public String convertToJSONArray(List<DepartmentForm> array) {
        JSONArray jsonArray = new JSONArray();
        String id = "deptId";
        String searchAttr1 = "deptName";
        String searchAttr2 = "deptCode";
        try {
            for (DepartmentForm o : array) {
                Class cls = o.getClass();
                String getMethodName;
                Method getMethod;
                JSONObject jsonObj = new JSONObject();

                getMethodName = "get" + UpcaseFirst(id);
                getMethod = cls.getMethod(getMethodName);
                String idValue = getMethod.invoke(o).toString();
                jsonObj.put("id", idValue);

                getMethodName = "get" + UpcaseFirst(searchAttr1);
                getMethod = cls.getMethod(getMethodName);
                String search1 = getMethod.invoke(o).toString();
                jsonObj.put("search1", search1);

                getMethodName = "get" + UpcaseFirst(searchAttr2);
                getMethod = cls.getMethod(getMethodName);

                if (getMethod.invoke(o) != null) {
                    String search2 = getMethod.invoke(o).toString();
                    jsonObj.put("search2", search2);
                }

                jsonArray.add(jsonObj);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            System.out.print(e.getMessage());
        }
        return jsonArray.toString();
    }

    /**
     *
     * @return
     */
    public Department getHeaderDeptSelect() {
        headerDept = new Department();
        headerDept.setDeptId(Constants.COMBOBOX_HEADER_VALUE);
        headerDept.setDeptName(Constants.COMBOBOX_HEADER_TEXT_SELECT);
        return headerDept;
    }

    /**
     *
     * @return
     */
    public Department getHeaderDeptSearch() {
        headerDept = new Department();
        headerDept.setDeptId(Constants.COMBOBOX_HEADER_VALUE);
        headerDept.setDeptName(Constants.COMBOBOX_HEADER_TEXT);
        return headerDept;
    }

    /**
     * find all user
     *
     * @param firstResult firstResult
     * @param maxResult maxResult
     * @return list<Users>
     */
    public Boolean checkNameExisted(String officerNumber) {
        keyList.add("deptName");
        valueList.add(officerNumber);
        return this.checkEntityExistedForInsert("deptName", officerNumber);
    }

    /**
     * find all user
     *
     * @param firstResult firstResult
     * @param maxResult maxResult
     * @return list<Users>
     */
    public Boolean checkCodeExisted(String officerNumber) {
        keyList.add("deptCode");
        valueList.add(officerNumber);
        return this.checkEntityExistedForInsert("deptCode", officerNumber);
    }

    /**
     * tao moi bo
     *
     * @param departmentForm
     * @param bo
     * @param isUpdate
     * @return
     * @throws Exception
     */
    public Department createBO(DepartmentForm departmentForm, Department bo, Boolean isUpdate)
            throws Exception {
        Department temp = bo;
        try {
            temp.setAddress(departmentForm.getAddress());
            temp.setDeptCode(departmentForm.getDeptCode());
            temp.setContactName(departmentForm.getContactName());
            temp.setContactTitle(departmentForm.getContactTitle());

            if (!isUpdate.booleanValue()) {
                temp.setCreateDate(getSysdate());
            }

            temp.setDeptName(departmentForm.getDeptName());
            temp.setDescription(departmentForm.getDescription());
            temp.setEmail(departmentForm.getEmail());
            temp.setFax(departmentForm.getFax());

            temp.setTel(departmentForm.getTel());
            temp.setTelephone(departmentForm.getTelephone());
            temp.setTin(departmentForm.getTin());
            temp.setProvinceId(departmentForm.getProvinceId());
            temp.setProvinceName(departmentForm.getProvinceName());

            if ((departmentForm.getStatus() != null) && (!departmentForm.getStatus().equals("")) && (!"ALL".equals(departmentForm.getStatus()))) {
                temp.setStatus(new Long(departmentForm.getStatus()));
            }

            if ((departmentForm.getDeptTypeId() != null) && (!departmentForm.getDeptTypeId().equals(""))) {
                temp.setDeptTypeId(new Long(departmentForm.getDeptTypeId()));
            }

            if ((departmentForm.getParentId() != null) && (!departmentForm.getParentId().equals("")) && (!departmentForm.getParentId().equals(departmentForm.getDeptId()))) {
                temp.setParentId(new Long(departmentForm.getParentId()));
            } else if (isUpdate.booleanValue()) {
                temp.setParentId(null);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            throw ex;
        }
        return temp;
    }

    /**
     * find all
     *
     * @param firstResult firstResult
     * @param maxResult maxResult
     * @return list<Users>
     */
    public List<Department> findAll(int firstResult, int maxResult) {
        return this.findByCriteria(firstResult, maxResult);
    }

    /**
     * check ton tai trong db
     *
     * @param deptCode
     * @return
     */
    public Boolean checkDeptExisted(String deptCode) {
        keyList.add("deptCode");
        valueList.add(deptCode);
        return this.isExistIDInDb(keyList, valueList);
    }

    /**
     * check ton tai trong db cho truong hop update
     *
     * @param deptCode
     * @param deptId
     * @return
     */
    public Boolean checkDeptExistedForUpdate(String deptCode, Long deptId) {

        return this.checkEntityExistedForUpdate("deptId", "deptCode", deptCode, deptId);
    }

    /**
     * check ton tai trong db cho truong hop update
     *
     * @param deptCode
     * @param deptId
     * @return
     */
    public Boolean checkDeptNameExistedForUpdate(String name, Long deptId) {

        return this.checkEntityExistedForUpdate("deptId", "deptName", name, deptId);
    }

    /**
     * tim kiem
     *
     * @param departmentForm
     * @return List<Department>
     */
    public GridResult searchGrid(DepartmentForm departmentForm, int start, int count) {
        List listParam = new ArrayList();
        GridResult result = new GridResult();
        try {
            String countHQL = "select count(a) ";
            String searchHQL = "Select a ";
            String hql = " from Department a where 1=1 ";

            if ((departmentForm.getAddress() != null) && (!"".equals(departmentForm.getAddress()))) {
                hql = hql + " and lower(address) like ?";
                listParam.add("%" + departmentForm.getAddress().toLowerCase() + "%");
            }

            if ((departmentForm.getDeptCode() != null) && (!"".equals(departmentForm.getDeptCode()))) {
                hql = hql + " and lower(code) like ?";
                listParam.add("%" + departmentForm.getDeptCode().toLowerCase() + "%");
            }

            if ((departmentForm.getDeptName() != null) && (!"".equals(departmentForm.getDeptName()))) {
                hql = hql + " and lower(deptName) like ?";
                listParam.add("%" + departmentForm.getDeptName().toLowerCase() + "%");
            }

            if ((departmentForm.getStatus() != null) && (!"".equals(departmentForm.getStatus())) && (!"ALL".equals(departmentForm.getStatus()))) {
                hql = hql + " and status = ?";
                listParam.add(new Long(departmentForm.getStatus()));
            }

            if ((departmentForm.getTelephone() != null) && (!"".equals(departmentForm.getTelephone()))) {
                hql = hql + " and lower(telephone) like ?";
                listParam.add("%" + departmentForm.getTelephone().toLowerCase() + "%");
            }

            if ((departmentForm.getDeptTypeId() != null) && (!"".equals(departmentForm.getDeptTypeId()))) {
                Long type = new Long(departmentForm.getDeptTypeId());
                if (type.longValue() != 0L) {
                    hql = hql + " and deptTypeId = ?";
                    listParam.add(type);
                }

            }
            searchHQL = searchHQL + hql;
            countHQL = countHQL + hql;
            Query searchQuery = getSession().createQuery(searchHQL);
            Query countQuery = getSession().createQuery(countHQL);
            for (int i = 0; i < listParam.size(); i++) {
                searchQuery.setParameter(i, listParam.get(i));
                countQuery.setParameter(i, listParam.get(i));
            }
            Long total = (Long) countQuery.uniqueResult();
            searchQuery.setFirstResult(start);
            searchQuery.setMaxResults(count);
            List lst = searchQuery.list();
            result = new GridResult(total, lst);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return result;
    }

    public GridResult searchGrid(Long deptId, int start, int count) {
        List listParam = new ArrayList();
        GridResult result = new GridResult();
        try {
            String countHQL = "select count(a) ";
            String searchHQL = "Select a ";
            String hql = " from Department a where a.parentId = ? and a.status = 1 "
                    + " order by nlssort(lower(a.deptName),'nls_sort = Vietnamese') ";
            listParam.add(deptId);
            searchHQL = searchHQL + hql;
            countHQL = countHQL + hql;
            Query searchQuery = getSession().createQuery(searchHQL);
            Query countQuery = getSession().createQuery(countHQL);
            for (int i = 0; i < listParam.size(); i++) {
                searchQuery.setParameter(i, listParam.get(i));
                countQuery.setParameter(i, listParam.get(i));
            }
            Long total = (Long) countQuery.uniqueResult();
            searchQuery.setFirstResult(start);
            searchQuery.setMaxResults(count);
            List lst = searchQuery.list();
            result = new GridResult(total, lst);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return result;
    }

    public List<DepartmentForm> search(DepartmentForm departmentForm) {
        List listParam = new ArrayList();
        List<Department> lst = null;
        List<DepartmentForm> result = new ArrayList<DepartmentForm>();
        try {
            String searchHQL = "Select a ";
            String hql = " from Department a where 1=1 ";

            if ((departmentForm.getAddress() != null) && (!"".equals(departmentForm.getAddress()))) {
                hql = hql + " and lower(address) like ?";
                listParam.add("%" + departmentForm.getAddress().toLowerCase() + "%");
            }

            if ((departmentForm.getDeptCode() != null) && (!"".equals(departmentForm.getDeptCode()))) {
                hql = hql + " and lower(code) like ?";
                listParam.add("%" + departmentForm.getDeptCode().toLowerCase() + "%");
            }

            if ((departmentForm.getDeptName() != null) && (!"".equals(departmentForm.getDeptName()))) {
                hql = hql + " and lower(deptName) like ?";
                listParam.add("%" + departmentForm.getDeptName().toLowerCase() + "%");
            }

            if ((departmentForm.getStatus() != null) && (!"".equals(departmentForm.getStatus())) && (!"ALL".equals(departmentForm.getStatus()))) {
                hql = hql + " and status = ?";
                listParam.add(new Long(departmentForm.getStatus()));
            }

            if ((departmentForm.getTelephone() != null) && (!"".equals(departmentForm.getTelephone()))) {
                hql = hql + " and lower(telephone) like ?";
                listParam.add("%" + departmentForm.getTelephone().toLowerCase() + "%");
            }

            if ((departmentForm.getDeptTypeId() != null) && (!"".equals(departmentForm.getDeptTypeId()))) {
                Long type = new Long(departmentForm.getDeptTypeId());
                if (type.longValue() != 0L) {
                    hql = hql + " and deptTypeId = ?";
                    listParam.add(type);
                }

            }
            hql = hql + " order by nlssort(lower(deptName),'nls_sort = Vietnamese') ";
            searchHQL = searchHQL + hql;
            Query searchQuery = getSession().createQuery(searchHQL);
            for (int i = 0; i < listParam.size(); i++) {
                searchQuery.setParameter(i, listParam.get(i));
            }
            lst = searchQuery.list();

            //escapeHTML
            if (lst != null && lst.size() > 0) {
                for (Department dept : lst) {
                    DepartmentForm deptForm = new DepartmentForm(dept);
                    result.add(deptForm);
                }
            }
            for (int i = 0; i < result.size(); i++) {
                for (int j = 0; j < result.size(); j++) {
                    if (i != j && result.get(j).getDeptName() != null
                            && result.get(j).getDeptName().equals(result.get(i).getDeptName())) {
                        if (result.get(j).getParentId() != null) {
                            String deptName = getNameWithParentName(result.get(j).getDeptId());
                            result.get(j).setDeptName(deptName);
                        }
                    }
                }
            }
//            if (result != null && result.size() > 0) {
//                for (int i = 0; i < result.size(); i++) {
//                    String deptName = result.get(i).getDeptName();
//                    deptName = StringUtils.escapeHtml(deptName);
//                    if (deptName.length() > 300) {
//                        deptName = deptName.substring(0, 299);
//                    }
//                    result.get(i).setDeptName(deptName);
//                }
//            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }

        return result;
    }

    public List<DepartmentForm> searchDeptByIds(String ids) {
        String[] idLst = ids.split(";");
        List<Long> idLstLong = new ArrayList<Long>();
        for (int i = 0; i < idLst.length; i++) {
            if (idLst[i] != null && !"".equals(idLst[i])) {
                idLstLong.add(Long.valueOf(idLst[i]));
            }
        }

        String hql = "select d from Department d where d.deptId in (:deptListId)";
        Query query = getSession().createQuery(hql);
        query.setParameterList("deptListId", idLstLong);
        List<Department> lst;
        List<DepartmentForm> result = new ArrayList<DepartmentForm>();
        lst = query.list();

        //escapeHTML
        if (lst != null && lst.size() > 0) {
            for (Department dept : lst) {
                DepartmentForm deptForm = new DepartmentForm(dept);
                result.add(deptForm);
            }
        }
        return result;
    }

    // tim kiem don vi con cua don vi
    public List<DepartmentForm> search(Long deptId) {
        List listParam = new ArrayList();
        List<Department> lst = null;
        List<DepartmentForm> result = new ArrayList<DepartmentForm>();
        try {
            String searchHQL = "Select a ";
            String hql = " from Department a where a.parentId = ?"
                    // + " order by a.deptTypeId, nlssort(lower(ltrim(a.deptName)),'nls_sort = Vietnamese') ";
                    + " order by nlssort(lower(ltrim(a.deptName)),'nls_sort = Vietnamese') ";

            listParam.add(deptId);
            searchHQL = searchHQL + hql;
            Query searchQuery = getSession().createQuery(searchHQL);
            for (int i = 0; i < listParam.size(); i++) {
                searchQuery.setParameter(i, listParam.get(i));
            }

            //escapeHTML
            lst = searchQuery.list();
            if (lst != null && lst.size() > 0) {
                for (Department dept : lst) {
                    DepartmentForm deptForm = new DepartmentForm(dept);
                    result.add(deptForm);
                }
            }

//            for (int i = 0; i < result.size(); i++) {
//                for (int j = 0; j < result.size(); j++) {
//                    if (i != j && result.get(j).getDeptName() != null
//                            && result.get(j).getDeptName().equals(result.get(i).getDeptName())) {
//                        if (result.get(j).getParentId() != null) {
//                            String deptName = getNameWithParentName(result.get(j).getDeptId());
//                            result.get(j).setDeptName(deptName);
//                        }
//                    }
//                }
//            }
            if (result != null && result.size() > 0) {
                for (int i = 0; i < result.size(); i++) {
                    String deptName = result.get(i).getDeptName();
                    deptName = StringUtils.escapeHtml(deptName);
                    if (deptName.length() > 300) {
                        deptName = deptName.substring(0, 299);
                    }
                    result.get(i).setDeptName(deptName);
                }
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return null;
        }

        return result;
    }

    // tim kiem don vi con cua don vi
    public List<DepartmentForm> searchCATTP(Long deptId) {
        List listParam = new ArrayList();
        List<Department> lst = null;
        List<DepartmentForm> result = new ArrayList<DepartmentForm>();
        try {
            String searchHQL = "Select a ";
            String hql = " from Department a where (a.parentId = ? and a.deptTypeId not in (Select dt.deptTypeId from DeptType dt where lower(dt.description) like ?)) "
                    + "or (a.parentId in (select c.deptId from Department c where c.deptTypeId in (Select dt.deptTypeId from DeptType dt where lower(dt.description) like ?) and c.parentId = ?))"
                    + " order by nlssort(lower(ltrim(a.deptName)),'nls_sort = Vietnamese')";
            listParam.add(deptId);
            listParam.add("%virtual%");
            listParam.add("%virtual%");
            listParam.add(deptId);

            searchHQL = searchHQL + hql;
            Query searchQuery = getSession().createQuery(searchHQL);
            for (int i = 0; i < listParam.size(); i++) {
                searchQuery.setParameter(i, listParam.get(i));
            }
//            lst = searchQuery.list();

            //escapeHTML
            lst = searchQuery.list();
            if (lst != null && lst.size() > 0) {
                for (Department dept : lst) {
                    DepartmentForm deptForm = new DepartmentForm(dept);
                    result.add(deptForm);
                }
            }
//            if (result != null && result.size() > 0) {
//                for (int i = 0; i < result.size(); i++) {
//                    String deptName = result.get(i).getDeptName();
//                    deptName = StringUtils.escapeHtml(deptName);
//                    if (deptName.length() > 300) {
//                        deptName = deptName.substring(0, 299);
//                    }
//                    result.get(i).setDeptName(deptName);
//                }
//            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return null;
        }

        return result;
    }

    /**
     *
     * @return
     */
    public List<Department> getParentDeptForTree(Boolean isCheckStatus) {
        Criterion[] criterion = new Criterion[3];
        criterion[0] = Restrictions.isNull("parentId");
        if (isCheckStatus) {
            criterion[1] = Restrictions.eq("status", 1L);
        } else {
            criterion[1] = Restrictions.isNotNull("deptId");
        }
        criterion[2] = Restrictions.sqlRestriction(" 1 = 1 order by nlssort(lower({alias}.DEPT_NAME),'nls_sort = Vietnamese') ");
        return this.findByCriteria(0, -1, criterion);
    }

    /**
     *
     * @return
     */
    public List<Department> getParentDeptForTree(List<Long> listDeptId, String... type) {
        Criterion[] criterion = new Criterion[4];
        criterion[0] = Restrictions.isNull("parentId");
        criterion[1] = Restrictions.in("deptId", listDeptId);
        criterion[2] = Restrictions.eq("status", (short) 1);
        criterion[3] = Restrictions.sqlRestriction(" 1 = 1 order by nlssort(lower({alias}.DEPT_NAME),'nls_sort = Vietnamese') ");
        return this.findByCriteria(0, -1, criterion);
    }

    public List<Department> getAllFalculty() {
        String[] deptCodes = {"K"};
        List<Long> listDeptTypeId = getDeptTypeIds(deptCodes);
        Criterion[] criterion = new Criterion[2];
        criterion[0] = Restrictions.in("deptTypeId", listDeptTypeId);
        criterion[1] = Restrictions.eq("status", (short) 1);
        return this.findByCriteria(0, -1, criterion);
    }

    public List<Department> getAllSubjectOfFalculty(long parentId) {
        String[] deptCodes = {"B", "K"};
        List<Long> listDeptTypeId = getDeptTypeIds(deptCodes);
        Criterion[] criterion = new Criterion[3];
        criterion[0] = Restrictions.eq("parentId", parentId);
        criterion[1] = Restrictions.in("deptTypeId", listDeptTypeId);
        criterion[2] = Restrictions.eq("status", (short) 1);
        return this.findByCriteria(0, -1, criterion);
    }

    /**
     *
     * @return
     */
    public List<Department> getDeptListByParentId(Long parentId, Boolean isCheckStatus) {

        Criterion[] criterion = new Criterion[3];
        criterion[0] = Restrictions.eq("parentId", parentId);
        if (isCheckStatus) {
            criterion[1] = Restrictions.eq("status", 1L);
        } else {
            criterion[1] = Restrictions.isNotNull("deptId");
        }
//            criterion[1] = Restrictions.ne(idField, entityId);
        //criteria.addOrder(Order.asc("name"));
        criterion[2] = Restrictions.sqlRestriction(" 1 = 1 order by nlssort(lower({alias}.DEPT_NAME),'nls_sort = Vietnamese') ");
        return this.findByCriteria(0, -1, criterion);
    }

    /**
     * public List<Department> getDeptListByParentId(Long parentId, Long
     * deptTypeId, Boolean isCheckStatus) {
     *
     * DeptType deptType = findDeptTypeBOByDeptTypeId(deptTypeId); String hql =
     * "select d from Department d where d.status = 1 and d.parentId =" +
     * parentId; if (deptType != null && "B".equals(deptType.getCode())) {
     * String searchHql = "select d from DeptType d where d.code ='K'"; Query
     * searchKQuery = getSession().createQuery(searchHql); List lstDeptType =
     * searchKQuery.list(); if (lstDeptType == null || lstDeptType.size() == 0)
     * { hql += "and true = false"; } else { DeptType fType = (DeptType)
     * lstDeptType.get(0); searchHql = "select v.path from VDepartment v where
     * v.deptTypeId = " + fType.getDeptTypeId(); Query searchQuery =
     * getSession().createQuery(searchHql); List lstPath = searchQuery.list();
     * String path = ""; if (lstPath != null && lstPath.size() > 0) { for (int i
     * = 0; i < lstPath.size(); i++) { String deptPath = (String)
     * lstPath.get(i); path += deptPath; } } path = path.replace('/', ','); path
     * = path.replace(",,", ","); if (!path.equals("")) { path = "(" + path +
     * ")"; path = path.replace("(,", "("); path = path.replace(",)", ")"); hql
     * += " and d.deptId in " + path; } else { hql += " and true = false"; } } }
     * else { } Query query = getSession().createQuery(hql); List lstResult =
     * query.list(); return lstResult; // criterion[1] =
     * Restrictions.ne(idField, entityId); }
     *
     */
    /**
     * cho treepicker
     *
     * @param parentId
     * @param isCheckStatus
     * @param deptTypeId
     * @return
     */
    /*
     * public List<Department> getDeptListByParentId(Long parentId, Long
     * deptTypeId) { Criterion[] criterion = new Criterion[3]; criterion[0] =
     * Restrictions.eq("parentId", parentId); criterion[1] =
     * Restrictions.eq("status", (short) 1); DeptType deptType =
     * findDeptTypeBOByDeptTypeId(deptTypeId); if (deptType != null) { String
     * code = deptType.getCode(); if ("B".equals(code)) { criterion[2] =
     * Restrictions.eq("deptTypeId",
     * findDeptTypeBOByDeptTypeCode("K").getDeptTypeId()); } else { criterion[2]
     * = Restrictions.isNotNull("deptId"); } } return this.findByCriteria(0, -1,
     * criterion); }
     */
    /**
     * tree picker
     *
     * @return
     */
    /*
     * public List<Department> getParentDeptForTree(Long deptTypeId) { DeptType
     * deptType = findDeptTypeBOByDeptTypeId(deptTypeId); String hql = "select d
     * from Department d where d.status = 1 and d.parentId is null "; List
     * lstResult = null; if (deptType != null && "B".equals(deptType.getCode()))
     * { String searchHql = "select d from DeptType d where d.code ='K'"; Query
     * searchKQuery = getSession().createQuery(searchHql); List lstDeptType =
     * searchKQuery.list(); if (lstDeptType == null || lstDeptType.size() == 0)
     * { hql += "and true = false"; } else { searchHql = "select v.path from
     * VDepartment v where v.deptTypeId = " + deptType.getDeptTypeId(); Query
     * searchQuery = getSession().createQuery(searchHql); List lstPath =
     * searchQuery.list(); String path = ""; if (lstPath != null &&
     * lstPath.size() > 0) { for (int i = 0; i < lstPath.size(); i++) { String
     * deptPath = (String) lstPath.get(i); path += deptPath; } } path =
     * path.replace('/', ','); path = path.replace(",,", ","); if
     * (!path.equals("")) { path = "(" + path + ")"; path = path.replace("(,",
     * "("); path = path.replace(",)", ")"); hql += " and d.deptId in " + path;
     * } else { hql += " and true = false"; } } } Query query =
     * getSession().createQuery(hql); lstResult = query.list(); return
     * lstResult; }
     */
    /**
     *
     * @return
     */
    public List<Department> getDeptListByParentId(Long parentId, List<Long> listDeptId, List<Long> deptTypeIds) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(" SELECT d FROM Department d");
        strBuilder.append(" WHERE d.status =:ACTIVE");
        if (parentId != null) {
            strBuilder.append(" AND d.parentId =:parentId");
        } else {
            strBuilder.append(" AND d.parentId IS NULL");
        }
        if (listDeptId != null && listDeptId.size() > 0) {
            strBuilder.append(" AND d.deptId IN (:deptIds)");
        }
        if (deptTypeIds != null && deptTypeIds.size() > 0) {
            strBuilder.append(" AND d.deptTypeId IN (:deptTypeIds)");
        }
        strBuilder.append(" order by nlssort(lower(d.deptName),'nls_sort = Vietnamese')");

        Query query = getSession().createQuery(strBuilder.toString());
        query.setParameter("ACTIVE", Constants.Status.ACTIVE);
        if (parentId != null) {
            query.setParameter("parentId", parentId);
        }
        if (listDeptId != null && listDeptId.size() > 0) {
            query.setParameterList("deptIds", listDeptId);
        }
        if (deptTypeIds != null && deptTypeIds.size() > 0) {
            query.setParameterList("deptTypeIds", deptTypeIds);
        }
//            criterion[1] = Restrictions.ne(idField, entityId);
        return query.list();
    }

    /**
     * linhdx Lay ra danh sach he de chon co them tat ca
     *
     * @return
     */
    public List<Department> findFullListDepartment() {
        Query query = getSession().createQuery("select a from Department a , DeptType b " + "where a.deptTypeId = b.deptTypeId and lower(b.code) like 'h' "
                + " order nlssort(lower(a.deptName),'nls_sort = Vietnamese')");
        List<Department> lstDepartment = query.list();
        Department a = new Department();
        a.setDeptId(Constants.COMBOBOX_HEADER_VALUE);
        a.setDeptName(Constants.COMBOBOX_HEADER_TEXT);
        List<Department> lstDepartment2 = new ArrayList();
        lstDepartment2.add(a);
        lstDepartment2.addAll(lstDepartment);
        return lstDepartment2;
    }

    /**
     * linhdx Lay ra danh sach he de chon co them tat ca
     *
     * @return
     */
    public List<Department> findListDepartment() {
        Query query = getSession().createQuery("select a from Department a , DeptType b "
                + " where a.deptTypeId = b.deptTypeId and lower(b.code) like 'h'");
        List<Department> lstDepartment = query.list();
        return lstDepartment;
    }

    /**
     * linhdx Lay ra danh sach khoa de chon co them tat ca
     *
     * @return
     */
    public List<Department> findFullListFaculty() {
        Query query = getSession().createQuery("select a from Department a , DeptType b " + "where a.deptTypeId = b.deptTypeId and lower(b.code) like 'k'");
        List<Department> lstDepartment = query.list();
        Department a = new Department();
        a.setDeptId(Constants.COMBOBOX_HEADER_VALUE);
        a.setDeptName(Constants.COMBOBOX_HEADER_TEXT);
        List<Department> lstDepartment2 = new ArrayList();
        lstDepartment2.add(a);
        lstDepartment2.addAll(lstDepartment);
        return lstDepartment2;
    }

    /**
     * linhdx Lay ra danh sach khoa de chon co them tat ca
     *
     * @return List<Department>
     */
    public List<Department> findListFaculty() {
        Query query = getSession().createQuery("select a from Department a , DeptType b " + "where a.deptTypeId = b.deptTypeId and lower(b.code) like 'k'");
        List<Department> lstDepartment = query.list();
        return lstDepartment;
    }

    /**
     * Lay ra danh sach bo mon theo khoa
     *
     * @param facultyId
     * @return List<Long>
     */
    public List<Department> findListMajorByFacultyId(Long facultyId) {
        Query query = getSession().createQuery("select a from Department a , DeptType b " + "where a.deptTypeId = b.deptTypeId and lower(b.code) like 'b' and a.parentId =? ").setParameter(0, facultyId);
        List<Department> lstDepartment = query.list();
        return lstDepartment;
    }

    /**
     * trungnq Lay ra danh sach khoa de chon co them tat ca
     *
     * @return List<Department>
     */
    public List<Department> findListMajor() {
        Query query = getSession().createQuery("select a from Department a , DeptType b " + "where a.deptTypeId = b.deptTypeId and lower(b.code) like 'b'");
        List<Department> lstDepartment = query.list();
        return lstDepartment;
    }

    /**
     * Lay ra danh sach bo mon theo khoa
     *
     * @param facultyId
     * @return List<Long>
     */
    public List<Long> findListMajorIdByFaculty(Long facultyId) {
        Query query = getSession().createQuery("select a.deptId from Department a , DeptType b " + "where a.deptTypeId = b.deptTypeId and lower(b.code) like 'b' and a.parentId =? ").setParameter(0, facultyId);
        List<Long> lstDepartment = query.list();
        return lstDepartment;
    }

    /**
     * Get all child id by parent id
     *
     * @param parentId parentId
     * @return List<Long>
     */
    public List<Long> getListDeptIdByParentId(Long parentId) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(" SELECT d.deptId");
        strBuilder.append(" FROM Department d");
        if (parentId != null && parentId > 0) {
            strBuilder.append(" WHERE d.parentId =:parentId ");
        } else {
            strBuilder.append(" WHERE d.parentId IS NULL ");
        }
        Query query = getSession().createQuery(strBuilder.toString());
        if (parentId != null && parentId > 0) {
            query.setParameter("parentId", parentId);
        }
        return query.list();
    }

    /**
     * Get all child id and sub child by parent id
     *
     * @param parentId parentId
     * @return List<Long>
     */
    public List<Department> getAllChildIdByParentId(Long parentId) {
        try {
            StringBuilder strBuilder = new StringBuilder();
            List<Department> lst;

            strBuilder.append(" SELECT d FROM Department d");
            if (parentId != null && parentId > 0) {
                strBuilder.append(" WHERE d.status = :status and d.parentId =:parentId ");
            } else {
                strBuilder.append(" WHERE d.status = :status and d.parentId IS NULL ");
            }
            strBuilder.append(" order by nlssort(lower(d.deptName),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(strBuilder.toString());
            query.setParameter("status", Constants.Status.ACTIVE);
            if (parentId != null && parentId > 0) {
                query.setParameter("parentId", parentId);
            }

            lst = query.list();
            if (lst != null && !lst.isEmpty()) {
                return lst;
            } else {
                return new ArrayList<Department>();
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            System.out.print(e.getMessage());
            return new ArrayList<Department>();
        }
    }

    public Department findByName(String name) {
        // Build sql
        List listParam = new ArrayList();
        String sqlBuilder = "SELECT p FROM Department p WHERE p.status = 1 and lower(p.deptName) like ? escape'!'";
        listParam.add("%" + convertToLikeString(name) + "%");

        Query query = session.createQuery(sqlBuilder);
        for (int i = 0; i < listParam.size(); i++) {
            query.setParameter(i, listParam.get(i));
        }
        List<Department> lsObject = query.list();
        Department returnObject = null;
        if (lsObject != null && lsObject.size() > 0) {
            returnObject = lsObject.get(0);
            //
            // Kiem tra xem co cai nao giong het khong
            //
            for (int i = 0; i < lsObject.size(); i++) {
                if (name.toLowerCase().equals(lsObject.get(i).getDeptName())) {
                    returnObject = lsObject.get(i);
                    break;
                }
            }
        }
        return returnObject;
    }

    public Department findBOById(Long deptId) {
        // Build sql
        //11/11/2014 viethd
        //String sqlBuilder = "SELECT p FROM Department p WHERE p.status = 1 and p.deptId = '" + deptId + "'";
        String sqlBuilder = "SELECT p FROM Department p WHERE p.status = 1 and p.deptId =:deptId";

        Query query = session.createQuery(sqlBuilder);
        query.setParameter("deptId", deptId);
        List<Department> lsObject = query.list();
        if (lsObject != null && lsObject.size() > 0) {
            return lsObject.get(0);
        }
        return null;
    }

    public Department findOfAllDept(Long deptId) {
        // Build sql
        // 11/11/2014 viethd
        //String sqlBuilder = "SELECT p FROM Department p WHERE p.deptId = '" + deptId + "'";
        String sqlBuilder = "SELECT p FROM Department p WHERE p.deptId =:deptId";

        Query query = session.createQuery(sqlBuilder);
        query.setParameter("deptId", deptId);
        List<Department> lsObject = query.list();
        if (lsObject != null && lsObject.size() > 0) {
            return lsObject.get(0);
        }
        return null;
    }

    public Department getDeptByUserId(Long userId) {
        Department result = null;
        // 11/11/2014 viethd
        //String hql = "SELECT d From Department d where d.deptId in (select u.deptId From Users u where u.userId =" + userId + ") ";
        String hql = "SELECT d From Department d where d.deptId in (select u.deptId From Users u where u.userId =:userId) ";
        Query query = getSession().createQuery(hql);
        query.setParameter("userId", userId);
        List<Department> lstResult = query.list();
        if (lstResult != null && lstResult.size() > 0) {
            result = lstResult.get(0);
        }
        return result;
    }

    public Department getDeptRepresentByUserId(Long userId) {
        Department result = null;
        //String hql = "SELECT d From Department d where d.deptId in (select u.deptRepresentId From Users u where u.userId =" + userId + ") ";
        String hql = "SELECT d From Department d where d.deptId in (select u.deptRepresentId From Users u where u.userId =:userId) ";
        Query query = getSession().createQuery(hql);
        query.setParameter("userId", userId);
        List<Department> lstResult = query.list();
        if (lstResult != null && lstResult.size() > 0) {
            result = lstResult.get(0);
        }
        return result;
    }

    /**
     *
     * @param deptTypeId
     * @return
     */
    public DeptType findDeptTypeBOByDeptTypeId(Long deptTypeId) {
        // Build sql
        String sqlBuilder = "SELECT p FROM DeptType p WHERE p.deptTypeId = ?";
        Query query = session.createQuery(sqlBuilder);
        query.setParameter(0, deptTypeId);
        List<DeptType> lsObject = query.list();
        if (lsObject != null && lsObject.size() > 0) {
            return lsObject.get(0);
        }
        return null;
    }

    /**
     *
     * @param deptTypeCode
     * @return
     */
    public DeptType findDeptTypeBOByDeptTypeCode(String deptTypeCode) {
        // Build sql
        String sqlBuilder = "SELECT p FROM DeptType p WHERE p.code = ?";
        Query query = session.createQuery(sqlBuilder);
        query.setParameter(0, deptTypeCode);
        List<DeptType> lsObject = query.list();
        if (lsObject != null && lsObject.size() > 0) {
            return lsObject.get(0);
        }
        return null;
    }

    /**
     * Get all child id and sub child by parent id
     *
     * @param parentId parentId
     * @return List<Long>
     */
    public List<Long> getAllParentIdByChildId(Long childId, Long... deptTypeIds) {

        List<Long> listAllParentId = new ArrayList<Long>();
        Department entity = read(childId);
        Long parentId = entity.getParentId();
        boolean hasCode = false;
        if (deptTypeIds != null && deptTypeIds.length > 0) {
            hasCode = true;
        }
        while (parentId != null && parentId > 0) {
            if (hasCode) {
                boolean valid = false;
                for (Long deptTypeId : deptTypeIds) {
                    if (deptTypeId.equals(entity.getDeptTypeId())) {
                        valid = true;
                        break;
                    }
                }
                if (valid) {
                    listAllParentId.add(parentId);
                }
            } else {
                listAllParentId.add(parentId);
            }
            entity = read(parentId);
            parentId = entity.getParentId();
        }
        return listAllParentId;
    }

    public List<Long> getDeptTypeIds(String... deptTypeCodes) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(" SELECT dt.deptTypeId");
        strBuilder.append(" FROM DeptType dt");
        if (deptTypeCodes != null && deptTypeCodes.length > 0) {
            strBuilder.append(" WHERE dt.code IN(:deptTypeCodes) ");
        }
        Query query = getSession().createQuery(strBuilder.toString());
        if (deptTypeCodes != null && deptTypeCodes.length > 0) {
            query.setParameterList("deptTypeCodes", deptTypeCodes);
        }
        return (List<Long>) query.list();
    }

    //HanPTT
    /**
     * Tim kiem tat ca Department
     *
     * @return List<Department>
     */
    public List<Department> searchAllDepartment() {
        try {
            String sql = " from Department v where v.status = ? "
                    + " order by nlssort(lower(v.deptName),'nls_sort = Vietnamese')";
            Query q = getSession().createQuery(sql);
            q.setParameter(0, Constants.Status.ACTIVE);
            List<Department> result = q.list();

            Department v = new Department();
            v.setDeptId(Constants.COMBOBOX_HEADER_VALUE);
            v.setDeptName(Constants.COMBOBOX_HEADER_TEXT_SELECT);
            List<Department> lst = new ArrayList();
            lst.add(v);
            lst.addAll(result);

            return lst;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            String msg = ex.getMessage();
            return null;
        }
    }

    public List<DepartmentForm> searchAllDepartmentNotHeader() {
        try {
            List<DepartmentForm> deptList = new ArrayList<DepartmentForm>();
            String sql = " from Department v where v.status = ? "
                    + " order by nlssort(lower(v.deptName),'nls_sort = Vietnamese')";
            Query q = getSession().createQuery(sql);
            q.setParameter(0, Constants.Status.ACTIVE);
            List<Department> result = q.list();
            if (result.size() > 0) {
                for (Department deptBo : result) {
                    DepartmentForm deptForm = new DepartmentForm();
                    boToForm(deptBo, deptForm);
                    String deptName = getNameWithParentName(deptBo.getDeptId());
                    deptForm.setDeptName(deptName);
                    deptList.add(deptForm);
                }
            }

            return deptList;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            String msg = ex.getMessage();
            return null;
        }
    }

    public List<DepartmentForm> searchAllDepartmentNotHeader2() {
        try {
            List<DepartmentForm> deptList = new ArrayList<DepartmentForm>();
            String sql = " Select d1.deptId , d1.deptName , d2.deptName "
                    + " from Department d1, Department d2 "
                    + " where d1.parentId = d2.deptId "
                    + " and d1.status = 1 "
                    + " order by nlssort(lower(d1.deptName),'nls_sort = Vietnamese')";

            Query q = getSession().createQuery(sql);
            Iterator lstObject = q.list().iterator();

            while (lstObject.hasNext()) {
                Object[] row = (Object[]) lstObject.next();
                DepartmentForm form = new DepartmentForm();
                Long id = (Long) row[0];
                String deptName = (String) row[1];
                String parentName = (String) row[2];
                if (parentName != null && !"".equals(parentName)) {
                    deptName = deptName + " - " + parentName;
                }
                form.setDeptId(id);
                form.setDeptName(deptName);

                deptList.add(form);
            }
            return deptList;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            String msg = ex.getMessage();
            return null;
        }
    }

    public List<DepartmentForm> getPublishdeptOfCQ_BTP(Long parentId) {
        try {
            if (parentId != null && parentId != 0L) {
                List<DepartmentForm> deptList = new ArrayList<DepartmentForm>();
                String sql = " Select d "
                        + " from Department d"
                        + " where d.status = 1 and (d.parentId = ? or d.deptId = ? or lower(d.deptName) like ? or lower(d.deptName) like ? or lower(d.deptName) like ? )"
                        // + "  order by d.deptTypeId, nlssort(d.deptName,'nls_sort = Vietnamese') "; -- sap xep theo loai truoc
                        + "  order by  nlssort(d.deptName,'nls_sort = Vietnamese') ";
                Query q = getSession().createQuery(sql);
                q.setParameter(0, parentId);
                q.setParameter(1, parentId);
                q.setParameter(2, "%chi cục thads%");
                q.setParameter(3, "%cục thi hành án%");
                q.setParameter(4, "%trung tâm đăng ký giao dịch, tài sản%");
                List<Department> lstObject = q.list();
                if (lstObject != null && lstObject.size() > 0) {
                    for (int i = 0; i < lstObject.size(); i++) {
                        DepartmentForm form = new DepartmentForm(lstObject.get(i));
                        deptList.add(form);
                    }
                }
                return deptList;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            String msg = ex.getMessage();
        }
        return null;
    }

    public List<DepartmentForm> getPublishdeptOfCQ_BTP2(Long parentId) {
        if (lstFactory == null) {
            lstFactory = new HashMap();
        }

        if (lstFactory.containsKey(parentId)) {
            return lstFactory.get(parentId);
        }
        try {
            if (parentId != null && parentId != 0L) {
                List<DepartmentForm> deptList = new ArrayList<DepartmentForm>();
                String sql = " Select d "
                        + " from Department d"
                        + " where d.status = 1 and d.parentId = ? " //(d.parentId = ? or d.deptId = ?) 
                        // + "  order by d.deptTypeId, nlssort(d.deptName,'nls_sort = Vietnamese') "; -- sap xep theo loai truoc
                        + "  order by nlssort(d.deptName,'nls_sort = Vietnamese') ";
                Query q = getSession().createQuery(sql);
                q.setParameter(0, parentId);
                // q.setParameter(1, parentId);

                List<Department> lstObject = q.list();
                if (lstObject != null && lstObject.size() > 0) {
                    for (int i = 0; i < lstObject.size(); i++) {
                        DepartmentForm form = new DepartmentForm(lstObject.get(i));
                        deptList.add(form);
                    }
                }
                if (lstFactory.size() < 10) {
                    lstFactory.put(parentId, deptList);
                }
                return deptList;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            String msg = ex.getMessage();
        }
        return null;
    }

    public List<DepartmentForm> getPublishdeptOfDV_BTP(Long parentId) {
        if (lstFactory == null) {
            lstFactory = new HashMap();
        }

        if (lstFactory.containsKey(parentId)) {
            return lstFactory.get(parentId);
        }
        try {
            if (parentId != null && parentId != 0L) {
                List<DepartmentForm> deptList = new ArrayList<DepartmentForm>();
                String sql = " Select d "
                        + " from Department d"
                        + " where d.status = 1 and ("
                        + "         d.parentId = ? or d.deptId = ? "
                        + "         or ((lower(d.deptName) like ? or lower(d.deptName) like ? or lower(d.deptName) like ?) and d.deptId in (select v.deptId from VDepartment v where v.deptPath like ? )) )"
                        // + "  order by d.deptTypeId, nlssort(d.deptName,'nls_sort = Vietnamese') ";
                        + "  order by nlssort(d.deptName,'nls_sort = Vietnamese') ";

                Query q = getSession().createQuery(sql);
                q.setParameter(0, parentId);
                q.setParameter(1, parentId);
                q.setParameter(2, "%chi cục thads%");
                q.setParameter(3, "%cục thi hành án%");
                q.setParameter(4, "%trung tâm đăng ký giao dịch, tài sản%");
                q.setParameter(5, "%/" + parentId.toString() + "/%");
                List<Department> lstObject = q.list();
                if (lstObject != null && lstObject.size() > 0) {
                    for (int i = 0; i < lstObject.size(); i++) {
                        DepartmentForm form = new DepartmentForm(lstObject.get(i));
                        deptList.add(form);
                    }
                }
                if (lstFactory.size() < 10) {
                    lstFactory.put(parentId, deptList);
                }
                return deptList;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            String msg = ex.getMessage();
        }
        return null;
    }

    public List<DepartmentForm> boToFormWithParentName(Long parentId) {
        try {
            if (parentId != null && parentId != 0L) {
                VDepartmentDAOHE vdeptDaoHe = new VDepartmentDAOHE();
                VDepartment vdept = vdeptDaoHe.getById("deptId", parentId);
                Long virtualDeptTypeId = getDeptTypeIdByDescription("virtual");
                List<DepartmentForm> deptList = new ArrayList<DepartmentForm>();
                String sql = " Select d1.deptId , d1.deptName , d2.deptName, d2.deptTypeId "
                        + " from Department d1, Department d2 "
                        + " where d1.status = 1 and d1.parentId = d2.deptId and d1.deptId in "
                        + "(Select v.deptId from VDepartment v where v.deptPath like ? and v.deptLevel > ?) "
                        + " and (d1.deptTypeId != ?) "
                        + " order by nlssort(d1.deptName,'nls_sort = Vietnamese') ";

                Query q = getSession().createQuery(sql);
                q.setParameter(0, "%/" + parentId.toString() + "/%");
                q.setParameter(1, vdept.getDeptLevel());
                q.setParameter(2, virtualDeptTypeId);
//            q.setParameterList("lstDept", departmentList);
                Iterator lstObject = q.list().iterator();

                while (lstObject.hasNext()) {
                    Object[] row = (Object[]) lstObject.next();
                    DepartmentForm form = new DepartmentForm();
                    Long id = (Long) row[0];
                    String deptName = (String) row[1];
                    String parentName = (String) row[2];
                    Long parentDeptTypeId = (Long) row[3];
                    if (parentName != null && !"".equals(parentName) && parentDeptTypeId != null && !parentDeptTypeId.equals(virtualDeptTypeId)) {
                        deptName = deptName + " - " + parentName;
                    }
                    form.setDeptId(id);
                    form.setDeptName(deptName);

                    deptList.add(form);
                }
                return deptList;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            String msg = ex.getMessage();
        }
        return null;
    }

    public List<DepartmentForm> boToFormNotParentName(Long parentId) {
        try {
            if (parentId != null && parentId != 0L) {
                VDepartmentDAOHE vdeptDaoHe = new VDepartmentDAOHE();
                VDepartment vdept = vdeptDaoHe.getById("deptId", parentId);
                Long virtualDeptTypeId = getDeptTypeIdByDescription("virtual");
                List<DepartmentForm> deptList = new ArrayList<DepartmentForm>();
                String sql = " Select d1.deptId , d1.deptName from Department d1 "
                        + " where d1.status = 1 and d1.deptId in "
                        + "(Select v.deptId from VDepartment v where v.deptPath like ? and v.deptLevel >= ?) "
                        + " and (d1.deptTypeId != ? ) "
                        + " order by nlssort(lower(d1.deptName),'nls_sort = Vietnamese') ";

                Query q = getSession().createQuery(sql);
                q.setParameter(0, "%/" + parentId.toString() + "/%");
                q.setParameter(1, vdept.getDeptLevel());
                q.setParameter(2, virtualDeptTypeId);
//            q.setParameterList("lstDept", departmentList);
                Iterator lstObject = q.list().iterator();

                while (lstObject.hasNext()) {
                    Object[] row = (Object[]) lstObject.next();
                    DepartmentForm form = new DepartmentForm();
                    Long id = (Long) row[0];
                    String deptName = (String) row[1];
//                    String parentName = (String) row[2];
//                    if (parentName != null && !"".equals(parentName)) {
//                        deptName = deptName + " - " + parentName;
//                    }
                    form.setDeptId(id);
                    form.setDeptName(deptName);

                    deptList.add(form);
                }
                return deptList;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            String msg = ex.getMessage();
        }
        return null;
    }

    public List<Department> searchAllChildOfBTP() {
        List<Department> deptList = new ArrayList<Department>();
        try {
            String sql = "Select a from Department a where a.parentId = 52 and a.status = ? "
                    //+ " order by a.deptTypeId, nlssort(lower(a.deptName),'nls_sort = Vietnamese') ";
                    + " order by nlssort(lower(a.deptName),'nls_sort = Vietnamese') ";

            Query q = getSession().createQuery(sql);
            q.setParameter(0, Constants.Status.ACTIVE);
            deptList = q.list();
            return deptList;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            String msg = ex.getMessage();
            return deptList;
        }
    }

    //HanPTT
    /**
     * Tim kiem Department theo id
     *
     * @return Department
     */
    public String getNameById(Long deptId) {
        Department department = new Department();
        try {
            Query query = getSession().createQuery("Select a from Department a where a.status = ? and deptId=?");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            department = (Department) query.uniqueResult();
            if (department != null) {
                return department.getDeptName();
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return "";
    }

    //SyTV
    /*
     * danh sach phong ban cua mot don vi
     */
    public List<Department> findDeptChildren(Long deptId) {
        try {
            Query query = getSession().createQuery("SELECT de FROM Department de"
                    + " WHERE de.status =1 "
                    // + " AND (de.deptId IN (SELECT d.deptId FROM Department d, DeptType b "
                    // + " WHERE d.deptTypeId=b.deptTypeId "
                    // + " AND lower(b.typeName) like ? escape '!' AND d.parentId = ? )"
                    + " AND de.parentId IN (SELECT d.deptId FROM Department d, DeptType b "
                    + " WHERE d.deptTypeId=b.deptTypeId "
                    + " AND lower(b.typeName) like ? escape '!' AND d.parentId = ? )"
                    + " order by nlssort(lower(de.deptName),'nls_sort = Vietnamese') ");
            query.setParameter(0, convertToLikeString(Constants.DEPARTMENT.DEPT_TYPE_CODE_PHONG).toLowerCase());
            query.setParameter(1, deptId);
            // query.setParameter(2, convertToLikeString(Constants.DEPARTMENT.DEPT_TYPE_CODE_PHONG).toLowerCase());
            // query.setParameter(3, deptId);
            List<Department> lstDepartment = query.list();
            return lstDepartment;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return null;
    }

    public Department getDeptById(Long id) {
        Department ret = null;
        try {
            String hql = "select d from Department d where d.deptId = ?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, id);
            List lst = query.list();
            if (lst != null && lst.size() >= 1) {
                ret = (Department) lst.get(0);
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            ret = null;
        }
        return ret;
    }

    public List<Department> findOffices(Department dept) {
        List<Department> lstDept = new ArrayList<Department>();
        List<Department> lstChildDept;

        if (dept != null) {
            Department parentDept = this.getOriginalParent(dept.getDeptId());
            lstDept.add(parentDept);
            lstChildDept = this.getAllChildOfOffice(parentDept.getDeptId());
            if (lstChildDept != null && !lstChildDept.isEmpty()) {
                lstDept.addAll(lstChildDept);
            }
        }

        return lstDept;
    }

    public List<DepartmentForm> findOfficeForm(Department dept) {
        List<Department> lstDept = new ArrayList<Department>();
        List<DepartmentForm> result = new ArrayList<DepartmentForm>();
        List<Department> lstChildDept;

        if (dept != null) {
            Department parentDept = this.getOriginalParent(dept.getDeptId());
            lstDept.add(parentDept);
            lstChildDept = this.getAllChildOfOffice(parentDept.getDeptId());
            if (lstChildDept != null && !lstChildDept.isEmpty()) {
                lstDept.addAll(lstChildDept);
            }
        }
        if (lstDept != null && lstDept.size() > 0) {
            for (Department bo : lstDept) {
                DepartmentForm deptForm = new DepartmentForm(bo);
                deptForm.setDeptName(getNameWithParentName(deptForm.getDeptId()));
                result.add(deptForm);
            }
        }
//
//        for (int i = 0; i < result.size(); i++) {
//            for (int j = 0; j < result.size(); j++) {
//                if (i!=j && result.get(j).getDeptName() != null
//                        && result.get(j).getDeptName().equals(result.get(i).getDeptName())) {
//                    if (result.get(j).getParentId() != null) {
//                        String deptName = getNameWithParentName(result.get(j).getDeptId());
//                        result.get(j).setDeptName(deptName);
//                    }
//                }
//            }
//        }
        return result;
    }

    /**
     *
     */
    public List<Department> findDepartmentStore(Department dept) {
        List<Department> lstDept = new ArrayList<Department>();
        List<Department> lstDeptLevel;
        if (dept != null && dept.getParentId() != null && dept.getParentId() != 0L) {
            Department parentDept = this.findById(dept.getParentId());
            lstDept.add(parentDept);

            //get dept cung cap
            lstDeptLevel = this.getAllChildIdByParentId(dept.getParentId());
            if (lstDeptLevel != null && !lstDeptLevel.isEmpty()) {
                lstDept.addAll(lstDeptLevel);
            }
        } else if (dept != null && dept.getParentId() == null) {
            lstDeptLevel = this.getAllChildIdByParentId(null);
            if (lstDeptLevel != null && !lstDeptLevel.isEmpty()) {
                lstDept.addAll(lstDeptLevel);
            }
        }
        return lstDept;
    }

    /*
     * Tk list dept cha + dep ngang cap + dep con cua 1 don vi
     * ko lay dept ngang cap nua (4/9/2012)
     */

    public List<Department> findDepartment(Department dept) {

        List<Department> lstDeptLevel;
        List<Department> lstChildDept;

        /*if (dept != null && dept.getParentId() != null && dept.getParentId() != 0L) {
         Department parentDept = this.findById(dept.getParentId());
         lstDept.add(parentDept);
        
         //get dept cung cap
         lstDeptLevel = this.getAllChildIdByParentId(dept.getParentId());
         if (lstDeptLevel != null && !lstDeptLevel.isEmpty()) {
         lstDept.addAll(lstDeptLevel);
         }
         } else if (dept != null && dept.getParentId() == null) {
         lstDeptLevel = this.getAllChildIdByParentId(null);
         if (lstDeptLevel != null && !lstDeptLevel.isEmpty()) {
         lstDept.addAll(lstDeptLevel);
         }
         }
         lstChildDept = this.getAllChildIdByParentId(dept.getDeptId());
         if (lstChildDept != null && !lstChildDept.isEmpty()) {
         lstDept.addAll(lstChildDept);
         }
         */
        //Lay dept cha:
        if (dept != null) {
            Department parentDept = this.getOriginalParent(dept.getDeptId());
            List<Department> lstDept = new ArrayList<Department>();
            lstDept.add(parentDept);
            List<Department> listDept = this.getSingleTreeDept(parentDept.getDeptId(), dept.getDeptId());
            lstDept.addAll(listDept);

            //Lay dept con
            lstChildDept = this.getAllChildIdByParentId(dept.getDeptId());
            if (lstChildDept != null && !lstChildDept.isEmpty()) {
                lstDept.addAll(lstChildDept);
            }

            //get dept cung cap
            lstDeptLevel = this.getAllChildIdByParentId(dept.getParentId());
            if (lstDeptLevel != null && !lstDeptLevel.isEmpty()) {
                lstDept.addAll(lstDeptLevel);
            }
            return lstDept;
        }
        return null;

    }

    public List<Department> findInsideDept(Department dept) {
        List<Department> lstDept = new ArrayList<Department>();

        //Lay dept cha:
        if (dept != null) {
//            Department parentDept = this.getOriginalParent(dept.getDeptId());
//            lstDept.add(parentDept);
            Long virtualDeptTypeId = getDeptTypeIdByDescription("virtual");
            try {

                StringBuilder strBuilder = new StringBuilder();
//                List<Department> lst = new ArrayList<Department>();

                strBuilder.append(" SELECT d FROM Department d WHERE d.status = 1 ");
                if (dept.getDeptId() != null && dept.getDeptId() > 0) {
                    strBuilder.append(" and ((d.parentId = :deptId and d.deptTypeId != :virtualDeptTypeId) "
                            + "or (d.parentId in (select d2.deptId from Department d2 where d2.deptTypeId =:virtualDeptTypeId and d2.parentId = :deptId))");
                    if (dept.getParentId() != null && dept.getParentId() > 0) {
                        strBuilder.append(" OR d.parentId =:parentId ");
                    } else {
                        strBuilder.append(" OR d.parentId IS NULL ");
                    }
                } else {
                    strBuilder.append(" and (d.parentId IS NULL ");
                }

                //Lay danh sách các dept thuộc cây đơn vị dọc. (có trong deptPath)
                /*         List<Long> deptLst = new ArrayList<Long>();
                 VDepartment vDepartment = (new VDepartmentDAOHE()).findById(dept.getDeptId(), false);
                 if (vDepartment != null && vDepartment.getDeptPath() != null) {
                 String[] pathStr = vDepartment.getDeptPath().split("/");
                 if (pathStr.length != 0) {
                 for (String a : pathStr) {
                 if (a != null && !"".equals(a)) {
                 deptLst.add(Long.valueOf(a));
                 }
                 }
                 }
                 }
                 strBuilder.append(" OR (d.parentId =:parentId1 and d.deptId IN(:deptLst)) ");
                 */
                strBuilder.append(") ");

                strBuilder.append(" order by nlssort(lower(d.deptName),'nls_sort = Vietnamese') ");
                Query query = getSession().createQuery(strBuilder.toString());
                if (dept.getDeptId() != null && dept.getDeptId() > 0) {
                    query.setParameter("deptId", dept.getDeptId());
                    query.setParameter("virtualDeptTypeId", virtualDeptTypeId);
                    if (dept.getParentId() != null && dept.getParentId() > 0) {
                        query.setParameter("parentId", dept.getParentId());
                    }
                }
//                query.setParameter("parentId1", parentDept.getDeptId());
//                query.setParameterList("deptLst", deptLst);
                List<Department> lstDepartment = query.list();
                lstDept.addAll(lstDepartment);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                log.error(ex.getMessage());
            }

//            //Lay dept con
//            lstChildDept = this.getAllChildIdByParentId(dept.getDeptId());
//            if (lstChildDept != null && !lstChildDept.isEmpty()) {
//                lstDept.addAll(lstChildDept);
//            }
            //get dept cung cap
//            lstDeptLevel = this.getAllChildIdByParentId(dept.getParentId());
//            if (lstDeptLevel != null && !lstDeptLevel.isEmpty()) {
//                lstDept.addAll(lstDeptLevel);
//            }
        }

        return lstDept;
    }

    //Get don vi gui VB noi bo (VB di), luon lay cac dvi con cua BTP
    public List<Department> findInsideDeptBTP(Department dept) {
        List<Department> lstDept = new ArrayList<Department>();

        //Lay dept cha:
        if (dept != null) {
            try {

                StringBuilder strBuilder = new StringBuilder();
//                List<Department> lst = new ArrayList<Department>();

                strBuilder.append(" SELECT d FROM Department d WHERE d.status = 1 "
                        + " and d.deptId in (select v.deptId from VDepartment v where v.deptLevel = 2 ) ");//Lay tca con cua BTP (fix)

                strBuilder.append(" order by nlssort(lower(d.deptName),'nls_sort = Vietnamese') ");
                Query query = getSession().createQuery(strBuilder.toString());
//                if (dept.getDeptId() != null && dept.getDeptId() > 0) {
//                    query.setParameter("deptId", dept.getDeptId());
//                    if (dept.getParentId() != null && dept.getParentId() > 0) {
//                        query.setParameter("parentId", dept.getParentId());
//                    }
//                }
//                query.setParameter("parentId1", parentDept.getDeptId());
//                query.setParameterList("deptLst", deptLst);
                List<Department> lstDepartment = query.list();
                lstDept.addAll(lstDepartment);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                log.error(ex.getMessage());
            }
        }

        return lstDept;
    }

    //Get don vi gui VB noi bo (VB di), luon lay cac dvi con cua BTP
    public GridResult findInsideDeptBTP(Department dept, int start, int count) {
//        List<Department> lstDept = new ArrayList<Department>();
        GridResult gridResult = new GridResult();

        //Lay dept cha:
        if (dept != null) {
            try {

                String countHQL = "select count(d) ";
                String searchHQL = "Select d ";
//                List<Department> lst = new ArrayList<Department>();
                String appendSql = " FROM Department d WHERE d.status = 1 "
                        + " and d.deptId in (select v.deptId from VDepartment v where v.deptLevel = 2 ) "//Lay tca con cua BTP (fix)
                        + " order by nlssort(lower(d.deptName),'nls_sort = Vietnamese') ";
                Query countQuery = getSession().createQuery(countHQL + appendSql);
                Query query = getSession().createQuery(searchHQL + appendSql);

                Long total = (Long) countQuery.uniqueResult();
                query.setFirstResult(start);
                query.setMaxResults(count);

                List<Department> lstDepartment = query.list();
                gridResult = new GridResult(total, lstDepartment);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                log.error(ex.getMessage());
            }
        }

        return gridResult;
    }

    // dept con va dept cung cap
    public List<Department> findOfficesCoordinate(Department dept) {
        List<Department> lstDept = new ArrayList<Department>();
        List<Department> lstDeptLevel;
        List<Department> lstChildDept;

        if (dept != null && dept.getParentId() != null && dept.getParentId() != 0L) {
            //get dept cung cap
            lstDeptLevel = this.getAllChildIdByParentId(dept.getParentId());
            if (lstDeptLevel != null && !lstDeptLevel.isEmpty()) {
                lstDept.addAll(lstDeptLevel);
            }
        } else if (dept != null && dept.getParentId() == null) {
            // lstDeptLevel = this.getAllChildIdByParentId(null);
            // if (lstDeptLevel != null && !lstDeptLevel.isEmpty()) {
            lstDept.add(dept);
            // }
        }
        lstChildDept = this.getAllChildIdByParentId(dept.getDeptId());
        if (lstChildDept != null && !lstChildDept.isEmpty()) {
            lstDept.addAll(lstChildDept);
        }

        //Lay dept cha:
        if (dept != null) {
            Department parentDept = this.getOriginalParent(dept.getDeptId());
            //lstDept.add(parentDept);
            List<Department> listDept = this.getSingleTreeDept(parentDept.getDeptId(), dept.getDeptId());
            lstDept.addAll(listDept);

            //Lay dept con
            lstChildDept = this.getAllChildIdByParentId(dept.getDeptId());
            if (lstChildDept != null && !lstChildDept.isEmpty()) {
                lstDept.addAll(lstChildDept);
            }
        }

        return lstDept;
    }

    public Department getOriginalParent(Long deptId) {
        Department dept = this.findBOById(deptId);
        if (dept != null && dept.getParentId() == null) {
            return dept;
        } else if (dept != null && dept.getParentId() != null) {
            return this.getOriginalParent(dept.getParentId());
        }

        return null;
    }

    /*
     * Tim tca cac dept con cua 1 cay dvi đơn (đơn vị cấp dọc từ cấp cao nhất đến phòng ban hiện tại)
     */
    public List<Department> getSingleTreeDept(Long parentId, Long deptId) {
        try {
            //Lay danh sách các dept thuộc cây đơn vị dọc. (có trong deptPath)
            List<Long> deptLst = new ArrayList<Long>();
            VDepartment vDepartment = (new VDepartmentDAOHE()).findById(deptId, false);
            if (vDepartment != null && vDepartment.getDeptPath() != null) {
                String[] pathStr = vDepartment.getDeptPath().split("/");
                if (pathStr.length != 0) {
                    for (String a : pathStr) {
                        if (a != null && !"".equals(a)) {
                            deptLst.add(Long.valueOf(a));
                        }
                    }
                }
            }
            Query query = getSession().createQuery(" Select de FROM Department de where de.status =1 AND de.parentId =:parentId and de.deptId IN(:deptLst) "
                    + " order by nlssort(lower(de.deptName),'nls_sort = Vietnamese') ");
            query.setParameter("parentId", parentId);
            query.setParameterList("deptLst", deptLst);
            List<Department> lstDepartment = query.list();
            return lstDepartment;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return null;
    }

    //Get list dept cua don vi cha
    public List<Department> getListDept(String deptId) {
        if (deptId != null && !"".equals(deptId)) {
            List<Department> deptLst = new ArrayList<Department>();
            Query searchQuery = getSession().createQuery("Select v from VDepartment v where 1=1 ");
            List<VDepartment> vDeptList = searchQuery.list();
            if (vDeptList != null && !vDeptList.isEmpty()) {
                for (int ind = 0; ind < vDeptList.size(); ind++) {
                    String[] deptPathLst = vDeptList.get(ind).getDeptPath().split("/");
                    if (deptId.equals(deptPathLst[1])) {
                        Department deptResult = getById("deptId", vDeptList.get(ind).getDeptId());
                        deptLst.add(deptResult);
                    }
                }

                return deptLst;
            }
        }

        return null;
    }

    //Get list dept cua don vi cha (for tree)
    public List<Department> getListChildDeptAndParent(Long deptId) {
        if (deptId != null && deptId != 0L) {
            List<Department> deptLst = new ArrayList<Department>();
            deptLst.add(getById("deptId", deptId));
            List<Department> lstChildDept = this.getAllChildIdByParentId(deptId);
            if (lstChildDept != null && !lstChildDept.isEmpty()) {
                deptLst.addAll(lstChildDept);
                for (Department childDept : lstChildDept) {
                    deptLst.addAll(this.getAllChildIdByParentId(childDept.getDeptId()));
                }
            }

            return deptLst;
        }

        return null;
    }

    //Get list dept cua don vi cha
    public List<Department> getListDept(Long deptId) {
        if (deptId != null && deptId != 0L) {
            List<Department> deptLst = new ArrayList<Department>();
            Department a = new Department();
            a.setDeptId(Constants.COMBOBOX_HEADER_VALUE);
            a.setDeptName(Constants.COMBOBOX_HEADER_TEXT);
            deptLst.add(a);
            deptLst.add(getById("deptId", deptId));
            List<Department> lstChildDept = this.getAllChildIdByParentId(deptId);
            if (lstChildDept != null && !lstChildDept.isEmpty()) {
                deptLst.addAll(lstChildDept);
                for (Department childDept : lstChildDept) {
                    deptLst.addAll(this.getAllChildIdByParentId(childDept.getDeptId()));
                }
            }

            return deptLst;
        }

        return null;
    }

    //Get tat ca con va cháu chắt chút chít cua don vi cha
    public List<Department> getAllChildOfParent(Long parentId) {
        VDepartmentDAOHE vdeptDaoHe = new VDepartmentDAOHE();
        try {
            if (parentId != null && parentId != 0L) {
                List<Department> deptLst = new ArrayList<Department>();
//                Department a = new Department();
//                a.setDeptId(Constants.COMBOBOX_HEADER_VALUE);
//                a.setDeptName(Constants.COMBOBOX_HEADER_TEXT);
//                deptLst.add(a);

                VDepartment vdept = vdeptDaoHe.getById("deptId", parentId);
                Long virtualDeptTypeId = getDeptTypeIdByDescription("virtual");
                Query searchQuery = getSession().createQuery(" Select d from Department d where d.status = 1 and d.deptId in (Select v.deptId from VDepartment v where deptPath like ? and deptLevel > ?) "
                        + " and d.deptTypeId != ? "
                        // + " order by d.deptTypeId, nlssort(lower(d.deptName),'nls_sort = Vietnamese') ");
                        + " order by nlssort(lower(d.deptName),'nls_sort = Vietnamese') ");

                searchQuery.setParameter(0, "%/" + parentId.toString() + "/%");
                searchQuery.setParameter(1, vdept.getDeptLevel());
                searchQuery.setParameter(2, virtualDeptTypeId);
                List<Department> vDeptList = searchQuery.list();
                if (vDeptList != null && !vDeptList.isEmpty()) {
                    deptLst.addAll(vDeptList);
                }

                return deptLst;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            System.out.print(ex.getMessage());
        }
        return null;
    }

    public List<Long> getAllChildOfParent1(Long parentId) {
        VDepartmentDAOHE vdeptDaoHe = new VDepartmentDAOHE();
        try {
            if (parentId != null && parentId != 0L) {
                Long virtualDeptTypeId = getDeptTypeIdByDescription("virtual");
                VDepartment vdept = vdeptDaoHe.getById("deptId", parentId);
                Query searchQuery = getSession().createQuery(" Select d.deptId from Department d where d.status = 1 and d.deptId in (Select v.deptId from VDepartment v where deptPath like ? and deptLevel > ?) "
                        + " and (d.deptTypeId != ?) "
                        + " order by nlssort(lower(d.deptName),'nls_sort = Vietnamese') ");
                searchQuery.setParameter(0, "%/" + parentId.toString() + "/%");
                searchQuery.setParameter(1, vdept.getDeptLevel());
                searchQuery.setParameter(2, virtualDeptTypeId);
                List<Long> vDeptList = searchQuery.list();
                if (vDeptList != null && !vDeptList.isEmpty()) {
                    return vDeptList;
                }
                return new ArrayList<Long>();
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            System.out.print(ex.getMessage());
        }
        return null;
    }

    // Get don vi con
    public List<Department> getChildDept(Long deptId) {
        try {
            Query query = getSession().createQuery("SELECT de FROM Department de"
                    + " WHERE de.status =1 "
                    + " AND de.parentId = ?"
                    + " order by nlssort(lower(de.deptName),'nls_sort = Vietnamese') ");

            query.setParameter(0, deptId);
            List<Department> lstDepartment = query.list();
            return lstDepartment;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return null;
    }

    /*
     * Tim tca cac don vi cha cua don vi
     */
    public List<Department> getParentOfDept(Long deptId) {
        List<Department> lstDepartment = new ArrayList<Department>();
        try {
            //Lay danh sách các dept thuộc cây đơn vị dọc. (có trong deptPath)
            List<Long> deptLst = new ArrayList<Long>();
            VDepartment vDepartment = (new VDepartmentDAOHE()).findById(deptId, false);
            if (vDepartment != null && vDepartment.getDeptPath() != null) {
                String[] pathStr = vDepartment.getDeptPath().split("/");
                if (pathStr.length != 0) {
                    for (String a : pathStr) {
                        if (a != null && !"".equals(a)) {
                            deptLst.add(Long.valueOf(a));
                        }
                    }
                }
            }
            if (deptLst.size() > 0) {
                for (int i = deptLst.size() - 1; i >= 0; i--) {
                    Department dept = findById(deptLst.get(i));
                    lstDepartment.add(dept);
                }

            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return lstDepartment;
    }
    // Lay danh sach tat ca cac don vi con chau cua mot don vi

    public List<Department> getAllChildOfOffice(Long deptId) {
        VDepartmentDAOHE vdeptDaoHe = new VDepartmentDAOHE();
        if (deptId != null && deptId != 0L) {

            VDepartment vdept = vdeptDaoHe.getById("deptId", deptId);
            Query searchQuery = getSession().createQuery("Select d from Department d where d.status = 1 and d.deptId in (Select v.deptId from VDepartment v where v.deptPath like ? and v.deptLevel >= ?) and d.deptId != ? "
                    + " order by nlssort(lower(d.deptName),'nls_sort = Vietnamese') ");
            searchQuery.setParameter(0, "%/" + deptId.toString() + "/%");
            searchQuery.setParameter(1, vdept.getDeptLevel());
            searchQuery.setParameter(2, deptId);
            List<Department> vDeptList = searchQuery.list();

            return vDeptList;
        }
        return null;
    }

    public List<Department> getAllChildrentDept(Long deptId) {
        try {
            Query query = getSession().createQuery("SELECT de FROM Department de"
                    + " WHERE de.status =1 "
                    + " AND de.parentId = ?"
                    + " order by nlssort(lower(de.deptName),'nls_sort = Vietnamese') ");
            query.setParameter(0, deptId);
            List<Department> lstDepartment = query.list();
            return lstDepartment;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return null;
    }

    public List<Department> getChildrentOfDept(Long deptId) {
        try {
            Query query = getSession().createQuery("SELECT de FROM Department de"
                    + " WHERE de.status =1 "
                    + " AND (de.parentId = ? or de.deptId = ? )"
                    + " order by nlssort(lower(de.deptName),'nls_sort = Vietnamese') ");
            query.setParameter(0, deptId);
            query.setParameter(1, deptId);
            List<Department> lstDepartment = query.list();
            return lstDepartment;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return null;
    }

    //Check 1 don vi co phai la co quan ko
    public boolean checkDeptCQ(Long deptId) {
        try {
            Query query = getSession().createQuery("select count(d) from Department d where d.deptId = ?"
                    + "and d.deptTypeId in (select dt.deptTypeId from DeptType dt where dt.description like 'CQ') ");
            query.setParameter(0, deptId);

            Long count = (Long) query.list().get(0);
            if (count != 0L) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return false;
        }
    }

    public boolean isDepartmentCQ(Long deptId) {
        try {
            String sql = " from Department d where d.deptId = ? and d.status = 1 and d.parentId is null"
                    + " and d.deptTypeId in (select dt.deptTypeId from DeptType dt where dt.description like 'CQ') ";
            Query query = getSession().createQuery("select count(d) " + sql);
            query.setParameter(0, deptId);
            Long count = (Long) query.list().get(0);
            if (count != 0L) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return false;
        }
    }

    //Check don vi la Tong cuc
    public boolean isDepartmentTC(Long deptId) {
        try {
            String sql = " from Department d where d.deptId = ? and d.status = 1 "
                    + " and d.deptTypeId in (select dt.deptTypeId from DeptType dt where lower(dt.description) like 'tong cuc' or lower(typeName) like 'tổng cục') ";
            Query query = getSession().createQuery("select count(d) " + sql);
            query.setParameter(0, deptId);
            Long count = (Long) query.list().get(0);
            if (count != 0L) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return false;
        }
    }

    public DepartmentForm boToForm(Department dept, DepartmentForm form) {
        if (form == null) {
            form = new DepartmentForm();
        }
        if (dept != null) {
            form.setDeptId(dept.getDeptId());
            form.setTelephone(dept.getTelephone());
            form.setDeptName(dept.getDeptName());
            form.setAddress(dept.getAddress());
            form.setDescription(dept.getDescription());
            form.setDeptCode(dept.getDeptCode());
            if (dept.getCreateDate() != null) {
                form.setCreateDate(dept.getCreateDate().toString());
            }
            form.setTin(dept.getTin());
            form.setEmail(dept.getEmail());
            form.setContactName(dept.getContactName());
            form.setFax(dept.getFax());
            form.setTel(dept.getTel());
            if (dept.getLocationId() != null) {
                form.setLocationId(dept.getLocationId().toString());
            }
            form.setParentId(dept.getParentId());
            form.setDeptTypeId(dept.getDeptTypeId());
        }
        return form;
    }

    public String getNameWithParentName(Long deptId) {
        String name = "";
        String parentName = "";
        try {
            Department dept = findById(deptId);
            if (dept != null) {
                Long parentId = dept.getParentId();
                if (parentId != null && parentId != -1L) {
                    parentName = getNameById(parentId);
                }

                if (parentName != null && !"".equals(parentName)) {
                    name = dept.getDeptName() + " - " + parentName;
                } else {
                    name = dept.getDeptName();
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(e);
            return null;
        }
        return name;
    }

    public Long getDeptTypeIdByDescription(String description) {
        String sql = " from DeptType d where lower(ltrim(d.description)) = ? ";
        Query query = getSession().createQuery(sql);
        query.setParameter(0, description);

        Long id = 0L;
        List<DeptType> deptTypeList = query.list();
        if (deptTypeList != null && !deptTypeList.isEmpty()) {
            id = deptTypeList.get(0).getDeptTypeId();
        }
        return id;
    }

    //Tim don vi cha gan nhat ko phai la don vi ao
    public Department getParentDeptNotVirtual(Long parentId) {
        Long virtualDeptTypeId = getDeptTypeIdByDescription("virtual");
        if (parentId != null) {
            Department deptParent = getById("deptId", parentId);
            if (deptParent != null && !virtualDeptTypeId.equals(deptParent.getDeptTypeId())) {//khac don vi ao
                return deptParent;
            } else if (deptParent != null && virtualDeptTypeId.equals(deptParent.getDeptTypeId())) {
                return getParentDeptNotVirtual(deptParent.getParentId());
            }
        }

        return null;
    }

    //
    public boolean isExistDeptInDept(DepartmentForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(d) from Department d where 1 = 1 ";
        if (form.getDeptId() != null && form.getDeptId() > 0) {
            hql += " and d.deptId <> ? and d.parentId = ? ";
            lstParam.add(form.getDeptId());
            lstParam.add(form.getDeptId());
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

    public boolean isExistUsersInDept(DepartmentForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(u) from Users u where 1 = 1 ";
        if (form.getDeptId() != null && form.getDeptId() > 0) {
            hql += " and u.deptId = ? ";
            lstParam.add(form.getDeptId());
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

    public Department findByDeptCode(String deptCode) {
        // Build sql
        String sqlBuilder = "SELECT p FROM Department p WHERE p.deptCode = ?";
        Query query = session.createQuery(sqlBuilder);
        query.setParameter(0, deptCode);
        List<Department> lsObject = query.list();
        if (lsObject != null && lsObject.size() > 0) {
            return lsObject.get(0);
        }
        return null;
    }

    public List<Department> getAllDept() {
        try {
            Query query = getSession().createQuery("SELECT de FROM Department de"
                    + " WHERE de.status =1 "
                    + " order by nlssort(lower(de.deptName),"
                    + "'nls_sort = Vietnamese') ");
            List<Department> lstDepartment = query.list();
            return lstDepartment;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return null;
    }
}
