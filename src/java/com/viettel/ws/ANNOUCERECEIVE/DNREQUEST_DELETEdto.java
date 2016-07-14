/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.ANNOUCERECEIVE;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Administrator
 */
public class DNREQUEST_DELETEdto {

    /*
    NSW_FILE_CODE
REASON
CREATE_DATE

     */

    public DNREQUEST_DELETEdto() {
    }

    @XmlElement(name = "NswFileCode")
    protected String NswFileCode;
    @XmlElement(name = "Reason")
    protected String Reason;
    @XmlElement(name = "CreateDate")
    protected String CreateDate;

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
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }
}
