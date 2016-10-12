/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAOHibernate;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.common.util.StringUtils;
import com.viettel.voffice.client.form.ProcessCommentForm;
import com.viettel.voffice.database.BO.ProcessComment;
import com.viettel.voffice.database.DAO.GridResult;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author DiuLTT
 */
public class ProcessCommentDAOHE extends GenericDAOHibernate<ProcessComment, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ProcessCommentDAOHE.class);

    public ProcessCommentDAOHE() {
        super(ProcessComment.class);
    }

    public ProcessComment getCommentOfUser(Long processId, Long userId, String content) {
        try {
            String hql = " SELECT p FROM ProcessComment p WHERE p.isActive = ? and p.processId = ?  and p.userId = ? "
                    + " and p.commentText like ? escape '!' ";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, processId);
            query.setParameter(2, userId);
            query.setParameter(3, content);
            List<ProcessComment> lst = query.list();
            if (lst != null && lst.size() > 0) {
                return lst.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    //DiuLTT
    public GridResult getCommentOfDoc(Long userId, Long documentId, Long objectType) {
        List<ProcessCommentForm> list = new ArrayList();
        try {
            String hql = " SELECT pc FROM ProcessComment pc WHERE pc.isActive = ? ";
            hql += " and (pc.objectId = ? or pc.processId in (Select p.processId from Process p where p.objectId = ? and p.objectType= ? )) ";
            hql += " ORDER BY pc.processCommentId ";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, documentId);
            query.setParameter(2, documentId);
            query.setParameter(3, objectType);
            List<ProcessComment> lst = query.list();
            for (ProcessComment bo : lst) {
                list.add(boToForm(bo, userId));
            }
            GridResult result = new GridResult(list.size(), list);
            return result;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new GridResult(0, new ArrayList());
        }

    }

    public GridResult getCommentOfDocument(Long userId, Long objectId, Long commentType, Long version, boolean isLastIsTemp, int start, int count, String sortField) {
        List<ProcessCommentForm> list = new ArrayList();
        List<ProcessComment> lst = new ArrayList();
        List lstParam = new ArrayList();
        try {
            String countHql = "SELECT count(pc) ";
            String selectHQL = "SELECT pc";
            String hql = " FROM ProcessComment pc, Process p"
                    + " WHERE (pc.isActive = 1)"
                    + " and (pc.processId = p.processId)"
                    + " and  p.objectId =?";
//                    + " and p.objectType=?";//binhnt53 150130
            lstParam.add(objectId);
//            lstParam.add(objectType);//binhnt53 150130
            if (commentType > -1L) {
                hql += " and pc.commentType=?";
                lstParam.add(commentType);
            } else {
                hql += " and pc.commentType <> 0";
            }
            if (version > 0L) {
                hql += " and pc.version =?";
                lstParam.add(version);
            }
            if (isLastIsTemp) {
                hql += " and pc.lastIsTemp =?";
                lstParam.add(1L);
            }
            hql += " ORDER BY pc.version DESC, pc.createdDate DESC group by pc.version";

            Query query = getSession().createQuery(selectHQL + hql);
            Query countQuery = getSession().createQuery(countHql + hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }
            query.setFirstResult(start);
            query.setMaxResults(count);
            Long nCount = (Long) countQuery.uniqueResult();
            lst = query.list();
            if (lst != null && lst.isEmpty() == false) {
                for (ProcessComment bo : lst) {
                    list.add(boToForm(bo, userId));
                }
            }
            GridResult result = new GridResult(nCount, list);
            return result;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new GridResult(0, null);
        }
    }

    public ProcessCommentForm boToForm(ProcessComment bo, Long userId) {
        ProcessCommentForm form = new ProcessCommentForm();
        form.setProcessCommentId(bo.getProcessCommentId());
        form.setProcessId(bo.getProcessId());
        form.setCommentText(StringUtils.escapeHtml(bo.getCommentText()));
        form.setCommentType(bo.getCommentType());
        form.setUserId(bo.getUserId());
        form.setUserName(bo.getUserName());
        form.setGroupId(bo.getGroupId());
        form.setGroupName(bo.getGroupName());
        form.setCreatedBy(bo.getCreatedBy());
        form.setCreatedDate(bo.getCreatedDate());
        form.setModifiedBy(bo.getModifiedBy());
        form.setModifiedDate(bo.getModifiedDate());
        form.setIsActive(bo.getIsActive());
        form.setStatus(bo.getStatus());
        VoAttachsDAOHE attachsDAOHE = new VoAttachsDAOHE();
        String links = attachsDAOHE.getAttachLinksByObject(bo.getProcessCommentId(), Constants.OBJECT_TYPE.FILES);
        form.setAttachIds(attachsDAOHE.getAttachIDs(bo.getProcessCommentId(), Constants.OBJECT_TYPE.FILES));
        form.setAttachFileLinks(links);
        if (bo.getCreatedBy() != null && bo.getCreatedBy().equals(userId)) {
            form.setEditable(1l);
        } else {
            form.setEditable(0l);
        }
        form.setVersion(bo.getVersion());
        return form;
    }

    public void insertComment(ProcessCommentForm form) {
        try {
            ProcessComment bo = new ProcessComment();

            bo.setLastIsTemp(Constants.Status.ACTIVE);

            bo.setCommentText(form.getCommentText());
            bo.setCommentType(form.getCommentType());
            bo.setObjectId(form.getObjectId());
            bo.setObjectType(form.getObjectType());
            bo.setUserId(form.getUserId());
            bo.setUserName(form.getUserName());
            bo.setGroupId(form.getGroupId());
            bo.setGroupName(form.getGroupName());

            bo.setCreatedBy(form.getCreatedBy());
            bo.setCreatedDate(this.getSysdate());
            bo.setProcessId(form.getProcessId());

            bo.setIsActive(Constants.Status.ACTIVE);
            bo.setStatus(Constants.DOCUMENT_STATUS.SEND); // da gui

            getSession().save(bo);

            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            daoHe.updateAttacths(form.getAttachIds(), bo.getProcessCommentId(), Constants.OBJECT_TYPE.FILES);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

    }

    public List<ProcessCommentForm> getLstCommentOfDocument(Long userId, Long objectId, Long objectType, int start, int nRecord, String sortField) {
        List<ProcessCommentForm> list = new ArrayList<ProcessCommentForm>();
        try {
//            String countHql = "SELECT count(pc) ";
            String selectHQL = "SELECT pc";
            String hql = " FROM ProcessComment pc, Process p WHERE (pc.isActive = 1) and (pc.processId = p.processId) and pc.lastIsTemp = 1"
                    + " and  p.objectId =" + objectId
                    + " and p.objectType=" + objectType;
            hql += " ORDER BY pc.createdDate DESC";

            Query query = getSession().createQuery(selectHQL + hql);
//            Query countQuery = getSession().createQuery(countHql + hql);
//            Long nCount = (Long) countQuery.uniqueResult();
            query.setFirstResult(start);
            query.setMaxResults(nRecord);
            List<ProcessComment> lst = query.list();
            for (ProcessComment bo : lst) {
                list.add(boToForm(bo, userId));
            }
            return list;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }
//140714

    public Long getLastVersionComment(Long processId) {
        try {
            String hql = " SELECT p FROM ProcessComment p WHERE p.isActive = ? and p.processId = ? ORDER BY p.version DESC";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, processId);
            List<ProcessComment> lst = query.list();
            if (lst != null && lst.size() > 0) {
                return lst.get(0).getVersion();
            } else {
                return 0L;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return 0L;
        }
    }

    public GridResult updateCommentOfDocument(Long userId, Long objectId, Long objectType, int start, int nRecord, String sortField) {
        List<ProcessCommentForm> list = new ArrayList<ProcessCommentForm>();
        try {
            String countHql = "SELECT count(pc) ";
            String selectHQL = "SELECT pc";
            String hql = " FROM ProcessComment pc, Process p WHERE (pc.isActive = 1) and (pc.processId = p.processId)"
                    + " and  p.objectId =" + objectId
                    + " and p.objectType=" + objectType;
            hql += " ORDER BY pc.createdDate DESC";

            Query query = getSession().createQuery(selectHQL + hql);
            Query countQuery = getSession().createQuery(countHql + hql);
            Long nCount = (Long) countQuery.uniqueResult();

//            query.setFirstResult(start);
//            query.setMaxResults(nRecord);
            List<ProcessComment> lst = query.list();
            for (ProcessComment bo : lst) {
                list.add(boToForm(bo, userId));
            }
            GridResult result = new GridResult(nCount, list);
            return result;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new GridResult(0, null);
        }
    }

    //update all to not last is temp
    public int updateSetNotLastIsTemp(Long objId) {//140728
        try {
            String hql = " update ProcessComment t set t.lastIsTemp = 0 where t.objectId = ?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, objId);
            return query.executeUpdate();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return 0;
        }
    }

    //update toan bo comment truoc thanh comment cua version cu
    public int updateVersion(Long objId, Long version) {//140728
        try {
            String hql = " update ProcessComment t set t.lastIsTemp = 0, t.version = ? where t.objectId = ? and t.lastIsTemp = 1";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, version);
            query.setParameter(1, objId);
            return query.executeUpdate();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return 0;
        }
    }
}
