package com.viettel.hqmc.DAO;

import com.viettel.common.util.Constants;
import com.viettel.common.util.DateTimeUtils;
import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.AnnouncementReceiptPaper;
import com.viettel.hqmc.BO.Business;
import com.viettel.hqmc.BO.EvaluationRecords;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.hqmc.DAOHE.AnnouncementReceiptPaperDAOHE;
import com.viettel.hqmc.DAOHE.BusinessDAOHE;
import com.viettel.hqmc.DAOHE.EvaluationRecordsDAOHE;
import com.viettel.hqmc.DAOHE.FilesDAOHE;
import com.viettel.hqmc.DAOHE.ProcedureDAOHE;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.utils.WordExportUtils;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import com.viettel.voffice.database.DAOHibernate.ProcessDAOHE;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.DAOHibernate.EventLogDAOHE;
import com.viettel.vsaadmin.database.BO.Department;
import com.viettel.vsaadmin.database.BO.Position;
import com.viettel.vsaadmin.database.DAOHibernate.DepartmentDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.PositionDAOHE;
import com.viettel.voffice.database.BO.Process;

/**
 *
 * @author vtit_havm2, binhnt53
 */
public class ExportFileDAO extends BaseDAO {

    //giay cong bo + files_details    
    private final String tempSignCongbohopquiTHTL = "/WEB-INF/template/signCongbohopquiTHTL.docx";
    private final String tempSignCongbophuhopCNTL = "/WEB-INF/template/signCongBoPhuHopCNTL.docx";
    private final String tempSignCongbophuhopTHTL = "/WEB-INF/template/signCongbophuhopTHTL.docx";

    private final String tempSignCongbohopquiTH = "/WEB-INF/template/signCongbohopquiTH.docx";
    private final String tempSignCongbohopquiBB = "/WEB-INF/template/signCongbohopquiBB.docx";
    private final String tempSignCongbophuhopCN = "/WEB-INF/template/signCongBoPhuHopCN.docx";
    private final String tempSignCongbophuhopTH = "/WEB-INF/template/signCongbophuhopTH.docx";
    private final String tempSignCongbophuhopBB = "/WEB-INF/template/signCongbophuhopBB.docx";

    private final String tempSignCongbohopquiTHsub = "/WEB-INF/template/signCongbohopquiTHsub.docx";
    private final String tempSignCongbohopquiBBsub = "/WEB-INF/template/signCongbohopquiBBsub.docx";
    private final String tempSignCongbophuhopCNsub = "/WEB-INF/template/signCongBoPhuHopCNsub.docx";
    private final String tempSignCongbophuhopTHsub = "/WEB-INF/template/signCongbophuhopTHsub.docx";
    private final String tempSignCongbophuhopBBsub = "/WEB-INF/template/signCongbophuhopBBsub.docx";
    //files_details
    private final String DN_Congbohopqui01TH = "/WEB-INF/template/DN_congbohopquiTH.docx";//xuat giay doanh nghiep: bản công bố phù hợp qui định attp 01 thuc pham thuong
    private final String DN_Congbohopqui01BB = "/WEB-INF/template/DN_congbohopquiBB.docx";//xuat giay doanh nghiep: bản công bố phù hợp qui định attp 01 bao bi    
    private final String DN_CongbophuhopCN = "/WEB-INF/template/DN_congbophuhopCN.docx";//xuat giay cong bo phu hop doanh nghiep
    private final String DN_CongbophuhopTH = "/WEB-INF/template/DN_congbophuhopTH.docx";//
    private final String DN_CongbophuhopBB = "/WEB-INF/template/DN_congbophuhopBB.docx";//
    private final String DN_Congbohopqui01THTL = "/WEB-INF/template/DN_congbohopquiTHTL.docx";//xuat giay doanh nghiep: bản công bố phù hợp qui định attp 01 thuc pham thuong
    private final String DN_Congbohopqui01BBTL = "/WEB-INF/template/DN_congbohopquiBBTL.docx";//xuat giay doanh nghiep: bản công bố phù hợp qui định attp 01 bao bi
    private final String DN_CongbophuhopCNTL = "/WEB-INF/template/DN_congbophuhopCNTL.docx";//xuat giay cong bo phu hop doanh nghiep
    private final String DN_CongbophuhopCNsubTL = "/WEB-INF/template/DN_congbophuhopCNsubTL.docx";//xuat giay cong bo cho doanh nghiep
    private final String DN_AnnounementReceiptPaper4Star = "/WEB-INF/template/DN_AnnounementReceiptPaper4Star.docx";
    private final String DN_Congbohopqui01THsub = "/WEB-INF/template/congbohopquiTHsub.docx";//xuat giay doanh nghiep: bản công bố phù hợp qui định attp 01 thuc pham thuong
    private final String DN_Congbohopqui01BBsub = "/WEB-INF/template/congbohopquiBBsub.docx";//xuat giay doanh nghiep: bản công bố phù hợp qui định attp 01 bao bi
    private final String DN_CongbophuhopCNsub = "/WEB-INF/template/congbophuhopCNsub.docx";//xuat giay cong bo cho doanh nghiep
    private final String DN_CongbophuhopTHsub = "/WEB-INF/template/congbophuhopTHsub.docx";//
    private final String DN_CongbophuhopBBsub = "/WEB-INF/template/congbophuhopBBsub.docx";//

    private final String tempCongbohopquiCL = "/WEB-INF/template/recongbohopqui.docx";//
    private final String tempCongbophuhopCL = "/WEB-INF/template/recongbophuhop.docx";//    
    private final String tempSignsuadoisaucongbo = "/WEB-INF/template/tempSignSuaDoiSauCongBo.docx";
//    private final String tempSignsuadoisaucongboPreview = "/WEB-INF/template/tempSignSuaDoiSauCongBoPreview.docx";
    private final String tempSuadoisaucongbo = "/WEB-INF/template/tempSuaDoiSauCongBo.docx";

    //print giay cong bo
    private final String ReceiptPaperConfirmFucnImpTemp = "/WEB-INF/template/ConfirmFucnImpReceiptPaper.docx";//XÁC NHẬN CÔNG BỐ PHÙ HỢP QUY ĐỊNH AN TOÀN THỰC PHẨM
    private final String ReceiptPaperAnnounementTemp = "/WEB-INF/template/AnnounementReceiptPaper.docx";//GIẤY TIẾP NHẬN BẢN CÔNG BỐ HỢP QUY
    private final String ReceiptPaperAnnounementTemp4Star = "/WEB-INF/template/AnnounementReceiptPaper4Star.docx";//GIẤY TIẾP NHẬN BẢN CÔNG BỐ HỢP QUY
    //giay cong bo none files_details
    private final String ReceiptPaperConfirmFucnImpSign = "/WEB-INF/template/ConfirmFucnImpReceiptPaper_Sign.docx";//XÁC NHẬN CÔNG BỐ PHÙ HỢP QUY ĐỊNH AN TOÀN THỰC PHẨM
    private final String ReceiptPaperConfirmFucnImpSignTL = "/WEB-INF/template/ConfirmFucnImpReceiptPaper_Sign_TL.docx";//XÁC NHẬN CÔNG BỐ PHÙ HỢP QUY ĐỊNH AN TOÀN THỰC PHẨM
    private final String ReceiptPaperAnnounementSign = "/WEB-INF/template/AnnounementReceiptPaper_Sign.docx";//GIẤY TIẾP NHẬN BẢN CÔNG BỐ HỢP QUY
    private final String ReceiptPaperAnnounementSignTL = "/WEB-INF/template/AnnounementReceiptPaper_Sign_TL.docx";//GIẤY TIẾP NHẬN BẢN CÔNG BỐ HỢP QUY
    private final String ReceiptPaperAnnounementSign4Star = "/WEB-INF/template/AnnounementReceiptPaper_Sign_4Star.docx";//GIẤY TIẾP NHẬN BẢN CÔNG BỐ HỢP QUY 4Star
    private final String reReceiptPaperAnnounementSign = "/WEB-INF/template/signReCongBoHopQui.docx";
    private final String reReceiptPaperConfirmFucnImpSign = "/WEB-INF/template/signReCongBoPhuHop.docx";
    //bieu mau tham dinh, cong van thong bao sdbs
    private final String bieumauthongbaosuadoibosung = "/WEB-INF/template/bieumauthongbaosuadoibosung.docx";//xuat giay cong bo cho doanh nghiep 03
    private final String bieumauthongbaosuadoibosungUDDT = "/WEB-INF/template/bieumauthongbaosuadoibosungUDDT.docx";//xuat giay cong bo cho doanh nghiep 03
    private final String tempPaperWordEvaluation = "/WEB-INF/template/bien_ban_tham_xet_thuc_pham_chuc_nang.docx";//biên bản thẩm xét thực phẩm
    private final String temPaperWordAnnouce4Star = "/WEB-INF/template/signCongbohopqui4S.docx";
    private Long fileId;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ExportFileDAO.class);

    /**
     * xuat in giay cong bo
     *
     * @return
     */
    public String onExportPaper() {
        try {
            WordExportUtils wU = new WordExportUtils();
            WordprocessingMLPackage wmp = null;
            // insert ban cong bo
            if (fileId == null) {
                throw new Exception("Không có hồ sơ");
            }
            FilesDAOHE fdhe = new FilesDAOHE();
            FilesForm filesForm = fdhe.getFilesDetail(fileId);

            ProcedureDAOHE procedurehe = new ProcedureDAOHE();
            Procedure procedure = procedurehe.findById(filesForm.getFileType());
            if (filesForm == null) {
                throw new Exception("Không có hồ sơ");
            }
            String receiptDeptName = "";
            String businessName = "";
            String businessAdd = "";
            String telephone = "";
            String fax = "";
            String email = "";
            String productName = "";
            String productTypeName = "";
            String manufactureName = "";
            String manufactureAdd = "";
            String nationName = "";
            String matchingTarget = "";
            String effectiveDate = "";
            String receiptNo = "";
            String fileCode;
            // tempwordpaper
            switch (procedure.getDescription()) {
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperAnnounementTemp))));
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperAnnounementTemp))));
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperConfirmFucnImpTemp))));
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperConfirmFucnImpTemp))));
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperConfirmFucnImpTemp))));
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperConfirmFucnImpTemp))));
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperAnnounementTemp4Star))));
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05:
                    String contentEdit;
                    String publishDate = "";
                    String announmentNo = "";
                    String proName = "";
                    String busiName = "";
                    String busiAdd = "";
                    String titleEdit;
                    String receiptNoOld = "";
                    receiptNo = "0000";
                    //                    if (filesForm.getAnnouncement().getPublishDate() != null) {
//                        publishDate = DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "dd")
//                                + " tháng " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "MM")
//                                + " năm " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "yyyy");
//                    }
                    if (filesForm.getAnnouncement().getAnnouncementNo() != null) {
                        announmentNo = filesForm.getAnnouncement().getAnnouncementNo();
                    }
                    if (filesForm.getAnnouncement().getProductName() != null) {
                        proName = filesForm.getAnnouncement().getProductName();
                    }
                    if (filesForm.getAnnouncement().getBusinessAddress() != null) {
                        busiAdd = filesForm.getAnnouncement().getBusinessAddress();
                    }
                    if (filesForm.getAnnouncement().getBusinessName() != null) {
                        busiName = filesForm.getAnnouncement().getBusinessName();
                    }
                    if (filesForm.getFilesSourceID() != null
                            && filesForm.getFilesSourceID() > 0L) {
                        Files fboOld = fdhe.findById(filesForm.getFilesSourceID());
                        if (fboOld != null
                                && fboOld.getAnnouncementReceiptPaperId() != null) {
                            AnnouncementReceiptPaperDAOHE arpDaohe = new AnnouncementReceiptPaperDAOHE();
                            AnnouncementReceiptPaper arpbo = arpDaohe.findById(fboOld.getAnnouncementReceiptPaperId());
                            if (arpbo != null
                                    && arpbo.getReceiptDate() != null
                                    && arpbo.getSignDate() != null
                                    && arpbo.getReceiptNo() != null) {
                                receiptNo = arpbo.getReceiptNo();
                                publishDate = " ngày " + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "MM") + " năm "
                                        + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "yyyy");
                            }
                        }
                    }
                    Date dateNow = getSysdate();
                    String signedDate = "Hà Nội, ngày " + DateTimeUtils.convertDateToString(dateNow, "dd")
                            + " tháng " + DateTimeUtils.convertDateToString(dateNow, "MM")
                            + " năm " + DateTimeUtils.convertDateToString(dateNow, "yyyy");
                    if (filesForm.getAnnouncementReceiptPaperForm() != null) {
                        receiptDeptName = filesForm.getAnnouncementReceiptPaperForm().getReceiptDeptName();
                    } else if (filesForm.getConfirmImportSatistPaperForm() != null) {
                        receiptDeptName = filesForm.getConfirmImportSatistPaperForm().getTestAgencyName();
                    }

                    if (filesForm.getTitleEditATTP() != null) {
                        titleEdit = filesForm.getTitleEditATTP();
                    } else if (filesForm.getTitleEdit() != null) {
                        titleEdit = filesForm.getTitleEdit();
                    } else {
                        titleEdit = "sửa đổi bổ sung hồ sơ đã công bố";
                    }
                    if (filesForm.getContentsEditATTP() != null
                            && !filesForm.getContentsEditATTP().isEmpty()) {
                        contentEdit = filesForm.getContentsEditATTP();
                    } else {
                        contentEdit = filesForm.getContentsEdit();
                    }

                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignsuadoisaucongbo))));
                    //Các biến truyền vào Công văn
