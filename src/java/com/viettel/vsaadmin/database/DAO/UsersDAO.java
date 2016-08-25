/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.vsaadmin.database.DAO;

import com.viettel.common.util.Constants;
import com.viettel.common.util.DateTimeUtils;
import com.viettel.common.util.StringUtils;
import com.viettel.dojoTag.DojoJSON;
import com.viettel.hqmc.BO.Business;
import com.viettel.hqmc.BO.BusinessAlert;
import com.viettel.hqmc.DAOHE.BusinessAlertDAOHE;
import com.viettel.hqmc.DAOHE.BusinessDAOHE;
import com.viettel.hqmc.FORM.BusinessForm;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import com.viettel.voffice.database.DAOHibernate.RolesDAOHE;
import com.viettel.vsaadmin.ConstraintUtils;
import com.viettel.vsaadmin.client.form.DepartmentForm;
import com.viettel.vsaadmin.client.form.DepartmentGridForm;
import com.viettel.vsaadmin.client.form.RoleGridForm;
import com.viettel.vsaadmin.client.form.UserGridForm;
import com.viettel.vsaadmin.client.form.UserRoleForm;
import com.viettel.vsaadmin.client.form.UsersForm;
import com.viettel.vsaadmin.common.util.PasswordService;
import com.viettel.vsaadmin.database.BO.Department;
import com.viettel.vsaadmin.database.BO.Position;
import com.viettel.vsaadmin.database.BO.RoleUser;
import com.viettel.vsaadmin.database.BO.RoleUserDept;
import com.viettel.vsaadmin.database.BO.RoleUserPK;
import com.viettel.vsaadmin.database.BO.Roles;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.DepartmentDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.PositionDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import viettel.passport.client.UserToken;

/**
 *
 * @author gpdn_havm2
 */
public class UsersDAO extends BaseDAO {

