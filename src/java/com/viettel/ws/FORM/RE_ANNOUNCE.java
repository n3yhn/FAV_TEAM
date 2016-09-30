/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.FORM;

import com.viettel.hqmc.BO.Business;
import com.viettel.hqmc.DAOHE.BusinessDAOHE;
import com.viettel.hqmc.FORM.ReIssueFormForm;
import com.viettel.vsaadmin.database.BO.Department;
import com.viettel.vsaadmin.database.DAOHibernate.DepartmentDAOHE;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class RE_ANNOUNCE {

    private String FILE_CODE;
    private String TYPE;
    private String BUSSINESS_CODE;
    private String DOCUMENT_NUMBER;
    private Date PUBLISH_DATE;
    private String ANNOUNCE_NUMBER;
    private Long ANNOUNCE_DEPT_ID;
    private Date ANNOUNCE_DATE;
    private Date SIGN_DATE;
    private String FILE_ATTP_CODE;
    private List<ATTACH> ATTACHMENTS;

    public RE_ANNOUNCE() {
    }

    public RE_ANNOUNCE(String FILE_CODE, String TYPE, String BUSSINESS_CODE, String DOCUMENT_NUMBER, Date PUBLISH_DATE, String ANNOUNCE_NUMBER, Long ANNOUNCE_DEPT_ID, Date ANNOUNCE_DATE, Date SIGN_DATE, String FILE_ATTP_CODE, List<ATTACH> ATTACHMENTS) {
        this.FILE_CODE = FILE_CODE;
        this.TYPE = TYPE;
        this.BUSSINESS_CODE = BUSSINESS_CODE;
        this.DOCUMENT_NUMBER = DOCUMENT_NUMBER;
        this.PUBLISH_DATE = PUBLISH_DATE;
        this.ANNOUNCE_NUMBER = ANNOUNCE_NUMBER;
        this.ANNOUNCE_DEPT_ID = ANNOUNCE_DEPT_ID;
        this.ANNOUNCE_DATE = ANNOUNCE_DATE;
        this.SIGN_DATE = SIGN_DATE;
        this.FILE_ATTP_CODE = FILE_ATTP_CODE;
        this.ATTACHMENTS = ATTACHMENTS;
    }

    public ReIssueFormForm toReIssueFormForm() {
        ReIssueFormForm bo = new ReIssueFormForm();
        //so cong van
        bo.setDocumentNo(DOCUMENT_NUMBER);
        //ngay ban hanh
        bo.setDocumentDate(PUBLISH_DATE);
        //ten to chuc ca nhan
        BusinessDAOHE busdaohe = new BusinessDAOHE();
        Business bus;
        bus = busdaohe.findByBusinessTaxCode(BUSSINESS_CODE);
        if (bus != null) {
            bo.setBusinessName(bus.getBusinessName());
        }
        //ten co quan tiep nhan
        DepartmentDAOHE deptdaohe = new DepartmentDAOHE();
        Department deptbo;
        deptbo = deptdaohe.findBOById(ANNOUNCE_DEPT_ID);
        if (deptbo != null) {
            bo.setReIssueAgencyId(deptbo.getDeptId());
            bo.setReIssueAgency(deptbo.getDeptName());
        }
        bo.setFormNumber(ANNOUNCE_NUMBER);
        bo.setIssueDate(ANNOUNCE_DATE);
        //ATTACHMENTS notthing
        return bo;
    }

    public String getFILE_CODE() {
        return FILE_CODE;
    }

    public void setFILE_CODE(String FILE_CODE) {
        this.FILE_CODE = FILE_CODE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getBUSSINESS_CODE() {
        return BUSSINESS_CODE;
    }

    public void setBUSSINESS_CODE(String BUSSINESS_CODE) {
        this.BUSSINESS_CODE = BUSSINESS_CODE;
    }

    public String getDOCUMENT_NUMBER() {
        return DOCUMENT_NUMBER;
    }

    public void setDOCUMENT_NUMBER(String DOCUMENT_NUMBER) {
        this.DOCUMENT_NUMBER = DOCUMENT_NUMBER;
    }

    public Date getPUBLISH_DATE() {
        return PUBLISH_DATE;
    }

    public void setPUBLISH_DATE(Date PUBLISH_DATE) {
        this.PUBLISH_DATE = PUBLISH_DATE;
    }

    public String getANNOUNCE_NUMBER() {
        return ANNOUNCE_NUMBER;
    }

    public void setANNOUNCE_NUMBER(String ANNOUNCE_NUMBER) {
        this.ANNOUNCE_NUMBER = ANNOUNCE_NUMBER;
    }

    public Long getANNOUNCE_DEPT_ID() {
        return ANNOUNCE_DEPT_ID;
    }

    public void setANNOUNCE_DEPT_ID(Long ANNOUNCE_DEPT_ID) {
        this.ANNOUNCE_DEPT_ID = ANNOUNCE_DEPT_ID;
    }

    public Date getANNOUNCE_DATE() {
        return ANNOUNCE_DATE;
    }

    public void setANNOUNCE_DATE(Date ANNOUNCE_DATE) {
        this.ANNOUNCE_DATE = ANNOUNCE_DATE;
    }

    public Date getSIGN_DATE() {
        return SIGN_DATE;
    }

    public void setSIGN_DATE(Date SIGN_DATE) {
        this.SIGN_DATE = SIGN_DATE;
    }

    public String getFILE_ATTP_CODE() {
        return FILE_ATTP_CODE;
    }

    public void setFILE_ATTP_CODE(String FILE_ATTP_CODE) {
        this.FILE_ATTP_CODE = FILE_ATTP_CODE;
    }

    public List<ATTACH> getATTACHMENTS() {
        return ATTACHMENTS;
    }

    public void setATTACHMENTS(List<ATTACH> ATTACHMENTS) {
        this.ATTACHMENTS = ATTACHMENTS;
    }
}
