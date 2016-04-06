/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAO;

import com.viettel.common.util.Constants;
import com.viettel.common.util.DateTimeUtils;
import com.viettel.hqmc.BO.Business;
import com.viettel.hqmc.DAOHE.BusinessDAOHE;
import com.viettel.hqmc.DAOHE.FeeDAOHE;
import com.viettel.hqmc.DAOHE.FilesNoClobDAOHE;
import com.viettel.hqmc.DAOHE.MessageEmailDAOHE;
import com.viettel.hqmc.DAOHE.MessageSmsDAOHE;
import com.viettel.hqmc.FORM.HomeLiveTileForm;
import com.viettel.voffice.client.form.EventLogForm;
import com.viettel.voffice.common.util.PasswordService;
import com.viettel.voffice.common.util.StringUtils;
import com.viettel.voffice.database.BO.EmailUser;
import com.viettel.vsaadmin.client.form.UsersForm;
import com.viettel.vsaadmin.database.BO.Roles;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import viettel.passport.client.ObjectToken;
import viettel.passport.client.UserToken;

/**
 *
 * @author HanPT1
 */
public class HomeDAO extends BaseDAO {

    private UsersForm userPasswordForm;
    boolean loadCountFilesNeedToPropose = false;
    boolean loadCountFilesNeedToAssign = false;
    boolean loadCountFilesNeedToEvaluate = false;
    boolean loadCountFilesNeedToCoEvaluate = false;
    boolean loadCountFilesNeedToReview = false;
    boolean loadCountFilesNeedToApprove = false;
    boolean loadCountFilesNeedToSend = false;
    boolean loadCountFilesNeedToReceive = false;
    boolean loadCountFilesNeedToComparison = false;
    boolean loadCountFilesNeedToComparisonFail = false;
    boolean loadCountFilesPrepareFeePayManage = false;
    boolean loadCountFilesPrepareFeeManage = false;
    // By role
    boolean loadCountFilesClericalRole = false;

    String countFilesNeedToPropose = "";
    String countFilesNeedToReceive = "";
    String countFilesIsCompared = "";
    String countFilesNeedToComparison = "";
    String countFilesNeedToComparisonFail = "";
    String countFilesNeedToPrepareFeePayManage = "";
    String countFilesNeedToPrepareFeeManage = "";
    String countFilesNeedToAssign = "";
    String countFilesNeedToEvaluate = "";
    String countFilesNeedToCoEvaluate = "";
    String countFilesNeedToReview = "";
    String countFilesNeedToAlertCompariston = "";
    String countFilesNeedToApprove = "";
    String countFilesNeedToSend = "";
    String countFilesNeedToSign = "";
    List lstItem;
    private final String LOG_PAGE = "log_page";
    private EventLogForm searchLogForm;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(HomeDAO.class);

    public String gotoLogManager() {
        return LOG_PAGE;
    }