//                    if (receiptDeptName == null || receiptDeptName.equals("Cục ATTP")) {
//                        wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
//                        wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
//                        wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");
//                    } else {
//                        wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${deptParent}");
//                        wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptName}");
//                        wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptNames}");
//                    }

                    wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
                    wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
                    wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");

                    wU.replacePlaceholder(wmp, contentEdit, "${contentEdit}");
                    wU.replacePlaceholder(wmp, publishDate, "${publishDate}");
                    wU.replacePlaceholder(wmp, announmentNo, "${announmentNo}");
                    wU.replacePlaceholder(wmp, proName, "${proName}");
                    wU.replacePlaceholder(wmp, busiAdd, "${busiAdd}");
                    wU.replacePlaceholder(wmp, busiName, "${busiName}");
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    wU.replacePlaceholder(wmp, titleEdit, "${titleEdit}");
                    wU.replacePlaceholder(wmp, receiptNoOld, "${receiptNoOld}");
                    ProcessDAOHE pDaohe = new ProcessDAOHE();
                    Process pBo = pDaohe.getProcessByAction(filesForm.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, filesForm.getStatus(), Constants.FILE_STATUS.NEW_CREATE);
                    DepartmentDAOHE deptDaohe = new DepartmentDAOHE();
                    Department deptBo = deptDaohe.findBOById(pBo.getSendGroupId());
                    if (deptBo != null && deptBo.getDeptCode() != null) {
                        wU.replacePlaceholder(wmp, deptBo.getDeptCode(), "${deptCode}");
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(reReceiptPaperAnnounementSign))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(reReceiptPaperAnnounementSign))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(reReceiptPaperAnnounementSign))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(reReceiptPaperAnnounementSign))));
                    break;
                case Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(reReceiptPaperAnnounementSign))));
                    break;
            }

            if (filesForm.getAnnouncementReceiptPaperForm() != null) {
                receiptDeptName = filesForm.getAnnouncementReceiptPaperForm().getReceiptDeptName();
                receiptNo = filesForm.getAnnouncementReceiptPaperForm().getReceiptNo();
                if (receiptNo != null) {
                    String[] arr = receiptNo.split("/");
                    if (arr.length == 3) {
                        receiptNo = arr[0] + "         " + arr[1];
                    }
                }
                businessName = filesForm.getAnnouncementReceiptPaperForm().getBusinessName();
                if (filesForm.getAnnouncement() != null) {
                    businessAdd = filesForm.getAnnouncement().getBusinessAddress();
                } else {
                    businessAdd = filesForm.getBusinessAddress();
                }
                telephone = filesForm.getAnnouncementReceiptPaperForm().getTelephone();
                fax = filesForm.getAnnouncementReceiptPaperForm().getFax();
                email = filesForm.getAnnouncementReceiptPaperForm().getEmail();
                productName = filesForm.getAnnouncementReceiptPaperForm().getProductName();
                manufactureName = filesForm.getAnnouncementReceiptPaperForm().getManufactureName();
                if (filesForm.getAnnouncement() != null) {
                    manufactureAdd = filesForm.getAnnouncement().getManufactureAddress();
                } else {
                    manufactureAdd = filesForm.getManufactureAddress();
                }
                nationName = filesForm.getAnnouncementReceiptPaperForm().getNationName();
                matchingTarget = filesForm.getAnnouncementReceiptPaperForm().getMatchingTarget();
                if (filesForm.getEffectiveDate() != null) {
                    if (filesForm.getEffectiveDate() > 0L) {
                        effectiveDate = filesForm.getEffectiveDate().toString();
                    } else {
                        effectiveDate = "3";
                    }
                } else {
                    effectiveDate = "3";
                }
            } else if (filesForm.getConfirmImportSatistPaperForm() != null) {
                receiptDeptName = filesForm.getConfirmImportSatistPaperForm().getTestAgencyName();
                businessName = filesForm.getConfirmImportSatistPaperForm().getImportBusinessName();
                businessAdd = filesForm.getBusinessAddress();
                telephone = filesForm.getConfirmImportSatistPaperForm().getImportBusinessTel();
                fax = filesForm.getConfirmImportSatistPaperForm().getImportBusinessFax();
                email = filesForm.getConfirmImportSatistPaperForm().getImportBusinessEmail();
                productName = filesForm.getConfirmImportSatistPaperForm().getProductName();
                manufactureName = filesForm.getConfirmImportSatistPaperForm().getExportBusinessName();
                matchingTarget = filesForm.getMatchingTarget();
            }
            if (filesForm.getDetailProduct() != null) {
                productTypeName = filesForm.getDetailProduct().getProductTypeName();
                if (filesForm.getDetailProduct().getUseage() == null || filesForm.getDetailProduct().getUseage().trim().length() == 0) {
                    filesForm.getDetailProduct().setUseage("Không có.");
                }
                if (filesForm.getDetailProduct().getObjectUse() == null || filesForm.getDetailProduct().getObjectUse().trim().length() == 0) {
                    filesForm.getDetailProduct().setObjectUse("Không có.");
                }
                if (filesForm.getDetailProduct().getGuideline() == null || filesForm.getDetailProduct().getGuideline().trim().length() == 0) {
                    filesForm.getDetailProduct().setGuideline("Không có.");
                }
                if (filesForm.getDetailProduct().getDateOfManufacture() != null) {
                    filesForm.getDetailProduct().setDateOfManufactureStr(DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getDateOfManufacture(), "dd/MM/yyyy"));
                }
            }
            if ("Cục ATTP".equals(receiptDeptName)) {
                wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
                wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
                wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");
            } else {
                wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${deptParent}");
                wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptName}");
                wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptNames}");
            }
            wU.replacePlaceholder(wmp, receiptNo, "${receiptNo}");
            // Get signed date
            AnnouncementReceiptPaperDAOHE arpDhe = new AnnouncementReceiptPaperDAOHE();
            AnnouncementReceiptPaper arp = arpDhe.findById(filesForm.getAnnouncementReceiptPaperId());
            String signedDate = DateTimeUtils.convertDateToString(arp.getSignDate(), "dd") + "                " + DateTimeUtils.convertDateToString(arp.getSignDate(), "MM") + "                 " + DateTimeUtils.convertDateToString(arp.getSignDate(), "yy");
            wU.replacePlaceholder(wmp, signedDate, "${signDate}");
            wU.replacePlaceholder(wmp, businessName, "${businessName}");
            wU.replacePlaceholder(wmp, businessAdd, "${businessAdd}");
            wU.replacePlaceholder(wmp, telephone, "${telephone}");
            wU.replacePlaceholder(wmp, fax, "${fax}");
            wU.replacePlaceholder(wmp, email, "${email}");
            wU.replacePlaceholder(wmp, productName, "${productName}");
            wU.replacePlaceholder(wmp, productTypeName, "${productTypeName}");
            wU.replacePlaceholder(wmp, manufactureName, "${manufactureName}");
            wU.replacePlaceholder(wmp, manufactureAdd, "${manufactureAdd}");
            wU.replacePlaceholder(wmp, nationName, "${nationName}");
            wU.replacePlaceholder(wmp, matchingTarget, "${matchingTarget}");

            if (effectiveDate != null || effectiveDate.length() > 0) {
                wU.replacePlaceholder(wmp, effectiveDate, "${effectiveDate}");
            }

            String signer = "";
            String rolesigner;

            PositionDAOHE posdaohe = new PositionDAOHE();
            Position posbo = posdaohe.findPositionCode(getUserId());
            if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_CT)) {
                rolesigner = "CỤC TRƯỞNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_PCT)) {
                signer = "KT. CỤC TRƯỞNG";
                rolesigner = "PHÓ CỤC TRƯỞNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_OF_STAFF_T)) {
                signer = "TL. CỤC TRƯỞNG";
                rolesigner = "TRƯỞNG PHÒNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.GDTT)) {
                signer = "TL. CỤC TRƯỞNG";
                rolesigner = "GIÁM ĐỐC TRUNG TÂM";
            } else {
                rolesigner = "UNKNOW";
            }

            wU.replacePlaceholder(wmp, signer, "${signer}");
            wU.replacePlaceholder(wmp, rolesigner, "${roleSigner}");
            String leaderSinged = "";
            if (arp.getSignerName() != null) {
                leaderSinged = arp.getSignerName();
            }
            fileCode = filesForm.getFileCode();
            wU.replacePlaceholder(wmp, leaderSinged, "${LeaderSigned}");
            wU.replacePlaceholder(wmp, fileCode, "${fileCode}");

            HashMap map = new HashMap();
            map.put("createForm", filesForm);
            wU.replacePlaceholder(wmp, map);
            // Check type
            String typeExport = getRequest().getParameter("typeExport");
            if ("docx".equals(typeExport)) {
                wU.createFooterPart(wmp, "MA HO SO: " + fileCode);
                wU.writeDocxToStream(wmp, getResponse());
            } else {
                wU.writePDFToStream(wmp, getResponse(), fileId, fileCode, filesForm.getQrCode(), false);
            }
            EventLogDAOHE edhe = new EventLogDAOHE();
            edhe.insertEventLog("Xuất giấy văn thư", "hồ sơ có id =" + filesForm.getFileId(), getRequest());
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    /**
     * sign giay cong bo only
     *
     * @return
     */
    public WordprocessingMLPackage onSignOnlyPaper(WordExportUtils wU, FilesDAOHE fdhe, ProcedureDAOHE procedurehe, AnnouncementReceiptPaperDAOHE arpDhe, HashMap map) {
        try {
            // insert ban cong bo
            if (fileId == null) {
                throw new Exception("Không có hồ sơ");
            }
            FilesForm filesForm = fdhe.getFilesDetail(fileId);
            Procedure procedure = procedurehe.findById(filesForm.getFileType());
            if (filesForm == null) {
                throw new Exception("Không có hồ sơ");
            }
            String receiptDeptName = "";
            String businessName = "";
            String businessAdd = "";
            String telephone = "";
            String fax = "";
            String email = "";
            String productName = "";
            String productTypeName = "";
            String manufactureName = "";
            String manufactureAdd = "";
            String nationName = "";
            String matchingTarget = "";
            String effectiveDate = "";
            String receiptNo = "";
            WordprocessingMLPackage wmpOnlyPaper = new WordprocessingMLPackage();
            switch (procedure.getDescription()) {
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperAnnounementSign))));
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperAnnounementSign))));
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperConfirmFucnImpSign))));
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperConfirmFucnImpSign))));
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperConfirmFucnImpSign))));
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperConfirmFucnImpSign))));
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperAnnounementSign4Star))));
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05:
//                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(suadoisaucongbo))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(reReceiptPaperAnnounementSign))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(reReceiptPaperConfirmFucnImpSign))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(reReceiptPaperConfirmFucnImpSign))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(reReceiptPaperConfirmFucnImpSign))));
                    break;
                case Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(reReceiptPaperConfirmFucnImpSign))));
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01_TL:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperAnnounementSignTL))));
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03_TL:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperAnnounementSignTL))));
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP_TL:
                    wmpOnlyPaper = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(ReceiptPaperConfirmFucnImpSignTL))));
                    break;
            }
            if (filesForm.getAnnouncementReceiptPaperForm() != null) {
                receiptDeptName = filesForm.getAnnouncementReceiptPaperForm().getReceiptDeptName();
                receiptNo = filesForm.getAnnouncementReceiptPaperForm().getReceiptNo();
                businessName = filesForm.getAnnouncementReceiptPaperForm().getBusinessName();
                if (filesForm.getAnnouncement() != null) {
                    businessAdd = filesForm.getAnnouncement().getBusinessAddress();
                } else {
                    businessAdd = filesForm.getBusinessAddress();
                }
                telephone = filesForm.getAnnouncementReceiptPaperForm().getTelephone();
                fax = filesForm.getAnnouncementReceiptPaperForm().getFax();
                email = filesForm.getAnnouncementReceiptPaperForm().getEmail();
                productName = filesForm.getAnnouncementReceiptPaperForm().getProductName();
                manufactureName = filesForm.getAnnouncementReceiptPaperForm().getManufactureName();
                if (filesForm.getAnnouncement() != null) {
                    manufactureAdd = filesForm.getAnnouncement().getManufactureAddress();
                } else {
                    manufactureAdd = filesForm.getManufactureAddress();
                }
                nationName = filesForm.getAnnouncementReceiptPaperForm().getNationName();
                matchingTarget = filesForm.getAnnouncementReceiptPaperForm().getMatchingTarget();
                if (filesForm.getEffectiveDate() != null) {
                    if (filesForm.getEffectiveDate() > 0L) {
                        effectiveDate = filesForm.getEffectiveDate().toString();
                    } else {
                        effectiveDate = "3";
                    }
                } else {
                    effectiveDate = "3";
                }
            } else if (filesForm.getConfirmImportSatistPaperForm() != null) {
//                wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempPaperWordStatisImport))));
                receiptDeptName = filesForm.getConfirmImportSatistPaperForm().getTestAgencyName();
                businessName = filesForm.getConfirmImportSatistPaperForm().getImportBusinessName();
                businessAdd = filesForm.getBusinessAddress();
                telephone = filesForm.getConfirmImportSatistPaperForm().getImportBusinessTel();
                fax = filesForm.getConfirmImportSatistPaperForm().getImportBusinessFax();
                email = filesForm.getConfirmImportSatistPaperForm().getImportBusinessEmail();
                productName = filesForm.getConfirmImportSatistPaperForm().getProductName();
                manufactureName = filesForm.getConfirmImportSatistPaperForm().getExportBusinessName();
                matchingTarget = filesForm.getMatchingTarget();
            }
            if (filesForm.getDetailProduct() != null) {
                productTypeName = filesForm.getDetailProduct().getProductTypeName();
                if (filesForm.getDetailProduct().getUseage() == null || filesForm.getDetailProduct().getUseage().trim().length() == 0) {
                    filesForm.getDetailProduct().setUseage("Không có.");
                }
                if (filesForm.getDetailProduct().getObjectUse() == null || filesForm.getDetailProduct().getObjectUse().trim().length() == 0) {
                    filesForm.getDetailProduct().setObjectUse("Không có.");
                }
                if (filesForm.getDetailProduct().getGuideline() == null || filesForm.getDetailProduct().getGuideline().trim().length() == 0) {
                    filesForm.getDetailProduct().setGuideline("Không có.");
                }
                if (filesForm.getDetailProduct().getDateOfManufacture() != null) {
                    filesForm.getDetailProduct().setDateOfManufactureStr(DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getDateOfManufacture(), "dd/MM/yyyy"));
                }
            }
            if ("Cục ATTP".equals(receiptDeptName)) {
                wU.replacePlaceholder(wmpOnlyPaper, "BỘ Y TẾ", "${deptParent}");
                wU.replacePlaceholder(wmpOnlyPaper, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
                wU.replacePlaceholder(wmpOnlyPaper, "Cục An toàn thực phẩm", "${receiptDeptNames}");
            } else {
                wU.replacePlaceholder(wmpOnlyPaper, "CỤC AN TOÀN THỰC PHẨM", "${deptParent}");
                wU.replacePlaceholder(wmpOnlyPaper, receiptDeptName, "${receiptDeptName}");
                wU.replacePlaceholder(wmpOnlyPaper, receiptDeptName, "${receiptDeptNames}");
            }
            wU.replacePlaceholder(wmpOnlyPaper, receiptNo, "${receiptNo}");
            // Get signed date
            AnnouncementReceiptPaper arp = arpDhe.findById(filesForm.getAnnouncementReceiptPaperId());
            Date dateNow = getSysdate();
            String signedDate = "Hà Nội, ngày " + DateTimeUtils.convertDateToString(dateNow, "dd") + " tháng " + DateTimeUtils.convertDateToString(dateNow, "MM") + " năm " + DateTimeUtils.convertDateToString(dateNow, "yyyy");
            wU.replacePlaceholder(wmpOnlyPaper, signedDate, "${signDate}");
            wU.replacePlaceholder(wmpOnlyPaper, businessName, "${businessName}");
            wU.replacePlaceholder(wmpOnlyPaper, businessAdd, "${businessAdd}");
            wU.replacePlaceholder(wmpOnlyPaper, telephone, "${telephone}");
            wU.replacePlaceholder(wmpOnlyPaper, fax, "${fax}");
            wU.replacePlaceholder(wmpOnlyPaper, email, "${email}");
            wU.replacePlaceholder(wmpOnlyPaper, productName, "${productName}");
            wU.replacePlaceholder(wmpOnlyPaper, productTypeName, "${productTypeName}");
            wU.replacePlaceholder(wmpOnlyPaper, manufactureName, "${manufactureName}");
            wU.replacePlaceholder(wmpOnlyPaper, manufactureAdd, "${manufactureAdd}");
            wU.replacePlaceholder(wmpOnlyPaper, nationName, "${nationName}");
            wU.replacePlaceholder(wmpOnlyPaper, matchingTarget, "${matchingTarget}");

            if (effectiveDate != null || effectiveDate.length() > 0) {
                wU.replacePlaceholder(wmpOnlyPaper, effectiveDate, "${effectiveDate}");
            }

            String signer = "";
            String rolesigner;

            PositionDAOHE posdaohe = new PositionDAOHE();
            Position posbo = posdaohe.findPositionCode(getUserId());
            if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_CT)) {
                rolesigner = "CỤC TRƯỞNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_PCT)) {
                signer = "KT. CỤC TRƯỞNG";
                rolesigner = "PHÓ CỤC TRƯỞNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_OF_STAFF_T)) {
                signer = "TL. CỤC TRƯỞNG";
                rolesigner = "TRƯỞNG PHÒNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.GDTT)) {
                signer = "TL. CỤC TRƯỞNG";
                rolesigner = "GIÁM ĐỐC TRUNG TÂM";
            } else {
                rolesigner = "UNKNOW";
            }

            wU.replacePlaceholder(wmpOnlyPaper, signer, "${signer}");
            wU.replacePlaceholder(wmpOnlyPaper, rolesigner, "${roleSigner}");
            String leaderSinged = "";
            if (arp.getSignerName() != null) {
                leaderSinged = arp.getSignerName();
            }
            wU.replacePlaceholder(wmpOnlyPaper, leaderSinged, "${LeaderSigned}");
            //map.put("createForm", filesForm);
            wU.replacePlaceholder(wmpOnlyPaper, map);
            return wmpOnlyPaper;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    /**
     * xuat giay cong bo + detail_files
     *
     * @return
     */
    public String onExportPaperSign() {
        List resultMessage = new ArrayList();
        try {
            WordExportUtils wU = new WordExportUtils();
            AnnouncementReceiptPaperDAOHE ann = new AnnouncementReceiptPaperDAOHE();
            // insert ban cong bo
            if (fileId == null) {
                resultMessage.add("3");
                resultMessage.add("Lỗi trong quá trình Convert và lưu file PDF: Không có hồ sơ");
                jsonDataGrid.setItems(resultMessage);
                return GRID_DATA;
            }
            Date dateNow = getSysdate();
            FilesDAOHE fdhe = new FilesDAOHE();
            FilesForm filesForm = fdhe.getFilesDetail(fileId);

            ProcedureDAOHE procedurehe = new ProcedureDAOHE();
            Procedure procedure = procedurehe.findById(filesForm.getFileType());
            if (filesForm == null) {
                resultMessage.add("3");
                resultMessage.add("Lỗi trong quá trình Convert và lưu file PDF: Không có hồ sơ");
                jsonDataGrid.setItems(resultMessage);
                return GRID_DATA;
            }
            String receiptDeptName = "";
            String businessName = "";
            String businessAdd = "";
            String telephone = "";
            String fax = "";
            String email = "";
            String productName = "";
            String productTypeName = "";
            String manufactureName = "";
            String manufactureAdd = "";
            String nationName = "";
            String matchingTarget = "";
            String effectiveDate = "";
            String receiptNo = "";

            WordprocessingMLPackage wmp = null;

            String fileCode = filesForm.getFileCode();
            Long productType = 0L;

            if (filesForm.getDetailProduct() != null && filesForm.getDetailProduct().getProductType() != null) {
                productType = filesForm.getDetailProduct().getProductType();
            }
            CategoryDAOHE catedaohe = new CategoryDAOHE();
            Category catebo = catedaohe.findById(productType);

            switch (procedure.getDescription()) {
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiTH))));
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiBB))));
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiTHsub))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiBBsub))));
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiTH))));
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiBB))));
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiTHsub))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiBBsub))));
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopCN))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopCNsub))));
                    }

                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopCN))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopCNsub))));
                    }

                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopTH))));
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopBB))));
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopTHsub))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopBBsub))));
                    }

                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopTH))));
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopBB))));
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopTHsub))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopBBsub))));
                    }

                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(announcement4star))));
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(suadoisaucongbo))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReAnnounFiles))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    break;
                case Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    break;
            }
            if (filesForm.getAnnouncementReceiptPaperForm() != null) {
                receiptDeptName = filesForm.getAnnouncementReceiptPaperForm().getReceiptDeptName();
                receiptNo = filesForm.getAnnouncementReceiptPaperForm().getReceiptNo();
                businessName = filesForm.getAnnouncementReceiptPaperForm().getBusinessName();
                if (filesForm.getAnnouncement() != null) {
                    businessAdd = filesForm.getAnnouncement().getBusinessAddress();
                } else {
                    businessAdd = filesForm.getBusinessAddress();
                }
                telephone = filesForm.getAnnouncementReceiptPaperForm().getTelephone();
                fax = filesForm.getAnnouncementReceiptPaperForm().getFax();
                email = filesForm.getAnnouncementReceiptPaperForm().getEmail();
                productName = filesForm.getAnnouncementReceiptPaperForm().getProductName();
                manufactureName = filesForm.getAnnouncementReceiptPaperForm().getManufactureName();
                if (filesForm.getAnnouncement() != null) {
                    manufactureAdd = filesForm.getAnnouncement().getManufactureAddress();
                } else {
                    manufactureAdd = filesForm.getManufactureAddress();
                }
                nationName = filesForm.getAnnouncementReceiptPaperForm().getNationName();
                matchingTarget = filesForm.getAnnouncementReceiptPaperForm().getMatchingTarget();
                if (filesForm.getEffectiveDate() != null) {
                    if (filesForm.getEffectiveDate() > 0L) {
                        effectiveDate = filesForm.getEffectiveDate().toString();
                    } else {
                        effectiveDate = "3";
                    }
                } else {
                    effectiveDate = "3";
                }
            } else if (filesForm.getConfirmImportSatistPaperForm() != null) {
//                wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempPaperWordStatisImport))));
                receiptDeptName = filesForm.getConfirmImportSatistPaperForm().getTestAgencyName();
                businessName = filesForm.getConfirmImportSatistPaperForm().getImportBusinessName();
                businessAdd = filesForm.getBusinessAddress();
                telephone = filesForm.getConfirmImportSatistPaperForm().getImportBusinessTel();
                fax = filesForm.getConfirmImportSatistPaperForm().getImportBusinessFax();
                email = filesForm.getConfirmImportSatistPaperForm().getImportBusinessEmail();
                productName = filesForm.getConfirmImportSatistPaperForm().getProductName();
                manufactureName = filesForm.getConfirmImportSatistPaperForm().getExportBusinessName();
                matchingTarget = filesForm.getMatchingTarget();
            }
            if (filesForm.getDetailProduct() != null) {
                productTypeName = filesForm.getDetailProduct().getProductTypeName();
                if (filesForm.getDetailProduct().getUseage() == null || filesForm.getDetailProduct().getUseage().trim().length() == 0) {
                    filesForm.getDetailProduct().setUseage("Không có.");
                }
                if (filesForm.getDetailProduct().getObjectUse() == null || filesForm.getDetailProduct().getObjectUse().trim().length() == 0) {
                    filesForm.getDetailProduct().setObjectUse("Không có.");
                }
                if (filesForm.getDetailProduct().getGuideline() == null || filesForm.getDetailProduct().getGuideline().trim().length() == 0) {
                    filesForm.getDetailProduct().setGuideline("Không có.");
                }
                if (filesForm.getDetailProduct().getDateOfManufacture() != null) {
                    filesForm.getDetailProduct().setDateOfManufactureStr(DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getDateOfManufacture(), "dd/MM/yyyy"));
                }
            }
            if ("Cục ATTP".equals(receiptDeptName)) {
                wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
                wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
                wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");
            } else {
                wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${deptParent}");
                wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptName}");
                wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptNames}");
            }
            wU.replacePlaceholder(wmp, receiptNo, "${receiptNo}");
            wU.replacePlaceholder(wmp, businessName, "${businessName}");
            wU.replacePlaceholder(wmp, businessAdd, "${businessAdd}");
            wU.replacePlaceholder(wmp, telephone, "${telephone}");
            wU.replacePlaceholder(wmp, fax, "${fax}");
            wU.replacePlaceholder(wmp, email, "${email}");
            wU.replacePlaceholder(wmp, productName, "${productName}");
            wU.replacePlaceholder(wmp, productTypeName, "${productTypeName}");
            wU.replacePlaceholder(wmp, manufactureName, "${manufactureName}");
            wU.replacePlaceholder(wmp, manufactureAdd, "${manufactureAdd}");
            wU.replacePlaceholder(wmp, nationName, "${nationName}");
            wU.replacePlaceholder(wmp, matchingTarget, "${matchingTarget}");

            if (effectiveDate != null || effectiveDate.length() > 0) {
                wU.replacePlaceholder(wmp, effectiveDate, "${effectiveDate}");
            }
            String signer = "";
            String rolesigner;

            PositionDAOHE posdaohe = new PositionDAOHE();
            Position posbo = posdaohe.findPositionCode(getUserId());
            if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_CT)) {
                rolesigner = "CỤC TRƯỞNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_PCT)) {
                signer = "KT. CỤC TRƯỞNG";
                rolesigner = "PHÓ CỤC TRƯỞNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_OF_STAFF_T)) {
                signer = "TL. CỤC TRƯỞNG";
                rolesigner = "TRƯỞNG PHÒNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.GDTT)) {
                signer = "TL. CỤC TRƯỞNG";
                rolesigner = "GIÁM ĐỐC TRUNG TÂM";
            } else {
                rolesigner = "UNKNOW";
            }

            wU.replacePlaceholder(wmp, signer, "${signer}");
            wU.replacePlaceholder(wmp, rolesigner, "${roleSigner}");
            String leaderSinged = getUserName();
            wU.replacePlaceholder(wmp, leaderSinged, "${LeaderSigned}");
            String signedDate = "Hà Nội, ngày " + DateTimeUtils.convertDateToString(dateNow, "dd") + " tháng " + DateTimeUtils.convertDateToString(dateNow, "MM") + " năm " + DateTimeUtils.convertDateToString(dateNow, "yyyy");

            // them vao thong tin ho so
            List lstMainlyTarget;
            List lstBiologisTarget;
            List lstHeavyMetalTarget;
            List lstChemicalTarget;
            switch (procedure.getDescription()) {
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01:
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                        filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                    }
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                        wU.replaceTable(wmp, 6, lstBiologisTarget);
                        wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 8, lstChemicalTarget);
                    } else {
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03:
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                        wU.replaceTable(wmp, 6, lstBiologisTarget);
                        wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 8, lstChemicalTarget);
                    } else {
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP:
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                    lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                    lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                    lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                    wU.replaceTable(wmp, 5, lstMainlyTarget);//u150122 binhnt53
                    wU.replaceTable(wmp, 6, lstBiologisTarget);
                    wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                    wU.replaceTable(wmp, 8, lstChemicalTarget);//u150122 binhnt53
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN:
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {

                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                    lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                    lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                    lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                    wU.replaceTable(wmp, 5, lstMainlyTarget);//u150122 binhnt53
                    wU.replaceTable(wmp, 6, lstBiologisTarget);
                    wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                    wU.replaceTable(wmp, 8, lstChemicalTarget);//u150122 binhnt53
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN:
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);//u150122 binhnt53
                        wU.replaceTable(wmp, 6, lstBiologisTarget);
                        wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 8, lstChemicalTarget);//u150122 binhnt53
                    } else {
                        wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);//u150122 binhnt53
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP:
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);//u150122 binhnt53
                        wU.replaceTable(wmp, 6, lstBiologisTarget);
                        wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 8, lstChemicalTarget);//u150122 binhnt53
                    } else {
                        wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);//u150122 binhnt53
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR:
                    List lstProductOfFile = fdhe.getProductOfFile(fileId);
                    wU.replaceTable(wmp, 2, lstProductOfFile);
                    break;

                case Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT:
                    if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP:
                    if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN:
                    if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN:
                    if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP:
                    if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                default:
                    ;
            }
            HashMap map = new HashMap();
            map.put("createForm", filesForm);
            wU.replacePlaceholder(wmp, map);
            //wU.createFooterPart(wmp, "MA HO SO: " + fileCode);
            Boolean fullFile = wU.writePDFToStreamSign(wmp, getResponse(), fileId, fileCode, filesForm.getQrCode(), "PDHS", true, 1, true);
            wmp = onSignOnlyPaper(wU, fdhe, procedurehe, ann, map);
            Boolean paperOnly = wU.writePDFToStreamSign(wmp, getResponse(), fileId, fileCode, filesForm.getQrCode(), "PDHS", false, 2, false);
            if (fullFile && paperOnly) {
                resultMessage.add("1");
                resultMessage.add("Xuất hồ sơ thành công");
                jsonDataGrid.setItems(resultMessage);
                return GRID_DATA;
            } else {
                resultMessage.add("3");
                resultMessage.add("Lỗi trong quá trình Convert và lưu file PDF");
                jsonDataGrid.setItems(resultMessage);
                return GRID_DATA;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Lỗi trong quá trình xử lý File PDF");
//            Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình xử lý File PDF: " + ex.getMessage());
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     * hieptq update 101214 download file ky pdf
     *
     * @return
     */
    public String onExportPaperSignDownload() {
        List resultMessage = new ArrayList();
        try {
            WordExportUtils wU = new WordExportUtils();
            // insert ban cong bo
            if (fileId == null) {
                throw new Exception("Không có hồ sơ");
            }
            Date dateNow = getSysdate();
            FilesDAOHE fdhe = new FilesDAOHE();
            FilesForm filesForm = fdhe.getFilesDetail(fileId);

            ProcedureDAOHE procedurehe = new ProcedureDAOHE();
            Procedure procedure = procedurehe.findById(filesForm.getFileType());
            if (filesForm == null) {
                throw new Exception("Không có hồ sơ");
            }
            String receiptDeptName = "";
            String businessName = "";
            String businessAdd = "";
            String telephone = "";
            String fax = "";
            String email = "";
            String productName = "";
            String productTypeName = "";
            String manufactureName = "";
            String manufactureAdd = "";
            String nationName = "";
            String matchingTarget = "";
            String effectiveDate = "";
            String receiptNo = "";
            WordprocessingMLPackage wmp = null;
            String fileCode = filesForm.getFileCode();
            Long productType = 0L;
            if (filesForm.getDetailProduct() != null && filesForm.getDetailProduct().getProductType() != null) {
                productType = filesForm.getDetailProduct().getProductType();
            }
            CategoryDAOHE catedaohe = new CategoryDAOHE();
            Category catebo = catedaohe.findById(productType);
            switch (procedure.getDescription()) {
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01:
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiTH))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiBB))));
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03:
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiTH))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiBB))));
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopCN))));
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopCN))));
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN:
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopTH))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopBB))));
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP:
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopTH))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopBB))));
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR:
                    String pubDate = "";
                    String annouNo = "";
                    String effDate;
                    if (filesForm.getAnnouncement().getPublishDate() != null) {
                        pubDate = "ngày " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "dd")
                                + " tháng " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "MM")
                                + " năm " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "yyyy");
                    }
                    if (filesForm.getEffectiveDate() != null) {
                        if (filesForm.getEffectiveDate() > 0L) {
                            effDate = filesForm.getEffectiveDate().toString();
                        } else {
                            effDate = "3";
                        }
                    } else {
                        effDate = "3";
                    }
                    if (filesForm.getAnnouncement() != null && filesForm.getAnnouncement().getAnnouncementNo() != null) {
                        annouNo = filesForm.getAnnouncement().getAnnouncementNo();
                    }
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(temPaperWordAnnouce4Star))));
                    wU.replacePlaceholder(wmp, pubDate, "${pubDate}");
                    wU.replacePlaceholder(wmp, annouNo, "${annouNo}");
                    wU.replacePlaceholder(wmp, effDate, "${effDate}");
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05:
                    String contentEdit;
                    String publishDate = "";
                    String announmentNo = "";
                    String proName = "";
                    String busiName = "";
                    String busiAdd = "";
                    String titleEdit;
                    String receiptNoOld = "";
                    if (filesForm.getContentsEditATTP() != null && !filesForm.getContentsEditATTP().isEmpty()) {
                        contentEdit = filesForm.getContentsEditATTP();
                    } else {
                        contentEdit = filesForm.getContentsEdit();
                    }
                    if (filesForm.getAnnouncement().getPublishDate() != null) {
                        publishDate = DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "dd")
                                + " tháng " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "MM")
                                + " năm " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "yyyy");
                    }
                    if (filesForm.getAnnouncement().getAnnouncementNo() != null) {
                        announmentNo = filesForm.getAnnouncement().getAnnouncementNo();
                    }
                    if (filesForm.getAnnouncement().getProductName() != null) {
                        proName = filesForm.getAnnouncement().getProductName();
                    }
                    if (filesForm.getAnnouncement().getBusinessAddress() != null) {
                        busiAdd = filesForm.getAnnouncement().getBusinessAddress();
                    }
                    if (filesForm.getAnnouncement().getBusinessName() != null) {
                        busiName = filesForm.getAnnouncement().getBusinessName();
                    }
                    dateNow = getSysdate();
                    String signedDate = "Hà Nội, ngày " + DateTimeUtils.convertDateToString(dateNow, "dd")
                            + " tháng " + DateTimeUtils.convertDateToString(dateNow, "MM")
                            + " năm " + DateTimeUtils.convertDateToString(dateNow, "yyyy");
                    receiptDeptName = "";
                    if (filesForm.getAnnouncementReceiptPaperForm() != null) {
                        receiptDeptName = filesForm.getAnnouncementReceiptPaperForm().getReceiptDeptName();
                    } else if (filesForm.getConfirmImportSatistPaperForm() != null) {
                        receiptDeptName = filesForm.getConfirmImportSatistPaperForm().getTestAgencyName();
                    }
                    if (filesForm.getFilesSourceID() != null
                            && filesForm.getFilesSourceID() > 0L) {
                        Files fboOld = fdhe.findById(filesForm.getFilesSourceID());
                        if (fboOld != null
                                && fboOld.getAnnouncementReceiptPaperId() != null) {
                            AnnouncementReceiptPaperDAOHE arpDaohe = new AnnouncementReceiptPaperDAOHE();
                            AnnouncementReceiptPaper arpbo = arpDaohe.findById(fboOld.getAnnouncementReceiptPaperId());
                            if (arpbo != null
                                    && arpbo.getReceiptDate() != null
                                    && arpbo.getSignDate() != null
                                    && arpbo.getReceiptNo() != null) {
                                receiptNo = arpbo.getReceiptNo();
                                publishDate = " ngày " + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "yyyy");
                            }
                        }
                    }
                    if (filesForm.getTitleEdit() != null) {
                        titleEdit = filesForm.getTitleEdit();
                    } else if (filesForm.getTitleEditATTP() != null) {
                        titleEdit = filesForm.getTitleEditATTP();
                    } else {
                        titleEdit = "sửa đổi bổ sung hồ sơ đã công bố";
                    }
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignsuadoisaucongbo))));
                    //Các biến truyền vào Công văn

                    wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
                    wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
                    wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");

