/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtit_binhnt53
 */
@Entity
@Table(name = "REGISTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Register.findAll", query = "SELECT r FROM Register r"),
    @NamedQuery(name = "Register.findByRegisterId", query = "SELECT r FROM Register r WHERE r.registerId = :registerId"),
    @NamedQuery(name = "Register.findByManageEmail", query = "SELECT r FROM Register r WHERE r.manageEmail = :manageEmail"),
    @NamedQuery(name = "Register.findByUserFullName", query = "SELECT r FROM Register r WHERE r.userFullName = :userFullName"),
    @NamedQuery(name = "Register.findByUserTelephone", query = "SELECT r FROM Register r WHERE r.userTelephone = :userTelephone"),
    @NamedQuery(name = "Register.findByUserMobile", query = "SELECT r FROM Register r WHERE r.userMobile = :userMobile"),
    @NamedQuery(name = "Register.findByBusinessTypeId", query = "SELECT r FROM Register r WHERE r.businessTypeId = :businessTypeId"),
    @NamedQuery(name = "Register.findByBusinessTypeName", query = "SELECT r FROM Register r WHERE r.businessTypeName = :businessTypeName"),
    @NamedQuery(name = "Register.findByBusinessNameVi", query = "SELECT r FROM Register r WHERE r.businessNameVi = :businessNameVi"),
    @NamedQuery(name = "Register.findByBusinessNameEng", query = "SELECT r FROM Register r WHERE r.businessNameEng = :businessNameEng"),
    @NamedQuery(name = "Register.findByBusinessNameAlias", query = "SELECT r FROM Register r WHERE r.businessNameAlias = :businessNameAlias"),
    @NamedQuery(name = "Register.findByBusinessTaxCode", query = "SELECT r FROM Register r WHERE r.businessTaxCode = :businessTaxCode"),
    @NamedQuery(name = "Register.findByBusinessLicense", query = "SELECT r FROM Register r WHERE r.businessLicense = :businessLicense"),
    @NamedQuery(name = "Register.findByBusinessAdd", query = "SELECT r FROM Register r WHERE r.businessAdd = :businessAdd"),
    @NamedQuery(name = "Register.findByBusinessProvince", query = "SELECT r FROM Register r WHERE r.businessProvince = :businessProvince"),
    @NamedQuery(name = "Register.findByBusinessTelephone", query = "SELECT r FROM Register r WHERE r.businessTelephone = :businessTelephone"),
    @NamedQuery(name = "Register.findByBusinessFax", query = "SELECT r FROM Register r WHERE r.businessFax = :businessFax"),
    @NamedQuery(name = "Register.findByBusinessWebsite", query = "SELECT r FROM Register r WHERE r.businessWebsite = :businessWebsite"),
    @NamedQuery(name = "Register.findByBusinessLawRep", query = "SELECT r FROM Register r WHERE r.businessLawRep = :businessLawRep"),
    @NamedQuery(name = "Register.findByDescription", query = "SELECT r FROM Register r WHERE r.description = :description"),
    @NamedQuery(name = "Register.findByBusinessEstablishYear", query = "SELECT r FROM Register r WHERE r.businessEstablishYear = :businessEstablishYear"),
    @NamedQuery(name = "Register.findByManagePassword", query = "SELECT r FROM Register r WHERE r.managePassword = :managePassword"),
    @NamedQuery(name = "Register.findByUserEmail", query = "SELECT r FROM Register r WHERE r.userEmail = :userEmail"),
    @NamedQuery(name = "Register.findByStatus", query = "SELECT r FROM Register r WHERE r.status = :status"),
    @NamedQuery(name = "Register.findByReason", query = "SELECT r FROM Register r WHERE r.reason = :reason")})
