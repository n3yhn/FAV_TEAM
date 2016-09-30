/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.flow.DAOHE;

import com.viettel.voffice.client.form.FlowForm;
import com.viettel.flow.BO.Flow;
import com.viettel.flow.BO.Node;
import com.viettel.voffice.database.BO.Process;
import com.viettel.flow.BO.NodeDeptUser;
import com.viettel.hqmc.BO.Files;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.DAOHE.FilesDAOHE;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.List;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class FlowDAOHE extends GenericDAOHibernate<Flow, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FlowDAOHE.class);

    public FlowDAOHE() {
        super(Flow.class);
    }

    public GridResult searchFlow(FlowForm searchForm, int start, int count) {
        List lstParam = new ArrayList();
        try {
            String selectHQL = "SELECT f";
            String countHQL = "SELECT count(f)";
            String hql = " FROM Flow f WHERE f.isActive = 1";
            if (searchForm != null) {
                if (searchForm.getFlowName() != null && searchForm.getFlowName().trim().length() > 0) {
                    hql += " and lower(f.flowName) like ? escape'!'";
                    lstParam.add("%" + convertToLikeString(searchForm.getFlowName()) + "%");
                }
                if (searchForm.getDeptId() != null && searchForm.getDeptId() > 0l) {
                    hql += " and f.deptId = ?";
                    lstParam.add(searchForm.getDeptId());
                }
            }
            hql += " ORDER BY f.flowName";
            Query query = getSession().createQuery(selectHQL + hql);
            Query countQuery = getSession().createQuery(countHQL + hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }
            Long total = (Long) countQuery.uniqueResult();
            query.setFirstResult(start);
            query.setMaxResults(count);
            List lst = query.list();
            GridResult result = new GridResult(total, lst);
            return result;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    /*
     * Tim kiem flow voi vai tro admin
     */
    public GridResult searchFlow(Long deptId, FlowForm searchForm, int start, int count) {
        List lstParam = new ArrayList();
        try {
            String selectHQL = "SELECT f";
            String countHQL = "SELECT count(f)";
            String hql = " FROM Flow f WHERE f.isActive = 1";
            if (searchForm != null) {
                if (searchForm.getFlowName() != null && searchForm.getFlowName().trim().length() > 0) {
                    hql += " and lower(f.flowName) like ? escape'!'";
                    lstParam.add("%" + convertToLikeString(searchForm.getFlowName()) + "%");
                }
                if (searchForm.getDeptId() != null && searchForm.getDeptId() > 0l) {
                    hql += " and f.deptId = ?";
                    lstParam.add(searchForm.getDeptId());
                } else {
                    hql += " and f.deptId in (select v.deptId from VDepartment v where v.deptPath like ?)";
                    lstParam.add("%/" + deptId.toString() + "/%");
                }
            } else {
                hql += " and f.deptId in (select v.deptId from VDepartment v where v.deptPath like ?)";
                lstParam.add("%/" + deptId.toString() + "/%");
            }
            hql += " ORDER BY f.flowName";
            Query query = getSession().createQuery(selectHQL + hql);
            Query countQuery = getSession().createQuery(countHQL + hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }
            Long total = (Long) countQuery.uniqueResult();
            query.setFirstResult(start);
            query.setMaxResults(count);
            List lst = query.list();
            GridResult result = new GridResult(total, lst);
            return result;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    /*
     * Tim kiem flow voi vai tro la admin don vi
     */
    public GridResult searchMyFlow(Long deptId, Long flowType, int start, int count) {
        List lstParam = new ArrayList();
        try {
            String selectHQL = "SELECT f";
            String countHQL = "SELECT count(f)";
            String hql = " FROM Flow f WHERE f.isActive = 1 and f.deptId=? and f.flowType=?";
            hql += " ORDER BY f.flowName";
            lstParam.add(deptId);
            lstParam.add(flowType);
            Query query = getSession().createQuery(selectHQL + hql);
            Query countQuery = getSession().createQuery(countHQL + hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }
            Long total = (Long) countQuery.uniqueResult();
            query.setFirstResult(start);
            query.setMaxResults(count);
            List lst = query.list();
            GridResult result = new GridResult(total, lst);
            return result;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    /*
     * Tim kiem luong theo deptId
     */
    public Flow findByDept(Long deptId, Long categoryId) {
        String hql = "select f from Flow f where f.isActive = 1 and f.deptId=? and f.flowType=?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, deptId);
        query.setParameter(1, categoryId);
        List lst = query.list();
        Flow returnItem;
        if (lst == null || lst.isEmpty()) {
            returnItem = null;
        } else {
            returnItem = (Flow) lst.get(0);
        }
        return returnItem;
    }

    /*
     * Tim node dau tien cua luong
     */
    public Node findFirstNodeOfFlow(Long flowId) {
        Node node = null;
        String hql = "select n from Node n where n.isActive = 1 and n.flowId = ? and n.nodeId not in (select ntn.nodeToNodePK.nextId from NodeToNode ntn where ntn.isActive = 1)";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, flowId);
        List<Node> lst = query.list();
        if (lst != null && lst.size() > 0) {
            node = lst.get(0);
        } else {
            //
            // Khong co node nao dau tien, chon node co id thap nhat
            //
            hql = "select n from Node n where n.isActive = 1 and n.flowId = ? order by n.nodeId";
            query = getSession().createQuery(hql);
            query.setParameter(0, flowId);
            lst = query.list();
            if (lst != null && lst.size() > 0) {
                node = lst.get(0);
            }
        }
        return node;
    }

    /*
     * Tim node ke tiep cua nodeId trong luong flowId
     */
    public List<Node> getNextNodes(Long flowId, Long nodeId) {
        List<Node> lst;
        if (nodeId != null) {
            String hql = "select n from Node n where n.isActive = 1 and n.flowId = ? and n.nodeId in (select ntn.nodeToNodePK.nextId from NodeToNode ntn where ntn.isActive = 1 and ntn.nodeToNodePK.previousId = ?)";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, flowId);
            query.setParameter(1, nodeId);
            lst = query.list();
        } else {
            //
            // neu nodeId = null thi khoi tim node dau tien
            //
            lst = new ArrayList();
            Node firstNode = findFirstNodeOfFlow(flowId);
            if (firstNode != null) {
                lst.add(firstNode);
            } else {
                lst = null;
            }
        }
        return lst;
    }

    /*
     * Tim node ke tiep theo action
     */
    public Node getNextNodeByAction(Long flowId, Long nodeId, String action) {
        List<Node> lst;
        Node node = null;
        if (nodeId != null) {
            String hql = "select n from Node n where n.isActive = 1 and n.flowId = ? and n.nodeId in (select ntn.nodeToNodePK.nextId from NodeToNode ntn where ntn.isActive = 1 and ntn.nodeToNodePK.previousId = ? and lower(ntn.action)=?)";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, flowId);
            query.setParameter(1, nodeId);
            query.setParameter(2, action);
            lst = query.list();
        } else {
            //
            // neu nodeId = null thi khoi tim node dau tien
            //
            lst = new ArrayList();
            Node firstNode = findFirstNodeOfFlow(flowId);
            if (firstNode != null) {
                lst.add(firstNode);
            } else {
                lst = null;
            }
        }
        if (lst != null && lst.size() > 0) {
            node = lst.get(0);
        }
        return node;
    }

    /*
     * Tim node ke tiep cua ho so
     */
    public List<Node> getNextNodes(Long fileId) {
        String hql = "select d from Files d where d.fileId = ?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, fileId);
        List<Files> lstDoc = query.list();
        Files doc = null;
        if (lstDoc != null && lstDoc.size() > 0) {
            doc = lstDoc.get(0);
        }
        List<Node> lst = null;
        if (doc != null) {
            Long flowId = doc.getFlowId();
            Long nodeId = doc.getNodeId();
            lst = getNextNodes(flowId, nodeId);
        }
        return lst;
    }

    /*
     * Tim node ke tiep cua ho so theo action
     */
    public Node getNextNodesByAction(Long fileId, String action) {
        String hql = "select d from Files d where d.fileId = ?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, fileId);
        List<Files> lstDoc = query.list();
        Files doc = null;
        if (lstDoc != null && lstDoc.size() > 0) {
            doc = lstDoc.get(0);
        }
        Node node = null;

        if (doc != null) {
            Long flowId = doc.getFlowId();
            Long nodeId = doc.getNodeId();
            node = getNextNodeByAction(flowId, nodeId, action);
        }
        return node;
    }

    /*
     * Tim cac node ke tiep cua ho so de hien thi tren danh sach
     */
    public GridResult searchNextNode(Long fileId, int start, int count) {
        List lst = null;
        Long total = 0l;
        try {
            String hql = "select d from Files d where d.fileId = ?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, fileId);
            List<Files> lstFile = query.list();
            Files file = null;
            if (lstFile != null && lstFile.size() > 0) {
                file = lstFile.get(0);
            }

            if (file != null) {
                Long flowId = file.getFlowId();
                Long nodeId = file.getNodeId();
                if (nodeId == null) {
                    nodeId = findFirstNodeOfFlow(flowId).getNodeId();
                }
                String selectHQL = "SELECT n";
                String countHQL = "SELECT count(n)";
                hql = " from Node n where n.isActive = 1 and n.flowId = ? and n.nodeId in (select ntn.nodeToNodePK.nextId from NodeToNode ntn where ntn.isActive = 1 and ntn.nodeToNodePK.previousId = ?)";
                Query countQuery = getSession().createQuery(countHQL + hql);
                countQuery.setParameter(0, flowId);
                countQuery.setParameter(1, nodeId);

                total = (Long) countQuery.uniqueResult();

                query = getSession().createQuery(selectHQL + hql);
                query.setParameter(0, flowId);
                query.setParameter(1, nodeId);
                query.setFirstResult(start);
                query.setMaxResults(count);
                lst = query.list();
            }

            GridResult result = new GridResult(total, lst);
            return result;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    /*
     * Chuyen ho so sang node ke tiep, biet nodeid cua node ke tiep
     */
    public boolean moveDocumentToNextNode(Long deptId, String deptName, Long userId, String userName, Long fileId, Long nextNodeId) {
        //
        // tim cac don vi, nguoi tham gia node
        //
        String hql = "select d from Files d where d.fileId = ?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, fileId);
        List<Files> lstDoc = query.list();
        Files doc = null;
        if (lstDoc != null && lstDoc.size() > 0) {
            doc = lstDoc.get(0);
        }

        NodeDAOHE ndhe = new NodeDAOHE();
        Node node = ndhe.findById(nextNodeId);

        if (doc != null) {
            doc.setPreviousNodeId(doc.getNodeId());
            doc.setNodeId(nextNodeId);
            if (!doc.getStatus().equals(Constants.FILE_STATUS.NEW_TO_ADD)) {
                doc.setStatus(node.getStatus());
                doc.setDisplayStatus(FilesDAOHE.getFileStatusName(node.getStatus()));
            }
            String nodePath = doc.getNodeHistory();
            if (nodePath == null || nodePath == "") {
                nodePath = nextNodeId.toString();
            } else {
                nodePath = nodePath + "," + nextNodeId;
            }
            doc.setNodeHistory(nodePath);
            getSession().update(doc);
        }

        //
        // update cac process truoc
        //
        hql = "update Process p set p.status = ? where p.objectType = ? and p.objectId = ? and p.status = ? and ( (p.receiveGroupId = ? and p.receiveUserId is null) or p.receiveUserId =? )";
        query = getSession().createQuery(hql);
        query.setParameter(0, node.getStatus());
        query.setParameter(1, Constants.OBJECT_TYPE.FILES);
        query.setParameter(2, fileId);
        query.setParameter(3, 0l);
        query.setParameter(4, deptId);
        query.setParameter(5, userId);
        query.executeUpdate();
        //
        //
        //
        hql = "select nde from NodeDeptUser nde where nde.nodeId = ?";
        query = getSession().createQuery(hql);
        query.setParameter(0, nextNodeId);
        List<NodeDeptUser> lst = query.list();
        if (lst != null && lst.size() > 0) {
            //
            // chuyen cac NodeDeptUser thanh cac process 
            //
            for (int i = 0; i < lst.size(); i++) {
                NodeDeptUser ndu = lst.get(i);
                Process p = new Process();
                p.setSendGroupId(deptId);
                p.setSendGroup(deptName);
                p.setSendUserId(userId);
                p.setSendUser(userName);
                p.setReceiveGroup(ndu.getDeptName());
                p.setReceiveGroupId(ndu.getDeptId());
                p.setReceiveUser(ndu.getUserName());
                p.setReceiveUserId(ndu.getUserId());
                p.setProcessType(ndu.getProcessType());
                p.setSendDate(new Date());
                p.setReceiveDate(new Date());
                p.setProcessStatus(doc.getStatus());
                p.setStatus(BaseDAO.PROCESS_STATUS_NEW);
                p.setObjectId(fileId);
                p.setObjectType(Constants.OBJECT_TYPE.FILES);
                p.setIsActive(1l);
                getSession().save(p);
            }
        } else {
            //
            // node hien tai ko co ai thi coi nhu da chay qua node hien tai va chuyen sang node ke tiep :-)
            //
            moveDocumentToNextNode(deptId, deptName, userId, userName, fileId);

        }
        return true;
    }

    /*
     * Chuyen ho so ve node truoc do ma no di qua
     */
    public boolean moveDocumentToPreviousNode(Long deptId, String deptName, Long userId, String userName, Long fileId, Long previousNodeId) {
        //
        // tim cac don vi, nguoi tham gia node
        //
        Long nodeId = previousNodeId;
        String hql = "select d from Files d where d.fileId = ?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, fileId);
        List<Files> lstDoc = query.list();
        Files doc = null;
        if (lstDoc != null && lstDoc.size() > 0) {
            doc = lstDoc.get(0);
        }

        NodeDAOHE ndhe = new NodeDAOHE();
        Node node = ndhe.findById(previousNodeId);

        if (doc != null) {
            //doc.setPreviousNodeId(doc.getNodeId());
            doc.setNodeId(nodeId);
            doc.setStatus(node.getStatus());
            String nodePath = doc.getNodeHistory();
            if (nodePath != null) {
                String[] nodes = nodePath.split(",");
                if (nodes != null && nodes.length > 2) {
                    previousNodeId = Long.parseLong(nodes[nodes.length - 3]);
                } else {
                    previousNodeId = null;
                }
                nodePath = nodePath.substring(0, nodePath.lastIndexOf(","));
            }
            doc.setPreviousNodeId(previousNodeId);
            doc.setNodeHistory(nodePath);

            getSession().update(doc);
        }

        //
        // update cac process truoc
        //
        hql = "update Process p set p.status = ? where p.objectType = ? and p.objectId = ? and p.status = ? and ( (p.receiveGroupId = ? and p.receiveUserId is null) or p.receiveUserId =? )";
        query = getSession().createQuery(hql);
        query.setParameter(0, node.getStatus());
        query.setParameter(1, Constants.OBJECT_TYPE.FILES);
        query.setParameter(2, fileId);
        query.setParameter(3, 0l);
        query.setParameter(4, deptId);
        query.setParameter(5, userId);
        query.executeUpdate();
        //
        //
        //
        hql = "select nde from NodeDeptUser nde where nde.nodeId = ?";
        query = getSession().createQuery(hql);
        query.setParameter(0, nodeId);
        List<NodeDeptUser> lst = query.list();
        if (lst != null && lst.size() > 0) {
            //
            // chuyen cac NodeDeptUser thanh cac process 
            //
            for (int i = 0; i < lst.size(); i++) {
                NodeDeptUser ndu = lst.get(i);
                Process p = new Process();
                p.setSendGroupId(deptId);
                p.setSendGroup(deptName);
                p.setSendUserId(userId);
                p.setSendUser(userName);
                p.setReceiveGroup(ndu.getDeptName());
                p.setReceiveGroupId(ndu.getDeptId());
                p.setReceiveUser(ndu.getUserName());
                p.setReceiveUserId(ndu.getUserId());
                p.setProcessType(ndu.getProcessType());
                p.setSendDate(new Date());
                p.setReceiveDate(new Date());
                p.setProcessStatus(doc.getStatus());
                p.setStatus(BaseDAO.PROCESS_STATUS_NEW);
                p.setObjectId(fileId);
                p.setObjectType(Constants.OBJECT_TYPE.FILES);
                p.setIsActive(1l);
                getSession().save(p);
            }
        } else {
            //
            // node hien tai ko co ai thi coi nhu da chay qua node hien tai va chuyen sang node ke tiep :-)
            //
            moveDocumentToNextNode(deptId, deptName, userId, userName, fileId);

        }
        return true;
    }

    /*
     * chuyen ho so sang node ke tiep khi ko biet node ke tiep
     */
    public boolean moveDocumentToNextNode(Long deptId, String deptName, Long userId, String userName, Long fileId) {
        boolean bReturn = true;

        List<Node> lstNode = getNextNodes(fileId);

        if (lstNode != null && lstNode.size() > 0) {
            if (lstNode.size() == 1) {
                bReturn = moveDocumentToNextNode(deptId, deptName, userId, userName, fileId, lstNode.get(0).getNodeId());
            } else {
                //
                // neu so node > 1 thi nguoi dung phai chon node di den
                //
                bReturn = false;
            }
        } else {
            //
            // Khong con node nao thi nam tai day :-)
            //
//            String hql = "update Files d set d.status = ? where d.fileId = ?";
//            Query query = getSession().createQuery(hql);
//            query.setParameter(0, Constants.FILE_STATUS.APPROVED);
//            query.setParameter(1, fileId);
//            int n = query.executeUpdate();
        }
        return bReturn;

    }

    /*
     * Chuyen ho so sang node ke tiep dua theo ho so
     */
    public boolean moveDocumentToNextNodeStatus(Long deptId, String deptName, Long userId, String userName, Long fileId, Long status) {
        boolean bReturn = true;
//        boolean bHaveNode = false;

        List<Node> lstNode = getNextNodes(fileId);

        if (lstNode != null && lstNode.size() > 0) {
            if (lstNode.size() == 1) {
                if (lstNode.get(0).getStatus().equals(status)) {
                    //
                    // Co node duy nhat lai la node tra lai -> ko quay lai ma dung lai
                    //
                    bReturn = moveDocumentToNextNode(deptId, deptName, userId, userName, fileId, lstNode.get(0).getNodeId());
                    //bReturn = true;
                } else {
                    //
                    // node duy nhat ma trang thai la di tiep -> tu dong den node day
                    //
                }
            } else {
                //
                // neu so node > 1 thi nguoi dung phai chon node di den
                //
                bReturn = false;
                for (int i = 0; i < lstNode.size(); i++) {
                    if (lstNode.get(i).getStatus().equals(status)) {
                        bReturn = moveDocumentToNextNode(deptId, deptName, userId, userName, fileId, lstNode.get(i).getNodeId());
//                        bHaveNode = true;
                        break;
                    } else if (Constants.FILE_STATUS.FEDBACK_TO_ADD.equals(status) && Constants.FILE_STATUS.NEW_CREATE.equals(lstNode.get(i).getStatus())) {
                        //
                        // Tim den node tuong duong voi tra lai de bo sung -> moi tao
                        //
                        bReturn = moveDocumentToNextNode(deptId, deptName, userId, userName, fileId, lstNode.get(i).getNodeId());
//                        bHaveNode = true;
                        break;
                    } else if (Constants.FILE_STATUS.FEDBACK_TO_EVALUATE.equals(status) && Constants.FILE_STATUS.ASSIGNED.equals(lstNode.get(i).getStatus())) {
                        //
                        // Tim den node tuong duong voi tra lai de tham dinh lai -> da giao viec
                        //
                        bReturn = moveDocumentToNextNode(deptId, deptName, userId, userName, fileId, lstNode.get(i).getNodeId());
//                        bHaveNode = true;
                        break;
                    } else if (Constants.FILE_STATUS.FEDBACK_TO_REVIEW.equals(status) && Constants.FILE_STATUS.EVALUATED.equals(lstNode.get(i).getStatus())) {
                        //
                        // Tim den node tuong duong voi tra lai de xem xet -> da tham dinh
                        //
                        bReturn = moveDocumentToNextNode(deptId, deptName, userId, userName, fileId, lstNode.get(i).getNodeId());
//                        bHaveNode = true;
                        break;
                    } else if (Constants.FILE_STATUS.EVALUATED.equals(status) && Constants.FILE_STATUS.REVIEWED.equals(lstNode.get(i).getStatus())) {
                        //
                        // Tim den node tuong duong voi tra lai de xem xet -> da tham dinh
                        //
                        bReturn = moveDocumentToNextNode(deptId, deptName, userId, userName, fileId, lstNode.get(i).getNodeId());
//                        bHaveNode = true;
                        break;
                    }
                }

                //}
            }
        } else {
            //
            // Khong con node nao thi ho so ket thuc :-)
            //
            String hql = "update Files d set d.status = ? where d.fileId = ?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, Constants.FILE_STATUS.APPROVED);
            query.setParameter(1, fileId);
            query.executeUpdate();
        }
        return bReturn;

    }

    /*
     * Chuyen ho so sang node ke tiep dua vao action
     */
    public boolean moveDocumentToNextNodeByAction(Long deptId, String deptName, Long userId, String userName, Long fileId, String action) {
        boolean bReturn = true;
        Node node = getNextNodesByAction(fileId, action);
        if (node != null) {
            //
            bReturn = moveDocumentToNextNode(deptId, deptName, userId, userName, fileId, node.getNodeId());
        } else {
            //
            // Khong con node nao thi dung lai tai day :-)
            //
//            String hql = "update Process p set p.status = ? where p.objectType = ? and p.objectId = ?";
//            Query query = getSession().createQuery(hql);
//            query.setParameter(0, BaseDAO.PROCESS_STATUS_FINISH);
//            query.setParameter(1, Constants.OBJECT_TYPE.FILES);
//            query.setParameter(2, fileId);
//            int n = query.executeUpdate();

            /*
             String hql = "update Files d set d.status = ? where d.fileId = ?";
             Query query = getSession().createQuery(hql);
             query.setParameter(0, Constants.FILE_STATUS.APPROVED);
             query.setParameter(1, fileId);
             int n = query.executeUpdate();
             */
        }
        return bReturn;

    }

    public boolean isDuplicate(FlowForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(f) from Flow f where f.isActive = 1 ";
        if (form.getFlowId() != null && form.getFlowId() > 0l) {
            hql += " and f.flowId <> ? ";
            lstParam.add(form.getFlowId());
        }

        if (form.getDeptId() != null && form.getDeptId() > 0l && form.getFlowType() != null && form.getFlowType() > 0l && form.getFlowName() != null && form.getFlowName().trim().length() > 0) {
            hql += " and lower(f.deptId) = ? and lower(f.flowType) = ? and lower(f.flowName) = ?";
            lstParam.add(form.getDeptId());
            lstParam.add(form.getFlowType());
            lstParam.add(form.getFlowName().toLowerCase());
        }

        Query query = getSession().createQuery(hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }

        Long count = Long.parseLong(query.uniqueResult().toString());
        boolean bReturn = false;
        if (count >= 1l) {
            bReturn = true;
        }
        return bReturn;
    }
}
