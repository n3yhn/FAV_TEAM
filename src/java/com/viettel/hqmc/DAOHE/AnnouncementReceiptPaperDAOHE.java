/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.LogUtil;
import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.AnnouncementReceiptPaper;
import com.viettel.hqmc.FORM.AnnouncementReceiptPaperForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author vtit_binhnt53
 */
public class AnnouncementReceiptPaperDAOHE extends GenericDAOHibernate<AnnouncementReceiptPaper, Long> {

    private static HashMap<String, List> lstFactory = new HashMap();

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        AnnouncementReceiptPaperDAOHE.lstFactory = lstFactory;
    }
    private static Logger log = Logger.getLogger(AnnouncementReceiptPaper.class);
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

    /**
     * Save BO
     *
     * @param arp
     * @throws Exception
     */
    public void saveDb(AnnouncementReceiptPaper arp) throws Exception {
        getSession().update(arp);
        getSession().getTransaction().commit();
    }

    /**
     * tim kiem ban cong bo hop quy
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findAnnouncementReceiptPaper(AnnouncementReceiptPaperForm form, int start, int count, String sortField) {
//        String hql = " from AnnouncementReceiptPaper t where 1 = 1 ";
        String hql = " from AnnouncementReceiptPaper t where t.isActive = 1 ";
        List lstParam = new ArrayList();

        if (form != null) {
            //code filter here
            if (form.getProductName() != null && form.getProductName().trim().length() > 0) {
                hql += " and lower(t.productName) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getProductName().trim()));
            }
            if (form.getReceiptNo() != null && form.getReceiptNo().trim().length() > 0) {
                hql += " and lower(t.receiptNo) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getReceiptNo().trim()));
            }
        }

        Query countQuery = getSession().createQuery("select count(t.receiptNo) " + hql);
        Query query = getSession().createQuery("select t " + hql + " order by t.receiptNo  desc");
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

    /**
     * tim kiem ban cong bo hop quy
     *
     * @return
     */
    public List findAllAnnouncementReceiptPaper() {
        if (lstFactory == null) {
            lstFactory = new HashMap();
        }

        if (lstFactory.containsKey("type")) {
            return lstFactory.get("type");
        }

        List<AnnouncementReceiptPaper> lstAnnouncementReceiptPaper = null;
//        List<CategorySearchForm> lstResult = new ArrayList();
        try {
            StringBuilder stringBuilder = new StringBuilder(" from AnnouncementReceiptPaper t ");
            stringBuilder.append("  where t.isActive = 1 "
                    + " order by nlssort(lower(ltrim(t.receiptNo)),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstAnnouncementReceiptPaper = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<AnnouncementReceiptPaper>();
        }
//        lstCategoryFactory.put(type, lstResult);
        lstFactory.put("type", lstAnnouncementReceiptPaper);

        return lstAnnouncementReceiptPaper;
    }

    //kiem tra doi tuong co trung khong
    /**
     * kiem tra trung
     *
     * @param form
     * @return
     */
    public boolean isDuplicate(AnnouncementReceiptPaperForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
//        String hql = "select count(t) from AnnouncementReceiptPaper t where 1 = 1 ";
        String hql = "select count(t) from AnnouncementReceiptPaper t where t.isActive = 1 ";
        if (form.getAnnouncementReceiptPaperId() != null && form.getAnnouncementReceiptPaperId() > 0l) {
            hql += " and t.announcementReceiptPaperId <> ? ";
            lstParam.add(form.getAnnouncementReceiptPaperId());
        }
        if (form.getReceiptNo() != null && form.getReceiptNo().trim().length() > 0) {
            hql += " and t.receiptNo = ? ";
            lstParam.add(form.getReceiptNo());
        }
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }

        Long count = Long.parseLong(query.uniqueResult().toString());
        boolean bReturn = false;
        if (count >= 1l) {
            bReturn = true;
        }
        return bReturn;
    }

    /**
     * validate so cong bo
     *
     * @param receiptNo
     * @return
     */
    public boolean validateReceiptNo(String receiptNo) {
        String[] arrString = receiptNo.split("/");
        if (arrString != null && arrString.length == 3) {
            if (arrString[1].length() == 4) {
                if (arrString[1].matches("[0-9]+")) {
                } else {
                    return false;
                }
            } else {
                return false;
            }
            if (arrString[2].length() == 9) {
                if (arrString[2].substring(5, 9).equals("TNCB")) {
                    if (arrString[2].charAt(4) == '-') {
                        if (arrString[2].substring(0, 4).equals("ATTP")) {
                            return true;
                        } else {
                            if (arrString[2].substring(0, 2).equals("YT")) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    /*
     *
     */

    public void formToBO(AnnouncementReceiptPaperForm stAddEditForm, AnnouncementReceiptPaper bo) {
    }

    public AnnouncementReceiptPaperForm boToForm(AnnouncementReceiptPaper bo) {
        AnnouncementReceiptPaperForm frm = new AnnouncementReceiptPaperForm(bo);
        return frm;
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

    public AnnouncementReceiptPaperDAOHE() {
        super(AnnouncementReceiptPaper.class);
    }

    /**
     * lay so cong bo tu ws
     *
     * @param bus1
     * @param annNo
     * @return
     */
    public AnnouncementReceiptPaper getAnnouncementReceiptPaperToWs(String bus1, String annNo) {
        List<AnnouncementReceiptPaper> lstItem = new ArrayList<AnnouncementReceiptPaper>();
        AnnouncementReceiptPaper item = null;
        try {
            if (bus1 != "" && annNo != "") {
                StringBuilder stringBuilder = new StringBuilder(" from AnnouncementReceiptPaper a ");
                stringBuilder.append("  where a.businessName=? and a.receiptNo =? ");
                Query query = getSession().createQuery(stringBuilder.toString());
                query.setParameter(0, bus1);
                query.setParameter(1, annNo);
                lstItem = query.list();
                if (lstItem != null && lstItem.size() > 0) {
                    item = lstItem.get(0);
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return item;
    }

    public AnnouncementReceiptPaper getAnnouncementReceiptPaperToWs(String annNo) {
        List<AnnouncementReceiptPaper> lstItem = new ArrayList<AnnouncementReceiptPaper>();
        AnnouncementReceiptPaper item = null;
        try {
            if (annNo != "") {
                StringBuilder stringBuilder = new StringBuilder(" from AnnouncementReceiptPaper a ");
                stringBuilder.append("  where a.receiptNo =? ");
                Query query = getSession().createQuery(stringBuilder.toString());
                query.setParameter(0, annNo);
                lstItem = query.list();
                if (lstItem != null && lstItem.size() > 0) {
                    item = lstItem.get(0);
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return item;
    }

    /**
     *
     *
     * @param annNo
     * @return
     */
    public AnnouncementReceiptPaper getARPToWsRAQ(String annNo) {
        List<AnnouncementReceiptPaper> lstItem = new ArrayList<AnnouncementReceiptPaper>();
        AnnouncementReceiptPaper item = null;
        try {
            if (annNo != "") {
                StringBuilder stringBuilder = new StringBuilder(" from AnnouncementReceiptPaper a ");
                stringBuilder.append(" where a.receiptNo =? ");
                Query query = getSession().createQuery(stringBuilder.toString());
                query.setParameter(0, annNo);
                lstItem = query.list();
                if (lstItem != null && lstItem.size() > 0) {
                    item = lstItem.get(0);
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return item;
    }
}
