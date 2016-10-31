/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.common.util;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author @since since_text
 * @version 1.0 update binhnt53
 */
public final class Constants {

    public static final long COMBOBOX_HEADER_VALUE = -1l;
    public static final String COMBOBOX_HEADER_VALUE_STRING = "-1";
    public static final String COMBOBOX_HEADER_TEXT = "-- Chọn --";
    public static final String COMBOBOX_HEADER_TEXT_SELECT = "-- Chọn --";
    public static final ComboBoxItem cbbItemSearch = new ComboBoxItem(COMBOBOX_HEADER_VALUE, COMBOBOX_HEADER_TEXT);
    public static final ComboBoxItem cbbItemCreate = new ComboBoxItem(COMBOBOX_HEADER_VALUE, COMBOBOX_HEADER_TEXT_SELECT);
    public static final String LM_NAME_FIELD = "name";
    public static final long default_profile_id = 22L;
    public static final int tree_wrap_text_length = 20;
    public static final long max_semesester_number = 15L;
    public static final String VSA_USER_TOKEN = "vsaUserToken";
    public static final String prefix_outsite_office = "9999";

    public static final long COMBOBOX_REPOSITORY_HEADER_VALUE = -1;
    public static final String COMBOBOX_REPOSITORY_HEADER_TEXT = "-- Chọn --";

    public interface PRODUCT_TARGET_TYPE {//havm2 add 150128

        public static final Long BIO = 1l;
        public static final Long HEAVY_METAL = 2l;
        public static final Long CHEMICAL = 3l;

    }

    public interface FILE_STATUS {
//last status 33
        //vai tro doanh nghiep

        public static final Long NEW_CREATE = 0L;//Mới tao, chua nop
        public static final Long NEW = 1L;//Mới nộp - chờ tiếp nhận hồ sơ
        public static final Long RECEIVED_REJECT = 21L;//HS đã bị VT từ chối, yêu cầu BS
        public static final Long RECEIVED_REJECT_TO_ADD = 33L;//HS đã bị VT từ chối tiếp nhận SDBS - 150206 u binhnt53
        public static final Long EVALUATED_TO_ADD = 20L;//Đã gửi công văn yêu cầu SĐBS
        public static final Long NEW_TO_ADD = 18L;//Mới nộp - chờ tiếp nhận SĐBS
        public static final Long ALERT_COMPARISON = 23L;//Chờ đối chiếu hồ sơ gốc, đã thông báo đối chiếu
        //vai tro ke toan
        public static final Long CONFIRM_FEE_EVALUATE = 31L;//Hồ sơ kế toán đã xác nhận phí thẩm định
        public static final Long CONFIRM_FEE_APPROVED = 32L;//Hồ sơ kế toán đã xác nhận lệ phí cấp số.
        //vai tro van thu
        public static final Long RECEIVED = 14L;//Đã tiếp nhận hồ sơ
        public static final Long RECEIVED_TO_ADD = 17L;//Đã tiếp nhận hồ sơ SĐBS
        public static final Long GIVE_BACK = 22L;//Đã trả hồ sơ/HS đã trả lại cho doanh nghiệp/ đã trả bản công bố
        //vai tro chuyen vien
        public static final Long ASSIGNED = 3L;//Đã phân công xử lý
        public static final Long EVALUATED = 4L;//Đã thẩm định
        public static final Long FEDBACK_TO_ADD = 7L;//Chuyên viên kết luận yêu cầu SDBS
        public static final Long FEDBACK_TO_EVALUATE = 8L;//Đã trả lại để thẩm định lại
        public static final Long EVALUATE_TO_ADD = 28L;//Da soan du thao sua doi bo sung
        //vai tro lanh dao phong
        public static final Long REVIEWED = 5L;//Đã xem xét kết quả
        public static final Long FEDBACK_TO_REVIEW = 9L;//Đã trả lại để xem xét lại
        public static final Long REVIEWED_TO_ADD = 19L;//Đã xem xét yêu cầu SĐBS
        public static final Long REVIEW_TO_ADD = 26L;//đã xem xét nội dung cv sđbs
        //vai tro lanh dao cuc
        public static final Long APPROVED = 6L;//Đã phê duyệt kết quả
        public static final Long APPROVE_TO_ADD = 27L;//đã phê duyệt nội dung thông báo SDBS
        //vai tro cuc truong
        public static final Long REVIEW_TO_BOSS = 29L;//Hồ sơ trình cục trưởng xem xét
        public static final Long APPROVE_TO_LEADER = 30L;//Hồ sơ cục trưởng đã quyết định
        //don't using
        public static final Long PROPOSED = 2L;//Đã được đề xuất xử lý
        public static final Long LICENSING = 10L;//Đã tạo giấy phép
        public static final Long SIGNING = 11L;//
        public static final Long SIGNED = 12L;//Đã ký công bố
        public static final Long REJECT = 13L;//Từ chối kí
        public static final Long COMPARED = 15L;//Đã đối chiếu thành công
        public static final Long COMPARED_FAIL = 16L;//Đã đối chiếu có sai lệch
        public static final Long REVIEW_COMPARISON = 24L;//Đã xem xét đối chiếu
        public static final Long REVIEW_COMPARISON_FAIL = 25L;//Đã trả lại để xem xét đối chiếu lại
        //!don't using
    }