    public String onSearchLog() {
        try {
            getGridInfo();
            List listParam = new ArrayList();
            String countHQL = "select count(e) ";
            String hql = "from EventLogBO e where 1=1";
            if (searchLogForm != null) {
                if (searchLogForm.getUserName() != null && searchLogForm.getUserName().trim().length() > 0) {
                    hql += " and( lower(e.userName) like ? escape '/' or lower(e.actor) like ? escape '/' )";
                    listParam.add("%" + StringUtils.escapeSql(searchLogForm.getUserName()) + "%");
                    listParam.add("%" + StringUtils.escapeSql(searchLogForm.getUserName()) + "%");

                }
                if (searchLogForm.getAction() != null && searchLogForm.getAction().trim().length() > 0) {
                    //hql+=" AND "+ convertToSearchVietNamese("action", searchLogForm.getAction(), listParam);
                    hql += " and lower(e.action) like ? escape '/' ";
                    listParam.add("%" + StringUtils.escapeSql(searchLogForm.getAction()) + "%");
                }
                if (searchLogForm.getEventDateFrom() != null) {
                    hql += " and e.eventDate >= ?";
                    listParam.add(searchLogForm.getEventDateFrom());
                }
                if (searchLogForm.getEventDateTo() != null) {
                    hql += " and e.eventDate < ?";
                    listParam.add(DateTimeUtils.addOneDay(searchLogForm.getEventDateTo()));
                }
                if (searchLogForm.getDeptName() != null && searchLogForm.getDeptName().length() > 0) {
                    hql += " and lower(e.deptName) like ? escape '/' ";
                    listParam.add(searchLogForm.getDeptName());
                }
                if (searchLogForm.getAppName() != null && searchLogForm.getAppName().length() > 0) {
                    hql += " and lower(e.appName) like ? escape '/' ";
                    listParam.add(searchLogForm.getAppName());
                }
                if (searchLogForm.getObjectName() != null && searchLogForm.getObjectName().length() > 0) {
                    hql += " and lower(e.objectName) like ? escape '/' ";
                    listParam.add(searchLogForm.getObjectName());
                }
            }

            countHQL = countHQL + hql;

            String sortType = null;
            if (sortField != null) {
                if (sortField.indexOf('-') != -1) {
                    sortType = "asc";
                    sortField = sortField.substring(1); // not use in this case
                } else {
                    sortType = "desc";
                }
            }

            if (sortField != null) {
                hql += " order by " + validateColumnName(sortField) + " " + sortType;
            } else {
                hql += " order by e.eventDate desc, e.eventId desc ";
            }

            Query query = getSession().createQuery(hql);
            Query countQuery = getSession().createQuery(countHQL);
            if (listParam != null && listParam.size() > 0) {
                for (int i = 0; i < listParam.size(); i++) {
                    query.setParameter(i, listParam.get(i));
                    countQuery.setParameter(i, listParam.get(i));
                }
            }

            query.setFirstResult(start);
            query.setMaxResults(count);
            List lstResult = query.list();
            Long total = (Long) countQuery.uniqueResult();
            jsonDataGrid.setItems(lstResult);
            jsonDataGrid.setTotalRows(total.intValue());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return GRID_DATA;
    }

    public String gotoDocumentDirectionPortal() {

//        Date fromDate = new Date(new Date().getYear(), new Date().getMonth(), 1);
//        Date toDate = new Date();
//        getRequest().setAttribute("documentDirectionSearchForm.fromDate", fromDate);
//        getRequest().setAttribute("documentDirectionSearchForm.toDate", toDate);
        return "website.definition";
    }

    public String gotoGuidelinePage() {
        return "guidelinePage";
    }
//1: Hồ sơ chờ thẩm định
//2: Hồ sơ đang xử lý
//3: Hồ sơ SĐBS
//4: Hồ sơ bị trả lại
//5: Hồ sơ đối chiếu
//6: Hồ sơ đã xử lý

    public void checkObjectToken(ObjectToken ob, String menu) {
        try {
            if (ob.getDescription().toLowerCase() != null) {
                String jsFunction = "doGoToMenu( '" + ob.getObjectUrl() + "', '" + menu + "' );";
                if (ob.getObjectUrl().contains("filesAction!toBusinessListPage.do")) {
                    loadCountFilesNeedToSend = true;
                    countFilesNeedToSend = jsFunction;
                } else if (ob.getObjectUrl().contains("filesAction!proposeEvaluation.do")) {
                    loadCountFilesNeedToPropose = true;
                    countFilesNeedToPropose = jsFunction;
                } else if (ob.getObjectUrl().contains("filesAction!assignEvaluation.do")) {
                    loadCountFilesNeedToAssign = true;
                    countFilesNeedToAssign = jsFunction;
                } else if (ob.getObjectUrl().contains("filesAction!toEvaluatePage.do")) {
                    loadCountFilesNeedToEvaluate = true;
                    countFilesNeedToEvaluate = jsFunction;
                } else if (ob.getObjectUrl().contains("filesAction!toCoEvaluate.do")) {
                    loadCountFilesNeedToCoEvaluate = true;
                    countFilesNeedToCoEvaluate = jsFunction;
                } else if (ob.getObjectUrl().contains("filesAction!toReviewPage.do")) {
                    loadCountFilesNeedToReview = true;
                    countFilesNeedToReview = jsFunction;
                } else if (ob.getObjectUrl().contains("filesAction!toApprovePage.do")) {
                    loadCountFilesNeedToApprove = true;
                    countFilesNeedToApprove = jsFunction;
                } else if (ob.getObjectUrl().contains("filesAction!toReceived.do")) {
                    loadCountFilesNeedToReceive = true;
                    countFilesNeedToReceive = jsFunction;
                } else if (ob.getObjectUrl().contains("filesAction!toComparison.do")) {
                    loadCountFilesNeedToComparison = true;
                    countFilesNeedToComparison = jsFunction;
                } else if (ob.getObjectUrl().contains("filesAction!toComparisonFail.do")) {
                    loadCountFilesNeedToComparisonFail = true;
                    countFilesNeedToComparisonFail = jsFunction;
                } else if (ob.getObjectUrl().contains("filesAction!prepareFeePayManage.do")) {
                    loadCountFilesPrepareFeePayManage = true;
                    countFilesNeedToPrepareFeePayManage = jsFunction;
                } // hieptq 15.11.14
                else if (ob.getObjectUrl().contains("filesAction!prepareFeeManage.do")) {
                    loadCountFilesPrepareFeeManage = true;
                    countFilesNeedToPrepareFeeManage = jsFunction;
                }
            }

            if (ob.getChildObjects() != null && ob.getChildObjects().size() > 0) {
                for (int i = 0; i < ob.getChildObjects().size(); i++) {
                    String execMenu = menu + "." + i;
                    checkObjectToken(ob.getChildObjects().get(i), execMenu);
                }
            }

            // By Role - DuND
            // Get Role
            List<Roles> roles = getListRolesByUser();
            String lstRole = "";
            if (roles != null && roles.size() > 0) {
                for (int i = 0; i < roles.size(); i++) {
                    lstRole += roles.get(i).getRoleCode() + ";";
                }
                if (lstRole.contains(Constants.ROLES.LEAD_UNIT_DEPUTY_ROLE + ";")) {
                    loadCountFilesClericalRole = true;
                }
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public String getGeneralCount() {

        FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
        FeeDAOHE feedhe = new FeeDAOHE();
        UsersDAOHE udhe = new UsersDAOHE();
        Long userId = getUserId();
        Long deptId = getDepartmentId();
        Long agencyId = udhe.getAgencyOfStaff(getUserId());
        Long businessId = getBusinessId();
        Long filesNeedToReEvaluate;
        Long filesOutOfAdd;
        Long filesNeedComment;
        Long filesNeedToSend;
        Long filesNeedToReSend;
        Long filesNeedToPropose;
        Long filesNeedToAssign;
        Long filesNeedToEvaluate;
        Long filesNeedToCoEvaluate;
        Long filesNeedToReview;
        Long filesNeedToReReview;
        Long filesApproved;
        Long filesReviewed;
        Long filesNeedToAdd;
        Long filesNeedToApprove;
        Long filesEvaluated;
        Long filesCompared;
        Long filesNeedToReceive;
        Long filesNeedToComparison;
        Long filesNeedToComparisonFail;
        Long filesStatistics;
        Long filesAlertComparison;
        Long filesReReview;
        Long filesReviewComparison;
        Long filesWaiting;
        Long filesAnnouceToAdd;
        Long filesFeePayManage;
        Long filesFeeManage;
        Long filesFeePayManageUnCheck;
        Long filesFeeManageUnCheck;
        Long filesFeePayManageFunction;
        Long filesFeePayManageUnCheckFunction;
        Long filesNeedToAssign2;
        Long filesNeedToReceive2;
        Long filesNeedToSign = null;

        try {
            String menu = "";
            UserToken token = (UserToken) getRequest().getSession().getAttribute(Constants.VSA_USER_TOKEN);

            if (token != null) {
                for (int i = 0; i < token.getParentMenu().size(); i++) {
                    menu = "" + i;
                    ObjectToken obj = token.getParentMenu().get(i);
                    checkObjectToken(obj, menu);
                }
            }
            lstItem = new ArrayList();
            // By role
            if (loadCountFilesClericalRole) {
                // tong so ho so trong ngay ( lanh dao cuc )
                filesStatistics = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 24l, null));
                getRequest().setAttribute("filesstatistics", filesStatistics);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.status=" + 41 + "',"),
                        "share/images/document/15_xanh_tonghophs.png",
                        "Tổng số hồ sơ trong ngày",
                        filesStatistics, 1l, "darkgray", 1);
                lstItem.add(item);
                //--hồ sơ đã phân công--
                //filesNeedToAssign = (Long) (long) fdhe.getCountFileToAssign(userId, deptId);
                /*
                 filesNeedToAssign = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, -3l, null));
                 getRequest().setAttribute("filesNeedToAssign", filesNeedToAssign);
                 item = new HomeLiveTileForm(countFilesNeedToAssign.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.ASSIGNED + "',"),
                 "share/images/document/14_xanh_hsdaguiphanhoi.png",
                 "Hồ sơ Trưởng phòng đã phân công",
                 filesNeedToAssign, 1l, "cadetblue", 1);
                 lstItem.add(item);

                 //            filesNeedToAssign = (Long) (long) fdhe.getCountFileToAssign(userId, deptId);
                 filesNeedToAssign = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 0l, null));
                 getRequest().setAttribute("filesNeedToAssign", filesNeedToAssign);
                 item = new HomeLiveTileForm(countFilesNeedToAssign.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.RECEIVED + "',"),
                 "share/images/document/14_xanh_hsdaguiphanhoi.png",
                 "Hồ sơ chờ Trưởng phòng phân công",
                 filesNeedToAssign, 1l, "cadetblue", 2);
                 lstItem.add(item);
                 */
                //ho so cho xem xet
                filesNeedToReview = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 2l, Constants.FILE_STATUS.EVALUATED));
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                getRequest().setAttribute("filesNeedToReview", filesNeedToReview);
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 2L + "',"),
                        "share/images/document/7_xanh_hschothamdinh.png",
                        "Hồ sơ CV đã TĐ Đạt, chờ TP xem xét",
                        filesNeedToReview, 1l, "darkgray", 2);
                lstItem.add(item);
                //Hồ sơ đã trình lãnh đạo cục duyệt
                filesStatistics = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 5L, null));
                getRequest().setAttribute("filesstatistics", filesStatistics);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 5 + "',"),
                        "share/images/document/15_xanh_tonghophs.png",
                        "Hồ sơ LĐP đã xem xét Đạt, chờ LĐC phê duyệt",
                        filesStatistics, 1l, "darkgray", 2);
                lstItem.add(item);
                //Hồ sơ đã cấp giấy chứng nhận
                filesStatistics = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, -22l, null));
                getRequest().setAttribute("filesstatistics", filesStatistics);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 22 + "',"),
                        "share/images/document/15_xanh_tonghophs.png",
                        "Hồ sơ đã cấp giấy công bố",
                        filesStatistics, 1l, "darkgray", 6);
                lstItem.add(item);
                //ho so cho tham dinh //toEvaluateLeaderPage
                Long posId = getUser().getPosId();
                if (posId == 253) {
                    filesNeedToReview = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 9L, Constants.FILE_STATUS.EVALUATED));
                    countFilesNeedToReview = "doGoToMenu( 'filesAction!toEvaluateLeaderPage.do', '0.1' );";
                    getRequest().setAttribute("filesNeedToReview", filesNeedToReview);
                    item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 2L + "',"),
                            "share/images/document/7_xanh_hschothamdinh.png",
                            "Hồ sơ CV đã thẩm định, chờ PP thẩm định",
                            filesNeedToReview, 1l, "darkgray", 2);
                    lstItem.add(item);
                }
                // ho so cho xem xet sua doi bo sung
                filesReReview = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 29L, null));
                getRequest().setAttribute("filesReReview", filesReReview);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 7L + "',"),
                        "share/images/document/9_xanh_hssaphethanbosung.png",
                        "Hồ sơ CV đã thẩm định y/c SĐBS, chờ TP xem xét",
                        filesReReview,
                        1l,
                        "darkgray",
                        3);
                lstItem.add(item);
                // ho so sap het han bo sung
                filesOutOfAdd = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 22l, null));
                countFilesNeedToEvaluate = "doGoToMenu('filesAction!lookupFilesByLeaderOfStaff.do', '0.1');";
                item = new HomeLiveTileForm(countFilesNeedToEvaluate.replace("',", "?searchForm.searchType=" + 30 + "',"),
                        "share/images/document/10_xanh_hscholanhdaoxemxet.png",
                        "Hồ sơ đã gửi công văn y/c doanh nghiệp SĐBS",
                        filesOutOfAdd, 1l, "darkgray", 3);
                lstItem.add(item);
                // 3 ho so sap het han bo sung
                filesOutOfAdd = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 39L, null));
                countFilesNeedToEvaluate = "doGoToMenu('filesAction!lookupFilesByLeaderOfStaff.do', '0.1');";
                item = new HomeLiveTileForm(countFilesNeedToEvaluate.replace("',", "?searchForm.searchType=" + 39 + "',"),
                        "share/images/document/10_xanh_hscholanhdaoxemxet.png",
                        "Hồ sơ LĐC đã từ chối phê duyệt gửi công văn y/c SĐBS",
                        filesOutOfAdd, 1l, "darkgray", 3);
                lstItem.add(item);
                // ho so cho xem xet du thao sdbs
                /*binhnt53 150126
                 filesReReview = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 35L, null));
                 getRequest().setAttribute("filesReReview", filesReReview);
                 countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                 item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 8L + "',"),
                 "share/images/document/9_xanh_hssaphethanbosung.png",
                 "Hồ sơ Chuyên viên đã tạo dự thảo công văn SĐBS, chờ Lãnh đạo phòng xem xét",
                 filesReReview, 1l, "darkgray", 3);
                 lstItem.add(item);
                 */
                /*
                 //hồ sơ chờ xem xét đối chiếu
                 filesReviewComparison = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 30l, null));
                 getRequest().setAttribute("filesReviewComparison", filesReviewComparison);
                 countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                 item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.COMPARED + "',"), "share/images/document/9_xanh_hssaphethanbosung.png", "Hồ sơ chờ xem xét đối chiếu", filesReviewComparison, 1l, "darkgray", 5);
                 lstItem.add(item);
                 */
                /*
                 //hồ sơ chờ xem xét đối chiếu lại
                 filesReviewComparison = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 31l, null));
                 getRequest().setAttribute("filesReviewComparison", filesReviewComparison);
                 countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                 item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL + "',"),
                 "share/images/document/9_xanh_hssaphethanbosung.png", "Hồ sơ chờ xem xét đối chiếu lại", filesReviewComparison, 1l, "darkgray", 5);
                 lstItem.add(item);
                 */
            }

            // Ho so cua doanh nghiep chua gui
            if (loadCountFilesNeedToSend) {
                // 1- Hồ sơ chưa hoàn thiện = Mới tạo (files.status = 0)
                filesNeedToSend = (Long) (long) fdhe.getCountFileToSend(businessId, Constants.FILE_STATUS.NEW_CREATE);
                getRequest().setAttribute("filesNeedToSend", filesNeedToSend);
                HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.NEW_CREATE.toString() + "&searchForm.searchType=" + 55 + "',"),
                        "share/images/document/Ho so gui toi co quan.png",
                        "Hồ sơ chưa hoàn thiện",
                        filesNeedToSend,
                        1l,
                        "#008000",
                        1);
                lstItem.add(item);
                //2- Hồ sơ chưa nộp phí  = Mới tạo, chưa nộp phí, đã ký or upload file ký(files.status = 0, fee_payment_info.status = 0, files.user_signed is not null)
                filesNeedToReSend = (Long) (long) fdhe.getCountFileToSend(businessId, -3L);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.NEW_CREATE + "&searchForm.searchType=" + -3 + "',"),
                        "share/images/document/Ho so bi tra xem xet lai.png",
                        "Hồ sơ chưa nộp phí thẩm định ",
                        filesNeedToReSend, 1l,
                        "#008000",
                        1);
                lstItem.add(item);
                // ho so cho ke toan xac nhan doanh nghiep
                filesNeedToReSend = (Long) (long) fdhe.getCountFileToSend(businessId, -100L);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.NEW + "&searchForm.searchType=" + -100 + "',"),
                        "share/images/document/Ho so bi tra xem xet lai.png",
                        "Hồ sơ chờ kế toán xác nhận phí thẩm định ",
                        filesNeedToReSend, 1l,
                        "#008000",
                        2);
                lstItem.add(item);

                // ho so cho ke toan xac nhan le phi cap so - hieptq 161214
                filesNeedToReSend = (Long) (long) fdhe.getCountFileToSend(businessId, -109L);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.APPROVED + "&searchForm.searchType=" + -109 + "',"),
                        "share/images/document/Ho so bi tra xem xet lai.png",
                        "Hồ sơ chờ kế toán xác nhận lệ phí cấp số ",
                        filesNeedToReSend, 1l,
                        "#008000",
                        2);
                lstItem.add(item);

                //3-Hồ sơ chờ tiếp nhận =
                //  + Mới tạo, đã nộp phí: (files.status = 0, fee_payment_info.status = 3, 4, loại phí thẩm định) ==> Bỏ
                //  + Mới nộp (files.status = 1)
                //  + Mới nộp SĐBS (files.status = 18)
                filesWaiting = (Long) (long) fdhe.getCountFileToSend(businessId, -4L);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.NEW_CREATE + "&searchForm.searchType=" + -4 + "',"),
                        "share/images/document/10_xanh_hscholanhdaoxemxet.png",
                        "Hồ sơ chờ văn thư tiếp nhận ",
                        filesWaiting, 1l,
                        "#008000",
                        2);
                lstItem.add(item);

                //  4- Hồ sơ đã tiếp nhận = Đã tiếp nhận (files.status = 14)
                filesNeedToReSend = (Long) (long) fdhe.getCountFileToSend(businessId, Constants.FILE_STATUS.RECEIVED);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.RECEIVED + "&searchForm.searchType=" + 1 + "',"),
                        "share/images/document/Ho so bi tra xem xet lai.png",
                        "Hồ sơ văn thư đã tiếp nhận ",
                        filesNeedToReSend, 1l,
                        "#008000",
                        2);
                lstItem.add(item);
                //  5- Hồ sơ đang xử lý = Đã phân công (3), Đã thẩm định (4),  Đã xem xét (5), Đã trả bổ sung (7),  Đã trả thẩm định lại (8), Đã trả xem xét lại (9), Đã xem xét yêu cầu SĐBS (19), 15,16,24,25
                filesNeedToReSend = (Long) (long) fdhe.getCountFileToSend(businessId, -2L);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.searchType=" + -2 + "',"),
                        "share/images/document/Ho so bi tra xem xet lai.png",
                        "Hồ sơ đang trong quá trình xử lý",
                        filesNeedToReSend, 1l,
                        "#008000",
                        2);
                lstItem.add(item);
                //9- Hồ sơ chờ nộp giấy tờ gốc = Chờ đối chiếu hồ sơ gốc (23)
                filesAlertComparison = (Long) (long) fdhe.getCountFileToSend(businessId, Constants.FILE_STATUS.ALERT_COMPARISON);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.ALERT_COMPARISON + "&searchForm.searchType=" + 1 + "',"),
                        "share/images/document/10_xanh_hscholanhdaoxemxet.png",
                        "Hồ sơ chờ nộp giấy tờ gốc đối chiếu ",
                        filesAlertComparison, 1l,
                        "#008000",
                        5);
                lstItem.add(item);
                // 12- Hồ sơ chờ nộp lệ phí = Đã phê duyệt, chưa nộp lệ phí (files.status = 6, fee_payment_info.status = 0, loại lệ phí)
                filesApproved = (Long) (long) fdhe.getCountFileToSend(businessId, -5L);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.APPROVED + "&searchForm.searchType=" + -5 + "',"),
                        "share/images/document/11_xanh_hslanhdaocucdapheduyet.png",
                        "Hồ sơ chờ nộp lệ phí ",
                        filesApproved, 1l,
                        "#008000",
                        1);
                lstItem.add(item);

                //6- Hồ sơ chờ sửa đổi bổ sung = Đã thông báo SĐBS (20)
                filesNeedToReSend = (Long) (long) fdhe.getCountFileToSend(businessId, Constants.FILE_STATUS.EVALUATED_TO_ADD);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.EVALUATED_TO_ADD + "&searchForm.searchType=" + 1 + "',"),
                        "share/images/document/Ho so bi tra xem xet lai.png",
                        "Hồ sơ đã gửi công văn yêu cầu sửa đổi bổ sung",
                        filesNeedToReSend, 1l,
                        "#008000",
                        3);
                lstItem.add(item);
                //7- Hồ sơ đã tiếp nhận SĐBS  (files.status = 17)
                filesNeedToReSend = (Long) (long) fdhe.getCountFileToSend(businessId, Constants.FILE_STATUS.RECEIVED_TO_ADD);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.RECEIVED_TO_ADD + "&searchForm.searchType=" + 1 + "',"),
                        "share/images/document/Ho so bi tra xem xet lai.png",
                        "Hồ sơ văn thư đã tiếp nhận SĐBS ",
                        filesNeedToReSend, 1l,
                        "#008000",
                        3);
                lstItem.add(item);

                // ho so cho van thu tiep nhan SDBS
                filesNeedToReSend = (Long) (long) fdhe.getCountFileToSend(businessId, -7l);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.NEW_TO_ADD + "&searchForm.searchType=" + -7 + "',"),
                        "share/images/document/Ho so bi tra xem xet lai.png",
                        "Hồ sơ chờ văn thư tiếp nhận SĐBS ",
                        filesNeedToReSend, 1l,
                        "#008000",
                        3);
                lstItem.add(item);

                //8- Hồ sơ bị trả lại = Đã trả lại - có HD bổ sung (21)
                filesNeedToReSend = (Long) (long) fdhe.getCountFileToSend(businessId, Constants.FILE_STATUS.RECEIVED_REJECT_TO_ADD);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.RECEIVED_REJECT + "&searchForm.searchType=" + Constants.FILE_STATUS.RECEIVED_REJECT_TO_ADD + "',"),
                        "share/images/document/Ho so bi tra xem xet lai.png",
                        "Hồ sơ bị văn thư từ chối",
                        filesNeedToReSend, 1l, "#008000",
                        4);
                lstItem.add(item);
                //8- Hồ sơ bị kế toán từ chối
                filesNeedToReSend = (Long) (long) fdhe.getCountFileToSend(businessId, Constants.SEARCH_TYPE.DTCXNP_L);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.searchType=" + Constants.SEARCH_TYPE.DTCXNP_L + "',"),
                        "share/images/document/Ho so bi tra xem xet lai.png",
                        "Hồ sơ bị kế toán từ chối",
                        filesNeedToReSend, 1L, "#008000",
                        4);
                lstItem.add(item);
                //10- Hồ sơ đối chiếu hợp lệ (15)
                filesCompared = (Long) (long) fdhe.getCountFileToSend(businessId, Constants.FILE_STATUS.COMPARED);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.COMPARED + "&searchForm.searchType=" + 1 + "',"),
                        "share/images/document/12_xanh_hslanhdaophongdaxemxet.png",
                        "Hồ sơ đối chiếu giấy tờ gốc hợp lệ ",
                        filesCompared, 1l,
                        "#008000",
                        5);
                lstItem.add(item);
                //11- Hồ sơ đối chiếu có sai lệch (16)
                filesAlertComparison = (Long) (long) fdhe.getCountFileToSend(businessId, Constants.FILE_STATUS.COMPARED_FAIL);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.COMPARED_FAIL + "&searchForm.searchType=" + 1 + "',"),
                        "share/images/document/10_xanh_hscholanhdaoxemxet.png",
                        "Hồ sơ đối chiếu giấy tờ gốc có sai lệch ",
                        filesAlertComparison, 1l,
                        "#008000",
                        5);
                lstItem.add(item);
                /*u binhnt53 gộp 2 phần count thành 1 202
                 //13- Hồ sơ đã trả kết quả = Đã trả hồ sơ (22)
                 filesNeedToReSend = (Long) (long) fdhe.getCountFileToSend(businessId, Constants.FILE_STATUS.GIVE_BACK);
                 item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.GIVE_BACK + "&searchForm.searchType=" + 1 + "',"),
                 "share/images/document/Ho so bi tra xem xet lai.png",
                 "Hồ sơ văn thư đã trả bản công bố (bản cứng) ",
                 filesNeedToReSend, 1l,
                 "#008000",
                 6);
                 lstItem.add(item);
                 // ho so da ky so + dong tien + dong dau so
                 filesNeedToReSend = (Long) (long) fdhe.getCountFileToSend(businessId, Constants.FILE_STATUS.APPROVED);
                 item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.APPROVED + "&searchForm.searchType=" + 6 + "',"),
                 "share/images/document/Ho so bi tra xem xet lai.png",
                 "Hồ sơ văn thư đã trả bản công bố (bản mềm)",
                 filesNeedToReSend, 1l,
                 "#008000",
                 6);
                 lstItem.add(item);
                 */
                //Hồ sơ văn thư đã trả bản công bố (bản mềm) =  ho so da ky so + dong tien + dong dau so
                filesNeedToReSend = (Long) (long) fdhe.getCountFileToSend(businessId, 76L);
                item = new HomeLiveTileForm(countFilesNeedToSend.replace("',", "?searchForm.searchType=" + 76 + "',"),
                        "share/images/document/Ho so bi tra xem xet lai.png",
                        "Hồ sơ văn thư đã trả bản công bố",
                        filesNeedToReSend, 1l,
                        "#008000",
                        6);
                lstItem.add(item);

            }
            //Ho so cho tiep nhan
            if (loadCountFilesNeedToReceive) {
//                filesNeedToReceive = (Long) (long) fdhe.getCountFileToProcess(userId, deptId, -1L, null);
//                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
//                HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToReceive, "share/images/document/images10.png", "Hồ sơ gửi đến cơ quan", filesNeedToReceive, -1l, "blueviolet");
//                lstItem.add(item);
//                filesNeedToReceive = (Long) (long) fdhe.getCountFileToProcess(userId, deptId, -2L, 15L);
//                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
//                //item = new HomeLiveTileForm(countFilesIsCompared, "share/images/document/images10.png", "Hồ sơ đã đối chiếu", filesIsCompared, -1l, "blueviolet");
//                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
//                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.APPROVED + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ đã phê duyệt", filesNeedToReceive, 15l, "cadetblue");
//                lstItem.add(item);

//1- Hồ sơ chờ tiếp nhận = Mới nộp và đã xác nhận phí (files.status = 1, fee_payment_info.status = 1, fee.fee_type=2), Mới nộp SĐBS (18)
                // hieptq update 201114 them nhom san pham
                // nhom san pham thuong
                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, -1L, Constants.FILE_STATUS.NEW);