public class Register implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "REGISTER_SEQ", sequenceName = "REGISTER_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGISTER_SEQ")
    @Basic(optional = false)
    @Column(name = "REGISTER_ID")
    private Long registerId;
    @Column(name = "MANAGE_EMAIL")
    private String manageEmail;
    @Column(name = "USER_FULL_NAME")
    private String userFullName;
    @Column(name = "USER_TELEPHONE")
    private String userTelephone;
    @Column(name = "USER_MOBILE")
    private String userMobile;
    @Column(name = "BUSINESS_TYPE_ID")
    private Long businessTypeId;
    @Column(name = "BUSINESS_TYPE_NAME")
    private String businessTypeName;
    @Column(name = "BUSINESS_NAME_VI")
    private String businessNameVi;
    @Column(name = "BUSINESS_NAME_ENG")
    private String businessNameEng;
    @Column(name = "BUSINESS_NAME_ALIAS")
    private String businessNameAlias;
    @Column(name = "BUSINESS_TAX_CODE")
    private String businessTaxCode;
    @Column(name = "BUSINESS_LICENSE")
    private String businessLicense;
    @Column(name = "BUSINESS_ADD")
    private String businessAdd;
    @Column(name = "BUSINESS_PROVINCE")
    private String businessProvince;
    @Column(name = "BUSINESS_TELEPHONE")
    private String businessTelephone;
    @Column(name = "BUSINESS_FAX")
    private String businessFax;
    @Column(name = "BUSINESS_WEBSITE")
    private String businessWebsite;
    @Column(name = "BUSINESS_LAW_REP")
    private String businessLawRep;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "BUSINESS_ESTABLISH_YEAR")
    private String businessEstablishYear;
    @Column(name = "MANAGE_PASSWORD")
    private String managePassword;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "REASON")
    private String reason;
    @Column(name = "BUSINESS_PROVINCE_ID")
    private Long businessProvinceId;
    @Column(name = "POS_ID")
    private Long posId;
    @Column(name = "POS_NAME")
    private String posName;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "GOVERNING_BODY")
    private String governingBody;
    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;
    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public Register() {
    }

    public Register(Long registerId) {
        this.registerId = registerId;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public String getManageEmail() {
        return manageEmail;
    }

    public void setManageEmail(String manageEmail) {
        this.manageEmail = manageEmail;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public String getBusinessNameVi() {
        return businessNameVi;
    }

    public void setBusinessNameVi(String businessNameVi) {
        this.businessNameVi = businessNameVi;
    }

    public String getBusinessNameEng() {
        return businessNameEng;
    }

    public void setBusinessNameEng(String businessNameEng) {
        this.businessNameEng = businessNameEng;
    }

    public String getBusinessNameAlias() {
        return businessNameAlias;
    }

    public void setBusinessNameAlias(String businessNameAlias) {
        this.businessNameAlias = businessNameAlias;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessAdd() {
        return businessAdd;
    }

    public void setBusinessAdd(String businessAdd) {
        this.businessAdd = businessAdd;
    }

    public String getBusinessProvince() {
        return businessProvince;
    }

    public void setBusinessProvince(String businessProvince) {
        this.businessProvince = businessProvince;
    }

    public String getBusinessTelephone() {
        return businessTelephone;
    }

    public void setBusinessTelephone(String businessTelephone) {
        this.businessTelephone = businessTelephone;
    }

    public String getBusinessFax() {
        return businessFax;
    }

    public void setBusinessFax(String businessFax) {
        this.businessFax = businessFax;
    }

    public String getBusinessWebsite() {
        return businessWebsite;
    }

    public void setBusinessWebsite(String businessWebsite) {
        this.businessWebsite = businessWebsite;
    }

    public String getBusinessLawRep() {
        return businessLawRep;
    }

    public void setBusinessLawRep(String businessLawRep) {
        this.businessLawRep = businessLawRep;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessEstablishYear() {
        return businessEstablishYear;
    }

    public void setBusinessEstablishYear(String businessEstablishYear) {
        this.businessEstablishYear = businessEstablishYear;
    }

    public String getManagePassword() {
        return managePassword;
    }

    public void setManagePassword(String managePassword) {
        this.managePassword = managePassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getBusinessProvinceId() {
        return businessProvinceId;
    }

    public void setBusinessProvinceId(Long businessProvinceId) {
        this.businessProvinceId = businessProvinceId;
    }

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getBusinessTaxCode() {
        return businessTaxCode;
    }

    public void setBusinessTaxCode(String businessTaxCode) {
        this.businessTaxCode = businessTaxCode;
    }
//141213 binhnt53

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getGoverningBody() {
        return governingBody;
    }

    public void setGoverningBody(String governingBody) {
        this.governingBody = governingBody;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registerId != null ? registerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Register)) {
            return false;
        }
        Register other = (Register) object;
        if ((this.registerId == null && other.registerId != null) || (this.registerId != null && !this.registerId.equals(other.registerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.Register[ registerId=" + registerId + " ]";
    }
}