    /**
     * Status 0: delete;1: active
     */
    public interface Status {

        public static final long ACTIVE = 1l;
        public static final long INACTIVE = 0l;
        public static final long CHANGED = -1l;
    }

    public interface FEE_STATUS {

        public static final long DA_TU_CHOI = 2L;
        public static final long DA_XAC_NHAN = 1L;
        public static final long CHO_XU_LY_TIEN_MAT = 4L;
        public static final long CHO_XU_LY_CHUYEN_KHOAN = 3L;
        public static final long CHO_XU_LY_ONLINE = 5L;
    }

    public interface FEE_TYPE {

        public static final long LE_PHI = 1L;
        public static final long PHI_THAM_DINH = 2L;
        public static final String KS4SLP = "4SLP";
        public static final String KS4STD = "4STD";
        public static final String TPK = "TPK";
        public static final String TPCN = "TPCN";
        public static final String TPDB = "TPDB";
        public static final String TL = "TL";
    }

    public interface SEARCH_TYPE {

        public static final long DTCXNP_L = -77L;//da tu choi xac nhan phi
    }

    /**
     * type of subject.major choosed in popup
     */
    public interface ChooserType {

        public static final String SUBJECT = "0";
        public static final String MAJOR = "1";
    }

    public interface Report {

        public static final String TOPIC_TEMPLATE_PATH = "/WEB-INF/template/report/knowledge/DSDeTai.xls";
        public static final String TOPIC_EXPORT_PATH = "/share/templateReport/knowledge/DSDeTai";
        public static final String BOOK_TEMPLATE_PATH = "/WEB-INF/template/report/knowledge/DSSach.xls";
        public static final String BOOK_EXPORT_PATH = "/share/templateReport/knowledge/DSSach";
        public static final String WORKSHOP_TEMPLATE_PATH = "/WEB-INF/template/report/knowledge/DSHoiThao.xls";
        public static final String WORKSHOP_EXPORT_PATH = "/share/templateReport/knowledge/DSHoiThao";
    }

    public interface Type {

        public static final long TOPIC = 0l;
        public static final long BOOK = 1l;
        public static final long WORKSHOP = 2l;
        public static final String LEAD = "Lãnh đạo";
        public static final String CLERICAL = "Văn thư";
        public static final String EXPERT = "Chuyên viên";
        public static final String OTHER = "Khác";
    }

    public interface CATEGORY_TYPE {

        public static final String SECURITY_TYPE = "VOMVB"; // Do mat cua van ban
        public static final String DOCUMENT_TYPE = "VOLVB"; // Loai van ban
        public static final String PRIORITY_TYPE = "VOKVB";  // Do khan
        public static final String ACCESS_TYPE = "TC"; // Muc do truy cap
        public static final String IMPORTANT_TYPE = "IPT"; // Muc do quan trong
        public static final String SEND_TYPE = "VOPTN"; // Phuong thuc gui
        public static final String FILE = "TTHC"; // Loai ho so
        public static final String PROVINCE = "PROVINCE";
        public static final String DVI = "DVI";
        public static final String TTHC = "TTHC";
        public static final String NATIONAL = "NATIONAL";
        public static final String STATUS = "STATUS";
        public static final String TPCN = "TPCN";//thuc pham chuc nang
        public static final String DBT = "DBT";//thuc pham dac biet
        public static final String TPK = "TPK";//thuc pham khac
        public static final String CLT = "CLT";//cap lai thuc pham thuong
        public static final String CLCN = "CLCN";//cap lai thuc pham thuong
        public static final String BBP = "BBP";//Bao bi
        public static final String CBSP_SERVICE = "CBSP_SERVICE";
        public static final String CBSP_OBJECT = "HS_CBSP_KIT";
    }

