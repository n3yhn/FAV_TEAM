/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.LogUtil;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.hqmc.BO.TechnicalStandard;
import com.viettel.hqmc.DAOHE.TechnicalStandardDAOHE;
import com.viettel.hqmc.FORM.TechnicalStandardForm;
import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.VoAttachsDAOHE;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vtit_binhnt53
 */
public class TechnicalStandardDAO extends BaseDAO {

    private TechnicalStandardForm searchForm;
    private TechnicalStandardForm createForm;
    private List<TechnicalStandardForm> lstItemOnGrid;
    private List<TechnicalStandardForm> lstItemTerminate;
    private List<TechnicalStandardForm> lstItemPublished;
    private TechnicalStandardDAOHE technicalStandardDAOHE;
    private String forwardPage = "technicalStandardPage";
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TechnicalStandardDAO.class);
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
            if (createForm != null) {
                searchForm = new TechnicalStandardForm();
                searchForm.setStandardType(createForm.getStandardType());
//                System.out.println(searchForm.getStandardType());
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return this.forwardPage;
    }

    /**
     *
     * @return
     */
    public String onSearch() {
        getGridInfo();
        TechnicalStandardDAOHE he = new TechnicalStandardDAOHE();
        GridResult gr = he.findTechnicalStandard(searchForm, start, count, sortField);
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
            TechnicalStandardDAOHE cthe = new TechnicalStandardDAOHE();
            if (cthe.isDuplicate(createForm)) {
                resultMessage.add("3");
                resultMessage.add("Thông tin Qui chuẩn kĩ thuật bị trùng");
            } else {
                Long id = createForm.getTechnicalStandardId();
                TechnicalStandard bo = createForm.convertToEntity();
                bo.setIsActive(1L);
                if (id == null) {
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới Qui chuẩn kĩ thuật thành công");
                } else {
                    getSession().update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật Qui chuẩn kĩ thuật thành công");
                }
                String[] lstVo = createForm.getUploadId().split(";");

                for (int i = 0; i < lstVo.length; i++) {
                    if (lstVo[i] != null) {
                        VoAttachsDAOHE vadhe = new VoAttachsDAOHE();
                        VoAttachs voUpload = vadhe.findById(Long.parseLong(lstVo[i]));
                        voUpload.setObjectId(bo.getTechnicalStandardId());
                        voUpload.setObjectType(23L);
                        getSession().update(voUpload);
                    }
                }
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Cập nhật Qui chuẩn kĩ thuật thành công");
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
            TechnicalStandardDAOHE cthe = new TechnicalStandardDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                TechnicalStandardForm form = lstItemOnGrid.get(i);
                if (form != null && form.getTechnicalStandardId() != null && form.getTechnicalStandardId() != 0D) {
                    TechnicalStandard bo = cthe.getById("technicalStandardId", form.getTechnicalStandardId());
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
//            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Xóa danh mục không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }
    /*cong bo*/

    /**
     *
     * @return
     * @throws Exception
     */
    public String onPublished() throws Exception {
        List resultMessage = new ArrayList();
        String result = "";
        try {
            TechnicalStandardDAOHE cthe = new TechnicalStandardDAOHE();
            for (int i = 0; i < lstItemPublished.size(); i++) {
                TechnicalStandardForm form = lstItemPublished.get(i);
                if (form != null && form.getTechnicalStandardId() != null && form.getTechnicalStandardId() != 0D) {
                    TechnicalStandard bo = cthe.getById("technicalStandardId", form.getTechnicalStandardId());
                    if (bo != null) {
                        if (bo.getStatus() == 0L) {
                            bo.setStatus(1l);
                            getSession().update(bo);
                        } else {
                            result = "Một số tiêu chuẩn không công bố vì không trong trạng thái Chưa công bố";
                        }
                    }
                }
            }
            resultMessage.add("1");
            resultMessage.add("Công bố tiêu chuẩn kĩ thuật thành công. " + result);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Công bố tiêu chuẩn kĩ thuật không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }
    /*cham dut*/

    /**
     *
     * @return
     * @throws Exception
     */
    public String onTerminate() throws Exception {
        List resultMessage = new ArrayList();
        String result = "";
        try {
            TechnicalStandardDAOHE cthe = new TechnicalStandardDAOHE();
            for (int i = 0; i < lstItemTerminate.size(); i++) {
                TechnicalStandardForm form = lstItemTerminate.get(i);
                if (form != null && form.getTechnicalStandardId() != null && form.getTechnicalStandardId() != 0D) {
                    TechnicalStandard bo = cthe.getById("technicalStandardId", form.getTechnicalStandardId());
                    if (bo != null) {
                        if (bo.getStatus() == 1L) {
                            bo.setStatus(2l);
                            getSession().update(bo);
                        } else {
                            result = "Một số tiêu chuẩn không thể chấm dứt vì không trong trạng thái Đã công bố";
                        }
                    }
                }
            }
            resultMessage.add("1");
            resultMessage.add("Chấm dứt hiệu lực tiêu chuẩn kĩ thuật thành công. " + result);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Chấm dứt hiệu lực tiêu chuẩn kĩ thuật không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }
    /**/

    /**
     *
     * @return
     * @throws Exception
     */
    public String onUpdateStatus() throws Exception {
        List resultMessage = new ArrayList();
        try {
            TechnicalStandardDAOHE cthe = new TechnicalStandardDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                TechnicalStandardForm form = lstItemOnGrid.get(i);
                if (form != null && form.getTechnicalStandardId() != null && form.getTechnicalStandardId() != 0D) {
                    TechnicalStandard bo = cthe.getById("technicalStandardId", form.getTechnicalStandardId());
                    if (bo != null) {
                        bo.setStatus(0l);
                        getSession().update(bo);
                    }
                }
            }
            resultMessage.add("1");
            resultMessage.add("Cập nhật trạng thái thành công");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Cập nhật trạng thái không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }
    /*
     * 
     */
    
    /**
     *
     * @return
     */
    public TechnicalStandardForm getSearchForm() {
        return searchForm;
    }

    /**
     *
     * @param searchForm
     */
    public void setSearchForm(TechnicalStandardForm searchForm) {
        this.searchForm = searchForm;
    }

    /**
     *
     * @return
     */
    public TechnicalStandardForm getCreateForm() {
        return createForm;
    }

    /**
     *
     * @param createForm
     */
    public void setCreateForm(TechnicalStandardForm createForm) {
        this.createForm = createForm;
    }

    /**
     *
     * @return
     */
    public TechnicalStandardDAOHE getTechnicalStandardDAOHE() {
        return technicalStandardDAOHE;
    }

    /**
     *
     * @param technicalStandardDAOHE
     */
    public void setTechnicalStandardDAOHE(TechnicalStandardDAOHE technicalStandardDAOHE) {
        this.technicalStandardDAOHE = technicalStandardDAOHE;
    }

    /**
     *
     * @return
     */
    public List<TechnicalStandardForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    /**
     *
     * @param lstItemOnGrid
     */
    public void setLstItemOnGrid(List<TechnicalStandardForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

    /**
     *
     * @return
     */
    public List<TechnicalStandardForm> getLstItemTerminate() {
        return lstItemTerminate;
    }

    /**
     *
     * @param lstItemTerminate
     */
    public void setLstItemTerminate(List<TechnicalStandardForm> lstItemTerminate) {
        this.lstItemTerminate = lstItemTerminate;
    }

    /**
     *
     * @return
     */
    public List<TechnicalStandardForm> getLstItemPublished() {
        return lstItemPublished;
    }

    /**
     *
     * @param lstItemPublished
     */
    public void setLstItemPublished(List<TechnicalStandardForm> lstItemPublished) {
        this.lstItemPublished = lstItemPublished;
    }
}