//                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=" + -1L + "&searchForm.searchTypeNew=1" + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ chờ văn thư tiếp nhận (Nhóm thực phẩm thường)", filesNeedToReceive, 15l, "cadetblue",
                        1);
                lstItem.add(item);

                // nhom san pham khac
                filesNeedToReceive2 = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, -8L, Constants.FILE_STATUS.NEW);
//                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=" + -1L + "&searchForm.searchTypeNew=2" + "',"),
                        "share/images/document/14_xanh_hsdaguiphanhoi.png",
                        "Hồ sơ chờ văn thư tiếp nhận (Nhóm thực phẩm khác)",
                        filesNeedToReceive2,
                        15l,
                        "cadetblue",
                        1);
                lstItem.add(item);

                // Hồ sơ chờ kế toán xác nhận = Mới nộp và chưa xác nhận phí (files.status = 1, fee_payment_info.status <> 1 , fee.fee_type=2)
                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, -7L, Constants.FILE_STATUS.NEW);
                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=" + -7L + "',"),
                        "share/images/document/14_xanh_hsdaguiphanhoi.png",
                        "Hồ sơ chờ kế toán xác nhận phí thẩm định",
                        filesNeedToReceive,
                        15l,
                        "cadetblue",
                        1);
                lstItem.add(item);

