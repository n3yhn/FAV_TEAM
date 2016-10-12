/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAO;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.dojoTag.DojoJSON;
import com.viettel.voffice.client.form.PositionForm;
import com.viettel.vsaadmin.database.BO.Position;
import com.viettel.vsaadmin.database.DAOHibernate.PositionDAOHE;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HanPT1
 */
public class PositionDAO extends BaseDAO {

    private String forwardPage = "positionSearch";
    private DojoJSON jsonDataGrid = new DojoJSON();
    private PositionForm positionSearchForm;
    private PositionForm positionAddEditForm;
    private PositionDAOHE positionDAOHE = new PositionDAOHE();
    private List<PositionForm> lstItemOnGrid;


    public String prepareSearch() {    

        return this.forwardPage;
    }

    public String onSearch() {

        getGridInfo();      
        GridResult gridResult = positionDAOHE.findPosition(positionSearchForm, start, count, sortField);
        jsonDataGrid.setItems(gridResult.getLstResult());
        jsonDataGrid.setTotalRows(gridResult.getnCount().intValue());
        
        return "gridData";
    }

    public String onInsert() {

        List resultMessage = new ArrayList();

         try {
            Long posId = positionAddEditForm.getPosId();

            if (posId == null) {
                if (checkPosition(posId) > 0) {
                    resultMessage.add("3");
                    resultMessage.add("Đã tồn tại chức danh");
                } else {
                    Position bo = new Position();
                    positionDAOHE.formToBO(positionAddEditForm, bo);
                    bo.setStatus(Constants.Status.ACTIVE);
                    getSession().save(bo);
                    resultMessage.add("1");
                    resultMessage.add("Thêm mới thành công");
                }
            } else {
                if (checkPosition(posId) > 0) {
                    resultMessage.add("3");
                    resultMessage.add("Đã tồn tại chức danh");
                } else {
                    Position bo = positionDAOHE.findById(posId, false);
                    positionDAOHE.formToBO(positionAddEditForm, bo);
                    positionDAOHE.update(bo);
                    resultMessage.add("1");
                    resultMessage.add("Cập nhật thành công");
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Cập nhật chức danh không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return "gridData";
    }

    public String showEditPopup() {
        Long posId = 0L;
        if(getRequest().getParameter("posId") != null && !"".equals(getRequest().getParameter("posId"))){
            posId = Long.valueOf(getRequest().getParameter("posId"));
        }
        Position bo = positionDAOHE.findById(posId, false);

        jsonDataGrid.setCustomInfo(bo);
        return "gridData";
    }

    public String onDelete() throws Exception {
        List resultMessage = new ArrayList();
        try {
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                PositionForm form = lstItemOnGrid.get(i);
                if (form != null && form.getPosId() != null && form.getPosId() != 0L) {
                    Position bo = positionDAOHE.getById("posId", form.getPosId());
                    if (bo != null) {
                        bo.setStatus(Constants.Status.INACTIVE);
                        getSession().update(bo); 
                    }
                }
            }
            resultMessage.add("1");
            resultMessage.add("Xóa thành công");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Xóa không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return "gridData";
    }

    public int checkPosition(Long posId) {
        int count = 0;
        String posCode = positionAddEditForm.getPosCode();      
        String posCodeBo;
        List<Position> positionList = positionDAOHE.getPosition(posId);
        for (int i = 0; i < positionList.size(); i++) {
            posCodeBo = positionList.get(i).getPosCode();

            if (posCodeBo != null ) {
                if ((posCodeBo.equalsIgnoreCase(posCode))) {
                    count++;
                }
            }
        }
        return count;
    }

     @Override
    public DojoJSON getJsonDataGrid() {
        return jsonDataGrid;
    }

    @Override
    public void setJsonDataGrid(DojoJSON jsonDataGrid) {
        this.jsonDataGrid = jsonDataGrid;
    }
    
    public String getForwardPage() {
        return forwardPage;
    }

    public void setForwardPage(String forwardPage) {
        this.forwardPage = forwardPage;
    }

    public List<PositionForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<PositionForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

    public PositionForm getPositionAddEditForm() {
        return positionAddEditForm;
    }

    public void setPositionAddEditForm(PositionForm positionAddEditForm) {
        this.positionAddEditForm = positionAddEditForm;
    }

    public PositionForm getPositionSearchForm() {
        return positionSearchForm;
    }

    public void setPositionSearchForm(PositionForm positionSearchForm) {
        this.positionSearchForm = positionSearchForm;
    }               
}
