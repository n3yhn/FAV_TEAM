/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.vsaadmin.database.DAOHibernate;

import com.viettel.common.util.Constants;
import com.viettel.common.util.DateTimeUtils;
import com.viettel.common.util.StringUtils;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import com.viettel.vsaadmin.client.form.UsersForm;
import com.viettel.vsaadmin.common.util.PasswordService;
import com.viettel.vsaadmin.database.BO.Department;
import com.viettel.vsaadmin.database.BO.Position;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.BO.VDepartment;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.proxy.HibernateProxy;

/**
 * DepartmentDAOHibernate
 *
 * @author trungnq7@viettel.com.vn
 * @since Apr 6, 2011
 * @version 1.0
 */
public class UsersDAOHE extends GenericDAOHibernate<Users, Long> {

    /**
     * log tool.
     */
    private static Logger log = Logger.getLogger(UsersDAOHE.class);
    /**
     * .
     */
    private final int maxResult = 10;
    /**
     * .
     */
    private final int batchSize = 1000;
    private List keyList = new ArrayList();
    private List valueList = new ArrayList();
    PositionDAOHE positionDaoHe = new PositionDAOHE();
    private static final Long POS_ID_LD = 1L;           // Lanh dao
    private static final Long POS_ID_LDP = 27L;         // truong phong
    private static final Long POS_ID_LDVP = 32L;        // lanh dao van phong
    private static HashMap<Long, List> lstFactory = new HashMap();

    public static void removeCache() {
        if (lstFactory == null) {
            return;
        }
        lstFactory.clear();
    }

    /**
     * check lan dang nhap dau tien
     *
     * @param fileId
     * @param password
     * @return
     */
    public boolean checkUserPass(String userName, String password) {
        boolean bReturn = false;
        try {
            String encryptedPass = PasswordService.getInstance().encrypt(userName.toLowerCase() + password);
            String hql = "select count(u) from Users u where u.status = 1 and u.userName = ? and password = ?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, userName);
            query.setParameter(1, encryptedPass);
            long ncount = (Long) query.uniqueResult();

            if (ncount == 1) {
                bReturn = true;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return bReturn;

    }

    public List getKeyList() {
        return keyList;
    }

    public void setKeyList(List keyList) {
        this.keyList = keyList;
    }

    public List getValueList() {
        return valueList;
    }

    public void setValueList(List valueList) {
        this.valueList = valueList;
    }

    public UsersDAOHE() {
        super(Users.class);
    }

    /**
     * lay danh sach user cua don vi
     *
     * @param deptId
     * @return
     */
    public List getListUserOfDept(Long deptId) throws Exception {
        Criterion[] criterion = new Criterion[2];
        criterion[0] = Restrictions.eq("deptId", deptId);
        criterion[1] = Restrictions.sqlRestriction(" 1 = 1 order by {alias}.USER_NAME ");
        return this.findByCriteria(0, -1, criterion);
    }

    /**
     * find all user
     *
     * @param firstResult firstResult
     * @param maxResult maxResult
     * @return list<Users>
     */
    public List<Users> findAll(int firstResult, int maxResult) {
        return this.findByCriteria(firstResult, maxResult);
    }

    /**
     * Get List active users by code
     *
     * @param identityCard identityCard
     * @return List<User>
     */
    public List<Users> getListUserByIdentityCard(String identityCard) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(" FROM Users u ");
        strBuilder.append(" WHERE u.identityCard =:identityCard ");
        Query query = getSession().createQuery(strBuilder.toString());
        query.setParameter("identityCard", identityCard);
        return query.list();
    }

    /**
     * lay roleid theo userid
     *
     * @param userId
     * @return
     */
    public List<Long> getListRoleIdsByUser(Long userId) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(" SELECT ru.roleId ");
        strBuilder.append(" FROM RoleUser ru ");
        strBuilder.append(" WHERE ru.isActive =:ACTIVE ");
        strBuilder.append(" AND ru.userId =:userId ");
        Query query = getSession().createQuery(strBuilder.toString());
        query.setParameter("ACTIVE", Constants.Status.ACTIVE);
        query.setParameter("userId", userId);
        return query.list();
    }

    /**
     * tim kiem username
     *
     * @param deptId
     * @return
     */
    public List<Users> findAllUserName() {
        try {
            String sql = " from Users v where v.status = ? order by nlssort(lower(v.fullName),'nls_sort = Vietnamese')";
            Query q = getSession().createQuery(sql);
            q.setParameter(0, Constants.Status.ACTIVE);
            List<Users> result = q.list();

            Users v = new Users();
            v.setUserId(Constants.COMBOBOX_HEADER_VALUE);
            v.setFullName(Constants.COMBOBOX_HEADER_TEXT_SELECT);
            List<Users> lst = new ArrayList();
            if (result != null && result.size() > 0) {
                for (int i = 0; i < result.size(); i++) {
                    String fullName = result.get(i).getFullName();
                    fullName = StringUtils.escapeHtml(fullName);
                    if (fullName.length() > 500) {
                        fullName = fullName.substring(0, 499);
                    }
                    result.get(i).setFullName(fullName);
                }
            }
            lst.add(v);
            lst.addAll(result);

            return lst;
        } catch (Exception ex) {
            String msg = ex.getMessage();
            return null;
        }
    }

    //DiuLTT add
    /**
     * tim kiem chuc vu
     *
     * @param userId
     * @return
     */
    public Position findPosition(Long userId) {
        Query query = getSession().createQuery("Select a from Users a where a.status = ? and userId=?");
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, userId);
        Users user = (Users) query.uniqueResult();
        if (user != null && user.getPosId() != null) {
            Position position = positionDaoHe.findById(user.getPosId());
            return position;
        }

