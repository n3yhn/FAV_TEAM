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
public class COMPLAINTS {

    String FILE_CODE;
    String BUSSINESS_CODE;
    String OLD_DEPT_CODE;
    Long CURRENT_STATE;
    String NEXT_DEPT_CODE;
    String DESCRIPTION;

    public COMPLAINTS(String FILE_CODE, String BUSSINESS_CODE, String OLD_DEPT_CODE, Long CURRENT_STATE, String NEXT_DEPT_CODE, String DESCRIPTION) {
        this.FILE_CODE = FILE_CODE;
        this.BUSSINESS_CODE = BUSSINESS_CODE;
        this.OLD_DEPT_CODE = OLD_DEPT_CODE;
        this.CURRENT_STATE = CURRENT_STATE;
        this.NEXT_DEPT_CODE = NEXT_DEPT_CODE;
        this.DESCRIPTION = DESCRIPTION;
    }

    public COMPLAINTS() {
    }

    public String getFILE_CODE() {
        return FILE_CODE;
    }

    public void setFILE_CODE(String FILE_CODE) {
        this.FILE_CODE = FILE_CODE;
    }

    public String getBUSSINESS_CODE() {
        return BUSSINESS_CODE;
    }

    public void setBUSSINESS_CODE(String BUSSINESS_CODE) {
        this.BUSSINESS_CODE = BUSSINESS_CODE;
    }

    public String getOLD_DEPT_CODE() {
        return OLD_DEPT_CODE;
    }

    public void setOLD_DEPT_CODE(String OLD_DEPT_CODE) {
        this.OLD_DEPT_CODE = OLD_DEPT_CODE;
    }

    public Long getCURRENT_STATE() {
        return CURRENT_STATE;
    }

    public void setCURRENT_STATE(Long CURRENT_STATE) {
        this.CURRENT_STATE = CURRENT_STATE;
    }

    public String getNEXT_DEPT_CODE() {
        return NEXT_DEPT_CODE;
    }

    public void setNEXT_DEPT_CODE(String NEXT_DEPT_CODE) {
        this.NEXT_DEPT_CODE = NEXT_DEPT_CODE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

}
