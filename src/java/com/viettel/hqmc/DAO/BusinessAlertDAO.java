/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.Constants;
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
        BusinessAlertDAOHE  busalertDaoHe = new BusinessAlertDAOHE();
        
        try {
            if (searchForm.getClass() != null) {
                gridResult = busalertDaoHe.findBusinessAlert(searchForm, start, count, sortField);
                jsonDataGrid.setItems(gridResult.getLstResult());
                jsonDataGrid.setTotalRows(gridResult.getnCount().intValue());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return strReturn;
        }
        return GRID_DATA;
    }

    public String onInsert() {
        List resultMessage = new ArrayList();
        if (createBusinessAlertForm != null) {
            try {
                BusinessAlertDAOHE bdhe = new BusinessAlertDAOHE();
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
                log.error(ex.getMessage());
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lỗi");
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
