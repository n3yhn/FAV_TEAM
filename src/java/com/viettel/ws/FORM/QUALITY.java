/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.FORM;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public class QUALITY {

    private String PRODUCT_PROCESS_DETAIL;
    private String CONTROL_TARGET;
    private String TECHNICAL_REGULATION;
    private String PATTERN_FREQUENCE;
    private String TEST_DEVICE;
    private String TEST_METHOD;
    private String NOTE_FORM;
    private String NOTE;

    public QUALITY() {
    }

    public QUALITY(String PRODUCT_PROCESS_DETAIL, String CONTROL_TARGET, String TECHNICAL_REGULATION, String PATTERN_FREQUENCE, String TEST_DEVICE, String TEST_METHOD, String NOTE_FORM, String NOTE) {
        this.PRODUCT_PROCESS_DETAIL = PRODUCT_PROCESS_DETAIL;
        this.CONTROL_TARGET = CONTROL_TARGET;
        this.TECHNICAL_REGULATION = TECHNICAL_REGULATION;
        this.PATTERN_FREQUENCE = PATTERN_FREQUENCE;
        this.TEST_DEVICE = TEST_DEVICE;
        this.TEST_METHOD = TEST_METHOD;
        this.NOTE_FORM = NOTE_FORM;
        this.NOTE = NOTE;
    }

    public String getPRODUCT_PROCESS_DETAIL() {
        return PRODUCT_PROCESS_DETAIL;
    }

    public void setPRODUCT_PROCESS_DETAIL(String PRODUCT_PROCESS_DETAIL) {
        this.PRODUCT_PROCESS_DETAIL = PRODUCT_PROCESS_DETAIL;
    }

    public String getCONTROL_TARGET() {
        return CONTROL_TARGET;
    }

    public void setCONTROL_TARGET(String CONTROL_TARGET) {
        this.CONTROL_TARGET = CONTROL_TARGET;
    }

    public String getTECHNICAL_REGULATION() {
        return TECHNICAL_REGULATION;
    }

    public void setTECHNICAL_REGULATION(String TECHNICAL_REGULATION) {
        this.TECHNICAL_REGULATION = TECHNICAL_REGULATION;
    }

    public String getPATTERN_FREQUENCE() {
        return PATTERN_FREQUENCE;
    }

    public void setPATTERN_FREQUENCE(String PATTERN_FREQUENCE) {
        this.PATTERN_FREQUENCE = PATTERN_FREQUENCE;
    }

    public String getTEST_DEVICE() {
        return TEST_DEVICE;
    }

    public void setTEST_DEVICE(String TEST_DEVICE) {
        this.TEST_DEVICE = TEST_DEVICE;
    }

    public String getTEST_METHOD() {
        return TEST_METHOD;
    }

    public void setTEST_METHOD(String TEST_METHOD) {
        this.TEST_METHOD = TEST_METHOD;
    }

    public String getNOTE_FORM() {
        return NOTE_FORM;
    }

    public void setNOTE_FORM(String NOTE_FORM) {
        this.NOTE_FORM = NOTE_FORM;
    }

    public String getNOTE() {
        return NOTE;
    }

    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }

}
