/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.FORM;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ANNOUCE_RESULT {

    List<ERROR> error;
    private String BUSSINESS_A;
    private String BUSSINESS_B;
    private String ANNOUCE_NUMBER;
    private Date ANNOUNCE_DATE;
    private Long ANNOUCE_DEPT_ID;
    private Date ANNOUNCE_END_DATE;
    private Date START_DATE;
    private Date END_DATE;

    public ANNOUCE_RESULT() {
    }

    public ANNOUCE_RESULT(List<ERROR> error, String BUSSINESS_A, String BUSSINESS_B, String ANNOUCE_NUMBER, Date ANNOUNCE_DATE, Long ANNOUCE_DEPT_ID, Date ANNOUNCE_END_DATE, Date START_DATE, Date END_DATE) {
        this.error = error;
        this.BUSSINESS_A = BUSSINESS_A;
        this.BUSSINESS_B = BUSSINESS_B;
        this.ANNOUCE_NUMBER = ANNOUCE_NUMBER;
        this.ANNOUNCE_DATE = ANNOUNCE_DATE;
        this.ANNOUCE_DEPT_ID = ANNOUCE_DEPT_ID;
        this.ANNOUNCE_END_DATE = ANNOUNCE_END_DATE;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
    }

    public List<ERROR> getError() {
        return error;
    }

    public void setError(List<ERROR> error) {
        this.error = error;
    }

    public String getBUSSINESS_A() {
        return BUSSINESS_A;
    }

    public void setBUSSINESS_A(String BUSSINESS_A) {
        this.BUSSINESS_A = BUSSINESS_A;
    }

    public String getBUSSINESS_B() {
        return BUSSINESS_B;
    }

    public void setBUSSINESS_B(String BUSSINESS_B) {
        this.BUSSINESS_B = BUSSINESS_B;
    }

    public String getANNOUCE_NUMBER() {
        return ANNOUCE_NUMBER;
    }

    public void setANNOUCE_NUMBER(String ANNOUCE_NUMBER) {
        this.ANNOUCE_NUMBER = ANNOUCE_NUMBER;
    }

    public Date getANNOUNCE_DATE() {
        return ANNOUNCE_DATE;
    }

    public void setANNOUNCE_DATE(Date ANNOUNCE_DATE) {
        this.ANNOUNCE_DATE = ANNOUNCE_DATE;
    }

    public Long getANNOUCE_DEPT_ID() {
        return ANNOUCE_DEPT_ID;
    }

    public void setANNOUCE_DEPT_ID(Long ANNOUCE_DEPT_ID) {
        this.ANNOUCE_DEPT_ID = ANNOUCE_DEPT_ID;
    }

    public Date getANNOUNCE_END_DATE() {
        return ANNOUNCE_END_DATE;
    }

    public void setANNOUNCE_END_DATE(Date ANNOUNCE_END_DATE) {
        this.ANNOUNCE_END_DATE = ANNOUNCE_END_DATE;
    }

    public Date getSTART_DATE() {
        return START_DATE;
    }

    public void setSTART_DATE(Date START_DATE) {
        this.START_DATE = START_DATE;
    }

    public Date getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(Date END_DATE) {
        this.END_DATE = END_DATE;
    }

}
