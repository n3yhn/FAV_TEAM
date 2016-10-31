/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.BusinessAlert;
import com.viettel.hqmc.DAOHE.BusinessAlertDAOHE;
import com.viettel.hqmc.FORM.BusinessAlertForm;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class BusinessAlertDAO extends BaseDAO {

    private String forwardPage = "businessAlertPage";
    private BusinessAlertForm searchForm;
    private BusinessAlertForm createBusinessAlertForm;
//    private List<BusinessAlertForm> lstItemOnGrid;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(BusinessAlertDAO.class);

    public String loadBusinessAlertView() {
        getGridInfo();
        String strReturn = ERROR_PERMISSION;
        GridResult gridResult = new GridResult(0, new ArrayList());
        BusinessAlertDAOHE busalertDaoHe = new BusinessAlertDAOHE();

        try {
            if (searchForm.getClass() != null) {
                gridResult = busalertDaoHe.findBusinessAlert(searchForm, start, count, sortField);
                jsonDataGrid.setItems(gridResult.getLstResult());
                jsonDataGrid.setTotalRows(gridResult.getnCount().intValue());
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return strReturn;
        }
        return GRID_DATA;
    }

    public String getContent4BusinessView() {
        getGridInfo();
        List customInfo = new ArrayList();

        Long businessId = 0l;
        try {
            businessId = getBusinessId();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        BusinessAlertDAOHE pcdhe = new BusinessAlertDAOHE();
        List<BusinessAlertForm> result = pcdhe.getLstCommentOfDocument(businessId);
        String content = "";
        for (BusinessAlertForm t : result) {
            content += t.getContent() + "\n";

        }
        customInfo.add(content);//0
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    public String onUpdateCheckedSeen() {
        List resultMessage = new ArrayList();
        BusinessAlertDAOHE pcdhe = new BusinessAlertDAOHE();
        List<BusinessAlert> list = pcdhe.getAllBusinessAlertByUserId(getBusinessId());
        if (list != null && list.isEmpty() == false && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSeen(1L);
                pcdhe.update(list.get(i));
            }
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String onInsert() {
        List resultMessage = new ArrayList();
        if (createBusinessAlertForm != null) {
            try {
//                BusinessAlertDAOHE bdhe = new BusinessAlertDAOHE();
//                BusinessAlert bus = bdhe.findById(getBusinessId());
                BusinessAlert bus = new BusinessAlert();
                bus.setBusinessId(createBusinessAlertForm.getBusinessId());
                bus.setIsActive(Constants.ACTIVE_STATUS.ACTIVE);
                bus.setContent(createBusinessAlertForm.getContent());
                bus.setSeen(0L);
                bus.setCreatedDate(getSysdate());
                bus.setCreatedByName(getUserName());
                bus.setCreatedById(getUserId());
                getSession().saveOrUpdate(bus);
                resultMessage.add("1");
                resultMessage.add("Đăng kí thành công");
            } catch (Exception ex) {
                resultMessage.add("3");
                resultMessage.add("Lỗi");
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lỗi");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String onDelete() throws Exception {
        List resultMessage = new ArrayList();
        BusinessAlertDAOHE pcdhe = new BusinessAlertDAOHE();
        try {
            Long businessAlertId = Long.valueOf(getRequest().getParameter("businessAlertId"));
            BusinessAlert p = pcdhe.findById(businessAlertId);
            if (p != null) {
                p.setIsActive(Constants.ACTIVE_STATUS.DELETED);
                pcdhe.update(p);
                resultMessage.add("1");
                resultMessage.add("Xóa Thông báo thành công");
            } else {
                resultMessage.add("3");
                resultMessage.add("Xóa Thông báo không thành công");
            }
        } catch (Exception ex) {
            resultMessage.add("3");
            resultMessage.add("Xóa Thông báo không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String toBusinessAlertPage() {
        return this.forwardPage;
    }

    public BusinessAlertForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(BusinessAlertForm searchForm) {
        this.searchForm = searchForm;
    }

    public BusinessAlertForm getCreateBusinessAlertForm() {
        return createBusinessAlertForm;
    }

    public void setCreateBusinessAlertForm(BusinessAlertForm createBusinessAlertForm) {
        this.createBusinessAlertForm = createBusinessAlertForm;
    }

}
