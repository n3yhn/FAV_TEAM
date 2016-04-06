/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.FORM;

import com.viettel.hqmc.BO.MainlyTarget;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public class TARGET {

    private String TARGET_NAME;
    private String UNIT_ID;
    private String MAX_LEVEL;

    public TARGET() {
    }

    public TARGET(String TARGET_NAME, String UNIT_ID, String MAX_LEVEL) {
        this.TARGET_NAME = TARGET_NAME;
        this.UNIT_ID = UNIT_ID;
        this.MAX_LEVEL = MAX_LEVEL;
    }

    public String getTARGET_NAME() {
        return TARGET_NAME;
    }

    public void setTARGET_NAME(String TARGET_NAME) {
        this.TARGET_NAME = TARGET_NAME;
    }

    public String getUNIT_ID() {
        return UNIT_ID;
    }

    public void setUNIT_ID(String UNIT_ID) {
        this.UNIT_ID = UNIT_ID;
    }

    public String getMAX_LEVEL() {
        return MAX_LEVEL;
    }

    public void setMAX_LEVEL(String MAX_LEVEL) {
        this.MAX_LEVEL = MAX_LEVEL;
    }

    public MainlyTarget toMainlyTarget() {
        MainlyTarget bo = new MainlyTarget();
        return bo;
    }

}