//2- Hồ sơ đã tiếp nhận = Đã tiếp nhận (14)
                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, 0L, Constants.FILE_STATUS.RECEIVED);
//                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=0&searchForm.status=" + Constants.FILE_STATUS.RECEIVED + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ văn thư đã tiếp nhận", filesNeedToReceive, 15l, "cadetblue",
                        1);
                lstItem.add(item);

                //5- Hồ sơ đã phê duyệt chưa nộp phí cấp số = Đã phê duyệt, chưa nộp lệ phí (files.status = 6, fee_payment_info.status = 0,fee.fee_type = 1 , files.isSignPdf=1)
                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, -4L, Constants.FILE_STATUS.NEW);
//                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=" + -4L + "',"),
                        "share/images/document/14_xanh_hsdaguiphanhoi.png",
                        "Hồ sơ đã phê duyệt chưa nộp phí cấp số", filesNeedToReceive, 15l, "cadetblue", 2);
                lstItem.add(item);
                //Hồ sơ cần gửi thông báo SĐBS cho doanh nghiệp
                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, -27L, Constants.FILE_STATUS.APPROVE_TO_ADD);
                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=" + -27L + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ cần gửi thông báo SĐBS", filesNeedToReceive, 15l, "cadetblue", 3);
                lstItem.add(item);
                //hỒ SƠ CẦN KÍ XÁC NHÂN VĂN THƯ
                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, -6L, Constants.FILE_STATUS.NEW);
                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=" + -6L + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ đã nộp lệ phí cấp số, chờ Văn thư ký xác nhận - trả bản mềm", filesNeedToReceive, 15l, "cadetblue",
                        2);
                lstItem.add(item);
                /*
                 //6- Hồ sơ đã nộp phí cấp số, chờ trả hồ sơ = Đã phê duyệt, đã nộp lệ phí (files.status = 6, fee_payment_info.status = 1, fee.fee_type=1, files.isSignPdf=2)
                 filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, -3L, Constants.FILE_STATUS.NEW);
                 countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                 item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=" + -3L + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ đã nộp phí cấp số, chờ trả hồ sơ", filesNeedToReceive, 15l, "cadetblue",
                 2);
                 lstItem.add(item);
                 */
//3- Hồ sơ đã tiếp nhận SĐBS (17)
                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, 0L, Constants.FILE_STATUS.RECEIVED_TO_ADD);
//                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=0&searchForm.status=" + Constants.FILE_STATUS.RECEIVED_TO_ADD + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ đã tiếp nhận SĐBS", filesNeedToReceive, 15l, "cadetblue",
                        3);
                lstItem.add(item);
                // hồ sơ chờ tiếp nhận SĐBS
                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, 0L, Constants.FILE_STATUS.NEW_TO_ADD);
//                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=0&searchForm.status=" + Constants.FILE_STATUS.NEW_TO_ADD + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ chờ tiếp nhận SĐBS", filesNeedToReceive, 15l, "cadetblue",
                        3);
                lstItem.add(item);
                //hồ sơ đã gửi công văn SĐBS
                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, 20L, Constants.FILE_STATUS.EVALUATED_TO_ADD);
//                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=20&searchForm.status=" + Constants.FILE_STATUS.EVALUATED_TO_ADD + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ đã thông báo SĐBS", filesNeedToReceive, 15l, "cadetblue",
                        3);
                lstItem.add(item);

//4- Hồ sơ đã phê duyệt (6)
                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, 22L, Constants.FILE_STATUS.APPROVED);
//                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=22&searchForm.status=" + Constants.FILE_STATUS.APPROVED + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ đã được phê duyệt", filesNeedToReceive, 15l, "cadetblue",
                        6);
                lstItem.add(item);

//7- Hồ sơ đã trả kết quả = Đã trả hồ sơ (22)
                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, 0L, Constants.FILE_STATUS.GIVE_BACK);
//                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=0&searchForm.status=" + Constants.FILE_STATUS.GIVE_BACK + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ văn thư đã trả kết quả", filesNeedToReceive, 15l, "cadetblue",
                        6);
                lstItem.add(item);

            }
            //ho so cho tham dinh
            if (loadCountFilesNeedToEvaluate) {//ccv
                // Tong so ho so trong ngay
                /*Tam thoi dong
                 filesStatistics = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 23l, null));
                 getRequest().setAttribute("filesstatistics", filesStatistics);
                 countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByStaff.do', '0.1' );";
                 HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.status=" + 40 + "',"), "share/images/document/15_xanh_tonghophs.png", "Tổng hồ sơ trong ngày ", filesStatistics, 1l, "#F0F0F0", 1);
                 lstItem.add(item);
                 */
                //1- Hồ sơ chờ thẩm định = Đã phân công (3)
                filesNeedToEvaluate = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 1L, null));
                getRequest().setAttribute("filesNeedToEvaluate", filesNeedToEvaluate);
                countFilesNeedToEvaluate = "doGoToMenu( 'filesAction!lookupFilesByStaff.do', '0.1' );";
                HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToEvaluate.replace("',", "?searchForm.searchType=" + 50 + "',"),
                        "share/images/document/7_xanh_hschothamdinh.png",
                        "Hồ sơ mới, chờ CV thẩm định", filesNeedToEvaluate, 1l, "#F0F0F0", 2);
                lstItem.add(item);
                //2- Hồ sơ chờ thẩm định SĐBS CV = Đã tiếp nhận SĐBS (17)
                filesNeedComment = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 33L, null));
                countFilesNeedToEvaluate = "doGoToMenu('filesAction!lookupFilesByStaff.do', '0.1');";
                item = new HomeLiveTileForm(countFilesNeedToEvaluate.replace("',", "?searchForm.searchType=" + 33 + "',"),
                        "share/images/document/images10.png",
                        "Hồ sơ SĐBS, chờ CV thẩm định", filesNeedComment, 1l, "#F0F0F0", 2);
                lstItem.add(item);
                // ho so bi tra tham dinh lai
                filesNeedToReEvaluate = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 26L, null));
                countFilesNeedToEvaluate = "doGoToMenu('filesAction!lookupFilesByStaff.do', '0.1');";
                item = new HomeLiveTileForm(countFilesNeedToEvaluate.replace("',", "?searchForm.searchType=" + 26 + "',"),
                        "share/images/document/8_xanh_hsbitrathamdinhlai.png",
                        "Hồ sơ yêu cầu CV thẩm định lại", filesNeedToReEvaluate, 1L, "#F0F0F0", 2);
                lstItem.add(item);
                //3- Hồ sơ chuyên viên cần thông báo SĐBS 150115
//                filesNeedToAdd = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 19L, null));
//                getRequest().setAttribute("filesNeedToReview", filesNeedToAdd);
//                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByStaff.do', '0.1' );";
//                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 19 + "',"),
//                        "share/images/document/13_xanh_hslanhdaocucycbosung.png",
//                        "Hồ sơ chờ CV soạn dự thảo công văn SĐBS", filesNeedToAdd, 1l, "#F0F0F0", 2);
//                lstItem.add(item);
                //4- Hồ sơ chuyên viên đã thẩm định
                filesReReview = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 47L, null));
                getRequest().setAttribute("filesReReview", filesReReview);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByStaff.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 47L + "',"),
                        "share/images/document/9_xanh_hssaphethanbosung.png",
                        "Hồ sơ CV đã thẩm định yêu cầu SĐBS, chờ LĐP xem xét", filesReReview, 1l, "darkgray", 3);
                lstItem.add(item);
                //ho so cho xem xet
                filesNeedToReview = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 42l, Constants.FILE_STATUS.EVALUATED));
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByStaff.do', '0.1' );";
                getRequest().setAttribute("filesNeedToReview", filesNeedToReview);
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 42L + "',"),
                        "share/images/document/7_xanh_hschothamdinh.png",
                        "Hồ sơ CV đã thẩm định Đạt, chờ LĐP xem xét", filesNeedToReview, 1l, "darkgray", 1);
                lstItem.add(item);
                //5- hồ sơ đã xem xét
                filesReviewed = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 45l, null));
                getRequest().setAttribute("filesNeedToReview", filesReviewed);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByStaff.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.status=" + 46 + "',"),
                        "share/images/document/12_xanh_hslanhdaophongdaxemxet.png",
                        "Hồ sơ LĐP đã duyệt, chờ LĐC phê duyệt", filesReviewed, 1l, "#F0F0F0", 1);
                lstItem.add(item);
                //Hồ sơ lãnh đạo cục đã phê duyệt
                filesApproved = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 417L, null));
                getRequest().setAttribute("filesNeedToReview", filesApproved);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByStaff.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 417 + "',"),
                        "share/images/document/11_xanh_hslanhdaocucdapheduyet.png",
                        "Hồ sơ LĐC đã phê duyệt, chờ nộp lệ phí trả hồ sơ", filesApproved, 1l, "#F0F0F0", 6);
                lstItem.add(item);
                //Hồ sơ lãnh đạo cục đã phê duyệt
                filesApproved = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 423L, null));
                getRequest().setAttribute("filesNeedToReview", filesApproved);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByStaff.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 423 + "',"),
                        "share/images/document/11_xanh_hslanhdaocucdapheduyet.png",
                        "Hồ sơ VT đã trả giấy công bố bản mềm", filesApproved, 1l, "#F0F0F0", 6);
                lstItem.add(item);
                //Hồ sơ lãnh đạo cục đã phê duyệt
                filesApproved = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 422L, null));
                getRequest().setAttribute("filesNeedToReview", filesApproved);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByStaff.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 422 + "',"),
                        "share/images/document/11_xanh_hslanhdaocucdapheduyet.png",
                        "Hồ sơ VT đã trả giấy công bố bản cứng", filesApproved, 1l, "#F0F0F0", 6);
                lstItem.add(item);
                //Hồ sơ chuyên viên đã gửi thông báo sửa đổi bổ sung
