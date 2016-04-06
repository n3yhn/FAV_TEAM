/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.FORM;

import com.viettel.hqmc.FORM.TestRegistrationForm;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class TEST_REGISTRATIONType {

    private String FILE_CODE;
    private String DEPT_CODE;
    private Long PROCESS_ID;
    private Date PROCESS_DATE;
    private Long PROCESS_RESULT;
    private String DESCRIPTION;
    private IMPORTERType IMPORTER;
    private EXPORTERType EXPORTER;
    private String ADDRESS_FROM;
    private String ADDRESS_TO;
    private Date COMING_DATE;
    private String INVOICE_NO;
    private String TRANS_NO;
    private String IMPORT_NO;
    private String CHECK_ADDRESS;
    private Date CHECK_DATE;
    private List<PRODUCTType> PRODUCT_LIST;

    public TEST_REGISTRATIONType(String FILE_CODE, String DEPT_CODE, Long PROCESS_ID, Date PROCESS_DATE, Long PROCESS_RESULT, String DESCRIPTION, IMPORTERType IMPORTER, EXPORTERType EXPORTER, String ADDRESS_FROM, String ADDRESS_TO, Date COMING_DATE, String INVOICE_NO, String TRANS_NO, String IMPORT_NO, String CHECK_ADDRESS, Date CHECK_DATE, List<PRODUCTType> PRODUCT_LIST) {
        this.FILE_CODE = FILE_CODE;
        this.DEPT_CODE = DEPT_CODE;
        this.PROCESS_ID = PROCESS_ID;
        this.PROCESS_DATE = PROCESS_DATE;
        this.PROCESS_RESULT = PROCESS_RESULT;
        this.DESCRIPTION = DESCRIPTION;
        this.IMPORTER = IMPORTER;
        this.EXPORTER = EXPORTER;
        this.ADDRESS_FROM = ADDRESS_FROM;
        this.ADDRESS_TO = ADDRESS_TO;
        this.COMING_DATE = COMING_DATE;
        this.INVOICE_NO = INVOICE_NO;
        this.TRANS_NO = TRANS_NO;
        this.IMPORT_NO = IMPORT_NO;
        this.CHECK_ADDRESS = CHECK_ADDRESS;
        this.CHECK_DATE = CHECK_DATE;
        this.PRODUCT_LIST = PRODUCT_LIST;
    }

    public TEST_REGISTRATIONType() {
    }

    public String getFILE_CODE() {
        return FILE_CODE;
    }

    public void setFILE_CODE(String FILE_CODE) {
        this.FILE_CODE = FILE_CODE;
    }

    public String getDEPT_CODE() {
        return DEPT_CODE;
    }

    public void setDEPT_CODE(String DEPT_CODE) {
        this.DEPT_CODE = DEPT_CODE;
    }

    public Long getPROCESS_ID() {
        return PROCESS_ID;
    }

    public void setPROCESS_ID(Long PROCESS_ID) {
        this.PROCESS_ID = PROCESS_ID;
    }

    public Date getPROCESS_DATE() {
        return PROCESS_DATE;
    }

    public void setPROCESS_DATE(Date PROCESS_DATE) {
        this.PROCESS_DATE = PROCESS_DATE;
    }

    public Long getPROCESS_RESULT() {
        return PROCESS_RESULT;
    }

    public void setPROCESS_RESULT(Long PROCESS_RESULT) {
        this.PROCESS_RESULT = PROCESS_RESULT;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public IMPORTERType getIMPORTER() {
        return IMPORTER;
    }

    public void setIMPORTER(IMPORTERType IMPORTER) {
        this.IMPORTER = IMPORTER;
    }

    public EXPORTERType getEXPORTER() {
        return EXPORTER;
    }

    public void setEXPORTER(EXPORTERType EXPORTER) {
        this.EXPORTER = EXPORTER;
    }

    public String getADDRESS_FROM() {
        return ADDRESS_FROM;
    }

    public void setADDRESS_FROM(String ADDRESS_FROM) {
        this.ADDRESS_FROM = ADDRESS_FROM;
    }

    public String getADDRESS_TO() {
        return ADDRESS_TO;
    }

    public void setADDRESS_TO(String ADDRESS_TO) {
        this.ADDRESS_TO = ADDRESS_TO;
    }

    public Date getCOMING_DATE() {
        return COMING_DATE;
    }

    public void setCOMING_DATE(Date COMING_DATE) {
        this.COMING_DATE = COMING_DATE;
    }

    public String getINVOICE_NO() {
        return INVOICE_NO;
    }

    public void setINVOICE_NO(String INVOICE_NO) {
        this.INVOICE_NO = INVOICE_NO;
    }

    public String getTRANS_NO() {
        return TRANS_NO;
    }

    public void setTRANS_NO(String TRANS_NO) {
        this.TRANS_NO = TRANS_NO;
    }

    public String getIMPORT_NO() {
        return IMPORT_NO;
    }

    public void setIMPORT_NO(String IMPORT_NO) {
        this.IMPORT_NO = IMPORT_NO;
    }

    public String getCHECK_ADDRESS() {
        return CHECK_ADDRESS;
    }

    public void setCHECK_ADDRESS(String CHECK_ADDRESS) {
        this.CHECK_ADDRESS = CHECK_ADDRESS;
    }

    public Date getCHECK_DATE() {
        return CHECK_DATE;
    }

    public void setCHECK_DATE(Date CHECK_DATE) {
        this.CHECK_DATE = CHECK_DATE;
    }

    public List<PRODUCTType> getPRODUCT_LIST() {
        return PRODUCT_LIST;
    }

    public void setPRODUCT_LIST(List<PRODUCTType> PRODUCT_LIST) {
        this.PRODUCT_LIST = PRODUCT_LIST;
    }

    public TestRegistrationForm totestRegistrationForm() {
        TestRegistrationForm result = new TestRegistrationForm();
        result.setTestAgency(TRANS_NO);//co quan kiem tra        
        return result;
    }
}
