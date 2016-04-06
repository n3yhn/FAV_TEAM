/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.hqmc.BO.VietnameseStandard;
import com.viettel.hqmc.DAOHE.VietnameseStandardDAOHE;
import com.viettel.hqmc.FORM.VietnameseStandardForm;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import com.viettel.voffice.database.DAOHibernate.VoAttachsDAOHE;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author vtit_binhnt53
 */
public class VietnameseStandardDAO extends BaseDAO {

    private String forwardPage = "vietnameseStandardPage";
    private VietnameseStandardForm searchForm;
    private VietnameseStandardForm createForm;
    private List<VietnameseStandardForm> lstItemOnGrid;
    VietnameseStandardDAOHE vietnameseStandardDaohe = new VietnameseStandardDAOHE();
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(VietnameseStandardDAO.class);

    /*
     * prepare method
     * toShowPage
     */

    /**
     *
     * @return
     */
    
    public String prepare() {
        try {
            CategoryDAOHE cdhe = new CategoryDAOHE();
            List lstObj = cdhe.findAllCategory("StandardType");
            List lstCategory = new ArrayList();
            lstCategory.addAll(lstObj);
            lstCategory.add(0, new Category(0l, "--- Chọn ---"));
            getRequest().setAttribute("lstStandardType", lstCategory);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return this.forwardPage;
    }

    /**
     *event search item
     * @return
     */
    
    public String onSearch() {
        getGridInfo();

        VietnameseStandardDAOHE bdhe = new VietnameseStandardDAOHE();
        GridResult gr = bdhe.findVietnameseStandard(searchForm, start, count, sortField);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());

        return GRID_DATA;
    }

    /**
     *event insert item
     * @return
     */
    
    public String onInsert() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        try {
            VietnameseStandardDAOHE cthe = new VietnameseStandardDAOHE();
            if (cthe.isDuplicate(createForm)) {
                resultMessage.add("3");
                resultMessage.add("Thông danh mục bị trùng");
            } else {
                Long Id = createForm.getVietnameseStandardId();
                VietnameseStandard bo = createForm.convertToEntity();
                if (Id == null) {
                    
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới thành công");
                } else {
                    getSession().update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật thành công");
                }
                String[] lstVo = createForm.getUploadId().split(";");

                for (int i = 0; i < lstVo.length; i++) {
                    if (lstVo[i] != null) {
                        VoAttachsDAOHE vadhe = new VoAttachsDAOHE();
                        VoAttachs voUpload = vadhe.findById(Long.parseLong(lstVo[i]));
                        voUpload.setObjectId(bo.getVietnameseStandardId());
                        voUpload.setObjectType(24L);
                        getSession().update(voUpload);
                    }
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
            VietnameseStandardDAOHE cthe = new VietnameseStandardDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                VietnameseStandardForm form = lstItemOnGrid.get(i);
                if (form != null && form.getVietnameseStandardId() != null && form.getVietnameseStandardId() != 0D) {
                    VietnameseStandard bo = cthe.getById("vietnameseStandardId", form.getVietnameseStandardId());
                    if (bo != null) {
                        bo.setIsActive(0l);
                        getSession().update(bo);
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
    
    /*
     * getter setter
     */
    public VietnameseStandardForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(VietnameseStandardForm searchForm) {
        this.searchForm = searchForm;
    }

    public VietnameseStandardForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(VietnameseStandardForm createForm) {
        this.createForm = createForm;
    }

    public List<VietnameseStandardForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<VietnameseStandardForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }
}