//                    if (receiptDeptName == null || receiptDeptName.equals("Cục ATTP")) {
//                        wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
//                        wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
//                        wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");
//                    } else {
//                        wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${deptParent}");
//                        wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptName}");
//                        wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptNames}");
//                    }
                    wU.replacePlaceholder(wmp, contentEdit, "${contentEdit}");
                    wU.replacePlaceholder(wmp, publishDate, "${publishDate}");
                    wU.replacePlaceholder(wmp, announmentNo, "${announmentNo}");
                    wU.replacePlaceholder(wmp, proName, "${proName}");
                    wU.replacePlaceholder(wmp, busiAdd, "${busiAdd}");
                    wU.replacePlaceholder(wmp, busiName, "${busiName}");
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    wU.replacePlaceholder(wmp, titleEdit, "${titleEdit}");
                    wU.replacePlaceholder(wmp, receiptNoOld, "${receiptNoOld}");
                    ProcessDAOHE pDaohe = new ProcessDAOHE();
                    Process pBo = pDaohe.getProcessByAction(filesForm.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, filesForm.getStatus(), Constants.FILE_STATUS.NEW_CREATE);
                    DepartmentDAOHE deptDaohe = new DepartmentDAOHE();
                    Department deptBo = deptDaohe.findBOById(pBo.getSendGroupId());
                    if (deptBo != null && deptBo.getDeptCode() != null) {
                        wU.replacePlaceholder(wmp, deptBo.getDeptCode(), "${deptCode}");
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReAnnounFiles))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    break;
                case Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    break;
            }
            if (filesForm.getAnnouncementReceiptPaperForm() != null) {
                receiptDeptName = filesForm.getAnnouncementReceiptPaperForm().getReceiptDeptName();
                receiptNo = filesForm.getAnnouncementReceiptPaperForm().getReceiptNo();
                businessName = filesForm.getAnnouncementReceiptPaperForm().getBusinessName();
                if (filesForm.getAnnouncement() != null) {
                    businessAdd = filesForm.getAnnouncement().getBusinessAddress();
                } else {
                    businessAdd = filesForm.getBusinessAddress();
                }
                telephone = filesForm.getAnnouncementReceiptPaperForm().getTelephone();
                fax = filesForm.getAnnouncementReceiptPaperForm().getFax();
                email = filesForm.getAnnouncementReceiptPaperForm().getEmail();
                productName = filesForm.getAnnouncementReceiptPaperForm().getProductName();
                manufactureName = filesForm.getAnnouncementReceiptPaperForm().getManufactureName();
                if (filesForm.getAnnouncement() != null) {
                    manufactureAdd = filesForm.getAnnouncement().getManufactureAddress();
                } else {
                    manufactureAdd = filesForm.getManufactureAddress();
                }
                nationName = filesForm.getAnnouncementReceiptPaperForm().getNationName();
                matchingTarget = filesForm.getAnnouncementReceiptPaperForm().getMatchingTarget();
                if (filesForm.getEffectiveDate() != null) {
                    if (filesForm.getEffectiveDate() > 0L) {
                        effectiveDate = filesForm.getEffectiveDate().toString();
                    } else {
                        effectiveDate = "3";
                    }
                } else {
                    effectiveDate = "3";
                }
            } else if (filesForm.getConfirmImportSatistPaperForm() != null) {
//                wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempPaperWordStatisImport))));
                receiptDeptName = filesForm.getConfirmImportSatistPaperForm().getTestAgencyName();
                businessName = filesForm.getConfirmImportSatistPaperForm().getImportBusinessName();
                businessAdd = filesForm.getBusinessAddress();
                telephone = filesForm.getConfirmImportSatistPaperForm().getImportBusinessTel();
                fax = filesForm.getConfirmImportSatistPaperForm().getImportBusinessFax();
                email = filesForm.getConfirmImportSatistPaperForm().getImportBusinessEmail();
                productName = filesForm.getConfirmImportSatistPaperForm().getProductName();
                manufactureName = filesForm.getConfirmImportSatistPaperForm().getExportBusinessName();
                matchingTarget = filesForm.getMatchingTarget();
            }
            if (filesForm.getDetailProduct() != null) {
                productTypeName = filesForm.getDetailProduct().getProductTypeName();
                if (filesForm.getDetailProduct().getUseage() == null || filesForm.getDetailProduct().getUseage().trim().length() == 0) {
                    filesForm.getDetailProduct().setUseage("Không có.");
                }
                if (filesForm.getDetailProduct().getObjectUse() == null || filesForm.getDetailProduct().getObjectUse().trim().length() == 0) {
                    filesForm.getDetailProduct().setObjectUse("Không có.");
                }
                if (filesForm.getDetailProduct().getGuideline() == null || filesForm.getDetailProduct().getGuideline().trim().length() == 0) {
                    filesForm.getDetailProduct().setGuideline("Không có.");
                }
                if (filesForm.getDetailProduct().getDateOfManufacture() != null) {
                    filesForm.getDetailProduct().setDateOfManufactureStr(DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getDateOfManufacture(), "dd/MM/yyyy"));
                }
            }
            if ("Cục ATTP".equals(receiptDeptName)) {
                wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
                wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
                wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");
            } else {
                wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${deptParent}");
                wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptName}");
                wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptNames}");
            }
            wU.replacePlaceholder(wmp, receiptNo, "${receiptNo}");
            wU.replacePlaceholder(wmp, businessName, "${businessName}");
            wU.replacePlaceholder(wmp, businessAdd, "${businessAdd}");
            wU.replacePlaceholder(wmp, telephone, "${telephone}");
            wU.replacePlaceholder(wmp, fax, "${fax}");
            wU.replacePlaceholder(wmp, email, "${email}");
            wU.replacePlaceholder(wmp, productName, "${productName}");
            wU.replacePlaceholder(wmp, productTypeName, "${productTypeName}");
            wU.replacePlaceholder(wmp, manufactureName, "${manufactureName}");
            wU.replacePlaceholder(wmp, manufactureAdd, "${manufactureAdd}");
            wU.replacePlaceholder(wmp, nationName, "${nationName}");
            wU.replacePlaceholder(wmp, matchingTarget, "${matchingTarget}");

            if (effectiveDate != null || effectiveDate.length() > 0) {
                wU.replacePlaceholder(wmp, effectiveDate, "${effectiveDate}");
            }

            String signer = "";
            String rolesigner;

            PositionDAOHE posdaohe = new PositionDAOHE();
            Position posbo = posdaohe.findPositionCode(getUserId());
            if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_CT)) {
                rolesigner = "CỤC TRƯỞNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_PCT)) {
                signer = "KT. CỤC TRƯỞNG";
                rolesigner = "PHÓ CỤC TRƯỞNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_OF_STAFF_T)) {
                signer = "TL. CỤC TRƯỞNG";
                rolesigner = "TRƯỞNG PHÒNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.GDTT)) {
                signer = "TL. CỤC TRƯỞNG";
                rolesigner = "GIÁM ĐỐC TRUNG TÂM";
            } else {
                rolesigner = "UNKNOW";
            }

            wU.replacePlaceholder(wmp, signer, "${signer}");
            wU.replacePlaceholder(wmp, rolesigner, "${roleSigner}");
            String leaderSinged = getUserName();
            wU.replacePlaceholder(wmp, leaderSinged, "${LeaderSigned}");
            String signedDate = "Hà Nội, ngày " + DateTimeUtils.convertDateToString(dateNow, "dd") + " tháng " + DateTimeUtils.convertDateToString(dateNow, "MM") + " năm " + DateTimeUtils.convertDateToString(dateNow, "yyyy");

            // them vao thong tin ho so
            List lstMainlyTarget;
            List lstBiologisTarget;
            List lstHeavyMetalTarget;
            List lstChemicalTarget;
            switch (procedure.getDescription()) {
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempPaperWordFileDetails))));
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                        filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                    }
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                        wU.replaceTable(wmp, 6, lstBiologisTarget);
                        wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 8, lstChemicalTarget);
                    } else {
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                    }

                    //wU.replaceTable(wmp, 9, lstQualityControlPlan);
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(createAnnounFilesPage03))));
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                        wU.replaceTable(wmp, 6, lstBiologisTarget);
                        wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 8, lstChemicalTarget);
                    } else {
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(createConfirmFuncImport))));
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                    lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                    lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                    lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                    wU.replaceTable(wmp, 4, lstMainlyTarget);
                    wU.replaceTable(wmp, 5, lstBiologisTarget);
                    wU.replaceTable(wmp, 6, lstHeavyMetalTarget);
                    wU.replaceTable(wmp, 7, lstChemicalTarget);
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(createConfirmFuncImport))));
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                    lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                    lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                    lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                    wU.replaceTable(wmp, 4, lstMainlyTarget);
                    wU.replaceTable(wmp, 5, lstBiologisTarget);
                    wU.replaceTable(wmp, 6, lstHeavyMetalTarget);
                    wU.replaceTable(wmp, 7, lstChemicalTarget);
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN:
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 4, lstMainlyTarget);
                        wU.replaceTable(wmp, 5, lstBiologisTarget);
                        wU.replaceTable(wmp, 6, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 7, lstChemicalTarget);
                    } else {
                        wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 4, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP:
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 4, lstMainlyTarget);
                        wU.replaceTable(wmp, 5, lstBiologisTarget);
                        wU.replaceTable(wmp, 6, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 7, lstChemicalTarget);
                    } else {
                        wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 4, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(announcement4star))));
                    List lstProductOfFile = fdhe.getProductOfFile(fileId);
                    wU.replaceTable(wmp, 2, lstProductOfFile);
                    break;
                case Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReAnnounFiles))));
                    if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                default:
                    ;
            }
            HashMap map = new HashMap();
            map.put("createForm", filesForm);
            wU.replacePlaceholder(wmp, map);
            //wU.createFooterPart(wmp, "MA HO SO: " + fileCode);
            if (wU.writePDFToStreamSignDownload(wmp, getResponse(), fileId, fileCode, filesForm.getQrCode(), "PDHS", true)) {
                resultMessage.add("1");
                resultMessage.add("Thành công");
                jsonDataGrid.setItems(resultMessage);
                return GRID_DATA;
                //Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình Convert và lưu file PDF");
            } else {
                resultMessage.add("3");
                resultMessage.add("Lỗi trong quá trình Convert và lưu file PDF");
                jsonDataGrid.setItems(resultMessage);
                return GRID_DATA;
                //Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, "Thành Công");
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Lỗi trong quá trình xử lý File PDF");
//            Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình xử lý File PDF: " + ex.getMessage());
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     * xuat bien ban tham dinh
     *
     * @return
     */
    public String onExportEvaluation() {
        try {
            WordExportUtils wU = new WordExportUtils();
            String sendDate;
            String businessName;
            String businessAddress;
            String productName;
            String legalContent;
            String foodSafetyQualityContent;
            String effectUtilityContent;

            if (fileId == null) {
                throw new Exception("Không có hồ sơ");
            }
            FilesDAOHE fdhe = new FilesDAOHE();
            Files form = fdhe.findById(fileId);
            //tim tham dinh
            EvaluationRecordsDAOHE evaluationRecordsDAOHE = new EvaluationRecordsDAOHE();
            EvaluationRecords evaluationRecords;
            evaluationRecords = evaluationRecordsDAOHE.findFilesByFileId(form);
            if (evaluationRecords == null) {
                throw new Exception("không có thẩm định");
            } else {
                sendDate = DateTimeUtils.convertDateToString(evaluationRecords.getSendDate(), "dd/MM/yyyy");
                businessName = evaluationRecords.getBusinessName();
                businessAddress = evaluationRecords.getBusinessAddress();
                productName = evaluationRecords.getProductName();
                legalContent = evaluationRecords.getLegalContent();
                foodSafetyQualityContent = evaluationRecords.getFoodSafetyQualityContent();
                effectUtilityContent = evaluationRecords.getEffectUtilityContent();
            }

            WordprocessingMLPackage wmp;
            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempPaperWordEvaluation))));
            wU.replacePlaceholder(wmp, sendDate, "${sendDate}");
            wU.replacePlaceholder(wmp, businessName, "${businessName}");
            wU.replacePlaceholder(wmp, businessAddress, "${businessAddress}");
            wU.replacePlaceholder(wmp, productName, "${productName}");

            if (evaluationRecords.getLegal() == 1) {
                wU.replacePlaceholder(wmp, " [X] ", "${legalAccept}");
                wU.replacePlaceholder(wmp, " [ ] ", "${legalDenied}");
                wU.replacePlaceholder(wmp, " [ ] ", "${legalAdditional}");
            } else if (evaluationRecords.getLegal() == -1) {
                wU.replacePlaceholder(wmp, " [ ] ", "${legalAccept}");
                wU.replacePlaceholder(wmp, " [ ] ", "${legalDenied}");
                wU.replacePlaceholder(wmp, " [X] ", "${legalAdditional}");
            } else {
                wU.replacePlaceholder(wmp, " [ ] ", "${legalAccept}");
                wU.replacePlaceholder(wmp, " [X] ", "${legalDenied}");
                wU.replacePlaceholder(wmp, " [ ] ", "${legalAdditional}");
            }
            wU.replacePlaceholder(wmp, legalContent, "${legalContent}");
            if (evaluationRecords.getFoodSafetyQuality() == 1) {
                wU.replacePlaceholder(wmp, " [X] ", "${foodSafetyAccept}");
                wU.replacePlaceholder(wmp, " [ ] ", "${foodSafetyDenied}");
                wU.replacePlaceholder(wmp, " [ ] ", "${foodSafetyAdditional}");
            } else if (evaluationRecords.getFoodSafetyQuality() == -1) {
                wU.replacePlaceholder(wmp, " [ ] ", "${foodSafetyAccept}");
                wU.replacePlaceholder(wmp, " [ ] ", "${foodSafetyDenied}");
                wU.replacePlaceholder(wmp, " [X] ", "${foodSafetyAdditional}");
            } else {
                wU.replacePlaceholder(wmp, " [ ] ", "${foodSafetyAccept}");
                wU.replacePlaceholder(wmp, " [X] ", "${foodSafetyDenied}");
                wU.replacePlaceholder(wmp, " [ ] ", "${foodSafetyAdditional}");
            }
            wU.replacePlaceholder(wmp, foodSafetyQualityContent, "${foodSafetyQualityContent}");
            if (evaluationRecords.getEffectUtility() == 1) {
                wU.replacePlaceholder(wmp, "[X]", "${effectUtilityAccept}");
                wU.replacePlaceholder(wmp, " [ ] ", "${effectUtilityDenied}");
                wU.replacePlaceholder(wmp, " [ ] ", "${effectUtilityAdditional}");
            } else if (evaluationRecords.getEffectUtility() == -1) {
                wU.replacePlaceholder(wmp, " [ ] ", "${effectUtilityAccept}");
                wU.replacePlaceholder(wmp, " [ ] ", "${effectUtilityDenied}");
                wU.replacePlaceholder(wmp, " [X] ", "${effectUtilityAdditional}");

            } else {
                wU.replacePlaceholder(wmp, " [ ] ", "${effectUtilityAccept}");
                wU.replacePlaceholder(wmp, " [X] ", "${effectUtilityDenied}");
                wU.replacePlaceholder(wmp, " [ ] ", "${effectUtilityAdditional}");
            }
            wU.replacePlaceholder(wmp, effectUtilityContent, "${effectUtilityContent}");
            wU.writeDocxToStream(wmp, getResponse());

        } catch (Exception en) {
            LogUtil.addLog(en);//binhnt sonar a160901
        }
        return null;
    }

    /**
     * xuat chi tiet ho so
     *
     * @return
     */
    public String onExportFileDetails() {
        try {
            Long fileId = Long.parseLong(getRequest().getParameter("fileId"));
            WordExportUtils wU = new WordExportUtils();
            FilesDAOHE fdhe = new FilesDAOHE();
            FilesForm filesForm = fdhe.getFilesDetail(fileId);
            ProcedureDAOHE procedurehe = new ProcedureDAOHE();
            Procedure procedure = procedurehe.findById(filesForm.getFileType());

            WordprocessingMLPackage wmp = null;
            String fileCode = filesForm.getFileCode();
            String businessId = filesForm.getDeptId().toString();
            String businessName = "N/A";
            if (filesForm != null && filesForm.getAnnouncement() != null) {
                businessName = filesForm.getAnnouncement().getBusinessName();
            }
            String mainStaff = "";
            ProcessDAOHE psdhe = new ProcessDAOHE();
            Long status = filesForm.getStatus();
            com.viettel.voffice.database.BO.Process pold = psdhe.getProcessByAction(fileId, Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.ASSIGNED, Constants.FILE_STATUS.EVALUATED);

            if (pold != null) {
                mainStaff = pold.getReceiveUser();
            } else {
                pold = psdhe.getProcessByAction(fileId, Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.ASSIGNED, Constants.FILE_STATUS.FEDBACK_TO_ADD);
                if (pold != null) {
                    mainStaff = pold.getReceiveUser();
                }
            }
            Long productType = 0L;
            if (filesForm.getDetailProduct() != null
                    && filesForm.getDetailProduct().getProductType() != null) {
                productType = filesForm.getDetailProduct().getProductType();
                if (filesForm.getDetailProduct().getUseage() == null
                        || filesForm.getDetailProduct().getUseage().trim().length() == 0) {
                    filesForm.getDetailProduct().setUseage("Không có.");
                }
                if (filesForm.getDetailProduct().getObjectUse() == null
                        || filesForm.getDetailProduct().getObjectUse().trim().length() == 0) {
                    filesForm.getDetailProduct().setObjectUse("Không có.");
                }
                if (filesForm.getDetailProduct().getGuideline() == null
                        || filesForm.getDetailProduct().getGuideline().trim().length() == 0) {
                    filesForm.getDetailProduct().setGuideline("Không có.");
                }
                if (filesForm.getDetailProduct().getDateOfManufacture() != null) {
                    filesForm.getDetailProduct().setDateOfManufactureStr(DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getDateOfManufacture(), "dd/MM/yyyy"));
                }
            }
            CategoryDAOHE catedaohe = new CategoryDAOHE();
            Category catebo = catedaohe.findById(productType);

            List lstMainlyTarget;
            List lstBiologisTarget;
            List lstHeavyMetalTarget;
            List lstChemicalTarget;
            switch (procedure.getDescription()) {
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01:
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        if (filesForm.getIsHaveSubLabel() != null
                                && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01TH))));
                            if (filesForm.getDetailProduct() != null
                                    && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                                filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            //List lstQualityControlPlan = fdhe.getQualityControlOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                            //wU.replaceTable(wmp, 7, lstQualityControlPlan);   
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01THsub))));
                            if (filesForm.getDetailProduct() != null
                                    && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                                filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            //List lstQualityControlPlan = fdhe.getQualityControlOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                            //wU.replaceTable(wmp, 7, lstQualityControlPlan);
                        }
                    } else if (filesForm.getIsHaveSubLabel() != null
                            && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BB))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                            filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BBsub))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                            filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01_TL:
                    if (catebo != null
                            && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        if (filesForm.getIsHaveSubLabel() != null
                                && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01THTL))));
                            if (filesForm.getDetailProduct() != null
                                    && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                                filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            //List lstQualityControlPlan = fdhe.getQualityControlOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                            //wU.replaceTable(wmp, 7, lstQualityControlPlan);   
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01THTL))));
                            if (filesForm.getDetailProduct() != null
                                    && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                                filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            //List lstQualityControlPlan = fdhe.getQualityControlOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                            //wU.replaceTable(wmp, 7, lstQualityControlPlan);
                        }
                    } else if (filesForm.getIsHaveSubLabel() != null
                            && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BBTL))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince()
                                            + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                            + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                            + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                            + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                            + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                            filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BBsub))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                            filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01TH))));
                            if (filesForm.getAnnouncement() != null && filesForm.getDetailProduct() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BB))));
                            if (filesForm.getAnnouncement() != null
                                    && filesForm.getDetailProduct() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01THsub))));
                        if (filesForm.getAnnouncement() != null && filesForm.getDetailProduct() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BBsub))));
                        if (filesForm.getAnnouncement() != null && filesForm.getDetailProduct() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03_TL:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01THTL))));
                            if (filesForm.getAnnouncement() != null && filesForm.getDetailProduct() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BBTL))));
                            if (filesForm.getAnnouncement() != null && filesForm.getDetailProduct() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01THsub))));
                        if (filesForm.getAnnouncement() != null && filesForm.getDetailProduct() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BBsub))));
                        if (filesForm.getAnnouncement() != null
                                && filesForm.getDetailProduct() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP:
                    if (filesForm.getIsHaveSubLabel() != null
                            && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopCN))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopCNsub))));
                        if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP_TL:
                    if (filesForm.getIsHaveSubLabel() != null
                            && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopCNTL))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày "
                                            + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                            + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm "
                                            + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopCNsubTL))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopCN))));
                        if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopCNsub))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN:
                    if (filesForm.getIsHaveSubLabel() != null
                            && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null
                                && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopTH))));
                            if (filesForm.getDetailProduct() != null
                                    && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopBB))));
                            if (filesForm.getDetailProduct() != null
                                    && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopTHsub))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopBBsub))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopTH))));
                            if (filesForm.getDetailProduct() != null
                                    && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopBB))));
                            if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopTHsub))));
                        if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopBBsub))));
                        if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempCongbohopquiCL))));
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getDocumentDate() != null) {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày... tháng... năm 20..");
                        }
                    }
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getReIssueDate() != null) {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                        }
                    }

                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempCongbophuhopCL))));
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getDocumentDate() != null) {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày... tháng... năm 20..");
                        }
                    }
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getReIssueDate() != null) {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempCongbophuhopCL))));
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getDocumentDate() != null) {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày... tháng... năm 20..");
                        }
                    }
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getReIssueDate() != null) {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempCongbophuhopCL))));
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getDocumentDate() != null) {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày... tháng... năm 20..");
                        }
                    }
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getReIssueDate() != null) {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempCongbophuhopCL))));
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getDocumentDate() != null) {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày... tháng... năm 20..");
                        }
                    }
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getReIssueDate() != null) {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05:
//                    Hiepvv_Home Xem truoc cong van SDBS sau cong bo
                    Long preLong = getRequest().getParameter("isPre") == null ? 0L : Long.parseLong(getRequest().getParameter("isPre"));
                    if (preLong > 0L) {
                        String contentEdit;
                        String publishDate = "";
                        String announmentNo = "";
                        String proName = "";
                        String busiName = "";
                        String busiAdd = "";
                        String receiptNoOld = "";
                        String receiptNo = "0000";
                        String titleEdit;
                        if (filesForm.getContentsEditATTP() != null && !filesForm.getContentsEditATTP().isEmpty()) {
                            contentEdit = filesForm.getContentsEditATTP();
                        } else {
                            contentEdit = filesForm.getContentsEdit();
                        }
                        if (filesForm.getAnnouncement().getPublishDate() != null) {
                            publishDate = DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "dd")
                                    + " tháng " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "MM")
                                    + " năm " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "yyyy");
                        }
                        if (filesForm.getAnnouncement().getAnnouncementNo() != null) {
                            announmentNo = filesForm.getAnnouncement().getAnnouncementNo();
                        }
                        if (filesForm.getAnnouncement().getProductName() != null) {
                            proName = filesForm.getAnnouncement().getProductName();
                        }
                        if (filesForm.getAnnouncement().getBusinessAddress() != null) {
                            busiAdd = filesForm.getAnnouncement().getBusinessAddress();
                        }
                        if (filesForm.getAnnouncement().getBusinessName() != null) {
                            busiName = filesForm.getAnnouncement().getBusinessName();
                        }
                        Date dateNow = getSysdate();
                        String signedDate = "Hà Nội, ngày " + DateTimeUtils.convertDateToString(dateNow, "dd")
                                + " tháng " + DateTimeUtils.convertDateToString(dateNow, "MM")
                                + " năm " + DateTimeUtils.convertDateToString(dateNow, "yyyy");
                        //binhnt fix sonar