        return null;
    }

    /**
     * tim kiem ten theo id
     *
     * @param userId
     * @return
     */
    public String findNameById(Long userId) {
        Query query = getSession().createQuery("Select a from Users a where userId=?");
        query.setParameter(0, userId);
        List<Users> lst = query.list();
        if (lst != null && !lst.isEmpty()) {
            Users user = lst.get(0);
            if (user != null) {
                return user.getFullName();
            }
        }

        return null;
    }

    /**
     * tim kiem user theo ten
     *
     * @param userName
     * @return
     */
    public Users findUserByName(String userName) {
        Query query = getSession().createQuery("Select a from Users a where lower(userName)=?");
        query.setParameter(0, userName.toLowerCase());
        List<Users> lst = query.list();
        if (lst != null && !lst.isEmpty()) {
            Users user = lst.get(0);
            if (user != null) {
                return user;
            }
        }
        return null;

    }

    /**
     * tim kiem username theo id
     *
     * @param userId
     * @return
     */
    public String getUserNameById(Long userId) {
        Query query = getSession().createQuery("Select a from Users a where userId=?");
        query.setParameter(0, userId);
        List<Users> lst = query.list();
        if (lst != null && !lst.isEmpty()) {
            Users user = lst.get(0);
            if (user != null) {
                return user.getUserName();
            }
        }
        return null;

    }

    /**
     * tim kiem danh sach nguoi dung cua don vi
     *
     * @param deptId
     * @return
     */
    public List<Users> getUsersOfDept(Long deptId) {
        List lstResult = new ArrayList<Users>();
        String sql = "";
        try {
            sql = "SELECT u FROM Users u WHERE u.status = ? and ((u.deptId = ? or u.deptRepresentId = ?) "
                    + "OR u.userId in (SELECT ru.userId FROM RoleUserDept ru,RoleUser r "
                    + " WHERE ru.userId = r.userId and ru.roleId=r.roleId "
                    + " and ru.deptId = ? and ru.isActive=1 and r.isActive = 1))"
                    + " order by u.posId desc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ";
            Query query = getSession().createQuery(sql);
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, deptId);
            query.setParameter(3, deptId);
            lstResult = query.list();

            //Get User dc gan role vao BTP
            if (lstResult == null) {
                lstResult = new ArrayList<Users>();
            }

            if (lstResult != null && !lstResult.isEmpty()) {
                for (int i = 0; i < lstResult.size(); i++) {
                    Users user = (Users) lstResult.get(i);
                    if (user instanceof HibernateProxy) {
                        HibernateProxy proxy = (HibernateProxy) user;
                        Users newUser = (Users) proxy.getHibernateLazyInitializer().getImplementation();
                        lstResult.set(i, newUser);
                    }
                }
            }

        } catch (Exception e) {
            e.getMessage();
            return new ArrayList<Users>();
        }
        return lstResult;
    }

    /**
     * tim kiem danh sach nguoi dung cua don vi
     *
     * @param deptId
     * @return
     */
    public List<Users> getUsersOfDepartment(Long deptId) {
        List<Users> lstResult = new ArrayList<Users>();
        List<Users> result = new ArrayList<Users>();
        String sql = "";
        try {
            sql = "SELECT u FROM Users u WHERE u.status = ? and (u.deptId = ? "
                    + "OR u.userId in (SELECT ru.userId FROM RoleUserDept ru,RoleUser r "
                    + " WHERE ru.userId = r.userId and ru.roleId=r.roleId "
                    + " and ru.deptId = ? and ru.isActive=1 and r.isActive = 1))"
                    + " order by u.posId desc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ";
            Query query = getSession().createQuery(sql);
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, deptId);

            lstResult = query.list();

            //Get User dc gan role vao BTP
            if (lstResult == null) {
                lstResult = new ArrayList<Users>();
            }
//
//            if (lstResult != null && !lstResult.isEmpty()) {
//                for (int i = 0; i < lstResult.size(); i++) {
//                    Users user = (Users) lstResult.get(i);
//                    if (user instanceof HibernateProxy) {
//                        HibernateProxy proxy = (HibernateProxy) user;
//                        Users newUser = (Users) proxy.getHibernateLazyInitializer().getImplementation();
//                        lstResult.set(i, newUser);
//                    }
//                }
//            }          

            for (Users users : lstResult) {
                Users bo = new Users();
                bo.setUserId(users.getUserId());
                bo.setFullName(users.getFullName());
                result.add(bo);
            }

//            lstResult.addAll(roleUsers);
        } catch (Exception e) {
            e.getMessage();
            return new ArrayList<Users>();
        }
        return result;
    }

    /**
     * get All Users of Department (not self)
     *
     * @param deptId
     * @param userId
     * @return
     */
    public List<Users> getUsersOfDept(Long deptId, Long userId) {
        List lstResult = new ArrayList<Users>();
        String sql = "";
        try {
            sql = "SELECT u FROM Users u WHERE u.status = ? and u.userId <> ? and (u.deptId = ? "
                    + "OR u.userId in (SELECT ru.userId FROM RoleUserDept ru,RoleUser r "
                    + " WHERE ru.userId = r.userId and ru.roleId=r.roleId "
                    + " and ru.deptId = ? and ru.isActive=1 and r.isActive = 1))"
                    + " order by u.posId desc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ";

            Query query = getSession().createQuery(sql);
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, userId);
            query.setParameter(2, deptId);
            query.setParameter(3, deptId);
            lstResult = query.list();
            if (lstResult != null && !lstResult.isEmpty()) {
                for (int i = 0; i < lstResult.size(); i++) {
                    Users user = (Users) lstResult.get(i);
                    if (user instanceof HibernateProxy) {
                        HibernateProxy proxy = (HibernateProxy) user;
                        Users newUser = (Users) proxy.getHibernateLazyInitializer().getImplementation();
                        lstResult.set(i, newUser);
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            return new ArrayList<Users>();
        }
        return lstResult;
    }

    /**
     * tim kiem danh sach nguoi dung cua don vi
     *
     * @param deptId
     * @param userId
     * @return
     */
    public List<Users> getUsersOfDept(String deptIds, Long userId) {
        List lstResult = new ArrayList<Users>();
        String sql = "";
        try {
            if (deptIds != null && !deptIds.equals("")) {
                String[] depts = deptIds.split(";");
                Long[] deptId = new Long[depts.length];
                for (int i = 0; i < depts.length; i++) {
                    deptId[i] = Long.parseLong(depts[i]);
                }
                sql = "SELECT u FROM Users u WHERE u.status = :status and (u.deptId IN (:deptIds) "
                        + "OR u.userId in ( SELECT ru.userId FROM RoleUserDept ru WHERE ru.deptId IN (:deptIds2)))"
                        + " order by u.posId desc,nlssort(lower(u.fullName),'nls_sort = Vietnamese') ";

                Query query = getSession().createQuery(sql);
                query.setParameter("status", Constants.Status.ACTIVE);
                query.setParameterList("deptIds", Arrays.asList(deptId));
                query.setParameterList("deptIds2", Arrays.asList(deptId));
                // query.setParameter(2, userId);
                lstResult = query.list();
                if (lstResult != null && !lstResult.isEmpty()) {
                    for (int i = 0; i < lstResult.size(); i++) {
                        Users user = (Users) lstResult.get(i);
                        if (user instanceof HibernateProxy) {
                            HibernateProxy proxy = (HibernateProxy) user;
                            Users newUser = (Users) proxy.getHibernateLazyInitializer().getImplementation();
                            lstResult.set(i, newUser);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            return new ArrayList<Users>();
        }
        return lstResult;
    }

    /**
     * tim kiem danh sach chuyen vien cua don vi
     *
     * @param deptId
     * @param userId
     * @param pos
     * @param objectId
     * @param objectType
     * @return
     */
    public List<Users> getStaffOfDept(Long userId, Long deptId, String pos, Long objectId, Long objectType) {
        List lstResult = new ArrayList<Users>();
        List listParam = new ArrayList();
        String sql = "";
        try {
            sql = "SELECT u FROM Users u WHERE u.status = ? and (u.deptId = ? or u.deptRepresentId = ?)";
            listParam.add(Constants.Status.ACTIVE);
            listParam.add(deptId);
            listParam.add(deptId);
            if (pos != null && pos.equals("LEADER")) {
                sql += " and u.posId in (SELECT p.posId FROM Position p WHERE p.status = 1 and p.posType = 1 and p.posCode like ? )";
                listParam.add("%" + Constants.POSITION.LEADER_CODE + "%");

                //get user by role
                sql += " OR (u.userId in ("
                        + " SELECT ru.userId FROM RoleUserDept ru WHERE ru.deptId = ?"
                        + " AND ru.roleId in (SELECT r.roleId FROM Roles r WHERE r.roleCode like ? escape '!' or r.roleCode like ? escape '!' )))";
                listParam.add(deptId);
                listParam.add("%" + Constants.ROLES.LEAD_ROLE + "%");
                listParam.add("%" + Constants.ROLES.LEAD_OFFICE_ROLE + "%");
            } else if (pos != null && pos.equals("STAFF")) {
                sql += " and u.posId not in (SELECT p.posId FROM Position p WHERE p.status = 1 and p.posType = 1 and (p.posCode like ? or p.posCode like ?) )";
                listParam.add("%" + Constants.POSITION.LEAD_CODE + "%");
                listParam.add("%" + Constants.POSITION.LEAD_OFFICE_CODE + "%");

                //get user by role
                sql += " OR (u.userId in ("
                        + " SELECT ru.userId FROM RoleUserDept ru WHERE ru.deptId = ?"
                        + " AND ru.roleId not in (SELECT r.roleId FROM Roles r WHERE r.roleCode like ? escape '!' or r.roleCode like ? escape '!' )))";
                listParam.add(deptId);
                listParam.add("%" + Constants.ROLES.LEAD_ROLE + "%");
                listParam.add("%" + Constants.ROLES.LEAD_OFFICE_ROLE + "%");
            } else {
                sql += "OR u.userId in ("
                        + " SELECT ru.userId FROM RoleUserDept ru WHERE ru.deptId = ?)";
                listParam.add(deptId);
            }
//            sql += " and u.userId <> ?";
//            listParam.add(userId);
//            sql += " and u.userId not in (SELECT pr.sendUserId FROM Process pr WHERE pr.isActive = 1 and pr.sendUserId is not null "
//                    + " and pr.objectId = ? and pr.objectType = ? and pr.status <> 5)";
//            listParam.add(objectId);
//            listParam.add(objectType);
//            sql += " and u.userId not in (SELECT pr.receiveUserId FROM Process pr WHERE pr.isActive = 1 and pr.receiveUserId is not null "
//                    + " and pr.objectId = ? and pr.objectType = ? and pr.status <> 5)";
//            listParam.add(objectId);
//            listParam.add(objectType);
            Query query = getSession().createQuery(sql);
            for (int i = 0; i < listParam.size(); i++) {
                query.setParameter(i, listParam.get(i));
            }
            lstResult = query.list();
            if (lstResult != null && !lstResult.isEmpty()) {
                for (int i = 0; i < lstResult.size(); i++) {
                    Users user = (Users) lstResult.get(i);
                    if (user instanceof HibernateProxy) {
                        HibernateProxy proxy = (HibernateProxy) user;
                        Users newUser = (Users) proxy.getHibernateLazyInitializer().getImplementation();
                        lstResult.set(i, newUser);
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            return new ArrayList<Users>();
        }
        return lstResult;
    }

    /**
     * tim kiem chuyen vien phoi hop tham dinh
     *
     * @param staffs
     * @param start
     * @param count
     * @return
     */
    public GridResult getCoordinateStaffs(String staffs, int start, int count) {
        List lstResult = new ArrayList<Users>();
        int total = 0;
        GridResult gridResult = new GridResult();
        try {
            if (staffs != null && !staffs.equals("")) {
                String[] depts = staffs.split(";");
                Long[] deptId = new Long[depts.length];
                for (int i = 0; i < depts.length; i++) {
                    deptId[i] = Long.parseLong(depts[i]);
                }
                String countSql = "SELECT count(u) FROM Users u WHERE u.status = :status and u.userId IN (:userIds) "; // and u.userId <> ?
                Query query = getSession().createQuery(countSql);
                query.setParameter("status", Constants.Status.ACTIVE);
                query.setParameterList("userIds", Arrays.asList(deptId));
                // query.setParameter(2, userId);
                total = Integer.parseInt(query.list().get(0).toString());
                String sql = "SELECT u FROM Users u WHERE u.status = :status and u.userId IN (:userIds) "
                        + " order by nlssort(lower(u.fullName),'nls_sort = Vietnamese') "; // and u.userId <> ?
                query = getSession().createQuery(sql);
                query.setParameter("status", Constants.Status.ACTIVE);
                query.setParameterList("userIds", Arrays.asList(deptId));
                query.setFirstResult(start);
                query.setMaxResults(count);
                lstResult = query.list();
            }
            gridResult.setLstResult(lstResult);
            gridResult.setnCount(Long.valueOf(total));

            return gridResult;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    /**
     * tim kiem lanh dao don vi
     *
     * @param deptId
     * @param role
     * @param start
     * @param sortField
     * @return
     */
    public GridResult getLeadersOfDept(Long deptId, String role, int start, int count, String sortField) {
        List listParam = new ArrayList();
        GridResult gridResult = new GridResult();
        try {
            StringBuilder strBuf = new StringBuilder(" FROM Users u,  Position p where u.status = ? and (u.deptId = ? or u.deptRepresentId = ?) and u.posId = p.posId and p.status = 1 and p.posType = 1 and p.posCode like ? ");
            listParam.add(Constants.Status.ACTIVE);
            listParam.add(deptId);
            listParam.add(deptId);
            listParam.add("%" + role + "%");
            Query query = getSession().createQuery("SELECT count(u) " + strBuf.toString());
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
                strBuf.append(" order by u.posId desc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
            }
            query = getSession().createQuery("SELECT u" + strBuf.toString());
            for (int i = 0; i < listParam.size(); i++) {
                query.setParameter(i, listParam.get(i));
            }
            query.setFirstResult(start);
            query.setMaxResults(count);

            List<Users> lstUserses = query.list();
            gridResult.setLstResult(lstUserses);
            gridResult.setnCount(Long.valueOf(total));

            return gridResult;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
    /*
     * Lay danh sach lanh dao Lanh dao van phong 
     * Added by SyTv
     */

    public List<Users> getLeaderOffice(Long deptId) {
        List<Users> user = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery("SELECT u FROM Users u WHERE u.status = ? AND (((u.deptId = ? OR u.deptRepresentId = ?) "
                    + " AND u.posId IN (SELECT p.posId FROM Position p WHERE p.posCode LIKE ? escape '!'))"
                    + " OR u.userId in ("
                    + " SELECT ru.userId FROM RoleUserDept ru WHERE ru.deptId = ?"
                    + " AND ru.roleId in (SELECT r.roleId FROM Roles r WHERE r.roleCode LIKE ? escape '!')))"
                    + " order by u.posId desc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, deptId);
            query.setParameter(3, "%" + Constants.POSITION.LEAD_OFFICE_CODE + "%");
            query.setParameter(4, deptId);
            query.setParameter(5, "%" + Constants.ROLES.LEAD_ROLE + "%");
            user = query.list();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /*
     * Lay danh sach lanh dao
     */
    /*
     public List<Users> getLeader(Long deptId) {
     List<Users> user = new ArrayList<Users>();
     try {
     Query query = getSession().createQuery("SELECT u FROM Users u WHERE u.status = ? AND (((u.deptId = ? OR u.deptRepresentId = ?) "
     + " AND u.posId IN (SELECT p.posId FROM Position p WHERE p.posCode LIKE ? escape '!' and p.posCode NOT LIKE ? escape '!'))"
     + " OR u.userId in ("
     + " SELECT ru.userId FROM RoleUserDept ru WHERE ru.deptId = ?"
     + " AND ru.roleId in (SELECT r.roleId FROM Roles r WHERE r.roleCode LIKE ? escape '!')))"
     + " order by u.posId desc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
     query.setParameter(0, Constants.Status.ACTIVE);
     query.setParameter(1, deptId);
     query.setParameter(2, deptId);
     query.setParameter(3, "%" + Constants.POSITION.LEADER_CODE + "%");
     query.setParameter(4, "%" + Constants.POSITION.LEAD_OFFICE_CODE + "%");
     query.setParameter(5, deptId);
     query.setParameter(6, "%" + Constants.ROLES.LEAD_OFFICE_ROLE + "%");
     // query.setParameter(7, "%" + Constants.ROLES.LEAD_OFFICE_ROLE + "%");
     user = query.list();
     return user;
     } catch (Exception e) {
     log.error(e);
     return null;
     }
     }
     */
    public List<Users> getLeaderOfStaff(Long deptId) {
        List<Users> user = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery("SELECT u FROM Users u WHERE u.status = ? AND ((u.deptId = ? OR u.deptRepresentId = ?) "
                    + " AND u.posId IN (SELECT p.posId FROM Position p WHERE p.posCode LIKE ? escape '!' or p.posCode LIKE ? escape '!'))"
                    + " order by u.posId desc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, deptId);
            query.setParameter(3, "%" + Constants.POSITION.LEADER_OF_STAFF_T + "%");
            query.setParameter(4, "%" + Constants.POSITION.GDTT + "%");
            user = query.list();
            return user;
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    public List<Users> getLeader(Long deptId) {
        List<Users> user = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery("SELECT u FROM Users u WHERE u.status = ? AND ((u.deptId = ? OR u.deptRepresentId = ?) "
                    + " AND u.posId IN (SELECT p.posId FROM Position p WHERE p.posCode LIKE ? escape '!' or p.posCode LIKE ? escape '!'))"
                    + " order by u.posId asc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, deptId);
            query.setParameter(3, "%" + Constants.POSITION.LEADER_CT + "%");
            query.setParameter(4, "%" + Constants.POSITION.LEADER_PCT + "%");
            user = query.list();
            return user;
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    public List<Users> getLeaderByUser(Long deptLoginUser) {
        try {
            // 11/11/2014 viethd
            //Query queryDepts = getSession().createSQLQuery("select PARENT_ID from DEPARTMENT where status = 1 start with DEPT_ID = " + deptLoginUser + " connect by prior DEPT_ID =  PARENT_ID");
            Query queryDepts = getSession().createSQLQuery("select PARENT_ID from DEPARTMENT "
                    + "where status = 1 start with DEPT_ID = ? connect by prior DEPT_ID =  PARENT_ID");
            queryDepts.setParameter(0, deptLoginUser);
            List<BigDecimal> depts = queryDepts.list();

            List<Long> longList = new ArrayList<Long>();
            for (BigDecimal i : depts) {
                longList.add(i.longValue());
            }
            if (depts != null && depts.size() > 0) {

                //11/11/2014 viethd
//                Query query = getSession().createQuery("SELECT u FROM Users u "
//                        + "WHERE u.status = 1 "
//                        + "AND u.posId IN (SELECT p.posId FROM Position p WHERE p.status = 1 AND p.posCode LIKE ? escape '!' or p.posCode LIKE ? escape '!') "
//                        + "AND (u.deptId IN (" + lstDept + ")"
//                        + "OR u.deptRepresentId IN (" + lstDept + ")) "
//                        + "order by u.posId asc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') "
//                );
                Query query = getSession().createQuery("SELECT u FROM Users u "
                        + "WHERE u.status = 1 "
                        + "AND u.posId IN (SELECT p.posId FROM Position p WHERE p.status = 1 AND p.posCode LIKE ? escape '!' or p.posCode LIKE ? escape '!') "
                        + "AND (u.deptId IN (:depts1)"
                        + "OR u.deptRepresentId IN (:depts2)) "
                        + "order by u.posId asc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') "
                );

                query.setParameter(0, "%" + Constants.POSITION.LEADER_CT + "%");
                query.setParameter(1, "%" + Constants.POSITION.LEADER_PCT + "%");
                query.setParameterList("depts1", longList);
                query.setParameterList("depts2", longList);
                List<Users> user = query.list();
                return user;
            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<Users> getLeaderP(Long deptId) {
        List<Users> user = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery("SELECT u FROM Users u WHERE u.status = ? AND ((u.deptId = ? OR u.deptRepresentId = ?) "
                    + " AND u.posId IN (SELECT p.posId FROM Position p WHERE p.posCode LIKE ? or p.posCode LIKE ? escape '!'))"
                    + " order by u.posId asc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, deptId);
            query.setParameter(3, "%" + Constants.POSITION.LEADER_PCT + "%");
            query.setParameter(4, "%" + Constants.POSITION.LEADER_CT + "%");
            user = query.list();
            return user;
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    public List<Users> getCVOfDept(Long deptId) {
        List<Users> user = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery("SELECT u FROM Users u WHERE u.status = ? AND ((u.deptId = ? OR u.deptRepresentId = ?) "
                    + " AND u.posId IN (SELECT p.posId FROM Position p WHERE p.posCode LIKE ? escape '!'))"
                    + " order by u.posId asc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, deptId);
            query.setParameter(3, "%" + Constants.POSITION.VFA_CV + "%");
            user = query.list();
            return user;
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    public List<Users> getAllLeaderOfStaffInOffice(Long deptId) {
        List<Users> user = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery("SELECT u FROM Users u WHERE u.status = ? AND ((u.deptId = ? OR u.deptRepresentId = ?) "
                    + " AND u.posId IN (SELECT p.posId FROM Position p"
                    + " WHERE p.posCode LIKE ? escape '!'"
                    + " or p.posCode LIKE ? escape '!'"
                    + " or p.posCode LIKE ? escape '!'"
                    + " or p.posCode LIKE ? escape '!'))"//old + " AND u.posId IN (SELECT p.posId FROM Position p WHERE p.posCode LIKE ? escape '!' or p.posCode LIKE ? escape '!'))"
                    + " order by u.posId asc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, deptId);
            query.setParameter(3, "%" + Constants.POSITION.LEADER_OF_STAFF_T + "%");
            query.setParameter(4, "%" + Constants.POSITION.LEADER_OF_STAFF_P + "%");
            query.setParameter(5, "%" + Constants.POSITION.GDTT + "%");//binhnt update 071015
            query.setParameter(6, "%" + Constants.POSITION.PGDTT + "%");//binhnt update 071015
            user = query.list();
            return user;
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }
    /*
     * Lay danh sach lanh dao cua don vi
     */

    public List<Users> getLeaderOfDept(Long deptId) {

        Query query = getSession().createQuery("SELECT distinct u FROM Users u WHERE u.status = ? "
                + " AND ((u.deptId = ? OR u.deptRepresentId = ?) "
                + " AND u.posId IN (SELECT p.posId FROM Position p WHERE p.status = 1 and p.posType = 1 and (p.posCode LIKE ? escape '!') or (p.posCode LIKE ? escape '!')) "
                //get Leader theo Role (duoc gan quyen LD cua don vi nay)
                + " OR (u.userId in ("
                + " SELECT ru.userId FROM RoleUserDept ru WHERE ru.deptId = ?"
                + " AND ru.roleId in (SELECT r.roleId FROM Roles r WHERE r.roleCode like ? escape '!' or r.roleCode like ? escape '!' )) "
                + " AND u.posId IN (SELECT p.posId FROM Position p WHERE p.status = 1 and p.posType = 1 and (p.posCode LIKE ? escape '!') or (p.posCode LIKE ? escape '!')) "
                + " )) "
                + " order by u.posId desc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, deptId);
        query.setParameter(2, deptId);
        query.setParameter(3, "%" + Constants.POSITION.LEADER_CODE + "%");
        query.setParameter(4, "%" + Constants.POSITION.LEAD_OFFICE_CODE + "%");
        query.setParameter(5, deptId);
        query.setParameter(6, "%" + Constants.ROLES.LEAD_ROLE + "%");
        query.setParameter(7, "%" + Constants.ROLES.LEAD_OFFICE_ROLE + "%");
        query.setParameter(8, "%" + Constants.POSITION.LEADER_CODE + "%");
        query.setParameter(9, "%" + Constants.POSITION.LEAD_OFFICE_CODE + "%");
        List<Users> users = query.list();

        List<Users> lst = new ArrayList();
//        Users v = new Users();
//        v.setUserId(Constants.COMBOBOX_HEADER_VALUE);
//        v.setFullName(Constants.COMBOBOX_HEADER_TEXT_SELECT);
//        lst.add(v);

        //get Leader theo Role (duoc gan quyen LD cua don vi nay)
//        List<Users> roleLeads = findLeaderRoleUserDept(deptId);
        if (users != null && !users.isEmpty()) {
            for (int i = 0; i < users.size(); i++) {
                String fullName = users.get(i).getFullName();
                fullName = StringUtils.escapeHtml(fullName);
                if (fullName.length() > 500) {
                    fullName = fullName.substring(0, 499);
                }
                users.get(i).setFullName(fullName);

//                //check xem 2list co chung user nao ko?
//                if (roleLeads != null && !roleLeads.isEmpty()) {
//                    for (int j = 0; j < roleLeads.size(); j++) {
//                        if (roleLeads.get(j).getUserId().equals(users.get(i).getUserId())) {
//                            roleLeads.remove(j);
//                        }
//                    }
//                }
            }
            lst.addAll(users);
        }
//        lst.addAll(roleLeads);

        return lst;
    }

    public List<Users> getLeaderOfDeptBTP(Long deptId) {
        if (lstFactory == null) {
            lstFactory = new HashMap();
        }

        if (lstFactory.containsKey(deptId)) {
            return lstFactory.get(deptId);
        }

        Query query = getSession().createQuery("SELECT distinct u FROM Users u WHERE u.status = ? "
                + "AND ((u.deptId in (select d.deptId from Department d where (d.deptId = ? or d.parentId = ?) and d.status = 1)) "
                + " AND u.posId IN (SELECT p.posId FROM Position p WHERE p.status = 1 and p.posType = 1 and (p.posCode LIKE ? escape '!') or (p.posCode LIKE ? escape '!')) "
                //get Leader theo Role (duoc gan quyen LD cua don vi nay)
                + " OR (u.userId in ("
                + " SELECT ru.userId FROM RoleUserDept ru WHERE ru.deptId in (select d2.deptId from Department d2 where (d2.deptId = ? or d2.parentId = ?) and d2.status = 1) "
                + " AND ru.roleId in (SELECT r.roleId FROM Roles r WHERE r.roleCode like ? escape '!' or r.roleCode like ? escape '!' )) "
                + "AND u.posId IN (SELECT p.posId FROM Position p WHERE p.status = 1 and p.posType = 1 and (p.posCode LIKE ? escape '!') or (p.posCode LIKE ? escape '!')) "
                + " AND u.deptId in (select d.deptId from Department d where (d.deptId = ? or d.parentId = ?) and d.status = 1)"
                + " )) "
                + " order by u.posId desc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, deptId);
        query.setParameter(2, deptId);
        query.setParameter(3, "%" + Constants.POSITION.LEADER_CODE + "%");
        query.setParameter(4, "%" + Constants.POSITION.LEAD_OFFICE_CODE + "%");
        query.setParameter(5, deptId);
        query.setParameter(6, deptId);
        query.setParameter(7, "%" + Constants.ROLES.LEAD_ROLE + "%");
        query.setParameter(8, "%" + Constants.ROLES.LEAD_OFFICE_ROLE + "%");
        query.setParameter(9, "%" + Constants.POSITION.LEADER_CODE + "%");
        query.setParameter(10, "%" + Constants.POSITION.LEAD_OFFICE_CODE + "%");
        query.setParameter(11, deptId);
        query.setParameter(12, deptId);
        List<Users> users = query.list();

//        List<Users> lst = new ArrayList();
//        Users v = new Users();
//        v.setUserId(Constants.COMBOBOX_HEADER_VALUE);
//        v.setFullName(Constants.COMBOBOX_HEADER_TEXT_SELECT);
//        lst.add(v);
        //get Leader theo Role (duoc gan quyen LD cua don vi nay)
//        List<Users> roleLeads = findLeaderRoleUserDept(deptId);
//        if (users != null && !users.isEmpty()) {
//            for (int i = 0; i < users.size(); i++) {
//                String fullName = users.get(i).getFullName();
//                fullName = StringUtils.escapeHtml(fullName);
//                if (fullName.length() > 500) {
//                    fullName = fullName.substring(0, 499);
//                }
//                users.get(i).setFullName(fullName);
//
////                //check xem 2list co chung user nao ko?
////                if (roleLeads != null && !roleLeads.isEmpty()) {
////                    for (int j = 0; j < roleLeads.size(); j++) {
////                        if (roleLeads.get(j).getUserId().equals(users.get(i).getUserId())) {
////                            roleLeads.remove(j);
////                        }
////                    }
////                }
//            }
//            lst.addAll(users);
//        }
//        lst.addAll(roleLeads);
        if (lstFactory.size() < 10) {
            lstFactory.put(deptId, users);
        }
        return users;
    }

    /*
     * Lay danh sach lanh dao cua don vi va cac lanh dao don vi cha
     */
    public List<Users> getAllLeaderOfDept(Long deptId) {
        if (lstFactory == null) {
            lstFactory = new HashMap();
        }

        if (lstFactory.containsKey(deptId)) {
            return lstFactory.get(deptId);
        }
        Query query = getSession().createQuery("SELECT u FROM Users u WHERE u.status = ? AND ((u.deptId = ? OR u.deptRepresentId = ?) "
                + " AND u.posId IN (SELECT p.posId FROM Position p WHERE p.status = 1 and p.posType = 1 and (p.posCode LIKE ? escape '!') or (p.posCode LIKE ? escape '!')) "
                //get Leader theo Role (duoc gan quyen LD cua don vi nay)
                + " OR u.userId in ("
                + " SELECT ru.userId FROM RoleUserDept ru WHERE ru.deptId = ?"
                + " AND ru.roleId in (SELECT r.roleId FROM Roles r WHERE r.roleCode like ? escape '!' or r.roleCode like ? escape '!' )))"
                + " order by u.posId desc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
        query.setParameter(0, Constants.Status.ACTIVE);
        query.setParameter(1, deptId);
        query.setParameter(2, deptId);
        query.setParameter(3, "%" + Constants.POSITION.LEADER_CODE + "%");
        query.setParameter(4, "%" + Constants.POSITION.LEAD_OFFICE_CODE + "%");
        query.setParameter(5, deptId);
        query.setParameter(6, "%" + Constants.ROLES.LEAD_ROLE + "%");
        query.setParameter(7, "%" + Constants.ROLES.LEAD_OFFICE_ROLE + "%");
        List<Users> users = query.list();

        List<Users> lst = new ArrayList();
        Users v = new Users();
        v.setUserId(Constants.COMBOBOX_HEADER_VALUE);
        v.setFullName(Constants.COMBOBOX_HEADER_TEXT_SELECT);
        lst.add(v);

        if (users != null && !users.isEmpty()) {
            for (int i = 0; i < users.size(); i++) {
                String fullName = users.get(i).getFullName();
                fullName = StringUtils.escapeHtml(fullName);
                if (fullName.length() > 500) {
                    fullName = fullName.substring(0, 499);
                }
                users.get(i).setFullName(fullName);
            }
            lst.addAll(users);
        }
//        lst.addAll(roleLeads);
        if (lstFactory.size() < 10) {
            lstFactory.put(deptId, lst);
        }
        return lst;
    }

    /**
     * lay lanh dao don vi
     *
     * @param deptId
     * @return
     */
    public Users getLeaderOfOffice(Long deptId) {
        try {
            Query query = getSession().createQuery("SELECT u FROM Users u,Position p WHERE p.status = 1 and p.posType = 1 and u.posId= p.posId and u.status = ? AND u.deptId=?"
                    + " AND p.posCode LIKE (?) AND  p.posCode LIKE (?)"
                    + " order by u.posId desc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, "%" + Constants.POSITION.LEADER_CODE + "%");
            query.setParameter(3, "%" + Constants.POSITION.SMS_CODE + "%");

            List<Users> user = query.list();
            if (!user.isEmpty()) {
                return user.get(0);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public List<Users> getAllLeaderOffice(Long deptId) {
        try {
            List<Users> users = new ArrayList<Users>();
            Query query = getSession().createQuery("SELECT u FROM Users u WHERE u.status = ? AND (((u.deptId=? OR u.deptRepresentId = ?)"
                    + " AND (u.posId IN (SELECT p.posId FROM Position p WHERE p.status = 1 and p.posType = 1 and p.posCode LIKE ? escape '!' or p.posCode LIKE ? escape '!')))"
                    + " OR u.userId in ("
                    + " SELECT ru.userId FROM RoleUserDept ru WHERE ru.deptId = ?"
                    + " AND ru.roleId in (SELECT r.roleId FROM Roles r WHERE r.roleCode like ? escape '!' or r.roleCode like ? escape '!' )))"
                    + " ORDER BY u.posId desc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, deptId);
            query.setParameter(3, "%" + Constants.POSITION.LEAD_OFFICE_CODE + "%");
            query.setParameter(4, "%" + Constants.POSITION.LEADER_CODE + "%");
            query.setParameter(5, deptId);
            query.setParameter(6, "%" + Constants.ROLES.LEAD_ROLE + "%");
            query.setParameter(7, "%" + Constants.ROLES.LEAD_OFFICE_ROLE + "%");
            users = query.list();
            List<Users> lst = new ArrayList();

            Users v = new Users();
            v.setUserId(Constants.COMBOBOX_HEADER_VALUE);
            v.setFullName(Constants.COMBOBOX_HEADER_TEXT_SELECT);
//            if (users != null && !users.isEmpty()) {
//                for (int i = 0; i < users.size(); i++) {
//                    String fullName = users.get(i).getFullName();
//                    fullName = StringUtils.escapeHtml(fullName);
//                    if (fullName.length() > 500) {
//                        fullName = fullName.substring(0, 499);
//                    }
//                    users.get(i).setFullName(fullName);
//                }
//            }
            lst.add(v);
            for (Users users1 : users) {
                Users bo = new Users();
                bo.setUserId(users1.getUserId());
                bo.setFullName(users1.getFullName());
                lst.add(bo);
            }

            return lst;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return null;
    }

    /**
     * tim kiem don vi theo userId
     *
     * @param userId
     * @return
     */
    public Long findDeptByUser(Long userId) {
        try {
            Query query = getSession().createQuery("Select a from Users a where a.status = ? and a.userId = ?");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, userId);
            List<Users> userList = (List<Users>) query.list();
            if (userList.size() > 0) {
                return userList.get(0).getDeptId();
            }
            return null;
        } catch (Exception ex) {
            String msg = ex.getMessage();
            return null;
        }
    }

    // tim danh sach can bo theo chuc vu trong don vi
    /**
     * tim kiem user theo chuc vu
     *
     * @param deptId
     * @param posCode
     * @return
     */
    public Users findUserByPosition(Long deptId, String posCode) {
        try {
            Query query = getSession().createQuery("select u from Users u"
                    + " where u.status=? and u.deptId = ? and "
                    + " u.posId in (select p.posId from Position p where p.posCode = ? and p.status = 1 and p.posType = 1)");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, posCode);
            List<Users> userList = (List<Users>) query.list();
            if (userList.size() > 0) {
                return userList.get(0);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    /**
     * tim kiem user theo chuc vu
     *
     * @param deptId
     * @param posCode
     * @return
     */
    public List<Users> findLstUserByPosition(Long deptId, String posCode) {
        List<Users> user = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery("select u from Users u"
                    + " where u.status=? and u.deptId = ? and "
                    + " u.posId in (select p.posId from Position p where p.posCode = ? and p.status = 1 and p.posType = 1)");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, posCode);
            user = (List<Users>) query.list();
            if (user.size() > 0) {
                return user;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    /**
     * tim kiem user theo danh sach chuc vu binhnt 08102015
     *
     * @param deptId
     * @param lstPosCode
     * @return
     */
    public List<Users> findLstUserByLstPosition(Long deptId, List<String> lstPosCode) {
        List<Users> user = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery("select u from Users u"
                    + " where u.status=? and u.deptId = ? and "
                    + " u.posId in (select p.posId"
                    + " from Position p"
                    + " where p.posCode in (:lstPosCode)"
                    + " and p.status = 1"
                    + " and p.posType = 1)");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameterList("lstPosCode", lstPosCode);
            user = (List<Users>) query.list();
            if (user.size() > 0) {
                return user;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    /**
     * Tim danh sach nhan vien theo role_user_dept
     *
     * @return Danh sach User
     * @param deptId
     * @since
     */
    public List<Users> findUsersRoleUserDept(Long deptId) {
        List<Users> result = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery("SELECT u FROM Users u "
                    + " WHERE u.status = ? AND u.userId in ("
                    + " SELECT ru.userId FROM RoleUserDept ru WHERE ru.deptId = ?)"
                    + " order by nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            // query.setParameter(2, deptId);
            // query.setParameter(3, "%" + Constants.POSITION.LEAD_CODE + "%");
            result = query.list();

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return result;
    }

    /**
     * Get Full Name by UserName
     *
     * @return String
     * @param userName
     * @since
     */
    public String getFullNameByUserName(String userName) {
        if ("".equals(userName)) {
            return "";
        }
        String fullName = "";
        try {
            Users user = findUserByName(userName);
            if (user != null) {
                fullName = user.getFullName();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return fullName;
    }

    /**
     * Tim danh sach lanh dao theo role_user_dept
     *
     * @return Danh sach User
     * @param deptId
     * @since
     */
    public List<Users> findLeaderRoleUserDept(Long deptId) {
        List<Users> result = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery("SELECT u FROM Users u "
                    + " WHERE u.status = ? AND u.userId in ("
                    + " SELECT ru.userId FROM RoleUserDept ru WHERE ru.deptId = ?"
                    + " AND ru.roleId in (SELECT r.roleId FROM Roles r WHERE r.roleCode like ? escape '!' or r.roleCode like ? escape '!' ))"
                    + " order by u.posId desc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
            //+ " AND (u.posId IN (SELECT p.posId FROM Position p WHERE p.status = 1 and p.posType = 1 and p.posCode LIKE ? escape '!' or p.posCode LIKE ? escape '!'))");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, "%" + Constants.ROLES.LEAD_ROLE + "%");
            query.setParameter(3, "%" + Constants.ROLES.LEAD_OFFICE_ROLE + "%");
            // query.setParameter(2, deptId);
            // query.setParameter(3, "%" + Constants.POSITION.LEAD_CODE + "%");
            result = query.list();

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return result;
    }

    /**
     * bo to form
     *
     * @param bo
     * @param form
     * @return
     */
    public UsersForm boToForm(Users bo, UsersForm form) {
        DepartmentDAOHE deptDaoHe = new DepartmentDAOHE();
        Department dept = null;
        Department deptParent = null;
        if (bo != null && form != null) {
            form.setUserId(bo.getUserId().toString());
            form.setUserName(bo.getUserName());
            form.setAliasName(bo.getAliasName());
            form.setBirthPlace(bo.getBirthPlace());
            form.setCellphone(bo.getCellphone());
//            form.setCreateDate(bo.getCreateDate());
            if (bo.getDateOfBirth() != null) {
                form.setDateOfBirth(bo.getDateOfBirth().toString());
            }
            form.setDateOfBirthDate(bo.getDateOfBirth());
            if (bo.getDeptId() != null) {
                form.setDeptId(bo.getDeptId());
                form.setDeptIdStr(bo.getDeptId().toString());
                form.setDeptLevel(bo.getDeptLevel());
                form.setDeptName(bo.getDeptName());

                dept = deptDaoHe.getById("deptId", bo.getDeptId());
                if (dept != null && dept.getParentId() != null) {
                    deptParent = deptDaoHe.getById("deptId", dept.getParentId());
                    if (deptParent != null) {
                        form.setDeptName(dept.getDeptName() + " - " + deptParent.getDeptName());
                    } else {
                        form.setDeptName(dept.getDeptName());
                    }
                }
            }

            if (bo.getDeptRepresentId() != null) {
                form.setDeptRepresentId(bo.getDeptRepresentId());
                form.setDeptRepresentIdStr(bo.getDeptRepresentId().toString());
                form.setDeptRepresentName(bo.getDeptRepresentName());
            }

            form.setBusinessId(bo.getBusinessId());
            form.setBusinessName(bo.getBusinessName());

            form.setDescription(bo.getDescription());
            form.setStaffCode(bo.getStaffCode());
            form.setEmail(bo.getEmail());
            form.setTelephone(bo.getTelephone());
            form.setFax(bo.getFax());
            form.setFullName(bo.getFullName());
            if (bo.getGender() != null) {
                form.setGender(bo.getGender().toString());
            }

            form.setIdentityCard(bo.getIdentityCard());
            form.setIp(bo.getIp());
            if (bo.getIssueDateIdent() != null) {
                form.setIssueDateIdent(bo.getIssueDateIdent().toString());
            }
            form.setIssueDateIdentDate(bo.getIssueDateIdent());
//            form.setIssueDatePassport(bo.getIssueDatePassport().toString());
            form.setIssuePlaceIdent(bo.getIssuePlaceIdent());
            form.setIssuePlacePassport(bo.getIssuePlacePassport());
            if (bo.getPosId() != null) {
                form.setPosId(bo.getPosId());
                form.setPosIdStr(bo.getPosId().toString());
            } else {
                form.setPosIdStr("-1");
            }

            if (bo.getProfileId() != null) {
                form.setProfileId(bo.getProfileId().toString());
            }

            if (bo.getUserRight() != null) {
                form.setUserRight(bo.getUserRight().toString());
            }
            form.setUserTypeId(bo.getUserTypeId());
            form.setStaffCode(bo.getStaffCode());
            form.setLastLogin(bo.getLastLogin());
            form.setStatus(bo.getStatus().toString());
            form.setDescription(bo.getDescription());
            form.setLastResetPassword(bo.getLastResetPassword());
            form.setLastChangePassword(bo.getLastChangePassword());
        }
        return form;
    }

    /*
     * Method lưu thong tin nguoi dung, khi nguoi dung tu sua = cach click vao DOI MAT KHAU
     * (Chi luu 1 so truong tren form cập nhật của người dùng (khác form cập nhật user của admin)
     */
    public Users formToBo(Users bo, UsersForm form) {
        if (bo != null && form != null) {
            bo.setFullName(form.getFullName());
            bo.setUserName(form.getUserName());
            bo.setCellphone(form.getCellphone());
            if (form.getDeptIdStr() != null && !"".equals(form.getDeptIdStr())) {
                bo.setDeptId(Long.valueOf(form.getDeptIdStr()));
                bo.setDeptName(form.getDeptName());
            } else {
                bo.setDeptId(null);
                bo.setDeptName(null);
            }
            if (form.getDeptRepresentIdStr() != null && !"".equals(form.getDeptRepresentIdStr())) {
                bo.setDeptRepresentId(Long.valueOf(form.getDeptRepresentIdStr()));
                bo.setDeptRepresentName(form.getDeptRepresentName());
            } else {
                bo.setDeptRepresentId(null);
                bo.setDeptRepresentName(null);
            }

            if (form.getPosIdStr() != null && !"".equals(form.getPosIdStr())) {
                bo.setPosId(Long.valueOf(form.getPosIdStr()));
            }

            bo.setAliasName(form.getAliasName());
            bo.setGender(Long.valueOf(form.getGender()));
            bo.setTelephone(form.getTelephone());
            bo.setFax(form.getFax());
            if (form.getDateOfBirth() != null && !"".equals(form.getDateOfBirth())) {
                bo.setDateOfBirth(DateTimeUtils.convertStringToTime(form.getDateOfBirth(), "yyyy-MM-dd"));
            } else {
                bo.setDateOfBirth(form.getDateOfBirthDate());
            }
            bo.setBirthPlace(form.getBirthPlace());
            bo.setStaffCode(form.getStaffCode());
            bo.setIdentityCard(form.getIdentityCard());

            if ((form.getIssueDateIdent() != null) && (!form.getIssueDateIdent().equals(""))) {
                bo.setIssueDateIdent(DateTimeUtils.convertStringToTime(form.getIssueDateIdent(), "yyyy-MM-dd"));
            } else {
                bo.setIssueDateIdent(form.getIssueDateIdentDate());
            }

            bo.setIssuePlaceIdent(form.getIssuePlaceIdent());
            bo.setDescription(form.getDescription());
            bo.setEmail(form.getEmail());
        }

        return bo;
    }

    /**
     * tim kiem don vi cua chuyen vien
     *
     * @return depid
     * @param deptId
     * @since
     */
    public Long getAgencyOfStaff(Long userId) {
        Long deptId = 0l;
        try {
            Users u = findById(userId);
            if (u != null) {
                deptId = u.getDeptId();
            }
            String hql = " select d from VDepartment d, Users u where d.deptId = u.deptId and u.userId = ? ";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, userId);
            List<VDepartment> lst = query.list();
            String lstPath = "";
            if (lst != null && lst.size() > 0) {
                lstPath = lst.get(0).getDeptPath();
                hql = "select v from VDepartment v, DeptType dt "
                        + "where v.deptTypeId = dt.deptTypeId and lower(dt.description) = 'cq' and ? like '%'|| v.deptPath ||'%' order by v.deptLevel desc";
                query = getSession().createQuery(hql);
                query.setParameter(0, lstPath);
                List<VDepartment> lstVDept = query.list();
                if (lstVDept != null) {
                    deptId = lstVDept.get(0).getDeptId();
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return deptId;
    }

    /**
     * tim kiem user theo staff code
     *
     * @return userId
     * @param staffCode
     * @since
     */
    public Long getUserByStaffCode(String staffCode) {//lay users theo staff code
        try {
            Query query = getSession().createQuery("SELECT u FROM Users u where u.staffCode = ?");
            query.setParameter(0, staffCode);

            List<Users> user = query.list();
            if (!user.isEmpty()) {
                return user.get(0).getUserId();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    /**
     *
     * @return
     */
    public List<Users> findAllUserOfBusiness() {
        List<Users> result = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery("SELECT u FROM Users u "
                    + " WHERE u.status = ? "
                    + "AND u.deptId is null "
                    + "order by u.posId desc, "
                    + "nlssort(lower(u.fullName), "
                    + "'nls_sort = Vietnamese') ");
            query.setParameter(0, Constants.Status.ACTIVE);
            result = query.list();

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return result;
    }

    /**
     *
     * @return
     */
    public List<Users> findAllUserOfStaff() {
        List<Users> result = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery("SELECT u FROM Users u "
                    + " WHERE u.status = ? "
                    + "AND u.deptId is not null "
                    + "order by u.posId desc, "
                    + "nlssort(lower(u.fullName), "
                    + "'nls_sort = Vietnamese') ");
            query.setParameter(0, Constants.Status.ACTIVE);
            result = query.list();

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return result;
    }

    /**
     *
     * @return
     */
    public List<Users> findAllUser() {
        List<Users> result = new ArrayList<>();
        try {
            Query query = getSession().createQuery("SELECT u FROM Users u "
                    + " WHERE u.status = ? "
                    + "order by u.posId desc, "
                    + "nlssort(lower(u.fullName),"
                    + "'nls_sort = Vietnamese') ");
            query.setParameter(0, Constants.Status.ACTIVE);
            result = query.list();

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return result;
    }

    public boolean checkUserByLstPosition(Long deptId, Long userId, List<String> lstPosCode) {
        boolean result = false;
        List<Users> user = new ArrayList<>();
        try {
            Query query = getSession().createQuery(
                    "select u from Users u"
                    + " where u.status= ?"
                    + " and u.deptId = ?"
                    + " and u.userId = ?"
                    + " and u.posId in (select p.posId"
                    + " from Position p"
                    + " where p.posCode in (:lstPosCode)"
                    + " and p.status = 1"
                    + " and p.posType = 1)");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, userId);
            query.setParameterList("lstPosCode", lstPosCode);
            user = (List<Users>) query.list();
            if (user.size() > 0) {
                result = true;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return result;
    }

    /**
     *
     * @param deptId
     * @param userId
     * @param lstPosCode
     * @return
     */
    public boolean checkRoleUserOfLst(Long deptId, Long userId, List<String> lstPosCode) {
        boolean result = false;
        List<Users> user = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery(
                    "select u from Users u"
                    + " where u.status= ?"
                    + " and u.deptId = ?"
                    + " and u.userId = ?"
                    + " and u.posId in (select p.posId"
                    + " from Position p"
                    + " where p.posCode in (:lstPosCode)"
                    + " and p.status = 1"
                    + " and p.posType = 1)");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, userId);
            query.setParameterList("lstPosCode", lstPosCode);
            user = (List<Users>) query.list();
            if (user.size() > 0) {
                result = true;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return result;
    }

    //tim kiem truong phong
    public List<Users> getTruongPhong(Long deptId) {
        List<Users> user = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery(
                    "SELECT u FROM Users u WHERE "
                    + "u.status = ? AND "
                    + "((u.deptId = ? OR u.deptRepresentId = ?) "
                    + " AND u.posId IN (SELECT p.posId FROM Position p"
                    + " WHERE p.posCode LIKE ? escape '!'"
                    + " or p.posCode LIKE ? escape '!'))"
                    + " order by u.posId asc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, deptId);
            query.setParameter(2, deptId);
            query.setParameter(3, Constants.POSITION.LEADER_OF_STAFF_T);
            query.setParameter(4, Constants.POSITION.GDTT);
            user = query.list();
            return user;
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    public boolean checkTruongPhong(Long userId) {
        boolean result = false;
        List<Users> user = new ArrayList<Users>();
        try {
            Query query = getSession().createQuery(
                    "SELECT u FROM Users u WHERE "
                    + "u.status = ? AND "
                    + "((u.userId = ?) "
                    + " AND u.posId IN (SELECT p.posId FROM Position p"
                    + " WHERE p.posCode LIKE ? escape '!'"
                    + " or p.posCode LIKE ? escape '!'))"
                    + " order by u.posId asc, nlssort(lower(u.fullName),'nls_sort = Vietnamese') ");
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, userId);
            query.setParameter(2, Constants.POSITION.LEADER_OF_STAFF_T);
            query.setParameter(3, Constants.POSITION.GDTT);
            user = query.list();
            if (user != null && user.size() > 0) {
                result = true;
            }
        } catch (Exception e) {
            log.error(e);
            return result;
        }
        return result;
    }
}
