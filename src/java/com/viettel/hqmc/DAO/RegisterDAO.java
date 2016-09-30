/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.Business;
import com.viettel.hqmc.BO.MessageGroup;
import com.viettel.hqmc.BO.MessageGroupPerson;
import com.viettel.hqmc.BO.Register;
import com.viettel.hqmc.DAOHE.MessageEmailDAOHE;
import com.viettel.hqmc.DAOHE.MessageGroupDAOHE;
import com.viettel.hqmc.DAOHE.MessageGroupPersonDAOHE;
import com.viettel.hqmc.DAOHE.MessageSmsDAOHE;
import com.viettel.hqmc.DAOHE.RegisterDAOHE;
import com.viettel.hqmc.FORM.RegisterForm;
import com.viettel.voffice.common.util.PasswordService;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import com.viettel.voffice.database.DAOHibernate.RolesDAOHE;
import com.viettel.vsaadmin.database.BO.Position;
import com.viettel.vsaadmin.database.BO.RoleUser;
import com.viettel.vsaadmin.database.BO.RoleUserPK;
import com.viettel.vsaadmin.database.BO.Roles;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author vtit_binhnt53
 */
public class RegisterDAO extends BaseDAO {

    private String registerCreatePage = "registerCreatePage";
    private String registerSearchPage = "registerSearchPage";
    private RegisterForm searchForm;
    private RegisterForm createForm;
    private RegisterForm approveForm;
    RegisterDAOHE categoryTypeDao = new RegisterDAOHE();
    private List<RegisterForm> lstItemOnGrid;
    private static final Logger log = Logger.getLogger(RegisterDAO.class);

