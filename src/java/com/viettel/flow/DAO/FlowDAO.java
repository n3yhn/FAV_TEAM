/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.flow.DAO;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.voffice.client.form.FlowForm;
import com.viettel.voffice.client.form.NodeForm;
import com.viettel.voffice.client.form.NodeToNodeForm;
import com.viettel.flow.BO.Node;
import com.viettel.flow.BO.NodeDeptUser;
import com.viettel.flow.BO.NodeToNode;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.flow.DAOHE.FlowDAOHE;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.hqmc.DAOHE.ProcedureDAOHE;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author havm2
 */
public class FlowDAO extends BaseDAO {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FlowDAO.class);
    private FlowForm searchFlowForm;
    private FlowForm createForm;
    private NodeForm viewNodeForm;
    private List<FlowForm> lstItemOnGrid;
    private List<NodeForm> nodeList;
    private List<NodeToNodeForm> nodeToNodeList;
    private List<NodeDeptUser> lstNodeDeptUser;
    Long flowId;
    Long nodeId;
    Long flowType;
    Long fileId;

    public String prepare() {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        List lstTTHC = cdhe.getAllProcedure();
        List lstCategory = new ArrayList();
        lstCategory.addAll(lstTTHC);
        lstCategory.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
        getRequest().setAttribute("lstCategory", lstCategory);

//        ProcedureDAOHE pdhe = new ProcedureDAOHE();
//        Long procedureId = -1L;
//        List lstDept = pdhe.getAllDeptOfProcedureCbx(procedureId, start, count);
//        List lstDeptcbx = new ArrayList();
//        lstDeptcbx.addAll(lstDept);
//        lstDeptcbx.add(0, new Procedure(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
//        getRequest().setAttribute("lstDeptProcedure", lstDeptcbx);
        return "searchPage";
    }

