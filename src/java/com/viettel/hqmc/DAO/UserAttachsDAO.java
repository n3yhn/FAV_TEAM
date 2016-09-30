/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.common.util.UploadFile;
import com.viettel.hqmc.BO.UserAttachs;
import com.viettel.hqmc.DAOHE.UserAttachsDAOHE;
import com.viettel.hqmc.FORM.UserAttachsForm;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import static com.viettel.voffice.database.DAO.UploadIframeDAO.getSafeFileName;
import static com.viettel.voffice.database.DAO.UploadIframeDAO.toNoneUnicode;
import com.viettel.voffice.database.DAOHibernate.EventLogDAOHE;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

/**
 *
 * @author binhnt53
 */
public class UserAttachsDAO extends BaseDAO {

    private UserAttachsForm createForm;
    private UserAttachsForm searchForm;
    private final String forwardPage = "userAttachsPage";
    private UserAttachsDAOHE userAttachsDAOHE;
    private List<UserAttachsForm> lstItemOnGrid;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserAttachsDAO.class);
//==============================================================================

    /**
     *
     * @return
     */
    public String getUserAttach() {
        getGridInfo();
        List customInfo = new ArrayList();
        userAttachsDAOHE = new UserAttachsDAOHE();
        GridResult result = userAttachsDAOHE.getLstUserAttach(getUserId(), start, count, sortField);
        jsonDataGrid.setItems(result.getLstResult());
        jsonDataGrid.setTotalRows(result.getnCount().intValue());
        jsonDataGrid.setCustomInfo(customInfo);
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
            userAttachsDAOHE = new UserAttachsDAOHE();
            if (userAttachsDAOHE.isDuplicate(createForm, getUserId())) {
                resultMessage.add("3");
                resultMessage.add("Thông tin Hồ sơ cá nhân bị trùng");
            } else {
                Long id = Long.parseLong(createForm.getUploadId());
                UserAttachs boOld = userAttachsDAOHE.findById(id);
                boOld.getAttachPath();
                UserAttachs bo = createForm.convertToEntity();
                bo.setUserAttachsId(boOld.getUserAttachsId());
                bo.setAttachPath(boOld.getAttachPath());
                bo.setFileName(boOld.getFileName());
                bo.setIsActive(Constants.ACTIVE_STATUS.ACTIVE);
                bo.setCreateDate(boOld.getCreateDate());
                bo.setCreatedBy(boOld.getCreatedBy());
                if (bo.getUserAttachsId() == null) {
                    bo.setCreatedBy(getUserId());
                    bo.setCreateDate(getSysdate());
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới Hồ sơ pháp lí thành công");
                    EventLogDAOHE edhe = new EventLogDAOHE();
                    edhe.insertEventLog("Uploadfile", "Thêm mới Upload id=" + bo.getUserAttachsId(), getRequest());
                } else {
                    if (id != null) {
                        bo.setModifiedBy(getUserId());
                        bo.setModifyDate(getSysdate());

                        getSession().getTransaction().commit();
                        getSession().getTransaction().begin();
                        getSession().update(bo);
                        getSession().getTransaction().commit();

                        resultMessage.add("1");
                        resultMessage.add("Lưu Hồ sơ pháp lí thành công");
                        EventLogDAOHE edhe = new EventLogDAOHE();
                        edhe.insertEventLog("Uploadfile", "Cập nhật Upload id=" + bo.getUserAttachsId(), getRequest());
                    } else {
                        resultMessage.add("3");
                        resultMessage.add("Lưu Hồ sơ pháp lí không thành công");
                    }

                }
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Lưu Hồ sơ pháp lí không thành công");
//            Logger.getLogger(UserAttachsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String uploadFile() {
        try {
            HttpServletRequest request = getRequest();
            String strId = request.getParameter("id");
            MultiPartRequestWrapper multi = (MultiPartRequestWrapper) request;
            Enumeration files = multi.getFileParameterNames();
            String fieldName = "";
            String fileName = "";
            File file;
            while (files.hasMoreElements()) {
                fieldName = (String) files.nextElement();
                file = multi.getFiles(fieldName)[0];
                fileName = multi.getFileNames(fieldName)[0];
                fileName = getSafeFileName(fileName);
                fileName = toNoneUnicode(fileName);

                if (true) {
                    String filePath = UploadFile.uploadFileUserAttach("temp", fileName, file, getRequest(), getUserLogin());
                    UserAttachs bo = new UserAttachs();
                    userAttachsDAOHE = new UserAttachsDAOHE();
                    Long id = 0L;
                    int version = userAttachsDAOHE.isDuplicateFileName(fileName, getUserId());
                    if (createForm != null && createForm.getUserAttachsId() != null) {
                        bo = userAttachsDAOHE.findById(createForm.getUserAttachsId());
                        //check ten file
                        if (version > 0) {
                            String[] a = fileName.split(".");
                            bo.setFileName(a[0] + "_" + version + "." + a[1]);
                        } else {
                            bo.setFileName(fileName);
                        }
                        bo.setAttachPath(filePath);
                        bo.setIsActive(Constants.ACTIVE_STATUS.DELETED);
                        userAttachsDAOHE.update(bo);
                        id = createForm.getUserAttachsId();
                    } else {
                        //check ten file
                        if (version > 0) {
                            String[] a = fileName.split(".");
                            bo.setFileName(a[0] + "_" + version + "." + a[1]);
                        } else {
                            bo.setFileName(fileName);
                        }
                        bo.setAttachPath(filePath);
                        bo.setIsActive(Constants.ACTIVE_STATUS.DELETED);
                        bo.setCreateDate(getSysdate());
                        bo.setCreatedBy(getUserId());
                        id = userAttachsDAOHE.create(bo);
                    }
                    getSession().getTransaction().commit();
                    getRequest().setAttribute("attachId", id);
                    getRequest().setAttribute("fileName", fileName);
                    getRequest().setAttribute("id", strId);

                    String idSession = (String) getRequest().getSession().getAttribute("idSession");
                    if (idSession == null) {
                        idSession = "";
                    }
                    idSession += id.toString() + ";";
                    getRequest().getSession().setAttribute("idSession", idSession);

                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        return "uploadResultPage";
    }

    /**
     *
     * @return
     */
    public String prepare() {
        try {
            //todo code here
            if (createForm != null) {
                searchForm = new UserAttachsForm();
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
     * @throws Exception
     */
    public String onDelete() throws Exception {
        List resultMessage = new ArrayList();
        try {
            userAttachsDAOHE = new UserAttachsDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                UserAttachsForm form = lstItemOnGrid.get(i);
                if (form != null && form.getUserAttachsId() != null && form.getUserAttachsId() != 0D) {
                    UserAttachs bo = userAttachsDAOHE.getById("userAttachsId", form.getUserAttachsId());
                    if (bo != null) {
                        bo.setIsActive(Constants.ACTIVE_STATUS.DELETED);
                        getSession().update(bo);
                        EventLogDAOHE edhe = new EventLogDAOHE();
                        edhe.insertEventLog("Uploadfile", "Delete UserAttachs id=" + bo.getUserAttachsId(), getRequest());
                    }
                }
            }
            resultMessage.add("1");
            resultMessage.add("Xóa danh mục hồ sơ pháp lí thành công");

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Xóa danh mục hồ sơ pháp lí không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onSearch() {
        getGridInfo();
        userAttachsDAOHE = new UserAttachsDAOHE();
        GridResult gr = userAttachsDAOHE.findUserAttachs(searchForm, getUserId(), start, count, sortField);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }
//==============================================================================    

    /**
     *
     * @return
     */
    public UserAttachsForm getCreateForm() {
        return createForm;
    }

    /**
     *
     * @param createForm
     */
    public void setCreateForm(UserAttachsForm createForm) {
        this.createForm = createForm;
    }

    /**
     *
     * @return
     */
    public UserAttachsForm getSearchForm() {
        return searchForm;
    }

    /**
     *
     * @param searchForm
     */
    public void setSearchForm(UserAttachsForm searchForm) {
        this.searchForm = searchForm;
    }

    /**
     *
     * @return
     */
    public List<UserAttachsForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    /**
     *
     * @param lstItemOnGrid
     */
    public void setLstItemOnGrid(List<UserAttachsForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

}
