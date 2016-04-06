/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.FORM;

import com.viettel.voffice.database.BO.VoAttachs;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public class ATTACH {

    private String ATTACH_NAME;
    private String ATTACH_DES;
    private Byte[] ATTACH_DATA;

    public ATTACH() {
    }

    public ATTACH(String ATTACH_NAME, String ATTACH_DES, Byte[] ATTACH_DATA) {
        this.ATTACH_NAME = ATTACH_NAME;
        this.ATTACH_DES = ATTACH_DES;
        this.ATTACH_DATA = ATTACH_DATA;
    }

    public String getATTACH_NAME() {
        return ATTACH_NAME;
    }

    public void setATTACH_NAME(String ATTACH_NAME) {
        this.ATTACH_NAME = ATTACH_NAME;
    }

    public String getATTACH_DES() {
        return ATTACH_DES;
    }

    public void setATTACH_DES(String ATTACH_DES) {
        this.ATTACH_DES = ATTACH_DES;
    }

    public Byte[] getATTACH_DATA() {
        return ATTACH_DATA;
    }

    public void setATTACH_DATA(Byte[] ATTACH_DATA) {
        this.ATTACH_DATA = ATTACH_DATA;
    }

    public VoAttachs wsToBO() {
        VoAttachs bo = new VoAttachs();
        bo.setAttachName(ATTACH_NAME);
        bo.setAttachDes(ATTACH_DES);
        return bo;
    }
}