    public interface DOCUMENT_STATUS {

        public static final Long DRAFT = 0L;                // du thao
        public static final Long PUBLISH = 1L;              // da ban hanh
        public static final Long INSTRUCTION = 2L;          // da xin y kien lanh dao
        public static final Long RETURN = 3L;               // Lãnh đạo trả lại
        public static final Long APPROVAL = 4L;             // "Đã phê duyệt va gui xu ly tiep";
        public static final Long SEND_COORDINATE = 5L;      // "Đã gửi phối hợp";
        public static final Long RECEIVE_COORDINATE = 6L;   // "Đã nhận ý kiến phối hợp";
        public static final Long ASSIGN_NEXT = 7L;          // Da chuyen tiep
        public static final Long APPROVAL_FORM = 8L;        // Da phe duyet ve the thuc
        public static final Long JUST_REFERENCE = 11L;       // Cong bo van ban
        // van ban den
        public static final Long NOT_SEND = 0L;             // chua gui
        public static final Long SEND = 1L;                 // da gui
        //Trang thai dang String
        public static final String DRAFT_STR = "Dự thảo";
        public static final String PUBLISH_STR = "Đã ban hành";
        public static final String INSTRUCTION_STR = "Đã gửi lãnh đạo";
        public static final String RETURN_STR = "Lãnh đạo trả lại";
        public static final String APPROVAL_STR = "Đã phê duyệt";
        public static final String SEND_COORDINATE_STR = "Đã gửi phối hợp";
        public static final String RECEIVE_COORDINATE_STR = "Đã nhận ý kiến phối hợp";
    }

    public interface DOCUMENT_STORE {

        public static final Long STORE_NEXT = 1L;       // Da chuyen luu tru
        public static final Long STORE_RECORD = 2L;   // Da luu tru ho so
        public static final Long STORAGE = 3L;          // Da luu tru
    }

    public interface ROLE {

        public static final Long EXPERT = 0L;
        public static final Long CLERICAL = 1L;
        public static final Long LEAD = 2L;
        public static final Long ALL = 3L;
        public static final String LEAD_POSITION = "LD";
    }

    public interface MONITOR {

        public static final String VBDi_TK = "VBDi_TK"; // MH TK vbdi
        public static final String VBDi_VS = "VBDi_VS"; // MH vao so VBDi
        public static final String VBDi_CBH = "VBDi_CBH"; // MH VB cho ban hanh
        public static final String VBDi_DVXL = "VBDi_DVXL"; // MH VB don vi cho xy ly
        public static final String VBDi_VBNB = "VBDi_VBNB"; // MH VB den noi bo
        public static final String VBDi_DT = "VBDi_DT"; // MH DS VB du thao
        public static final String VBDi_DTA = "VBDi_DTA"; // MH DS VB du thao da duoc Approve
        public static final String VBDi_TDHB = "VBDi_TDHB"; // MH DS VB theo doi hoi bao
        public static final String VBDi_CXL = "VBDi_CXL"; // MH VB cho LD xu ly
        public static final String VBDi_DXL = "VBDi_DXL"; // MH VB LD da xu ly
        public static final String VBDi_DT_HSCV = "VBDi_DT_HSCV"; // MH DS VB du thao cua HSCV
        public static final String VBDi_BH_HSCV = "VBDi_BH_HSCV"; // MH DS VB da ban hanh cua HSCV
        public static final String VBDi_PD_BH_HSCV = "VBDi_PD_BH_HSCV"; // DS VB chua phe duyet hoac chua ban hanh
    }

    public interface PROCESS_STATUS {

        public static Long NEW = 0L; // moi den
        public static Long BOOKED = 1L; // da luu so don vi
        public static Long INSTRUCTION = 2L; // da chuyen xin y kien chi dao
        public static Long ASSIGNED = 3L; // da phan cong cho chuyen vien
        public static Long FINISH = 4L; // hoan thanh
        public static Long RETURN = 5L; // tra lai
        public static Long APPROVED = 6L; // da phe duyet
        public static Long ASSIGN_NEXT = 7L; // da chuyen tiep
        public static Long DOING = 8L; // dang xu ly
        public static Long DID = 9L; // da xu ly
        public static Long PUBLISHED = 10L; // da ban hanh
        public static final Long PROPOSED = 3L;//Đề xuất
    }

