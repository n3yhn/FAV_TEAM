/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAOHibernate;

import com.viettel.database.DAO.GenericHibernateDAO;
import com.viettel.voffice.common.util.DateTimeUtils;
import com.viettel.voffice.common.util.StringUtils;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

/**
 * GenericDAOHibernate
 *
 * @author GPDN_NgocTM3
 */
public class GenericDAOHibernate<T, ID extends Serializable> extends GenericHibernateDAO<T, ID> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(GenericHibernateDAO.class);
    private Class<T> type;
    protected Session session = getSession();
    private Long lastId;
    String a = "aàáảạăằắẳẳâầấẩậ";
    String e = "eèéẻẹêềếểệ";
    String i = "iìíỉị";
    String o = "oòóỏọôồốổộơờớởợ";
    String u = "uùúủụưừứửự";
    String d = "dđ";
    Character[] aZ = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'u', 'x', 'y', 'z', '-', '_',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public Long getLastId() {
        return lastId;
    }

    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }

    public Date addOneDay(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
        return c.getTime();
    }

    public Date maxDayToCompare(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return DateTimeUtils.convertStringToDateTime(DateTimeUtils.convertDateTimeToString(date) + " 23:59:59");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
    
    public Date minDayToCompare(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return DateTimeUtils.convertStringToDateTime(DateTimeUtils.convertDateTimeToString(date) + " 00:00:00");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public String removeVienameseCharacter(String source) {
        char[] lowerVietnameseChar = {'à', 'á', 'ạ', 'ả', 'â', 'ầ', 'ấ', 'ẩ', 'ậ', 'ă', 'ằ', 'ắ', 'ặ', 'ẳ', 'đ', 'è', 'é', 'ẹ', 'ẻ', 'ê', 'ề', 'ế', 'ệ', 'ể', 'ì', 'í', 'ị', 'ỉ',
            'ò', 'ó', 'ọ', 'ỏ', 'ô', 'ồ', 'ố', 'ộ', 'ổ', 'ù', 'ú', 'ụ', 'ủ', 'ư', 'ừ', 'ứ', 'ự', 'ử'};
        char[] upperVietnameseChar = {'À', 'Á', 'Ạ', 'Ả', 'Â', 'Ầ', 'Ấ', 'Ẩ', 'Ậ', 'Ă', 'Ằ', 'Ắ', 'Ặ', 'Ẳ', 'Đ', 'È', 'É', 'Ẹ', 'Ẻ', 'Ê', 'Ề', 'Ế', 'Ệ', 'Ể', 'Ì', 'Í', 'Ị', 'Ỉ',
            'Ò', 'Ó', 'Ọ', 'Ỏ', 'Ô', 'Ồ', 'Ố', 'Ộ', 'Ổ', 'Ù', 'Ú', 'Ụ', 'Ủ', 'Ư', 'Ừ', 'Ứ', 'Ự', 'Ử'};
        char[] toChar = {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'd', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'i', 'i', 'i', 'i',
            'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'};
        String des = "";
        if (source == null || source.equals("")) {
        } else {
            for (int i = 0; i < source.length(); i++) {
                char addChar = source.charAt(i);
                for (int j = 0; j < lowerVietnameseChar.length; j++) {
                    if (lowerVietnameseChar[j] == source.charAt(i)) {
                        addChar = toChar[j];
                        break;
                    }
                    if (upperVietnameseChar[j] == source.charAt(i)) {
                        addChar = toChar[j];
                        break;
                    }
                }
                des = des + addChar;
            }
        }

        return des;
    }

    public String convertVietNameseToNoSign(String source) {

        for (int j = 0; j < a.length(); j++) {
            source = source.replace(a.charAt(j), 'a');
        }
        for (int j = 0; j < e.length(); j++) {
            source = source.replace(e.charAt(j), 'e');
        }
        for (int j = 0; j < i.length(); j++) {
            source = source.replace(i.charAt(j), 'i');
        }
        for (int j = 0; j < o.length(); j++) {
            source = source.replace(o.charAt(j), 'o');
        }
        for (int j = 0; j < u.length(); j++) {
            source = source.replace(u.charAt(j), 'u');
        }
        for (int j = 0; j < d.length(); j++) {
            source = source.replace(d.charAt(j), 'd');
        }
        return source;
    }

    public String convertToSearchVietNamese(String columnName, String source, List lstParam) {
        if (source == null) {
            return source;
        }
        source = source.toLowerCase().trim();

        source = convertVietNameseToNoSign(source);
        source = source.replace("a", "[" + a + "]");
        source = source.replace("e", "[" + e + "]");
        source = source.replace("i", "[" + i + "]");
        source = source.replace("o", "[" + o + "]");
        source = source.replace("u", "[" + u + "]");
        source = source.replace("d", "[" + d + "]");
        String des = " REGEXP_LIKE(lower(" + columnName + "),N'" + source + "') ";
        //lstParam.add(source);
        return des;
    }

    public String convertToLikeString(String source) {
        if (source == null) {
            return "";
        }

        source = source.trim().toLowerCase();
        String des = "";
        Boolean isSpecial;

        char[] specialChar = {'%', '_', '?', '\''};
        for (int i = 0; i < source.length(); i++) {
            isSpecial = false;
            for (int j = 0; j < specialChar.length; j++) {
                if (specialChar[j] == source.charAt(i)) {
                    isSpecial = true;
                    break;

                }
            }
            if (isSpecial) {
                des = des + '!' + source.charAt(i);
            } else {
                des = des + source.charAt(i);
            }

        }
        return des;
    }

    public boolean checkEmptyString(String str) {
        if (str == null) {
            return true;
        }
        str = str.trim();
        if (str.length() == 0) {
            return true;
        }
        return false;
    }

    public GenericDAOHibernate(Class<T> type) {
        this.type = type;
    }

    public ID create(T o) {
        return (ID) session.save(o);
    }

    public ID create(T o, String type) {
//        Session sessionTmp = null;
////        if (type != null && "LTCQ".equals(type)) {
////            sessionTmp = getSession("ltcq");
////        } else {
//        sessionTmp = getSession();
////        }
//        return (ID) sessionTmp.save(o);
        return (ID) session.save(o);
    }

    public T read(ID id) {
        return (T) session.get(this.type, id);
    }

    public void update(T o) {
        session.update(o);
    }

    public void update(T o, String type) {
//        Session sessionTmp = null;
////        if (type != null && "LTCQ".equals(type)) {
////            sessionTmp = getSession("ltcq");
////        } else {
//        sessionTmp = getSession();
////        }
//        sessionTmp.update(o);
        session.update(o);
    }

    public void delete(T o) {
        session.delete(o);
    }

    public Date getSysdate() {
        try {
            String query = "Select sysdate as system_datetime from dual";
            SQLQuery queryObj = getSession().createSQLQuery(query);
            queryObj.addScalar("system_datetime", Hibernate.TIMESTAMP);
            Date sysdate = (Date) queryObj.uniqueResult();
            return sysdate;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public List<T> getDataByFields(Class entityClass, List<String> key, List<Object> valueOfKey, String sortField) {
        String entityName = entityClass.getSimpleName();
        // Build sql
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" FROM ").append(entityName).append(" obj WHERE ");
        if (key != null && valueOfKey != null && key.size() > 0 && (key.size() == valueOfKey.size())) {
            sqlBuilder.append("obj.").append(key.get(0)).append("= :value0");
            for (int i = 1; i < key.size(); i++) {
                sqlBuilder.append(" AND ").append("obj.").append(key.get(i)).append("= :value").append(i);
            }
        }
        if (!"".equals(sortField)) {
            sqlBuilder.append(" ORDER BY ");
            sqlBuilder.append("  nlssort(lower(");
            sqlBuilder.append(validateColumnName(sortField));
            sqlBuilder.append("),'nls_sort = BINARY_AI')");
        }

        Query query = session.createQuery(sqlBuilder.toString());
        if (key != null && valueOfKey != null && key.size() > 0 && (key.size() == valueOfKey.size())) {
            query.setParameter("value0", valueOfKey.get(0));
            for (int i = 1; i < key.size(); i++) {
                query.setParameter("value" + i, valueOfKey.get(i));
            }
        }

        return query.list();
    }

    public List<T> getListByKeys(Class entityClass, List<ID> key) {
        String entityName = entityClass.getSimpleName();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" FROM ").append(entityName).append(
                " obj WHERE obj.id IN (:key)");
        Query query = session.createQuery(sqlBuilder.toString()).setParameter("key", key);
        return query.list();
    }

    /**
     * kiem tra trung khi update trungnq
     *
     * @param code
     * @param deptId
     * @return
     * @throws Exception
     */
    public Boolean checkEntityExistedForUpdate(String idField, String codeField, String entityCode, Long entityId) {
        Boolean isExisted = Boolean.valueOf(false);
        try {
//            Criterion[] criterion = new Criterion[2];
//            criterion[0] = Restrictions.like(codeField, entityCode.toLowerCase(), MatchMode.EXACT).ignoreCase();
//            criterion[1] = Restrictions.ne(idField, entityId);
//            List result = this.findByCriteria(-1, -1, criterion);
            String entityName = type.getSimpleName();
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(" FROM ").append(entityName).append(" obj WHERE ");
            sqlBuilder.append("lower(").append(codeField).append(")").append("  like ? ");
            sqlBuilder.append(" and ").append(idField).append("  <> ? ");
            Query query = session.createQuery(sqlBuilder.toString());
            query.setParameter(0, entityCode.toLowerCase());
            query.setParameter(1, entityId);
            List result = query.list();
            if ((result != null) && (!result.isEmpty()) && (result.size() > 0)) {
                isExisted = Boolean.valueOf(true);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //     throw ex;
        }
        return isExisted;
    }

    /**
     * kiem tra trung khi insert * trungnq
     *
     * @param code
     * @param deptId
     * @return
     * @throws Exception
     */
    public Boolean checkEntityExistedForInsert(String codeField, String entityCode) {
        Boolean isExisted = Boolean.valueOf(false);
        try {

//            Criterion[] criterion = {Restrictions.like(codeField, entityCode.toLowerCase(),MatchMode.EXACT).ignoreCase()};// new Criterion[1];
//            List result = this.findByCriteria(-1,-1,criterion);
            String entityName = type.getSimpleName();
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(" FROM ").append(entityName).append(" obj WHERE ");
            sqlBuilder.append("lower(").append(codeField).append(")").append("  like ? ");
            Query query = session.createQuery(sqlBuilder.toString());
            query.setParameter(0, entityCode.toLowerCase());
            List result = query.list();
            if ((result != null) && (!result.isEmpty()) && (result.size() > 0)) {
                isExisted = Boolean.valueOf(true);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            // throw ex;
        }
        return isExisted;
    }

    /**
     * Check exist in DB by list key
     *
     * @author DPDN_NgocTM3
     * @param entityClass
     * @param key
     * @param valueOfKey
     * @return
     */
    public Boolean isExistIDInDb(Class entityClass, List<String> key, List<Object> valueOfKey) {
        String entityName = entityClass.getSimpleName();
        // Build sql
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" FROM ").append(entityName).append(" obj WHERE ");
        if (key != null && valueOfKey != null && key.size() > 0 && (key.size() == valueOfKey.size())) {
            sqlBuilder.append("obj.").append(key.get(0)).append("= :value0");
            for (int i = 1; i < key.size(); i++) {
                sqlBuilder.append(" AND ").append("obj.").append(key.get(i)).append("= :value").append(i);
            }
        }
        Query query = session.createQuery(sqlBuilder.toString());
        if (key != null && valueOfKey != null && key.size() > 0 && (key.size() == valueOfKey.size())) {
            query.setParameter("value0", valueOfKey.get(0));
            for (int i = 1; i < key.size(); i++) {
                query.setParameter("value" + i, valueOfKey.get(i));
            }
        }
        List<Object> lsObject = query.list();
        if (lsObject != null && lsObject.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check exist in DB by list key
     *
     * @author DPDN_NgocTM3
     * @param entityClass
     * @param key
     * @param valueOfKey
     * @return
     */
    public Boolean isExistIDInDb(List<String> key, List<Object> valueOfKey) {
        String entityName = type.getSimpleName();
        // Build sql
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" FROM ").append(entityName).append(" obj WHERE ");
        if (key != null && valueOfKey != null && key.size() > 0 && (key.size() == valueOfKey.size())) {
            sqlBuilder.append("obj.").append(key.get(0)).append("= :value0");
            for (int i = 1; i < key.size(); i++) {
                sqlBuilder.append(" AND ").append("obj.").append(key.get(i)).append("= :value").append(i);
            }
        }
        Query query = session.createQuery(sqlBuilder.toString());
        if (key != null && valueOfKey != null && key.size() > 0 && (key.size() == valueOfKey.size())) {
            query.setParameter("value0", valueOfKey.get(0));
            for (int i = 1; i < key.size(); i++) {
                query.setParameter("value" + i, valueOfKey.get(i));
            }
        }
        List<Object> lsObject = query.list();
        if (lsObject != null && lsObject.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List findAll(Class entityClass, String... orderFields) {
        String entityName = entityClass.getSimpleName();
        // Build sql
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" FROM ").append(entityName);
        if (orderFields != null && orderFields.length > 0) {
            int length = orderFields.length;
            sqlBuilder.append(" ORDER BY ");

            for (int i = 0; i < length; i++) {
                sqlBuilder.append("  nlssort(lower(");
                sqlBuilder.append(orderFields[i]);
                sqlBuilder.append("),'nls_sort = BINARY_AI')");
                if (i < length - 1) {
                    sqlBuilder.append(",");
                }
            }
        }
        Query query = session.createQuery(sqlBuilder.toString());
        List<Object> lsObject = query.list();
        return lsObject;
    }

    public List findAllActive(Class entityClass, String... orderFields) {
        String entityName = entityClass.getSimpleName();
        // Build sql
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" FROM ").append(entityName).append(" obj ");
        if (entityClass.getSimpleName().equalsIgnoreCase("position")) {
            sqlBuilder.append(" WHERE obj.status = 1");
        } else {
            sqlBuilder.append(" WHERE obj.isActive = 1");
        }
        if (orderFields != null && orderFields.length > 0) {
            int length = orderFields.length;
            sqlBuilder.append(" ORDER BY ");
            for (int i = 0; i < length; i++) {
                sqlBuilder.append("  nlssort(lower(");
                sqlBuilder.append(orderFields[i]);
                sqlBuilder.append("),'nls_sort = BINARY_AI')");
                if (i < length - 1) {
                    sqlBuilder.append(",");
                }
            }
        } else {
            sqlBuilder.append(" ORDER BY ");
            sqlBuilder.append("  nlssort(lower(");
            sqlBuilder.append("name");
            sqlBuilder.append("),'nls_sort = BINARY_AI')");
        }
        Query query = session.createQuery(sqlBuilder.toString());
        List<Object> lsObject = query.list();
        return lsObject;
    }

    public Object findByName(Class entityClass, String name) {
        String entityName = entityClass.getSimpleName();
        // Build sql
        StringBuilder sqlBuilder = new StringBuilder();
        List listParam = new ArrayList();
        sqlBuilder.append(" FROM ").append(entityName).append(" obj WHERE obj.isActive = 1 and lower(obj.name) like ? escape'!'");
        listParam.add("%" + convertToLikeString(name) + "%");

        Query query = session.createQuery(sqlBuilder.toString());
        for (int i = 0; i < listParam.size(); i++) {
            query.setParameter(i, listParam.get(i));
        }

        List<Object> lsObject = query.list();
        if (lsObject != null && lsObject.size() > 0) {
            return lsObject.get(0);
        }
        return null;
    }

    public Object findById(Class entityClass, String idName, Long id) {
        String entityName = entityClass.getSimpleName();
        Object returnOb = null;
        // Build sql
        if (id != null && id > 0L) {
            String hql = "from " + entityName + " where " + idName + " = " + id.toString();
            Query query = session.createQuery(hql);
            List<Object> lsObject = query.list();
            if (lsObject != null && lsObject.size() > 0) {
                returnOb = lsObject.get(0);
            }
        }
        return returnOb;
    }

    @Override
    public T findById(ID id, boolean lock) {
        return super.findById(id, false);
    }

    public T findById(ID id) {
        return super.findById(id, false);
    }

    // tham khao cua LLTP
    public T getById(String idName, Object idValue) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Restrictions.eq(idName, idValue));
        List lst = crit.list();
        if (lst != null && lst.size() > 0) {
            return (T) crit.list().get(0);
        } else {
            return null;
        }
    }

    public T getById(String idName, Object idValue, String type) {
        Session currentSession = null;
//        if (type != null && "LTCQ".equals(type)) {
//            currentSession = getSession("ltcq");
//        } else {
        currentSession = getSession();
//        }
        Criteria crit = currentSession.createCriteria(getPersistentClass());
        crit.add(Restrictions.eq(idName, idValue));
        return (T) crit.list().get(0);
    }

    @SuppressWarnings("unchecked")
    public List<T> getByProperty(String propertyName, Object value) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Restrictions.eq("isActive", "1"));
        crit.add(Restrictions.eq(propertyName, value));
        return (List<T>) crit.list();
    }

    public List<T> getByProperty(String propertyName, Object value, String type) {
        Session currentSession = null;
//        if (type != null && "LTCQ".equals(type)) {
//            currentSession = getSession("ltcq");
//        } else {
        currentSession = getSession();
//        }
        Criteria crit = currentSession.createCriteria(getPersistentClass());
        crit.add(Restrictions.eq("isActive", "1"));
        crit.add(Restrictions.eq(propertyName, value));
        return (List<T>) crit.list();
    }

    public String UpcaseFirst(String str) {
        String first = str.substring(0, 1);
        String concat = str.substring(1);
        return first.toUpperCase() + concat;
    }

    public String convertToJSONArray(List<T> array, String id, String searchAttr1, String searchAttr2) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (T o : array) {
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

                String search2 = "";
                if (searchAttr2 != null && !"".equals(searchAttr2)) {
                    getMethodName = "get" + UpcaseFirst(searchAttr2);
                    getMethod = cls.getMethod(getMethodName);
                    if (getMethod.invoke(o) != null) {
                        search2 = getMethod.invoke(o).toString();
                    }
                }
                jsonObj.put("search2", StringUtils.escapeHTML(search2));

                jsonArray.add(jsonObj);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return jsonArray.toString();
    }

    public String validateColumnName(String name) {
        List<Character> aToZ = new ArrayList<Character>();
        aToZ.addAll(Arrays.asList(aZ));
        if (name != null && !"".equals(name)) {
            if (name.length() > 30) {
                //
                // Ten cot dai nhat trong sql = 30
                //
                return "";
            } else {
                for (int ind = 0; ind < name.length(); ind++) {
                    String nameLower = name.toLowerCase();
                    if (!aToZ.contains(nameLower.charAt(ind))) {
                        return "";
                    }
                }
            }
        }
        return name;
    }
}
