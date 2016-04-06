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
public class IMPORTERType {

    private String BUSSINESS_CODE;
    private String ADDRESS;
    private String PHONE;
    private String FAX;
    private String CONTRACT_NO;

    public IMPORTERType(String BUSSINESS_CODE, String ADDRESS, String PHONE, String FAX, String CONTRACT_NO) {
        this.BUSSINESS_CODE = BUSSINESS_CODE;
        this.ADDRESS = ADDRESS;
        this.PHONE = PHONE;
        this.FAX = FAX;
        this.CONTRACT_NO = CONTRACT_NO;
    }

    public IMPORTERType() {
    }

    public String getBUSSINESS_CODE() {
        return BUSSINESS_CODE;
    }

    public void setBUSSINESS_CODE(String BUSSINESS_CODE) {
        this.BUSSINESS_CODE = BUSSINESS_CODE;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getFAX() {
        return FAX;
    }

    public void setFAX(String FAX) {
        this.FAX = FAX;
    }

    public String getCONTRACT_NO() {
        return CONTRACT_NO;
    }

    public void setCONTRACT_NO(String CONTRACT_NO) {
        this.CONTRACT_NO = CONTRACT_NO;
    }

}
