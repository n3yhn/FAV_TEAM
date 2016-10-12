/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.email.DAOHE;

import com.viettel.common.util.LogUtil;
import com.viettel.email.form.EmailForm;
import com.viettel.voffice.common.util.StringUtils;
import com.viettel.voffice.database.BO.Email;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;

/**
 *
 * @author dungnt78
 */
public class EmailDAOHE extends GenericDAOHibernate<Email, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(EmailDAOHE.class);

    public EmailDAOHE() {
        super(Email.class);
    }

    public List<Email> findEmailByUserId(long UserId, String folderName) {
        List<Email> listEmail = new ArrayList<Email>();
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Email n ");
            stringBuilder.append("  where n.userId = ? and n.folder = ? ORDER BY n.sentDate DESC");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, UserId);
            query.setParameter(1, folderName);
            listEmail = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return listEmail;
    }

    public List<String> findEmailByUserId(long UserId) {
        List<String> listEmail = new ArrayList<String>();
        try {
            StringBuilder stringBuilder = new StringBuilder(" select distinct(sender) from Email n ");
            stringBuilder.append("  where n.userId = ?  ");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, UserId);
            listEmail = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return listEmail;
    }

    public String convertToJsonArray(List<Email> array, String id, String searchAttr1, String searchAttr2) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (Email o : array) {
                Class cls = o.getClass();
                String getMethodName;
                Method getMethod;
                JSONObject jsonObj = new JSONObject();

                getMethodName = "get" + UpcaseFirst(id);
                getMethod = cls.getMethod(getMethodName);
                String idValue = getMethod.invoke(o).toString();
                jsonObj.put("id", idValue);

                getMethodName = "get" + UpcaseFirst(searchAttr1);
                getMethod = cls.getMethod(getMethodName);
                String search1 = getMethod.invoke(o).toString();
                jsonObj.put("search1", StringUtils.escapeHTML(search1));

                getMethodName = "get" + UpcaseFirst(searchAttr2);
                getMethod = cls.getMethod(getMethodName);

                String search2 = "";
                if (getMethod.invoke(o) != null) {
                    search2 = getMethod.invoke(o).toString();
                }
                jsonObj.put("search2", StringUtils.escapeHTML(search2));

                jsonArray.add(jsonObj);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return jsonArray.toString();
    }

    public List<String> getAllUIDByUserID(long UserId, String folderName) {
        List<String> result = new ArrayList<String>();
        try {
            StringBuilder stringBuilder = new StringBuilder(" select emailUid from Email n ");
            stringBuilder.append("  where n.userId = ? and n.folder = ? ");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, UserId);
            query.setParameter(1, folderName);
            result = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return result;
    }

    public void deleteByUid(long UserId, String UID, String folderName) {
        List<Email> listEmail = new ArrayList<Email>();
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Email n ");

            stringBuilder.append("  where n.userId = ? and n.emailUid = ? and  n.folder = ?");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, UserId);
            query.setParameter(1, UID);
            query.setParameter(2, folderName);
            listEmail = query.list();
            Email email = listEmail.get(0);
            getSession().delete(email);

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
    }

    public long insert(Email email) {
        long id = create(email);
        getSession().flush();

        return id;
    }

    public GridResult search(EmailForm emailformsearch, Long userId) {
        List<Email> email = new ArrayList<Email>();
        GridResult gridResult = new GridResult();
        try {
            StringBuilder sql = new StringBuilder(" from Email a ");
            if (emailformsearch.getFromDate() == null && emailformsearch.getToDate() == null) {
                if (!"".equals(emailformsearch.getSubject())) {
                    sql.append(" where a.folder like :folder ");
                    sql.append("and a.userId like :userId");
                    sql.append(" and (lower(a.subject) like lower(:subject) or lower(a.content) like lower(:content) )");
                    sql.append(" ORDER BY a.sentDate DESC");

                    Query query = getSession().createQuery(sql.toString());
                    query.setParameter("folder", emailformsearch.getFolder());
                    query.setParameter("userId", userId);
                    query.setParameter("subject", "%" + emailformsearch.getSubject() + "%");
                    query.setParameter("content", "%" + emailformsearch.getSubject() + "%");
                    email = query.list();
                } else {
                    sql.append(" where a.folder like :folder ");
                    sql.append("and a.userId like :userId");
                    sql.append(" ORDER BY a.sentDate DESC");
                    Query query = getSession().createQuery(sql.toString());
                    query.setParameter("folder", emailformsearch.getFolder());
                    query.setParameter("userId", userId);
                    email = query.list();
                }
            } else {
                if (emailformsearch.getFromDate() != null) {
                    sql.append(" where trunc(a.sentDate) >= :sentdate1 ");
                }
                if (emailformsearch.getToDate() != null) {
                    sql.append(" and trunc(a.sentDate) <= :sentdate2 ");
                }
                sql.append(" and a.folder like :folder ");
                sql.append("and a.userId like :userId");
                if (!"".equals(emailformsearch.getSubject())) {
                    sql.append(" and ( lower(a.subject) like lower(:subject) or  lower(a.content) like lower(:content)  )");
                }
                sql.append(" ORDER BY a.sentDate DESC");
                Query query = getSession().createQuery(sql.toString());
                query.setParameter("folder", emailformsearch.getFolder());
                query.setParameter("userId", userId);
                if (!"".equals(emailformsearch.getSubject())) {
                    query.setParameter("subject", "%" + emailformsearch.getSubject() + "%");
                    query.setParameter("content", "%" + emailformsearch.getContent() + "%");
                }
                if (emailformsearch.getFromDate() != null) {
                    query.setParameter("sentdate1", emailformsearch.getFromDate());
                }
                if (emailformsearch.getToDate() != null) {
                    query.setParameter("sentdate2", emailformsearch.getToDate());
                }
                email = query.list();
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
        gridResult.setLstResult(email);
        return gridResult;
    }
}
