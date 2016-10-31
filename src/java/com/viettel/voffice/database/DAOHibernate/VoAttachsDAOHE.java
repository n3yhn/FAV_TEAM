/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAOHibernate;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.common.util.StringUtils;
import com.viettel.voffice.client.form.VoAttachForm;
import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.vsaadmin.database.BO.Department;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.DepartmentDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author HanPT1
 */
public class VoAttachsDAOHE extends GenericDAOHibernate<VoAttachs, Long> {

    //private static Logger logger = Logger.getLogger(VoAttachsDAOHE.class);
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(VoAttachsDAOHE.class);

    public VoAttachsDAOHE() {
        super(VoAttachs.class);
    }

    public void boToForm(VoAttachs bo, VoAttachForm form) {
        UsersDAOHE userDAOHE = new UsersDAOHE();
        DepartmentDAOHE deptDAOHE = new DepartmentDAOHE();
        if (bo != null) {
            form.setAttachId(bo.getAttachId());
            form.setObjectId(bo.getObjectId());
            form.setObjectType(bo.getObjectType());
            form.setAttachName(bo.getAttachName());
            form.setAttachPath(bo.getAttachPath());
            form.setAttachDes(StringUtils.escapeHtml(bo.getAttachDes()));
            if (bo.getUserCreateId() != null) {
                Users user = userDAOHE.findById(bo.getUserCreateId(), false);
                Department dept = deptDAOHE.getDeptByUserId(bo.getUserCreateId());
                form.setUserCreateId(bo.getUserCreateId());
                form.setUserName(user.getFullName());
                form.setDeptId(dept.getDeptId());
                form.setDeptName(dept.getDeptName());
            }
            form.setCreateDate(bo.getCreateDate());
            //form.setType(bo.getType());
        }
    }

