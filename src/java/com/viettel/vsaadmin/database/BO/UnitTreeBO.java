/* 
 * Copyright (C) 2010 Viettel Telecom. All rights reserved. 
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.vsaadmin.database.BO;

import com.viettel.database.BO.BasicBO;

/** 
 * @author 
 * @since Thu Jan 27 08:29:53 ICT 2011
 * @version 1.0 
 */
public class UnitTreeBO extends BasicBO {

    private Long treeId;
    private String description;
    private Long nodeId;
    private String treeName;
    private Long appId;
    private Long rootDeptId;

    public Long getRootDeptId() {
        return this.rootDeptId;
    }

    public void setRootDeptId(Long rootDeptId) {
        this.rootDeptId = rootDeptId;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public UnitTreeBO() {
    }

    public UnitTreeBO(Long treeId) {
        this.treeId = treeId;
    }
}
