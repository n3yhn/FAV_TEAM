/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.viettel.vsaadmin.database.BO;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gpdn_trungnq7
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "UNIT_TREE_NODE")
@NamedQueries({
    @NamedQuery(name = "UnitTreeNode.findAll", query = "SELECT u FROM UnitTreeNode u"),
    @NamedQuery(name = "UnitTreeNode.findByNodeId", query = "SELECT u FROM UnitTreeNode u WHERE u.nodeId = :nodeId"),
    @NamedQuery(name = "UnitTreeNode.findByItemId", query = "SELECT u FROM UnitTreeNode u WHERE u.itemId = :itemId"),
    @NamedQuery(name = "UnitTreeNode.findByNodeType", query = "SELECT u FROM UnitTreeNode u WHERE u.nodeType = :nodeType"),
    @NamedQuery(name = "UnitTreeNode.findByNodeName", query = "SELECT u FROM UnitTreeNode u WHERE u.nodeName = :nodeName")})
public class UnitTreeNode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NODE_ID")
    private Long nodeId;
    @Basic(optional = false)
    @Column(name = "ITEM_ID")
    private long itemId;
    @Basic(optional = false)
    @Column(name = "NODE_TYPE")
    private short nodeType;
    @Basic(optional = false)
    @Column(name = "NODE_NAME")
    private String nodeName;
    @OneToMany(mappedBy = "unitTreeNode")
    private Collection<UnitTreeNode> unitTreeNodeCollection;
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "NODE_ID")
    @ManyToOne
    private UnitTreeNode unitTreeNode;
    @OneToMany(mappedBy = "unitTreeNode")
    private Collection<UnitTree> unitTreeCollection;

    public UnitTreeNode() {
    }

    public UnitTreeNode(Long nodeId) {
        this.nodeId = nodeId;
    }

    public UnitTreeNode(Long nodeId, long itemId, short nodeType, String nodeName) {
        this.nodeId = nodeId;
        this.itemId = itemId;
        this.nodeType = nodeType;
        this.nodeName = nodeName;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public short getNodeType() {
        return nodeType;
    }

    public void setNodeType(short nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Collection<UnitTreeNode> getUnitTreeNodeCollection() {
        return unitTreeNodeCollection;
    }

    public void setUnitTreeNodeCollection(Collection<UnitTreeNode> unitTreeNodeCollection) {
        this.unitTreeNodeCollection = unitTreeNodeCollection;
    }

    public UnitTreeNode getUnitTreeNode() {
        return unitTreeNode;
    }

    public void setUnitTreeNode(UnitTreeNode unitTreeNode) {
        this.unitTreeNode = unitTreeNode;
    }

    public Collection<UnitTree> getUnitTreeCollection() {
        return unitTreeCollection;
    }

    public void setUnitTreeCollection(Collection<UnitTree> unitTreeCollection) {
        this.unitTreeCollection = unitTreeCollection;
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
        if (!(object instanceof UnitTreeNode)) {
            return false;
        }
        UnitTreeNode other = (UnitTreeNode) object;
        if ((this.nodeId == null && other.nodeId != null) || (this.nodeId != null && !this.nodeId.equals(other.nodeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.UnitTreeNode[nodeId=" + nodeId + "]";
    }

}
