/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.flow.BO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtit_havm2
 */
@Entity
@Table(name = "NODE_TO_NODE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NodeToNode.findAll", query = "SELECT n FROM NodeToNode n"),
    @NamedQuery(name = "NodeToNode.findByPreviousId", query = "SELECT n FROM NodeToNode n WHERE n.nodeToNodePK.previousId = :previousId"),
    @NamedQuery(name = "NodeToNode.findByNextId", query = "SELECT n FROM NodeToNode n WHERE n.nodeToNodePK.nextId = :nextId"),
    @NamedQuery(name = "NodeToNode.findByAction", query = "SELECT n FROM NodeToNode n WHERE n.action = :action"),
    @NamedQuery(name = "NodeToNode.findByIsActive", query = "SELECT n FROM NodeToNode n WHERE n.isActive = :isActive")})
public class NodeToNode implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NodeToNodePK nodeToNodePK;
    @Column(name = "ACTION")
    private String action;
    @Column(name = "IS_ACTIVE")
    private Long isActive;

    public NodeToNode() {
    }

    public NodeToNode(NodeToNodePK nodeToNodePK) {
        this.nodeToNodePK = nodeToNodePK;
    }

    public NodeToNode(long previousId, long nextId) {
        this.nodeToNodePK = new NodeToNodePK(previousId, nextId);
    }

    public NodeToNodePK getNodeToNodePK() {
        return nodeToNodePK;
    }

    public void setNodeToNodePK(NodeToNodePK nodeToNodePK) {
        this.nodeToNodePK = nodeToNodePK;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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
        hash += (nodeToNodePK != null ? nodeToNodePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodeToNode)) {
            return false;
        }
        NodeToNode other = (NodeToNode) object;
        if ((this.nodeToNodePK == null && other.nodeToNodePK != null) || (this.nodeToNodePK != null && !this.nodeToNodePK.equals(other.nodeToNodePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.voffice.database.BO.NodeToNode[ nodeToNodePK=" + nodeToNodePK + " ]";
    }
    
}
