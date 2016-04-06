/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

/**
 *
 * @author GPCP_BINHNT53
 */
public class ProcessReportForm {

    private Long receiveUserId;
    private String receiveUser;
    private Long statusOne;
    private Long statusTwo;
    private Long statusThree;

    public ProcessReportForm(Long receiveUserId, String receiveUser, Long statusOne, Long statusTwo, Long statusThree) {
        this.receiveUserId = receiveUserId;
        this.receiveUser = receiveUser;
        this.statusOne = statusOne;
        this.statusTwo = statusTwo;
        this.statusThree = statusThree;
    }

    public ProcessReportForm() {
    }

    public Long getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Long receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public Long getStatusOne() {
        return statusOne;
    }

    public void setStatusOne(Long statusOne) {
        this.statusOne = statusOne;
    }

    public Long getStatusTwo() {
        return statusTwo;
    }

    public void setStatusTwo(Long statusTwo) {
        this.statusTwo = statusTwo;
    }

    public Long getStatusThree() {
        return statusThree;
    }

    public void setStatusThree(Long statusThree) {
        this.statusThree = statusThree;
    }
    
}
