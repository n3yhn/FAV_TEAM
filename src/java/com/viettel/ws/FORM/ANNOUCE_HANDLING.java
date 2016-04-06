/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.FORM;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public class ANNOUCE_HANDLING {

    private String FILE_CODE;
    private String FILE_ATTP_CODE;
    private Long STATUS;
    private String CONFIRM_ANNOUNCE_NUMBER;
    private Date PROCESS_DATE;
    private Long PROCESS_ID;
    private Date EFFECT_DATE;

    public ANNOUCE_HANDLING() {
    }

    public ANNOUCE_HANDLING(String FILE_CODE, String FILE_ATTP_CODE, Long STATUS, String CONFIRM_ANNOUNCE_NUMBER, Date PROCESS_DATE, Long PROCESS_ID, Date EFFECT_DATE) {
        this.FILE_CODE = FILE_CODE;
        this.FILE_ATTP_CODE = FILE_ATTP_CODE;
        this.STATUS = STATUS;
        this.CONFIRM_ANNOUNCE_NUMBER = CONFIRM_ANNOUNCE_NUMBER;
        this.PROCESS_DATE = PROCESS_DATE;
        this.PROCESS_ID = PROCESS_ID;
        this.EFFECT_DATE = EFFECT_DATE;
    }

    public String getFILE_CODE() {
        return FILE_CODE;
    }

    public void setFILE_CODE(String FILE_CODE) {
        this.FILE_CODE = FILE_CODE;
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

    public String getCONFIRM_ANNOUNCE_NUMBER() {
        return CONFIRM_ANNOUNCE_NUMBER;
    }

    public void setCONFIRM_ANNOUNCE_NUMBER(String CONFIRM_ANNOUNCE_NUMBER) {
        this.CONFIRM_ANNOUNCE_NUMBER = CONFIRM_ANNOUNCE_NUMBER;
    }

    public Date getPROCESS_DATE() {
        return PROCESS_DATE;
    }

    public void setPROCESS_DATE(Date PROCESS_DATE) {
        this.PROCESS_DATE = PROCESS_DATE;
    }

    public Long getPROCESS_ID() {
        return PROCESS_ID;
    }

    public void setPROCESS_ID(Long PROCESS_ID) {
        this.PROCESS_ID = PROCESS_ID;
    }

    public Date getEFFECT_DATE() {
        return EFFECT_DATE;
    }

    public void setEFFECT_DATE(Date EFFECT_DATE) {
        this.EFFECT_DATE = EFFECT_DATE;
    }

}
