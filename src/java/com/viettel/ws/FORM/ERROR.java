/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.FORM;

/**
 *
 * @author Administrator
 */
public class ERROR {

    private Long ID;//
    private String ERROR_TYPE_CODE;//kieu 
    private String ERROR_SERIAL_NO;//
    private String ERROR_CODE;
    private String ELEMENT_NAME;
    private String DESCRIPTION;
    private String SERVICE_CODE;
    private String TABLE_SAVE;

    public ERROR() {
    }

    public ERROR(Long ID, String ERROR_TYPE_CODE, String ERROR_SERIAL_NO, String ERROR_CODE, String ELEMENT_NAME, String DESCRIPTION, String SERVICE_CODE, String TABLE_SAVE) {
        this.ID = ID;
        this.ERROR_TYPE_CODE = ERROR_TYPE_CODE;
        this.ERROR_SERIAL_NO = ERROR_SERIAL_NO;
        this.ERROR_CODE = ERROR_CODE;
        this.ELEMENT_NAME = ELEMENT_NAME;
        this.DESCRIPTION = DESCRIPTION;
        this.SERVICE_CODE = SERVICE_CODE;
        this.TABLE_SAVE = TABLE_SAVE;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getERROR_TYPE_CODE() {
        return ERROR_TYPE_CODE;
    }

    public void setERROR_TYPE_CODE(String ERROR_TYPE_CODE) {
        this.ERROR_TYPE_CODE = ERROR_TYPE_CODE;
    }

    public String getERROR_SERIAL_NO() {
        return ERROR_SERIAL_NO;
    }

    public void setERROR_SERIAL_NO(String ERROR_SERIAL_NO) {
        this.ERROR_SERIAL_NO = ERROR_SERIAL_NO;
    }

    public String getERROR_CODE() {
        return ERROR_CODE;
    }

    public void setERROR_CODE(String ERROR_CODE) {
        this.ERROR_CODE = ERROR_CODE;
    }

    public String getELEMENT_NAME() {
        return ELEMENT_NAME;
    }

    public void setELEMENT_NAME(String ELEMENT_NAME) {
        this.ELEMENT_NAME = ELEMENT_NAME;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getSERVICE_CODE() {
        return SERVICE_CODE;
    }

    public void setSERVICE_CODE(String SERVICE_CODE) {
        this.SERVICE_CODE = SERVICE_CODE;
    }

    public String getTABLE_SAVE() {
        return TABLE_SAVE;
    }

    public void setTABLE_SAVE(String TABLE_SAVE) {
        this.TABLE_SAVE = TABLE_SAVE;
    }

}
