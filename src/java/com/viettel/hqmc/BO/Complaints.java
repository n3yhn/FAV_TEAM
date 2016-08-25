/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
import java.io.Serializable;
import java.lang.Long;
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
 * @author Administrator
 */
@Entity
@Table(name = "COMPLAINTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Complaints.findAll", query = "SELECT c FROM Complaints c"),
    @NamedQuery(name = "Complaints.findByFileCode", query = "SELECT c FROM Complaints c WHERE c.fileCode = :fileCode"),
    @NamedQuery(name = "Complaints.findByBussinessCode", query = "SELECT c FROM Complaints c WHERE c.bussinessCode = :bussinessCode"),
    @NamedQuery(name = "Complaints.findByOldDeptCode", query = "SELECT c FROM Complaints c WHERE c.oldDeptCode = :oldDeptCode"),
    @NamedQuery(name = "Complaints.findByCurrentState", query = "SELECT c FROM Complaints c WHERE c.currentState = :currentState"),
    @NamedQuery(name = "Complaints.findByNextDeptCode", query = "SELECT c FROM Complaints c WHERE c.nextDeptCode = :nextDeptCode"),
    @NamedQuery(name = "Complaints.findByDescription", query = "SELECT c FROM Complaints c WHERE c.description = :description"),
    @NamedQuery(name = "Complaints.findByComplaintsId", query = "SELECT c FROM Complaints c WHERE c.complaintsId = :complaintsId")})
public class Complaints implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "COMPLAINTS_SEQ", sequenceName = "COMPLAINTS_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPLAINTS_SEQ")
    @Basic(optional = false)
    @Column(name = "COMPLAINTS_ID")
    private Long complaintsId;

    @Column(name = "FILE_CODE")
    private String fileCode;
    @Column(name = "BUSSINESS_CODE")
    private String bussinessCode;
    @Column(name = "OLD_DEPT_CODE")
    private String oldDeptCode;
    @Column(name = "CURRENT_STATE")
    private Long currentState;
    @Column(name = "NEXT_DEPT_CODE")
    private String nextDeptCode;
    @Column(name = "DESCRIPTION")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    public Complaints() {
    }

    public Complaints(Long complaintsId) {
        this.complaintsId = complaintsId;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = StringUtils.removeEventHandlerJS(fileCode);
    }

    public String getBussinessCode() {
        return bussinessCode;
    }

    public void setBussinessCode(String bussinessCode) {
        this.bussinessCode = StringUtils.removeEventHandlerJS(bussinessCode);
    }

    public String getOldDeptCode() {
        return oldDeptCode;
    }

    public void setOldDeptCode(String oldDeptCode) {
        this.oldDeptCode = StringUtils.removeEventHandlerJS(oldDeptCode);
    }

    public Long getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Long currentState) {
        this.currentState = currentState;
    }

    public String getNextDeptCode() {
        return nextDeptCode;
    }

    public void setNextDeptCode(String nextDeptCode) {
        this.nextDeptCode = StringUtils.removeEventHandlerJS(nextDeptCode);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = StringUtils.removeEventHandlerJS(description);
    }

    public Long getComplaintsId() {
        return complaintsId;
    }

    public void setComplaintsId(Long complaintsId) {
        this.complaintsId = complaintsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (complaintsId != null ? complaintsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Complaints)) {
            return false;
        }
        Complaints other = (Complaints) object;
        if ((this.complaintsId == null && other.complaintsId != null) || (this.complaintsId != null && !this.complaintsId.equals(other.complaintsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.Complaints[ complaintsId=" + complaintsId + " ]";
    }

}
