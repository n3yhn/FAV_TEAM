/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.BO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Administrator
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncFileStatus311")
public class SyncFileStatus311 {
    @XmlElement(name = "NswFileCode")
    protected String NswFileCode;

    public SyncFileStatus311(String NswFileCode) {
        this.NswFileCode = NswFileCode;
    }

    public SyncFileStatus311() {
    }

    public String getNswFileCode() {
        return NswFileCode;
    }

    public void setNswFileCode(String NswFileCode) {
        this.NswFileCode = NswFileCode;
    }
    
    
}
