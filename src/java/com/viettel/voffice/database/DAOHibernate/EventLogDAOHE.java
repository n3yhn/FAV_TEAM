/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.voffice.database.DAOHibernate;

import com.viettel.voffice.common.util.StringUtils;
import com.viettel.vsaadmin.database.BO.EventLogBO;
import com.viettel.vsaadmin.database.BO.Objects;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;
import viettel.passport.client.UserToken;

/**
 * EventLogBODAOHibernate
 *
 * @author trungnq7@viettel.com.vn
 * @since Apr 6, 2011
 * @version 1.0
 */
public class EventLogDAOHE extends GenericDAOHibernate<EventLogBO, Long> {

    private static List lstLogs = new ArrayList();
    private static boolean saveLog = true;
    private static long cacheSize = 100;
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(EventLogDAOHE.class);


    private static void loadParam() {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String sl = rb.getString("saveLog");
            String cs = rb.getString("cacheSize");
            cacheSize = Long.parseLong(cs);
            saveLog = Boolean.parseBoolean(sl);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    static {
        try {
            loadParam();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public EventLogDAOHE() {
        super(EventLogBO.class);
    }

    /**
     * luu vao db
     *
     * @param classForm
     */
    public Boolean saveEventLog(EventLogBO eventLogBO) {
        if (!saveLog) {
            return false;
        }
        Boolean result;
        try {
            //luu db
            lstLogs.add(eventLogBO);
            if (lstLogs.size() == cacheSize) {
                for (int i = 0; i < lstLogs.size(); i++) {
                    create((EventLogBO) lstLogs.get(i));
                }
                lstLogs.clear();
            }
            flush();
            //loi - them o day thi lam sao them log dc???
            //lstLogs.clear();
            result = true;
        } catch (Exception ex) {
            result = false;
            log.error(ex.getMessage());
        }
        return result;
    }

    public Boolean insertEventLog(String action, String description, HttpServletRequest request) {
        if (!saveLog) {
            return false;
        }
        Boolean result = false;
        UserToken userToken = (UserToken) request.getSession().getAttribute("vsaUserToken");
        EventLogBO eventLogBO = new EventLogBO();
        eventLogBO.setAction(action);
        if (userToken != null) {
            eventLogBO.setActor(userToken.getFullName());
            eventLogBO.setUserName(userToken.getUserName());
        }
        eventLogBO.setEventDate(new Date());
        eventLogBO.setIp(request.getRemoteAddr());
        eventLogBO.setDescription(description);
        result = saveEventLog(eventLogBO);
        return result;
    }

    /**
     * luu vao db
     *
     * @param classForm
     */
    public Boolean insertEventLog(HttpServletRequest request, String action, String actor, String description) {
        if (!saveLog) {
            return false;
        }
        Boolean result = false;
        UserToken userToken = (UserToken) request.getSession().getAttribute("userToken");
        EventLogBO eventLogBO = new EventLogBO();
        int lx = action.lastIndexOf("/");
        String mainAction = action.substring(lx + 1, action.length());
        //LogUtil.setIP(eventLogBO, request);
        String hql = "select o from Objects o where lower(o.objectUrl) like ? escape '/'";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, "%" + StringUtils.escapeSql(mainAction) + "%");
        List<Objects> lst = query.list();

        if (lst != null && lst.size() > 0) {
            action = lst.get(0).getObjectName();
            eventLogBO.setAction(action);
            if (userToken != null) {
                eventLogBO.setActor(userToken.getFullName());
                eventLogBO.setUserName(userToken.getFullName());
            }
            eventLogBO.setEventDate(new Date());
            eventLogBO.setIp(request.getRemoteAddr());
//        if(userToken!=null)
//        eventLogBO.setUserName(userToken.getUserName());
            eventLogBO.setDescription(description);
            result = saveEventLog(eventLogBO);
        }
        return result;
    }

    public Boolean insertTraceLog(HttpServletRequest request) {
        if (!saveLog) {
            return false;
        }
        Boolean result = false;
        try {
            UserToken userToken = (UserToken) request.getSession().getAttribute("userToken");
            EventLogBO eventLogBO = new EventLogBO();
            String action = request.getRequestURI();
            int lx = action.lastIndexOf("/");
            String mainAction = action.substring(lx + 1, action.length());
            //LogUtil.setIP(eventLogBO, request);
            String hql = "select o from Objects o where lower(o.objectUrl) like ? escape '/'";
            if (!getSession().isOpen()) {
                getSession().beginTransaction();
            }
            Query query = getSession().createQuery(hql);
            query.setParameter(0, "%" + StringUtils.escapeSql(mainAction.toLowerCase()) + "%");
            List<Objects> lst = query.list();

            if (lst != null && lst.size() > 0) {
                action = lst.get(0).getObjectName();
                eventLogBO.setAction(action);
                if (userToken != null) {
                    eventLogBO.setActor(userToken.getFullName());
                    eventLogBO.setUserName(userToken.getFullName());
                }
                eventLogBO.setEventDate(new Date());
                eventLogBO.setIp(request.getRemoteAddr());
                result = saveEventLog(eventLogBO);
            } else {
                action = request.getServletPath();
                eventLogBO.setAction(action);
                if (userToken != null) {
                    eventLogBO.setActor(userToken.getFullName());
                    eventLogBO.setUserName(userToken.getFullName());
                }
                eventLogBO.setEventDate(new Date());
                eventLogBO.setIp(request.getRemoteAddr());
                Map map = request.getParameterMap();
                String xml = mapToString(map);
                if (xml != null && xml.length() > 4000) {
                    xml = xml.substring(0, 3999);
                }
                eventLogBO.setDescription(xml);
                result = saveEventLog(eventLogBO);
            }
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return result;
    }

    public String mapToString(Map map) {
        StringBuilder str = new StringBuilder();
        for (Object key : map.keySet()) {
            String keyName = key.toString();
            String keyValue = "";
            try {
                keyValue = ((String[]) map.get(key))[0];

            } catch (Exception en) {
            }
            str.append(keyName).append(":").append(keyValue).append(";");
        }
        return str.toString();
    }

    public Boolean linkIsMenu(HttpServletRequest request) {
        Boolean result = false;
        try {
            String action = request.getRequestURI();
            int lx = action.lastIndexOf("/");
            String mainAction = action.substring(lx + 1, action.length());

            if (mainAction.isEmpty()) {
            } else {
                //LogUtil.setIP(eventLogBO, request);
                String hql = "select o from Objects o where o.status=1 and lower(o.objectUrl) like ? escape '/'";
                Query query = getSession().createQuery(hql);
                query.setParameter(0, "%" + StringUtils.escapeSql(mainAction.toLowerCase()) + "%");
                List<Objects> lst = query.list();

                if (lst != null && lst.size() > 0) {
                    result = true;
                }
            }
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return result;
    }
}
