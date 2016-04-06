/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.hqmc.BO.Announcement;
import com.viettel.hqmc.DAOHE.AnnouncementDAOHE;
import com.viettel.hqmc.FORM.AnnouncementForm;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vtit_binhnt53
 */
public class AnnouncementDAO extends BaseDAO {

    private String announcementCreatePage = "announcementCreatePage";
    private String announcementSearchPage = "announcementSearchPage";
    private AnnouncementForm searchForm;
    private AnnouncementForm createForm;
    private AnnouncementForm receptionForm;
    AnnouncementDAOHE categoryTypeDao = new AnnouncementDAOHE();
    private List<AnnouncementForm> lstItemOnGrid;
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AnnouncementDAO.class);
    /*
     * toShowPage
     * show data perpare after show page
     */

    /**
     *
     * @return
     */
    public String prepareCreate() {
        try {
            //todo code here
            CategoryDAOHE cdhe = new CategoryDAOHE();
            List lstProvince = cdhe.findAllCategory("PROVINCE");
            List lstCategory = new ArrayList();
            lstCategory.addAll(lstProvince);
            lstCategory.add(0, new Category(0l, "-- Chọn --"));
            getRequest().setAttribute("lstProvince", lstCategory);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return this.announcementCreatePage;
    }

    /**
     *
     * @return
     */
    public String prepareSearch() {
        try {
            //todo code here
            CategoryDAOHE cdhe = new CategoryDAOHE();
            List lstProvince = cdhe.findAllCategory("PROVINCE");
            List lstCategory = new ArrayList();
            lstCategory.addAll(lstProvince);
            lstCategory.add(0, new Category(0l, "-- Chọn --"));
            getRequest().setAttribute("lstProvince", lstCategory);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return this.announcementSearchPage;
    }

    /**
     *
     * @return
     */
    public String onSearch() {
        getGridInfo();

        AnnouncementDAOHE bdhe = new AnnouncementDAOHE();
        GridResult gr = bdhe.findAnnouncement(searchForm, start, count, sortField);

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
            AnnouncementDAOHE cthe = new AnnouncementDAOHE();
            if (cthe.isDuplicate(createForm) == true) {
                resultMessage.add("3");
                resultMessage.add("Thông tin đăng kí bị trùng");
            } else {
                Long ObjId = createForm.getAnnouncementId();

                if (ObjId == null) {
                    Announcement bo = createForm.convertToEntity();
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới đăng kí thành công");
                } else {
                    Announcement bo = createForm.convertToEntity();
                    getSession().update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật đăng kí thành công");
                }
            }

        } catch (Exception ex) {
            resultMessage.add("3");
            resultMessage.add("Cập nhật đăng kí không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }
    public AnnouncementForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(AnnouncementForm searchForm) {
        this.searchForm = searchForm;
    }

    public AnnouncementForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(AnnouncementForm createForm) {
        this.createForm = createForm;
    }

    public List<AnnouncementForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<AnnouncementForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

    public AnnouncementDAOHE getCategoryTypeDao() {
        return categoryTypeDao;
    }

    public void setCategoryTypeDao(AnnouncementDAOHE categoryTypeDao) {
        this.categoryTypeDao = categoryTypeDao;
    }

    public String getAnnouncementCreatePage() {
        return announcementCreatePage;
    }

    public void setAnnouncementCreatePage(String announcementCreatePage) {
        this.announcementCreatePage = announcementCreatePage;
    }

    public String getAnnouncementSearchPage() {
        return announcementSearchPage;
    }

    public void setAnnouncementSearchPage(String announcementSearchPage) {
        this.announcementSearchPage = announcementSearchPage;
    }

    public AnnouncementForm getReceptionForm() {
        return receptionForm;
    }

    public void setReceptionForm(AnnouncementForm receptionForm) {
        this.receptionForm = receptionForm;
    }
}
