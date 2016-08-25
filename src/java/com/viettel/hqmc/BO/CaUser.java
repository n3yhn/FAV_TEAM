/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BINHNT53
 */
@Entity
@Table(name = "CA_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaUser.findAll", query = "SELECT c FROM CaUser c"),
    @NamedQuery(name = "CaUser.findByCaUserId", query = "SELECT c FROM CaUser c WHERE c.caUserId = :caUserId"),
    @NamedQuery(name = "CaUser.findByCaSerial", query = "SELECT c FROM CaUser c WHERE c.caSerial = :caSerial"),
    @NamedQuery(name = "CaUser.findByUserName", query = "SELECT c FROM CaUser c WHERE c.userName = :userName")})
public class CaUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "CA_USER_SEQ", sequenceName = "CA_USER_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CA_USER_SEQ")
    @Basic(optional = false)
    @Column(name = "CA_USER_ID")
    private Long caUserId;
    @Column(name = "CA_SERIAL")
    private String caSerial;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "STATUS")
    private int status;
    @Column(name = "BUSINESS_ID")
    private Long businessId;
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
    @Column(name = "NAME")
    private String name;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "COMMAND")
    private String command;
    @Column(name = "STAMPER")
    private String stamper;
    @Column(name = "SIGNATURE")
    private String signature;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CaUser() {
    }

    public CaUser(Long caUserId) {
        this.caUserId = caUserId;
    }

    public Long getCaUserId() {
        return caUserId;
    }

    public void setCaUserId(Long caUserId) {
        this.caUserId = caUserId;
    }

    public String getCaSerial() {
        return caSerial;
    }

    public void setCaSerial(String caSerial) {
        this.caSerial = StringUtils.removeEventHandlerJS(caSerial);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = StringUtils.removeEventHandlerJS(userName);
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.removeEventHandlerJS(name);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = StringUtils.removeEventHandlerJS(position);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = StringUtils.removeEventHandlerJS(command);
    }

    public String getStamper() {
        return stamper;
    }

    public void setStamper(String stamper) {
        this.stamper = StringUtils.removeEventHandlerJS(stamper);
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = StringUtils.removeEventHandlerJS(signature);
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (caUserId != null ? caUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaUser)) {
            return false;
        }
        CaUser other = (CaUser) object;
        if ((this.caUserId == null && other.caUserId != null) || (this.caUserId != null && !this.caUserId.equals(other.caUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.CaUser[ caUserId=" + caUserId + " ]";
    }

}
