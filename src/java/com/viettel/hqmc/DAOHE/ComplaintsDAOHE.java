/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.hqmc.BO.Complaints;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class ComplaintsDAOHE extends GenericDAOHibernate<Complaints, Long> {

    private static HashMap<String, List> lstFactory = new HashMap();

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        ComplaintsDAOHE.lstFactory = lstFactory;
    }
    private static Logger log = Logger.getLogger(Complaints.class);
    private List keyList = new ArrayList();
    private List valueList = new ArrayList();
    //removeCache

    public static void removeCache(String type) {
        if (lstFactory == null) {
            return;
        }
        if (lstFactory.containsKey(type)) {
            lstFactory.remove(type);
        }
    }

    public ComplaintsDAOHE() {
        super(Complaints.class);
    }

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        ComplaintsDAOHE.log = log;
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

    /**
     * save don khieu nai
     *
     * @param complaints
     * @return
     */
    public void saveComplaints(Complaints complaints) {
        getSession().save(complaints);
        System.out.println(complaints.getComplaintsId());
        getSession().getTransaction().commit();
    }
}
