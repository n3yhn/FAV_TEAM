package com.viettel.voffice.database.DAO;

import com.viettel.common.util.ComboBoxItem;
import com.viettel.common.util.Constants;
import com.viettel.hqmc.DAO.FeeDao;
import com.viettel.hqmc.DAOHE.FilesNoClobDAOHE;
import com.viettel.hqmc.DAOHE.NotificationDAOHE;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import com.viettel.voffice.database.DAOHibernate.EventLogDAOHE;
import com.viettel.vsaadmin.client.form.DepartmentForm;
import com.viettel.vsaadmin.database.BO.Position;
import com.viettel.vsaadmin.database.BO.RoleUserDept;
import com.viettel.vsaadmin.database.BO.VDepartment;
import com.viettel.vsaadmin.database.DAO.RolesDAO;
import com.viettel.vsaadmin.database.DAOHibernate.PositionDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import com.viettel.ws.client.UtilDAO;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.*;
import org.hibernate.Query;
import viettel.passport.client.ObjectToken;
import viettel.passport.client.UserToken;

// Referenced classes of package com.viettel.database.DAO:
//            BaseDAOMDBAction
public class AuthenticateDAO extends BaseDAO {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AuthenticateDAO.class);
    boolean loadCountFilesNeedToPropose = false;
    boolean loadCountFilesNeedToAssign = false;
    boolean loadCountFilesNeedToEvaluate = false;
    boolean loadCountFilesNeedToReview = false;
    boolean loadCountFilesNeedToApprove = false;
    boolean loadCountFilesNeedToSend = false;
    boolean loadCountFilesNeedToReceive = false;
    String countFilesNeedToPropose = "";
    String countFilesNeedToAssign = "";
    String countFilesNeedToEvaluate = "";
    String countFilesNeedToReview = "";
    String countFilesNeedToApprove = "";
    String countFilesNeedToSend = "";
    String countFilesNeedToReceive = "";
    List lstItem;

    public List<ComboBoxItem> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<ComboBoxItem> lstItem) {
        this.lstItem = lstItem;
    }

    public AuthenticateDAO() {
    }

    /**
     * Home page
     *
     * @return
     */
    public String getHomePage() {
        try {
            FilesNoClobDAOHE bnhe = new FilesNoClobDAOHE();
            // Tổng số hồ sơ đang xử lý
            getRequest().setAttribute("countFileIsProcess", bnhe.getCountFileIsProcessHomePage());
            // Tổng số hồ sơ chờ tiếp nhận
            getRequest().setAttribute("countSelectNewHome", bnhe.getCountSelectNewHomePage());
            // Tổng số hồ sơ chờ tiếp nhận bổ sung
            getRequest().setAttribute("countSelectNewSdbs", bnhe.getCountSelectNewSdbs());
            // Tổng số hồ sơ đang chờ bồ sung
            getRequest().setAttribute("countSelectEVALUATED_TO_ADD", bnhe.getCountSelectEVALUATED_TO_ADDHomePage());
            // Tổng số hồ sơ đang chờ xác nhận nộp phí thẩm xét
            getRequest().setAttribute("countSelectReceivePay", bnhe.getCountSelectRECEIVE_PAYHomePage());
            // Tổng số hồ sơ đã phê duyệt chờ nộp lệ phí
            getRequest().setAttribute("countSelectApprovePay", bnhe.getCountSelectAPPROVE_PAYHomePage());
            // Tổng số hồ sơ chờ đối chiếu
            //getRequest().setAttribute("countSelectWaitGiveBack", bnhe.getCountSelectWaitGiveBackHomePage());
            // Tổng số hồ sơ đã trả bản công bố
            //getRequest().setAttribute("countSelectGiveBack", bnhe.getCountSelectGiveBackHomePage());
            // Tổng số hồ sơ đã được phê duyệt
            getRequest().setAttribute("countSelectGIVE_BACK", bnhe.getCountSelectGIVE_BACKHomePage());
            NotificationDAOHE ndaohe = new NotificationDAOHE();
            getRequest().setAttribute("lstNotice", ndaohe.GetNOTICE());
        } catch (Exception e) {
            log.error(e);
        }
        try {
            String type = getRequest().getParameter("type");
            if (type != null && type.equals("lookupBYT")) {
                return "LookupBYT.Page";
            } else {
                return "HomePage.Page";
            }
        } catch (Exception ex) {
            return "HomePage.Page";
        }
    }

    /**
     * Procedure
     *
     * @return
     */
    public String getProcedureHomePage() {
        return "procedureHomePage.Page";
    }

    /**
     * Contact
     *
     * @return
     */
    public String getContactHomePage() {
        return "contactHomePage.Page";
    }

    public void getKeypayPage() {
        String feeInfoId = getRequest().getParameter("feeInfoId");
        System.out.println("\n");
        System.out.println(feeInfoId);
        String command = getRequest().getParameter("command");
        System.out.println(command);
        String merchant_trans_id = getRequest().getParameter("merchant_trans_id");
        System.out.println(merchant_trans_id);
        String merchant_code = getRequest().getParameter("merchant_code");
        System.out.println(merchant_code);
        String responseCode = getRequest().getParameter("response_code");
        System.out.println(responseCode);
        String trans_id = getRequest().getParameter("trans_id");
        System.out.println(trans_id);
        String good_code = getRequest().getParameter("good_code");
        System.out.println(good_code);
        String net_cost = getRequest().getParameter("net_cost");
        System.out.println(net_cost);
        String ship_fee = getRequest().getParameter("ship_fee");
        System.out.println(ship_fee);
        String tax = getRequest().getParameter("tax");
        System.out.println(tax);
        String service_code = getRequest().getParameter("service_code");
        System.out.println(service_code);
        String currency_code = getRequest().getParameter("currency_code");
        System.out.println(currency_code);
        String bank_code = getRequest().getParameter("bank_code");
        System.out.println(bank_code);
        String desc_1 = getRequest().getParameter("desc_1");
        System.out.println(desc_1);
        String desc_2 = getRequest().getParameter("desc_2");
        System.out.println(desc_2);
        String desc_3 = getRequest().getParameter("desc_3");
        System.out.println(desc_3);
        String desc_4 = getRequest().getParameter("desc_4");
        System.out.println(desc_4);
        String desc_5 = getRequest().getParameter("desc_5");
        System.out.println(desc_5);
        String secure_hash = getRequest().getParameter("secure_hash");
        System.out.println(secure_hash);
        String files_code = getRequest().getParameter("files_code");
        System.out.println(files_code);
        String paymentInfo = "command:" + command + ";" + "merchant_trans_id:" + merchant_trans_id + ";"
                + "merchant_code:" + merchant_code + ";" + "response_code:" + responseCode + ";" + "trans_id:" + trans_id + ";" + "good_code:" + good_code
                + ";" + "net_cost:" + net_cost + ";" + "ship_fee:" + ship_fee + ";" + "tax:" + tax + ";" + "service_code:" + service_code + ";"
                + "currency_code:" + currency_code + ";" + "bank_code:" + bank_code + ";" + "desc_1:" + desc_1 + ";" + "desc_2:" + desc_2 + ";" + "desc_3:" + desc_3 + ";" + "desc_4:" + desc_4 + ";" + "desc_5:" + desc_5 + ";" + "secure_hash:" + secure_hash + ";";
//        System.out.println(paymentInfo);
        EventLogDAOHE edhe = new EventLogDAOHE();//A 16 07 29
        edhe.insertEventLog("KEYPAY", "paymentInfo=" + paymentInfo, getRequest());
        String hash = command + ";" + merchant_trans_id + ";"
                + merchant_code + ";" + responseCode + ";" + trans_id + ";" + good_code
                + ";" + net_cost + ";" + ship_fee + ";" + tax + ";" + service_code + ";"
                + currency_code + ";" + bank_code + ";" + desc_1 + ";" + desc_2 + ";" + desc_3 + ";" + desc_4 + ";" + desc_5 + ";" + secure_hash + ";";
//        System.out.println(hash);
        FeeDao feedao = new FeeDao();
        try {
            Thread.sleep(4000);
            System.out.println("175");
            if (feedao.checkPaymentStatus(feeInfoId) == true && responseCode.equals("00")) {
                // cap nhat ban ghi khi thanh toan thanh cong
                System.out.println("Err_01");
                feedao.onInsertFeePaymentInfoIpn(paymentInfo, files_code, hash, feeInfoId);
                System.out.println("Err_02");
                // xac nhan lai ban ghi thanh toan thanh cong
                String[] lstfeeInfoId = feeInfoId.split(",");
                System.out.println("Err_03");
                int countObj = lstfeeInfoId.length;
                System.out.println("Err_04");
                Long feeInfoIdCheck;
                System.out.println("Err_05");
                for (int i = 0; i < countObj; i++) {
                    //feeInfoIdCheck = Long.parseLong(lstfeeInfoId[i]);
                    feedao.onSavePaymentOnlineIPN(lstfeeInfoId[i]);
                }
                System.out.println("Err_06");
            }
        } catch (Exception ex) {            
            edhe.insertEventLog("KEYPAY", "Err=" + "Loi cap nhat KeyPay qua IPN, ma thanh toan: " + files_code + ex, getRequest());//A 16 07 29
            System.out.println("Err_07");
            System.out.println("Loi cap nhat KeyPay qua IPN, ma thanh toan: " + files_code + ex);
        }
    }

    /**
     * Guide RegisterHtmlPage
     *
     * @return
     */
    public String getGuideRegisterHtmlPage() {
        String type = getRequest().getParameter("type");
        getRequest().setAttribute("typeHtmlPage", type);
        return "guideRegisterHtmlPage.Page";
    }

    /**
     * Guide
     *
     * @return
     */
    public String getGuideLineHomePage() {
        return "guideLineHomePage.Page";
    }

    /**
     * Guide Video
     *
     * @return
     */
    public String getGuideLineVideoHomePage() {
        String type = getRequest().getParameter("type");
        getRequest().setAttribute("typeVideo", type);
        return "guideLineVideoHomePage.Page";
    }

    /**
     * Register
     *
     * @return
     */
    public String getRegisterHomePage() {
        List lstCategory = new ArrayList();
        List lstCategory1 = new ArrayList();
        List lstCategory2 = new ArrayList();
        try {
            //todo code here
            CategoryDAOHE cdhe = new CategoryDAOHE();
            List lstProvince = cdhe.findAllCategory("PROVINCE");

            lstCategory.addAll(lstProvince);
            lstCategory.add(0, new Category(0l, "-- Chọn --"));
            getRequest().setAttribute("lstProvince", lstCategory);

            List lstBusinessType = cdhe.findAllCategory("BUSTYPE");

            lstCategory1.addAll(lstBusinessType);
            lstCategory1.add(0, new Category(0l, "-- Chọn --"));
            getRequest().setAttribute("lstBusinessType", lstCategory1);

            List lstPosId = cdhe.findAllPositionDn();
            lstCategory2.addAll(lstPosId);
            lstCategory2.add(0, new Position(0l, "-- Chọn --"));
            lstCategory2.add(new Position(-1l, "Chức vụ khác"));
            getRequest().setAttribute("lstPosId", lstCategory2);

        } catch (Exception ex) {
            lstCategory.add(0, new Category(0l, "-- Chọn --"));
            getRequest().setAttribute("lstProvince", lstCategory);
            lstCategory1.add(0, new Category(0l, "-- Chọn --"));
            getRequest().setAttribute("lstBusinessType", lstCategory1);
            lstCategory2.add(0, new Position(0l, "-- Chọn --"));
            getRequest().setAttribute("lstPosId", lstCategory2);
            log.error(ex.getMessage());
        }
        return "registerHomePage.Page";
    }

    public String getResetPasswordHomePage() {
        try {
            //todo code here            
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return "resetPasswordHomePage.Page";
    }

    public String getIndexPage()
            throws Exception {
        HttpServletRequest req = getRequest();
        HttpSession session = req.getSession();
        String forwardPage = "indexSuccess";
        UserToken userToken = null;
        String changeDept = getRequest().getParameter("changeDept");
        if (session.getAttribute("workingDeptId") == null || (changeDept != null && changeDept.trim().equals("true"))) {
            UserToken vsaUserToken = (UserToken) session.getAttribute("vsaUserToken");
            if (vsaUserToken == null || vsaUserToken.getUserID() == null) {
                forwardPage = "loginErrorPermission";
                return forwardPage;
            }

            String hql = "select u from RoleUserDept u where u.isActive=1 and u.userId=?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, vsaUserToken.getUserID());

            List<RoleUserDept> lstRUD = query.list();

            hql = "select r from Roles r, RoleUser u where u.isActive=1 and r.status =1 and r.roleId=u.roleId and u.userId=?";
            query = getSession().createQuery(hql);
            query.setParameter(0, vsaUserToken.getUserID());
            RolesDAO rd = new RolesDAO();
            Long roleIdOfDeputy = rd.getRoleIdByCode(Constants.ROLES.LEAD_MONITOR_ROLE);
            boolean bHasRoleDeputy = false;
            List<DepartmentForm> lstDepartment = new ArrayList<DepartmentForm>();
            for (int i = 0; i < lstRUD.size(); i++) {
                if (lstRUD.get(i).getRoleId().equals(roleIdOfDeputy)) {
                    //
                    // nhung cai role dai dien cho nhieu don vi thi bo qua va add sau
                    //
                    bHasRoleDeputy = true;
                } else {
                    DepartmentForm form = new DepartmentForm();
                    form.setDeptId(lstRUD.get(i).getDeptId());
                    form.setDeptName(lstRUD.get(i).getDeptName());
                    lstDepartment.add(form);
                }
            }

            if (bHasRoleDeputy) {
                //
                // tim Don vi cao nhat (level thap nhat)
                //
                hql = "select v from VDepartment v where v.deptId in ( select rud.deptId from RoleUserDept rud where rud.isActive = 1 and rud.userId = ? and rud.roleId = ? ) or v.deptId in ( select u.deptId from Users u where u.userId = ?)";
                query = getSession().createQuery(hql);
                query.setParameter(0, vsaUserToken.getUserID());
                query.setParameter(1, roleIdOfDeputy);
                query.setParameter(2, vsaUserToken.getUserID());
                List<VDepartment> lstDept = query.list();

                if (lstDept != null) {
                    Long deptId = 0l;
                    String deptName = "";
                    Long deptLevel = 100l;
                    for (int i = 0; i < lstDept.size(); i++) {
                        if (lstDept.get(i).getDeptLevel() < deptLevel) {
                            deptId = lstDept.get(i).getDeptId();
                            deptName = lstDept.get(i).getDeptName();
                            deptLevel = lstDept.get(i).getDeptLevel();
                        }
                    }
                    DepartmentForm form = new DepartmentForm();
                    form.setDeptId(deptId);
                    form.setDeptName(deptName);
                    lstDepartment.add(form);

                }
            }

            boolean checkHasRoleNoDept = false;
            //
            // kiem tra neu khong co role trong role_user ma ko co trong role_user_dept thi gan don vi hanh chinh la don vi
            //
            hql = "select count(*) from RoleUser u where u.isActive=1 and u.userId=? and u.roleId not in ( select rud.roleId from RoleUserDept rud where rud.userId=? and rud.isActive=1 )";
            query = getSession().createQuery(hql);
            query.setParameter(0, vsaUserToken.getUserID());
            query.setParameter(1, vsaUserToken.getUserID());
            Long countRU = (Long) query.uniqueResult();
            if (countRU > 0L) {
                checkHasRoleNoDept = true;
            }

            if (checkHasRoleNoDept) {
//                DepartmentForm form = new DepartmentForm();
//                DepartmentDAOHE dephe = new DepartmentDAOHE();
//                Department dept = dephe.getDeptByUserId(vsaUserToken.getUserID());
//                if (dept == null) {
//                    forwardPage = "loginErrorPermission";
//                    return forwardPage;
//                }
//                form.setDeptId(dept.getDeptId());
//                form.setDeptName(dept.getDeptName());
//                lstDepartment.add(0, form);
            }

            if (lstDepartment.size() > 1) {
                //
                // Loai bo cac don vi bi trung di
                //
                for (int i = lstDepartment.size() - 1; i > 0; i--) {
                    for (int j = 0; j < i; j++) {
                        if (lstDepartment.get(i).getDeptId().equals(lstDepartment.get(j).getDeptId())) {
                            lstDepartment.remove(i);
                            break;
                        }
                    }
                }
            }

            if (lstDepartment.size() > 1) {
                forwardPage = "selectDept";
                session.setAttribute("userId", vsaUserToken.getUserID());
                if (changeDept != null && changeDept.trim().equals("true")) {
                } else {
                    session.removeAttribute("vsaUserToken");
                }
                loadToken(lstDepartment.get(0).getDeptId());

                for (int i = 0; i < lstDepartment.size() - 1; i++) {
                    DepartmentForm item = lstDepartment.get(i);
                    item.setParentId(lstDepartment.get(i + 1).getDeptId());
                    item.setDeptParentName(lstDepartment.get(i + 1).getDeptName());
                    lstDepartment.set(i, item);
                }

                for (int i = lstDepartment.size() - 1; i > 0; i--) {
                    if (i % 2 == 1) {
                        lstDepartment.remove(i);
                    }
                }
                getRequest().setAttribute("lstDepartment", lstDepartment);

                return forwardPage;
            } else if (lstDepartment.size() == 1) {
                req.setAttribute("canSelectDept", 1);
                req.setAttribute("hasOneDept", "true");
                req.setAttribute("displayChangeDept", "none");
                if (lstRUD.size() > 0) {
                    session.setAttribute("workingDeptId", lstDepartment.get(0).getDeptId());
                }
            } else {
                //forwardPage = "loginErrorPermission";
                //return forwardPage;
            }
        }

        try {
            UserToken vsaUserToken = (UserToken) session.getAttribute("vsaUserToken");
            PositionDAOHE posDHE = new PositionDAOHE();
            Position pos = posDHE.findPositionCode(vsaUserToken.getUserID());
            if (pos != null) {
                session.setAttribute("userPosition", pos.getPosName());
            }
            Date sysDate = posDHE.getSysdate();
            session.setAttribute("sysDate", sysDate);
            if (vsaUserToken != null) {
                userToken = new UserToken();
                userToken.setUserID(vsaUserToken.getUserID());
                userToken.setFullName(vsaUserToken.getFullName());
                session.setAttribute("isValidate", "true");
                session.setAttribute("userToken", userToken);
                session.setAttribute("contextPath", req.getContextPath());
            }
        } catch (Exception ex) {
            log.error("Error while perform user login action..");
            log.error(ex.getMessage());
            forwardPage = "error";
        }
        log.info("User login has been done!");
        return forwardPage;
    }

    public void checkObjectToken(ObjectToken ob, String menu) {

        if (ob.getDescription().toLowerCase() != null) {
            if (ob.getObjectUrl().contains("filesAction!toBusinessListPage.do")) {
                loadCountFilesNeedToSend = true;
                countFilesNeedToSend = menu;
            } else if (ob.getObjectUrl().contains("filesAction!toReceived.do")) {
                loadCountFilesNeedToReceive = true;
                countFilesNeedToReceive = menu;
            } else if (ob.getObjectUrl().contains("filesAction!proposeEvaluation.do")) {
                loadCountFilesNeedToPropose = true;
                countFilesNeedToPropose = menu;
            } else if (ob.getObjectUrl().contains("filesAction!assignEvaluation.do")) {
                loadCountFilesNeedToAssign = true;
                countFilesNeedToAssign = menu;
            } else if (ob.getObjectUrl().contains("filesAction!toEvaluatePage.do")) {
                loadCountFilesNeedToEvaluate = true;
                countFilesNeedToEvaluate = menu;
            } else if (ob.getObjectUrl().contains("filesAction!toReviewPage.do")) {
                loadCountFilesNeedToReview = true;
                countFilesNeedToReview = menu;
            } else if (ob.getObjectUrl().contains("filesAction!toApprovePage.do")) {
                loadCountFilesNeedToApprove = true;
                countFilesNeedToApprove = menu;
            }
        }

        if (ob.getChildObjects() != null && ob.getChildObjects().size() > 0) {
            for (int i = 0; i < ob.getChildObjects().size(); i++) {
                String execMenu = menu + "." + i;
                checkObjectToken(ob.getChildObjects().get(i), execMenu);
            }
        }
    }

    public String getWaitingObject() {
        //Long deptId = Long.parseLong(getRequest().getParameter("deptId"));
        return "waitingContent";

    }

    public String changeWorkingDept() {
        HttpServletRequest req = getRequest();

        String forwardPage = "indexSuccess";
        Long deptId = Long.parseLong(req.getParameter("deptId"));
        forwardPage = loadToken(deptId);
        return forwardPage;

    }

    /*
     public String getGeneralCount() {

     FilesDAOHE fdhe = new FilesDAOHE();
     Long userId = (Long) getRequest().getSession().getAttribute("userId");
     Long deptId = Long.parseLong(getRequest().getParameter("deptId"));
     Long businessId = getBusinessId();

     Long filesNeedToSend;
     Long filesNeedToPropose;
     Long filesNeedToAssign;
     Long filesNeedToEvaluate;
     Long filesNeedToReview;
     Long filesNeedToApprove;
     Long filesNeedToReceive;
     Long filesNeedToComparison;
     Long filesNeedToComparisonFail;

     String menu = "";
     UserToken token = getToken(userId, deptId);

     if (token != null) {
     for (int i = 0; i < token.getParentMenu().size(); i++) {
     menu = "" + i;
     ObjectToken obj = token.getParentMenu().get(i);
     checkObjectToken(obj, menu);
     }
     }
     //VB đến
     //VB chưa vào sổ
     lstItem = new ArrayList();
     // Ho so cua doanh nghiep chua gui
     if (loadCountFilesNeedToSend) {
     filesNeedToSend = (Long) (long) fdhe.getCountFileToSend(businessId, null);
     getRequest().setAttribute("filesNeedToSend", filesNeedToSend);
     HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToSend, "share/images/document/new_document.png", "Hồ sơ cần gửi", filesNeedToSend, 1l, "burlywood");
     lstItem.add(item);
     filesNeedToSend = (Long) (long) fdhe.getCountFileToSend(businessId, Constants.FILE_STATUS.FEDBACK_TO_ADD);
     item = new HomeLiveTileForm(countFilesNeedToSend, "share/images/document/reject_document.png", "Hồ sơ bị trả lại", filesNeedToSend, 1l, "crimson");
     lstItem.add(item);
     filesNeedToSend = (Long) (long) fdhe.getCountFileToSend(businessId, Constants.FILE_STATUS.APPROVED);
     item = new HomeLiveTileForm(countFilesNeedToSend, "share/images/document/approve_document.png", "Hồ sơ đã phê duyệt", filesNeedToSend, 1l, "#008000");
     lstItem.add(item);
     }
     //Ho so cho tiep nhan
     if (loadCountFilesNeedToReceive) {
     filesNeedToReceive = (Long) (long) fdhe.getCountFileToPropose(userId, deptId);
     getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
     HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToPropose, "share/images/document/new_document.png", "Hồ sơ gửi đến cơ quan", filesNeedToReceive, 1l, "blueviolet");
     lstItem.add(item);

     filesNeedToComparison = (Long) (long) fdhe.getCountFileToPropose(userId, deptId);
     getRequest().setAttribute("filesNeedToComparison", filesNeedToComparison);
     item = new HomeLiveTileForm(countFilesNeedToPropose, "share/images/document/new_document.png", "Hồ sơ cần đối chiếu", filesNeedToComparison, 1l, "blueviolet");
     lstItem.add(item);

     filesNeedToComparisonFail = (Long) (long) fdhe.getCountFileToPropose(userId, deptId);
     getRequest().setAttribute("filesNeedToComparisonFail", filesNeedToComparisonFail);
     item = new HomeLiveTileForm(countFilesNeedToPropose, "share/images/document/new_document.png", "Hồ sơ đối chiếu sai lệch", filesNeedToComparisonFail, 1l, "#008000");
     lstItem.add(item);
     }
     //Ho so cho de xuat
     if (loadCountFilesNeedToPropose) {
     filesNeedToPropose = (Long) (long) fdhe.getCountFileToPropose(userId, deptId);
     getRequest().setAttribute("filesNeedToPropose", filesNeedToPropose);
     HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToPropose, "share/images/document/new_document.png", "Hồ sơ gửi đến cơ quan", filesNeedToPropose, 1l, "blueviolet");
     lstItem.add(item);
     }
     //Hồ sơ chờ phân công
     if (loadCountFilesNeedToAssign) {
     //            filesNeedToAssign = (Long) (long) fdhe.getCountFileToAssign(userId, deptId);
     //            getRequest().setAttribute("filesNeedToAssign", filesNeedToAssign);
     filesNeedToAssign = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 0l, null));
     getRequest().setAttribute("filesNeedToAssign", filesNeedToAssign);
     HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToAssign, "share/images/document/new_document.png", "Hồ sơ chờ phân công", filesNeedToAssign, 1l, "cadetblue");
     lstItem.add(item);
     }
     //Hồ sơ chờ thẩm định
     if (loadCountFilesNeedToEvaluate) {
     filesNeedToEvaluate = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 1l, null));
     getRequest().setAttribute("filesNeedToEvaluate", filesNeedToEvaluate);
     HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToEvaluate, "share/images/document/evaluate_document.png", "Hồ sơ chờ thẩm định", filesNeedToEvaluate, 1l, "coral");
     lstItem.add(item);
     filesNeedToEvaluate = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 1l, Constants.FILE_STATUS.FEDBACK_TO_EVALUATE));
     item = new HomeLiveTileForm(countFilesNeedToEvaluate, "share/images/document/reject_document.png", "Hồ sơ bị trả thẩm định lại", filesNeedToEvaluate, 1l, "crimson");
     lstItem.add(item);
     filesNeedToEvaluate = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 1l, Constants.FILE_STATUS.FEDBACK_TO_EVALUATE));
     item = new HomeLiveTileForm(countFilesNeedToEvaluate, "share/images/document/reject_document.png", "Hồ sơ bị trả thẩm định lại", filesNeedToEvaluate, 1l, "crimson");
     lstItem.add(item);

     }
     //Hồ sơ chờ xem xét
     if (loadCountFilesNeedToReview) {
     filesNeedToReview = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 2l, null));
     getRequest().setAttribute("filesNeedToReview", filesNeedToReview);
     HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToReview, "share/images/document/review_document.png", "Hồ sơ chờ xem xét", filesNeedToReview, 1l, "darkgray");
     lstItem.add(item);
     filesNeedToReview = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 2l, Constants.FILE_STATUS.FEDBACK_TO_REVIEW));
     item = new HomeLiveTileForm(countFilesNeedToSend, "share/images/document/reject_document.png", "Hồ sơ bị trả xem xét lại", filesNeedToReview, 1l, "crimson");
     lstItem.add(item);
     }

     //Hồ sơ chờ phê duyệt
     if (loadCountFilesNeedToApprove) {
     filesNeedToApprove = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 3l, null));
     getRequest().setAttribute("filesNeedToApprove", filesNeedToApprove);
     HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToApprove, "share/images/document/approve_document.png", "Hồ sơ chờ phê duyệt", filesNeedToApprove, 1l, "#008000");
     lstItem.add(item);
     filesNeedToApprove = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 3l, Constants.FILE_STATUS.APPROVED));
     item = new HomeLiveTileForm(countFilesNeedToApprove, "share/images/document/approved_document.png", "Hồ sơ đã phê duyệt", filesNeedToApprove, 1l, "#008000");
     lstItem.add(item);
     }
     getRequest().setAttribute("lstItem", lstItem);

     return "home";

     }
     */
    public UserToken getToken(Long userId, Long deptId) {
        UserToken ut = null;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("cas_en_US");
            String domainCode = rb.getString("domainCode");
            UsersDAOHE udhe = new UsersDAOHE();
            String userName = udhe.getUserNameById(userId);

            String willParse = new UtilDAO().validate(userName, deptId, domainCode);
            ut = UserToken.parseXMLResponse(willParse);
        } catch (Exception en) {
        }
        return ut;

    }

    public String loadToken(Long deptId) {
        HttpServletRequest req = getRequest();
        //HttpServletResponse res = getResponse();
        HttpSession session = req.getSession();
        String forwardPage = "indexSuccess";

        UserToken userToken = null;

        Long userId = (Long) session.getAttribute("userId");
        UsersDAOHE udhe = new UsersDAOHE();
        String userName = udhe.getUserNameById(userId);

        session.setAttribute("workingDeptId", deptId);

        ResourceBundle rb = ResourceBundle.getBundle("cas_en_US");
        String domainCode = rb.getString("domainCode");

        try {
            String willParse = new UtilDAO().validate(userName, deptId, domainCode);
            UserToken ut = UserToken.parseXMLResponse(willParse);
            session.setAttribute("vsaUserToken", ut);
        } catch (Exception en) {
        }

        try {
            UserToken vsaUserToken = (UserToken) session.getAttribute("vsaUserToken");
            if (vsaUserToken != null) {
                userToken = new UserToken();
                userToken.setUserID(vsaUserToken.getUserID());
                userToken.setFullName(vsaUserToken.getFullName());
                session.setAttribute("isValidate", "true");
                session.setAttribute("userToken", userToken);
                session.setAttribute("contextPath", req.getContextPath());
            }
        } catch (Exception ex) {
            log.error("Error while perform user login action..");
            log.error(ex.getMessage());
            forwardPage = "error";
        }
        log.info("User login has been done!");
        return forwardPage;

    }

    public String doExit()
            throws Exception {
        String forwardPage = "exit";
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        ResourceBundle bundle = ResourceBundle.getBundle("cas");
        request.setAttribute("logoutUrl", (new StringBuilder()).append(bundle.getString("logoutUrl")).append("?service=").append(URLEncoder.encode(bundle.getString("service"), "UTF-8")).toString());
        request.getSession().invalidate();
        response.sendRedirect((String) request.getAttribute("logoutUrl"));
        return forwardPage;
    }

    public String actionLogin()
            throws Exception {
        String forwardPage = "loginSuccess";
        return forwardPage;
    }

    public String actionDefault()
            throws Exception {
        HttpServletResponse res = getResponse();
        res.setHeader("RDWF-Flag", "ActionNotFound");
        return "success";
    }

    public String keepAlive()
            throws Exception {
        return "keepAlive";
    }

    //private static final String LOGIN_SUCCESS_PAGE = "loginSuccess";
    //private static final String EXIT_PAGE = "exit";
    //private static final String INDEX_PAGE = "indexSuccess";
}
