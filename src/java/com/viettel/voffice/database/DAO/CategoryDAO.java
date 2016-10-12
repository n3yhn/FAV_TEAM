/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAO;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.dojoTag.DojoJSON;
import com.viettel.hqmc.BO.CategoryType;
import com.viettel.hqmc.DAOHE.BusinessDAOHE;
import com.viettel.hqmc.DAOHE.CategoryTypeDAOHE;
import com.viettel.voffice.client.form.CategorySearchForm;
import com.viettel.voffice.database.BO.Category;
import static com.viettel.voffice.database.DAO.BaseDAO.GRID_DATA;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author gpdn_cuongnx8
 */
public class CategoryDAO extends BaseDAO {

    /**
     * .
     */
    private String forwardPage = "categoryPage";
    private CategorySearchForm categorySearchForm;
    private CategorySearchForm categoryAddEditForm;
    private CategorySearchForm categoryAddEditFormSp;
    private CategorySearchForm searchForm;
    private CategorySearchForm createForm;
    CategoryDAOHE categoryDao = new CategoryDAOHE();
    BusinessDAOHE bhe = new BusinessDAOHE();
    private List<CategorySearchForm> lstItemOnGrid;

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CategoryDAO.class);

    /**
     * @return String
     */
    public String prepare() {
        try {
            String type = getRequest().getParameter("type");
            String categoryName = "Danh mục";
            if ("NATION".equals(type)) {
                categoryName = "Danh mục quốc gia";
            } else if ("PROVINCE".equals(type)) {
                categoryName = "Danh mục tỉnh, thành phố";
            } else if ("TTHC".equals(type)) {
                categoryName = "Danh mục thủ tục hành chính";
            } else if ("SP".equals(type)) {
                categoryName = "Danh mục sản phẩm";
            } else if ("DOCTYPE".equals(type)) {
                categoryName = "Danh mục loại tài liệu";
            } else if ("DVI".equals(type)) {
                categoryName = "Danh mục đơn vị";
            }
            List lstCategoryType = new ArrayList();
            // lstCategoryType.add(0, new CategoryType("0", "--- Chọn ---"));
            lstCategoryType.add(new CategoryType("SP", "DBT"));
            lstCategoryType.add(new CategoryType("SP", "TPCN"));
            lstCategoryType.add(new CategoryType("SP", "TPK"));
            lstCategoryType.add(0, new CategoryType(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
            getRequest().setAttribute("lstSpCode", lstCategoryType);
            getRequest().setAttribute("type", type);
            getRequest().setAttribute("categoryName", categoryName);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return this.forwardPage;
    }

    /*
     * 
     */

    public String prepareCategory() {
        try {
            CategoryTypeDAOHE he = new CategoryTypeDAOHE();
            List lstType = he.findAllCategoryType();
            List lstCategoryType = new ArrayList();
            lstCategoryType.addAll(lstType);
            lstCategoryType.add(0, new CategoryType("0", "--- Chọn ---"));
            getRequest().setAttribute("lstType", lstCategoryType);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "categorysPage";
    }

    public String onInit() {
        try {
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "gridData";
    }

    public String returnLabel() {
        return "gridData";
    }

    public String prepareChooseCrime() {
        return "prepareChooseCrime";
    }

    public String search() {
        int start = Integer.parseInt(getRequest().getParameter("startval"));
        AtomicInteger count = new AtomicInteger(Integer.parseInt(getRequest().getParameter("count")));
        String sortField = getRequest().getParameter("sort");
        String type = getRequest().getParameter("type");
        if (categorySearchForm == null) {
            categorySearchForm = new CategorySearchForm();
            categorySearchForm.setType(type);
        }
        if (("VOLT").equals(categorySearchForm.getType())) {
            categorySearchForm.setCreatedBy(getDeptRepresentId());
        }
        if (("DVI").equals(categorySearchForm.getType())) {
            //categorySearchForm.setCreatedBy(getUserId());
        }

        List<Category> listCategory = categoryDao.findCategory(categorySearchForm, start, count, sortField);

        jsonDataGrid.setItems(listCategory);
        jsonDataGrid.setTotalRows(count.intValue());
        return "gridData";
    }

    public String checkCategory() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        Category bo = new Category();

        bo.setType(categoryAddEditForm.getType());
        bo.setCode(categoryAddEditForm.getCode());
        bo.setName(categoryAddEditForm.getName());
        bo.setDescription(categoryAddEditForm.getDescription());
        bo.setIsActive(categoryAddEditForm.getIsActive());

        if ("VOLT".equals(categoryAddEditForm.getType())) {
            bo.setCreatedBy(getDeptRepresentId());
        }
        bo.setCategoryId(categoryAddEditForm.getCategoryId());
        try {
            bo = categoryDao.checkBO(bo);

            if (bo == null) {
                resultMessage.add("1");
                resultMessage.add("Danh mục chưa tồn tại");
            } else {
                resultMessage.add("3");
                if (bo.getCode().equals(categoryAddEditForm.getCode().trim())) {
                    resultMessage.add("Mã danh mục đã tồn tại! Không thể thực hiện thao tác");
                } else if (bo.getName().equals(categoryAddEditForm.getName().trim())) {
                    resultMessage.add("Tên danh mục đã tồn tại! Không thể thực hiện thao tác");
                }
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Danh mục đã tồn tại với mã này! Không thể thực hiện thao tác");
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return "gridData";
    }

//      public String checkCategorySp() {
//        List resultMessage = new ArrayList();
//        List customInfo = new ArrayList();
//        Category bo = new Category();
//
//        bo.setType("SP");
//        bo.setCode(categoryAddEditFormSp.getCode());
//        bo.setName(categoryAddEditFormSp.getName());
//        bo.setDescription(categoryAddEditFormSp.getDescription());
//        bo.setIsActive(categoryAddEditFormSp.getIsActive());
//
//        if ("VOLT".equals(categoryAddEditFormSp.getType())) {
//            bo.setCreatedBy(getDeptRepresentId());
//        }
//        bo.setCategoryId(categoryAddEditFormSp.getCategoryId());
//        try {
//            bo = categoryDao.checkBO(bo);
//
//            if (bo == null) {
//                resultMessage.add("1");
//                resultMessage.add("Danh mục chưa tồn tại");
//            } else {
//                resultMessage.add("3");
//                if (bo.getCode().equals(categoryAddEditFormSp.getCode().trim())) {
//                    resultMessage.add("Mã danh mục đã tồn tại! Không thể thực hiện thao tác");
//                } else if (bo.getName().equals(categoryAddEditFormSp.getName().trim())) {
//                    resultMessage.add("Tên danh mục đã tồn tại! Không thể thực hiện thao tác");
//                }
//            }
//
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            resultMessage.add("3");
//            resultMessage.add("Danh mục đã tồn tại với mã này! Không thể thực hiện thao tác");
//        }
//
//        jsonDataGrid.setItems(resultMessage);
//        jsonDataGrid.setCustomInfo(customInfo);
//        return "gridData";
//    }
    public String insertCategory() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();

        try {
            CategoryDAOHE.removeCacheCategory(categoryAddEditForm.getType());
            Long categoryId = categoryAddEditForm.getCategoryId();
            if (categoryId == null) {
                Category bo = new Category();
                categoryDao.formToBO(categoryAddEditForm, bo);
                //bo.setCreatedBy(getDeptRepresentId());
                bo.setCreatedBy(getUserId());
                bo.setCreateDate(categoryDao.getSysdate());
                categoryDao.create(bo);
                resultMessage.add("1");
                resultMessage.add("Thêm mới danh mục thành công");
            } else {
                Category bo = categoryDao.findById(categoryId, false);
                categoryDao.formToBO(categoryAddEditForm, bo);
                bo.setModifiedBy(getUserId());
                bo.setModifyDate(categoryDao.getSysdate());
                if (bo.getCreatedBy().equals(getUserId())) {
                    categoryDao.update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật danh mục thành công");
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Danh mục của người dùng khác bạn không thể sửa đổi.");
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Cập nhật danh mục không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return "gridData";
    }

    public String insertCategorySp() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();

        try {
            CategoryDAOHE.removeCacheCategory("SP");
            Long categoryId = categoryAddEditFormSp.getCategoryId();
            if (categoryId == null) {
                Category bo = new Category();
                categoryDao.formToBO(categoryAddEditFormSp, bo);
                //bo.setCreatedBy(getDeptRepresentId());
                bo.setCreatedBy(getUserId());
                bo.setCreateDate(categoryDao.getSysdate());
                categoryDao.create(bo);
                resultMessage.add("1");
                resultMessage.add("Thêm mới danh mục thành công");
            } else {
                Category bo = categoryDao.findById(categoryId, false);
                categoryDao.formToBO(categoryAddEditFormSp, bo);
                bo.setModifiedBy(getUserId());
                bo.setModifyDate(categoryDao.getSysdate());
                if (bo.getCreatedBy().equals(getUserId())) {
                    categoryDao.update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật danh mục thành công");
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Danh mục của người dùng khác bạn không thể sửa đổi.");
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Cập nhật danh mục không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return "gridData";
    }

    public String showEditPopup() {
        Long categoryId = Long.valueOf(getRequest().getParameter("categoryId"));
        Category bo = categoryDao.findById(categoryId, false);

        jsonDataGrid.setCustomInfo(bo);
        return "gridData";
    }

    public String gridDeleteData() throws Exception {
        List resultMessage = new ArrayList();
        try {
            if (lstItemOnGrid.size() > 0) {
                CategorySearchForm form = lstItemOnGrid.get(0);
                CategoryDAOHE.removeCacheCategory(form.getType());
            }
            boolean bReturn = false;
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                CategorySearchForm form = lstItemOnGrid.get(i);
                if (form != null && form.getCategoryId() != null && form.getCategoryId() != 0L) {
                    if (!bhe.isExistProvince(form.getCategoryId())) {
                        Category bo = categoryDao.getById("categoryId", form.getCategoryId());
                        if (bo != null) {
                            if (bo.getCreatedBy().equals(getUserId())) {
                                bo.setIsActive("-1");
                                getSession().update(bo);
                                bReturn = true;
                            } else {
                                bReturn = false;
                            }
                        }
                    } else {
                        bReturn = false;
                    }
                }
            }
            if (bReturn) {
                resultMessage.add("1");
                resultMessage.add("Xóa danh mục thành công");
            } else {
                resultMessage.add("3");
                resultMessage.add("Có ràng buộc, không thể xóa hoặc bạn đang thực hiện xóa không phải dữ liệu người dùng tạo.");
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Xóa danh mục không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return "gridData";
    }

    /*
     * oninsert 
     */

    public String onInsertCategory() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        try {
            CategoryDAOHE he = new CategoryDAOHE();
            if (he.isDuplicate(createForm)) {
                resultMessage.add("3");
                resultMessage.add("Thông tin loại danh mục bị trùng");
            } else {
                Long categoryId = createForm.getCategoryId();

                if (categoryId == null) {
                    Category bo = createForm.convertToEntity();
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới danh mục thành công");
                } else {
                    Category bo = createForm.convertToEntity();
                    getSession().update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật danh mục thành công");
                }
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Cập nhật loại danh mục không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /*
     * 
     */

    public String onSearchCategory() {
        getGridInfo();

        CategoryDAOHE he = new CategoryDAOHE();
        GridResult gr = he.findCategory(searchForm, start, count, sortField);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());

        return GRID_DATA;
    }

    /*
     * 
     */

    public String onDelete() throws Exception {
        List resultMessage = new ArrayList();
        try {
            CategoryDAOHE cthe = new CategoryDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                CategorySearchForm form = lstItemOnGrid.get(i);
                if (form != null && form.getCategoryId() != null && form.getCategoryId() != 0L) {
                    if (!bhe.isExistProvince(form.getCategoryId())) {
                        Category bo = cthe.getById("categoryId", form.getCategoryId());
                        if (bo != null) {
                            bo.setIsActive("0");
                            getSession().update(bo);
                        }
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

    public DojoJSON getJsonDataGrid() {
        return jsonDataGrid;
    }

    public void setJsonDataGrid(DojoJSON jsonDataGrid) {
        this.jsonDataGrid = jsonDataGrid;
    }

    public String getForwardPage() {
        return forwardPage;
    }

    public void setForwardPage(String forwardPage) {
        this.forwardPage = forwardPage;
    }

    public CategorySearchForm getCategorySearchForm() {
        return categorySearchForm;
    }

    public void setCategorySearchForm(CategorySearchForm categorySearchForm) {
        this.categorySearchForm = categorySearchForm;
    }

    public CategorySearchForm getCategoryAddEditForm() {
        return categoryAddEditForm;
    }

    public void setCategoryAddEditForm(CategorySearchForm categoryAddEditForm) {
        this.categoryAddEditForm = categoryAddEditForm;
    }

    public CategorySearchForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(CategorySearchForm searchForm) {
        this.searchForm = searchForm;
    }

    public CategorySearchForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(CategorySearchForm createForm) {
        this.createForm = createForm;
    }

    public CategoryDAOHE getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao(CategoryDAOHE categoryDao) {
        this.categoryDao = categoryDao;
    }

    public BusinessDAOHE getBhe() {
        return bhe;
    }

    public void setBhe(BusinessDAOHE bhe) {
        this.bhe = bhe;
    }

    public List<CategorySearchForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<CategorySearchForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

    public CategorySearchForm getCategoryAddEditFormSp() {
        return categoryAddEditFormSp;
    }

    public void setCategoryAddEditFormSp(CategorySearchForm categoryAddEditFormSp) {
        this.categoryAddEditFormSp = categoryAddEditFormSp;
    }

}
