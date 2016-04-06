/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.hqmc.BO.StandardProduct;
import com.viettel.hqmc.DAOHE.StandardProductDAOHE;
import com.viettel.hqmc.FORM.StandardProductForm;
import com.viettel.voffice.database.DAO.BaseDAO;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author vtit_binhnt53
 */
public class StandardProductDAO extends BaseDAO {

    private String forwardPage = "standardProductPage";
    //private StandardProductForm standardProductForm;
    private StandardProductForm standardProductAddEditForm;
    StandardProductDAOHE categoryTypeDao = new StandardProductDAOHE();
    private List<StandardProductForm> lstItemOnGrid;
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(StandardProductDAO.class);

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
            //code todo here
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return this.forwardPage;
    }

    //
    /**
     *
     * @return
     */
    public String onInit() {
        try {
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String returnLabel() {
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String prepareChooseCrime() {
        return "prepareChooseCrime";
    }

    /**
     *
     * @return
     */
    public String search() {
        return GRID_DATA;
    }

//check
    /**
     *
     * @return
     */
    public String checkStandardProduct() {
        //list remss
        List resultMessage = new ArrayList();
        //list cusinfo
        List customInfo = new ArrayList();
        //bo
        StandardProduct bo = new StandardProduct();
        //set bo
        bo.setCategoryName(standardProductAddEditForm.getCategoryName().trim());
        bo.setVietnameseStandardId(standardProductAddEditForm.getVietnameseStandardId());
        try {
            bo = categoryTypeDao.checkBO(bo);
            if (bo == null) {
                resultMessage.add("1");
                resultMessage.add("Danh mục chưa tồn tại");
            } else {
                resultMessage.add("3");
                if (bo.getCategoryName().equals(standardProductAddEditForm.getCategoryName().trim())) {
                    resultMessage.add("Tên đã tồn tại! Không thể thực hiện thao tác");
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Đã tồn tại với mã này! Không thể thực hiện thao tác");
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    //insert
    /**
     *
     * @return
     */
    public String insertCategory() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();

        try {
            StandardProductDAOHE.removeCache(standardProductAddEditForm.getCategoryName());
            Long categoryTypeId = standardProductAddEditForm.getVietnameseStandardId();
//            DMWS port = WebServiceUtils.getDMWS();
            if (categoryTypeId == null) {
                StandardProduct bo = new StandardProduct();
                categoryTypeDao.formToBO(standardProductAddEditForm, bo);
                categoryTypeDao.create(bo);
                resultMessage.add("1");
                resultMessage.add("Thêm mới thành công");
            } else {
                StandardProduct bo = categoryTypeDao.findById(categoryTypeId, false);
                categoryTypeDao.formToBO(standardProductAddEditForm, bo);
                categoryTypeDao.update(bo);
                resultMessage.add("1");
                resultMessage.add("Cập nhật thành công");
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
     */
    public String showEditPopup() {
        Long categoryTypeId = Long.valueOf(getRequest().getParameter("categoryTypeId"));
        StandardProduct bo = categoryTypeDao.findById(categoryTypeId, false);

        jsonDataGrid.setCustomInfo(bo);
        return GRID_DATA;
    }

    /**
     *
     * @return @throws Exception
     */
    public String gridDeleteData() throws Exception {
        List resultMessage = new ArrayList();
        try {
            if (lstItemOnGrid.size() > 0) {
                StandardProductForm form = lstItemOnGrid.get(0);
                StandardProductDAOHE.removeCache(NONE);
            }
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                StandardProductForm form = lstItemOnGrid.get(i);
                if (form != null && form.getVietnameseStandardId() != null && form.getVietnameseStandardId() != 0D) {
                    StandardProduct bo = categoryTypeDao.getById("vietnameseStandardId", form.getVietnameseStandardId());
                    if (bo != null) {
                        //bo.setIsActive(0L);
                        getSession().update(bo);
//                        categoryTypeDao.delete(bo);
//                        wsLst.add(bo.getCategoryId().toString());
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
}
