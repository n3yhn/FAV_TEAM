/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.FORM;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class ANNOUNCE_DETAILS {

    private String CONFIRM_NO;
    private Date CONFIRM_DATE;
    private Long CONFIRMER_ID;
    private Long CONFIRM_DEPT_ID;
    private Date CONFIRM_START_DATE;
    private Date CONFIRM_END_DATE;

    public String getCONFIRM_NO() {
        return CONFIRM_NO;
    }

    public void setCONFIRM_NO(String CONFIRM_NO) {
        this.CONFIRM_NO = CONFIRM_NO;
    }

    public Date getCONFIRM_DATE() {
        return CONFIRM_DATE;
    }

    public void setCONFIRM_DATE(Date CONFIRM_DATE) {
        this.CONFIRM_DATE = CONFIRM_DATE;
    }

    public Long getCONFIRMER_ID() {
        return CONFIRMER_ID;
    }

    public void setCONFIRMER_ID(Long CONFIRMER_ID) {
        this.CONFIRMER_ID = CONFIRMER_ID;
    }

    public Long getCONFIRM_DEPT_ID() {
        return CONFIRM_DEPT_ID;
    }

    public void setCONFIRM_DEPT_ID(Long CONFIRM_DEPT_ID) {
        this.CONFIRM_DEPT_ID = CONFIRM_DEPT_ID;
    }

    public Date getCONFIRM_START_DATE() {
        return CONFIRM_START_DATE;
    }

    public void setCONFIRM_START_DATE(Date CONFIRM_START_DATE) {
        this.CONFIRM_START_DATE = CONFIRM_START_DATE;
    }

    public Date getCONFIRM_END_DATE() {
        return CONFIRM_END_DATE;
    }

    public void setCONFIRM_END_DATE(Date CONFIRM_END_DATE) {
        this.CONFIRM_END_DATE = CONFIRM_END_DATE;
    }

    public ANNOUNCE_DETAILS(String CONFIRM_NO, Date CONFIRM_DATE, Long CONFIRMER_ID, Long CONFIRM_DEPT_ID, Date CONFIRM_START_DATE, Date CONFIRM_END_DATE) {
        this.CONFIRM_NO = CONFIRM_NO;
        this.CONFIRM_DATE = CONFIRM_DATE;
        this.CONFIRMER_ID = CONFIRMER_ID;
        this.CONFIRM_DEPT_ID = CONFIRM_DEPT_ID;
        this.CONFIRM_START_DATE = CONFIRM_START_DATE;
        this.CONFIRM_END_DATE = CONFIRM_END_DATE;
    }

    public ANNOUNCE_DETAILS() {
    }

}