    public void formToBo(VoAttachForm form, VoAttachs bo) {
        try {
            if (form != null) {
                bo.setObjectId(form.getObjectId());
                // bo.setObjectType(form.getObjectType());
                bo.setAttachName(form.getAttachName());
                bo.setAttachPath(form.getAttachPath());
                bo.setAttachDes(form.getAttachDes());
                // bo.setUserCreateId(form.getUserCreateId());
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
    }

    public void updateAttacths(String ids, Long objectId, Long objectType) {
        try {
            String[] idArr;
            if (ids != null && !"".equals(ids)) {
                idArr = ids.split(";");
                for (String idAttStr : idArr) {
                    Long idAtt = Long.parseLong(idAttStr);
                    VoAttachs boAttach = this.findById(idAtt, false);
                    boAttach.setObjectId(objectId);
                    boAttach.setObjectType(objectType);
                    boAttach.setCreateDate(getSysdate());
                    this.update(boAttach);
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
    }

    //DiuLTT
    public void updateAttacths(String ids, Long objectId, Long objectType, Long userId, boolean updateVersion) {
        try {
            String[] idArr;

            if (ids != null && !"".equals(ids)) {
                idArr = ids.split(";");
                for (String idAttStr : idArr) {
                    Long idAtt = Long.parseLong(idAttStr);
                    VoAttachs boAttach = this.findById(idAtt, false);
                    boAttach.setObjectId(objectId);
                    boAttach.setObjectType(objectType);
                    boAttach.setUserCreateId(userId);
                    boAttach.setCreateDate(getSysdate());

                    this.update(boAttach);
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
    }

    public GridResult getAttachsByObjectId(Long objectId, int start, int count, String sortField) {

        List voAttachsList = new ArrayList();
        GridResult gridResult = new GridResult();

        try {
            StringBuilder strBuf = new StringBuilder("from VoAttachs a where a.isActive = ?");
            strBuf.append(" and a.objectId = ?");

            Query query = getSession().createQuery("SELECT count(a) " + strBuf.toString());
            query.setParameter(0, Constants.Status.ACTIVE);

            if (objectId != null) {
                query.setParameter(1, objectId);
            } else {
                query.setParameter(1, -1L);
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
                strBuf.append(" order by (CASE WHEN a.createDate IS NULL THEN 1 ELSE 0 END), a.createDate ").append(sortType).append(", ").append(validateColumnName(sortField)).append(" ").append(sortType);
                //strBuf.append(" order by ").append(validateColumnName(sortField)).append(" ").append(sortType);
            } else {
                strBuf.append(" order by (CASE WHEN a.createDate IS NULL THEN 1 ELSE 0 END), a.createDate desc, a.attachId desc ");
            }

            query = getSession().createQuery("SELECT a " + strBuf.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            if (objectId != null) {
                query.setParameter(1, objectId);
            } else {
                query.setParameter(1, -1L);
            }

            query.setFirstResult(start);
            query.setMaxResults(count);

            voAttachsList = query.list();
            gridResult.setLstResult(voAttachsList);
            gridResult.setnCount(Long.valueOf(total));
            return gridResult;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    public List<VoAttachs> getAttachsByObject(Long objectId, Long objectType) {
        List<VoAttachs> voAttachsList = new ArrayList<VoAttachs>();
        try {
            StringBuilder strBuf = new StringBuilder("from VoAttachs a where a.isActive = ?");
            strBuf.append(" and a.objectId = ?");
            strBuf.append(" and a.objectType = ?");
            Query query = getSession().createQuery("SELECT a " + strBuf.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            if (objectId != null) {
                query.setParameter(1, objectId);
            } else {
                query.setParameter(1, -1L);
            }

            if (objectType != null) {
                query.setParameter(2, objectType);
            } else {
                query.setParameter(2, -1L);
            }
            voAttachsList = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return voAttachsList;
    }

    public List<VoAttachs> getRelationAttachs(Long objectId, Long objectType) {
        List<VoAttachs> voAttachsList = new ArrayList<VoAttachs>();
        try {
            StringBuilder strBuf = new StringBuilder("from VoAttachs a where a.isActive = ?");
            strBuf.append(" and a.objectId = ?");
            strBuf.append(" and a.objectType = ?");
            strBuf.append(" and a.type is null ");
            Query query = getSession().createQuery("SELECT a " + strBuf.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            if (objectId != null) {
                query.setParameter(1, objectId);
            } else {
                query.setParameter(1, -1L);
            }

            if (objectType != null) {
                query.setParameter(2, objectType);
            } else {
                query.setParameter(2, -1L);
            }
            voAttachsList = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return voAttachsList;
    }

    public GridResult searchRelationDoc(Long objectId, Long objectType, int start, int count, String sortField) {

        List listParam = new ArrayList();
        List<VoAttachs> voFilesList = new ArrayList<VoAttachs>();
        GridResult gridResult = new GridResult();
        try {
            StringBuilder strBuf = new StringBuilder(" from VoAttachs a where  a.isActive = 1");
            strBuf.append(" and a.objectId= ? and  a.objectType = ? ");
            strBuf.append(" and a.type = 4 ");
            listParam.add(objectId);
            listParam.add(objectType);

            Query query = getSession().createQuery("SELECT count(a) " + strBuf.toString());
            for (int i = 0; i < listParam.size(); i++) {
                query.setParameter(i, listParam.get(i));
            }

            int total = Integer.parseInt(query.list().get(0).toString());
            /*
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
             strBuf.append(" order by ").append(sortField).append(" ").append(sortType);
             } else {
             strBuf.append(" order by a.attachId desc ");
             }
             */
            strBuf.append(" order by (CASE WHEN a.createDate IS NULL THEN 1 ELSE 0 END), a.createDate desc, a.attachId desc ");
            query = getSession().createQuery("SELECT a " + strBuf.toString());

            for (int i = 0; i < listParam.size(); i++) {
                query.setParameter(i, listParam.get(i));
            }

            query.setFirstResult(start);
            query.setMaxResults(count);

            voFilesList = query.list();
            List<VoAttachForm> list = new ArrayList<VoAttachForm>();
            for (VoAttachs bo : voFilesList) {
                VoAttachForm form = new VoAttachForm();
                boToForm(bo, form);
                list.add(form);
            }
            gridResult.setLstResult(list);
            gridResult.setnCount(Long.valueOf(total));
            return gridResult;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    // sytv add 
    public String getAttachLinksByObject(Long objectId, Long objectType) {
        List<VoAttachs> voAttachsList = new ArrayList<VoAttachs>();
        String result = "";
        try {
            StringBuilder strBuf = new StringBuilder("from VoAttachs a where a.isActive = ?");
            strBuf.append(" and a.objectId = ?");
            strBuf.append(" and a.objectType = ?");
            Query query = getSession().createQuery("SELECT a " + strBuf.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            if (objectId != null) {
                query.setParameter(1, objectId);
            } else {
                query.setParameter(1, -1L);
            }

            if (objectType != null) {
                query.setParameter(2, objectType);
            } else {
                query.setParameter(2, -1L);
            }
            voAttachsList = query.list();
            for (VoAttachs bo : voAttachsList) {
                result += "<a href='uploadiframe!openFile.do?attachId=" + bo.getAttachId().toString() + "' >" + StringUtils.escapeHtml(bo.getAttachName()) + "</a><br/>";
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return result;
    }

    // DiuLTT add 
    /*
     * Tk Old Attach, isActive==INACTIVE
     */
    public List<VoAttachs> getOldAttachs(Long objectId, Long versions, Long objectType) {
        List<VoAttachs> voAttachsList = new ArrayList<VoAttachs>();
        try {
            StringBuilder strBuf = new StringBuilder("from VoAttachs a where a.isActive = ?");
            strBuf.append(" and a.objectId = ?");
            strBuf.append(" and a.objectType = ?");
            if (versions == null) {
                strBuf.append(" and a.versions is null");
            } else {
                strBuf.append(" and a.versions is not null");
            }

            Query query = getSession().createQuery("SELECT a " + strBuf.toString());
            query.setParameter(0, Constants.Status.INACTIVE);
            if (objectId != null) {
                query.setParameter(1, objectId);
            } else {
                query.setParameter(1, -1L);
            }
            if (objectType != null) {
                query.setParameter(2, objectType);
            } else {
                query.setParameter(2, -1L);
            }

            voAttachsList = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return voAttachsList;
    }

    // List Attachs of Version
    public String getAttachIDs(Long objectId, Long objectType) {
        List<VoAttachs> voAttachsList = new ArrayList<VoAttachs>();
        List lstParam = new ArrayList();
        String result = "";
        try {
            StringBuilder strBuf = new StringBuilder("FROM VoAttachs va WHERE  va.objectId = ?");
            lstParam.add(objectId);
            strBuf.append(" AND va.objectType = ?");
            lstParam.add(objectType);
            strBuf.append(" AND va.isActive = ?");
            lstParam.add(Constants.Status.ACTIVE);

            Query query = getSession().createQuery("SELECT va " + strBuf.toString());
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }
            voAttachsList = query.list();
            if (voAttachsList != null && voAttachsList.size() > 0) {
                for (VoAttachs bo : voAttachsList) {
                    result += bo.getAttachId() + ";";
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (!"".equals(result)) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    public String getAttachNames(Long objectId, Long objectType) {
        List<VoAttachs> voAttachsList = new ArrayList<VoAttachs>();
        List lstParam = new ArrayList();
        String result = "";
        try {
            StringBuilder strBuf = new StringBuilder("FROM VoAttachs va WHERE  va.objectId = ?");
            lstParam.add(objectId);
            strBuf.append(" AND va.objectType = ?");
            lstParam.add(objectType);
            strBuf.append(" AND va.isActive = ?");
            lstParam.add(Constants.Status.ACTIVE);

            Query query = getSession().createQuery("SELECT va " + strBuf.toString());
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }
            voAttachsList = query.list();
            if (voAttachsList != null && voAttachsList.size() > 0) {
                for (VoAttachs bo : voAttachsList) {
                    result += bo.getAttachName() + ";";
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (!"".equals(result)) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    public String getAttachLinksById(String attachIds) {
        if ("".equals(attachIds)) {
            return "";
        }
        String result = "";
        String[] attachIdList = attachIds.split(";");
        for (String attach : attachIdList) {
            Long id = Long.valueOf(attach);
            StringBuilder strBuf = new StringBuilder(" FROM VoAttachs va WHERE  va.attachId = ?");
            Query query = getSession().createQuery("SELECT va " + strBuf.toString());
            query.setParameter(0, id);
            VoAttachs bo = (VoAttachs) query.list().get(0);
            if (bo != null) {
                result += "<a href='uploadiframe!openFile.do?attachId=" + bo.getAttachId().toString() + "' >" + StringUtils.escapeHtml(bo.getAttachName()) + "</a><br/>";
            }
        }
        return result;
    }

    public String getAttachLinksByIdForPortal(String attachIds) {
        if ("".equals(attachIds)) {
            return "";
        }
        String result = "";
        String[] attachIdList = attachIds.split(";");
        for (String attach : attachIdList) {
            Long id = Long.valueOf(attach);
            StringBuilder strBuf = new StringBuilder(" FROM VoAttachs va WHERE  va.attachId = ?");
            Query query = getSession().createQuery("SELECT va " + strBuf.toString());
            query.setParameter(0, id);
            VoAttachs bo = (VoAttachs) query.list().get(0);
            if (bo != null) {
                result += "<a href='uploadiframe!openFileForPortal.do?attachId=" + bo.getAttachId().toString() + "' >" + StringUtils.escapeHtml(bo.getAttachName()) + "</a><br/>";
            }
        }
        return result;
    }

    public String getAttachLinksIconByIdForPortal(String attachIds) {
        if ("".equals(attachIds)) {
            return "";
        }
        String result = "";
        String[] attachIdList = attachIds.split(";");
        for (String attach : attachIdList) {
            Long id = Long.valueOf(attach);
            StringBuilder strBuf = new StringBuilder(" FROM VoAttachs va WHERE  va.attachId = ?");
            Query query = getSession().createQuery("SELECT va " + strBuf.toString());
            query.setParameter(0, id);
            VoAttachs bo = (VoAttachs) query.list().get(0);
            if (bo != null) {
                result += "<a href='uploadiframe!openFileForPortal.do?attachId=" + bo.getAttachId().toString() + "' ><img src='share/images/copy.gif' title='" + StringUtils.escapeHtml(bo.getAttachName()) + "'/></a>";
            }
        }
        return result;
    }

    public VoAttachs getAttach(Long objectId, Long objectType) {
        VoAttachs bo = new VoAttachs();
        try {
            StringBuilder strBuf = new StringBuilder(" FROM VoAttachs va WHERE ");
            strBuf.append(" va.objectId = ? AND va.objectType = ?");
            Query query = getSession().createQuery("SELECT va " + strBuf.toString());
            query.setParameter(0, objectId);
            query.setParameter(1, objectType);
            List lst = query.list();
            if (lst != null && lst.size() > 0) {
                return (VoAttachs) lst.get(0);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return bo;
    }

    public VoAttachs getAttById(Long attachId) {
        VoAttachs voAttach = null;
        try {
            StringBuilder strBuf = new StringBuilder(" SELECT va FROM VoAttachs va WHERE va.attachId = ?");
            Query query = getSession().createQuery(strBuf.toString());
            query.setParameter(0, attachId);
            List lst = query.list();
            if (lst != null && lst.size() > 0) {
                return (VoAttachs) lst.get(0);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return voAttach;
    }

    public void saveDb(VoAttachs vo) throws Exception {
        getSession().save(vo);
        getSession().getTransaction().commit();
    }

    public void saveDbNotCommit(VoAttachs vo) throws Exception {
        getSession().save(vo);
    }

    public void commitDb() throws Exception {
        getSession().getTransaction().commit();
    }

    public void updateDbNotCommit(VoAttachs vo) throws Exception {
        getSession().update(vo);
    }

    public VoAttachs getLstVoAttachByFilesId(Long filesId, String signType) {//n140729 - get lst cac phien ban cua ho so
        List<VoAttachs> lstFiles = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(" from VoAttachs a ");
            stringBuilder.append(" where a.objectId = ? and a.objectType = ? and a.isActive = 1"
                    + " order by (CASE WHEN a.createDate IS NULL THEN 1 ELSE 0 END), a.createDate DESC, a.attachId DESC");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, filesId);
            if ("PDHS".equals(signType)) {
                query.setParameter(1, 40L);
            } else if ("PDHS_PUBLIC".equals(signType)) {
                query.setParameter(1, 41L);
            } else if ("CVBS".equals(signType)) {
                query.setParameter(1, 71L);
            }
            lstFiles = query.list();
            //Hiepvv Xuat Cong bo cong van cua ho so. Uu tien Cong van neu la ho so goc
            if (lstFiles != null && lstFiles.size() > 0) {
                if (lstFiles.size() == 1) {
                    return lstFiles.get(0);
                } else {
                    VoAttachs voAtt;//sonar
                    boolean check = false;
                    for (int i = 0; i < lstFiles.size(); i++) {
                        voAtt = lstFiles.get(i);
                        if (voAtt.getAttachName().startsWith("Bancongbo_VT")) {
//                            check = true;//sonar
                            return lstFiles.get(i);
                        }
                    }
                    if (check == false) {
                        return lstFiles.get(0);
                    }
                }
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
        return null;
    }

    public List<VoAttachs> getLstVoAttachSignByFilesId(Long filesId, String signType) {//n140729 - get lst cac phien ban cua ho so
        try {
            StringBuilder stringBuilder = new StringBuilder(" from VoAttachs a ");
            stringBuilder.append(" where a.isActive = 1 and a.objectId = ? and (a.objectType = ? or a.objectType = ?) "
                    + " order by (CASE WHEN a.createDate IS NULL THEN 1 ELSE 0 END), a.createDate DESC, a.attachId DESC");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, filesId);
            if ("PDHS".equals(signType)) {
                query.setParameter(1, 40L);
                query.setParameter(2, 41L);
            } else if ("CVBS".equals(signType)) {
                query.setParameter(1, 71L);
                query.setParameter(2, 71L);
            }
            return query.list();
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    public VoAttachs getLstVoAttachUploadByFilesId(Long filesId) {//n140729 - get lst cac phien ban cua ho so
        List<VoAttachs> lstFiles = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(" from VoAttachs a ");
            stringBuilder.append(" where a.objectId = ? and a.objectType = 60 and a.isActive = 1 "
                    + " order by (CASE WHEN a.createDate IS NULL THEN 1 ELSE 0 END), a.createDate DESC, a.attachId DESC");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, filesId);
            lstFiles = query.list();
            if (lstFiles != null && lstFiles.size() > 0) {
                return lstFiles.get(0);
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
        return null;
    }

    public GridResult getAttachsByIdType(Long objectId, Long objectType, int start, int count, String sortField) {
        List voAttachsList = new ArrayList();
        GridResult gridResult = new GridResult();
        try {
            StringBuilder strBuf = new StringBuilder("from VoAttachs a where a.isActive = ? and lower(a.attachName) like ? ESCAPE '/'");
            strBuf.append(" and a.objectId = ?");
            strBuf.append(" and a.objectType = ?");
            Query query = getSession().createQuery("SELECT count(a) " + strBuf.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, "CongvanSdbs_VT");
            if (objectId != null) {
                query.setParameter(2, objectId);
            } else {
                query.setParameter(2, -1L);
            }
            if (objectType != null) {
                query.setParameter(3, objectType);
            } else {
                query.setParameter(3, -1L);
            }
            int total = query.list().size();
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
                strBuf.append(" order by (CASE WHEN a.createDate IS NULL THEN 1 ELSE 0 END), a.createDate ").append(sortType).append(", ").append(validateColumnName(sortField)).append(" ").append(sortType);
            } else {
                strBuf.append(" order by (CASE WHEN a.createDate IS NULL THEN 1 ELSE 0 END), a.createDate desc, a.attachId desc ");
            }
            query = getSession().createQuery("SELECT a " + strBuf.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, StringUtils.toLikeString("CongvanSdbs_VT".toLowerCase().trim()));
            if (objectId != null) {
                query.setParameter(2, objectId);
            } else {
                query.setParameter(2, -1L);
            }
            if (objectType != null) {
                query.setParameter(3, objectType);
            } else {
                query.setParameter(3, -1L);
            }
            query.setFirstResult(start);
            query.setMaxResults(count);
            voAttachsList = query.list();
            gridResult.setLstResult(voAttachsList);
            gridResult.setnCount(Long.valueOf(total));
            return gridResult;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    //Hiepvv Get List VO_ATTACHS BY Object_ID: List file đính kèm của một hồ sơ SĐBS
    public List<VoAttachs> getLstVoAttachByObjectId(Long filesId) {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from VoAttachs a ");
            stringBuilder.append(" where a.isActive = 1 and a.objectId = ? "
                    + " order by (CASE WHEN a.createDate IS NULL THEN 1 ELSE 0 END), a.createDate DESC, a.attachId DESC");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, filesId);
            return query.list();
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    public VoAttachs getLstVoAttachByFilesIdForAA(Long filesId, String signType) {//n140729 - get lst cac phien ban cua ho so
        List<VoAttachs> lstFiles = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(" from VoAttachs a ");
            stringBuilder.append(" where a.objectId = ? and a.objectType = ? and a.isActive = 1"
                    + " order by (CASE WHEN a.createDate IS NULL THEN 1 ELSE 0 END), a.createDate ASC, a.attachId ASC");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, filesId);
            if ("PDHS".equals(signType)) {
                query.setParameter(1, 40L);
            } else if ("PDHS_PUBLIC".equals(signType)) {
                query.setParameter(1, 41L);
            } else if ("CVBS".equals(signType)) {
                query.setParameter(1, 71L);
            }
            lstFiles = query.list();
            //Hiepvv Xuat Cong bo cong van cua ho so. Uu tien Cong van neu la ho so goc
            if (lstFiles != null && lstFiles.size() > 0) {
                if (lstFiles.size() == 1) {
                    return lstFiles.get(0);
                } else {
                    VoAttachs voAtt;
                    boolean check = false;
                    for (int i = 0; i < lstFiles.size(); i++) {
                        voAtt = lstFiles.get(i);
                        if (voAtt.getAttachName().startsWith("Bancongbo_VT")) {
//                            check = true;
                            return lstFiles.get(i);
                        }
                    }
                    if (check == false) {
                        return lstFiles.get(0);
                    }
                }
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
        return null;
    }

    public GridResult getAttachsByIdTypeChanged(Long objectId, Long objectType, int start, int count, String sortField) {
        List voAttachsList = new ArrayList();
        GridResult gridResult = new GridResult();
        try {
            StringBuilder strBuf = new StringBuilder("from VoAttachs a where a.isActive = ? ");
            strBuf.append(" and a.objectId = ?");
//            strBuf.append(" and a.objectType = ?");
            Query query = getSession().createQuery("SELECT count(a) " + strBuf.toString());
            query.setParameter(0, Constants.Status.CHANGED);
            if (objectId != null) {
                query.setParameter(1, objectId);
            } else {
                query.setParameter(1, -1L);
            }
            int total = query.list().size();
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
                strBuf.append(" order by (CASE WHEN a.createDate IS NULL THEN 1 ELSE 0 END), a.createDate ").append(sortType).append(", ").append(validateColumnName(sortField)).append(" ").append(sortType);
            } else {
                strBuf.append(" order by (CASE WHEN a.createDate IS NULL THEN 1 ELSE 0 END), a.createDate desc, a.attachId desc ");
            }
            query = getSession().createQuery("SELECT a " + strBuf.toString());
            query.setParameter(0, Constants.Status.CHANGED);
            if (objectId != null) {
                query.setParameter(1, objectId);
            } else {
                query.setParameter(1, -1L);
            }
            query.setFirstResult(start);
            query.setMaxResults(count);
            voAttachsList = query.list();
            gridResult.setLstResult(voAttachsList);
            gridResult.setnCount(Long.valueOf(total));
            return gridResult;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }
}