//                        String receiptDeptName;
//                        if (filesForm.getAnnouncementReceiptPaperForm() != null) {
//                            receiptDeptName = filesForm.getAnnouncementReceiptPaperForm().getReceiptDeptName();
//                        } else if (filesForm.getConfirmImportSatistPaperForm() != null) {
//                            receiptDeptName = filesForm.getConfirmImportSatistPaperForm().getTestAgencyName();
//                        }
                        //!binhnt fix sonar
                        if (filesForm.getFilesSourceID() != null
                                && filesForm.getFilesSourceID() > 0L) {
                            Files fboOld = fdhe.findById(filesForm.getFilesSourceID());
                            if (fboOld != null
                                    && fboOld.getAnnouncementReceiptPaperId() != null) {
                                AnnouncementReceiptPaperDAOHE arpDaohe = new AnnouncementReceiptPaperDAOHE();
                                AnnouncementReceiptPaper arpbo = arpDaohe.findById(fboOld.getAnnouncementReceiptPaperId());
                                if (arpbo != null
                                        && arpbo.getReceiptDate() != null
                                        && arpbo.getSignDate() != null
                                        && arpbo.getReceiptNo() != null) {
                                    receiptNoOld = arpbo.getReceiptNo();
                                    publishDate = " ngày " + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "dd")
                                            + " tháng " + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "MM") + " năm "
                                            + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "yyyy");
                                }
                            }
                        }
                        if (filesForm.getTitleEdit() != null) {
                            titleEdit = filesForm.getTitleEdit();
                        } else if (filesForm.getTitleEditATTP() != null) {
                            titleEdit = filesForm.getTitleEditATTP();
                        } else {
                            titleEdit = "sửa đổi bổ sung hồ sơ đã công bố";
                        }
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignsuadoisaucongbo))));
                        //Các biến truyền vào Công văn
                        wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
                        wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
                        wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");
