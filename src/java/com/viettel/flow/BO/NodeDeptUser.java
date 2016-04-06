/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.flow.BO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author vtit_havm2
 */
@Entity
@Table(name = "NODE_DEPT_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NodeDeptUser.findAll", query = "SELECT n FROM NodeDeptUser n"),
    @NamedQuery(name = "NodeDeptUser.findByNodeDeptUserId", query = "SELECT n FROM NodeDeptUser n WHERE n.nodeDeptUserId = :nodeDeptUserId"),
    @NamedQuery(name = "NodeDeptUser.findByNodeName", query = "SELECT n FROM NodeDeptUser n WHERE n.nodeName = :nodeName"),
    @NamedQuery(name = "NodeDeptUser.findByDeptId", query = "SELECT n FROM NodeDeptUser n WHERE n.deptId = :deptId"),
    @NamedQuery(name = "NodeDeptUser.findByDeptName", query = "SELECT n FROM NodeDeptUser n WHERE n.deptName = :deptName"),
    @NamedQuery(name = "NodeDeptUser.findByUserId", query = "SELECT n FROM NodeDeptUser n WHERE n.userId = :userId"),
    @NamedQuery(name = "NodeDeptUser.findByUserName", query = "SELECT n FROM NodeDeptUser n WHERE n.userName = :userName"),
    @NamedQuery(name = "NodeDeptUser.findByProcessType", query = "SELECT n FROM NodeDeptUser n WHERE n.processType = :processType"),
    @NamedQuery(name = "NodeDeptUser.findByNodeId", query = "SELECT n FROM NodeDeptUser n WHERE n.nodeId = :nodeId")})
public class NodeDeptUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "NODE_DEPT_USER_SEQ", sequenceName = "NODE_DEPT_USER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NODE_DEPT_USER_SEQ")
    @Column(name = "NODE_DEPT_USER_ID")
    private Long nodeDeptUserId;
    @Column(name = "NODE_NAME")
    private String nodeName;
    @Column(name = "DEPT_ID")
    private Long deptId;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PROCESS_TYPE")
    private Long processType;
    @Basic(optional = false)
    @Column(name = "NODE_ID")
    private Long nodeId;

    public NodeDeptUser() {
    }

    public NodeDeptUser(Long nodeDeptUserId) {
        this.nodeDeptUserId = nodeDeptUserId;
    }

    public NodeDeptUser(Long nodeDeptUserId, long nodeId) {
        this.nodeDeptUserId = nodeDeptUserId;
        this.nodeId = nodeId;
    }

    public Long getNodeDeptUserId() {
        return nodeDeptUserId;
    }

    public void setNodeDeptUserId(Long nodeDeptUserId) {
        this.nodeDeptUserId = nodeDeptUserId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getProcessType() {
        return processType;
    }

    public void setProcessType(Long processType) {
        this.processType = processType;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nodeDeptUserId != null ? nodeDeptUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodeDeptUser)) {
            return false;
        }
        NodeDeptUser other = (NodeDeptUser) object;
        if ((this.nodeDeptUserId == null && other.nodeDeptUserId != null) || (this.nodeDeptUserId != null && !this.nodeDeptUserId.equals(other.nodeDeptUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.voffice.database.BO.NodeDeptUser[ nodeDeptUserId=" + nodeDeptUserId + " ]";
    }
    
}
