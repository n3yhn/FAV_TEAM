/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.AnnouncementReceiptPaper;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.DAOHE.AnnouncementReceiptPaperDAOHE;
import com.viettel.hqmc.DAOHE.FilesDAOHE;
import com.viettel.hqmc.FORM.AnnouncementReceiptPaperForm;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import com.viettel.voffice.database.DAOHibernate.EventLogDAOHE;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vtit_binhnt53
 */
public class AnnouncementReceiptPaperDAO extends BaseDAO {

    private String announcementReceiptPaperCreatePage = "announcementReceiptPaperPage";
    private String announcementReceiptPaperSearchPage = "announcementReceiptPaperSearchPage";
    private String signingPage = "signingPage";
    private AnnouncementReceiptPaperForm searchForm;
    private AnnouncementReceiptPaperForm createForm;
    private AnnouncementReceiptPaperForm receptionForm;
    private FilesForm provideForm;
    AnnouncementReceiptPaperDAOHE categoryTypeDao = new AnnouncementReceiptPaperDAOHE();
    private List<AnnouncementReceiptPaperForm> lstItemOnGrid;

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AnnouncementReceiptPaperDAO.class);

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
            CategoryDAOHE cdhe = new CategoryDAOHE();
            List lstProvince = cdhe.findAllCategory("NATION");
            List lstCategory = new ArrayList();
            lstCategory.addAll(lstProvince);
            lstCategory.add(0, new Category(0L, "--- Chọn ---"));
            getRequest().setAttribute("lstNation", lstCategory);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return this.announcementReceiptPaperCreatePage;
    }

    /**
     *
     * @return
     */
    public String onSearch() {
        getGridInfo();
        AnnouncementReceiptPaperDAOHE bdhe = new AnnouncementReceiptPaperDAOHE();
        GridResult gr = bdhe.findAnnouncementReceiptPaper(searchForm, start, count, sortField);
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
            AnnouncementReceiptPaperDAOHE cthe = new AnnouncementReceiptPaperDAOHE();
            if (cthe.isDuplicate(createForm) == true) {
                resultMessage.add("3");
                resultMessage.add("[Số giấy tiếp nhận hồ sơ] bị trùng");
            } else if (cthe.validateReceiptNo(createForm.getReceiptNo())) {
                Long ObjId = createForm.getAnnouncementReceiptPaperId();
                if (ObjId == null) {
                    AnnouncementReceiptPaper bo = createForm.convertToEntity();
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới thành công");
                    EventLogDAOHE edhe = new EventLogDAOHE();
                    edhe.insertEventLog("Giấy TNCB hợp qui", "Thêm mới Giấy TNCB hợp quy có id=" + bo.getAnnouncementReceiptPaperId(), getRequest());
                } else {
                    AnnouncementReceiptPaper bo = createForm.convertToEntity();
                    getSession().update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật thành công");
                    EventLogDAOHE edhe = new EventLogDAOHE();
                    edhe.insertEventLog("Giấy TNCB hợp qui", "Cập nhật Giấy TNCB hợp quy có id=" + bo.getAnnouncementReceiptPaperId(), getRequest());
                }
            } else {
                resultMessage.add("3");
                resultMessage.add("[Số giấy tiếp nhận hồ sơ] Không đúng định dạng");
            }

        } catch (Exception ex) {
            resultMessage.add("3");
            resultMessage.add("Cập nhật không thành công");
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
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
            AnnouncementReceiptPaperDAOHE cthe = new AnnouncementReceiptPaperDAOHE();
            if (cthe.isDuplicate(provideForm.getAnnouncementReceiptPaperForm()) == true) {
                resultMessage.add("3");
                resultMessage.add("[Số giấy tiếp nhận hồ sơ] bị trùng");
            } else if (cthe.validateReceiptNo(provideForm.getAnnouncementReceiptPaperForm().getReceiptNo())) {
                Long ObjId = provideForm.getAnnouncementReceiptPaperForm().getAnnouncementReceiptPaperId();
                if (ObjId == null) {
                    AnnouncementReceiptPaper bo = provideForm.getAnnouncementReceiptPaperForm().convertToEntity();
                    getSession().save(bo);

                    FilesDAOHE fdhe = new FilesDAOHE();
                    Files fbo = fdhe.findById(provideForm.getFileId());
                    fbo.setAnnouncementReceiptPaperId(bo.getAnnouncementReceiptPaperId());
                    fbo.setStatus(Constants.FILE_STATUS.LICENSING);
                    getSession().update(fbo);

                    resultMessage.add("1");
                    resultMessage.add("Thêm mới Giấy tiếp nhận thành công");
                } else {
                    AnnouncementReceiptPaper bo = provideForm.getAnnouncementReceiptPaperForm().convertToEntity();
                    getSession().update(bo);

                    FilesDAOHE fdhe = new FilesDAOHE();
                    Files fbo = fdhe.findById(provideForm.getFileId());
                    fbo.setAnnouncementReceiptPaperId(bo.getAnnouncementReceiptPaperId());
                    fbo.setStatus(Constants.FILE_STATUS.LICENSING);
                    getSession().update(fbo);

                    resultMessage.add("1");
                    resultMessage.add("Cập nhật thành công");
                }
            } else {
                resultMessage.add("3");
                resultMessage.add("[Số giấy tiếp nhận hồ sơ] Không đúng định dạng");
            }

        } catch (Exception ex) {
            resultMessage.add("3");
            resultMessage.add("Cập nhật không thành công");
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /**
     *
     * @return @throws Exception
     */
    public String onDelete() throws Exception {
        List resultMessage = new ArrayList();
        try {
            AnnouncementReceiptPaperDAOHE cthe = new AnnouncementReceiptPaperDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                AnnouncementReceiptPaperForm form = lstItemOnGrid.get(i);
                if (form != null
                        && form.getAnnouncementReceiptPaperId() != null
                        && form.getAnnouncementReceiptPaperId() != 0L) {
                    AnnouncementReceiptPaper bo = cthe.getById("announcementReceiptPaperId", form.getAnnouncementReceiptPaperId());
                    if (bo != null) {
                        bo.setIsActive(0);
                        getSession().update(bo);
                        EventLogDAOHE edhe = new EventLogDAOHE();
                        edhe.insertEventLog("Giấy TNCB hợp qui", "Xóa Giấy TNCB hợp quy có id=" + bo.getAnnouncementReceiptPaperId(), getRequest());
                    }
                }
            }
            resultMessage.add("1");
            resultMessage.add("Xóa thành công");

        } catch (Exception ex) {
            resultMessage.add("3");
            resultMessage.add("Xóa không thành công");
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public AnnouncementReceiptPaperForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(AnnouncementReceiptPaperForm searchForm) {
        this.searchForm = searchForm;
    }

    public AnnouncementReceiptPaperForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(AnnouncementReceiptPaperForm createForm) {
        this.createForm = createForm;
    }

    public List<AnnouncementReceiptPaperForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<AnnouncementReceiptPaperForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

    public AnnouncementReceiptPaperDAOHE getCategoryTypeDao() {
        return categoryTypeDao;
    }

    public void setCategoryTypeDao(AnnouncementReceiptPaperDAOHE categoryTypeDao) {
        this.categoryTypeDao = categoryTypeDao;
    }

    public String getAnnouncementReceiptPaperCreatePage() {
        return announcementReceiptPaperCreatePage;
    }

    public void setAnnouncementReceiptPaperCreatePage(String announcementReceiptPaperCreatePage) {
        this.announcementReceiptPaperCreatePage = announcementReceiptPaperCreatePage;
    }

    public String getAnnouncementReceiptPaperSearchPage() {
        return announcementReceiptPaperSearchPage;
    }

    public void setAnnouncementReceiptPaperSearchPage(String announcementReceiptPaperSearchPage) {
        this.announcementReceiptPaperSearchPage = announcementReceiptPaperSearchPage;
    }

    public AnnouncementReceiptPaperForm getReceptionForm() {
        return receptionForm;
    }

    public void setReceptionForm(AnnouncementReceiptPaperForm receptionForm) {
        this.receptionForm = receptionForm;
    }

    public String getSigningPage() {
        return signingPage;
    }

    public void setSigningPage(String signingPage) {
        this.signingPage = signingPage;
    }

    public FilesForm getProvideForm() {
        return provideForm;
    }

    public void setProvideForm(FilesForm provideForm) {
        this.provideForm = provideForm;
    }
}
