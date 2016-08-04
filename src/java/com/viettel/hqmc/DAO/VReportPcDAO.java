/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.hqmc.DAOHE.VReportPcDAOHE;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;

/**
 *
 * @author Administrator
 */
public class VReportPcDAO extends BaseDAO {

    private String forwardPage = "reportLProcess.Page";
    private FilesForm searchForm;
    private FilesForm createForm;
    VReportPcDAOHE VReportLProcessDao = new VReportPcDAOHE();
    private final String V_REPORT_PC = "vReportPc.Page";
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(VReportPcDAO.class);

    /**
     *
     * @return
     */
    public String prepare() {
        try {
            //todo code here
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return this.forwardPage;
    }

    public String onSearch() {
        getGridInfo();

        VReportPcDAOHE bdhe = new VReportPcDAOHE();
        GridResult gr = bdhe.findVReportPcProcess(searchForm, start, count, sortField);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());

        return GRID_DATA;
    }
    
    public String onsearchReportLProcess() {//141215u binhnt53
        getGridInfo();
        VReportPcDAOHE bdhe = new VReportPcDAOHE();
        searchForm = new FilesForm();
        searchForm.setDeptId(getDepartmentId());
        GridResult gr = bdhe.findVReportPcProcess(searchForm, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
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

    public String toVReportPc() {//141215u binhnt53
        return V_REPORT_PC;
    }

}
