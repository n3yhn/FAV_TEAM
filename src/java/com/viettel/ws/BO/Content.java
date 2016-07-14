package com.viettel.ws.BO;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Content")
public class Content {

    public Content() {
    }

    @XmlElement(name = "ProfilePublishedCosmetics")
    protected ProfilePublishedCosmetics profilePublishedCosmetics;
    @XmlElement(name = "ReceiveDate")
    private String ReceiveDate;
    @XmlElement(name = "RejectDate")
    private String RejectDate;
    @XmlElement(name = "Attachment")
    private Attachment Attachment;
    @XmlElement(name = "AcceptRequestProductNotification")
    private AcceptRequestProductNotification AcceptRequestProductNotification;

//DnRequestChange 310
    @XmlElement(name = "DnRequestChange")
    private DnRequestChange dnRequestChange;

    //DnRequestDelete 320
    @XmlElement(name = "DnRequestDelete")
    private DnRequestDelete dnRequestDelete;

    public ProfilePublishedCosmetics getProfilePublishedCosmetics() {
        return profilePublishedCosmetics;
    }

    public void setProfilePublishedCosmetics(ProfilePublishedCosmetics value) {
        this.profilePublishedCosmetics = value;
    }

    public String getReceiveDate() {
        return ReceiveDate;
    }

    public void setReceiveDate(String ReceiveDate) {
        this.ReceiveDate = ReceiveDate;
    }

    public String getRejectDate() {
        return RejectDate;
    }

    public void setRejectDate(String RejectDate) {
        this.RejectDate = RejectDate;
    }

    public Attachment getAttachment() {
        return Attachment;
    }

    public void setAttachment(Attachment Attachment) {
        this.Attachment = Attachment;
    }

    public AcceptRequestProductNotification getAcceptRequestProductNotification() {
        return AcceptRequestProductNotification;
    }

    public void setAcceptRequestProductNotification(AcceptRequestProductNotification AcceptRequestProductNotification) {
        this.AcceptRequestProductNotification = AcceptRequestProductNotification;
    }

    public DnRequestChange getDnRequestChange() {
        return dnRequestChange;
    }

    public void setDnRequestChange(DnRequestChange dnRequestChange) {
        this.dnRequestChange = dnRequestChange;
    }

    public DnRequestDelete getDnRequestDelete() {
        return dnRequestDelete;
    }

    public void setDnRequestDelete(DnRequestDelete dnRequestDelete) {
        this.dnRequestDelete = dnRequestDelete;
    }

}
