/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.hqmc.BO.PaymentHistory;
import com.viettel.hqmc.DAOHE.PaymentHistoryDAOHE;
import com.viettel.hqmc.FORM.PaymentHistoryForm;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BINHNT53
 */
public class PaymentHistoryDAO extends BaseDAO {

    private String forwardPage = "categoryTypePage";
    private PaymentHistoryForm searchForm;
    private PaymentHistoryForm createForm;
    PaymentHistoryDAOHE categoryTypeDao = new PaymentHistoryDAOHE();
    private List<PaymentHistoryForm> lstItemOnGrid;
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PaymentHistoryDAO.class);
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
        PaymentHistoryDAOHE bdhe = new PaymentHistoryDAOHE();
        GridResult gr = bdhe.findPaymentHistory(searchForm, start, count, sortField);

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
            PaymentHistoryDAOHE cthe = new PaymentHistoryDAOHE();
            if (cthe.isDuplicate(createForm)) {
                resultMessage.add("3");
                resultMessage.add("Thông tin loại danh mục bị trùng");
            } else {
                Long categoryTypeId = createForm.getPaymentHistoryId();

                if (categoryTypeId == null) {
                    PaymentHistory bo = createForm.convertToEntity();
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới loại danh mục thành công");
                } else {
                    PaymentHistory bo = createForm.convertToEntity();
                    getSession().update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật loại danh mục thành công");
                }
            }

        } catch (Exception ex) {
            resultMessage.add("3");
            resultMessage.add("Cập nhật loại danh mục không thành công");
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
            PaymentHistoryDAOHE cthe = new PaymentHistoryDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                PaymentHistoryForm form = lstItemOnGrid.get(i);
                if (form != null && form.getPaymentHistoryId() != null && form.getPaymentHistoryId() != 0D) {
                    PaymentHistory bo = cthe.getById("categoryTypeId", form.getPaymentHistoryId());
                    if (bo != null) {
                        bo.setStatus(0l);
                        getSession().update(bo);
                    }
                }
            }
            resultMessage.add("1");
            resultMessage.add("Xóa danh mục thành công");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Xóa danh mục không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }
}
