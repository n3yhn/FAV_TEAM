/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.flow.DAOHE;

import com.viettel.flow.BO.Node;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;

/**
 *
 * @author Administrator
 */
public class NodeDAOHE extends GenericDAOHibernate<Node, Long> {

    public NodeDAOHE() {
        super(Node.class);
    }

}
