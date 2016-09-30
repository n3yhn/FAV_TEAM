/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAOHibernate;

import com.viettel.common.util.StringUtils;
import com.viettel.voffice.client.form.CategorySearchForm;
import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.DAO.GridResult;
import java.util.List;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Administrator
 */
public class CategoryDAOHE extends GenericDAOHibernate<Category, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CategoryDAOHE.class);
    private static HashMap<String, List> lstCategoryFactory = new HashMap();

    public CategoryDAOHE() {
        super(Category.class);
    }

    public static void removeCacheCategory(String type) {
        if (lstCategoryFactory == null) {
            return;
        }
        if (lstCategoryFactory.containsKey(type)) {
            lstCategoryFactory.remove(type);
        }
    }

    public List findAllCategory(String type) {
        //        if (lstCategoryFactory == null) {
        //            lstCategoryFactory = new HashMap();
        //        }
        //
        //        if (lstCategoryFactory.containsKey(type)) {
        //            return lstCategoryFactory.get(type);
        //        }

        List<Category> lstCategory = null;
        try {
            if (type != null) {
                StringBuilder stringBuilder = new StringBuilder(" from Category a ");
                stringBuilder.append("  where a.isActive = ? and a.type=?"
                        + " order by nlssort(lower(ltrim(a.name)),'nls_sort = Vietnamese') ");
                Query query = getSession().createQuery(stringBuilder.toString());
                query.setParameter(0, "1");
                query.setParameter(1, type);
                lstCategory = query.list();
            }

        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<Category>();
        }
        lstCategoryFactory.put(type, lstCategory);

        return lstCategory;
    }

    //hieptq update 150415
    public List findAllCategoryByFee(String type, Long typeFee) {
        List<Category> lstCategory = null;
        try {
            if (type != null) {
                StringBuilder stringBuilder = new StringBuilder(" from Category a ");
                stringBuilder.append("  where a.isActive = ? and a.type=? ");
                if (typeFee == 1) {
                    stringBuilder.append(" and (a.code = 'DBT' or a.code = 'TPCN' or a.code = 'TPDB') ");
                }
                if (typeFee == 0) {
                    stringBuilder.append(" and (a.code <> 'DBT' and a.code <> 'TPCN' and a.code <> 'TPDB') ");
                }
                if (typeFee == 7) {
                    stringBuilder.append(" and (a.code = 'TL') ");
                }
                stringBuilder.append(" order by nlssort(lower(ltrim(a.name)),'nls_sort = Vietnamese') ");
                Query query = getSession().createQuery(stringBuilder.toString());
                query.setParameter(0, "1");
                query.setParameter(1, type);
                lstCategory = query.list();
            }

        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<Category>();
        }
        lstCategoryFactory.put(type, lstCategory);

        return lstCategory;
    }

    //hieptq update 170415
    public Long findTypeFee(Long fileType) {
        List<Procedure> lstProcedure = null;
        try {
            if (fileType != null) {
                StringBuilder stringBuilder = new StringBuilder(" from Procedure p ");
                stringBuilder.append("  where p.isActive = ? and p.procedureId = ? ");
                Query query = getSession().createQuery(stringBuilder.toString());
                query.setParameter(0, "1");
                query.setParameter(1, fileType);
                lstProcedure = query.list();
            }

        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return -1l;
        }
        return lstProcedure.get(0).getTypeFee();
    }

    public List findAllPosition(String type) {
        List<Category> lstPosition = null;
        try {
            if (type != null) {
                StringBuilder stringBuilder = new StringBuilder("select a from Position a ");
                stringBuilder.append("  where a.status = 1 AND a.posType = 1");
                Query query = getSession().createQuery(stringBuilder.toString());
                lstPosition = query.list();
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<Category>();
        }
//        lstCategoryFactory.put(type, lstResult);
        //lstCategoryFactory.put(type, lstPosition);

        return lstPosition;
    }

    public List findAllPositionDn() {
        List<Category> lstPosition = null;
        try {
            StringBuilder stringBuilder = new StringBuilder("select a from Position a ");
            stringBuilder.append("  where a.status = 1 and a.posType = 2 order by nlssort(lower(ltrim(a.posName)),'nls_sort = Vietnamese')");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstPosition = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<Category>();
        }
//        lstCategoryFactory.put(type, lstResult);
        //lstCategoryFactory.put(type, lstPosition);

        return lstPosition;
    }

    public List findAllPositionDnByPosName(String posName) {
        List<Long> lstPosition = null;
        try {
            if ("".equals(posName) || "".equals(posName.trim())) {
                return lstPosition;
            }
            StringBuilder stringBuilder = new StringBuilder("select a.posId from Position a ");
            stringBuilder.append("  where a.status = 1 and a.posType = 2 and lower(a.posName) like ?");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, posName.trim().toLowerCase());
            lstPosition = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<Long>();
        }
        return lstPosition;
    }

    public List<Category> findAllCategoryOrderByCode(String type) {
        List<Category> lstCategory = new ArrayList();
        try {
            if (type != null) {
                StringBuilder stringBuilder = new StringBuilder(" from Category a ");
                stringBuilder.append("  where a.isActive = ? and a.type=?"
                        + " order by a.code DESC");
                Query query = getSession().createQuery(stringBuilder.toString());
                query.setParameter(0, "1");
                query.setParameter(1, type);
                lstCategory = query.list();

                if (lstCategory != null && lstCategory.size() > 0) {
                    for (int i = 0; i < lstCategory.size(); i++) {
                        String name = lstCategory.get(i).getName();
                        name = StringUtils.escapeHtml(name);
                        if (name.length() > 255) {
                            name = name.substring(0, 254);
                        }
                        lstCategory.get(i).setName(name);
                    }
                }
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<Category>();
        }
        return lstCategory;
    }

    public Category findCategoryByName(String type, String searchName) {
        List<Category> lstCategory = new ArrayList<Category>();
        Category item = null;
        try {
            if (type != null) {
                StringBuilder stringBuilder = new StringBuilder(" from Category a ");
                //stringBuilder.append("  where a.isActive = ? and a.type=? and lower(a.name) LIKE ? ESCAPE '/' ");
                stringBuilder.append("  where a.isActive = ? and a.type=? and a.name = ? ");
                Query query = getSession().createQuery(stringBuilder.toString());
                query.setParameter(0, "1");
                query.setParameter(1, type);
//                query.setParameter(2, StringUtils.toLikeString(searchName.trim().toLowerCase()));
                query.setParameter(2, searchName.trim());
                lstCategory = query.list();

                if (lstCategory != null && lstCategory.size() > 0) {
                    item = lstCategory.get(0);
                }
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return item;
    }

    public List<Category> findAllCategorySearch(String type) {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Category a ");
            stringBuilder.append("  where a.isActive = ? and a.type=? ");
            if (("VOBQ").equals(type)) {
                stringBuilder.append(" order by a.code");
            } else {
                stringBuilder.append(" order by nlssort(lower(ltrim(a.name)),'nls_sort = Vietnamese')");
            }

            //Thoi han bao quan xep theo code
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, "1");
            query.setParameter(1, type);
            List<Category> lstCategory = query.list();
            Category a = new Category();
            a.setCategoryId(Constants.COMBOBOX_HEADER_VALUE);
            a.setName(Constants.COMBOBOX_HEADER_TEXT);
            List<Category> lstCategoryFull = new ArrayList();
            lstCategoryFull.add(a);
            lstCategoryFull.addAll(lstCategory);
            return lstCategoryFull;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    //Tim kiem Category theo don vi tao(DM kho luu tru)
    public List<Category> findAllCategorySearch1(String type, Long deptId) {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Category a ");
            stringBuilder.append("  where a.isActive = ? and a.type=? ");
            if (("VOLT").equals(type)) {
                stringBuilder.append(" and a.createdBy = ? ");
            }
            stringBuilder.append(" order by nlssort(lower(ltrim(a.name)),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, type);
            if (("VOLT").equals(type) && deptId != null) {
                query.setParameter(2, deptId);
            }

            List<Category> lstCategory = query.list();
            Category a = new Category();
            a.setCategoryId(Constants.COMBOBOX_HEADER_VALUE);
            a.setName(Constants.COMBOBOX_HEADER_TEXT);
            List<Category> lstCategoryFull = new ArrayList();
            lstCategoryFull.add(a);
            lstCategoryFull.addAll(lstCategory);
            return lstCategoryFull;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    /**
     * Tim kiem Loai danh muc
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public List<Category> findCategory(CategorySearchForm form, int start, AtomicInteger count, String sortField) {
        List paramList = new ArrayList();
        try {
            StringBuilder strBuf = new StringBuilder(" from Category a where a.isActive <> -1 ");//binhnt update active = 1;deactive = 0;
            if (form != null) {
                if (form.getType() != null && !form.getType().equals("")) {
                    strBuf.append(" and a.type= ? ");
                    paramList.add(form.getType());
                    //Neu la kho luu tru thi tim theo don vi tao Kho luu tru
                    if (form.getType().equals("VOLT")) {
                        strBuf.append(" and a.createdBy = ? ");
                        paramList.add(form.getCreatedBy());
                    }
                    //Neu la kho luu tru thi tim theo don vi tao Kho luu tru
                    if (form.getType().equals("DVI") && form.getCreatedBy() != null && form.getCreatedBy() > 0) {
                        strBuf.append(" and a.createdBy = ? ");
                        paramList.add(form.getCreatedBy());
                    }
                }

                if (form.getCode() != null && !form.getCode().equals("")) {
                    strBuf.append(" and lower(a.code) LIKE ? ESCAPE '/' ");
                    paramList.add(StringUtils.toLikeString(form.getCode().trim().toLowerCase()));
                }

                if (form.getName() != null && !form.getName().equals("")) {
                    strBuf.append(" and lower(a.name) LIKE ? ESCAPE '/' ");
                    paramList.add(StringUtils.toLikeString(form.getName().trim().toLowerCase()));
                }
                if (form.getIsActive() != null && !form.getIsActive().equals("") && !form.getIsActive().equals("-1")) {
                    strBuf.append(" and a.isActive = ? ");
                    paramList.add(form.getIsActive().trim());
                }
            }

            Query query = getSession().createQuery("SELECT count(*) " + strBuf.toString());
            for (int i = 0; i < paramList.size(); i++) {
                query.setParameter(i, paramList.get(i));
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
                strBuf.append(" order by a.").append(validateColumnName(sortField)).append(" ").append(sortType);
            } else {
                strBuf.append("  order by nlssort(lower(a.name),'nls_sort = Vietnamese') ");
            }

            query = getSession().createQuery(strBuf.toString());
            for (int i = 0; i < paramList.size(); i++) {
                query.setParameter(i, paramList.get(i));
            }

            query.setFirstResult(start);
            query.setMaxResults(count.intValue());

            List<Category> lstCategory = query.list();
            count.set(total);
            return lstCategory;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    public Category checkBO(Category bo) {
        try {
            StringBuilder strBuf = new StringBuilder(" from Category a ");
            strBuf.append(" where a.isActive = :isActive ");

            if (bo.getCategoryId() != null) {
                strBuf.append(" and a.categoryId != :categoryId ");
            }
            if (bo.getType() != null) {
                strBuf.append(" and a.type = :type ");
            }
            if (bo.getCreatedBy() != null) {
                strBuf.append(" and a.createdBy = :createdBy ");
            }

            if (bo.getCode() != null && bo.getName() != null) {
                strBuf.append(" and (a.code = :code  or a.name = :name)");
            }

            Query query = getSession().createQuery(strBuf.toString());
            query.setParameter("isActive", "1");

            if (bo.getCategoryId() != null) {
                query.setParameter("categoryId", bo.getCategoryId());
            }
            if (bo.getType() != null) {
                query.setParameter("type", bo.getType().trim());
            }
            if (bo.getCreatedBy() != null) {
                query.setParameter("createdBy", bo.getCreatedBy());
            }
            if (bo.getCode() != null && bo.getName() != null) {
                query.setParameter("code", bo.getCode().trim());
                query.setParameter("name", bo.getName().trim());
            }

            List<Category> lstCategory = query.list();
            if (!lstCategory.isEmpty()) {
                return lstCategory.get(0);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        return null;
    }

    public void formToBO(CategorySearchForm categoryAddEditForm, Category bo) {
        if (categoryAddEditForm.getType() != null) {
            bo.setType(categoryAddEditForm.getType().trim());
        }

        if (categoryAddEditForm.getCode() != null) {
            bo.setCode(categoryAddEditForm.getCode().trim());
        }

        if (categoryAddEditForm.getName() != null) {
            bo.setName(categoryAddEditForm.getName().trim());
        }
        if (categoryAddEditForm.getDescription() != null) {
            bo.setDescription(categoryAddEditForm.getDescription());
        }
        if (categoryAddEditForm.getIsActive() != null) {
            bo.setIsActive(categoryAddEditForm.getIsActive());
        }
    }

    public CategorySearchForm boToForm(Category bo) {
        CategorySearchForm form = new CategorySearchForm();
        if (bo.getType() != null) {
            form.setType(bo.getType().trim());
        }

        if (bo.getCode() != null) {
            form.setCode(bo.getCode().trim());
        }

        if (bo.getName() != null) {
            form.setName(bo.getName().trim());
        }
        form.setCategoryId(bo.getCategoryId());
        return form;
    }

    public Category getCategoryById(long id) throws Exception {
        Criteria cri = getSession().createCriteria(Category.class);
        cri.add(Restrictions.eq("categoryId", id));
        List<Category> cats = cri.list();
        if (cats.isEmpty()) {
            return null;
        } else {
            return cats.get(0);
        }
    }

    public String getNameById(Long categoryId) {
        Category category = new Category();
        try {
            StringBuilder strBuf = new StringBuilder(" from Category a ");
            strBuf.append(" where a.isActive = ? AND a.categoryId=?  ");

            Query query = getSession().createQuery(strBuf.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, categoryId);
            category = (Category) query.uniqueResult();
            if (category != null) {
                return category.getName();
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return "";
        }
        return "";
    }

    public List<Category> findByCategoryName(String categoryName) {
        List<Category> category = new ArrayList<Category>();
        try {
            StringBuilder strBuf = new StringBuilder(" from Category a ");
            strBuf.append(" where a.isActive = ? AND lower(a.name) = ? ");

            Query query = getSession().createQuery(strBuf.toString());
            query.setParameter(0, Constants.Status.ACTIVE);
            query.setParameter(1, categoryName.toLowerCase());
            category = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return category;
    }

    //
    public boolean isDuplicate(CategorySearchForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(c) from Category c where c.isActive = 1 ";
        if (form.getCategoryId() != null && form.getCategoryId() > 0l) {
            hql += " and c.categoryId <> ? ";
            lstParam.add(form.getCategoryId());
        }
        if (form.getCode() != null && form.getCode().trim().length() > 0) {
            hql += " and c.code = ? ";
            lstParam.add(form.getCode());
        }
        if (form.getName() != null && form.getName().trim().length() > 0) {
            hql += " and lower(c.name) = ?";
            lstParam.add(form.getName().toLowerCase());
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
    /*
     *
     */

    public GridResult findCategory(CategorySearchForm form, int start, int count, String sortField) {
        String hql = " from Category c where 1 = 1 ";
        List lstParam = new ArrayList();
        if (form != null) {
            if (form.getName() != null && form.getName().trim().length() > 0) {
                hql += " and lower(c.name) like ? escape'!'";
                lstParam.add("%" + convertToLikeString(form.getName()) + "%");
            }
            if (form.getCode() != null && form.getCode().trim().length() > 0) {
                hql += " and lower(c.code) like ? escape'!'";
                lstParam.add("%" + convertToLikeString(form.getCode()) + "%");
            }
            if (form.getType() != null && form.getType().trim().length() > 0 && form.getType().equals("0") != true) {
                hql += " and lower(c.type) like ? escape'!'";
                lstParam.add("%" + convertToLikeString(form.getType()) + "%");
            }
            if (form.getIsActive() != null && form.getIsActive().trim().length() > 0 && form.getIsActive().equals("-1") != true) {
                hql += " and lower(c.isActive) like ? escape'!'";
                lstParam.add("%" + convertToLikeString(form.getIsActive()) + "%");
            }
        }

        Query countQuery = getSession().createQuery("select count(*) " + hql);
        Query query = getSession().createQuery("select c " + hql + " order by c.categoryId desc");
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

    public Category findCategoryByTypeAndCode(String type, String code) {
        try {
            Query query = getSession().createQuery("select a from Category a where a.isActive = ? and a.type=? and a.code=? order by a.code");
            query.setParameter(0, "1");
            query.setParameter(1, type);
            query.setParameter(2, code);
            List<Category> lstCategory = query.list();
            return lstCategory.get(0);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    public List<Category> findCategoryByTypeAndCodeNew(String type, String code) {
        try {
            Query query = getSession().createQuery("select a from Category a where a.isActive = ? and a.type=? and a.code=? order by a.code");
            query.setParameter(0, "1");
            query.setParameter(1, type);
            query.setParameter(2, code);
            List<Category> lstCategory = query.list();
            return lstCategory;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }
    /*
     public void setGridList(String lstId, String lstName, List<Category> lstInfoType, HttpServletRequest req) {
     try {
     String id = "";
     String name = "";
     if (lstInfoType.size() > 0) {
     int first = 0;
     if (lstInfoType.get(0).getCategoryId() == Constants.COMBOBOX_HEADER_VALUE) {
     id = lstInfoType.get(0).getCategoryId() + ",";
     name = "'',";
     first = 1;
     }
     Category ctg;
     for (int i = first; i < lstInfoType.size(); i++) {
     ctg = lstInfoType.get(i);
     id = id + ctg.getCategoryId() + ",";
     name = name + "'" + ctg.getName() + "',";
     }
     if (id.length() > 1 && name.length() > 2) {
     id = id.substring(0, id.length() - 1);
     name = name.substring(0, name.length() - 1);
     }
     id = "[" + id + "]";
     name = "[" + name + "]";
     req.setAttribute(lstId, id);
     req.setAttribute(lstName, name);
     }
     } catch (Exception ex) {
     log.error(ex.getMessage());
     }

     }
     */

    /*
     public Category findQuocTichVN() {
     try {
     StringBuilder strBuf = new StringBuilder(" from Category a ");
     strBuf.append(" where a.isActive = :isActive  ");
     strBuf.append(" and a.type= :type ");
     strBuf.append(" and a.code= :code ");

     Query query = getSession().createQuery(strBuf.toString());
     query.setParameter("isActive", Constants.Status.ACTIVE);
     query.setParameter("type", Constants.CATEGORY_TYPE.QUOC_TICH);
     query.setParameter("code", Constants.NATIONALITY_CODE.VIETNAM);

     List<Category> lstCategory = query.list();
     if (!lstCategory.isEmpty()) {
     return lstCategory.get(0);
     }
     } catch (Exception ex) {
     String msg = ex.getMessage();
     }

     return new Category();
     }

     public Category findQuocTichKhongCoThongTin() {
     try {
     StringBuilder strBuf = new StringBuilder(" from Category a ");
     strBuf.append(" where a.isActive = :isActive  ");
     strBuf.append(" and a.type= :type ");
     strBuf.append(" and a.code= :code ");

     Query query = getSession().createQuery(strBuf.toString());
     query.setParameter("isActive", Constants.Status.ACTIVE);
     query.setParameter("type", Constants.CATEGORY_TYPE.QUOC_TICH);
     query.setParameter("code", Constants.NATIONALITY_CODE.KHONGCOTHONGTIN);

     List<Category> lstCategory = query.list();
     if (!lstCategory.isEmpty()) {
     return lstCategory.get(0);
     }
     } catch (Exception ex) {
     String msg = ex.getMessage();
     }

     return new Category();
     }
     */
    /*
     public List<Category> findCategoryOrderByCode(String type) {
     try {
     Query query = getSession().createQuery("select a from Category a where a.isActive = ? and a.type=? order by a.code");
     query.setParameter(0, Constants.Status.ACTIVE);
     query.setParameter(1, type);
     //        Query query = getSession().createQuery("from Category a where 1=1");
     List<Category> lstCategory = query.list();
     Category a = new Category();
     a.setCategoryId(Constants.COMBOBOX_HEADER_VALUE);
     a.setName(Constants.COMBOBOX_HEADER_SEARCH);
     List<Category> lstCategoryFull = new ArrayList();
     lstCategoryFull.add(a);
     lstCategoryFull.addAll(lstCategory);
     return lstCategoryFull;
     } catch (Exception ex) {
     String msg = ex.getMessage();
     return null;
     }
     }
     */
    /**
     * Kiem tra xem co quan trong bang danh muc co phai la TTLLTP hay khong
     *
     * @param agencyId
     * @return
     */
    /*
     public boolean checkIsCenter(Long agencyId) {
     Query query = getSession().createQuery("SELECT a FROM Category a WHERE a.categoryId = ?");
     query.setParameter(0, agencyId);

     try {

     Category cat = (Category) query.uniqueResult();
     if (cat != null && "1".equals(cat.getCode()) && "CQ".equals(cat.getType())) {
     return true;
     }
     } catch (Exception ex) {
     }
     return false;
     }
     */
}
