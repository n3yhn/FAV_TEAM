/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.vsaadmin.database.DAO;

import com.viettel.common.util.LogUtil;
import com.viettel.voffice.client.form.EventLogLoginForm;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.vsaadmin.database.DAOHibernate.EventLogLoginDAOHE;
import java.util.List;

/**
 *
 * @author vtit_binhnt53
 */
public class EventLogLoginDAO  extends BaseDAO{
    private String forwardPage = "eventLogLoginPage";
    private EventLogLoginForm searchEventLogLoginForm;
    //private EventLogLoginForm createForm;
    EventLogLoginDAOHE categoryTypeDao = new EventLogLoginDAOHE();
    private List<EventLogLoginForm> lstItemOnGrid;
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(EventLogLoginDAO.class);
    //perpare method

    public String prepare() {
        try {
            //todo code here
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return this.forwardPage;
    }

    public String onSearch() {
        getGridInfo();

        EventLogLoginDAOHE bdhe = new EventLogLoginDAOHE();
        GridResult gr = bdhe.findEventLogLogin(searchEventLogLoginForm, start, count, sortField);

        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());

        return GRID_DATA;
    }

//    public EventLogLoginForm getCreateForm() {
//        return createForm;
//    }
//
//    public void setCreateForm(EventLogLoginForm createForm) {
//        this.createForm = createForm;
//    }

    public List<EventLogLoginForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<EventLogLoginForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

    public EventLogLoginForm getSearchEventLogLoginForm() {
        return searchEventLogLoginForm;
    }

    public void setSearchEventLogLoginForm(EventLogLoginForm searchEventLogLoginForm) {
        this.searchEventLogLoginForm = searchEventLogLoginForm;
    }
    
}