//                        if (receiptDeptName == null || receiptDeptName.equals("Cục ATTP")) {
//                            wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
//                            wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
//                            wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");
//                        } else {
//                            wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${deptParent}");
//                            wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptName}");
//                            wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptNames}");
//                        }
                        wU.replacePlaceholder(wmp, contentEdit, "${contentEdit}");
                        wU.replacePlaceholder(wmp, publishDate, "${publishDate}");
                        wU.replacePlaceholder(wmp, announmentNo, "${announmentNo}");
                        wU.replacePlaceholder(wmp, proName, "${proName}");
                        wU.replacePlaceholder(wmp, busiAdd, "${busiAdd}");
                        wU.replacePlaceholder(wmp, busiName, "${busiName}");
                        wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                        wU.replacePlaceholder(wmp, receiptNoOld, "${receiptNoOld}");
                        wU.replacePlaceholder(wmp, receiptNo, "${receiptNo}");
                        wU.replacePlaceholder(wmp, titleEdit, "${titleEdit}");
                        ProcessDAOHE pDaohe = new ProcessDAOHE();
                        Process pBo = pDaohe.getProcessByAction(filesForm.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, filesForm.getStatus(), Constants.FILE_STATUS.NEW_CREATE);
                        DepartmentDAOHE deptDaohe = new DepartmentDAOHE();
                        Department deptBo = deptDaohe.findBOById(pBo.getSendGroupId());
                        if (deptBo != null && deptBo.getDeptCode() != null) {
                            wU.replacePlaceholder(wmp, deptBo.getDeptCode(), "${deptCode}");
                        } else {
                            wU.replacePlaceholder(wmp, "SP", "${deptCode}");
                        }
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSuadoisaucongbo))));
                    }
//                    End Hiepvv_Home
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR:
                    if (filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getAnnouncement().getPublishDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince()
                                        + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày "
                                        + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                        }
                    }

                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_AnnounementReceiptPaper4Star))));
                    List lstProductOfFile = fdhe.getProductOfFile(fileId);
                    wU.replaceTable(wmp, 0, lstProductOfFile);
                    wU.replacePlaceholder(wmp, filesForm.getAnnouncement().getSignDateStr(), "${signDateStr}");
