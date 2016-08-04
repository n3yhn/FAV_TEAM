/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.vsaadmin.database.DAOHibernate;

import com.viettel.common.util.Constants;
import com.viettel.common.util.StringUtils;
import com.viettel.voffice.client.form.PositionForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import com.viettel.vsaadmin.client.form.DepartmentForm;
import com.viettel.vsaadmin.database.BO.Department;
import com.viettel.vsaadmin.database.BO.Position;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * DepartmentDAOHibernate
 * @author trungnq7@viettel.com.vn
 * @since Apr 6, 2011
 * @version 1.0
 */
public class PositionDAOHE extends GenericDAOHibernate<Position, Long> {
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PositionDAOHE.class);

    /** .*/
    private Position headerPosition;

    public PositionDAOHE() {
        super(Position.class);
    }

    /**
     *
     * @return
     */
    public Position getHeaderPositionSelect() {
        headerPosition = new Position();
        headerPosition.setPosId(Constants.COMBOBOX_HEADER_VALUE);
        headerPosition.setPosName(Constants.COMBOBOX_HEADER_TEXT_SELECT);
        return headerPosition;
    }

    /**
     *
     * @return
     */
    public Position getHeaderPositionSearch() {
        headerPosition = new Position();
        headerPosition.setPosId(Constants.COMBOBOX_HEADER_VALUE);
        headerPosition.setPosName(Constants.COMBOBOX_HEADER_TEXT);
        return headerPosition;
    }

    /**
     * Tao doi tuong
     * @param form
     * @return
     * @throws Exception
     */
