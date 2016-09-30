/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.google.gson.JsonSyntaxException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.common.util.ResourceBundleUtil;
import com.viettel.common.util.UploadFile;
import com.viettel.hqmc.BO.Announcement;
import com.viettel.hqmc.BO.AnnouncementReceiptPaper;
import com.viettel.hqmc.BO.Business;
import com.viettel.hqmc.BO.CaUser;
import com.viettel.hqmc.BO.ConfirmAnnouncementPaper;
import com.viettel.hqmc.BO.ConfirmImportSatistPaper;
import com.viettel.hqmc.BO.CountNo;
import com.viettel.hqmc.BO.EvaluationRecords;
import com.viettel.hqmc.BO.Fee;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.BO.FilesNoClob;
import com.viettel.hqmc.BO.MainlyTarget;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.hqmc.BO.ProcedureDepartment;
import com.viettel.hqmc.BO.ProductTarget;
import com.viettel.hqmc.BO.QualityControlPlan;
import com.viettel.hqmc.BO.Repositories;
import com.viettel.hqmc.BO.RequestComment;
import com.viettel.hqmc.BO.TechnicalStandard;
import com.viettel.hqmc.BO.UserAttachs;
import com.viettel.hqmc.DAOHE.AnnouncementReceiptPaperDAOHE;
import com.viettel.hqmc.DAOHE.BusinessDAOHE;
import com.viettel.hqmc.DAOHE.CaUserDAOHE;
import com.viettel.hqmc.DAOHE.ConfirmAnnouncementPaperDAOHE;
import com.viettel.hqmc.DAOHE.ConfirmImportSatistPaperDAOHE;
import com.viettel.hqmc.DAOHE.CountNoDAOHE;
import com.viettel.hqmc.DAOHE.EvaluationRecordsDAOHE;
import com.viettel.hqmc.DAOHE.FeeDAOHE;
import com.viettel.hqmc.DAOHE.FeePaymentInfoDAOHE;
import com.viettel.hqmc.DAOHE.FilesDAOHE;
import com.viettel.hqmc.DAOHE.FilesNoClobDAOHE;
import com.viettel.hqmc.DAOHE.PaymentHistoryDAOHE;
import com.viettel.hqmc.DAOHE.ProcedureDAOHE;
import com.viettel.hqmc.DAOHE.ProcedureDepartmentDAOHE;
import com.viettel.hqmc.DAOHE.RepositoryDAOHE;
import com.viettel.hqmc.DAOHE.RequestCommentDAOHE;
import com.viettel.hqmc.DAOHE.TechnicalStandardDAOHE;
import com.viettel.hqmc.DAOHE.UserAttachsDAOHE;
import com.viettel.hqmc.DAOHE.VLookupHomepageDAOHE;
import com.viettel.hqmc.DAOHE.VReportLDAOHE;
import com.viettel.hqmc.DAOHE.VReportLOSDAOHE;
import com.viettel.hqmc.DAOHE.VReportStaffProcessDAOHE;
import com.viettel.hqmc.FORM.AnnouncementForm;
import com.viettel.hqmc.FORM.AnnouncementReceiptPaperForm;
import com.viettel.hqmc.FORM.ConfirmImportSatistPaperForm;
import com.viettel.hqmc.FORM.DetailProductForm;
import com.viettel.hqmc.FORM.FeeForm;
import com.viettel.hqmc.FORM.FeePaymentFileForm;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.hqmc.FORM.LoginCAForm;
import com.viettel.hqmc.FORM.ReIssueFormForm;
import com.viettel.hqmc.FORM.RepositoriesForm;
import com.viettel.signature.pdf.PDFServerClientSignature;
import com.viettel.signature.pdf.SearchTextLocations;
import com.viettel.signature.plugin.SignPdfFile;
import com.viettel.signature.utils.CertUtils;
import com.viettel.voffice.client.form.ProcessCommentForm;
import com.viettel.voffice.client.form.ProcessForm;
import com.viettel.voffice.common.util.CommonUtils;
import static com.viettel.voffice.common.util.CommonUtils.UpcaseFirst;
import com.viettel.voffice.common.util.DateTimeUtils;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.BO.Process;
import com.viettel.voffice.database.BO.ProcessComment;
import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import static com.viettel.voffice.database.DAO.UploadIframeDAO.getSafeFileName;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import com.viettel.voffice.database.DAOHibernate.EventLogDAOHE;
import com.viettel.voffice.database.DAOHibernate.ProcessCommentDAOHE;
import com.viettel.voffice.database.DAOHibernate.ProcessDAOHE;
import com.viettel.voffice.database.DAOHibernate.VoAttachsDAOHE;
import com.viettel.vsaadmin.database.BO.Department;
import com.viettel.vsaadmin.database.BO.Roles;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAO.UsersDAO;
import com.viettel.vsaadmin.database.DAOHibernate.DepartmentDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import com.viettel.ws.FilesWS;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import static org.apache.commons.codec.binary.StringUtils.newStringUtf8;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.ocsp.BasicOCSPResp;
import org.bouncycastle.ocsp.CertificateID;
import org.bouncycastle.ocsp.CertificateStatus;
import org.bouncycastle.ocsp.OCSPReq;
import org.bouncycastle.ocsp.OCSPReqGenerator;
import org.bouncycastle.ocsp.OCSPResp;
import org.bouncycastle.ocsp.OCSPRespStatus;
import org.bouncycastle.ocsp.SingleResp;
import org.bouncycastle.x509.extension.X509ExtensionUtil;
import org.hibernate.Query;
import org.w3c.dom.Document;
import sun.security.provider.certpath.OCSP;

/**
 *
 * @author gpcp_binhnt53
 */
public class FilesDAO extends BaseDAO {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FilesDAO.class);
    private final String businessRegisterPage = "businessRegister.Page";
    private final String businessAdditionPage = "businessAddition.Page";
    private final String businessListPage = "businessList.Page";
    private final String payFilePage = "payFilePage.Page";
    private final String payFilePageMore = "payFilePageMore.Page";
    private final String feePayManagePage = "feePayManagePage.Page";
    private final String feeManagePage = "feeManagePage.Page";
    private String lookupFilesByLeaderPage = "lookupFilesByLeader.Page";
    private String lookupFilesByLeaderApproveSdbs = "lookupFilesByLeaderApproveSdbs.Page";
    private String lookupFilesByStaffPage = "lookupFilesByStaff.Page";
    private String lookupFilesByStaffDonothingPage = "lookupFilesByStaffDonothing.Page";
    private final String lookupFilesByClericalPage = "lookupFilesByClerical.Page";
    private final String lookupFilesByClericalForAAPage = "lookupFilesByClericalForAA.Page";
    private final String lookupAfterAnnouncedPage = "lookupAfterAnnounced.Page";
    private String lookupFilesByStaffOfStaffPage = "lookupFilesByLeaderOfStaff.Page";
    private String toComparisonPage = "toComparison.Page";
    private final String toComparisonFailPage = "toComparisonFail.Page";
    private final String toComparisonAlertPage = "toComparisonAlert.Page";
    private final String RECEIVED_PAGE = "received.page";
    private final String PROPOSE_EVALUATION_PAGE = "proposeEvaluation.page";
    private final String REPORT_STAFF_PROCESS_PAGE = "reportStaffProcess.Page";
    private final String RETURN_FILE_TO_ADDITION_PAGE = "returnFileToAddition.Page";
    private final String REPORT_LOS_PROCESS_PAGE = "reportLOSProcess.Page";//141215u binhnt53 page thong ke lanh dao phong xu ly ho so
    private final String REPORT_L_PROCESS_PAGE = "reportLProcess.Page";//141215u binhnt53 page thong ke lanh dao cuc xu ly ho so
    private final String ASSIGN_EVALUATION_PAGE = "assignEvaluation.page";
    private final String ASSIGN_EVALUATION_FORRE_PAGE = "assignEvaluationForRE.page";
    private final String ASSIGN_EVALUATION_AFTER_ANNOUNCED_PAGE = "assignEvaluationAfterAnnounced.page";
    private final String RE_ASSIGN_EVALUATION_PAGE = "reAssignEvaluation.page";
    private final String EVALUATION_PAGE = "evaluateFile.page";
    private final String EVALUATION_PAGE_VIEW = "evaluateFileView.page";
    private final String EVALUATION_LEADER_PAGE = "evaluateLeaderFile.page";//pho phong tham dinh ho so
    private final String EVALUATION_LEADER_PAGE_AA = "evaluateLeaderFileAA.page";//ldp tham dinh hso bsung sau cong bo
    private final String FEEDBACK_EVALUATION_PAGE = "feedbackEvaluateFile.page";
    private final String COEVALUATION_PAGE = "coEvaluation.page";
    private final String REVIEW_PAGE = "reviewFile.page";
    private final String ADDITION_REVIEW_PAGE = "additionReviewFile.page";
    private final String SIGNING_PAGE = "signingFile.page";
    private final String SIGN_PAGE = "signFile.page";
    private final String APPROVE_PAGE = "approveFile.page";
    private final String APPROVED_PAGE = "approvedFile.page";
    private final String ASSIGN_APPROVE_PAGE = "assignApproveFile.page";
    private final String APPROVE_BY_CT_PAGE = "approveByCT.page";
    private final String lookupRepositoriesPage = "lookupRepositories.page";
    private final String REPOSITORY_PAGE = "repository.page";

    //hiepvv sdbs
    private final String businessEditAfterAnnouncedFilesPage = "businessEditAfterAnnouncedFiles.Page";//Hiepvv
    private final String announcementFile05 = "announcementFile05";//Hiepvv
    //end
    private final String APPROVED_CHANGEFILE_PAGE = "approvedChangeFile.page";//Hiepvv
    private final String RECEIVEDAFTERANNOUNED_PAGE = "receivedAfterAnnounced.page";//Hiepvv
    private final String APPROVE_EDITAFTERANNOUNCED_PAGE = "approveEditFileAfterAnnounced.page";//Hiepvv
    private final String REVIEW_EDITAFTERANNOUNCE_PAGE = "reviewFileEditAfterAnnounced.page"; //Hiepvv
    private final String EVALUATION_EDITAFTERANNOUNCED_PAGE = "evaluateFileEditAfterAnnounced.page"; //Hiepvv
//    private final String ASSIGN_EVALUATION_EDITAFTERANNOUNCED_PAGE = "assignEvaluationEditAfterAnnounced.page"; //Hiepvv

    private FilesForm searchForm;
    private FilesForm createForm;
    private FilesForm createFormCompare;
    private FilesForm createFormClone;
    private FilesForm createFormOriginal;
    private FilesForm signCAForm;
    private FilesForm provideForm;
    private FilesForm signingForm;
    private FilesForm rejectForm;
    private FilesForm signedForm;
    private FilesForm signForm;
    private FilesForm flowForm;
    private RepositoriesForm repForm;
    private ProcessCommentForm commentForm;
    private ProcessCommentForm filesCommentForm;
    private List<FilesForm> lstItemOnGrid;
    private List<ProcessForm> lstProcessOnGrid;
    private List lstCategory;
    private List lstDeptProcessFile;
    private List lstLeaderOfStaff;
    private List lstLeader;
    private List lstLeaderP;
    private List lstStatus;
    private List lstOldVersion;
    private List lstProductType;
    private List lstRepositories;
    private List lstUnit;
    private List lstProvince;
    private RepositoriesForm repoForm;
    private List lstStandard;
    private List lstUserAttach;
    private FeeForm createFeeForm;
    private FeeForm searchFeeForm;
    private FeeDAOHE FeeDAOHE;
    private FeePaymentFileForm searchFeeFormNew;
    private LoginCAForm loginCAForm;
    private static Long fileSourceID;
    private static boolean isEdit = false;
    private static final String separatorFile = String.valueOf(File.separatorChar);

    /**
     *
     * @return String
     */
    public String toReportStaffProcesssPage() {
        return REPORT_STAFF_PROCESS_PAGE;
    }

    /**
     *
     * @return
     */
    public String toReportLOSProcesssPage() {//141215u binhnt53
        return REPORT_LOS_PROCESS_PAGE;
    }

    public String toReportLProcesssPage() {//141215u binhnt53
        return REPORT_L_PROCESS_PAGE;
    }

    /**
     * add 150108 binhnt53 chuc nang quan tri quan li chuyen luong ho so sdbs
     *
     * @return
     */
    public String toReturnFileToAdditionPage() {
        return RETURN_FILE_TO_ADDITION_PAGE;
    }

    /**
     * a150108 binhnt53 onserch chuc nang quan tri quan li chuyen luong ho so
     * sdbs
     *
     * @return
     */
    public String onsearchReturnFileToAddition() {
        getGridInfo();
        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        GridResult gr = bdhe.searchLookupFiles(searchForm, getDepartmentId(), getUserId(), Constants.ROLES.ADMIN, start, count, sortField, "");
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onsearchReportStaffProcess() {
        getGridInfo();

        VReportStaffProcessDAOHE bdhe = new VReportStaffProcessDAOHE();
        GridResult gr = bdhe.findVReportStaffProcess(searchForm, start, count, sortField);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());

        return GRID_DATA;
    }

    /**
     * thong ke lanh dao phong xu ly ho so
     *
     * @return
     */
    public String onsearchReportLOSProcess() {//141215u binhnt53
        getGridInfo();

        VReportLOSDAOHE bdhe = new VReportLOSDAOHE();
        GridResult gr = bdhe.findVReportLOSProcess(searchForm, start, count, sortField);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());

        return GRID_DATA;
    }

    /**
     * thong ke lanh dao cuc xu ly ho so
     *
     * @return
     */
    public String onsearchReportLProcess() {//141215u binhnt53
        getGridInfo();
        VReportLDAOHE bdhe = new VReportLDAOHE();
        GridResult gr = bdhe.findVReportLProcess(searchForm, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    public String toSelectProcedurePage() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        lstCategory = cdhe.getAllProcedure();
        getRequest().setAttribute("lstCategory", lstCategory);
        return businessRegisterPage;
    }

    public String toLookUpHomePage() {
        CategoryDAOHE cdhe = new CategoryDAOHE();
        lstProductType = cdhe.findAllCategory("SP");
        if (((Category) lstProductType.get(0)).getCategoryId() != -1L) {
            lstProductType.add(0, new Category(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        }
        getRequest().setAttribute("lstProductType", lstProductType);
        return "lookupHomePage.Page";
    }

    public String onSearchLookUpHomePage() {
        getGridInfo();
        VLookupHomepageDAOHE fdhe = new VLookupHomepageDAOHE();
        GridResult gr = fdhe.findAllForHomePage(searchForm, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * prepare phân công thẩm định
     *
     * @return
     */
    public String assignEvaluation() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        //Hiepvv tach rieng chuc nang sua doi sau cong bo
        //List lstTTHC = cdhe.getAllProcedure();
        List lstTTHC = cdhe.getAllProcedure2();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        return ASSIGN_EVALUATION_PAGE;
    }

    public String toAssignEvaluationForRE() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure2();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        return ASSIGN_EVALUATION_FORRE_PAGE;
    }

    /**
     * prepare phân công lại 160317
     *
     * @return
     */
    public String reAssignEvaluation() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        return RE_ASSIGN_EVALUATION_PAGE;
    }

    /**
     * phan cong tham dinh ho so
     *
     * @return
     */
    public String onAssignEvaluation() {
        getGridInfo();

        if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
            try {
                String startServerStr = getRequest().getSession().getAttribute("assignEvaluation.startServer") == null ? "" : getRequest().getSession().getAttribute("assignEvaluation.startServer").toString();
                String countServerStr = getRequest().getSession().getAttribute("assignEvaluation.countServer") == null ? "" : getRequest().getSession().getAttribute("assignEvaluation.countServer").toString();

                if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                    count = Integer.parseInt(countServerStr);
                    start = Integer.parseInt(startServerStr);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901

            }
        }

        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        GridResult gr = bdhe.findAllFileForAssignEvaluation(searchForm, getDepartmentId(), getUserId(), start, count, sortField);

        getRequest().getSession().setAttribute("assignEvaluation.startServer", start);
        getRequest().getSession().setAttribute("assignEvaluation.countServer", count);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * lưu luồng xử lý
     *
     * @return
     */
    public String onSaveProcess() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        String sid = "";
        Long staffProcess = 0l;
        String nameStaffProcess = "";
        Long leaderAssignId = 0L;
        String leaderAssignName = "";
        String type = getRequest().getParameter("type");
        String lstObjectId = getRequest().getParameter("lstObjectId");
        try {
            ProcessDAOHE pDaoHe = new ProcessDAOHE();
            Long fileId;
            if (lstProcessOnGrid != null && lstProcessOnGrid.size() > 0) {
                fileId = lstProcessOnGrid.get(0).getObjectId();

                Process p;
                Integer countObj = 1;
                String[] lstObjectIdSplit = null;
                if ("2".equals(type)) {
                    lstObjectIdSplit = lstObjectId.split(",");
                    countObj = lstObjectIdSplit.length;
                }
                Process processMain;
                List<Process> processSlave;
                for (int obj = 0; obj < countObj; obj++) {
                    if ("2".equals(type)) {
                        fileId = Long.parseLong(lstObjectIdSplit[obj]);
                    }
                    processMain = pDaoHe.getProcessMain(getUserId(), getDepartmentId(), fileId, Constants.OBJECT_TYPE.FILES, Constants.PROCESS_TYPE.MAIN);
                    processSlave = pDaoHe.getProcessSalve(getUserId(), getDepartmentId(), fileId, Constants.OBJECT_TYPE.FILES, Constants.PROCESS_TYPE.COOPERATE);
                    for (int i = 0; i < lstProcessOnGrid.size(); i++) {
                        ProcessForm form = lstProcessOnGrid.get(i);
                        if (form.getProcessId() != null) {
                            p = pDaoHe.findById(form.getProcessId());
                            /*
                             if (Constants.PROCESS_TYPE.PROPOSE.equals(form.getProcessType())) {
                             form.setProcessType(Constants.PROCESS_TYPE.MAIN);
                             }
                             */
                            p.setProcessType(form.getProcessType());
                            p.setProcessStatus(Constants.FILE_STATUS.ASSIGNED);
                            p.setSendUser(getUserName());
                            p.setSendUserId(getUserId());
                            p.setSendGroup(getDepartmentName());
                            p.setSendGroupId(getDepartmentId());
                            p.setStatus(0l);
                            p.setIsActive(1L);
                            getSession().update(p);
                        } else {
                            // Check PROCESS_TYPE.MAIN
                            if (form.getProcessType() == Constants.PROCESS_TYPE.COOPERATE
                                    && processMain != null
                                    && processMain.getReceiveUserId().equals(form.getReceiveUserId())) {
                                resultMessage.add("3");
                                resultMessage.add("Thực hiện phân công xử lý không thành công: Không thể phân công Xử lý chính làm Xử lý phối hợp");
                                jsonDataGrid.setItems(resultMessage);
                                jsonDataGrid.setCustomInfo(customInfo);
                                return GRID_DATA;
                            }

                            // Check PROCESS_TYPE.MAIN
                            if (form.getProcessType() == Constants.PROCESS_TYPE.MAIN
                                    && processSlave != null
                                    && processSlave.size() > 0) {
                                for (int j = 0; j < processSlave.size(); j++) {
                                    if (processSlave.get(j).getSendUserId().equals(form.getReceiveUserId())) {
                                        resultMessage.add("3");
                                        resultMessage.add("Thực hiện phân công xử lý chính không thành công: Bạn cần xóa vai trò xử lý phối hợp trước khi phân xử lý chính cho Cán bộ");
                                        jsonDataGrid.setItems(resultMessage);
                                        jsonDataGrid.setCustomInfo(customInfo);
                                        return GRID_DATA;
                                    }
                                }
                            }
                            ProcessDAOHE psdhe = new ProcessDAOHE();
                            p = psdhe.getProcessByAction(fileId, Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.ASSIGNED, Constants.FILE_STATUS.NEW_CREATE);
                            if (p != null) {
                                p.setSendUser(getUserName());
                                leaderAssignName = getUserName();
                                p.setSendUserId(getUserId());
                                leaderAssignId = getUserId();
                                p.setSendGroup(getDepartmentName());
                                p.setSendGroupId(getDepartmentId());
                                p.setObjectType(Constants.OBJECT_TYPE.FILES);
                                p.setProcessType(form.getProcessType());
                                p.setReceiveDate(getSysdate());
                                p.setReceiveUser(form.getReceiveUser());
                                nameStaffProcess = form.getReceiveUser();
                                p.setReceiveUserId(form.getReceiveUserId());
                                staffProcess = form.getReceiveUserId();
                                p.setReceiveGroup(form.getReceiveGroup());
                                p.setReceiveGroupId(form.getReceiveGroupId());
                                p.setObjectId(fileId);
                                p.setProcessStatus(Constants.FILE_STATUS.ASSIGNED);
                                p.setStatus(0l);
                                p.setIsActive(1L);
                                getSession().update(p);
                            } else {
                                p = new Process();
                                p.setSendUser(getUserName());
                                leaderAssignName = getUserName();
                                p.setSendUserId(getUserId());
                                leaderAssignId = getUserId();
                                p.setSendGroup(getDepartmentName());
                                p.setSendGroupId(getDepartmentId());
                                p.setObjectType(Constants.OBJECT_TYPE.FILES);
                                p.setProcessType(form.getProcessType());
                                p.setReceiveDate(getSysdate());
                                p.setReceiveUser(form.getReceiveUser());
                                nameStaffProcess = form.getReceiveUser();
                                p.setReceiveUserId(form.getReceiveUserId());
                                staffProcess = form.getReceiveUserId();
                                p.setReceiveGroup(form.getReceiveGroup());
                                p.setReceiveGroupId(form.getReceiveGroupId());
                                p.setObjectId(fileId);
                                p.setProcessStatus(Constants.FILE_STATUS.ASSIGNED);
                                p.setStatus(0l);
                                p.setIsActive(1L);
                                getSession().save(p);
                            }
                        }
                        if ("".equals(sid)) {
                            sid = p.getProcessId().toString();
                        } else {
                            sid = sid + "," + p.getProcessId().toString();
                        }
                        boolean bReturn = pDaoHe.deleteProcessNotIn(getUserId(), getDepartmentId(), fileId, Constants.OBJECT_TYPE.FILES, sid);
                        if (!bReturn) {
                            resultMessage.add("3");
                            resultMessage.add("Thực hiện phân công xử lý không thành công");
                            jsonDataGrid.setItems(resultMessage);
                            jsonDataGrid.setCustomInfo(customInfo);
                            return GRID_DATA;
                        }
                        Process pWorking = pDaoHe.getWorkingProcess(fileId, Constants.OBJECT_TYPE.FILES, getDepartmentId());
                        if (pWorking != null) {
                            pWorking.setStatus(Constants.FILE_STATUS.ASSIGNED);
                            getSession().update(pWorking);
                        } else {
                            ProcessDAOHE psdhe = new ProcessDAOHE();
                            Process pold = psdhe.getProcessByAction(fileId, Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.RECEIVED, Constants.FILE_STATUS.NEW_CREATE);
                            if (pold != null) {
                                pold.setStatus(Constants.FILE_STATUS.ASSIGNED);
                                getSession().update(pold);
                            }
                        }
                        if (fileId != null && fileId.longValue() > -1) {
                            FilesDAOHE fDAOHE = new FilesDAOHE();
                            Files f = fDAOHE.findById(fileId);
                            if (lstProcessOnGrid.size() > 0) {
                                f.setStatus(Constants.FILE_STATUS.ASSIGNED);
                                if (((f.getStaffProcess() != null
                                        && f.getStaffProcess().equals(getUserId()) == false)
                                        || f.getStaffProcess() == null)
                                        && !staffProcess.equals(0L)) {
                                    f.setStaffProcess(staffProcess);
                                    f.setNameStaffProcess(nameStaffProcess);
                                }
                                //add lanh dao phan cong vao ho so - 140915 binhnt53
                                if (f.getLeaderAssignId() == null) {
                                    f.setLeaderAssignId(leaderAssignId);
                                    f.setLeaderAssignName(leaderAssignName);
                                }

                            } else {
                                f.setStatus(Constants.FILE_STATUS.NEW);
                            }
                            f.setDisplayStatus(FilesDAOHE.getFileStatusName(f.getStatus()));
                            getSession().update(f);
                            fDAOHE.saveStatusFiles(f, "Trạng thái hiện tại của hồ sơ: Đã thực hiện phân công hồ sơ mã: " + f.getFileCode());
                        }
                    }
                }
            }
            resultMessage.add("1");
            resultMessage.add("Thực hiện phân công xử lý thành công");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Thực hiện phân công xử lý không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /**
     * get luong xu ly cua ho so
     *
     * @return
     * @throws Exception
     */
    public String getProcessOfFile() throws Exception {
        getGridInfo();
        Long fileId = com.viettel.vsaadmin.common.util.StringUtils.convertFromStringToLong(getRequest().getParameter("fileId"));
        FilesDAOHE bdhe = new FilesDAOHE();
        GridResult gr = bdhe.findAllProcessByFileId(fileId, getDepartmentId(), getUserId(), start, count, sortField);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * lưu luồng phối hợp xử lý
     *
     * @return
     * @throws Exception
     */
    public String getCoProcessOfFile() throws Exception {
        getGridInfo();
        Long fileId = com.viettel.vsaadmin.common.util.StringUtils.convertFromStringToLong(getRequest().getParameter("fileId"));
        FilesDAOHE bdhe = new FilesDAOHE();
        GridResult gr = bdhe.findAllCoProcessByFileId(fileId, getDepartmentId(), getUserId(), start, count, sortField);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * đề xuất thẩm định
     *
     * @return
     */
    public String proposeEvaluation() {
        ProcedureDAOHE pdhe = new ProcedureDAOHE();
        List lstTTHC = pdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        return PROPOSE_EVALUATION_PAGE;
    }

    /**
     * Phối hợp xử lý
     *
     * @return
     */
    public String onProposeEvaluation() {
        getGridInfo();
        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        UsersDAOHE udhe = new UsersDAOHE();
        Long deptId = udhe.getAgencyOfStaff(getUserId());
        GridResult gr = bdhe.findAllFileForProposeEvaluation(searchForm, deptId, start, count, sortField);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * đề xuất thẩm định
     *
     * @return
     */
    public String onPropose() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        HttpServletRequest req = getRequest();
        HttpSession session = req.getSession();
        viettel.passport.client.UserToken userToken = (viettel.passport.client.UserToken) session.getAttribute("userToken");
        try {
            FilesDAOHE bdhe = new FilesDAOHE();
            Long userId = userToken.getUserID();
            String userName = userToken.getFullName();

            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                FilesForm form = lstItemOnGrid.get(i);
                if (form != null) {
                    Files bo = bdhe.getById("fileId", form.getFileId());
                    if (bo != null && !Constants.FILE_STATUS.PROPOSED.equals(bo.getStatus())) {
                        bo.setStatus(Constants.FILE_STATUS.PROPOSED);
                        bo.setDisplayStatus(FilesDAOHE.getFileStatusName(Constants.FILE_STATUS.PROPOSED));
                        getSession().update(bo);
                    }

                    String hql = " select count(p)"
                            + " from Process p"
                            + " where p.isActive = 1"
                            + " and p.objectType = ?"
                            + " and p.objectId = ?"
                            + " and p.receiveUserId =?";
                    Query query = getSession().createQuery(hql);
                    query.setParameter(0, Constants.OBJECT_TYPE.FILES);
                    query.setParameter(1, form.getFileId());
                    query.setParameter(2, userId);
                    Long count = (Long) query.uniqueResult();
                    if (count > 0l) {
                        resultMessage.add("3");
                        resultMessage.add("Bạn đã thực hiện đề xuất");
                    } else {
                        Process p = new Process();
                        p.setIsActive(1L);
                        p.setProcessType(Constants.PROCESS_TYPE.PROPOSE);
                        p.setObjectId(bo.getFileId());
                        p.setReceiveUserId(userId);
                        p.setReceiveUser(userName);
                        p.setReceiveGroupId(getDepartmentId());
                        p.setReceiveGroup(getDepartment().getDeptName());
                        p.setObjectType(Constants.OBJECT_TYPE.FILES);
                        p.setReceiveDate(DateTimeUtils.getDate());
                        getSession().save(p);
                    }
                }
            }

            resultMessage.add("1");
            resultMessage.add("Thực hiện đề xuất thành công");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Thực hiện đề xuất không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /**
     * phối hợp thẩm định
     *
     * @return
     */
    public String toCoEvaluate() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        return COEVALUATION_PAGE;
    }

    /**
     * Vao trang tham dinh
     *
     * @return
     */
    public String toEvaluatePage() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        //Hiepvv tach rieng tham dinh ho so SDBS sau cong bo
        Long isChangeAfteAnnounce = getRequest().getParameter("IsChange") == null ? 0L : Long.parseLong(getRequest().getParameter("IsChange"));
        if (isChangeAfteAnnounce > 0) {
            isEdit = true;
            Procedure p = new Procedure();
            try {
                p = cdhe.getProcedureByDescription(announcementFile05);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                p = null;
            }
            lstCategory = new ArrayList();
            lstCategory.add(p);
            lstCategory.add(1, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            getRequest().setAttribute("lstFileType", lstCategory);
            UsersDAOHE udaohe = new UsersDAOHE();
            List<String> lstStaff = new ArrayList<String>();
            lstStaff.add(Constants.POSITION.VFA_CV);
            lstStaff.add(Constants.POSITION.NV);
            if (udaohe.checkUserByLstPosition(getDepartmentId(), getUserId(), lstStaff)) {//la chuyen vien
                List lstLDP = udaohe.getAllLeaderOfStaffInOffice(getDepartmentId());
                if (lstLDP != null) {
                    List lstLeaderOfStaffOnGrid = new ArrayList();
                    lstLeaderOfStaffOnGrid.addAll(lstLDP);
                    lstLeaderOfStaffOnGrid.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                    getRequest().setAttribute("lstLeaderOfStaff", lstLeaderOfStaffOnGrid);
                }
            } else {//la pho phong - lay danh sach ldc va truong phong
                List lstLDC = udaohe.getLeaderByUser(getDepartmentId());
                List lstLDP = udaohe.getTruongPhong(getDepartmentId());
                List lstLeaderOfStaffOnGrid = new ArrayList();
                if (lstLDC != null) {
                    lstLeaderOfStaffOnGrid.addAll(lstLDC);
                }
                if (lstLDP != null) {
                    lstLeaderOfStaffOnGrid.addAll(lstLDP);
                }
                lstLeaderOfStaffOnGrid.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                getRequest().setAttribute("lstLeaderOfStaff", lstLeaderOfStaffOnGrid);
            }
            return EVALUATION_EDITAFTERANNOUNCED_PAGE;
        } else {
            isEdit = false;
//        List lstTTHC = cdhe.getAllProcedure();
//        Hiepvv Loc rieng SDBS sau cong bo
            List lstTTHC = cdhe.getAllProcedure2();
            lstCategory = new ArrayList();
            lstCategory.addAll(lstTTHC);
            lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            getRequest().setAttribute("lstFileType", lstCategory);
            UsersDAOHE udaohe = new UsersDAOHE();
            List<String> lstStaff = new ArrayList<String>();
            lstStaff.add(Constants.POSITION.VFA_CV);
            lstStaff.add(Constants.POSITION.NV);
            if (udaohe.checkUserByLstPosition(getDepartmentId(), getUserId(), lstStaff)) {//la chuyen vien
                List lstLDP = udaohe.getAllLeaderOfStaffInOffice(getDepartmentId());
                if (lstLDP != null) {
                    List lstLeaderOfStaffOnGrid = new ArrayList();
                    lstLeaderOfStaffOnGrid.addAll(lstLDP);
                    lstLeaderOfStaffOnGrid.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                    getRequest().setAttribute("lstLeaderOfStaff", lstLeaderOfStaffOnGrid);
                }
            } else {//la pho phong - lay danh sach ldc va truong phong
                List lstLDC = udaohe.getLeaderByUser(getDepartmentId());
                List lstLDP = udaohe.getTruongPhong(getDepartmentId());
                List lstLeaderOfStaffOnGrid = new ArrayList();
                if (lstLDC != null) {
                    lstLeaderOfStaffOnGrid.addAll(lstLDC);
                }
                if (lstLDP != null) {
                    lstLeaderOfStaffOnGrid.addAll(lstLDP);
                }
                lstLeaderOfStaffOnGrid.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                getRequest().setAttribute("lstLeaderOfStaff", lstLeaderOfStaffOnGrid);
            }
            return EVALUATION_PAGE;
        }
    }

    /**
     * Xem chi tiet ho so tren Dialog
     *
     * @return
     */
    public String toFileDlgView() {
        String fileId = getRequest().getParameter("fileId");
        String viewType = getRequest().getParameter("viewType");
        String backPage = getRequest().getParameter("backPage");
        getRequest().setAttribute("fileId", fileId);
        getRequest().setAttribute("viewType", viewType);
        getRequest().setAttribute("backPage", backPage);
        return EVALUATION_PAGE_VIEW;
    }

    /**
     * Vao trang pho phong tham dinh
     *
     * @return
     */
    public String toEvaluateLeaderPage() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        // Get Role
        List<Roles> roles = getListRolesByUser();
        String lstRole = "";
        if (roles != null && roles.size() > 0) {
            for (int i = 0; i < roles.size(); i++) {
                lstRole += roles.get(i).getRoleCode() + ";";
            }
        }
        getRequest().setAttribute("lstRole", lstRole);

        UsersDAOHE udaohe = new UsersDAOHE();
        List<String> lstStaff = new ArrayList<String>();
        lstStaff.add(Constants.POSITION.VFA_CV);
        lstStaff.add(Constants.POSITION.NV);
        if (udaohe.checkUserByLstPosition(getDepartmentId(), getUserId(), lstStaff)) {//la chuyen vien
            List lstLDP = udaohe.getAllLeaderOfStaffInOffice(getDepartmentId());
            if (lstLDP != null) {
                List lstLeaderOfStaffOnGrid = new ArrayList();
                lstLeaderOfStaffOnGrid.addAll(lstLDP);
                lstLeaderOfStaffOnGrid.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                getRequest().setAttribute("lstLeaderOfStaff", lstLeaderOfStaffOnGrid);
            }
        } else {//la pho phong - lay danh sach ldc va truong phong
            List lstLDC = udaohe.getLeaderByUser(getDepartmentId());
            List lstLDP = udaohe.getTruongPhong(getDepartmentId());
            List lstLeaderOfStaffOnGrid = new ArrayList();
            if (lstLDC != null) {
                lstLeaderOfStaffOnGrid.addAll(lstLDC);
            }
            if (lstLDP != null) {
                lstLeaderOfStaffOnGrid.addAll(lstLDP);
            }
            lstLeaderOfStaffOnGrid.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            getRequest().setAttribute("lstLeaderOfStaff", lstLeaderOfStaffOnGrid);
        }
        return EVALUATION_LEADER_PAGE;
    }

    /**
     * thông báo thẩm định
     *
     * @return
     */
    public String toFeedbackEvaluatePage() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        return FEEDBACK_EVALUATION_PAGE;
    }

    /**
     * tìm kiếm để xem xét hồ sơ
     *
     * @return
     */
    public String toReviewPage() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        //Hiepvv tach rieng tham dinh ho so SDBS sau cong bo
        Long isChangeAfteAnnounce = getRequest().getParameter("IsChange") == null ? 0L : Long.parseLong(getRequest().getParameter("IsChange"));
        if (isChangeAfteAnnounce > 0) {
            isEdit = true;
            Procedure p = new Procedure();
            try {
                p = cdhe.getProcedureByDescription(announcementFile05);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                p = null;
            }
            lstCategory = new ArrayList();
            lstCategory.add(p);
            lstCategory.add(1, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            getRequest().setAttribute("lstFileType", lstCategory);
            UsersDAOHE udaohe = new UsersDAOHE();
            List lstLDC = udaohe.getLeaderByUser(getDepartmentId());
            lstLeader = new ArrayList();
            if (lstLDC != null) {
                lstLeader.addAll(lstLDC);
            }
            lstLeader.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            getRequest().setAttribute("lstLeader", lstLeader);
            return REVIEW_EDITAFTERANNOUNCE_PAGE;
        } else {
            isEdit = false;
            List lstTTHC = cdhe.getAllProcedure2();
            lstCategory = new ArrayList();
            lstCategory.addAll(lstTTHC);
            lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            getRequest().setAttribute("lstFileType", lstCategory);

            UsersDAOHE udaohe = new UsersDAOHE();
            List lstLDC = udaohe.getLeaderByUser(getDepartmentId());
            lstLeader = new ArrayList();
            if (lstLDC != null) {
                lstLeader.addAll(lstLDC);
            }
            lstLeader.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            getRequest().setAttribute("lstLeader", lstLeader);

            return REVIEW_PAGE;
        }
    }

    /**
     * tìm kiếm để phê duyệt hồ sơ
     *
     * @return
     */
    public String toApprovePage() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();//Hiepvv tach rieng tham dinh ho so SDBS sau cong bo
        isEdit = false;
        List lstTTHC = cdhe.getAllProcedure2();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        return APPROVE_PAGE;
    }

    public String toApprovePageAA() {//bổ sung sau công bố
        ProcedureDAOHE cdhe = new ProcedureDAOHE();//Hiepvv tach rieng tham dinh ho so SDBS sau cong bo
        isEdit = true;
        Procedure p = new Procedure();
        try {
            p = cdhe.getProcedureByDescription(announcementFile05);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            p = null;
        }
        lstCategory = new ArrayList();
        lstCategory.add(p);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        return APPROVE_EDITAFTERANNOUNCED_PAGE;
    }

    /**
     * tim ho so de phan cong phe duyet
     *
     * @return
     */
    public String toAssignApprovePage() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        UsersDAOHE udaohe = new UsersDAOHE();
        List lstLDC = udaohe.getLeaderP(getDepartmentId());
        if (lstLDC != null) {
            lstLeaderP = new ArrayList();
            lstLeaderP.addAll(lstLDC);
            lstLeaderP.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            getRequest().setAttribute("lstLeaderP", lstLeaderP);
        }

        return ASSIGN_APPROVE_PAGE;
    }

    public String toApproveByCTPage() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        UsersDAOHE udaohe = new UsersDAOHE();
        List lstLDC = udaohe.getLeaderP(getDepartmentId());
        if (lstLDC != null) {
            lstLeaderP = new ArrayList();
            lstLeaderP.addAll(lstLDC);
            lstLeaderP.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            getRequest().setAttribute("lstLeaderP", lstLeaderP);
        }

        return APPROVE_BY_CT_PAGE;
    }

    /**
     * hồ sơ đã phê duyệt
     *
     * @return
     */
//    public String toApprovedPage() {
//        ProcedureDAOHE cdhe = new ProcedureDAOHE();//Hiepvv tach rieng tham dinh ho so SDBS sau cong bo
//        
//            List lstTTHC = cdhe.getAllProcedure2();
//            lstCategory = new ArrayList();
//            lstCategory.addAll(lstTTHC);
//            lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
//            getRequest().setAttribute("lstFileType", lstCategory);
//            return APPROVED_PAGE;
//        
//    }
    public String toApprovedPage() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();//Hiepvv tach rieng tham dinh ho so SDBS sau cong bo
        Long isChangeAfteAnnounce = getRequest().getParameter("IsChange") == null ? 0L : Long.parseLong(getRequest().getParameter("IsChange"));
        if (isChangeAfteAnnounce > 0) {
            Procedure p = new Procedure();
            try {
                p = cdhe.getProcedureByDescription(announcementFile05);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                p = null;
            }
            lstCategory = new ArrayList();
            lstCategory.add(p);
            lstCategory.add(1, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            getRequest().setAttribute("lstFileType", lstCategory);
            return APPROVED_CHANGEFILE_PAGE;
        } else {
            List lstTTHC = cdhe.getAllProcedure2(); //Hiepvv tach rieng man hinh sdbs sau cong bo
//            List lstTTHC = cdhe.getAllProcedure();
            lstCategory = new ArrayList();
            lstCategory.addAll(lstTTHC);
            lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            getRequest().setAttribute("lstFileType", lstCategory);
            return APPROVED_PAGE;
        }
    }

    /**
     * Tim kiem ho so de tham dinh
     *
     * @return
     */
    public String onSearchFileToEvaluate() {
        getGridInfo();

        if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
            try {
                String startServerStr = getRequest().getSession().getAttribute("evaluatePage.startServer") == null ? "" : getRequest().getSession().getAttribute("evaluatePage.startServer").toString();
                String countServerStr = getRequest().getSession().getAttribute("evaluatePage.countServer") == null ? "" : getRequest().getSession().getAttribute("evaluatePage.countServer").toString();

                if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                    count = Integer.parseInt(countServerStr);
                    start = Integer.parseInt(startServerStr);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                log.error(ex.getMessage());
            }
        }
        //Hiepvv tách riêng SDBS sau công bố
        if (isEdit) {
            ProcedureDAOHE pHE = new ProcedureDAOHE();
            Procedure p = new Procedure();
            try {
                p = pHE.getProcedureByDescription(announcementFile05);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
            if (p != null) {
                searchForm.setFileType(p.getProcedureId());
                searchForm.setNoteEdit(announcementFile05);
            }
        }
        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
        GridResult gr = fdhe.searchFilesToProcess(searchForm, getDepartmentId(), getUserId(), 1L, start, count, sortField);

        getRequest().getSession().setAttribute("evaluatePage.startServer", start);
        getRequest().getSession().setAttribute("evaluatePage.countServer", count);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * Tim kiem ho so de pho phong tham dinh
     *
     * @return
     */
    public String onSearchFileToEvaluateLeader() {
        getGridInfo();
        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
        GridResult gr = fdhe.searchFilesToProcess(searchForm, getDepartmentId(), getUserId(), 9L, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /*
     * Tim kiem ho so de tham dinh
     *
     * @return
     */
    public String onSearchFileToFeedbackEvaluate() {
        getGridInfo();
        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
        GridResult gr = fdhe.searchFilesToProcess(searchForm, getDepartmentId(), getUserId(), 7l, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * tìm kiếm hồ sơ phối hợp thẩm định
     *
     * @return
     */
    public String onSearchFileToCoEvaluate() {
        getGridInfo();
        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
        GridResult gr = fdhe.searchFilesToProcess(searchForm, getDepartmentId(), getUserId(), 6l, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * Tim kiem hò so de xem xet - lanh dao cuc
     *
     * @return
     */
    public String onSearchFileToReview() {
        getGridInfo();
        //Hiepvv tách riêng SDBS sau công bố
        if (isEdit) {
            ProcedureDAOHE pHE = new ProcedureDAOHE();
            Procedure p = new Procedure();
            try {
                p = pHE.getProcedureByDescription(announcementFile05);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
            if (p != null) {
                searchForm.setFileType(p.getProcedureId());
                searchForm.setNoteEdit(announcementFile05);
            }
        }
        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
        GridResult gr = fdhe.searchFilesToProcess(searchForm, getDepartmentId(), getUserId(), 2l, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * Tim kiem hò so đã phê duyệt
     *
     * @return
     */
    public String onSearchFileToApprove() {//
        getGridInfo();

        if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
            try {
                String startServerStr = getRequest().getSession().getAttribute("feeSearch.startServer") == null ? "" : getRequest().getSession().getAttribute("feeSearch.startServer").toString();
                String countServerStr = getRequest().getSession().getAttribute("feeSearch.countServer") == null ? "" : getRequest().getSession().getAttribute("feeSearch.countServer").toString();

                if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                    count = Integer.parseInt(countServerStr);
                    start = Integer.parseInt(startServerStr);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
        }
        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
        GridResult gr = fdhe.searchFilesToProcess(searchForm, getDepartmentId(), getUserId(), 3L, start, count, sortField);

        getRequest().getSession().setAttribute("feeSearch.startServer", start);
        getRequest().getSession().setAttribute("feeSearch.countServer", count);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    public String onSearchFileToApproveAA() {//
        getGridInfo();

        if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
            try {
                String startServerStr = getRequest().getSession().getAttribute("feeSearch.startServer") == null ? "" : getRequest().getSession().getAttribute("feeSearch.startServer").toString();
                String countServerStr = getRequest().getSession().getAttribute("feeSearch.countServer") == null ? "" : getRequest().getSession().getAttribute("feeSearch.countServer").toString();

                if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                    count = Integer.parseInt(countServerStr);
                    start = Integer.parseInt(startServerStr);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
        }

        ProcedureDAOHE pHE = new ProcedureDAOHE();
        Procedure p = new Procedure();
        try {
            p = pHE.getProcedureByDescription(announcementFile05);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (p != null) {
            searchForm.setFileType(p.getProcedureId());
            searchForm.setNoteEdit(announcementFile05);
        }
        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
        GridResult gr = fdhe.searchFilesToProcess(searchForm, getDepartmentId(), getUserId(), 3L, start, count, sortField);

        getRequest().getSession().setAttribute("feeSearch.startServer", start);
        getRequest().getSession().setAttribute("feeSearch.countServer", count);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    public String onSearchFileToAssignApprove() {//
        getGridInfo();
        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
        GridResult gr = fdhe.searchFilesToProcess(searchForm, getDepartmentId(), getUserId(), -3L, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    //cuc truong phe duyet ho so - binhnt53
    public String onSearchFileToApproveByCT() {
        getGridInfo();

        if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
            try {
                String startServerStr = getRequest().getSession().getAttribute("approvePageByCT.startServer") == null ? "" : getRequest().getSession().getAttribute("approvePageByCT.startServer").toString();
                String countServerStr = getRequest().getSession().getAttribute("approvePageByCT.countServer") == null ? "" : getRequest().getSession().getAttribute("approvePageByCT.countServer").toString();

                if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                    count = Integer.parseInt(countServerStr);
                    start = Integer.parseInt(startServerStr);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
        }

        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
        GridResult gr = fdhe.searchFilesToProcess(searchForm, getDepartmentId(), getUserId(), 29L, start, count, sortField);

        getRequest().getSession().setAttribute("approvePageByCT.startServer", start);
        getRequest().getSession().setAttribute("approvePageByCT.countServer", count);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * Tim kiem hò so da phe duyet
     *
     * @return
     */
    public String onSearchFileToApproved() {
        getGridInfo();
        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
        GridResult gr = fdhe.searchFilesToProcess(searchForm, getDepartmentId(), getUserId(), 30l, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * Tham dinh ho so
     *
     * @return
     */
    public String onEvaluate() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List resultMessage = new ArrayList();
        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        if (check) {
            boolean bReturn = fdhe.onEvaluate(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());//thuc hien tham dinh ho so
            if (bReturn) {
                resultMessage.add("1");
                resultMessage.add("Lưu dữ liệu thành công");
                Files file = fdhe.findById(createForm.getFileId());
                fdhe.saveStatusFiles(file, "Hồ sơ mã: " + file.getFileCode() + " Đã được thẩm định");
            } else {
                resultMessage.add("3");
                resultMessage.add("Lưu dữ liệu không thành công");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Thẩm định hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());
        return GRID_DATA;
    }

    /**
     * soan thao noi dung cong van thong bao sdbs
     *
     * @return
     */
    public String onFeedbackEvaluate() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List resultMessage = new ArrayList();
        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        if (check) {
            boolean bReturn = fdhe.onEvaluate(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
            if (bReturn) {
                resultMessage.add("1");
                resultMessage.add("Lưu dữ liệu thành công");
                //
                Files file = fdhe.findById(createForm.getFileId());
                fdhe.saveStatusFiles(file, "Hồ sơ mã: " + file.getFileCode() + " Đã được thẩm định");
            } else {
                resultMessage.add("3");
                resultMessage.add("Lưu dữ liệu không thành công");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");
        }

        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Thẩm định hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());
        return GRID_DATA;
    }

    /**
     * Đối chiếu hồ sơ
     *
     * @return
     */
    public String onComparison() {
        FilesDAOHE fdhe = new FilesDAOHE();
        boolean bReturn = fdhe.onComparison(createFormCompare, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        List resultMessage = new ArrayList();
        EventLogDAOHE edhe = new EventLogDAOHE();
        if (bReturn) {
            Files file = fdhe.findById(createFormCompare.getFileId());

            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");

            fdhe.saveStatusFiles(file, "Hồ sơ mã: " + file.getFileCode() + " Đã được đối chiếu");

            if (file.getStatus().equals(Constants.FILE_STATUS.COMPARED)) {
                edhe.insertEventLog("Đối chiếu hồ sơ", "Hồ sơ ID" + createFormCompare.getFileId() + " Mã: " + file.getFileCode() + " Đã được đối chiếu Thành công", getRequest());
            } else {
                edhe.insertEventLog("Đối chiếu hồ sơ", "Hồ sơ ID" + createFormCompare.getFileId() + " Mã: " + file.getFileCode() + " Đã được đối chiếu Có sai lệch", getRequest());
            }

        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
            edhe.insertEventLog("Đối chiếu hồ sơ", "Hồ sơ ID:" + createFormCompare.getFileId() + " gặp sự cố khi đối chiếu", getRequest());
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /*
     * lanh dao don vi xem xet ho so
     */
    public String onReviewManyFiles() {

        List resultMessage = new ArrayList();
        jsonDataGrid.setItems(resultMessage);
        FilesDAOHE fdhe = new FilesDAOHE();
        int nSuccess = 0;
        int nError = 0;
        String sid = "";
        Long id = getRequest().getParameter("leaderId") == null ? 0L : Long.parseLong(getRequest().getParameter("leaderId"));
        String name = "";
        try {
            Base64 decoder = new Base64();
            name = new String(decoder.decode(getRequest().getParameter("leaderName").replace("_", "+").getBytes()), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            Logger.getLogger(FilesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < lstItemOnGrid.size(); i++) {
            FilesForm form = lstItemOnGrid.get(i);
            if (form != null && form.getFileId() != null && form.getFileId() != 0D) {
                boolean check = fdhe.validateRoleUser(form.getFileId(), form, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
                if (check) {
                    form.setStatus(Constants.FILE_STATUS.REVIEWED);
                    form.setLeaderStaffRequest("Lãnh đạo phòng đã xem xét hồ sơ đạt.");
                    form.setLeaderApproveId(id);
                    form.setLeaderApproveName(name);
                    boolean bReturn = fdhe.onReviewManyFiles(form, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
                    sid += form.getFileId() + ",";
                    if (bReturn) {
                        nSuccess++;
                    } else {
                        nError++;
                    }
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");
                }
            }
        }
        String strAlert = "Xem xét nhiều hồ sơ thành công, có " + nSuccess + " hồ sơ thành công và " + nError + " hồ sơ xem xét không thành công";
        resultMessage.add("1");
        resultMessage.add(strAlert);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Thêm mới hồ sơ", "Hủy hồ sơ có id=" + sid, getRequest());
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;

    }

    /*
     * lanh dao don vi xem xet ho so
     */
    public String onReview() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List resultMessage = new ArrayList();
        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        if (check) {
            boolean bReturn = fdhe.onReviewEvaluate(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
            if (bReturn) {
                resultMessage.add("1");
                resultMessage.add("Lưu dữ liệu thành công");
            } else {
                resultMessage.add("3");
                resultMessage.add("Lưu dữ liệu không thành công");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Xem xét hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());

        return GRID_DATA;
    }

    public String onAssignApprove() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List resultMessage = new ArrayList();
        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        if (check) {

            boolean bReturn = fdhe.onAssignApprove(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());

            if (bReturn) {
                resultMessage.add("1");
                resultMessage.add("Lưu dữ liệu thành công");
            } else {
                resultMessage.add("3");
                resultMessage.add("Lưu dữ liệu không thành công");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");
        }

        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Phân công phê duyệt hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());

        return GRID_DATA;
    }

    public String onAssignApproveByCT() {
        FilesDAOHE fdhe = new FilesDAOHE();
        boolean bReturn = fdhe.onAssignApproveByCT(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Phân công phê duyệt hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());

        return GRID_DATA;
    }

    /**
     * xem xet du thao sdbs
     *
     * @return
     */
    public String onFeedbackReview() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List resultMessage = new ArrayList();
        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        if (check) {
            boolean bReturn = fdhe.onReviewEvaluate(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());

            if (bReturn) {
                resultMessage.add("1");
                resultMessage.add("Lưu dữ liệu thành công");
            } else {
                resultMessage.add("3");
                resultMessage.add("Lưu dữ liệu không thành công");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Phê duyệt dự thảo SĐBS", "hồ sơ có id=" + createForm.getFileId(), getRequest());
        return GRID_DATA;
    }

    public String onFeedbackReviewSendVt() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List resultMessage = new ArrayList();
        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        if (check) {
            boolean bReturn = fdhe.onApprove(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
            if (bReturn) {
                resultMessage.add("1");
                resultMessage.add("Lưu dữ liệu thành công");
            } else {
                resultMessage.add("3");
                resultMessage.add("Lưu dữ liệu không thành công");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Phê duyệt công văn SĐBS", "hồ sơ có id=" + createForm.getFileId(), getRequest());
        return GRID_DATA;
    }
//phe duyet du thao sdbs

    public String onFeedbackApprove() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List resultMessage = new ArrayList();
        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        if (check) {
            boolean bReturn = fdhe.onApprove(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
            if (bReturn) {
                resultMessage.add("1");
                resultMessage.add("Lưu dữ liệu thành công");
            } else {
                resultMessage.add("3");
                resultMessage.add("Lưu dữ liệu không thành công");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Phê duyệt công văn SĐBS", "hồ sơ có id=" + createForm.getFileId(), getRequest());

        return GRID_DATA;
    }

    /**
     * xem xet du thao cong van sdbs
     *
     * @return
     */
    public String onFeedbackGiveBack() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List resultMessage = new ArrayList();
        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        if (check) {
            boolean bReturn = fdhe.onEvaluate(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
            if (bReturn) {
                resultMessage.add("1");
                resultMessage.add("Lưu dữ liệu thành công");
            } else {
                resultMessage.add("3");
                resultMessage.add("Lưu dữ liệu không thành công");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Xem xét hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());

        return GRID_DATA;
    }

    /*
     * Phe duyet ho so
     */
    public String onApprove() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List resultMessage = new ArrayList();
        try {
            boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
            if (check) {
                boolean bReturn = fdhe.onApprove(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
                if (bReturn) {
                    resultMessage.add("1");
                    resultMessage.add("Lưu dữ liệu thành công");
                    if (createForm.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {
                        getBarcode(createForm);
                    }
                    Files file = fdhe.findById(createForm.getFileId());
                    // Save status
                    fdhe.saveStatusFiles(file, "Hồ sơ mã: " + file.getFileCode() + " Đã được phê duyệt");
                    // Save Sign date
                    AnnouncementReceiptPaperDAOHE arpDhe = new AnnouncementReceiptPaperDAOHE();
                    AnnouncementReceiptPaper arp = arpDhe.findById(file.getAnnouncementReceiptPaperId());
                    if (arp != null) {
                        VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
                        arp.setSignDate(daoHe.getSysdate());
                        getSession().update(arp);
                    } else {
                        resultMessage.add("3");
                        resultMessage.add("Phê duyệt không thành công");
                        jsonDataGrid.setItems(resultMessage);
                        return GRID_DATA;
                    }
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Phê duyệt không thành công");
                    jsonDataGrid.setItems(resultMessage);
                    return GRID_DATA;
                }
            } else {
                resultMessage.add("3");
                resultMessage.add("Phê duyệt không thành công - Lỗi phân quyền người dùng");
            }

            EventLogDAOHE edhe = new EventLogDAOHE();
            edhe.insertEventLog("Phê duyệt hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());
            //160620 binhnt add gui service den hai quan ban tin cong bo.
//            Helper h = new Helper();
//            try {
//                XmlWs xmlBo = new XmlWs();
//                xmlBo.setContent(h.sendARP(createForm.getFileId()));
//                xmlBo.setUserCreateId(getUserId());
//                xmlBo.setUserCreateName(getUserName());
//                getSession().save(xmlBo);
//
//            } catch (IOException ex) {
//                Logger.getLogger(FilesDAO.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (Base64DecodingException ex) {
//                Logger.getLogger(FilesDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
            getSession().getTransaction().commit();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Phê duyệt không thành công");
//            log.error(ex.getMessage());
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     * Tạo giấy công bố
     *
     * @return
     */
    public String onCreatePaper() {
        FilesDAOHE fdhe = new FilesDAOHE();
        boolean bReturn = fdhe.onCreatePaper(createForm, getDepartmentId(),
                getDepartment().getDeptName(), getUserId(), getUserName());
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
            if (createForm.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {
                getBarcode(createForm);
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     * 150121 binhnt53 luu trang thai ho so
     *
     * @return
     */
    public String onSaveEditFileStatus() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        try {
            Files filesBo;
            FilesDAOHE fdhe = new FilesDAOHE();
            filesBo = fdhe.findById(createForm.getFileId());
            if (filesBo != null) {
                if (createForm.getStatus() != null) {
                    filesBo.setStatus(createForm.getStatus());
                    filesBo.setDisplayStatus(FilesDAOHE.getFileStatusName(createForm.getStatus()));
                }
                if (createForm.getNameStaffProcess() != null && createForm.getStaffProcess() != null && createForm.getStaffProcess() > -1L) {
                    filesBo.setNameStaffProcess(createForm.getNameStaffProcess());
                    filesBo.setStaffProcess(createForm.getStaffProcess());
                }
                if (createForm.getLeaderApproveId() != null && createForm.getLeaderApproveName() != null && createForm.getLeaderApproveId() > -1L) {
                    filesBo.setLeaderApproveId(createForm.getLeaderApproveId());
                    filesBo.setLeaderApproveName(createForm.getLeaderApproveName());
                }
                if (createForm.getLeaderReviewId() != null && createForm.getLeaderReviewName() != null && createForm.getLeaderReviewId() > -1L) {
                    filesBo.setLeaderReviewId(createForm.getLeaderReviewId());
                    filesBo.setLeaderReviewName(createForm.getLeaderReviewName());
                }
                if (createForm.getLeaderEvaluateId() != null && createForm.getLeaderEvaluateName() != null && createForm.getLeaderEvaluateId() > -1L) {
                    filesBo.setLeaderEvaluateId(createForm.getLeaderEvaluateId());
                    filesBo.setLeaderEvaluateName(createForm.getLeaderEvaluateName());
                }
                getSession().save(filesBo);
                resultMessage.add("1");
                resultMessage.add("Cập nhật trạng thái hồ sơ thành công");
            } else {
                resultMessage.add("3");
                resultMessage.add("Cập nhật trạng thái hồ sơ không thành công");
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Cập nhật luồng xử lý không thành công");
//            Logger.getLogger(FilesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return "gridData";
    }
//==============================================================================

    /**
     * Tao CV SDBS
     *
     * @return
     */
    public String onExportCvSdbsSign() {
        ExportFileDAO exp = new ExportFileDAO();
        boolean bReturn = exp.exportDataCvSdbs("EX_SIGN");
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Xuất công văn SĐBS thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lỗi trong quá trình xuất công văn SĐBS");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String onExportCvSdbsSignPlugin() {
        ExportFileDAO exp = new ExportFileDAO();
        //boolean bReturn = exp.exportDataCvSdbs("EX_SIGN");
        String path = exp.exportDataCvSdbsPlugin("EX_SIGN");
        List resultMessage = new ArrayList();
        if (path.trim().length() > 0 && !"false".equals(path)) {
            resultMessage.add("1");
            resultMessage.add("Xuất công văn SĐBS thành công");
            resultMessage.add(path);
        } else {
            resultMessage.add("3");
            resultMessage.add("Lỗi trong quá trình xuất công văn SĐBS");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     * Vao trang khai bao ho so
     *
     * @return
     */
    public String toCreateFilePage() {
//        Long fileId = getRequest().getParameter("fileId") == null ? 0L : Long.parseLong(getRequest().getParameter("fileId"));
        Long fileType = getRequest().getParameter("fileType") == null ? 0L : Long.parseLong(getRequest().getParameter("fileType"));
        Long checkEdit = getRequest().getParameter("checkEdit") == null ? 0L : Long.parseLong(getRequest().getParameter("checkEdit"));
        Long typeFee = getRequest().getParameter("typeFee") == null ? 0L : Long.parseLong(getRequest().getParameter("typeFee"));
//      hiepvv check edit after announced
        Long isEdits = getRequest().getParameter("isEdit") == null ? 0L : Long.parseLong(getRequest().getParameter("isEdit"));
        if (fileType > 0L) {
            createForm = new FilesForm();
            createForm.setFileType(fileType);
        }
        Users user;
        UsersDAOHE udhe = new UsersDAOHE();
        user = udhe.findById(getUserId());

        Business bus;
        BusinessDAOHE bdhe = new BusinessDAOHE();
        bus = bdhe.findById(user.getBusinessId());
        //San pham ho so goc
        String proName = "";
        String manuName = "";
        String manuTel = "";
        String manuAdd = "";
        String manuEmail = "";
        String manuFax = "";
        String matchingTaget = "";
        String nameStaffProcess;
        Long staffProcessId;
        Date publishDate = null;
        String strReturn = ERROR_PERMISSION;
        if (createForm.getFileId() != null && createForm.getFileId() > 0l) {
            FilesDAOHE fdhe = new FilesDAOHE();
            createForm = fdhe.getFilesDetail(createForm.getFileId());
//            //hiepvv SDBS sau cong bo
            if (isEdits > 0L) {
                String fileSourceCode = createForm.getFileCode();
                Long fileSource = createForm.getFileId();
                staffProcessId = createForm.getStaffProcess();
                nameStaffProcess = createForm.getNameStaffProcess();
                //Thong tin ho so cu
                proName = createForm.getAnnouncement().getProductName();
                manuName = createForm.getAnnouncement().getManufactureName();
                manuAdd = createForm.getAnnouncement().getManufactureAddress();
                manuEmail = createForm.getAnnouncement().getManufactureName();
                manuTel = createForm.getAnnouncement().getManufactureTel();
                manuFax = createForm.getAnnouncement().getManufactureFax();
                matchingTaget = createForm.getAnnouncement().getMatchingTarget();
                publishDate = createForm.getAnnouncementReceiptPaperForm().getReceiptDate();
                //end
                ProcedureDAOHE pdaohe = new ProcedureDAOHE();
                Procedure p = new Procedure();
                try {
                    p = pdaohe.getProcedureByDescription(announcementFile05);
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
                    p = null;
//                    Logger.getLogger(FilesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                createForm = new FilesForm();
                if (p != null) {
                    createForm.setFileType(p.getProcedureId());
                }
                createForm.setFilesSourceID(fileSource);
                createForm.setFileSourceCode(fileSourceCode);
                createForm.setStaffProcess(staffProcessId);//them nguoi tham dinh ho so truoc day
                createForm.setNameStaffProcess(nameStaffProcess);//them nguoi tham dinh ho so truoc day
            }
//            if (!createForm.getFileType().equals(0L)) {
//                ProcedureDAOHE cdhe = new ProcedureDAOHE();
//                List lstTTHC = cdhe.getProcedureForChange(createForm.getFileType());
//                lstCategory = new ArrayList();
//                lstCategory.addAll(lstTTHC);
//                lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
//                getRequest().setAttribute("lstFileType", lstCategory);
//            }
        }

        if (createForm.getFileType() != null && createForm.getFileType() > 0l) {
            ProcedureDAOHE pdhe = new ProcedureDAOHE();
            CategoryDAOHE cdhe = new CategoryDAOHE();
            TechnicalStandardDAOHE tdhe = new TechnicalStandardDAOHE();
            FilesDAOHE fdhe = new FilesDAOHE();
            if (!fileType.equals(0L)) {
                createForm.setFileType(fileType);
            }
            Procedure tthc = pdhe.findById(createForm.getFileType());

            if (tthc != null) {

                //lstProductType = cdhe.findAllCategory("SP");//hieptq update 150415                
                if (checkEdit == 1) {
                    typeFee = cdhe.findTypeFee(tthc.getProcedureId());
                    lstProductType = cdhe.findAllCategoryByFee("SP", typeFee);
                } else {
                    lstProductType = cdhe.findAllCategoryByFee("SP", typeFee);
                }

                if (((Category) lstProductType.get(0)).getCategoryId() != -1L) {
                    lstProductType.add(0, new Category(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                }
                lstUnit = cdhe.findAllCategory("DVI");
                lstUnit.add(0, new Category(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                lstStandard = tdhe.findAllStandard();
                String lstDepts = convertToJSONData(lstStandard, "vietnameseName", "vietnameseName");
                getRequest().setAttribute("lstStandard", lstDepts);

                UserAttachsDAOHE uahe = new UserAttachsDAOHE();
                lstUserAttach = uahe.findAllUserAttach(getUserId());
                String lstUserAttachs = convertToJSONData(lstUserAttach, "attachName", "attachName");
                getRequest().setAttribute("lstUserAttach", lstUserAttachs);
                if (lstUserAttachs.trim().length() > 0) {
                    createForm.setCountUA(1L);
                } else {
                    createForm.setCountUA(0L);
                }

                getRequest().setAttribute("lstProductType", lstProductType);
                getRequest().setAttribute("lstUnit", lstUnit);
                String fileLst = tthc.getFileList();
                getRequest().setAttribute("fileList", com.viettel.common.util.StringUtils.removeHTML(fileLst));
                getRequest().setAttribute("agencyName", getDepartmentName());
                getRequest().setAttribute("fileNameFull", tthc.getName());
                // check edit hide excel tab
                getRequest().setAttribute("checkEdit", checkEdit);
                strReturn = tthc.getDescription();
                if (strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05) && isEdits == 1) {
                    strReturn = Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05_PAPER;
                }
                if (createForm.getAnnouncement() != null) {
                    if (createForm.getAnnouncement().getAnnouncementNo() != null
                            && createForm.getAnnouncement().getAnnouncementNo().length() > 0l
                            && isEdits != 1L) {
                        CategoryDAOHE ctdhe = new CategoryDAOHE();
                        Category cate = ctdhe.findCategoryByTypeAndCode("SP", "TPCN");
                        Category cateTL = ctdhe.findCategoryByTypeAndCode("SP", "TL");
                        List<Category> cate1 = ctdhe.findCategoryByTypeAndCodeNew("SP", "DBT");
                        String dbtId = "";
                        for (int i = 0; i < cate1.size(); i++) {
                            dbtId += cate1.get(i).getCategoryId().toString() + ";";
                        }

                        Long tpcnId = cate.getCategoryId();
                        Long tlId = cateTL.getCategoryId();
                        FeeDAOHE fdhe1 = new FeeDAOHE();
                        Fee findfee1 = fdhe1.findFeeByCode("TPDB");
                        Long priceTPDB = findfee1.getPrice();
                        Fee findfee2 = fdhe1.findFeeByCode("TPCN");
                        Long priceTPCN = findfee2.getPrice();
                        Fee findfee3 = fdhe1.findFeeByCode("TPK");
                        Long priceETC = findfee3.getPrice();
                        getRequest().setAttribute("dbtId", dbtId);
                        getRequest().setAttribute("tpcnId", tlId);
                        getRequest().setAttribute("tlId", tpcnId);
                        getRequest().setAttribute("tpcnId", tpcnId);
                        getRequest().setAttribute("priceTPCN", priceTPCN);
                        getRequest().setAttribute("priceTPDB", priceTPDB);
                        getRequest().setAttribute("priceETC", priceETC);
                        return strReturn;
                    }
                }
                if (strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN)
                        //hiepvv SDBS sau cong bo
                        || strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05_PAPER)
                        //hiepvv KS 4S
                        || strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR)) {
                    String announcementNoStr = fdhe.getReceiptNoNew(getUserId(), getUserLogin(), createForm.getFileType());
                    createForm.setAnnouncement(new AnnouncementForm());
                    createForm.getAnnouncement().setAnnouncementNo(announcementNoStr);
                    // thong tin doanh nghiep
                    createForm.getAnnouncement().setBusinessAddress(bus.getBusinessAddress());
                    createForm.getAnnouncement().setBusinessFax(bus.getBusinessFax());
                    createForm.getAnnouncement().setBusinessName(bus.getBusinessName());
                    createForm.getAnnouncement().setBusinessTelephone(bus.getBusinessTelephone());
                    createForm.getAnnouncement().setBusinessEmail(bus.getUserEmail());
                    createForm.getAnnouncement().setBusinessLicence(bus.getBusinessLicense());
                    // ho so cap lai 7-11
                    createForm.setReIssueForm(new ReIssueFormForm());
                    createForm.getReIssueForm().setBusinessName(bus.getBusinessName());
                    createForm.getReIssueForm().setIdentificationNumber(bus.getBusinessLicense());
                    createForm.getReIssueForm().setAddress(bus.getBusinessAddress());
                    createForm.getReIssueForm().setEmail(bus.getUserEmail());
                    createForm.getReIssueForm().setTelephone(bus.getBusinessTelephone());
                    createForm.getReIssueForm().setFax(bus.getBusinessFax());
//                    //San pham ho so goc
                    if (strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05)
                            || strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05_PAPER)) {
                        createForm.getAnnouncement().setProductName(proName);
                        createForm.getAnnouncement().setManufactureAddress(manuAdd);
                        createForm.getAnnouncement().setManufactureName(manuName);
                        createForm.getAnnouncement().setManufactureTel(manuTel);
                        createForm.getAnnouncement().setManufactureEmail(manuEmail);
                        createForm.getAnnouncement().setManufactureFax(manuFax);
                        createForm.getAnnouncement().setMatchingTarget(matchingTaget);
                        createForm.getAnnouncement().setPublishDate(publishDate);
                        createForm.setIsFee(1L);
                    }
                }
            }
            CategoryDAOHE ctdhe = new CategoryDAOHE();
            Category cate = ctdhe.findCategoryByTypeAndCode("SP", "TPCN");
            Category cateTL = ctdhe.findCategoryByTypeAndCode("SP", "TL");
            List<Category> cate1 = ctdhe.findCategoryByTypeAndCodeNew("SP", "DBT");
            String dbtId = "";
            for (int i = 0; i < cate1.size(); i++) {
                dbtId += cate1.get(i).getCategoryId().toString() + ";";
            }
//            Category cate1 = ctdhe.findCategoryByTypeAndCode("SP", "DBT");
//            Long dbtId = cate1.getCategoryId();
            Long tpcnId = cate.getCategoryId();
            Long tlId = cateTL.getCategoryId();
            FeeDAOHE fdhe1 = new FeeDAOHE();
            Fee findfee1 = fdhe1.findFeeByCode("TPDB");
            Long priceTPDB = findfee1.getPrice();
            Fee findfee2 = fdhe1.findFeeByCode("TPCN");
            Long priceTPCN = findfee2.getPrice();
            Fee findfee3 = fdhe1.findFeeByCode("TPK");
            Long priceETC = findfee3.getPrice();
            getRequest().setAttribute("dbtId", dbtId);
            getRequest().setAttribute("tpcnId", tpcnId);
            getRequest().setAttribute("tlId", tlId);
            getRequest().setAttribute("priceTPCN", priceTPCN);
            getRequest().setAttribute("priceTPDB", priceTPDB);
            getRequest().setAttribute("priceETC", priceETC);
        }
        return strReturn;
    }

    public String toCreateFilePage4AA() {
        return "announcementFile05";
    }

    /**
     * Xem chi tiet ho so
     *
     * @return
     */
    public String loadFileView() {//

        String strReturn = ERROR_PERMISSION;
        String agencyName = "";
        if (createForm.getFileId() != null && createForm.getFileId() > 0l) {
            fileSourceID = createForm.getFileId();
            Long viewType = -1L;

            if (createForm.getViewType() == null) {
                createForm.setViewType(-1L);
            } else {
                viewType = createForm.getViewType();
            }
            FilesDAOHE fdhe = new FilesDAOHE();
            createForm = fdhe.getFilesDetail(createForm.getFileId());
            createForm.setViewType(viewType);
            createFormOriginal = fdhe.getFilesDetail(createForm.getFileId());
            if (createFormOriginal != null) {
                if (createFormOriginal.getAnnouncement() != null) {
                    if (createFormOriginal.getAnnouncement().getAssessmentMethod() != null) {
                        createFormOriginal.getAnnouncement().setAssessmentMethod(createFormOriginal.getAnnouncement().getAssessmentMethod().replace("\n", "nl"));
                        createFormOriginal.getAnnouncement().setAssessmentMethod(createFormOriginal.getAnnouncement().getAssessmentMethod().replace("\r", ""));
                    }
                    if (createFormOriginal.getAnnouncement().getMatchingTarget() != null) {
                        createFormOriginal.getAnnouncement().setMatchingTarget(createFormOriginal.getAnnouncement().getMatchingTarget().replace(";", "nl"));
                        createFormOriginal.getAnnouncement().setMatchingTarget(createFormOriginal.getAnnouncement().getMatchingTarget().replace("\n", ""));
                        createFormOriginal.getAnnouncement().setMatchingTarget(createFormOriginal.getAnnouncement().getMatchingTarget().replace("\r", ""));
                    }

                }
                if (createFormOriginal.getDetailProduct() != null) {
                    if (createFormOriginal.getDetailProduct().getOtherTarget() != null) {
                        createFormOriginal.getDetailProduct().setOtherTarget(createFormOriginal.getDetailProduct().getOtherTarget().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setOtherTarget(createFormOriginal.getDetailProduct().getOtherTarget().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getComponents() != null) {
                        createFormOriginal.getDetailProduct().setComponents(createFormOriginal.getDetailProduct().getComponents().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setComponents(createFormOriginal.getDetailProduct().getComponents().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getTimeInUse() != null) {
                        createFormOriginal.getDetailProduct().setTimeInUse(createFormOriginal.getDetailProduct().getTimeInUse().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setTimeInUse(createFormOriginal.getDetailProduct().getTimeInUse().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getUseage() != null) {
                        createFormOriginal.getDetailProduct().setUseage(createFormOriginal.getDetailProduct().getUseage().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setUseage(createFormOriginal.getDetailProduct().getUseage().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getObjectUse() != null) {
                        createFormOriginal.getDetailProduct().setObjectUse(createFormOriginal.getDetailProduct().getObjectUse().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setObjectUse(createFormOriginal.getDetailProduct().getObjectUse().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getGuideline() != null) {
                        createFormOriginal.getDetailProduct().setGuideline(createFormOriginal.getDetailProduct().getGuideline().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setGuideline(createFormOriginal.getDetailProduct().getGuideline().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getPackateMaterial() != null) {
                        createFormOriginal.getDetailProduct().setPackateMaterial(createFormOriginal.getDetailProduct().getPackateMaterial().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setPackateMaterial(createFormOriginal.getDetailProduct().getPackateMaterial().replace("\r", ""));
                    }

//                    if (createFormOriginal.getDetailProduct().getPackageRecipe() != null) {
//                        createFormOriginal.getDetailProduct().setPackageRecipe(createFormOriginal.getDetailProduct().getPackageRecipe().replace("\n", "nl"));
//                        createFormOriginal.getDetailProduct().setPackageRecipe(createFormOriginal.getDetailProduct().getPackageRecipe().replace("\r", ""));
//                    }
                    if (createFormOriginal.getDetailProduct().getProductionProcess() != null) {
                        createFormOriginal.getDetailProduct().setProductionProcess(createFormOriginal.getDetailProduct().getProductionProcess().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setProductionProcess(createFormOriginal.getDetailProduct().getProductionProcess().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getCounterfeitDistinctive() != null) {
                        createFormOriginal.getDetailProduct().setCounterfeitDistinctive(createFormOriginal.getDetailProduct().getCounterfeitDistinctive().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setCounterfeitDistinctive(createFormOriginal.getDetailProduct().getCounterfeitDistinctive().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getOrigin() != null) {
                        createFormOriginal.getDetailProduct().setOrigin(createFormOriginal.getDetailProduct().getOrigin().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setOrigin(createFormOriginal.getDetailProduct().getOrigin().replace("\r", ""));
                    }

                }
                if (createFormOriginal.getTestRegistration() != null) {
                }
            }

            createFormClone = fdhe.getCloneFilesDetail(createForm.getFileId());
            if (createFormClone != null) {
                if (createFormClone.getAnnouncement() != null) {
                    if (createFormClone.getAnnouncement().getAssessmentMethod() != null) {
                        createFormClone.getAnnouncement().setAssessmentMethod(createFormClone.getAnnouncement().getAssessmentMethod().replace("\n", "nl"));
                        createFormClone.getAnnouncement().setAssessmentMethod(createFormClone.getAnnouncement().getAssessmentMethod().replace("\r", ""));
                    }
                    if (createFormClone.getAnnouncement().getMatchingTarget() != null) {
                        createFormClone.getAnnouncement().setMatchingTarget(createFormClone.getAnnouncement().getMatchingTarget().replace("\n", "nl"));
                        createFormClone.getAnnouncement().setMatchingTarget(createFormClone.getAnnouncement().getMatchingTarget().replace("\r", ""));
                    }

                }
                if (createFormClone.getDetailProduct() != null) {
                    if (createFormClone.getDetailProduct().getOtherTarget() != null) {
                        createFormClone.getDetailProduct().setOtherTarget(createFormClone.getDetailProduct().getOtherTarget().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setOtherTarget(createFormClone.getDetailProduct().getOtherTarget().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getComponents() != null) {
                        createFormClone.getDetailProduct().setComponents(createFormClone.getDetailProduct().getComponents().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setComponents(createFormClone.getDetailProduct().getComponents().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getTimeInUse() != null) {
                        createFormClone.getDetailProduct().setTimeInUse(createFormClone.getDetailProduct().getTimeInUse().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setTimeInUse(createFormClone.getDetailProduct().getTimeInUse().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getUseage() != null) {
                        createFormClone.getDetailProduct().setUseage(createFormClone.getDetailProduct().getUseage().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setUseage(createFormClone.getDetailProduct().getUseage().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getObjectUse() != null) {
                        createFormClone.getDetailProduct().setObjectUse(createFormClone.getDetailProduct().getObjectUse().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setObjectUse(createFormClone.getDetailProduct().getObjectUse().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getGuideline() != null) {
                        createFormClone.getDetailProduct().setGuideline(createFormClone.getDetailProduct().getGuideline().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setGuideline(createFormClone.getDetailProduct().getGuideline().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getPackateMaterial() != null) {
                        createFormClone.getDetailProduct().setPackateMaterial(createFormClone.getDetailProduct().getPackateMaterial().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setPackateMaterial(createFormClone.getDetailProduct().getPackateMaterial().replace("\r", ""));
                    }

//                    if (createFormClone.getDetailProduct().getPackageRecipe() != null) {
//                        createFormClone.getDetailProduct().setPackageRecipe(createFormClone.getDetailProduct().getPackageRecipe().replace("\n", "nl"));
//                        createFormClone.getDetailProduct().setPackageRecipe(createFormClone.getDetailProduct().getPackageRecipe().replace("\r", ""));
//                    }
                    if (createFormClone.getDetailProduct().getProductionProcess() != null) {
                        createFormClone.getDetailProduct().setProductionProcess(createFormClone.getDetailProduct().getProductionProcess().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setProductionProcess(createFormClone.getDetailProduct().getProductionProcess().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getCounterfeitDistinctive() != null) {
                        createFormClone.getDetailProduct().setCounterfeitDistinctive(createFormClone.getDetailProduct().getCounterfeitDistinctive().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setCounterfeitDistinctive(createFormClone.getDetailProduct().getCounterfeitDistinctive().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getOrigin() != null) {
                        createFormClone.getDetailProduct().setOrigin(createFormClone.getDetailProduct().getOrigin().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setOrigin(createFormClone.getDetailProduct().getOrigin().replace("\r", ""));
                    }

                }
            }

            DepartmentDAOHE ddhe = new DepartmentDAOHE();
            Department dept = ddhe.getDeptByUserId(createForm.getUserCreateId());
            if (dept != null) {
                agencyName = dept.getDeptName();
            }
        }
        if (createForm.getFileType() != null && createForm.getFileType() > 0l) {
            ProcedureDAOHE pdhe = new ProcedureDAOHE();
            CategoryDAOHE cdhe = new CategoryDAOHE();
            Procedure tthc = pdhe.findById(createForm.getFileType());
            if (tthc != null) {
                //binhnt 060215 thêm combobox chọn đơn vị xử lý hồ sơ
                ProcedureDepartmentDAOHE pddaohe = new ProcedureDepartmentDAOHE();
                List lstCQXL = pddaohe.getAllProcedureDepartmentByProcedureId(createForm.getFileType());
                lstDeptProcessFile = new ArrayList();
                lstDeptProcessFile.addAll(lstCQXL);
                lstDeptProcessFile.add(0, new ProcedureDepartment(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                getRequest().setAttribute("lstDeptProcessFile", lstDeptProcessFile);
                //binhnt add
                //getLstVersionFiles();
                FilesDAOHE filesDaohe = new FilesDAOHE();
                lstOldVersion = new ArrayList();
                lstOldVersion.addAll(filesDaohe.getLstOldVersionFiles(createForm.getFileId()));
                lstOldVersion.add(0, new FilesForm(Constants.COMBOBOX_HEADER_VALUE, "--Chọn--"));
                lstOldVersion.add(1, new FilesForm(createForm.getFileId(), "Lần sửa đổi gần nhất"));
                getRequest().setAttribute("lstOldVersion", lstOldVersion);
                //
                lstProductType = cdhe.findAllCategory("SP");
                lstUnit = cdhe.findAllCategory("DVI");
                getRequest().setAttribute("lstProductType", lstProductType);
                getRequest().setAttribute("lstUnit", lstUnit);
                String fileLst = tthc.getFileList();
                getRequest().setAttribute("fileList", com.viettel.common.util.StringUtils.removeHTML(fileLst));
                getRequest().setAttribute("agencyName", agencyName);

                UsersDAOHE udaohe = new UsersDAOHE();
                List<String> lstStaff = new ArrayList<String>();
                lstStaff.add(Constants.POSITION.VFA_CV);
                lstStaff.add(Constants.POSITION.NV);
                if (udaohe.checkUserByLstPosition(getDepartmentId(), getUserId(), lstStaff)) {//la chuyen vien
                    List lstLDP = udaohe.getAllLeaderOfStaffInOffice(getDepartmentId());
                    if (lstLDP != null) {
                        List lstLeaderOfStaffOnGrid = new ArrayList();
                        lstLeaderOfStaffOnGrid.addAll(lstLDP);
                        lstLeaderOfStaffOnGrid.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                        getRequest().setAttribute("lstLeaderOfStaff", lstLeaderOfStaffOnGrid);
                    }
                } else {//la pho phong - lay danh sach ldc va truong phong
                    List lstLDC = udaohe.getLeaderByUser(getDepartmentId());
                    List lstLDP = udaohe.getTruongPhong(getDepartmentId());
                    List lstLeaderOfStaffOnGrid = new ArrayList();
                    if (lstLDC != null) {
                        lstLeaderOfStaffOnGrid.addAll(lstLDC);
                    }
                    if (lstLDP != null) {
                        lstLeaderOfStaffOnGrid.addAll(lstLDP);
                    }
                    lstLeaderOfStaffOnGrid.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                    getRequest().setAttribute("lstLeaderOfStaff", lstLeaderOfStaffOnGrid);
                }
                if (createForm.getAgencyId() != null) {
                    List lstLDC = udaohe.getLeader(createForm.getAgencyId());
                    lstLeader = new ArrayList();
                    if (lstLDC != null) {
                        lstLeader.addAll(lstLDC);
                    }
                    lstLeader.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                    getRequest().setAttribute("lstLeader", lstLeader);
                } else {
                    lstLeader = new ArrayList();
                    lstLeader.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                    getRequest().setAttribute("lstLeader", lstLeader);
                }
                String viewTypeDialog = getRequest().getParameter("viewTypeDialog");
                if (viewTypeDialog != null && !"".equals(viewTypeDialog) && "1".equals(viewTypeDialog)) {
                    strReturn = tthc.getDescription() + "DialogView";
                } else {
                    strReturn = tthc.getDescription() + "View";
                }
            }
        }
        try {
            //Check validate ky CA
            if (createForm.getContentSigned() != null) {
                String validCAStatus = "";
                if (CommonUtils.checkSecurityPublishCA(createForm.getFileId(), createForm.getContentSigned())) {
                    String contentSigned = createForm.getContentSigned();
                    KeyInfo keyInfo = CommonUtils.validateContentSigned(contentSigned);
                    if (keyInfo != null) {
                        try {
                            CommonUtils.getUserSigned(keyInfo);
                            validCAStatus = "OK";
                        } catch (CertificateExpiredException | CertificateNotYetValidException ex) {
                            LogUtil.addLog(ex);//binhnt sonar a160901
//                            log.error(expiredEx);
                        }
                    } else {
                        validCAStatus = "NG";
                    }
                } else {
                    validCAStatus = "NG";
                }
                getRequest().setAttribute("validCAStatus", validCAStatus);
            }
            // Get Role
            List<Roles> roles = getListRolesByUser();
            String lstRole = "";
            if (roles != null && roles.size() > 0) {
                for (int i = 0; i < roles.size(); i++) {
                    lstRole += roles.get(i).getRoleCode() + ";";
                }
            }
            getRequest().setAttribute("lstRole", lstRole);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(e.getMessage());
        }
        /*
         if (createForm.getAnnouncement() != null) {
         getBarcode(createForm);
         }
         */
        return strReturn;
    }

    /**
     * lấy hồ sơ
     *
     * @return
     */
    public String loadFile() {
        //List resultMessage = new ArrayList();
        List items = new ArrayList();
        if (createForm.getFileId() != null && createForm.getFileId() > 0l) {
            //FilesDAOHE fdhe = new FilesDAOHE();
            //createForm = fdhe.getFilesDetail(createForm.getFileId());
            try {
                Document document = CommonUtils.buildAllPublishDocument(createForm.getFileId());
                createForm.setContentXml(com.viettel.common.util.StringUtils.escapeHtml(CommonUtils.convertDocument2String(document)));
                items.add(createForm.getContentXml());
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                log.error(e.getMessage());
            }
        }
        //jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setItems(items);
        return GRID_DATA;
    }

    /**
     * danh sách các đơn vị có thể nộp hồ sơ
     *
     * @return
     */
    public String getListOfAgency() {
        getGridInfo();
        FilesDAOHE fdhe = new FilesDAOHE();
        Long provinceId = getProvinceId();
        GridResult gr = fdhe.getAgencyToSendFile(createForm.getFileId(), provinceId, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /*
     * Doanh nghiep chon don vi de xu ly ho so
     */
    public String onSelectFlow() throws JAXBException {
        FilesDAOHE fdhe = new FilesDAOHE();
        boolean bReturn = fdhe.assignFileToDept(createForm.getFileId(), createForm.getDeptId(), getUserId(), getUserName(), getBusinessId(), getBusinessName());
        List resultMessage = new ArrayList();
        if (bReturn == true) {
            resultMessage.add("1");
            resultMessage.add("Nộp hồ sơ thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Nộp hồ sơ thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        //String xml = fdhe.prepareAnnouceHandling(createForm.getFileId());
        return GRID_DATA;
    }

    /*
     * Tra ve danh sach chi tieu chu yeu cua ho so
     */
    public String loadMainlyTarget() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List lst = fdhe.getMainlyTargetOfFile(createForm.getFileId());
        jsonDataGrid.setItems(lst);
        return GRID_DATA;
    }

    public String onValidate() {
        FilesDAOHE fdhe = new FilesDAOHE();
        String error = fdhe.validateFiles(searchForm.getFileId());
        List resultMessage = new ArrayList();
        if (error == null) {
            resultMessage.add("1");
            resultMessage.add("Hồ sơ validate thành công");
        } else {
            resultMessage.add("2");
            resultMessage.add(error);
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /*
     * TRa ve danh sach chi tieu cua san pham
     */
    public String loadProductTarget() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List lst = fdhe.getProductTargetOfFile(createForm.getFileId());
        jsonDataGrid.setItems(lst);
        return GRID_DATA;
    }

    /*
     * Tra ve danh sach attach cua ho so
     */
    public String loadAttachs() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List lst = fdhe.getAttachsOfFile(createForm.getFileId());
        jsonDataGrid.setItems(lst);

        String idSession = (String) getRequest().getSession().getAttribute("idSession");
        if (idSession == null) {
            idSession = "";
        }
        for (int i = 0; i < lst.size(); i++) {
            VoAttachs bo = (VoAttachs) lst.get(i);
            idSession += bo.getAttachId().toString();
            idSession += ";";
        }
        getRequest().getSession().setAttribute("idSession", idSession);

        return GRID_DATA;
    }

    /*
     * Hiepvv 1403
     * Tra ve danh sach attach cua ho so sdbs sau cong bo
     */
    public String loadAttachs2() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List lst = fdhe.getAttachsOfFileSDBS(createForm.getFileId());
        jsonDataGrid.setItems(lst);

        String idSession = (String) getRequest().getSession().getAttribute("idSession");
        if (idSession == null) {
            idSession = "";
        }
        for (int i = 0; i < lst.size(); i++) {
            VoAttachs bo = (VoAttachs) lst.get(i);
            idSession += bo.getAttachId().toString();
            idSession += ";";
        }
        getRequest().getSession().setAttribute("idSession", idSession);

        return GRID_DATA;
    }

    /*
     * Tra ve danh sach ke hoach quan ly chat luong cua ho so
     */
    public String loadQualityControls() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List lst = fdhe.getQualityControlOfFile(createForm.getFileId());
        jsonDataGrid.setItems(lst);
        return GRID_DATA;
    }

    /*
     * Tra ve danh sach san pham dang ky trong ho so
     */
    public String loadProductInFiles() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List lst = fdhe.getProductOfFile(createForm.getFileId());
        jsonDataGrid.setItems(lst);
        return GRID_DATA;
    }

    /*
     * Vao trang bo sung ho so cua doanh nghiep
     */
    public String toBusinessAdditionPage() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        return businessAdditionPage;
    }

    /*
     * Vao trang danh sach ho so cua doanh nghiep
     */
    public String toBusinessListPage() {
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setStatus(-1l);
        }
        isEdit = false;
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        CategoryDAOHE che = new CategoryDAOHE();
        lstProductType = che.findAllCategory("SP");
        getRequest().setAttribute("lstProductType", lstProductType);
//        try {
//            Date sysDate = cdhe.getSysdate();
//            getRequest().setAttribute("sysDate", sysDate);
//        } catch (Exception ex) {
//            Logger.getLogger(FilesDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        getRequest().setAttribute("lstProductType", lstProductType);
        BusinessDAOHE bdhe = new BusinessDAOHE();
        Business bus = bdhe.findById(getBusinessId());

        if (bus.getIsCa() != null && bus.getIsCa() == 1) {
            getRequest().setAttribute("isCa", bus.getIsCa());
        } else {
            getRequest().setAttribute("isCa", 0);
        }
        DepartmentDAOHE dphe = new DepartmentDAOHE();
        Department dept = dphe.findByDeptCode("ATTP");
        Long AgencyId = dept.getDeptId();
        String AgencyName = dept.getDeptName();
        getRequest().setAttribute("AgencyId", AgencyId);
        getRequest().setAttribute("AgencyName", AgencyName);
        return businessListPage;
    }

    /**
     * Tim kiem ho so cua doanh nghiep
     *
     * @return
     */
    public String onSearchBusinessFiles() {
        getGridInfo();

        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();

        CategoryDAOHE cdhe = new CategoryDAOHE();
        lstProductType = cdhe.findAllCategory("SP");
        getRequest().setAttribute("lstProductType", lstProductType);

        if (searchForm == null) {
            searchForm = new FilesForm();
        }

        searchForm.setDeptId(getBusinessId());
        Long deptId = getBusinessId();
        GridResult gr;
        if (isEdit) {
            gr = fdhe.searchBusinessFilesNoSDBSAnd4Star(searchForm, start, count, sortField, deptId);
        } else {
            gr = fdhe.searchBusinessFiles(searchForm, start, count, sortField, deptId);
        }
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /*
     * Xu ly them moi, bo sung ho so savefiles
     */
    public String onInsert() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        FilesDAOHE fdhe = new FilesDAOHE();
        ProcedureDAOHE pdhe = new ProcedureDAOHE();
        Procedure tthc = pdhe.findById(createForm.getFileType());
        Long fee = tthc.getFee();

        try {
            Long filesId = createForm.getFileId();
            Boolean isCreate = true;
            if (filesId == null) {//check la update hay them moi
                createForm.setCreateDate(getSysdate());

                createForm.setUserCreateId(getUserId());
                createForm.setUserCreateName(getUserName());

                createForm.setDeptId(getBusinessId());
                createForm.setDeptName(getBusinessName());
                createForm.setBusinessName(getBusinessName());

                createForm.setFeeFile(fee);

                createForm.setIsDownload(Constants.ACTIVE_STATUS.ACTIVE);//141215u binhnt53

                if (getBusinessId() != null) {//141214u binhnt53 bo sung co quan chu quan.
                    BusinessDAOHE busdaohe = new BusinessDAOHE();
                    Business busbo = busdaohe.findById(getBusinessId());
                    if (busbo != null && busbo.getGoverningBody() != null) {
                        createForm.setGoverningBody(busbo.getGoverningBody());
                    }
                }
            } else {
                isCreate = false;
                createForm.setModifyDate(getSysdate());
            }

            Procedure fileType = pdhe.findById(createForm.getFileType());
            if (fileType != null) {
                createForm.setFileTypeName(fileType.getName());
            }

            createForm.setStatus(Constants.FILE_STATUS.NEW_CREATE);

            if (createForm.getStatus() != null
                    && (createForm.getStatus().equals(Constants.FILE_STATUS.NEW_CREATE)
                    || createForm.getStatus().equals(Constants.FILE_STATUS.NEW)
                    || createForm.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT)
                    || createForm.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT_TO_ADD)
                    || createForm.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD))) {//check role
                if (isCreate) {//insert Files
                    Files fileSaved = fdhe.saveFiles(createForm);
                    if (fileSaved == null) {
                        resultMessage.clear();
                        resultMessage.add("3");
                        resultMessage.add("Thêm mới hồ sơ không thành công");

                    } else {
                        resultMessage.add("1");
                        resultMessage.add("Thêm mới hồ sơ thành công");
                        // Log
                        EventLogDAOHE edhe = new EventLogDAOHE();
                        edhe.insertEventLog("Thêm mới hồ sơ", "Thêm mới hồ sơ mã " + fileSaved.getFileCode(), getRequest());
                    }
                } else {// update File

                    FilesDAOHE fdaohe = new FilesDAOHE();
                    Files files = fdaohe.findById(filesId);
                    if (files != null) {// update File
                        if (files.getStatus() != null
                                && (files.getStatus().equals(Constants.FILE_STATUS.NEW_CREATE)
                                || files.getStatus().equals(Constants.FILE_STATUS.NEW)
                                || files.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT)
                                || files.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT_TO_ADD)
                                || files.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD))
                                && files.getUserCreateId() != null
                                && files.getUserCreateId().equals(getUserId())) {
                            Files fileSaved = fdhe.saveFiles(createForm);

                            if (fileSaved == null) {
                                resultMessage.clear();
                                resultMessage.add("3");
                                resultMessage.add("Cập nhật hồ sơ không thành công");

                            } else {
                                resultMessage.add("1");
                                resultMessage.add("Cập nhật hồ sơ thành công");
                                // Log
                                EventLogDAOHE edhe = new EventLogDAOHE();
                                edhe.insertEventLog("Cập nhật hồ sơ", "Cập nhật hồ sơ mã " + fileSaved.getFileCode(), getRequest());
                            }

                        }
                    } else {
                        resultMessage.clear();
                        log.error("Lỗi hệ thống: Phân quyền xử lý hồ sơ: " + files.getFileCode());
                        resultMessage.add("3");
                        resultMessage.add("Lưu hồ sơ không thành công. Bạn không có quyền cập nhật hồ sơ!");
                    }
                }
            } else {
                resultMessage.clear();
                resultMessage.add("3");
                resultMessage.add("Lưu hồ sơ không thành công: Bạn không được phép xử lý hồ sơ!");
            }
        } catch (Exception ex) {
//            log.error(ex.getMessage());
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.clear();
            resultMessage.add("3");
            resultMessage.add("Lưu hồ sơ không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /*
     * Huy ho so
     */
    public String onDelete() throws Exception {
        List resultMessage = new ArrayList();
        try {
            FilesDAOHE fdhe = new FilesDAOHE();
            int nSuccess = 0;
            int nError = 0;
            String sid = "";
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                FilesForm form = lstItemOnGrid.get(i);
                if (form != null && form.getFileId() != null && form.getFileId() != 0D) {
                    int nReturn = fdhe.deleteFile(getUserId(), getBusinessId(), form.getFileId());
                    sid += form.getFileId() + ",";
                    if (nReturn == 0) {
                        nSuccess++;
                    } else {
                        nError++;
                    }
                }
            }
//            String strAlert = "Hủy hồ sơ thành công";
            String strAlert = "Hủy hồ sơ thành công, có " + nSuccess + " hồ sơ bị hủy và " + nError + " hồ sơ không được phép hủy";
            resultMessage.add("1");
            resultMessage.add(strAlert);
            EventLogDAOHE edhe = new EventLogDAOHE();
            edhe.insertEventLog("Thêm mới hồ sơ", "Hủy hồ sơ có id=" + sid, getRequest());

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Hủy hồ sơ không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return "gridData";
    }

    /*
     * signing trình ký giay
     */
    //<editor-fold desc="TAO VA TRINH KY GIAY CHUNG NHAN">
    public String onSearchFileToSigning() {
        getGridInfo();
        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
        GridResult gr = fdhe.searchFilesToProcess(searchForm, getDepartmentId(), getUserId(), 4l, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * tìm kiếm file đã ký
     *
     * @return
     */
    public String onSearchFileToSigned() {
        getGridInfo();
        FilesDAOHE fdhe = new FilesDAOHE();
        GridResult gr = fdhe.searchFilesToSign(searchForm, getDepartmentId(), getUserId(), start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * vào trang xem xét
     *
     * @return
     */
    public String toSigningPage() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        return SIGNING_PAGE;
    }

    /**
     * gui ky
     *
     * @return
     */
    public String onSendToSign() {
        FilesDAOHE fdhe = new FilesDAOHE();
        boolean bReturn = fdhe.onSendToSign(signingForm, getUserId(), getUserName());
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
//        EventLogDAOHE edhe = new EventLogDAOHE();
//        edhe.insertEventLog("Xem xét hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());

        return GRID_DATA;
    }

    /**
     * action ky
     *
     * @return
     */
    public String onSign() {
        FilesDAOHE fdhe = new FilesDAOHE();
        boolean bReturn = fdhe.onSign(signedForm, getUserId(), getUserName());
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
//        EventLogDAOHE edhe = new EventLogDAOHE();
//        edhe.insertEventLog("Xem xét hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());

        return GRID_DATA;
    }

    /**
     * tu choi tiep nhan
     *
     * @return
     */
    public String onReject() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List resultMessage = new ArrayList();
        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        if (check) {

            boolean bReturn = fdhe.onReject(rejectForm, getUserId(), getUserName());

            if (bReturn) {
                resultMessage.add("1");
                resultMessage.add("Lưu dữ liệu thành công");
            } else {
                resultMessage.add("3");
                resultMessage.add("Lưu dữ liệu không thành công");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");
        }

        jsonDataGrid.setItems(resultMessage);
//        EventLogDAOHE edhe = new EventLogDAOHE();
//        edhe.insertEventLog("Xem xét hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());

        return GRID_DATA;
    }

    /**
     * load page soan thao giay tiep nhan cong bo hop quy
     *
     * @return
     */
    public String loadProvidePage() {
        String strReturn = ERROR_PERMISSION;
        if (provideForm.getFileId() != null && provideForm.getFileId() > 0l) {
            FilesDAOHE fdhe = new FilesDAOHE();
            provideForm = fdhe.getFilesDetail(provideForm.getFileId());
        }
        if (provideForm.getFileType() != null && provideForm.getFileType() > 0l) {
            ProcedureDAOHE pdhe = new ProcedureDAOHE();
            CategoryDAOHE cdhe = new CategoryDAOHE();
            Procedure tthc = pdhe.findById(provideForm.getFileType());
            if (tthc != null) {
                strReturn = tthc.getDescription() + "Provide";
                List lstNation = cdhe.findAllCategory("NATION");
                getRequest().setAttribute("lstNation", lstNation);
                if (Constants.FILE_STATUS.APPROVED.equals(provideForm.getStatus())) {
                    if (provideForm.getAnnouncementId() != null && provideForm.getAnnouncementId() > 0L) {
                        provideForm.getAnnouncementReceiptPaperForm().setBusinessName(provideForm.getAnnouncement().getBusinessName());
                        provideForm.getAnnouncementReceiptPaperForm().setProductName(provideForm.getAnnouncement().getProductName());
                        provideForm.getAnnouncementReceiptPaperForm().setManufactureName(provideForm.getAnnouncement().getManufactureName());
                        provideForm.getAnnouncementReceiptPaperForm().setEmail(provideForm.getAnnouncement().getBusinessEmail());
                        provideForm.getAnnouncementReceiptPaperForm().setFax(provideForm.getAnnouncement().getBusinessFax());
                        provideForm.getAnnouncementReceiptPaperForm().setTelephone(provideForm.getAnnouncement().getBusinessTelephone());
                        provideForm.getAnnouncementReceiptPaperForm().setNationName(provideForm.getAnnouncement().getNationName());
                    }
                    if (provideForm.getTestRegistrationId() != null && provideForm.getTestRegistrationId() > 0L) {
                        provideForm.getConfirmImportSatistPaperForm().setTestAgencyName(provideForm.getTestRegistration().getTestAgency());
                        provideForm.getConfirmImportSatistPaperForm().setTestAdd(provideForm.getTestRegistration().getTestAdd());
                        provideForm.getConfirmImportSatistPaperForm().setExportBusinessName(provideForm.getTestRegistration().getExportBusinessName());
                        provideForm.getConfirmImportSatistPaperForm().setExportBusinessAdd(provideForm.getTestRegistration().getExportBusinessAdd());
                        provideForm.getConfirmImportSatistPaperForm().setExportBusinessMail(provideForm.getTestRegistration().getExportBusinessMail());
                        provideForm.getConfirmImportSatistPaperForm().setExportBusinessTel(provideForm.getTestRegistration().getExportBusinessTel());
                        provideForm.getConfirmImportSatistPaperForm().setExportBusinessFax(provideForm.getTestRegistration().getExportBusinessFax());
                        provideForm.getConfirmImportSatistPaperForm().setExportContractCode(provideForm.getTestRegistration().getExportContractCode());
                        provideForm.getConfirmImportSatistPaperForm().setExportContractDate(provideForm.getTestRegistration().getExportContractDate());
                        provideForm.getConfirmImportSatistPaperForm().setExportLadingCode(provideForm.getTestRegistration().getExportLadingCode());
                        provideForm.getConfirmImportSatistPaperForm().setExportLadingDate(provideForm.getTestRegistration().getExportLadingDate());
                        provideForm.getConfirmImportSatistPaperForm().setExportPort(provideForm.getTestRegistration().getExportPort());
                        provideForm.getConfirmImportSatistPaperForm().setImportBusinessName(provideForm.getTestRegistration().getImportBusinessName());
                        provideForm.getConfirmImportSatistPaperForm().setImportBusinessAddress(provideForm.getTestRegistration().getImportBusinessAddress());
                        provideForm.getConfirmImportSatistPaperForm().setImportBusinessEmail(provideForm.getTestRegistration().getImportBusinessEmail());
                        provideForm.getConfirmImportSatistPaperForm().setImportBusinessTel(provideForm.getTestRegistration().getImportBusinessTel());
                        provideForm.getConfirmImportSatistPaperForm().setImportBusinessFax(provideForm.getTestRegistration().getImportBusinessFax());
                        provideForm.getConfirmImportSatistPaperForm().setImportPort(provideForm.getTestRegistration().getImportPort());
                        provideForm.getConfirmImportSatistPaperForm().setImportDate(provideForm.getTestRegistration().getImportDate());
                        provideForm.getConfirmImportSatistPaperForm().setProductName(provideForm.getTestRegistration().getProductName());
                        provideForm.getConfirmImportSatistPaperForm().setProductDescription(provideForm.getTestRegistration().getProductDescription());
                        provideForm.getConfirmImportSatistPaperForm().setProductCode(provideForm.getTestRegistration().getProductCode());
                        provideForm.getConfirmImportSatistPaperForm().setProductOrigin(provideForm.getTestRegistration().getProductOrigin());
                        provideForm.getConfirmImportSatistPaperForm().setProductAmount(provideForm.getTestRegistration().getProductAmount());
                        provideForm.getConfirmImportSatistPaperForm().setProductWeight(provideForm.getTestRegistration().getProductWeight());
                        provideForm.getConfirmImportSatistPaperForm().setProductValue(provideForm.getTestRegistration().getProductValue());
                        provideForm.getConfirmImportSatistPaperForm().setGatheringAdd(provideForm.getTestRegistration().getGatheringAdd());
                        provideForm.getConfirmImportSatistPaperForm().setTestDate(provideForm.getTestRegistration().getTestDate());
                        provideForm.getConfirmImportSatistPaperForm().setBusinessRepresent(provideForm.getTestRegistration().getBusinessRepresent());
                        provideForm.getConfirmImportSatistPaperForm().setBusinessSignAdd(provideForm.getTestRegistration().getBusinessSignAdd());
                        provideForm.getConfirmImportSatistPaperForm().setBusinessSigndate(provideForm.getTestRegistration().getBusinessSigndate());
                        provideForm.getConfirmImportSatistPaperForm().setAgencyRepresent(provideForm.getTestRegistration().getAgencyRepresent());
                        provideForm.getConfirmImportSatistPaperForm().setAgencySignAdd(provideForm.getTestRegistration().getAgencySignAdd());
                        provideForm.getConfirmImportSatistPaperForm().setAgencySigndate(provideForm.getTestRegistration().getAgencySigndate());
                        provideForm.getConfirmImportSatistPaperForm().setStandardTargetNo(provideForm.getTestRegistration().getStandardTargetNo());
                        provideForm.getConfirmImportSatistPaperForm().setStandardTargetDate(provideForm.getTestRegistration().getStandardTargetDate());
                        provideForm.getConfirmImportSatistPaperForm().setReleaseDocumentNo(provideForm.getTestRegistration().getReleaseDocumentNo());
                        provideForm.getConfirmImportSatistPaperForm().setReleaseDocumentDate(provideForm.getTestRegistration().getReleaseDocumentDate());
                    }
                }
                if (Constants.FILE_STATUS.REJECT.equals(provideForm.getStatus())) {
                    if (provideForm.getAnnouncementId() != null && provideForm.getAnnouncementId() > 0L) {
                        AnnouncementReceiptPaperDAOHE arphe = new AnnouncementReceiptPaperDAOHE();
                        AnnouncementReceiptPaperForm bo = new AnnouncementReceiptPaperForm(arphe.getById("announcementReceiptPaperId", provideForm.getAnnouncementReceiptPaperId()));
                        provideForm.setAnnouncementReceiptPaperForm(bo);
                    }
                    if (provideForm.getConfirmImportSatistPaperId() != null && provideForm.getConfirmImportSatistPaperId() > 0L) {
                        ConfirmImportSatistPaperDAOHE cisphe = new ConfirmImportSatistPaperDAOHE();
                        ConfirmImportSatistPaperForm bo = new ConfirmImportSatistPaperForm(cisphe.getById("confirmImportSatistPaperId", provideForm.getConfirmImportSatistPaperId()));
                        provideForm.setConfirmImportSatistPaperForm(bo);
                    }
                }
            }
        }
        return strReturn;
    }

    /**
     * loadSignPage
     *
     * @return
     */
    public String loadSignPage() {

        String strReturn = ERROR_PERMISSION;
        if (signForm.getFileId() != null && signForm.getFileId() > 0l) {
            FilesDAOHE fdhe = new FilesDAOHE();
            signForm = fdhe.getFilesDetail(signForm.getFileId());
        }
        if (signForm.getFileType() != null && signForm.getFileType() > 0l) {
            ProcedureDAOHE pdhe = new ProcedureDAOHE();
            CategoryDAOHE cdhe = new CategoryDAOHE();
            Procedure tthc = pdhe.findById(signForm.getFileType());
            if (tthc != null) {
                strReturn = tthc.getDescription() + "Sign";
                if (signForm.getAnnouncementId() != null && signForm.getAnnouncementId() > 0L) {
                }
                if (signForm.getTestRegistrationId() != null && signForm.getTestRegistrationId() > 0L) {
                }
                List lstNation = cdhe.findAllCategory("NATION");
                getRequest().setAttribute("lstNation", lstNation);
            }
        }
        getBarcodeImg();
        return strReturn;
    }

    public String loadEvaluateView() {

        return GRID_DATA;
    }

    /**
     * trang xem xet
     *
     * @return
     */
    public String toSignPage() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        return SIGN_PAGE;
    }

    //</editor-fold>
    /*#lookup file by leader or staff*/
    /**
     * trang tim kiem ho so vai tro lanh dao cuc
     *
     * @return
     */
    public String lookupFilesByLeader() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        return lookupFilesByLeaderPage;
    }

    /**
     * trang tim kiem ho so vai tro lanh dao cuc
     *
     * @return
     */
    public String lookupFilesByLeaderApproveSdbs() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        return lookupFilesByLeaderApproveSdbs;
    }

    /**
     * action tim kiem ho so vai tro lanh dao cuc
     *
     * @return
     */
    public String onsearchLookupFilesByLeader() {
        getGridInfo();

        if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
            try {
                String startServerStr = getRequest().getSession().getAttribute("lookupFilesByLeaderApproveSdbs.startServer") == null ? "" : getRequest().getSession().getAttribute("lookupFilesByLeaderApproveSdbs.startServer").toString();
                String countServerStr = getRequest().getSession().getAttribute("lookupFilesByLeaderApproveSdbs.countServer") == null ? "" : getRequest().getSession().getAttribute("lookupFilesByLeaderApproveSdbs.countServer").toString();

                if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                    count = Integer.parseInt(countServerStr);
                    start = Integer.parseInt(startServerStr);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
        }

        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        GridResult gr = bdhe.searchLookupFiles(searchForm, getDepartmentId(), getUserId(), Constants.ROLES.LEAD_OFFICE_ROLE, start, count, sortField, "");

        getRequest().getSession().setAttribute("lookupFilesByLeaderApproveSdbs.startServer", start);
        getRequest().getSession().setAttribute("lookupFilesByLeaderApproveSdbs.countServer", count);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * trang tim kiem ho so vai tro lanh dao phong
     *
     * @return
     */
    public String lookupFilesByLeaderOfStaff() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        UsersDAOHE udaohe = new UsersDAOHE();
        List lstLDP = udaohe.findLstUserByPosition(getDepartmentId(), Constants.POSITION.LEADER_OF_STAFF_T);
        if (lstLDP == null || lstLDP.isEmpty()) {
            List<String> lstLeader = new ArrayList<String>();
            lstLeader.add(Constants.POSITION.LEADER_OF_STAFF_T);
            lstLeader.add(Constants.POSITION.GDTT);
            lstLDP = udaohe.findLstUserByLstPosition(getDepartmentId(), lstLeader);
        }
        lstLeaderOfStaff = new ArrayList();
        lstLeaderOfStaff.addAll(lstLDP);
        lstLeaderOfStaff.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstLeaderOfStaff", lstLeaderOfStaff);
        return lookupFilesByStaffOfStaffPage;
    }

    /**
     * action tim kiem ho so vai tro lanh dao phong
     *
     * @return
     */
    public String onsearchLookupFilesByLeaderOfStaff() {
        getGridInfo();

        if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
            try {
                String startServerStr = getRequest().getSession().getAttribute("lookupFilesByLeaderOfStaff.startServer") == null ? "" : getRequest().getSession().getAttribute("lookupFilesByLeaderOfStaff.startServer").toString();
                String countServerStr = getRequest().getSession().getAttribute("lookupFilesByLeaderOfStaff.countServer") == null ? "" : getRequest().getSession().getAttribute("lookupFilesByLeaderOfStaff.countServer").toString();

                if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                    count = Integer.parseInt(countServerStr);
                    start = Integer.parseInt(startServerStr);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
        }

        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        GridResult gr = bdhe.searchLookupFiles(searchForm, getDepartmentId(), getUserId(), Constants.ROLES.LEAD_UNIT, start, count, sortField, "");

        getRequest().getSession().setAttribute("lookupFilesByLeaderOfStaff.startServer", start);
        getRequest().getSession().setAttribute("lookupFilesByLeaderOfStaff.countServer", count);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * trang tim kiem ho so vai tro chuyên viên
     *
     * @return
     */
    public String lookupFilesByStaff() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);

        lstProductType = new ArrayList();

        CategoryDAOHE cat = new CategoryDAOHE();
        getRequest().setAttribute("lstProductType", null);
        lstProductType = new ArrayList();
        lstProductType.addAll(cat.findAllCategory("SP"));
        lstProductType.add(0, new Category(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstProductType", lstProductType);

        lstProvince = new ArrayList();
        getRequest().setAttribute("lstProvince", null);
        lstProvince = new ArrayList();
        lstProvince.addAll(cat.findAllCategory("PROVINCE"));
        lstProvince.add(0, new Category(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstProvince", lstProvince);

        RepositoryDAOHE repDAO = new RepositoryDAOHE();
        lstRepositories = new ArrayList();
        List allRep = repDAO.getRepositoryFromCreator(getUserId());
        lstRepositories.addAll(allRep);
        lstRepositories.add(0, new Repositories(Constants.COMBOBOX_REPOSITORY_HEADER_VALUE, Constants.COMBOBOX_REPOSITORY_HEADER_TEXT));
        getRequest().setAttribute("lstRepository", lstRepositories);

        return lookupFilesByStaffPage;
    }

    /**
     * trang tim kiem ho so vai tro chuyên viên
     *
     * @return
     */
    public String lookupFilesByStaffDonothing() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);

        lstProductType = new ArrayList();

        CategoryDAOHE cat = new CategoryDAOHE();
        getRequest().setAttribute("lstProductType", null);
        lstProductType = new ArrayList();
        lstProductType.addAll(cat.findAllCategory("SP"));
        lstProductType.add(0, new Category(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstProductType", lstProductType);

        lstProvince = new ArrayList();
        getRequest().setAttribute("lstProvince", null);
        lstProvince = new ArrayList();
        lstProvince.addAll(cat.findAllCategory("PROVINCE"));
        lstProvince.add(0, new Category(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstProvince", lstProvince);

        RepositoryDAOHE repDAO = new RepositoryDAOHE();
        lstRepositories = new ArrayList();
        List allRep = repDAO.getRepositoryFromCreator(getUserId());
        lstRepositories.addAll(allRep);
        lstRepositories.add(0, new Repositories(Constants.COMBOBOX_REPOSITORY_HEADER_VALUE, Constants.COMBOBOX_REPOSITORY_HEADER_TEXT));
        getRequest().setAttribute("lstRepository", lstRepositories);

        return lookupFilesByStaffDonothingPage;
    }

    /**
     * action tim kiem ho so vai tro chuyên viên
     *
     * @return
     */
    public String onsearchLookupFilesByStaff() {
        getGridInfo();

        if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
            try {
                String startServerStr = getRequest().getSession().getAttribute("lookupFilesByStaff.startServer") == null ? "" : getRequest().getSession().getAttribute("lookupFilesByStaff.startServer").toString();
                String countServerStr = getRequest().getSession().getAttribute("lookupFilesByStaff.countServer") == null ? "" : getRequest().getSession().getAttribute("lookupFilesByStaff.countServer").toString();

                if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                    count = Integer.parseInt(countServerStr);
                    start = Integer.parseInt(startServerStr);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
        }

        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        GridResult gr = bdhe.searchLookupFiles(searchForm, getDepartmentId(), getUserId(), Constants.ROLES.STAFF_ROLE, start, count, sortField, "");

        getRequest().getSession().setAttribute("lookupFilesByStaff.startServer", start);
        getRequest().getSession().setAttribute("lookupFilesByStaff.countServer", count);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * action tim kiem ho so vai tro chuyên viên
     *
     * @return
     */
    public String onsearchLookupFilesByClerical() {
        getGridInfo();

        if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
            try {
                String startServerStr = getRequest().getSession().getAttribute("lookupFilesByClerical.startServer") == null ? "" : getRequest().getSession().getAttribute("lookupFilesByClerical.startServer").toString();
                String countServerStr = getRequest().getSession().getAttribute("lookupFilesByClerical.countServer") == null ? "" : getRequest().getSession().getAttribute("lookupFilesByClerical.countServer").toString();

                if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                    count = Integer.parseInt(countServerStr);
                    start = Integer.parseInt(startServerStr);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
        }

        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        GridResult gr = bdhe.searchLookupFiles(searchForm, getDepartmentId(), getUserId(), Constants.ROLES.CLERICAL_ROLE, start, count, sortField, "");

        getRequest().getSession().setAttribute("lookupFilesByClerical.startServer", start);
        getRequest().getSession().setAttribute("lookupFilesByClerical.countServer", count);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * action tim kiem ho so vai tro chuyên viên
     *
     * @return
     */
    public String onsearchLookupFilesByStaffDonothing() {
        getGridInfo();
        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        GridResult gr = bdhe.searchLookupFilesDonothing(searchForm, getDepartmentId(), getUserId(), Constants.ROLES.STAFF_ROLE, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * trang tim kiem ho so vai tro van thu
     *
     * @return
     */
    public String lookupFilesByClerical() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
//        CategoryDAOHE catedaohe = new CategoryDAOHE();
//        List status = catedaohe.findAllCategoryOrderByCode("STATUS");
//        lstStatus = new ArrayList();
//        lstStatus.addAll(status);
//        lstStatus.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
//        getRequest().setAttribute("lstStatus", lstStatus);
        return lookupFilesByClericalPage;
    }

    public String lookupFilesByClericalForAA() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        return lookupFilesByClericalForAAPage;
    }

    /**
     * action doi chieu ho so
     *
     * @return
     */
    public String toComparison() {
        getGridInfo();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
            searchForm.setStatus(Constants.FILE_STATUS.ALERT_COMPARISON);
        }
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        return toComparisonPage;
    }

    /**
     * action doi chieu ho so loi
     *
     * @return
     */
    public String toComparisonFail() {
        getGridInfo();
        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
            searchForm.setStatus(Long.valueOf(16));
        }
        GridResult gr = bdhe.searchLookupFiles(searchForm, getDepartmentId(), getUserId(), Constants.ROLES.CLERICAL_ROLE, start, count, sortField, "");
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        //return GRID_DATA;
        return toComparisonFailPage;
    }

    /**
     * action thông báo đối chiếu hồ
     *
     * @return
     */
    public String toComparisonAlert() {
        getGridInfo();
        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
            searchForm.setStatus(Constants.FILE_STATUS.APPROVED);
        }
        GridResult gr = bdhe.searchLookupFiles(searchForm, getDepartmentId(), getUserId(), Constants.ROLES.CLERICAL_ROLE, start, count, sortField, "");
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return toComparisonAlertPage;
    }

    /**
     * action tim kiem ho so vai tro van thu
     *
     * @return
     */
    public String onsearchLookupFilesByClericalOnHomePage() {
        getGridInfo();

        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        GridResult gr = bdhe.searchLookupFilesOnHomePage(searchForm, getDepartmentId(), getUserId(), Constants.ROLES.CLERICAL_ROLE, start, count, sortField);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * Report day
     *
     * @param form
     * @return
     */
    public List<FilesNoClob> onsearchDayReportClerical(FilesForm form) {
        getGridInfo();
        searchForm = form;
        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        GridResult gr = bdhe.searchLookupReport(searchForm, getDepartmentId(), getUserId(), Constants.ROLES.CLERICAL_ROLE, start, -1, "", "f.sendDate ASC,f.businessName ASC");
        return gr.getLstResult();
    }

    /**
     *
     * @param form
     * @return
     */
    public List<FilesNoClob> onsearchWeekReportClerical(FilesForm form) {
        getGridInfo();
        searchForm = form;
        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        GridResult gr = bdhe.searchLookupFiles(searchForm, getDepartmentId(), getUserId(), Constants.ROLES.CLERICAL_ROLE, start, -1, "", "f.sendDate ASC,f.businessName ASC");
        return gr.getLstResult();
    }

    /**
     * Xem luồng xử lý hồ sơ.
     *
     * @return
     */
    public String loadFLowView() {
        getGridInfo();
        String strReturn = ERROR_PERMISSION;
        GridResult gridResult = new GridResult(0, new ArrayList());
        ProcessDAOHE prodhe = new ProcessDAOHE();
        try {
            if (flowForm.getFileId() != null) {
                gridResult = prodhe.searchProcessOfDoc(Long.valueOf(flowForm.getFileId()), Long.valueOf(Constants.OBJECT_TYPE.FILES), start, count, "processId");
                jsonDataGrid.setItems(gridResult.getLstResult());
                jsonDataGrid.setTotalRows(gridResult.getnCount().intValue());
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return strReturn;
        }
        return GRID_DATA;
    }

    //Hiepvv Luồng hồ sơ gốc
    public String loadFLowView2() {
        getGridInfo();
        String strReturn = ERROR_PERMISSION;
        GridResult gridResult = new GridResult(0, new ArrayList());
        ProcessDAOHE prodhe = new ProcessDAOHE();
        try {
            if (flowForm.getFileId() != null) {
                FilesDAOHE fHE = new FilesDAOHE();
                Files f = fHE.findById(flowForm.getFileId());
                if (f.getFilesSourceID() != null && f.getFilesSourceID() > 0) {
                    gridResult = prodhe.searchProcessOfDoc(Long.valueOf(f.getFilesSourceID()), Long.valueOf(Constants.OBJECT_TYPE.FILES), start, count, "processId");
                } else {
                    return null;
                }
                jsonDataGrid.setItems(gridResult.getLstResult());
                jsonDataGrid.setTotalRows(gridResult.getnCount().intValue());
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return strReturn;
        }
        return GRID_DATA;
    }

    /**
     * TIẾP NHẬn HỒ SƠ BINHNT53
     *
     * @return
     */
    public String toReceived() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        //Hiepvv Tach chuc nang sua doi sau cong bo
        List lstTTHC = cdhe.getAllProcedure2();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
//        try {
//            Date sysDate = cdhe.getSysdate();
//            getRequest().setAttribute("sysDate", sysDate);
//        } catch (Exception ex) {
//            Logger.getLogger(FilesDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        return RECEIVED_PAGE;
    }

    /**
     * Hiepvv 22/01 TIẾP NHẬn HỒ SƠ Sua doi bo sung sau cong bo
     *
     * @return
     */
    public String toReceivedAfterAnnounced() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        Procedure p = new Procedure();
        try {
            p = cdhe.getProcedureByDescription(announcementFile05);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            p = null;
        }
        lstCategory = new ArrayList();
        lstCategory.add(p);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        if (p != null) {
            searchForm.setFileType(p.getProcedureId());
        }
        return RECEIVEDAFTERANNOUNED_PAGE;
    }

    //VĂN THƯ TIẾP NHẬN HỒ SƠ BINHNT53
    /**
     * van thu tiep nhan ho so
     *
     * @return
     */
    public String onSearchFilesToReceived() {
        getGridInfo();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        GridResult gr = bdhe.findAllFileForReceived(searchForm, getDepartmentId(), start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    //Hiepvv 
    //Van thu tiep nhan ho so sua doi bo sung sau cong bo
    public String onSearchFilesToReceivedAfterAnnounced() {
        getGridInfo();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        Procedure p = new Procedure();
        ProcedureDAOHE pdao = new ProcedureDAOHE();
        try {
            p = pdao.getProcedureByDescription(announcementFile05);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            p = null;
        }
        if (p != null) {
            searchForm.setFileType(p.getProcedureId());
        }
        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        GridResult gr = bdhe.findAllFileForReceived(searchForm, getDepartmentId(), start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }
    //từ chối tiếp nhận hồ sơ binhnt

    /**
     * tu choi tiep nhan ho so
     *
     * @return
     */
    public String onRejectReveived() {
        FilesDAOHE fdhe = new FilesDAOHE();
        //        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
//        if (check) {

        boolean bReturn = fdhe.rejectReveived(createForm, getUserId(), getUserName());
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        //        } else {
//            resultMessage.add("3");
//            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");

//        }
        jsonDataGrid.setItems(resultMessage);
//        EventLogDAOHE edhe = new EventLogDAOHE();
//        edhe.insertEventLog("Xem xét hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());

        return GRID_DATA;
    }

    /**
     * Văn thư tiếp nhận hồ sơ - binhnt53
     *
     * @return
     */
    public String onReceivedFile() {
        FilesDAOHE fdhe = new FilesDAOHE();
//        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
//        if (check) {

        boolean bReturn = fdhe.onReceivedFile(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        //        } else {
//            resultMessage.add("3");
//            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");

//        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Văn thư tiếp nhận", "hồ sơ có id=" + createForm.getFileId(), getRequest());
        return GRID_DATA;
    }

    //hieptq update 120115
    public String onReceivedMoreFile() {
        FilesDAOHE fdhe = new FilesDAOHE();
        //        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
//        if (check) {

        String lstObjectId = getRequest().getParameter("lstObjectId");
        boolean bReturn = fdhe.onReceivedMoreFile(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName(), lstObjectId);
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công - có hồ sơ có nhiều đơn vị xử lý không thể tiếp nhận tự động");
        }
        //        } else {
//            resultMessage.add("3");
//            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");

//        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Văn thư tiếp nhận", "Những hồ sơ có id=" + lstObjectId, getRequest());
        return GRID_DATA;
    }

    /**
     * Văn thư tiếp nhận hồ sơ - binhnt53
     *
     * @return
     */
    public String onReturnFiles() {
        FilesDAOHE fdhe = new FilesDAOHE();
        //        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
//        if (check) {

        createForm = new FilesForm();
        String strObjectId = getRequest().getParameter("signFileId");
        Long fileId = 0L;
        try {
            fileId = Long.parseLong(strObjectId);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        createForm.setFileId(fileId);
        boolean bReturn = fdhe.onReturnFiles(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Tra dấu hồ sơ thành công!");
        } else {
            resultMessage.add("3");
            resultMessage.add("Tra dấu hồ sơ không thành công");
        }
        //        } else {
//            resultMessage.add("3");
//            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");

//        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Văn thư trả hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());
        return GRID_DATA;
    }

    /**
     * u150121 binhnt53 update luong tra ho so luong cu
     *
     * @return
     */
    public String onReturnFilesOldFlow() {
        FilesDAOHE fdhe = new FilesDAOHE();
        boolean bReturn = fdhe.onReturnFiles(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Tra dấu hồ sơ thành công!");
        } else {
            resultMessage.add("3");
            resultMessage.add("Tra dấu hồ sơ không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Văn thư trả hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());
        return GRID_DATA;
    }

    /**
     * Xem chi tiết hồ sơ tiếp nhận
     *
     * @return
     */
    public String loadFileViewReceived() {
        String strReturn = ERROR_PERMISSION;
        String agencyName = "";
        if (createForm.getFileId() != null && createForm.getFileId() > 0l) {
            FilesDAOHE fdhe = new FilesDAOHE();
            createForm = fdhe.getFilesDetail(createForm.getFileId());
            DepartmentDAOHE ddhe = new DepartmentDAOHE();
            Department dept = ddhe.getDeptByUserId(createForm.getUserCreateId());
            if (dept != null) {
                agencyName = dept.getDeptName();
            }
        }
        if (createForm.getFileType() != null && createForm.getFileType() > 0l) {
            ProcedureDAOHE pdhe = new ProcedureDAOHE();
            CategoryDAOHE cdhe = new CategoryDAOHE();
            Procedure tthc = pdhe.findById(createForm.getFileType());
            if (tthc != null) {

                lstProductType = cdhe.findAllCategory("SP");
                lstUnit = cdhe.findAllCategory("DVI");
                getRequest().setAttribute("lstProductType", lstProductType);
                getRequest().setAttribute("lstUnit", lstUnit);
                String fileLst = com.viettel.voffice.common.util.StringUtils.escapeHTML(tthc.getFileList());
                getRequest().setAttribute("fileList", fileLst);
                getRequest().setAttribute("agencyName", agencyName);

                strReturn = tthc.getDescription() + "ViewReceived";
            }
        }
        return strReturn;
    }

    /**
     * get ket qua doi chieu
     *
     * @return
     */
    public String getComments() {
        getGridInfo();
        String strObjectId = getRequest().getParameter("objectId");
//        String strObjectType = getRequest().getParameter("objectType");
        String strCommentType = getRequest().getParameter("commentType");
//        String attachIds = getRequest().getParameter("attachIds");

        Long objectId = 0l;
//        Long objectType = 0l;
        Long commentType = 0l;
        try {
            objectId = Long.parseLong(strObjectId);
//            objectType = Long.parseLong(strObjectType);
            commentType = Long.parseLong(strCommentType);
        } catch (NumberFormatException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        ProcessCommentDAOHE pcdhe = new ProcessCommentDAOHE();
        //VoAttachsDAOHE attDaoHe = new VoAttachsDAOHE();
        GridResult result = pcdhe.getCommentOfDocument(getUserId(), objectId, commentType, 0L, true, start, count, sortField);//binhnt53 150130 bo object type thua
        List<ProcessCommentForm> listPc = result.getLstResult();

        String idSession = (String) getRequest().getSession().getAttribute("idSession");
        if (idSession == null) {
            idSession = "";
        }
        if (listPc != null && listPc.isEmpty() == false) {
            for (ProcessCommentForm pc : listPc) {
                idSession += pc.getAttachIds();
                idSession += ";";
            }
        }

        getRequest().getSession().setAttribute("idSession", idSession);

        jsonDataGrid.setItems(result.getLstResult());
        jsonDataGrid.setTotalRows(result.getnCount().intValue());
        return GRID_DATA;
    }

    //140709
    /**
     * lay y kien doanh nghiep
     *
     * @return
     */
    public String getCommentsBusiness() {
        getGridInfo();
        String strObjectId = getRequest().getParameter("objectId");
        String strObjectType = getRequest().getParameter("objectType");

        Long objectId = 0l;
//        Long objectType = 0l;
        try {
            objectId = Long.parseLong(strObjectId);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
//        try {
//            objectType = Long.parseLong(strObjectType);
//        } catch (Exception ex) {
//            LogUtil.addLog(ex);//binhnt sonar a160901
//        }
        ProcessCommentDAOHE pcdhe = new ProcessCommentDAOHE();
        GridResult result = pcdhe.getCommentOfDocument(getUserId(), objectId, 0L, 0l, false, start, count, sortField);//binhnt53 150130 bo object type thua
        List<ProcessCommentForm> listPc = result.getLstResult();
        String idSession = (String) getRequest().getSession().getAttribute("idSession");
        if (idSession == null) {
            idSession = "";
        }

        for (ProcessCommentForm pc : listPc) {
            idSession += pc.getAttachIds();
            idSession += ";";
        }
        getRequest().getSession().setAttribute("idSession", idSession);

        jsonDataGrid.setItems(result.getLstResult());
        jsonDataGrid.setTotalRows(result.getnCount().intValue());
        return GRID_DATA;
    }

    public String getVersionSDBS() {
        getGridInfo();
        String strObjectId = getRequest().getParameter("objectId");
        String strObjectType = getRequest().getParameter("objectType");
        Long objectId = -1L;
        Long objectType = -1L;
        try {
            objectId = Long.parseLong(strObjectId);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901            
        }
        try {
            objectType = Long.parseLong(strObjectType);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        VoAttachsDAOHE vtdhe = new VoAttachsDAOHE();
        GridResult result = vtdhe.getAttachsByIdType(objectId, objectType, start, count, sortField);
        jsonDataGrid.setItems(result.getLstResult());
        jsonDataGrid.setTotalRows(result.getnCount().intValue());
        return GRID_DATA;
    }

    public String getAttachsSDBS() {
        getGridInfo();
        String strObjectId = getRequest().getParameter("objectId");
        String strObjectType = getRequest().getParameter("objectType");
        Long objectId = -1L;
        Long objectType = -1L;
        try {
            objectId = Long.parseLong(strObjectId);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        try {
            objectType = Long.parseLong(strObjectType);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        VoAttachsDAOHE vtdhe = new VoAttachsDAOHE();
        GridResult result = vtdhe.getAttachsByIdTypeChanged(objectId, objectType, start, count, sortField);
        jsonDataGrid.setItems(result.getLstResult());
        jsonDataGrid.setTotalRows(result.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * lay lich su thanh toan
     *
     * @return
     */
    public String getPaymentHistory() {
        getGridInfo();
        String strObjectId = getRequest().getParameter("objectId");
        String strObjectType = getRequest().getParameter("objectType");
        List customInfo = new ArrayList();
        Long objectId = 0l;
        Long objectType = 0l;
        Long totalFeeFile;
        Long feeFile;

        try {
            objectId = Long.parseLong(strObjectId);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(en.getMessage());
        }
        try {
            objectType = Long.parseLong(strObjectType);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        PaymentHistoryDAOHE pcdhe = new PaymentHistoryDAOHE();
        FilesDAOHE bdhe = new FilesDAOHE();
        Files filebo = bdhe.findById(objectId);
        String fileCode = "0";
        if (filebo != null) {
            fileCode = filebo.getFileCode();
        }
        GridResult result = pcdhe.getLstPaymentHistory(fileCode, objectType, start, count, sortField);
        FilesDAOHE filesDHE = new FilesDAOHE();
        feeFile = filesDHE.findById(objectId).getFeeFile();
        if (feeFile == null) {
            feeFile = 0l;
        }
        totalFeeFile = pcdhe.getTotalPaymentHistory(fileCode, objectType);
        customInfo.add(feeFile);
        customInfo.add(totalFeeFile);
        // nop du
        if ((totalFeeFile.equals(feeFile) && feeFile > 0l) || (totalFeeFile > feeFile)) {
            customInfo.add(1);
        } else // nop thieu
        {
            if (totalFeeFile < feeFile && totalFeeFile > 0) {
                customInfo.add(0);
            } // chua nop
            else {
                customInfo.add(-1);
            }
        }

        jsonDataGrid.setItems(result.getLstResult());
        jsonDataGrid.setTotalRows(result.getnCount().intValue());
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /**
     * get ý kiến xử lý hồ sơ
     *
     * @return
     */
    public String getComparisonRecordsDetails() {
        List items = new ArrayList();
        FilesDAOHE fdhe = new FilesDAOHE();
        Files file = fdhe.getById("fileId", createForm.getFileId());
        //file.setIsComparison(createForm.getIsComparison());
        Long check = file.getIsComparison();
        //file.setComparisonContent(createForm.getComparisonContent());
        String content = file.getComparisonContent();
        items.add(check);
        items.add(content);
        jsonDataGrid.setItems(items);
        return GRID_DATA;
    }

    /**
     * them noi dung y kien tham dinh ho so
     *
     * @return
     */
    public String insertComment() {
        if (commentForm != null) {
            ProcessDAOHE pdhe = new ProcessDAOHE();
            ProcessCommentDAOHE pcdhe = new ProcessCommentDAOHE();
            Long userId = getUserId();
            String userName = getUserName();
            Long departmentId = getDeptRepresentId();
            Department dept = getDeptRepresent();
            String deptName = "";
            if (dept != null) {
                deptName = dept.getDeptName();
            } else {
            }

            ProcessCommentForm pcf = new ProcessCommentForm();
            Long processId = pdhe.getProcessId(commentForm.getObjectId());
            if (processId != null) {
                pcf.setProcessId(processId);
                pcf.setCreatedBy(userId);
                pcf.setUserId(userId);
                pcf.setUserName(userName);
                if (departmentId > 0) {
                    pcf.setGroupId(departmentId);
                } else {
                    UsersDAOHE udhe = new UsersDAOHE();
                    Users ubo = udhe.findById(userId);
                    if (ubo != null) {
                        pcf.setGroupId(ubo.getBusinessId());
                    }
                }
                if (deptName != "") {
                    pcf.setGroupName(deptName);
                } else {
                    UsersDAOHE udhe = new UsersDAOHE();
                    Users ubo = udhe.findById(userId);
                    if (ubo != null) {
                        pcf.setGroupName(ubo.getBusinessName());
                    }
                }
                pcf.setCreatedDate(new Date());
                pcf.setCommentText(commentForm.getCommentText());
                pcf.setCommentType(commentForm.getCommentType());
                pcf.setAttachIds(commentForm.getAttachIds());
                pcf.setObjectId(commentForm.getObjectId());
                pcf.setObjectType(commentForm.getObjectType());
                pcf.setIsActive(1L);

                pcdhe.insertComment(pcf);
//                Process newItem = new Process();
//                newItem.setIsActive(IS_ACTIVE);
//                newItem.setObjectId(commentForm.getObjectId());
//                newItem.setObjectType(commentForm.getObjectType());
//                newItem.setProcessType(Constants.PROCESS_TYPE.COMMENT);
//                newItem.setSendGroupId(departmentId);
//                newItem.setSendGroup(deptName);
//                newItem.setReceiveGroupId(departmentId);
//                newItem.setReceiveGroup(deptName);
//                newItem.setSendUser(userName);
//                newItem.setSendUserId(userId);
//                newItem.setReceiveUser(userName);
//                newItem.setReceiveUserId(userId);
//                newItem.setSendDate(new Date());
//                newItem.setReceiveDate(new Date());
//                newItem.setStatus(-1L);
//                processId = pdhe.create(newItem);
            }
        }
        if (filesCommentForm != null) {
            ProcessDAOHE pdhe = new ProcessDAOHE();
            ProcessCommentDAOHE pcdhe = new ProcessCommentDAOHE();
            Long userId = getUserId();
            String userName = getUserName();
            Long departmentId = getDeptRepresentId();
            Department dept = getDeptRepresent();
            String deptName = "";
            if (dept != null) {
                deptName = dept.getDeptName();
            } else {
            }

            ProcessCommentForm pcf = new ProcessCommentForm();
            Long processId = pdhe.getProcessId(filesCommentForm.getObjectId());
            if (processId != null) {
                pcf.setProcessId(processId);
                pcf.setCreatedBy(userId);
                pcf.setUserId(userId);
                pcf.setUserName(userName);
                if (departmentId > 0) {
                    pcf.setGroupId(departmentId);
                } else {
                    UsersDAOHE udhe = new UsersDAOHE();
                    Users ubo = udhe.findById(userId);
                    if (ubo != null) {
                        pcf.setGroupId(ubo.getBusinessId());
                    }
                }
                if (deptName != "") {
                    pcf.setGroupName(deptName);
                } else {
                    UsersDAOHE udhe = new UsersDAOHE();
                    Users ubo = udhe.findById(userId);
                    if (ubo != null) {
                        pcf.setGroupName(ubo.getBusinessName());
                    }
                }
                pcf.setCreatedDate(new Date());
                pcf.setAttachIds(filesCommentForm.getAttachIds());
                pcf.setObjectId(filesCommentForm.getObjectId());
                pcf.setObjectType(filesCommentForm.getObjectType());
                pcf.setIsActive(1L);
                if (filesCommentForm.getCommentText() != null && filesCommentForm.getCommentText().length() > 0) {
                    pcf.setCommentText(filesCommentForm.getCommentText());
                    pcf.setCommentType(4L);
                    pcdhe.insertComment(pcf);
                }
                if (filesCommentForm.getLegal() != null && filesCommentForm.getLegal().length() > 0) {
                    pcf.setCommentText(filesCommentForm.getLegal());
                    pcf.setCommentType(1L);
                    pcdhe.insertComment(pcf);
                }
                if (filesCommentForm.getEffectUtility() != null && filesCommentForm.getEffectUtility().length() > 0) {
                    pcf.setCommentText(filesCommentForm.getEffectUtility());
                    pcf.setCommentType(3L);
                    pcdhe.insertComment(pcf);
                }
                if (filesCommentForm.getFoodSafetyQuality() != null && filesCommentForm.getFoodSafetyQuality().length() > 0) {
                    pcf.setCommentText(filesCommentForm.getFoodSafetyQuality());
                    pcf.setCommentType(2L);
                    pcdhe.insertComment(pcf);
                }
//                Process newItem = new Process();
//                newItem.setIsActive(IS_ACTIVE);
//                newItem.setObjectId(filesCommentForm.getObjectId());
//                newItem.setObjectType(filesCommentForm.getObjectType());
//                newItem.setProcessType(Constants.PROCESS_TYPE.COMMENT);
//                newItem.setSendGroupId(departmentId);
//                newItem.setSendGroup(deptName);
//                newItem.setReceiveGroupId(departmentId);
//                newItem.setReceiveGroup(deptName);
//                newItem.setSendUser(userName);
//                newItem.setSendUserId(userId);
//                newItem.setReceiveUser(userName);
//                newItem.setReceiveUserId(userId);
//                newItem.setSendDate(new Date());
//                newItem.setReceiveDate(new Date());
//                newItem.setStatus(-1L);
//                processId = pdhe.create(newItem);
            }
        }
        return GRID_DATA;
    }

    /**
     * Xóa ý kiến thẩm định hồ sơ
     *
     * @return
     */
    public String onDeleteComment() {
        List resultMessage = new ArrayList();
        ProcessCommentDAOHE procCmtDaoHe = new ProcessCommentDAOHE();
        try {
            String processCommentId = getRequest().getParameter("processCommentId");
            if (processCommentId != null) {
                ProcessComment bo = procCmtDaoHe.findById(Long.valueOf(processCommentId), false);
                bo.setIsActive(Constants.Status.INACTIVE);
                getSession().update(bo);
                resultMessage.add("0");
                resultMessage.add("Xóa ý kiến thành công");
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("0");
            resultMessage.add("Xóa ý kiến không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     * Kiểm tra hồ sơ có phiếu thẩm định hay không
     *
     * @return
     */
    public String onCheckEvaluationFile() {
        List resultMessage = new ArrayList();
        if (createForm.getFileId() != null) {
            FilesDAOHE fdhe = new FilesDAOHE();
            Files form = fdhe.findById(createForm.getFileId());
            //tim tham dinh
            EvaluationRecordsDAOHE evaluationRecordsDAOHE = new EvaluationRecordsDAOHE();
            EvaluationRecords evaluationRecords = evaluationRecordsDAOHE.findFilesByFileId(form);
            if (evaluationRecords == null) {
                resultMessage.add("3");
                resultMessage.add("Không có phiếu thẩm xét");
            } else {
                resultMessage.add("1");
                resultMessage.add("Xuất file thẩm định hồ sơ");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Hồ sơ không tồn tại");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     * Kiểm tra Hồ sơ có bản công bố hợp qui ko
     *
     * @return
     */
    public String onCheckPaperFile() {
        List resultMessage = new ArrayList();
        if (createForm.getFileId() != null) {
            FilesDAOHE fdhe = new FilesDAOHE();
            FilesForm form = fdhe.getFilesDetail(createForm.getFileId());
            if (form != null) {
                if (form.getAnnouncementReceiptPaperForm() != null) {
                    if (form.getAnnouncementReceiptPaperForm().getAnnouncementReceiptPaperId() != null) {
                        resultMessage.add("1");
                        resultMessage.add(createForm.getFileId());
                    } else {
                        resultMessage.add("3");
                        resultMessage.add("Không có bản công bố");
                    }
                } else if (form.getConfirmImportSatistPaperForm() != null) {
                    if (form.getConfirmImportSatistPaperForm().getConfirmImportSatistPaperId() != null) {
                        resultMessage.add("1");
                        resultMessage.add(createForm.getFileId());
                    } else {
                        resultMessage.add("3");
                        resultMessage.add("Không có bản công bố");
                    }
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Không có bản công bố");
                }
            } else {
                resultMessage.add("3");
                resultMessage.add("Không có bản công bố");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Không có bản công bố");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     * Kiểm tra hồ sơ có tồn tại bản công bố phù hợp ko
     *
     * @return
     */
    public String oncheckAnnouncementReceiptPaper() {
        List resultMessage = new ArrayList();
        if (createForm.getFileId() != null) {
            FilesDAOHE fdhe = new FilesDAOHE();
            FilesForm form = fdhe.getFilesDetail(createForm.getFileId());
            if (form != null) {
                if (form.getAnnouncementReceiptPaperForm() != null) {
                    if (form.getAnnouncementReceiptPaperForm().getAnnouncementReceiptPaperId() != null) {
                        resultMessage.add("1");
                        resultMessage.add(createForm.getFileId());
                    } else {
                        resultMessage.add("3");
                        resultMessage.add("Không có bản công bố");
                    }
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Không có bản công bố");
                }
            } else {
                resultMessage.add("3");
                resultMessage.add("Không có bản công bố");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Không có bản công bố");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }//

    /**
     * Kiểm tra hồ sơ có tồn tại với Số tiếp nhận không
     *
     * @return
     */
    public String oncheckReAnnouncementFiles() {
        List resultMessage = new ArrayList();
        if (createForm.getFileId() != null) {
            FilesDAOHE fdhe = new FilesDAOHE();
            FilesForm form = fdhe.getFilesDetail(createForm.getFileId());
            if (form != null) {
                if (form.getReIssueForm() != null) {
                    Announcement ann = fdhe.checkAnnouncementFilesExist(form.getReIssueForm().convertToEntity());
                    if (ann != null) {
                        resultMessage.add("1");
                        resultMessage.add(createForm.getFileId());
                        jsonDataGrid.setCustomInfo(ann);
                    } else {
                        resultMessage.add("3");
                        resultMessage.add("Không có thông tin hồ sơ");
                    }
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Không có thông tin hồ sơ");
                }
            } else {
                resultMessage.add("3");
                resultMessage.add("Không có thông tin hồ sơ");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Không có thông tin hồ sơ");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     * Tới trang copy hồ sơ của doanh nghiệp
     *
     * @return
     */
    public String toCopyFilePage() {
        String strReturn = ERROR_PERMISSION;
        Long checkEdit = getRequest().getParameter("checkEdit") == null ? 0L : Long.parseLong(getRequest().getParameter("checkEdit"));
        Long fileType = getRequest().getParameter("fileType") == null ? 0L : Long.parseLong(getRequest().getParameter("fileType"));
        // check edit hide excel tab
        getRequest().setAttribute("checkEdit", checkEdit);
        try {
            if (createForm.getFileId() != null && createForm.getFileId() > 0l) {
                FilesDAOHE fdhe = new FilesDAOHE();

                createForm = fdhe.getFilesDetail(createForm.getFileId());
                ProcedureDAOHE pdhe = new ProcedureDAOHE();
                Procedure tthc = pdhe.findById(createForm.getFileType());
                if (fileType > 0L) {
                    createForm.setFileType(fileType);
                    //createForm.setFileId(fileId);
                }
                createForm.setAnnouncementId(null);
                createForm.setDetailProductId(null);
                createForm.setReIssueFormId(null);
                createForm.setTestRegistrationId(null);
                createForm.setAnnouncementReceiptPaperId(null);
                createForm.setConfirmAnnouncementPaperId(null);
                createForm.setConfirmImportSatistPaperId(null);
                strReturn = tthc.getDescription();
                if (strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN)) {
                    String a = fdhe.getReceiptNoNew(getUserId(), getUserLogin(), createForm.getFileType());
                    createForm.getAnnouncement().setAnnouncementNo(a);
                }
                //createForm.setFileId(null);
                createForm.setFlowId(null);
                createForm.setLeaderStaffSignId(null);

                if (createForm.getAnnouncement() != null) {
                    createForm.getAnnouncement().setAnnouncementId(null);
                }
                if (createForm.getAnnouncementReceiptPaperForm() != null) {
                    createForm.getAnnouncementReceiptPaperForm().setAnnouncementReceiptPaperId(null);
                }
                if (createForm.getAnnouncementReceiptPaperForm() != null) {
                    createForm.getAnnouncementReceiptPaperForm().setReceiptNo(null);
                }
                if (createForm.getDetailProduct() != null) {
                    createForm.getDetailProduct().setDetailProductId(null);
                }
            }
            if (createForm.getFileType() != null && createForm.getFileType() > 0l) {
                ProcedureDAOHE pdhe = new ProcedureDAOHE();
                CategoryDAOHE cdhe = new CategoryDAOHE();
                TechnicalStandardDAOHE tdhe = new TechnicalStandardDAOHE();
                UserAttachsDAOHE uahe = new UserAttachsDAOHE();
                Procedure tthc = pdhe.findById(createForm.getFileType());
                if (tthc != null) {
                    //lstProductType = cdhe.findAllCategory("SP");
                    Long typeFee = cdhe.findTypeFee(tthc.getProcedureId());
                    lstProductType = cdhe.findAllCategoryByFee("SP", typeFee);
                    lstUnit = cdhe.findAllCategory("DVI");
                    lstUnit.add(0, new Category(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                    lstStandard = tdhe.findAllStandard();
                    String lstDepts = convertToJSONData(lstStandard, "vietnameseName", "vietnameseName");
                    getRequest().setAttribute("lstStandard", lstDepts);
                    lstUserAttach = uahe.findAllUserAttach(getUserId());
                    String lstUserAttachs = convertToJSONData(lstUserAttach, "attachName", "attachName");
                    getRequest().setAttribute("lstUserAttach", lstUserAttachs);
                    if (lstUserAttachs.trim().length() > 0) {
                        createForm.setCountUA(1L);
                    } else {
                        createForm.setCountUA(0L);
                    }
                    getRequest().setAttribute("lstProductType", lstProductType);
                    getRequest().setAttribute("lstUnit", lstUnit);
                    String fileLst = tthc.getFileList();
                    getRequest().setAttribute("fileList", com.viettel.common.util.StringUtils.removeHTML(fileLst));
                    getRequest().setAttribute("agencyName", getDepartmentName());
                    getRequest().setAttribute("fileNameFull", tthc.getName());
                    strReturn = tthc.getDescription();

                }
            }
            //hieptq 17.11.14
            if (createForm.getFileId() != null && createForm.getFileId() > 0l) {
                if (!createForm.getFileType().equals(0L)) {
                    ProcedureDAOHE cdhe = new ProcedureDAOHE();
                    List lstTTHC = cdhe.getProcedureForChange(createForm.getFileType());
                    lstCategory = new ArrayList();
                    lstCategory.addAll(lstTTHC);
                    lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                    getRequest().setAttribute("lstFileType", lstCategory);
                }
            }
            //
            getRequest().setAttribute("isCopy", true);
            CategoryDAOHE ctdhe = new CategoryDAOHE();
            Category cate = ctdhe.findCategoryByTypeAndCode("SP", "TPCN");
            Category cateTL = ctdhe.findCategoryByTypeAndCode("SP", "TL");
            List<Category> cate1 = ctdhe.findCategoryByTypeAndCodeNew("SP", "DBT");
            String dbtId = "";
            for (int i = 0; i < cate1.size(); i++) {
                dbtId += cate1.get(i).getCategoryId().toString() + ";";
            }
            Long tpcnId = cate.getCategoryId();
            Long tlId = cateTL.getCategoryId();
            FeeDAOHE fdhe1 = new FeeDAOHE();
            Fee findfee1 = fdhe1.findFeeByCode("TPDB");
            Long priceTPDB = findfee1.getPrice();
            Fee findfee2 = fdhe1.findFeeByCode("TPCN");
            Long priceTPCN = findfee2.getPrice();
            Fee findfee3 = fdhe1.findFeeByCode("TPK");
            Long priceETC = findfee3.getPrice();
            getRequest().setAttribute("dbtId", dbtId);
            getRequest().setAttribute("tpcnId", tpcnId);
            getRequest().setAttribute("tlId", tlId);
            getRequest().setAttribute("priceTPCN", priceTPCN);
            getRequest().setAttribute("priceTPDB", priceTPDB);
            getRequest().setAttribute("priceETC", priceETC);
            getRequest().setAttribute("fileIdCopy", createForm.getFileId());
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return strReturn;
    }

    //
    /*
     * Chứng thực chữ ký số
     */
    public String validateCA() {
        List resultMessage = new ArrayList();
        if (signCAForm != null) {
            try {
                FilesDAOHE bdhe = new FilesDAOHE();
                Files files = bdhe.getById("fileId", signCAForm.getFileId());
                if (CommonUtils.checkSecurityPublishCA(files.getFileId(), signCAForm.getContentSigned())) {
                    String contentSigned = signCAForm.getContentSigned();
                    KeyInfo keyInfo = CommonUtils.validateContentSigned(contentSigned);
                    String userSigned = "";
                    if (keyInfo != null) {
                        try {
                            userSigned = CommonUtils.getUserSigned(keyInfo);
                            //validate serial
                            String serialNumber = CommonUtils.getSerial(keyInfo);
                            CaUserDAOHE caUserDao = new CaUserDAOHE();
                            if (!caUserDao.checkCaUser(getUserLogin(), serialNumber)) {
                                resultMessage.add("0");
                                resultMessage.add("Phê duyệt không thành công. Bạn đang sử dụng USB token chưa được đăng ký");
                                jsonDataGrid.setItems(resultMessage);
                                return "gridData";
                            }
                        } catch (CertificateExpiredException ex) {
                            LogUtil.addLog(ex);//binhnt sonar a160901
                        } catch (CertificateNotYetValidException ex) {
                            LogUtil.addLog(ex);//binhnt sonar a160901
                        }
                    } else {
                        resultMessage.add("0");
                        resultMessage.add("Phê duyệt không thành công. Dữ liệu ký không hợp lệ");
                        jsonDataGrid.setItems(resultMessage);
                        return "gridData";
                    }
                    files.setContentSigned(contentSigned);
                    files.setUserSigned(userSigned);
                    getSession().saveOrUpdate(files);
                    resultMessage.add("1");
                    resultMessage.add("Ký duyệt thành công");
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                resultMessage.add("3");
                resultMessage.add("Có lỗi trong quá trình gửi hồ sơ");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Có lỗi dữ liệu trong quá trình gửi hồ sơ");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     * xem chi tiet y kien tham dinh
     *
     * @return
     */
    public String getEvaluationRecordsDetails() {
        EvaluationRecords evaluationRecordsBo;
        EvaluationRecordsDAOHE evaluationRecordsDAOHE = new EvaluationRecordsDAOHE();
        if (createForm != null) {
            FilesDAOHE fdhe = new FilesDAOHE();
            Files form = fdhe.findById(createForm.getFileId());
            evaluationRecordsBo = (EvaluationRecords) evaluationRecordsDAOHE.findFilesByFileId(form);
            jsonDataGrid.setCustomInfo(evaluationRecordsBo);
        }
        return GRID_DATA;
    }
//140405

    /*
     * Tim kiem hò so de review
     */
    public String onSearchFileToAdditionReview() {
        getGridInfo();

        if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
            try {
                String startServerStr = getRequest().getSession().getAttribute("additionReviewPage.startServer") == null ? "" : getRequest().getSession().getAttribute("additionReviewPage.startServer").toString();
                String countServerStr = getRequest().getSession().getAttribute("additionReviewPage.countServer") == null ? "" : getRequest().getSession().getAttribute("additionReviewPage.countServer").toString();

                if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                    count = Integer.parseInt(countServerStr);
                    start = Integer.parseInt(startServerStr);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
        }

        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
        GridResult gr = fdhe.searchFilesToProcess(searchForm, getDepartmentId(), getUserId(), 8l, start, count, sortField);

        getRequest().getSession().setAttribute("additionReviewPage.startServer", start);
        getRequest().getSession().setAttribute("additionReviewPage.countServer", count);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * tìm kiếm để xem xét SDBS hồ sơ
     *
     * @return
     */
    public String toAdditionReviewPage() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        return ADDITION_REVIEW_PAGE;
    }

    /**
     * tim kiem kho luu tru ho so giay
     *
     * @return
     */
    public String getAllRepository() {
        RepositoryDAOHE repDAO = new RepositoryDAOHE();
        lstRepositories = new ArrayList();
        List allRep = repDAO.findAllRepositories();
        lstRepositories.addAll(allRep);
        lstRepositories.add(0, new Repositories(Constants.COMBOBOX_REPOSITORY_HEADER_VALUE, Constants.COMBOBOX_REPOSITORY_HEADER_TEXT));
        getRequest().setAttribute("lstRepository", lstRepositories);
        return GRID_DATA;
    }

    /**
     * thong tin luu tru ho so giay
     *
     * @return
     */
    public String getRepositoryRecordsDetails() {
        RepositoryDAOHE rdhe = new RepositoryDAOHE();
        List items = rdhe.getRepositoryFromId(createForm.getRepositoriesId());

        jsonDataGrid.setItems(items);
        return GRID_DATA;
    }

    /**
     * thêm mới kho lưu trữ
     *
     * @return
     */
    public String onInsertRepo() {
        RepositoryDAOHE rdhe = new RepositoryDAOHE();
        boolean bReturn = rdhe.onCreateRepositories(repoForm, getUserId());
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Chuyên viên tạo mới", "kho có id=" + repoForm.getRepId(), getRequest());
        return GRID_DATA;
    }

    /**
     * sua kho luu tru
     *
     * @return
     */
    public String toRepositorySearch() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);

        RepositoryDAOHE repDAO = new RepositoryDAOHE();
        lstRepositories = new ArrayList();
        List allRep = repDAO.getRepositoryFromCreator(getUserId());
        lstRepositories.addAll(allRep);
        lstRepositories.add(0, new Repositories(Constants.COMBOBOX_REPOSITORY_HEADER_VALUE, Constants.COMBOBOX_REPOSITORY_HEADER_TEXT));
        getRequest().setAttribute("lstRepository", lstRepositories);
        return REPOSITORY_PAGE;
    }

    /**
     * tim kiem kho luu tru
     *
     * @return
     */
    public String lookupRepositories() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);

        RepositoryDAOHE repDAO = new RepositoryDAOHE();
        lstRepositories = new ArrayList();
        List allRep = repDAO.getRepositoryFromCreator(getUserId());
        lstRepositories.addAll(allRep);
        lstRepositories.add(0, new Repositories(Constants.COMBOBOX_REPOSITORY_HEADER_VALUE, Constants.COMBOBOX_REPOSITORY_HEADER_TEXT));
        getRequest().setAttribute("lstRepository", lstRepositories);
        return this.lookupRepositoriesPage;
    }

    /**
     * quan ly kho luu tru
     *
     * @return
     */
    public String onSearchLookupRepositories() {
        getGridInfo();
        RepositoryDAOHE rdhe = new RepositoryDAOHE();
        GridResult gr = rdhe.findAllRepositoriesById(repForm, getDepartmentId(), getUserId(), start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * action tim kiem kho luu tru
     *
     * @return
     */
    public String onSearchRepository() {
        getGridInfo();
        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        GridResult gr = bdhe.searchLookupFiles(searchForm, getDepartmentId(), getUserId(), Constants.ROLES.CLERICAL_ROLE, start, count, sortField, "");
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
        //return REPOSITORY_PAGE;
    }

    /**
     * cap nhat kho luu tru
     *
     * @return
     */
    public String onUpdateRepository() throws Exception {
        FilesDAOHE fdhe = new FilesDAOHE();

        if (createForm.getRepositoriesId() >= 0) {
            fdhe.updateRepository(createForm.getFileId(), createForm.getRepositoriesId());
        }
        return GRID_DATA;
    }

    /**
     * xoa kho luu tru
     *
     * @return
     */
    public String onDeleteRepositories() {
        RepositoryDAOHE rdhe = new RepositoryDAOHE();
        int bReturn = rdhe.deleteRepo(getUserId(), repoForm.getRepId());
        List resultMessage = new ArrayList();
        if (bReturn == 1) {
            resultMessage.add("1");
            resultMessage.add("Xóa dữ liệu thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     * lay ho so goc
     *
     * @return
     */
    public FilesForm getCreateFormOriginal() {
        return createFormOriginal;
    }

    /**
     * set ho so goc
     *
     * @return
     */
    public void setCreateFormOriginal(FilesForm createFormOriginal) {
        this.createFormOriginal = createFormOriginal;
    }

    /**
     * lay barcode
     *
     * @return
     */
    public void getBarcode(FilesForm form) {

        FilesDAOHE filehe = new FilesDAOHE();
        Files filesqr = filehe.findById(form.getFileId());
        Long announcementReceiptPaperId = 0L;
        Long confirmAnnouncementPaperId = 0L;
        Long confirmImportSatistPaperId = 0L;
        if (filesqr != null) {
            announcementReceiptPaperId = filesqr.getAnnouncementReceiptPaperId();
            confirmAnnouncementPaperId = filesqr.getConfirmAnnouncementPaperId();
            confirmImportSatistPaperId = filesqr.getConfirmImportSatistPaperId();
        }
        String barcodeInfo = "";
        if (announcementReceiptPaperId != null && announcementReceiptPaperId > 0L) {
            AnnouncementReceiptPaperDAOHE announcementReceiptPaperDaohe = new AnnouncementReceiptPaperDAOHE();
            AnnouncementReceiptPaper announcementReceiptPaper = announcementReceiptPaperDaohe.findById(announcementReceiptPaperId);
            if (announcementReceiptPaper != null) {
                if (announcementReceiptPaper.getProductName() != null) {
                    barcodeInfo += "Tên sản phẩm: " + announcementReceiptPaper.getProductName();
                } else {
                    barcodeInfo += "Tên sản phẩm: N/A";
                }
                if (announcementReceiptPaper.getBusinessName() != null) {
                    barcodeInfo += "\nĐơn vị đăng ký: " + announcementReceiptPaper.getBusinessName();
                } else {
                    barcodeInfo += "\nĐơn vị đăng ký: N/A";
                }
                if (announcementReceiptPaper.getManufactureName() != null) {
                    barcodeInfo += "\nTên nhà sản xuất: " + announcementReceiptPaper.getManufactureName();
                } else {
                    barcodeInfo += "\nTên nhà sản xuất: N/A";
                }
                if (announcementReceiptPaper.getManufactureAdd() != null) {
                    barcodeInfo += "\nĐịa chỉ: " + announcementReceiptPaper.getManufactureAdd();
                } else {
                    barcodeInfo += "\nĐịa chỉ: N/A";
                }
                if (announcementReceiptPaper.getNationName() != null) {
                    barcodeInfo += "\nTên nước xuất xứ: " + announcementReceiptPaper.getNationName();
                } else {
                    //barcodeInfo += "\nTên nước xuất xứ: N/A";
                }
//                if (announcementReceiptPaper.getMatchingTarget() != null) {
//                    barcodeInfo += "\nSố qui chuẩn KT/QĐATTP: " + announcementReceiptPaper.getMatchingTarget();
//                } else {
//                    barcodeInfo += "\nSố qui chuẩn KT/QĐATTP: N/A";
//                }
                if (announcementReceiptPaper.getReceiptDeptName() != null) {
                    barcodeInfo += "\nĐơn vi cấp chứng nhận: " + announcementReceiptPaper.getReceiptDeptName();
                } else {
                    barcodeInfo += "\nĐơn vi cấp chứng nhận: N/A";
                }
                if (announcementReceiptPaper.getReceiptNo() != null) {
                    barcodeInfo += "\nSố công bố: " + announcementReceiptPaper.getReceiptNo();
                } else {
                    barcodeInfo += "\nSố công bố: N/A";
                }
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                if (announcementReceiptPaper.getEffectiveDate() != null) {
                    barcodeInfo += "\nNgày cấp: " + formatter.format(announcementReceiptPaper.getReceiptDate());
                } else {
                    barcodeInfo += "\nNgày cấp: N/A";
                }
            } else {
                barcodeInfo = "Sản phẩm hiện tại chưa có thông tin";
            }
        } else if (confirmAnnouncementPaperId != null && confirmAnnouncementPaperId > 0L) {
//            ConfirmAnnouncementPaperDAOHE confirmAnnouncementPaperDaohe = new ConfirmAnnouncementPaperDAOHE();
//            ConfirmAnnouncementPaper confirmAnnouncementPaper = confirmAnnouncementPaperDaohe.findById(confirmAnnouncementPaperId);
            barcodeInfo = "Sản phẩm hiện tại chưa có thông tin";
        } else if (confirmImportSatistPaperId != null && confirmImportSatistPaperId > 0L) {
//            ConfirmImportSatistPaperDAOHE confirmImportSatistPaperDaohe = new ConfirmImportSatistPaperDAOHE();
//            ConfirmImportSatistPaper confirmImportSatistPaper = confirmImportSatistPaperDaohe.findById(announcementReceiptPaperId);
            barcodeInfo = "Sản phẩm hiện tại chưa có thông tin";
        } else {
            barcodeInfo = "Sản phẩm hiện tại chưa có thông tin";
        }
//        byte[] bAvatar = filebo1.getQrCode();
//
//        try {
//            FileOutputStream fos = new FileOutputStream("C:\\QR_Code.PNG");
//            fos.write(bAvatar);
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String dir = rb.getString("directoryQR");
        File folderExisting = new File(dir);
        if (!folderExisting.isDirectory()) {
            folderExisting.mkdir();
        }
        if (folderExisting.isDirectory()) {//tao folder theo ngay thang
            File temp = new File(dir);
            if (!temp.isDirectory()) {
                temp.mkdirs();
            }
        }
        Charset charset = Charset.forName("UTF-8");
        CharsetEncoder encoder = charset.newEncoder();
        byte[] b = null;
        try {// Convert a string to UTF-8 bytes in a ByteBuffer
            ByteBuffer bbuf = encoder.encode(
                    CharBuffer.wrap(barcodeInfo));
            b = bbuf.array();
        } catch (CharacterCodingException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        String data;
        String fName = dir + File.separatorChar + filesqr.getFileCode() + "_QRCode.PNG";
        try {
            data = new String(b, "UTF-8");
            // get a byte matrix for the data
            BitMatrix matrix = null;
            int h = 200;
            int w = 200;
            Writer writer = new MultiFormatWriter();
            try {
                Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>(2);
                hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                matrix = writer.encode(data, BarcodeFormat.QR_CODE, w, h, hints);
            } catch (com.google.zxing.WriterException ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
            // change this path to match yours (this is my mac home folder, you can use: c:\\qr_png.png if you are on windows)
            File file = new File(fName);
            try {
                MatrixToImageWriter.writeToFile(matrix, "PNG", file);//System.out.println("printing to " + file.getAbsolutePath());
            } catch (IOException ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
        } catch (UnsupportedEncodingException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        //save image into database
        File file = new File(fName);
        byte[] bFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            //convert file into array of bytes
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (IOException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            Logger.getLogger(FilesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Files filebo = filehe.findById(form.getFileId());
        filebo.setQrCode(bFile);
        getSession().update(filebo);
        file.deleteOnExit();
    }

    public void getBarcodeImg() {
        try {
            Long fileId = signForm.getFileId();
            HttpServletRequest request = getRequest();
//            HttpServletResponse response = getResponse();
            FilesDAOHE filesdaohe = new FilesDAOHE();
            Files filesbo = filesdaohe.findById(fileId);
//            response.setContentType(UploadFile.getCustomContentType());
//            response.getOutputStream().write(UploadFile.getCustomImageInBytes(filesbo.getFileCode(), request));
//            response.getOutputStream().flush();

            StringBuilder sb = new StringBuilder();
            sb.append("data:image/png;base64,");
            sb.append(org.apache.commons.codec.binary.StringUtils.newStringUtf8(Base64.encodeBase64(UploadFile.getCustomImageInBytes(filesbo.getFileCode(), request), false)));
            getRequest().setAttribute("imgsrc", sb.toString());
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
    }

    /**
     * chuyen doi list sang json cho multiselect
     *
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
            Logger.getLogger(FilesDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return jsonArray.toString();
    }

    /**
     * load file attach cua user
     *
     * @return
     */
    public String loadUserAttachs() {
        try {
            Base64 decoder = new Base64();
            String UserAttachArr = new String(decoder.decode(getRequest().getParameter("lstUserAttach").replace("_", "+").getBytes()), "UTF-8");
            FilesDAOHE fdhe = new FilesDAOHE();
            List lst = fdhe.getAttachsOfUserAttach(UserAttachArr, getUserId());
            List lstAttach = new ArrayList();
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            for (int i = 0; i < lst.size(); i++) {
                VoAttachs bo = new VoAttachs();
                bo.setCategoryName(((UserAttachs) lst.get(i)).getAttachName());
                bo.setAttachDes(((UserAttachs) lst.get(i)).getDescription());
                bo.setAttachName(((UserAttachs) lst.get(i)).getFileName());
                bo.setAttachPath(((UserAttachs) lst.get(i)).getAttachPath());
                bo.setIsActive(Constants.ACTIVE_STATUS.ACTIVE);
                bo.setUserCreateId(this.getUserId());
                bo.setCreateDate(daoHe.getSysdate());
                Long id = daoHe.create(bo);
                getRequest().setAttribute("attachId", id);
                getRequest().setAttribute("fileName", ((UserAttachs) lst.get(i)).getFileName());
                lstAttach.add(bo);
                String idSession = (String) getRequest().getSession().getAttribute("idSession");
                if (idSession == null) {
                    idSession = "";
                }
                idSession += id.toString() + ";";
                getRequest().getSession().setAttribute("idSession", idSession);
            }
            jsonDataGrid.setItems(lstAttach);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }

        return GRID_DATA;
    }

    //!140616
    //import from excel - hieptq
    /**
     * import du lieu tu excel
     *
     * @return
     */
    public String importFileFromExcel() throws FileNotFoundException, IOException, ParseException {
        List fileInfo = new ArrayList();
        String strReturn = ERROR_PERMISSION;
//        TechnicalStandard ts = new TechnicalStandard();
        TechnicalStandardDAOHE tshe = new TechnicalStandardDAOHE();
        String err = "";
        Long attachId = Long.parseLong(getRequest().getParameter("attachId"));//get attactId
        VoAttachs att = (VoAttachs) getSession().get("com.viettel.voffice.database.BO.VoAttachs", attachId);//Attachs BO
        if (att == null) {
            fileInfo.add("File not found");
            err += "File not found";
        } else {
            Category item;
            ResourceBundle rb = ResourceBundle.getBundle("config");//get link tuong doi
            String dir = rb.getString("directoryExcel");
            String linkFile = att.getAttachPath();
            linkFile = dir + linkFile;
            createForm.setPath(linkFile);
            InputStream myxls = new FileInputStream(linkFile);//get file excel
            XSSFWorkbook wb = new XSSFWorkbook(myxls);

            XSSFRow row = null;
            String matchingTarget = null;
            XSSFCell productName = null;
            XSSFCell businessTaxCode = null;
            XSSFCell manufactorAddress = null;
            XSSFCell manufactorName = null;
            XSSFCell manufactorTel = null;
            XSSFCell manufactorFax = null;
            XSSFCell manufactorEmail = null;
            XSSFCell nationName = null;
            XSSFCell signer = null;
            XSSFCell assessmentMethod = null;
            XSSFCell annoucementNo = null;

            XSSFCell pushlishDate = null;
            XSSFCell nationCompanyName = null;
            XSSFCell nationCompanyAddress = null;
            try {

                XSSFSheet sheet = wb.getSheetAt(0);
                try {
//                    XSSFSheet sheet1 = wb.getSheetAt(1);
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
//                    log.error(e.getMessage());
                    err += "Không tìm thấy Sheet Chi tiết sản phẩm, ";
                }

                try {
//                    XSSFSheet sheet2 = wb.getSheetAt(2);
                } catch (Exception ex) {
//                    log.error(e.getMessage());
                    LogUtil.addLog(ex);//binhnt sonar a160901
                    err += "Không tìm thấy Sheet Chỉ tiêu chất lượng chủ yếu, ";
                }

                try {
//                    XSSFSheet sheet3 = wb.getSheetAt(3);
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
//                    log.error(e.getMessage());
                    err += "Không tìm thấy Sheet Chỉ tiêu vi sinh vật, ";
                }

                try {
//                    XSSFSheet sheet4 = wb.getSheetAt(4);
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
//                    log.error(e.getMessage());
                    err += "Không tìm thấy Sheet Hàm lượng kim loại nặng, ";
                }
                try {
//                    XSSFSheet sheet5 = wb.getSheetAt(5);
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
//                    log.error(e.getMessage());
                    err += "Không tìm thấy Sheet Hàm lượng hóa chất, ";
                }
                try {
//                    XSSFSheet sheet6 = wb.getSheetAt(6);
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
//                    log.error(e.getMessage());
                    err += "Không tìm thấy Sheet Kế hoạch kiểm soát chất lượng, ";
                }

                if (sheet == null) {
                    err += "Không tìm thấy Sheet Bản công bố, ";
                } else {
                    String sheetName = sheet.getSheetName();
                    if (!"Ban_Cong_bo".equals(sheetName)) {
                        err += "Sai tên sheet Bản công bố, ";
                    }
                }

//                XSSFRow firstRow = sheet.getRow(1);
                int rowNums = sheet.getLastRowNum();
//                UsersDAOHE sdhe = new UsersDAOHE();
//                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                row = sheet.getRow(1);
                businessTaxCode = row.getCell((short) 1);
                productName = row.getCell((short) 3);
                row = sheet.getRow(4);
                manufactorName = row.getCell((short) 1);
                manufactorAddress = row.getCell((short) 3);
                row = sheet.getRow(5);
                manufactorTel = row.getCell((short) 1);
                manufactorFax = row.getCell((short) 3);
                row = sheet.getRow(6);
                manufactorEmail = row.getCell((short) 1);
                nationName = row.getCell((short) 3);
                row = sheet.getRow(7);
                nationCompanyName = row.getCell((short) 1);
                nationCompanyAddress = row.getCell((short) 3);

                row = sheet.getRow(10);
                annoucementNo = row.getCell((short) 1);
                pushlishDate = row.getCell((short) 3);
                row = sheet.getRow(11);
                signer = row.getCell((short) 1);
                assessmentMethod = row.getCell((short) 3);
                matchingTarget = "";
                String standardCode;
                for (int i = 12; i < rowNums; i++) {
                    row = sheet.getRow(i);
                    if (row.getCell((short) 1).toString() != "") {
                        XSSFCell standardCodeCell = row.getCell((short) 1);
                        standardCode = standardCodeCell.getRichStringCellValue().toString();
                        if (tshe.findStandardByCode(standardCode)) {
                            XSSFCell matchingTargetCell = row.getCell((short) 2);
                            matchingTarget += matchingTargetCell.getRichStringCellValue() + ";";
                        } else {
                            err += "Quy chuản (quy định) " + standardCode + " không chính xác ! ";
                            break;
                        }
                    } else {
                        break;
                    }
                }

            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                log.error(e.getMessage());
                err += "lỗi tab bản công bố hợp quy ";
            }
            if (matchingTarget != "" && matchingTarget != null) {
                matchingTarget = matchingTarget.substring(0, matchingTarget.length() - 1);
            }
            //tab chi tiet san pham
            XSSFCell productNo = null;
            XSSFCell productStatus = null;
            XSSFCell productColor = null;
            XSSFCell productSmell = null;
            XSSFCell productOtherstatus = null;
            XSSFCell productType = null;
            XSSFCell otherTarget = null;
            XSSFCell component = null;
            XSSFCell timeinuse = null;
            XSSFCell useage = null;
            XSSFCell objectInUse = null;
            XSSFCell guideline = null;
            //XSSFCell packageRecipe = null;
            XSSFCell packageMaterial = null;
            XSSFCell productProcess = null;
            XSSFCell counterfeitDistinctive = null;
            XSSFCell origin = null;
            XSSFCell signDate = null;
            XSSFCell signer_productdetails = null;
            XSSFCell chemicalTargetUnwanted = null;
            try {

                XSSFSheet sheet1 = wb.getSheetAt(1);
                if (sheet1 == null) {
                    err += "Không tìm thấy Sheet Chi tiết sản phẩm, ";

                } else {
                    String sheetName = sheet1.getSheetName();
                    if (!"Chi_tiet_san_pham".equals(sheetName)) {
                        err += "Sai tên Sheet Chi tiết sản phẩm, ";
                    }
                }

                row = sheet1.getRow(1);
                productType = row.getCell((short) 1);
                productNo = row.getCell((short) 3);
                row = sheet1.getRow(4);
                productStatus = row.getCell((short) 1);
                productColor = row.getCell((short) 3);
                row = sheet1.getRow(5);
                productSmell = row.getCell((short) 1);
                productOtherstatus = row.getCell((short) 3);
                row = sheet1.getRow(13);
                otherTarget = row.getCell((short) 1);
                row = sheet1.getRow(14);
                component = row.getCell((short) 1);
                timeinuse = row.getCell((short) 3);
                row = sheet1.getRow(15);
                useage = row.getCell((short) 1);
                objectInUse = row.getCell((short) 3);
                row = sheet1.getRow(16);
                guideline = row.getCell((short) 1);
                packageMaterial = row.getCell((short) 3);
                row = sheet1.getRow(17);
                productProcess = row.getCell((short) 3);
                //packageRecipe = row.getCell((short) 1);
                row = sheet1.getRow(18);
                counterfeitDistinctive = row.getCell((short) 1);
                origin = row.getCell((short) 3);
                row = sheet1.getRow(19);
                signDate = row.getCell((short) 1);
                signer_productdetails = row.getCell((short) 3);
                // bo sung ham luong hoa chat khong mong muon
                XSSFSheet sheet5 = wb.getSheetAt(5);

                int rowNums = sheet5.getLastRowNum();
                do {
                    row = sheet5.getRow(rowNums);
                    chemicalTargetUnwanted = row.getCell((short) 2);
                    rowNums--;
                } while (chemicalTargetUnwanted == null);

//                chemicalTargetUnwanted = row.getCell((short) 2);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                log.error(e.getMessage());
                err += "Lỗi tab chi tiết sản phẩm ";
            }
            // do du lieu vao form
            Long fileId = getRequest().getParameter("fileId") == null ? 0L : Long.parseLong(getRequest().getParameter("fileId"));
            Long fileType = getRequest().getParameter("fileType") == null ? 0L : Long.parseLong(getRequest().getParameter("fileType"));
            if (fileType > 0L && fileId > 0L) {
                createForm = new FilesForm();
                createForm.setFileType(fileType);
                createForm.setFileId(fileId);
            }
            UsersDAOHE udhe = new UsersDAOHE();
            Users user = udhe.findById(getUserId());

            BusinessDAOHE bdhe = new BusinessDAOHE();
            Business bus = bdhe.findById(user.getBusinessId());

            if (createForm.getFileId() != null && createForm.getFileId() > 0l) {
                FilesDAOHE fdhe = new FilesDAOHE();
                createForm = fdhe.getFilesDetail(createForm.getFileId());
                if (!createForm.getFileType().equals(0L)) {
                    ProcedureDAOHE cdhe = new ProcedureDAOHE();
                    List lstTTHC = cdhe.getProcedureForChange(createForm.getFileType());
                    lstCategory = new ArrayList();
                    lstCategory.addAll(lstTTHC);
                    lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                    getRequest().setAttribute("lstFileType", lstCategory);

                }
            }

            if (createForm.getFileType() != null && createForm.getFileType() > 0l) {
                ProcedureDAOHE pdhe = new ProcedureDAOHE();
                CategoryDAOHE cdhe = new CategoryDAOHE();
                TechnicalStandardDAOHE tdhe = new TechnicalStandardDAOHE();
                FilesDAOHE fdhe = new FilesDAOHE();
                if (!fileType.equals(0L)) {
                    createForm.setFileType(fileType);
                }
                Procedure tthc = pdhe.findById(createForm.getFileType());
                if (tthc != null) {

                    lstProductType = cdhe.findAllCategory("SP");
                    lstUnit = cdhe.findAllCategory("DVI");

                    lstStandard = tdhe.findAllStandard();
                    String lstDepts = convertToJSONData(lstStandard, "vietnameseName", "vietnameseName");
                    getRequest().setAttribute("lstStandard", lstDepts);

                    UserAttachsDAOHE uahe = new UserAttachsDAOHE();
                    lstUserAttach = uahe.findAllUserAttach(getUserId());
                    String lstUserAttachs = convertToJSONData(lstUserAttach, "attachName", "attachName");
                    getRequest().setAttribute("lstUserAttach", lstUserAttachs);
                    if (lstUserAttachs.trim().length() > 0) {
                        createForm.setCountUA(1L);
                    } else {
                        createForm.setCountUA(0L);
                    }
                    getRequest().setAttribute("lstProductType", lstProductType);
                    getRequest().setAttribute("lstUnit", lstUnit);

                    String fileLst = tthc.getFileList();
                    getRequest().setAttribute("fileList", com.viettel.common.util.StringUtils.removeHTML(fileLst));
                    getRequest().setAttribute("agencyName", getDepartmentName());
                    getRequest().setAttribute("fileNameFull", tthc.getName());
                    strReturn = tthc.getDescription();
                    if (createForm.getAnnouncement() != null) {
                        if (createForm.getAnnouncement().getAnnouncementNo() != null && createForm.getAnnouncement().getAnnouncementNo().length() > 0l) {
                            return strReturn;
                        }
                    }
                    if (strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01)
                            || strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03)
                            || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP)
                            || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN)
                            || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP)
                            || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN)
                            || strReturn.equals(Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP)
                            || strReturn.equals(Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT)
                            || strReturn.equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP)
                            || strReturn.equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN)
                            || strReturn.equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN)) {
                        String announcementNoStr = fdhe.getReceiptNoNew(getUserId(), getUserLogin(), createForm.getFileType());
                        createForm.setAnnouncement(new AnnouncementForm());
                        createForm.getAnnouncement().setAnnouncementNo(announcementNoStr);
                        // thong tin doanh nghiep
                        createForm.getAnnouncement().setBusinessAddress(bus.getBusinessAddress());
                        createForm.getAnnouncement().setBusinessFax(bus.getBusinessFax());
                        createForm.getAnnouncement().setBusinessName(bus.getBusinessName());
                        createForm.getAnnouncement().setBusinessTelephone(bus.getBusinessTelephone());
                        createForm.getAnnouncement().setBusinessEmail(bus.getUserEmail());
                        createForm.getAnnouncement().setBusinessLicence(bus.getBusinessLicense());

                        // ho so cap lai 7-11
                        createForm.setReIssueForm(new ReIssueFormForm());
                        createForm.getReIssueForm().setBusinessName(bus.getBusinessName());
                        createForm.getReIssueForm().setIdentificationNumber(bus.getBusinessLicense());
                        createForm.getReIssueForm().setAddress(bus.getBusinessAddress());
                        createForm.getReIssueForm().setEmail(bus.getUserEmail());
                        createForm.getReIssueForm().setTelephone(bus.getBusinessTelephone());
                        createForm.getReIssueForm().setFax(bus.getBusinessFax());
                        //set thong tin tu excel

                        try {
                            if (businessTaxCode != null 
                                    && user.getUserName().equals(businessTaxCode.toString())) {
                                if (matchingTarget != "" && matchingTarget != null) {
                                    createForm.getAnnouncement().setMatchingTarget(matchingTarget.toString());
                                }
                                createForm.getAnnouncement().setProductName(productName.toString());
                                createForm.getAnnouncement().setManufactureAddress(manufactorAddress.toString());
                                createForm.getAnnouncement().setManufactureName(manufactorName.toString());
                                createForm.getAnnouncement().setManufactureTel(manufactorTel.toString());
                                createForm.getAnnouncement().setManufactureFax(manufactorFax.toString());
                                createForm.getAnnouncement().setManufactureEmail(manufactorEmail.toString());
                                createForm.getAnnouncement().setNationName(nationName.toString());
                                createForm.getAnnouncement().setSigner(signer.toString());
                                createForm.getAnnouncement().setNationCompanyAddress(nationCompanyAddress.toString());
                                createForm.getAnnouncement().setNationCompanyName(nationCompanyName.toString());
                                createForm.getAnnouncement().setAssessmentMethod(assessmentMethod.toString());
                                if (pushlishDate.toString() != null && pushlishDate.toString().length() > 0) {
                                    createForm.getAnnouncement().setPublishDate(DateTimeUtils.convertStringToTime(pushlishDate.toString(), "dd/MM/yyyy"));
                                }
                                createForm.getAnnouncement().setAnnouncementNo(annoucementNo.toString());
                                //tab thong tin chi tiet
                                createForm.setDetailProduct(new DetailProductForm());
                                createForm.getDetailProduct().setProductNo(productNo.toString());
                                createForm.getDetailProduct().setProductStatus(productStatus.toString());
                                createForm.getDetailProduct().setProductColor(productColor.toString());
                                createForm.getDetailProduct().setProductSmell(productSmell.toString());
                                createForm.getDetailProduct().setProductOtherStatus(productOtherstatus.toString());

                                item = cdhe.findCategoryByName("SP", productType.toString());
                                if (item != null) {
                                    createForm.getDetailProduct().setProductType(item.getCategoryId());
                                } else {
                                    err += "Danh mục " + productType.toString() + " không chính xác, ";
                                }

                                createForm.getDetailProduct().setOtherTarget(otherTarget.toString());
                                createForm.getDetailProduct().setComponents(component.toString());
                                createForm.getDetailProduct().setTimeInUse(timeinuse.toString());
                                createForm.getDetailProduct().setUseage(useage.toString());
                                createForm.getDetailProduct().setObjectUse(objectInUse.toString());
                                createForm.getDetailProduct().setGuideline(guideline.toString());
                                //createForm.getDetailProduct().setPackageRecipe(packageRecipe.toString());
                                createForm.getDetailProduct().setPackateMaterial(packageMaterial.toString());
                                createForm.getDetailProduct().setProductionProcess(productProcess.toString());
                                createForm.getDetailProduct().setCounterfeitDistinctive(counterfeitDistinctive.toString());
                                createForm.getDetailProduct().setOrigin(origin.toString());
                                if (signDate.toString() != null && signDate.toString().length() > 0) {
                                    createForm.getDetailProduct().setSignDate(DateTimeUtils.convertStringToTime(signDate.toString(), "dd/MM/yyyy"));
                                }
                                createForm.getDetailProduct().setSigner(signer_productdetails.toString());
                                createForm.getDetailProduct().setChemicalTargetUnwanted(chemicalTargetUnwanted.toString());
                                createForm.setStatusExcel(err += "Thêm mới bản công bố hợp quy thành công ");
                            } else {
                                createForm.setStatusExcel(err += "Mã số thuế không chính xác ");
                            }
                        } catch (Exception ex) {
//                            log.error(parseException);
                            LogUtil.addLog(ex);//binhnt sonar a160901
                            createForm.setStatusExcel(err += "Thêm mới bản công bố hợp quy không thành công ");
                        }
                    }
                }
            }
        }
        CategoryDAOHE ctdhe = new CategoryDAOHE();
        Category cate = ctdhe.findCategoryByTypeAndCode("SP", "TPCN");
        Category cateTL = ctdhe.findCategoryByTypeAndCode("SP", "TL");
        List<Category> cate1 = ctdhe.findCategoryByTypeAndCodeNew("SP", "DBT");
        String dbtId = "";
        for (int i = 0; i < cate1.size(); i++) {
            dbtId += cate1.get(i).getCategoryId().toString() + ";";
        }
        Long tpcnId = cate.getCategoryId();
        Long tlId = cateTL.getCategoryId();
        FeeDAOHE fdhe1 = new FeeDAOHE();
        Fee findfee1 = fdhe1.findFeeByCode("TPDB");
        Long priceTPDB = findfee1.getPrice();
        Fee findfee2 = fdhe1.findFeeByCode("TPCN");
        Long priceTPCN = findfee2.getPrice();
        Fee findfee3 = fdhe1.findFeeByCode("TPK");
        Long priceETC = findfee3.getPrice();
        getRequest().setAttribute("dbtId", dbtId);
        getRequest().setAttribute("tpcnId", tpcnId);
        getRequest().setAttribute("tlId", tlId);
        getRequest().setAttribute("priceTPCN", priceTPCN);
        getRequest().setAttribute("priceTPDB", priceTPDB);
        getRequest().setAttribute("priceETC", priceETC);

        return strReturn;
    }

    //load mainly target from excel - hieptq
    /**
     * load mainly target tu excel
     *
     * @return
     */
    public String loadMainlyTargetExcel() throws FileNotFoundException, IOException {
        // mainly target
        Category item = new Category();
        String linkFile = getRequest().getParameter("path");
        String fileError = "";
        List customInfo = new ArrayList();
        InputStream myxls = new FileInputStream(linkFile);//get file excel
        XSSFWorkbook wb = new XSSFWorkbook(myxls);
        List<MainlyTarget> lstMainlyTarget = null;
        try {
            XSSFSheet sheet2 = wb.getSheetAt(2);
            if (sheet2 == null) {
                fileError += "Không tìm thấy Sheet Chỉ tiêu chất lượng chủ yếu, ";
            } else {
                String sheetName = sheet2.getSheetName();
                if (!"Chi_tieu_chat_luong_chu_yeu".equals(sheetName)) {
                    fileError += "Sai tên Sheet Chỉ tiêu chất lượng chủ yếu, ";
                }
            }

            XSSFRow row;
            int rowNums2 = sheet2.getLastRowNum();
            lstMainlyTarget = new ArrayList<MainlyTarget>();
            CategoryDAOHE cdhed = new CategoryDAOHE();

            for (int i = 2; i < rowNums2; i++) {
                row = sheet2.getRow(i);
                if (row.getCell((short) 1) != null && row.getCell((short) 1).toString().trim() != "") {
                    MainlyTarget temp = new MainlyTarget();
                    XSSFCell targetName = row.getCell((short) 1);
                    XSSFCell unitId = row.getCell((short) 2);
                    XSSFCell publishLevel = row.getCell((short) 3);
                    XSSFCell meetLevel = row.getCell((short) 4);
                    item = cdhed.findCategoryByName("DVI", unitId.toString());
                    if (item != null) {
                        temp.setMeetLevel(meetLevel.toString());
                        temp.setTargetName(targetName.toString());
                        temp.setUnitId(item.getCategoryId().toString());
                        temp.setPublishLevel(publishLevel.toString());
                        lstMainlyTarget.add(temp);
                    } else {
                        fileError += "Danh mục đơn vị " + unitId.toString() + " không chính xác, ";
                    }
                } else {
                    break;
                }
            }
            fileError += "Thêm mới các chỉ tiêu chất lượng chủ yếu thành công ";
            customInfo.add(fileError);
        } catch (Exception ex) {
            fileError += "Thêm mới các chỉ tiêu chất lượng chủ yếu không thành công ";
            customInfo.add(fileError);
//            log.error(e.getMessage());
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        jsonDataGrid.setCustomInfo(customInfo);
        jsonDataGrid.setItems(lstMainlyTarget);
        return GRID_DATA;
    }

    // load product target excel
    /**
     * load product target tu excel
     *
     * @return
     */
    public String loadProductTargetExcel() throws FileNotFoundException, IOException {

        Category item = new Category();
        String linkFile = getRequest().getParameter("path");
        String fileError = "";
        List customInfo = new ArrayList();
        InputStream myxls = new FileInputStream(linkFile);//get file excel
        XSSFWorkbook wb = new XSSFWorkbook(myxls);
        List<ProductTarget> lstProductTarget = null;
        try {
            XSSFSheet sheet3 = wb.getSheetAt(3);
            if (sheet3 == null) {
                fileError += "Không tìm thấy Sheet Chỉ tiêu vi sinh vật, ";
            } else {
                String sheetName = sheet3.getSheetName();
                if (!"Chi_tieu_vi_sinh_vat".equals(sheetName)) {
                    fileError += "Sai tên Sheet Chỉ tiêu vi sinh vật, ";
                }
            }
            XSSFRow row;
            int rowNums3 = sheet3.getLastRowNum();
            lstProductTarget = new ArrayList<ProductTarget>();
            CategoryDAOHE cdhed = new CategoryDAOHE();
            // vi sinh vat
            for (int i = 2; i < rowNums3; i++) {
                row = sheet3.getRow(i);
                if (row.getCell((short) 1) != null 
                        && !"".equals(row.getCell((short) 1).toString().trim())) {
                    ProductTarget temp = new ProductTarget();
                    XSSFCell targetName = row.getCell((short) 1);
                    XSSFCell unitId = row.getCell((short) 2);
                    XSSFCell maxLevel = row.getCell((short) 3);
                    item = cdhed.findCategoryByName("DVI", unitId.toString().trim());
                    if (item != null) {
                        temp.setTargetName(targetName.toString());
                        temp.setUnitId(item.getCategoryId().toString());
                        temp.setMaxLevel(maxLevel.toString());
                        temp.setTargetType(1l);
                        lstProductTarget.add(temp);
                    } else {
                        fileError += "Danh mục đơn vị " + unitId.toString() + " không chính xác, ";
                    }
                } else {
                    break;
                }

            }
            //kim loai nang

            XSSFSheet sheet4 = wb.getSheetAt(4);
            if (sheet4 == null) {
                fileError += "Không tìm thấy Sheet Hàm lượng kim loại nặng, ";
            } else {
                String sheetName2 = sheet4.getSheetName();
                if (!"Ham_luong_kim_loai_nang".equals(sheetName2)) {
                    fileError += "Sai tên Sheet Hàm lượng kim loại nặng, ";
                }
            }
            int rowNums4 = sheet4.getLastRowNum();
            for (int i = 2; i < rowNums4; i++) {
                row = sheet4.getRow(i);
                if (row.getCell((short) 1) != null 
                        && !"".equals(row.getCell((short) 1).toString().trim())) {
                    ProductTarget temp = new ProductTarget();
                    XSSFCell targetName = row.getCell((short) 1);
                    XSSFCell unitId = row.getCell((short) 2);
                    XSSFCell maxLevel = row.getCell((short) 3);
                    item = cdhed.findCategoryByName("DVI", unitId.toString().trim());
                    if (item != null) {
                        temp.setTargetName(targetName.toString());
                        temp.setUnitId(item.getCategoryId().toString());
                        temp.setMaxLevel(maxLevel.toString());
                        temp.setTargetType(2l);
                        lstProductTarget.add(temp);
                    } else {
                        fileError += "Danh mục đơn vị " + unitId.toString() + " không chính xác, ";
                    }
                } else {
                    break;
                }
            }

            //hoa chat khong mong muon
            XSSFSheet sheet5 = wb.getSheetAt(5);
            if (sheet5 == null) {
                fileError += "Không tìm thấy Sheet Hàm lượng hóa chất không mong muốn, ";
            } else {
                String sheetName1 = sheet5.getSheetName();

                if (!"Ham_luong_hoa_chat".equals(sheetName1)) {
                    fileError += "Sai tên Sheet Hàm lượng hóa chất không mong muốn, ";
                }
            }
            int rowNums5 = sheet5.getLastRowNum();
            for (int i = 2; i < rowNums5; i++) {
                row = sheet5.getRow(i);
                if (row.getCell((short) 1) != null 
                        && !"".equals(row.getCell((short) 1).toString().trim())) {
                    ProductTarget temp = new ProductTarget();
                    XSSFCell targetName = row.getCell((short) 1);
                    XSSFCell unitId = row.getCell((short) 2);
                    XSSFCell maxLevel = row.getCell((short) 3);
                    item = cdhed.findCategoryByName("DVI", unitId.toString().trim());
                    if (item != null) {
                        temp.setTargetName(targetName.toString());
                        temp.setUnitId(item.getCategoryId().toString());
                        temp.setMaxLevel(maxLevel.toString());
                        temp.setTargetType(3l);
                        lstProductTarget.add(temp);
                    } else {
                        fileError += "Danh mục đơn vị " + unitId.toString() + " không chính xác, ";
                    }
                } else {
                    break;
                }
            }
            fileError += "Thêm mới các chỉ tiêu vi sinh vật, hàm lượng kim loại nặng, hàm lượng hóa chất không mong muốn thành công ";
            customInfo.add(fileError);

        } catch (Exception ex) {
            fileError += "Thêm mới các chỉ tiêu vi sinh vật, hàm lượng kim loại nặng, hàm lượng hóa chất không mong muốn không thành công ";
            customInfo.add(fileError);
//            log.error(e.getMessage());
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        jsonDataGrid.setCustomInfo(customInfo);
        jsonDataGrid.setItems(lstProductTarget);
        return GRID_DATA;
    }

    //load quality control plan
    /**
     * load quality control plan excel
     *
     * @return
     */
    public String loadQualityControlsExcel() throws FileNotFoundException, IOException {
        // mainly target
//        Category item = new Category();
        String linkFile = getRequest().getParameter("path");
        String fileError = "";
        List customInfo = new ArrayList();
        InputStream myxls = new FileInputStream(linkFile);//get file excel
        XSSFWorkbook wb = new XSSFWorkbook(myxls);
        List<QualityControlPlan> lstQualityControl = null;
        try {
            XSSFSheet sheet6 = wb.getSheetAt(6);
            if (sheet6 == null) {
                fileError += "Không tìm thấy Sheet Kế hoạch kiểm soát, ";
            } else {
                String sheetName1 = sheet6.getSheetName();

                if (!"Ke_Hoach_Kiem_soat".equals(sheetName1)) {
                    fileError += "Không tìm thấy Sheet Kế hoạch kiểm soát, ";
                }
            }
            XSSFRow row;
            int rowNums6 = sheet6.getLastRowNum();
            lstQualityControl = new ArrayList<QualityControlPlan>();
//            CategoryDAOHE cdhed = new CategoryDAOHE();

            for (int i = 2; i < rowNums6; i++) {
                row = sheet6.getRow(i);
                if (row.getCell((short) 1).toString().trim() != "") {
                    QualityControlPlan temp = new QualityControlPlan();
                    XSSFCell processDetails = row.getCell((short) 1);
                    XSSFCell controlTarget = row.getCell((short) 2);
                    XSSFCell technicalRegulation = row.getCell((short) 3);
                    XSSFCell patternFrequence = row.getCell((short) 4);
                    XSSFCell testDevice = row.getCell((short) 5);
                    XSSFCell testMethod = row.getCell((short) 6);
//                    XSSFCell noteForm = row.getCell((short) 7);
                    XSSFCell note = row.getCell((short) 8);
                    temp.setProductProcessDetail(processDetails.toString());
                    temp.setControlTarget(controlTarget.toString());
                    temp.setTechnicalRegulation(technicalRegulation.toString());
                    temp.setPatternFrequence(patternFrequence.toString());
                    temp.setTestDevice(testDevice.toString());
                    temp.setTestMethod(testMethod.toString());
                    temp.setNote(note.toString());
                    temp.setNoteForm(note.toString());
                    lstQualityControl.add(temp);
                } else {
                    break;
                }

            }
            fileError = "Thêm mới kế hoạch kiểm soát chất lượng thành công ";
            customInfo.add(fileError);
        } catch (Exception ex) {
            fileError = "Thêm mới kế hoạch kiểm soát chất lượng không thành công ";
            customInfo.add(fileError);
//            log.error(e.getMessage());
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        jsonDataGrid.setCustomInfo(customInfo);
        jsonDataGrid.setItems(lstQualityControl);
        return GRID_DATA;
    }
//140624-binhnt53 lay thong tin nhan vien tham dinh

    /**
     * thong tin lien lac chuyen vien
     *
     * @return
     */
    public String showStaffContact() {
        getGridInfo();
        String strObjectId = getRequest().getParameter("objectId");
        List customInfo = new ArrayList();
        Long objectId = 0l;
        Long staffId = 0l;
        try {
            objectId = Long.parseLong(strObjectId);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }

        ProcessDAOHE pdhe = new ProcessDAOHE();
        Process result = pdhe.getStaffEvaluate(objectId);
        if (result != null) {
            staffId = result.getReceiveUserId();
        }
        UsersDAOHE udaohe = new UsersDAOHE();
        Users u = udaohe.findById(staffId);
        try {
            customInfo.add(u.getFullName());
            customInfo.add(u.getEmail());
            customInfo.add(u.getTelephone());
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            customInfo.add("N/A");
            customInfo.add("N/A");
            customInfo.add("N/A");
        }
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    //140704 binhnt53 get noi dung comment vao tham dinh ho so
    /**
     * get noi dung comment tham dinh vao ho so
     *
     * @return
     */
    public String getCommentEvaluateForm() {
        getGridInfo();
        List customInfo = new ArrayList();

        String strObjectId = getRequest().getParameter("objectId");
        String strObjectType = getRequest().getParameter("objectType");
//        String strProductType = getRequest().getParameter("productType");

        Long objectId = 0l;
        Long objectType;
//        Long productType = 0L;
        try {
            objectId = Long.parseLong(strObjectId);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(en.getMessage());
        }
        try {
            objectType = Long.parseLong(strObjectType);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(en.getMessage());
            objectType = 0l;
        }
//        try {
//            productType = Long.parseLong(strProductType);
//        } catch (Exception en) {
//            log.error(en.getMessage());
//        }
        ProcessCommentDAOHE pcdhe = new ProcessCommentDAOHE();
        List<ProcessCommentForm> result = pcdhe.getLstCommentOfDocument(getUserId(), objectId, objectType, start, count, sortField);
        String legal = "";
        String foodSafetyQuality = "";
        String effectUtility = "";
        String standard = "";
        for (ProcessCommentForm t : result) {
            if (t.getCommentType().equals(Constants.COMMENT_TYPE.LEGAL)) {
                legal += t.getUserName() + ": " + t.getCommentText() + "\n";
            }
            if (t.getCommentType().equals(Constants.COMMENT_TYPE.FOOD_SAFETY_QUALITY)) {
                foodSafetyQuality += t.getUserName() + ": " + t.getCommentText() + "\n";
            }
            if (t.getCommentType().equals(Constants.COMMENT_TYPE.EFFECT_UTILITY)) {
                effectUtility += t.getUserName() + ": " + t.getCommentText() + "\n";
            }
            if (t.getCommentType().equals(Constants.COMMENT_TYPE.STANDARD)) {
                standard += t.getUserName() + ": " + t.getCommentText() + "\n";
            }
        }
        customInfo.add(legal);//0
        customInfo.add(foodSafetyQuality);//1
        customInfo.add(effectUtility);//2
        customInfo.add(standard);//3
        //160628 bo sung noi dung sua sau cong bo
        FilesDAOHE filesDaohe = new FilesDAOHE();
        Files filebo = filesDaohe.findById(objectId);
        String titleEditATTP = "";
        String contentsEditATTP = "";
        if (filebo != null) {
            if (filebo.getTitleEditATTP() != null
                    && !"".equals(filebo.getTitleEditATTP().trim())) {
                titleEditATTP = filebo.getTitleEditATTP();
            } else {
                titleEditATTP = filebo.getTitleEdit();
            }
            if (filebo.getContentsEditATTP() != null
                    && !"".equals(filebo.getContentsEditATTP().trim())) {
                contentsEditATTP = filebo.getContentsEditATTP();
            } else {
                contentsEditATTP = filebo.getContentsEdit();
            }
        }
        customInfo.add(titleEditATTP);//4
        customInfo.add(contentsEditATTP);//5
        //!160628
        /*
         CategoryDAOHE catedaohe = new CategoryDAOHE();
         Category catebo = catedaohe.findById(productType);
         if (catebo != null) {
         if (catebo.getCode() != null && catebo.getCode().equals("TPCN")) {
         customInfo.add(1);
         } else {
         customInfo.add(0);
         }
         } else {
         customInfo.add(0);
         } */
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }//!140704

    //get noi dung tham dinh - tham dinh lanh dao phong
    public String getCommentEvaluateFormByLeader() {
        getGridInfo();
        List customInfo = new ArrayList();
        String strObjectId = getRequest().getParameter("objectId");
        Long objectId = 0l;
        try {
            objectId = Long.parseLong(strObjectId);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(en.getMessage());
        }
        FilesDAOHE fdaohe = new FilesDAOHE();
        Files fbo = fdaohe.findById(objectId);
        EvaluationRecordsDAOHE erdaohe = new EvaluationRecordsDAOHE();

        EvaluationRecords erbo = erdaohe.findFilesByFileId(fbo);
        String legal = "";
        String foodSafetyQuality = "";
        String effectUtility = "";
        String standard = "";
        Long llegal = null;
        Long lfoodSafetyQuality = null;
        Long leffectUtility = null;
        Long lstandard = null;
        if (erbo != null && fbo != null) {
            legal = erbo.getLegalContent();
            foodSafetyQuality = erbo.getFoodSafetyQualityContent();
            effectUtility = erbo.getEffectUtilityContent();
            standard = fbo.getStaffRequest();
            lstandard = fbo.getEffectiveDate();
            llegal = erbo.getLegal();
            lfoodSafetyQuality = erbo.getFoodSafetyQuality();
            leffectUtility = erbo.getEffectUtility();
        }
        customInfo.add(legal);//0
        customInfo.add(foodSafetyQuality);//1
        customInfo.add(effectUtility);//2
        customInfo.add(standard);//3
        customInfo.add(lstandard);//4
        customInfo.add(llegal);//5
        customInfo.add(lfoodSafetyQuality);//6
        customInfo.add(leffectUtility);//7

//160628 bo sung noi dung sua sau cong bo
        FilesDAOHE filesDaohe = new FilesDAOHE();
        Files filebo = filesDaohe.findById(objectId);
        String titleEditATTP = "";
        String contentsEditATTP = "";
        if (filebo != null) {
            if (filebo.getTitleEditATTP() != null
                    && !"".equals(filebo.getTitleEditATTP().trim())) {
                titleEditATTP = filebo.getTitleEditATTP();
            } else {
                titleEditATTP = filebo.getTitleEdit();
            }
            if (filebo.getContentsEditATTP() != null
                    && !"".equals(filebo.getContentsEditATTP().trim())) {
                contentsEditATTP = filebo.getContentsEditATTP();
            } else {
                contentsEditATTP = filebo.getContentsEdit();
            }
        }
        customInfo.add(titleEditATTP);//8
        customInfo.add(contentsEditATTP);//9
//!160628

        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }//!140704
//140724 hien thi phi nop ho so - phi tham xet

    public String getCommentEvaluateFeedbackEvaluate() {
        getGridInfo();
        List customInfo = new ArrayList();
        String strObjectId = getRequest().getParameter("objectId");
        Long objectId = 0l;
        try {
            objectId = Long.parseLong(strObjectId);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(en.getMessage());
        }
        FilesDAOHE fdaohe = new FilesDAOHE();
        Files fbo = fdaohe.findById(objectId);
        EvaluationRecordsDAOHE erdaohe = new EvaluationRecordsDAOHE();

        EvaluationRecords erbo = erdaohe.findFilesByFileId(fbo);
        String staffContent = "";
        String leaderContent = "";
        if (erbo != null && fbo != null) {
            if (fbo.getStaffRequest() != null 
                    && !"null".equals(fbo.getStaffRequest().trim())) {
                staffContent += "* Ý kiến chung:" + "\n";
                staffContent += fbo.getStaffRequest() + "\n";
            }
//            else {
//                staffContent += "Không có nội dung." + "\n";
//            }            
            if (erbo.getLegalContent() != null 
                    && !"null".equals(erbo.getLegalContent())) {
                staffContent += "* Về pháp chế:" + "\n";
                staffContent += erbo.getLegalContent() + "\n";
            }
//            else {
//                staffContent += "Không có nội dung." + "\n";
//            }
            if (erbo.getFoodSafetyQualityContent() != null && !"null".equals(erbo.getFoodSafetyQualityContent())) {
                staffContent += "* Về chỉ tiêu chất lượng an toàn thực phẩm:" + "\n";
                staffContent += erbo.getFoodSafetyQualityContent() + "\n";
            }
//            else {
//                staffContent += "Không có nội dung." + "\n";
//            }            
            if (erbo.getEffectUtilityContent() != null && !"null".equals(erbo.getEffectUtilityContent())) {
                staffContent += "* Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng:" + "\n";
                staffContent += erbo.getEffectUtilityContent() + "\n";
            }
//            else {
//                staffContent += "Không có nội dung." + "\n";
//            }

            if (fbo.getLeaderRequest() != null && !"null".equals(fbo.getLeaderRequest())) {
                leaderContent += "* Ý kiến chung:" + "\n";
                leaderContent += fbo.getLeaderRequest() + "\n";
            }
//            else {
//                leaderContent += "Không có nội dung." + "\n";
//            }
            if (fbo.getLeaderStaffRequest() != null && !"null".equals(fbo.getLeaderStaffRequest())) {
                leaderContent += fbo.getLeaderStaffRequest() + "\n";
            }
//            else {
//                leaderContent += "Không có nội dung." + "\n";
//            }

            if (erbo.getLegalContentL() != null && !"null".equals(erbo.getLegalContentL())) {
                leaderContent += "* Về pháp chế:" + "\n";
                leaderContent += erbo.getLegalContentL() + "\n";
            }
//            else {
//                leaderContent += "Không có nội dung." + "\n";
//            }

            if (erbo.getFoodSafetyQualityContentL() != null && !"null".equals(erbo.getFoodSafetyQualityContentL())) {
                leaderContent += "* Về chỉ tiêu chất lượng an toàn thực phẩm:" + "\n";
                leaderContent += erbo.getFoodSafetyQualityContentL() + "\n";
            }
//            else {
//                leaderContent += "Không có nội dung." + "\n";
//            }

            if (erbo.getEffectUtilityContentL() != null && !"null".equals(erbo.getEffectUtilityContentL())) {
                leaderContent += "* Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng:" + "\n";
                leaderContent += erbo.getEffectUtilityContentL() + "\n";
            }
//            else {
//                leaderContent += "Không có nội dung." + "\n";
//            }

        }
        customInfo.add(staffContent);//0
        customInfo.add(leaderContent);//1

        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    public String getCommentFeedbackReview() {
        getGridInfo();
        /*
         List customInfo = new ArrayList();
         String strObjectId = getRequest().getParameter("objectId");
         Long objectId = 0l;
         try {
         objectId = Long.parseLong(strObjectId);
         } catch (Exception en) {
         log.error(en.getMessage());
         }
         FilesDAOHE fdaohe = new FilesDAOHE();
         Files fbo = fdaohe.findById(objectId);
         if (fbo != null) {
         RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
         RequestComment lastRQBo = rqdaohe.findLastRequestComment(fbo.getFileId(), 1L);
         customInfo.add(lastRQBo.getContent());
         } else {
         customInfo.add("Không có dữ liệu.");
         }
         */
        List customInfo = new ArrayList();
        String strObjectId = getRequest().getParameter("objectId");
        Long objectId = 0l;
        try {
            objectId = Long.parseLong(strObjectId);
        } catch (Exception ex) {
//            log.error(en.getMessage());
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        FilesDAOHE fdaohe = new FilesDAOHE();
        Files fbo = fdaohe.findById(objectId);
        EvaluationRecordsDAOHE erdaohe = new EvaluationRecordsDAOHE();

        EvaluationRecords erbo = erdaohe.findFilesByFileId(fbo);
        String staffContent = "";

        //hieptq update 070515
        RequestCommentDAOHE rqcmdhe = new RequestCommentDAOHE();
        RequestComment rqcm = rqcmdhe.findLeaderComment(objectId, 1l);
        if (rqcm != null && !"".equals(rqcm.getContent())) {
            staffContent = rqcm.getContent();
        } else if (erbo != null && fbo != null) {

            if (fbo.getStaffRequest() != null && !"null".equals(fbo.getStaffRequest())) {
                staffContent += "* Ý kiến chung:" + "\n";
                staffContent += fbo.getStaffRequest() + "\n";
            }
//            else {
//                staffContent += "Không có nội dung." + "\n";
//            }

            if (erbo.getLegalContent() != null && !"null".equals(erbo.getLegalContent())) {
                staffContent += "* Về pháp chế:" + "\n";
                staffContent += erbo.getLegalContent() + "\n";
            }
//            else {
//                staffContent += "Không có nội dung." + "\n";
//            }

            if (erbo.getFoodSafetyQualityContent() != null && !"null".equals(erbo.getFoodSafetyQualityContent())) {
                staffContent += "* Về chỉ tiêu chất lượng an toàn thực phẩm:" + "\n";
                staffContent += erbo.getFoodSafetyQualityContent() + "\n";
            }
//            else {
//                staffContent += "Không có nội dung." + "\n";
//            }

            if (erbo.getEffectUtilityContent() != null && !"null".equals(erbo.getEffectUtilityContent())) {
                staffContent += "* Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng:" + "\n";
                staffContent += erbo.getEffectUtilityContent() + "\n";
            }
//            else {
//                staffContent += "Không có nội dung." + "\n";
//            }
        }
        customInfo.add(staffContent);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }
// lay noi dung thong bao cua lanh dao phong gui len

    public String getCommentFeedbackApprove() {//
        getGridInfo();
        List customInfo = new ArrayList();
        String strObjectId = getRequest().getParameter("objectId");
        Long objectId = 0l;
        try {
            objectId = Long.parseLong(strObjectId);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(en.getMessage());
        }
        FilesDAOHE fdaohe = new FilesDAOHE();
        Files fbo = fdaohe.findById(objectId);
        if (fbo != null) {
            RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
            RequestComment lastRQBo = rqdaohe.findLastRequestComment(fbo.getFileId(), 1L);
            if (lastRQBo != null) {
                customInfo.add(lastRQBo.getContent());
            } else {
                EvaluationRecordsDAOHE erdaohe = new EvaluationRecordsDAOHE();
                EvaluationRecords erbo = erdaohe.findFilesByFileId(fbo);
                String staffContent = "";
                if (erbo != null && fbo != null) {

                    if (fbo.getStaffRequest() != null && !"null".equals(fbo.getStaffRequest())) {
                        staffContent += "* Ý kiến chung:" + "\n";
                        staffContent += fbo.getStaffRequest() + "\n\n";
                    }

                    if (erbo.getLegalContent() != null && !"null".equals(erbo.getLegalContent())) {
                        staffContent += "* Về pháp chế:" + "\n";
                        staffContent += erbo.getLegalContent() + "\n\n";
                    }

                    if (erbo.getFoodSafetyQualityContent() != null && !"null".equals(erbo.getFoodSafetyQualityContent())) {
                        staffContent += "* Về chỉ tiêu chất lượng an toàn thực phẩm:" + "\n";
                        staffContent += erbo.getFoodSafetyQualityContent() + "\n\n";
                    }

                    if (erbo.getEffectUtilityContent() != null && !"null".equals(erbo.getEffectUtilityContent())) {
                        staffContent += "* Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng:" + "\n";
                        staffContent += erbo.getEffectUtilityContent() + "\n";
                    }
                }
                if (staffContent.trim().length() > 0) {
                    customInfo.add(staffContent);
                } else {
                    customInfo.add("Không có dữ liệu.");
                }
            }
        } else {
            customInfo.add("Không có dữ liệu.");
        }

        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /**
     * hien thi phi nop ho so
     *
     * @return
     */
    public String getFeePaymentInfo() {
        getGridInfo();
        String strObjectId = getRequest().getParameter("objectId");
        String strObjectType = getRequest().getParameter("objectType");
        String strFileType = getRequest().getParameter("fileType");
        List customInfo = new ArrayList();
        Long objectId = 0l;
        Long objectType = 0l;
        Long fileType = 0l;
//        Long feeFile;

        try {
            objectId = Long.parseLong(strObjectId);
            fileType = Long.parseLong(strFileType);
            objectType = Long.parseLong(strObjectType);
        } catch (NumberFormatException ex) {
//            log.error(en.getMessage());
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        FeePaymentInfoDAOHE fpidaohe = new FeePaymentInfoDAOHE();
        FilesDAOHE bdhe = new FilesDAOHE();
        Files filebo = bdhe.findById(objectId);
        Long fileId = 0L;
        Long isFee = 0L;
        Long deptId = 0L;
        if (filebo != null) {
            if (filebo.getFileId() != null && filebo.getFileId() > 0) {
                fileId = filebo.getFileId();
            }
            if (filebo.getIsFee() != null && filebo.getIsFee() > 0) {
                isFee = filebo.getIsFee();
            }
            if (filebo.getAgencyId() != null && filebo.getAgencyId() > 0) {
                deptId = filebo.getAgencyId();
            }
        }
        GridResult result = fpidaohe.getLstFeePaymentInfo(fileId, objectType, 2L, start, count, sortField);
        /*SONAR
        FilesDAOHE filesDHE = new FilesDAOHE();        
        feeFile = filesDHE.findById(objectId).getFeeFile();
        if (feeFile == null) {
            feeFile = 0l;
        }
         */
        customInfo.add(isFee);
        //lay so tiep nhan hien tai
        CountNoDAOHE cndaohe = new CountNoDAOHE();
        CountNo cnbo = cndaohe.returnCountNoByDeptId(deptId);
        if (cnbo != null && cnbo.getReceiveNo() != null && cnbo.getReceiveNo() > 0L) {
            customInfo.add(cnbo.getReceiveNo());
        } else {
            customInfo.add(1L);
        }

        if (fileType > 0L) {
            ProcedureDAOHE pdhe = new ProcedureDAOHE();
            Procedure tthc = pdhe.findById(fileType);
            if (tthc != null) {
                String fileLst = tthc.getFileList();
                customInfo.add(com.viettel.common.util.StringUtils.removeHTML(fileLst));
            } else {
                customInfo.add("");
            }
        } else if (filebo != null && filebo.getFileType() != null && filebo.getFileType() > 0L) {
            ProcedureDAOHE pdhe = new ProcedureDAOHE();
            Procedure tthc = pdhe.findById(filebo.getFileType());
            if (tthc != null && tthc.getFileList() != null) {
                String fileLst = tthc.getFileList();
                customInfo.add(com.viettel.common.util.StringUtils.removeHTML(fileLst));
            } else {
                customInfo.add("");
            }
        } else {
            customInfo.add("");
        }
        jsonDataGrid.setItems(result.getLstResult());
        jsonDataGrid.setTotalRows(result.getnCount().intValue());
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    //140724 hien thi le phi tra ho so - phi cap giay
    /**
     * hien thi le phi cap giay
     *
     * @return
     */
    public String getFeePaymentInfoForReturnFiles() {
        getGridInfo();
        String strObjectId = getRequest().getParameter("objectId");
        String strObjectType = getRequest().getParameter("objectType");
        List customInfo = new ArrayList();
        Long objectId = 0l;
        Long objectType;
//        Long feeFile;

        try {
            objectId = Long.parseLong(strObjectId);
        } catch (NumberFormatException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(en.getMessage());
        }
        try {
            objectType = Long.parseLong(strObjectType);
        } catch (NumberFormatException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(en.getMessage());
            objectType = 0L;
        }
        FeePaymentInfoDAOHE fpidaohe = new FeePaymentInfoDAOHE();
        FilesDAOHE bdhe = new FilesDAOHE();
        Files filebo = bdhe.findById(objectId);
        Long fileId = 0L;
        Long isFee = 0L;
        Long isCourier = 0L;
        String recipientAddress = "";
        if (filebo != null) {
            if (filebo.getFileId() != null && filebo.getFileId() > 0) {
                fileId = filebo.getFileId();
            }
            if (filebo.getIsFee() != null && filebo.getIsFee() > 0) {
                isFee = filebo.getIsFee();
            }
            isCourier = filebo.getIsCourier();
            recipientAddress = filebo.getRecipientAddress();
        }
        GridResult result = fpidaohe.getLstFeePaymentInfo(fileId, objectType, 1L, start, count, sortField);
        /*SONAR
        FilesDAOHE filesDHE = new FilesDAOHE();
        feeFile = filesDHE.findById(objectId).getFeeFile();
        if (feeFile == null) {
            feeFile = 0l;
        }
         */
        customInfo.add(isFee);
        customInfo.add(isCourier);
        customInfo.add(recipientAddress);
        jsonDataGrid.setItems(result.getLstResult());
        jsonDataGrid.setTotalRows(result.getnCount().intValue());
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    // thanh toan dien tu hieptq
    /**
     * thanh toan dien tu
     *
     * @return
     */
    public String preparePayment() {
        try {
            if (createFeeForm != null) {
                searchFeeForm = new FeeForm();
            }
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String calink = rb.getString("KEYPAY_LINK");
            getRequest().setAttribute("calink", calink);
            FilesDAOHE fdhe = new FilesDAOHE();
            Files files = fdhe.findById(createFeeForm.getFileId());
            if (files != null) {
                String fileCode = files.getFileCode();
//            Base64 encoder = new Base64();
                String productName = "";
                String businessName = newStringUtf8(Base64.encodeBase64(files.getBusinessName().getBytes("UTF-8")));;
                ProcedureDAOHE prodaohe = new ProcedureDAOHE();
                Procedure probo = prodaohe.findById(files.getFileType());
                if (probo != null) {
                    if (probo.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR)) {
                        productName = newStringUtf8(Base64.encodeBase64("KS4S".getBytes("UTF-8")));
                    } else {
                        productName = newStringUtf8(Base64.encodeBase64(files.getProductName().getBytes("UTF-8")));
                    }

                }
                //String businessName = files.getBusinessName();
                getRequest().setAttribute("fileCode", fileCode);
                getRequest().setAttribute("businessName", businessName);
                getRequest().setAttribute("productName", productName);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return payFilePage;
    }

    //hieptq update 130115
    public String preparePaymentMore() {
        try {
            if (createFeeForm != null) {
                searchFeeForm = new FeeForm();
            }
            String lstObjectId = getRequest().getParameter("lstObjectId");
            FeeDAOHE feedhe = new FeeDAOHE();
            List listFpi = feedhe.getLstFpiId(lstObjectId);
            String fpiId = "";
            for (int i = 0; i < listFpi.size(); i++) {
                if (i == listFpi.size() - 1) {
                    fpiId += listFpi.get(i).toString();
                } else {
                    fpiId += listFpi.get(i).toString() + ",";
                }
            }
            String[] lstObjectIdSplit = lstObjectId.split(",");
            int countObj = lstObjectIdSplit.length;
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String calink = rb.getString("KEYPAY_LINK");
            getRequest().setAttribute("calink", calink);
            FilesDAOHE fdhe = new FilesDAOHE();
//            Base64 encoder = new Base64();
            //Long filesCode = feedhe.getMaxFilesCode();
//            if (filesCode == 0l || filesCode == null) {
//                filesCode = 1l;
//            } else {
//                filesCode++;
//            }

            CountNo countNo = feedhe.getMaxFilesCode("ATTP");
            Long filesCode = countNo.getKeypayNo();
            if (filesCode == 0l || filesCode == null) {
                filesCode = 1l;
            } else {
                filesCode++;
            }
            countNo.setKeypayNo(filesCode);
            getSession().update(countNo);
            String fileCode = "MF_" + filesCode.toString();
            String productNameNew = "";
            Long amount = feedhe.getAmountKeyPay(lstObjectId);
            for (int i = 0; i < countObj; i++) {
                Files files = fdhe.findById(Long.parseLong(lstObjectIdSplit[i]));
                if (i == countObj - 1) {
                    //fileCode += files.getFileCode();
                    productNameNew += files.getProductName();
                } else {
                    //fileCode += files.getFileCode() + " ";
                    productNameNew += files.getProductName() + ",";
                }
            }
            String productName = newStringUtf8(Base64.encodeBase64(productNameNew.getBytes("UTF-8")));
            Files files = fdhe.findById(Long.parseLong(lstObjectIdSplit[0]));
            String businessName = newStringUtf8(Base64.encodeBase64(files.getBusinessName().getBytes("UTF-8")));
            //String businessName = files.getBusinessName();
            getRequest().setAttribute("amount", amount);
            getRequest().setAttribute("fpiId", fpiId);
            getRequest().setAttribute("fileCode", fileCode);
            getRequest().setAttribute("businessName", businessName);
            getRequest().setAttribute("productName", productName);

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());;
        }
        return payFilePageMore;
    }

    // quan ly le phi
    /**
     * quan ly le phi
     *
     * @return
     */
    public String prepareFeeManage() {
        try {
            if (createFeeForm != null) {
                searchFeeForm = new FeeForm();
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return feeManagePage;
    }

    // quan ly nop phi
    /**
     * quan ly nop phi
     *
     * @return
     */
    public String prepareFeePayManage() {
        try {
            if (createFeeForm != null) {
                searchFeeForm = new FeeForm();
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return feePayManagePage;
    }

    /**
     * tim kiem le phi
     *
     * @return
     */
    public String onSearchFeeManage() {
        getGridInfo();
        String userId = getRequest().getParameter("userId");
        FeeDAOHE = new FeeDAOHE();
        GridResult gr = FeeDAOHE.getLstFeeManage(searchFeeFormNew, Long.parseLong(userId), start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * tim kiem phi tham xet
     *
     * @return
     */
    public String onSearchFeePayManage() {
        getGridInfo();
        String userId = getRequest().getParameter("userId");
        FeeDAOHE = new FeeDAOHE();
        GridResult gr = FeeDAOHE.getLstFeePayManage(searchFeeFormNew, Long.parseLong(userId), start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * tim kiem phi
     *
     * @return
     */
    public String onSearchPayment() {
        getGridInfo();
        Long fileId = Long.parseLong(getRequest().getParameter("fileId"));//get attactId
        FeeDAOHE = new FeeDAOHE();
        GridResult gr = FeeDAOHE.getLstPayment(searchFeeForm, fileId, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     * tim kiem ho so theo version cu
     *
     * @return
     */
    public String loadFilesByOldVersion() {//
        String strReturn = ERROR_PERMISSION;
        String agencyName = "";

        String thisVersion = getRequest().getParameter("thisVersion");
        String oldVersion = getRequest().getParameter("oldVersion");
        Long thisIdVersion = 0L;
        Long oldIdVersion = 0L;
        try {
            thisIdVersion = Long.parseLong(thisVersion);
            oldIdVersion = Long.parseLong(oldVersion);
        } catch (NumberFormatException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(en.getMessage());
        }

        if (thisIdVersion > 0l) {
            FilesDAOHE fdhe = new FilesDAOHE();
            createForm = fdhe.getFilesDetail(thisIdVersion);
            createFormOriginal = fdhe.getFilesDetail(thisIdVersion);
            if (createFormOriginal != null) {
                if (createFormOriginal.getAnnouncement() != null) {
                    if (createFormOriginal.getAnnouncement().getAssessmentMethod() != null) {
                        createFormOriginal.getAnnouncement().setAssessmentMethod(createFormOriginal.getAnnouncement().getAssessmentMethod().replace("\n", "nl"));
                        createFormOriginal.getAnnouncement().setAssessmentMethod(createFormOriginal.getAnnouncement().getAssessmentMethod().replace("\r", ""));
                    }
                    if (createFormOriginal.getAnnouncement().getMatchingTarget() != null) {
                        createFormOriginal.getAnnouncement().setMatchingTarget(createFormOriginal.getAnnouncement().getMatchingTarget().replace(";", "nl"));
                        createFormOriginal.getAnnouncement().setMatchingTarget(createFormOriginal.getAnnouncement().getMatchingTarget().replace("\n", ""));
                        createFormOriginal.getAnnouncement().setMatchingTarget(createFormOriginal.getAnnouncement().getMatchingTarget().replace("\r", ""));
                    }

                }
                if (createFormOriginal.getDetailProduct() != null) {
                    if (createFormOriginal.getDetailProduct().getOtherTarget() != null) {
                        createFormOriginal.getDetailProduct().setOtherTarget(createFormOriginal.getDetailProduct().getOtherTarget().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setOtherTarget(createFormOriginal.getDetailProduct().getOtherTarget().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getComponents() != null) {
                        createFormOriginal.getDetailProduct().setComponents(createFormOriginal.getDetailProduct().getComponents().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setComponents(createFormOriginal.getDetailProduct().getComponents().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getTimeInUse() != null) {
                        createFormOriginal.getDetailProduct().setTimeInUse(createFormOriginal.getDetailProduct().getTimeInUse().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setTimeInUse(createFormOriginal.getDetailProduct().getTimeInUse().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getUseage() != null) {
                        createFormOriginal.getDetailProduct().setUseage(createFormOriginal.getDetailProduct().getUseage().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setUseage(createFormOriginal.getDetailProduct().getUseage().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getObjectUse() != null) {
                        createFormOriginal.getDetailProduct().setObjectUse(createFormOriginal.getDetailProduct().getObjectUse().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setObjectUse(createFormOriginal.getDetailProduct().getObjectUse().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getGuideline() != null) {
                        createFormOriginal.getDetailProduct().setGuideline(createFormOriginal.getDetailProduct().getGuideline().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setGuideline(createFormOriginal.getDetailProduct().getGuideline().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getPackateMaterial() != null) {
                        createFormOriginal.getDetailProduct().setPackateMaterial(createFormOriginal.getDetailProduct().getPackateMaterial().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setPackateMaterial(createFormOriginal.getDetailProduct().getPackateMaterial().replace("\r", ""));
                    }

//                    if (createFormOriginal.getDetailProduct().getPackageRecipe() != null) {
//                        createFormOriginal.getDetailProduct().setPackageRecipe(createFormOriginal.getDetailProduct().getPackageRecipe().replace("\n", "nl"));
//                        createFormOriginal.getDetailProduct().setPackageRecipe(createFormOriginal.getDetailProduct().getPackageRecipe().replace("\r", ""));
//                    }
                    if (createFormOriginal.getDetailProduct().getProductionProcess() != null) {
                        createFormOriginal.getDetailProduct().setProductionProcess(createFormOriginal.getDetailProduct().getProductionProcess().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setProductionProcess(createFormOriginal.getDetailProduct().getProductionProcess().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getCounterfeitDistinctive() != null) {
                        createFormOriginal.getDetailProduct().setCounterfeitDistinctive(createFormOriginal.getDetailProduct().getCounterfeitDistinctive().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setCounterfeitDistinctive(createFormOriginal.getDetailProduct().getCounterfeitDistinctive().replace("\r", ""));
                    }

                    if (createFormOriginal.getDetailProduct().getOrigin() != null) {
                        createFormOriginal.getDetailProduct().setOrigin(createFormOriginal.getDetailProduct().getOrigin().replace("\n", "nl"));
                        createFormOriginal.getDetailProduct().setOrigin(createFormOriginal.getDetailProduct().getOrigin().replace("\r", ""));
                    }

                }
                if (createFormOriginal.getTestRegistration() != null) {
                }
            }

            createFormClone = fdhe.getFilesDetail(oldIdVersion);
            if (createFormClone != null) {
                if (createFormClone.getAnnouncement() != null) {
                    if (createFormClone.getAnnouncement().getAssessmentMethod() != null) {
                        createFormClone.getAnnouncement().setAssessmentMethod(createFormClone.getAnnouncement().getAssessmentMethod().replace("\n", "nl"));
                        createFormClone.getAnnouncement().setAssessmentMethod(createFormClone.getAnnouncement().getAssessmentMethod().replace("\r", ""));
                    }
                    if (createFormClone.getAnnouncement().getMatchingTarget() != null) {
                        createFormClone.getAnnouncement().setMatchingTarget(createFormClone.getAnnouncement().getMatchingTarget().replace(";", "nl"));
                        createFormClone.getAnnouncement().setMatchingTarget(createFormClone.getAnnouncement().getMatchingTarget().replace("\n", "nl"));
                        createFormClone.getAnnouncement().setMatchingTarget(createFormClone.getAnnouncement().getMatchingTarget().replace("\r", ""));
                    }
                }
                if (createFormClone.getDetailProduct() != null) {
                    if (createFormClone.getDetailProduct().getOtherTarget() != null) {
                        createFormClone.getDetailProduct().setOtherTarget(createFormClone.getDetailProduct().getOtherTarget().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setOtherTarget(createFormClone.getDetailProduct().getOtherTarget().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getComponents() != null) {
                        createFormClone.getDetailProduct().setComponents(createFormClone.getDetailProduct().getComponents().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setComponents(createFormClone.getDetailProduct().getComponents().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getTimeInUse() != null) {
                        createFormClone.getDetailProduct().setTimeInUse(createFormClone.getDetailProduct().getTimeInUse().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setTimeInUse(createFormClone.getDetailProduct().getTimeInUse().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getUseage() != null) {
                        createFormClone.getDetailProduct().setUseage(createFormClone.getDetailProduct().getUseage().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setUseage(createFormClone.getDetailProduct().getUseage().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getObjectUse() != null) {
                        createFormClone.getDetailProduct().setObjectUse(createFormClone.getDetailProduct().getObjectUse().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setObjectUse(createFormClone.getDetailProduct().getObjectUse().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getGuideline() != null) {
                        createFormClone.getDetailProduct().setGuideline(createFormClone.getDetailProduct().getGuideline().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setGuideline(createFormClone.getDetailProduct().getGuideline().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getPackateMaterial() != null) {
                        createFormClone.getDetailProduct().setPackateMaterial(createFormClone.getDetailProduct().getPackateMaterial().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setPackateMaterial(createFormClone.getDetailProduct().getPackateMaterial().replace("\r", ""));
                    }

//                    if (createFormClone.getDetailProduct().getPackageRecipe() != null) {
//                        createFormClone.getDetailProduct().setPackageRecipe(createFormClone.getDetailProduct().getPackageRecipe().replace("\n", "nl"));
//                        createFormClone.getDetailProduct().setPackageRecipe(createFormClone.getDetailProduct().getPackageRecipe().replace("\r", ""));
//                    }
                    if (createFormClone.getDetailProduct().getProductionProcess() != null) {
                        createFormClone.getDetailProduct().setProductionProcess(createFormClone.getDetailProduct().getProductionProcess().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setProductionProcess(createFormClone.getDetailProduct().getProductionProcess().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getCounterfeitDistinctive() != null) {
                        createFormClone.getDetailProduct().setCounterfeitDistinctive(createFormClone.getDetailProduct().getCounterfeitDistinctive().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setCounterfeitDistinctive(createFormClone.getDetailProduct().getCounterfeitDistinctive().replace("\r", ""));
                    }

                    if (createFormClone.getDetailProduct().getOrigin() != null) {
                        createFormClone.getDetailProduct().setOrigin(createFormClone.getDetailProduct().getOrigin().replace("\n", "nl"));
                        createFormClone.getDetailProduct().setOrigin(createFormClone.getDetailProduct().getOrigin().replace("\r", ""));
                    }

                }
            }

            DepartmentDAOHE ddhe = new DepartmentDAOHE();
            Department dept = ddhe.getDeptByUserId(createFormOriginal.getUserCreateId());
            if (dept != null) {
                agencyName = dept.getDeptName();
            }

            if (createFormOriginal != null && createFormOriginal.getFileType() != null && createFormOriginal.getFileType() > 0l) {
                ProcedureDAOHE pdhe = new ProcedureDAOHE();
                CategoryDAOHE cdhe = new CategoryDAOHE();
                Procedure tthc = pdhe.findById(createFormOriginal.getFileType());
                if (tthc != null) {
                    FilesDAOHE filesDaohe = new FilesDAOHE();
                    lstOldVersion = new ArrayList();
                    if (createFormOriginal != null && createFormOriginal.getOriginalId() != null && createFormOriginal.getOriginalId() > 0L) {
                        lstOldVersion.addAll(filesDaohe.getLstOldVersionFiles(createFormOriginal.getOriginalId()));
                        lstOldVersion.add(0, new FilesForm(createFormOriginal.getOriginalId(), "Lần sửa đổi gần nhất"));
                    } else {
                        lstOldVersion.addAll(filesDaohe.getLstOldVersionFiles(createFormOriginal.getFileId()));
                        lstOldVersion.add(0, new FilesForm(createFormOriginal.getFileId(), "Lần sửa đổi gần nhất"));
                    }
                    lstOldVersion.add(0, new FilesForm(Constants.COMBOBOX_HEADER_VALUE, "--Chọn--"));
                    getRequest().setAttribute("lstOldVersion", lstOldVersion);
                    //
                    lstProductType = cdhe.findAllCategory("SP");
                    lstUnit = cdhe.findAllCategory("DVI");
                    getRequest().setAttribute("lstProductType", lstProductType);
                    getRequest().setAttribute("lstUnit", lstUnit);
                    String fileLst = tthc.getFileList();
                    getRequest().setAttribute("fileList", com.viettel.common.util.StringUtils.removeHTML(fileLst));
                    getRequest().setAttribute("agencyName", agencyName);
                    strReturn = tthc.getDescription() + "View";
                }
            }
            try {
                //Check validate ky CA
                String validCAStatus = "NG";
                if (createFormOriginal != null && createFormOriginal.getContentSigned() != null) {
                    if (CommonUtils.checkSecurityPublishCA(createFormOriginal.getFileId(), createFormOriginal.getContentSigned())) {
                        String contentSigned = createFormOriginal.getContentSigned();
                        KeyInfo keyInfo = CommonUtils.validateContentSigned(contentSigned);

                        if (keyInfo != null) {
                            try {
                                CommonUtils.getUserSigned(keyInfo);
                                validCAStatus = "OK";
                            } catch (CertificateExpiredException | CertificateNotYetValidException ex) {
                                LogUtil.addLog(ex);//binhnt sonar a160901
//                                log.error(expiredEx);
                            }
                        } else {
                            validCAStatus = "NG";
                        }
                    } else {
                        validCAStatus = "NG";
                    }
                    getRequest().setAttribute("validCAStatus", validCAStatus);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                log.error(e.getMessage());
            }
        }
        return strReturn;
    }

    /**
     **Thông báo cho doanh nghiệp Đối chiếu hồ sơ
     *
     * @return
     */
    public String onAlertComparison() {
        FilesDAOHE fdhe = new FilesDAOHE();
        boolean bReturn = fdhe.onAlertComparison(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Thông báo đối chiếu thành công");
            Files file = fdhe.findById(createForm.getFileId());
            fdhe.saveStatusFiles(file, "Hồ sơ mã: " + file.getFileCode() + " Đã được thông báo đối chiếu");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Thông báo đối chiếu hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());
        return GRID_DATA;
    }
//141219 binhnt53

    public String onSetCourierAlertComparison() {
        List resultMessage = new ArrayList();
        try {
            FilesDAOHE fdhe = new FilesDAOHE();
            Files file = fdhe.findById(createForm.getFileId());
            if (file != null) {
                file.setIsCourier(createForm.getIsCourier());
                file.setRecipientAddress(createForm.getRecipientAddress());
                getSession().update(file);
                resultMessage.add("1");
                resultMessage.add("Lưu nội dung thành công");
            } else {
                resultMessage.add("3");
                resultMessage.add("Lưu dữ liệu không thành công");
            }

        } catch (Exception ex) {
//            log.error(e.getMessage());
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Cập nhật thông tin nhận bản công bố.", "hồ sơ có id=" + createForm.getFileId(), getRequest());
        return GRID_DATA;
    }

    /**
     * get noi dung comment vao tham dinh ho so
     *
     * @return
     */
    public String getLastRequestComment() {
        getGridInfo();
        List customInfo = new ArrayList();
        String strObjectId = getRequest().getParameter("objectId");
//        String strObjectType = getRequest().getParameter("objectType");

        Long objectId = 0l;
        try {
            objectId = Long.parseLong(strObjectId);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        RequestCommentDAOHE rcdhe = new RequestCommentDAOHE();
        RequestComment lastRQBo = rcdhe.findLastRequestComment(objectId, 1L);
        String content = lastRQBo.getContent();
        customInfo.add(content);
        ProcedureDAOHE pdhe = new ProcedureDAOHE();
        FilesDAOHE fdaohe = new FilesDAOHE();
        Files f = fdaohe.findById(objectId);
        if (f != null && f.getFileId() > 0L) {
            Procedure tthc = pdhe.findById(f.getFileType());
            String fileLst = tthc.getFileList();
            customInfo.add(fileLst);
        }
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /**
     * trang download ho so
     *
     * @return
     */
    public String toDownloadPage() {//
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();

        Long filesId = createForm.getFileId();

        if (filesId == null) {
            resultMessage.add("1");
            resultMessage.add("Download thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Cập nhật hồ sơ không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    // lãnh đạo phòng xem xét Đối chiếu hồ sơ
    /**
     * lãnh đạo phòng xem xét Đối chiếu hồ sơ
     *
     * @return
     */
    public String onComparisonByLeaderOfStaff() {
        FilesDAOHE fdhe = new FilesDAOHE();
        boolean bReturn = fdhe.onComparisonByLeaderOfStaff(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        List resultMessage = new ArrayList();
        EventLogDAOHE edhe = new EventLogDAOHE();
        if (bReturn) {
            Files file = fdhe.findById(createForm.getFileId());

            resultMessage.add("1");
            resultMessage.add("Hồ sơ đã được Xem xét đối chiếu");

            fdhe.saveStatusFiles(file, "Hồ sơ mã: " + file.getFileCode() + " Đã được Xem xét đối chiếu");

            if (file.getStatus().equals(Constants.FILE_STATUS.COMPARED)) {
                edhe.insertEventLog("Đối chiếu hồ sơ", "Hồ sơ ID" + createForm.getFileId() + " Mã: " + file.getFileCode() + " Đã được đối chiếu Thành công", getRequest());
            } else {
                edhe.insertEventLog("Đối chiếu hồ sơ", "Hồ sơ ID" + createForm.getFileId() + " Mã: " + file.getFileCode() + " Đã được đối chiếu Có sai lệch", getRequest());
            }

        } else {
            resultMessage.add("3");
            resultMessage.add("Xem xét đối chiếu dữ liệu không thành công");
            edhe.insertEventLog("Xem xét Đối chiếu hồ sơ", "Hồ sơ ID:" + createForm.getFileId() + " gặp sự cố khi đối chiếu", getRequest());
        }
        jsonDataGrid.setItems(resultMessage);

        //edhe.insertEventLog("Đối chiếu hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());
        return GRID_DATA;
    }

    /**
     * upload file ky cua doanh nghiep
     *
     * @return
     */
    public String onUploadFileSign() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        String fileId = getRequest().getParameter("fileId");
        String billPath = getRequest().getParameter("billPath");
        String[] lstVo = billPath.split(";");
        for (int i = 0; i < lstVo.length; i++) {
            if (lstVo[i] != null) {
                VoAttachsDAOHE vadhe = new VoAttachsDAOHE();
                VoAttachs voUpload = vadhe.findById(Long.parseLong(lstVo[i]));
                voUpload.setObjectId(Long.parseLong(fileId));
                voUpload.setObjectType(60L);
                getSession().update(voUpload);
            }
        }
        FilesDAOHE fdhe = new FilesDAOHE();
        Files files = fdhe.findById(Long.parseLong(fileId));
        files.setUserSigned("fileUploaded");
        getSession().update(files);
        getSession().getTransaction().commit();
        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    //hieptq update lanh dao day file ky len
    public String onUploadFileSignPdf() throws Exception, Exception {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        String fileId = getRequest().getParameter("fileId");
        String billPath = getRequest().getParameter("billPath");
        String[] lstVo = billPath.split(";");
        for (int i = 0; i < lstVo.length; i++) {
            if (lstVo[i] != null) {
                VoAttachsDAOHE vadhe = new VoAttachsDAOHE();
                VoAttachs voUpload = vadhe.findById(Long.parseLong(lstVo[i]));
                voUpload.setObjectId(Long.parseLong(fileId));
                voUpload.setObjectType(40L);
                getSession().update(voUpload);
            }
        }
        FilesDAOHE fdhe = new FilesDAOHE();
        Files files = fdhe.findById(Long.parseLong(fileId));
        files.setIsSignPdf(1l);
        getSession().update(files);
        getSession().getTransaction().commit();
        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    //hieptq update 121214
    public String onUploadFileSignPdfVT() throws Exception, Exception {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        String fileId = getRequest().getParameter("fileId");
        String billPath = getRequest().getParameter("billPath");
        String[] lstVo = billPath.split(";");
        VoAttachsDAOHE vdhe = new VoAttachsDAOHE();
        List<VoAttachs> voa = vdhe.getAttachsByObject(Long.parseLong(fileId), 40L);
        if (voa != null && voa.size() > 0) {
            for (int i = 0; i < voa.size(); i++) {
                voa.get(i).setIsActive(0L);
                vdhe.updateDbNotCommit(voa.get(i));
            }
        }

        for (int i = 0; i < lstVo.length; i++) {
            if (lstVo[i] != null) {
                VoAttachsDAOHE vadhe = new VoAttachsDAOHE();
                VoAttachs voUpload = vadhe.findById(Long.parseLong(lstVo[i]));
                voUpload.setObjectId(Long.parseLong(fileId));
                voUpload.setObjectType(40L);
                getSession().update(voUpload);
            }
        }
        FilesDAOHE fdhe = new FilesDAOHE();
        Files files = fdhe.findById(Long.parseLong(fileId));

        files.setIsSignPdf(
                2l);
        getSession()
                .update(files);
        getSession()
                .getTransaction().commit();
        jsonDataGrid.setItems(resultMessage);

        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

// check thong tu 30
    /**
     *
     * @return
     */
    public String check30() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        String fileId = getRequest().getParameter("fileId");
        FilesDAOHE fdhe = new FilesDAOHE();
        Files files = fdhe.findById(Long.parseLong(fileId));
        files.setIs30(1l);
        getSession().update(files);
        getSession().getTransaction().commit();
        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String UnCheck30() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        String fileId = getRequest().getParameter("fileId");
        FilesDAOHE fdhe = new FilesDAOHE();
        Files files = fdhe.findById(Long.parseLong(fileId));
        files.setIs30(0l);
        getSession().update(files);
        getSession().getTransaction().commit();
        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /**
     * Login USB Token
     *
     * @return
     */
    public String loginUsbCA() {
        List resultMessage = new ArrayList();
        try {
            if (!"".equals(loginCAForm.getPassword())) {
                HttpServletRequest req = getRequest();
                HttpSession session = req.getSession();
                session.setAttribute("passwordCa", loginCAForm.getPassword());
                resultMessage.add("1");
                resultMessage.add(loginCAForm.getPassword());
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("0");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }
//-

    /**
     * 150121 binhnt53 check trang thai ho so
     *
     * @return
     */
    public String checkEditStatusFiles() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        Files bo = new Files();
        bo.setFileId(createForm.getFileId());
        bo.setStatus(createForm.getStatus());
        bo.setDisplayStatus(createForm.getDisplayStatus());
        bo.setStaffProcess(createForm.getStaffProcess());
        bo.setNameStaffProcess(createForm.getNameStaffProcess());
        bo.setLeaderEvaluateId(createForm.getLeaderEvaluateId());
        bo.setLeaderEvaluateName(createForm.getLeaderEvaluateName());
        bo.setLeaderReviewId(createForm.getLeaderReviewId());
        bo.setLeaderReviewName(createForm.getLeaderReviewName());
        bo.setLeaderApproveId(createForm.getLeaderApproveId());
        bo.setLeaderApproveName(createForm.getLeaderApproveName());
//        UsersDAO udao = new UsersDAO();
        try {
//            if (!udao.checkUserExist(bo.getNameStaffProcess(), bo.getStaffProcess())
//                    || !udao.checkUserExist(bo.getLeaderEvaluateName(), bo.getLeaderEvaluateId())
//                    || !udao.checkUserExist(bo.getLeaderReviewName(), bo.getLeaderReviewId())
//                    || !udao.checkUserExist(bo.getLeaderApproveName(), bo.getLeaderApproveId())) {
//                resultMessage.add("3");
//                resultMessage.add("Người dùng không tồn tại");
//                jsonDataGrid.setItems(resultMessage);
//                jsonDataGrid.setCustomInfo(customInfo);
//                return "gridData";
//            }
//            FilesDAOHE fdhe = new FilesDAOHE();
            String displayStatus = FilesDAOHE.getFileStatusName(bo.getStatus());
            if (!displayStatus.equals(bo.getDisplayStatus())) {
                resultMessage.add("3");
                resultMessage.add("Tên trạng thái hiển thị không đúng");
                jsonDataGrid.setItems(resultMessage);
                jsonDataGrid.setCustomInfo(customInfo);
                return "gridData";
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            Logger.getLogger(FilesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultMessage.add("1");
        resultMessage.add("Mời nộp");
        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return "gridData";
    }

    /**
     * binhnt 060215 thêm combobox chọn đơn vị xử lý hồ sơ
     *
     * @return
     */
    public String loadDepartmentProcessFile() {
        FilesDAOHE fdhe = new FilesDAOHE();
        createForm = fdhe.getFilesDetail(createForm.getFileId());
        List<ProcedureDepartment> lstDepartmentProcessFile = new ArrayList<ProcedureDepartment>();
        ProcedureDepartmentDAOHE pddaohe = new ProcedureDepartmentDAOHE();
        List lstCQXL = pddaohe.getAllProcedureDepartmentByProcedureId(createForm.getFileType());
        lstDepartmentProcessFile.addAll(lstCQXL);
        lstDepartmentProcessFile.add(0, new ProcedureDepartment(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        jsonDataGrid.setItems(lstDepartmentProcessFile);
        jsonDataGrid.setLabel("processDeptName");
        jsonDataGrid.setIdentifier("processDeptId");
        return GRID_DATA;
    }

    public String updateFileSearchIndex() {
        getGridInfo();
        FilesDAOHE fdhe = new FilesDAOHE();
        fdhe.updateIndex(start, count);
        return GRID_DATA;
    }

    public String getCountIndex() {
        FilesDAOHE fdhe = new FilesDAOHE();
        int fileCount = fdhe.getUpdateCount();
        jsonDataGrid.setCustomInfo(fileCount);
        return GRID_DATA;
    }

    //<editor-fold desc="GETTER-SETTER">
    public LoginCAForm getLoginCAForm() {
        return loginCAForm;
    }

    public void setLoginCAForm(LoginCAForm loginCAForm) {
        this.loginCAForm = loginCAForm;
    }

    public FilesForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(FilesForm searchForm) {
        this.searchForm = searchForm;
    }

    public FilesForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(FilesForm createForm) {
        this.createForm = createForm;
    }

    public FilesForm getCreateFormCompare() {
        return createFormCompare;
    }

    public void setCreateFormCompare(FilesForm createFormCompare) {
        this.createFormCompare = createFormCompare;
    }

    public List<FilesForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<FilesForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

    public List getLstCategory() {
        return lstCategory;
    }

    public void setLstCategory(List lstCategory) {
        this.lstCategory = lstCategory;
    }

    public List getLstDeptProcessFile() {
        return lstDeptProcessFile;
    }

    public void setLstDeptProcessFile(List lstDeptProcessFile) {
        this.lstDeptProcessFile = lstDeptProcessFile;
    }

    public List<ProcessForm> getLstProcessOnGrid() {
        return lstProcessOnGrid;
    }

    public void setLstProcessOnGrid(List<ProcessForm> lstProcessOnGrid) {
        this.lstProcessOnGrid = lstProcessOnGrid;
    }

    public FilesForm getSigningForm() {
        return signingForm;
    }

    public void setSigningForm(FilesForm signingForm) {
        this.signingForm = signingForm;
    }

    public FilesForm getProvideForm() {
        return provideForm;
    }

    public void setProvideForm(FilesForm provideForm) {
        this.provideForm = provideForm;
    }

    public FilesForm getRejectForm() {
        return rejectForm;
    }

    public void setRejectForm(FilesForm rejectForm) {
        this.rejectForm = rejectForm;
    }

    public FilesForm getSignedForm() {
        return signedForm;
    }

    public void setSignedForm(FilesForm signedForm) {
        this.signedForm = signedForm;
    }

    public FilesForm getSignForm() {
        return signForm;
    }

    public void setSignForm(FilesForm signForm) {
        this.signForm = signForm;
    }

    public String getLookupFilesByLeaderPage() {
        return lookupFilesByLeaderPage;
    }

    public void setLookupFilesByLeaderPage(String lookupFilesByLeaderPage) {
        this.lookupFilesByLeaderPage = lookupFilesByLeaderPage;
    }

    public String getLookupFilesByStaffPage() {
        return lookupFilesByStaffPage;
    }

    public void setLookupFilesByStaffPage(String lookupFilesByStaffPage) {
        this.lookupFilesByStaffPage = lookupFilesByStaffPage;
    }

    public FilesForm getFlowForm() {
        return flowForm;
    }

    public void setFlowForm(FilesForm flowForm) {
        this.flowForm = flowForm;
    }

    public FilesForm getCreateFormClone() {
        return createFormClone;
    }

    public void setCreateFormClone(FilesForm createFormClone) {
        this.createFormClone = createFormClone;
    }

    public ProcessCommentForm getCommentForm() {
        return commentForm;
    }

    public void setCommentForm(ProcessCommentForm commentForm) {
        this.commentForm = commentForm;
    }

    public String getLookupFilesByStaffOfStaffPage() {
        return lookupFilesByStaffOfStaffPage;
    }

    public void setLookupFilesByStaffOfStaffPage(String lookupFilesByStaffOfStaffPage) {
        this.lookupFilesByStaffOfStaffPage = lookupFilesByStaffOfStaffPage;
    }

    public String getToComparisonPage() {
        return toComparisonPage;
    }

    public void setToComparisonPage(String toComparisonPage) {
        this.toComparisonPage = toComparisonPage;
    }

    public FilesForm getSignCAForm() {
        return signCAForm;
    }

    public void setSignCAForm(FilesForm signCAForm) {
        this.signCAForm = signCAForm;
    }

    public RepositoriesForm getRepoForm() {
        return repoForm;
    }

    public void setRepoForm(RepositoriesForm repoForm) {
        this.repoForm = repoForm;
    }

    public ProcessCommentForm getFilesCommentForm() {
        return filesCommentForm;
    }

    public void setFilesCommentForm(ProcessCommentForm filesCommentForm) {
        this.filesCommentForm = filesCommentForm;
    }

    public FeeForm getCreateFeeForm() {
        return createFeeForm;
    }

    public void setCreateFeeForm(FeeForm createFeeForm) {
        this.createFeeForm = createFeeForm;
    }

    public FeeForm getSearchFeeForm() {
        return searchFeeForm;
    }

    public void setSearchFeeForm(FeeForm searchFeeForm) {
        this.searchFeeForm = searchFeeForm;
    }

    public FeePaymentFileForm getSearchFeeFormNew() {
        return searchFeeFormNew;
    }

    public void setSearchFeeFormNew(FeePaymentFileForm searchFeeFormNew) {
        this.searchFeeFormNew = searchFeeFormNew;
    }

    public List getLstStatus() {
        return lstStatus;
    }

    public void setLstStatus(List lstStatus) {
        this.lstStatus = lstStatus;
    }

    public String getLookupFilesByStaffDonothingPage() {
        return lookupFilesByStaffDonothingPage;
    }

    public void setLookupFilesByStaffDonothingPage(String lookupFilesByStaffDonothingPage) {
        this.lookupFilesByStaffDonothingPage = lookupFilesByStaffDonothingPage;
    }

    public List getLstLeaderOfStaff() {
        return lstLeaderOfStaff;
    }

    public void setLstLeaderOfStaff(List lstLeaderOfStaff) {
        this.lstLeaderOfStaff = lstLeaderOfStaff;
    }

    public List getLstLeader() {
        return lstLeader;
    }

    public void setLstLeader(List lstLeader) {
        this.lstLeader = lstLeader;
    }

    public List getLstLeaderP() {
        return lstLeaderP;
    }

    public void setLstLeaderP(List lstLeaderP) {
        this.lstLeaderP = lstLeaderP;
    }

    public List getLstOldVersion() {
        return lstOldVersion;
    }

    public void setLstOldVersion(List lstOldVersion) {
        this.lstOldVersion = lstOldVersion;
    }

    public List getLstProductType() {
        return lstProductType;
    }

    public void setLstProductType(List lstProductType) {
        this.lstProductType = lstProductType;
    }

    public List getLstRepositories() {
        return lstRepositories;
    }

    public void setLstRepositories(List lstRepositories) {
        this.lstRepositories = lstRepositories;
    }

    public List getLstUnit() {
        return lstUnit;
    }

    public void setLstUnit(List lstUnit) {
        this.lstUnit = lstUnit;
    }

    public List getLstProvince() {
        return lstProvince;
    }

    public void setLstProvince(List lstProvince) {
        this.lstProvince = lstProvince;
    }

    public List getLstStandard() {
        return lstStandard;
    }

    public void setLstStandard(List lstStandard) {
        this.lstStandard = lstStandard;
    }

    public List getLstUserAttach() {
        return lstUserAttach;
    }

    public void setLstUserAttach(List lstUserAttach) {
        this.lstUserAttach = lstUserAttach;
    }

    public String loadAllLDCOfDept() {
        UsersDAOHE udaohe = new UsersDAOHE();
        Long userId = 0L;
        try {
            userId = Long.parseLong(getRequest().getParameter("leaderAssignId"));
            Users a = udaohe.findById(userId);
            List lstLDC = udaohe.getLeaderByUser(a.getDeptId());
            List<Users> lstUsers = new ArrayList<Users>();
            lstUsers.addAll(lstLDC);
            lstUsers.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            jsonDataGrid.setItems(lstUsers);
            jsonDataGrid.setLabel("fullName");
            jsonDataGrid.setIdentifier("userId");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return GRID_DATA;
    }

    public String loadAllLDPOfDept() {
        UsersDAOHE udaohe = new UsersDAOHE();
        Long userId = 0L;
        try {
            userId = Long.parseLong(getRequest().getParameter("leaderAssignId"));
            Users a = udaohe.findById(userId);
            List lstLDC = udaohe.getAllLeaderOfStaffInOffice(a.getDeptId());
            List<Users> lstUsers = new ArrayList<Users>();
            lstUsers.addAll(lstLDC);
            lstUsers.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            jsonDataGrid.setItems(lstUsers);
            jsonDataGrid.setLabel("fullName");
            jsonDataGrid.setIdentifier("userId");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return GRID_DATA;
    }

    public String loadAllCVOfDept() {
        UsersDAOHE udaohe = new UsersDAOHE();
        Long userId = 0L;
        try {
            userId = Long.parseLong(getRequest().getParameter("leaderAssignId"));
            Users a = udaohe.findById(userId);
            List lstLDC = udaohe.getCVOfDept(a.getDeptId());
            List<Users> lstUsers = new ArrayList<Users>();
            lstUsers.addAll(lstLDC);
            lstUsers.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            jsonDataGrid.setItems(lstUsers);
            jsonDataGrid.setLabel("fullName");
            jsonDataGrid.setIdentifier("userId");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return GRID_DATA;
    }

    /**
     * binhnt
     *
     * @return
     */
    public String loadAllStatus() {
        CategoryDAOHE catedaohe = new CategoryDAOHE();
        List lstCategory = catedaohe.findAllCategorySearch(Constants.CATEGORY_TYPE.STATUS);
        List<Category> lstStatus = new ArrayList<Category>();
        lstStatus.addAll(lstCategory);
        jsonDataGrid.setItems(lstStatus);
        jsonDataGrid.setLabel("name");
        jsonDataGrid.setIdentifier("code");
        return GRID_DATA;
    }

    public String loadAllBusinessUser() {
        UsersDAOHE udaohe = new UsersDAOHE();
        List lst = udaohe.findAllUserOfBusiness();
        List<Users> lstStatus = new ArrayList<Users>();
        lstStatus.addAll(lst);
        jsonDataGrid.setItems(lstStatus);
        jsonDataGrid.setLabel("fullName");
        jsonDataGrid.setIdentifier("userId");
        return GRID_DATA;
    }

    public String loadAllDept() {
        UsersDAOHE udaohe = new UsersDAOHE();
        List lst = udaohe.findAllUserOfBusiness();
        List<Users> lstStatus = new ArrayList<Users>();
        lstStatus.addAll(lst);
        jsonDataGrid.setItems(lstStatus);
        jsonDataGrid.setLabel("fullName");
        jsonDataGrid.setIdentifier("userId");
        return GRID_DATA;
    }

    public String loadAllBusiness() {
        DepartmentDAOHE ddaohe = new DepartmentDAOHE();
        List lst = ddaohe.getAllDept();
        List<Department> lstStatus = new ArrayList<Department>();
        lstStatus.addAll(lst);
        jsonDataGrid.setItems(lstStatus);
        jsonDataGrid.setLabel("deptName");
        jsonDataGrid.setIdentifier("deptId");
        return GRID_DATA;
    }

    public String loadAllUsers() {
        UsersDAOHE udaohe = new UsersDAOHE();
        List lst = udaohe.findAllUserOfStaff();
        List<Users> lstStatus = new ArrayList<Users>();
        lstStatus.addAll(lst);
        jsonDataGrid.setItems(lstStatus);
        jsonDataGrid.setLabel("fullName");
        jsonDataGrid.setIdentifier("userId");
        return GRID_DATA;
    }

    public String getRequestComments() {
        getGridInfo();
        String strObjectId = getRequest().getParameter("objectId");

        Long objectId = 0l;
        try {
            objectId = Long.parseLong(strObjectId);
        } catch (NumberFormatException ex) {
//            log.error(en.getMessage());
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        RequestCommentDAOHE pcdhe = new RequestCommentDAOHE();
        GridResult result = pcdhe.findLstRequestComment(objectId, start, count, sortField);//binhnt53 150130 bo object type thua

        jsonDataGrid.setItems(result.getLstResult());
        jsonDataGrid.setTotalRows(result.getnCount().intValue());
        return GRID_DATA;
    }

    //hieptq update 070515
    public String saveDraftLeaderComment() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List resultMessage = new ArrayList();
        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        if (check) {
            boolean bReturn = fdhe.saveDraftComment(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
            if (bReturn) {
                resultMessage.add("1");
                resultMessage.add("Lưu dữ liệu thành công");
            } else {
                resultMessage.add("3");
                resultMessage.add("Lưu dữ liệu không thành công");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Lưu ý kiến công văn SĐBS", "hồ sơ có id=" + createForm.getFileId(), getRequest());
        return GRID_DATA;
    }

    //hieptq update 161015
    public String actionSignCA() throws IOException {
        boolean result = true;
        String base64Hash = "";
        String base64Hash0 = "";
        String certSerial = "";
        String fileId = "";
        String outPutFileFinal = "";
        String outPutFileFinal2 = "";
        String fileName = "";
        String fileName0 = "";
        String fileToSign = "";
        String fileToSign0 = "";
        String errorCode = "";
        SignPdfFile pdfSig = new SignPdfFile();
        SignPdfFile pdfSig0 = new SignPdfFile();
        try {
            fileId = getRequest().getParameter("fileId");
            String rootCert, base64Certificate, certChain;
            Base64 decoder = new Base64();
            certChain = new String(decoder.decode(getRequest().getParameter("cert").replace("_", "+").getBytes()), "UTF-8");
            String sToFind = getRequest().getParameter("signType");
            String path = getRequest().getParameter("path");
            String[] pathArr = path.split(";");
            fileToSign = pathArr[0];
            fileName = pathArr[1];
            if ("PDHS".equals(sToFind) || "PDHS_VT".equals(sToFind)) {
                fileToSign0 = pathArr[2];
                fileName0 = pathArr[3];
            }
            String[] chain;
            try {
                chain = certChain.split(",");
                rootCert = chain[1];
                base64Certificate = chain[0];
            } catch (Exception ex) {
                base64Certificate = null;
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_001";
                rootCert = "";
                result = false;
            }
            // hieptq update 150615
            if (base64Certificate == null) {
                errorCode = "SI_002";
                result = false;
            }
            X509Certificate x509Cert = null;
            X509Certificate x509CertChain = null;
            try {
                x509Cert = CertUtils.getX509Cert(base64Certificate);
                x509CertChain = CertUtils.getX509Cert(rootCert);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_003";
                result = false;
            }

            PDFServerClientSignature pdfSCS = new PDFServerClientSignature();
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String TSA_LINK = rb.getString("tsaUrl");
            pdfSCS.setTSA_LINK(TSA_LINK);
            String checkOcspStr = rb.getString("checkOCSP");
            Long checkOCSP = Long.parseLong(checkOcspStr);
            try {
                certSerial = x509Cert.getSerialNumber().toString(16);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_004";
                result = false;
            }
            // hieptq update 160615 - check serial        
            String filePath = rb.getString("sign_temp_plugin");
            File f = new File(filePath);
            if (!f.exists()) {
                f.mkdirs();
            }
            outPutFileFinal = filePath + fileName;
            outPutFileFinal2 = filePath + fileName0;
            CaUserDAOHE ca = new CaUserDAOHE();
            //CaUser caur = null;
            boolean checkCaUser = true;
            if (!ca.checkCaSerial("SerialNumber:[" + certSerial + "]")) {
                errorCode = "SI_005";
                result = false;
            }
            try {
                if (checkOCSP == 1l) {
                    OCSP.RevocationStatus.CertStatus status = checkRevocationStatus((X509Certificate) x509Cert, (X509Certificate) x509CertChain);
                    if (status != OCSP.RevocationStatus.CertStatus.GOOD) {
                        errorCode = "SI_006";
                        result = false;
                    }
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_007";
                result = false;
            }
            if (checkCaUser) {
                String folderPath = ResourceBundleUtil.getString("sign_image");
                //String separator = ResourceBundleUtil.getString("separator");
                String linkImageSign = folderPath + getUserId() + ".png";
                String linkImageStamp = folderPath + "attpStamp.png";
                if ((linkImageSign == null
                        && "".equals(linkImageSign))
                        || (linkImageStamp == null
                        && "".equals(linkImageStamp))) {
                    errorCode = "SI_008";
                    result = false;
                }
                try {
                    if (sToFind.equals("PDHS")) {
                        // ky lanh dao
                        if (fileToSign == null
                                && "".equals(fileToSign)
                                || fileToSign0 == null
                                && "".equals(fileToSign0)) {
                            errorCode = "SI_009";
                            result = false;
                        }
                        sToFind = "<SI>";
                        SearchTextLocations ptl = new SearchTextLocations();
                        List local = ptl.searchLocation(sToFind, fileToSign,
                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
                        String location = "0;0;0";
                        int pageNumber, lx, ly;
                        if (local != null && local.size() > 0) {
                            location = local.get(0).toString();
                        }
                        String[] parts = location.split(";");
                        pageNumber = Integer.parseInt(parts[0]);
                        lx = (int) Float.parseFloat(parts[1]);
                        ly = (int) Float.parseFloat(parts[2]);
                        ly = convertLocation(ly);
                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
                                new Certificate[]{x509Cert}, pageNumber, linkImageSign, lx + 70, ly + 130, 120, 70, "LD");
                        SearchTextLocations ptl2 = new SearchTextLocations();
                        List local2 = ptl2.searchLocation(sToFind, fileToSign0,
                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
                        String location2 = local2.get(0).toString();
                        String parts2[] = location2.split(";");
                        pageNumber = Integer.parseInt(parts2[0]);
                        int lx1 = (int) Float.parseFloat(parts2[1]);
                        int ly1 = (int) Float.parseFloat(parts2[2]);
                        ly1 = convertLocation(ly1);
                        base64Hash0 = pdfSig0.createHash(fileToSign0, outPutFileFinal2,
                                new Certificate[]{x509Cert}, pageNumber, linkImageSign, lx1 + 70, ly1 + 130, 120, 70, "LD");

                    }
                    if ("PDHS_VT".equals(sToFind)) {
                        // ky van thu
                        if (fileToSign == null && "".equals(fileToSign) || fileToSign0 == null && "".equals(fileToSign0)) {
                            errorCode = "SI_010";
                            result = false;
                        }
                        String sToFindtemp = "<SI>";
                        SearchTextLocations ptl = new SearchTextLocations();
                        List local = ptl.searchLocation(sToFindtemp, fileToSign,
                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
                        String location = "0;0;0";
                        int pageNumber, lx, ly;
                        if (local != null && local.size() > 0) {
                            location = local.get(0).toString();
                        }
                        String[] parts = location.split(";");
                        pageNumber = Integer.parseInt(parts[0]);
                        lx = (int) Float.parseFloat(parts[1]);
                        ly = (int) Float.parseFloat(parts[2]);
                        ly = convertLocation(ly);
                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
                                new Certificate[]{x509Cert}, pageNumber, linkImageStamp, lx + 23, ly + 115, 90, 90, "VT");
                        SearchTextLocations ptl2 = new SearchTextLocations();
                        List local2 = ptl2.searchLocation(sToFindtemp, fileToSign0,
                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
                        String location2 = local2.get(0).toString();
                        String parts2[] = location2.split(";");
                        pageNumber = Integer.parseInt(parts2[0]);
                        int lx1 = (int) Float.parseFloat(parts2[1]);
                        int ly1 = (int) Float.parseFloat(parts2[2]);
                        ly1 = convertLocation(ly1);
                        base64Hash0 = pdfSig0.createHash(fileToSign0, outPutFileFinal2,
                                new Certificate[]{x509Cert}, pageNumber, linkImageStamp, lx1 + 23, ly1 + 115, 90, 90, "VT");

                    }
                    if ("CVBS_VT".equals(sToFind)) {
                        // ky van thu
                        if (fileToSign == null
                                && "".equals(fileToSign)
                                || fileToSign0 == null
                                && "".equals(fileToSign0)) {
                            errorCode = "SI_026";
                            result = false;
                        }
                        String sToFindtemp = "<SI>";
                        SearchTextLocations ptl = new SearchTextLocations();
                        List local = ptl.searchLocation(sToFindtemp, fileToSign,
                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
                        String location = "0;0;0";
                        int pageNumber, lx, ly;
                        if (local != null && local.size() > 0) {
                            location = local.get(0).toString();
                        }
                        String[] parts = location.split(";");
                        pageNumber = Integer.parseInt(parts[0]);
                        lx = (int) Float.parseFloat(parts[1]);
                        ly = (int) Float.parseFloat(parts[2]);
                        ly = convertLocation(ly);
                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
                                new Certificate[]{x509Cert}, pageNumber, linkImageStamp, lx + 23, ly + 130, 90, 90, "VT");
                    }

                    if ("CVBS".equals(sToFind)) {
                        // ky lanh dao
                        if (fileToSign == null && "".equals(fileToSign)) {
                            errorCode = "SI_011";
                            result = false;
                        }
                        String sToFindtemp = "<SI>";
                        SearchTextLocations ptl = new SearchTextLocations();
                        List local = ptl.searchLocation(sToFindtemp, fileToSign,
                                SearchTextLocations.SEARCH_BOTTOMUP, SearchTextLocations.FIND_ONE);
                        String location = "0;0;0";
                        int pageNumber, lx, ly;
                        if (local != null && local.size() > 0) {
                            location = local.get(0).toString();
                        }
                        String[] parts = location.split(";");
                        pageNumber = Integer.parseInt(parts[0]);
                        lx = (int) Float.parseFloat(parts[1]);
                        ly = (int) Float.parseFloat(parts[2]);
                        ly = convertLocation(ly);
                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
                                new Certificate[]{x509Cert}, pageNumber, linkImageSign, lx + 80, ly + 150, 120, 70, "LD");

                    }
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
                    System.out.println("ERROR SI_012|" + ex.getMessage());
                    errorCode = "SI_012";
                    result = false;
                }

            } else {
                errorCode = "SI_013";
                result = false;
            }
        } catch (JsonSyntaxException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            errorCode = "SI_014";
            result = false;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            errorCode = "SI_015";
            ex.printStackTrace();
//            System.out.println(ex.getMessage());
            result = false;
        } finally {

        }
        List resultMessage = new ArrayList();
        if (result) {
            HttpServletRequest req = getRequest();
            HttpSession session = req.getSession();
            session.setAttribute("PDFSignature", pdfSig);
            session.setAttribute("PDFSignature2", pdfSig0);
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
            resultMessage.add(base64Hash);
            resultMessage.add(certSerial);
            resultMessage.add(fileId);
            resultMessage.add(outPutFileFinal);
            resultMessage.add(fileName);
            resultMessage.add(base64Hash0);
            resultMessage.add(outPutFileFinal2);
            resultMessage.add(fileName0);
        } else {
            resultMessage.add("0");
            resultMessage.add("Lưu dữ liệu không thành công " + errorCode);
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String actionSignCAForAA() throws IOException {
        boolean result = true;
        String base64Hash = "";
//        String base64Hash0 = "";
        String certSerial = "";
        String outPutFileFinal = "";
//        String outPutFileFinal2 = "";
        String fileId = "";
        String fileName = "";
//        String fileName0 = "";
        String fileToSign = "";
//        String fileToSign0 = "";
        String errorCode = "";
        SignPdfFile pdfSig = new SignPdfFile();
//        SignPdfFile pdfSig0 = new SignPdfFile();
        try {
            fileId = getRequest().getParameter("fileId");
            String rootCert = null, base64Certificate = null, certChain;
            Base64 decoder = new Base64();
            certChain = new String(decoder.decode(getRequest().getParameter("cert").replace("_", "+").getBytes()), "UTF-8");
            String sToFind = getRequest().getParameter("signType");
            String path = getRequest().getParameter("path");
            String[] pathArr = path.split(";");
            fileToSign = pathArr[0];
            fileName = pathArr[1];

            String[] chain;
            try {
                chain = certChain.split(",");
                rootCert = chain[1];
                base64Certificate = chain[0];
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_001";
                result = false;
            }
            if (base64Certificate == null) {
                errorCode = "SI_002";
                result = false;
            }
            X509Certificate x509Cert = null;
            X509Certificate x509CertChain = null;
            try {
                x509Cert = CertUtils.getX509Cert(base64Certificate);
                x509CertChain = CertUtils.getX509Cert(rootCert);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_003";
                result = false;
            }

            PDFServerClientSignature pdfSCS = new PDFServerClientSignature();
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String TSA_LINK = rb.getString("tsaUrl");
            pdfSCS.setTSA_LINK(TSA_LINK);
            String checkOcspStr = rb.getString("checkOCSP");
            Long checkOCSP = Long.parseLong(checkOcspStr);
            try {
                certSerial = x509Cert.getSerialNumber().toString(16);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_004";
                result = false;
            }
            String filePath = rb.getString("sign_temp_plugin");
            File f = new File(filePath);
            if (!f.exists()) {
                f.mkdirs();
            }
            outPutFileFinal = filePath + fileName;
//            outPutFileFinal2 = filePath + fileName0;
            CaUserDAOHE ca = new CaUserDAOHE();
            boolean checkCaUser = true;
            if (!ca.checkCaSerial("SerialNumber:[" + certSerial + "]")) {
                errorCode = "SI_005";
                result = false;
            }
            try {
                if (checkOCSP == 1l) {
                    OCSP.RevocationStatus.CertStatus status = checkRevocationStatus((X509Certificate) x509Cert, (X509Certificate) x509CertChain);
                    if (status != OCSP.RevocationStatus.CertStatus.GOOD) {
                        errorCode = "SI_006";
                        result = false;
                    }
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_007";
                result = false;
            }
            if (checkCaUser) {
                String folderPath = ResourceBundleUtil.getString("sign_image");
                String linkImageSign = folderPath + getUserId() + ".png";
                String linkImageStamp = folderPath + "attpStamp.png";
                if ((linkImageSign == null
                        && "".equals(linkImageSign))
                        || (linkImageStamp == null
                        && "".equals(linkImageStamp))) {
                    errorCode = "SI_008";
                    result = false;
                }
                try {
                    if ("PDHS".equals(sToFind)) {
                        if (fileToSign == null
                                && "".equals(fileToSign)) {
                            errorCode = "SI_009";
                            result = false;
                        }
                        sToFind = "<SI>";
                        SearchTextLocations ptl = new SearchTextLocations();
                        List local = ptl.searchLocation(sToFind, fileToSign,
                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
                        String location = "0;0;0";
                        int pageNumber, lx, ly;
                        if (local != null && local.size() > 0) {
                            location = local.get(0).toString();
                        }
                        String[] parts = location.split(";");
                        pageNumber = Integer.parseInt(parts[0]);
                        lx = (int) Float.parseFloat(parts[1]);
                        ly = (int) Float.parseFloat(parts[2]);
                        ly = convertLocation(ly);
                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
                                new Certificate[]{x509Cert}, pageNumber, linkImageSign, lx + 70, ly + 130, 120, 70, "LD");
                    }
                    if ("PDHS_VT".equals(sToFind)) {
                        // ky van thu
                        if (fileToSign == null && "".equals(fileToSign)) {
                            errorCode = "SI_010";
                            result = false;
                        }
                        String sToFindtemp = "<SI>";
                        SearchTextLocations ptl = new SearchTextLocations();
                        List local = ptl.searchLocation(sToFindtemp, fileToSign,
                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
                        String location = "0;0;0";
                        int pageNumber, lx, ly;
                        if (local != null && local.size() > 0) {
                            location = local.get(0).toString();
                        }
                        String[] parts = location.split(";");
                        pageNumber = Integer.parseInt(parts[0]);
                        lx = (int) Float.parseFloat(parts[1]);
                        ly = (int) Float.parseFloat(parts[2]);
                        ly = convertLocation(ly);
                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
                                new Certificate[]{x509Cert}, pageNumber, linkImageStamp, lx + 23, ly + 115, 90, 90, "VT");
                    }
                    if ("CVBS_VT".equals(sToFind)) {
                        // ky van thu
                        if (fileToSign == null && "".equals(fileToSign)) {
                            errorCode = "SI_026";
                            result = false;
                        }
                        String sToFindtemp = "<SI>";
                        SearchTextLocations ptl = new SearchTextLocations();
                        List local = ptl.searchLocation(sToFindtemp, fileToSign,
                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
                        String location = "0;0;0";
                        int pageNumber, lx, ly;
                        if (local != null && local.size() > 0) {
                            location = local.get(0).toString();
                        }
                        String[] parts = location.split(";");
                        pageNumber = Integer.parseInt(parts[0]);
                        lx = (int) Float.parseFloat(parts[1]);
                        ly = (int) Float.parseFloat(parts[2]);
                        ly = convertLocation(ly);
                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
                                new Certificate[]{x509Cert}, pageNumber, linkImageStamp, lx + 23, ly + 130, 90, 90, "VT");
                    }

                    if ("CVBS".equals(sToFind)) {
                        // ky lanh dao
                        if (fileToSign == null && fileToSign.equals("")) {
                            errorCode = "SI_011";
                            result = false;
                        }
                        String sToFindtemp = "<SI>";
                        SearchTextLocations ptl = new SearchTextLocations();
                        List local = ptl.searchLocation(sToFindtemp, fileToSign,
                                SearchTextLocations.SEARCH_BOTTOMUP, SearchTextLocations.FIND_ONE);
                        String location = "0;0;0";
                        int pageNumber, lx, ly;
                        if (local != null && local.size() > 0) {
                            location = local.get(0).toString();
                        }
                        String[] parts = location.split(";");
                        pageNumber = Integer.parseInt(parts[0]);
                        lx = (int) Float.parseFloat(parts[1]);
                        ly = (int) Float.parseFloat(parts[2]);
                        ly = convertLocation(ly);
                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
                                new Certificate[]{x509Cert}, pageNumber, linkImageSign, lx + 80, ly + 150, 120, 70, "LD");
                    }
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
                    System.out.println("ERROR SI_012|" + ex.getMessage());
                    errorCode = "SI_012";
                    result = false;
                }

            } else {
                errorCode = "SI_013";
                result = false;
            }
        } catch (JsonSyntaxException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            errorCode = "SI_014";
            result = false;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            errorCode = "SI_015";
            result = false;
        } finally {

        }
        List resultMessage = new ArrayList();
        if (result) {
            HttpServletRequest req = getRequest();
            HttpSession session = req.getSession();
            session.setAttribute("PDFSignature", pdfSig);
//            session.setAttribute("PDFSignature2", pdfSig0);
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
            resultMessage.add(base64Hash);
            resultMessage.add(certSerial);
            resultMessage.add(fileId);
            resultMessage.add(outPutFileFinal);
            resultMessage.add(fileName);
//            resultMessage.add(base64Hash0);
//            resultMessage.add(outPutFileFinal2);
//            resultMessage.add(fileName0);
        } else {
            resultMessage.add("0");
            resultMessage.add("Lưu dữ liệu không thành công " + errorCode);
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }
//    public String actionSignCA() throws IOException {
//        boolean result = true;
//        String base64Hash = "";
//        String base64Hash0 = "";
//        String certSerial = "";
//        String fileId = "";
//        String outPutFileFinal = "";
//        String outPutFileFinal2 = "";
//        String fileName = "";
//        String fileName0 = "";
//        String fileToSign = "";
//        String fileToSign0 = "";
//        String errorCode = "";
////        Long fID = 0L;
//        Files f0 = new Files();
//        FilesDAOHE fDAO = new FilesDAOHE();
//        SignPdfFile pdfSig = new SignPdfFile();
//        SignPdfFile pdfSig0 = new SignPdfFile();
//        try {
//            fileId = getRequest().getParameter("fileId");
//            if(fileId!=null && Long.parseLong(fileId)>0L){
//                f0 = fDAO.findById(Long.parseLong(fileId));
//            }
//            f0.getFilesSourceID();
//            String rootCert = null, base64Certificate = null, certChain = null;
//            Base64 decoder = new Base64();
//            certChain = new String(decoder.decode(getRequest().getParameter("cert").replace("_", "+").getBytes()), "UTF-8");
//            String sToFind = getRequest().getParameter("signType");
//            String path = getRequest().getParameter("path");
//            String[] pathArr = path.split(";");
//            fileToSign = pathArr[0];
//            fileName = pathArr[1];
//            if (sToFind.equals("PDHS") || sToFind.equals("PDHS_VT")) {
//                if(pathArr.length>=4){
//                    fileToSign0 = pathArr[2];
//                    fileName0 = pathArr[3];
//                }
//            }
//            String[] chain;
//            try {
//                chain = certChain.split(",");
//                rootCert = chain[1];
//                base64Certificate = chain[0];
//            } catch (Exception e) {
//                errorCode = "SI_001";
//                result = false;
//            }
//            // hieptq update 150615
//            if (base64Certificate == null) {
//                errorCode = "SI_002";
//                result = false;
//            }
//            X509Certificate x509Cert = null;
//            X509Certificate x509CertChain = null;
//            try {
//                x509Cert = CertUtils.getX509Cert(base64Certificate);
//                x509CertChain = CertUtils.getX509Cert(rootCert);
//            } catch (Exception e) {
//                errorCode = "SI_003";
//                result = false;
//            }
// 
//            PDFServerClientSignature pdfSCS = new PDFServerClientSignature();
//            ResourceBundle rb = ResourceBundle.getBundle("config");
//            String TSA_LINK = rb.getString("tsaUrl");
//            pdfSCS.setTSA_LINK(TSA_LINK);
//            String checkOcspStr = rb.getString("checkOCSP");
//            Long checkOCSP = Long.parseLong(checkOcspStr);
//            try {
//                certSerial = x509Cert.getSerialNumber().toString(16);
//            } catch (Exception e) {
//                errorCode = "SI_004";
//                result = false;
//            }
//            // hieptq update 160615 - check serial       
//            String filePath = rb.getString("sign_temp_plugin");
//            File f = new File(filePath);
//            if (!f.exists()) {
//                f.mkdirs();
//            }
//            // String outputFileFinalName = "_" + (new Date()).getTime() + ".pdf";
//            outPutFileFinal = filePath + fileName;
//            if(pathArr.length>=4)
//                outPutFileFinal2 = filePath + fileName0;
//            CaUserDAOHE ca = new CaUserDAOHE();
//            //CaUser caur = null;
//            boolean checkCaUser = true;
//            if (ca.checkCaSerial("SerialNumber:[" + certSerial + "]") == false) {
//                errorCode = "SI_005";
//                result = false;
//            }
//            try {
//                if (checkOCSP == 1l) {
//                    OCSP.RevocationStatus.CertStatus status = checkRevocationStatus((X509Certificate) x509Cert, (X509Certificate) x509CertChain);
//                    if (status != OCSP.RevocationStatus.CertStatus.GOOD) {
//                        errorCode = "SI_006";
//                        result = false;
//                    }
//                }
//            } catch (Exception e) {
//                errorCode = "SI_007";
//                result = false;
//            }
//            if (checkCaUser != false) {
//                String folderPath = ResourceBundleUtil.getString("sign_image");
//                //String separator = ResourceBundleUtil.getString("separator");
//                String linkImageSign = folderPath + getUserId() + ".png";
//                String linkImageStamp = folderPath + "attpStamp.png";
//                if ((linkImageSign == null && linkImageSign.equals("")) || (linkImageStamp == null && linkImageStamp.equals(""))) {
//                    errorCode = "SI_008";
//                    result = false;
//                }
//                try {
//                    if (sToFind.equals("PDHS")) {
//                        // ky lanh dao
//                        //String date = df.format(today);
//                        if(pathArr.length>=4){
//                            if (fileToSign == null && fileToSign.equals("") || fileToSign0 == null && fileToSign0.equals("")) {
//                                errorCode = "SI_009";
//                                result = false;
//                            }
//                        }
//                        //Hiepvv 0703 sdbs sau cong bo
//                        else{
//                            if(fileToSign == null || fileToSign.equals("")){
//                                errorCode = "SI_009";
//                                result = false;
//                            }
//                        }
//                        
//                        sToFind = "<SI>";
//                        SearchTextLocations ptl = new SearchTextLocations();
//                        List local = ptl.searchLocation(sToFind, fileToSign,
//                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
//                        String location = local.get(0).toString();
//                        int pageNumber, lx, ly;
//                        String[] parts = location.split(";");
//                        pageNumber = Integer.parseInt(parts[0]);
//                        lx = (int) Float.parseFloat(parts[1]);
//                        ly = (int) Float.parseFloat(parts[2]);
//                        ly = convertLocation(ly);
//                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
//                                    new Certificate[]{x509Cert}, pageNumber, linkImageSign, lx + 70, ly + 130, 120, 70, "LD");
//
// //Hiepvv neu ko phai ho so sdbs sau cong bo  
//                        if(pathArr.length>=4){
//                            SearchTextLocations ptl2 = new SearchTextLocations();
//                            List local2 = ptl2.searchLocation(sToFind, fileToSign0,
//                                    SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
//                            String location2 = local2.get(0).toString();
//                            String parts2[] = location2.split(";");
//                            pageNumber = Integer.parseInt(parts2[0]);
//                            int lx1 = (int) Float.parseFloat(parts2[1]);
//                            int ly1 = (int) Float.parseFloat(parts2[2]);
//                            ly1 = convertLocation(ly1);
//                            base64Hash0 = pdfSig0.createHash(fileToSign0, outPutFileFinal2,
//                                new Certificate[]{x509Cert}, pageNumber, linkImageSign, lx1 + 70, ly1 + 130, 120, 70, "LD");
//                    
//                        }
////                            base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
////                                    new Certificate[]{x509Cert}, pageNumber, linkImageSign, lx + 70, ly + 80, 120, 70, "LD");
////                        }
//                        }
//                    if (sToFind.equals("PDHS_VT")) {
//                        // ky van thu
//                        if(pathArr.length>=4){
//                            if (fileToSign == null && fileToSign.equals("") || fileToSign0 == null && fileToSign0.equals("")) {
//                                errorCode = "SI_009";
//                                result = false;
//                            }
//                        }
//                        //Hiepvv 0703 sdbs sau cong bo
//                        else{
//                            if(fileToSign == null || fileToSign.equals("")){
//                                errorCode = "SI_009";
//                                result = false;
//                            }
//                        }
//                        String sToFindtemp = "<SI>";
//                        SearchTextLocations ptl = new SearchTextLocations();
//                        List local = ptl.searchLocation(sToFindtemp, fileToSign,
//                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
//                        String location = local.get(0).toString();
//                        int pageNumber, lx, ly;
//                        String[] parts = location.split(";");
//                        pageNumber = Integer.parseInt(parts[0]);
//                        lx = (int) Float.parseFloat(parts[1]);
//                        ly = (int) Float.parseFloat(parts[2]);
//                        ly = convertLocation(ly);
//                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
//                                    new Certificate[]{x509Cert}, pageNumber, linkImageStamp, lx + 23, ly + 115, 90, 90, "VT");
//
//                        SearchTextLocations ptl2 = new SearchTextLocations();
//                        List local2 = ptl2.searchLocation(sToFindtemp, fileToSign0,
//                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
//                        String location2 = local2.get(0).toString();
//                        String parts2[] = location2.split(";");
//                        pageNumber = Integer.parseInt(parts2[0]);
//                        int lx1 = (int) Float.parseFloat(parts2[1]);
//                        int ly1 = (int) Float.parseFloat(parts2[2]);
//                        ly1 = convertLocation(ly1);
//                        base64Hash0 = pdfSig0.createHash(fileToSign0, outPutFileFinal2,
//                                new Certificate[]{x509Cert}, pageNumber, linkImageStamp, lx1 + 23, ly1 + 115, 90, 90, "VT");
//                    }
// 
//                    if (sToFind.equals("CVBS_VT")) {
//                        // ky van thu
//                        if (fileToSign == null && fileToSign.equals("") || fileToSign0 == null && fileToSign0.equals("")) {
//                            errorCode = "SI_026";
//                            result = false;
//                        }
//                        String sToFindtemp = "<SI>";
//                        SearchTextLocations ptl = new SearchTextLocations();
//                        List local = ptl.searchLocation(sToFindtemp, fileToSign,
//                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
//                        String location = local.get(0).toString();
//                        int pageNumber, lx, ly;
//                        String[] parts = location.split(";");
//                        pageNumber = Integer.parseInt(parts[0]);
//                        lx = (int) Float.parseFloat(parts[1]);
//                        ly = (int) Float.parseFloat(parts[2]);
//                        ly = convertLocation(ly);
//                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
//                                new Certificate[]{x509Cert}, pageNumber, linkImageStamp, lx + 23, ly + 130, 90, 90, "VT");
//                    }
// 
//                    if (sToFind.equals("CVBS")) {
//                        // ky lanh dao
//                        if (fileToSign == null && fileToSign.equals("")) {
//                            errorCode = "SI_011";
//                            result = false;
//                        }
//                        String sToFindtemp = "<SI>";
//                        SearchTextLocations ptl = new SearchTextLocations();
//                        List local = ptl.searchLocation(sToFindtemp, fileToSign,
//                                SearchTextLocations.SEARCH_BOTTOMUP, SearchTextLocations.FIND_ONE);
//                        String location = local.get(0).toString();
//                        int pageNumber, lx, ly;
//                        String[] parts = location.split(";");
//                        pageNumber = Integer.parseInt(parts[0]);
//                        lx = (int) Float.parseFloat(parts[1]);
//                        ly = (int) Float.parseFloat(parts[2]);
//                        ly = convertLocation(ly);
//                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
//                                new Certificate[]{x509Cert}, pageNumber, linkImageSign, lx + 80, ly + 150, 120, 70, "LD");
//                    }
//                } catch (Exception ex) {
//                    errorCode = "SI_012";
//                    result = false;
//                }
// 
//            } else {
//                errorCode = "SI_013";
//                result = false;
//            }
//        } catch (JsonSyntaxException jsonSyntaxException) {
//            errorCode = "SI_014";
//            result = false;
//        } finally {
//        }
//        List resultMessage = new ArrayList();
//        if (result) {
//            HttpServletRequest req = getRequest();
//            HttpSession session = req.getSession();
//            session.setAttribute("PDFSignature", pdfSig);
//            session.setAttribute("PDFSignature2", pdfSig0);
//            resultMessage.add("1");
//            resultMessage.add("Lưu dữ liệu thành công");
//            resultMessage.add(base64Hash);
//            resultMessage.add(certSerial);
//            resultMessage.add(fileId);
//            resultMessage.add(outPutFileFinal);
//            resultMessage.add(fileName);
//            resultMessage.add(base64Hash0);
//            resultMessage.add(outPutFileFinal2);
//            resultMessage.add(fileName0);
//        } else {
//            resultMessage.add("0");
//            resultMessage.add("Lưu dữ liệu không thành công " + errorCode);
//        }
//        jsonDataGrid.setItems(resultMessage);
//        return GRID_DATA;
//    }

    public static OCSP.RevocationStatus.CertStatus checkRevocationStatus(X509Certificate peerCert, X509Certificate issuerCert)
            throws Exception {
        OCSPReq request = generateOCSPRequest2(issuerCert, peerCert.getSerialNumber());
        //This list will sometimes have non ocsp urls as well.
        List<String> locations = getAIALocations(peerCert);
        for (String serviceUrl : locations) {
            SingleResp[] responses;
            try {
                ResourceBundle rb = ResourceBundle.getBundle("config");
                //String host = rb.getString("ocspUrl");
                String BCY = rb.getString("BCY");
                String checkBCY = issuerCert.getIssuerDN().toString();
                String host;
                if (BCY.equals(checkBCY)) {
                    host = rb.getString("ocspBcyUrl");
                } else {
                    host = getOcspUrl(peerCert);
                }
                //String host = "http://ocsp.ca.gov.vn:2560";
                OCSPResp ocspResponse = getOCSPResponse(host, request);
                if (OCSPRespStatus.SUCCESSFUL != ocspResponse.getStatus()) {
                    continue; // Server didn't give the response right.
                }
                BasicOCSPResp basicResponse = (BasicOCSPResp) ocspResponse.getResponseObject();
                responses = (basicResponse == null) ? null : basicResponse.getResponses();
                //todo use the super exception
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                continue;
            }
            if (responses != null && responses.length == 1) {
                SingleResp resp = responses[0];
                OCSP.RevocationStatus.CertStatus status = getRevocationStatus(resp);
                return status;
            }
        }
        throw new Exception("Cant get Revocation Status from OCSP.");
    }

    private static OCSP.RevocationStatus.CertStatus getRevocationStatus(SingleResp resp) throws Exception {
        Object status = resp.getCertStatus();
        if (status == CertificateStatus.GOOD) {
            return OCSP.RevocationStatus.CertStatus.GOOD;
        } else if (status instanceof org.bouncycastle.ocsp.RevokedStatus) {
            return OCSP.RevocationStatus.CertStatus.REVOKED;
        } else if (status instanceof org.bouncycastle.ocsp.UnknownStatus) {
            return OCSP.RevocationStatus.CertStatus.UNKNOWN;
        }
        throw new Exception("Cant recognize Certificate Status");
    }

    private static List<String> getAIALocations(X509Certificate cert) throws Exception {

        //Gets the DER-encoded OCTET string for the extension value for Authority information access Points
        byte[] aiaExtensionValue = cert.getExtensionValue(X509Extensions.AuthorityInfoAccess.getId());
        if (aiaExtensionValue == null) {
            throw new Exception("Certificate doesn't have authority "
                    + "information access points");
        }
        //might have to pass an ByteArrayInputStream(aiaExtensionValue)
        ASN1InputStream asn1In = new ASN1InputStream(aiaExtensionValue);
        AuthorityInformationAccess authorityInformationAccess;

        try {
            DEROctetString aiaDEROctetString = (DEROctetString) (asn1In.readObject());
            ASN1InputStream asn1InOctets = new ASN1InputStream(aiaDEROctetString.getOctets());
            ASN1Sequence aiaASN1Sequence = (ASN1Sequence) asn1InOctets.readObject();
            authorityInformationAccess = AuthorityInformationAccess.getInstance(aiaASN1Sequence);
        } catch (IOException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            throw new Exception("Cannot read certificate to get OCSP URLs", ex);
        }

        List<String> ocspUrlList = new ArrayList<String>();
        AccessDescription[] accessDescriptions = authorityInformationAccess.getAccessDescriptions();
        for (AccessDescription accessDescription : accessDescriptions) {

            GeneralName gn = accessDescription.getAccessLocation();
            if (gn.getTagNo() == GeneralName.uniformResourceIdentifier) {
                DERIA5String str = DERIA5String.getInstance(gn.getName());
                String accessLocation = str.getString();
                ocspUrlList.add(accessLocation);
            }
        }
        if (ocspUrlList.isEmpty()) {
            throw new Exception("Cant get OCSP urls from certificate");
        }

        return ocspUrlList;
    }

    protected static OCSPResp getOCSPResponse(String serviceUrl,
            OCSPReq request) throws Exception {
        try {
            //Todo: Use http client.
            byte[] array = request.getEncoded();
            if (serviceUrl.startsWith("http")) {
                HttpURLConnection con;
                URL url = new URL(serviceUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("Content-Type", "application/ocsp-request");
                con.setRequestProperty("Accept", "application/ocsp-response");
                con.setDoOutput(true);
                OutputStream out = con.getOutputStream();
                DataOutputStream dataOut = new DataOutputStream(new BufferedOutputStream(out));
                dataOut.write(array);

                dataOut.flush();
                dataOut.close();

                //Check errors in response:
                if (con.getResponseCode() / 100 != 2) {
                    throw new Exception("Error getting ocsp response."
                            + "Response code is " + con.getResponseCode());
                }

                //Get Response
                InputStream in = (InputStream) con.getContent();
                return new OCSPResp(in);
            } else {
                throw new Exception("Only http is supported for ocsp calls");
            }
        } catch (IOException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            throw new Exception("Cannot get ocspResponse from url: " + serviceUrl, ex);
        }
    }

    private static OCSPReq generateOCSPRequest2(X509Certificate issuerCert, BigInteger serialNumber)
            throws Exception {

        //TODO: Have to check if this is OK with synapse implementation.
        //Add provider BC
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        CertificateID id = new CertificateID(CertificateID.HASH_SHA1, issuerCert, serialNumber);
        OCSPReqGenerator generator = new OCSPReqGenerator();
        generator.addRequest(id);
        BigInteger nonce = BigInteger.valueOf(System.currentTimeMillis());
        Vector<ASN1ObjectIdentifier> objectIdentifiers = new Vector<ASN1ObjectIdentifier>();
        Vector<X509Extension> values = new Vector<X509Extension>();
        objectIdentifiers.add(OCSPObjectIdentifiers.id_pkix_ocsp_nonce);
        values.add(new X509Extension(false, new DEROctetString(nonce.toByteArray())));
        generator.setRequestExtensions(new X509Extensions(objectIdentifiers, values));
        return generator.generate();
    }

    private static String getOcspUrl(X509Certificate certificate) throws Exception {
        byte[] octetBytes = certificate
                .getExtensionValue(X509Extension.authorityInfoAccess.getId());
        DLSequence dlSequence = null;
        ASN1Encodable asn1Encodable = null;
        try {
            ASN1Primitive fromExtensionValue = X509ExtensionUtil
                    .fromExtensionValue(octetBytes);
            if (!(fromExtensionValue instanceof DLSequence)) {
                return null;
            }
            dlSequence = (DLSequence) fromExtensionValue;
            for (int i = 0; i < dlSequence.size(); i++) {
                asn1Encodable = dlSequence.getObjectAt(i);
                if (!(asn1Encodable instanceof DLSequence)) {
                    break;
                }
            }
            if (!(asn1Encodable instanceof DLSequence)) {
                return null;
            }
            dlSequence = (DLSequence) asn1Encodable;
            for (int i = 0; i < dlSequence.size(); i++) {
                asn1Encodable = dlSequence.getObjectAt(i);
                if (asn1Encodable instanceof DERTaggedObject) {
                    break;
                }
            }
            if (!(asn1Encodable instanceof DERTaggedObject)) {
                return null;
            }
            DERTaggedObject derTaggedObject = (DERTaggedObject) asn1Encodable;
            byte[] encoded = derTaggedObject.getEncoded();
            if (derTaggedObject.getTagNo() == 6) {
                int len = encoded[1];
                return new String(encoded, 2, len);
            }
        } catch (IOException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    public int convertLocation(int y) {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String pageHeightStr = rb.getString("pageHeight");
        String imageSignatureHeightStr = rb.getString("imageSignatureHeight");
        int pageHeight = Integer.parseInt(pageHeightStr);
        int imageSignatureHeight = Integer.parseInt(imageSignatureHeightStr);
        int padding = 10;
        if ((pageHeight - y) < 0) {
            y = 0;
        } else {
            y = pageHeight - y;
        }
        if (y > pageHeight - imageSignatureHeight) {
            y = pageHeight - imageSignatureHeight - padding;
        }
        return y;
    }

    /**
     * hieptq update 141015 sign file using plugin
     *
     * @return
     */
    public String onSignPlugin() {
        boolean result = true;
        String errorCode = "";
        Calendar cal = Calendar.getInstance();
        try {
            String signType = getRequest().getParameter("signType");
            String fileName = getRequest().getParameter("fileName");
            String fileName0 = getRequest().getParameter("fileName0");
            //hieptq update vi tri luu file
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String uploadPath = rb.getString("PERMIT_path");//160629 binhnt update duong dan luu file cong bo
            String subDir = String.valueOf(cal.getTime().getYear() + 1900)
                    + separatorFile + String.valueOf(cal.getTime().getMonth() + 1)
                    + separatorFile + String.valueOf(cal.getTime().getDate())
                    + separatorFile;//ex: 2016\6\29\
            String strPath = rb.getString("PERMIT_upload") + subDir;
            String copyPath = rb.getString("file_sign_link");

            String paperOnly = "";

            File folderExisting = new File(getSafeFileName(strPath));
            if (!folderExisting.isDirectory()) {
                folderExisting.mkdir();
            }
            if (folderExisting.isDirectory()) {
                //tao folder theo ngay thang
                File temp = new File(strPath);
                if (!temp.isDirectory()) {
                    temp.mkdirs();
                }
            }

            String[] parts = fileName.split("_");
            if (parts.length != 4 && parts.length != 5 && parts.length != 6) {
                errorCode = "SI_015";
                result = false;
            }
            if (parts.length == 5 && "LD".equals(parts[0])) {
                paperOnly = parts[0] + "_" + parts[1] + "_" + parts[2] + "_" + parts[3] + "_" + "2" + ".pdf";
            }
            if (parts.length == 6 && parts[0].equals("VT")) {
                paperOnly = parts[0] + "_" + parts[1] + "_" + parts[2] + "_" + parts[3] + "_" + parts[4] + "_" + "2" + ".pdf";
            }
            //hieptq update 106015
            String outputFile = strPath + fileName;
            String outputFileOriginal = strPath + fileName0;
            String signature;
            String signatureOriginal = null;
            Base64 decoder = new Base64();
            signature = new String(decoder.decode(getRequest().getParameter("signData").replace("_", "+").getBytes()), "UTF-8");

            SignPdfFile pdfSig = new SignPdfFile();
            SignPdfFile pdfSig0 = new SignPdfFile();
            String checkTsaStr = rb.getString("checkTSA");
            Long checkTSA = Long.parseLong(checkTsaStr);
            pdfSig = (SignPdfFile) getRequest().getSession().getAttribute("PDFSignature");
            //Hiepvv hoso SDBS sau cong bo khong can cai nay
            if (fileName0 != null && fileName0.length() > 0 && ("PDHS".equals(signType) || "PDHS_VT".equals(signType))) {
                signatureOriginal = new String(decoder.decode(getRequest().getParameter("signDataOriginal").replace("_", "+").getBytes()), "UTF-8");
                pdfSig0 = (SignPdfFile) getRequest().getSession().getAttribute("PDFSignature2");
            }
            //hieptq update 110615
            String fileSignOutLink = getRequest().getParameter("outPutPath");
            String fileSignOutLink2 = getRequest().getParameter("outPutPath2");
            try {
                if (checkTSA == 1l) {
                    pdfSig.insertSignatureFinal(signature, fileSignOutLink, outputFile, true);
                    if ("PDHS".equals(signType) || "PDHS_VT".equals(signType)) {
                        pdfSig0.insertSignatureFinal(signatureOriginal, fileSignOutLink2, outputFileOriginal, true);
                    }
                } else {
                    pdfSig.insertSignatureFinal(signature, fileSignOutLink, outputFile, false);
                    //Hiepvv hoso SDBS sau cong bo khong can cai nay
                    if (fileSignOutLink2 != null && fileSignOutLink2.length() > 0 && ("PDHS".equals(signType) || "PDHS_VT".equals(signType))) {
                        pdfSig0.insertSignatureFinal(signatureOriginal, fileSignOutLink2, outputFileOriginal, false);
                    }
                }
//                if (signType.equals("PDHS") || signType.equals("VT")) {
//                    File source = new File(copyPath + paperOnly);
//                    File dest = new File(PATH1 + paperOnly);
//                    copyFileUsingFileStreams(source, dest);
//                }
            } catch (IOException ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_016";
                System.out.println("IOException " + ex.toString());
                result = false;
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_017";
                System.out.println("Exception " + ex.getMessage());
                result = false;
            } finally {
                try {
                    if (deleteFile(fileSignOutLink)) {
                        System.out.println("Deleted file: " + fileSignOutLink);
                    } else {
                        errorCode = "SI_018";
                        result = false;
                    }
                    //Hiepvv SDBS sau cong bo khong co file 2
                    if (("PDHS".equals(signType) || "PDHS_VT".equals(signType))
                            && fileSignOutLink2 != null && fileSignOutLink2.length() > 0) {
                        if (deleteFile(copyPath + paperOnly)) {
                            System.out.println("Deleted file: " + copyPath + paperOnly);
                        } else {
                            errorCode = "SI_020";
                            result = false;
                        }
                        if (deleteFile(fileSignOutLink2)) {
                            System.out.println("Deleted file: " + fileSignOutLink2);
                        } else {
                            errorCode = "SI_019";
                            result = false;
                        }
                    }
                    if (deleteFile(copyPath + fileName)) {
                        System.out.println("Deleted file: " + copyPath + fileName);
                    } else {
                        errorCode = "SI_021";
                        result = false;
                    }
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
                    System.out.println("Delete file fail ! " + ex.toString());
                }
            }
            System.out.println("Signed file: " + outputFile);
            try {
                if (updateSignPlugin(fileName, subDir, uploadPath) == false) {
                    errorCode = "SI_022";
                    result = false;
                }
                if (("PDHS".equals(signType) || "PDHS_VT".equals(signType))
                        //Hiepvv
                        && fileSignOutLink2 != null && fileSignOutLink2.length() > 0) {
                    if (updateSignPlugin(paperOnly, subDir, uploadPath) == false) {
                        errorCode = "SI_023";
                        result = false;
                    }
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_024";
                result = false;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            errorCode = "SI_025";
            result = false;

        }
        List resultMessage = new ArrayList();
        if (result) {
            resultMessage.add("1");
        } else {
            resultMessage.add("0");
            resultMessage.add(errorCode);
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String onSignPluginAA() {
        boolean result = true;
        String errorCode = "";
        Calendar cal = Calendar.getInstance();
        try {
//            String signType = getRequest().getParameter("signType");
            String fileName = getRequest().getParameter("fileName");
//            String fileName0 = getRequest().getParameter("fileName0");
            //hieptq update vi tri luu file
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String uploadPath = rb.getString("PERMIT_path");
            String subDir = String.valueOf(cal.getTime().getYear() + 1900)
                    + separatorFile + String.valueOf(cal.getTime().getMonth() + 1)
                    + separatorFile + String.valueOf(cal.getTime().getDate())
                    + separatorFile;//ex: 2016\6\29\
            String PATH1 = rb.getString("PERMIT_upload") + subDir;
            String copyPath = rb.getString("file_sign_link");

            File folderExisting = new File(getSafeFileName(PATH1));
            if (!folderExisting.isDirectory()) {
                folderExisting.mkdir();
            }
            if (folderExisting.isDirectory()) {
                //tao folder theo ngay thang
                File temp = new File(PATH1);
                if (!temp.isDirectory()) {
                    temp.mkdirs();
                }
            }

            String[] parts = fileName.split("_");
            if (parts.length != 4 && parts.length != 5 && parts.length != 6) {
                errorCode = "SI_015";
                result = false;
            }
//            if (parts.length == 5 && parts[0].equals("LD")) {
//                paperOnly = parts[0] + "_" + parts[1] + "_" + parts[2] + "_" + parts[3] + "_" + "2" + ".pdf";
//            }
//            if (parts.length == 6 && parts[0].equals("VT")) {
//                paperOnly = parts[0] + "_" + parts[1] + "_" + parts[2] + "_" + parts[3] + "_" + parts[4] + "_" + "2" + ".pdf";
//            }
            //hieptq update 106015
            String outputFile = PATH1 + fileName;
//            String outputFileOriginal = "";
//            if (fileName0 != null && fileName0 != "") {
//                outputFileOriginal = PATH1 + fileName0;
//            }
            String signature;
//            String signatureOriginal = null;
            Base64 decoder = new Base64();
            signature = new String(decoder.decode(getRequest().getParameter("signData").replace("_", "+").getBytes()), "UTF-8");

            SignPdfFile pdfSig = new SignPdfFile();
//            SignPdfFile pdfSig0 = new SignPdfFile();
            String checkTsaStr = rb.getString("checkTSA");
            Long checkTSA = Long.parseLong(checkTsaStr);
            pdfSig = (SignPdfFile) getRequest().getSession().getAttribute("PDFSignature");
            //Hiepvv hoso SDBS sau cong bo khong can cai nay
//            if (fileName0 != null && fileName0.length() > 0 && (signType.equals("PDHS") || signType.equals("PDHS_VT"))) {
//                signatureOriginal = new String(decoder.decode(getRequest().getParameter("signDataOriginal").replace("_", "+").getBytes()), "UTF-8");
//                pdfSig0 = (SignPdfFile) getRequest().getSession().getAttribute("PDFSignature2");
//            }
            //hieptq update 110615
            String fileSignOutLink = getRequest().getParameter("outPutPath");
//            String fileSignOutLink2 = getRequest().getParameter("outPutPath2");
            try {
                if (checkTSA == 1l) {
                    pdfSig.insertSignatureFinal(signature, fileSignOutLink, outputFile, true);
//                    if (signType.equals("PDHS") || signType.equals("PDHS_VT")) {
//                        if (!outputFileOriginal.equals("")) {
//                            pdfSig0.insertSignatureFinal(signatureOriginal, fileSignOutLink2, outputFileOriginal, true);
//                        }
//                    }
                } else {
                    pdfSig.insertSignatureFinal(signature, fileSignOutLink, outputFile, false);
//                    if (fileSignOutLink2 != null && fileSignOutLink2.length() > 0 && (signType.equals("PDHS") || signType.equals("PDHS_VT"))) {
//                        if (!outputFileOriginal.equals("")) {
//                            pdfSig0.insertSignatureFinal(signatureOriginal, fileSignOutLink2, outputFileOriginal, false);
//                        }                        
//                    }
                }
            } catch (IOException ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_016";
                System.out.println("IOException " + ex.toString());
                result = false;
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_017";
                System.out.println("Exception " + ex.getMessage());
                result = false;
            } finally {
                try {
                    if (deleteFile(fileSignOutLink)) {
                        System.out.println("Deleted file: " + fileSignOutLink);
                    } else {
                        errorCode = "SI_018";
                        result = false;
                    }
                    //Hiepvv SDBS sau cong bo khong co file 2
//                    if ((signType.equals("PDHS") || signType.equals("PDHS_VT"))
//                            && fileSignOutLink2 != null && fileSignOutLink2.length() > 0 && !outputFileOriginal.equals("")) {
//                        if (deleteFile(copyPath + paperOnly)) {
//                            System.out.println("Deleted file: " + copyPath + paperOnly);
//                        } else {
//                            errorCode = "SI_020";
//                            result = false;
//                        }
//                        if (deleteFile(fileSignOutLink2)) {
//                            System.out.println("Deleted file: " + fileSignOutLink2);
//                        } else {
//                            errorCode = "SI_019";
//                            result = false;
//                        }
//                    }
                    if (deleteFile(copyPath + fileName)) {
                        System.out.println("Deleted file: " + copyPath + fileName);
                    } else {
                        errorCode = "SI_021";
                        result = false;
                    }
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
                    System.out.println("Delete file fail ! " + ex.toString());
                }
            }
            System.out.println("Signed file: " + outputFile);
            try {
                if (updateSignPlugin(fileName, subDir, uploadPath) == false) {
                    errorCode = "SI_022";
                    result = false;
                }
//                if ((signType.equals("PDHS") || signType.equals("PDHS_VT"))
//                        //Hiepvv
//                        && fileSignOutLink2 != null && fileSignOutLink2.length() > 0) {
//                    if (updateSignPlugin(paperOnly) == false) {
//                        errorCode = "SI_023";
//                        result = false;
//                    }
//                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_024";
                result = false;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            errorCode = "SI_025";
            result = false;

        }
        List resultMessage = new ArrayList();
        if (result) {
            resultMessage.add("1");
        } else {
            resultMessage.add("0");
            resultMessage.add(errorCode);
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public boolean deleteFile(String filePath) {
        boolean result = true;
        try {
            if (filePath != null && filePath.trim().length() > 0) {
                File file = new File(filePath);
                if (!file.delete()) {
                    result = false;
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            result = false;
        }
        return result;
    }
// Insert cac file dc ky vao DB

    /**
     *
     * @param fileName
     * @return
     */
    public Boolean updateSignPlugin(String fileName, String destination, String uploadPath) {
        boolean result = true;
        try {
            // Save info to DB
            String[] parts = fileName.split("_");
            if (parts.length != 4
                    && parts.length != 5
                    && parts.length != 6) {
                result = false;
            }
            String signType = "";
            Integer indexFile = 0;
            if (parts.length == 4) {
                signType = parts[3].substring(0, parts[3].indexOf(".pdf"));
            } else if (parts.length == 6) {
                if ("VT".equals(parts[0])) {
                    signType = parts[4];
                    indexFile = Integer.parseInt(parts[5].substring(0, parts[5].indexOf(".pdf")));
                } else {
                    signType = parts[3];
                    indexFile = Integer.parseInt(parts[4].substring(0, parts[4].indexOf(".pdf")));
                }
            } else if (parts.length == 5) {
                if ("VT".equals(parts[0])) {
                    signType = parts[4].substring(0, parts[4].indexOf(".pdf"));
                } else {
                    signType = parts[3];
                    indexFile = Integer.parseInt(parts[4].substring(0, parts[4].indexOf(".pdf")));
                }
            }
            String fileId = parts[2];
            if ("".equals(fileId) || "".equals(signType)) {
                Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: Tên File không đúng định dạng");
                result = false;
            }

            VoAttachsDAOHE vdhe = new VoAttachsDAOHE();
            if ("LD".equals(parts[0])) {
                VoAttachs voUpload = new VoAttachs();
                voUpload.setObjectId(Long.parseLong(fileId));
                voUpload.setIsActive(1l);
                voUpload.setCreateDate(vdhe.getSysdate());
                if ("PDHS".equals(signType)) {
                    if (indexFile == 0 || indexFile == 1) {
                        voUpload.setObjectType(40L);
                    } else if (indexFile == 2) {
                        voUpload.setObjectType(41L);
                    } else {
                        Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: PDHS không xác định được thứ tự File ký");
                        result = false;
                    }
                    //Hiepvv 0703 dat ten file
                    FilesDAOHE fdhe = new FilesDAOHE();
                    Files fName = fdhe.findById(Long.parseLong(fileId));
                    if (fName.getFilesSourceID() != null && fName.getFilesSourceID() > 0) {
                        voUpload.setAttachName("CongvanSDBSsaucongbo_" + fileName);
                        voUpload.setObjectType(40L);

                    } else {
                        voUpload.setAttachName("Bancongbo_" + fileName);
                    }
                } else if ("CVBS".equals(signType)) {
                    voUpload.setObjectType(71L);
                    voUpload.setAttachName("CongvanSdbs_" + fileName);
                } else {
                    Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: Tên File không đúng định dạng");
                    result = false;
                }
                voUpload.setAttachPath(uploadPath + destination + fileName);
                vdhe.saveDbNotCommit(voUpload);
            } else if ("VT".equals(parts[0])) {
                List<VoAttachs> voa;
                if ("PDHS".equals(signType)) {
                    if (indexFile == 0 || indexFile == 1) {
                        voa = vdhe.getAttachsByObject(Long.parseLong(fileId), 40L);
                        if (voa != null && voa.size() > 0) {
                            for (int i = 0; i < voa.size(); i++) {
                                voa.get(i).setIsActive(0L);
                                vdhe.updateDbNotCommit(voa.get(i));
                            }
                        }
                    } else if (indexFile == 2) {
                        voa = vdhe.getAttachsByObject(Long.parseLong(fileId), 41L);
                        if (voa != null && voa.size() > 0) {
                            for (int i = 0; i < voa.size(); i++) {
                                voa.get(i).setIsActive(0L);
                                vdhe.updateDbNotCommit(voa.get(i));
                            }
                        }
                    }
                }

                VoAttachs voUpload = new VoAttachs();
                voUpload.setObjectId(Long.parseLong(fileId));
                voUpload.setIsActive(1l);
                voUpload.setCreateDate(vdhe.getSysdate());
                if ("PDHS".equals(signType)) {
                    if (indexFile == 0 || indexFile == 1) {
                        voUpload.setObjectType(40L);
                    } else if (indexFile == 2) {
                        voUpload.setObjectType(41L);
                    } else {
                        Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: PDHS không xác định được thứ tự File ký");
                        result = false;
                    }
                    //Hiepvv 0703 dat ten file
                    FilesDAOHE fdhe = new FilesDAOHE();
                    Files fName = fdhe.findById(Long.parseLong(fileId));
                    if (fName.getFilesSourceID() != null && fName.getFilesSourceID() > 0) {
                        voUpload.setAttachName("CongvanSDBSsaucongbo_" + fileName);
                        voUpload.setObjectType(40L);
                    } else {
                        voUpload.setAttachName("Bancongbo_" + fileName);
                    }
                } else if ("CVBS".equals(signType)) {
                    voUpload.setObjectType(71L);
                    voUpload.setAttachName("CongvanSdbs_" + fileName);
                } else {
                    Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: Tên File không đúng định dạng");
                    result = false;
                }
                voUpload.setAttachPath(uploadPath + destination + fileName);
                vdhe.saveDbNotCommit(voUpload);
            }
            // Update status to File
            if ("PDHS".equals(signType)) {
                FilesDAOHE fdhe = new FilesDAOHE();
                Files file = fdhe.findById(Long.parseLong(fileId));
                file.setIsDownload(1L);
                if ("VT".equals(parts[0])) {
                    file.setIsSignPdf(2l);
                    fdhe.saveDbNoCommit(file);
                } else if ("LD".equals(parts[0])) {
                    file.setIsSignPdf(1l);
                    fdhe.saveDbNoCommit(file);
                } else {
                    Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: Tên File không đúng định dạng");
                    result = false;
                }
            }
            vdhe.commitDb();
            //Hiepvv_Home copy file SĐBS sau công bố sang file gốc
            if ("PDHS".equals(signType) && "VT".equals(parts[0])) {
                if (fileId != null && fileId.length() > 0) {
                    Long fId = Long.parseLong(fileId);
                    result = copyFileChangeAfterAnnouncedToFileSource(fId);
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }

    //Hiepvv_Home Copy file nhãn và file công văn của hồ sơ SĐBS sau công bố sang hồ sơ gốc
    public boolean copyFileChangeAfterAnnouncedToFileSource(Long fileId) {
        VoAttachsDAOHE vDAO = new VoAttachsDAOHE();
        ProcedureDAOHE pdhe = new ProcedureDAOHE();
        Files f = new Files();
        FilesDAOHE fDAO = new FilesDAOHE();
        try {
            if (fileId != null && fileId > 0L) {

                f = fDAO.findById(fileId);

                Procedure pro = pdhe.findById(f.getFileType());
                String typePro = "";
                if (pro != null) {
                    typePro = pro.getDescription();
                }
                //Nếu là SĐBS sau công bố
                if (typePro != null && announcementFile05.equalsIgnoreCase(typePro)) {
                    boolean isCheck = false;

                    VoAttachs vAtt;
                    VoAttachs voUpload;

                    List<VoAttachs> lstVoAtt = vDAO.getLstVoAttachByObjectId(fileId);
                    if (lstVoAtt != null && lstVoAtt.size() > 0) {
                        //Check văn thư đóng dấu trả doanh nghiệp
                        for (int i = 0; i < lstVoAtt.size(); i++) {
                            vAtt = lstVoAtt.get(i);
                            if (vAtt.getObjectType() == 41L || vAtt.getObjectType() == 40L) {
                                isCheck = true;
                                break;
                            }
                        }
                        //Nếu văn thư đã trả công bố và chưa copy files
                        if (isCheck == true && (f.getIsCopy() == null || f.getIsCopy() != 1L)) {
                            for (int i = 0; i < lstVoAtt.size(); i++) {
                                vAtt = lstVoAtt.get(i);
                                //Hiepvv chi insert cong van sdbs sau cong bo ve ho so goc
                                if (vAtt.getObjectType() == 41L || vAtt.getObjectType() == 40L) {
                                    voUpload = new VoAttachs();
                                    //Copy
                                    voUpload.setObjectId(f.getFilesSourceID());
                                    voUpload.setIsActive(1l);
                                    voUpload.setCreateDate(vAtt.getCreateDate());
                                    voUpload.setAttachName(vAtt.getAttachName());
                                    voUpload.setAttachPath(vAtt.getAttachPath());
                                    voUpload.setAttachDes("Công văn sửa đổi bổ sung sau công bố");
                                    voUpload.setCategoryName("Công văn sửa đổi bổ sung sau công bố");
                                    voUpload.setObjectType(vAtt.getObjectType());
                                    if (vAtt.getCategoryId() != null) {
                                        voUpload.setCategoryId(vAtt.getCategoryId());
                                    }
                                    if (vAtt.getDeptId() != null) {
                                        voUpload.setDeptId(vAtt.getDeptId());
                                    }
                                    if (vAtt.getIsTemp() != null) {
                                        voUpload.setIsTemp(vAtt.getIsTemp());
                                    }
                                    if (vAtt.getOriginalId() != null) {
                                        voUpload.setOriginalId(vAtt.getOriginalId());
                                    }
                                    if (vAtt.getUserCreateId() != null) {
                                        voUpload.setUserCreateId(vAtt.getUserCreateId());
                                    }

                                    vDAO.saveDbNotCommit(voUpload);
//                                    voUpload.set(vAtt.getAttachDes());
                                }
                                //Hiepvv update file nhan dinh kem ve ho so goc
//                                if(vAtt.getObjectType()==17L || vAtt.getObjectType()==41L || vAtt.getObjectType()==40L){
//                                    voUpload = new VoAttachs();
//                                    //Copy
//                                    voUpload.setObjectId(f.getFilesSourceID();
//                                    voUpload.setIsActive(1l);
//                                    voUpload.setCreateDate(vAtt.getCreateDate());
//                                    voUpload.setAttachName(vAtt.getAttachName());
//                                    voUpload.setAttachPath(vAtt.getAttachPath());
//                                    if(vAtt.getObjectType()==41L || vAtt.getObjectType()==40L){
//                                        voUpload.setAttachDes("Công văn sửa đổi bổ sung sau công bố");
//                                        voUpload.setCategoryName("Công văn sửa đổi bổ sung sau công bố");
//                                        voUpload.setObjectType(30L);
//                                    }else{
//                                        voUpload.setAttachDes(vAtt.getAttachDes());
//                                        voUpload.setCategoryName(vAtt.getCategoryName());
//                                        voUpload.setObjectType(vAtt.getObjectType());
//                                    }
//                                    if(vAtt.getCategoryId()!=null)
//                                        voUpload.setCategoryId(vAtt.getCategoryId());
//                                    if(vAtt.getDeptId()!=null)
//                                        voUpload.setDeptId(vAtt.getDeptId());
//                                    if(vAtt.getIsTemp()!=null)
//                                        voUpload.setIsTemp(vAtt.getIsTemp());
//                                    if(vAtt.getOriginalId()!=null)
//                                        voUpload.setOriginalId(vAtt.getOriginalId());
//                                    if(vAtt.getUserCreateId()!=null)
//                                        voUpload.setUserCreateId(vAtt.getUserCreateId());
//                                    
//                                    vDAO.saveDbNotCommit(voUpload);
////                                    voUpload.set(vAtt.getAttachDes());
//                                }
                            }
                            vDAO.commitDb();
                            //Update files đã chuyển các file cần thiết qua hồ sơ gốc
                            f.setIsCopy(1L);
                            getSession().update(f);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    //Hiepvv_Home Lãnh đạo sửa đổi nội dung công văn
    public boolean loadTitleAndContentEditAfterAnnounced() {
        Long fileId = getRequest().getParameter("fileId") == null ? 0L : Long.parseLong(getRequest().getParameter("fileId"));
        try {
            if (fileId > 0L) {
                FilesDAOHE fHE = new FilesDAOHE();
                Files f = new Files();
                f = fHE.findById(fileId);
                createForm = new FilesForm();
                createForm.setFileId(fileId);
                createForm.setTitleEditATTP(f.getTitleEditATTP());
                createForm.setContentsEditATTP(f.getContentsEditATTP());
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return false;
        }
        return true;
    }

    //Acction edit title and content
    public String onEditTitleAndContent() {
        Long fileId = getRequest().getParameter("fileId") == null ? 0L : Long.parseLong(getRequest().getParameter("fileId"));
        String title = getRequest().getParameter("title") == null ? "" : getRequest().getParameter("fileId");
        String content = getRequest().getParameter("content") == null ? "" : getRequest().getParameter("fileId");
        try {
            if (fileId > 0L) {
                FilesDAOHE fHE = new FilesDAOHE();
                Files f = fHE.findById(fileId);
                f.setTitleEditATTP(title);
                f.setContentsEditATTP(content);
                getSession().update(f);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return GRID_DATA;
        }
        return GRID_DATA;
    }

    /**
     * phan cong tham dinh ho so
     *
     * @return
     */
    public String onReAssignEvaluation() {
        getGridInfo();

        if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
            try {
                String startServerStr = getRequest().getSession().getAttribute("reAssignEvaluation.startServer") == null ? "" : getRequest().getSession().getAttribute("reAssignEvaluation.startServer").toString();
                String countServerStr = getRequest().getSession().getAttribute("reAssignEvaluation.countServer") == null ? "" : getRequest().getSession().getAttribute("reAssignEvaluation.countServer").toString();

                if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                    count = Integer.parseInt(countServerStr);
                    start = Integer.parseInt(startServerStr);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                log.error(ex.getMessage());
            }
        }

        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        GridResult gr = bdhe.findAllFileForReAssignEvaluation(searchForm, getDepartmentId(), getUserId(), start, count, sortField);

        getRequest().getSession().setAttribute("reAssignEvaluation.startServer", start);
        getRequest().getSession().setAttribute("reAssignEvaluation.countServer", count);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /*
     * Danh sach ho so da cong bo cua doanh nghiep
     */
    public String toBusinessEditAfterAnnouncedFilesPage() {
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setStatus(22l);
        }
        isEdit = true;
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure2();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
//        CategoryDAOHE che = new CategoryDAOHE();
//        lstProductType = che.findAllCategory("SP");
//        getRequest().setAttribute("lstProductType", lstProductType);
//        BusinessDAOHE bdhe = new BusinessDAOHE();
//        Business bus = bdhe.findById(getBusinessId());
//
//        if (bus.getIsCa() != null && bus.getIsCa() == 1) {
//            getRequest().setAttribute("isCa", bus.getIsCa());
//        } else {
//            getRequest().setAttribute("isCa", 0);
//        }
//        DepartmentDAOHE dphe = new DepartmentDAOHE();
//        Department dept = dphe.findByDeptCode("ATTP");
//        Long AgencyId = dept.getDeptId();
//        String AgencyName = dept.getDeptName();
//        getRequest().setAttribute("AgencyId", AgencyId);
//        getRequest().setAttribute("AgencyName", AgencyName);
        return businessEditAfterAnnouncedFilesPage;
    }

    /**
     * Hiepvv Tim kiem danh sach ho so sua doi cua mot ho so cua doanh nghiep
     *
     * @return
     */
    public String onsearchListFilesChangesAfterAnnouned() {
        getGridInfo();

        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
        ProcedureDAOHE pdaohe = new ProcedureDAOHE();
        Procedure p = new Procedure();
        //Get Procedure SDBS 
        try {
            p = pdaohe.getProcedureByDescription(announcementFile05);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            p = null;
            return GRID_DATA;
        }
        GridResult gr;
        //Get ID File source when click view
        if (p != null) {
            if (fileSourceID == null) {
                fileSourceID = 0L;
            }
            gr = fdhe.searchListFilesChangesAfterAnnouned(fileSourceID, start, count, sortField, p.getProcedureId());
        } else {
            gr = new GridResult(0, null);
        }

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    public String onEvaluateNew() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List resultMessage = new ArrayList();
        boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
        if (check) {
            boolean bReturn = fdhe.onEvaluate(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());//thuc hien tham dinh ho so
            if (bReturn) {
                resultMessage.add("1");
                resultMessage.add("Lưu dữ liệu thành công");
                // Hiepvv_Home Update Title And Content of File SDBS after announced
                Files fo = fdhe.findById(createForm.getFileId());
                if (fo.getFilesSourceID() != null && fo.getFilesSourceID() > 0 && fo.getFileSourceCode() != null) {
                    if (createForm.getTitleEditATTP() != null) {
                        fo.setTitleEditATTP(createForm.getTitleEditATTP());
                    } else {
                        fo.setTitleEditATTP(createForm.getTitleEdit());
                    }
                    if (createForm.getContentsEditATTP() != null) {
                        fo.setContentsEditATTP(createForm.getContentsEditATTP());
                    } else {
                        fo.setContentsEditATTP(createForm.getContentsEdit());
                    }
                    getSession().update(fo);
                }
                //End Hiepvv_Home
                Files file = fdhe.findById(createForm.getFileId());
                fdhe.saveStatusFiles(file, "Hồ sơ mã: " + file.getFileCode() + " Đã được thẩm định");
            } else {
                resultMessage.add("3");
                resultMessage.add("Lưu dữ liệu không thành công");
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Thẩm định hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());
        return GRID_DATA;
    }

    public String onAssignEvaluationAfterAnnounced() {
        getGridInfo();

        if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
            try {
                String startServerStr = getRequest().getSession().getAttribute("assignEvaluation.startServer") == null ? "" : getRequest().getSession().getAttribute("assignEvaluation.startServer").toString();
                String countServerStr = getRequest().getSession().getAttribute("assignEvaluation.countServer") == null ? "" : getRequest().getSession().getAttribute("assignEvaluation.countServer").toString();

                if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                    count = Integer.parseInt(countServerStr);
                    start = Integer.parseInt(startServerStr);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                log.error(ex.getMessage());
            }
        }

        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        GridResult gr = bdhe.findAllFileForAssignEvaluationAfterAnnounced(searchForm, getDepartmentId(), getUserId(), start, count, sortField);

        getRequest().getSession().setAttribute("assignEvaluation.startServer", start);
        getRequest().getSession().setAttribute("assignEvaluation.countServer", count);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    public String toAssignEvaluationAfterAnnounced() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        //Hiepvv tach rieng chuc nang sua doi sau cong bo
        List lstTTHC = cdhe.getAllProcedureAfterAnnounced();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        return ASSIGN_EVALUATION_AFTER_ANNOUNCED_PAGE;
    }

    public String onsearchLookupFilesAfterAnnounced() {
        getGridInfo();

        if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
            try {
                String startServerStr = getRequest().getSession().getAttribute("lookupAfterAnnounced.startServer") == null ? "" : getRequest().getSession().getAttribute("lookupFilesByClerical.startServer").toString();
                String countServerStr = getRequest().getSession().getAttribute("lookupAfterAnnounced.countServer") == null ? "" : getRequest().getSession().getAttribute("lookupFilesByClerical.countServer").toString();

                if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                    count = Integer.parseInt(countServerStr);
                    start = Integer.parseInt(startServerStr);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                log.error(ex.getMessage());
            }
        }

        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        GridResult gr = bdhe.searchLookupFilesAfterAnnounced(searchForm,
                getDepartmentId(), getUserId(), Constants.ROLES.CLERICAL_ROLE, start, count, sortField, "");

        getRequest().getSession().setAttribute("lookupAfterAnnounced.startServer", start);
        getRequest().getSession().setAttribute("lookupAfterAnnounced.countServer", count);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    public String lookupFilesAfterAnnounced() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE,
                Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        UsersDAOHE udaohe = new UsersDAOHE();
        List lstLDP = udaohe.findLstUserByPosition(getDepartmentId(), Constants.POSITION.LEADER_OF_STAFF_T);
        if (lstLDP == null || lstLDP.isEmpty()) {
            List<String> lstLeader = new ArrayList<String>();
            lstLeader.add(Constants.POSITION.LEADER_OF_STAFF_T);
            lstLeader.add(Constants.POSITION.GDTT);
            lstLDP = udaohe.findLstUserByLstPosition(getDepartmentId(), lstLeader);
        }
        lstLeaderOfStaff = new ArrayList();
        if (lstLDP != null && !lstLDP.isEmpty()) {
            lstLeaderOfStaff.addAll(lstLDP);
        }

        lstLeaderOfStaff.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE,
                Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstLeaderOfStaff", lstLeaderOfStaff);
        return lookupAfterAnnouncedPage;
    }

    public String toEvaluateLeaderAAPage() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE,
                Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        // Get Role
        List<Roles> roles = getListRolesByUser();
        String lstRole = "";
        if (roles != null && roles.size() > 0) {
            for (int i = 0; i < roles.size(); i++) {
                lstRole += roles.get(i).getRoleCode() + ";";
            }
        }
        getRequest().setAttribute("lstRole", lstRole);

        UsersDAOHE udaohe = new UsersDAOHE();
        List<String> lstStaff = new ArrayList<String>();
        lstStaff.add(Constants.POSITION.VFA_CV);
        lstStaff.add(Constants.POSITION.NV);
        if (udaohe.checkUserByLstPosition(getDepartmentId(), getUserId(), lstStaff)) {//la chuyen vien
            List lstLDP = udaohe.getAllLeaderOfStaffInOffice(getDepartmentId());
            if (lstLDP != null) {
                List lstLeaderOfStaffOnGrid = new ArrayList();
                lstLeaderOfStaffOnGrid.addAll(lstLDP);
                lstLeaderOfStaffOnGrid.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE,
                        Constants.COMBOBOX_HEADER_TEXT_SELECT));
                getRequest().setAttribute("lstLeaderOfStaff", lstLeaderOfStaffOnGrid);
            }
        } else {//la pho phong - lay danh sach ldc va truong phong
            List lstLDC = udaohe.getLeaderByUser(getDepartmentId());
            List lstLDP = udaohe.getTruongPhong(getDepartmentId());
            List lstLeaderOfStaffOnGrid = new ArrayList();
            if (lstLDC != null) {
                lstLeaderOfStaffOnGrid.addAll(lstLDC);
            }
            if (lstLDP != null) {
                lstLeaderOfStaffOnGrid.addAll(lstLDP);
            }
            lstLeaderOfStaffOnGrid.add(0, new Users(Constants.COMBOBOX_HEADER_VALUE,
                    Constants.COMBOBOX_HEADER_TEXT_SELECT));
            getRequest().setAttribute("lstLeaderOfStaff", lstLeaderOfStaffOnGrid);
        }
        return EVALUATION_LEADER_PAGE_AA;
    }

    public String onEvaluateByLeaderManyFiles() {

        List resultMessage = new ArrayList();
        jsonDataGrid.setItems(resultMessage);
        FilesDAOHE fdhe = new FilesDAOHE();
        int nSuccess = 0;
        int nError = 0;
        String sid = "";
        Long id = getRequest().getParameter("leaderId") == null ? 0L : Long.parseLong(getRequest().getParameter("leaderId"));
        String name = "";
        try {
            Base64 decoder = new Base64();
            name = new String(decoder.decode(getRequest().getParameter("leaderName").replace("_", "+").getBytes()), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            Logger.getLogger(FilesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < lstItemOnGrid.size(); i++) {
            FilesForm form = lstItemOnGrid.get(i);
            if (form != null && form.getFileId() != null && form.getFileId() != 0D) {
                boolean check = fdhe.validateRoleUser(form.getFileId(), form, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
                if (check) {
                    form.setStatus(Constants.FILE_STATUS.EVALUATED);
                    form.setLeaderStaffRequest("Đã thẩm định nhiều hồ sơ đạt.");
                    form.setLeaderReviewId(id);
                    form.setLeaderReviewName(name);
                    boolean bReturn = fdhe.onEvaluateByLeaderManyFiles(form, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
                    sid += form.getFileId() + ",";
                    if (bReturn) {
                        nSuccess++;
                    } else {
                        nError++;
                    }
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");
                }
            }
        }
        String strAlert = "Thẩm định nhiều hồ sơ thành công, có " + nSuccess + " hồ sơ thành công và " + nError + " hồ sơ thẩm định không thành công";
        resultMessage.add("1");
        resultMessage.add(strAlert);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Thẩm định nhiều hồ sơ", "Thẩm định nhiều hồ sơ id=" + sid, getRequest());
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String onEvaluateByLeaderManyFilesToAdd() {

        List resultMessage = new ArrayList();
        jsonDataGrid.setItems(resultMessage);
        FilesDAOHE fdhe = new FilesDAOHE();
        int nSuccess = 0;
        int nError = 0;
        String sid = "";
        Long id = getRequest().getParameter("leaderId") == null ? 0L : Long.parseLong(getRequest().getParameter("leaderId"));
        String name = "";
        try {
            Base64 decoder = new Base64();
            name = new String(decoder.decode(getRequest().getParameter("leaderName").replace("_", "+").getBytes()), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            Logger.getLogger(FilesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < lstItemOnGrid.size(); i++) {
            FilesForm form = lstItemOnGrid.get(i);
            if (form != null && form.getFileId() != null && form.getFileId() != 0D) {
                boolean check = fdhe.validateRoleUser(form.getFileId(), form,
                        getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
                if (check) {
                    form.setStatus(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                    form.setLeaderStaffRequest("Phó phòng đồng ý với kết luận yêu cầu sđbs của chuyên viên.");
                    form.setLeaderReviewId(id);
                    form.setLeaderReviewName(name);
                    boolean bReturn = fdhe.onEvaluateByLeaderManyFilesToAdd(form,
                            getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
                    sid += form.getFileId() + ",";
                    if (bReturn) {
                        nSuccess++;
                    } else {
                        nError++;
                    }
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Lưu dữ liệu không thành công - Lỗi phân quyền người dùng");
                }
            }
        }
        String strAlert = "Thẩm định bổ sung nhiều hồ sơ thành công, có "
                + nSuccess + " hồ sơ thành công và " + nError + " hồ sơ thẩm định bổ sung không thành công";
        resultMessage.add("1");
        resultMessage.add(strAlert);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Thẩm định nhiều hồ sơ", "Thẩm định nhiều hồ sơ id=" + sid, getRequest());
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String onAssignEvaluationForRE() {
        getGridInfo();

        if (searchForm.getFlagSavePaging() != null && searchForm.getFlagSavePaging() == 1) {
            try {
                String startServerStr = getRequest().getSession().getAttribute("assignEvaluation.startServer") == null ? "" : getRequest().getSession().getAttribute("assignEvaluation.startServer").toString();
                String countServerStr = getRequest().getSession().getAttribute("assignEvaluation.countServer") == null ? "" : getRequest().getSession().getAttribute("assignEvaluation.countServer").toString();

                if (!startServerStr.isEmpty() && !countServerStr.isEmpty()) {
                    count = Integer.parseInt(countServerStr);
                    start = Integer.parseInt(startServerStr);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                log.error(ex.getMessage());
            }
        }

        if (searchForm == null) {
            searchForm = new FilesForm();
            searchForm.setDeptId(getDepartmentId());
        }
        FilesNoClobDAOHE bdhe = new FilesNoClobDAOHE();
        GridResult gr = bdhe.findAllFileForAssignEvaluationForRE(searchForm,
                getDepartmentId(), getUserId(), start, count, sortField);

        getRequest().getSession().setAttribute("assignEvaluation.startServer", start);
        getRequest().getSession().setAttribute("assignEvaluation.countServer", count);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    public String getCommentEvaluateFormByLeaderForAA() {
        getGridInfo();
        List customInfo = new ArrayList();
        String strObjectId = getRequest().getParameter("objectId");
        Long objectId = 0l;
        try {
            objectId = Long.parseLong(strObjectId);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(en.getMessage());
        }
        FilesDAOHE fdaohe = new FilesDAOHE();
        Files fbo = fdaohe.findById(objectId);
        EvaluationRecordsDAOHE erdaohe = new EvaluationRecordsDAOHE();

        EvaluationRecords erbo = erdaohe.findFilesByFileId(fbo);
        String legal = "";
        String foodSafetyQuality = "";
        String effectUtility = "";
        String standard = "";
        Long llegal = null;
        Long lfoodSafetyQuality = null;
        Long leffectUtility = null;
        Long lstandard = null;
        if (erbo != null && fbo != null) {
            legal = erbo.getLegalContent();
            foodSafetyQuality = erbo.getFoodSafetyQualityContent();
            effectUtility = erbo.getEffectUtilityContent();
            standard = fbo.getStaffRequest();
            lstandard = fbo.getEffectiveDate();
            llegal = erbo.getLegal();
            lfoodSafetyQuality = erbo.getFoodSafetyQuality();
            leffectUtility = erbo.getEffectUtility();
        }
        customInfo.add(legal);//0
        customInfo.add(foodSafetyQuality);//1
        customInfo.add(effectUtility);//2
        customInfo.add(standard);//3
        customInfo.add(lstandard);//4
        customInfo.add(llegal);//5
        customInfo.add(lfoodSafetyQuality);//6
        customInfo.add(leffectUtility);//7

        //160628 bo sung noi dung sua sau cong bo
        String titleEditATTP = "";
        String contentsEditATTP = "";
        if (fbo != null) {
            if (fbo.getTitleEditATTP() != null
                    && !"".equals(fbo.getTitleEditATTP().trim())) {
                titleEditATTP = fbo.getTitleEditATTP();
            } else {
                titleEditATTP = fbo.getTitleEdit();
            }
            if (fbo.getContentsEditATTP() != null
                    && !"".equals(fbo.getContentsEditATTP().trim())) {
                contentsEditATTP = fbo.getContentsEditATTP();
            } else {
                contentsEditATTP = fbo.getContentsEdit();
            }
        }
        customInfo.add(titleEditATTP);//8
        customInfo.add(contentsEditATTP);//9
        //!160628

        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /**
     *
     * @return @throws IOException
     */
    public String actionSignCARegisterCA() throws IOException {
        boolean result;
        String base64Hash = "";
        String certSerial = "";
        String errorCode = "";
        SignPdfFile pdfSig = new SignPdfFile();
        try {
            String rootCert = null, base64Certificate = null, certChain = null;
            Base64 decoder = new Base64();
            certChain = new String(decoder.decode(getRequest().getParameter("cert").replace("_", "+").getBytes()), "UTF-8");
            String[] chain;
            try {
                chain = certChain.split(",");
                rootCert = chain[1];
                base64Certificate = chain[0];
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_001";
                result = false;
            }
            if (base64Certificate == null) {
                errorCode = "SI_002";
                result = false;
            }
            X509Certificate x509Cert = null;
            X509Certificate x509CertChain = null;
            try {
                x509Cert = CertUtils.getX509Cert(base64Certificate);
                x509CertChain = CertUtils.getX509Cert(rootCert);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_003";
                result = false;
            }

            PDFServerClientSignature pdfSCS = new PDFServerClientSignature();
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String TSA_LINK = rb.getString("tsaUrl");
            pdfSCS.setTSA_LINK(TSA_LINK);
            String checkOcspStr = rb.getString("checkOCSP");
            Long checkOCSP = Long.parseLong(checkOcspStr);
            try {
                certSerial = x509Cert.getSerialNumber().toString(16);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_004";
                result = false;
            }
            CaUserDAOHE ca = new CaUserDAOHE();
            if (!ca.checkCaSerial("SerialNumber:[" + certSerial + "]")) {
                result = true;
            } else {
                result = false;
            }
            try {
                if (checkOCSP == 1l) {
                    OCSP.RevocationStatus.CertStatus status = checkRevocationStatus((X509Certificate) x509Cert, (X509Certificate) x509CertChain);
                    if (status != OCSP.RevocationStatus.CertStatus.GOOD) {
                        errorCode = "SI_006";
                        result = false;
                    }
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_007";
                result = false;
            }
        } catch (JsonSyntaxException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            errorCode = "SI_014";
            result = false;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            errorCode = "SI_015";
            result = false;
        } finally {

        }
        List resultMessage = new ArrayList();
        if (result) {
            CaUser caUserBo = new CaUser();
            caUserBo.setCaSerial("SerialNumber:[" + certSerial + "]");
            caUserBo.setUserName(getUserLogin());
            caUserBo.setStatus(1);
            caUserBo.setBusinessId(getBusinessId());
            getSession().saveOrUpdate(caUserBo);

            BusinessDAOHE bdhe = new BusinessDAOHE();
            Business bus = bdhe.findById(getBusinessId());
            bus.setIsCa(1l);
            getSession().update(bus);

            HttpServletRequest req = getRequest();
            HttpSession session = req.getSession();
            session.setAttribute("PDFSignature", pdfSig);
            resultMessage.add("1");
            resultMessage.add("Kiểm tra thông tin chữ kí số thành công.");
            resultMessage.add(base64Hash);
            resultMessage.add(certSerial);
        } else {
            resultMessage.add("0");
            resultMessage.add("Kiểm tra thông tin chữ kí số không thành công " + errorCode);
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onSignPluginRegisterCA() {
        List resultMessage = new ArrayList();
        resultMessage.add("1");
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return @throws IOException
     */
    public String actionSignCAFile() throws IOException {
        boolean result = true;
        String base64Hash = "";
        String certSerial = "";
        String fileId = "";
        String outPutFileFinal = "";
        String fileName = "";
        String fileToSign = "";
        String errorCode = "";
        SignPdfFile pdfSig = new SignPdfFile();
        try {
            fileId = getRequest().getParameter("fileId");
            String rootCert = null,
                    base64Certificate = null,
                    certChain = null;
            Base64 decoder = new Base64();
            certChain = new String(decoder.decode(getRequest().getParameter("cert").replace("_", "+").getBytes()), "UTF-8");
            String sToFind = getRequest().getParameter("signType");
            String path = getRequest().getParameter("path");
            String[] pathArr = path.split(";");
            fileToSign = pathArr[0];
            fileName = pathArr[1];
            String[] chain;
            try {
                chain = certChain.split(",");
                rootCert = chain[1];
                base64Certificate = chain[0];
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_001";
                result = false;
            }
            if (base64Certificate == null) {
                errorCode = "SI_002";
                result = false;
            }
            X509Certificate x509Cert = null;
            X509Certificate x509CertChain = null;
            try {
                x509Cert = CertUtils.getX509Cert(base64Certificate);
                x509CertChain = CertUtils.getX509Cert(rootCert);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_003";
                result = false;
            }

            PDFServerClientSignature pdfSCS = new PDFServerClientSignature();
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String TSA_LINK = rb.getString("tsaUrl");
            pdfSCS.setTSA_LINK(TSA_LINK);
            String checkOcspStr = rb.getString("checkOCSP");
            Long checkOCSP = Long.parseLong(checkOcspStr);
            try {
                certSerial = x509Cert.getSerialNumber().toString(16);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_004";
                result = false;
            }
            String filePath = rb.getString("sign_temp_plugin");
            File f = new File(filePath);
            if (!f.exists()) {
                f.mkdirs();
            }
            outPutFileFinal = filePath + fileName;
            CaUserDAOHE ca = new CaUserDAOHE();
            boolean checkCaUser = true;
            if (!ca.checkCaSerial("SerialNumber:[" + certSerial + "]")) {
                errorCode = "SI_005";
                result = false;
            }
            try {
                if (checkOCSP == 1l) {
                    OCSP.RevocationStatus.CertStatus status = checkRevocationStatus((X509Certificate) x509Cert, (X509Certificate) x509CertChain);
                    if (status != OCSP.RevocationStatus.CertStatus.GOOD) {
                        errorCode = "SI_006";
                        result = false;
                    }
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_007";
                result = false;
            }
            if (checkCaUser) {//u 16 07 29
                String folderPath = ResourceBundleUtil.getString("sign_image");
                String folderStampDN = ResourceBundleUtil.getString("directory");
                String linkImageSign = folderPath + getUserId() + ".png";
                String linkImageStamp = folderPath + "attp.png";
                CaUser cabo;
                CaUserDAOHE cadaohe = new CaUserDAOHE();
                List<CaUser> lstCabo = cadaohe.findCaUserBySerialUser("SerialNumber:[" + certSerial + "]", getUserLogin());
                if (!lstCabo.isEmpty() && lstCabo != null) {
                    cabo = lstCabo.get(0);
                    folderStampDN += cabo.getSignature();
                }
                linkImageSign = linkImageStamp;
                if ((linkImageSign == null && "".equals(linkImageSign))
                        || (linkImageStamp == null && "".equals(linkImageStamp))
                        || (folderStampDN == null && "".equals(folderStampDN))) {
                    errorCode = "SI_008";
                    result = false;
                }
                try {
                    if ("CBDN".equals(sToFind)) {
                        if (fileToSign == null && "".equals(fileToSign)) {
                            errorCode = "SI_009";
                            result = false;
                        }
                        sToFind = "<SI>";
                        SearchTextLocations ptl = new SearchTextLocations();
                        List local = ptl.searchLocation(sToFind, fileToSign,
                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
                        String location = "0;0;0";
                        int pageNumber, lx, ly;
                        if (local != null && local.size() > 0) {
                            location = local.get(0).toString();
                        }
                        String[] parts = location.split(";");
                        pageNumber = Integer.parseInt(parts[0]);
                        lx = (int) Float.parseFloat(parts[1]);
                        ly = (int) Float.parseFloat(parts[2]);
                        ly = convertLocation(ly);
                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
                                new Certificate[]{x509Cert}, pageNumber, folderStampDN, lx + 70, ly + 130, 120, 70, "DN");
                    }
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
                    System.out.println("ERROR SI_012|" + ex.getMessage());
                    errorCode = "SI_012";
                    result = false;
                }

            } else {
                errorCode = "SI_013";
                result = false;
            }
        } catch (JsonSyntaxException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            errorCode = "SI_014";
            result = false;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            errorCode = "SI_015";
            System.out.println(ex.getMessage());
            result = false;
        } finally {

        }
        List resultMessage = new ArrayList();
        if (result) {
            HttpServletRequest req = getRequest();
            HttpSession session = req.getSession();
            session.setAttribute("PDFSignature", pdfSig);
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
            resultMessage.add(base64Hash);
            resultMessage.add(certSerial);
            resultMessage.add(fileId);
            resultMessage.add(outPutFileFinal);
            resultMessage.add(fileName);
        } else {
            resultMessage.add("0");
            resultMessage.add("Lưu dữ liệu không thành công " + errorCode);
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onSignFileUsingPlugin() {
        boolean result = true;
        String errorCode = "";
        Calendar cal = Calendar.getInstance();
        try {
            String fileName = getRequest().getParameter("fileName");
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String uploadPath = rb.getString("PERMIT_path");//160629 binhnt update duong dan luu file cong bo
            String subDir = String.valueOf(cal.getTime().getYear() + 1900)
                    + separatorFile + String.valueOf(cal.getTime().getMonth() + 1)
                    + separatorFile + String.valueOf(cal.getTime().getDate())
                    + separatorFile;//ex: 2016\6\29\
            String strPath = rb.getString("PERMIT_upload") + subDir;
            String copyPath = rb.getString("file_sign_link");

            File folderExisting = new File(getSafeFileName(strPath));
            if (!folderExisting.isDirectory()) {
                folderExisting.mkdir();
            }
            if (folderExisting.isDirectory()) {
                //tao folder theo ngay thang
                File temp = new File(strPath);
                if (!temp.isDirectory()) {
                    temp.mkdirs();
                }
            }

            String[] parts = fileName.split("_");
            if (parts.length != 3) {
                errorCode = "SI_015";
                result = false;
            }
            String outputFile = strPath + fileName;
            String signature;
            Base64 decoder = new Base64();
            signature = new String(decoder.decode(getRequest().getParameter("signData").replace("_", "+").getBytes()), "UTF-8");

            SignPdfFile pdfSig = new SignPdfFile();
            pdfSig = (SignPdfFile) getRequest().getSession().getAttribute("PDFSignature");

            String fileSignOutLink = getRequest().getParameter("outPutPath");
            try {
                pdfSig.insertSignatureFinal(signature, fileSignOutLink, outputFile, false);
            } catch (IOException ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_016";
                System.out.println("IOException " + ex.toString());
                result = false;
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_017";
                System.out.println("Exception " + ex.getMessage());
                result = false;
            } finally {
                try {
                    if (deleteFile(fileSignOutLink)) {
                        System.out.println("Deleted file: " + fileSignOutLink);
                    } else {
                        errorCode = "SI_018";
                        result = false;
                    }
                    if (deleteFile(copyPath + fileName)) {
                        System.out.println("Deleted file: " + copyPath + fileName);
                    } else {
                        errorCode = "SI_021";
                        result = false;
                    }
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
                    System.out.println("Delete file fail ! " + ex.toString());
                }
            }
            System.out.println("Signed file: " + outputFile);
            try {
                if (updateSignFileUsingPlugin(fileName, subDir, uploadPath) == false) {
                    errorCode = "SI_022";
                    result = false;
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_024";
                result = false;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            errorCode = "SI_025";
            result = false;

        }
        List resultMessage = new ArrayList();
        if (result) {
            resultMessage.add("1");
        } else {
            resultMessage.add("0");
            resultMessage.add(errorCode);
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onUpdateIsSignFile() {
        FilesDAOHE fdhe = new FilesDAOHE();
        List resultMessage = new ArrayList();
        try {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
            if (createForm.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {
                getBarcode(createForm);
            }
            Files file = fdhe.findById(createForm.getFileId());
            file.setIsSignPdf(1L);
            getSession().update(file);

            EventLogDAOHE edhe = new EventLogDAOHE();
            edhe.insertEventLog("Phê duyệt hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());
            getSession().getTransaction().commit();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Phê duyệt không thành công");
//            log.error(ex.getMessage());
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @param fileName
     * @param destination
     * @param uploadPath
     * @return
     */
    public Boolean updateSignFileUsingPlugin(String fileName, String destination, String uploadPath) {
        boolean result = true;
        try {
            // Save info to DB
            String[] parts = fileName.split("_");
            String signType = parts[0];
            String fileId = parts[2].substring(0, parts[2].indexOf(".pdf"));

            if ("".equals(fileId) || "".equals(signType)) {
                Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: Tên File không đúng định dạng");
                result = false;
            }
            FilesDAOHE fdhe = new FilesDAOHE();
            VoAttachsDAOHE vdhe = new VoAttachsDAOHE();
            VoAttachs voUpload;
            Files file = fdhe.findById(Long.parseLong(fileId));
            if (file.getStatus().equals(Constants.FILE_STATUS.NEW_CREATE)) {
                List<VoAttachs> listVoAtt = vdhe.getAttachsByObject(file.getFileId(), Constants.ATTACH_OBJECT_TYPE.CBDN);
                if (listVoAtt != null && !listVoAtt.isEmpty()) {
                    voUpload = listVoAtt.get(0);
                    voUpload.setCreateDate(vdhe.getSysdate());
                    voUpload.setObjectType(Constants.ATTACH_OBJECT_TYPE.CBDN);
                    voUpload.setAttachName(fileName);
                    voUpload.setAttachPath(uploadPath + destination + fileName);
                    voUpload.setUserCreateId(getUserId());
                    vdhe.saveDbNotCommit(voUpload);
                } else {
                    voUpload = new VoAttachs();
                    voUpload.setObjectId(Long.parseLong(fileId));
                    voUpload.setIsActive(1l);
                    voUpload.setCreateDate(vdhe.getSysdate());
                    voUpload.setObjectType(Constants.ATTACH_OBJECT_TYPE.CBDN);
                    voUpload.setAttachName(fileName);
                    voUpload.setAttachPath(uploadPath + destination + fileName);
                    voUpload.setUserCreateId(getUserId());
                    vdhe.saveDbNotCommit(voUpload);
                }
            }

            if (file.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {
                List<VoAttachs> listVoAtt = vdhe.getAttachsByObject(file.getFileId(), Constants.ATTACH_OBJECT_TYPE.CBDN);
                if (listVoAtt != null && !listVoAtt.isEmpty()) {
                    for (int i = 0; i < listVoAtt.size(); i++) {
                        listVoAtt.get(0).setIsActive(-1L);
                        vdhe.saveDbNotCommit(listVoAtt.get(0));
                    }
                    voUpload = new VoAttachs();
                    voUpload.setObjectId(Long.parseLong(fileId));
                    voUpload.setIsActive(1l);
                    voUpload.setCreateDate(vdhe.getSysdate());
                    voUpload.setObjectType(Constants.ATTACH_OBJECT_TYPE.CBDN);
                    voUpload.setAttachName(fileName);
                    voUpload.setAttachPath(uploadPath + destination + fileName);
                    voUpload.setUserCreateId(getUserId());
                    vdhe.saveDbNotCommit(voUpload);
                } else {
                    voUpload = new VoAttachs();
                    voUpload.setObjectId(Long.parseLong(fileId));
                    voUpload.setIsActive(1l);
                    voUpload.setCreateDate(vdhe.getSysdate());
                    voUpload.setObjectType(Constants.ATTACH_OBJECT_TYPE.CBDN);
                    voUpload.setAttachName(fileName);
                    voUpload.setAttachPath(uploadPath + destination + fileName);
                    voUpload.setUserCreateId(getUserId());
                    vdhe.saveDbNotCommit(voUpload);
                }
            }

            file.setIsSignPdf(Constants.TYPE_SIGN.DN);
            file.setUserSigned(Constants.TYPE_SIGN.CBDN);
            fdhe.saveDbNoCommit(file);
            vdhe.commitDb();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }
}
