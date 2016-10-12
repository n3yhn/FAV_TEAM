/*
 * To change this bolate, choose Tools | bolates
 * and open the bolate in the editor.
 */
package com.viettel.voffice.database.DAOHibernate;

import com.viettel.common.util.Constants;
import com.viettel.common.util.DateTimeUtils;
import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.DAOHE.FilesDAOHE;
import com.viettel.hqmc.FORM.ProcessReportForm;
import com.viettel.voffice.client.form.ProcessForm;
import com.viettel.voffice.database.BO.Process;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

/**
 *
 * @author user
 */
public class ProcessDAOHE extends GenericDAOHibernate<Process, Long> {

    private static final Logger log = Logger.getLogger(ProcessDAOHE.class);

    public ProcessDAOHE() {
        super(Process.class);
    }

    //
    // HaVM added update process after assign
    //
    public boolean updateProcessOfUser(Long objectId, Long objectType, Long userId, Long departmentId) {
        boolean bReturn = true;
        try {
            String hql = "update Process p set p.status = ? where p.objectId = ? and p.objectType=? and p.receiveGroupId = ? and (p.receiveUserId is null or (p.receiveUserId is not null and p.receiveUserId=?) or ( p.receiveUserType = 1 or p.receiveUserType = 2))";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, BaseDAO.PROCESS_STATUS_ASSIGNED);
            query.setParameter(1, objectId);
            query.setParameter(2, objectType);
            query.setParameter(3, departmentId);
            query.setParameter(4, userId);
            query.executeUpdate();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            bReturn = false;
        }
        return bReturn;
    }

    public boolean updateProcess(Long objectId, Long objectType, Long departmentId, Long status) {
        boolean bReturn = true;
        try {
            String hql = "update Process p set p.status = ? where p.objectId = ? and p.objectType=? and p.receiveGroupId = ? and (p.receiveUserId is null or ( p.receiveUserType = 1 or p.receiveUserType = 2))";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, status);
            query.setParameter(1, objectId);
            query.setParameter(2, objectType);
            query.setParameter(3, departmentId);
            query.executeUpdate();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            bReturn = false;
        }
        return bReturn;
    }

    public GridResult getProcessOfDocument(Long documentId) {
        String selectHQL = "SELECT p";
        String hql = " FROM Process p WHERE p.isActive = 1 and p.objectId =" + documentId;
        hql += " ORDER BY p.processId, p.receiveGroupId";
        Query query = getSession().createQuery(selectHQL + hql);
        List lst = query.list();
        GridResult result = new GridResult(lst.size(), lst);
        return result;
    }

    public GridResult getProcessOfDocumentGroup(Long deptId, Long userId, Long documentId, int start, int count) {
        try {
            String selectHQL = "SELECT p";
            String countHQL = "SELECT count(p)";
            String hql = " FROM Process p WHERE p.isActive = 1 and p.processType NOT IN (?,?) and p.sendGroupId=? and p.objectId =? and (p.receiveUserId is null or p.receiveUserId <> ?) and (p.receiveUserType is null or (p.receiveUserType <> 1 and p.receiveUserType <> 2))";
            hql += " ORDER BY p.processId, p.receiveGroupId";
            Query query = getSession().createQuery(selectHQL + hql);
            Query countQuery = getSession().createQuery(countHQL + hql);
            query.setParameter(0, BaseDAO.PROCESS_TYPE_COMMENT);
            query.setParameter(1, BaseDAO.PROCESS_TYPE_DOCUMENT);
            query.setParameter(2, deptId);
            query.setParameter(3, documentId);
            query.setParameter(4, userId);
            countQuery.setParameter(0, BaseDAO.PROCESS_TYPE_COMMENT);
            countQuery.setParameter(1, BaseDAO.PROCESS_TYPE_DOCUMENT);
            countQuery.setParameter(2, deptId);
            countQuery.setParameter(3, documentId);
            countQuery.setParameter(4, userId);
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

    public Process getMainOfficeProcess(Long deptId, Long userId, Long objectId, Long objectType) {
        String selectHQL = "SELECT p";
        String hql = " FROM Process p WHERE p.isActive = 1 and p.status!=5 and p.processType=1 and p.sendGroupId=? and p.objectId =? and p.objectType=? and (p.receiveUserId is null or p.receiveUserId <> ?)  and (p.receiveUserType is null or (p.receiveUserType <> 1 and p.receiveUserType <> 2))";
        hql += " ORDER BY p.processId desc, p.receiveGroupId";
        Query query = getSession().createQuery(selectHQL + hql);
        query.setParameter(0, deptId);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, userId);
        List<Process> lst = query.list();
        if (lst != null && lst.size() > 0) {
            return lst.get(0);
        } else {
            return null;
        }
    }

    public Process getMainOfficeProcessDesc(Long objectId, Long objectType) {
        String selectHQL = "SELECT p";
        String hql = " FROM Process p WHERE p.isActive = 1 and p.processType in (-1,1) and p.status != 5 and p.objectId =? and p.objectType=? and (p.receiveUserType is null or (p.receiveUserType <> 1 and p.receiveUserType <> 2))";
        hql += " ORDER BY p.processId desc ";
        Query query = getSession().createQuery(selectHQL + hql);
        query.setParameter(0, objectId);
        query.setParameter(1, objectType);
        List<Process> lst = query.list();
        if (lst != null && lst.size() > 0) {
            return lst.get(0);
        } else {
            return null;
        }
    }

    public Process getMainDeptProcessDocument(Long deptId, Long userId, Long objectId, Long objectType) {
        String selectHQL = "SELECT p";
        String hql = " FROM Process p WHERE p.isActive = 1 and p.processType=1 and p.receiveGroupId=? and p.objectId =? and p.objectType=? and (p.receiveUserId is null or p.receiveUserId <> ?)  and (p.receiveUserType is null or (p.receiveUserType <> 1 and p.receiveUserType <> 2))";
        hql += " ORDER BY p.processId, p.sendGroupId";
        Query query = getSession().createQuery(selectHQL + hql);
        query.setParameter(0, deptId);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, userId);
        List<Process> lst = query.list();
        if (lst != null && lst.size() > 0) {
            return lst.get(0);
        } else {
            return null;
        }
    }

    public List<Process> getCoorOfficeProcess(Long deptId, Long userId, Long objectId, Long objectType) {
        String selectHQL = "SELECT p";
        String hql = " FROM Process p WHERE p.isActive = 1 and p.status!=5 and p.processType=0 and p.sendGroupId=? and p.objectId =? and p.objectType=? and (p.receiveUserId is null or p.receiveUserId <> ?) and (p.receiveUserType is null or p.receiveUserType not in (1,2,4))";
        hql += " ORDER BY p.processId, p.receiveGroupId";
        Query query = getSession().createQuery(selectHQL + hql);
        query.setParameter(0, deptId);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, userId);
        List<Process> lst = query.list();

        return lst;
    }

    public Process getMainStaffProcess(Long deptId, Long userId, Long objectId, Long objectType) {
        String selectHQL = "SELECT p";
        String hql = " FROM Process p WHERE p.isActive = 1 and p.status!=5 and p.processType=1 and p.sendGroupId=? and p.objectId =? and p.objectType=? and p.receiveUserId is not null and p.receiveUserId <>? and (p.receiveUserType is null or (p.receiveUserType <> 1 and p.receiveUserType <> 2))";
        hql += " ORDER BY p.processId, p.receiveGroupId";
        Query query = getSession().createQuery(selectHQL + hql);
        query.setParameter(0, deptId);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, userId);
        List<Process> lst = query.list();
        if (lst != null && lst.size() > 0) {
            return lst.get(0);
        } else {
            return null;
        }
    }

    public Process getMainStaffProcess(Long deptId, Long objectId, Long objectType) {
        String selectHQL = "SELECT p";
        String hql = " FROM Process p WHERE p.isActive = 1 and p.processType=1 and p.sendGroupId=? and p.objectId =? and p.objectType=? and p.receiveUserId is not null and (p.receiveUserType is null or (p.receiveUserType <> 1 and p.receiveUserType <> 2))";
        hql += " ORDER BY p.processId, p.receiveGroupId";
        Query query = getSession().createQuery(selectHQL + hql);
        query.setParameter(0, deptId);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        List<Process> lst = query.list();
        if (lst != null && lst.size() > 0) {
            return lst.get(0);
        } else {
            return null;
        }
    }

    public Process getMainStaffProcessDesc(Long objectId, Long objectType) {
        String selectHQL = "SELECT p";
        String hql = " FROM Process p WHERE p.isActive = 1 and p.processType=1 and p.status !=5 and p.objectId =? and p.objectType=? and p.receiveUserId is not null and (p.receiveUserType is null or (p.receiveUserType <> 1 and p.receiveUserType <> 2))";
        hql += " ORDER BY p.processId desc";
        Query query = getSession().createQuery(selectHQL + hql);
        query.setParameter(0, objectId);
        query.setParameter(1, objectType);
        List<Process> lst = query.list();
        if (lst != null && lst.size() > 0) {
            return lst.get(0);
        } else {
            return null;
        }
    }

    public Process getMainStaffProcessDocument(Long deptId, Long userId, Long objectId, Long objectType) {
        String selectHQL = "SELECT p";
        String hql = " FROM Process p WHERE p.isActive = 1 and p.processType=1 and p.sendGroupId=? and p.objectId =? and p.objectType=? and p.receiveUserId is not null and p.receiveUserId <>? and (p.receiveUserType is null or (p.receiveUserType <> 1 and p.receiveUserType <> 2))";
        hql += " ORDER BY p.processId, p.receiveGroupId";
        Query query = getSession().createQuery(selectHQL + hql);
        query.setParameter(0, deptId);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, userId);
        List<Process> lst = query.list();
        if (lst != null && lst.size() > 0) {
            return lst.get(0);
        } else {
            return null;
        }
    }

    public List<Process> getCoorStaffProcess(Long deptId, Long userId, Long objectId, Long objectType) {
        String selectHQL = "SELECT p";
        String hql = " FROM Process p WHERE p.isActive = 1 and p.status!=5 and p.processType=0 and p.sendGroupId=? and p.objectId =? and p.objectType=? and p.receiveUserId is not null and p.receiveUserId <>? and (p.receiveUserType is null or p.receiveUserType not in (1,2,4))";
        hql += " ORDER BY p.processId, p.receiveGroupId";
        Query query = getSession().createQuery(selectHQL + hql);
        query.setParameter(0, deptId);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, userId);
        List<Process> lst = query.list();

        return lst;
    }

    public List<Process> getCoorStaffProcess(Long deptId, Long objectId, Long objectType) {
        String selectHQL = "SELECT p";
        String hql = " FROM Process p WHERE p.isActive = 1 and p.processType=0 and p.sendGroupId=? and p.objectId =? and p.objectType=? and p.receiveUserId is not null and (p.receiveUserType is null or p.receiveUserType not in (1,2,4))";
        hql += " ORDER BY p.processId, p.receiveGroupId";
        Query query = getSession().createQuery(selectHQL + hql);
        query.setParameter(0, deptId);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        List<Process> lst = query.list();

        return lst;
    }

    public List<Process> getCoorStaffProcessMonitor(Long deptId, Long userId, Long objectId, Long objectType) {
        String selectHQL = "SELECT p";
        String hql = " FROM Process p WHERE p.isActive = 1 and p.processType=0 and p.sendGroupId=? and p.objectId =? and p.objectType=? and p.receiveUserId is not null and p.receiveUserId <>? and p.receiveUserType = 4";
        hql += " ORDER BY p.processId, p.receiveGroupId";
        Query query = getSession().createQuery(selectHQL + hql);
        query.setParameter(0, deptId);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, userId);
        List<Process> lst = query.list();

        return lst;
    }

    public GridResult getProcessOfDocumentUser(Long userId, Long documentId, int start, int count) {
        String selectHQL = "SELECT p";
        String countHQL = "SELECT count(p)";
        String hql = " FROM Process p WHERE p.isActive = 1 and p.sendUserId=? and p.objectId =?";
        hql += " ORDER BY p.processId, p.receiveGroupId";
        Query query = getSession().createQuery(selectHQL + hql);
        Query countQuery = getSession().createQuery(countHQL + hql);
        query.setParameter(0, userId);
        query.setParameter(1, documentId);
        countQuery.setParameter(0, userId);
        countQuery.setParameter(1, documentId);
        Long total = (Long) countQuery.uniqueResult();
        query.setFirstResult(start);
        query.setMaxResults(count);
        List lst = query.list();
        GridResult result = new GridResult(total, lst);
        return result;
    }

    public boolean setMainProcess(Long processId, Long processType) {
        String hql = "UPDATE Process p SET p.processType= :processType"
                + " WHERE p.processId = :processId";
        Query query = getSession().createQuery(hql);
        query.setParameter("processType", processType);
        query.setParameter("processId", processId);
        query.executeUpdate();
        return true;
    }

    public boolean insertProcess(Process item) {
        //
        // check item
        //
        if (item == null) {
            return false;
        }
        if (item.getObjectId() == null) {
            return false;
        }
        if (item.getReceiveGroupId() == null) {
            return false;
        }
        //
        // end check
        //
        if (item.getReceiveUserId() == null || item.getReceiveUserId() <= 0L) {
            if (!isExist(item.getObjectId(), item.getReceiveGroupId())) {
                getSession().save(item);
            }

        } else if (!isExist(item.getObjectId(), item.getReceiveGroupId(), item.getReceiveUserId())) {
            Process oldItem = getProcessNotAssignUSer(item.getObjectId(), item.getReceiveGroupId());
            if (oldItem != null) {
                oldItem.setReceiveUserId(item.getReceiveUserId());
                getSession().update(oldItem);
            } else {
                getSession().save(item);
            }
        }
        getSession().flush();

        return true;

    }

    public boolean updateProcess(Process item) {
        getSession().update(item);
        return true;
    }

