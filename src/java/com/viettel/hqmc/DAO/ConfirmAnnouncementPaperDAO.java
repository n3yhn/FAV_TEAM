/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.hqmc.BO.ConfirmAnnouncementPaper;
import com.viettel.hqmc.DAOHE.ConfirmAnnouncementPaperDAOHE;
import com.viettel.hqmc.FORM.ConfirmAnnouncementPaperForm;
//import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
//import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import com.viettel.voffice.database.DAOHibernate.EventLogDAOHE;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vtit_binhnt53
 */
public class ConfirmAnnouncementPaperDAO extends BaseDAO {

    private String confirmAnnouncementPaperPage = "confirmAnnouncementPaperPage";
    private String confirmAnnouncementPaperSearchPage = "confirmAnnouncementPaperSearchPage";
    private ConfirmAnnouncementPaperForm searchForm;
    private ConfirmAnnouncementPaperForm createForm;
    private ConfirmAnnouncementPaperForm receptionForm;
    ConfirmAnnouncementPaperDAOHE categoryTypeDao = new ConfirmAnnouncementPaperDAOHE();
    private List<ConfirmAnnouncementPaperForm> lstItemOnGrid;
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ConfirmAnnouncementPaperDAO.class);
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
        return this.confirmAnnouncementPaperPage;
    }

    /**
     *
     * @return
     */
    public String onSearch() {
        getGridInfo();
        ConfirmAnnouncementPaperDAOHE bdhe = new ConfirmAnnouncementPaperDAOHE();
        GridResult gr = bdhe.findConfirmAnnouncementPaper(searchForm, start, count, sortField);
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
            ConfirmAnnouncementPaperDAOHE cthe = new ConfirmAnnouncementPaperDAOHE();
            if (cthe.isDuplicate(createForm) == true) {
                resultMessage.add("3");
                resultMessage.add("Số kí hiệu giấy xác nhận bị trùng, Thử lại");
            } else {
                Long ObjId = createForm.getConfirmAnnouncementPaperId();

                if (ObjId == null) {
                    ConfirmAnnouncementPaper bo = createForm.convertToEntity();
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới thành công");
                    EventLogDAOHE edhe = new EventLogDAOHE();
                    edhe.insertEventLog("Giấy CBPH ATTP", "Thêm mới Giấy CBPH ATTP có id=" + bo.getConfirmAnnouncementPaperId(), getRequest());
                } else {
                    ConfirmAnnouncementPaper bo = createForm.convertToEntity();
                    getSession().update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật thành công");
                    EventLogDAOHE edhe = new EventLogDAOHE();
                    edhe.insertEventLog("Giấy CBPH ATTP", "Cập nhật Giấy CBPH ATTP có id=" + bo.getConfirmAnnouncementPaperId(), getRequest());
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

    /**
     *
     * @return
     * @throws Exception
     */
    public String onDelete() throws Exception {
        List resultMessage = new ArrayList();
        try {
            ConfirmAnnouncementPaperDAOHE cthe = new ConfirmAnnouncementPaperDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                ConfirmAnnouncementPaperForm form = lstItemOnGrid.get(i);
                if (form != null && form.getConfirmAnnouncementPaperId() != null && form.getConfirmAnnouncementPaperId() != 0D) {
                    ConfirmAnnouncementPaper bo = cthe.getById("confirmAnnouncementPaperId", form.getConfirmAnnouncementPaperId());
                    if (bo != null) {
                        bo.setIsActive(0l);
                        getSession().update(bo);
                        EventLogDAOHE edhe = new EventLogDAOHE();
                        edhe.insertEventLog("Giấy CBPH ATTP", "Xóa Giấy CBPH ATTP có id=" + bo.getConfirmAnnouncementPaperId(), getRequest());
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

    public ConfirmAnnouncementPaperForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(ConfirmAnnouncementPaperForm searchForm) {
        this.searchForm = searchForm;
    }

    public ConfirmAnnouncementPaperForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(ConfirmAnnouncementPaperForm createForm) {
        this.createForm = createForm;
    }

    public List<ConfirmAnnouncementPaperForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<ConfirmAnnouncementPaperForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

    public ConfirmAnnouncementPaperDAOHE getCategoryTypeDao() {
        return categoryTypeDao;
    }

    public void setCategoryTypeDao(ConfirmAnnouncementPaperDAOHE categoryTypeDao) {
        this.categoryTypeDao = categoryTypeDao;
    }

    public String getConfirmAnnouncementPaperCreatePage() {
        return confirmAnnouncementPaperPage;
    }

    public void setConfirmAnnouncementPaperCreatePage(String confirmAnnouncementPaperCreatePage) {
        this.confirmAnnouncementPaperPage = confirmAnnouncementPaperCreatePage;
    }

    public String getConfirmAnnouncementPaperSearchPage() {
        return confirmAnnouncementPaperSearchPage;
    }

    public void setConfirmAnnouncementPaperSearchPage(String confirmAnnouncementPaperSearchPage) {
        this.confirmAnnouncementPaperSearchPage = confirmAnnouncementPaperSearchPage;
    }

    public ConfirmAnnouncementPaperForm getReceptionForm() {
        return receptionForm;
    }

    public void setReceptionForm(ConfirmAnnouncementPaperForm receptionForm) {
        this.receptionForm = receptionForm;
    }
}
