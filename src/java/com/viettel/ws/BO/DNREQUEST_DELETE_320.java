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
@XmlType(name = "dnRequestDelete320")

public class DNREQUEST_DELETE_320 {

    @XmlElement(name = "NswFileCode")
    protected String NswFileCode;
    @XmlElement(name = "Reason")
    protected String Reason;
    @XmlElement(name = "createDate")
    protected String createDate;

    public DNREQUEST_DELETE_320() {
    }

    public DNREQUEST_DELETE_320(String NswFileCode, String Reason, String createDate) {
        this.NswFileCode = NswFileCode;
        this.Reason = Reason;
        this.createDate = createDate;
    }

    public String getNswFileCode() {
        return NswFileCode;
    }

    public void setNswFileCode(String NswFileCode) {
        this.NswFileCode = NswFileCode;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
