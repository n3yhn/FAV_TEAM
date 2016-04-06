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
public class ASSIGNMENT_QUERY {

    private String BUSSINESS_A;
    private String BUSSINESS_B;
    private String ANNOUNCE_NUMBER;

    public ASSIGNMENT_QUERY(String BUSSINESS_A, String BUSSINESS_B, String ANNOUNCE_NUMBER) {
        this.BUSSINESS_A = BUSSINESS_A;
        this.BUSSINESS_B = BUSSINESS_B;
        this.ANNOUNCE_NUMBER = ANNOUNCE_NUMBER;
    }

    public ASSIGNMENT_QUERY() {
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

    public String getANNOUNCE_NUMBER() {
        return ANNOUNCE_NUMBER;
    }

    public void setANNOUNCE_NUMBER(String ANNOUNCE_NUMBER) {
        this.ANNOUNCE_NUMBER = ANNOUNCE_NUMBER;
    }

}