    public interface PROCESS_TYPE {

        public static Long MAIN = 1L; // Xu ly chinh
        public static Long COOPERATE = 0L; // dong xu ly
        public static Long PROPOSE = 2L; // de xuat
        public static Long REFERENCE = 3L; // tham khao
        public static Long COMMENT = 4L;
    }

    public interface RECEIVE_USER_TYPE {

        public static Long OFFICE_LEADER = 1l; // lanh dao van phong
        public static Long LEADER = 2l; // lanh dao don vi
        public static Long OFFICE_PROCESS = 3l; // don vi xu ly
        public static Long MONITER = 4l; // Phong xu ly(Phong giam sat xu ly)
    }

    public interface OBJECT_TYPE {

        public static Long DOCUMENT_RECEIVE = 1l; // Van ban den
        public static Long DOCUMENT_PUBLISH = 2l; // Van ban di
        public static Long DOCUMENT_DIRECTION = 3l; // Van ban dieu hanh
        public static Long PROFILE_WORK = 4l; // ho so cong viec
        public static Long PROFILE_STORE = 5l; // Ho so luu tru
        public static Long FORM = 6l; // Phieu yeu cau
        public static Long TECHNICAL_STANDARD_ATTACH = 23L;
        public static Long FILES = 30L;  // Ho so
        public static Long FILES_LABEL = 17L;  // Nhan dinh kem
    }

    public interface RECORDS_TYPE {

        public static Long REGISTER_RECORDS = 4l; // ho so dang ky
        public static Long RECORDS_ADVERTISE = 1l; // So hs qcao
        public static Long RECORDS_PUBLIC = 2l; // So hs cong bo
        public static Long RECORDS_DDK = 3l; // So hs DDK
    }

    public interface POSITION {

        //BTP
        public static String LEAD_CODE = "BTP_LD_P"; // Lanh dao
        public static String LEAD_OFFICE_CODE = "BTP_LD_VP"; // Lanh dao van phong
        public static String LEADER_CODE = "BTP_LD";
        public static String STAFF_CODE = "POS8"; // Nhan vien
        //CUC ATTP
        public static String LEAD_SMS_CODE = "^BTP_LD(.*)SMS$";
        public static String SMS_CODE = "_SMS";
        public static String LEADER_OF_STAFF_T = "LDTP";//lanh dao don vi attp - 140915 binhnt53
        public static String LEADER_OF_STAFF_P = "LDPP";//lanh dao don vi attp - 140915 binhnt53
        public static String LEADER_CT = "CT";//cuc truong - 140915 binhnt53
        public static String LEADER_PCT = "PCT";//pho cuc truong attp - 140915 binhnt53
        public static String VFA_CV = "VFA_CV";//chuyen vien attp - 150401 binhnt53
        public static String GDTT = "GDTT";//giam doc trung tam - 150911 binhnt53
        public static String PGDTT = "PGDTT";//pho giam doc trung tam - 081015 binhnt53
        public static String VT = "VT";//van thu
        public static String NV = "NV";//nhan vien

        public static String LST_CV = "NV;VFA_CV;VT";
        public static String LST_LDP_P = "LDPP;PGDTT";
        public static String LST_LDP_T = "GDTT;LDTP";
    }

    public interface ROLES {

        public static String LEAD_ROLE = "voffice_cvp";         // Chanh van phong
        public static String LEAD_OFFICE_ROLE = "voffice_ld";   // Lanh dao
        public static String STAFF_ROLE = "voffice_cv";         // Chuyen vien
        public static String CLERICAL_ROLE = "voffice_vt";      // Van thu
        public static String CBLT_CQ = "voffice_cblt_cq"; // Can bo luu tru co quan
        public static String LEAD_UNIT = "voffice_lddv"; // lanh dao don vi
//CUC ATTP
        public static String LEAD_UNIT_DEPUTY_ROLE = "LDDV_PP";// Pho phong
        public static String LEAD_MONITOR_ROLE = "voffice_ld_pc";// Cuc Pho
        public static String DIRECTOR_ROLE = "voffice_ld";// Cuc truong
        public static String ADMIN = "QTHT";//QUAN TRI HE THONG
        public static String LEADER_OF_STAFF_T = "LDTP";//lanh dao don vi attp
        public static String LEADER_OF_STAFF_P = "LDPP";//lanh dao don vi attp
        public static String LEADER_CT = "CT";//cuc truong
        public static String LEADER_PCT = "PCT";//pho cuc truong attp
        public static String CV = "CV";//chuyen vien attp.
        public static String VT = "VT";//chuyen vien attp
        public static String GDTT = "GDTT";//giam doc trung tam
        public static String PGDTT = "PGDTT";//pho giam doc trung tam
        public static String PCHS = "PHANCONG_HOSO";//phan cong ho so
    }

