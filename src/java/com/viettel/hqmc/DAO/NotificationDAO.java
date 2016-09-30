/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.Notification;
import com.viettel.hqmc.DAOHE.NotificationDAOHE;
import com.viettel.hqmc.FORM.NotificationForm;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.EventLogDAOHE;
import java.util.ArrayList;
import java.util.List;
//import java.util.ResourceBundle;

/**
 *
 * @author hieptq
 */
public class NotificationDAO extends BaseDAO {

    private final String forwardPage = "notificationPage";
    private NotificationDAOHE notiDAOHE;
    private NotificationForm createForm;
    private NotificationForm searchForm;

//==============================================================================
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(NotificationDAO.class);

    public String prepare() {
//        try {
//            if (createForm != null) {
//                NotiSearchForm = new NotificationForm();
//            }
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//        }
        return this.forwardPage;
    }

    /**
     *
     * @return
     */
    //hieptq update 210115
    public String onSearch() {
        getGridInfo();
        NotificationDAOHE notiDAOHE = new NotificationDAOHE();
        GridResult gr = notiDAOHE.findNoti(searchForm, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    //hieptq update 210115
    public String onInsert() {
        NotificationDAOHE ndhe = new NotificationDAOHE();
        boolean bReturn = ndhe.onCreateNotification(createForm, getUserId());
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
        } else {
            resultMessage.add("2");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        EventLogDAOHE edhe = new EventLogDAOHE();
        edhe.insertEventLog("Admin tạo mới ", "Thông báo có id=" + createForm.getNoticeId(), getRequest());
        return GRID_DATA;
    }

    public String onDelete() throws Exception {
        List resultMessage = new ArrayList();
        String lstObjectId = getRequest().getParameter("lstObjectId");
        try {
            NotificationDAOHE ndhe = new NotificationDAOHE();
            String[] lstObjectIdSplit = lstObjectId.split(",");
            int countObj = lstObjectIdSplit.length;
            for (int i = 0; i < countObj; i++) {
                Long noticeId = Long.parseLong(lstObjectIdSplit[i]);
                Notification noti = ndhe.findById(noticeId);
                noti.setIsActive(Long.parseLong("0"));
                getSession().update(noti);
            }
            resultMessage.add("1");
            resultMessage.add("Xóa thông báo thành công");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Xóa thông báo không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public NotificationForm getNotiForm() {
        return createForm;
    }

    public void setNotiForm(NotificationForm NotiForm) {
        this.createForm = NotiForm;
    }

    public NotificationForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(NotificationForm createForm) {
        this.createForm = createForm;
    }

    public NotificationForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(NotificationForm searchForm) {
        this.searchForm = searchForm;
    }
}
