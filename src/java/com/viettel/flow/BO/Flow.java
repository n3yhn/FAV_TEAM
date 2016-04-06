/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.flow.BO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtit_havm2
 */
@Entity
@Table(name = "FLOW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flow.findAll", query = "SELECT f FROM Flow f"),
    @NamedQuery(name = "Flow.findByFlowId", query = "SELECT f FROM Flow f WHERE f.flowId = :flowId"),
    @NamedQuery(name = "Flow.findByFlowName", query = "SELECT f FROM Flow f WHERE f.flowName = :flowName"),
    @NamedQuery(name = "Flow.findByDeptId", query = "SELECT f FROM Flow f WHERE f.deptId = :deptId"),
    @NamedQuery(name = "Flow.findByDescription", query = "SELECT f FROM Flow f WHERE f.description = :description"),
    @NamedQuery(name = "Flow.findByFlowType", query = "SELECT f FROM Flow f WHERE f.flowType = :flowType"),
    @NamedQuery(name = "Flow.findByIsActive", query = "SELECT f FROM Flow f WHERE f.isActive = :isActive")})
public class Flow implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "FLOW_SEQ", sequenceName = "FLOW_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLOW_SEQ")
    @Column(name = "FLOW_ID")
    private Long flowId;
    @Basic(optional = false)
    @Column(name = "FLOW_NAME")
    private String flowName;
    @Basic(optional = false)
    @Column(name = "DEPT_ID")
    private Long deptId;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "FLOW_TYPE")
    private Long flowType;
    @Column(name = "FLOW_TYPE_NAME")
    private String flowTypeName;
    @Column(name = "IS_ACTIVE")
    private Long isActive;

    public Flow() {
    }

    public Flow(Long flowId) {
        this.flowId = flowId;
    }

    public Flow(Long flowId, String flowName, long deptId, long flowType) {
        this.flowId = flowId;
        this.flowName = flowName;
        this.deptId = deptId;
        this.flowType = flowType;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFlowType() {
        return flowType;
    }

    public void setFlowType(Long flowType) {
        this.flowType = flowType;
    }

    public String getFlowTypeName() {
        return flowTypeName;
    }

    public void setFlowTypeName(String flowTypeName) {
        this.flowTypeName = flowTypeName;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flowId != null ? flowId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flow)) {
            return false;
        }
        Flow other = (Flow) object;
        if ((this.flowId == null && other.flowId != null) || (this.flowId != null && !this.flowId.equals(other.flowId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.voffice.database.BO.Flow[ flowId=" + flowId + " ]";
    }
    
}
