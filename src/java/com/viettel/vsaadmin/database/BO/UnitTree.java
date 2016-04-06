/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.viettel.vsaadmin.database.BO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gpdn_trungnq7
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "UNIT_TREE")
@NamedQueries({
    @NamedQuery(name = "UnitTree.findAll", query = "SELECT u FROM UnitTree u"),
    @NamedQuery(name = "UnitTree.findByTreeId", query = "SELECT u FROM UnitTree u WHERE u.treeId = :treeId"),
    @NamedQuery(name = "UnitTree.findByTreeName", query = "SELECT u FROM UnitTree u WHERE u.treeName = :treeName"),
    @NamedQuery(name = "UnitTree.findByDescription", query = "SELECT u FROM UnitTree u WHERE u.description = :description")})
public class UnitTree implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TREE_ID")
    private Long treeId;
    @Basic(optional = false)
    @Column(name = "TREE_NAME")
    private String treeName;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "NODE_ID", referencedColumnName = "NODE_ID")
    @ManyToOne
    private UnitTreeNode unitTreeNode;

    public UnitTree() {
    }

    public UnitTree(Long treeId) {
        this.treeId = treeId;
    }

    public UnitTree(Long treeId, String treeName) {
        this.treeId = treeId;
        this.treeName = treeName;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UnitTreeNode getUnitTreeNode() {
        return unitTreeNode;
    }

    public void setUnitTreeNode(UnitTreeNode unitTreeNode) {
        this.unitTreeNode = unitTreeNode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (treeId != null ? treeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnitTree)) {
            return false;
        }
        UnitTree other = (UnitTree) object;
        if ((this.treeId == null && other.treeId != null) || (this.treeId != null && !this.treeId.equals(other.treeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.UnitTree[treeId=" + treeId + "]";
    }

}