//                    wU.replacePlaceholder(wmp, pubDate, "${pubDate}");
//                    wU.replacePlaceholder(wmp, annouNo, "${annouNo}");
//                    wU.replacePlaceholder(wmp, effDate, "${effDate}");
                    break;
                default:;
            }
            if (status >= 4) {
                wU.replacePlaceholder(wmp, mainStaff, "${mainStaffNew}");
            } else {
                wU.replacePlaceholder(wmp, "", "${mainStaffNew}");
            }
            wU.replacePlaceholder(wmp, fileCode, "${fileCodeNew}");
            wU.replacePlaceholder(wmp, businessId, "${businessIdNew}");
            wU.replacePlaceholder(wmp, businessName, "${businessNameNew}");
            HashMap map = new HashMap();
            map.put("createForm", filesForm);

            wU.replacePlaceholder(wmp, map);
            //wU.createFooterPart(wmp, "Mã hồ sơ: " + fileCode);
            wU.writePDFToStream(wmp, getResponse(), fileId, fileCode, filesForm.getQrCode(), true);

        } catch (NumberFormatException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    public Boolean exportDataCvSdbs(String typeExport) {
        try {
            String receiptDeptName = "";
            String sendNo = "0";
            Date dateNow = getSysdate();
            String strFileId = getRequest().getParameter("fileId");
            Base64 decoder = new Base64();
            String strContent = new String(decoder.decode(getRequest().getParameter("content").replace("_", "+").getBytes()), "UTF-8");
            try {
                fileId = Long.parseLong(strFileId);
            } catch (NumberFormatException ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }

            WordExportUtils wU = new WordExportUtils();
            WordprocessingMLPackage wmp;
            String businessName = "";
            if (fileId == null) {
                throw new Exception("Không có hồ sơ");
            }
            FilesDAOHE fdhe = new FilesDAOHE();
            FilesForm form = fdhe.getFilesDetail(fileId);
            String fileCode = "";
            if (form != null) {
                businessName = form.getBusinessName();
                form.setStaffRequest(strContent);
                receiptDeptName = form.getAgencyName();
                sendNo = fdhe.getNewSendNo(form.getAgencyId());
                fileCode = form.getFileCode();
            }

            ProcessDAOHE pDaohe = new ProcessDAOHE();
            Process pBo = pDaohe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, form.getStatus(), Constants.FILE_STATUS.NEW_CREATE);
            if (pBo == null || pBo.getSendGroupId() == null) {
                return false;
            }
            DepartmentDAOHE deptDaohe = new DepartmentDAOHE();
            Department deptBo = deptDaohe.findBOById(pBo.getSendGroupId());
            if (deptBo != null) {
                wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(bieumauthongbaosuadoibosungUDDT))));
                wU.replacePlaceholder(wmp, deptBo.getDeptName(), "${deptName}");

            } else {
                wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(bieumauthongbaosuadoibosung))));
            }

            if ("Cục ATTP".equals(receiptDeptName)) {
                wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
                wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
                wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");
            } else {
                wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${deptParent}");
                wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptName}");
                wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptNames}");
            }
            wU.replacePlaceholder(wmp, sendNo, "${sendNo}");
            if (deptBo != null && deptBo.getDeptCode() != null) {
                wU.replacePlaceholder(wmp, deptBo.getDeptCode() + "e", "${deptCodeE}");
            }

            String signedDate = "Hà Nội, ngày " + DateTimeUtils.convertDateToString(dateNow, "dd")
                    + " tháng " + DateTimeUtils.convertDateToString(dateNow, "MM")
                    + " năm " + DateTimeUtils.convertDateToString(dateNow, "yyyy");
            wU.replacePlaceholder(wmp, signedDate, "${signDateStr}");
            wU.replacePlaceholder(wmp, businessName, "${businessName}");
            String signer = "";
            String rolesigner;

            PositionDAOHE posdaohe = new PositionDAOHE();
            Position posbo = posdaohe.findPositionCode(getUserId());
            if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_CT)) {
                rolesigner = "CỤC TRƯỞNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_PCT)) {
                signer = "KT. CỤC TRƯỞNG";
                rolesigner = "PHÓ CỤC TRƯỞNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_OF_STAFF_T)) {
                signer = "TL. CỤC TRƯỞNG";
                rolesigner = "TRƯỞNG PHÒNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.GDTT)) {
                signer = "TL. CỤC TRƯỞNG";
                rolesigner = "GIÁM ĐỐC TRUNG TÂM";
            } else {
                rolesigner = "UNKNOW";
            }

            wU.replacePlaceholder(wmp, signer, "${signer}");
            wU.replacePlaceholder(wmp, rolesigner, "${roleSigner}");
            if (deptBo != null && deptBo.getDeptCode() != null) {
                wU.replacePlaceholder(wmp, deptBo.getDeptCode(), "${deptCode}");
            }

            String leaderSinged = getUserName();
            wU.replacePlaceholder(wmp, leaderSinged, "${LeaderSigned}");
            HashMap map = new HashMap();
            map.put("createForm", form);
            wU.replacePlaceholder(wmp, map);
            if ("EX_TEMP".equals(typeExport)) {
                wU.createFooterPart(wmp, "MA HO SO: " + fileCode);
                wU.writePDF4Preview(wmp, getResponse(), fileCode);
                return true;
            } else if ("EX_SIGN".equals(typeExport)) {
                return wU.writePDFToStreamSign(wmp, getResponse(), fileId, fileCode, null, "CVBS", false, 0, true);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return false;
    }

    // hieptq update 211015
    public String exportDataCvSdbsPlugin(String typeExport) {
        try {
            String receiptDeptName = "";
            String sendNo = "0";
            Date dateNow = getSysdate();
            String strFileId = getRequest().getParameter("fileId");
            Base64 decoder = new Base64();
            String strContent = new String(decoder.decode(getRequest().getParameter("content").replace("_", "+").getBytes()), "UTF-8");
            try {
                fileId = Long.parseLong(strFileId);
            } catch (NumberFormatException ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }

            WordExportUtils wU = new WordExportUtils();
            String businessName = "";
            if (fileId == null) {
                throw new Exception("Không có hồ sơ");
            }
            FilesDAOHE fdhe = new FilesDAOHE();
            FilesForm form = fdhe.getFilesDetail(fileId);
            String fileCode = "";
            if (form != null) {
                businessName = form.getBusinessName();
                form.setStaffRequest(strContent);
                receiptDeptName = form.getAgencyName();
                sendNo = fdhe.getNewSendNo(form.getAgencyId());
                fileCode = form.getFileCode();
            }

            WordprocessingMLPackage wmp;
            ProcessDAOHE pDaohe = new ProcessDAOHE();
            Process pBo = pDaohe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, form.getStatus(), Constants.FILE_STATUS.NEW_CREATE);
            if (pBo == null || pBo.getSendGroupId() == null) {
                return "false";
            }
            DepartmentDAOHE deptDaohe = new DepartmentDAOHE();
            Department deptBo = deptDaohe.findBOById(pBo.getSendGroupId());
            if (deptBo != null) {
                wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(bieumauthongbaosuadoibosungUDDT))));
                wU.replacePlaceholder(wmp, deptBo.getDeptName(), "${deptName}");

            } else {
                wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(bieumauthongbaosuadoibosung))));
            }

            if ("Cục ATTP".equals(receiptDeptName)) {
                wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
                wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
                wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");
            } else {
                wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${deptParent}");
                wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptName}");
                wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptNames}");
            }
            wU.replacePlaceholder(wmp, sendNo, "${sendNo}");
            if (deptBo != null && deptBo.getDeptCode() != null) {
                wU.replacePlaceholder(wmp, deptBo.getDeptCode() + "e", "${deptCodeE}");
            }

            String signedDate = "Hà Nội, ngày " + DateTimeUtils.convertDateToString(dateNow, "dd") + " tháng " + DateTimeUtils.convertDateToString(dateNow, "MM") + " năm " + DateTimeUtils.convertDateToString(dateNow, "yyyy");
            wU.replacePlaceholder(wmp, signedDate, "${signDateStr}");
            wU.replacePlaceholder(wmp, businessName, "${businessName}");

            String signer = "";
            String rolesigner;

            PositionDAOHE posdaohe = new PositionDAOHE();
            Position posbo = posdaohe.findPositionCode(getUserId());
            if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_CT)) {
                rolesigner = "CỤC TRƯỞNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_PCT)) {
                signer = "KT. CỤC TRƯỞNG";
                rolesigner = "PHÓ CỤC TRƯỞNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_OF_STAFF_T)) {
                signer = "TL. CỤC TRƯỞNG";
                rolesigner = "TRƯỞNG PHÒNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.GDTT)) {
                signer = "TL. CỤC TRƯỞNG";
                rolesigner = "GIÁM ĐỐC TRUNG TÂM";
            } else {
                rolesigner = "UNKNOW";
            }

            wU.replacePlaceholder(wmp, signer, "${signer}");
            wU.replacePlaceholder(wmp, rolesigner, "${roleSigner}");
            if (deptBo != null && deptBo.getDeptCode() != null) {
                wU.replacePlaceholder(wmp, deptBo.getDeptCode(), "${deptCode}");
            }

            String leaderSinged = getUserName();
            wU.replacePlaceholder(wmp, leaderSinged, "${LeaderSigned}");
            HashMap map = new HashMap();
            map.put("createForm", form);
            wU.replacePlaceholder(wmp, map);
            if ("EX_TEMP".equals(typeExport)) {
                wU.createFooterPart(wmp, "MA HO SO: " + fileCode);
                wU.writeDocxToStream(wmp, getResponse());
                return "true";
            } else if ("EX_SIGN".equals(typeExport)) {
                return wU.writePDFToStreamSignPlugin(wmp, getResponse(), fileId, fileCode, null, "CVBS", false, 0, true);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "false";
    }

    public String onXuatTBSDBS() {
        exportDataCvSdbs("EX_TEMP");
        return null;
    }

    /**
     * xuat CV ky SDBS
     *
     * @return
     */
    public String onExportCvSdbsSign() {
        List resultMessage = new ArrayList();
        if (exportDataCvSdbs("EX_SIGN")) {
            resultMessage.add("1");
            resultMessage.add("Xuất công văn SĐBS thành công");
        } else {
            resultMessage.add("3");
            resultMessage.add("Lỗi trong quá trình xuất công văn SĐBS");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    // hieptq update 131015
    public String onExportPaperSignPlugin() {
        List resultMessage = new ArrayList();
        try {
            WordExportUtils wU = new WordExportUtils();
            AnnouncementReceiptPaperDAOHE ann = new AnnouncementReceiptPaperDAOHE();
            // insert ban cong bo
            if (fileId == null) {
                resultMessage.add("3");
                resultMessage.add("Lỗi trong quá trình Convert và lưu file PDF: Không có hồ sơ");
                jsonDataGrid.setItems(resultMessage);
                return GRID_DATA;
            }
            Date dateNow = getSysdate();
            FilesDAOHE fdhe = new FilesDAOHE();
            FilesForm filesForm = fdhe.getFilesDetail(fileId);

            ProcedureDAOHE procedurehe = new ProcedureDAOHE();
            Procedure procedure = procedurehe.findById(filesForm.getFileType());
            if (filesForm == null) {
                resultMessage.add("3");
                resultMessage.add("Lỗi trong quá trình Convert và lưu file PDF: Không có hồ sơ");
                jsonDataGrid.setItems(resultMessage);
                return GRID_DATA;
            }
//            String receiptDeptName;
            String businessName = "";
            String businessAdd = "";
            String telephone = "";
            String fax = "";
            String email = "";
            String productName = "";
            String productTypeName = "";
            String manufactureName = "";
            String manufactureAdd = "";
            String nationName = "";
            String matchingTarget = "";
            String effectiveDate = "";
            String receiptNo = "";

            WordprocessingMLPackage wmp = null;

            String fileCode = filesForm.getFileCode();
            Long productType = 0L;

            if (filesForm.getDetailProduct() != null
                    && filesForm.getDetailProduct().getProductType() != null) {
                productType = filesForm.getDetailProduct().getProductType();
            }
            CategoryDAOHE catedaohe = new CategoryDAOHE();
            Category catebo = catedaohe.findById(productType);

            switch (procedure.getDescription()) {
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01:
                    if (filesForm.getIsHaveSubLabel() != null
                            && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiTH))));
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiBB))));
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiTHsub))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiBBsub))));
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01_TL:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiTHTL))));
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03:
                    if (filesForm.getIsHaveSubLabel() != null
                            && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiTH))));
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiBB))));
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiTHsub))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiBBsub))));
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03_TL:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbohopquiTHTL))));
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopCN))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopCNsub))));
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP_TL:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopCNTL))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopCNTL))));
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopCN))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopCNsub))));
                    }

                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopTH))));
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopBB))));
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopTHsub))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopBBsub))));
                    }

                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopTH))));
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopBB))));
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopTHsub))));
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignCongbophuhopBBsub))));
                    }

                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05:
//                    Hiepvv SDBS sau cong bo
                    String contentEdit;
                    String publishDate = "";
                    String announmentNo = "";
                    String proName = "";
                    String busiName = "";
                    String busiAdd = "";
                    String titleEdit;
                    String receiptNoOld = "";
                    receiptNo = "0000";
                    if (filesForm.getContentsEditATTP() != null && !filesForm.getContentsEditATTP().isEmpty()) {
                        contentEdit = filesForm.getContentsEditATTP();
                    } else {
                        contentEdit = filesForm.getContentsEdit();
                    }
                    if (filesForm.getAnnouncement().getPublishDate() != null) {
                        publishDate = DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "yyyy");
                    }
                    if (filesForm.getAnnouncement().getAnnouncementNo() != null) {
                        announmentNo = filesForm.getAnnouncement().getAnnouncementNo();
                    }
                    if (filesForm.getAnnouncement().getProductName() != null) {
                        proName = filesForm.getAnnouncement().getProductName();
                    }
                    if (filesForm.getAnnouncement().getBusinessAddress() != null) {
                        busiAdd = filesForm.getAnnouncement().getBusinessAddress();
                    }
                    if (filesForm.getAnnouncement().getBusinessName() != null) {
                        busiName = filesForm.getAnnouncement().getBusinessName();
                    }
                    if (filesForm.getTitleEdit() != null) {
                        titleEdit = filesForm.getTitleEdit();
                    } else if (filesForm.getTitleEditATTP() != null) {
                        titleEdit = filesForm.getTitleEditATTP();
                    } else {
                        titleEdit = "sửa đổi bổ sung hồ sơ đã công bố";
                    }
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignsuadoisaucongbo))));
                    //Các biến truyền vào Công văn
                    wU.replacePlaceholder(wmp, contentEdit, "${contentEdit}");
                    wU.replacePlaceholder(wmp, publishDate, "${publishDate}");
                    wU.replacePlaceholder(wmp, announmentNo, "${announmentNo}");
                    wU.replacePlaceholder(wmp, proName, "${proName}");
                    wU.replacePlaceholder(wmp, busiAdd, "${busiAdd}");
                    wU.replacePlaceholder(wmp, busiName, "${busiName}");
                    wU.replacePlaceholder(wmp, titleEdit, "${titleEdit}");
                    wU.replacePlaceholder(wmp, receiptNoOld, "${receiptNoOld}");
                    ProcessDAOHE pDaohe = new ProcessDAOHE();
                    Process pBo = pDaohe.getProcessByAction(filesForm.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, filesForm.getStatus(), Constants.FILE_STATUS.NEW_CREATE);
                    DepartmentDAOHE deptDaohe = new DepartmentDAOHE();
                    Department deptBo = deptDaohe.findBOById(pBo.getSendGroupId());
                    if (deptBo != null && deptBo.getDeptCode() != null) {
                        wU.replacePlaceholder(wmp, deptBo.getDeptCode(), "${deptCode}");
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR:
                    String pubDate = "";
                    String annouNo = "";
                    String effDate;
                    if (filesForm.getAnnouncement().getPublishDate() != null) {
                        pubDate = "ngày " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "yyyy");
                    }
                    if (filesForm.getEffectiveDate() != null) {
                        if (filesForm.getEffectiveDate() > 0L) {
                            effDate = filesForm.getEffectiveDate().toString();
                        } else {
                            effDate = "3";
                        }
                    } else {
                        effDate = "3";
                    }
                    if (filesForm.getAnnouncement() != null && filesForm.getAnnouncement().getAnnouncementNo() != null) {
                        annouNo = filesForm.getAnnouncement().getAnnouncementNo();
                    }
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(temPaperWordAnnouce4Star))));
                    wU.replacePlaceholder(wmp, pubDate, "${pubDate}");
                    wU.replacePlaceholder(wmp, annouNo, "${annouNo}");
                    wU.replacePlaceholder(wmp, effDate, "${effDate}");
                    break;
                case Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(reReceiptPaperAnnounementSign))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    break;
                case Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP:
                    //wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempReConfirmFuncImport))));
                    break;
            }
            //binhnt update 260216
            String signedDate = "Hà Nội, ngày " + DateTimeUtils.convertDateToString(dateNow, "dd")
                    + " tháng " + DateTimeUtils.convertDateToString(dateNow, "MM")
                    + " năm " + DateTimeUtils.convertDateToString(dateNow, "yyyy");
            if (filesForm.getAnnouncementReceiptPaperForm() != null) {
//                receiptDeptName = filesForm.getAnnouncementReceiptPaperForm().getReceiptDeptName();//binhnt fix sonar
                if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getFormNumber() != null) {
                    receiptNo = filesForm.getReIssueForm().getFormNumber();
                } else {
                    receiptNo = filesForm.getAnnouncementReceiptPaperForm().getReceiptNo();
                }
                businessName = filesForm.getAnnouncementReceiptPaperForm().getBusinessName();
                if (filesForm.getAnnouncement() != null) {
                    businessAdd = filesForm.getAnnouncement().getBusinessAddress();
                } else {
                    businessAdd = filesForm.getBusinessAddress();
                }
                telephone = filesForm.getAnnouncementReceiptPaperForm().getTelephone();
                fax = filesForm.getAnnouncementReceiptPaperForm().getFax();
                email = filesForm.getAnnouncementReceiptPaperForm().getEmail();
                productName = filesForm.getAnnouncementReceiptPaperForm().getProductName();
                manufactureName = filesForm.getAnnouncementReceiptPaperForm().getManufactureName();
                if (filesForm.getAnnouncement() != null) {
                    manufactureAdd = filesForm.getAnnouncement().getManufactureAddress();
                } else {
                    manufactureAdd = filesForm.getManufactureAddress();
                }
                nationName = filesForm.getAnnouncementReceiptPaperForm().getNationName();
                matchingTarget = filesForm.getAnnouncementReceiptPaperForm().getMatchingTarget();
                if (filesForm.getEffectiveDate() != null) {
                    if (filesForm.getEffectiveDate() > 0L) {
                        effectiveDate = filesForm.getEffectiveDate().toString();
                    } else {
                        effectiveDate = "3";
                    }
                } else {
                    effectiveDate = "3";
                }
                //binhnt update 260216
                if (filesForm.getAnnouncementReceiptPaperForm().getSignDate() != null) {
                    signedDate = "Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getAnnouncementReceiptPaperForm().getSignDate(), "dd")
                            + " tháng " + DateTimeUtils.convertDateToString(filesForm.getAnnouncementReceiptPaperForm().getSignDate(), "MM")
                            + " năm " + DateTimeUtils.convertDateToString(filesForm.getAnnouncementReceiptPaperForm().getSignDate(), "yyyy");
                }
            } else if (filesForm.getConfirmImportSatistPaperForm() != null) {
//                wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempPaperWordStatisImport))));
//                receiptDeptName = filesForm.getConfirmImportSatistPaperForm().getTestAgencyName();//binhnt fix sonar
                businessName = filesForm.getConfirmImportSatistPaperForm().getImportBusinessName();
                businessAdd = filesForm.getBusinessAddress();
                telephone = filesForm.getConfirmImportSatistPaperForm().getImportBusinessTel();
                fax = filesForm.getConfirmImportSatistPaperForm().getImportBusinessFax();
                email = filesForm.getConfirmImportSatistPaperForm().getImportBusinessEmail();
                productName = filesForm.getConfirmImportSatistPaperForm().getProductName();
                manufactureName = filesForm.getConfirmImportSatistPaperForm().getExportBusinessName();
                matchingTarget = filesForm.getMatchingTarget();
            }
            if (filesForm.getDetailProduct() != null) {
                productTypeName = filesForm.getDetailProduct().getProductTypeName();
                if (filesForm.getDetailProduct().getUseage() == null
                        || filesForm.getDetailProduct().getUseage().trim().length() == 0) {
                    filesForm.getDetailProduct().setUseage("Không có.");
                }
                if (filesForm.getDetailProduct().getObjectUse() == null
                        || filesForm.getDetailProduct().getObjectUse().trim().length() == 0) {
                    filesForm.getDetailProduct().setObjectUse("Không có.");
                }
                if (filesForm.getDetailProduct().getGuideline() == null
                        || filesForm.getDetailProduct().getGuideline().trim().length() == 0) {
                    filesForm.getDetailProduct().setGuideline("Không có.");
                }
                if (filesForm.getDetailProduct().getDateOfManufacture() != null) {
                    filesForm.getDetailProduct().setDateOfManufactureStr(DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getDateOfManufacture(), "dd/MM/yyyy"));
                }
            }

            wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
            wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
            wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");