    public interface DEPARTMENT {

        public static Long BTP_ID = 52l; // id cua bo tu phap
        public static Long DEPT_TYPE_PHONG = 8l; //Phong cua don vi
        public static String DEPT_TYPE_CODE_PHONG = "Văn phòng"; // ma cua phong
    }

    public interface CommentType {

        public static Long APPROVED = 1l; // Phe duyet
        public static Long RETURN = 2l; // tra lai
        public static Long COORDINATE = 3l; // phoi hop
        public static Long INSTRUCTION = 4l; // gui xin y kien lanh dao
    }

    public interface NOTIFY_TYPE {

        public static Long BY_RECEIVE_DOC = 1l; // Hồi báo bằng VB đến
        public static Long OTHER = 0l; // Hồi báo bằng dạng khác
    }

    public interface NOTIFY_STATUS {

        public static Long ON_TIME = 1l; // Đã hồi báo (đúng hạn)
        public static Long OUT_TIME = 0l; // Đã hồi báo (quá thời hạn)
        public static String ON_TIME_STR = "Đã hồi báo (đúng hạn)";
        public static String OUT_TIME_STR = "Đã hồi báo (quá thời hạn)";
        public static Long ACTIVE = 1L; //Dang theo doi hoi bao
        public static Long INACTIVE = 2L; //Ket thuc theo doi hoi bao
    }

    public interface ROLE_STAFF {

        public static final String ROLE_PUBLISH = "ROLE_STAFF_PUBLISH";
        public static final String ROLE_ASSIGN = "ROLE_STAFF_ASSIGN";
    }

    public interface DEPT_TYPE {

        public static final List<Long> PHONG_BAN = Arrays.asList(8L, 11L);
    }

    public interface FILE_DESCRIPTION {

        public static final String ANNOUNCEMENT_FILE01 = "announcementFile01";
        public static final String ANNOUNCEMENT_FILE03 = "announcementFile03";
        public static final String CONFIRM_NORMAL_IMP = "confirmNormalImport";
        public static final String CONFIRM_FUNC_IMP = "confirmFuncImport";
        public static final String CONFIRM_FUNC_VN = "confirmFuncVN";
        public static final String CONFIRM_NORMAL_VN = "confirmNormalVN";
        public static final String CONFIRM_SATISFACTORY = "confirmSatisfactory";

        public static final String ANNOUNCEMENT_FILE01_TL = "annoucementTL01";
        public static final String ANNOUNCEMENT_FILE03_TL = "annoucementTL03";

        public static final String RE_ANNOUNCEMENT = "reAnnouncement";
        public static final String RE_CONFIRM_FUNC_IMP = "reConfirmFuncImport";
        public static final String REC_CONFIRM_NORMAL_IMP = "reConfirmNormalImp";
        public static final String RE_CONFIRM_FUNC_VN = "reConfirmFuncVN";
        public static final String RE_CONFIRM_NORMAL_VN = "reConfirmNormalVN";
        
        public static final Long  RE_ANNOUNCEMENT_ID = 66741L;
        public static final Long RE_CONFIRM_FUNC_IMP_ID = 66747L;
        public static final Long REC_CONFIRM_NORMAL_IMP_ID = 66743L;
        public static final Long RE_CONFIRM_FUNC_VN_ID = 66749L;
        public static final Long RE_CONFIRM_NORMAL_VN_ID = 66745L;
        
        
        

        public static final String CONFIRM_FUNC_IMP_TL = "confirmTL";
        //hiepvv SDBS sau cong bo
        public static final String ANNOUNCEMENT_FILE05 = "announcementFile05";
        public static final String ANNOUNCEMENT_FILE05_PAPER = "announcementFile05Paper";
        public static final String ANNOUNCEMENT_4STAR = "announcement4star";

        public static final String ANNOUNCEMENT_TL01 = "annoucementTL01";
        public static final String ANNOUNCEMENT_TL03 = "annoucementTL03";
        public static final String CONFIRMTL = "confirmTL";

    }

