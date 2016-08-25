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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PERMIT_370")
public class PERMIT_370 {

    @XmlElement(name = "NswFileCode")
    protected String NswFileCode;
    @XmlElement(name = "Status")
    protected Long Status;
    @XmlElement(name = "StatusName")
    protected String StatusName;

    public PERMIT_370() {
    }

    public PERMIT_370(String NswFileCode, Long Status, String StatusName) {
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

    public Long getStatus() {
        return Status;
    }

    public void setStatus(Long Status) {
        this.Status = Status;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String StatusName) {
        this.StatusName = StatusName;
    }
    
    
}