//binhnt53: danh sach don vi cua thu tuc hanh chinh
    public String prepareDepartment() {
        ProcedureDAOHE pdhe = new ProcedureDAOHE();
        try {
            Long procedureId = Long.parseLong(getRequest().getParameter("procedureId"));
            List lstDept = pdhe.getAllDeptOfProcedureCbx(procedureId, start, count);
            if (!lstDept.isEmpty()) {

            }
            com.viettel.dojoTag.DojoJSON lstDeptProcedure = new com.viettel.dojoTag.DojoJSON();
            lstDeptProcedure.setLabel("deptName");
            lstDeptProcedure.setIdentifier("deptId");
            lstDeptProcedure.setItems(lstDept);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "lstDeptProcedure";
    }
//!binhnt53

    public String searchFlow() {
        getGridInfo();
        FlowDAOHE fdhe = new FlowDAOHE();
        Long deptId = getDepartmentId();
        GridResult result = fdhe.searchFlow(deptId, searchFlowForm, start, count);
        jsonDataGrid.setItems(result.getLstResult());
        jsonDataGrid.setTotalRows(result.getnCount().intValue());
        return GRID_DATA;
    }

    public String loadFlowOfMyDept() {
        getGridInfo();
        Long deptId = getDepartmentId();
        FlowDAOHE fdhe = new FlowDAOHE();
        GridResult result = fdhe.searchMyFlow(deptId, flowType, start, count);
        jsonDataGrid.setItems(result.getLstResult());
        jsonDataGrid.setTotalRows(result.getnCount().intValue());
        return GRID_DATA;
    }

    public String viewFlow() {
        return "viewPage";
    }

    public String insertFlow() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();

        try {
            String strResult;
            FlowDAOHE flowDAO = new FlowDAOHE();
            if (flowDAO.isDuplicate(createForm)) {
                resultMessage.add("3");
                resultMessage.add("Thông tin luồng bị trùng");
            } else {
                if (createForm.getFlowId() != null && createForm.getFlowId() > 0) {
                    strResult = "Cập nhật luồng thành công";
                } else {
                    strResult = "Thêm mới luồng thành công";
                }
                getSession().saveOrUpdate(createForm.convertToEntity());
                resultMessage.add("1");
                resultMessage.add(strResult);
            }

        } catch (Exception ex) {
            resultMessage.add("3");
            resultMessage.add("Cập nhật luồng không thành công");
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        jsonDataGrid.setItems(resultMessage);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    public String getNodeOfFlow() {

        try {
            String hql = "select n From Node n where n.isActive = 1 and n.flowId = ?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, flowId);
            List lstResult = query.list();
            jsonDataGrid.setItems(lstResult);

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        return GRID_DATA;
    }

    public String getRelationOfFlow() {
        try {
            String hql = "select n From  NodeToNode n where n.isActive = 1 and (n.nodeToNodePK.previousId in (select nd.nodeId From Node nd where nd.flowId=? and nd.isActive = 1) or n.nodeToNodePK.nextId in (select nde.nodeId From Node nde where nde.flowId=? and nde.isActive = 1))";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, flowId);
            query.setParameter(1, flowId);
            List<NodeToNode> lst = query.list();
            List<NodeToNodeForm> lstResult = new ArrayList<NodeToNodeForm>();
            if (lst != null && lst.size() > 0) {
                for (int i = 0; i < lst.size(); i++) {
                    NodeToNodeForm form = new NodeToNodeForm(lst.get(i));
                    lstResult.add(form);
                }
            }
            jsonDataGrid.setItems(lstResult);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return GRID_DATA;
    }

    public String loadDeptUser() {
        try {
            if (nodeId != null) {
                String hql = "select n From NodeDeptUser n where n.nodeId =?";
                Query query = getSession().createQuery(hql);
                query.setParameter(0, nodeId);
                List<NodeDeptUser> lst = query.list();
                jsonDataGrid.setItems(lst);
                jsonDataGrid.setTotalRows(lst.size());
                jsonDataGrid.setNumRows(lst.size());
            } else {
                jsonDataGrid.setItems(new ArrayList<NodeDeptUser>());
                jsonDataGrid.setTotalRows(0);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return GRID_DATA;
    }

    public String updateNode() {
        List lst = new ArrayList();
        try {
            Node node = viewNodeForm.convertToEntity();
            getSession().saveOrUpdate(node);
            lst.add(node.getNodeId());
        } catch (Exception ex) {
            lst.add("");
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        jsonDataGrid.setItems(lst);
        return GRID_DATA;
    }

    public String updateNodeDeptUser() {
        if (nodeId != null && nodeId > 0l) {
            if (lstNodeDeptUser != null && lstNodeDeptUser.size() > 0) {
                ArrayList lstDel = new ArrayList();
                for (int i = 0; i < lstNodeDeptUser.size(); i++) {
                    NodeDeptUser item = lstNodeDeptUser.get(i);
                    item.setNodeId(nodeId);
                    getSession().saveOrUpdate(item);
                    lstDel.add(item.getNodeDeptUserId());
                }

                // 11/11/2014 viethd
                //sid = "(" + sid + ")";
                //String hql = "delete from NodeDeptUser ndu where ndu.nodeId = ? and ndu.nodeDeptUserId not in " + sid;
                String hql = "delete from NodeDeptUser ndu where ndu.nodeId = :nodeId and ndu.nodeDeptUserId not in (:lstDel)";
                Query query = getSession().createQuery(hql);
                query.setParameter("nodeId", nodeId);
                query.setParameterList("lstDel", lstDel);
                query.executeUpdate();
            }
        }
        jsonDataGrid.setCustomInfo("Cập nhật thành công");
        return GRID_DATA;
    }

    public String saveNode() {
        if (nodeList == null) {
            System.out.println("null");
            String hql = "update Node n set n.isActive = 0 where n.flowId = ?  ";
            Query updateQuery = getSession().createQuery(hql);
            updateQuery.setParameter(0, flowId);
            updateQuery.executeUpdate();
            //
            // Cap nhat NodeInNode
            //
            //
            // Dua tat ca cac relation ve dang khong hoat dong
            //
            hql = "update NodeToNode n set n.isActive = 0 where n.nodeToNodePK.previousId in (select nd.nodeId From Node nd where nd.flowId=?) or n.nodeToNodePK.nextId in (select nde.nodeId From Node nde where nde.flowId=?)";
            updateQuery = getSession().createQuery(hql);
            updateQuery.setParameter(0, flowId);
            updateQuery.setParameter(1, flowId);
            updateQuery.executeUpdate();
        } else {
            Session session = getSession();
            String nodeInFlow = "-1";
            //
            // cap nhat thong tin node
            //
            if (nodeList.size() > 0) {
                flowId = nodeList.get(0).getFlowId();
                for (int i = 0; i < nodeList.size(); i++) {
                    Node entities = nodeList.get(i).convertToEntity();
                    session.saveOrUpdate(entities);
                    nodeId = entities.getNodeId();
                    nodeList.get(i).setNodeId(nodeId);
                    nodeInFlow += "," + nodeId;
                }
            }
            //
            // Xoa cac node khong con trong flow
            //
            // 11/11/2014 viethd
            //nodeInFlow = "(" + nodeInFlow + ")";
            //String hql = "update Node n set n.isActive = 0 where n.flowId = ? and n.nodeId not in " + nodeInFlow;
            String hql = "update Node n set n.isActive = 0 where n.flowId = :flowId and n.nodeId not in (:nodeList)";
            String[] lstNodeStrId = nodeInFlow.split(",");
            ArrayList lstNodeId = new ArrayList();
            if (lstNodeStrId != null && lstNodeStrId.length > 0) {
                for (String node : lstNodeStrId) {
                    Long id = Long.parseLong(node);
                    lstNodeId.add(id);
                }
            }
            Query updateQuery = getSession().createQuery(hql);
            updateQuery.setParameter("flowId", flowId);
            updateQuery.setParameterList("nodeList", lstNodeId);
            updateQuery.executeUpdate();
            //
            // Cap nhat NodeInNode
            //
            //
            // Dua tat ca cac relation ve dang khong hoat dong
            //
            hql = "update NodeToNode n set n.isActive = 0 where n.nodeToNodePK.previousId in (select nd.nodeId From Node nd where nd.flowId=?) or n.nodeToNodePK.nextId in (select nde.nodeId From Node nde where nde.flowId=?)";
            updateQuery = getSession().createQuery(hql);
            updateQuery.setParameter(0, flowId);
            updateQuery.setParameter(1, flowId);
            updateQuery.executeUpdate();
            //
            // cap nhat relation tren form
            //
            if (nodeToNodeList != null) {
                for (int i = 0; i < nodeToNodeList.size(); i++) {
                    int previousIndex = (int) (long) nodeToNodeList.get(i).getPreviousId();
                    int nextIndex = (int) (long) nodeToNodeList.get(i).getNextId();
                    nodeToNodeList.get(i).setPreviousId(nodeList.get(previousIndex).getNodeId());
                    nodeToNodeList.get(i).setNextId(nodeList.get(nextIndex).getNodeId());
                    NodeToNode entity = nodeToNodeList.get(i).convertToEntity();
                    session.saveOrUpdate(entity);
                }
            }
            System.out.println("not null");
        }
        return GRID_DATA;
    }

    public String deleteFlow() {
        //begin binhnt53
        List resultMessage = new ArrayList();
        try {
            //String deleteIds = lstItemOnGrid.get(0).getFlowId().toString();
            ArrayList lstFlowIds = new ArrayList();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                FlowForm form = lstItemOnGrid.get(i);
                if (form != null && form.getFlowId() != null && form.getFlowId() != 0L) {
                    //deleteIds = deleteIds + "," + form.getFlowId();
                    lstFlowIds.add(form.getFlowId());
                }
            }
            // 11/11/2014 viethd
            //deleteIds = "(" + deleteIds + ")";
            //String hql = "select count(f) from Files f where f.flowId in " + deleteIds;
            String hql = "select count(f) from Files f where f.flowId in (:lstFlowIds)";
            Query query = getSession().createQuery(hql);
            query.setParameterList("lstFlowIds", lstFlowIds);
            int total = Integer.parseInt(query.list().get(0).toString());
            //end binhnt53
            if (total > 0) {
                resultMessage.add("3");
                resultMessage.add("Luồng đang tồn tại hồ sơ không xóa được");
            } else {
                //String deleteQuery = "update Flow f set f.isActive = 0 where f.flowId in " + deleteIds;
                String deleteQuery = "update Flow f set f.isActive = 0 where f.flowId in (:lstFlowIds)";

                Query query1 = getSession().createQuery(deleteQuery);
                query1.setParameterList("lstFlowIds", lstFlowIds);
                int n = query1.executeUpdate();
                resultMessage.add("1");
                resultMessage.add("Xóa thành công " + n + " bản ghi");
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Xóa luồng không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String loadNextNodeOfDoc() {
        getGridInfo();
        FlowDAOHE fdhe = new FlowDAOHE();
        GridResult result = fdhe.searchNextNode(fileId, start, count);
        jsonDataGrid.setItems(result.getLstResult());
        jsonDataGrid.setTotalRows(result.getnCount().intValue());

        return GRID_DATA;
    }

    public String moveDocumentToNextNode() {
        FlowDAOHE fdhe = new FlowDAOHE();
        Long deptId = getDeptRepresentId();
        String deptName = getDeptRepresent().getDeptName();
        Long userId = getUserId();
        String userName = getUserName();
        boolean bReturn;
        if (nodeId != null) {
            bReturn = fdhe.moveDocumentToNextNode(deptId, deptName, userId, userName, fileId, nodeId);
        } else {
            bReturn = fdhe.moveDocumentToNextNode(deptId, deptName, userId, userName, fileId);
        }
        if (bReturn) {
            jsonDataGrid.setCustomInfo("Cập nhật thành công");
        } else {
            jsonDataGrid.setCustomInfo("Cập nhật không thành công");
        }
        return GRID_DATA;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public FlowForm getSearchFlowForm() {
        return searchFlowForm;
    }

    public void setSearchFlowForm(FlowForm searchFlowForm) {
        this.searchFlowForm = searchFlowForm;
    }

    public FlowForm getCreateForm() {
        return createForm;
    }

    public NodeForm getViewNodeForm() {
        return viewNodeForm;
    }

    public void setViewNodeForm(NodeForm viewNodeForm) {
        this.viewNodeForm = viewNodeForm;
    }

    public void setCreateForm(FlowForm createForm) {
        this.createForm = createForm;
    }

    public List<FlowForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<FlowForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

    public List<NodeForm> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<NodeForm> nodeList) {
        this.nodeList = nodeList;
    }

    public List<NodeToNodeForm> getNodeToNodeList() {
        return nodeToNodeList;
    }

    public void setNodeToNodeList(List<NodeToNodeForm> nodeToNodeList) {
        this.nodeToNodeList = nodeToNodeList;
    }

    public List<NodeDeptUser> getLstNodeDeptUser() {
        return lstNodeDeptUser;
    }

    public void setLstNodeDeptUser(List<NodeDeptUser> lstNodeDeptUser) {
        this.lstNodeDeptUser = lstNodeDeptUser;
    }

    public Long getFlowType() {
        return flowType;
    }

    public void setFlowType(Long flowType) {
        this.flowType = flowType;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

}
