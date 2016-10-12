/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.common.util.LogUtil;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.hqmc.DAOHE.ProcedureDAOHE;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.hqmc.BO.ProcedureDepartment;
import com.viettel.hqmc.FORM.ProcedureDepartmentForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.vsaadmin.client.form.DepartmentForm;
import com.viettel.vsaadmin.database.BO.Department;
import com.viettel.vsaadmin.database.DAOHibernate.DepartmentDAOHE;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author gpdn_cuongnx8
 */
public class ProcedureDAO extends BaseDAO {

    /**
     * .
     */
    private String forwardPage = "procedurePage";
    private Procedure searchForm;
    private Procedure createForm;
    ProcedureDAOHE pdhe = new ProcedureDAOHE();
    private DepartmentForm departmentForm;
    private List<Procedure> lstItemOnGrid;
    private List<ProcedureDepartmentForm> lstProcedureDept;
    private List<DepartmentForm> lstDept;

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ProcedureDAO.class);

    /**
     * @return String
     */
    public String prepare() {
        return this.forwardPage;

    }

    /**
     *
     * @return
     */
    public String onSearch() {
        getGridInfo();
        String type = getRequest().getParameter("type");
        if (searchForm == null) {
            searchForm = new Procedure();
            searchForm.setType(type);
        }

        GridResult gr = pdhe.findProcedure(searchForm, start, count, sortField);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return "gridData";
    }

    /**
     *
     * @return
     */
    public String onInsert() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();

        try {
            if (pdhe.isDuplicate(createForm)) {
                resultMessage.add("3");
                resultMessage.add("Thủ tục đã được khai báo trên hệ thống");
            } else {
                Long procedureId = createForm.getProcedureId();
                if (procedureId == null) {
                    createForm.setCreatedBy(getDeptRepresentId());
                    createForm.setCreateDate(pdhe.getSysdate());
                    pdhe.create(createForm);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới thủ tục thành công");
                } else {
                    createForm.setCreatedBy(getDeptRepresentId());
                    createForm.setCreateDate(pdhe.getSysdate());
                    createForm.setModifiedBy(getDeptRepresentId());
                    createForm.setModifyDate(pdhe.getSysdate());
                    pdhe.update(createForm);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật thủ tục thành công");
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Cập nhật thủ tục không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return "gridData";
    }

    /**
     *
     * @return @throws Exception
     */
    public String onDelete() throws Exception {
        List resultMessage = new ArrayList();
        try {
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                Procedure form = lstItemOnGrid.get(i);
                if (form != null && form.getProcedureId() != null && form.getProcedureId() != 0L) {
                    Procedure bo = pdhe.getById("procedureId", form.getProcedureId());
                    if (bo != null) {
                        bo.setIsActive("2");
                        getSession().update(bo);
                        resultMessage.add("1");
                        resultMessage.add("Xóa thủ tục thành công");
                    }
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Xóa thủ tục không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return "gridData";
    }

    /**
     *
     * @return
     */
    public String onDeleteDept() {//xxxx
        List resultMessage = new ArrayList();
        if (lstProcedureDept != null && !lstProcedureDept.isEmpty()) {
            try {
                List<Long> lstIdOfMainlyTargets = new ArrayList();
                //String deptIdStr = lstProcedureDept.get(0).getDeptId().toString();
                for (int i = 0; i < lstProcedureDept.size(); i++) {
                    //deptIdStr = deptIdStr + "," + lstProcedureDept.get(i).getDeptId();
                    lstIdOfMainlyTargets.add(lstProcedureDept.get(i).getProcedureDepartmentId());
//                    ProcedureDepartmentForm form = lstProcedureDept.get(i);
//                    if (form != null) {
//                        ProcedureDepartment entity = form.convertToEntity();
//                        getSession().delete(entity);
//                    }
                }

                // 11/11/2014 viethd
                //String hql = "delete from ProcedureDepartment p where p.procedureId = ? and p.deptId in (" + deptIdStr + ")";
                String hql = "delete from ProcedureDepartment p where p.procedureDepartmentId in (:lstIdProcedure)";
                Query query = getSession().createQuery(hql);
                query.setParameterList("lstIdProcedure", lstIdOfMainlyTargets);
                query.executeUpdate();

                resultMessage.add("1");
                resultMessage.add("Cập nhật thành công");

            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                log.error(ex.getMessage());
                resultMessage.add("3");
                resultMessage.add("Cập nhật không thành công");
            }

        } else {
            resultMessage.add("3");
            resultMessage.add("Cập nhật không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onInsertDept() {
        List resultMessage = new ArrayList();
        if (lstDept != null && lstDept.size() > 0) {
            try {
                Long procedureId = Long.parseLong(getRequest().getParameter("procedureId"));
                for (int i = 0; i < lstDept.size(); i++) {
                    DepartmentForm form = lstDept.get(i);
                    if (form != null) {
                        ProcedureDepartment entity = new ProcedureDepartment();
                        entity.setProcedureId(procedureId);
                        entity.setDeptId(form.getDeptId());
                        entity.setDeptName(form.getDeptName());
                        getSession().save(entity);
                    }
                }
                resultMessage.add("1");
                resultMessage.add("Cập nhật thành công");

            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                log.error(ex.getMessage());
                resultMessage.add("3");
                resultMessage.add("Cập nhật không thành công");
            }

        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onRemoveDeptProcessing() {
        List resultMessage = new ArrayList();
        try {
            Long procedureId = Long.parseLong(getRequest().getParameter("procedureId"));
            Long deptId = Long.parseLong(getRequest().getParameter("deptId"));
            String hql = "select d from ProcedureDepartment d where d.procedureId =? and d.deptId =?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, procedureId);
            query.setParameter(1, deptId);
            ProcedureDepartment pd = (ProcedureDepartment) query.list().get(0);
            pd.setProcessDeptId(null);
            pd.setProcessDeptName(null);
            getSession().update(pd);
            resultMessage.add("1");
            resultMessage.add("Cập nhật thành công");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Cập nhật không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String onUpdateDeptProcessing() {
        List resultMessage = new ArrayList();
        try {
            Long procedureId = Long.parseLong(getRequest().getParameter("procedureDepartmentId"));
            Long processDeptId = Long.parseLong(getRequest().getParameter("processDeptId"));
            DepartmentDAOHE ddhe = new DepartmentDAOHE();
            Department dd = ddhe.findBOById(processDeptId);
            String processDeptName = dd.getDeptName();
            String hql = "select d from ProcedureDepartment d where d.procedureDepartmentId =?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, procedureId);
            ProcedureDepartment pd = (ProcedureDepartment) query.list().get(0);
            pd.setProcessDeptId(processDeptId);
            pd.setProcessDeptName(processDeptName);
            getSession().update(pd);
            resultMessage.add("1");
            resultMessage.add("Cập nhật thành công");

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Cập nhật không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return @throws Exception
     */
    public String getDeptProcedure() throws Exception {
        getGridInfo();
        Long procedureId = Long.parseLong(getRequest().getParameter("procedureId"));
        GridResult gr = pdhe.getAllDeptOfProcedure(procedureId, start, count);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String searchDeptAddToProcedure() {
        getGridInfo();
        Long procedureId = Long.parseLong(getRequest().getParameter("procedureId"));
        GridResult gr = pdhe.getAllDeptNotInProcedure(procedureId, departmentForm, start, count);
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public DepartmentForm getDepartmentForm() {
        return departmentForm;
    }

    /**
     *
     * @param departmentForm
     */
    public void setDepartmentForm(DepartmentForm departmentForm) {
        this.departmentForm = departmentForm;
    }

    /**
     *
     * @return
     */
    public Procedure getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(Procedure searchForm) {
        this.searchForm = searchForm;
    }

    public Procedure getCreateForm() {
        return createForm;
    }

    public void setCreateForm(Procedure createForm) {
        this.createForm = createForm;
    }

    public List<Procedure> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<Procedure> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

    public List<ProcedureDepartmentForm> getLstProcedureDept() {
        return lstProcedureDept;
    }

    public void setLstProcedureDept(List<ProcedureDepartmentForm> lstProcedureDept) {
        this.lstProcedureDept = lstProcedureDept;
    }

    public List<DepartmentForm> getLstDept() {
        return lstDept;
    }

    public void setLstDept(List<DepartmentForm> lstDept) {
        this.lstDept = lstDept;
    }
}
