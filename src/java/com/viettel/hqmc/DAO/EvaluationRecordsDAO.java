/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

//import com.viettel.hqmc.BO.EvaluationRecords;
//import com.viettel.hqmc.DAOHE.CategoryTypeDAOHE;
import com.viettel.hqmc.DAOHE.EvaluationRecordsDAOHE;
import com.viettel.hqmc.FORM.EvaluationRecordsForm;
import com.viettel.voffice.database.DAO.BaseDAO;
//import com.viettel.voffice.database.DAO.GridResult;
import java.util.List;

/**
 *
 * @author binhnt53
 */
public class EvaluationRecordsDAO extends BaseDAO {

    private String forwardPage = "Page";
    private EvaluationRecordsForm searchForm;
    private EvaluationRecordsForm createForm;
    EvaluationRecordsDAOHE evaluationRecordsDao = new EvaluationRecordsDAOHE();
    private List<EvaluationRecordsForm> lstItemOnGrid;
}
