/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.hqmc.BO.XmlWs;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class XmlWsDAOHE  extends GenericDAOHibernate<XmlWs, Long>{
    private static Logger logger = Logger.getLogger(XmlWsDAOHE.class);
    private static final Long ZERO = 0L;
    private BaseDAO baseDAO = new BaseDAO();

    public XmlWsDAOHE() {
        super(XmlWs.class);
    }
}
