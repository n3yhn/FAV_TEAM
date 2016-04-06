/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAO;

import com.viettel.database.DAO.BaseDAOMDBAction;
import com.viettel.dojoTag.DojoJSON;
import com.viettel.common.util.ResourceText;
import java.util.ArrayList;
import java.util.List;
import viettel.passport.client.UserToken;
import com.viettel.common.util.Constants;
import com.viettel.crypto.AESCrypto;
import com.viettel.vsaadmin.database.BO.Department;
import com.viettel.vsaadmin.database.BO.Roles;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAO.UsersDAO;
import com.viettel.vsaadmin.database.DAOHibernate.DepartmentDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import java.util.Arrays;
import org.hibernate.Query;

/**
 *
 * @author gpdn_havm2
 */
public class BaseDAO extends BaseDAOMDBAction {

    /**
     * struts result.
     */
    protected static final String GRID_DATA = "gridData";
    protected static final String SET_PREFIX = "set";
    protected static final String GET_PREFIX = "get";
    protected static final String NAME_FIELD = "Name";
    protected static final String ID_FIELD = "Id";
    protected static final int ERROR_UPLOAD = -2;
    protected static final int ERROR_RUNTIME = -1;
    protected static final int ERROR_HEADER = 0;
    protected static final int SUCCESS_ONLY = 1;
    protected static final int FAILED_ONLY = 2;
    protected static final int SUCCESS_FAILED = 3;
    protected static final String SESSION_TIMEOUT = "sessionTimeout";
    protected static final String ERROR_PERMISSION = "loginErrorPermission";
    public static Long PROCESS_TYPE_MAIN_PROCESS = 1L;
    public static Long PROCESS_TYPE_DOCUMENT = -1L;
    public static Long PROCESS_TYPE_COOPERATE_PROCESS = 0L;
    public static Long PROCESS_TYPE_COMMENT = 2L;
    public static Long PROCESS_STATUS_NEW = 0L; // moi den
    public static Long PROCESS_STATUS_BOOKED = 1L; // da luu so don vi
    public static Long PROCESS_STATUS_INSTRUCTION = 2L; // da chuyen xin y kien chi dao
    public static Long PROCESS_STATUS_ASSIGNED = 3L; // da phan cong cho chuyen vien
    public static Long PROCESS_STATUS_FINISH = 4L; // hoan thanh
    public static Long PROCESS_STATUS_RETURN = 5L; // tra lai
    public static Long PROCESS_STATUS_APPROVED = 6L; // da phe duyet
    public static Long PROCESS_STATUS_NEXT = 7L; // da chuyen tiep
    public static Long PROCESS_JUST_REFERENCE = 11L; // Cong bo van ban
    public static Long PROCESS_CV1FINISH = 12L; // Chuyen vien 1 da hoan thanh xu ly, CV2, PP xu ly tiep(review)
    public static Long PROCESS_CV2FINISH = 13L; // Chuyen vien 2 da hoan thanh xu ly, PP xu ly tiep(review)
    public static Long PROCESS_PPFINISH = 14L; // PP hoan thanh xu ly
    public static Long DOING = 8L; // dang xu ly
    public static Long DOCUMENT_STATUS_NEW = 0L; // moi den
    public static Long DOCUMENT_STATUS_BOOKED = 1L; // da luu so don vi
    public static Long DOCUMENT_STATUS_INSTRUCTION = 2L; // da chuyen xin y kien chi dao
    public static Long DOCUMENT_STATUS_ASSIGNED = 3L; // da phan cong cho chuyen vien
    public static Long DOCUMENT_STATUS_FINISH = 4L; // hoan thanh
    public static Long DOCUMENT_STATUS_RETURN = 5L; // tra lai
    public static Long DOCUMENT_STATUS_STORAGE = 9L;  // da luu tru
    public static Long DOCUMENT_STATUS_CV1FINISH = 12L;  // Chuyen vien 1 da hoan thanh xu ly, CV2, PP xu ly tiep(review)
    public static Long DOCUMENT_STATUS_CV2FINISH = 13L; // Chuyen vien 2 da hoan thanh xu ly, PP xu ly tiep(review)
    public static Long DOCUMENT_STATUS_PPFINISH = 14L; // PP hoan thanh xu ly
    public static Long PROCESS_STATUS_JUST_REFERENCE = 1l; // nhan de biet
    public static Long OBJECT_TYPE_DOCUMENT_RECEIVE = 1l; // Van ban den
    public static Long OBJECT_TYPE_DOCUMENT_PUBLISH = 2l; // Van ban di
    public static Long OBJECT_TYPE_DOCUMENT_DIRECTION = 3l; // Van ban dieu hanh
    public static Long OBJECT_TYPE_PROFILE_WORK = 4l; // ho so cong viec
    public static Long OBJECT_TYPE_PROFILE_STORE = 5l; // Ho so luu tru
    public static Long OBJECT_TYPE_FORM = 6l; // Phieu yeu cau
    public static Long BOOK_TYPE_DOCUMENT_RECEIVE = 1l; // Phieu yeu cau
    public static Long BOOK_TYPE_DOCUMENT_PUBLISH = 2l; // Phieu yeu cau
    public static Long BOOK_TYPE_FORM = 3l; // Phieu yeu cau
    public static Long IS_ACTIVE = 1L;
    public static Long IN_ACTIVE = 0L;
    public static final String CATEGORY_VOLVB = "VOLVB";        // Code loai van ban
    public static final String OBJECT_CODE_VTCXL = "CXLVB";     // Van thu chuyen xu ly
    public static final String OBJECT_CODE_LDPD = "VBDI_LDDV_PD"; // Lanh dao phe 
    public static final String OBJECT_CODE_LDPD_CA = "VBDI_LDDV_PD_CA"; // Lanh dao phe duyet CA
    public static final String OBJECT_CODE_EDIT_DRAFT = "VBDI_EDIT_DRAFT"; // Quyen edit van ban du thao
    public static final String OBJECT_CODE_SEND_OUTSIDE = "VBDI_SEND_OUTSIDE"; // Quyen gui van ban di ra don vi ngoai
    public static final String OBJECT_CODE_SEND_DRAFT = "VBDI_SEND_DRAFT"; // Quyen gui van ban du thao len van thu co quan
    public static final String ROLE_ONLY_VIEW_NOT_ASSIGN = "ROLE_ONLY_VIEW_NOT_ASSIGN"; //Quyen chi duoc xem VB, ko duoc phan cong (TH TK LD BO duoc gan quyen Thứ trưởng Bo)
    public static Long BTP_ID = 52L; // ID Bo Tu Phap
    protected DojoJSON jsonData = new DojoJSON();
    protected DojoJSON jsonDataGrid = new DojoJSON();
    private String a = "aàáảãạăằắẳẵặâầấẩẫậ";
    private String e = "eèéẻẽẹêềếểễệ";
    private String i = "iìíỉĩị";
    private String o = "oòóỏõọôồốổỗộơờớởỡợ";
    private String u = "uùúủũụưừứửữự";
    private String d = "dđ";
    private String y = "yỳýỷỹỵ";
    private String A = "AÀÁẢÃẠĂẰẮẲẴẶÂẦẤẨẪẬ";
    private String E = "EÈÉẺẼẸÊỀẾỂỄỆ";
    private String I = "IÌÍỈĨỊ";
    private String O = "OÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢ";
    private String U = "UÙÚỦŨỤƯỪỨỬỮỰ";
    private String D = "DĐ";
    private String Y = "YỲÝỶỸỴ";

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(BaseDAO.class);

