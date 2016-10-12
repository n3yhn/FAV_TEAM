/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.TimeProcess;
import com.viettel.hqmc.DAOHE.TimeProcessDAOHE;
import com.viettel.hqmc.FORM.TimeProcessForm;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.EventLogDAOHE;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author binhnt53
 */
public class TimeProcessDAO extends BaseDAO {

    private TimeProcessForm createForm;
    private TimeProcessForm searchForm;
    private final String forwardPage = "timeProcessPage";
    private TimeProcessDAOHE userAttachsDAOHE;
    private List<TimeProcessForm> lstItemOnGrid;
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TimeProcessDAO.class);
//==============================================================================

    /**
     *
     * @return
     */
    public String getUserAttach() {
        getGridInfo();
        List customInfo = new ArrayList();
        userAttachsDAOHE = new TimeProcessDAOHE();
        GridResult result = userAttachsDAOHE.getLstUserAttach(getUserId(), start, count, sortField);
        jsonDataGrid.setItems(result.getLstResult());
        jsonDataGrid.setTotalRows(result.getnCount().intValue());
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String prepare() {
        try {
            if (createForm != null) {
                searchForm = new TimeProcessForm();
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return this.forwardPage;
    }

    /**
     *
     * @return
     */
    public String onInsert() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        try {
            userAttachsDAOHE = new TimeProcessDAOHE();
            if (userAttachsDAOHE.isDuplicate(createForm)) {
                resultMessage.add("3");
                resultMessage.add("Ngày bị trùng hoặc sai");
            } else {
                Long categoryTypeId = createForm.getTimeProcessId();
                if (categoryTypeId == null) {
                    if (createForm.getTimeProcessDateFrom().before(createForm.getTimeProcessDateTo())) {//insert vao list
                        Date from = createForm.getTimeProcessDateFrom();
                        Date to = createForm.getTimeProcessDateTo();
                        Calendar temp = DateToCalendar(to);
                        temp.add(Calendar.DATE, 1);
                        to = CalendarToDate(temp);
                        while (to.after(from)) {
                            Calendar calFrom = DateToCalendar(from);
                            TimeProcess bo = createForm.formToBo();
                            bo.setCreateDate(getSysdate());
                            bo.setCreatedBy(getUserId());
                            bo.setTimeProcessDate(CalendarToDate(calFrom));
                            getSession().save(bo);
                            calFrom.add(Calendar.DATE, 1);
                            from = CalendarToDate(calFrom);
                            EventLogDAOHE edhe = new EventLogDAOHE();
                            edhe.insertEventLog("TIME_PROCESS", "Thêm mới TimeProcessId=" + bo.getTimeProcessId(), getRequest());
                        }
                        resultMessage.add("1");
                        resultMessage.add("Thêm mới thành công");
                    } else {
                        if (createForm.getTimeProcessDateFrom().equals(createForm.getTimeProcessDateTo())) {
                            TimeProcess bo = createForm.formToBo();
                            bo.setTimeProcessDate(createForm.getTimeProcessDateFrom());
                            bo.setCreateDate(getSysdate());
                            bo.setCreatedBy(getUserId());
                            getSession().save(bo);
                            resultMessage.add("1");
                            resultMessage.add("Thêm mới thành công");
                            EventLogDAOHE edhe = new EventLogDAOHE();
                            edhe.insertEventLog("TIME_PROCESS", "Thêm mới TimeProcessId=" + bo.getTimeProcessId(), getRequest());
                        }
                    }
                } else {
                    TimeProcess bo = createForm.formToBo();
                    bo.setModifiedBy(getUserId());
                    bo.setModifyDate(getSysdate());
                    //bo.setTimeProcessDate(createForm.getTimeProcessDateFrom());
                    getSession().update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật thành công");
                    EventLogDAOHE edhe = new EventLogDAOHE();
                    edhe.insertEventLog("TIME_PROCESS", "Cập nhật TimeProcessId=" + bo.getTimeProcessId(), getRequest());
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Cập nhật không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public String onDelete() throws Exception {
        List resultMessage = new ArrayList();
        try {
            userAttachsDAOHE = new TimeProcessDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                TimeProcessForm form = lstItemOnGrid.get(i);
                if (form != null && form.getTimeProcessId() != null && form.getTimeProcessId() != 0L) {
                    TimeProcess bo = userAttachsDAOHE.getById("timeProcessId", form.getTimeProcessId());
                    if (bo != null) {
                        bo.setIsActive(Constants.ACTIVE_STATUS.DELETED);
                        bo.setModifiedBy(getUserId());
                        bo.setModifyDate(getSysdate());
                        getSession().update(bo);
                        EventLogDAOHE edhe = new EventLogDAOHE();
                        edhe.insertEventLog("TIME_PROCESS", "Xóa TimeProcessId=" + bo.getTimeProcessId(), getRequest());
                    }
                }
            }
            resultMessage.add("1");
            resultMessage.add("Xóa danh mục ngày nghỉ lễ thành công");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Xóa danh mục ngày nghỉ lễ không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onSearch() {
        getGridInfo();
        userAttachsDAOHE = new TimeProcessDAOHE();
        GridResult gr = userAttachsDAOHE.findTimeProcess(searchForm, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     *
     * @param date
     * @return
     */
    public static boolean isWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY;
    }

    /**
     *
     * @throws ParseException
     */
    public void demo() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String[] dates = {"12/2/2010", "12/3/2010", "12/5/2010", "12/10/2010", "12/5/2010", "12/13/2010", "12/14/2010", "12/12/2010"};
        Set<String> weekends = new HashSet<String>();
        for (String dt : dates) {
            Date date = dateFormat.parse(dt);
            if (isWeekend(date)) {
                weekends.add(dt);
            }
        }
        System.out.println("There are " + weekends.size() + " distinct weekends."); // 2
    }

    /**
     *
     * @param date
     * @return
     */
    public static Calendar DateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     *
     * @param calendar
     * @return
     */
    public static Date CalendarToDate(Calendar calendar) {
        Date date = calendar.getTime();
        return date;
    }
//==============================================================================    

    /**
     *
     * @return
     */
    public TimeProcessForm getCreateForm() {
        return createForm;
    }

    /**
     *
     * @param createForm
     */
    public void setCreateForm(TimeProcessForm createForm) {
        this.createForm = createForm;
    }

    /**
     *
     * @return
     */
    public TimeProcessForm getSearchForm() {
        return searchForm;
    }

    /**
     *
     * @param searchForm
     */
    public void setSearchForm(TimeProcessForm searchForm) {
        this.searchForm = searchForm;
    }

    /**
     *
     * @return
     */
    public List<TimeProcessForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    /**
     *
     * @param lstItemOnGrid
     */
    public void setLstItemOnGrid(List<TimeProcessForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

}