    /*
     * toShowPage
     * show data perpare after show page
     */
    /**
     *
     * @return
     */
    public String prepareCreate() {
        try {
            //todo code here
            CategoryDAOHE cdhe = new CategoryDAOHE();
            List lstProvince = cdhe.findAllCategory("PROVINCE");
            List lstCategory = new ArrayList();
            lstCategory.addAll(lstProvince);
            lstCategory.add(0, new Category(0l, "-- Chọn --"));
            getRequest().setAttribute("lstProvince", lstCategory);

            List lstBusinessType = cdhe.findAllCategory("BUSTYPE");
            List lstCategory1 = new ArrayList();
            lstCategory1.addAll(lstBusinessType);
            lstCategory1.add(0, new Category(0l, "-- Chọn --"));
            getRequest().setAttribute("lstBusinessType", lstCategory1);

            List lstPosId = cdhe.findAllPosition("POSITION");
            List lstCategory2 = new ArrayList();
            lstCategory2.addAll(lstPosId);
            lstCategory2.add(0, new Position(0l, "-- Chọn --"));
            getRequest().setAttribute("lstPosId", lstCategory2);

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return this.registerCreatePage;
    }

    /**
     *
     * @return
     */
    public String registerAccount() {
        try {
            //todo code here
            CategoryDAOHE cdhe = new CategoryDAOHE();
            List lstProvince = cdhe.findAllCategory("PROVINCE");
            List lstCategory = new ArrayList();
            lstCategory.addAll(lstProvince);
            lstCategory.add(0, new Category(0l, "--- Chọn ---"));
            getRequest().setAttribute("lstProvince", lstCategory);

//            List lstBusinessType = cdhe.findAllCategory("BUSTYPE");
//            List lstCategory1 = new ArrayList();
//            lstCategory1.addAll(lstBusinessType);
//            lstCategory1.add(0, new Category(0l, "--- Chọn ---"));
//            getRequest().setAttribute("lstBusinessType", lstCategory1);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return "registerAccountPage";
    }

    /**
     *
     * @return
     */
    public String prepareSearch() {
        try {
            CategoryDAOHE cdhe = new CategoryDAOHE();
            List lstProvince = cdhe.findAllCategory("PROVINCE");
            List lstCategory = new ArrayList();
            lstCategory.addAll(lstProvince);
            lstCategory.add(0, new Category(0l, "--- Chọn ---"));
            getRequest().setAttribute("lstProvince", lstCategory);

//            List lstBusinessType = cdhe.findAllCategory("BUSTYPE");
//            List lstCategory1 = new ArrayList();
//            lstCategory1.addAll(lstBusinessType);
//            lstCategory1.add(0, new Category(0l, "--- Chọn ---"));
//            getRequest().setAttribute("lstBusinessType", lstCategory1);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return this.registerSearchPage;
    }

    /**
     *
     * @return
     */
    public String onSearch() {
        getGridInfo();

        RegisterDAOHE bdhe = new RegisterDAOHE();
        GridResult gr = bdhe.findRegister(searchForm, start, count, sortField);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());

        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onInsert() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        try {
            RegisterDAOHE cthe = new RegisterDAOHE();
            if (cthe.isDuplicate(createForm) == true || cthe.isDuplicateBusiness(createForm) == true || cthe.isDuplicateUsers(createForm) == true) {
                resultMessage.add("3");
                resultMessage.add("Thông tin đăng ký bị trùng. Vui lòng kiểm tra lại Mã số thuế, Tên công ty hoặc địa chỉ Email!");
            } else {
                Long ObjId = createForm.getRegisterId();

                if (ObjId == null) {
                    Register bo = createForm.convertToEntity();
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Đăng ký thành công");
                } else {
                    Register bo = createForm.convertToEntity();
                    getSession().update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật thành công");
                }
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("3");
            Long ObjId = createForm.getRegisterId();
            if (ObjId == null) {
                resultMessage.add("Đăng ký không thành công");
            } else {
                resultMessage.add("Cập nhật không thành công");
            }
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /**
     * Dang ky tk Doanh nghiep
     *
     * @return
     */
    public String onInsertHomePage() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        try {
            RegisterDAOHE cthe = new RegisterDAOHE();
            Long checkPos = checkNewPos();
            if (cthe.isDuplicate(createForm) || cthe.isDuplicateBusiness(createForm) || cthe.isDuplicateUsers(createForm)) {
                resultMessage.add("3");
                resultMessage.add("Thông tin đăng ký tài khoản Doanh nghiệp bị trùng Mã số thuế, hoặc Số đăng ký kinh doanh");
            } else if (cthe.isDuplicateEmail(createForm)) {
                resultMessage.add("3");
                resultMessage.add("Thông tin đăng ký tài khoản Doanh nghiệp bị trùng Email");
            } else if (!checkPos.equals(0l) && checkPos < 0) {
                if (checkPos.equals(-1l)) {
                    resultMessage.add("4");
                    resultMessage.add("Chức vụ mới đã tồn tại trên hệ thống");
                } else if (checkPos.equals(-2l)) {
                    resultMessage.add("4");
                    resultMessage.add("Đăng ký tài khoản Doanh nghiệp không thành công");
                }
            } else {
                if (createForm.getPosId() == -1l) {
                    //New posName
                    createForm.setPosId(checkPos);
                    createForm.setPosName(createForm.getNewPosName().trim());
                }
                Register bo = createForm.convertToEntityHomePage();
                bo.setCreateDate(getSysdate());//u141213 binhnt53
                bo.setModifyDate(getSysdate());//u141213 binhnt53
                getSession().save(bo);
                //Thong SMS EMAIL toi cho quan tri he thong biet - 140830 - binhnt53
                MessageGroupDAOHE mgdaohe = new MessageGroupDAOHE();
                MessageGroup mgbo = mgdaohe.returnMessageGroupById("ADMIN");
                if (mgbo != null) {
                    MessageGroupPersonDAOHE mgpdaohe = new MessageGroupPersonDAOHE();
                    List<MessageGroupPerson> lstMgp = mgpdaohe.findAllByMessageGroupId(mgbo.getMesssageGroupId());
                    if (lstMgp.size() > 0) {
                        String msg = "Doanh nghiệp: " + createForm.getBusinessNameVi() + " - Có mã số thuế: " + createForm.getBusinessTaxCode() + " - Vừa thực hiện đăng ký vào hệ thống.";
                        String msge = "Kính gửi: " + createForm.getBusinessNameVi()
                                + " - Có mã số thuế: " + createForm.getBusinessTaxCode()
                                + "<br//>Quý khách đã thực hiện đăng ký vào hệ thống. Tài khoản của quý khách sẽ được phê duyệt trong 24h làm việc."
                                + "<br//"
                                + "<br//>Trân trọng,"
                                + "<br//>Cục ATTP";
                        for (int i = 0; i < lstMgp.size(); i++) {
                            //sms
                            MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                            msdhe.saveMessageSMSGroup(lstMgp.get(i).getMessageGroupPersonId(), lstMgp.get(i).getPersonPhoneNumber(), msg);
                            //email
                            MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                            msedhe.saveMessageEmailGroup(lstMgp.get(i).getMessageGroupPersonId(), lstMgp.get(i).getPersonEmail(), msge);
                        }
                        //send to user register
                        //sms
                        /*
                         if (bo != null) {
                         MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                         msdhe.saveMessageSMSGroup(bo.getRegisterId(), bo.getUserMobile(), msg);
                         //email
                         MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                         msedhe.saveMessageEmailGroup(bo.getRegisterId(), bo.getManageEmail(), msge);
                         }
                         */
                    }
                }
                resultMessage.add("1");
                resultMessage.add("Đăng ký tài khoản Doanh nghiệp thành công. Tài khoản của quý khách sẽ được phê duyệt trong 24h làm việc. Thông tin tài khoản đăng nhập vào hệ thống sẽ được gửi vào Email đã đăng ký sau khi tài khoản được phê duyệt. Cám ơn!");
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Đăng ký tài khoản Doanh nghiệp không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    public String onResetPasswordHomePage() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        String taxCode = "";
        String emailManager = "";
        try {
            if (createForm != null) {
                taxCode = createForm.getBusinessTaxCode();
                emailManager = createForm.getManageEmail();
                UsersDAOHE udaohe = new UsersDAOHE();
                Users ubo = udaohe.findUserByName(taxCode);
                if (ubo != null) {
                    if (ubo.getEmail().equals(emailManager)) {//tao mk gui mk toi user
                        String subpass = "";//BINHNT53 U141230
                        try {
                            subpass = ubo.getUserName().substring(3, 3);
                        } catch (Exception ex) {
                            LogUtil.addLog(ex);//binhnt sonar a160901
                            subpass = "123";
                        }
                        String passwordNotEncrypt = "Attp@" + subpass;//BINHNT53 U141230
                        String passwordEncrypt = PasswordService.getInstance().encrypt(ubo.getUserName().toLowerCase() + passwordNotEncrypt);//ma hoa mat khau
                        ubo.setPassword(passwordEncrypt);
                        ubo.setPasswordchanged(0L);
                        getSession().save(ubo);
                        //email
                        MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                        String msge = "Kính gửi quí khách - Có tài khoản đăng nhập: " + ubo.getUserName()
                                + "<br//>Tài khoản của quý khách vừa được reset mật khẩu mới là: " + passwordNotEncrypt
                                + "<br//>Quý khách có thể truy cập vào hệ thống theo đường dẫn: <a href=//\"http://congbosanpham.vfa.gov.vn/\">congbosanpham.vfa.gov.vn<//a>"
                                + "<br//"
                                + "<br//>Trân trọng,"
                                + "<br//>Cục ATTP";
                        msedhe.saveMessageEmailGroup(ubo.getUserId(), ubo.getEmail(), msge);
                        resultMessage.add("1");
                        resultMessage.add("Gửi yêu cầu lấy lại mật khẩu thành công. Thông tin tài khoản đăng nhập vào hệ thống sẽ được gửi vào Email đã đăng ký. Cám ơn!");
                    } else {
                        resultMessage.add("3");
                        resultMessage.add("Thông tin người dùng nhập không đúng. Vui lòng kiểm tra lại");
                    }
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Thông tin người dùng nhập không đúng. Vui lòng kiểm tra lại");
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Lấy lại mật khẩu tài khoản không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /**
     * Check vi tri chuc vu moi da ton tai tren he thong hay chua
     *
     * @return
     */
    public Long checkNewPos() {
        try {
            if (createForm.getPosId() != -1) {
                return 0l;
            }
            CategoryDAOHE cdhe = new CategoryDAOHE();
            List lstPosId = cdhe.findAllPositionDnByPosName(createForm.getNewPosName());
            if (lstPosId != null && lstPosId.size() > 0) {
                return -1l;
            }
            Position bo = new Position();
            bo.setPosName(createForm.getNewPosName().trim());
            bo.setPosCode("DOANH_NGHIEP");
            bo.setPosType(2l);
            bo.setStatus(1l);
            getSession().save(bo);
            return bo.getPosId();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return -2l;
        }
    }

    /**
     *
     * @return
     */
    public String onApprove() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        try {
            RegisterDAOHE cthe = new RegisterDAOHE();
            Long ObjId = approveForm.getRegisterId();
            Long posId = approveForm.getPosId();
            if (ObjId != null) {//check co phe duyet hay khong phe duyet
                Register rbo = cthe.findById(ObjId);
                RegisterForm rf = cthe.boToForm(rbo);
                if (rbo != null) {
                    //u141213 binhnt53
                    rbo.setModifiedBy(getUserId());
                    rbo.setModifyDate(getSysdate());
                    if (approveForm.getStatus() == 1) {
                        Business bus = rf.convertToBusiness();                    //obj doanh nghiep
                        Users users = rf.convertToUsers(bus);                    //obj tai khoan
                        RoleUser ru = rf.convertToRoleUsers();
                        String pwNotEncrypt = users.getPassword();
                        String passwordEncrypt = PasswordService.getInstance().encrypt(users.getUserName().toLowerCase() + users.getPassword());                    //ma hoa mat khau
                        users.setPassword(passwordEncrypt);
                        if (cthe.isDuplicateBusiness(rf) == false && cthe.isDuplicateUsers(rf) == false) {                    //kiem tra du da ton tai chua
                            //insert
                            getSession().save(bus);
                            users.setBusinessId(bus.getBusinessId());
                            users.setProfileId(22L);
                            getSession().save(users);
                            getSession().save(ru);
                            rbo.setStatus(1);
                            rbo.setPosId(posId);
                            getSession().update(rbo);
                            RolesDAOHE rdaohe = new RolesDAOHE();
                            Roles rid = rdaohe.getRoleByCode("DN");
                            if (rid != null) {
                                RoleUser bo = new RoleUser();
                                RoleUserPK pk = new RoleUserPK(rid.getRoleId(), users.getUserId());
                                bo.setRoleUserPK(pk);
                                bo.setIsAdmin(Long.valueOf(0L));
                                bo.setIsActive(Long.valueOf(1L));
                                getSession().save(bo);
                            }
                            resultMessage.add("1");
                            resultMessage.add("Phê duyệt tài khoản thành công");
                            /* send sms to Business
                             MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                             String msg = "Kính gửi: Doanh nghiệp " + rf.getBusinessNameVi() + " - Có MST: " + rf.getBusinessTaxCode() + ". Tài khoản của quý khách đã được phê duyệt. Mật khẩu: " + pwNotEncrypt;
                             msdhe.saveMessageSMSGroup(rbo.getRegisterId(), rbo.getUserMobile(), msg);
                             */
                            //email
                            MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                            String msge = "Kính gửi: Doanh nghiệp " + rf.getBusinessNameVi()
                                    + " - Có mã số thuế: " + rf.getBusinessTaxCode()
                                    + "<br//>Tài khoản của quý khách đã được kích hoạt. "
                                    + "<br//>Tài khoản đăng nhập: " + rf.getBusinessTaxCode() + " - Mật khẩu mặc định: " + pwNotEncrypt
                                    + "<br//>Quý khách có thể truy cập vào hệ thống theo đường dẫn: <a href=//\"http://congbosanpham.vfa.gov.vn/\">congbosanpham.vfa.gov.vn<//a> và xem tài liệu hướng dẫn sử dụng phần mềm."
                                    + "<br//"
                                    + "<br//>Trân trọng,"
                                    + "<br//>Cục ATTP";
                            msedhe.saveMessageEmailGroup(rbo.getRegisterId(), rbo.getManageEmail(), msge);
                        } else {
                            resultMessage.add("3");
                            resultMessage.add("Phê duyệt tài khoản thất bại");
                        }
                    } else {//neu tu choi phe duyet tai khoan update lai thong tin

                        rbo.setStatus(2);
                        rbo.setReason(approveForm.getReason());
                        getSession().update(rbo);
                        resultMessage.add("1");
                        resultMessage.add("Từ chối tài khoản thành công");

                        MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                        String msg = "Kính gửi: Doanh nghiệp " + rf.getBusinessNameVi() + " - Có mã số thuế: " + rf.getBusinessTaxCode() + ". Tài khoản của quý khách đã bị từ chối.";
                        msdhe.saveMessageSMSGroup(rbo.getRegisterId(), rbo.getUserMobile(), msg);
                        //email
                        MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                        String msge = "Kính gửi: Doanh nghiệp " + rf.getBusinessNameVi()
                                + " - Có mã số thuế: " + rf.getBusinessTaxCode()
                                + "<br//>Tài khoản của quý khách đã bị từ chối. Lý do:" + rbo.getReason()
                                + "<br//"
                                + "<br//>Trân trọng,"
                                + "<br//>Cục ATTP";
                        msedhe.saveMessageEmailGroup(rbo.getRegisterId(), rbo.getManageEmail(), msge);
                    }
                }
            }
        } catch (Exception ex) {
//            log.error(ex.getMessage());
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Phê duyệt tài khoản thất bại");
        }

        jsonDataGrid.setItems(resultMessage);

        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }
//    public String onDelete() throws Exception {
//        List resultMessage = new ArrayList();
//        try {
//            RegisterDAOHE cthe = new RegisterDAOHE();
//            for (int i = 0; i < lstItemOnGrid.size(); i++) {
//                RegisterForm form = lstItemOnGrid.get(i);
//                if (form != null && form.getRegisterId()!= null && form.getRegisterId() != 0D) {
//                    Register bo = cthe.getById("registerId", form.getRegisterId());
//                    if (bo != null) {
//                        getSession().update(bo);
//                    }
//                }
//            }
//            resultMessage.add("1");
//            resultMessage.add("Xóa danh mục thành công");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            resultMessage.add("3");
//            resultMessage.add("Xóa danh mục không thành công");
//        }
//
//        jsonDataGrid.setItems(resultMessage);
//        return GRID_DATA;
//    }

    /**
     *
     * @return
     */
    public RegisterForm getSearchForm() {
        return searchForm;
    }

    /**
     *
     * @param searchForm
     */
    public void setSearchForm(RegisterForm searchForm) {
        this.searchForm = searchForm;
    }

    /**
     *
     * @return
     */
    public RegisterForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(RegisterForm createForm) {
        this.createForm = createForm;
    }

    public List<RegisterForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<RegisterForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

    public RegisterDAOHE getCategoryTypeDao() {
        return categoryTypeDao;
    }

    public void setCategoryTypeDao(RegisterDAOHE categoryTypeDao) {
        this.categoryTypeDao = categoryTypeDao;
    }

    public String getRegisterCreatePage() {
        return registerCreatePage;
    }

    public void setRegisterCreatePage(String registerCreatePage) {
        this.registerCreatePage = registerCreatePage;
    }

    public String getRegisterSearchPage() {
        return registerSearchPage;
    }

    public void setRegisterSearchPage(String registerSearchPage) {
        this.registerSearchPage = registerSearchPage;
    }

    public RegisterForm getApproveForm() {
        return approveForm;
    }

    public void setApproveForm(RegisterForm approveForm) {
        this.approveForm = approveForm;
    }
}
