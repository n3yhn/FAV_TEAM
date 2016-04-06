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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtit_binhnt53
 */
@Entity
@Table(name = "PROCEDURE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procedure.findAll", query = "SELECT c FROM Procedure c"),
    @NamedQuery(name = "Procedure.findByCategoryId", query = "SELECT c FROM Procedure c WHERE c.procedureId = :procedureId"),
    @NamedQuery(name = "Procedure.findByType", query = "SELECT c FROM Procedure c WHERE c.type = :type"),
    @NamedQuery(name = "Procedure.findByName", query = "SELECT c FROM Procedure c WHERE c.name = :name"),
    @NamedQuery(name = "Procedure.findByCode", query = "SELECT c FROM Procedure c WHERE c.code = :code"),
    @NamedQuery(name = "Procedure.findByIsActive", query = "SELECT c FROM Procedure c WHERE c.isActive = :isActive"),
    @NamedQuery(name = "Procedure.findByCreatedBy", query = "SELECT c FROM Procedure c WHERE c.createdBy = :createdBy"),
    @NamedQuery(name = "Procedure.findByCreateDate", query = "SELECT c FROM Procedure c WHERE c.createDate = :createDate"),
    @NamedQuery(name = "Procedure.findByModifiedBy", query = "SELECT c FROM Procedure c WHERE c.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "Procedure.findByModifyDate", query = "SELECT c FROM Procedure c WHERE c.modifyDate = :modifyDate"),
    @NamedQuery(name = "Procedure.findByDescription", query = "SELECT c FROM Procedure c WHERE c.description = :description")})
public class Procedure implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "PROCEDURE_SEQ", sequenceName = "PROCEDURE_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROCEDURE_SEQ")
    @Basic(optional = false)
    @Column(name = "PROCEDURE_ID")
    private Long procedureId;
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;
    @Column(name = "IS_ACTIVE")
    private String isActive;
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;
    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "FILE_LIST")
    private String fileList;
    @Column(name = "DEADLINE")
    private Long deadline;
    @Column(name = "FEE")
    private Long fee;
    @Column(name = "TYPE_FEE")
    private Long typeFee;

    public Procedure() {
    }

    public Procedure(Long procedureId) {
        this.procedureId = procedureId;
    }

    public Procedure(Long categoryId, String type, String name, String code) {
        this.procedureId = categoryId;
        this.type = type;
        this.name = name;
        this.code = code;
    }

    public Procedure(Long procedureId, String name) {
        this.procedureId = procedureId;
        this.name = name;
    }

    public Procedure(Long procedureId, String type, String name, String code, String description) {
        this.procedureId = procedureId;
        this.type = type;
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId = procedureId;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileList() {
        return fileList;
    }

    public void setFileList(String fileList) {
        this.fileList = fileList;
    }

    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    public Long getTypeFee() {
        return typeFee;
    }

    public void setTypeFee(Long typeFee) {
        this.typeFee = typeFee;
    }
    
    
    // phi cap giay cong bo

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (procedureId != null ? procedureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procedure)) {
            return false;
        }
        Procedure other = (Procedure) object;
        if ((this.procedureId == null && other.procedureId != null) || (this.procedureId != null && !this.procedureId.equals(other.procedureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.Procedure[ procedureId=" + procedureId + " ]";
    }

}
