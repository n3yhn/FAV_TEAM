/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.vsaadmin.database.DAOHibernate;
import com.viettel.voffice.client.form.EventLogLoginForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import com.viettel.vsaadmin.database.BO.EventLogLogin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author vtit_binhnt53
 */
public class EventLogLoginDAOHE extends GenericDAOHibernate<EventLogLogin, Long>{
    private static HashMap<String, List> lstFactory = new HashMap();

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        EventLogLoginDAOHE.lstFactory = lstFactory;
    }
    private static Logger log = Logger.getLogger(EventLogLogin.class);
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
    /*
     *
     */

    public GridResult findEventLogLogin(EventLogLoginForm form, int start, int count, String sortField) {
        String hql = " from EventLogLogin t where 1 = 1 ";
        List lstParam = new ArrayList();
        if (form != null) {
            if (form.getUserName()!= null && form.getUserName().trim().length() > 0) {
                hql += " and lower(t.userName) like ? escape'!'";
                lstParam.add("%" + convertToLikeString(form.getUserName()) + "%");
            }
            if (form.getEventDateFrom() != null && form.getEventDateFrom().toString().length() > 0) {
                hql += " and t.eventDate >= ?";
                lstParam.add(form.getEventDateFrom());
            }
            if (form.getEventDateTo()!= null && form.getEventDateTo().toString().length() > 0) {
                hql += " and t.eventDate <= ?";
                lstParam.add(form.getEventDateTo());
            }
        }

        Query countQuery = getSession().createQuery("select count(t) " + hql);
        Query query = getSession().createQuery("select t " + hql + " order by t.eventId desc");
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            countQuery.setParameter(i, lstParam.get(i));
        }

        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List lstResult = query.list();
        GridResult gr = new GridResult(total, lstResult);
        return gr;
    }
    /*
     * 
     */

    public List findAllEventLogLogin() {
        if (lstFactory == null) {
            lstFactory = new HashMap();
        }

        if (lstFactory.containsKey("type")) {
            return lstFactory.get("type");
        }

        List<EventLogLogin> lstEventLogLogin = null;
//        List<CategorySearchForm> lstResult = new ArrayList();
        try {
            StringBuilder stringBuilder = new StringBuilder(" from EventLogLogin t ");
            stringBuilder.append("  where 1 = 1 "
                    + " order by nlssort(lower(ltrim(t.userName)),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstEventLogLogin = query.list();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ArrayList<EventLogLogin>();
        }
//        lstCategoryFactory.put(type, lstResult);
        lstFactory.put("type", lstEventLogLogin);

        return lstEventLogLogin;
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

    public EventLogLoginDAOHE() {
        super(EventLogLogin.class);
    }
}