//            if (receiptDeptName.equals("Cục ATTP")) {
//                wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
//                wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
//                wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");
//            } else {
//                wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${deptParent}");
//                wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptName}");
//                wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptNames}");
//            }
            wU.replacePlaceholder(wmp, receiptNo, "${receiptNo}");
            wU.replacePlaceholder(wmp, businessName, "${businessName}");
            wU.replacePlaceholder(wmp, businessAdd, "${businessAdd}");
            wU.replacePlaceholder(wmp, telephone, "${telephone}");
            wU.replacePlaceholder(wmp, fax, "${fax}");
            wU.replacePlaceholder(wmp, email, "${email}");
            wU.replacePlaceholder(wmp, productName, "${productName}");
            wU.replacePlaceholder(wmp, productTypeName, "${productTypeName}");
            wU.replacePlaceholder(wmp, manufactureName, "${manufactureName}");
            wU.replacePlaceholder(wmp, manufactureAdd, "${manufactureAdd}");
            wU.replacePlaceholder(wmp, nationName, "${nationName}");
            wU.replacePlaceholder(wmp, matchingTarget, "${matchingTarget}");

            if (effectiveDate != null || effectiveDate.length() > 0) {
                wU.replacePlaceholder(wmp, effectiveDate, "${effectiveDate}");
            }
            String signer = "";
            String rolesigner;

            PositionDAOHE posdaohe = new PositionDAOHE();
            Position posbo = posdaohe.findPositionCode(getUserId());
            if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_CT)) {
                rolesigner = "CỤC TRƯỞNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_PCT)) {
                signer = "KT. CỤC TRƯỞNG";
                rolesigner = "PHÓ CỤC TRƯỞNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.LEADER_OF_STAFF_T)) {
                signer = "TL. CỤC TRƯỞNG";
                rolesigner = "TRƯỞNG PHÒNG";
            } else if (posbo != null && posbo.getPosCode().equals(Constants.POSITION.GDTT)) {
                signer = "TL. CỤC TRƯỞNG";
                rolesigner = "GIÁM ĐỐC TRUNG TÂM";
            } else {
                rolesigner = "UNKNOW";
            }
            wU.replacePlaceholder(wmp, signer, "${signer}");
            wU.replacePlaceholder(wmp, rolesigner, "${roleSigner}");
            String leaderSinged = getUserName();
            wU.replacePlaceholder(wmp, leaderSinged, "${LeaderSigned}");
            // binhnt53 update 260216
//            String signedDate = "Hà Nội, ngày " + DateTimeUtils.convertDateToString(dateNow, "dd") + " tháng " + DateTimeUtils.convertDateToString(dateNow, "MM") + " năm " + DateTimeUtils.convertDateToString(dateNow, "yyyy");

            // them vao thong tin ho so
            List lstMainlyTarget;
            List lstBiologisTarget;
            List lstHeavyMetalTarget;
            List lstChemicalTarget;
            switch (procedure.getDescription()) {
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01:
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince()
                                        + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                        filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                    }
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                        wU.replaceTable(wmp, 6, lstBiologisTarget);
                        wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 8, lstChemicalTarget);
                    } else {
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01_TL:
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince()
                                        + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                        filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                    }
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                        wU.replaceTable(wmp, 6, lstBiologisTarget);
                        wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 8, lstChemicalTarget);
                    } else {
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03:
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                        wU.replaceTable(wmp, 6, lstBiologisTarget);
                        wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 8, lstChemicalTarget);
                    } else {
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03_TL:
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince()
                                        + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                        wU.replaceTable(wmp, 6, lstBiologisTarget);
                        wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 8, lstChemicalTarget);
                    } else {
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP:
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince()
                                        + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                    lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                    lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                    lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                    wU.replaceTable(wmp, 5, lstMainlyTarget);//u150122 binhnt53
                    wU.replaceTable(wmp, 6, lstBiologisTarget);
                    wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                    wU.replaceTable(wmp, 8, lstChemicalTarget);//u150122 binhnt53
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP_TL:
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince()
                                        + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                    lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                    lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                    lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                    wU.replaceTable(wmp, 5, lstMainlyTarget);//u150122 binhnt53
                    wU.replaceTable(wmp, 6, lstBiologisTarget);
                    wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                    wU.replaceTable(wmp, 8, lstChemicalTarget);//u150122 binhnt53
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN:
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {

                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince()
                                        + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                    lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                    lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                    lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                    wU.replaceTable(wmp, 5, lstMainlyTarget);//u150122 binhnt53
                    wU.replaceTable(wmp, 6, lstBiologisTarget);
                    wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                    wU.replaceTable(wmp, 8, lstChemicalTarget);//u150122 binhnt53
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN:
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince()
                                        + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);//u150122 binhnt53
                        wU.replaceTable(wmp, 6, lstBiologisTarget);
                        wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 8, lstChemicalTarget);//u150122 binhnt53
                    } else {
                        wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);//u150122 binhnt53
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP:
                    if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getDetailProduct().getSignDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince()
                                        + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("Hà Nội, ngày... tháng... năm 20..");
                        }
                    }
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);//u150122 binhnt53
                        wU.replaceTable(wmp, 6, lstBiologisTarget);
                        wU.replaceTable(wmp, 7, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 8, lstChemicalTarget);//u150122 binhnt53
                    } else {
                        wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 5, lstMainlyTarget);//u150122 binhnt53
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR:
                    List lstProductOfFile = fdhe.getProductOfFile(fileId);
                    wU.replaceTable(wmp, 2, lstProductOfFile);
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05:
                    wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                    break;
                case Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT:
                    if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP:
                    if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN:
                    if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN:
                    if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP:
                    if (filesForm.getReIssueForm() != null
                            && filesForm.getReIssueForm().getReIssueDate() != null) {
                        if (filesForm.getReIssueForm() != null
                                && filesForm.getReIssueForm().getReIssueDate() != null) {
                            if (filesForm.getReIssueForm().getReIssueDate() != null) {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                            } else {
                                filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                            }
                        }
                    }
                    break;
                default:
                    ;
            }
            HashMap map = new HashMap();
            map.put("createForm", filesForm);
            wU.replacePlaceholder(wmp, map);
            //wU.createFooterPart(wmp, "MA HO SO: " + fileCode);
            //create file temp
            String fullFile = wU.writePDFToStreamSignPlugin(wmp, getResponse(), fileId, fileCode, filesForm.getQrCode(), "PDHS", true, 1, true);
            //End create file temp
            //Hiepvv SDBS sau cong bo
            if (procedure != null
                    && procedure.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05)) {
                if (!"false".equals(fullFile)) {
//                    Copy file Công văn và các file đính kèm của hs SĐBS sau công bố vào hồ sơ gốc

                    resultMessage.add("1");
                    resultMessage.add("Xuất hồ sơ thành công");
                    resultMessage.add(fullFile);
                    jsonDataGrid.setItems(resultMessage);
                    return GRID_DATA;
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Lỗi trong quá trình Convert và lưu file PDF");
                    jsonDataGrid.setItems(resultMessage);
                    return GRID_DATA;
                }
            } else {
                wmp = onSignOnlyPaper(wU, fdhe, procedurehe, ann, map);
                String paperOnly = wU.writePDFToStreamSignPlugin(wmp, getResponse(), fileId, fileCode, filesForm.getQrCode(), "PDHS", false, 2, false);
                if (!"false".equals(fullFile) && !"false".equals(paperOnly)) {
                    resultMessage.add("1");
                    resultMessage.add("Xuất hồ sơ thành công");
                    resultMessage.add(fullFile + ";" + paperOnly);
                    jsonDataGrid.setItems(resultMessage);
                    return GRID_DATA;
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Lỗi trong quá trình Convert và lưu file PDF");
                    jsonDataGrid.setItems(resultMessage);
                    return GRID_DATA;
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Lỗi trong quá trình xử lý File PDF");
//            Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình xử lý File PDF: " + ex.getMessage());
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String onExportPaperSignFilePlugin() {
        List resultMessage = new ArrayList();
        try {
            WordExportUtils wU = new WordExportUtils();
            // insert ban cong bo
            if (fileId == null) {
                resultMessage.add("3");
                resultMessage.add("Lỗi trong quá trình Convert và lưu file PDF: Không có hồ sơ");
                jsonDataGrid.setItems(resultMessage);
                return GRID_DATA;
            }
            Date dateNow = getSysdate();
            FilesDAOHE fdhe = new FilesDAOHE();
            FilesForm filesForm = fdhe.getFilesDetail(fileId);

            ProcedureDAOHE procedurehe = new ProcedureDAOHE();
            Procedure procedure = procedurehe.findById(filesForm.getFileType());
            if (filesForm == null) {
                resultMessage.add("3");
                resultMessage.add("Lỗi trong quá trình Convert và lưu file PDF: Không có hồ sơ");
                jsonDataGrid.setItems(resultMessage);
                return GRID_DATA;
            }

            WordprocessingMLPackage wmp = null;
            String fileCode = filesForm.getFileCode();
            Long productType = 0L;

            if (filesForm.getDetailProduct() != null
                    && filesForm.getDetailProduct().getProductType() != null) {
                productType = filesForm.getDetailProduct().getProductType();
            }
            CategoryDAOHE catedaohe = new CategoryDAOHE();
            Category catebo = catedaohe.findById(productType);
            List lstMainlyTarget;
            List lstBiologisTarget;
            List lstHeavyMetalTarget;
            List lstChemicalTarget;
            switch (procedure.getDescription()) {
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01:
                    if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        if (filesForm.getIsHaveSubLabel() != null
                                && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01TH))));
                            if (filesForm.getDetailProduct() != null
                                    && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                                filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            //List lstQualityControlPlan = fdhe.getQualityControlOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                            //wU.replaceTable(wmp, 7, lstQualityControlPlan);   
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01THsub))));
                            if (filesForm.getDetailProduct() != null
                                    && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                                filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            //List lstQualityControlPlan = fdhe.getQualityControlOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                            //wU.replaceTable(wmp, 7, lstQualityControlPlan);
                        }
                    } else if (filesForm.getIsHaveSubLabel() != null
                            && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BB))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                            filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BBsub))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                            filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01_TL:
                    if (catebo != null
                            && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        if (filesForm.getIsHaveSubLabel() != null
                                && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01THTL))));
                            if (filesForm.getDetailProduct() != null
                                    && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                                filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            //List lstQualityControlPlan = fdhe.getQualityControlOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                            //wU.replaceTable(wmp, 7, lstQualityControlPlan);   
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01THTL))));
                            if (filesForm.getDetailProduct() != null
                                    && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                                filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                        }
                    } else if (filesForm.getIsHaveSubLabel() != null
                            && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BBTL))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince()
                                            + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                            + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                            + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                            + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM")
                                            + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                            filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BBsub))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                            filesForm.getAnnouncement().setMatchingTarget(filesForm.getAnnouncement().getMatchingTarget().replace(";", "\n\r"));
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01TH))));
                            if (filesForm.getAnnouncement() != null && filesForm.getDetailProduct() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BB))));
                            if (filesForm.getAnnouncement() != null
                                    && filesForm.getDetailProduct() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01THsub))));
                        if (filesForm.getAnnouncement() != null && filesForm.getDetailProduct() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BBsub))));
                        if (filesForm.getAnnouncement() != null && filesForm.getDetailProduct() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03_TL:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01THTL))));
                            if (filesForm.getAnnouncement() != null && filesForm.getDetailProduct() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BBTL))));
                            if (filesForm.getAnnouncement() != null && filesForm.getDetailProduct() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01THsub))));
                        if (filesForm.getAnnouncement() != null && filesForm.getDetailProduct() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_Congbohopqui01BBsub))));
                        if (filesForm.getAnnouncement() != null
                                && filesForm.getDetailProduct() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP:
                    if (filesForm.getIsHaveSubLabel() != null
                            && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopCN))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopCNsub))));
                        if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP_TL:
                    if (filesForm.getIsHaveSubLabel() != null
                            && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopCNTL))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày "
                                            + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd")
                                            + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm "
                                            + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopCNsubTL))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopCN))));
                        if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopCNsub))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN:
                    if (filesForm.getIsHaveSubLabel() != null
                            && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null
                                && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopTH))));
                            if (filesForm.getDetailProduct() != null
                                    && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopBB))));
                            if (filesForm.getDetailProduct() != null
                                    && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopTHsub))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopBBsub))));
                        if (filesForm.getDetailProduct() != null
                                && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP:
                    if (filesForm.getIsHaveSubLabel() != null && filesForm.getIsHaveSubLabel().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                        if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopTH))));
                            if (filesForm.getDetailProduct() != null
                                    && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                            lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                            lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                            wU.replaceTable(wmp, 4, lstBiologisTarget);
                            wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                            wU.replaceTable(wmp, 6, lstChemicalTarget);
                        } else {
                            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopBB))));
                            if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                                BusinessDAOHE busdaohe = new BusinessDAOHE();
                                Business busbo = busdaohe.findById(filesForm.getDeptId());
                                if (filesForm.getDetailProduct().getSignDate() != null) {
                                    if (busbo != null) {
                                        filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    } else {
                                        filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                    }
                                } else if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                                }
                            }
                            lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                            wU.replaceTable(wmp, 3, lstMainlyTarget);
                        }
                    } else if (catebo != null && catebo.getCode().equals(Constants.CATEGORY_TYPE.BBP) == false) {//u150122 binhnt53
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopTHsub))));
                        if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        lstBiologisTarget = fdhe.getProductTargetOfFile(fileId, 1l);
                        lstHeavyMetalTarget = fdhe.getProductTargetOfFile(fileId, 2l);
                        lstChemicalTarget = fdhe.getProductTargetOfFile(fileId, 3l);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                        wU.replaceTable(wmp, 4, lstBiologisTarget);
                        wU.replaceTable(wmp, 5, lstHeavyMetalTarget);
                        wU.replaceTable(wmp, 6, lstChemicalTarget);
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_CongbophuhopBBsub))));
                        if (filesForm.getDetailProduct() != null && filesForm.getAnnouncement() != null) {
                            BusinessDAOHE busdaohe = new BusinessDAOHE();
                            Business busbo = busdaohe.findById(filesForm.getDeptId());
                            if (filesForm.getDetailProduct().getSignDate() != null) {
                                if (busbo != null) {
                                    filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                } else {
                                    filesForm.getAnnouncement().setSignDateStr("..., ngày " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getSignDate(), "yyyy"));
                                }
                            } else if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                            }
                        }
                        lstMainlyTarget = fdhe.getMainlyTargetOfFile(fileId);
                        wU.replaceTable(wmp, 3, lstMainlyTarget);
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempCongbohopquiCL))));
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getDocumentDate() != null) {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày... tháng... năm 20..");
                        }
                    }
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getReIssueDate() != null) {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                        }
                    }

                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempCongbophuhopCL))));
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getDocumentDate() != null) {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày... tháng... năm 20..");
                        }
                    }
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getReIssueDate() != null) {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempCongbophuhopCL))));
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getDocumentDate() != null) {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày... tháng... năm 20..");
                        }
                    }
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getReIssueDate() != null) {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempCongbophuhopCL))));
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getDocumentDate() != null) {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày... tháng... năm 20..");
                        }
                    }
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getReIssueDate() != null) {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP:
                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempCongbophuhopCL))));
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getDocumentDate() != null) {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getDocumentDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setDocumentDateStr("Ngày... tháng... năm 20..");
                        }
                    }
                    if (filesForm.getReIssueForm() != null) {
                        if (filesForm.getReIssueForm().getReIssueDate() != null) {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "dd") + " tháng " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "MM") + " năm " + DateTimeUtils.convertDateToString(filesForm.getReIssueForm().getReIssueDate(), "yyyy"));
                        } else {
                            filesForm.getReIssueForm().setReIssueDateStr("ngày... tháng... năm 20..");
                        }
                    }
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05:
//                    Hiepvv_Home Xem truoc cong van SDBS sau cong bo
                    Long preLong = getRequest().getParameter("isPre") == null ? 0L : Long.parseLong(getRequest().getParameter("isPre"));
                    if (preLong > 0L) {
                        String contentEdit;
                        String publishDate = "";
                        String announmentNo = "";
                        String proName = "";
                        String busiName = "";
                        String busiAdd = "";
                        String receiptNoOld = "";
                        String receiptNo = "0000";
                        String titleEdit;
                        if (filesForm.getContentsEditATTP() != null && !filesForm.getContentsEditATTP().isEmpty()) {
                            contentEdit = filesForm.getContentsEditATTP();
                        } else {
                            contentEdit = filesForm.getContentsEdit();
                        }
                        if (filesForm.getAnnouncement().getPublishDate() != null) {
                            publishDate = DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "dd")
                                    + " tháng " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "MM")
                                    + " năm " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "yyyy");
                        }
                        if (filesForm.getAnnouncement().getAnnouncementNo() != null) {
                            announmentNo = filesForm.getAnnouncement().getAnnouncementNo();
                        }
                        if (filesForm.getAnnouncement().getProductName() != null) {
                            proName = filesForm.getAnnouncement().getProductName();
                        }
                        if (filesForm.getAnnouncement().getBusinessAddress() != null) {
                            busiAdd = filesForm.getAnnouncement().getBusinessAddress();
                        }
                        if (filesForm.getAnnouncement().getBusinessName() != null) {
                            busiName = filesForm.getAnnouncement().getBusinessName();
                        }
                        String signedDate = "Hà Nội, ngày " + DateTimeUtils.convertDateToString(dateNow, "dd")
                                + " tháng " + DateTimeUtils.convertDateToString(dateNow, "MM")
                                + " năm " + DateTimeUtils.convertDateToString(dateNow, "yyyy");
                        /* binhnt fix sonar
//                        String receiptDeptName = "";
                        if (filesForm.getAnnouncementReceiptPaperForm() != null) {
                            receiptDeptName = filesForm.getAnnouncementReceiptPaperForm().getReceiptDeptName();
                        } else if (filesForm.getConfirmImportSatistPaperForm() != null) {
                            receiptDeptName = filesForm.getConfirmImportSatistPaperForm().getTestAgencyName();
                        }
                         */
                        if (filesForm.getFilesSourceID() != null
                                && filesForm.getFilesSourceID() > 0L) {
                            Files fboOld = fdhe.findById(filesForm.getFilesSourceID());
                            if (fboOld != null
                                    && fboOld.getAnnouncementReceiptPaperId() != null) {
                                AnnouncementReceiptPaperDAOHE arpDaohe = new AnnouncementReceiptPaperDAOHE();
                                AnnouncementReceiptPaper arpbo = arpDaohe.findById(fboOld.getAnnouncementReceiptPaperId());
                                if (arpbo != null
                                        && arpbo.getReceiptDate() != null
                                        && arpbo.getSignDate() != null
                                        && arpbo.getReceiptNo() != null) {
                                    receiptNoOld = arpbo.getReceiptNo();
                                    publishDate = " ngày " + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "dd")
                                            + " tháng " + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "MM") + " năm "
                                            + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "yyyy");
                                }
                            }
                        }
                        if (filesForm.getTitleEdit() != null) {
                            titleEdit = filesForm.getTitleEdit();
                        } else if (filesForm.getTitleEditATTP() != null) {
                            titleEdit = filesForm.getTitleEditATTP();
                        } else {
                            titleEdit = "sửa đổi bổ sung hồ sơ đã công bố";
                        }
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignsuadoisaucongbo))));
                        //Các biến truyền vào Công văn

                        wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
                        wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
                        wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");

