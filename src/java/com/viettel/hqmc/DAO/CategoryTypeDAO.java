/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.CategoryType;
import com.viettel.hqmc.DAOHE.CategoryTypeDAOHE;
import com.viettel.hqmc.FORM.CategoryTypeForm;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author vtit_binhnt53
 */
public class CategoryTypeDAO extends BaseDAO {

    private String forwardPage = "categoryTypePage";
    private CategoryTypeForm searchForm;
    private CategoryTypeForm createForm;
    CategoryTypeDAOHE categoryTypeDao = new CategoryTypeDAOHE();
    private List<CategoryTypeForm> lstItemOnGrid;

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CategoryTypeDAO.class);

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
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return this.forwardPage;
    }

    /**
     *
     * @return
     */
    public String onSearch() {
        getGridInfo();

        CategoryTypeDAOHE bdhe = new CategoryTypeDAOHE();
        GridResult gr = bdhe.findCategoryType(searchForm, start, count, sortField);

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
            CategoryTypeDAOHE cthe = new CategoryTypeDAOHE();
            if (cthe.isDuplicate(createForm)) {
                resultMessage.add("3");
                resultMessage.add("Thông tin loại danh mục bị trùng");
            } else {
                Long categoryTypeId = createForm.getCategoryTypeId();

                if (categoryTypeId == null) {
                    CategoryType bo = createForm.convertToEntity();
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới loại danh mục thành công");
                } else {
                    CategoryType bo = createForm.convertToEntity();
                    getSession().update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật loại danh mục thành công");
                }
            }

        } catch (Exception ex) {
            resultMessage.add("3");
            resultMessage.add("Cập nhật loại danh mục không thành công");
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
            CategoryTypeDAOHE cthe = new CategoryTypeDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                CategoryTypeForm form = lstItemOnGrid.get(i);
                if (form != null && form.getCategoryTypeId() != null && form.getCategoryTypeId() != 0L) {
                    CategoryType bo = cthe.getById("categoryTypeId", form.getCategoryTypeId());
                    if (bo != null) {
                        bo.setIsActive(0l);
                        getSession().update(bo);
                    }
                }
            }
            resultMessage.add("1");
            resultMessage.add("Xóa danh mục thành công");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Xóa danh mục không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public CategoryTypeForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(CategoryTypeForm searchForm) {
        this.searchForm = searchForm;
    }

    public CategoryTypeForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(CategoryTypeForm createForm) {
        this.createForm = createForm;
    }

    public List<CategoryTypeForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<CategoryTypeForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }
}
