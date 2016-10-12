/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.common.util;

/**
 *
 * @author MSS_TULN
 */
public final class CategoryConstant {


    protected static final String[] COMMENT_TYPE=new String[]{
        "Nhiệm kỳ, chức vụ",
        "Nhận xét hàng năm",
        "Quy hoạch, bổ nhiệm",
	"Ngày tháng năm"
    };

    /**
     * QUẢN LÝ DANH MỤC THÔNG TIN CÁ NHÂN 
     */
    public static final String ETHNIC_GROUP_TYPE = "A01" ; // Dan toc
    public static final String ETHNIC_GROUP_TYPE_TEXT = "Dân tộc" ; // Dan toc
    
    public static final String RELIGION_TYPE = "A02" ;    // Ton Giao
    public static final String RELIGION_TYPE_TEXT = "Tôn giáo" ;    // Ton Giao

    public static final String POSITION = "A03" ;    // Chuc vu
    public static final String POSITION_TEXT = "Chức vụ" ;    // Chuc vu

    public static final String ORGAN_JOIN_TYPE = "A04";   // Hinh thuc vao co quan
    public static final String ORGAN_JOIN_TYPE_TEXT = "Hình thức vào cơ quan";   // Hinh thuc vao co quan

    public static final String STAFF_OBJECT = "A05";     // Doi tuong can bo
    public static final String STAFF_OBJECT_TEXT = "Đối tượng cán bộ";     // Doi tuong can bo

    public static final String COMMON_EDUCATION_TYPE = "A06";   // Trinh do giao duc pho thong
    public static final String COMMON_EDUCATION_TYPE_TEXT = "Trình độ giáo dục phổ thông";   // Trinh do giao duc pho thong

    public static final String HIGHEST_PROFESSION_TYPE = "A07";   // Trinh do chuyen mon
    public static final String HIGHEST_PROFESSION_TYPE_TEXT = "Trình độ chuyên môn";   // Trinh do chuyen mon

    public static final String POLITICS_ARGUE_TYPE = "A08";    // Ly luan chinh tri
    public static final String POLITICS_ARGUE_TYPE_TEXT = "Lý luận chính trị";    // Ly luan chinh tri

    public static final String STATE_MANAGE_TYPE = "A09";    // Quan ly nha nuoc
    public static final String STATE_MANAGE_TYPE_TEXT = "Quản lý nhà nước";    // Quan ly nha nuoc

    public static final String HIGHEST_HONOUR_TYPE = "A10";    // Danh hieu phong tang
    public static final String HIGHEST_HONOUR_TYPE_TEXT = "Danh hiệu phong tặng";    // Danh hieu phong tang

    public static final String POLICY_FAMILY_TYPE = "A11";    // Dien chinh sach
    public static final String POLICY_FAMILY_TYPE_TEXT = "Diện chính sách";    // Dien chinh sach

    public static final String PROFILE_STATUS = "A12";    // Trang thai ho so
    public static final String PROFILE_STATUS_TEXT = "Trạng thái hồ sơ";    // Trang thai ho so

    public static final String WORKING_FIELD = "A13";    // Linh vuc hoat dong
    public static final String WORKING_FIELD_TEXT = "Lĩnh vực hoạt động";    // Linh vuc hoat dong

    public static final String RELATION_TYPE = "A14";    // Quan he gia dinh
    public static final String RELATION_TYPE_TEXT = "Quan hệ gia đình";    // Quan he gia dinh

    public static final String FAMILY_TYPE = "A15";    // Thanh phan gia dinh
    public static final String FAMILY_TYPE_TEXT = "Thành phần gia đình";    // Thanh phan gia dinh

    public static final String RANK_TYPE = "A16";    // Quan ham
    public static final String RANK_TYPE_TEXT = "Quân hàm";    // Quan ham

    public static final String HONOUR_TYPE = "A17";    // Danh hieu thi dua
    public static final String HONOUR_TYPE_TEXT = "Danh hiệu thi đua";    // Danh hieu thi dua

    public static final String REWARD_FORM_TYPE = "A18";   // Hinh thuc khen thuong
    public static final String REWARD_FORM_TYPE_TEXT = "Hình thức khen thưởng";   // Hinh thuc khen thuong

    public static final String DISCIPLINE_FORM_TYPE = "A19";   // Hinh thuc kỷ luật
    public static final String DISCIPLINE_FORM_TYPE_TEXT = "Hình thức kỷ luật";   // Hinh thuc kỷ luật

    /**
     * QUẢN LÝ DANH MỤC Đào tạo
     */
    public static final String STUDY_FORM_TYPE = "B01";   // Hinh thuc dao tao
    public static final String STUDY_FORM_TYPE_TEXT = "Hình thức đào tạo";

    public static final String TRAINING_DEGREE_TYPE = "B02"; // Van bang, chung chi
    public static final String TRAINING_DEGREE_TYPE_TEXT = "Văn bằng chứng chỉ";

