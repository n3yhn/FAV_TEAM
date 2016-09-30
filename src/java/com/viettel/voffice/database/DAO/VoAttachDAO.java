/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAO;

import com.viettel.common.util.LogUtil;
import com.viettel.voffice.client.form.VoAttachForm;
import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAOHibernate.VoAttachsDAOHE;
import com.viettel.vsaadmin.database.BO.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author DiuLTT
 */
public class VoAttachDAO extends BaseDAO {

    private static Logger logger = Logger.getLogger(VoAttachDAO.class);
    private VoAttachForm fileRelationForm;
    VoAttachsDAOHE voAttDaoHe = new VoAttachsDAOHE();

    public String preInsertRelationDoc() {
        String fileIdStr = getRequest().getParameter("fileId");
        Long fileId = 0L;
        try {
            fileId = Long.valueOf(fileIdStr);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        try {
            Users user = getUser();

            fileRelationForm = new VoAttachForm();
            fileRelationForm.setAttachId(0L);
            fileRelationForm.setUserCreateId(user.getUserId());
            fileRelationForm.setUserName(user.getFullName());
            fileRelationForm.setCreateDate(new Date());
            fileRelationForm.setDeptId(getDepartmentId());
            fileRelationForm.setDeptName(getDepartment().getDeptName());

            getRequest().setAttribute("fileId", fileId);

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "fileRelationForm";
    }

    public String preUpdateRelationDoc() {
        String attachIdStr = getRequest().getParameter("attachId");
        Long attachId = 0L;
        try {
            attachId = Long.valueOf(attachIdStr);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        try {
            VoAttachs bo = voAttDaoHe.findById(attachId, false);
            fileRelationForm = new VoAttachForm();
            voAttDaoHe.boToForm(bo, fileRelationForm);
            getRequest().setAttribute("fileId", fileRelationForm.getObjectId());

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "fileRelationForm";
    }

    public String deleteItem() {
        List resultMessage = new ArrayList();
        String attachIdStr = getRequest().getParameter("attachId");
        Long attachId = 0L;
        try {
            attachId = Long.valueOf(attachIdStr);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        try {
            VoAttachs bo = voAttDaoHe.findById(attachId, false);
            if (bo.getUserCreateId().equals(getUserId())) {
                bo.setIsActive(0L);
                voAttDaoHe.update(bo);
                resultMessage.add("1");
                resultMessage.add("Xóa văn bản thành công");
            } else {
                resultMessage.add("2");
                resultMessage.add("Xóa văn bản không thành công");
            }
        } catch (Exception ex) {
            logger.error(ex);
            resultMessage.add("3");
            resultMessage.add("Có lỗi xảy ra khi thực hiện");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String getFileName() {
        List customInfo = new ArrayList();
        String attachId = getRequest().getParameter("attachId");
        String name = "";
        if (attachId != null && !"".equals(attachId)) {
            VoAttachs vo = voAttDaoHe.getById("attachId", Long.valueOf(attachId));
            if (vo != null) {
                name = vo.getAttachName();
            }
//            extension = name.substring(name.length()-6);
        }
        customInfo.add(name);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    public VoAttachForm getFileRelationForm() {
        return fileRelationForm;
    }

    public void setFileRelationForm(VoAttachForm fileRelationForm) {
        this.fileRelationForm = fileRelationForm;
    }

}