//                        if (receiptDeptName == null || receiptDeptName.equals("Cục ATTP")) {
//                            wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
//                            wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
//                            wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");
//                        } else {
//                            wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${deptParent}");
//                            wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptName}");
//                            wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptNames}");
//                        }
                        wU.replacePlaceholder(wmp, contentEdit, "${contentEdit}");
                        wU.replacePlaceholder(wmp, publishDate, "${publishDate}");
                        wU.replacePlaceholder(wmp, announmentNo, "${announmentNo}");
                        wU.replacePlaceholder(wmp, proName, "${proName}");
                        wU.replacePlaceholder(wmp, busiAdd, "${busiAdd}");
                        wU.replacePlaceholder(wmp, busiName, "${busiName}");
                        wU.replacePlaceholder(wmp, signedDate, "${signDate}");
                        wU.replacePlaceholder(wmp, receiptNoOld, "${receiptNoOld}");
                        wU.replacePlaceholder(wmp, receiptNo, "${receiptNo}");
                        wU.replacePlaceholder(wmp, titleEdit, "${titleEdit}");
                        ProcessDAOHE pDaohe = new ProcessDAOHE();
                        Process pBo = pDaohe.getProcessByAction(filesForm.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, filesForm.getStatus(), Constants.FILE_STATUS.NEW_CREATE);
                        DepartmentDAOHE deptDaohe = new DepartmentDAOHE();
                        Department deptBo = deptDaohe.findBOById(pBo.getSendGroupId());
                        if (deptBo != null && deptBo.getDeptCode() != null) {
                            wU.replacePlaceholder(wmp, deptBo.getDeptCode(), "${deptCode}");
                        } else {
                            wU.replacePlaceholder(wmp, "SP", "${deptCode}");
                        }
                    } else {
                        wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSuadoisaucongbo))));
                    }
//                    End Hiepvv_Home
                    break;
                case Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR:
                    if (filesForm.getAnnouncement() != null) {
                        BusinessDAOHE busdaohe = new BusinessDAOHE();
                        Business busbo = busdaohe.findById(filesForm.getDeptId());
                        if (filesForm.getAnnouncement().getPublishDate() != null) {
                            if (busbo != null) {
                                filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince()
                                        + ", ngày " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "yyyy"));
                            } else {
                                filesForm.getAnnouncement().setSignDateStr("..., ngày "
                                        + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "dd")
                                        + " tháng " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "MM")
                                        + " năm " + DateTimeUtils.convertDateToString(filesForm.getAnnouncement().getPublishDate(), "yyyy"));
                            }
                        } else if (busbo != null) {
                            filesForm.getAnnouncement().setSignDateStr(busbo.getBusinessProvince() + ", ngày... tháng... năm 20..");
                        } else {
                            filesForm.getAnnouncement().setSignDateStr("..., ngày... tháng... năm 20..");
                        }
                    }

                    wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(DN_AnnounementReceiptPaper4Star))));
                    List lstProductOfFile = fdhe.getProductOfFile(fileId);
                    wU.replaceTable(wmp, 0, lstProductOfFile);
                    wU.replacePlaceholder(wmp, filesForm.getAnnouncement().getSignDateStr(), "${signDateStr}");
                    break;
                default:;
            }
            //binhnt update 260216
            if (filesForm.getDetailProduct() != null) {
                if (filesForm.getDetailProduct().getUseage() == null
                        || filesForm.getDetailProduct().getUseage().trim().length() == 0) {
                    filesForm.getDetailProduct().setUseage("Không có.");
                }
                if (filesForm.getDetailProduct().getObjectUse() == null
                        || filesForm.getDetailProduct().getObjectUse().trim().length() == 0) {
                    filesForm.getDetailProduct().setObjectUse("Không có.");
                }
                if (filesForm.getDetailProduct().getGuideline() == null
                        || filesForm.getDetailProduct().getGuideline().trim().length() == 0) {
                    filesForm.getDetailProduct().setGuideline("Không có.");
                }
                if (filesForm.getDetailProduct().getDateOfManufacture() != null) {
                    filesForm.getDetailProduct().setDateOfManufactureStr(DateTimeUtils.convertDateToString(filesForm.getDetailProduct().getDateOfManufacture(), "dd/MM/yyyy"));
                }
            }
            HashMap map = new HashMap();
            map.put("createForm", filesForm);
            wU.replacePlaceholder(wmp, map);
            String fullFile = wU.writePDFToStreamSignFileUsingPlugin(wmp, getResponse(), fileId, fileCode, filesForm.getQrCode(), Constants.TYPE_SIGN.CBDN, true, 1, true);
            //Hiepvv SDBS sau cong bo
            if (procedure != null
                    && procedure.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05)) {
                if (!"false".equals(fullFile)) {
//                    Copy file Công văn và các file đính kèm của hs SĐBS sau công bố vào hồ sơ gốc

                    resultMessage.add("1");
                    resultMessage.add("Xuất hồ sơ thành công");
                    resultMessage.add(fullFile);
                    jsonDataGrid.setItems(resultMessage);
                    return GRID_DATA;
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Lỗi trong quá trình Convert và lưu file PDF");
                    jsonDataGrid.setItems(resultMessage);
                    return GRID_DATA;
                }
            } else if (!"false".equals(fullFile)) {
                resultMessage.add("1");
                resultMessage.add("Xuất hồ sơ thành công");
                resultMessage.add(fullFile + ";");
                jsonDataGrid.setItems(resultMessage);
                return GRID_DATA;
            } else {
                resultMessage.add("3");
                resultMessage.add("Lỗi trong quá trình Convert và lưu file PDF");
                jsonDataGrid.setItems(resultMessage);
                return GRID_DATA;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Lỗi trong quá trình xử lý File PDF");
//            Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình xử lý File PDF: " + ex.getMessage());
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * Xem truoc cong van tra bo sung sau cong bo 16 08 01
     *
     * @param typeExport
     * @return
     */
    public String onExportEEAA() {
//        boolean result = exportDataEEAA("EX_TEMP");
        exportDataEEAA("EX_TEMP");
        return null;
    }

    public Boolean exportDataEEAA(String typeExport) {
        try {
            Date dateNow = getSysdate();
            Base64 decoder = new Base64();
            String strFileId = getRequest().getParameter("fileId");

//            String receiptDeptName = "";//fix sonar
//            String sendNo = "0";//fix sonar
            String strTitle = new String(decoder.decode(getRequest().getParameter("title").replace("_", "+").getBytes()), "UTF-8");
            String strContent = new String(decoder.decode(getRequest().getParameter("contents").replace("_", "+").getBytes()), "UTF-8");

            try {
                fileId = Long.parseLong(strFileId);
            } catch (NumberFormatException ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }

            WordExportUtils wU = new WordExportUtils();
//            String businessName = "";
            if (fileId == null) {
                throw new Exception("Không có hồ sơ");
            }
            FilesDAOHE fdhe = new FilesDAOHE();
            FilesForm form = fdhe.getFilesDetail(fileId);
            String fileCode = "";
            if (form != null) {
//                businessName = form.getBusinessName();
                form.setStaffRequest(strContent);
//                receiptDeptName = form.getAgencyName();//fix sonar
//                sendNo = fdhe.getNewSendNo(form.getAgencyId());//fix sonar
                fileCode = form.getFileCode();
            }

            WordprocessingMLPackage wmp;
//                String contentEdit = "";
            String publishDate = "";
            String announmentNo = "";
            String proName = "";
            String busiName = "";
            String busiAdd = "";
            String receiptNoOld = "";
            String receiptNo = "0000";
//                String titleEdit = "";                
            if (form.getAnnouncement().getPublishDate() != null) {
                publishDate = DateTimeUtils.convertDateToString(form.getAnnouncement().getPublishDate(), "dd")
                        + " tháng " + DateTimeUtils.convertDateToString(form.getAnnouncement().getPublishDate(), "MM")
                        + " năm " + DateTimeUtils.convertDateToString(form.getAnnouncement().getPublishDate(), "yyyy");
            }
            if (form.getAnnouncement().getAnnouncementNo() != null) {
                announmentNo = form.getAnnouncement().getAnnouncementNo();
            }
            if (form.getAnnouncement().getProductName() != null) {
                proName = form.getAnnouncement().getProductName();
            }
            if (form.getAnnouncement().getBusinessAddress() != null) {
                busiAdd = form.getAnnouncement().getBusinessAddress();
            }
            if (form.getAnnouncement().getBusinessName() != null) {
                busiName = form.getAnnouncement().getBusinessName();
            }
            String signedDate = "Hà Nội, ngày " + DateTimeUtils.convertDateToString(dateNow, "dd")
                    + " tháng " + DateTimeUtils.convertDateToString(dateNow, "MM")
                    + " năm " + DateTimeUtils.convertDateToString(dateNow, "yyyy");
            /* fix sonar
            if (form.getAnnouncementReceiptPaperForm() != null) {
                receiptDeptName = form.getAnnouncementReceiptPaperForm().getReceiptDeptName();
            } else if (form.getConfirmImportSatistPaperForm() != null) {
                receiptDeptName = form.getConfirmImportSatistPaperForm().getTestAgencyName();
            }
             */
            if (form.getFilesSourceID() != null
                    && form.getFilesSourceID() > 0L) {
                Files fboOld = fdhe.findById(form.getFilesSourceID());
                if (fboOld != null
                        && fboOld.getAnnouncementReceiptPaperId() != null) {
                    AnnouncementReceiptPaperDAOHE arpDaohe = new AnnouncementReceiptPaperDAOHE();
                    AnnouncementReceiptPaper arpbo = arpDaohe.findById(fboOld.getAnnouncementReceiptPaperId());
                    if (arpbo != null
                            && arpbo.getReceiptDate() != null
                            && arpbo.getSignDate() != null
                            && arpbo.getReceiptNo() != null) {
                        receiptNoOld = arpbo.getReceiptNo();
                        publishDate = " ngày " + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "dd")
                                + " tháng " + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "MM") + " năm "
                                + DateTimeUtils.convertDateToString(arpbo.getSignDate(), "yyyy");
                    }
                }
            }
//                if (form.getTitleEdit() != null) {
//                    titleEdit = form.getTitleEdit();
//                } else if (form.getTitleEditATTP() != null) {
//                    titleEdit = form.getTitleEditATTP();
//                } else {
//                    titleEdit = "sửa đổi bổ sung hồ sơ đã công bố";
//                }
            wmp = WordprocessingMLPackage.load(new FileInputStream(new File(getRequest().getRealPath(tempSignsuadoisaucongbo))));
            //Các biến truyền vào Công văn
            wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
            wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
            wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");

//            if (receiptDeptName == null || receiptDeptName.equals("Cục ATTP")) {
//                wU.replacePlaceholder(wmp, "BỘ Y TẾ", "${deptParent}");
//                wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${receiptDeptName}");
//                wU.replacePlaceholder(wmp, "Cục An toàn thực phẩm", "${receiptDeptNames}");
//            } else {
//                wU.replacePlaceholder(wmp, "CỤC AN TOÀN THỰC PHẨM", "${deptParent}");
//                wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptName}");
//                wU.replacePlaceholder(wmp, receiptDeptName, "${receiptDeptNames}");
//            }
            wU.replacePlaceholder(wmp, strContent, "${contentEdit}");
            wU.replacePlaceholder(wmp, publishDate, "${publishDate}");
            wU.replacePlaceholder(wmp, announmentNo, "${announmentNo}");
            wU.replacePlaceholder(wmp, proName, "${proName}");
            wU.replacePlaceholder(wmp, busiAdd, "${busiAdd}");
            wU.replacePlaceholder(wmp, busiName, "${busiName}");
            wU.replacePlaceholder(wmp, signedDate, "${signDate}");
            wU.replacePlaceholder(wmp, receiptNoOld, "${receiptNoOld}");
            wU.replacePlaceholder(wmp, receiptNo, "${receiptNo}");
            wU.replacePlaceholder(wmp, strTitle, "${titleEdit}");

            ProcessDAOHE pDaohe = new ProcessDAOHE();
            Process pBo = pDaohe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, form.getStatus(), Constants.FILE_STATUS.NEW_CREATE);
            DepartmentDAOHE deptDaohe = new DepartmentDAOHE();
            Department deptBo = deptDaohe.findBOById(pBo.getSendGroupId());
            if (deptBo != null && deptBo.getDeptCode() != null) {
                wU.replacePlaceholder(wmp, deptBo.getDeptCode(), "${deptCode}");
            } else {
                wU.replacePlaceholder(wmp, "SP", "${deptCode}");
            }

            HashMap map = new HashMap();
            map.put("createForm", form);
            wU.replacePlaceholder(wmp, map);
            if ("EX_TEMP".equals(typeExport)) {
//                wU.createFooterPart(wmp, "MA HO SO: " + fileCode);
                wU.writePDF4Preview(wmp, getResponse(), fileCode);
                return true;
            } else if ("EX_SIGN".equals(typeExport)) {
                return wU.writePDFToStreamSign(wmp, getResponse(), fileId, fileCode, null, "CVBS", false, 0, true);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return false;
    }
}
