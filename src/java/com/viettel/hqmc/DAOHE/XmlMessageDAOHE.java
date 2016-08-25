/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import com.viettel.ws.BO.XmlMessage;

public class XmlMessageDAOHE extends GenericDAOHibernate<XmlMessage, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(XmlMessageDAOHE.class);

    public XmlMessageDAOHE() {
        super(XmlMessage.class);
    }
}