//                filesAnnouceToAdd = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 22l, null));
//                getRequest().setAttribute("filesNeedToReview", filesAnnouceToAdd);
//                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByStaff.do', '0.1' );";
//                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 22 + "',"), "share/images/document/Ho so xem xet SDBS.png", "Hồ sơ đã gửi công văn SĐBS", filesAnnouceToAdd, 1l, "#F0F0F0",
//                        1);
//                lstItem.add(item);

                /*
                 //Hồ sơ gửi phối hợp chưa cho ý kiến 21- Hồ sơ gửi phối hợp, chưa có ý kiến = Đã phân công (3) xử lý chính, chưa có ý kiến tại version hiện tại trong bảng process_comment
                 filesNeedComment = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 21l, null));
                 countFilesNeedToEvaluate = "doGoToMenu('filesAction!lookupFilesByStaff.do', '0.1');";
                 item = new HomeLiveTileForm(
                 countFilesNeedToEvaluate.replace("',", "?searchForm.searchType=" + 21 + "',"),
                 "share/images/document/images10.png",
                 "Hồ sơ gửi phối hợp chưa cho ý kiến",
                 filesNeedComment,
                 1l,
                 "#F0F0F0",
                 2);
                 lstItem.add(item);
                 */
                /*
                 //Hồ sơ đã có ý kiến của tổ thẩm xét 28
                 filesEvaluated = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 28l, null));
                 getRequest().setAttribute("filesNeedToReview", filesEvaluated);
                 countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByStaff.do', '0.1' );";
                 item = new HomeLiveTileForm(
                 countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 28 + "',"),
                 "share/images/document/14_xanh_hsdaguiphanhoi.png",
                 "Hồ sơ đã có ý kiến của tổ thẩm xét ", filesEvaluated, 1l, "#F0F0F0", 2);
                 lstItem.add(item);
                 */
                //Hồ sơ đã xem xét công văn SĐBS chờ lãnh đạo cục phê duyệt
                filesNeedToReview = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, -26L, null));
                getRequest().setAttribute("filesNeedToReview", filesNeedToReview);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByStaff.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + -26 + "',"),
                        "share/images/document/10_xanh_hscholanhdaoxemxet.png",
                        "Hồ sơ LĐP đã xem xét công văn SĐBS chờ LĐC phê duyệt",
                        filesNeedToReview, 1l, "#F0F0F0", 3);
                lstItem.add(item);
                //HỒ sơ chuyên viên cần thông báo SĐBS
                filesNeedToAdd = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, -20L, null));
                getRequest().setAttribute("filesNeedToReview", filesNeedToAdd);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByStaff.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + -20 + "',"),
                        "share/images/document/13_xanh_hslanhdaocucycbosung.png",
                        "Hồ sơ VT đã gửi công văn SĐBS", filesNeedToAdd, 1l, "#F0F0F0", 3);
                lstItem.add(item);
                /*
                 // ho so sap qua thoi han bo sung ( con 5 ngay )
                 filesOutOfAdd = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 27L, null));
                 countFilesNeedToEvaluate = "doGoToMenu('filesAction!lookupFilesByStaff.do', '0.1');";
                 item = new HomeLiveTileForm(countFilesNeedToEvaluate.replace("',", "?searchForm.searchType=" + 27 + "',"),
                 "share/images/document/9_xanh_hssaphethanbosung.png",
                 "Hồ sơ sắp hết hạn SĐBS", filesOutOfAdd, 1l, "#F0F0F0", 3);
                 lstItem.add(item);
                 */
                //item = new HomeLiveTileForm(countFilesNeedToEvaluate, "share/images/document/alarmd.png", "Hồ sơ sắp quá hạn bổ sung", filesOutOfAdd, 1l, "darkorange");
                //lstItem.add(item);
                /*
                 // Hồ sơ cần thông báo đối chiếu
                 filesAlertComparison = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 5L, null));
                 getRequest().setAttribute("filesAlertComparison", filesAlertComparison);
                 countFilesNeedToAlertCompariston = "doGoToMenu( 'filesAction!lookupFilesByStaff.do', '0.1' );";
                 item = new HomeLiveTileForm(countFilesNeedToAlertCompariston.replace("',", "?searchForm.searchType=" + 5 + "',"), "share/images/document/15_xanh_tonghophs.png", "Hồ sơ cần thông báo Đối chiếu ", filesAlertComparison, 1l, "#F0F0F0", 5);
                 lstItem.add(item);
                 */
            }
            /* hồ sơ cần đối chiếu, stt = đã thông báo đối chiếu or đối chiếu có sai lệch */
            if (loadCountFilesNeedToComparison) {
                //150115
                filesNeedToComparison = (Long) (long) fdhe.getCountFileToProcess(userId, deptId, 1623L, null);
                getRequest().setAttribute("filesNeedToComparison", filesNeedToComparison);
                countFilesNeedToComparison = "doGoToMenu( 'filesAction!lookupFilesByClerical.do', '0.1' );";
                HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToComparison.replace("',", "?searchForm.searchType=" + 1623 + "',"),
                        "share/images/document/15_xanh_tonghophs.png",
                        "Hồ sơ cần đối chiếu hồ sơ gốc - đã trả bản mềm",
                        filesNeedToComparison, 1l, "#F0F0F0", 5);
                lstItem.add(item);

                // hồ sơ cần thông báo đối chiếu giấy tờ gốc
                filesNeedToComparison = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, 6L, Constants.FILE_STATUS.APPROVED);
//                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=6&searchForm.status=" + Constants.FILE_STATUS.APPROVED + "',"),
                        "share/images/document/14_xanh_hsdaguiphanhoi.png",
                        "Hồ sơ cần thông báo đối chiếu hồ sơ gốc",
                        filesNeedToComparison, 15l, "cadetblue", 2);
                lstItem.add(item);

                // hồ sơ cần thông báo đối chiếu giấy tờ gốc
/*
                 filesNeedToComparison = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, 15L, Constants.FILE_STATUS.COMPARED);
                 countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
                 item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=15&searchForm.status=" + Constants.FILE_STATUS.COMPARED + "',"),
                 "share/images/document/14_xanh_hsdaguiphanhoi.png",
                 "Hồ sơ cần trả giấy công bố - bản cứng",
                 filesNeedToComparison, 15l, "cadetblue", 2);
                 lstItem.add(item);
                 */
            }
            // ho so sai lech
            if (loadCountFilesNeedToComparisonFail) {
                filesNeedToComparisonFail = (Long) (long) fdhe.getCountFileToProcess(userId, deptId, 16L, null);
                getRequest().setAttribute("filesNeedToComparisonFail", filesNeedToComparisonFail);
                HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToComparisonFail,
                        "share/images/document/8_xanh_hsbitrathamdinhlai.png",
                        "Hồ sơ đối chiếu có sai lệch",
                        filesNeedToComparisonFail, 16l, "burlywood", 5);
                //HomeLiveTileForm item = new HomeLiveTileForm("doGoToMenu( 'filesAction!onsearchLookupFilesByClerical.do?searchForm.status=16', '0.0' );", "share/images/document/new_document.png", "Hồ sơ sai lệch", filesNeedToComparisonFail, 16l, "burlywood");
                lstItem.add(item);
            }
            //Ho so cho de xuat
            if (loadCountFilesNeedToPropose) {
                filesNeedToPropose = (Long) (long) fdhe.getCountFileToPropose(userId, agencyId);
                getRequest().setAttribute("filesNeedToPropose", filesNeedToPropose);
                HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToPropose,
                        "share/images/document/10_xanh_hscholanhdaoxemxet.png",
                        "Hồ sơ gửi đến cơ quan",
                        filesNeedToPropose, 1l, "blueviolet", 2);
                lstItem.add(item);
            }
            //Hồ sơ chờ phân công
            if (loadCountFilesNeedToAssign) {
                // tong so ho so trong ngay ( lanh dao cuc )
                filesStatistics = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 24l, null));
                getRequest().setAttribute("filesstatistics", filesStatistics);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.status=" + 41 + "',"),
                        "share/images/document/15_xanh_tonghophs.png",
                        "Tổng số hồ sơ trong ngày",
                        filesStatistics, 1l, "darkgray", 1);
                lstItem.add(item);
                //--hồ sơ đã phân công--
//            filesNeedToAssign = (Long) (long) fdhe.getCountFileToAssign(userId, deptId);
                filesNeedToAssign = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, -3l, null));
                getRequest().setAttribute("filesNeedToAssign", filesNeedToAssign);
                item = new HomeLiveTileForm(countFilesNeedToAssign.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.ASSIGNED + "',"),
                        "share/images/document/14_xanh_hsdaguiphanhoi.png",
                        "Hồ sơ Trưởng phòng đã phân công",
                        filesNeedToAssign, 1l, "cadetblue", 1);
                lstItem.add(item);
                //            filesNeedToAssign = (Long) (long) fdhe.getCountFileToAssign(userId, deptId);
                filesNeedToAssign = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 211l, null));
                getRequest().setAttribute("filesNeedToAssign", filesNeedToAssign);
                item = new HomeLiveTileForm(countFilesNeedToAssign.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.RECEIVED + "&searchForm.searchTypeNew=1" + "',"),
                        "share/images/document/14_xanh_hsdaguiphanhoi.png",
                        "Hồ sơ chờ Trưởng phòng phân công (Nhóm thực phẩm thường)",
                        filesNeedToAssign, 1l, "cadetblue", 2);
                lstItem.add(item);

                // phan cong thuc pham khac
                filesNeedToAssign2 = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 212l, null));
                getRequest().setAttribute("filesNeedToAssign2", filesNeedToAssign2);
                item = new HomeLiveTileForm(countFilesNeedToAssign.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.RECEIVED + "&searchForm.searchTypeNew=2" + "',"),
                        "share/images/document/14_xanh_hsdaguiphanhoi.png",
                        "Hồ sơ chờ Trưởng phòng phân công (Nhóm thực phẩm khác)",
                        filesNeedToAssign2, 1l, "cadetblue", 2);
                lstItem.add(item);

                //ho so cho xem xet
                filesNeedToReview = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 2l, Constants.FILE_STATUS.EVALUATED));
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                getRequest().setAttribute("filesNeedToReview", filesNeedToReview);
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 2L + "',"),
                        "share/images/document/7_xanh_hschothamdinh.png",
                        "Hồ sơ Chuyên viên đã thẩm định Đạt, chờ Trưởng phòng xem xét",
                        filesNeedToReview, 1l, "darkgray", 2);
                lstItem.add(item);
                //Hồ sơ đã trình lãnh đạo cục duyệt
                filesStatistics = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 5L, null));
                getRequest().setAttribute("filesstatistics", filesStatistics);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 5 + "',"),
                        "share/images/document/15_xanh_tonghophs.png",
                        "Hồ sơ Trưởng phòng đã xem xét Đạt, chờ Lãnh đạo cục phê duyệt",
                        filesStatistics, 1l, "darkgray", 2);
                lstItem.add(item);

                //Hồ sơ đã cấp giấy chứng nhận
                //binhnt 160317