    public interface RECEIVED_STATUS {

        public static final Long IS_FEE_ACCEPT = 1L;//đã nộp đầy đủ phí hồ sơ
        public static final Long IS_FEE_ACCEPT_NOT_ENOUGH = 0L;//đã nộp chưa đủ hồ sơ
        public static final Long IS_FEE_DENY = -1L;// chưa nộp phí hồ sơ
    }

    public interface ACTIVE_STATUS {

        public static final Long ACTIVE = 1L;//đã nộp đầy đủ phí hồ sơ
        public static final Long DEACTIVE = 0L;//đã nộp chưa đủ hồ sơ
        public static final Long DELETED = -1L;// chưa nộp phí hồ sơ
    }

    public interface VIEW_TYPE {

        public static final Long BUSINESS = 0L;//doanh nghiệp
        public static final Long CLERICAL = 1L;// văn thư
        public static final Long LEADER_DEPT = 2L;// lãnh đạo đơn vị
        public static final Long LEADER = 3L;// lãnh đạo cục
        public static final Long STAFF = 4L;// chuyên viên
    }

    public interface FILE_TYPE {

        public static final Long ATTACH_OF_FILE = 0L;//doanh nghiệp
        public static final Long ATTACH_OF_USERATTACHS = 24L;// văn thư
    }

    public interface COMMENT_TYPE {

        public static Long CHAT = -1l; // Phe duyet
        public static Long BUSINESS_CHAT = 0l; // Phe duyet
        public static Long LEGAL = 1l; //Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)
        public static Long FOOD_SAFETY_QUALITY = 2l; //Về chỉ tiêu chất lượng an toàn thực phẩm
        public static Long EFFECT_UTILITY = 3l; //Về cơ chế tác dụng và công dụng
        public static Long STANDARD = 4l; //

    }

    public interface REQUEST_COMMENT_TYPE {

        public static final String TBDC = "TBDC";//thong bao doi chieu
        public static final String TBSDBS = "TBSDBS";//thong bao sua doi bo sung
        public static final String TCT = "TCT";//trinh cuc truongs
        public static final String YK = "YK";//y kien
        public static final String TD = "TD";//tham dinh
        public static final String TPLN = "TPLN";//truong phong luu nhap
    }

    public interface FLAG_SEND_SMSEMAIL {

        public static final String FLAG_SMS = "0";
        public static final String FLAG_EMAIL = "1";
    }

    public interface TYPE_DATETIME {

        public static final String DAY = "D";
        public static final String WEEK = "W";
        public static final String MONTH = "M";
        public static final String YEAR = "Y";
        public static final String QUARTER = "Q";
    }

    //hieptq update 090715
    public interface PRICE {

        public static final Long TPCN = 1500000L;
        public static final Long TPT = 500000L;
    }

    //hieptq update 090715
    public interface KEYPAY {

        public static final String KEYPAY = "KEYPAY";
    }

    public interface EFFECTIVEDATE {

        public static final Long FIVE = 5L;
        public static final Long THREE = 3L;
    }

    public interface TYPE_SIGN {//loại hồ sơ ký số

        public static final String PDHS = "PDHS";//là phê duyệt hồ sơ đat
        public static final String SDBS = "SDBS";//hồ sơ sđbs
        public static final String CBDN = "CBDN";//bản công bố của dn upload lên

        public static final Long LD = 1L;//lãnh đạo cục ký
        public static final Long VT = 2L;//văn thư ký
        public static final Long DN = 3L;//doanh nghiệp ký số

    }

    public interface ATTACH_OBJECT_TYPE {//loại file đính kèm.

        public static final Long LABEL = 17L;//là nhãn sp
        public static final Long FILES = 30L;//là tệp đính kèm bình thường
        public static final Long CBDN = 60L;//là bản công bố của doanh nghiệp
        public static final Long CB_FULL = 40L;//là bản công bố full
        public static final Long CB_PUBLIC = 41L;//là bản công bố đơn

    }

   public interface TEMP {
        public static final String FEATURE_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
        public static final String FEATURE_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    }
   
   public static String NSW_FUNCTION(Long k) {
		if (k < 10L) {
			return "0" + k.toString();
		}
		return k.toString();
	}

	public static String NSW_TYPE(Long k) {
		if (k < 10L) {
			return "0" + k.toString();
		}
		return k.toString();
	}
}
