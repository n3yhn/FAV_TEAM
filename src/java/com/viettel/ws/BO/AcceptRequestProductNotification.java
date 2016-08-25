package com.viettel.ws.BO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NhoDVT
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcceptRequestProductNotification")

public class AcceptRequestProductNotification {

    public AcceptRequestProductNotification() {

    }

    @XmlElement(name = "NSWFileCode")
    protected String NSWFileCode;
    @XmlElement(name = "ProductNotificationNo")
    protected String ProductNotificationNo;
    @XmlElement(name = "ProductNotificationDate")
    protected String ProductNotificationDate;
    @XmlElement(name = "AcceptNo")
    protected String AcceptNo;
    @XmlElement(name = "WithdrawDate")
    protected String WithdrawDate;
    @XmlElement(name = "Note")
    protected String Note;

    public String getNSWFileCode() {
        return NSWFileCode;
    }

    public void setNSWFileCode(String nSWFileCode) {
        NSWFileCode = nSWFileCode;
    }

    public String getProductNotificationNo() {
        return ProductNotificationNo;
    }

    public void setProductNotificationNo(String productNotificationNo) {
        ProductNotificationNo = productNotificationNo;
    }

    public String getProductNotificationDate() {
        return ProductNotificationDate;
    }

    public void setProductNotificationDate(String productNotificationDate) {
        ProductNotificationDate = productNotificationDate;
    }

    public String getAcceptNo() {
        return AcceptNo;
    }

    public void setAcceptNo(String acceptNo) {
        AcceptNo = acceptNo;
    }

    public String getWithdrawDate() {
        return WithdrawDate;
    }

    public void setWithdrawDate(String withdrawDate) {
        WithdrawDate = withdrawDate;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