//                filesStatistics = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, -22l, null));
//                getRequest().setAttribute("filesstatistics", filesStatistics);
//                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
//                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 22 + "',"),
//                        "share/images/document/15_xanh_tonghophs.png",
//                        "Hồ sơ đã cấp giấy công bố",
//                        filesStatistics, 1l, "darkgray", 6);
//                lstItem.add(item);
                //binhnt 160317
                //ho so cho tham dinh //toEvaluateLeaderPage
                Long posId = getUser().getPosId();
                if (posId == 253) {
                    filesNeedToReview = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 9L, Constants.FILE_STATUS.EVALUATED));
                    countFilesNeedToReview = "doGoToMenu( 'filesAction!toEvaluateLeaderPage.do', '0.1' );";
                    getRequest().setAttribute("filesNeedToReview", filesNeedToReview);
                    item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 2L + "',"),
                            "share/images/document/7_xanh_hschothamdinh.png",
                            "Hồ sơ Chuyên viên đã thẩm định, chờ Phó phòng thẩm định",
                            filesNeedToReview, 1l, "darkgray", 2);
                    lstItem.add(item);
                }
                // ho so cho xem xet sua doi bo sung
                filesReReview = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 29L, null));
                getRequest().setAttribute("filesReReview", filesReReview);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 7L + "',"),
                        "share/images/document/9_xanh_hssaphethanbosung.png",
                        "Hồ sơ Chuyên viên đã thẩm định y/c SĐBS, chờ Trưởng phòng xem xét",
                        filesReReview, 1l, "darkgray", 3);
                lstItem.add(item);
                // ho so sap het han bo sung
                filesOutOfAdd = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 22l, null));
                countFilesNeedToEvaluate = "doGoToMenu('filesAction!lookupFilesByLeaderOfStaff.do', '0.1');";
                item = new HomeLiveTileForm(countFilesNeedToEvaluate.replace("',", "?searchForm.searchType=" + 30 + "',"),
                        "share/images/document/10_xanh_hscholanhdaoxemxet.png",
                        "Hồ sơ đã gửi công văn yêu cầu doanh nghiệp SĐBS",
                        filesOutOfAdd, 1l, "darkgray", 3);
                lstItem.add(item);
                // 3 ho so sap het han bo sung
                filesOutOfAdd = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 39L, null));
                countFilesNeedToEvaluate = "doGoToMenu('filesAction!lookupFilesByLeaderOfStaff.do', '0.1');";
                item = new HomeLiveTileForm(countFilesNeedToEvaluate.replace("',", "?searchForm.searchType=" + 39 + "',"),
                        "share/images/document/10_xanh_hscholanhdaoxemxet.png",
                        "Hồ sơ Lãnh đạo cục đã từ chối phê duyệt gửi công văn thông báo SĐBS",
                        filesOutOfAdd, 1l, "darkgray", 3);
                lstItem.add(item);
                // ho so cho xem xet du thao sdbs
                /*
                 filesReReview = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 35L, null));
                 getRequest().setAttribute("filesReReview", filesReReview);
                 countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                 item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 8L + "',"),
                 "share/images/document/9_xanh_hssaphethanbosung.png",
                 "Hồ sơ Chuyên viên đã tạo dự thảo công văn SĐBS, chờ Lãnh đạo phòng xem xét",
                 filesReReview, 1l, "darkgray", 3);
                 lstItem.add(item);
                 */
                /*
                 //hồ sơ chờ xem xét đối chiếu
                 filesReviewComparison = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 30l, null));
                 getRequest().setAttribute("filesReviewComparison", filesReviewComparison);
                 countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                 item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.COMPARED + "',"), "share/images/document/9_xanh_hssaphethanbosung.png", "Hồ sơ chờ xem xét đối chiếu", filesReviewComparison, 1l, "darkgray", 5);
                 lstItem.add(item);
                 */
                /*
                 //hồ sơ chờ xem xét đối chiếu lại
                 filesReviewComparison = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 31l, null));
                 getRequest().setAttribute("filesReviewComparison", filesReviewComparison);
                 countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                 item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL + "',"),
                 "share/images/document/9_xanh_hssaphethanbosung.png", "Hồ sơ chờ xem xét đối chiếu lại", filesReviewComparison, 1l, "darkgray", 5);
                 lstItem.add(item);
                 */
            }
            if (loadCountFilesNeedToCoEvaluate) {
                //ho so cho phoi hop tham dinh
                filesNeedToCoEvaluate = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 32L, null));
                getRequest().setAttribute("filesNeedToCoEvaluate", filesNeedToCoEvaluate);
                HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToCoEvaluate,
                        "share/images/document/16_xanh_hschophoihopthamdinh.png",
                        "Hồ sơ chờ phối hợp thẩm định",
                        filesNeedToCoEvaluate, 1l, "#F0F0F0", 2);
                lstItem.add(item);
            }
            //Hồ sơ chờ xem xét
            if (loadCountFilesNeedToReview) {

                /*
                 //ho so tra xem xet lai
                 filesNeedToReReview = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 2l, Constants.FILE_STATUS.FEDBACK_TO_REVIEW));
                 countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderOfStaff.do', '0.1' );";
                 item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.FEDBACK_TO_REVIEW.toString() + "',"), "share/images/document/12_xanh_hslanhdaophongdaxemxet.png", "Hồ sơ bị trả xem xét lại", filesNeedToReReview, 1l, "crimson", 4);
                 lstItem.add(item);
                 */
            }

            //Hồ sơ chờ phê duyệt
            if (loadCountFilesNeedToApprove) {
                Long posId = getUser().getPosId();
                //2- Hồ sơ đã ký = Đã phê duyệt (6)
                filesNeedToSign = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 10L, Constants.FILE_STATUS.APPROVED));
                getRequest().setAttribute("filesNeedToSign", filesNeedToSign);
                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeader.do', '0.1' );";
                HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 10L + "',"),
                        "share/images/document/11_xanh_hslanhdaocucdapheduyet.png",
                        "Hồ sơ đã ký",
                        filesNeedToSign, 1l, "crimson", 2);
                lstItem.add(item);

                if (posId == 57) {
                    //- hồ sơ cục trưởng có thể phân công phê duyệt
                    filesNeedToSign = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, -29L, Constants.FILE_STATUS.APPROVED));
                    getRequest().setAttribute("filesNeedToSign", filesNeedToSign);
                    countFilesNeedToReview = "doGoToMenu( 'filesAction!toApproveByCTPage.do', '0.1' );";
                    item = new HomeLiveTileForm(countFilesNeedToReview,
                            "share/images/document/11_xanh_hslanhdaocucdapheduyet.png",
                            "Hồ sơ chờ Cục Trưởng xem xét",
                            filesNeedToSign, 1l, "crimson", 2);
                    lstItem.add(item);
                    //- hồ sơ cục trưởng có thể phân công phê duyệt
                    filesNeedToSign = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, -23L, Constants.FILE_STATUS.APPROVED));
                    getRequest().setAttribute("filesNeedToSign", filesNeedToSign);
                    countFilesNeedToReview = "doGoToMenu( 'filesAction!toAssignApprovePage.do', '0.1' );";
                    item = new HomeLiveTileForm(countFilesNeedToReview,
                            "share/images/document/11_xanh_hslanhdaocucdapheduyet.png",
                            "Hồ sơ Cục Trưởng có thể phân công phê duyệt",
                            filesNeedToSign, 1l, "crimson", 2);
                    lstItem.add(item);
                } else {
                    //1- Hồ sơ chờ phê duyệt = Đã xem xét (5)
                    filesNeedToApprove = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 3l, Constants.FILE_STATUS.REVIEWED));
                    getRequest().setAttribute("filesNeedToApprove", filesNeedToApprove);
                    item = new HomeLiveTileForm(countFilesNeedToApprove.replace("',", "?searchForm.searchType=" + 3L + "',"),
                            "share/images/document/14_xanh_hsdaguiphanhoi.png",
                            "Hồ sơ chờ phê duyệt",
                            filesNeedToApprove, 1l, "#008000", 2);
                    lstItem.add(item);
                    //ho so cho lanh dao phe duyet thong bao sdbs (26)
                    filesNeedToSign = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 226L, Constants.FILE_STATUS.REVIEW_TO_ADD));
                    getRequest().setAttribute("filesNeedToSign", filesNeedToSign);
                    countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeaderApproveSdbs.do', '0.1' );";
                    item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 26L + "',"),
                            "share/images/document/11_xanh_hslanhdaocucdapheduyet.png",
                            "Hồ sơ chờ phê duyệt công văn thông báo SĐBS",
                            filesNeedToSign, 1l, "crimson", 3);
                    lstItem.add(item);
                    //u260515 binhnt53
                    //5- Hồ sơ đã trả kết quả  = Đã trả hồ sơ (22)
                    filesNeedToSign = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 36L, Constants.FILE_STATUS.GIVE_BACK));
                    getRequest().setAttribute("filesNeedToSign", filesNeedToSign);
                    countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeader.do', '0.1' );";
                    item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.searchType=" + 36L + "',"),
                            "share/images/document/11_xanh_hslanhdaocucdapheduyet.png",
                            "Hồ sơ đã trả kết quả",
                            filesNeedToSign, 1l, "crimson", 6);
                    lstItem.add(item);
                    //u260515 binhnt53
                }

                /*
                 //3- Hồ sơ đã trả xem xét lại (9)
                 filesNeedToSign = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 3l, Constants.FILE_STATUS.FEDBACK_TO_REVIEW));
                 getRequest().setAttribute("filesNeedToSign", filesNeedToSign);
                 countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeader.do', '0.1' );";
                 item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.FEDBACK_TO_REVIEW.toString() + "',"), "share/images/document/11_xanh_hslanhdaocucdapheduyet.png", "Hồ sơ đã trả xem xét lại", filesNeedToSign, 1l, "crimson", 4);
                 lstItem.add(item);
                 */
                /*
                 //4- Hồ sơ đã trả đối chiếu lại (25)
                 filesNeedToSign = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 3l, Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL));
                 getRequest().setAttribute("filesNeedToSign", filesNeedToSign);
                 countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeader.do', '0.1' );";
                 item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.status=" + Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL.toString() + "',"), "share/images/document/11_xanh_hslanhdaocucdapheduyet.png", "Hồ sơ đã trả đối chiếu lại", filesNeedToSign, 1l, "crimson", 5);
                 lstItem.add(item);
                 */
                //Tổng hồ sơ xử lý trong ngày lãnh đạo cục
