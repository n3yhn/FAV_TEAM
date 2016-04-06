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
public class PRODUCTType {

    private String PRODUCT_ID;
    private String PRODUCT_NAME;
    private String PRODUCT_SIG;
    private Date PR_DATE;
    private Date EX_DATE;
    private Long NATIONAL_CODE;
    private String NUMBER;
    private Long UNIT_ID;
    private String WEIGHT;
    private Long WEIGHT_UNIT_ID;
    private String PRICE;
    private Long PRICE_UNIT_ID;

    public PRODUCTType() {
    }

    public PRODUCTType(String PRODUCT_ID, String PRODUCT_NAME, String PRODUCT_SIG, Date PR_DATE, Date EX_DATE, Long NATIONAL_CODE, String NUMBER, Long UNIT_ID, String WEIGHT, Long WEIGHT_UNIT_ID, String PRICE, Long PRICE_UNIT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
        this.PRODUCT_NAME = PRODUCT_NAME;
        this.PRODUCT_SIG = PRODUCT_SIG;
        this.PR_DATE = PR_DATE;
        this.EX_DATE = EX_DATE;
        this.NATIONAL_CODE = NATIONAL_CODE;
        this.NUMBER = NUMBER;
        this.UNIT_ID = UNIT_ID;
        this.WEIGHT = WEIGHT;
        this.WEIGHT_UNIT_ID = WEIGHT_UNIT_ID;
        this.PRICE = PRICE;
        this.PRICE_UNIT_ID = PRICE_UNIT_ID;
    }

    public String getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(String PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }

    public void setPRODUCT_NAME(String PRODUCT_NAME) {
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    public String getPRODUCT_SIG() {
        return PRODUCT_SIG;
    }

    public void setPRODUCT_SIG(String PRODUCT_SIG) {
        this.PRODUCT_SIG = PRODUCT_SIG;
    }

    public Date getPR_DATE() {
        return PR_DATE;
    }

    public void setPR_DATE(Date PR_DATE) {
        this.PR_DATE = PR_DATE;
    }

    public Date getEX_DATE() {
        return EX_DATE;
    }

    public void setEX_DATE(Date EX_DATE) {
        this.EX_DATE = EX_DATE;
    }

    public Long getNATIONAL_CODE() {
        return NATIONAL_CODE;
    }

    public void setNATIONAL_CODE(Long NATIONAL_CODE) {
        this.NATIONAL_CODE = NATIONAL_CODE;
    }

    public String getNUMBER() {
        return NUMBER;
    }

    public void setNUMBER(String NUMBER) {
        this.NUMBER = NUMBER;
    }

    public Long getUNIT_ID() {
        return UNIT_ID;
    }

    public void setUNIT_ID(Long UNIT_ID) {
        this.UNIT_ID = UNIT_ID;
    }

    public String getWEIGHT() {
        return WEIGHT;
    }

    public void setWEIGHT(String WEIGHT) {
        this.WEIGHT = WEIGHT;
    }

    public Long getWEIGHT_UNIT_ID() {
        return WEIGHT_UNIT_ID;
    }

    public void setWEIGHT_UNIT_ID(Long WEIGHT_UNIT_ID) {
        this.WEIGHT_UNIT_ID = WEIGHT_UNIT_ID;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public Long getPRICE_UNIT_ID() {
        return PRICE_UNIT_ID;
    }

    public void setPRICE_UNIT_ID(Long PRICE_UNIT_ID) {
        this.PRICE_UNIT_ID = PRICE_UNIT_ID;
    }

}
