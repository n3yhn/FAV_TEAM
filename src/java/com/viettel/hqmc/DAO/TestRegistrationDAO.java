/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.TestRegistration;
import com.viettel.hqmc.DAOHE.TestRegistrationDAOHE;
import com.viettel.hqmc.FORM.TestRegistrationForm;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vtit_binhnt53
 */
public class TestRegistrationDAO extends BaseDAO {

    private String forwardPage = "testRegistrationActionPage";
    private TestRegistrationForm searchForm;
    private TestRegistrationForm createForm;
    TestRegistrationDAOHE testRegistrationDao = new TestRegistrationDAOHE();
    private List<TestRegistrationForm> lstItemOnGrid;

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TestRegistrationDAO.class);

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
//            log.error(ex.getMessage());
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

        TestRegistrationDAOHE bdhe = new TestRegistrationDAOHE();
        GridResult gr = bdhe.findTestRegistration(searchForm, start, count, sortField);

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
            TestRegistrationDAOHE cthe = new TestRegistrationDAOHE();
            if (cthe.isDuplicate(createForm)) {
                resultMessage.add("3");
                resultMessage.add("Thông tin bị trùng");
            } else {
                Long id = createForm.getTestRegistrationId();

                if (id == null) {
                    TestRegistration bo = createForm.convertToEntity();
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới thành công");
                } else {
                    TestRegistration bo = createForm.convertToEntity();
                    getSession().update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật thành công");
                }
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Cập nhật không thành công");
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
            TestRegistrationDAOHE cthe = new TestRegistrationDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                TestRegistrationForm form = lstItemOnGrid.get(i);
                if (form != null && form.getTestRegistrationId() != null && form.getTestRegistrationId() != 0L) {
                    TestRegistration bo = cthe.getById("testRegistrationId", form.getTestRegistrationId());
                    if (bo != null) {
                        bo.setIsActive(0l);
                        getSession().update(bo);
                    }
                }
            }
            resultMessage.add("1");
            resultMessage.add("Xóa thành công");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Xóa không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public TestRegistrationForm getSearchForm() {
        return searchForm;
    }

    /**
     *
     * @param searchForm
     */
    public void setSearchForm(TestRegistrationForm searchForm) {
        this.searchForm = searchForm;
    }

    /**
     *
     * @return
     */
    public TestRegistrationForm getCreateForm() {
        return createForm;
    }

    /**
     *
     * @param createForm
     */
    public void setCreateForm(TestRegistrationForm createForm) {
        this.createForm = createForm;
    }

    /**
     *
     * @return
     */
    public List<TestRegistrationForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    /**
     *
     * @param lstItemOnGrid
     */
    public void setLstItemOnGrid(List<TestRegistrationForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }
}
