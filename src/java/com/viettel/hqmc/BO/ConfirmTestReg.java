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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtit_havm2
 */
@Entity
@Table(name = "CONFIRM_TEST_REG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfirmTestReg.findAll", query = "SELECT c FROM ConfirmTestReg c"),
    @NamedQuery(name = "ConfirmTestReg.findByConfirmTestRegId", query = "SELECT c FROM ConfirmTestReg c WHERE c.confirmTestRegId = :confirmTestRegId"),
    @NamedQuery(name = "ConfirmTestReg.findByRegisterNo", query = "SELECT c FROM ConfirmTestReg c WHERE c.registerNo = :registerNo"),
    @NamedQuery(name = "ConfirmTestReg.findByRegisterDate", query = "SELECT c FROM ConfirmTestReg c WHERE c.registerDate = :registerDate"),
    @NamedQuery(name = "ConfirmTestReg.findByRegisterSummary", query = "SELECT c FROM ConfirmTestReg c WHERE c.registerSummary = :registerSummary"),
    @NamedQuery(name = "ConfirmTestReg.findByTestMethodName", query = "SELECT c FROM ConfirmTestReg c WHERE c.testMethodName = :testMethodName"),
    @NamedQuery(name = "ConfirmTestReg.findByTestMethodId", query = "SELECT c FROM ConfirmTestReg c WHERE c.testMethodId = :testMethodId"),
    @NamedQuery(name = "ConfirmTestReg.findByCustomsFormNo", query = "SELECT c FROM ConfirmTestReg c WHERE c.customsFormNo = :customsFormNo"),
    @NamedQuery(name = "ConfirmTestReg.findByAgencyRepresentName", query = "SELECT c FROM ConfirmTestReg c WHERE c.agencyRepresentName = :agencyRepresentName"),
    @NamedQuery(name = "ConfirmTestReg.findByAgencyRepresentId", query = "SELECT c FROM ConfirmTestReg c WHERE c.agencyRepresentId = :agencyRepresentId"),
    @NamedQuery(name = "ConfirmTestReg.findBySignerName", query = "SELECT c FROM ConfirmTestReg c WHERE c.signerName = :signerName"),
    @NamedQuery(name = "ConfirmTestReg.findBySignerId", query = "SELECT c FROM ConfirmTestReg c WHERE c.signerId = :signerId"),
    @NamedQuery(name = "ConfirmTestReg.findBySignDate", query = "SELECT c FROM ConfirmTestReg c WHERE c.signDate = :signDate"),
    @NamedQuery(name = "ConfirmTestReg.findBySignAdd", query = "SELECT c FROM ConfirmTestReg c WHERE c.signAdd = :signAdd"),
    @NamedQuery(name = "ConfirmTestReg.findByIsActive", query = "SELECT c FROM ConfirmTestReg c WHERE c.isActive = :isActive")})
public class ConfirmTestReg implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "CONFIRM_TEST_REG", sequenceName = "CONFIRM_TEST_REG")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONFIRM_TEST_REG")
    @Basic(optional = false)
    @Column(name = "CONFIRM_TEST_REG_ID")
    private Long confirmTestRegId;
    @Column(name = "REGISTER_NO")
    private String registerNo;
    @Column(name = "REGISTER_DATE")
    @Temporal(TemporalType.DATE)
    private Date registerDate;
    @Column(name = "REGISTER_SUMMARY")
    private String registerSummary;
    @Column(name = "TEST_METHOD_NAME")
    private String testMethodName;
    @Column(name = "TEST_METHOD_ID")
    private Long testMethodId;
    @Column(name = "CUSTOMS_FORM_NO")
    private String customsFormNo;
    @Column(name = "AGENCY_REPRESENT_NAME")
    private String agencyRepresentName;
    @Column(name = "AGENCY_REPRESENT_ID")
    private Long agencyRepresentId;
    @Column(name = "SIGNER_NAME")
    private String signerName;
    @Column(name = "SIGNER_ID")
    private Long signerId;
    @Column(name = "SIGN_DATE")
    @Temporal(TemporalType.DATE)
    private Date signDate;
    @Column(name = "SIGN_ADD")
    private String signAdd;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "TEST_REGISTRATION_ID")
    private Long testRegistrationId;

    public ConfirmTestReg() {
    }

    public ConfirmTestReg(Long confirmTestRegId) {
        this.confirmTestRegId = confirmTestRegId;
    }

    public Long getConfirmTestRegId() {
        return confirmTestRegId;
    }

    public void setConfirmTestRegId(Long confirmTestRegId) {
        this.confirmTestRegId = confirmTestRegId;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterSummary() {
        return registerSummary;
    }

    public void setRegisterSummary(String registerSummary) {
        this.registerSummary = registerSummary;
    }

    public String getTestMethodName() {
        return testMethodName;
    }

    public void setTestMethodName(String testMethodName) {
        this.testMethodName = testMethodName;
    }

    public Long getTestMethodId() {
        return testMethodId;
    }

    public void setTestMethodId(Long testMethodId) {
        this.testMethodId = testMethodId;
    }

    public String getCustomsFormNo() {
        return customsFormNo;
    }

    public void setCustomsFormNo(String customsFormNo) {
        this.customsFormNo = customsFormNo;
    }

    public String getAgencyRepresentName() {
        return agencyRepresentName;
    }

    public void setAgencyRepresentName(String agencyRepresentName) {
        this.agencyRepresentName = agencyRepresentName;
    }

    public Long getAgencyRepresentId() {
        return agencyRepresentId;
    }

    public void setAgencyRepresentId(Long agencyRepresentId) {
        this.agencyRepresentId = agencyRepresentId;
    }

    public String getSignerName() {
        return signerName;
    }

    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }

    public Long getSignerId() {
        return signerId;
    }

    public void setSignerId(Long signerId) {
        this.signerId = signerId;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getSignAdd() {
        return signAdd;
    }

    public void setSignAdd(String signAdd) {
        this.signAdd = signAdd;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getTestRegistrationId() {
        return testRegistrationId;
    }

    public void setTestRegistrationId(Long testRegistrationId) {
        this.testRegistrationId = testRegistrationId;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (confirmTestRegId != null ? confirmTestRegId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfirmTestReg)) {
            return false;
        }
        ConfirmTestReg other = (ConfirmTestReg) object;
        if ((this.confirmTestRegId == null && other.confirmTestRegId != null) || (this.confirmTestRegId != null && !this.confirmTestRegId.equals(other.confirmTestRegId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.ConfirmTestReg[ confirmTestRegId=" + confirmTestRegId + " ]";
    }
    
}
