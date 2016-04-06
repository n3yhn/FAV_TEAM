/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

import java.util.Date;

/**
 *
 * @author DiuLTT
 */
public class PublishDocumentReportForm {
    private Long publishDocumentId;
    private Date publishDate;
    private String code;
    private Long documentTypeId;
    private String documentType;
    private String documentAbstract;
    private String receiveOutside;
    private String signer;
    private Long signId;
    private String receiveOutsideId;
    private String receiveInsideId;
    private String receiveInside;
    private Long bookId;
    private String bookNumber;
    private String bookName;
    private Long stt;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDocumentAbstract() {
        return documentAbstract;
    }

    public void setDocumentAbstract(String documentAbstract) {
        this.documentAbstract = documentAbstract;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Long getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Long getPublishDocumentId() {
        return publishDocumentId;
    }

    public void setPublishDocumentId(Long publishDocumentId) {
        this.publishDocumentId = publishDocumentId;
    }

    public String getReceiveInside() {
        return receiveInside;
    }

    public void setReceiveInside(String receiveInside) {
        this.receiveInside = receiveInside;
    }

    public String getReceiveInsideId() {
        return receiveInsideId;
    }

    public void setReceiveInsideId(String receiveInsideId) {
        this.receiveInsideId = receiveInsideId;
    }

    public String getReceiveOutside() {
        return receiveOutside;
    }

    public void setReceiveOutside(String receiveOutside) {
        this.receiveOutside = receiveOutside;
    }

    public String getReceiveOutsideId() {
        return receiveOutsideId;
    }

    public void setReceiveOutsideId(String receiveOutsideId) {
        this.receiveOutsideId = receiveOutsideId;
    }

    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public Long getStt() {
        return stt;
    }

    public void setStt(Long stt) {
        this.stt = stt;
    }
}
