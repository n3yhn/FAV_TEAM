/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.hqmc.BO.MessageGroup;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author GPCP_BINHNT53
 */
public class MessageGroupDAOHE extends GenericDAOHibernate<MessageGroup, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MessageGroupDAOHE.class);
//========================================================================

    public MessageGroupDAOHE() {
        super(MessageGroup.class);
    }

    public MessageGroup returnMessageGroupById(String code) {
        String hql = "FROM MessageGroup m WHERE m.messsageGroupCode =? and m.isActive=1";
        List lstParam = new ArrayList();
        lstParam.add(code);
        //Query countQuery = getSession().createQuery("select count(m) " + hql);
        Query query = getSession().createQuery("select m " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            //countQuery.setParameter(i, lstParam.get(i));
        }
        //query.setFirstResult(start);
        //query.setMaxResults(count);
        //int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List<MessageGroup> lstResult = query.list();
        if (lstResult.size() > 0) {
            return lstResult.get(0);
        }
        return null;
    }
    
    //binhnt53 140915
    public MessageGroup returnMessageGroupByCodeDeptId(String code, Long deptId) {
        String hql = "FROM MessageGroup m WHERE m.messsageGroupCode=? and m.deptId=? and m.isActive=1";
        List lstParam = new ArrayList();
        lstParam.add(code);
        lstParam.add(deptId);
        //Query countQuery = getSession().createQuery("select count(m) " + hql);
        Query query = getSession().createQuery("select m " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            //countQuery.setParameter(i, lstParam.get(i));
        }
        //query.setFirstResult(start);
        //query.setMaxResults(count);
        //int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List<MessageGroup> lstResult = query.list();
        if (lstResult.size() > 0) {
            return lstResult.get(0);
        }
        return null;
    }
}
