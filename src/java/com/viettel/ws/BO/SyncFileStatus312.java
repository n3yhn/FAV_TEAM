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
@XmlType(name = "SyncFileStatus312")

public class SyncFileStatus312 {
    @XmlElement(name = "NswFileCode")
    protected String NswFileCode;
    @XmlElement(name = "Status")
    protected String Status;
    @XmlElement(name = "StatusName")
    protected String StatusName;

    public SyncFileStatus312() {
    }

    public SyncFileStatus312(String NswFileCode, String Status, String StatusName) {
        this.NswFileCode = NswFileCode;
        this.Status = Status;
        this.StatusName = StatusName;
    }

    public String getNswFileCode() {
        return NswFileCode;
    }

    public void setNswFileCode(String NswFileCode) {
        this.NswFileCode = NswFileCode;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String StatusName) {
        this.StatusName = StatusName;
    }
    
    
}