    public static final String TRAINING_PROFESSION_TYPE = "B03"; // Chuyen nganh dao tao
    public static final String TRAINING_PROFESSION_TYPE_TEXT = "Chuyên ngành đào tạo";

    public static final String TRAINING_TYPE = "B04"; // Loai hinh dao tao
    public static final String TRAINING_TYPE_TEXT = "Loại hình đào tạo";

    public static final String COMPUTER_LEVEL_TYPE = "B05"; // Tin hoc
    public static final String COMPUTER_LEVEL_TYPE_TEXT = "Tin học";

    public static final String FOREIGN_LANGUAGE_TYPE = "B06"; // Ngoai ngu
    public static final String FOREIGN_LANGUAGE_TYPE_TEXT = "Ngoại ngữ";
    
    public static final String RESULT_TYPE = "B07"; // Ket qua dao tao
    public static final String RESULT_TYPE_TEXT = "Kết quả đào tạo";

    public static final String FOREIGN_LANGUAGE_LEVEL_TYPE = "B08"; //
    public static final String FOREIGN_LANGUAGE_LEVEL_TEXT = "Trình độ ngoại ngữ";

    public static final String TRAINING_CONTENS_TYPE = "B09"; //
    public static final String TRAINING_CONTENS_TYPE_TEXT = "Nội dung đào tạo";

    public static final String TRAINING_LEVEL_TYPE = "B10"; //
    public static final String TRAINING_LEVEL_TYPE_TEXT = "Trình độ đào tạo";
    /**
     * QUẢN LÝ DANH MỤC Đơn vị hành chính
     */
    public static final String PROVINCE = "C01"; // Tinh
    public static final String PROVINCE_TEXT = "Đơn vị hành chính";


    /**
     * QUẢN LÝ DANH MỤC Tổ chức hành nghề
     */
    public static final String LAWYER_GROUP = "D01"; // Doan luat su

    public static final String LAWYER_FEDERATION = "D02"; // Lien doan luat su

    public static final String WORK_ORGAN = "D03"; // To chuc hanh nghe
    /**
     * QUẢN LÝ DANH MỤC Ngạch lương
     */
    public static final String GRADE_SALARY = "categoryGradeSalary"; // Bac luong
    public static final String GRADE_SALARY_TEXT = "Bậc lương";
    public static final String SCALE_SALARY_GROUP = "categoryScaleSalaryGroup"; // Nhom Bac luong
    public static final String SCALE_SALARY_GROUP_TEXT = "Nhóm ngạch công chức";
    public static final String SCALE_SALARY = "categoryScaleSalary"; // Bac luong
    public static final String SCALE_SALARY_TEXT = "Ngạch công chức";


    /**
     * QUẢN LÝ Thông tin cán bộ
     */

    public static final String EMPLOYEE_CV = "Lý lịch";
    public static final String EMPLOYEE_TRAINING = "Quá trình đào tạo - bồi dưỡng";
    public static final String EMPLOYEE_MISSION_GO = "Quá trình đi công tác";
    public static final String EMPLOYEE_MISSION = "Quá trình công tác";
    public static final String EMPLOYEE_FAMILY = "Quan hệ gia đình";
    public static final String EMPLOYEE_COMMENDATION_DISCIPLINE = "Khen thưởng - Kỷ luật";
    public static final String EMPLOYEE_ASSESSMENT = "Nhận xét đánh giá";
    public static final String EMPLOYEE_EMULATION = "Danh hiệu thi đua";
    public static final String EMPLOYEE_SALARY = "Qúa trình lương";
    public static final String EMPLOYEE_CONCURRENTLY_POSITION = "Chức vụ kiêm nhiệm";
    public static final String EMPLOYEE_RELATED_DOCUMENTS = "Thành phần hồ sơ liên quan";
    public static final String EMPLOYEE_ID_CV = "EMPLOYEE_ID_CV";
    public static final String EMPLOYEE_ID_TRAINING = "EMPLOYEE_ID_TRAINING";
    public static final String EMPLOYEE_ID_MISSION_GO = "EMPLOYEE_ID_MISSION_GO";
    public static final String EMPLOYEE_ID_MISSION = "EMPLOYEE_ID_MISSION";
    public static final String EMPLOYEE_ID_FAMILY = "EMPLOYEE_ID_FAMILY";
    public static final String EMPLOYEE_ID_COMMENDATION_DISCIPLINE = "EMPLOYEE_ID_COMMENDATION_DISCIPLINE";
    public static final String EMPLOYEE_ID_ASSESSMENT = "EMPLOYEE_ID_ASSESSMENT";
    public static final String EMPLOYEE_ID_EMULATION = "EMPLOYEE_ID_EMULATION";
    public static final String EMPLOYEE_ID_SALARY = "EMPLOYEE_ID_SALARY";
    public static final String EMPLOYEE_ID_CONCURRENTLY_POSITION = "EMPLOYEE_ID_CONCURRENTLY_POSITION";
    public static final String EMPLOYEE_ID_RELATED_DOCUMENTS = "EMPLOYEE_ID_RELATED_DOCUMENTS";
}
