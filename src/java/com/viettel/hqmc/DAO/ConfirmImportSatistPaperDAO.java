/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.Constants;
import com.viettel.hqmc.BO.ConfirmImportSatistPaper;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.DAOHE.ConfirmImportSatistPaperDAOHE;
import com.viettel.hqmc.DAOHE.FilesDAOHE;
import com.viettel.hqmc.FORM.ConfirmImportSatistPaperForm;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.EventLogDAOHE;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vtit_binhnt53
 */
public class ConfirmImportSatistPaperDAO extends BaseDAO {

    private String forwardPage = "confirmImportSatistPaperPage";
    private ConfirmImportSatistPaperForm searchForm;
    private ConfirmImportSatistPaperForm createForm;
    private FilesForm provideForm;
    ConfirmImportSatistPaperDAOHE confirmImportSatistPaperDao = new ConfirmImportSatistPaperDAOHE();
    private List<ConfirmImportSatistPaperForm> lstItemOnGrid;
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ConfirmImportSatistPaperDAO.class);
    /*
     * toShowPage
     * show data perpare after show page
     */

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

    /**
     *
     * @return
     */
    public String onSearch() {
        getGridInfo();
        ConfirmImportSatistPaperDAOHE bdhe = new ConfirmImportSatistPaperDAOHE();
        GridResult gr = bdhe.findConfirmImportSatistPaper(searchForm, start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());

        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onInsert() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        try {
            ConfirmImportSatistPaperDAOHE cthe = new ConfirmImportSatistPaperDAOHE();
            if (cthe.isDuplicate(createForm)) {
                resultMessage.add("3");
                resultMessage.add("[Tên hàng hóa] bị trùng");
            } else {
                Long id = createForm.getConfirmImportSatistPaperId();

                if (id == null) {
                    ConfirmImportSatistPaper bo = createForm.convertToEntity();
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới thành công");
                    EventLogDAOHE edhe = new EventLogDAOHE();
                    edhe.insertEventLog("Giấy ĐYCNK", "Thêm mới Giấy Đạt yêu cầu nhập khẩu có id=" + bo.getConfirmImportSatistPaperId(), getRequest());
                } else {
                    ConfirmImportSatistPaper bo = createForm.convertToEntity();
                    getSession().update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật thành công");
                    EventLogDAOHE edhe = new EventLogDAOHE();
                    edhe.insertEventLog("Giấy ĐYCNK", "Cập nhật Giấy Đạt yêu cầu nhập khẩu có id=" + bo.getConfirmImportSatistPaperId(), getRequest());
                }
            }

        } catch (Exception ex) {
            resultMessage.add("3");
            resultMessage.add("Cập nhật không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    public String onDelete() throws Exception {
        List resultMessage = new ArrayList();
        try {
            ConfirmImportSatistPaperDAOHE cthe = new ConfirmImportSatistPaperDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                ConfirmImportSatistPaperForm form = lstItemOnGrid.get(i);
                if (form != null && form.getConfirmImportSatistPaperId() != null && form.getConfirmImportSatistPaperId() != 0D) {
                    ConfirmImportSatistPaper bo = cthe.getById("confirmImportSatistPaperId", form.getConfirmImportSatistPaperId());
                    if (bo != null) {
                        bo.setIsActive(0l);
                        getSession().update(bo);
                        EventLogDAOHE edhe = new EventLogDAOHE();
                        edhe.insertEventLog("Giấy ĐYCNK", "Xóa Giấy Đạt yêu cầu nhập khẩu có id=" + bo.getConfirmImportSatistPaperId(), getRequest());
                    }
                }
            }
            resultMessage.add("1");
            resultMessage.add("Xóa thành công");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Xóa không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onProvide() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        try {
            ConfirmImportSatistPaperDAOHE cthe = new ConfirmImportSatistPaperDAOHE();
            if (cthe.isDuplicate(provideForm.getConfirmImportSatistPaperForm()) == true) {
                resultMessage.add("3");
                resultMessage.add("[Số giấy tiếp nhận hồ sơ] bị trùng");
            } else {
                    Long ObjId = provideForm.getConfirmImportSatistPaperId();
                    if (ObjId == null) {
                        ConfirmImportSatistPaper bo = provideForm.getConfirmImportSatistPaperForm().convertToEntity();                        
                        getSession().save(bo);
                        
                        FilesDAOHE fdhe = new FilesDAOHE();
                        Files fbo = fdhe.findById(provideForm.getFileId());
                        fbo.setConfirmImportSatistPaperId(bo.getConfirmImportSatistPaperId());
                        fbo.setStatus(Constants.FILE_STATUS.LICENSING);
                        getSession().update(fbo);
                        
                        resultMessage.add("1");
                        resultMessage.add("Thêm mới Giấy tiếp nhận thành công");
                    } else {
                        ConfirmImportSatistPaper bo = provideForm.getConfirmImportSatistPaperForm().convertToEntity();
                        getSession().update(bo);
                        
                        FilesDAOHE fdhe = new FilesDAOHE();
                        Files fbo = fdhe.findById(provideForm.getFileId());
                        fbo.setConfirmImportSatistPaperId(bo.getConfirmImportSatistPaperId());
                        fbo.setStatus(Constants.FILE_STATUS.LICENSING);
                        getSession().update(fbo);
                        
                        resultMessage.add("1");
                        resultMessage.add("Cập nhật thành công");
                    }
            }

        } catch (Exception ex) {
            resultMessage.add("3");
            resultMessage.add("Cập nhật không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }
    public ConfirmImportSatistPaperForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(ConfirmImportSatistPaperForm searchForm) {
        this.searchForm = searchForm;
    }

    public ConfirmImportSatistPaperForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(ConfirmImportSatistPaperForm createForm) {
        this.createForm = createForm;
    }

    public List<ConfirmImportSatistPaperForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<ConfirmImportSatistPaperForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

    public FilesForm getProvideForm() {
        return provideForm;
    }

    public void setProvideForm(FilesForm provideForm) {
        this.provideForm = provideForm;
    }
    
}
