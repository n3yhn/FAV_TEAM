/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.email.DAOHE;

import com.viettel.voffice.database.BO.EmailUser;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;

/**
 *
 * @author dungnt78
 */
public class EmailUserDAOHE extends GenericDAOHibernate<EmailUser, Long> {

    public EmailUserDAOHE() {
        super(EmailUser.class);
    }

}
