/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.hqmc.BO.FilesEdit;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;

/**
 *
 * @author Os_Hiepvv
 */
public class FilesEditDAOHE extends GenericDAOHibernate<FilesEdit, Long> {

    public FilesEditDAOHE() {
        super(FilesEdit.class);
    }

//    public void saveFilesEdit(FilesEdit bo) {
//        FilesEditDAOHE a = new FilesEditDAOHE();
//        a.save(bo);
//    }
}
