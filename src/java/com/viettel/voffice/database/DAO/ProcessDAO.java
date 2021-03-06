/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAO;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.voffice.client.form.ProcessForm;
import com.viettel.voffice.database.BO.Process;
import com.viettel.voffice.database.DAOHibernate.EventLogDAOHE;
import com.viettel.voffice.database.DAOHibernate.ProcessDAOHE;
import com.viettel.vsaadmin.database.BO.Department;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.DepartmentDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author user
 */
public class ProcessDAO extends BaseDAO {

    private static final String FORWARD_PAGE = "processPage";
    ProcessDAOHE processDAOHE = new ProcessDAOHE();
    private ProcessForm processForm;
    private ProcessForm processAddEditForm, filesStatusEditForm;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ProcessDAO.class);

    public String prepare() {
        try {
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return FORWARD_PAGE;
    }

    public String searchProcess() {
        getGridInfo();
        AtomicInteger countAI = new AtomicInteger(count);
        List<com.viettel.voffice.database.BO.Process> lstUserses = processDAOHE.getLeaderProcess(getUserId(), processForm, start, countAI, sortField);
        jsonDataGrid.setItems(lstUserses);
        jsonDataGrid.setTotalRows(countAI.intValue());
        return GRID_DATA;
    }

    //DiuLTT add
    public String findProcess() {
        getGridInfo();
        String objectId = getRequest().getParameter("publishDocumentId");
        String objectType = getRequest().getParameter("objectType");
        GridResult gridResult = new GridResult(0, new ArrayList());
        try {
            if (objectId != null
                    && objectType != null) {
                gridResult = processDAOHE.searchProcessOfDoc(Long.valueOf(objectId), Long.valueOf(objectType), start, count, sortField);
                jsonDataGrid.setItems(gridResult.getLstResult());
                jsonDataGrid.setTotalRows(gridResult.getnCount().intValue());
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }

        return GRID_DATA;
    }

    //DiuLTT add
    public String findProcessByStatus() {
        List resultMessage = new ArrayList();
        String objectId = getRequest().getParameter("publishDocumentId");
        String objectType = getRequest().getParameter("objectType");
        String processStatusId = getRequest().getParameter("processStatusId");
        GridResult gridResult = new GridResult(0, new ArrayList());
        try {
            if (objectId != null
                    && !"".equals(objectId)
                    && objectType != null
                    && !"".equals(objectType)
                    && processStatusId != null
                    && !"".equals(processStatusId)) {
                gridResult = processDAOHE.getProcessDoc(Long.valueOf(objectId), getUserId(), Long.valueOf(objectType), Long.valueOf(processStatusId));
                if (gridResult != null && !gridResult.getLstResult().isEmpty()) {
                    Process process = (Process) gridResult.getLstResult().get(0);
                    resultMessage.add(process.getReceiveUser());
                    jsonDataGrid.setCustomInfo(resultMessage);
//                    jsonDataGrid.setItems(gridResult.getLstResult());
//                    jsonDataGrid.setTotalRows(gridResult.getnCount().intValue());
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }

        return GRID_DATA;
    }

    /**
     * 150120 binhnt53 man hinh show dlg sua process
     *
     * @return
     */
    public String showEditPopup() {
        Long processId = Long.valueOf(getRequest().getParameter("processId"));
        Process bo = processDAOHE.findById(processId, false);
        jsonDataGrid.setCustomInfo(bo);
        return "gridData";
    }

    /**
     * 150120 binhnt53 check process
     *
     * @return
     */
    public String checkProcess() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        Process bo = new Process();
        bo.setObjectId(processAddEditForm.getObjectId());
        bo.setProcessId(processAddEditForm.getProcessId());
        bo.setSendUser(processAddEditForm.getSendUser());
        bo.setSendUserId(processAddEditForm.getSendUserId());
        bo.setSendGroup(processAddEditForm.getSendGroup());
        bo.setSendGroupId(processAddEditForm.getSendGroupId());
        bo.setReceiveGroup(processAddEditForm.getReceiveGroup());
        bo.setReceiveGroupId(processAddEditForm.getReceiveGroupId());
        bo.setReceiveUser(processAddEditForm.getReceiveUser());
        bo.setReceiveUserId(processAddEditForm.getReceiveUserId());
//        UsersDAO udao = new UsersDAO();
//        try {
//            if (!udao.checkUserExist(bo.getReceiveUser(), bo.getReceiveUserId())
//                    || !udao.checkUserExist(bo.getSendUser(), bo.getSendUserId())) {
//                resultMessage.add("3");
//                resultMessage.add("Người dùng không tồn tại");
//            } else {
//                resultMessage.add("1");
//                resultMessage.add("Danh mục chưa tồn tại");
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(ProcessDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return "gridData";
    }

    /**
     * 150120 binhnt53 save process
     *
     * @return
     */
    public String onSaveProcess() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();

        try {
            if (processAddEditForm != null
                    && processAddEditForm.getProcessId() != null) {
                ProcessDAOHE pdaohe = new ProcessDAOHE();
                Process p = pdaohe.findById(processAddEditForm.getProcessId());
                if (p != null) {
                    DepartmentDAOHE deptdaohe = new DepartmentDAOHE();
                    Department deptbo;
                    UsersDAOHE usersdaohe = new UsersDAOHE();
                    Users userbo;

                    if (processAddEditForm.getSendUser() != null
                            && "".equals(processAddEditForm.getSendUser()) == false
                            && processAddEditForm.getSendUserId() != null) {
                        p.setSendUserId(processAddEditForm.getSendUserId());

                        userbo = usersdaohe.findById(processAddEditForm.getSendUserId());
                        if (userbo != null && userbo.getFullName() != null) {
                            p.setSendUser(userbo.getFullName());
                        } else {
                            p.setSendUser(processAddEditForm.getSendUser());
                        }
                    } else {
                        p.setSendUser(null);
                        p.setSendUserId(null);
                    }
                    if (processAddEditForm.getReceiveUser() != null
                            && "".equals(processAddEditForm.getReceiveUser()) == false
                            && processAddEditForm.getReceiveUserId() != null) {
                        p.setReceiveUserId(processAddEditForm.getReceiveUserId());

                        userbo = usersdaohe.findById(processAddEditForm.getReceiveUserId());
                        if (userbo != null && userbo.getFullName() != null) {
                            p.setReceiveUser(userbo.getFullName());
                        } else {
                            p.setReceiveUser(processAddEditForm.getReceiveUser());
                        }

                    } else {
                        p.setReceiveUser(null);
                        p.setReceiveUserId(null);
                    }

                    if (processAddEditForm.getReceiveGroup() != null
                            && "".equals(processAddEditForm.getReceiveGroup()) == false
                            && processAddEditForm.getReceiveGroupId() != null) {
                        p.setReceiveGroupId(processAddEditForm.getReceiveGroupId());
//                        deptbo = new Department();
                        deptbo = deptdaohe.findBOById(processAddEditForm.getReceiveGroupId());
                        if (deptbo != null && deptbo.getDeptName() != null) {
                            p.setReceiveGroup(deptbo.getDeptName());
                        } else {
                            p.setReceiveGroup(processAddEditForm.getReceiveGroup());
                        }
                    } else {
                        p.setReceiveGroupId(null);
                        p.setReceiveGroup(null);
                    }
                    if (processAddEditForm.getSendGroup() != null
                            && "".equals(processAddEditForm.getSendGroup()) == false
                            && processAddEditForm.getSendGroupId() != null) {
                        p.setSendGroupId(processAddEditForm.getSendGroupId());
//                        deptbo = new Department();
                        deptbo = deptdaohe.findBOById(processAddEditForm.getSendGroupId());
                        if (deptbo != null && deptbo.getDeptName() != null) {
                            p.setSendGroup(deptbo.getDeptName());
                        } else {
                            p.setSendGroup(processAddEditForm.getSendGroup());
                        }

                    } else {
                        p.setSendGroupId(null);
                        p.setSendGroup(null);
                    }
                    if (processAddEditForm.getStatus() != null
                            && processAddEditForm.getProcessStatus() != null
                            && processAddEditForm.getStatus() > -1L
                            && processAddEditForm.getProcessStatus() > -1L) {
                        p.setStatus(processAddEditForm.getStatus());
                        p.setProcessStatus(processAddEditForm.getProcessStatus());
                    }
                    getSession().update(p);
                    getSession().beginTransaction().commit();
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật luồng xử lý thành công");

                    EventLogDAOHE edhe = new EventLogDAOHE();
                    edhe.insertEventLog("Chuyển luồng hồ sơ", getUserLogin() + " ProcessId=" + processAddEditForm.getProcessId().toString(), getRequest());
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Cập nhật luồng xử lý không thành công");
                }
            } else {
                resultMessage.add("3");
                resultMessage.add("Cập nhật luồng xử lý không thành công");
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Cập nhật luồng xử lý không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return "gridData";
    }

    /**
     * 150120 binhnt53 delete process
     *
     * @return
     * @throws Exception
     */
    public String onDelete() throws Exception {
        List resultMessage = new ArrayList();
        try {
            Long processId = Long.valueOf(getRequest().getParameter("processId"));
            Process p = processDAOHE.findById(processId);
            if (p != null) {
                p.setIsActive(Constants.ACTIVE_STATUS.DELETED);
                processDAOHE.update(p);
                resultMessage.add("1");
                resultMessage.add("Xóa luồng xử lý thành công");
            } else {
                resultMessage.add("3");
                resultMessage.add("Xóa luồng xử lý không thành công");
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Xóa luồng xử lý không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public ProcessDAOHE getProcessDAOHE() {
        return processDAOHE;
    }

    public void setProcessDAOHE(ProcessDAOHE processDAOHE) {
        this.processDAOHE = processDAOHE;
    }

    public ProcessForm getProcessForm() {
        return processForm;
    }

    public void setProcessForm(ProcessForm processForm) {
        this.processForm = processForm;
    }

    public ProcessForm getProcessAddEditForm() {
        return processAddEditForm;
    }

    public void setProcessAddEditForm(ProcessForm processAddEditForm) {
        this.processAddEditForm = processAddEditForm;
    }

    public ProcessForm getFilesStatusEditForm() {
        return filesStatusEditForm;
    }

    public void setFilesStatusEditForm(ProcessForm filesStatusEditForm) {
        this.filesStatusEditForm = filesStatusEditForm;
    }

}
