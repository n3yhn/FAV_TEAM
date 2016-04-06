/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.hqmc.BO.MessageGroupPerson;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author GPCP_BINHNT53
 */
public class MessageGroupPersonDAOHE extends GenericDAOHibernate<MessageGroupPerson, Long> {

//========================================================================

    public MessageGroupPersonDAOHE() {
        super(MessageGroupPerson.class);
    }

    //get list MessageGroupPerson
    public List findAllByMessageGroupId(Long groupId) {
        String hql = "FROM MessageGroupPerson m WHERE m.messageGroupId =? and m.isActive=1";
        List lstParam = new ArrayList();
        lstParam.add(groupId);
        //Query countQuery = getSession().createQuery("select count(m) " + hql);
        Query query = getSession().createQuery("select m " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            //countQuery.setParameter(i, lstParam.get(i));
        }
        //query.setFirstResult(start);
        //query.setMaxResults(count);
        //int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List lstResult = query.list();
        return lstResult;
    }
}