//    public boolean deleteProcess(Long userId, String ids) {
//        ids = "(" + ids + ")";
//        String hql = "DELETE FROM Process p WHERE p.sendUserId=? and p.processId IN " + ids;
//        Query query = getSession().createQuery(hql);
//        query.setParameter(0, userId);
//        query.executeUpdate();
//        return true;
//    }
    public boolean deleteProcessOf(Long userId, Long deptId, Long objectId, Long objectType) {
        String hql = "UPDATE Process p SET p.isActive=0 WHERE (p.sendUserId=? or p.sendGroupId =? ) and p.objectId = ? and p.objectType=?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, deptId);
        query.setParameter(2, objectId);
        query.setParameter(3, objectType);
        query.executeUpdate();
        return true;
    }

    public boolean deleteProcessNotIn(Long userId, Long deptId, Long objectId, Long objectType, String ids) {
        try {
            String hql;
            if (!"".equals(ids)) {
                ids = "(" + ids + ")";
                hql = "UPDATE Process p SET p.isActive=0 WHERE (p.sendUserId=? and p.sendGroupId =? ) and p.objectId=? and p.objectType=? and p.processId NOT IN " + ids;
            } else {
                hql = "UPDATE Process p SET p.isActive=0 WHERE (p.sendUserId=? and p.sendGroupId =? ) and p.objectId=? and p.objectType=? ";
            }
            Query query = getSession().createQuery(hql);
            query.setParameter(0, userId);
            query.setParameter(1, deptId);
            query.setParameter(2, objectId);
            query.setParameter(3, objectType);
            query.executeUpdate();
            return true;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return false;
        }
    }

    public Process getProcessMain(Long userId, Long deptId, Long objectId, Long objectType, Long processType) {
        try {
            String hql = "SELECT p FROM Process p WHERE p.isActive=1 and p.receiveUserId=? and p.sendGroupId =? and p.objectId=? and p.objectType=? and p.processType = ?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, userId);
            query.setParameter(1, deptId);
            query.setParameter(2, objectId);
            query.setParameter(3, objectType);
            query.setParameter(4, processType);
            List<Process> lst = query.list();
            if (lst != null && lst.size() > 0) {
                return lst.get(0);
            }
            return null;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    public List<Process> getProcessSalve(Long userId, Long deptId, Long objectId, Long objectType, Long processType) {
        try {
            String hql = "SELECT p FROM Process p WHERE p.isActive=1 and p.sendUserId=? and p.sendGroupId =? and p.objectId=? and p.objectType=? and p.processType = ?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, userId);
            query.setParameter(1, deptId);
            query.setParameter(2, objectId);
            query.setParameter(3, objectType);
            query.setParameter(4, processType);
            return query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    public Process getProcessNotAssignUSer(Long documentId, Long groupId) {
        String hql = "SELECT p FROM Process p WHERE p.objectId = " + documentId
                + " AND p.receiveGroupId = " + groupId
                + " AND p.receiveUserId is null";
        Query query = getSession().createQuery(hql);
        List<Process> lst = query.list();
        Process result = null;
        if (lst != null && lst.size() > 0) {
            result = lst.get(0);
        }
        return result;
    }

    public List<Process> getProcessOfObject(Long objectId, Long objectType) {
        String hql = "select p from Process p where p.isActive = 1 and p.objectId = ? and p.objectType = ? order by p.processId";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, objectId);
        query.setParameter(1, objectType);
        List<Process> lst = query.list();
        /*
         lst.get(0).setOrderProcess(0L);
         int queue = 1;
         /*
         if (lst != null && lst.size() > 0) {
         for (int i = 0; i < lst.size(); i++) {
         Long receiveGroupId = lst.get(i).getReceiveGroupId();
         Long receiveUserId = lst.get(i).getReceiveUserId();
         for (int j = queue; j < lst.size(); j++) {
         if (lst.get(j).getSendGroupId().equals(receiveGroupId)) {
         boolean swap = false;
         if (receiveUserId == null) {
         swap = true;
         } else {
         if (receiveUserId.equals(lst.get(j).getSendUserId())) {
         swap = true;
         }
         }
         if (swap) {
         Process tmp = lst.get(queue);
         lst.set(queue, lst.get(j));
         lst.get(queue).setOrderProcess(lst.get(i).getOrderProcess() + 1);
         lst.set(j, tmp);
         queue = queue + 1;
         }
         }
         }
         }
         }*/
        return lst;
    }

    public GridResult getProcessOfObjectGrid(Long objectId, Long objectType, int start, int count) {
        String hql = "select p from Process p where p.isActive = 1 and p.objectId = ? and p.objectType = ? and p.processType not in (?,?) order by p.processId";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, objectId);
        query.setParameter(1, objectType);
        query.setParameter(2, BaseDAO.PROCESS_TYPE_COMMENT);
        query.setParameter(3, BaseDAO.PROCESS_TYPE_DOCUMENT);
        List<Process> lst = query.list();
//        if (lst != null && lst.size() > 0) {
//            lst.get(0).setOrderProcess(0L);
//            int queue = 1;
//            for (int i = 0; i < lst.size(); i++) {
//                Long receiveGroupId = lst.get(i).getReceiveGroupId();
//                Long receiveUserId = lst.get(i).getReceiveUserId();
//                Long orderProcess = lst.get(i).getOrderProcess();
//                if (orderProcess == null) {
//                    orderProcess = 0l;
//                }
//                for (int j = queue; j < lst.size(); j++) {
//                    if (lst.get(j).getSendGroupId().equals(receiveGroupId)) {
//                        boolean swap = false;
//                        if (receiveUserId == null) {
//                            swap = true;
//                        } else {
//                            if (receiveUserId.equals(lst.get(j).getSendUserId())) {
//                                swap = true;
//                            }
//                        }
//                        if (swap) {
//                            Process tmp = lst.get(queue);
//                            lst.set(queue, lst.get(j));
//                            lst.get(queue).setOrderProcess(orderProcess + 1);
//                            lst.set(j, tmp);
//                            queue = queue + 1;
//                        }
//                    }
//                }
//            }
//        }

        int total = lst.size();
        int size = start + count;
        if (size > lst.size()) {
            size = lst.size();
        }
        lst = lst.subList(start, size);
        GridResult result = new GridResult(total, lst);
        return result;
    }

    public boolean isExist(Long documentId, Long groupId) {
        String hql = "SELECT count(p) FROM Process p WHERE p.objectId = :documentId"
                + " AND p.receiveGroupId =:groupId";
        Query query = getSession().createQuery(hql);
        query.setParameter("documentId", documentId);
        query.setParameter("groupId", groupId);
        Long count = (Long) query.uniqueResult();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExist(Long documentId, Long groupId, Long receiverUserId) {
        String hql = "SELECT count(p) FROM Process p WHERE p.objectId = :documentId"
                + " AND p.receiveGroupId = :groupId"
                + " AND p.receiveUserId = :receiverUserId";
        Query query = getSession().createQuery(hql);
        query.setParameter("documentId", documentId);
        query.setParameter("groupId", groupId);
        query.setParameter("receiverUserId", receiverUserId);
        Long count = (Long) query.uniqueResult();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
    //add:  sytv

    public void formProcessToBO(ProcessForm form, Process bo) {
        try {
            bo.setProcessStatus(1l);
            bo.setObjectId(form.getObjectId());
            bo.setObjectType(1l); // van ban den
            bo.setReceiveUserId(form.getReceiveUserId());
            bo.setReceiveUser(form.getReceiveUser());
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
    }

    //add : sytv
    public List<Process> getLeaderProcess(Long receiveUserId, ProcessForm form, int start, AtomicInteger count, String sortField) {
        List listParam = new ArrayList();
        try {
            StringBuilder strBuf = new StringBuilder(" from Process p where p.isActive = 1 ");
            if (form != null) {
                if (form.getObjectType() != null) {
                    strBuf.append(" and p.objectType = ? ");
                    listParam.add(form.getObjectType());
                }
            }
            Query query = getSession().createQuery("SELECT count(p) " + strBuf.toString());
            for (int i = 0; i < listParam.size(); i++) {
                query.setParameter(i, listParam.get(i));
            }

            int total = Integer.parseInt(query.list().get(0).toString());
            String sortType = null;
            if (sortField != null) {
                if (sortField.indexOf('-') != -1) {
                    sortType = " asc";
                    sortField = sortField.substring(1); // not use in this case
                } else {
                    sortType = " desc";
                }
            }

            if (sortField != null) {
                strBuf.append(" order by ").append(validateColumnName(sortField)).append(" ").append(sortType);
            } else {
                strBuf.append(" order by p.receiveDate asc ");
            }

            query = getSession().createQuery(strBuf.toString());

            for (int i = 0; i < listParam.size(); i++) {
                query.setParameter(i, listParam.get(i));
            }

            query.setFirstResult(start);
            query.setMaxResults(count.intValue());

            List<Process> lstProcesses = query.list();
//            List<ProcessForm> list = new ArrayList<ProcessForm>();
//            for (Process bo : lstProcesses) {
//                list.add(boToForm(bo));
//            }
            count.set(total);
            return lstProcesses;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    public Process getProcess(Long objectId) {
        String hql = "select p From Process p"
                + " WHERE p.isActive = ?"
                + " and p.objectId = ?"
                + " and ( p.processType = ? or p.processType=? )"
                + " and p.status = 0"
                + " order by p.processId desc";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, objectId);
        query.setParameter(2, BaseDAO.PROCESS_TYPE_MAIN_PROCESS);
        query.setParameter(3, BaseDAO.PROCESS_TYPE_COOPERATE_PROCESS);

        List<Process> lst = null;
        try {
            lst = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        Process result = null;
        if (lst != null && lst.size() > 0) {
            result = lst.get(0);
        }
        return result;
    }

    public Process getMainProcess(Long objectId, Long objectType, Long groupId) {
        String hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? and "
                + " p.receiveGroupId = ? and p.receiveUserId is null and (p.processType = ? or p.processType = ?) order by p.processId desc";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, groupId);
        query.setParameter(4, BaseDAO.PROCESS_TYPE_MAIN_PROCESS);
        query.setParameter(5, BaseDAO.PROCESS_TYPE_COOPERATE_PROCESS);

        List<Process> lst = null;
        try {
            lst = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        Process result = null;
        if (lst != null && lst.size() > 0) {
            result = lst.get(0);
        }
        return result;
    }

    public Process getProcess(Long objectId, Long objectType, Long userId) {
        String hql = "select p From Process p WHERE p.isActive = ? and p.processType not in (?,?) and p.processType is not null and p.objectId = ? and p.objectType = ? and "
                + " p.receiveUserId = ? ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, BaseDAO.PROCESS_TYPE_DOCUMENT);
        query.setParameter(2, BaseDAO.PROCESS_TYPE_COMMENT);
        query.setParameter(3, objectId);
        query.setParameter(4, objectType);
        query.setParameter(5, userId);

        List<Process> lst = null;
        try {
            lst = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        Process result = null;
        if (lst != null && lst.size() > 0) {
            result = lst.get(0);
        }
        return result;
    }

    public Process getWorkingProcess(Long objectId, Long objectType, Long deptId) {
        String hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? and "
                + " p.receiveGroupId=? and p.receiveUserId is null and p.status = ? ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, deptId);
        query.setParameter(4, 0l);
        List<Process> lst = null;
        try {
            lst = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        Process result;
        if (lst != null && lst.size() > 0) {
            result = lst.get(0);
        } else {
            //
            // Tim gui den don vi, ko gui cho nguoi cu the
            //
            hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? and "
                    + " p.receiveGroupId=? and p.receiveUserId is null and p.status = ? ";
            query = getSession().createQuery(hql);
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, objectId);
            query.setParameter(2, objectType);
            query.setParameter(3, deptId);
            query.setParameter(4, 0l);
            try {
                lst = query.list();
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
            result = null;
            if (lst != null && lst.size() > 0) {
                result = lst.get(0);
            }
        }
        return result;

    }

    public Process getWorkingProcess(Long objectId, Long objectType, Long deptId, Long userId) {
        String hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? and "
                + " p.receiveGroupId=? and p.receiveUserId = ? and p.status = ? ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, deptId);
        query.setParameter(4, userId);
        query.setParameter(5, 0l);
        List<Process> lst = null;
        try {
            lst = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        Process result;
        if (lst != null && lst.size() > 0) {
            result = lst.get(lst.size() - 1);
        } else {
            //
            // Tim gui den don vi, ko gui cho nguoi cu the
            //
            hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? and "
                    + " p.receiveGroupId=? and p.receiveUserId is null and p.status = ? ";
            query = getSession().createQuery(hql);
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, objectId);
            query.setParameter(2, objectType);
            query.setParameter(3, deptId);
            query.setParameter(4, 0l);
            try {
                lst = query.list();
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
            result = null;
            if (lst != null && lst.size() > 0) {
                result = lst.get(0);
            }
        }
        return result;

    }

    public Process getProcessOfStaff(Long objectId, Long objectType, Long userId) {
        String hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? and "
                + " p.receiveUserId = ? and p.status <> ? ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, userId);
        query.setParameter(4, BaseDAO.PROCESS_STATUS_RETURN);
        List<Process> lst = null;
        try {
            lst = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        Process result = null;
        if (lst != null && lst.size() > 0) {
            result = lst.get(0);
        }
        return result;
    }

    public Process getProcessApprove(Long objectId, Long objectType, Long processType) {
        String hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? and "
                + " p.processType = ? ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, processType);

        List<Process> lst = null;
        try {
            lst = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        Process result = null;
        if (lst != null && lst.size() > 0) {
            result = lst.get(0);
        }
        return result;
    }

    public Process getProcessOffice(Long objectId, Long objectType, Long groupId) {
        String hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? and "
                + "p.receiveGroupId = ? and p.receiveUserId is null order by p.processId desc";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, groupId);

        List<Process> lst = null;
        try {
            lst = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        Process result = null;
        if (lst != null && lst.size() > 0) {
            result = lst.get(0);
        }
        return result;
    }

    public List<Process> getLstProcessOffice(Long objectId, Long objectType, Long groupId) {
        String hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? and "
                + "p.receiveGroupId = ? order by p.processId desc";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, groupId);

        List<Process> lst = null;
        try {
            lst = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        return lst;
    }

    public Process getProcessOfOffice(Long objectId, Long objectType, Long sendGroupId) {
        String hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ?"
                + " and p.receiveGroupId = ? and p.receiveUserId is null and p.processType not in (?,?)";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, sendGroupId);
        query.setParameter(4, BaseDAO.PROCESS_TYPE_COMMENT);
        query.setParameter(5, BaseDAO.PROCESS_TYPE_DOCUMENT);
        List<Process> lst = null;
        try {
            lst = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        Process result = null;
        if (lst != null && lst.size() > 0) {
            result = lst.get(0);
        }
        return result;
    }
    // tim kiem cac process gui den cho don vi trong trong hop muon tra lai

    public Process getProcessOfOfficeForReturn(Long objectId, Long objectType, Long receiveGroupId) {
        String hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? "
                + " and p.receiveGroupId = ? and p.processType in (?,?) and p.status  in (?,?) ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, receiveGroupId);
        query.setParameter(4, BaseDAO.PROCESS_TYPE_MAIN_PROCESS);
        query.setParameter(5, BaseDAO.PROCESS_TYPE_DOCUMENT);
        query.setParameter(6, BaseDAO.PROCESS_STATUS_NEW);
        query.setParameter(7, BaseDAO.PROCESS_STATUS_ASSIGNED);
        List<Process> lst = null;
        try {
            lst = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        Process result = null;
        if (lst != null && lst.size() > 0) {
            result = lst.get(0);
        }
        return result;
    }

    // tim kiem process cua van ban co process_status_id=5
    public Process getProcessStatusOfDocument(Long objectId, Long objectType, Long receiveGroupId) {
        String hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? "
                + " and p.receiveGroupId = ? and p.processStatusId = ? ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);
        query.setParameter(3, receiveGroupId);
        query.setParameter(4, BaseDAO.PROCESS_STATUS_RETURN);
        List<Process> lst = null;
        try {
            lst = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        Process result = null;
        if (lst != null && lst.size() > 0) {
            result = lst.get(0);
        }
        return result;
    }

    // tim kiem cac process do don vi phan cong
    public List<Process> getProcessOfficeOfDocument(Long objectId, Long objectType, Long sendGroupId) {
        List<Process> lst = new ArrayList<Process>();
        try {
            String hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? "
                    + " and p.sendGroupId = ? ";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, objectId);
            query.setParameter(2, objectType);
            query.setParameter(3, sendGroupId);
            lst = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        return lst;
    }
    // tim kiem cac process do don vi phan cong

    public List<Process> getProcessStaffOfDocument(Long objectId, Long objectType, Long sendUserId) {
        List<Process> lst = new ArrayList<Process>();
        try {
            String hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? "
                    + " and p.sendUserId = ? ";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, objectId);
            query.setParameter(2, objectType);
            query.setParameter(3, sendUserId);
            lst = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return lst;
    }

    public List<Process> getProcessOfOffice(Long objectId, Long objectType) {
        String hql = "select p From Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? "
                + " order by p.processId ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, objectId);
        query.setParameter(2, objectType);

        List<Process> lst = null;
        try {
            lst = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return lst;
    }

    public Long getProcessId(Long objectId) {
        Process pc = getProcess(objectId);
        Long processId = null;
        if (pc != null) {
            processId = pc.getProcessId();
        }
        return processId;
    }

    //DiuLTT add
    /*
     * Tim kiem các Process cua 1 van ban
     */
    public GridResult getProcessDoc(Long documentId, Long userId, Long objectType, Long processStatusId) {
        List listParam = new ArrayList();
        StringBuilder selectHQL = new StringBuilder("SELECT p  FROM Process p WHERE p.isActive = ? ");
        listParam.add(Constants.Status.ACTIVE);

        if (documentId != null) {
            selectHQL.append(" and p.objectId = ? ");
            listParam.add(documentId);
        }

        if (objectType != null) {
            selectHQL.append(" and p.objectType = ? ");
            listParam.add(objectType);
        }

        if (processStatusId != null) {
            selectHQL.append(" and p.processStatusId = ? ");
            listParam.add(processStatusId);
        }
        selectHQL.append(" order by p.sendDate desc ");
        Query query = getSession().createQuery(selectHQL.toString());

        for (int i = 0; i < listParam.size(); i++) {
            query.setParameter(i, listParam.get(i));
        }

        List lst = query.list();
        GridResult result = new GridResult(lst.size(), lst);
        return result;
    }

    //DiuLTT add
    /*
     * Tim kiem Process cua 1 van ban
     */
    public GridResult findProcess(Long documentId, Long senderId, Long status, Long objectType, Long receiverId, Long receiveGroupId) {

        List listParam = new ArrayList();
        StringBuilder selectHQL = new StringBuilder("SELECT p  FROM Process p WHERE p.isActive = ? ");
        listParam.add(Constants.Status.ACTIVE);

        if (documentId != null) {
            selectHQL.append(" and p.objectId = ? ");
            listParam.add(documentId);
        }

        if (objectType != null) {
            selectHQL.append(" and p.objectType = ? ");
            listParam.add(objectType);
        }

        if (senderId != null) {
            selectHQL.append(" and p.sendUserId = ? ");
            listParam.add(senderId);
        }

        if (status != null) {
            selectHQL.append(" and p.status = ? ");
            listParam.add(status);
        }
        if (receiverId != null && receiveGroupId == null) {
            selectHQL.append(" and p.receiveUserId = ? ");
            listParam.add(receiverId);
        } else if (receiveGroupId != null && receiverId != null) {
            selectHQL.append(" and (p.receiveUserId = ? or (p.receiveGroupId = ? and p.receiveUserId is null))");
            listParam.add(receiverId);
            listParam.add(receiveGroupId);
        } else if (receiveGroupId != null && receiverId == null) {
            selectHQL.append(" and p.receiveGroupId = ? ");
            listParam.add(receiveGroupId);
        }

        Query query = getSession().createQuery(selectHQL.toString());

        for (int i = 0; i < listParam.size(); i++) {
            query.setParameter(i, listParam.get(i));
        }

        List<Process> lst = query.list();
        GridResult result = new GridResult(lst.size(), lst);
        return result;
    }

    //DiuLTT add
    /*
     * Tim kiem tất cả các Process cua 1 van ban
     */
    public GridResult searchProcessOfDoc(Long objectId, Long objectType, int start, int count, String sortField) {
        List listParam = new ArrayList();
        StringBuilder selectHQL = new StringBuilder("SELECT p FROM Process p WHERE p.isActive = ? and (p.processType = ? or p.processType = ?)");
        listParam.add(Constants.Status.ACTIVE);
        listParam.add(Constants.PROCESS_TYPE.COOPERATE);
        listParam.add(Constants.PROCESS_TYPE.MAIN);

        if (objectId != null) {
            selectHQL.append(" and p.objectId = ? ");
            listParam.add(objectId);
        }

        if (objectType != null) {
            selectHQL.append(" and p.objectType = ? ");
            listParam.add(objectType);
        }

        String sortType;
        if (sortField != null && !"receiveGroup".equals(sortField) && !"-receiveGroup".equals(sortField)) {
            if (sortField.indexOf('-') != -1) {
                sortType = " desc";
//                sortType = " asc";
                sortField = sortField.substring(1);//not use in this case
            } else {
//                sortType = " desc";//binhnt53 u150115
                sortType = " asc";
            }

            selectHQL.append(" order by p.receiveDate asc,").append("p." + validateColumnName(sortField)).append(" ").append(sortType);
        } else {
            selectHQL.append(" order by p.receiveDate asc ");
        }

        Query query = getSession().createQuery(selectHQL.toString());
        for (int i = 0; i < listParam.size(); i++) {
            query.setParameter(i, listParam.get(i));
        }

        query.setFirstResult(start);
        query.setMaxResults(count);

        List<Process> lst = query.list();
//141226 binhnt update view process
        if (lst.size() > 0) {
            for (Process b : lst) {
                if ((b.getProcessStatus().equals(Constants.FILE_STATUS.NEW)
                        || b.getProcessStatus().equals(Constants.FILE_STATUS.APPROVED))
                        || b.getStatus().equals(Constants.FILE_STATUS.NEW_CREATE)) {
                    FilesDAOHE fdaohe = new FilesDAOHE();
                    Files fbo = fdaohe.findById(b.getObjectId());
                    if (fbo != null) {
                        b.setIsFee(fbo.getIsFee());
                    }
                }
            }
        }
//!141226 binhnt update view process
        GridResult result = new GridResult(lst.size(), lst);
        return result;
    }

    /**
     * ADD LAY RA DANH SACH CAC PROCESS TRONG 1 VAN BAN BINHNT53
     *
     * @param objId
     * @param objType
     * @return
     */
    public List<Process> findProcessOfDoc(Long objId, Long objType) {
        List listParam = new ArrayList();
        StringBuilder selectHQL = new StringBuilder("SELECT p FROM Process p WHERE p.isActive = ? ");
        listParam.add(Constants.Status.ACTIVE);

        if (objId != null && objId > 0) {
            selectHQL.append(" and p.objectId = ? ");
            listParam.add(objId);
        }

        if (objType != null && objType > 0) {
            selectHQL.append(" and p.objectType = ? ");
            listParam.add(objType);
        }

        Query query = getSession().createQuery(selectHQL.toString());
        for (int i = 0; i < listParam.size(); i++) {
            query.setParameter(i, listParam.get(i));
        }

        List<Process> lst = query.list();
        return lst;
    }

    // SyTV add
    public GridResult getProcessOfCVP(Long documentId, Long userId, Long objectType) {
        StringBuilder selectHQL = new StringBuilder("SELECT p  FROM Process p WHERE p.isActive = ? and p.objectId = ? ");
        selectHQL.append(" and p.objectType = ? and p.receiveUserId = ? and p.sendUserId = ?");
        Query query = getSession().createQuery(selectHQL.toString());
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, documentId);
        query.setParameter(2, objectType);
        query.setParameter(3, userId);
        query.setParameter(4, userId);
//        query.setParameter(3, userId);
//        query.setParameter(4, Constants.PROCESS_STATUS.INSTRUCTION);

        List lst = query.list();
        GridResult result = new GridResult(lst.size(), lst);
        return result;
    }

    public void updateStatusById(Long objectId, Long receiveGroup, Long receiveUser) {
        try {
            StringBuilder selectHQL = new StringBuilder("SELECT p  FROM Process p WHERE p.isActive = ? and p.objectId = ? ");
            selectHQL.append(" and p.objectType = ? and (p.receiveUserId = ? or (p.receiveGroupId = ? and p.receiveUserId is null))");
            Query query = getSession().createQuery(selectHQL.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, objectId);
            query.setParameter(2, Constants.OBJECT_TYPE.DOCUMENT_RECEIVE);
            query.setParameter(3, receiveUser);
            query.setParameter(4, receiveGroup);
            List<Process> lst = query.list();
            if (lst != null) {
                Process process = lst.get(0);
                process.setStatus(Constants.PROCESS_STATUS.BOOKED);
                this.update(process);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

    }

    public List<Process> getLeaderProcess(Long documentReceiveId, Long sendGroupId, Long receiveUserType) {
        List<Process> lst = new ArrayList();
        try {
            StringBuilder selectHQL = new StringBuilder("SELECT p  FROM Process p WHERE p.isActive = ? and p.objectId = ? ");
            selectHQL.append(" and p.objectType = ? and p.sendGroupId = ? and p.receiveUserType = ?");
            Query query = getSession().createQuery(selectHQL.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, documentReceiveId);
            query.setParameter(2, Constants.OBJECT_TYPE.DOCUMENT_RECEIVE);
            query.setParameter(3, sendGroupId);
            query.setParameter(4, receiveUserType);
            lst = query.list();
            if (!lst.isEmpty()) {
                return lst;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    public List<Process> getOfficeProcess(Long documentReceiveId, Long sendGroupId, Long processType) {
        List<Process> lst = new ArrayList();
        try {
            StringBuilder selectHQL = new StringBuilder("SELECT p  FROM Process p WHERE p.isActive = ? and p.objectId = ? ");
            selectHQL.append(" and p.objectType = ? and p.sendGroupId = ? and p.status!=5 and p.processType= ? and (p.receiveUserType is null or p.receiveUserType =3) ");
            Query query = getSession().createQuery(selectHQL.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, documentReceiveId);
            query.setParameter(2, Constants.OBJECT_TYPE.DOCUMENT_RECEIVE);
            query.setParameter(3, sendGroupId);
            query.setParameter(4, processType);
            lst = query.list();
            if (!lst.isEmpty()) {
                return lst;
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    public List<Process> getOfficeProcess2(Long documentReceiveId, Long sendGroupId, Long processType) {
        List<Process> lst = new ArrayList();
        try {
            StringBuilder selectHQL = new StringBuilder("SELECT p  FROM Process p WHERE p.isActive = ? and p.objectId = ? ");
            selectHQL.append(" and p.objectType = ? and p.sendGroupId = ? and p.processType= ? and (p.receiveUserType is null or p.receiveUserType =3) ");
            Query query = getSession().createQuery(selectHQL.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, documentReceiveId);
            query.setParameter(2, Constants.OBJECT_TYPE.DOCUMENT_RECEIVE);
            query.setParameter(3, sendGroupId);
            query.setParameter(4, processType);
            lst = query.list();
            if (!lst.isEmpty()) {
                return lst;
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    // SyTV add
    //
    public Process getProcessOfLeader(Long objectId, Long objectType, Long userId, Long receiveUserType) {
        try {
            StringBuilder selectHQL = new StringBuilder("SELECT p  FROM Process p WHERE p.isActive = ? and p.objectId = ? ");
            selectHQL.append(" and p.objectType = ? and p.receiveUserId = ? and p.receiveUserType = ? ");
            Query query = getSession().createQuery(selectHQL.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, objectId);
            query.setParameter(2, Constants.OBJECT_TYPE.DOCUMENT_RECEIVE);
            query.setParameter(3, userId);
            query.setParameter(4, receiveUserType);
            List<Process> lst = query.list();
            if (!lst.isEmpty()) {
                return lst.get(0);
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    public Process getProcessOfLeader(Long objectId, Long objectType, Long receiveUserType) {
        try {
            StringBuilder selectHQL = new StringBuilder("SELECT p  FROM Process p WHERE p.isActive = ? and p.objectId = ? ");
            selectHQL.append(" and p.objectType = ? and p.receiveUserType = ? ");
            Query query = getSession().createQuery(selectHQL.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, objectId);
            query.setParameter(2, Constants.OBJECT_TYPE.DOCUMENT_RECEIVE);
            query.setParameter(3, receiveUserType);
            List<Process> lst = query.list();
            if (!lst.isEmpty()) {
                return lst.get(0);
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    // Tim van ban den dc them moi bang process
    public Process getProcessDocumentReceive(Long docId) {
        try {
            StringBuilder selectHQL = new StringBuilder("SELECT p  FROM Process p WHERE p.isActive = ? and p.objectId = ? ");
            selectHQL.append(" and p.objectType = ? and p.processType = ? ");
            Query query = getSession().createQuery(selectHQL.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, docId);
            query.setParameter(2, Constants.OBJECT_TYPE.DOCUMENT_RECEIVE);
            query.setParameter(3, BaseDAO.PROCESS_TYPE_DOCUMENT);
            List<Process> lst = query.list();
            if (!lst.isEmpty()) {
                return lst.get(0);
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    // -- Process -- //
//    public void updateProcessDocument(VoDocumentReceive bo, Department dept) {
//        Long docId = bo.getDocumentReceiveId();
//
//        try {
//            Process oldProcess = getProcessDocumentReceive(docId);
//            if (oldProcess != null) {
//                oldProcess.setStatus(bo.getStatus());
//                oldProcess.setIsActive(bo.getIsActive());
//                oldProcess.setIsRead(bo.getIsRead());
//                this.update(oldProcess);
//            } else {
//                Process process = new Process();
//                process.setIsActive(bo.getIsActive());
//                process.setProcessType(-1L);
//                process.setObjectId(bo.getDocumentReceiveId());
//                process.setObjectType(BaseDAO.OBJECT_TYPE_DOCUMENT_RECEIVE);
//                process.setIsRead(1L);
//                process.setSendDate(bo.getReceiveDate());
//                process.setReceiveDate(bo.getReceiveDate());
//                process.setSendGroupId(dept.getDeptId());
//                process.setSendGroup(dept.getDeptName());
//                process.setStatus(bo.getStatus());
//
//                process.setReceiveGroupId(dept.getDeptId());
//                process.setReceiveGroup(dept.getDeptName());
//                process.setIsRead(bo.getIsRead());
//                this.create(process);
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
    // Tim kiem tat ca cac process cua vanban
    public void deleteAllProcessOfDocument(Long docId) {
        try {
            StringBuilder selectHQL = new StringBuilder("SELECT p FROM Process p WHERE p.isActive = ? and p.objectId = ? ");
            selectHQL.append(" and p.objectType = ?");
            Query query = getSession().createQuery(selectHQL.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, docId);
            query.setParameter(2, Constants.OBJECT_TYPE.DOCUMENT_RECEIVE);
            List<Process> lst = query.list();
            if (!lst.isEmpty()) {
                for (Process process : lst) {
                    process.setIsActive(Constants.Status.INACTIVE);
                    this.update(process);
                }
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
    }

    public boolean isExisted(Long objectId, Long objectType, Long processType, Long sendGroupID) {
        boolean result = false;
        try {

            StringBuilder selectHQL = new StringBuilder("SELECT count(p) FROM Process p WHERE p.isActive = ? and p.objectId = ? "
                    + "and p.objectType = ? and p.processType = ? and  p.sendGroupId = ? ");
            Query query = getSession().createQuery(selectHQL.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, objectId);
            query.setParameter(2, objectType);
            query.setParameter(3, processType);
            query.setParameter(4, sendGroupID);
            Long count = (Long) query.uniqueResult();
            if (count > 0) {
                result = true;
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901

        }
        return result;
    }

    public void deleteReturnProcess(Long objectId, Long objectType, Long deptId) {
        try {
            StringBuilder selectHQL = new StringBuilder("SELECT p  FROM Process p WHERE p.isActive = ? and p.objectId = ? ");
            selectHQL.append(" and p.objectType = ? and p.processType = ? and p.sendGroupId = ? and p.status = ?");
            Query query = getSession().createQuery(selectHQL.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, objectId);
            query.setParameter(2, Constants.OBJECT_TYPE.DOCUMENT_RECEIVE);
            query.setParameter(3, BaseDAO.PROCESS_TYPE_MAIN_PROCESS);
            query.setParameter(4, deptId);
            query.setParameter(5, BaseDAO.PROCESS_STATUS_RETURN);
            List<Process> lst = query.list();
            if (lst != null && !lst.isEmpty()) {
                for (Process process : lst) {
                    process.setIsActive(Constants.Status.INACTIVE);
                    this.update(process);
                }
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
    }

    public Long countProcessOfDocument(Long objectId, Long objectType, Long deptId) {
        try {
            StringBuilder selectHQL = new StringBuilder("SELECT count(p.processId)");
            selectHQL.append(" FROM Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? ");
            selectHQL.append(" and p.sendGroupId = ? and p.status = 0 ");
            Query query = getSession().createQuery(selectHQL.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, objectId);
            query.setParameter(2, objectType);
            query.setParameter(3, deptId);
            Long result = (Long) query.uniqueResult();
            return result;
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return 0L;
    }

    public Long countReadProcessOfDocument(Long objectId, Long objectType, Long deptId) {
        try {
            StringBuilder selectHQL = new StringBuilder("SELECT count(p.processId)");
            selectHQL.append(" FROM Process p WHERE p.isActive = ? and p.objectId = ? and p.objectType = ? ");
            selectHQL.append(" and p.sendGroupId = ? and p.status = 0 and p.isRead = ? and p.processType != -1 ");
            Query query = getSession().createQuery(selectHQL.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, objectId);
            query.setParameter(2, objectType);
            query.setParameter(3, deptId);
            query.setParameter(4, 1L);

            Long result = (Long) query.uniqueResult();
            return result;
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return 0L;
    }

    //140407 process chuyen vien xu ly ho so
    public Process findProcessByActionEvaluate(Long filesID) {
        List<Process> lstCategory;
        Process item = null;
        try {
            String stringBuilder = "SELECT p FROM Process p WHERE p.objectId = ? and (p.status = ? or p.status = ? ) and p.processStatus = ?";//141217u binhnt process chuyen vien tham dinh ho so
            //String stringBuilder = "SELECT p FROM Process p WHERE p.objectId = ? and (p.status = ? or p.status = ? )";//141217u binhnt process chuyen vien tham dinh ho so
            Query query = getSession().createQuery(stringBuilder);
            query.setParameter(0, filesID);
            query.setParameter(1, Constants.FILE_STATUS.EVALUATED);
            query.setParameter(2, Constants.FILE_STATUS.FEDBACK_TO_ADD);
            query.setParameter(3, Constants.FILE_STATUS.ASSIGNED);
            lstCategory = query.list();
            if (lstCategory != null && lstCategory.size() > 0) {
                item = lstCategory.get(0);
            }

        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return item;
    }

    public Process findProcessByAction(Long filesID, List<Long> action) {

        List<Process> lstCategory;
        Process item = null;
        try {
            String stringBuilder = "SELECT p FROM Process p WHERE p.objectId = ? and p.processStatus in (:lstProcessStatus)";
            Query query = getSession().createQuery(stringBuilder);
            query.setParameter(0, filesID);
            if (action.isEmpty()) {
                action.add(-1L);
            }
            query.setParameterList("lstProcessStatus", action);
            lstCategory = query.list();
            if (lstCategory != null && lstCategory.size() > 0) {
                item = lstCategory.get(0);
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return item;
    }

    public Process getStaffEvaluate(Long documentId) {
        List<Process> lst;
        Process item = null;
        try {
            String selectHQL = "SELECT p";
            String hql = " FROM Process p WHERE p.isActive = 1 and p.objectId =" + documentId;
            hql += " and processStatus=3 and processType=1 ORDER BY p.processId, p.receiveGroupId";
            Query query = getSession().createQuery(selectHQL + hql);
            lst = query.list();
            if (lst != null && lst.size() > 0) {
                item = lst.get(0);
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return item;
    }

    public Process getProcessByAction(Long filesID, Long isActive, Long objectType, Long processStatus, Long status) {
        List<Process> lstCategory;
        Process item = null;
        try {
            String stringBuilder = "SELECT p FROM Process p WHERE p.objectId = ? and p.isActive = ? and  p.objectType = ?";
            if (processStatus != null) {
                stringBuilder += " and p.processStatus = ?";
            }
            if (status != null) {
                stringBuilder += " and p.status = ?";
            }
            Query query = getSession().createQuery(stringBuilder);
            query.setParameter(0, filesID);
            query.setParameter(1, isActive);
            query.setParameter(2, objectType);
            if (processStatus != null) {
                query.setParameter(3, processStatus);
            }
            if (status != null) {
                query.setParameter(4, status);
            }
            lstCategory = query.list();
            if (lstCategory != null && lstCategory.size() > 0) {
                item = lstCategory.get(0);
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return item;
    }

    public void saveDbNotCommit(Process vo) throws Exception {
        getSession().save(vo);
    }

    public GridResult getStaffProcessFiles(Date from, Date to, Long deptId, int start, int count, String sortField) {

        String selectQuery = "select p.receive_user_id,p.receive_user,p.process_status, count(p.status) ";
        String conditionQuery = "from process  p where p.receive_user_id in (select user_id from users where dept_id is not null) ";
        String groupByQuery = "";
        List lstParam = new ArrayList();
        //lstParam.add(deptId);
        if (from != null) {
            conditionQuery += "and p.receive_date >= to_date( ?,'dd/MM/yyyy hh24:mi:ss') ";
            String strFrom = DateTimeUtils.convertDateToString(from, "dd/MM/yyyy") + " 23:59:59";
            lstParam.add(strFrom);
        }
        if (to != null) {
            conditionQuery += "and p.receive_date <= to_date( ?,'dd/MM/yyyy hh24:mi:ss') ";
            String strTo = DateTimeUtils.convertDateToString(to, "dd/MM/yyyy") + " 23:59:59";
            lstParam.add(strTo);
        }
        groupByQuery += "group by p.receive_user_id,p.receive_user,p.process_status "
                + "order by  p.process_status";

        SQLQuery querySql = (SQLQuery) getSession().createSQLQuery(selectQuery + conditionQuery + groupByQuery);
        for (int i = 0; i < lstParam.size(); i++) {
            querySql.setParameter(i, lstParam.get(i));
        }
        List lst = querySql.list();

        ProcessReportForm item;
        HashMap<Long, ProcessReportForm> result = new HashMap<>();
        for (int i = 0; i < lst.size(); i++) {//for all in            
            Object[] row = (Object[]) lst.get(i);
            if (row.length > 0) {
                item = new ProcessReportForm();
                if (row[0] != null && !"".equals(row[0])) {
                    item.setReceiveUserId(Long.parseLong(row[0].toString()));
                    if (result != null && result.containsKey(item.getReceiveUserId()) == true) {
                        item = result.get(item.getReceiveUserId());
                        if (row[1] != null && !"".equals(row[1])) {
                            item.setReceiveUser(row[1].toString());
                        }
                        if (row[2] != null && !"".equals(row[2])) {
                            Long processStatus = Long.parseLong(row[2].toString());
                            if (processStatus != null && (processStatus.equals(Constants.FILE_STATUS.ASSIGNED) || processStatus.equals(Constants.FILE_STATUS.RECEIVED_TO_ADD) || processStatus.equals(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE))) {
                                if (row[2] != null && !"".equals(row[2])) {
                                    if (item.getStatusOne() != null) {
                                        item.setStatusOne(Long.parseLong(row[2].toString()) + item.getStatusOne());
                                    } else {
                                        item.setStatusOne(Long.parseLong(row[2].toString()));
                                    }
                                }
                            } else if (processStatus != null && (processStatus.equals(Constants.FILE_STATUS.EVALUATED_TO_ADD))) {
                                if (item.getStatusTwo() != null) {
                                    item.setStatusTwo(Long.parseLong(row[2].toString()) + item.getStatusTwo());
                                } else {
                                    item.setStatusTwo(Long.parseLong(row[2].toString()));
                                }
                            } else if (processStatus != null) {
                                if (item.getStatusThree() != null) {
                                    item.setStatusThree(Long.parseLong(row[2].toString()) + item.getStatusThree());
                                } else {
                                    item.setStatusThree(Long.parseLong(row[2].toString()));
                                }
                            }
                        }
                        result.put(item.getReceiveUserId(), item);
                    } else {
                        if (row[1] != null && !"".equals(row[1])) {
                            item.setReceiveUser(row[1].toString());
                        }
                        if (row[2] != null && !"".equals(row[2])) {
                            Long status = Long.parseLong(row[2].toString());
                            if (status != null && (status.equals(Constants.FILE_STATUS.ASSIGNED) || status.equals(Constants.FILE_STATUS.RECEIVED_TO_ADD) || status.equals(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE))) {
                                if (row[2] != null && !"".equals(row[2])) {
                                    item.setStatusOne(Long.parseLong(row[2].toString()));
                                }
                            } else if (status != null && (status.equals(Constants.FILE_STATUS.EVALUATED_TO_ADD))) {
                                item.setStatusTwo(Long.parseLong(row[2].toString()));
                            } else if (status != null) {
                                item.setStatusThree(Long.parseLong(row[2].toString()));
                            }
                        }
                        result.put(item.getReceiveUserId(), item);
                    }
                }
            }
        }
        GridResult gr = new GridResult(result.size(), new ArrayList<ProcessReportForm>(result.values()));
        return gr;
    }
}
