/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.BO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HanPT1
 */
@Entity
@Table(name = "BOOK_DOCUMENT_EXPORT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookDocumentExport.findAll", query = "SELECT b FROM BookDocumentExport b"),
    @NamedQuery(name = "BookDocumentExport.findByReceiveDate", query = "SELECT b FROM BookDocumentExport b WHERE b.receiveDate = :receiveDate"),
    @NamedQuery(name = "BookDocumentExport.findByBookNumber", query = "SELECT b FROM BookDocumentExport b WHERE b.bookNumber = :bookNumber"),
    @NamedQuery(name = "BookDocumentExport.findByBookId", query = "SELECT b FROM BookDocumentExport b WHERE b.bookId = :bookId"),
    @NamedQuery(name = "BookDocumentExport.findByPublisherId", query = "SELECT b FROM BookDocumentExport b WHERE b.publisherId = :publisherId"),
    @NamedQuery(name = "BookDocumentExport.findByPublisherName", query = "SELECT b FROM BookDocumentExport b WHERE b.publisherName = :publisherName"),
    @NamedQuery(name = "BookDocumentExport.findByDocumentCode", query = "SELECT b FROM BookDocumentExport b WHERE b.documentCode = :documentCode"),
    @NamedQuery(name = "BookDocumentExport.findByDocumentDate", query = "SELECT b FROM BookDocumentExport b WHERE b.documentDate = :documentDate"),
    @NamedQuery(name = "BookDocumentExport.findByDocumentAbstract", query = "SELECT b FROM BookDocumentExport b WHERE b.documentAbstract = :documentAbstract"),
    @NamedQuery(name = "BookDocumentExport.findByOfficeLeaderId", query = "SELECT b FROM BookDocumentExport b WHERE b.officeLeaderId = :officeLeaderId"),
    @NamedQuery(name = "BookDocumentExport.findByOfficeLeaderName", query = "SELECT b FROM BookDocumentExport b WHERE b.officeLeaderName = :officeLeaderName"),
    @NamedQuery(name = "BookDocumentExport.findByOfficeId", query = "SELECT b FROM BookDocumentExport b WHERE b.officeId = :officeId"),
    @NamedQuery(name = "BookDocumentExport.findByGroupCreate", query = "SELECT b FROM BookDocumentExport b WHERE b.groupCreate = :groupCreate"),
    @NamedQuery(name = "BookDocumentExport.findByUserCreate", query = "SELECT b FROM BookDocumentExport b WHERE b.userCreate = :userCreate"),
    @NamedQuery(name = "BookDocumentExport.findByReceiveUserId", query = "SELECT b FROM BookDocumentExport b WHERE b.receiveUserId = :receiveUserId"),
    @NamedQuery(name = "BookDocumentExport.findByDocumentTypeId", query = "SELECT b FROM BookDocumentExport b WHERE b.documentTypeId = :documentTypeId"),
    @NamedQuery(name = "BookDocumentExport.findByReceiveGroupId", query = "SELECT b FROM BookDocumentExport b WHERE b.receiveGroupId = :receiveGroupId")})
public class BookDocumentExport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "RECEIVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date receiveDate;
    @Column(name = "BOOK_NUMBER")
    private String bookNumber;
    @Column(name = "BOOK_ID")
    private Long bookId;
    @Column(name = "PUBLISHER_ID")
    private String publisherId;
    @Column(name = "PUBLISHER_NAME")
    private String publisherName;
    @Column(name = "DOCUMENT_CODE")
    private String documentCode;
    @Column(name = "DOCUMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date documentDate;
    @Column(name = "DOCUMENT_ABSTRACT")
    private String documentAbstract;
    @Column(name = "OFFICE_LEADER_ID")
    private Long officeLeaderId;
    @Column(name = "OFFICE_LEADER_NAME")
    private String officeLeaderName;
    @Column(name = "OFFICE_ID")
    private Long officeId;
    @Column(name = "GROUP_CREATE")
    private Long groupCreate;
    @Column(name = "USER_CREATE")
    private Long userCreate;
    @Column(name = "RECEIVE_USER_ID")
    private Long receiveUserId;
    @Column(name = "DOCUMENT_TYPE_ID")
    private Long documentTypeId;
    @Column(name = "RECEIVE_GROUP_ID")
    private Long receiveGroupId;  
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "IS_NEED_REPLY_DEADLINE")
    @Temporal(TemporalType.DATE)
    private Date isNeedReplyDeadline;
    
    @Column(name = "DOCUMENT_RECEIVE_ID")
    @Id
    private Long documentReceiveId;
    

    public BookDocumentExport() {
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentAbstract() {
        return documentAbstract;
    }

    public void setDocumentAbstract(String documentAbstract) {
        this.documentAbstract = documentAbstract;
    }

    public Long getOfficeLeaderId() {
        return officeLeaderId;
    }

    public void setOfficeLeaderId(Long officeLeaderId) {
        this.officeLeaderId = officeLeaderId;
    }

    public String getOfficeLeaderName() {
        return officeLeaderName;
    }

    public void setOfficeLeaderName(String officeLeaderName) {
        this.officeLeaderName = officeLeaderName;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Long getGroupCreate() {
        return groupCreate;
    }

    public void setGroupCreate(Long groupCreate) {
        this.groupCreate = groupCreate;
    }

    public Long getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Long userCreate) {
        this.userCreate = userCreate;
    }

    public Long getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Long receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public Long getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Long getReceiveGroupId() {
        return receiveGroupId;
    }

    public void setReceiveGroupId(Long receiveGroupId) {
        this.receiveGroupId = receiveGroupId;
    }

    public Long getDocumentReceiveId() {
        return documentReceiveId;
    }

    public void setDocumentReceiveId(Long documentReceiveId) {
        this.documentReceiveId = documentReceiveId;
    }

    public Date getIsNeedReplyDeadline() {
        return isNeedReplyDeadline;
    }

    public void setIsNeedReplyDeadline(Date isNeedReplyDeadline) {
        this.isNeedReplyDeadline = isNeedReplyDeadline;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

  }
