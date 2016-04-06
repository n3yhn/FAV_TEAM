/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.FORM;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class RESULT {

    private List<ERROR> lstERROR;
    private Long RESULT;

    public RESULT(List<ERROR> lstERROR, Long RESULT) {
        this.lstERROR = lstERROR;
        this.RESULT = RESULT;
    }

    public RESULT() {
    }

    public List<ERROR> getLstERROR() {
        return lstERROR;
    }

    public void setLstERROR(List<ERROR> lstERROR) {
        this.lstERROR = lstERROR;
    }

    public Long getRESULT() {
        return RESULT;
    }

    public void setRESULT(Long RESULT) {
        this.RESULT = RESULT;
    }

}
