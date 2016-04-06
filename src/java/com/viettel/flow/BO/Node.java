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
@Table(name = "NODE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Node.findAll", query = "SELECT n FROM Node n"),
    @NamedQuery(name = "Node.findByNodeId", query = "SELECT n FROM Node n WHERE n.nodeId = :nodeId"),
    @NamedQuery(name = "Node.findByNodeName", query = "SELECT n FROM Node n WHERE n.nodeName = :nodeName"),
    @NamedQuery(name = "Node.findByXpoint", query = "SELECT n FROM Node n WHERE n.xpoint = :xpoint"),
    @NamedQuery(name = "Node.findByYpoint", query = "SELECT n FROM Node n WHERE n.ypoint = :ypoint"),
    @NamedQuery(name = "Node.findByIsActive", query = "SELECT n FROM Node n WHERE n.isActive = :isActive"),
    @NamedQuery(name = "Node.findByFlowId", query = "SELECT n FROM Node n WHERE n.flowId = :flowId")})
public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "NODE_SEQ", sequenceName = "NODE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NODE_SEQ")
    @Column(name = "NODE_ID")
    private Long nodeId;
    @Basic(optional = false)
    @Column(name = "NODE_NAME")
    private String nodeName;
    @Column(name = "XPOINT")
    private Long xpoint;
    @Column(name = "YPOINT")
    private Long ypoint;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Basic(optional = false)
    @Column(name = "FLOW_ID")
    private Long flowId;

    public Node() {
    }

    public Node(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Node(Long nodeId, String nodeName, long flowId) {
        this.nodeId = nodeId;
        this.nodeName = nodeName;
        this.flowId = flowId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Long getXpoint() {
        return xpoint;
    }

    public void setXpoint(Long xpoint) {
        this.xpoint = xpoint;
    }

    public Long getYpoint() {
        return ypoint;
    }

    public void setYpoint(Long ypoint) {
        this.ypoint = ypoint;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nodeId != null ? nodeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Node)) {
            return false;
        }
        Node other = (Node) object;
        if ((this.nodeId == null && other.nodeId != null) || (this.nodeId != null && !this.nodeId.equals(other.nodeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.voffice.database.BO.Node[ nodeId=" + nodeId + " ]";
    }
    
}
