/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import java.io.Serializable;
import java.math.BigInteger;
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
 * @author ducduit
 */
@Entity
@Table(name = "V_LOOKUP_HOMEPAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VLookupHomepage.findAll", query = "SELECT v FROM VLookupHomepage v"),
    @NamedQuery(name = "VLookupHomepage.findByFileId", query = "SELECT v FROM VLookupHomepage v WHERE v.fileId = :fileId"),
    @NamedQuery(name = "VLookupHomepage.findByProductName", query = "SELECT v FROM VLookupHomepage v WHERE v.productName = :productName"),
    @NamedQuery(name = "VLookupHomepage.findByBusinessName", query = "SELECT v FROM VLookupHomepage v WHERE v.businessName = :businessName"),
    @NamedQuery(name = "VLookupHomepage.findByBusinessAddress", query = "SELECT v FROM VLookupHomepage v WHERE v.businessAddress = :businessAddress"),
    @NamedQuery(name = "VLookupHomepage.findByProductTypeName", query = "SELECT v FROM VLookupHomepage v WHERE v.productTypeName = :productTypeName"),
    @NamedQuery(name = "VLookupHomepage.findByProductType", query = "SELECT v FROM VLookupHomepage v WHERE v.productType = :productType"),
    @NamedQuery(name = "VLookupHomepage.findByReceiptNo", query = "SELECT v FROM VLookupHomepage v WHERE v.receiptNo = :receiptNo"),
    @NamedQuery(name = "VLookupHomepage.findByReceiptDate", query = "SELECT v FROM VLookupHomepage v WHERE v.receiptDate = :receiptDate"),
    @NamedQuery(name = "VLookupHomepage.findByCode", query = "SELECT v FROM VLookupHomepage v WHERE v.code = :code"),
    @NamedQuery(name = "VLookupHomepage.findByIsDownload", query = "SELECT v FROM VLookupHomepage v WHERE v.isDownload = :isDownload"),
    @NamedQuery(name = "VLookupHomepage.findByStatus", query = "SELECT v FROM VLookupHomepage v WHERE v.status = :status"),
    @NamedQuery(name = "VLookupHomepage.findByIsExist", query = "SELECT v FROM VLookupHomepage v WHERE v.isExist = :isExist")})
public class VLookupHomepage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "FILE_ID")
    private BigInteger fileId;
    @Id
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "BUSINESS_NAME")
    private String businessName;
    @Column(name = "BUSINESS_ADDRESS")
    private String businessAddress;
    @Column(name = "PRODUCT_TYPE_NAME")
    private String productTypeName;
    @Column(name = "PRODUCT_TYPE")
    private Long productType;
    @Column(name = "RECEIPT_NO")
    private String receiptNo;
    @Column(name = "RECEIPT_DATE")
    @Temporal(TemporalType.DATE)
    private Date receiptDate;
    @Column(name = "CODE")
    private String code;
    @Column(name = "IS_DOWNLOAD")
    private Short isDownload;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "IS_EXIST")
    private BigInteger isExist;

    public VLookupHomepage() {
    }

    public BigInteger getFileId() {
        return fileId;
    }

    public void setFileId(BigInteger fileId) {
        this.fileId = fileId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public Long getProductType() {
        return productType;
    }

    public void setProductType(Long productType) {
        this.productType = productType;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Short getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(Short isDownload) {
        this.isDownload = isDownload;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public BigInteger getIsExist() {
        return isExist;
    }

    public void setIsExist(BigInteger isExist) {
        this.isExist = isExist;
    }
    
}