    /*
     *  default properties for grid
     */
    protected int start = 0;
    protected int count = 10;
    protected String sortField = "";
    /*
     * end default properties
     */

    public BaseDAO() {
    }

    public void getGridInfo() {
        if (getRequest() == null) {
            return;
        }
        String startstr = getRequest().getParameter("startval");
        String countstr = getRequest().getParameter("count");
        sortField = getRequest().getParameter("sort");

        try {
            if (startstr != null) {
                start = Integer.parseInt(startstr);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        try {
            if (countstr != null) {
                count = Integer.parseInt(countstr);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void getGridSesrchInfo() {
        if (getRequest() == null) {
            return;
        }
        String startClientStr = getRequest().getParameter("startGrid");
        String startCountStr = getRequest().getParameter("countGrid");
        sortField = getRequest().getParameter("sort");

        try {
            Integer startClient = 0;
            Integer startCount = 0;
            if (!startClientStr.isEmpty() && !startCountStr.isEmpty()) {
                startClient = Integer.parseInt(startClientStr);
                startCount = Integer.parseInt(startCountStr);
                if (startClient >= 0 && startCount > 0) {
                    start = startClient;
                    count = startCount;
                } else {
                    getGridInfo();
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public Long getUserId() {
        UserToken token = (UserToken) getRequest().getSession().getAttribute(Constants.VSA_USER_TOKEN);
        Long staffId = null;
        if (token != null) {
            staffId = token.getUserID();
        }
        return staffId;
    }

    //DiuLTT add
    public String getUserName() {
        UserToken token = (UserToken) getRequest().getSession().getAttribute(Constants.VSA_USER_TOKEN);
        String userName = token.getFullName();
        return userName;
    }

    public String getUserLogin() {
        UserToken token = (UserToken) getRequest().getSession().getAttribute(Constants.VSA_USER_TOKEN);
        String userName = token.getUserName();
        return userName;
    }

    public Users getUser() {
        Long staffId = getUserId();
        if (staffId != null) {
            UsersDAOHE usersDAOHE = new UsersDAOHE();
            return usersDAOHE.findById(staffId, false);
        } else {
            return null;
        }
    }

    public Department getDepartment() {
        DepartmentDAOHE dephe = new DepartmentDAOHE();
        if (getRequest().getSession().getAttribute("workingDeptId") != null) {
            Long deptId = (Long) getRequest().getSession().getAttribute("workingDeptId");
            Department dept = dephe.findById(deptId);
            return dept;
        } else {
            Long staffId = getUserId();
            Department dept = dephe.getDeptByUserId(staffId);
            return dept;
        }
    }

    public Long getProvinceId() {
        if (getRequest().getSession().getAttribute("provinceId") != null) {
            Long provinceId = (Long) getRequest().getSession().getAttribute("provinceId");
            return provinceId;
        } else {
            Department dept = getDepartment();
            if (dept != null) {
                return dept.getProvinceId();
            } else {
                return null;
            }
        }
    }

    public Long getDepartmentId() {
        if (getRequest().getSession().getAttribute("workingDeptId") != null) {
            Long deptId = (Long) getRequest().getSession().getAttribute("workingDeptId");
            return deptId;
        } else {
            if (getRequest().getSession().getAttribute("departmentId") != null) {
                Long deptId = (Long) getRequest().getSession().getAttribute("departmentId");
                return deptId;
            } else {
                Department dept = getDepartment();
                Long deptId = 0L;
                if (dept != null) {
                    deptId = dept.getDeptId();
                    getRequest().getSession().setAttribute("departmentId", deptId);
                }
                return deptId;
            }
        }
    }

    public String getDepartmentName() {
        if (getRequest().getSession().getAttribute("departmentName") != null) {
            String departmentName = (String) getRequest().getSession().getAttribute("departmentName");
            return departmentName;
        } else {
            Department dept = getDepartment();
            String departmentName = "";
            if (dept != null) {
                departmentName = dept.getDeptName();
                getRequest().getSession().setAttribute("departmentName", departmentName);
            }
            return departmentName;
        }
    }

    public Long getBusinessId() {
        if (getRequest().getSession().getAttribute("businessId") != null) {
            Long deptId = (Long) getRequest().getSession().getAttribute("businessId");
            return deptId;
        } else {
            Users usr = getUser();
            Long businessId = 0L;
            if (usr != null) {
                businessId = usr.getBusinessId();
                getRequest().getSession().setAttribute("businessId", businessId);
            }
            return businessId;
        }
    }

    public String getBusinessName() {
        if (getRequest().getSession().getAttribute("businessName") != null) {
            String businessName = (String) getRequest().getSession().getAttribute("businessName");
            return businessName;
        } else {
            Users usr = getUser();
            String businessName = "";
            if (usr != null) {
                businessName = usr.getBusinessName();
                getRequest().getSession().setAttribute("businessName", businessName);
            }
            return businessName;
        }
    }

//    public Department getDeptRepresent() {
//
//        Users user = getUser();
//        DepartmentDAOHE dephe = new DepartmentDAOHE();
//        Department dept = dephe.getDeptByUserId(user.getUserId());
//
//        if (user.getDeptRepresentId() != null) {
//            dept = dephe.getDeptRepresentByUserId(user.getUserId());
//        }
//        return dept;
//    }
    public Department getDeptRepresent() {
        DepartmentDAOHE dephe = new DepartmentDAOHE();
        if (getRequest().getSession().getAttribute("workingDeptId") != null) {
            Long deptId = (Long) getRequest().getSession().getAttribute("workingDeptId");
            Department dept = dephe.findById(deptId);
            return dept;
        } else {
            Users user = getUser();
            if (user == null) {
                return null;
            }
            Department dept = dephe.getDeptByUserId(user.getUserId());
            if (user.getDeptRepresentId() != null) {
                dept = dephe.getDeptRepresentByUserId(user.getUserId());
            }
            return dept;
        }
    }

    public Long getDeptRepresentId() {
        if (getRequest().getSession().getAttribute("workingDeptId") != null) {
            Long deptId = (Long) getRequest().getSession().getAttribute("workingDeptId");
            return deptId;
        } else {
            if (getRequest().getSession().getAttribute("deptRepresentId") != null) {
                Long deptId = (Long) getRequest().getSession().getAttribute("deptRepresentId");
                return deptId;
            } else {
                Department dept = getDeptRepresent();
                Long deptId = 0L;
                if (dept != null) {
                    deptId = dept.getDeptId();
                    getRequest().getSession().setAttribute("deptRepresentId", deptId);
                }
                return deptId;
            }
        }
    }

    public Department getDeptRepresentByUserId(Long userId) {
        Department dept = new Department();
        if (userId != null && userId != 0L) {
            UsersDAOHE userDaoHe = new UsersDAOHE();
            Users user = userDaoHe.findById(userId);
            DepartmentDAOHE dephe = new DepartmentDAOHE();
            dept = dephe.getDeptByUserId(userId);

            if (user.getDeptRepresentId() != null) {
                dept = dephe.getDeptRepresentByUserId(userId);
            }
        }

        return dept;
    }

    public String convertVietNameseToNoSign(String source) {

        for (int j = 0; j < a.length(); j++) {
            source = source.replace(a.charAt(j), 'a');
        }
        for (int j = 0; j < e.length(); j++) {
            source = source.replace(e.charAt(j), 'e');
        }
        for (int j = 0; j < i.length(); j++) {
            source = source.replace(i.charAt(j), 'i');
        }
        for (int j = 0; j < o.length(); j++) {
            source = source.replace(o.charAt(j), 'o');
        }
        for (int j = 0; j < u.length(); j++) {
            source = source.replace(u.charAt(j), 'u');
        }
        for (int j = 0; j < d.length(); j++) {
            source = source.replace(d.charAt(j), 'd');
        }
        for (int j = 0; j < y.length(); j++) {
            source = source.replace(y.charAt(j), 'y');
        }

        for (int j = 0; j < A.length(); j++) {
            source = source.replace(A.charAt(j), 'A');
        }
        for (int j = 0; j < E.length(); j++) {
            source = source.replace(E.charAt(j), 'E');
        }
        for (int j = 0; j < I.length(); j++) {
            source = source.replace(I.charAt(j), 'I');
        }
        for (int j = 0; j < O.length(); j++) {
            source = source.replace(O.charAt(j), 'O');
        }
        for (int j = 0; j < U.length(); j++) {
            source = source.replace(U.charAt(j), 'U');
        }
        for (int j = 0; j < D.length(); j++) {
            source = source.replace(D.charAt(j), 'D');
        }
        for (int j = 0; j < Y.length(); j++) {
            source = source.replace(Y.charAt(j), 'Y');
        }
        return source;
    }

    public String convertToSearchVietNamese(String columnName, String source, List lstParam) {
        if (source == null) {
            return source;
        }
        source = source.toLowerCase().trim();

        source = convertVietNameseToNoSign(source);
        source = source.replace("a", "[" + a + "]");
        source = source.replace("e", "[" + e + "]");
        source = source.replace("i", "[" + i + "]");
        source = source.replace("o", "[" + o + "]");
        source = source.replace("u", "[" + u + "]");
        source = source.replace("d", "[" + d + "]");
        source = " REGEXP_LIKE(lower(" + columnName + "),N'" + source + "') ";
        //lstParam.add(source);
        return source;
    }

    public String convertToLikeString(String source) {
        if (source == null) {
            return "";
        }

        source = source.trim().toLowerCase();
        String des = "";
        Boolean isSpecial;

        char[] specialChar = {'%', '_', '\''};
        for (int i = 0; i < source.length(); i++) {
            isSpecial = false;
            for (int j = 0; j < specialChar.length; j++) {
                if (specialChar[j] == source.charAt(i)) {
                    isSpecial = true;
                    break;

                }
            }
            if (isSpecial) {
                des = des + '!' + source.charAt(i);
            } else {
                des = des + source.charAt(i);
            }

        }
        return des;
    }

    public List<Long> getListRoleUser() {
        List<Long> lstReturn = new ArrayList<Long>();
        try {
            Long userId = getUserId();
            List listParam = new ArrayList();
            String sqlQuery = "select roleId from RoleUser where isActive = ? and userId = ?";
            listParam.add(1L);
            listParam.add(userId);
            Query query = getSession().createQuery(sqlQuery);
            for (int i = 0; i < listParam.size(); i++) {
                query.setParameter(i, listParam.get(i));
            }
            lstReturn = query.list();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return lstReturn;
    }

    public List<Roles> getListRolesByUser() {
        List<Roles> lstReturn = new ArrayList<Roles>();
        try {
            List<Long> lstRoleIds = getListRoleUser();
            UsersDAO user = new UsersDAO();
            for (int i = 0; i < lstRoleIds.size(); i++) {
                lstReturn.add((Roles) user.getRoleById(lstRoleIds.get(i)));
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return lstReturn;
    }

    public List<Roles> getRoles(Long deptId) {
        try {
            Long userId = getUserId();
            List listParam = new ArrayList();
            String sqlQuery = "select s from Roles s "
                    + " where s.roleId in (select ru.roleId from RoleUser ru where ru.isActive = ? and ru.userId = ?) "
                    + " and s.roleId not in (select rud.roleId from RoleUserDept rud where rud.deptId != ? and rud.userId = ? "
                    + " and rud.roleId not in (select rud2.roleId from RoleUserDept rud2 where rud2.deptId = ? and rud2.userId = ?)) "
                    + " and s.status = 1";
            listParam.add(1L);
            listParam.add(userId);
            listParam.add(deptId);
            listParam.add(userId);
            listParam.add(deptId);
            listParam.add(userId);

            Query query = getSession().createQuery(sqlQuery);
            for (int i = 0; i < listParam.size(); i++) {
                query.setParameter(i, listParam.get(i));
            }
            List<Roles> lstReturn = query.list();
            return lstReturn;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    /**
     * tra ve du lieu dojo cho ket qua cua action
     *
     * @param succ
     * @param err
     * @return
     */
    protected DojoJSON createMsgUpdate(int succ, int err) {
        int i = 0;
        List<String> lst = new ArrayList<String>();
        if (succ > 0) {
            i = i + 1;
        }
        if (err > 0) {
            i = i + 2;
        }
        lst.add(ResourceText.getString("alert.updatesucess"));
        lst.add(ResourceText.getString("alert.updaterror"));
        jsonData.setItems(lst);
        jsonData.setCustomInfo(String.valueOf(i));
        return jsonData;
    }

    /**
     * tra ve du lieu dojo cho ket qua cua action
     *
     * @param success (true: success; false: failed.)
     * @return
     */
    protected DojoJSON createMsgExport(boolean success, String filePath) {
        int i = 0;
        List<String> lst = new ArrayList<String>();
        if (success) {
            i = 1;
        } else {
            i = 2;
        }
        lst.add(ResourceText.getString("alert.exportSuccess"));
        lst.add(ResourceText.getString("alert.exportError"));
        lst.add(filePath);
        jsonData.setItems(lst);
        jsonData.setCustomInfo(String.valueOf(i));
        return jsonData;
    }

    /**
     * tra ve du lieu dojo cho ket qua cua action
     *
     * @param success (-2: error upload; -1: error runtime; 0: error header; 1:
     * success; 2: failed; 3: success and failed)
     * @return
     */
    protected DojoJSON createMsgImport(int success_type, int succ, int err, String filePath) {
        int i = 0;
        if (success_type > 0) {
            i = success_type;
        } else {
            i = 2;
        }
        List<String> lst = new ArrayList<String>();
        lst.add(ResourceText.getString("alert.importSuccess").replace("{0}", String.valueOf(succ)));
        if (success_type == ERROR_UPLOAD) {
            lst.add(ResourceText.getString("alert.importErrorUpload"));
        } else if (success_type == ERROR_RUNTIME) {
            lst.add(ResourceText.getString("alert.importErrorRuntime"));
        } else if (success_type == ERROR_HEADER) {
            lst.add(ResourceText.getString("alert.importErrorHeader"));
        } else {
            lst.add(ResourceText.getString("alert.importError").replace("{0}", String.valueOf(err)));
        }
        lst.add(filePath);
        jsonData.setItems(lst);
        jsonData.setCustomInfo(String.valueOf(i));
        return jsonData;
    }

    protected String encryptDataByUserName(String source) {
        String des = "";
        try {
            String userName = getUserName();
            AESCrypto crypto = new AESCrypto(userName);
            des = crypto.encrypt(source);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return des;

    }

    protected String decryptDataByUserName(String source) {
        String des = "";
        try {
            String userName = getUserName();

            AESCrypto crypto = new AESCrypto(userName);
            des = crypto.decrypt(source);
        } catch (Exception en) {
        }
        return des;

    }

    /**
     * tra ve du lieu dojo cho ket qua cua action
     *
     * @param succ
     * @param err
     * @param listNotDel
     * @return
     */
    protected DojoJSON createMsgDelete(int succ, int err, List<String> listNotDel) {
        int i = 0;
        List<String> lst = new ArrayList<String>();
        lst.add(ResourceText.getString("alert.deleteSuccess").replace("{0}", String.valueOf(succ)));
        lst.add(ResourceText.getString("alert.deleteError").replace("{0}", String.valueOf(err)));
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

    /**
     * tra ve du lieu dojo cho ket qua cua action
     *
     * @param succ succ
     * @param ignore ignore
     * @param listNotDel
     * @return
     */
    protected DojoJSON createMsgUpdateStatus(int succ, int ignore, String... statusName) {
        int i = 0;
        List<String> lst = new ArrayList<String>();
        String msgIgnore = ResourceText.getString("alert.updateStatusError");
        msgIgnore = msgIgnore.replace("{0}", String.valueOf(ignore));
        String msgSucc = ResourceText.getString("alert.updateStatusSuccess");
        msgSucc = msgSucc.replace("{0}", String.valueOf(succ));
        if (statusName.length >= 2) {
            msgSucc = msgSucc.replace("{1}", statusName[0]);
        }
        lst.add(msgSucc);
        lst.add(msgIgnore);
        if (succ > 0) {
            i = i + 1;
        }
        if (ignore > 0) {
            i = i + 2;
        }
        jsonData.setItems(lst);
        jsonData.setCustomInfo(String.valueOf(i));
        return jsonData;
    }

    protected DojoJSON createMsgUpdateStatusUnlock(int succ, int ignore, String... statusName) {
        int i = 0;
        List<String> lst = new ArrayList<String>();
        String msgIgnore = ResourceText.getString("alert.updateStatusErrorUnlock");
        msgIgnore = msgIgnore.replace("{0}", String.valueOf(ignore));
        String msgSucc = ResourceText.getString("alert.updateStatusSuccess");
        msgSucc = msgSucc.replace("{0}", String.valueOf(succ));
        if (statusName.length >= 2) {
            msgSucc = msgSucc.replace("{1}", statusName[0]);
        }
        lst.add(msgSucc);
        lst.add(msgIgnore);
        if (succ > 0) {
            i = i + 1;
        }
        if (ignore > 0) {
            i = i + 2;
        }
        jsonData.setItems(lst);
        jsonData.setCustomInfo(String.valueOf(i));
        return jsonData;
    }

    public String validateColumnName(String name) {
        Character[] aZ = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'u', 'x', 'y', 'z', '_',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        List<Character> aToZ = new ArrayList<Character>();
        aToZ.addAll(Arrays.asList(aZ));
        if (name != null && !"".equals(name)) {
            if (name.length() > 35) {
                return "";
            } else {
                for (int ind = 0; ind < name.length(); ind++) {
                    if (!aToZ.contains(name.charAt(ind))) {
                        return "";
                    }
                }
            }
        }
        return name;
    }

    public DojoJSON getJsonData() {
        return jsonData;
    }

    public void setJsonData(DojoJSON jsonData) {
        this.jsonData = jsonData;
    }

    public DojoJSON getJsonDataGrid() {
        return jsonDataGrid;
    }

    public void setJsonDataGrid(DojoJSON jsonDataGrid) {
        this.jsonDataGrid = jsonDataGrid;
    }
}
