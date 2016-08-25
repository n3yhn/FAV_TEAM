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
@XmlType(name = "feeNotice340")

public class FEE_NOTICE_340 {

    @XmlElement(name = "NswFileCode")
    protected String NswFileCode;
    @XmlElement(name = "Brand")
    protected String Brand;
    @XmlElement(name = "ProductName")
    protected String ProductName;
    @XmlElement(name = "Fee")
    protected String Fee;
    @XmlElement(name = "Content")
    protected String Content;

    public FEE_NOTICE_340() {
    }

    public FEE_NOTICE_340(String NswFileCode, String Brand, String ProductName, String Fee, String Content) {
        this.NswFileCode = NswFileCode;
        this.Brand = Brand;
        this.ProductName = ProductName;
        this.Fee = Fee;
        this.Content = Content;
    }

    public String getNswFileCode() {
        return NswFileCode;
    }

    public void setNswFileCode(String NswFileCode) {
        this.NswFileCode = NswFileCode;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getFee() {
        return Fee;
    }

    public void setFee(String Fee) {
        this.Fee = Fee;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

}
