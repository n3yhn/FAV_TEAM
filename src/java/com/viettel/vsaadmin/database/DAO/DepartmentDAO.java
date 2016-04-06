/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.vsaadmin.database.DAO;

import com.viettel.dojoTag.DojoJSON;
import com.viettel.common.util.Constants;
import com.viettel.common.util.ResourceText;
import com.viettel.common.util.StringUtils;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.DepartmentDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.PositionDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import com.viettel.vsaadmin.ConstraintUtils;
import com.viettel.vsaadmin.client.form.DepartmentForm;
import com.viettel.vsaadmin.client.form.UsersForm;
import com.viettel.vsaadmin.database.BO.Department;
import com.viettel.vsaadmin.database.BO.DeptType;
import com.viettel.vsaadmin.database.BO.Position;
import com.viettel.vsaadmin.database.BO.Users;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * @author gpdn_trungnq7@viettel.com.vn
 * @version 1.0
 * @since 1.0
 */
public class DepartmentDAO extends BaseDAO {

    private DepartmentForm departmentForm;
    private List<DepartmentForm> deptGridForm;
    private UsersForm usersForm;
    private List<UsersForm> listUnassignedUserForm;
    private List<UsersForm> userOfDeptForm;
    private DepartmentDAOHE deptDaoHibernate = new DepartmentDAOHE();
    private String forwardPage = "department";
    private String forwardPageChildDept = "prepareChildDept";
    private String RESULT_GRID = "gridData";
    private String RESULT_TREE = "treeData";
    private List childrenData = new ArrayList();
    private DojoJSON json = new DojoJSON();
    private PositionDAOHE posDAOHibernate = new PositionDAOHE();
    private UsersDAOHE usersDAOHibernate = new UsersDAOHE();
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DepartmentDAO.class);


    public List<UsersForm> getListUnassignedUserForm() {
        return listUnassignedUserForm;
    }

    public void setListUnassignedUserForm(List<UsersForm> listUnassignedUserForm) {
        this.listUnassignedUserForm = listUnassignedUserForm;
    }

    public List<UsersForm> getUserOfDeptForm() {
        return userOfDeptForm;
    }

    public void setUserOfDeptForm(List<UsersForm> userOfDeptForm) {
        this.userOfDeptForm = userOfDeptForm;
    }

    public UsersForm getUsersForm() {
        return this.usersForm;
    }

    public void setUsersForm(UsersForm usersForm) {
        this.usersForm = usersForm;
    }

    public List<DepartmentForm> getDeptGridForm() {
        return deptGridForm;
    }

    public void setDeptGridForm(List<DepartmentForm> deptGridForm) {
        this.deptGridForm = deptGridForm;
    }

    public List getChildrenData() {
        return this.childrenData;
    }

    public void setChildrenData(List childrenData) {
        this.childrenData = childrenData;
    }

    public DojoJSON getJson() {
        return this.json;
    }

    public void setJson(DojoJSON json) {
        this.json = json;
    }

    public String getTree() {
        return "tree";
    }

    public String getTreeDept() {
        return "treeDept";
    }

    /**
     * load form lan dau
     *
     * @return
     */
    public String prepare() {
        try {
            Session session = getSession();
            Criteria cri = session.createCriteria(DeptType.class);
            cri.addOrder(Order.asc("typeName"));
            List lst = cri.list();

            List listType = new ArrayList(lst);
            getRequest().setAttribute("deptTypeLst", listType);

            DeptType bo = new DeptType();
            bo.setDeptTypeId(Constants.COMBOBOX_HEADER_VALUE);
            bo.setTypeName(Constants.COMBOBOX_HEADER_TEXT_SELECT);

            lst.add(0, bo);

            getRequest().setAttribute("deptTypeList", lst);

            CategoryDAOHE cdhe = new CategoryDAOHE();
            List lstProvince = cdhe.findAllCategory("PROVINCE");
            List lstCategory = new ArrayList();
            lstCategory.addAll(lstProvince);
            lstCategory.add(0, new Category(-1l, "--- Chọn ---"));
            getRequest().setAttribute("lstProvince", lstCategory);

            // this.jsonDataGrid.setItems(lst);

            getPosList();
        } catch (Exception ex) {
        }
        return this.forwardPage;
    }

    /*
     * Prepare QL phong ban dvi
     */
    public String prepareChildDept() {
        try {
            Session session = getSession();
            Criteria cri = session.createCriteria(DeptType.class);
            cri.addOrder(Order.asc("typeName"));
            List lst = cri.list();

            List listType = new ArrayList(lst);
            getRequest().setAttribute("deptTypeLst", listType);

            DeptType bo = new DeptType();
            bo.setDeptTypeId(Constants.COMBOBOX_HEADER_VALUE);
            bo.setTypeName(Constants.COMBOBOX_HEADER_TEXT_SELECT);

            lst.add(0, bo);

            getRequest().setAttribute("deptTypeList", lst);
            // this.jsonDataGrid.setItems(lst);

            getPosList();
            
            CategoryDAOHE cdhe = new CategoryDAOHE();
            List lstProvince = cdhe.findAllCategory("PROVINCE");
            List lstCategory = new ArrayList();
            lstCategory.addAll(lstProvince);
            lstCategory.add(0, new Category(-1l, "--- Chọn ---"));
            getRequest().setAttribute("lstProvince", lstCategory);
            
        } catch (Exception ex) {
        }
        return this.forwardPageChildDept;
    }

    /**
     * chuan bi du lieu cho combobox
     *
     * @throws Exception
     */
    private void getPosList() throws Exception {

        List lstPos = posDAOHibernate.findAllActive();
        //   getRequest().setAttribute("positionList", lstPos);
        String posName = "";
        String posId = "";
        String arrPosition = "[";
        String arrPositionId = "[";
        for (int i = 0; i < lstPos.size() - 1; i++) {
            Position bo = (Position) lstPos.get(i);
            posName = bo.getPosName();
            posId = bo.getPosId().toString();
            arrPosition = arrPosition + "'" + posName + "',";
            arrPositionId = arrPositionId + "'" + posId + "',";
        }
        Position bo = (Position) lstPos.get(lstPos.size() - 1);
        posName = bo.getPosName();
        posId = bo.getPosId().toString();

        arrPosition = arrPosition + "'" + posName + "']";
        arrPositionId = arrPositionId + "'" + posId + "']";

        getRequest().setAttribute("position", arrPosition);
        getRequest().setAttribute("positionId", arrPositionId);
    }

    /**
     * khoi tao list cho grid department
     *
     * @return
     */
    public String onInit() {
        this.departmentForm = new DepartmentForm();
        return onSearchChildren();
    }

    /*
     * khoi tao list cho grid department - MH QL phong ban dvi
     */
    public String onInitChild() {
        this.departmentForm = new DepartmentForm();
//        departmentForm.setDeptId(getDepartmentId());
        return onSearchChildrenDept();
    }

    /**
     * tim kiem
     *
     * @return
     */
    public String onSearch() {
        try {
            getGridInfo();
            GridResult result = deptDaoHibernate.searchGrid(this.departmentForm, start, count);
            this.jsonDataGrid.setItems(result.getLstResult());
            this.jsonDataGrid.setTotalRows(result.getnCount().intValue());
        } catch (Exception ex) {
        }
        return RESULT_GRID;
    }

    /**
     * Tim kiem don vi dong xu ly
     */
    public String onSearchInsideOffice() {
        try {
            getGridInfo();
            GridResult result = deptDaoHibernate.findInsideDeptBTP(getDeptRepresent(), start, count);
            this.jsonDataGrid.setItems(result.getLstResult());
            this.jsonDataGrid.setTotalRows(result.getnCount().intValue());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return RESULT_GRID;
    }

    /**
     * Tim kiem don vi dong xu ly
     */
    public String onSearchOffice() {
        try {
            getGridInfo();
            GridResult result = deptDaoHibernate.searchGrid(getDeptRepresentId(), start, count);
            this.jsonDataGrid.setItems(result.getLstResult());
            this.jsonDataGrid.setTotalRows(result.getnCount().intValue());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return RESULT_GRID;
    }

    /**
     * them moi department
     *
     * @return
     */
    public String onInsert() {
        Department temp = new Department();
        List msgError = new ArrayList();
        String childDeptModule = getRequest().getParameter("childDeptModule");
        try {
            Session session = getSession();
            boolean bCreate = true;
            /*
             DeptType deptType = deptDaoHibernate.findDeptTypeBOByDeptTypeId(departmentForm.getDeptTypeId());
             if ("B".equals(deptType.getCode())) {
             Department parentDept = deptDaoHibernate.findBOById(departmentForm.getParentId());
             DeptType parentDeptType = deptDaoHibernate.findDeptTypeBOByDeptTypeId(parentDept.getDeptTypeId());
             if (!"K".equals(parentDeptType.getCode())) {
             msgError.add("notFalcuty");
             this.jsonDataGrid.setCustomInfo(msgError);
             bCreate = false;
             }
             }
             */
            DepartmentDAOHE.removeCache();
            if (bCreate) {
                Boolean deptExisted = deptDaoHibernate.checkDeptExisted(this.departmentForm.getDeptCode());
                //Boolean deptExisted = checkDeptExisted(this.departmentForm.getDeptCode(), Long.valueOf(0L));
                if (!deptExisted.booleanValue()) {
                    Department bo = new Department();
                    temp = deptDaoHibernate.createBO(this.departmentForm, bo, Boolean.valueOf(false));
                    session.save(temp);
                    msgError.add("insertOk");
                    this.jsonDataGrid.setCustomInfo(msgError);
                } else {
                    msgError.add("insertErr");
                    this.jsonDataGrid.setCustomInfo(msgError);
                }
                this.departmentForm = new DepartmentForm();
                this.departmentForm.setDeptId(temp.getParentId());
            }
        } catch (Throwable ex) {
            log.error(ex.getMessage());
            return "error";
        }

        if ("true".equals(childDeptModule)) {
            return onSearchChildrenDept();
        } else {
            return onSearchChildren();
        }
    }

    /**
     * tim kiem
     *
     * @return
     */
    public String onGetParentName() {
        try {

            String parentId = (String) getRequest().getParameter("parentId");
            if (parentId != null && !"".equals(parentId)) {
                Department result = deptDaoHibernate.findById(Long.parseLong(parentId), false);
                if (result != null) {
                    this.jsonDataGrid.setCustomInfo(result.getDeptName());
                } else {
                    this.jsonDataGrid.setCustomInfo("");
                }
            } else {
                this.jsonDataGrid.setCustomInfo("");
            }


        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return RESULT_GRID;
    }

    /**
     * check ten da ton tai
     *
     * @return
     */
    public String onCheckName() {
        try {

            String id = (String) getRequest().getParameter("id");
            Boolean result = false;
            if ("".equals(id)) {
                result = deptDaoHibernate.checkNameExisted(this.departmentForm.getDeptName());
            } else {
                result = deptDaoHibernate.checkDeptNameExistedForUpdate(this.departmentForm.getDeptName(), new Long(id));
            }

            this.jsonDataGrid.setCustomInfo(result);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return RESULT_GRID;
    }

    /**
     * check ma da ton tai
     *
     * @return
     */
    public String onCheckCode() {
        try {

            String id = (String) getRequest().getParameter("id");
            Boolean result = false;
            if ("".equals(id)) {
                result = deptDaoHibernate.checkCodeExisted(this.departmentForm.getDeptCode());
            } else {
                result = deptDaoHibernate.checkDeptExistedForUpdate(this.departmentForm.getDeptCode(), new Long(id));
            }

            this.jsonDataGrid.setCustomInfo(result);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return RESULT_GRID;
    }

    /**
     * cap nhat department
     *
     * @return
     */
    public String onUpdate() {
        List msgError = new ArrayList();
        String childDept = getRequest().getParameter("childDept");
        try {
            Session session = getSession();
            DepartmentDAOHE.removeCache();
            if ((this.departmentForm.getDeptId() != null) && (!this.departmentForm.getDeptId().equals(""))) {
                Long deptId = new Long(this.departmentForm.getDeptId());
                Boolean deptExisted = deptDaoHibernate.checkDeptExistedForUpdate(this.departmentForm.getDeptCode(), deptId);
                //    Boolean deptExisted = checkDeptExisted(this.departmentForm.getDeptCode(), deptId);
                if (!deptExisted.booleanValue()) {
                    boolean parentOK = true;
//                    if ((this.departmentForm.getParentId() != null) && (!this.departmentForm.getParentId().equals(""))) {
//                        parentOK = checkDeptParent(this.departmentForm.getParentId(), deptId);
//                    }

                    if (parentOK) {
                        Department bo = deptDaoHibernate.findById(deptId, false);//getDeptById(deptId);

                        Boolean isUpdate = Boolean.valueOf(true);
                        Department temp = deptDaoHibernate.createBO(this.departmentForm, bo, isUpdate);
                        session.saveOrUpdate(temp);

                        msgError.add("updateOk");
                        this.jsonDataGrid.setCustomInfo(msgError);
                        this.departmentForm = new DepartmentForm();
                        // this.departmentForm.setDeptId(temp.getParentId().toString());
                    } else {
                        msgError.add("parentNOK");
                        this.jsonDataGrid.setCustomInfo(msgError);
                    }
                } else {
                    msgError.add("updateErr");
                    this.jsonDataGrid.setCustomInfo(msgError);
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        if ("true".equals(childDept)) {
            this.departmentForm.setDeptId(getDepartmentId());
            return onSearchChildrenDept();
        } else {
            return onSearchChildren();
        }
    }

    /**
     * xoa cac department
     *
     * @return
     */
    public String onDelete() {
        int countSuccess = 0;
        int countError = 0;
        List<String> listIdNotDel = new ArrayList();
        // List msg = new ArrayList();
        try {
            DepartmentDAOHE.removeCache();
            Session session = getSession();
            if (deptGridForm != null) {
                ConstraintUtils checkConstraint = new ConstraintUtils();
                //      checkConstraint.setSession(session);
                //      Long parentId = 0L;
                for (int i = 0; i < deptGridForm.size(); i++) {
                    departmentForm = deptGridForm.get(i);
                    if (departmentForm != null && departmentForm.getDeptId() != null && departmentForm.getDeptId() != 0L) {
                        Long deptId = departmentForm.getDeptId();
                        Department bo = deptDaoHibernate.findById(deptId, true);//getDeptById(deptId);
//                        if (parentId.equals(0L)) {
//                            parentId = bo.getParentId();
//                        }
                        if (bo != null) {
                            if (!deptDaoHibernate.isExistDeptInDept(departmentForm) && !deptDaoHibernate.isExistUsersInDept(departmentForm)) {
                                session.delete(bo);
                                countSuccess++;
                            }else{
                                listIdNotDel.add(deptId.toString());
                                countError++;
                            }
//                            if (!checkConstraint.checkExistConstraint("DEPARTMENT", deptId)) {
//                                session.delete(bo);
//                                //   bo.setStatus((short) 0);
//                                countSuccess++;
//                            } else {
//                                listIdNotDel.add(deptId.toString());
//                                countError++;
//                            }
                        }
                    }
                }
            }
            jsonDataGrid = createMsgDeleteDept(countSuccess, countError, listIdNotDel);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            jsonDataGrid = createMsgUpdate(0, 2);
        }
        return RESULT_GRID;
    }

    /**
     * danh sach nguoi dung cua phong ban
     *
     * @return
     */
    public String onInitUserList() {
        try {
            getGridInfo();
            int ncount = start + count;
            if ((this.departmentForm.getDeptId() != null) && (!this.departmentForm.getDeptId().equals(""))) {
                Long deptId = new Long(this.departmentForm.getDeptId());
                List lst = usersDAOHibernate.getListUserOfDept(deptId);
                int total = lst.size();
                if (ncount > lst.size()) {
                    ncount = lst.size();
                }
                lst = lst.subList(start, ncount);
                this.jsonDataGrid.setItems(lst);
                this.jsonDataGrid.setTotalRows(total);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return RESULT_GRID;
    }

    /**
     * danh sach nguoi dung
     *
     * @return
     */
    public String actionListUser() {
        try {
            if ((this.departmentForm.getDeptId() != null) && (!this.departmentForm.getDeptId().equals(""))) {
                Long deptId = new Long(this.departmentForm.getDeptId());
                getRequest().setAttribute("deptId", deptId);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return "listUserPage";
    }

    /**
     * tim kiem department con
     *
     * @return
     */
    public String onSearchChildren() {
        try {
            getGridInfo();
            Session session = getSession();

            List listParam = new ArrayList();
            String countHQL = "select count(a) ";
            String searchHQL = "select a";
            String hql = " from Department a where 1=1 ";
            List lst1 = new ArrayList();
            Long adding = 0l;
            if (this.departmentForm != null) {
                if (this.departmentForm.getDeptId() != null) {
                    if (!"".equals(this.departmentForm.getDeptId())) {
                        lst1.add(deptDaoHibernate.findById(this.departmentForm.getDeptId(), false));
                        adding = 1l;
                        hql = hql + " and parentId = ?";
                        listParam.add(new Long(this.departmentForm.getDeptId()));
                    } else {
                        hql = hql + " and parentId is null";
                    }
                } else {
                    hql = hql + " and parentId is null ";
                }
            }
            hql = hql + "  order by TO_NUMBER(NVL(a.tin, '9998')), nlssort(lower(a.deptName),'nls_sort = Vietnamese')";

            countHQL = countHQL + hql;
            searchHQL = searchHQL + hql;
            Query searchQuery = session.createQuery(searchHQL);
            Query countQuery = session.createQuery(countHQL);
            for (int i = 0; i < listParam.size(); i++) {
                searchQuery.setParameter(i, listParam.get(i));
                countQuery.setParameter(i, listParam.get(i));
            }
            Long total = (Long) countQuery.uniqueResult() + adding;

            searchQuery.setFirstResult(start);
            if (start == 0) {
                searchQuery.setMaxResults(count - 1);
            } else {
                searchQuery.setFirstResult(start - 1);
                searchQuery.setMaxResults(count);
            }
            List lst = searchQuery.list();
            if (start == 0) {
                lst1.addAll(lst);
                this.jsonDataGrid.setItems(lst1);
            } else {
                this.jsonDataGrid.setItems(lst);
            }
            this.jsonDataGrid.setTotalRows(total.intValue());
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return RESULT_GRID;
    }

    /**
     * tim kiem department con MH QL phong ban don vi
     *
     * @return
     */
    public String onSearchChildrenDept() {
        try {
            getGridInfo();
            Session session = getSession();

            List listParam = new ArrayList();
            String countHQL = "select count(a) ";
            String searchHQL = "select a";
            String hql = " from Department a where 1=1 ";
            List lst1 = new ArrayList();
            Long adding = 0l;
            if (this.departmentForm != null) {
                if (this.departmentForm.getDeptId() != null) {
                    if (!"".equals(this.departmentForm.getDeptId())) {
                        lst1.add(deptDaoHibernate.findById(this.departmentForm.getDeptId(), false));
                        adding = 1l;
                        hql = hql + " and parentId = ?";
                        listParam.add(new Long(this.departmentForm.getDeptId()));
                    } else {
                        hql = hql + " and parentId is null";
                        if (getDepartmentId() != null) {
                            hql += " and deptId = ?";
                            listParam.add(getDepartmentId());
                        }
                    }
                } else {
                    hql = hql + " and parentId is null ";
                    if (getDepartmentId() != null) {
                        hql += " and deptId = ?";
                        listParam.add(getDepartmentId());
                    }
                }
            }
            hql = hql + "  order by TO_NUMBER(NVL(a.tin, '9998')), nlssort(lower(a.deptName),'nls_sort = Vietnamese')";
            countHQL = countHQL + hql;
            searchHQL = searchHQL + hql;
            Query searchQuery = session.createQuery(searchHQL);
            Query countQuery = session.createQuery(countHQL);
            for (int i = 0; i < listParam.size(); i++) {
                searchQuery.setParameter(i, listParam.get(i));
                countQuery.setParameter(i, listParam.get(i));
            }
            Long total = (Long) countQuery.uniqueResult() + adding;

            searchQuery.setFirstResult(start);
            if (start == 0) {
                searchQuery.setMaxResults(count - 1);
            } else {
                searchQuery.setFirstResult(start - 1);
                searchQuery.setMaxResults(count);
            }
            List lst = searchQuery.list();
            if (start == 0) {
                lst1.addAll(lst);
                this.jsonDataGrid.setItems(lst1);
            } else {
                this.jsonDataGrid.setItems(lst);
            }
            this.jsonDataGrid.setTotalRows(total.intValue());
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return RESULT_GRID;
    }

    public DepartmentForm getDepartmentForm() {
        return this.departmentForm;
    }

    public void setDepartmentForm(DepartmentForm departmentForm) {
        this.departmentForm = departmentForm;
    }

    /**
     * lay con cua 1 department
     *
     * @return
     */
    public String getCheckedChildrenDataByNode() {
        String parentItemId = getRequest().getParameter("parentItemId");
        try {
            if ((parentItemId != null) && (!parentItemId.equals(""))) {
                Long parentId = new Long(parentItemId);
//                String hql = "from Department      where parentId = ?";
//               Query q = getSession().createQuery(hql);
//           q.setParameter(0, parentId);
                List lst = deptDaoHibernate.getDeptListByParentId(parentId, false);
                if (lst.size() > 0) {
                    for (int i = 0; i < lst.size(); i++) {
                        Department bo = (Department) lst.get(i);
                        Node node = new Node();
                        node.setId(bo.getDeptId().toString());
                        node.setType("checkbox");
                        node.setCheckbox(false);
                        node.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
                        node.setMayHaveChildren("true");
                        this.childrenData.add(node);
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return "childrenData";
    }

    /**
     * lay con cua 1 department
     *
     * @return
     */
    public String getChildrenDataByNode() {
        String parentItemId = getRequest().getParameter("parentItemId");
        try {
            if ((parentItemId != null) && (!parentItemId.equals(""))) {
                Long parentId = new Long(parentItemId);
//                String hql = "from Department      where parentId = ?";
//               Query q = getSession().createQuery(hql);
//           q.setParameter(0, parentId);
//                List lst = deptDaoHibernate.getDeptListByParentId(parentId, false);
                List lst = deptDaoHibernate.getDeptListByParentId(parentId, null, null);
                if (lst.size() > 0) {
                    for (int i = 0; i < lst.size(); i++) {
                        Department bo = (Department) lst.get(i);
                        Node node = new Node();
                        node.setId(bo.getDeptId().toString());
                        node.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
                        node.setMayHaveChildren("true");
                        this.childrenData.add(node);
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return "childrenData";
    }

    /**
     * lay con cua 1 department
     *
     * @return
     */
    public String getChildrenLevel1ByDept() {
        String parentItemId = getRequest().getParameter("parentItemId");
        try {
            if ((parentItemId != null) && (!parentItemId.equals(""))) {
                Long parentId = new Long(parentItemId);
//                String hql = "from Department      where parentId = ?";
//               Query q = getSession().createQuery(hql);
//           q.setParameter(0, parentId);
//                List lst = deptDaoHibernate.getDeptListByParentId(parentId, false);
                List lst = deptDaoHibernate.getDeptListByParentId(parentId, null, null);
                if (lst.size() > 0) {
                    for (int i = 0; i < lst.size(); i++) {
                        Department bo = (Department) lst.get(i);
                        Node node = new Node();
                        node.setId(bo.getDeptId().toString());
                        node.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
                        node.setMayHaveChildren("false");
                        this.childrenData.add(node);
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return "childrenData";
    }

    /**
     * danh sach department goc (ko co cha)
     *
     * @return
     */
    public String getData() {
        List temp = new ArrayList();
        try {
            // String hql = "from Department      where parentId is null";

            //Query q = getSession().createQuery(hql);
//            List lst = deptDaoHibernate.getParentDeptForTree(false);

            //co order by Name
            List lst = deptDaoHibernate.getDeptListByParentId(null, null, null);
            if (lst.size() > 0) {
                for (int i = 0; i < lst.size(); i++) {
                    Department bo = (Department) lst.get(i);
                    Node node = new Node();
                    node.setId(bo.getDeptId().toString());
                    node.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
                    node.setMayHaveChildren("true");
                    temp.add(node);
                }
                this.json.setIdentifier("id");
                this.json.setLabel("name");
                this.json.setItems(temp);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return RESULT_TREE;
        //   return "tree";
    }

    public String getOfficeDeptRootTree() {
        List temp = new ArrayList();
        try {
            List lst = new ArrayList<Department>();
            lst.add(deptDaoHibernate.getOriginalParent(getDepartmentId()));
            if (lst.size() > 0) {
                for (int i = 0; i < lst.size(); i++) {
                    Department bo = (Department) lst.get(i);
                    Node node = new Node();
                    node.setId(bo.getDeptId().toString());
                    node.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
                    node.setMayHaveChildren("true");
                    temp.add(node);
                }
                this.json.setIdentifier("id");
                this.json.setLabel("name");
                this.json.setItems(temp);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return RESULT_TREE;
        //   return "tree";
    }

    /**
     * danh sach department goc (ko co cha)
     *
     * @return
     */
    public String getMyDeptRootTree() {
        List temp = new ArrayList();
        try {
            List lst = new ArrayList<Department>();
            Users user = getUser();
            Long deptId = getDepartmentId();
            if (user.getDeptRepresentId() != null) {
                deptId = user.getDeptRepresentId();
            }
            Department dept = deptDaoHibernate.findBOById(deptId);
            lst.add(dept);
            if (lst.size() > 0) {
                for (int i = 0; i < lst.size(); i++) {
                    Department bo = (Department) lst.get(i);
                    Node node = new Node();
                    node.setId(bo.getDeptId().toString());
                    node.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
                    node.setMayHaveChildren("true");
                    temp.add(node);
                }
                this.json.setIdentifier("id");
                this.json.setLabel("name");
                this.json.setItems(temp);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return RESULT_TREE;
        //   return "tree";
    }

    public String loadDepts() {
        String deptId = getRequest().getParameter("deptId");
        List<Department> list = new ArrayList<Department>();
        DepartmentForm deptPlus = new DepartmentForm();
        deptPlus.setDeptId(Constants.COMBOBOX_HEADER_VALUE);
        deptPlus.setDeptName(Constants.COMBOBOX_HEADER_TEXT_SELECT);
        List<DepartmentForm> officeList = new ArrayList<DepartmentForm>();
        officeList.add(deptPlus);
        try {
            if (deptId != null) {
                if (deptId.equals("-1")) {
                    list = deptDaoHibernate.findDeptChildren(getDepartmentId());
                } else {
                    officeList = deptDaoHibernate.search(Long.valueOf(deptId));
                }
                for (Department department : list) {
                    DepartmentForm deptForm = new DepartmentForm(department);
                    officeList.add(deptForm);
                }
                jsonDataGrid.setLabel("deptName");
                jsonDataGrid.setIdentifier("deptId");
                jsonDataGrid.setItems(officeList);
                return GRID_DATA;
                //  }
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            return null;
        }
        return null;
    }
    //DiuLTT
    /*
     * Search dept tree with its parent and depts at the same level
     */

    public String getDeptRootTree() {
        List temp = new ArrayList();
        DepartmentDAOHE deptDaoHe = new DepartmentDAOHE();

        try {
            List<Department> lstChildren = new ArrayList<Department>();

            //get dept at the same level
            Department userDept = getDepartment();
            if (userDept != null && userDept.getParentId() != null) {
                lstChildren = deptDaoHe.getAllChildIdByParentId(userDept.getParentId());
                //List lstChildNode = new ArrayList();
                //get parent node
//                Department dept = deptDaoHe.findBOById(userDept.getParentId());
                Department dept = deptDaoHe.getOriginalParent(userDept.getParentId());

                if (dept != null) {
                    Node node = new Node();
                    node.setId(dept.getDeptId().toString());
                    node.setName(StringUtils.wrapLongText(dept.getDeptName(), Constants.tree_wrap_text_length));
//                    for (Department bo : lstChildren) {
//                        Node childNode = new Node();
//                        childNode.setId(bo.getDeptId().toString());
//                        childNode.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
//                        childNode.setMayHaveChildren("false");
//                        lstChildNode.add(childNode);
//                    }
//                    node.setChildren(lstChildNode);
                    node.setMayHaveChildren("true");
                    temp.add(node);
                }
            } else if (userDept.getParentId() == null) {
                lstChildren = deptDaoHe.getAllChildIdByParentId(null);

                for (Department department : lstChildren) {
                    Node node = new Node();
                    node.setId(department.getDeptId().toString());
                    node.setName(StringUtils.wrapLongText(department.getDeptName(), Constants.tree_wrap_text_length));
                    node.setMayHaveChildren("true");
                    temp.add(node);
                }
            }

            this.json.setIdentifier("id");
            this.json.setLabel("name");
            this.json.setItems(temp);
        } catch (Exception e) {
        }

        return RESULT_TREE;
    }

    //DiuLTT
    /*
     * Search depttree bao gom parent va cac anh em
     */
    public String getDeptOfParentTree() {
        List temp = new ArrayList();
        DepartmentDAOHE deptDaoHe = new DepartmentDAOHE();
        Long deptId = getDeptRepresentId();
        Department dept = getDeptRepresent();

        try {

            if (dept != null && dept.getParentId() != null) {
                Department deptParent = deptDaoHe.getById("deptId", dept.getParentId());
                Node node = new Node();
                node.setId(dept.getParentId().toString());
                node.setName(StringUtils.wrapLongText(deptParent.getDeptName(), Constants.tree_wrap_text_length));
                node.setMayHaveChildren("true");
                temp.add(node);
            } else if (dept != null && dept.getParentId() == null) {
                Node node = new Node();
                node.setId(dept.getDeptId().toString());
                node.setName(StringUtils.wrapLongText(dept.getDeptName(), Constants.tree_wrap_text_length));
                node.setMayHaveChildren("true");
                temp.add(node);
            }


            this.json.setIdentifier("id");
            this.json.setLabel("name");
            this.json.setItems(temp);
        } catch (Exception e) {
        }

        return RESULT_TREE;
    }

    //DiuLTT
    public String getDeptChildrenByNode() {
        String parentItemId = getRequest().getParameter("parentItemId");

        String currentId = "";
        if (getRequest().getParameter("deptId") != null) {
            currentId = getRequest().getParameter("deptId");
        }

        try {
            Department userDept = getDepartment();
            if ((parentItemId != null) && (!parentItemId.equals(""))) { // && (!parentItemId.equals(currentId))
                Long parentId = new Long(parentItemId);

                if (userDept != null && (!parentId.equals(userDept.getParentId()))) {
                    //List lst = deptDaoHibernate.getDeptListByParentId(parentId, false);// q.list();
                    List lst = deptDaoHibernate.getSingleTreeDept(parentId, userDept.getDeptId());
                    if (lst.size() > 0) {
                        for (int i = 0; i < lst.size(); i++) {
                            Department bo = (Department) lst.get(i);
                            if (currentId.equals(bo.getDeptId().toString())) {
                                continue;
                            }
                            Node node = new Node();
                            node.setId(bo.getDeptId().toString());
                            node.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
                            node.setMayHaveChildren("true");
                            this.childrenData.add(node);
                        }
                    }
                } else if (userDept != null && parentId.equals(userDept.getParentId())) { //
                    List sameLevelDept = deptDaoHibernate.getAllChildIdByParentId(userDept.getParentId());
                    if (sameLevelDept.size() > 0) {
                        for (int i = 0; i < sameLevelDept.size(); i++) {
                            Department bo = (Department) sameLevelDept.get(i);
                            if (currentId.equals(bo.getDeptId().toString())) {
                                continue;
                            }
                            Node node = new Node();
                            node.setId(bo.getDeptId().toString());
                            node.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
                            node.setMayHaveChildren("true");
                            this.childrenData.add(node);
                        }
                    }
                    //return "childrenData";
                } else {
                    return "childrenData";
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return "childrenData";
    }

    /*
     *  Tim cac user cua phong hien tai
     */
    public String getMyDeptUser() {
        Long deptId = getDepartmentId();
        return getUserOfDept(deptId);
    }

    public String getUserOfDept() {
        String strDeptId = getRequest().getParameter("deptId");
        if (strDeptId == null || strDeptId.trim().length() == 0) {
            strDeptId = (String) getRequest().getSession().getAttribute("deptId");
        }
        Long deptId = 0l;
        try {
            deptId = Long.parseLong(strDeptId);
        } catch (Exception en) {
        }

        getRequest().getSession().removeAttribute("deptId");
        getRequest().getSession().setAttribute("deptId", deptId);
        return getUserOfDept(deptId);
    }

    public String getMyDeptLeader() {
        Long deptId = getDepartmentId();
        return getLeaderOfDept(deptId);
    }

    public String getLeaderOfDept() {
        String strDeptId = getRequest().getParameter("deptId");
        if (strDeptId == null || strDeptId.trim().length() == 0) {
            strDeptId = (String) getRequest().getSession().getAttribute("deptId");
        }
        Long deptId = 0l;
        try {
            deptId = Long.parseLong(strDeptId);
        } catch (Exception en) {
        }

        getRequest().getSession().removeAttribute("deptId");
        getRequest().getSession().setAttribute("deptId", deptId);
        return getLeaderOfDept(deptId);
    }

    public String getStaffOfDept() {
        String strDeptId = getRequest().getParameter("deptId");
        String pos = getRequest().getParameter("pos");
        String strObjectId = getRequest().getParameter("objectId");
        String strObjectType = getRequest().getParameter("objectType");
        Long userId = getUserId();
        Long objectId = 0l;
        Long deptId = 0l;
        Long objectType = 0l;

        try {
            deptId = Long.parseLong(strDeptId);
            objectId = Long.parseLong(strObjectId);
            objectType = Long.parseLong(strObjectType);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        if (strDeptId == null || strDeptId.trim().length() == 0) {
            strDeptId = (String) getRequest().getSession().getAttribute("deptId");
        }

        getRequest().getSession().removeAttribute("deptId");
        getRequest().getSession().setAttribute("deptId", deptId);
        getGridInfo();
        UsersDAOHE daohe = new UsersDAOHE();
        List lstResult = daohe.getStaffOfDept(userId, deptId, pos, objectId, objectType);
        int total = lstResult.size();
        int lastItem = start + count;
        if (lastItem > total - 1) {
            lastItem = total;
        }
        lstResult = lstResult.subList(start, lastItem);
        jsonDataGrid.setItems(lstResult);
        jsonDataGrid.setTotalRows(total);
        return RESULT_GRID;
    }
    /*
     *  Tim kiem tat ca user cua phong co dep_id = deptid
     */

    public String getUserOfDept(Long deptId) {
        getGridInfo();
        UsersDAOHE daohe = new UsersDAOHE();
        List lstResult = daohe.getUsersOfDept(deptId);
        int total = lstResult.size();
        int lastItem = start + count;
        if (lastItem > total - 1) {
            lastItem = total;
        }
        lstResult = lstResult.subList(start, lastItem);
        jsonDataGrid.setItems(lstResult);
        //jsonDataGrid.setNumRows(lstResult.size());
        jsonDataGrid.setTotalRows(total);
        return RESULT_GRID;
    }

    public String getLeaderOfDept(Long deptId) {
        getGridInfo();
        UsersDAOHE daohe = new UsersDAOHE();
        List lstResult = daohe.getAllLeaderOffice(deptId);
        lstResult.remove(0);
        int total = lstResult.size();
        int lastItem = start + count;
        if (lastItem > total - 1) {
            lastItem = total;
        }
        lstResult = lstResult.subList(start, lastItem);
        jsonDataGrid.setItems(lstResult);
        //jsonDataGrid.setNumRows(lstResult.size());
        jsonDataGrid.setTotalRows(total);
        return RESULT_GRID;
    }

    public String getUserCoordinate(Long deptId) {
        getGridInfo();
        UsersDAOHE daohe = new UsersDAOHE();
        List lstResult = daohe.getUsersOfDept(deptId);
        int total = lstResult.size();
        int lastItem = start + count;
        if (lastItem > total - 1) {
            lastItem = total;
        }
        lstResult = lstResult.subList(start, lastItem);
        jsonDataGrid.setItems(lstResult);
        //jsonDataGrid.setNumRows(lstResult.size());
        jsonDataGrid.setTotalRows(total);
        return RESULT_GRID;
    }

    /**
     * danh sach department goc (ko co cha)
     *
     * @return
     */
//    public String getDataByUser() {
//        List temp = new ArrayList();
//        try {
//            // String hql = "from Department      where parentId is null";
//
//            //Query q = getSession().createQuery(hql);
//            HttpSession session = getRequest().getSession();
//            UserToken vsaUserToken = (UserToken) session.getAttribute("userToken");
//            Users user = usersDAOHibernate.read(vsaUserToken.getUserID());
//            List<Department> lst = new ArrayList<Department>();
//            if (user.getUserName().contains("admin")) {
//                lst = deptDaoHibernate.getParentDeptForTree(false);
//            } else {
//                Staff staff = staffDAOHE.read(user.getStaffId());
//                Department deptObj=deptDaoHibernate.read(staff.getDepartmentId());
//                lst.add(deptObj);
//            }
//            if (lst.size() > 0) {
//                for (int i = 0; i < lst.size(); i++) {
//                    Department bo = (Department) lst.get(i);
//                    Node node = new Node();
//                    node.setId(bo.getDeptId().toString());
//                    node.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
//                    node.setMayHaveChildren("true");
//                    temp.add(node);
//                }
//                this.json.setIdentifier("id");
//                this.json.setLabel("name");
//                this.json.setItems(temp);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return RESULT_TREE;
//        //   return "tree";
//    }
    /**
     * kiem tra trung
     *
     * @param code
     * @param deptId
     * @return
     * @throws Exception
     */
    private Boolean checkDeptExisted(String code, Long deptId) throws Exception {
        Boolean isExisted = Boolean.valueOf(false);
        Session session = getSession();

        String hql = "from Department        where deptId != ?        and lower(deptCode) = ?";
        try {
            Query q = session.createQuery(hql);
            q.setParameter(0, deptId);
            q.setParameter(1, code.toLowerCase());

            if ((q.list() != null) && (!q.list().isEmpty()) && (q.list().size() > 0)) {
                isExisted = Boolean.valueOf(true);
            }
            return isExisted;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }

    /**
     * tim kiem nguoi dung
     *
     * @return
     */
    public String searchUser() {
        try {
            getGridInfo();
            Session session = getSession();
            List listParam = new ArrayList();
            String countHQL = "select count(a) ";
            String searchHQL = "select a ";
            String hql = " from Users a where 1 = 1 ";

            if (this.usersForm != null) {
                if ((this.usersForm.getUserName() != null) && (!"".equals(this.usersForm.getUserName()))) {
                    hql = hql + " and lower(userName) like ? ESCAPE '/'";
                    listParam.add("%" + StringUtils.escapeSql(this.usersForm.getUserName().toLowerCase()) + "%");
                }

                if ((this.usersForm.getFullName() != null) && (!"".equals(this.usersForm.getFullName()))) {
                    hql = hql + " and lower(fullName) like ? ESCAPE '/'";
                    listParam.add("%" + StringUtils.escapeSql(this.usersForm.getFullName().toLowerCase()) + "%");
                }

                if ((this.usersForm.getEmail() != null) && (!"".equals(this.usersForm.getEmail()))) {
                    hql = hql + " and lower(email) like ? ESCAPE '/'";
                    listParam.add("%" + StringUtils.escapeSql(this.usersForm.getEmail().toLowerCase()) + "%");
                }

                if ((this.usersForm.getCellphone() != null) && (!"".equals(this.usersForm.getCellphone()))) {
                    hql = hql + " and lower(cellphone) like ?";
                    listParam.add("%" + this.usersForm.getCellphone().toLowerCase() + "%");
                }
            }

            hql = hql + " and status = ?";
            listParam.add(Long.valueOf(1L));

            hql = hql + " and a.deptId is null "; // diultt: chi add them nhung user chua dc gán cho phòng ban nào
//            if ((this.usersForm.getDeptId() != null) && (!this.usersForm.getDeptId().equals(""))) {
//                Long deptId = new Long(this.usersForm.getDeptId());
//                hql = hql + " and ( a.deptId is null)  ";
//                hql = hql + " or ((a.deptId is not null) and (a.deptId <> ?) ))";
//                listParam.add(deptId);
//            }

            countHQL = countHQL + hql;
            searchHQL = searchHQL + hql;

            Query countQuery = session.createQuery(countHQL);
            Query searchQuery = session.createQuery(searchHQL);
            for (int i = 0; i < listParam.size(); i++) {
                countQuery.setParameter(i, listParam.get(i));
                searchQuery.setParameter(i, listParam.get(i));
            }
            Long total = (Long) countQuery.uniqueResult();
            searchQuery.setFirstResult(start);
            searchQuery.setMaxResults(count);
            List lst = searchQuery.list();

            this.jsonDataGrid.setItems(lst);
            this.jsonDataGrid.setTotalRows(total.intValue());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return RESULT_GRID;
    }

    /**
     * gan nguoi dung vao phong ban
     *
     * @return
     */
    public String assignUser() {
        try {
            Session session = getSession();
            Long deptId = null;
            if ((this.usersForm.getDeptId() != null) && (!this.usersForm.getDeptId().equals(""))) {
                deptId = new Long(this.usersForm.getDeptId());
                Department deptBO = deptDaoHibernate.findById(deptId, false);
                if (listUnassignedUserForm != null) {
                    for (int i = 0; i < listUnassignedUserForm.size(); i++) {
                        usersForm = listUnassignedUserForm.get(i);
                        if (usersForm != null && usersForm.getUserId() != null && !usersForm.getUserId().equals("")) {
                            Long userId = new Long(usersForm.getUserId());
                            Long posId = this.usersForm.getPosId();
                            Criteria cri = session.createCriteria(Users.class).add(Restrictions.eq("userId", userId));
                            List lst = cri.list();


                            if (lst.size()
                                    > 0) {
                                Users ubo = (Users) lst.get(0);
                                ubo.setPosId(posId);
                                ubo.setDeptId(deptBO.getDeptId());
                                ubo.setDeptLevel(deptBO.getDeptLevel());
                                ubo.setDeptName(deptBO.getDeptName());
                                session.saveOrUpdate(ubo);
                            }
                        }
                    }
                    List listUserOfDept = usersDAOHibernate.getListUserOfDept(deptId);
                    this.jsonDataGrid.setItems(listUserOfDept);
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return RESULT_GRID;
    }

    /**
     * loai bo nguoi dung khoi phong ban
     *
     * @return
     */
    public String removeUser() {
        try {
            Session session = getSession();
            if ((this.departmentForm.getDeptId() != null) && (!this.departmentForm.getDeptId().equals(""))) {
                Long deptId = new Long(this.departmentForm.getDeptId());
                if (userOfDeptForm != null) {
                    for (int i = 0; i < userOfDeptForm.size(); i++) {
                        usersForm = userOfDeptForm.get(i);
                        if (usersForm != null && usersForm.getUserId() != null && !usersForm.getUserId().equals("")) {
                            Long userId = new Long((String) usersForm.getUserId());
                            Criteria cri = session.createCriteria(Users.class).add(Restrictions.eq("userId", userId));
                            List lst = cri.list();


                            if (lst.size()
                                    > 0) {
                                Users ubo = (Users) lst.get(0);
                                ubo.setDeptId(null);
                                ubo.setDeptName("");
                                ubo.setDeptLevel("");
                                ubo.setPosId(null);
                                session.saveOrUpdate(ubo);
                            }
                        }
                    }
                    session.flush();
                    List listUserOfDept = usersDAOHibernate.getListUserOfDept(deptId);
                    this.jsonDataGrid.setItems(listUserOfDept);
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return RESULT_GRID;
    }

    /**
     * kiem tra phogn ban cha
     *
     * @param parentId
     * @param currentId
     * @return
     * @throws Exception
     */
    private boolean checkDeptParent(Long parentId, Long currentId) throws Exception {
        boolean parentOK = true;
        //Long parentId = new Long(id);

        while ((parentId != null) && (parentId.longValue() != 0L)) {
            if (!parentId.equals(currentId)) {
                // Department bo = getDeptById(parentId);
                Department bo = deptDaoHibernate.findById(parentId, false);
                if (bo != null) {
                    parentId = bo.getParentId();
                } else {
                    parentOK = false;
                    break;
                }

                continue;
            } else {
                parentOK = false;
            }

        }

        return parentOK;
    }

    /**
     * du lieu cho cay: khi chon loai phong ban=bo mon -> chi hien thi phong ban
     * cha la khoa
     *
     * @return
     */
    public String getDeptData() {
        List temp = new ArrayList();
        String type = getRequest().getParameter("type");
        try {
            String deptTypeId = getRequest().getParameter("deptTypeId");
            if (deptTypeId == null) {
                deptTypeId = "-1";
            }
            List lst = new ArrayList();
            if ("LTNN".equals(type)) {
                Department dp2 = new Department();
                dp2.setDeptId(0L);
                dp2.setDeptName("Lưu trữ nhà nước");
                lst.add(dp2);
            }
            lst.addAll(deptDaoHibernate.getParentDeptForTree(true));

            if (lst.size() > 0) {
                for (int i = 0; i < lst.size(); i++) {
                    Department bo = (Department) lst.get(i);
                    Node node = new Node();
                    node.setId(bo.getDeptId().toString());
                    node.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
                    node.setMayHaveChildren("true");
                    temp.add(node);
                }
                this.json.setIdentifier("id");
                this.json.setLabel("name");
                this.json.setItems(temp);
            }
        } catch (Exception e) {
        }
        return RESULT_TREE;
    }

    public String getDeptChildrenOfUser() {
        String parentItemId = getRequest().getParameter("parentItemId");

        String currentId = "";
        if (getRequest().getParameter("deptId") != null) {
            currentId = getRequest().getParameter("deptId");
        }

        try {
            Department userDept = getDepartment();
            if ((parentItemId != null) && (!parentItemId.equals(""))) { // && (!parentItemId.equals(currentId))
                Long parentId = new Long(parentItemId);

                if (userDept != null) {
                    //List lst = deptDaoHibernate.getDeptListByParentId(parentId, false);// q.list();
                    List lst = deptDaoHibernate.getSingleTreeDept(parentId, userDept.getDeptId());
                    if (lst.size() > 0) {
                        for (int i = 0; i < lst.size(); i++) {
                            Department bo = (Department) lst.get(i);
                            if (currentId.equals(bo.getDeptId().toString())) {
                                continue;
                            }
                            Node node = new Node();
                            node.setId(bo.getDeptId().toString());
                            node.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
                            node.setMayHaveChildren("true");
                            this.childrenData.add(node);
                        }
                    }
                } else {
                    return "childrenData";
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return "childrenData";
    }

    /**
     * phong ban con
     *
     * @return
     */
    public String getDeptChildrenDataByNode() {
        String parentItemId = getRequest().getParameter("parentItemId");

        String currentId = "";
        if (getRequest().getParameter("deptId") != null) {
            currentId = getRequest().getParameter("deptId");
        }
        Long deptTypeId = 0L;
        try {
            deptTypeId = Long.parseLong(getRequest().getParameter("deptTypeId"));
        } catch (Exception en) {
        }

        try {
            if ((parentItemId != null) && (!parentItemId.equals("")) && (!parentItemId.equals(currentId))) {
                Long parentId = new Long(parentItemId);
                List lst = deptDaoHibernate.getDeptListByParentId(parentId, false);// q.list();
                if (lst.size() > 0) {
                    for (int i = 0; i < lst.size(); i++) {
                        Department bo = (Department) lst.get(i);
                        if (currentId.equals(bo.getDeptId().toString())) {
                            continue;
                        }
                        Node node = new Node();
                        node.setId(bo.getDeptId().toString());
                        node.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
                        node.setMayHaveChildren("true");
                        this.childrenData.add(node);
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return "childrenData";
    }

    /**
     * du lieu cho cay
     *
     * @return
     */
    public String getFacultyData() {
        List temp = new ArrayList();
        try {

            List lst = deptDaoHibernate.findListFaculty();

            if (lst.size() > 0) {
                for (int i = 0; i < lst.size(); i++) {
                    Department bo = (Department) lst.get(i);
                    Node node = new Node();
                    node.setId(bo.getDeptId().toString());
                    node.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
                    node.setMayHaveChildren("true");
                    temp.add(node);
                }
                this.json.setIdentifier("id");
                this.json.setLabel("name");
                this.json.setItems(temp);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return RESULT_TREE;
    }

    /**
     * phong ban con
     *
     * @return
     */
    public String getFacultyChildrenDataByNode() {
        String parentItemId = getRequest().getParameter("parentItemId");

        String currentId = "";
        if (getRequest().getParameter("deptId") != null) {
            currentId = getRequest().getParameter("deptId");
        }
        try {
            if ((parentItemId != null) && (!parentItemId.equals("")) && (!parentItemId.equals(currentId))) {
                Long parentId = new Long(parentItemId);
                List lst = this.findListMajorByFacultyId(parentId);// q.list();
                if (lst.size() > 0) {
                    for (int i = 0; i < lst.size(); i++) {
                        Department bo = (Department) lst.get(i);
                        if (currentId.equals(bo.getDeptId().toString())) {
                            continue;
                        }
                        Node node = new Node();
                        node.setId(bo.getDeptId().toString());
                        node.setName(StringUtils.wrapLongText(bo.getDeptName(), Constants.tree_wrap_text_length));
                        node.setMayHaveChildren("true");
                        this.childrenData.add(node);
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return "childrenData";
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
    private DojoJSON createMsgDeleteDept(int succ, int err, List<String> listNotDel) {
        int i = 0;
        List<String> lst = new ArrayList<String>();
        lst.add(ResourceText.getString("alert.deleteSuccess").replace("{0}", String.valueOf(succ)));
        lst.add("Bạn không thể xóa {0} bản ghi đang tồn tại phòng ban con hoặc người dùng".replace("{0}", String.valueOf(err)));
        if (succ > 0) {
            i = i + 1;
        }
        if (err > 0) {
            i = i + 2;
            if (listNotDel != null && listNotDel.size() > 0) {
                for (String idNotDel : listNotDel) {
                    lst.add(idNotDel);
                }
            }
        }
        jsonData.setItems(lst);
        jsonData.setCustomInfo(String.valueOf(i));
        return jsonData;
    }
}
/* Location:           C:\Program Files\Apache Software Foundation\TomcatVSA\webapps\vsaadminv3\WEB-INF\classes\
 * Qualified Name:     com.viettel.vsaadmin.database.DAO.DepartmentDAO
 * JD-Core Version:    0.6.0
 */
