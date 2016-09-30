/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.BO.Repositories;
import com.viettel.hqmc.FORM.RepositoriesForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.List;
import org.hibernate.Query;
import java.util.ArrayList;
import java.util.HashMap;
import com.viettel.common.util.LogUtil;

/**
 *
 * @author Administrator
 */
public class RepositoryDAOHE extends GenericDAOHibernate<Repositories, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RepositoryDAOHE.class);
    private static HashMap<String, List> lstRepositoriesFactory = new HashMap();

    public RepositoryDAOHE() {
        super(Repositories.class);
    }

    /**
     * removeCacheRepositories
     *
     * @param type
     * @return
     */
    public static void removeCacheRepositories(String type) {
        if (lstRepositoriesFactory == null) {
            return;
        }
        if (lstRepositoriesFactory.containsKey(type)) {
            lstRepositoriesFactory.remove(type);
        }
    }

    /**
     * findAllRepositories
     *
     * @return
     */
    public List findAllRepositories() {
        if (lstRepositoriesFactory == null) {
            lstRepositoriesFactory = new HashMap();
        }

        List<Repositories> lstRepositories = null;

        try {

            StringBuilder stringBuilder = new StringBuilder(" from Repositories a ");
            stringBuilder.append(" where a.isActive = 1 order by a.repId asc ");
            Query query = getSession().createQuery(stringBuilder.toString());

            lstRepositories = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<Repositories>();
        }
        return lstRepositories;
    }

    /**
     * getRepositoryFromId
     *
     * @param rep_id
     * @return
     */
    public List getRepositoryFromId(Long rep_id) {
        if (lstRepositoriesFactory == null) {
            lstRepositoriesFactory = new HashMap();
        }

        List<Repositories> lstRepositories = null;

        try {

            StringBuilder stringBuilder = new StringBuilder(" from Repositories a ");
            stringBuilder.append("  where a.isActive = 1 and a.repId = " + rep_id + " ");
            Query query = getSession().createQuery(stringBuilder.toString());

            lstRepositories = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<Repositories>();
        }
        return lstRepositories;
    }

    /**
     * getRepositoryFromCreator
     *
     * @param user_id
     * @return
     */
    public List getRepositoryFromCreator(Long user_id) {
        if (lstRepositoriesFactory == null) {
            lstRepositoriesFactory = new HashMap();
        }

        List<Repositories> lstRepositories = null;

        try {

            StringBuilder stringBuilder = new StringBuilder(" from Repositories a ");
            stringBuilder.append(" where a.isActive = 1 and a.repCreator = " + user_id + "  order by a.repId asc ");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstRepositories = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<Repositories>();
        }
        return lstRepositories;
    }

    /**
     * findAllRepositoriesById
     *
     * @param repForm
     * @param depId
     * @param userId
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findAllRepositoriesById(RepositoriesForm repForm, Long depId, Long userId, int start, int count, String sortField) {

        if (lstRepositoriesFactory == null) {
            lstRepositoriesFactory = new HashMap();
        }
//        List<Repositories> lstRepositories = null;
        StringBuilder stringBuilder = new StringBuilder(" from Repositories a ");
        stringBuilder.append(" where a.isActive = 1 and a.repCreator = " + userId + "  order by a.repId asc ");
        Query countQuery = getSession().createQuery("select count(a)" + stringBuilder.toString());
        Query query = getSession().createQuery(stringBuilder.toString());
//        lstRepositories = query.list();
        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List<Files> lstResult = query.list();
        GridResult gr = new GridResult(total, lstResult);
        return gr;
    }

    /**
     * onCreateRepositories
     *
     * @param repoForm
     * @return
     */
    public boolean onCreateRepositories(RepositoriesForm repoForm, Long userId) {//lol
        boolean bReturn = true;
        try {
            //form.setStatus(Constants.FILE_STATUS.RECEIVED);
            Repositories repo = new Repositories();
            Long stt = repoForm.getIsActive();
            if (repoForm.getRepId() == null) {
                repo.setIsActive(stt);
                repo.setRepName(repoForm.getRepName());
                repo.setRepAddress(repoForm.getRepAddress());
                repo.setRepCreator(userId);
                getSession().save(repo);
                bReturn = true;
            } else {
                repo = findById(repoForm.getRepId());
                repo.setRepId(repo.getRepId());
                repo.setIsActive(stt);
                repo.setRepName(repoForm.getRepName());
                repo.setRepAddress(repoForm.getRepAddress());
                repo.setRepCreator(userId);
                getSession().update(repo);
                bReturn = true;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            bReturn = false;
        }
        return bReturn;
    }

    /**
     * deleteRepo
     *
     * @param userId
     * @param repId
     * @return
     */
    public int deleteRepo(Long userId, Long repId) {
        int iReturn = 0;
        Repositories repo = findById(repId);
        StringBuilder stringBuilder = new StringBuilder(" from Files f ");
        stringBuilder.append(" where f.isActive = 1 and f.repositoriesId =  " + repId);
        Query countQuery = getSession().createQuery("select count(f)" + stringBuilder.toString());
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        if (total == 0) {
            if (repo != null) {
                repo.setIsActive(0l);
                getSession().update(repo);
                iReturn = 1;
            }
        } else {
            iReturn = 2;
        }
        return iReturn;
    }

}
