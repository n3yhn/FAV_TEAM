/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.hqmc.BO.Parameter;
import com.viettel.hqmc.DAOHE.ParameterDAOHE;
import com.viettel.hqmc.FORM.ParameterForm;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vtit_binhnt53
 */
public class ParameterDAO extends BaseDAO {

    private String forwardPage = "parameterActionPage";
    private ParameterForm searchForm;
    private ParameterForm createForm;
    ParameterDAOHE parameterDao = new ParameterDAOHE();
    private List<ParameterForm> lstItemOnGrid;
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ParameterDAO.class);
    /*
     * toShowPage
     * show data perpare after show page
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

        ParameterDAOHE bdhe = new ParameterDAOHE();
        GridResult gr = bdhe.findParameter(searchForm, start, count, sortField);

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
            ParameterDAOHE cthe = new ParameterDAOHE();
            if (cthe.isDuplicate(createForm)) {
                resultMessage.add("3");
                resultMessage.add("Thông tin cấu hình bị trùng");
            } else {
                Long id = createForm.getParameterId();

                if (id == null) {
                    Parameter bo = createForm.convertToEntity();
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới cấu hình thành công");
                } else {
                    Parameter bo = createForm.convertToEntity();
                    getSession().update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật cấu hình thành công");
                }
            }

        } catch (Exception ex) {
            resultMessage.add("3");
            resultMessage.add("Cập nhật cấu hình không thành công");
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
            ParameterDAOHE cthe = new ParameterDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                ParameterForm form = lstItemOnGrid.get(i);
                if (form != null && form.getParameterId() != null && form.getParameterId() != 0D) {
                    Parameter bo = cthe.getById("parameterId", form.getParameterId());
                    if (bo != null) {
                        bo.setIsActive(0l);
                        getSession().update(bo);
                    }
                }
            }
            resultMessage.add("1");
            resultMessage.add("Xóa cấu hình thành công");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Xóa cấu hình không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public ParameterForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(ParameterForm searchForm) {
        this.searchForm = searchForm;
    }

    public ParameterForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(ParameterForm createForm) {
        this.createForm = createForm;
    }

    public List<ParameterForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<ParameterForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }
}