//                filesStatistics = (Long) (long) (fdhe.getCountFileToProcess(userId, deptId, 25l, null));
//                getRequest().setAttribute("filesstatistics", filesStatistics);
//                countFilesNeedToReview = "doGoToMenu( 'filesAction!lookupFilesByLeader.do', '0.1' );";
//                item = new HomeLiveTileForm(countFilesNeedToReview.replace("',", "?searchForm.status=" + 42 + "',"), "share/images/document/15_xanh_tonghophs.png", "Tổng hồ sơ xử lý trong ngày", filesStatistics, 1l, "darkgray");
//                lstItem.add(item);
            }
            // man hinh ke toan - hieptq 15.11.14
            if (loadCountFilesPrepareFeePayManage) {
                // ho so da xac nhan phi tham xet thuc pham thuong
                filesFeePayManage = (Long) (long) feedhe.getCountFileToProcessFeePayManage(getUserId(), 1l, 0l);
                getRequest().setAttribute("filesFeePayManage", filesFeePayManage);
                countFilesNeedToPrepareFeePayManage = "doGoToMenu( 'filesAction!prepareFeePayManage.do', '0.1' );";
                //HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToPrepareFeePayManage.replace("',", "?searchFeeFormNew.status=" + 1 + "&searchFeeFormNew.productType=" + 2 + "&searchFeeFormNew.feePaymentType=-1" + "',"),
                HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToPrepareFeePayManage.replace("',", "?searchFeeFormNew.searchType=1" + "',"),
                        "share/images/document/15_xanh_tonghophs.png",
                        "Hồ sơ đã xác nhận phí thẩm xét nhóm thực phẩm thường",
                        filesFeePayManage, 1l, "#F0F0F0", 6);
                lstItem.add(item);

                // ho so chua xac nhan phi tham xet thuc pham thuong
                filesFeePayManageUnCheck = (Long) (long) feedhe.getCountFileToProcessFeePayManage(getUserId(), 0l, 0l);
                getRequest().setAttribute("filesFeePayManageUnCheck", filesFeePayManageUnCheck);
                countFilesNeedToPrepareFeePayManage = "doGoToMenu( 'filesAction!prepareFeePayManage.do', '0.1' );";
                //item = new HomeLiveTileForm(countFilesNeedToPrepareFeePayManage.replace("',", "?searchFeeFormNew.status=" + 0 + "&searchFeeFormNew.productType=" + 2 + "&searchFeeFormNew.feePaymentType=-1" + "',"),
                item = new HomeLiveTileForm(countFilesNeedToPrepareFeePayManage.replace("',", "?searchFeeFormNew.searchType=2" + "',"),
                        "share/images/document/15_xanh_tonghophs.png",
                        "Hồ sơ chưa xác nhận phí thẩm xét nhóm thực phẩm thường",
                        filesFeePayManageUnCheck, 1l, "#F0F0F0", 2);
                lstItem.add(item);

                // thuc pham chuc nang
                // ho so da xac nhan phi tham xet thuc pham chuc nang
                filesFeePayManageFunction = (Long) (long) feedhe.getCountFileToProcessFeePayManage(getUserId(), 1l, 1l);
                getRequest().setAttribute("filesFeePayManageFunction", filesFeePayManageFunction);
                countFilesNeedToPrepareFeePayManage = "doGoToMenu( 'filesAction!prepareFeePayManage.do', '0.1' );";
                //item = new HomeLiveTileForm(countFilesNeedToPrepareFeePayManage.replace("',", "?searchFeeFormNew.status=" + 1 + "&searchFeeFormNew.productType=" + 1 + "&searchFeeFormNew.feePaymentType=-1" + "',"),
                item = new HomeLiveTileForm(countFilesNeedToPrepareFeePayManage.replace("',", "?searchFeeFormNew.searchType=3" + "',"),
                        "share/images/document/15_xanh_tonghophs.png",
                        "Hồ sơ đã xác nhận phí thẩm xét các nhóm thực phẩm khác",
                        filesFeePayManageFunction, 1l, "#F0F0F0", 6);
                lstItem.add(item);

                // ho so chua xac nhan phi tham xet thuc pham chuc nang
                filesFeePayManageUnCheckFunction = (Long) (long) feedhe.getCountFileToProcessFeePayManage(getUserId(), 0l, 1l);
                getRequest().setAttribute("filesFeePayManageUnCheckFunction", filesFeePayManageUnCheckFunction);
                countFilesNeedToPrepareFeePayManage = "doGoToMenu( 'filesAction!prepareFeePayManage.do', '0.1' );";
                //item = new HomeLiveTileForm(countFilesNeedToPrepareFeePayManage.replace("',", "?searchFeeFormNew.status=" + 0 + "&searchFeeFormNew.productType=" + 1 + "&searchFeeFormNew.feePaymentType=-1" + "',"),
                item = new HomeLiveTileForm(countFilesNeedToPrepareFeePayManage.replace("',", "?searchFeeFormNew.searchType=4" + "',"),
                        "share/images/document/15_xanh_tonghophs.png",
                        "Hồ sơ chưa xác nhận phí thẩm xét các nhóm thục phẩm khác",
                        filesFeePayManageUnCheckFunction, 1l, "#F0F0F0", 2);
                lstItem.add(item);

                // ho so da xac nhan le phi cap so
                filesFeeManage = (Long) (long) feedhe.getCountFileToProcessFeeManage(getUserId(), 1l);
                getRequest().setAttribute("filesFeeManage", filesFeeManage);
                countFilesNeedToPrepareFeePayManage = "doGoToMenu( 'filesAction!prepareFeeManage.do', '0.1' );";
                //item = new HomeLiveTileForm(countFilesNeedToPrepareFeePayManage.replace("',", "?searchFeeFormNew.status=" + 1 + "',"),
                item = new HomeLiveTileForm(countFilesNeedToPrepareFeePayManage.replace("',", "?searchFeeFormNew.searchType=" + 5 + "',"),
                        "share/images/document/15_xanh_tonghophs.png",
                        "Hồ sơ đã xác nhận lệ phí cấp số",
                        filesFeeManage, 1l, "#F0F0F0", 6);
                lstItem.add(item);

                // ho so chua xac nhan le phi cap so
                filesFeeManageUnCheck = (Long) (long) feedhe.getCountFileToProcessFeeManage(getUserId(), 0l);
                getRequest().setAttribute("filesFeeManageUnCheck", filesFeeManageUnCheck);
                countFilesNeedToPrepareFeePayManage = "doGoToMenu( 'filesAction!prepareFeeManage.do', '0.1' );";
                // item = new HomeLiveTileForm(countFilesNeedToPrepareFeePayManage.replace("',", "?searchFeeFormNew.status=" + 0 + "',"),
                item = new HomeLiveTileForm(countFilesNeedToPrepareFeePayManage.replace("',", "?searchFeeFormNew.searchType=" + 6 + "',"),
                        "share/images/document/15_xanh_tonghophs.png",
                        "Hồ sơ chưa xác nhận lệ phí cấp số",
                        filesFeeManageUnCheck, 1l, "#F0F0F0", 2);
                lstItem.add(item);

            }

