/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import java.io.Serializable;
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
@Table(name = "PARAMETER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parameter.findAll", query = "SELECT p FROM Parameter p"),
    @NamedQuery(name = "Parameter.findByParameterId", query = "SELECT p FROM Parameter p WHERE p.parameterId = :parameterId"),
    @NamedQuery(name = "Parameter.findByParameterCode", query = "SELECT p FROM Parameter p WHERE p.parameterCode = :parameterCode"),
    @NamedQuery(name = "Parameter.findByParameterName", query = "SELECT p FROM Parameter p WHERE p.parameterName = :parameterName"),
    @NamedQuery(name = "Parameter.findByParameterValue", query = "SELECT p FROM Parameter p WHERE p.parameterValue = :parameterValue"),
    @NamedQuery(name = "Parameter.findByParameterDataTypeId", query = "SELECT p FROM Parameter p WHERE p.parameterDataTypeId = :parameterDataTypeId"),
    @NamedQuery(name = "Parameter.findByParameterDataTypeName", query = "SELECT p FROM Parameter p WHERE p.parameterDataTypeName = :parameterDataTypeName"),
    @NamedQuery(name = "Parameter.findByIsActive", query = "SELECT p FROM Parameter p WHERE p.isActive = :isActive"),
    @NamedQuery(name = "Parameter.findByDescription", query = "SELECT p FROM Parameter p WHERE p.description = :description")})
public class Parameter implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "PARAMETER_SEQ", sequenceName = "PARAMETER_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARAMETER_SEQ")
    @Basic(optional = false)
    @Column(name = "PARAMETER_ID")
    private Long parameterId;
    @Column(name = "PARAMETER_CODE")
    private String parameterCode;
    @Column(name = "PARAMETER_NAME")
    private String parameterName;
    @Column(name = "PARAMETER_VALUE")
    private String parameterValue;
    @Column(name = "PARAMETER_DATA_TYPE_ID")
    private Long parameterDataTypeId;
    @Column(name = "PARAMETER_DATA_TYPE_NAME")
    private String parameterDataTypeName;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "DESCRIPTION")
    private String description;

    public Parameter() {
    }

    public Parameter(Long parameterId) {
        this.parameterId = parameterId;
    }

    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    public String getParameterCode() {
        return parameterCode;
    }

    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public Long getParameterDataTypeId() {
        return parameterDataTypeId;
    }

    public void setParameterDataTypeId(Long parameterDataTypeId) {
        this.parameterDataTypeId = parameterDataTypeId;
    }

    public String getParameterDataTypeName() {
        return parameterDataTypeName;
    }

    public void setParameterDataTypeName(String parameterDataTypeName) {
        this.parameterDataTypeName = parameterDataTypeName;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parameterId != null ? parameterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parameter)) {
            return false;
        }
        Parameter other = (Parameter) object;
        if ((this.parameterId == null && other.parameterId != null) || (this.parameterId != null && !this.parameterId.equals(other.parameterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.Parameter[ parameterId=" + parameterId + " ]";
    }
    
}
