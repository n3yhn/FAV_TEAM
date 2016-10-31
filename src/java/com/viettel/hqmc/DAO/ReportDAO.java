/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.Constants;
import com.viettel.common.util.DateTimeUtils;
import com.viettel.common.util.LogUtil;
import com.viettel.common.util.ReportUtil;
import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.FilesNoClob;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.hqmc.DAOHE.ProcedureDAOHE;
import com.viettel.hqmc.FORM.FeePaymentFileForm;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import com.viettel.voffice.database.DAOHibernate.ProcessDAOHE;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.SQLQuery;

/**
 *
 * @author dungu
 */
public class ReportDAO extends BaseDAO {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ReportDAO.class);
    private FilesForm searchForm;
    private FilesDAO fileDao = new FilesDAO();
    private FeePaymentFileForm searchFeeForm;
    private List lstCategory;
    private final String reportDayClerical = "reportDayClerical.page";
    private final String reportWeekClerical = "reportWeekClerical.page";
    private final String reportVT = "reportVT.page";
    private final String reportStaff = "reportStaff.page";
    private final String reportStaffAll = "reportStaffAll.page";
    private final String reportStaffOnRequest = "reportStaffOnRequest.page";
    private List lstProductType;
    private ArrayList lstProvince;
    private ArrayList lstRepositories;

    /**
     * Vao trang chon dang ky
     *
     * @return String
     */
    public String reportDayClericalPrepare() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstFileType", lstCategory);
        return reportDayClerical;
    }

    public String reportWeekClericalPrepare() {
        return reportWeekClerical;
    }

    public String reportVTPrepare() {
        return reportVT;
    }

    public String reportStaffPrepare() {
        CategoryDAOHE cdhe = new CategoryDAOHE();
        lstProductType = cdhe.findAllCategoryByFee("SP", 1l);
        lstProductType.add(0, new Category(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstProductType", lstProductType);
        return reportStaff;
    }

    public String reportStaffAllPrepare() {
//        CategoryDAOHE cdhe = new CategoryDAOHE();
//        lstProductType = cdhe.findAllCategoryByFee("SP", 1l);
//        lstProductType.add(0, new Category(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
//        getRequest().setAttribute("lstProductType", lstProductType);
        return reportStaffAll;
    }

    public String reportStaffOnRequestPrepare() {
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
        return reportStaffOnRequest;
    }

    /**
     *
     * Report Day Clerical
     *
     * @date 2013/11/28
     *
     * @author bachlx
     * @return
     */
    public void reportDayClerical() {
        try {
            String templateFile = "/WEB-INF/reportTemplate/reportDayClerical.xls";
            List<FilesNoClob> data;

            ConcurrentHashMap bean = new ConcurrentHashMap();
            data = fileDao.onsearchDayReportClerical(searchForm);
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i) != null && data.get(i).getStatus() != null && (data.get(i).getStatus().equals(Constants.FILE_STATUS.NEW_CREATE)
                        || data.get(i).getStatus().equals(Constants.FILE_STATUS.NEW)
                        || data.get(i).getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT))) {
                    data.remove(i);
                }
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String header = "";
//            if (searchForm.getReceivedDate() != null) {
//                header = "Từ ngày: " + dateFormat.format(searchForm.getReceivedDate());
//                if (searchForm.getReceivedDateTo() != null) {
//                    header = header + " - Đến ngày: " + dateFormat.format(searchForm.getReceivedDateTo());
//                }
//            } else {
//                if (searchForm.getReceivedDateTo() != null) {
//                    header = "Đến ngày: " + dateFormat.format(searchForm.getReceivedDateTo());
//                }
//            }
            //hieptq update 270415
//                       <sd:Option value="1">Hồ sơ đã tiếp nhận mới</sd:Option>
//                        <sd:Option value="2">Hồ sơ đã tiếp nhận sửa đổi bổ sung</sd:Option>
//                        <sd:Option value="3">Hồ sơ đã gửi công văn sửa đổi bổ sung</sd:Option>
//                        <sd:Option value="4">Hồ sơ đã trả bản công bố</sd:Option>
            if (searchForm.getSearchType() != -1) {
                if (searchForm.getSearchType() == 1) {
                    header = "Hồ sơ đã tiếp nhận mới ";
                }
                if (searchForm.getSearchType() == 2) {
                    header = "Hồ sơ đã tiếp nhận sửa đổi bổ sung ";
                }
                if (searchForm.getSearchType() == 3) {
                    header = "Hồ sơ đã gửi công văn sửa đổi bổ sung ";
                }
                if (searchForm.getSearchType() == 4) {
                    header = "Hồ sơ đã trả bản công bố ";
                }
            }

            if (searchForm.getSearchDateFrom() != null) {
                header += "Từ ngày: " + dateFormat.format(searchForm.getSearchDateFrom());
                if (searchForm.getSearchDateFrom() != null) {
                    header = header + " - Đến ngày: " + dateFormat.format(searchForm.getSearchDateTo());
                }
            } else if (searchForm.getSearchDateTo() != null) {
                header += "Đến ngày: " + dateFormat.format(searchForm.getSearchDateTo());
            }

            if (data == null) {
                data = new ArrayList<FilesNoClob>();
            }
            bean.put("header", header);
            bean.put("data", data);
            //bean.put("dateFormat", dateFormat);
            DateTimeUtils dateUtil = new DateTimeUtils();
            bean.put("ConvertTime", dateUtil);
            ReportUtil.exportReport(getRequest(), bean, templateFile, getResponse());
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex);
        }
    }

    public void reportVT() {
        try {
            String templateFile = "/WEB-INF/reportTemplate/reportDayKT.xls";
            //List<FilesNoClob> data;
            List<FeePaymentFileForm> data;
            List<FeePaymentFileForm> data3;
            List<FeePaymentFileForm> data2;
            Long spTM, spTM1, spTM2, spTM3, spCK, spCK1, spCK2, spCK3, spOL, spOL1, spOL2, spOL3, valueTM, valueTM1, valueTM2, valueTM3, valueCK, valueCK1, valueCK2, valueCK3, valueOL, valueOL1, valueOL2, valueOL3;
            Long value1, value2, value3, value4;
            Long total1, total2, total3, total4;
            Long checkCountTM = 0L;
            Long checkCountCK = 0L;
            Long checkCountOL = 0L;
            List lstParam = new ArrayList();
            ConcurrentHashMap bean = new ConcurrentHashMap();
            //data = fileDao.onsearchDayReportClerical();
            String sql = "";
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String header;

            if (searchFeeForm.getDateFrom() != null) {
                header = "Thống kê từ ngày: " + dateFormat.format(searchFeeForm.getDateFrom());
                if (searchFeeForm.getDateTo() != null) {
                    header = header + " - Đến ngày: " + dateFormat.format(searchFeeForm.getDateTo());
                }
            } else if (searchFeeForm.getDateTo() != null) {
                header = "Tiếp nhận đến ngày: " + dateFormat.format(searchFeeForm.getDateTo());
            } else {
                header = "Thống kê toàn bộ";
            }

            if (searchFeeForm.getDateFrom() != null) {
                sql += " and fpi.payment_date >= to_date( ? ,'dd/MM/yyyy hh24:mi:ss') ";
                String param = "" + DateTimeUtils.convertDateToString(searchFeeForm.getDateFrom(), "dd/MM/yyyy") + " 00:00:00";
                lstParam.add(param);
            }

            if (searchFeeForm.getDateTo() != null) {
                sql += " and fpi.payment_date <= to_date( ?,'dd/MM/yyyy hh24:mi:ss') ";
                String param = "" + DateTimeUtils.convertDateToString(searchFeeForm.getDateTo(), "dd/MM/yyyy") + " 23:59:59";
                lstParam.add(param);
            }
            // thuc pham thuong
            SQLQuery query1 = (SQLQuery) getSession().createSQLQuery("select wm_concat(fpi.payment_date) as payment_date ,wm_concat(fpi.payment_code) as payment_code,wm_concat(f.file_code) as file_code,f.business_name,wm_concat(fpi.fee_payment_type_id) as fee_payment_type_id, count(*) as sp,sum(fpi.cost) as total,ROW_NUMBER() OVER (ORDER BY f.business_name) idx from fee_payment_info fpi inner join files f  on fpi.file_id = f.file_id\n"
                    + "where (f.is_active = 1 or f.is_active = 2) and fpi.status = 1 and fpi.is_active = 1 and fpi.cost = 500000 " + sql + " group by f.business_name");

            // thuc pham chuc nang
            SQLQuery query2 = (SQLQuery) getSession().createSQLQuery("select wm_concat(fpi.payment_date) as payment_date ,wm_concat(fpi.payment_code) as payment_code,wm_concat(f.file_code) as file_code,f.business_name,wm_concat(fpi.fee_payment_type_id) as fee_payment_type_id, count(*) as sp,sum(fpi.cost) as total,ROW_NUMBER() OVER (ORDER BY f.business_name) idx from fee_payment_info fpi inner join files f  on fpi.file_id = f.file_id\n"
                    + "where (f.is_active = 1 or f.is_active = 2) and fpi.status = 1 and fpi.is_active = 1 and fpi.cost = 1500000 " + sql + " group by f.business_name");
            // le phi cap so

            SQLQuery query3 = (SQLQuery) getSession().createSQLQuery("select wm_concat(fpi.payment_date) as payment_date ,wm_concat(fpi.payment_code) as payment_code,wm_concat(f.file_code) as file_code,f.business_name,wm_concat(fpi.fee_payment_type_id) as fee_payment_type_id, count(*) as sp,sum(fpi.cost) as total,ROW_NUMBER() OVER (ORDER BY f.business_name) idx from fee_payment_info fpi inner join files f  on fpi.file_id = f.file_id \n"
                    + "where (f.is_active = 1 or f.is_active = 2) and fpi.status = 1 and fpi.is_active = 1  and fpi.fee_id = (select e.fee_id from fee e where e.fee_type = 1) " + sql + " group by f.business_name");

            for (int i = 0;
                    i < lstParam.size();
                    i++) {
                query1.setParameter(i, lstParam.get(i));
                query2.setParameter(i, lstParam.get(i));
                query3.setParameter(i, lstParam.get(i));
            }

            // thuc pham thuong
            List lstResult = query1.list();
            FeePaymentFileForm item = new FeePaymentFileForm();
            List result = new ArrayList<FeePaymentFileForm>();
            if (lstResult
                    != null && lstResult.size()
                    > 0) {
                for (int i = 0; i < lstResult.size(); i++) {
                    Object[] row = (Object[]) lstResult.get(i);
                    if (row.length > 0) {
                        //paymentDate
                        if (row[0] != null && !"".equals(row[0])) {
                            String paymentDate = row[0].toString();
                            String paymentDateNew = "";
                            List<String> myList = new ArrayList<>(Arrays.asList(paymentDate.split(",")));
                            if (myList.size() == 1) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
                                Date convertedCurrentDate = sdf.parse(paymentDate);
                                paymentDateNew = DateTimeUtils.convertDateToString(convertedCurrentDate, "dd/MM/yyyy");
                            } else {
                                for (int j = 0; j < myList.size(); j++) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
                                    Date convertedCurrentDate = sdf.parse(myList.get(j).toString());
                                    paymentDateNew += DateTimeUtils.convertDateToString(convertedCurrentDate, "dd/MM/yyyy") + "\n";

                                }
                            }
                            item.setPaymentDate(paymentDateNew);
                        }
                        //paymentCode
                        if (row[1] != null && !"".equals(row[1])) {
                            String paymentCode = row[1].toString();
                            String paymentCodeNew = "";
                            List<String> myList = new ArrayList<>(Arrays.asList(paymentCode.split(",")));
                            if (myList.size() == 1) {
                                paymentCodeNew = paymentCode;
                            } else {
                                for (int j = 0; j < myList.size(); j++) {
                                    paymentCodeNew += myList.get(j).toString() + "\n";
                                }
                            }
                            item.setPaymentCode(paymentCodeNew);
                        }
                        //fileCode
                        if (row[2] != null && !"".equals(row[2])) {
                            String fileCode = row[2].toString();
                            String fileCodeNew = "";
                            List<String> myList = new ArrayList<>(Arrays.asList(fileCode.split(",")));
                            if (myList.size() == 1) {
                                fileCodeNew = fileCode;
                            } else {
                                for (int j = 0; j < myList.size(); j++) {
                                    fileCodeNew += myList.get(j).toString() + "\n";
                                }
                            }
                            item.setFileCode(fileCodeNew);
                        }
                        // businessName
                        if (row[3] != null && !"".equals(row[3])) {
                            item.setBusinessName(row[3].toString());
                        }
                        // feepaymentType
                        if (row[4] != null && !"".equals(row[4])) {
                            String feePaymentType = row[4].toString();
                            Long countTM = 0l, countCK = 0l, countOL = 0l, totalTM = 0l, totalCK = 0l, totalOL = 0l;
                            List<String> myList = new ArrayList<>(Arrays.asList(feePaymentType.split(",")));
                            for (int j = 0; j < myList.size(); j++) {
                                if ("1".equals(myList.get(j).toString())) {
                                    countOL++;
                                    checkCountOL++;
                                }
                                if ("2".equals(myList.get(j).toString())) {
                                    countTM++;
                                    checkCountTM++;
                                }
                                if ("3".equals(myList.get(j).toString())) {
                                    countCK++;
                                    checkCountCK++;
                                }
                            }

                            if (countOL != null) {
                                totalOL = 500000 * countOL;
                            }
                            if (countCK != null) {
                                totalCK = 500000 * countCK;
                            }
                            if (countTM != null) {
                                totalTM = 500000 * countTM;
                            }
                            item.setCountCK(countCK);
                            item.setCountOL(countOL);
                            item.setCountTM(countTM);
                            item.setTotalCK(totalCK);
                            item.setTotalOL(totalOL);
                            item.setTotalTM(totalTM);
                        }
                        if (row[7] != null && !"".equals(row[7])) {
                            item.setIndex(Long.parseLong(row[7].toString()));
                        }

                    }
                    result.add(item);
                    item = new FeePaymentFileForm();
                }
            }

            spTM = checkCountTM;
            spOL = checkCountOL;
            spCK = checkCountCK;
            valueTM = 500000 * spTM;
            valueCK = 500000 * spCK;
            valueOL = 500000 * spOL;
            total1 = spTM + spOL + spCK;
            value1 = valueTM + valueCK + valueOL;

            //reset checkCount chay tiep nhung lan sau
            checkCountTM = 0l;
            checkCountCK = 0l;
            checkCountOL = 0l;

            // thuc pham chuc nang
            List lstResult2 = query2.list();
            FeePaymentFileForm item2 = new FeePaymentFileForm();
            List result2 = new ArrayList<FeePaymentFileForm>();
            if (lstResult2
                    != null && lstResult2.size()
                    > 0) {
                for (int i = 0; i < lstResult2.size(); i++) {
                    Object[] row = (Object[]) lstResult2.get(i);
                    if (row.length > 0) {
                        //paymentDate
                        if (row[0] != null && !"".equals(row[0])) {
                            String paymentDate = row[0].toString();
                            String paymentDateNew = "";
                            List<String> myList = new ArrayList<>(Arrays.asList(paymentDate.split(",")));
                            if (myList.size() == 1) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
                                Date convertedCurrentDate = sdf.parse(paymentDate);
                                paymentDateNew = DateTimeUtils.convertDateToString(convertedCurrentDate, "dd/MM/yyyy");
                            } else {
                                for (int j = 0; j < myList.size(); j++) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
                                    Date convertedCurrentDate = sdf.parse(myList.get(j).toString());
                                    paymentDateNew += DateTimeUtils.convertDateToString(convertedCurrentDate, "dd/MM/yyyy") + "\n";
                                }
                            }
                            item2.setPaymentDate(paymentDateNew);
                        }
                        //paymentCode
                        if (row[1] != null && !"".equals(row[1])) {
                            String paymentCode = row[1].toString();
                            String paymentCodeNew = "";
                            List<String> myList = new ArrayList<>(Arrays.asList(paymentCode.split(",")));
                            if (myList.size() == 1) {
                                paymentCodeNew = paymentCode;
                            } else {
                                for (int j = 0; j < myList.size(); j++) {
                                    paymentCodeNew += myList.get(j).toString() + "\n";
                                }
                            }
                            item2.setPaymentCode(paymentCodeNew);
                        }
                        //fileCode
                        if (row[2] != null && !"".equals(row[2])) {
                            String fileCode = row[2].toString();
                            String fileCodeNew = "";
                            List<String> myList = new ArrayList<>(Arrays.asList(fileCode.split(",")));
                            if (myList.size() == 1) {
                                fileCodeNew = fileCode;
                            } else {
                                for (int j = 0; j < myList.size(); j++) {
                                    fileCodeNew += myList.get(j).toString() + "\n";
                                }
                            }
                            item2.setFileCode(fileCodeNew);
                        }
                        // businessName
                        if (row[3] != null && !"".equals(row[3])) {
                            item2.setBusinessName(row[3].toString());
                        }
                        // feepaymentType
                        if (row[4] != null && !"".equals(row[4])) {
                            String feePaymentType = row[4].toString();
                            Long countTM = 0l, countCK = 0l, countOL = 0l, totalTM = 0l, totalCK = 0l, totalOL = 0l;
                            List<String> myList = new ArrayList<>(Arrays.asList(feePaymentType.split(",")));
                            for (int j = 0; j < myList.size(); j++) {
                                if ("1".equals(myList.get(j).toString())) {
                                    countOL++;
                                    checkCountOL++;
                                }
                                if ("2".equals(myList.get(j).toString())) {
                                    countTM++;
                                    checkCountTM++;
                                }
                                if ("3".equals(myList.get(j).toString())) {
                                    countCK++;
                                    checkCountCK++;
                                }
                            }
                            if (countOL != null) {
                                totalOL = 1500000 * countOL;
                            }
                            if (countCK != null) {
                                totalCK = 1500000 * countCK;
                            }
                            if (countTM != null) {
                                totalTM = 1500000 * countTM;
                            }
                            item2.setCountCK(countCK);
                            item2.setCountOL(countOL);
                            item2.setCountTM(countTM);
                            item2.setTotalCK(totalCK);
                            item2.setTotalOL(totalOL);
                            item2.setTotalTM(totalTM);
                        }
                        if (row[7] != null && !"".equals(row[7])) {
                            item2.setIndex(Long.parseLong(row[7].toString()));
                        }

                    }
                    result2.add(item2);
                    item2 = new FeePaymentFileForm();
                }
            }

            spTM1 = checkCountTM;
            spCK1 = checkCountCK;
            spOL1 = checkCountOL;
            valueTM1 = 1500000 * spTM1;
            valueCK1 = 1500000 * spCK1;
            valueOL1 = 1500000 * spOL1;
            total2 = spTM1 + spOL1 + spCK1;
            value2 = valueTM1 + valueCK1 + valueOL1;
            //reset checkCount chay tiep nhung lan sau
            checkCountTM = 0l;
            checkCountCK = 0l;
            checkCountOL = 0l;
            // le phi cap so
            List lstResult3 = query3.list();
            FeePaymentFileForm item3 = new FeePaymentFileForm();
            List result3 = new ArrayList<FeePaymentFileForm>();
            if (lstResult3
                    != null && lstResult3.size()
                    > 0) {
                for (int i = 0; i < lstResult3.size(); i++) {
                    Object[] row = (Object[]) lstResult3.get(i);
                    if (row.length > 0) {
                        //paymentDate
                        if (row[0] != null && !"".equals(row[0])) {
                            String paymentDate = row[0].toString();
                            String paymentDateNew = "";
                            List<String> myList = new ArrayList<>(Arrays.asList(paymentDate.split(",")));
                            if (myList.size() == 1) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
                                Date convertedCurrentDate = sdf.parse(paymentDate);
                                paymentDateNew = DateTimeUtils.convertDateToString(convertedCurrentDate, "dd/MM/yyyy");
                            } else {
                                for (int j = 0; j < myList.size(); j++) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
                                    Date convertedCurrentDate = sdf.parse(myList.get(j).toString());
                                    paymentDateNew += DateTimeUtils.convertDateToString(convertedCurrentDate, "dd/MM/yyyy") + "\n";
                                }
                            }
                            item3.setPaymentDate(paymentDateNew);
                        }
                        //paymentCode
                        if (row[1] != null && !"".equals(row[1])) {
                            String paymentCode = row[1].toString();
                            String paymentCodeNew = "";
                            List<String> myList = new ArrayList<>(Arrays.asList(paymentCode.split(",")));
                            if (myList.size() == 1) {
                                paymentCodeNew = paymentCode;
                            } else {
                                for (int j = 0; j < myList.size(); j++) {
                                    paymentCodeNew += myList.get(j).toString() + "\n";
                                }
                            }
                            item3.setPaymentCode(paymentCodeNew);
                        }
                        //fileCode
                        if (row[2] != null && !"".equals(row[2])) {
                            String fileCode = row[2].toString();
                            String fileCodeNew = "";
                            List<String> myList = new ArrayList<>(Arrays.asList(fileCode.split(",")));
                            if (myList.size() == 1) {
                                fileCodeNew = fileCode;
                            } else {
                                for (int j = 0; j < myList.size(); j++) {
                                    fileCodeNew += myList.get(j).toString() + "\n";
                                }
                            }
                            item3.setFileCode(fileCodeNew);
                        }
                        // businessName
                        if (row[3] != null && !"".equals(row[3])) {
                            item3.setBusinessName(row[3].toString());
                        }
                        // feepaymentType
                        if (row[4] != null && !"".equals(row[4])) {
                            String feePaymentType = row[4].toString();
                            Long countTM = 0l, countCK = 0l, countOL = 0l, totalTM = 0l, totalCK = 0l, totalOL = 0l;
                            List<String> myList = new ArrayList<>(Arrays.asList(feePaymentType.split(",")));
                            for (int j = 0; j < myList.size(); j++) {
                                if ("1".equals(myList.get(j).toString())) {
                                    countOL++;
                                    checkCountOL++;
                                }
                                if ("2".equals(myList.get(j).toString())) {
                                    countTM++;
                                    checkCountTM++;
                                }
                                if ("3".equals(myList.get(j).toString())) {
                                    countCK++;
                                    checkCountCK++;
                                }
                            }
                            if (countOL != null) {
                                totalOL = 150000 * countOL;
                            }
                            if (countCK != null) {
                                totalCK = 150000 * countCK;
                            }
                            if (countTM != null) {
                                totalTM = 150000 * countTM;
                            }
                            item3.setCountCK(countCK);
                            item3.setCountOL(countOL);
                            item3.setCountTM(countTM);
                            item3.setTotalCK(totalCK);
                            item3.setTotalOL(totalOL);
                            item3.setTotalTM(totalTM);
                        }
                        if (row[7] != null && !"".equals(row[7])) {
                            item3.setIndex(Long.parseLong(row[7].toString()));
                        }
                    }
                    result3.add(item3);
                    item3 = new FeePaymentFileForm();
                }
            }
            spTM2 = checkCountTM;
            spCK2 = checkCountCK;
            spOL2 = checkCountOL;
            valueTM2 = 150000 * spTM2;
            valueCK2 = 150000 * spCK2;
            valueOL2 = 150000 * spOL2;
            total3 = spTM2 + spOL2 + spCK2;
            value3 = valueTM2 + valueCK2 + valueOL2;
            //reset checkCount chay tiep nhung lan sau
            spTM3 = spTM + spTM1 + spTM2;
            spCK3 = spCK + spCK1 + spCK2;
            spOL3 = spOL + spOL1 + spOL2;
            valueTM3 = valueTM + valueTM1 + valueTM2;
            valueCK3 = valueCK + valueCK1 + valueCK2;
            valueOL3 = valueOL + valueOL1 + valueOL2;
            total4 = total1 + total2 + total3;
            value4 = value1 + value2 + value3;
            data = result;
            data2 = result2;
            data3 = result3;

            if (data
                    == null) {
                data = new ArrayList<FeePaymentFileForm>();
            }
            if (data2
                    == null) {
                data2 = new ArrayList<FeePaymentFileForm>();
            }
            if (data3
                    == null) {
                data3 = new ArrayList<FeePaymentFileForm>();
            }

            bean.put(
                    "header", header);
            bean.put(
                    "data", data);
            bean.put(
                    "data3", data3);
            bean.put(
                    "data2", data2);
            bean.put(
                    "dateFormat", dateFormat);
            bean.put(
                    "spTM", spTM);
            bean.put(
                    "spTM1", spTM1);
            bean.put(
                    "spTM2", spTM2);
            bean.put(
                    "spTM3", spTM3);
            bean.put(
                    "spCK", spCK);
            bean.put(
                    "spCK1", spCK1);
            bean.put(
                    "spCK2", spCK2);
            bean.put(
                    "spCK3", spCK3);
            bean.put(
                    "spOL", spOL);
            bean.put(
                    "spOL1", spOL1);
            bean.put(
                    "spOL2", spOL2);
            bean.put(
                    "spOL3", spOL3);
            bean.put(
                    "valueTM", valueTM);
            bean.put(
                    "valueTM1", valueTM1);
            bean.put(
                    "valueTM2", valueTM2);
            bean.put(
                    "valueTM3", valueTM3);
            bean.put(
                    "valueOL", valueOL);
            bean.put(
                    "valueOL1", valueOL1);
            bean.put(
                    "valueOL2", valueOL2);
            bean.put(
                    "valueOL3", valueOL3);
            bean.put(
                    "valueCK", valueCK);
            bean.put(
                    "valueCK1", valueCK1);
            bean.put(
                    "valueCK2", valueCK2);
            bean.put(
                    "valueCK3", valueCK3);
            bean.put(
                    "total1", total1);
            bean.put(
                    "total2", total2);
            bean.put(
                    "total3", total3);
            bean.put(
                    "total4", total4);
            bean.put(
                    "value1", value1);
            bean.put(
                    "value2", value2);
            bean.put(
                    "value3", value3);
            bean.put(
                    "value4", value4);
            ReportUtil.exportReport(getRequest(), bean, templateFile, getResponse());
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(e);
        }
    }

    //hieptq update 070115 bao cao cho chuyen vien, lanh dao
    public void reportStaff() {
        try {
            String templateFile = "/WEB-INF/reportTemplate/reportStaff.xls";
            List<FilesNoClob> data;
            ConcurrentHashMap bean = new ConcurrentHashMap();
            String sql = "";
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String header;
            List lstParam = new ArrayList();
            if (searchForm.getReceivedDate()
                    != null) {
                header = "Ký từ ngày: " + dateFormat.format(searchForm.getReceivedDate());
                if (searchForm.getReceivedDateTo() != null) {
                    header = header + " - Đến ngày: " + dateFormat.format(searchForm.getReceivedDateTo());
                }
            } else if (searchForm.getReceivedDateTo() != null) {
                header = "Ký đến ngày: " + dateFormat.format(searchForm.getReceivedDateTo());
            } else {
                header = "Thống kê toàn bộ";
            }

            if (searchForm.getReceivedDate()
                    != null) {
                sql += " and arp.sign_date >= to_date( ? ,'dd/MM/yyyy hh24:mi:ss') ";
                String param = "" + DateTimeUtils.convertDateToString(searchForm.getReceivedDate(), "dd/MM/yyyy") + " 00:00:00";
                lstParam.add(param);
            }

            if (searchForm.getReceivedDateTo()
                    != null) {
                sql += " and arp.sign_date <= to_date( ?,'dd/MM/yyyy hh24:mi:ss') ";
                String param = "" + DateTimeUtils.convertDateToString(searchForm.getReceivedDateTo(), "dd/MM/yyyy") + " 23:59:59";
                lstParam.add(param);
            }

            // hieptq update 160415
            if (searchForm.getProductTypeId()
                    != null && searchForm.getProductTypeId() != -1l) {
                sql += " and f.product_type_id = ? ";
                Long param = searchForm.getProductTypeId();
                lstParam.add(param);
            }

            if (searchForm.getIs30() != null && searchForm.getIs30() == 1) {
                sql += " and f.is_30 = 1  ";
            }
            // thuc pham thuong
            SQLQuery query = (SQLQuery) getSession().createSQLQuery("select f.business_name,"
                    + " f.file_code,"
                    + " f.business_address,"
                    + " b.business_telephone,"
                    + " f.product_name,"
                    + " f.product_type_name,"
                    + " f.nation_name,"
                    + " arp.receipt_no,"
                    + " arp.sign_date,"
                    + " dp.packate_material,"
                    + " dp.object_use,"
                    + " ROW_NUMBER() OVER (ORDER BY arp.sign_date) idx  from files f,"
                    + " announcement_receipt_paper arp,"
                    + " business b,"
                    + " detail_product dp"
                    + " where b.business_id = f.dept_id"
                    + " and f.announcement_receipt_paper_id = arp.announcement_receipt_paper_id"
                    + " and f.detail_product_id = dp.detail_product_id"
                    + " and f.is_active = 1 " + sql + " order by arp.sign_date ");

            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }

            // thuc pham thuong
            List lstResult = query.list();
            FilesForm item = new FilesForm();
            List result = new ArrayList<FilesForm>();
            if (lstResult != null && lstResult.size() > 0) {
                for (int i = 0; i < lstResult.size(); i++) {
                    Object[] row = (Object[]) lstResult.get(i);
                    if (row.length > 0) {
                        //businessName
                        if (row[0] != null && !"".equals(row[0])) {
                            String businessName = row[0].toString();
                            item.setBusinessName(businessName);
                        }
                        //fileCode
                        if (row[1] != null && !"".equals(row[1])) {
                            String fileCode = row[1].toString();
                            item.setFileCode(fileCode);
                        }
                        //busAnd
                        if (row[2] != null && !"".equals(row[2])) {
                            String businessAddress = row[2].toString();
                            item.setBusinessAddress(businessAddress);
                        }
                        // telephone
                        if (row[3] != null && !"".equals(row[3])) {
                            String telephone = row[3].toString();
                            item.setTelephone(telephone);
                        }
                        // productName
                        if (row[4] != null && !"".equals(row[4])) {
                            String productName = row[4].toString();
                            item.setProductName(productName);
                        }
                        // productTypeName
                        if (row[5] != null && !"".equals(row[5])) {
                            String productTypeName = row[5].toString();
                            item.setProductTypeName(productTypeName);
                        }
                        //nation
                        if (row[6] != null && !"".equals(row[6])) {
                            String nation = row[6].toString();
                            item.setNationName(nation);
                        }
                        // annoucementNo
                        if (row[7] != null && !"".equals(row[7])) {
                            String annoucementNo = row[7].toString();
                            item.setAnnoucementNo(annoucementNo);
                        }
                        // signdate
                        if (row[8] != null && !"".equals(row[8])) {
                            String signDate = row[8].toString();
                            item.setSignDateNew(signDate);
                        }
                        // packageMaterial
                        if (row[9] != null && !"".equals(row[9])) {
                            String pakageMaterial = row[9].toString();
                            item.setPackageMaterial(pakageMaterial);
                        }
                        // objectUse
                        if (row[10] != null && !"".equals(row[10])) {
                            String objectUse = row[10].toString();
                            item.setObjectUse(objectUse);
                        }
                        // index
                        if (row[11] != null && !"".equals(row[11])) {
                            Long index = Long.parseLong(row[11].toString());
                            item.setIndex(index);
                        }
                    }
                    result.add(item);
                    item = new FilesForm();
                }
            }
            data = result;
            if (data == null) {
                data = new ArrayList<FilesNoClob>();
            }
            bean.put("header", header);
            bean.put("data", data);
            bean.put("dateFormat", dateFormat);
            DateTimeUtils dateUtil = new DateTimeUtils();
            bean.put("ConvertTime", dateUtil);
            ReportUtil.exportReport(getRequest(), bean, templateFile, getResponse());
//            ReportUtil.exportReportSaveFileTemp(getRequest(), bean, templateFile);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
    }

    /**
     * 050209
     */
    public void reportWeekClerical() {
        try {
            String templateFile = "/WEB-INF/reportTemplate/reportWeekClerical.xls";
            List<FilesNoClob> data;
//            List<FilesNoClob> tiepnhansdbs;
            ConcurrentHashMap bean = new ConcurrentHashMap();
            if (searchForm != null && searchForm.getTypeDatetime().equals(Constants.TYPE_DATETIME.MONTH)) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(getSysdate());
//                int month = cal.get(Calendar.MONTH);
            }
            data = fileDao.onsearchWeekReportClerical(searchForm);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String header = "";
            if (searchForm.getSearchDateFrom() != null) {
                header = "Từ ngày: " + dateFormat.format(searchForm.getSearchDateFrom());
                if (searchForm.getSearchDateTo() != null) {
                    header = header + " - Đến ngày: " + dateFormat.format(searchForm.getSearchDateTo());
                }
            } else if (searchForm.getSearchDateTo() != null) {
                header = "Đến ngày: " + dateFormat.format(searchForm.getSearchDateTo());
            }
            if (data == null) {
                data = new ArrayList<FilesNoClob>();
            }
            bean.put("header", header);
            bean.put("data", data);
            DateTimeUtils dateUtil = new DateTimeUtils();
            bean.put("ConvertTime", dateUtil);
            ReportUtil.exportReport(getRequest(), bean, templateFile, getResponse());
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(e);
        }
    }

    // hieptq update 130515
    public void reportStaffOnRequest() {
        try {
            String templateFile = "/WEB-INF/reportTemplate/reportStaffOnRequest.xls";
            List<FilesNoClob> data;
            ConcurrentHashMap bean = new ConcurrentHashMap();
            String sql;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String header = "";
            List lstParam = new ArrayList();
            int check = 0;
            // co so cong bo, ngay ky, nguoi ky 
            if (searchForm.getSignerNameCheck() != null || searchForm.getAnnouncementNoCheck() != null || searchForm.getApproveDateFrom() != null || searchForm.getApproveDateTo() != null) {
                sql = "select distinct f.file_code,f.send_date,f.business_name,f.business_licence, f.product_type_name,f.nation_name,arp.SIGN_DATE,b.business_province\n"
                        + ",f.name_staff_process,f.file_type_name,f.display_status,b.business_address,arp.RECEIPT_NO,f.product_name,f.MANUFACTURE_NAME,arp.SIGNER_NAME "
                        + "from Files f, Process p, Detail_Product d, Business b , Announcement_Receipt_Paper arp \n"
                        + "where  f.detail_Product_Id = d.detail_Product_Id and f.file_Id = p.object_Id and f.dept_Id = b.business_Id \n"
                        + "and f.is_Active = 1 and p.receive_Group_Id = 3103 and (f.is_Temp is null or f.is_Temp = 0 ) and f.announcement_Receipt_Paper_Id = arp.announcement_Receipt_Paper_Id \n";
                if (searchForm.getApproveDateFrom()
                        != null) {
                    sql += " and arp.sign_date >= to_date( ? ,'dd/MM/yyyy hh24:mi:ss') ";
                    String param = "" + DateTimeUtils.convertDateToString(searchForm.getApproveDateFrom(), "dd/MM/yyyy") + " 00:00:00";
                    lstParam.add(param);
                }

                if (searchForm.getApproveDateTo()
                        != null) {
                    sql += " and arp.sign_date <= to_date( ?,'dd/MM/yyyy hh24:mi:ss') ";
                    String param = "" + DateTimeUtils.convertDateToString(searchForm.getApproveDateTo(), "dd/MM/yyyy") + " 23:59:59";
                    lstParam.add(param);
                }
                if (searchForm.getSendDateFrom()
                        != null) {
                    sql += " and f.send_date >= to_date( ? ,'dd/MM/yyyy hh24:mi:ss') ";
                    String param = "" + DateTimeUtils.convertDateToString(searchForm.getSendDateFrom(), "dd/MM/yyyy") + " 00:00:00";
                    lstParam.add(param);
                }

                if (searchForm.getSendDateTo()
                        != null) {
                    sql += " and f.send_date <= to_date( ?,'dd/MM/yyyy hh24:mi:ss') ";
                    String param = "" + DateTimeUtils.convertDateToString(searchForm.getSendDateTo(), "dd/MM/yyyy") + " 23:59:59";
                    lstParam.add(param);
                }
                if (searchForm.getIs30() != null && searchForm.getIs30() == 1) {
                    sql += " and f.is_30 = 1  ";
                }
                // hieptq update 190515
                //fileCode
                if (searchForm.getFileCode() != null && searchForm.getFileCode().length() > 0) {
                    sql += " and lower(f.file_code) like ? ESCAPE '/'";
                    lstParam.add(StringUtils.toLikeString(searchForm.getFileCode().toLowerCase().trim()));
                }
                //fileType
                if (searchForm.getFileType() != -1l) {
                    sql += " and f.file_type = ? ";
                    lstParam.add(searchForm.getFileType());
                }
                //tinh thanh pho
                if (searchForm.getBusinessProvinceId() != -1l) {
                    sql += " and b.business_province_id = ? ";
                    lstParam.add(searchForm.getBusinessProvinceId());
                }
                //trang thai
                if (searchForm.getStatus() != -1l) {
                    sql += " and f.status = ? ";
                    lstParam.add(searchForm.getStatus());
                }
                // businessName
                if (searchForm.getBusinessName() != null && searchForm.getBusinessName().length() > 0) {
                    sql += " and lower(f.dept_name) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getBusinessName().toLowerCase().trim()));
                }
                //bus address
                if (searchForm.getBusinessAddress() != null && searchForm.getBusinessAddress().length() > 0) {
                    sql += " and lower(b.business_address) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getBusinessAddress().toLowerCase().trim()));
                }
                // bus licence
                if (searchForm.getBusinessLicence() != null && searchForm.getBusinessLicence().length() > 0) {
                    sql += " and lower(b.business_license) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getBusinessLicence().toLowerCase().trim()));
                }
                //annoucementNo
                if (searchForm.getAnnoucementNo() != null && searchForm.getAnnoucementNo().length() > 0) {
                    sql += " and lower(arp.receipt_no) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getAnnoucementNo().toLowerCase().trim()));
                }
                // productTypeId
                if (searchForm.getProductTypeId() != -1l) {
                    sql += " and f.product_type_id = ? ";
                    lstParam.add(searchForm.getProductTypeId());
                }
                //    productName
                if (searchForm.getProductName() != null && searchForm.getProductName().length() > 0) {
                    sql += " and lower(f.product_name) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getProductName().toLowerCase().trim()));
                }
                // nationName
                if (searchForm.getNationName() != null && searchForm.getNationName().length() > 0) {
                    sql += " and lower(f.nation_name) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getNationName().toLowerCase().trim()));
                }
                //manufactureName
                if (searchForm.getManufactureName() != null && searchForm.getManufactureName().length() > 0) {
                    sql += " and lower(f.manufacture_name) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getManufactureName().toLowerCase().trim()));
                }
                //signerName
                if (searchForm.getSignerName() != null && searchForm.getSignerName().length() > 0) {
                    sql += " and lower(arp.signer_name) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getSignerName().toLowerCase().trim()));
                }
                //nameStaffProcess
                if (searchForm.getNameStaffProcess() != null && searchForm.getNameStaffProcess().length() > 0) {
                    sql += " and lower(f.name_staff_process) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getNameStaffProcess().toLowerCase().trim()));
                }

                sql += " order by f.send_Date ASC";
            } // khong co
            else {
                sql = "select distinct f.file_code,f.send_date,f.business_name,f.business_licence, f.product_type_name,f.nation_name,b.business_province\n"
                        + ",f.name_staff_process,f.file_type_name,f.display_status,b.business_address,f.product_name,f.MANUFACTURE_NAME  from Files f, Process p, Detail_Product d, Business b  where 1=1 \n"
                        + "and f.file_Id = p.object_Id and  f.detail_Product_Id = d.detail_Product_Id and f.dept_Id = b.business_Id \n"
                        + "and f.is_Active = 1 and f.file_Id = p.object_Id  and (f.is_Temp is null or f.is_Temp = 0 )\n"
                        + "and p.receive_Group_Id = 3103";
                if (searchForm.getSendDateFrom()
                        != null) {
                    sql += " and f.send_date >= to_date( ? ,'dd/MM/yyyy hh24:mi:ss') ";
                    String param = "" + DateTimeUtils.convertDateToString(searchForm.getSendDateFrom(), "dd/MM/yyyy") + " 00:00:00";
                    lstParam.add(param);
                }

                if (searchForm.getSendDateTo()
                        != null) {
                    sql += " and f.send_date <= to_date( ?,'dd/MM/yyyy hh24:mi:ss') ";
                    String param = "" + DateTimeUtils.convertDateToString(searchForm.getSendDateTo(), "dd/MM/yyyy") + " 23:59:59";
                    lstParam.add(param);
                }

                if (searchForm.getIs30() != null && searchForm.getIs30() == 1) {
                    sql += " and f.is_30 = 1  ";
                }

                //fileCode
                if (searchForm.getFileCode() != null && searchForm.getFileCode().length() > 0) {
                    sql += " and lower(f.file_code) like ? ESCAPE '/'";
                    lstParam.add(StringUtils.toLikeString(searchForm.getFileCode().toLowerCase().trim()));
                }
                //fileType
                if (searchForm.getFileType() != -1l) {
                    sql += " and f.file_type = ? ";
                    lstParam.add(searchForm.getFileType());
                }
                //tinh thanh pho
                if (searchForm.getBusinessProvinceId() != -1l) {
                    sql += " and b.business_province_id = ? ";
                    lstParam.add(searchForm.getBusinessProvinceId());
                }
                //trang thai
                if (searchForm.getStatus() != -1l) {
                    sql += " and f.status = ? ";
                    lstParam.add(searchForm.getStatus());
                }
                // businessName
                if (searchForm.getBusinessName() != null && searchForm.getBusinessName().length() > 0) {
                    sql += " and lower(f.dept_name) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getBusinessName().toLowerCase().trim()));
                }
                //bus address
                if (searchForm.getBusinessAddress() != null && searchForm.getBusinessAddress().length() > 0) {
                    sql += " and lower(b.business_address) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getBusinessAddress().toLowerCase().trim()));
                }
                // bus licence
                if (searchForm.getBusinessLicence() != null && searchForm.getBusinessLicence().length() > 0) {
                    sql += " and lower(b.business_license) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getBusinessLicence().toLowerCase().trim()));
                }
                //annoucementNo
                if (searchForm.getAnnoucementNo() != null && searchForm.getAnnoucementNo().length() > 0) {
                    sql += " and lower(arp.receipt_no) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getAnnoucementNo().toLowerCase().trim()));
                }
                // productTypeId
                if (searchForm.getProductTypeId() != -1l) {
                    sql += " and f.product_type_id = ? ";
                    lstParam.add(searchForm.getProductTypeId());
                }
                //    productName
                if (searchForm.getProductName() != null && searchForm.getProductName().length() > 0) {
                    sql += " and lower(f.product_name) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getProductName().toLowerCase().trim()));
                }
                // nationName
                if (searchForm.getNationName() != null && searchForm.getNationName().length() > 0) {
                    sql += " and lower(f.nation_name) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getNationName().toLowerCase().trim()));
                }
                //manufactureName
                if (searchForm.getManufactureName() != null && searchForm.getManufactureName().length() > 0) {
                    sql += " and lower(f.manufacture_name) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getManufactureName().toLowerCase().trim()));
                }
                //signerName
                if (searchForm.getSignerName() != null && searchForm.getSignerName().length() > 0) {
                    sql += " and lower(arp.signer_name) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getSignerName().toLowerCase().trim()));
                }
                //nameStaffProcess
                if (searchForm.getNameStaffProcess() != null && searchForm.getNameStaffProcess().length() > 0) {
                    sql += " and lower(f.name_staff_process) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(searchForm.getNameStaffProcess().toLowerCase().trim()));
                }

                sql += " order by f.send_Date ASC";
                check = 1;
            }

            SQLQuery query = (SQLQuery) getSession().createSQLQuery(sql);

            for (int i = 0;
                    i < lstParam.size();
                    i++) {

                query.setParameter(i, lstParam.get(i));

            }

            List lstResult = query.list();
            FilesForm item = new FilesForm();
            List result = new ArrayList<FilesForm>();
            if (lstResult
                    != null && lstResult.size()
                    > 0) {
                for (int i = 0; i < lstResult.size(); i++) {
                    Object[] row = (Object[]) lstResult.get(i);
                    if (row.length > 0) {
                        if (check == 1) {
                            //fileCode
                            if (row[0] != null && !"".equals(row[0])) {
                                String fileCode = row[0].toString();
                                item.setFileCode(fileCode);
                            }
                            //sendDate
                            if (row[1] != null && !"".equals(row[1])) {
                                String sendDate = row[1].toString();
                                item.setSendDateNew(sendDate);
                            }
                            //businessName
                            if (row[2] != null && !"".equals(row[2])) {
                                String businessName = row[2].toString();
                                item.setBusinessName(businessName);
                            }

                            //buslicense
                            if (row[3] != null && !"".equals(row[3])) {
                                String businessLicense = row[3].toString();
                                item.setBusinessLicence(businessLicense);
                            }
                            //productTypeName
                            if (row[4] != null && !"".equals(row[4])) {
                                String productTypeName = row[4].toString();
                                item.setProductTypeName(productTypeName);
                            }
                            //nationName
                            if (row[5] != null && !"".equals(row[5])) {
                                String nationName = row[5].toString();
                                item.setNationName(nationName);
                            }

                            //bus province
                            if (row[6] != null && !"".equals(row[6])) {
                                String businessProvince = row[6].toString();
                                item.setBusinessProvince(businessProvince);
                            }
                            // name staff process
                            if (row[7] != null && !"".equals(row[7])) {
                                String nameStaftProcess = row[7].toString();
                                item.setNameStaffProcess(nameStaftProcess);
                            }
                            // fileTypeName
                            if (row[8] != null && !"".equals(row[8])) {
                                String fileTypeName = row[8].toString();
                                item.setFileTypeName(fileTypeName);
                            }
                            //display status
                            if (row[9] != null && !"".equals(row[9])) {
                                String displayStatus = row[9].toString();
                                item.setDisplayStatus(displayStatus);
                            }
                            //businessAddress
                            if (row[10] != null && !"".equals(row[10])) {
                                String businessAddress = row[10].toString();
                                item.setBusinessAddress(businessAddress);
                            }
                            // productName
                            if (row[11] != null && !"".equals(row[11])) {
                                String productName = row[11].toString();
                                item.setProductName(productName);
                            }
                            // manufactureName
                            if (row[12] != null && !"".equals(row[12])) {
                                String manufactureName = row[12].toString();
                                item.setManufactureName(manufactureName);
                            }
                        } else {
                            //fileCode
                            if (row[0] != null && !"".equals(row[0])) {
                                String fileCode = row[0].toString();
                                item.setFileCode(fileCode);
                            }
                            //sendDate
                            if (row[1] != null && !"".equals(row[1])) {
                                String sendDate = row[1].toString();
                                item.setSendDateNew(sendDate);
                            }
                            //businessName
                            if (row[2] != null && !"".equals(row[2])) {
                                String businessName = row[2].toString();
                                item.setBusinessName(businessName);
                            }

                            //buslicense
                            if (row[3] != null && !"".equals(row[3])) {
                                String businessLicense = row[3].toString();
                                item.setBusinessLicence(businessLicense);
                            }
                            //productTypeName
                            if (row[4] != null && !"".equals(row[4])) {
                                String productTypeName = row[4].toString();
                                item.setProductTypeName(productTypeName);
                            }
                            //nationName
                            if (row[5] != null && !"".equals(row[5])) {
                                String nationName = row[5].toString();
                                item.setNationName(nationName);
                            }
                            //signDateNew
                            if (row[6] != null && !"".equals(row[6])) {
                                String signDateNew = row[6].toString();
                                item.setSignDateNew(signDateNew);
                            }
                            //bus province
                            if (row[7] != null && !"".equals(row[7])) {
                                String businessProvince = row[7].toString();
                                item.setBusinessProvince(businessProvince);
                            }
                            // name staff process
                            if (row[8] != null && !"".equals(row[8])) {
                                String nameStaftProcess = row[8].toString();
                                item.setNameStaffProcess(nameStaftProcess);
                            }
                            // fileTypeName
                            if (row[9] != null && !"".equals(row[9])) {
                                String fileTypeName = row[9].toString();
                                item.setFileTypeName(fileTypeName);
                            }
                            //display status
                            if (row[10] != null && !"".equals(row[10])) {
                                String displayStatus = row[10].toString();
                                item.setDisplayStatus(displayStatus);
                            }
                            //businessAddress
                            if (row[11] != null && !"".equals(row[11])) {
                                String businessAddress = row[11].toString();
                                item.setBusinessAddress(businessAddress);
                            }
                            // receiptNo
                            if (row[12] != null && !"".equals(row[12])) {
                                String receiptNo = row[12].toString();
                                item.setReceiptNo(receiptNo);
                            }

                            // productName
                            if (row[13] != null && !"".equals(row[13])) {
                                String productName = row[13].toString();
                                item.setProductName(productName);
                            }
                            // manufactureName
                            if (row[14] != null && !"".equals(row[14])) {
                                String manufactureName = row[14].toString();
                                item.setManufactureName(manufactureName);
                            }
                            //signer
                            if (row[15] != null && !"".equals(row[15])) {
                                String signerName = row[15].toString();
                                item.setSignerName(signerName);
                            }
                        }
                        // index
//                        if (row[16] != null && !"".equals(row[16])) {
//                            Long index = Long.parseLong(row[16].toString());
//                            item.setIndex(index);
//                        }

                    }
                    result.add(item);
                    item = new FilesForm();
                }
            }
            data = result;
            if (data == null) {
                data = new ArrayList<FilesNoClob>();
            }
            bean.put("header", header);
            bean.put("data", data);
            bean.put("dateFormat", dateFormat);
            DateTimeUtils dateUtil = new DateTimeUtils();
            bean.put("ConvertTime", dateUtil);
            String fileTemp = ReportUtil.exportReportSaveFileTemp(getRequest(), bean, templateFile);
            InputStream myxls = new FileInputStream(fileTemp);//get file excel
            Date newDate = new Date();
//fix sonar
//            ResourceBundle rb = ResourceBundle.getBundle("config");
//            String filePath = rb.getString("report_excel_temp");

//            String fullFilePath = filePath + "report_" + newDate.getTime() + ".xls";
//            File file = new File(fullFilePath);
//            FileOutputStream fop = new FileOutputStream(file);;
            HSSFWorkbook wb = new HSSFWorkbook(myxls);
            HSSFSheet sheet = wb.getSheetAt(0);
            // check hien thi cot
            if (searchForm.getSignerNameCheck() != null || searchForm.getAnnouncementNoCheck() != null || searchForm.getApproveDateFrom() != null || searchForm.getApproveDateTo() != null) {
                sheet.setColumnHidden((short) 0, true);
                if (searchForm.getFileCodeCheck() == null) {
                    sheet.setColumnHidden((short) 1, true);
                }
                if (searchForm.getSendDateFrom() == null && searchForm.getSendDateTo() == null) {
                    sheet.setColumnHidden((short) 2, true);
                }
                if (searchForm.getBusinessNameCheck() == null) {
                    sheet.setColumnHidden((short) 3, true);
                }
                if (searchForm.getBusinessLicenceCheck() == null) {
                    sheet.setColumnHidden((short) 4, true);
                }
                if (searchForm.getProductTypeNameCheck() == null) {
                    sheet.setColumnHidden((short) 5, true);
                }
                if (searchForm.getNationNameCheck() == null) {
                    sheet.setColumnHidden((short) 6, true);
                }
                if (searchForm.getApproveDateFrom()
                        == null && searchForm.getApproveDateTo()
                        == null) {
                    sheet.setColumnHidden((short) 7, true);
                }
                if (searchForm.getBusinessProvinceCheck() == null) {
                    sheet.setColumnHidden((short) 8, true);
                }
                if (searchForm.getNameStaffProcessCheck() == null) {
                    sheet.setColumnHidden((short) 9, true);
                }
                if (searchForm.getFileTypeNameCheck() == null) {
                    sheet.setColumnHidden((short) 10, true);
                }
                if (searchForm.getDisplayStatusCheck() == null) {
                    sheet.setColumnHidden((short) 11, true);
                }
                if (searchForm.getBusinessAddressCheck() == null) {
                    sheet.setColumnHidden((short) 12, true);
                }
                if (searchForm.getAnnouncementNoCheck() == null) {
                    sheet.setColumnHidden((short) 13, true);
                }
                if (searchForm.getProductNameCheck() == null) {
                    sheet.setColumnHidden((short) 14, true);
                }
                if (searchForm.getManufactureNameCheck() == null) {
                    sheet.setColumnHidden((short) 15, true);
                }
                if (searchForm.getSignerNameCheck() == null) {
                    sheet.setColumnHidden((short) 16, true);
                }

            } else {
                sheet.setColumnHidden((short) 0, true);
                sheet.setColumnHidden((short) 7, true);
                sheet.setColumnHidden((short) 13, true);
                sheet.setColumnHidden((short) 16, true);
                if (searchForm.getFileCodeCheck() == null) {
                    sheet.setColumnHidden((short) 1, true);
                }
                if (searchForm.getSendDateFrom() == null && searchForm.getSendDateTo() == null) {
                    sheet.setColumnHidden((short) 2, true);
                }
                if (searchForm.getBusinessNameCheck() == null) {
                    sheet.setColumnHidden((short) 3, true);
                }
                if (searchForm.getBusinessLicenceCheck() == null) {
                    sheet.setColumnHidden((short) 4, true);
                }
                if (searchForm.getProductTypeNameCheck() == null) {
                    sheet.setColumnHidden((short) 5, true);
                }
                if (searchForm.getNationNameCheck() == null) {
                    sheet.setColumnHidden((short) 6, true);
                }
                if (searchForm.getBusinessProvinceCheck() == null) {
                    sheet.setColumnHidden((short) 8, true);
                }
                if (searchForm.getNameStaffProcessCheck() == null) {
                    sheet.setColumnHidden((short) 9, true);
                }
                if (searchForm.getFileTypeNameCheck() == null) {
                    sheet.setColumnHidden((short) 10, true);
                }
                if (searchForm.getDisplayStatusCheck() == null) {
                    sheet.setColumnHidden((short) 11, true);
                }
                if (searchForm.getBusinessAddressCheck() == null) {
                    sheet.setColumnHidden((short) 12, true);
                }
                if (searchForm.getProductNameCheck() == null) {
                    sheet.setColumnHidden((short) 14, true);
                }
                if (searchForm.getManufactureNameCheck() == null) {
                    sheet.setColumnHidden((short) 15, true);
                }
            }

            HttpServletResponse res = getResponse();
            res.setContentType("application/vnd.ms-excel");
            res.setHeader("Content-Disposition", "attachment; filename=report_" + newDate.getTime() + ".xls");
            res.setHeader("Content-Type", "application/vnd.ms-excel");
            wb.write(res.getOutputStream());
            res.getOutputStream().flush();
            //fop.close();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(e);
        }
    }

    public FilesForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(FilesForm searchForm) {
        this.searchForm = searchForm;
    }

    public FeePaymentFileForm getSearchFeeForm() {
        return searchFeeForm;
    }

    public void setSearchFeeForm(FeePaymentFileForm searchFeeForm) {
        this.searchFeeForm = searchFeeForm;
    }

    public List getLstCategory() {
        return lstCategory;
    }

    public void setLstCategory(List lstCategory) {
        this.lstCategory = lstCategory;
    }

    public String onsearchReportStaffProcessAll() {
        getGridInfo();
        ProcessDAOHE bdhe = new ProcessDAOHE();
        GridResult gr = bdhe.getStaffProcessFiles(searchForm.getSearchDateFrom(), searchForm.getSearchDateTo(), getDepartmentId(), start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }
}