//Quản lý nộp - phí kế toán
//            if (loadCountFilesNeedToReceive) {
////1- Hồ sơ chờ tiếp nhận = Mới nộp và đã xác nhận phí (files.status = 1, fee_payment_info.status = 1, fee.fee_type=2), Mới nộp SĐBS (18)
//                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, -1L, Constants.FILE_STATUS.NEW);
////                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
//                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
//                HomeLiveTileForm item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=" + -1L + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ chờ tiếp nhận", filesNeedToReceive, 15l, "cadetblue",
//                        1);
//                lstItem.add(item);
////2- Hồ sơ đã tiếp nhận = Đã tiếp nhận (14)
//                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, 0L, Constants.FILE_STATUS.RECEIVED);
////                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
//                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
//                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=0&searchForm.status=" + Constants.FILE_STATUS.RECEIVED + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ đã tiếp nhận", filesNeedToReceive, 15l, "cadetblue",
//                        2);
//                lstItem.add(item);
//
//                //5- Hồ sơ đã yêu cầu nộp phí cấp số = Đã phê duyệt, chưa nộp lệ phí (files.status = 6, fee_payment_info.status = 0,fee.fee_type = 1 , files.isSignPdf=1)
//                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, -1L, Constants.FILE_STATUS.NEW);
////                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
//                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
//                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=" + -1L + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ đã yêu cầu nộp phí cấp số", filesNeedToReceive, 15l, "cadetblue",
//                        2);
//                lstItem.add(item);
////6- Hồ sơ đã nộp phí cấp số, chờ trả hồ sơ = Đã phê duyệt, đã nộp lệ phí (files.status = 6, fee_payment_info.status = 1, fee.fee_type=1, files.isSignPdf=2)
//                filesNeedToReceive = (Long) (long) fdhe.getCountFileOnHomePage(userId, deptId, -3L, Constants.FILE_STATUS.NEW);
////                getRequest().setAttribute("filesNeedToReceive", filesNeedToReceive);
//                countFilesNeedToReceive = "doGoToMenu('filesAction!lookupFilesByClerical.do', '0.1' );";
//                item = new HomeLiveTileForm(countFilesNeedToReceive.replace("',", "?searchForm.searchType=" + -3L + "',"), "share/images/document/14_xanh_hsdaguiphanhoi.png", "Hồ sơ đã nộp phí cấp số, chờ trả hồ sơ", filesNeedToReceive, 15l, "cadetblue",
//                        2);
//                lstItem.add(item);
//            }
            /*
             if (lstItem.size() < 4) {
             for (int i = lstItem.size() - 1; i < 4; i++) {
             HomeLiveTileForm item = new HomeLiveTileForm("","","",0l, 0l,"brown");
             lstItem.add(item);

             }
             }
             */
            getRequest().setAttribute("lstItem", lstItem);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return "home";

    }

    public String showChangePasswordPopup() {
        return "changePassword";
    }

    /**
     * action xu ly reset pass
     *
     * @return
     */
    public String onResetPass() {
        List msg = new ArrayList();
        UsersDAOHE usersDAOHE = new UsersDAOHE();
        try {
            String action = getRequest().getParameter("action");//updateUserInfo
            Session session = getSession();
            Long userId = getUserId();

            //Cap nhat thong tin nguoi dung
            if ("updateUserInfo".equals(action)) {
                Users user = usersDAOHE.getById("userId", userId);
                user = usersDAOHE.formToBo(user, userPasswordForm);
                session.update(user);
                msg.add("1");
                msg.add("Cập nhật thông tin người dùng thành công");
                this.jsonDataGrid.setItems(msg);
            } else {
                if ("updateBusinessInfo".equals(action)) {
                    Users user = usersDAOHE.getById("userId", userId);
                    BusinessDAOHE busdaohe = new BusinessDAOHE();
                    if (user != null && user.getBusinessId() != null && user.getBusinessId() > 0) {
                        Business bus = busdaohe.findById(user.getBusinessId());
                        if (userPasswordForm.getBusinessForm() != null) {
                            if (userPasswordForm.getBusinessForm().getBusinessAddress() != null && userPasswordForm.getBusinessForm().getBusinessAddress().length() > 0) {
                                bus.setBusinessAddress(userPasswordForm.getBusinessForm().getBusinessAddress());
                            }

                            if (userPasswordForm.getBusinessForm().getBusinessLawRep() != null && userPasswordForm.getBusinessForm().getBusinessLawRep().length() > 0) {
                                bus.setBusinessLawRep(userPasswordForm.getBusinessForm().getBusinessLawRep());
                            }

                            if (userPasswordForm.getBusinessForm().getBusinessEstablishYear() != null && userPasswordForm.getBusinessForm().getBusinessEstablishYear().length() > 0) {
                                bus.setBusinessEstablishYear(userPasswordForm.getBusinessForm().getBusinessEstablishYear());
                            }

                            if (userPasswordForm.getBusinessForm().getBusinessWebsite() != null && userPasswordForm.getBusinessForm().getBusinessWebsite().length() > 0) {
                                bus.setBusinessWebsite(userPasswordForm.getBusinessForm().getBusinessWebsite());
                            }

                            if (userPasswordForm.getBusinessForm().getBusinessFax() != null && userPasswordForm.getBusinessForm().getBusinessFax().length() > 0) {
                                bus.setBusinessFax(userPasswordForm.getBusinessForm().getBusinessFax());
                            }

                            if (userPasswordForm.getBusinessForm().getBusinessTelephone() != null && userPasswordForm.getBusinessForm().getBusinessTelephone().length() > 0) {
                                bus.setBusinessTelephone(userPasswordForm.getBusinessForm().getBusinessTelephone());
                            }

                            if (userPasswordForm.getBusinessForm().getBusinessProvince() != null && userPasswordForm.getBusinessForm().getBusinessProvince().length() > 0) {
                                bus.setBusinessProvince(userPasswordForm.getBusinessForm().getBusinessProvince());
                            }

                            if (userPasswordForm.getBusinessForm().getBusinessProvinceId() != null && userPasswordForm.getBusinessForm().getBusinessProvinceId() > 0L) {
                                bus.setBusinessProvinceId(userPasswordForm.getBusinessForm().getBusinessProvinceId());
                            }

                            if (userPasswordForm.getBusinessForm().getBusinessAddress() != null && userPasswordForm.getBusinessForm().getBusinessAddress().length() > 0) {
                                bus.setBusinessAddress(userPasswordForm.getBusinessForm().getBusinessAddress());
                            }

                            if (userPasswordForm.getBusinessForm().getBusinessLicense() != null && userPasswordForm.getBusinessForm().getBusinessLicense().length() > 0) {
                                bus.setBusinessLicense(userPasswordForm.getBusinessForm().getBusinessLicense());
                            }

                            if (userPasswordForm.getBusinessForm().getBusinessTaxCode() != null && userPasswordForm.getBusinessForm().getBusinessTaxCode().length() > 0) {
                                bus.setBusinessTaxCode(userPasswordForm.getBusinessForm().getBusinessTaxCode());
                            }

                            if (userPasswordForm.getBusinessForm().getBusinessNameAlias() != null && userPasswordForm.getBusinessForm().getBusinessNameAlias().length() > 0) {
                                bus.setBusinessNameAlias(userPasswordForm.getBusinessForm().getBusinessNameAlias());
                            }

                            if (userPasswordForm.getBusinessForm().getBusinessNameEng() != null && userPasswordForm.getBusinessForm().getBusinessNameEng().length() > 0) {
                                bus.setBusinessNameEng(userPasswordForm.getBusinessForm().getBusinessNameEng());
                            }

                            if (userPasswordForm.getBusinessForm().getBusinessName() != null && userPasswordForm.getBusinessForm().getBusinessName().length() > 0) {
                                bus.setBusinessName(userPasswordForm.getBusinessForm().getBusinessName());
                                user.setBusinessName(userPasswordForm.getBusinessForm().getBusinessName());//150206 U BINHNT53 UPDATE CAP NHAT TEN DOANH NGHIEP CUA USER KHI UPDATE THONG TIN DOANH NGHIEP
                            }

                            if (userPasswordForm.getBusinessForm().getBusinessTypeName() != null && userPasswordForm.getBusinessForm().getBusinessTypeName().length() > 0) {
                                bus.setBusinessTypeName(userPasswordForm.getBusinessForm().getBusinessTypeName());
                            }
                            if (userPasswordForm.getBusinessForm().getUserEmail() != null && userPasswordForm.getBusinessForm().getUserEmail().length() > 0) {
                                bus.setUserEmail(userPasswordForm.getBusinessForm().getUserEmail());
                            }
                            if (userPasswordForm.getBusinessForm().getBusinessTypeId() != null && userPasswordForm.getBusinessForm().getBusinessTypeId() > 0) {
                                bus.setBusinessTypeId(userPasswordForm.getBusinessForm().getBusinessTypeId());
                            }
                            //141214u binhnt53
                            if (userPasswordForm.getBusinessForm().getGoverningBody() != null && userPasswordForm.getBusinessForm().getGoverningBody().length() > 0) {
                                bus.setGoverningBody(userPasswordForm.getBusinessForm().getGoverningBody());
                            }
                            session.update(bus);
                            session.update(user);//150206 U BINHNT53 UPDATE CAP NHAT TEN DOANH NGHIEP CUA USER KHI UPDATE THONG TIN DOANH NGHIEP
                            msg.add("1");
                            msg.add("Cập nhật thông tin doanh nghiệp thành công");
                            this.jsonDataGrid.setItems(msg);
                        } else {
                            msg.add("3");
                            msg.add("Không thể cập nhật thông tin");
                            this.jsonDataGrid.setItems(msg);
                        }
                    }

                } else {
                    String password = "";
                    String oldPassword = "";

                    if (userPasswordForm != null) {
                        password = this.userPasswordForm.getPassword();
                        oldPassword = this.userPasswordForm.getOldPassword();
                    }

                    if (oldPassword == null) {
                        throw new Exception("password null");
                    }
                    if (oldPassword.trim().equals("")) {
                        throw new Exception("oldPassword is empty");
                    }
                    if (password == null) {
                        throw new Exception("password null");
                    }
                    if (password.trim().equals("")) {
                        throw new Exception("password is empty");
                    }
//            if (password.equals(oldPassword)) {
//                throw new Exception("password must be different from oldPassword ");
//            }
            /*
                     String iChars = "!@#$%^&*-";
                     int countTmp = 0;
                     for (int i = 0; i<password.length(); i++){
                     for (int j = 0; j <iChars.length(); j++){
                     if (password.charAt(i) == iChars.charAt(j)){
                     countTmp++;
                     }
                     }
                     }
                     if (countTmp == 0) {
                     throw new Exception("password must has one or more special characters");
                     }

                     countTmp = 0;
                     for (int i = 0; i<password.length(); i++){
                     if (Character.isLetter(password.charAt(i))){
                     countTmp++;
                     }
                     }
                     if (countTmp == 0) {
                     throw new Exception("password must has one or more anphabet characters");
                     }

                     countTmp = 0;
                     for (int i = 0; i<password.length(); i++){
                     if (Character.isDigit(password.charAt(i))){
                     countTmp++;
                     }
                     }
                     if (countTmp == 0) {
                     throw new Exception("password must has one or more number characters");
                     }
                     */

                    Users bo = new UsersDAOHE().findById(userId, false);
                    String encryptedOldPass = PasswordService.getInstance().encrypt(bo.getUserName().toLowerCase() + oldPassword);
                    String encryptedOldPassNoSalt = PasswordService.getInstance().encrypt(oldPassword);
                    if (encryptedOldPass.equals(bo.getPassword()) || encryptedOldPassNoSalt.equals(bo.getPassword()) || encryptedOldPass.equals(bo.getForgotPassword()) || encryptedOldPassNoSalt.equals(bo.getForgotPassword())) {
                        String passwordEncrypt = PasswordService.getInstance().encrypt(bo.getUserName().toLowerCase() + password);

                        if (userPasswordForm.getEmail() != null && userPasswordForm.getEmail().length() > 0) {
                            bo.setEmail(userPasswordForm.getEmail());
                        }
                        bo.setPassword(passwordEncrypt);
                        bo.setLastChangePassword(new Date());//binhnt53 u150105 cap nhat luu ngay nguoi dung thay doi mat khau
                        bo.setPasswordchanged(1L);
                        session.saveOrUpdate(bo);
                        msg.add("1");
                        msg.add("Thay đổi mật khẩu thành công");
                        this.jsonDataGrid.setItems(msg);
                        //sms
                        MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                        String smsmsg;
                        smsmsg = "Doanh nghiệp: " + bo.getBusinessName() + " vừa mới cập nhật mật khẩu, mật khẩu mới là: " + password;
                        msdhe.saveMessageSMS(userId, userId, smsmsg);
                        //email
                        MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                        String msge;
                        msge = "Doanh nghiệp: " + bo.getBusinessName() + " vừa mới cập nhật mật khẩu, mật khẩu mới là: " + password;
                        msedhe.saveMessageEmail(userId, userId, msge);
                    } else {
                        msg.add("3");
                        msg.add("Mật khẩu cũ không đúng");
                        this.jsonDataGrid.setItems(msg);
                        return "gridData";
                    }

                    if (userPasswordForm.getEmailPassword() != null && userPasswordForm.getEmail() != null
                            && !"".equals(userPasswordForm.getEmailPassword()) && !"".equals(userPasswordForm.getEmail())) {
                        EmailUser eu = new EmailUser();
                        eu.setUserId(userId);
                        eu.setEmailAddress(userPasswordForm.getEmail());
                        eu.setEmailPassword(encryptDataByUserName(userPasswordForm.getEmailPassword()));
                        session.saveOrUpdate(eu);
                    }
                }

            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            msg.add("3");
            msg.add("Thay đổi mật khẩu không thành công");
            this.jsonDataGrid.setItems(msg);
            return "gridData";
        }
        return "gridData";
    }

    private boolean checkRoleLTCQ(Long userId) {
        boolean result = false;
        try {
            String sql = "select count(r) from Roles r where r.roleCode = ? and r.roleId in (select ru.roleId from RoleUser ru where ru.isActive = 1 and ru.userId = ?)";
            Query query = getSession().createQuery(sql);
            query.setParameter(0, Constants.ROLES.CBLT_CQ);
            query.setParameter(1, getUserId());
            int total = Integer.parseInt(query.uniqueResult().toString());
            if (total != 0) {
                result = true;
            }
        } catch (Exception e) {
            log.error(e);
            return false;
        }
        return result;
    }

    public UsersForm getUserPasswordForm() {
        return userPasswordForm;
    }

    public void setUserPasswordForm(UsersForm userPasswordForm) {
        this.userPasswordForm = userPasswordForm;
    }

    public EventLogForm getSearchLogForm() {
        return searchLogForm;
    }

    public void setSearchLogForm(EventLogForm searchLogForm) {
        this.searchLogForm = searchLogForm;
    }
}