    private UsersForm usersForm;
    private BusinessForm businessForm;
    private UsersForm userPasswordForm;
    private UsersForm resetPassForm;
    private UsersForm usersFormSearch;
    private UserRoleForm userRoleFormOnDialog;
    private UserGridForm listUserGridForm;
    private RoleGridForm unassignedRoleFormOnDialog;
    private RoleGridForm userRoleForm;
    private DepartmentForm departmentForm;
    private DepartmentGridForm userDeptFormOnDialog;
    private DepartmentGridForm userDeptForm;
    private String forwardPage = "prepare";
    private String forwardPageUserOfDept = "prepareUserOfDept";
    private String passwordPage = "passwordPage";
    public DojoJSON json = new DojoJSON();
    protected DojoJSON staffsList = new DojoJSON();
    private List childrenData = new ArrayList();
    private static Random random = new Random();
    private UsersDAOHE usersDAOHE = new UsersDAOHE();
    public static final Long USER_TYPE_STAFF = 1L;
    public static final Long USER_TYPE_STUDENT = 2L;

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UsersDAO.class);

    /**
     * Vao trang quan ly user, khoi tao du lieu hien thi
     *
     * @return
     */
    public String prepare() {
        try {
//            DocumentDirectionWS ws ;
//            ws = new DocumentDirectionWS_Service().getDocumentDirectionWSPort();
//            ws.getDocumentDirection(15);
//            List lstDoc = ws.getDocumentDirection(getUserId());
            UserToken vsaUserToken = (UserToken) getRequest().getSession().getAttribute("userToken");
            Long currentUserId = getUserId();
            PositionDAOHE posDHE = new PositionDAOHE();
            List<Position> lst = posDHE.findAllActive();
            if (lst != null) {
                Position empty = new Position(-1L);
                empty.setPosName("--- Chọn ---");
                lst.add(0, empty);
            }
            BusinessDAOHE bdhe = new BusinessDAOHE();
            List<Business> lstBusiness = bdhe.getAllBusiness();
            if (lst != null) {
                Business empty = new Business(-1L);
                empty.setBusinessName("--- Chọn ---");
                lstBusiness.add(0, empty);
            }
            getRequest().setAttribute("currentUserId", currentUserId);
            getRequest().setAttribute("lstPosition", lst);
            getRequest().setAttribute("lstBusiness", lstBusiness);
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

        return this.forwardPage;
    }

    public String loadPasswordChange() {
        UserToken vsaUserToken = (UserToken) getRequest().getSession().getAttribute("userToken");
        Long currentUserId = vsaUserToken.getUserID();
        PositionDAOHE posDHE = new PositionDAOHE();
        List<Position> lst = posDHE.findAllActive();
        if (lst != null) {
            Position empty = new Position(-1L);
            empty.setPosName("--- Chọn ---");
            lst.add(0, empty);
        }
        getRequest().setAttribute("currentUserId", currentUserId);
        getRequest().setAttribute("lstPosition", lst);

        CategoryDAOHE cdhe = new CategoryDAOHE();
        List lstProvince = cdhe.findAllCategory("PROVINCE");
        List lstCategory = new ArrayList();
        lstCategory.addAll(lstProvince);
        lstCategory.add(0, new Category(0l, "--- Chọn ---"));
        getRequest().setAttribute("lstProvince", lstCategory);

        List lstBusinessType = cdhe.findAllCategory("BUSTYPE");
        List lstCategory1 = new ArrayList();
        lstCategory1.addAll(lstBusinessType);
        lstCategory1.add(0, new Category(0l, "--- Chọn ---"));
        getRequest().setAttribute("lstBusinessType", lstCategory1);

        List result = new ArrayList();
        Long userId = getUserId();
        Users user = usersDAOHE.getById("userId", userId);
        result.add(user);
        userPasswordForm = new UsersForm();
        if (user != null) {
            if (user.getBusinessId() != null && user.getBusinessId() > 0) {
                BusinessDAOHE busdaohe = new BusinessDAOHE();
                Business bus = busdaohe.findById(user.getBusinessId());
                if (bus != null) {
                    result.add(bus);
                    userPasswordForm.setBusinessForm(new BusinessForm(bus));
                }
            }
        }

        userPasswordForm = usersDAOHE.boToForm(user, userPasswordForm);

        return passwordPage;
    }

    /**
     * Prepare trang quan ly nguoi dung cua don vi Vao trang quan ly user, khoi
     * tao du lieu hien thi
     */
    public String prepareUserOfDept() {
        try {
            UserToken vsaUserToken = (UserToken) getRequest().getSession().getAttribute("userToken");
            Long currentUserId = getUserId();
            PositionDAOHE posDHE = new PositionDAOHE();
            List<Position> lst = posDHE.findAllActive();
            if (lst != null) {
                Position empty = new Position(-1L);
                empty.setPosName("--- Chọn ---");
                lst.add(0, empty);
            }
            BusinessDAOHE bdhe = new BusinessDAOHE();
            List<Business> lstBusiness = bdhe.getAllBusiness();
            if (lst != null) {
                Business empty = new Business(-1L);
                empty.setBusinessName("--- Chọn ---");
                lstBusiness.add(0, empty);
            }
            getRequest().setAttribute("currentUserId", currentUserId);
            getRequest().setAttribute("lstPosition", lst);
            getRequest().setAttribute("lstBusiness", lstBusiness);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return this.forwardPageUserOfDept;
    }

    /**
     * Khoi tao du lieu tim kiem
     *
     * @return
     */
    public String onInit() {
        try {
            Session session = getSession();
            Criteria cri = session.createCriteria(Users.class);
            cri.add(Restrictions.ne("status", Long.valueOf(-1L)));
            List lst = cri.list();

            this.jsonDataGrid.setItems(lst);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return "gridData";
    }

    /**
     * Tim kiem user
     *
     * @return
     */
    public String onSearch() {
        try {
            getGridInfo();
            String searchType = getRequest().getParameter("searchType");

            Session session = getSession();

            List listParam = new ArrayList();
            String hql = " from Users a where 1 = 1 ";
            if (usersForm != null) {
                if ((this.usersForm.getFullName() != null) && (!"".equals(this.usersForm.getFullName()))) {
                    hql = hql + " and lower(a.fullName) like ? ESCAPE '/' ";
                    listParam.add(StringUtils.toLikeString(this.usersForm.getFullName()));
                }

                if ((this.usersForm.getStatus() != null) && (!"".equals(this.usersForm.getStatus())) && (!"ALL".equals(this.usersForm.getStatus()))) {
                    hql = hql + " and a.status = ?";
                    listParam.add(new Long(this.usersForm.getStatus()));
                }

                if ((this.usersForm.getCellphone() != null) && (!"".equals(this.usersForm.getCellphone()))) {
                    hql = hql + " and a.cellphone like ? ESCAPE '/' ";
                    listParam.add(StringUtils.toLikeString(this.usersForm.getCellphone()));
                }

                if ((this.usersForm.getUserTypeId() != null) && (!"".equals(this.usersForm.getUserTypeId().toString())) && (this.usersForm.getUserTypeId() > 0)) {
                    hql = hql + " and a.userTypeId = ?";
                    listParam.add(new Long(this.usersForm.getUserTypeId()));
                }

                if ((this.usersForm.getUserName() != null) && (!"".equals(this.usersForm.getUserName()))) {
                    hql = hql + " and lower(a.userName) like ? ESCAPE '/' ";
                    listParam.add(StringUtils.toLikeString(usersForm.getUserName()));
                }
                if (this.usersForm.getDeptId() != null) {
                    hql = hql + " and a.deptId in (select v.deptId from VDepartment v where v.deptPath like ?) ";
//                    listParam.add(new Long(this.usersForm.getDeptId()));
                    listParam.add("%/" + this.usersForm.getDeptId() + "/%");
                }
                if (this.usersForm.getUserType() != -1) {
                    if (this.usersForm.getUserType() == 1) {
                        hql = hql + " and a.businessId <> null and a.deptId = null";
                    }
                    if (this.usersForm.getUserType() == 0) {
                        hql = hql + " and a.businessId = null and a.deptId <> null ";
                    }
                }
            }

            DepartmentDAOHE deptDaoHe = new DepartmentDAOHE();
            if ("local".equals(searchType)) {
                if (getDepartmentId() != null) {
                    Department dept = deptDaoHe.getOriginalParent(getDepartmentId());
                    hql = hql + " and (a.deptId in (select v.deptId from VDepartment v where v.deptPath like ?))";
                    listParam.add("%/" + dept.getDeptId() + "/%");
                }
            }

            hql = hql + "  order by nlssort(lower(a.userName),'nls_sort = Vietnamese')";

            Query searchQuery = session.createQuery(hql);
            Query countQuery = session.createQuery("SELECT count(a) " + hql);

            for (int i = 0; i < listParam.size(); i++) {
                searchQuery.setParameter(i, listParam.get(i));
                countQuery.setParameter(i, listParam.get(i));
            }

            searchQuery.setFirstResult(start);
            searchQuery.setMaxResults(count);

            Long total = (Long) countQuery.uniqueResult();
            List<Users> lst = (List<Users>) searchQuery.list();
            List<UsersForm> lstForm = new ArrayList<UsersForm>();
            for (int i = 0; i < lst.size(); i++) {
                Users u = lst.get(i);
                UsersForm form = new UsersForm();
                lstForm.add(usersDAOHE.boToForm(u, form));
            }

            this.jsonDataGrid.setItems(lstForm);
            this.jsonDataGrid.setNumRows(lst.size());
            this.jsonDataGrid.setTotalRows(total.intValue());
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return "gridData";
    }

    /*
     * TK user cua dvi
     */
    public String onSearchUserOfDept() {
        try {
            getGridInfo();

            Session session = getSession();
//            Long deptId = getDepartmentId();

            List listParam = new ArrayList();
            String hql = " from Users a where 1 = 1 ";
            if (usersForm != null) {
                if ((this.usersForm.getFullName() != null) && (!"".equals(this.usersForm.getFullName()))) {
                    hql = hql + " and lower(a.fullName) like ? ESCAPE '/' ";
                    listParam.add(StringUtils.toLikeString(this.usersForm.getFullName()));
                }

                if ((this.usersForm.getStatus() != null) && (!"".equals(this.usersForm.getStatus())) && (!"ALL".equals(this.usersForm.getStatus()))) {
                    hql = hql + " and a.status = ?";
                    listParam.add(new Long(this.usersForm.getStatus()));
                }

                if ((this.usersForm.getCellphone() != null) && (!"".equals(this.usersForm.getCellphone()))) {
                    hql = hql + " and a.cellphone like ? ESCAPE '/' ";
                    listParam.add(StringUtils.toLikeString(this.usersForm.getCellphone()));
                }

                if ((this.usersForm.getUserTypeId() != null) && (!"".equals(this.usersForm.getUserTypeId().toString())) && (this.usersForm.getUserTypeId() > 0)) {
                    hql = hql + " and a.userTypeId = ?";
                    listParam.add(new Long(this.usersForm.getUserTypeId()));
                }

                if ((this.usersForm.getUserName() != null) && (!"".equals(this.usersForm.getUserName()))) {
                    hql = hql + " and lower(a.userName) like ? ESCAPE '/' ";
                    listParam.add(StringUtils.toLikeString(usersForm.getUserName()));
                }
                if (this.usersForm.getDeptId() != null) {
                    hql = hql + " and (a.deptId in (select v.deptId from VDepartment v where v.deptPath like ?))";
                    listParam.add("%/" + this.usersForm.getDeptId() + "/%");
                } else {
                    hql = hql + " and (a.deptId in (select v.deptId from VDepartment v where v.deptPath like ?))";
                    listParam.add("%/" + getDeptRepresentId() + "/%");
                }
            } else {
                hql = hql + " and (a.deptId in (select v.deptId from VDepartment v where v.deptPath like ?))";
                listParam.add("%/" + getDeptRepresentId() + "/%");
            }
            hql = hql + "  order by nlssort(lower(a.userName),'nls_sort = Vietnamese')";
//            if (deptId != null) {
//                hql = hql + " and (a.deptId = ? or a.deptId in (select v.deptId from VDepartment v where v.deptPath like ?))";
//                listParam.add(deptId);
//                listParam.add("%/" + deptId + "/%");
//            }

            Query searchQuery = session.createQuery(hql);
            Query countQuery = session.createQuery("SELECT count(a) " + hql);

            for (int i = 0; i < listParam.size(); i++) {
                searchQuery.setParameter(i, listParam.get(i));
                countQuery.setParameter(i, listParam.get(i));
            }

            searchQuery.setFirstResult(start);
            searchQuery.setMaxResults(count);

            Long total = (Long) countQuery.uniqueResult();
            List<Users> lst = (List<Users>) searchQuery.list();
            List<UsersForm> lstForm = new ArrayList<UsersForm>();
            for (Users u : lst) {
                UsersForm form = new UsersForm();
                lstForm.add(usersDAOHE.boToForm(u, form));
            }

            this.jsonDataGrid.setItems(lstForm);
            this.jsonDataGrid.setNumRows(lst.size());
            this.jsonDataGrid.setTotalRows(total.intValue());
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return "gridData";
    }

    /**
     * Them moi mot user
     *
     * @return
     */
    public String onInsert() {
        List msgError = new ArrayList();
        try {
            Session session = getSession();
            UsersDAOHE.removeCache();
            Boolean userExisted = checkUserExisted(this.usersForm.getUserName(), Long.valueOf(0L));
            if (!userExisted.booleanValue()) {
                Users bo = new Users();
                Users temp = createBO(bo, Boolean.valueOf(false));
                temp.setUserId(getSequence("USERS_SEQ"));
                session.save(temp);
                session.flush();

                msgError.add("insertOk");
                this.jsonDataGrid.setCustomInfo(msgError);
                List items = new ArrayList();
                items.add(bo);
                jsonDataGrid.setItems(items);

                //LogUtil.createUser(session, getRequest(), user, temp.getUserName(), "CREATE_USER");
            } else {
                msgError.add("insertErr");
                this.jsonDataGrid.setCustomInfo(msgError);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            msgError.add("insertErr");
            this.jsonDataGrid.setCustomInfo(msgError);
            return "gridData";
        }

        return "gridData";
    }

    /**
     * Tim userTypeId cua user
     *
     * @param userId
     * @return
     */
    public Long getUserTypeId(Long userId) {

        String hql = "select u.userTypeId from Users u where u.userId = ?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, userId);
        List lstResult = query.list();
        if (lstResult == null || (lstResult != null && lstResult.isEmpty())) {
            return 0l;
        } else {
            return (Long) lstResult.get(0);
        }
    }

    /**
     * Tim department cua user
     *
     * @param userId
     * @return
     */
    public Long getUserDeptId(Long userId) {
        Long userTypeId = getUserTypeId(userId);
        Long deptId = 0l;
        if (USER_TYPE_STAFF.equals(userTypeId)) {
            // 11/11/2014 viethd
//            String hql = "select s.departmentId from Staff s "
//                    + "where s.staffId in (select u.staffId from Users u where u.userId =" + userId + ")";
            String hql = "select s.departmentId from Staff s "
                    + "where s.staffId in (select u.staffId from Users u where u.userId =:userId)";
            Query query = getSession().createQuery(hql);
            query.setParameter("userId", userId);
            List lstResult = query.list();
            if (lstResult == null || lstResult.size() == 0) {
                deptId = 0l;
            } else {
                deptId = (Long) lstResult.get(0);
            }
        }
        return deptId;
    }

    /**
     * Update User
     *
     * @return
     */
    public String onUpdate() {
        List msgError = new ArrayList();
        try {
            UsersDAOHE.removeCache();
            Session session = getSession();

            if ((this.usersForm.getUserId() != null) && (!this.usersForm.getUserId().equals(""))) {
                Long userId = new Long(this.usersForm.getUserId());
                Boolean userExisted = checkUserExisted(this.usersForm.getUserName(), userId);
                if (!userExisted.booleanValue()) {
                    Users bo = getUserById(userId);

                    Boolean isUpdate = Boolean.valueOf(true);
                    Users temp = createBO(bo, isUpdate);
                    session.saveOrUpdate(temp);

                    msgError.add("updateOk");
                    this.jsonDataGrid.setCustomInfo(msgError);
                } else {
                    msgError.add("updateErr");
                    this.jsonDataGrid.setCustomInfo(msgError);
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());

            msgError.add("updateErr");
            this.jsonDataGrid.setCustomInfo(msgError);
        }
        return "gridData";
    }

    /**
     * Check code
     *
     * @return struts result
     */
    public String checkIdentityCard() {
        HttpServletRequest req = getRequest();
        //String userIdStr = req.getParameter("userId");
        List<Users> lsUser = usersDAOHE.getListUserByIdentityCard(usersForm.getIdentityCard());
        boolean exist = false;
        for (Users user : lsUser) {
            if (!user.getUserId().toString().equals(usersForm.getUserId())) {
                exist = true;
                break;
            }
        }
        if (exist) {
            jsonDataGrid.setCustomInfo("exist");
        }
        return "gridData";
    }

    /**
     * Check code
     *
     * @return struts result
     */
    public String checkRight() {
        HttpServletRequest req = getRequest();
        String userIdStr = req.getParameter("userId");
        HttpSession session = getRequest().getSession();
        Long userId = StringUtils.toLong(userIdStr);
        UserToken vsaUserToken = (UserToken) session.getAttribute("userToken");
        Long currentUserId = vsaUserToken.getUserID();
        Users user = usersDAOHE.read(userId);
        user.getUserName();
        if (userId != null && userId > 0) {
            if (userId.equals(currentUserId)) {
                jsonDataGrid.setCustomInfo("currentUser");
            } else if ("admin".equalsIgnoreCase(user.getUserName().trim())) {
                jsonDataGrid.setCustomInfo("admin");
            }
        }
        return "gridData";
    }

    /**
     * Tim kiem user theo id
     *
     * @param userId
     * @return
     * @throws Exception
     */
    private Users getUserById(Long userId) throws Exception {
        Users temp = null;

        String hql = "from Users where userId = ?";
        Query q = getSession().createQuery(hql);
        q.setParameter(0, userId);

        List lst = q.list();
        if (lst.size() > 0) {
            temp = (Users) lst.get(0);
        }

        return temp;
    }

    /**
     * Action xu ly xoa user
     *
     * @return
     */
    public String onDelete() {
        try {
            UsersDAOHE.removeCache();
            int countSuccess = 0;
            int countError = 0;
            Session session = getSession();
            HttpServletRequest req = getRequest();
            String strUsers = req.getParameter("userId");
            ConstraintUtils constraintUtils = new ConstraintUtils();
            List<String> listIdNotDel = new ArrayList();
            if (strUsers == null || "".equals(strUsers.trim())) {
                throw new Exception("Không có bản ghi nào để xóa");
            }
            List<Long> listId = StringUtils.convertStringToListLong(strUsers, StringUtils.COMMA_SEPARATOR);
            for (Long id : listId) {
                if (!constraintUtils.checkExistConstraint("USERS", id)) {
                    try {
                        // 11/11/2014 viethd
                        //String hql = " delete from Users a  where a.userId = " + id + "";
                        String hql = " delete from Users a  where a.userId =:userId";
                        Query q = session.createQuery(hql);
                        q.setParameter("userId", id);
                        q.executeUpdate();
                        countSuccess++;
                    } catch (ConstraintViolationException ex) {
                        log.error(ex.getMessage());
                        listIdNotDel.add(id.toString());
                        countError++;
                    }
                } else {
                    listIdNotDel.add(id.toString());
                    countError++;
                }
            }
            jsonDataGrid = createMsgDelete(countSuccess, countError, listIdNotDel);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            jsonDataGrid = createMsgUpdate(0, 2);
        }
        return "gridData";
    }

    /**
     * action xu ly reset pass
     *
     * @return
     */
    public String onResetPass() {
        List msg = new ArrayList();
        try {
            Session session = getSession();
            Long userId = new Long(this.resetPassForm.getUserId());
            String password = this.resetPassForm.getPassword();

            if (password == null) {
                throw new Exception("password null");
            }
            if (password.trim().equals("")) {
                throw new Exception("password is empty");
            }

            if (password == null) {
                throw new Exception("password null");
            }

            if (password.trim().equals("")) {
                throw new Exception("password is empty");
            }

            String iChars = "!@#$%^&*-";
            int countTmp = 0;
            for (int i = 0; i < password.length(); i++) {
                for (int j = 0; j < iChars.length(); j++) {
                    if (password.charAt(i) == iChars.charAt(j)) {
                        countTmp++;
                    }
                }
            }
            if (countTmp == 0) {
                throw new Exception("password must has one or more special characters");
            }

            countTmp = 0;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isLetter(password.charAt(i))) {
                    countTmp++;
                }
            }
            if (countTmp == 0) {
                throw new Exception("password must has one or more anphabet characters");
            }

            countTmp = 0;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isDigit(password.charAt(i))) {
                    countTmp++;
                }
            }
            if (countTmp == 0) {
                throw new Exception("password must has one or more number characters");
            }

            Users bo = getUserById(userId);
            String passwordEncrypt = PasswordService.getInstance().encrypt(bo.getUserName().toLowerCase() + password);

            bo.setPassword(passwordEncrypt);
            bo.setLastResetPassword(new Date());
            bo.setPasswordchanged(1L);
            session.saveOrUpdate(bo);
            msg.add("resetSuccess");
            this.jsonDataGrid.setCustomInfo(msg);

        } catch (Exception ex) {
            log.error(ex.getMessage());

            msg.add("resetFail");
            this.jsonDataGrid.setCustomInfo(msg);
            return "gridData";
        }

        return "gridData";
    }

    /**
     * action xu ly khoa nguoi dung
     *
     * @return
     */
    public String onLock() {
//        try {
//            UsersDAOHE.removeCache();
//            Session session = getSession();
//            HttpServletRequest req = getRequest();
//            String strUsers = req.getParameter("userId");
//            String ignore = req.getParameter("ignore");
//            int succ = 0;
//            if (StringUtils.validString(strUsers)) {
//                String hql = " update Users a set a.status = 0 where a.userId in (" + strUsers + ")";
//                Query q = session.createQuery(hql);
//                succ = q.executeUpdate();
//            }
//            jsonDataGrid = createMsgUpdateStatus(succ, Integer.parseInt(ignore), new String[]{"Khóa", "khóa"});
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            jsonDataGrid = createMsgUpdate(0, 2);
//        }
        //140123 binhnt update
        try {
            UsersDAOHE.removeCache();
            Session session = getSession();
            HttpServletRequest req = getRequest();
            String strUsers = req.getParameter("userId");
            String[] arrUsers = strUsers.split(",");
            List<Long> lstUsers = new ArrayList<Long>();
            if (arrUsers.length > 0) {
                for (int i = 0; i < arrUsers.length; i++) {
                    try {
                        Long l = Long.parseLong(arrUsers[i]);
                        lstUsers.add(l);
                    } catch (Exception en) {
                    }
                }
            }
            String ignore = req.getParameter("ignore");
            int succ = 0;
            if (StringUtils.validString(strUsers)) {
                String hql = " update Users a set a.status = 0 where a.userId in (:lstUsers)";
                Query q = session.createQuery(hql);
                q.setParameterList("lstUsers", lstUsers);
                succ = q.executeUpdate();
            }
            jsonDataGrid = createMsgUpdateStatus(succ, Integer.parseInt(ignore), new String[]{"Khóa", "khóa"});
        } catch (Exception ex) {
            log.error(ex.getMessage());
            jsonDataGrid = createMsgUpdate(0, 2);
        }
        //!binhnt
        return "gridData";
    }

    /**
     * Action xu ly mo khoa nguoi dung
     *
     * @return
     */
    public String onUnLock() {
//        try {
//            UsersDAOHE.removeCache();
//            Session session = getSession();
//            HttpServletRequest req = getRequest();
//            String strUsers = req.getParameter("userId");
//            String ignore = req.getParameter("ignore");
//            int succ = 0;
//            if (StringUtils.validString(strUsers)) {
//                String hql = " update Users a set a.status = 1 where a.userId in (" + strUsers + ")";
//                Query q = session.createQuery(hql);
//                succ = q.executeUpdate();
//            }
//            jsonDataGrid = createMsgUpdateStatusUnlock(succ, Integer.parseInt(ignore), new String[]{"Mở khóa", "hoạt động"});
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            jsonDataGrid = createMsgUpdate(0, 2);
//        }
        try {
            UsersDAOHE.removeCache();
            Session session = getSession();
            HttpServletRequest req = getRequest();
            String strUsers = req.getParameter("userId");
            String[] arrUsers = strUsers.split(",");
            List<Long> lstUsers = new ArrayList<Long>();
            if (arrUsers.length > 0) {
                for (int i = 0; i < arrUsers.length; i++) {
                    try {
                        Long l = Long.parseLong(arrUsers[i]);
                        lstUsers.add(l);
                    } catch (Exception en) {
                    }
                }
            }
            String ignore = req.getParameter("ignore");
            int succ = 0;
            if (StringUtils.validString(strUsers)) {
                String hql = " update Users a set a.status = 1 where a.userId in (:lstUsers)";
                Query q = session.createQuery(hql);
                q.setParameterList("lstUsers", lstUsers);
                succ = q.executeUpdate();
            }
            jsonDataGrid = createMsgUpdateStatusUnlock(succ, Integer.parseInt(ignore), new String[]{"Mở khóa", "hoạt động"});
        } catch (Exception ex) {
            log.error(ex.getMessage());
            jsonDataGrid = createMsgUpdate(0, 2);
        }
        return "gridData";
    }

    /**
     * Tao mot Users moi
     *
     * @param bo
     * @param isUpdate
     * @return
     * @throws Exception
     */
    private Users createBO(Users bo, Boolean isUpdate)
            throws Exception {
        Users temp = bo;
        DepartmentDAOHE dhe = new DepartmentDAOHE();
        try {
            if (!isUpdate) {
                temp.setUserName(this.usersForm.getUserName());
            }
            temp.setFullName(this.usersForm.getFullName());
            temp.setCellphone(this.usersForm.getCellphone().trim());
            temp.setDeptId(usersForm.getDeptId());
            temp.setDeptRepresentId(usersForm.getDeptRepresentId());
            if (temp.getDeptRepresentId() != null && temp.getDeptRepresentId() > 0L) {
                Department dept = dhe.findBOById(temp.getDeptRepresentId());
                if (dept != null) {
                    temp.setDeptRepresentName(dept.getDeptName());
                }
            } else {
                temp.setDeptRepresentName(null);
            }

            if (temp.getDeptId() != null && temp.getDeptId() > 0l) {
                Department dept = dhe.findBOById(temp.getDeptId());
                if (dept != null) {
                    temp.setDeptName(dept.getDeptName());
                }
            } else {
                temp.setDeptName(null);
            }
            if ((this.usersForm.getDateOfBirth() != null) && (!this.usersForm.getDateOfBirth().equals(""))) {
                temp.setDateOfBirth(DateTimeUtils.convertStringToTime(this.usersForm.getDateOfBirth(), "yyyy-MM-dd"));
            } else {
                temp.setDateOfBirth(null);
            }
            temp.setDescription(this.usersForm.getDescription());
            temp.setEmail(this.usersForm.getEmail());
            temp.setFax(this.usersForm.getFax());
            temp.setBusinessId(usersForm.getBusinessId());
            temp.setBusinessName(usersForm.getBusinessName());
            temp.setPosId(this.usersForm.getPosId());

            if ((this.usersForm.getGender() != null) && (this.usersForm.getGender().length() != 0)) {
                temp.setGender(new Long(this.usersForm.getGender()));
            } else {
                temp.setGender(null);
            }

            /*
             * if ((this.usersForm.getProfileId() != null) &&
             * (this.usersForm.getProfileId().length() != 0) &&
             * (!"0".equals(this.usersForm.getProfileId()))) {
             * temp.setProfileId(new Long(this.usersForm.getProfileId())); }
             */
            temp.setProfileId(Constants.default_profile_id);

            temp.setIdentityCard(this.usersForm.getIdentityCard());

            if ((this.usersForm.getIssueDateIdent() != null) && (!this.usersForm.getIssueDateIdent().equals(""))) {
                temp.setIssueDateIdent(DateTimeUtils.convertStringToTime(this.usersForm.getIssueDateIdent(), "yyyy-MM-dd"));
            } else {
                temp.setIssueDateIdent(null);
            }
            if ((this.usersForm.getIssueDatePassport() != null) && (!this.usersForm.getIssueDatePassport().equals(""))) {
                temp.setIssueDatePassport(DateTimeUtils.convertStringToTime(this.usersForm.getIssueDatePassport(), "yyyy-MM-dd"));
            } else {
                temp.setIssueDatePassport(null);
            }

            temp.setIssuePlaceIdent(this.usersForm.getIssuePlaceIdent());
            temp.setIssuePlacePassport(this.usersForm.getIssuePlacePassport());

//            if (StringUtils.validString(this.usersForm.getPosId())) {
//                temp.setPosId(Long.valueOf(this.usersForm.getPosId()));
//            }
            if ((this.usersForm.getLocationId() != null) && (!this.usersForm.getLocationId().equals(""))) {
                temp.setLocationId(new Long(this.usersForm.getLocationId()));
            } else {
                temp.setLocationId(null);
            }

            temp.setPassportNumber(this.usersForm.getPassportNumber());
            temp.setStaffCode(this.usersForm.getStaffCode());

            if ((this.usersForm.getStatus() != null) && (!this.usersForm.getStatus().equals(""))) {
                temp.setStatus(new Long(this.usersForm.getStatus()));
            }

            temp.setTelephone(this.usersForm.getTelephone());
            /*
             if ((this.usersForm.getUserTypeId() != null) && (!this.usersForm.getUserTypeId().toString().equals(""))) {
             temp.setUserTypeId(new Long(this.usersForm.getUserTypeId()));
             }
             *
             */

            temp.setAliasName(this.usersForm.getAliasName());
            temp.setBirthPlace(this.usersForm.getBirthPlace());

            if (usersForm.getPassword() != null && !"".equals(usersForm.getPassword().trim())) {
                temp.setPassword(usersForm.getPassword());
                String encryptedPass = PasswordService.getInstance().encrypt(temp.getUserName().toLowerCase() + temp.getPassword());
                temp.setPassword(encryptedPass);
                Date now = new Date();
                if (!isUpdate.booleanValue()) {
                    int val = Math.abs(random.nextInt());
                    if (val < 100000) {
                        val += 100000;
                    }
                    temp.setCreateDate(now);
                    temp.setPasswordchanged(0L);
                    temp.setUserRight(Long.valueOf(0L));
                    temp.setLastResetPassword(now);
                } else {
                    temp.setLastResetPassword(now);
                }
            }
        } catch (Exception ex) {
            throw ex;
        }

        return temp;
    }

    /**
     * Kiem tra xem nguoi dung da ton tai chua
     *
     * @param userName
     * @param email
     * @param cellphone
     * @param userId
     * @return
     * @throws Exception
     */
    private Boolean checkUserExisted(String userName, Long userId) throws Exception {
        Boolean isExisted = Boolean.valueOf(false);
        Session session = getSession();

        String hql = "from Users        where userId != ?        and status != ?        and ( upper(userName) = ?)";
        try {
            Query q = session.createQuery(hql);
            q.setParameter(0, userId);
            q.setParameter(1, Long.valueOf(-1L));
            q.setParameter(2, userName.trim().toUpperCase());

            if ((q.list() != null) && (!q.list().isEmpty())
                    && (q.list().size() > 0)) {
                isExisted = Boolean.valueOf(true);
            }

            return isExisted;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }

    /**
     * 150119 binhnt53
     *
     * @param fullName
     * @param userId
     * @return
     * @throws Exception
     */
    public Boolean checkUserExist(String fullName, Long userId) throws Exception {
        Boolean isExisted = Boolean.valueOf(false);
        Session session = getSession();
        if (fullName == null || userId == null || fullName.equals("")) {
            return true;
        }
        String hql = "from Users where userId = ? "
                + "and status != ? "
                + "and fullName = ?)";
        try {
            Query q = session.createQuery(hql);
            q.setParameter(0, userId);
            q.setParameter(1, Long.valueOf(-1L));
            q.setParameter(2, fullName);
            if ((q.list() != null) && (!q.list().isEmpty())
                    && (q.list().size() > 0)) {
                isExisted = Boolean.valueOf(true);
            }
            return isExisted;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }

    /**
     * Tim kiem vai tro cua nguoi dung
     *
     * @return
     */
    public String searchRole() {
        try {

            getGridInfo();

            Session session = getSession();
            List listParam = new ArrayList();
            String countHQL = "select count(a) ";
            String searchHQL = "select a ";
            String hql = " from Roles a where 1 = 1 ";

            if ((this.userRoleFormOnDialog.getRoleName() != null)
                    && (!"".equals(this.userRoleFormOnDialog.getRoleName()))) {
                hql = hql + " and lower(roleName) like ? ESCAPE '/' ";
                listParam.add(StringUtils.toLikeString(this.userRoleFormOnDialog.getRoleName()));
            }

            if ((this.userRoleFormOnDialog.getStatus() != null)
                    && (!"".equals(this.userRoleFormOnDialog.getStatus())) && (!"ALL".equals(this.userRoleFormOnDialog.getStatus()))) {
                hql = hql + " and status = ?";
                listParam.add(new Long(this.userRoleFormOnDialog.getStatus()));
            }

            if ((this.userRoleFormOnDialog.getCode() != null)
                    && (!"".equals(this.userRoleFormOnDialog.getCode()))) {
                hql = hql + " and lower(roleCode) like ? ESCAPE '/' ";
                listParam.add(StringUtils.toLikeString(this.userRoleFormOnDialog.getCode()));
            }

            if ((this.userRoleFormOnDialog.getUserId() != null) && (!this.userRoleFormOnDialog.getUserId().equals(""))) {
                Long userId = Long.parseLong(this.userRoleFormOnDialog.getUserId());
                hql = hql + " and a.roleId not in( select b.roleId from RoleUser b   where b.userId = ?)";
                listParam.add(userId);
            }

            Query searchQuery = session.createQuery(hql);
            Query countQuery = session.createQuery(countHQL + hql);
            for (int i = 0; i < listParam.size(); i++) {
                searchQuery.setParameter(i, listParam.get(i));
                countQuery.setParameter(i, listParam.get(i));
            }
            Long total = (Long) countQuery.uniqueResult();
            searchQuery.setFirstResult(start);
            searchQuery.setMaxResults(count);
            List lst = searchQuery.list();

            this.jsonDataGrid.setItems(lst);
            this.jsonDataGrid.setTotalRows(total.intValue());
        } catch (Exception ex) {
        }
        return "gridData";
    }

    /*
     * TK role cua nguoi dung don vi(vai tro quan tri don vi)
     */
    public String searchRoleOfUserDept() {
        try {

            getGridInfo();

            Session session = getSession();
            List listParam = new ArrayList();
            String countHQL = "select count(a) ";
            String searchHQL = "select a ";
            String hql = " from Roles a where 1 = 1 ";

            if ((this.userRoleFormOnDialog.getRoleName() != null)
                    && (!"".equals(this.userRoleFormOnDialog.getRoleName()))) {
                hql = hql + " and lower(roleName) like ? ESCAPE '/' ";
                listParam.add(StringUtils.toLikeString(this.userRoleFormOnDialog.getRoleName()));
            }

            if ((this.userRoleFormOnDialog.getStatus() != null)
                    && (!"".equals(this.userRoleFormOnDialog.getStatus())) && (!"ALL".equals(this.userRoleFormOnDialog.getStatus()))) {
                hql = hql + " and status = ?";
                listParam.add(new Long(this.userRoleFormOnDialog.getStatus()));
            }

            if ((this.userRoleFormOnDialog.getCode() != null)
                    && (!"".equals(this.userRoleFormOnDialog.getCode()))) {
                hql = hql + " and lower(roleCode) like ? ESCAPE '/' ";
                listParam.add(StringUtils.toLikeString(this.userRoleFormOnDialog.getCode()));
            }

            String qtVofficeRole = "voffice_qt"; // ko cho phep thay role quan tri voffice
            hql = hql + " and lower(roleCode) != ? ";
            listParam.add(qtVofficeRole);

            if ((this.userRoleFormOnDialog.getUserId() != null) && (!this.userRoleFormOnDialog.getUserId().equals(""))) {
                Long userId = Long.parseLong(this.userRoleFormOnDialog.getUserId());
                hql = hql + " and a.roleId not in( select b.roleId from RoleUser b   where b.userId = ?)";
                listParam.add(userId);
            }

            Query searchQuery = session.createQuery(hql);
            Query countQuery = session.createQuery(countHQL + hql);
            for (int i = 0; i < listParam.size(); i++) {
                searchQuery.setParameter(i, listParam.get(i));
                countQuery.setParameter(i, listParam.get(i));
            }
            Long total = (Long) countQuery.uniqueResult();
            searchQuery.setFirstResult(start);
            searchQuery.setMaxResults(count);
            List lst = searchQuery.list();

            this.jsonDataGrid.setItems(lst);
            this.jsonDataGrid.setTotalRows(total.intValue());
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return "gridData";
    }

    /**
     * action xu ly gan vai tro cho nguoi dung
     *
     * @return
     */
    public String assignRole() {
        //String actor = (String) getRequest().getSession().getAttribute("user");
        HttpServletRequest req = getRequest();
        String roleIds = req.getParameter("roleId");
        String[] lstRoleId = roleIds.split(",");
        Long userId = 0l;
        try {
            userId = Long.parseLong(req.getParameter("userId"));
        } catch (Exception en) {
            System.out.print(en.getMessage());
            userId = 0l;
        }
        Users user = null;
        try {
            user = getUserById(userId);
        } catch (Exception en) {
            System.out.print(en.getMessage());
        }
        Long departmentId = 0L;
        if (user != null) {
            departmentId = user.getDeptId();
        }
        try {
            Session session = getSession();

            for (int i = 0; i < lstRoleId.length; i++) {
                Long roleId = new Long((String) lstRoleId[i]);

                RoleUser bo = new RoleUser();
                RoleUserPK pk = new RoleUserPK(roleId, userId);
                bo.setRoleUserPK(pk);
                bo.setIsAdmin(Long.valueOf(0L));
                bo.setIsActive(Long.valueOf(1L));

                session.save(bo);

            }

            GridResult result = getRoleUserDept(userId);
            this.jsonDataGrid.setItems(result.getLstResult());
            this.jsonDataGrid.setTotalRows(result.getnCount().intValue());
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return "gridData";
    }

    public String updateUserRoleDept() {
        String userStr = getRequest().getParameter("userId");
        String roleStr = getRequest().getParameter("roleId");
        String deptStr = getRequest().getParameter("deptId");
        String roleUserDeptStr = getRequest().getParameter("roleUserDeptId");

        Long userId = 0l;
        Long roleId = 0l;
        Long deptId = 0l;
        Long roleUserDeptId = 0l;
        List lstItems = new ArrayList<String>();
        try {
            userId = Long.parseLong(userStr);
            roleId = Long.parseLong(roleStr);
            deptId = Long.parseLong(deptStr);
            roleUserDeptId = Long.parseLong(roleUserDeptStr);
        } catch (Exception en) {
        }

        try {

            String hql = "select rud From RoleUserDept rud where rud.deptId = ? and rud.roleId=? and rud.userId = ? and rud.isActive=1";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, deptId);
            query.setParameter(1, roleId);
            query.setParameter(2, userId);
            List<RoleUserDept> lst = query.list();

            if (lst == null || lst.isEmpty()) {
                DepartmentDAOHE ddhe = new DepartmentDAOHE();
                Department dd = ddhe.getDeptById(deptId);

                Roles role = getRoleById(roleId);
                RoleUserDept rud = new RoleUserDept();
                rud.setUserId(userId);
                rud.setDeptId(deptId);
                rud.setDeptName(dd.getDeptName());

                rud.setRoleId(roleId);
                rud.setRoleName(role.getRoleName());
                rud.setRoleUserDeptId(roleUserDeptId);
                rud.setIsActive(1L);
                if (roleUserDeptId > 0) {
                    getSession().update(rud);
                } else {
                    getSession().save(rud);

                }
                lstItems.add("1");
                lstItems.add("Cập nhật thành công");
            } else {
                lstItems.add("0");
                lstItems.add("Đã tồn tại");
            }
        } catch (Exception en) {
            lstItems.add("0");
            lstItems.add("Có lỗi xảy ra");
        }
        jsonDataGrid.setItems(lstItems);
        return GRID_DATA;
    }

    /**
     * Action xu ly xoa bo vai tro cua nguoi dung
     *
     * @return
     */
    public String removeRole() {

        try {
            HttpServletRequest req = getRequest();
            Session session = getSession();
            String strRoleId = req.getParameter("roleId");
            String strRoleUserDeptId = req.getParameter("roleUserDeptId");
            Long userId = 0l;

            try {
                userId = Long.parseLong(req.getParameter("userId"));
            } catch (Exception en) {
                System.out.print(en.getMessage());
                userId = 0l;
            }

            Users user = null;
            try {
                user = getUserById(userId);
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
            Long departmentId = 0L;
            if (user != null) {
                departmentId = user.getDeptId();
            }

            if (userId != null && userId > 0 && strRoleId != null && (!"".equals(strRoleId))) {

                String[] roleUserDeptIdStr = strRoleUserDeptId.split(",");
                List<Long> lstRoleUserDeptIds = new ArrayList<Long>();

                if (roleUserDeptIdStr.length > 0) {
                    for (int i = 0; i < roleUserDeptIdStr.length; i++) {
                        try {
                            Long l = Long.parseLong(roleUserDeptIdStr[i]);
                            lstRoleUserDeptIds.add(l);
                        } catch (Exception en) {
                        }
                    }
                }

                String hql = " delete from RoleUserDept a where a.userId = :userId  and a.roleUserDeptId in (:lstRoleUserDept)";
                Query q = session.createQuery(hql);
                q.setParameter("userId", userId);
                q.setParameterList("lstRoleUserDept", lstRoleUserDeptIds);
                int succ = q.executeUpdate();
                getSession().flush();

                hql = " delete from RoleUser a where a.userId = :userId  and a.roleId in (:lstRole) and a.roleId not in (select rud.roleId from RoleUserDept rud where rud.isActive = 1 and rud.userId =:userIdOfRUD)";

                String[] roleIdStr = strRoleId.split(",");
                List<Long> lstRoleIds = new ArrayList<Long>();
                if (roleIdStr.length > 0) {
                    for (int i = 0; i < roleIdStr.length; i++) {
                        try {
                            Long l = Long.parseLong(roleIdStr[i]);
                            lstRoleIds.add(l);
                        } catch (Exception en) {
                        }
                    }
                }

                q = session.createQuery(hql);
                q.setParameter("userId", userId);

                q.setParameterList("lstRole", lstRoleIds);
                q.setParameter("userIdOfRUD", userId);

                succ = q.executeUpdate();

                GridResult result = getRoleUserDept(userId);
                this.jsonDataGrid.setItems(result.getLstResult());
                this.jsonDataGrid.setTotalRows(result.getnCount().intValue());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return "gridData";
    }

    /**
     * Tim kiem role cua user
     *
     * @return
     */
    public String searchRoleOfUsers() {
        try {
            getGridInfo();
            HttpServletRequest req = getRequest();
            Long userId = Long.parseLong(req.getParameter("userId"));
            if (userId != null && userId > 0) {
                GridResult result = getRoleUserDept(userId);
                this.jsonDataGrid.setItems(result.getLstResult());
                this.jsonDataGrid.setTotalRows(result.getnCount().intValue());
            }
        } catch (Exception ex) {
        }
        return "gridData";
    }

    /**
     * Action xu ly khoa role cua nguoi dung
     *
     * @return
     */
    public String lockRole() {
        try {
            HttpServletRequest req = getRequest();
            Session session = getSession();
            String strRoleId = req.getParameter("roleId");
            String strRoleUserDeptId = req.getParameter("roleUserDeptId");
            String ignore = req.getParameter("ignore");
            int succ = 0;
            Long userId = Long.parseLong(req.getParameter("userId"));

            if (userId != null && userId > 0 && StringUtils.validString(strRoleId)) {

                String[] roleIdStr = strRoleId.split(",");
                List<Long> lstRoleIds = new ArrayList<Long>();

                if (roleIdStr.length > 0) {
                    for (int i = 0; i < roleIdStr.length; i++) {
                        try {
                            Long l = Long.parseLong(roleIdStr[i]);
                            lstRoleIds.add(l);
                        } catch (Exception en) {
                        }
                    }
                }

                String[] roleUserDeptIdStr = strRoleUserDeptId.split(",");
                List<Long> lstRoleUserDeptIds = new ArrayList<Long>();

                if (roleUserDeptIdStr.length > 0) {
                    for (int i = 0; i < roleUserDeptIdStr.length; i++) {
                        try {
                            Long l = Long.parseLong(roleUserDeptIdStr[i]);
                            lstRoleUserDeptIds.add(l);
                        } catch (Exception en) {
                        }
                    }
                }

                String hql = " update RoleUserDept a set a.isActive = 0  where a.userId = :userId  and a.roleUserDeptId in (:lstRoleUserDept)";
                Query q = session.createQuery(hql);
                q.setParameter("userId", userId);
                q.setParameterList("lstRoleUserDept", lstRoleUserDeptIds);
                succ = q.executeUpdate();
                getSession().flush();
                //
                // Lock cac role
                //
                hql = " update RoleUser a set a.isActive = 0  where a.userId = :userId  and a.roleId in (:lstRole) and a.roleId not in (select rud.roleId from RoleUserDept rud where rud.isActive = 1 and rud.userId =:userIdOfRUD)";

                q = session.createQuery(hql);
                q.setParameter("userId", userId);

                q.setParameterList("lstRole", lstRoleIds);
                q.setParameter("userIdOfRUD", userId);

                succ = q.executeUpdate();
                //
                // end of lock role
                //

                GridResult result = getRoleUserDept(userId);
                this.jsonDataGrid.setItems(result.getLstResult());
                this.jsonDataGrid.setTotalRows(result.getnCount().intValue());
            }
            jsonDataGrid = createMsgUpdateStatus(succ, Integer.parseInt(ignore), new String[]{"Khóa", "khóa"});
        } catch (Exception ex) {
            log.error(ex.getMessage());
            jsonDataGrid = createMsgUpdate(0, 2);
        }
        return "gridData";
    }

    /**
     * aciton xu ly mo khoa vai tro cua nguoi dung
     *
     * @return
     */
    public String unLockRole() {
        try {
            HttpServletRequest req = getRequest();
            Session session = getSession();
            String strRoleId = req.getParameter("roleId");
            String strRoleUserDeptId = req.getParameter("roleUserDeptId");
            String ignore = req.getParameter("ignore");
            int succ = 0;
            Long userId = Long.parseLong(req.getParameter("userId"));

            if (userId != null && userId > 0 && StringUtils.validString(strRoleId)) {

                String[] roleUserDeptIdStr = strRoleUserDeptId.split(",");
                List<Long> lstRoleUserDeptIds = new ArrayList<Long>();

                if (roleUserDeptIdStr.length > 0) {
                    for (int i = 0; i < roleUserDeptIdStr.length; i++) {
                        try {
                            Long l = Long.parseLong(roleUserDeptIdStr[i]);
                            lstRoleUserDeptIds.add(l);
                        } catch (Exception en) {
                        }
                    }
                }

                String hql = " update RoleUserDept a set a.isActive = 1  where a.userId = :userId  and a.roleUserDeptId in (:lstRoleUserDept)";
                Query q = session.createQuery(hql);
                q.setParameter("userId", userId);
                q.setParameterList("lstRoleUserDept", lstRoleUserDeptIds);
                succ = q.executeUpdate();
                getSession().flush();

                hql = " update RoleUser a set a.isActive = 1  where a.userId = :userId  and a.roleId in (:lstRole)";

                String[] roleIdStr = strRoleId.split(",");
                List<Long> lstRoleIds = new ArrayList<Long>();
                if (roleIdStr.length > 0) {
                    for (int i = 0; i < roleIdStr.length; i++) {
                        try {
                            Long l = Long.parseLong(roleIdStr[i]);
                            lstRoleIds.add(l);
                        } catch (Exception en) {
                        }
                    }
                }

                q = session.createQuery(hql);
                q.setParameter("userId", userId);

                q.setParameterList("lstRole", lstRoleIds);

                succ = q.executeUpdate();

                GridResult result = getRoleUserDept(userId);
                this.jsonDataGrid.setItems(result.getLstResult());
                this.jsonDataGrid.setTotalRows(result.getnCount().intValue());
            }
            jsonDataGrid = createMsgUpdateStatusUnlock(succ, Integer.parseInt(ignore), new String[]{"Mở khóa", "hoạt động"});
        } catch (Exception ex) {
            log.error(ex.getMessage());
            jsonDataGrid = createMsgUpdate(0, 2);
        }
        return "gridData";
    }

    /**
     * tim kiem danh sach role theo userId
     *
     * @param userId
     * @return
     * @throws Exception
     */
    private GridResult getListCommonRole(Long userId) throws Exception {
        List lstParam = new ArrayList();
        String countHQL = "select count(a.roleId) ";
        String searchHQL = "select new com.viettel.vsaadmin.database.BO.Roles(a.roleId, a.roleName, a.roleCode ,a.description, b.isActive) ";
        String hql = " from Roles a,RoleUser b  where a.roleId = b.roleId ";

        Long isActive = null;

        if (userId != null && userId > 0) {
            hql = hql + " and b.userId = ?";
            lstParam.add(userId);
        }

        countHQL = countHQL + hql;
        searchHQL = searchHQL + hql;
        Query countQuery = getSession().createQuery(countHQL);
        Query searchQuery = getSession().createQuery(searchHQL);
        for (int j = 0; j < lstParam.size(); j++) {
            countQuery.setParameter(j, lstParam.get(j));
            searchQuery.setParameter(j, lstParam.get(j));
        }

        Long total = (Long) countQuery.uniqueResult();
        searchQuery.setFirstResult(start);
        searchQuery.setMaxResults(count);
        List lst = searchQuery.list();

        if (isActive != null) {
            for (int i = 0; i < lst.size(); i++) {
                Roles bo = (Roles) lst.get(i);
                bo.setStatus(isActive);
            }
        }

        GridResult result = new GridResult(total, lst);
        return result;
    }

    private GridResult getRoleUserDept(Long userId) throws Exception {
        List lstParam = new ArrayList();
        String countHQL = "select count(a.roleId) ";
        String searchHQL = "select new com.viettel.vsaadmin.database.BO.Roles(a.roleId, a.roleName, a.roleCode ,a.description, b.isActive) ";
        String hql = " from Roles a,RoleUser b  where a.roleId = b.roleId ";

        if (userId != null && userId > 0) {
            hql = hql + " and b.userId = ?";
            lstParam.add(userId);
        }

        countHQL = countHQL + hql;
        searchHQL = searchHQL + hql;
        Query countQuery = getSession().createQuery(countHQL);
        Query searchQuery = getSession().createQuery(searchHQL);
        for (int j = 0; j < lstParam.size(); j++) {
            countQuery.setParameter(j, lstParam.get(j));
            searchQuery.setParameter(j, lstParam.get(j));
        }

        Long total = (Long) countQuery.uniqueResult();
        searchQuery.setFirstResult(start);
        searchQuery.setMaxResults(count);
        List<Roles> lst = searchQuery.list();

        hql = "select r from RoleUserDept r where r.userId=?";
        searchQuery = getSession().createQuery(hql);
        searchQuery.setParameter(0, userId);
        List<RoleUserDept> lstRoleUserDept = searchQuery.list();
        List<RoleUserDept> lstResult = new ArrayList<RoleUserDept>();
        if (lst != null && lst.size() > 0) {
            for (int i = 0; i < lst.size(); i++) {
                boolean bHasDept = false;
                RoleUserDept entity = null;
                if (lstRoleUserDept != null && lstRoleUserDept.size() > 0) {
                    for (int j = 0; j < lstRoleUserDept.size(); j++) {
                        if (lstRoleUserDept.get(j).getRoleId().equals(lst.get(i).getRoleId())) {
                            bHasDept = true;
                            entity = lstRoleUserDept.get(j);
                            lstResult.add(entity);
                        }
                    }
                }
                if (!bHasDept) {
                    entity = new RoleUserDept();
                    entity.setUserId(userId);
                    entity.setRoleId(lst.get(i).getRoleId());
                    entity.setRoleName(lst.get(i).getRoleName());
                    entity.setDeptId(null);
                    entity.setDeptName(null);
                    entity.setRoleUserDeptId(null);
                    entity.setIsActive(lst.get(i).getStatus());
                    lstResult.add(entity);
                }
            }
        }

        GridResult result = new GridResult(total, lstResult);
        return result;
    }

    public String getShortName(String nameFull) {
        if (nameFull == null) {
            return "";
        }
        nameFull = nameFull.trim().toLowerCase();
        nameFull = removeVietnameseChar(nameFull);
        String shortName = "";
        String[] arrName = nameFull.split(" ");
        if (arrName == null || arrName.length <= 1) {
            shortName = nameFull;
        } else {
            arrName[arrName.length - 1] = arrName[arrName.length - 1].trim();
            shortName = arrName[arrName.length - 1];
            for (int i = 0; i < arrName.length - 1; i++) {
                arrName[i] = arrName[i].trim();
                if (arrName[i].length() == 0) {
                } else if (arrName[i].charAt(0) != ' ') {
                    shortName = shortName + arrName[i].charAt(0);
                }
            }
        }
        return shortName;
    }

    /* action lay danh sach lanh dao de xin y kien
     * Add: sytv
     * @param
     * @result
     */
    public String searchLeader() {
        getGridInfo();
        AtomicInteger countAI = new AtomicInteger(count);
        Department dept = getDepartment();
        Long deptId = dept.getDeptId();
//        List<Users> lstUserses = usersDAOHE.getUsersOfDept(deptId, start, countAI, sortField);
//        jsonDataGrid.setItems(lstUserses);
//        jsonDataGrid.setTotalRows(countAI.intValue());
        return GRID_DATA;
    }

    public String removeVietnameseChar(String name) {
        char[] vnChar = {'á', 'à', 'ả', 'ạ', 'â', 'ấ', 'ầ', 'ẩ', 'ậ', 'ă', 'ắ', 'ằ', 'ẳ', 'ặ', 'đ', 'é', 'è', 'ẻ', 'ẹ', 'ê', 'ế', 'ề', 'ể', 'ệ', 'í', 'ì', 'ỉ', 'ị', 'ó', 'ò', 'ỏ', 'ọ', 'ô', 'ố', 'ồ', 'ổ', 'ộ', 'ơ', 'ớ', 'ờ', 'ở', 'ợ', 'ú', 'ù', 'ủ', 'ụ', 'ư', 'ứ', 'ừ', 'ử', 'ự', 'ý', 'ỳ', 'ỷ', 'ỵ'};
        char[] engChar = {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'd', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'i', 'i', 'i', 'i', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'y', 'y', 'y', 'y'};
        for (int i = 0; i < name.length(); i++) {
            for (int j = 0; j < vnChar.length; j++) {
                if (name.charAt(i) == vnChar[j]) {
                    name = name.replace(vnChar[j], engChar[j]);
                }
            }
        }
        return name;
    }

    public Roles getRoleById(Long roleId) throws Exception {
        Roles temp = null;

        String hql = "from Roles where roleId = ?";
        Query q = getSession().createQuery(hql);
        q.setParameter(0, roleId);

        List lst = q.list();
        if (lst.size() > 0) {
            temp = (Roles) lst.get(0);
        }

        return temp;
    }

    private void createDataForm() {
        String userName = getRequest().getParameter("userName");
        String fullName = getRequest().getParameter("fullName");
        Long type = Long.parseLong(getRequest().getParameter("type"));
        String cellphone = getRequest().getParameter("cellphone");
        String status = getRequest().getParameter("status");
        String deptIdStr = getRequest().getParameter("deptId");
        Long deptId = null;
        try {
            deptId = Long.parseLong(deptIdStr);
        } catch (Exception en) {
        }
        if (this.usersForm == null) {
            this.usersForm = new UsersForm();
        }
        if (userName != null) {
            this.usersForm.setUserName(userName);
        }
        if (fullName != null) {
            this.usersForm.setFullName(fullName);
        }
        if (type != null) {
            this.usersForm.setUserTypeId(type);
        }
        if (cellphone != null) {
            this.usersForm.setCellphone(cellphone);
        }
        if (status != null) {
            this.usersForm.setStatus(status);
        }
        if (deptIdStr != null) {
            this.usersForm.setDeptId(deptId);
        }
    }

    public String onExcel() {
        createDataForm();
        return "excel";
    }

    /*
     * Lay danh sach nhan vien theo don vi.
     */
    public String getStaffsOfDept() {
        String deptIdStr = getRequest().getParameter("deptId");
        try {
            Long deptId = Long.valueOf(deptIdStr);
            if (deptId.equals(-1L)) {
                deptId = getDeptRepresentId();
            }
            List<Users> users = new ArrayList<Users>();
            users = usersDAOHE.getUsersOfDept(deptId, getUserId());
            Users selectUser = new Users();
            selectUser.setUserId(Constants.COMBOBOX_HEADER_VALUE);
            selectUser.setFullName(Constants.COMBOBOX_HEADER_TEXT_SELECT);
            List<Users> listUsers = new ArrayList<Users>();
            listUsers.add(selectUser);
            listUsers.addAll(users);

            staffsList.setLabel("fullName");
            staffsList.setIdentifier("userId");
            staffsList.setItems(listUsers);

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return "staffsList";
    }

    // Lay danh sach nhan vien cua cac don vi
    public String getStaffsOfDepts() {
        List result = new ArrayList();
        String deptIds = getRequest().getParameter("deptIds");
        try {
            List<Users> users = new ArrayList<Users>();
            users = usersDAOHE.getUsersOfDept(deptIds, getUserId());
            String usersList = new UsersDAOHE().convertToJSONArray(users, "userId", "fullName", "userName");
            result.add(usersList);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        jsonDataGrid.setItems(result);
        return GRID_DATA;
    }

    public String getUsersCoordinate() {
        String coordinateStaffs = getRequest().getParameter("staffs");
        getGridInfo();
        UsersDAOHE daohe = new UsersDAOHE();
        GridResult result = daohe.getCoordinateStaffs(coordinateStaffs, start, count);
        jsonDataGrid.setItems(result.getLstResult());
        jsonDataGrid.setTotalRows(result.getnCount().intValue());
        return GRID_DATA;
    }

    public String gotoProfilePage() {
        UserToken vsaUserToken = (UserToken) getRequest().getSession().getAttribute("userToken");
        Long currentUserId = vsaUserToken.getUserID();
        PositionDAOHE posDHE = new PositionDAOHE();
        List<Position> lst = posDHE.findAllActive();
        if (lst != null) {
            Position empty = new Position(-1L);
            empty.setPosName("--- Chọn ---");
            lst.add(0, empty);
        }
        getRequest().setAttribute("currentUserId", currentUserId);
        getRequest().setAttribute("lstPosition", lst);

        List result = new ArrayList();
        Long userId = getUserId();
        Users user = usersDAOHE.findById(userId);
        result.add(user);
        usersForm = new UsersForm();
        usersForm = usersDAOHE.boToForm(user, usersForm);
        return "userDialogStaff";
    }

    /*import users by file excel*/
    public String importStaffFromExcel() throws Exception {
        List customInfo = new ArrayList();//lst users
        Long attachId = Long.parseLong(getRequest().getParameter("attachId"));//get attactId
        VoAttachs att = (VoAttachs) getSession().get("com.viettel.voffice.database.BO.VoAttachs", attachId);//Attachs BO
        if (att == null) {// if att null return error users
            customInfo.add("error");
        } else {

            ResourceBundle rb = ResourceBundle.getBundle("config");//get link tuong doi
            String dir = rb.getString("directory");
            String linkFile = att.getAttachPath();
            linkFile = dir + linkFile;
            InputStream myxls = new FileInputStream(linkFile);//get file excel
//Get the workbook instance for XLS file
            HSSFWorkbook wb = new HSSFWorkbook(myxls);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFRow firstRow = sheet.getRow(1);
            int rowNums = sheet.getLastRowNum();
            UsersDAOHE sdhe = new UsersDAOHE();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
            String userError = "";

            for (int i = 1; i <= rowNums; i++) {
                try {
                    row = sheet.getRow(i);
                    if (row != null) {
                        Users entity = new Users();
                        HSSFCell cellUserName = row.getCell((short) 0);
                        HSSFCell cellFullName = row.getCell((short) 1);
                        HSSFCell cellEmail = row.getCell((short) 2);
                        HSSFCell cellCellPhone = row.getCell((short) 3);
                        HSSFCell cellDeptName = row.getCell((short) 4);
                        HSSFCell cellBusiness = row.getCell((short) 5);
                        HSSFCell cellPosition = row.getCell((short) 6);
                        HSSFCell cellGender = row.getCell((short) 7);
                        HSSFCell cellTelephone = row.getCell((short) 8);
                        HSSFCell cellFax = row.getCell((short) 9);
                        HSSFCell cellDateOfBirth = row.getCell((short) 10);
                        HSSFCell cellBirthPlace = row.getCell((short) 11);
                        HSSFCell cellStaffCode = row.getCell((short) 12);
                        HSSFCell cellIdentityCard = row.getCell((short) 13);
                        HSSFCell cellIssueDateIdent = row.getCell((short) 14);
                        HSSFCell cellIssuePlaceIdent = row.getCell((short) 15);
                        HSSFCell cellDescription = row.getCell((short) 16);
                        //validate input
                        if (cellUserName != null) {
                            entity.setUserName(cellUserName.toString());
                        } else {
                            userError += i + " lỗi Tài khoản,";
                            customInfo.add(userError);
                        }

                        if (cellFullName != null) {
                            entity.setFullName(cellFullName.toString());
                        } else {
                            userError += i + " lỗi Tên đầy đủ,";
                            customInfo.add(userError);
                        }

                        if (cellEmail.toString() != null && cellEmail.toString().length() > 0) {
                            entity.setEmail(cellEmail.toString());
                        }

                        if (cellCellPhone.toString() != null && cellCellPhone.toString().length() > 0) {
                            entity.setCellphone(cellCellPhone.toString());
                        }
                        //get dept
                        DepartmentDAOHE deptdhe = new DepartmentDAOHE();
                        Department deptBo = deptdhe.findByName(cellDeptName.toString());
                        if (deptBo != null) {
                            entity.setDeptName(deptBo.getDeptName());
                            entity.setDeptId(deptBo.getDeptId());
                        }
                        //get business
                        BusinessDAOHE busdhe = new BusinessDAOHE();
                        Business busbo = busdhe.findByName(cellBusiness.toString());
                        if (busbo != null) {
                            entity.setBusinessId(busbo.getBusinessId());
                            entity.setBusinessName(busbo.getBusinessName());
                        }
                        //get posId
                        PositionDAOHE posdhe = new PositionDAOHE();
                        Position pos = posdhe.findByName(cellPosition.toString());
                        if (pos != null) {
                            entity.setPosId(pos.getPosId());
                        } else {
                            userError += i + " lỗi Chức vụ,";
                            customInfo.add(userError);
                        }
                        if (cellTelephone != null) {
                            entity.setTelephone(cellTelephone.toString());
                        }
                        if (cellFax != null) {
                            entity.setFax(cellFax.toString());
                        }
                        if (cellBirthPlace != null) {
                            entity.setBirthPlace(cellBirthPlace.toString());
                        }
                        if (cellStaffCode != null) {
                            entity.setStaffCode(cellStaffCode.toString());
                        }
                        if (cellIdentityCard != null) {
                            entity.setIdentityCard(cellIdentityCard.toString());
                        }
                        if (cellIssuePlaceIdent != null) {
                            entity.setIssuePlaceIdent(cellIssuePlaceIdent.toString());
                        }
                        if (cellIssueDateIdent != null && cellIssueDateIdent.toString().length() > 0) {
                            entity.setIssueDateIdent(formatter.parse(cellIssueDateIdent.toString()));
                        }
                        if (cellDateOfBirth != null) {
                            entity.setDateOfBirth(formatter.parse(cellDateOfBirth.toString()));
                        }
                        if (cellDescription != null) {
                            entity.setDescription(cellDescription.toString());
                        }
                        // end validate input
                        String passwordEncrypt = PasswordService.getInstance().encrypt("Attp@123");
                        entity.setPassword(passwordEncrypt);
                        entity.setPasswordchanged(0L);
                        entity.setStatus(1L);
//                        entity.setDeptId(Long.parseLong(cellDeptId.toString()));
//                        entity.setPosId(Long.parseLong(cellPosId.toString()));
//                        entity.setStatus(Long.parseLong(cellStatus.toString()));
//                        entity.setGender(Long.parseLong(cellGender.toString()));
                        String gender = "";
                        if (cellGender == null) {
                            userError += i + " lỗi Giới tính,";
                            customInfo.add(userError);
                        } else {
                            gender = cellGender.toString().trim().toLowerCase();
                            if (gender.contains("Nam") || gender.contains("man") || gender.contains("male")) {
                                entity.setGender(0L);
                            } else {
                                entity.setGender(1L);
                            }
                        }
                        if (entity != null) {
                            getSession().saveOrUpdate(entity);
                            RoleUser roleUser = new RoleUser();
                            roleUser.setIsActive(1L);
                            roleUser.setIsAdmin(0L);
                            roleUser.setUserId(entity.getUserId());
                            roleUser.setRoleId(323L);
                            roleUser.setRoleUserPK(new RoleUserPK(322, entity.getUserId()));
                            getSession().saveOrUpdate(roleUser);
                            customInfo.add("success");
                        } else {
                            userError += i + ",";
                            customInfo.add(userError);
                        }

                    } // end if row != null
                } // end if att != null
                catch (Exception e) {
                    userError += i + ",";
                    customInfo.add(userError);
//                    jsonDataGrid.setCustomInfo(customInfo);
//                    return "gridData";
                }
            } // end loop

        }
        this.jsonDataGrid.setCustomInfo(customInfo);
        return "gridData";
    }

    public String checkPassIsChanged() {
        List resultMessage = new ArrayList();
        try {
            UserToken vsaUserToken = (UserToken) getRequest().getSession().getAttribute("userToken");
            Long currentUserId = vsaUserToken.getUserID();
            UsersDAOHE daohe = new UsersDAOHE();
            Users entity = daohe.findById(currentUserId);
            if (entity != null) {
                if (entity.getPasswordchanged().equals(1L)) {
                    resultMessage.add("1");
                    resultMessage.add("OK");
                } else {
                    resultMessage.add("0");
                    resultMessage.add("Mật khẩu của bạn hiện tại là mật khẩu mặc định, Vui lòng cập nhật mật khẩu mới");
                }
            }
            List<Long> lst = daohe.getListRoleIdsByUser(currentUserId);
            if (lst.size() > 0) {
                for (int i = 0; i < lst.size(); i++) {
                    RolesDAOHE rdhe = new RolesDAOHE();
                    Roles r = rdhe.findById(lst.get(i));
                    if (r != null) {
                        if (r.getRoleCode() != null && r.getRoleCode().equals("DN")) {
                            resultMessage.add("1");
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            //150206 binhnt53 update check null
                            if (entity != null) {
                                Business busbo = busdaohe.findById(entity.getBusinessId());
                                if (busbo != null) {
                                    resultMessage.add(busbo.getBusinessName());
                                } else if (entity.getBusinessName() != null) {
                                    resultMessage.add(entity.getBusinessName());
                                } else {
                                    resultMessage.add("");
                                }
                            } else {
                                resultMessage.add("");
                            }
                            break;
                            //!150206 binhnt53 update check null
                        }
                    }
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    //add new push businessAlert
    public String pushBusinessAlert() {
        List resultMessage = new ArrayList();
        try {
            UserToken vsaUserToken = (UserToken) getRequest().getSession().getAttribute("userToken");
            Long currentUserId = vsaUserToken.getUserID();
            UsersDAOHE daohe = new UsersDAOHE();
            Users entity = daohe.findById(currentUserId);
            if (entity != null) {
                if (entity.getBusinessId() != null) {
                    BusinessAlertDAOHE busalert = new BusinessAlertDAOHE();
                    List<BusinessAlert> lstBusinessAlert = busalert.findByBusinessAlertId(entity.getBusinessId());

                    if (lstBusinessAlert != null && lstBusinessAlert.size() > 0) {
                        resultMessage.add(1);
                        resultMessage.add(entity.getBusinessId());
                    } else {
                        resultMessage.add(0);
                        resultMessage.add(null);
                    }
                }
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public UsersForm getResetPassForm() {
        return resetPassForm;
    }

    public void setResetPassForm(UsersForm resetPassForm) {
        this.resetPassForm = resetPassForm;
    }

    public UsersForm getUsersForm() {
        return this.usersForm;
    }

    public void setUsersForm(UsersForm usersForm) {
        this.usersForm = usersForm;
    }

    public UserRoleForm getUserRoleFormOnDialog() {
        return this.userRoleFormOnDialog;
    }

    public void setUserRoleFormOnDialog(UserRoleForm userRoleFormOnDialog) {
        this.userRoleFormOnDialog = userRoleFormOnDialog;
    }

    public UsersForm getUsersFormSearch() {
        return this.usersFormSearch;
    }

    public void setUsersFormSearch(UsersForm usersFormSearch) {
        this.usersFormSearch = usersFormSearch;
    }

    public DepartmentGridForm getUserDeptForm() {
        return this.userDeptForm;
    }

    public void setUserDeptForm(DepartmentGridForm userDeptForm) {
        this.userDeptForm = userDeptForm;
    }

    public DepartmentGridForm getUserDeptFormOnDialog() {
        return this.userDeptFormOnDialog;
    }

    public void setUserDeptFormOnDialog(DepartmentGridForm userDeptFormOnDialog) {
        this.userDeptFormOnDialog = userDeptFormOnDialog;
    }

    public DepartmentForm getDepartmentForm() {
        return this.departmentForm;
    }

    public void setDepartmentForm(DepartmentForm departmentForm) {
        this.departmentForm = departmentForm;
    }

    public RoleGridForm getUserRoleForm() {
        return this.userRoleForm;
    }

    public void setUserRoleForm(RoleGridForm userRoleForm) {
        this.userRoleForm = userRoleForm;
    }

    public UserGridForm getListUserGridForm() {
        return this.listUserGridForm;
    }

    public void setListUserGridForm(UserGridForm listUserGridForm) {
        this.listUserGridForm = listUserGridForm;
    }

    public RoleGridForm getUnassignedRoleFormOnDialog() {
        return this.unassignedRoleFormOnDialog;
    }

    public void setUnassignedRoleFormOnDialog(RoleGridForm unassignedRoleFormOnDialog) {
        this.unassignedRoleFormOnDialog = unassignedRoleFormOnDialog;
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

    public DojoJSON getStaffsList() {
        return staffsList;
    }

    public void setStaffsList(DojoJSON staffsList) {
        this.staffsList = staffsList;
    }

    public UsersForm getUserPasswordForm() {
        return userPasswordForm;
    }

    public void setUserPasswordForm(UsersForm userPasswordForm) {
        this.userPasswordForm = userPasswordForm;
    }

    public BusinessForm getBusinessForm() {
        return businessForm;
    }

    public void setBusinessForm(BusinessForm businessForm) {
        this.businessForm = businessForm;
    }

}
