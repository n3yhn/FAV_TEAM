/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.vsaadmin.database.DAOHibernate;

import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import com.viettel.vsaadmin.database.BO.VDepartment;

/**
 *
 * @author DiuLTT
 */
public class VDepartmentDAOHE extends GenericDAOHibernate<VDepartment, Long>{
    
    public VDepartmentDAOHE() {
        super(VDepartment.class);
    }
}
