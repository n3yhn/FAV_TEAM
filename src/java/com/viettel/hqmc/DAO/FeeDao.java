/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.kpay.security.HashFunction;
import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.Business;
import com.viettel.hqmc.BO.Fee;
import com.viettel.hqmc.BO.FeePaymentInfo;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.hqmc.DAOHE.BusinessDAOHE;
import com.viettel.hqmc.DAOHE.FeeDAOHE;
import com.viettel.hqmc.DAOHE.FeePaymentInfoDAOHE;
import com.viettel.hqmc.DAOHE.FilesDAOHE;
import com.viettel.hqmc.DAOHE.ProcedureDAOHE;
import com.viettel.hqmc.FORM.FeeForm;
import static com.viettel.voffice.common.util.CommonUtils.UpcaseFirst;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.EventLogDAOHE;
import com.viettel.vsaadmin.database.BO.Department;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.DepartmentDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
//import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;

/**
 *
 * @author binhnt53
 */
public class FeeDao extends BaseDAO {

    private FeeForm createForm;
    private FeeForm searchForm;
    private final String forwardPage = "feePage";
    private FeeDAOHE FeeDAOHE;
    private List<FeeForm> lstItemOnGrid;
    private List lstCategory;
    private List lstFileType;
    private FeePaymentInfo createFeeForm;
    private FeePaymentInfo createFeeForm1;

//==============================================================================
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FeeDao.class);

    public String prepare() {
        try {
            if (createForm != null) {
                searchForm = new FeeForm();
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        lstCategory = cdhe.getAllProcedure();
        lstFileType = cdhe.getAllProcedure();
        lstFileType.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        //lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        String lstcat = convertToJSONData(lstCategory, "procedureId", "name");
        getRequest().setAttribute("lstCategory", lstcat);
        getRequest().setAttribute("lstFileType", lstFileType);
        return this.forwardPage;
    }

    /**
     *
     * @return
     */
    public String onSearch() {
        GridResult gridResult = new GridResult(0, new ArrayList());
        try {
            getGridInfo();
            if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
                try {
                    String startServerStr = getRequest().getSession().getAttribute("approvePage.startServer") == null ? "" : getRequest().getSession().getAttribute("approvePage.startServer").toString();
                    String countServerStr = getRequest().getSession().getAttribute("approvePage.countServer") == null ? "" : getRequest().getSession().getAttribute("approvePage.countServer").toString();

                    if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                        count = Integer.parseInt(countServerStr);
                        start = Integer.parseInt(startServerStr);
                    }
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
                }
            }
            FeeDAOHE = new FeeDAOHE();
            gridResult = FeeDAOHE.findFee(searchForm, start, count, sortField);

            getRequest().getSession().setAttribute("approvePage.startServer", start);
            getRequest().getSession().setAttribute("approvePage.countServer", count);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        jsonDataGrid.setItems(gridResult.getLstResult());
        jsonDataGrid.setTotalRows(gridResult.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onInsertFee() {
        FeeDAOHE rdhe = new FeeDAOHE();
        boolean bReturn = rdhe.onCreateFee(createForm, getUserId());
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Admin tạo mới ", "phí có id=" + createForm.getFeeId(), getRequest());
        return GRID_DATA;
    }

    /**
     *
     * @param array
     * @param att1
     * @param att2
     * @return
     */
    public String convertToJSONData(List array, String att1, String att2) {
        JSONArray jsonArray = new JSONArray();
        String id = att1;
        String searchAttr1 = att2;
        String searchAttr2 = att2;
        try {
            for (Object o : array) {
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
                jsonObj.put("search1", search1.replace("'", ""));
                getMethodName = "get" + UpcaseFirst(searchAttr2);
                getMethod = cls.getMethod(getMethodName);

                String search2 = "";
                if (getMethod.invoke(o) != null) {
                    search2 = getMethod.invoke(o).toString();
                }
                jsonObj.put("search2", search2.replace("'", ""));

                jsonArray.add(jsonObj);

            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            Logger.getLogger(FilesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonArray.toString();
    }

    /**
     *
     * @return @throws Exception
     */
    public String onDelete() throws Exception {
        List resultMessage = new ArrayList();
        try {
            FeeDAOHE cthe = new FeeDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                FeeForm form = lstItemOnGrid.get(i);
                if (form != null && form.getFeeId() != null && form.getFeeId() != 0) {
                    Fee bo = cthe.getById("feeId", form.getFeeId());
                    if (bo != null) {
                        bo.setIsActive(Long.parseLong("0"));
                        getSession().update(bo);
                    }
//                    FeeProcedure bo1 = (FeeProcedure) cthe.findById(FeeProcedure.class, "feeId", form.getFeeId());
//                    if (bo1 != null) {
//                        bo1.setIsActive(Long.parseLong("0"));
//                        getSession().update(bo1);
//                    }
                    // 11/11/2014 vietd
                    //String a = " update FeeProcedure fp set fp.isActive = 0 where fp.feeId=" + bo.getFeeId();
                    String a = " update FeeProcedure fp set fp.isActive = 0 where fp.feeId= ? ";

                    Query query = getSession().createQuery(a);
                    query.setParameter(0, bo.getFeeId());
                    query.executeUpdate();
                }
            }
            resultMessage.add("1");
            resultMessage.add("Xóa biểu phí thành công");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Xóa biểu phí không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     * update thong tin thanh toan
     *
     * @return
     */
    public String onInsertFeePaymentInfo() {
        FeeDao fdao = new FeeDao();
        FeeDAOHE rdhe = new FeeDAOHE();
        String paymentInfo = getRequest().getParameter("paymentInfo");
        String filescode = (getRequest().getParameter("filescode") == null ? "0" : getRequest().getParameter("filescode"));
        String hash = getRequest().getParameter("hash");

        // hieptq update 030615 hash lai noi dung so voi sercure hash keypay gui lai
        String[] parts = hash.split(";");
        ConcurrentHashMap<String, String> fields = new ConcurrentHashMap<String, String>();
        fields.put("command", parts[0]);
        fields.put("merchant_trans_id", parts[1]);
        fields.put("merchant_code", parts[2]);
        fields.put("response_code", parts[3]);
        fields.put("trans_id", parts[4]);
        fields.put("good_code", parts[5]);
        fields.put("net_cost", parts[6]);
        fields.put("ship_fee", parts[7]);
        fields.put("tax", parts[8]);
        fields.put("service_code", parts[9]);
        fields.put("currency_code", parts[10]);
        if (parts[11] != null && parts[11].trim().length() > 0) {
            fields.put("bank_code", parts[11]);
        }
        String secure_hash;
        int countObj = 0;
        String[] lstfeeInfoIdNew = null;
        HashFunction hf = new HashFunction();
        ResourceBundle rb = ResourceBundle.getBundle("config");
//        String url_redirect = rb.getString("online_keypay");
        String transKey = rb.getString("transkey");
        secure_hash = hf.hashAllFields(fields, transKey);
        boolean check = true;
        String feeInfoIdNew = getRequest().getParameter("feeInfoId");
        // hieptq update check trang thai thanh toan truoc khi chuyen luong
        if (fdao.checkPaymentStatus(feeInfoIdNew) == true) {
            if (secure_hash.equals(parts[17])) {
                Long userId = getUserId();
                lstfeeInfoIdNew = feeInfoIdNew.split(",");
                countObj = lstfeeInfoIdNew.length;
                Long feeInfoId;
                for (int i = 0; i < countObj; i++) {
                    feeInfoId = Long.parseLong(lstfeeInfoIdNew[i]);
                    boolean bReturn = rdhe.insertFeePaymentInfo(feeInfoId, userId, paymentInfo, filescode);
                    // gui den cuc attp -> doi trang thai
                    FeePaymentInfoDAOHE fdhe1 = new FeePaymentInfoDAOHE();
                    FilesDAOHE fdhe = new FilesDAOHE();
                    FeePaymentInfo finew = fdhe1.findById(feeInfoId);
                    Fee f = (Fee) rdhe.findById(Fee.class, "feeId", finew.getFeeId());
                    FeePaymentInfo fi = (FeePaymentInfo) fdhe1.findById(FeePaymentInfo.class, "fileId", finew.getFileId());
                    Files file = fdhe.findById(fi.getFileId());
                    if (file.getStatus().equals(Constants.FILE_STATUS.NEW_CREATE)
                            || file.getStatus().equals(Constants.FILE_STATUS.NEW)) {//binhnt53 update 150215
                        if (f.getFeeType().equals(Constants.FEE_TYPE.PHI_THAM_DINH)) {//binhnt53 update 150215
                            DepartmentDAOHE dphe = new DepartmentDAOHE();
                            Department dep = dphe.findByDeptCode("ATTP");
                            BusinessDAOHE bus = new BusinessDAOHE();
                            Business bus1 = bus.findById(file.getDeptId());//4
                            bReturn = fdhe.assignFileToDept(fi.getFileId(), dep.getDeptId(), file.getUserCreateId(), file.getUserCreateName(), bus1.getBusinessId(), bus1.getBusinessName());
                        }
                    }
                    check = bReturn;
                    if (check == false) {
                        break;
                    }
                }
            } else {
                check = false;
            }
        } else {
            check = false;
        }
        // hieptq update 021015
        try {
            for (int i = 0; i < countObj; i++) {
                this.onSavePaymentOnlineIPN(lstfeeInfoIdNew[i]);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            check = false;
        }
        List resultMessage = new ArrayList();
        if (check) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onSavePayment() {
        FeeDAOHE rdhe = new FeeDAOHE();
        Long paymentInfoId = Long.parseLong(getRequest().getParameter("paymentInfoId"));
        Long paymentType = Long.parseLong(getRequest().getParameter("paymentType"));
        String billPath = getRequest().getParameter("billPath");
        String paymentPerson = createFeeForm.getPaymentPerson();
        Date paymentDate = createFeeForm.getPaymentDate();
        //Long userId = getUserId();
        boolean bReturn = rdhe.savePaymentInfo(paymentInfoId, paymentType, billPath, paymentPerson, paymentDate);

        // gui den cuc ATTP
        FeePaymentInfoDAOHE fdhe1 = new FeePaymentInfoDAOHE();
        FilesDAOHE fdhe = new FilesDAOHE();
        FeePaymentInfo finew = fdhe1.findById(paymentInfoId);
        Fee f = (Fee) rdhe.findById(Fee.class, "feeId", finew.getFeeId());
        FeePaymentInfo fi = (FeePaymentInfo) fdhe1.findById(FeePaymentInfo.class, "fileId", finew.getFileId());
        Files file = fdhe.findById(fi.getFileId());
        if (file.getStatus().equals(Constants.FILE_STATUS.NEW_CREATE)) {//binhnt53 update 150215
            if (f.getFeeType().equals(Constants.FEE_TYPE.PHI_THAM_DINH)) {//binhnt53 update 150215
                DepartmentDAOHE dphe = new DepartmentDAOHE();
                Department dep = dphe.findByDeptCode("ATTP");
                BusinessDAOHE bus = new BusinessDAOHE();
                Business bus1 = bus.findById(file.getDeptId());//1
                bReturn = fdhe.assignFileToDept(fi.getFileId(), dep.getDeptId(), file.getUserCreateId(), file.getUserCreateName(), bus1.getBusinessId(), bus1.getBusinessName());
            }
        }
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

//hieptq update 011214 xac nhan phi tien mat
    public String onSavePaymentCash() {
        boolean bAcceptPay;//binhnt53 update 150215
        boolean bAcceptSend = false;//binhnt53 update 150215
        FeeDAOHE rdhe = new FeeDAOHE();
        Long paymentInfoId = Long.parseLong(getRequest().getParameter("paymentInfoId"));
        String paymentCode = createFeeForm.getPaymentCode();
        String billCode = createFeeForm.getBillCode();
        String paymentPerson = createFeeForm.getPaymentPerson();
        Long userId = getUserId();
        UsersDAOHE udhe = new UsersDAOHE();
        Users user = udhe.findById(userId);
        String fullName = user.getFullName();
        //gui den cuc ATTP        
        FeePaymentInfoDAOHE fdhe1 = new FeePaymentInfoDAOHE();
        FilesDAOHE fdhe = new FilesDAOHE();
        FeePaymentInfo finew = fdhe1.findById(paymentInfoId);
        Fee f = (Fee) rdhe.findById(Fee.class, "feeId", finew.getFeeId());
        FeePaymentInfo fi = (FeePaymentInfo) fdhe1.findById(FeePaymentInfo.class, "fileId", finew.getFileId());
        Files file = fdhe.findById(fi.getFileId());
        if (file.getStatus().equals(Constants.FILE_STATUS.NEW_CREATE)) {//binhnt53 update 150215
            if (f.getFeeType().equals(Constants.FEE_TYPE.PHI_THAM_DINH)) {//binhnt53 update 150215
                DepartmentDAOHE dphe = new DepartmentDAOHE();
                Department dep = dphe.findByDeptCode("ATTP");
                BusinessDAOHE bus = new BusinessDAOHE();
                Business bus1 = bus.findById(file.getDeptId());//2
                bAcceptSend = fdhe.assignFileToDept(fi.getFileId(), dep.getDeptId(), file.getUserCreateId(), file.getUserCreateName(), bus1.getBusinessId(), bus1.getBusinessName());
            }
        }
        //binhnt53 update 150215
        bAcceptPay = rdhe.savePaymentInfoCash(paymentInfoId, fullName, paymentCode, billCode, paymentPerson, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        List resultMessage = new ArrayList();
        if (bAcceptPay && bAcceptSend) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     * xác nhận phí kế toán
     *
     * @return
     */
    public String onSavePaymentFinal() {
        FeeDAOHE rdhe = new FeeDAOHE();
        Long paymentInfoId = Long.parseLong(getRequest().getParameter("paymentInfoId"));
        Long userId = getUserId();
        UsersDAOHE udhe = new UsersDAOHE();
        Users user = udhe.findById(userId);
        String fullName = user.getFullName();
        boolean bReturn = rdhe.savePaymentInfoFinal(paymentInfoId, fullName, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onSavePaymentNew() {
        FeeDAOHE rdhe = new FeeDAOHE();
        Long paymentInfoId = Long.parseLong(getRequest().getParameter("paymentInfoId"));
        Long paymentType = Long.parseLong(getRequest().getParameter("paymentType"));
        String billPath = getRequest().getParameter("billPath");
        String paymentPerson = createFeeForm1.getPaymentPerson();
        Date paymentDate = createFeeForm1.getPaymentDate();
        String paymentCode = createFeeForm1.getPaymentCode();
        String billCode = createFeeForm1.getBillCode();
        //Long userId = getUserId();
        boolean bReturn = rdhe.savePaymentInfoNew(paymentInfoId, paymentType, billPath, paymentPerson, paymentDate, paymentCode, billCode);

        // gui den cuc ATTP
        FeePaymentInfoDAOHE fdhe1 = new FeePaymentInfoDAOHE();
        FilesDAOHE fdhe = new FilesDAOHE();
        FeePaymentInfo finew = fdhe1.findById(paymentInfoId);
        Fee f = (Fee) rdhe.findById(Fee.class, "feeId", finew.getFeeId());
        FeePaymentInfo fi = (FeePaymentInfo) fdhe1.findById(FeePaymentInfo.class, "fileId", finew.getFileId());
        Files file = fdhe.findById(fi.getFileId());
        if (file.getStatus().equals(Constants.FILE_STATUS.NEW_CREATE) || file.getStatus().equals(Constants.FILE_STATUS.NEW)) {//141217u binhnt53 update khi tu choi ho so, dn nop lai thuc hien viec tao process moi
            if (f.getFeeType().equals(Constants.FEE_TYPE.PHI_THAM_DINH)) {//binhnt53 update 150215
                DepartmentDAOHE dphe = new DepartmentDAOHE();
                Department dep = dphe.findByDeptCode("ATTP");
                BusinessDAOHE bus = new BusinessDAOHE();
                Business bus1 = bus.findById(file.getDeptId());//3
                bReturn = fdhe.assignFileToDept(fi.getFileId(), dep.getDeptId(), file.getUserCreateId(), file.getUserCreateName(), bus1.getBusinessId(), bus1.getBusinessName());
            }
        }

        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String onDeny() {
        FeeDAOHE rdhe = new FeeDAOHE();
        Base64 decoder = new Base64();
        Long paymentInfoId = Long.parseLong(getRequest().getParameter("paymentInfoId"));
        String commentReject = null;
        try {
            commentReject = new String(decoder.decode(getRequest().getParameter("commentReject").replace("_", "+").getBytes()), "UTF-8");

        } catch (UnsupportedEncodingException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            Logger.getLogger(FeeDao.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        boolean bReturn = rdhe.denyFee(paymentInfoId, commentReject);
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     * xác nhận phí online kế toán
     *
     * @return
     */
    public String onSavePaymentOnline() {
        FeeDAOHE rdhe = new FeeDAOHE();
        Long paymentInfoId = Long.parseLong(getRequest().getParameter("paymentInfoId"));
        Long userId = getUserId();
        UsersDAOHE udhe = new UsersDAOHE();
        Users user = udhe.findById(userId);
        String fullName = user.getFullName();
        boolean bReturn = rdhe.savePaymentInfoOnline(paymentInfoId, fullName, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());

        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    //hieptq update 210515
    public String onResetFeeTl() {
        FeeDAOHE rdhe = new FeeDAOHE();
        String fileCode = getRequest().getParameter("fileCode");
        boolean bReturn = rdhe.onResetFeeTl(fileCode);
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("0");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    //hieptq update 090615
    public String onChangeFileType() {
        FeeDAOHE rdhe = new FeeDAOHE();
        String fileCode = getRequest().getParameter("fileCode");
        String fileType = getRequest().getParameter("fileType");
        boolean bReturn = rdhe.onChangeFileType(fileCode, fileType);
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công !");
            if (!Constants.ROLES.QT_VOFFICE.equals(getUserLogin())) {
                EventLogDAOHE edhe = new EventLogDAOHE();
                edhe.insertEventLog("Chuyển loại hồ sơ", getUserLogin() + " mã hồ sơ=" + fileCode, getRequest());
            }
        } else {
            resultMessage.add("0");
            resultMessage.add("Lưu dữ liệu không thành công, hồ sơ đang ở trạng thái không được phép sửa hoặc chuyển sang loại không đúng !");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    //hieptq update 170915 IPN-KEYPAY
    public String onInsertFeePaymentInfoIpn(String paymentInfo, String filescode, String hash, String feeInfoIdStr) {
        try {
            FeeDAOHE rdhe = new FeeDAOHE();
            // hieptq update 030615 hash lai noi dung so voi sercure hash keypay gui lai
            String[] parts = hash.split(";");
            ConcurrentHashMap<String, String> fields = new ConcurrentHashMap<String, String>();
            fields.put("command", parts[0]);
            fields.put("merchant_trans_id", parts[1]);
            fields.put("merchant_code", parts[2]);
            fields.put("response_code", parts[3]);
            fields.put("trans_id", parts[4]);
            fields.put("good_code", parts[5]);
            fields.put("net_cost", parts[6]);
            fields.put("ship_fee", parts[7]);
            fields.put("tax", parts[8]);
            fields.put("service_code", parts[9]);
            fields.put("currency_code", parts[10]);
            if (parts[11] != null && parts[11].trim().length() > 0) {
                fields.put("bank_code", parts[11]);
            }

            HashFunction hf = new HashFunction();
            ResourceBundle rb = ResourceBundle.getBundle("config");
//            String url_redirect = rb.getString("online_keypay");
            String transKey = rb.getString("transkey");
            String secure_hash = hf.hashAllFields(fields, transKey);
            boolean check = true;
            if (secure_hash.equals(parts[17])) {
                Long userId = getUserId();
                String[] lstfeeInfoId = feeInfoIdStr.split(",");
                int countObj = lstfeeInfoId.length;
                Long feeInfoId;
                for (int i = 0; i < countObj; i++) {
                    feeInfoId = Long.parseLong(lstfeeInfoId[i]);
                    boolean bReturn = rdhe.insertFeePaymentInfo(feeInfoId, userId, paymentInfo, filescode);
                    // gui den cuc attp -> doi trang thai
                    FeePaymentInfoDAOHE fdhe1 = new FeePaymentInfoDAOHE();
                    FilesDAOHE fdhe = new FilesDAOHE();
                    FeePaymentInfo finew = fdhe1.findById(feeInfoId);
                    Fee f = (Fee) rdhe.findById(Fee.class, "feeId", finew.getFeeId());
                    FeePaymentInfo fi = (FeePaymentInfo) fdhe1.findById(FeePaymentInfo.class, "fileId", finew.getFileId());
                    Files file = fdhe.findById(fi.getFileId());
                    if (file.getStatus().equals(Constants.FILE_STATUS.NEW_CREATE)
                            || file.getStatus().equals(Constants.FILE_STATUS.NEW)) {//binhnt53 update 150215
                        if (f.getFeeType().equals(Constants.FEE_TYPE.PHI_THAM_DINH)) {//binhnt53 update 150215
                            DepartmentDAOHE dphe = new DepartmentDAOHE();
                            Department dep = dphe.findByDeptCode("ATTP");
                            BusinessDAOHE bus = new BusinessDAOHE();
                            Business bus1 = bus.findById(file.getDeptId());//4
                            bReturn = fdhe.assignFileToDept(fi.getFileId(), dep.getDeptId(), file.getUserCreateId(), file.getUserCreateName(), bus1.getBusinessId(), bus1.getBusinessName());
                        }
                    }
                    check = bReturn;
                    if (check == false) {
                        break;
                    }
                }
            } else {
                EventLogDAOHE edhe = new EventLogDAOHE();
                edhe.insertEventLog("KEYPAY", "ERR null data", getRequest());
                check = false;
            }

            if (check) {
                EventLogDAOHE edhe = new EventLogDAOHE();
                edhe.insertEventLog("Cập nhật thanh toán thành công qua IPN ", "phí có id=" + feeInfoIdStr, getRequest());
            } else {
                EventLogDAOHE edhe = new EventLogDAOHE();
                edhe.insertEventLog("Cập nhật thanh toán không thành công qua IPN ", "phí có id=" + feeInfoIdStr, getRequest());
            }
        } catch (Exception ex) {
            EventLogDAOHE edhe = new EventLogDAOHE();
            edhe.insertEventLog("KEYPAY ERROR: ", "ERROR:" + ex.getMessage(), getRequest());
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return GRID_DATA;
    }

    // hieptq update check KeyPay 170915
    public String onSavePaymentOnlineIPN(String feePaymentInfoId) {
        try {

            FeeDAOHE rdhe = new FeeDAOHE();
            Long paymentInfoId = Long.parseLong(feePaymentInfoId);
            FilesDAOHE fdhe = new FilesDAOHE();
            FeePaymentInfoDAOHE fpidhe = new FeePaymentInfoDAOHE();
            FeePaymentInfo fpi = fpidhe.findById(paymentInfoId);
            Files files = fdhe.findById(fpi.getFileId());
            String fullName = Constants.KEYPAY.KEYPAY;
            boolean bReturn = rdhe.savePaymentInfoOnline(paymentInfoId, fullName, files.getAgencyId(), files.getAgencyName(), null, null);
            if (bReturn) {
                EventLogDAOHE edhe = new EventLogDAOHE();
                edhe.insertEventLog("Xác nhận thanh toán tự động thành công qua IPN ", "phí có id=" + paymentInfoId, getRequest());
            } else {
                EventLogDAOHE edhe = new EventLogDAOHE();
                edhe.insertEventLog("Xác nhận thanh toán tự động thành công qua IPN ", "phí có id=" + paymentInfoId, getRequest());
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            EventLogDAOHE edhe = new EventLogDAOHE();
            edhe.insertEventLog("KEYPAY ERROR 1: ", "ERROR:" + ex.getMessage(), getRequest());
        }
        return GRID_DATA;
    }

    //hieptq update 210915 check trung thanh toan keypay
    public boolean checkPaymentStatus(String feeInfoId) {
        boolean result = true;
        try {
            String[] lstfeeInfoId = feeInfoId.split(",");
            int countObj = lstfeeInfoId.length;
            Long feeInfoIdCheck;
            FeePaymentInfoDAOHE fpidhe = new FeePaymentInfoDAOHE();
            for (int i = 0; i < countObj; i++) {
                feeInfoIdCheck = Long.parseLong(lstfeeInfoId[i]);
                FeePaymentInfo fpif = fpidhe.findById(feeInfoIdCheck);
                if (fpif.getStatus() == 1l) {
                    result = false;
                    break;
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return result;
    }

    //==============================================================================
    public FeeForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(FeeForm createForm) {
        this.createForm = createForm;
    }

    public FeeForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(FeeForm searchForm) {
        this.searchForm = searchForm;
    }

    public List<FeeForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<FeeForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

    public FeePaymentInfo getCreateFeeForm() {
        return createFeeForm;
    }

    public void setCreateFeeForm(FeePaymentInfo createFeeForm) {
        this.createFeeForm = createFeeForm;
    }

    public FeePaymentInfo getCreateFeeForm1() {
        return createFeeForm1;
    }

    public void setCreateFeeForm1(FeePaymentInfo createFeeForm1) {
        this.createFeeForm1 = createFeeForm1;
    }

}
