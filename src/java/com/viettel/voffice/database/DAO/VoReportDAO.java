/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAO;

import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DungNV40
 */
public class VoReportDAO extends BaseDAO{
    CategoryDAOHE categoryDAOHE = new CategoryDAOHE();
    List<Category> docType = new ArrayList<Category>();
}
