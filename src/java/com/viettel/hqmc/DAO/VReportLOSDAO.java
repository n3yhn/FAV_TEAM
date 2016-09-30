/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.DAOHE.VReportLOSDAOHE;
import com.viettel.hqmc.DAOHE.VReportStaffProcessDAOHE;
import com.viettel.hqmc.FORM.CategoryTypeForm;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import java.util.List;

/**
 *
 * @author binhninh
 */
public class VReportLOSDAO extends BaseDAO {

    private String forwardPage = "toReportLOS.Page";
    private FilesForm searchForm;
    private FilesForm createForm;
    VReportStaffProcessDAOHE VReportStaffProcessDao = new VReportStaffProcessDAOHE();
    private List<CategoryTypeForm> lstItemOnGrid;

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(VReportLOSDAO.class);

    /**
     *
     * @return
     */
    public String prepare() {
        try {
            //todo code here
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return this.forwardPage;
    }

    public String onSearch() {
        getGridInfo();

        VReportLOSDAOHE bdhe = new VReportLOSDAOHE();
        GridResult gr = bdhe.findVReportLOSProcess(searchForm, start, count, sortField);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());

        return GRID_DATA;
    }

    public List<CategoryTypeForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<CategoryTypeForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
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

}
