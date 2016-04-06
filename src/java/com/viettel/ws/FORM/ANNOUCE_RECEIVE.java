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
public class ANNOUCE_RECEIVE {

    private String FILE_CODE;
    private Long ID;
    private String FILE_ATTP_CODE;
    private Long STATUS;
    private String BUSSINESS_CODE;
    private ANNOUNCE_DETAILS aNNOUNCE_DETAILS;

    public ANNOUNCE_DETAILS getaNNOUNCE_DETAILS() {
        return aNNOUNCE_DETAILS;
    }

    public void setaNNOUNCE_DETAILS(ANNOUNCE_DETAILS aNNOUNCE_DETAILS) {
        this.aNNOUNCE_DETAILS = aNNOUNCE_DETAILS;
    }

    public ANNOUCE_RECEIVE() {
    }

    public ANNOUCE_RECEIVE(String FILE_CODE, Long ID, String FILE_ATTP_CODE, Long STATUS, String BUSSINESS_CODE, ANNOUNCE_DETAILS aNNOUNCE_DETAILS) {
        this.FILE_CODE = FILE_CODE;
        this.ID = ID;
        this.FILE_ATTP_CODE = FILE_ATTP_CODE;
        this.STATUS = STATUS;
        this.BUSSINESS_CODE = BUSSINESS_CODE;
        this.aNNOUNCE_DETAILS = aNNOUNCE_DETAILS;
    }

    public String getFILE_CODE() {
        return FILE_CODE;
    }

    public void setFILE_CODE(String FILE_CODE) {
        this.FILE_CODE = FILE_CODE;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getFILE_ATTP_CODE() {
        return FILE_ATTP_CODE;
    }

    public void setFILE_ATTP_CODE(String FILE_ATTP_CODE) {
        this.FILE_ATTP_CODE = FILE_ATTP_CODE;
    }

    public Long getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Long STATUS) {
        this.STATUS = STATUS;
    }

    public String getBUSSINESS_CODE() {
        return BUSSINESS_CODE;
    }

    public void setBUSSINESS_CODE(String BUSSINESS_CODE) {
        this.BUSSINESS_CODE = BUSSINESS_CODE;
    }

}
