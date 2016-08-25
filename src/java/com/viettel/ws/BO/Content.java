package com.viettel.ws.BO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Content", propOrder = {
    "receiveDate",
    "rejectDate",
    "profilePublishedCosmetics",
    "attachment",
    "dnRequestChange310",
    "dnRequestDelete320",
    "cbRequestChange330",
    "feeNotice340",
    "permit370"
})
public class Content {

    public Content() {
    }

    @XmlElement(name = "ReceiveDate")
    private String receiveDate;

    @XmlElement(name = "RejectDate")
    private String rejectDate;

    @XmlElement(name = "ProfilePublishedCosmetics")
    protected ProfilePublishedCosmetics profilePublishedCosmetics;

    @XmlElement(name = "Attachment")
    private Attachment attachment;

    //DNREQUEST_CHANGE_310
    @XmlElement(name = "DnRequestChange310")
    private DNREQUEST_CHANGE_310 dnRequestChange310;

    //320	Yêu cầu hủy hồ sơ
    @XmlElement(name = "dnRequestDelete320")
    private DNREQUEST_DELETE_320 dnRequestDelete320;

    //330	Thông báo sửa đổi bổ sung hồ sơ
    @XmlElement(name = "cbRequestChange330")
    private CBREQUEST_CHANGE_330 cbRequestChange330;

    //340	Thông báo lệ phí
    @XmlElement(name = "feeNotice340")
    private FEE_NOTICE_340 feeNotice340;
    //370	Gửi kết quả kiểm tra hệ thống

    @XmlElement(name = "permit370")
    private PERMIT_370 permit370;

    public ProfilePublishedCosmetics getProfilePublishedCosmetics() {
        return profilePublishedCosmetics;
    }

    public void setProfilePublishedCosmetics(ProfilePublishedCosmetics value) {
        this.profilePublishedCosmetics = value;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(String rejectDate) {
        this.rejectDate = rejectDate;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public DNREQUEST_CHANGE_310 getDnRequestChange310() {
        return dnRequestChange310;
    }

    public void setDnRequestChange310(DNREQUEST_CHANGE_310 dnRequestChange310) {
        this.dnRequestChange310 = dnRequestChange310;
    }

    public DNREQUEST_DELETE_320 getDnRequestDelete320() {
        return dnRequestDelete320;
    }

    public void setDnRequestDelete320(DNREQUEST_DELETE_320 dnRequestDelete320) {
        this.dnRequestDelete320 = dnRequestDelete320;
    }

    public CBREQUEST_CHANGE_330 getCbRequestChange330() {
        return cbRequestChange330;
    }

    public void setCbRequestChange330(CBREQUEST_CHANGE_330 cbRequestChange330) {
        this.cbRequestChange330 = cbRequestChange330;
    }

    public FEE_NOTICE_340 getFeeNotice340() {
        return feeNotice340;
    }

    public void setFeeNotice340(FEE_NOTICE_340 feeNotice340) {
        this.feeNotice340 = feeNotice340;
    }

    public PERMIT_370 getPermit370() {
        return permit370;
    }

    public void setPermit370(PERMIT_370 permit370) {
        this.permit370 = permit370;
    }

}