//    public Position createBO(ParameterForm form) throws Exception {
//
//        Position temp = new Position();
//        try {
//            if (form.getDescription() == null) {
//                temp.setDescription("");
//            } else {
//                if (form.getDescription().trim() == null) {
//                    temp.setDescription("");
//                } else {
//                    temp.setDescription(form.getDescription().trim());
//                }
//            }
//            temp.setCode(form.getCode().trim());
//            temp.setName(form.getName().trim());
//            temp.setPosId(form.getPosId());
//            temp.setStatus(1L);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new Exception("Loi khi thuc hien CreateBO");
//        }
//        return temp;
//
//    }
    /**
     * find all 
     * @param firstResult firstResult
     * @param maxResult maxResult
     * @return list<Users>
     */
    @Override
    public List<Position> findAll() {
        return this.findByCriteria(0, -1);
    }

    /**
     * find all active
     * @param firstResult firstResult
     * @param maxResult maxResult
     * @return list<Users>
     */
    public List<Position> findAllActive() {
        String hql = "select p from Position p where p.status = 1 and p.posType = 1 order by nlssort(lower(p.posName),'nls_sort = Vietnamese')";
        Query query = getSession().createQuery(hql);

//        Criterion criterion = Restrictions.eq("status", 1L);

        return query.list();
    }

    /**
     * tim kiem
     * @param firstResult firstResult
     * @param maxResult maxResult
     * @return list<Users>
     */
    public List<Department> search(DepartmentForm departmentForm) {
        List listParam = new ArrayList();
        List result = new ArrayList();
        try {
            String hql = " from Department a where 1=1 ";
            if ((departmentForm.getAddress() != null)
                    && (!"".equals(departmentForm.getAddress()))) {
                hql = hql + " and lower(address) like ?";
                listParam.add("%" + departmentForm.getAddress().toLowerCase() + "%");
            }
            if ((departmentForm.getDeptCode() != null)
                    && (!"".equals(departmentForm.getDeptCode()))) {
                hql = hql + " and lower(code) like ?";
                listParam.add("%" + departmentForm.getDeptCode().toLowerCase() + "%");
            }

            if ((departmentForm.getDeptName() != null)
                    && (!"".equals(departmentForm.getDeptName()))) {
                hql = hql + " and lower(deptName) like ?";
                listParam.add("%" + departmentForm.getDeptName().toLowerCase() + "%");
            }

            if ((departmentForm.getStatus() != null)
                    && (!"".equals(departmentForm.getStatus())) && (!"ALL".equals(departmentForm.getStatus()))) {
                hql = hql + " and status = ?";
                listParam.add(new Long(departmentForm.getStatus()));
            }

            if ((departmentForm.getTelephone() != null)
                    && (!"".equals(departmentForm.getTelephone()))) {
                hql = hql + " and lower(telephone) like ?";
                listParam.add("%" + departmentForm.getTelephone().toLowerCase() + "%");
            }

            if ((departmentForm.getDeptTypeId() != null)
                    && (!"".equals(departmentForm.getDeptTypeId()))) {
                Long type = new Long(departmentForm.getDeptTypeId());
                if (type.longValue() != 0L) {
                    hql = hql + " and deptTypeId = ?";
                    listParam.add(type);
                }

            }
            Query q = getSession().createQuery(hql);
            for (int i = 0; i < listParam.size(); i++) {
                q.setParameter(i, listParam.get(i));
            }
            result = q.list();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return result;
    }

    /**
     * tim kiem root cua cay
     * @return
     */
    public List<Position> getParentDateForTree() {
        Criterion criterion = Restrictions.isNull("parentId");
        return this.findByCriteria(0, -1, criterion);
    }

    public Position findByName(String name) {
        // Build sql
        String sqlBuilder = "SELECT p FROM Position p WHERE p.status = 1 and p.posType = 1 and lower(p.posName) LIKE ? ESCAPE '/'";

        Query query = session.createQuery(sqlBuilder);
        query.setParameter(0, StringUtils.toLikeString(name));
        List<Position> lsObject = query.list();
        if (lsObject != null && lsObject.size() > 0) {
            return lsObject.get(0);
        }
        return null;
    }

    //DiuLTT add
    @Override
    public Position findById(Long positionId) {
        Query query = getSession().createQuery("Select a from Position a where a.status = ? and posId=?");
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, positionId);
        Position position = (Position) query.uniqueResult();
        return position;
    }

    // tim kiem ma chuc vu cua can bo theo ID - Chi ap dung can bo Cuc - DuND
    // SyTV 
    public Position findPositionCode(Long userID) {
        try {
        String sql = "SELECT p FROM Position p"
                + " WHERE p.status =1"
                + " AND p.posType =1"
                + " AND p.posId IN (SELECT u.posId FROM Users u"
                + " WHERE u.userId = ? AND u.status = 1)";
        Query query=session.createQuery(sql);
        query.setParameter(0, userID);
        List<Position> lst=query.list();
        if(lst!=null && lst.size() > 0) {
            return lst.get(0);
        }
        }catch(Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
     }

    //HanPT add
    public GridResult findPosition(PositionForm positionSearchForm, int start, int count, String sortField) {

        List listParam = new ArrayList();
        List voFilesList;
        GridResult gridResult = new GridResult();
        try {
            StringBuilder strBuf = new StringBuilder(" from Position a where a.posType =1 ");

            if (positionSearchForm != null) {

                if (positionSearchForm.getPosCode() != null && !"".equals(positionSearchForm.getPosCode().trim())) {
                    strBuf.append(" and lower(a.posCode) like ? ");
                    listParam.add("%" + convertToLikeString(positionSearchForm.getPosCode().trim()) + "%");
                }

                if (positionSearchForm.getPosName() != null && !"".equals(positionSearchForm.getPosName().trim())) {
                    strBuf.append(" and lower(a.posName) like ? ");
                    listParam.add("%" + convertToLikeString(positionSearchForm.getPosName().trim()) + "%");
                }
            }
            strBuf.append(" and a.status = 1");

            Query query = getSession().createQuery("SELECT count(a) " + strBuf.toString());
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
                strBuf.append(" order by nlssort(lower(a.posName),'nls_sort = Vietnamese') ");
            }

            query = getSession().createQuery("SELECT a " + strBuf.toString());

            for (int i = 0; i < listParam.size(); i++) {
                query.setParameter(i, listParam.get(i));
            }

            query.setFirstResult(start);
            query.setMaxResults(count);

            voFilesList = query.list();
            gridResult.setLstResult(voFilesList);
            gridResult.setnCount(Long.valueOf(total));
            return gridResult;
        } catch (Exception ex) {
            String msg = ex.getMessage();
            return null;
        }
    }

    public List<Position> getPosition(Long posId) {

        try {
            StringBuilder strBuf = new StringBuilder("select a from Position a where a.status = ? ");
            if (posId != null && posId != -1) {
                strBuf.append("and posId != ? ");
            }
            strBuf.append(" order by a.posId asc");
            Query query = getSession().createQuery(strBuf.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            if (posId != null && posId != -1) {
                query.setParameter(1, posId);
            }
            List<Position> positionList = query.list();
            return positionList;
        } catch (Exception ex) {
            String msg = ex.getMessage();
            return null;
        }
    }

    public void formToBO(PositionForm positionAddEditForm, Position bo) {

        if (positionAddEditForm != null) {

            if (positionAddEditForm.getPosId() != null && positionAddEditForm.getPosId() != -1) {
                bo.setPosId(positionAddEditForm.getPosId());
            }

            if (positionAddEditForm.getPosCode() != null && !"".equals(positionAddEditForm.getPosCode())) {
                bo.setPosCode(positionAddEditForm.getPosCode().trim());
            }

            if (positionAddEditForm.getPosName() != null && !"".equals(positionAddEditForm.getPosName())) {
                bo.setPosName(positionAddEditForm.getPosName().trim());
            }

            if (positionAddEditForm.getDescription() != null && !"".equals(positionAddEditForm.getDescription())) {
                bo.setDescription(positionAddEditForm.getDescription().trim());
            }
        }
    }
}
