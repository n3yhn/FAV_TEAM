/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.LogUtil;
import com.viettel.voffice.database.DAO.*;
import com.viettel.hqmc.BO.Business;
import com.viettel.hqmc.DAOHE.BusinessDAOHE;
import com.viettel.hqmc.FORM.BusinessForm;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gpdn_cuongnx8
 */
public class BusinessDAO extends BaseDAO {

    /**
     * .
     */
    private String forwardPage = "businessPage";
    private BusinessForm searchForm;
    private BusinessForm createForm;
    private List<BusinessForm> lstItemOnGrid;

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(BusinessDAO.class);

    /*
     * toShowPage
     * show data perpare after show page
     */
    /**
     *
     * @return
     */
    public String prepare() {
        CategoryDAOHE cdhe = new CategoryDAOHE();
        List lstProvince = cdhe.findAllCategory("PROVINCE");
        List lstCategory = new ArrayList();
        lstCategory.addAll(lstProvince);
        lstCategory.add(0, new Category(0l, "--- Chọn ---"));
        getRequest().setAttribute("lstProvince", lstCategory);

        List lstBusinessType = cdhe.findAllCategory("BUSTYPE");
        List lstCategory1 = new ArrayList();
        lstCategory1.addAll(lstBusinessType);
        lstCategory1.add(0, new Category(0l, "--- Chọn ---"));
        getRequest().setAttribute("lstBusinessType", lstCategory1);
        return this.forwardPage;
    }

    /**
     *
     * @return
     */
    public String onSearch() {
        getGridInfo();

        BusinessDAOHE bdhe = new BusinessDAOHE();
        GridResult gr = bdhe.findBusiness(searchForm, start, count, sortField);

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
            BusinessDAOHE bdhe = new BusinessDAOHE();
            if (bdhe.isDuplicate(createForm)) {
                resultMessage.add("3");
                resultMessage.add("Thông tin doanh nghiệp bị trùng");
            } else {
                Long businessId = createForm.getBusinessId();

                if (businessId == null) {
                    Business bo = createForm.convertToEntity();
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới doanh nghiệp thành công");
                } else {
                    Business bo = createForm.convertToEntity();
                    getSession().update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật doanh nghiệp thành công");
                }
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Cập nhật doanh nghiệp không thành công");
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
            BusinessDAOHE bdhe = new BusinessDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                BusinessForm form = lstItemOnGrid.get(i);
                if (form != null && form.getBusinessId() != null && form.getBusinessId() != 0L) {
                    Business bo = bdhe.getById("businessId", form.getBusinessId());
                    if (bo != null) {
                        bo.setIsActive(-1l);
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
        return "gridData";
    }

    public BusinessForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(BusinessForm searchForm) {
        this.searchForm = searchForm;
    }

    public BusinessForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(BusinessForm createForm) {
        this.createForm = createForm;
    }

    public List<BusinessForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<BusinessForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }
}
